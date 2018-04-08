package br.com.rr.feed.service.impl;

import br.com.rr.feed.enumeration.DescriptionType;
import br.com.rr.feed.model.Item;
import br.com.rr.feed.model.ItemDescription;
import br.com.rr.feed.repository.ItemRepository;
import br.com.rr.feed.service.FeedService;
import br.com.rr.feed.service.XMLParser;
import br.com.rr.feed.vo.FeedVO;
import br.com.rr.feed.vo.ItemDescriptionVO;
import br.com.rr.feed.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private XMLParser xmlParser;

    @Override
    @Transactional
    public void initFeed(String feedUrl) {
        try {
            FeedVO feed = this.xmlParser.parseToJSON(feedUrl);
            feed.getItems().stream().forEach(itemVO -> {
                Item item = voToEntity(itemVO);
                itemRepository.save(item);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public FeedVO getFeed() {
        FeedVO feedVO = new FeedVO();
        List<Item> allItems = itemRepository.findAll();

        allItems.stream().forEach(item -> {
            ItemVO itemVO = new ItemVO(item.getTitle(), item.getLink());
            feedVO.getItems().add(itemVO);

            item.getDescriptions().stream().forEach(itemDescription -> {
                String contentString = itemDescription.getContent();
                Serializable content = contentString;

                if(itemDescription.getType().equals(DescriptionType.LINKS)) {
                    content = contentString.substring(1, contentString.length() - 1).split(", ");
                }

                itemVO.getDescriptions().add(new ItemDescriptionVO(itemDescription.getType(), content));
            });
        });

        return feedVO;
    }

    @Override
    @Transactional
    public void addItem(ItemVO itemVO) {
        Item item = voToEntity(itemVO);
        itemRepository.save(item);
    }

    private Item voToEntity(ItemVO itemVO) {
        Item item = new Item(itemVO.getTitle(), itemVO.getLink());

        itemVO.getDescriptions().stream().forEach(itemDescriptionVO -> {
            item.getDescriptions().add(new ItemDescription(item,
                    itemDescriptionVO.getType(),
                    itemDescriptionVO.getContent().toString()));
        });
        return item;
    }
}
