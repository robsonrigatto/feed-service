package br.com.rr.feed.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.rr.feed.enumeration.DescriptionType;
import br.com.rr.feed.vo.FeedVO;
import br.com.rr.feed.vo.ItemVO;
import br.com.rr.feed.vo.ItemDescriptionVO;

@Service
public class XMLParser {
	
	public FeedVO parseToJSON(String path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(this.getClass().getClassLoader().getResourceAsStream(path));
		return parseDocument(document);
	}

	public FeedVO parseDocument(Document document) {
		document.getDocumentElement().normalize();
		
		FeedVO feed = new FeedVO();

		NodeList nList = document.getElementsByTagName("item");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			Element eElement = (Element) nNode;
			String title = eElement.getElementsByTagName("title").item(0).getTextContent().trim();
			String link = eElement.getElementsByTagName("link").item(0).getTextContent();
			String descriptionHtml = eElement.getElementsByTagName("description").item(0).getTextContent();
			
			ItemVO item = new ItemVO();
			item.setTitle(title); item.setLink(link);
			item.setDescriptions(this.parseDescriptions(descriptionHtml));
			
			feed.getItems().add(item);
		}
		
		return feed;
	}
	
	private List<ItemDescriptionVO> parseDescriptions(String descriptionHtml) {
		List<ItemDescriptionVO> descriptions = new ArrayList<>();
		org.jsoup.nodes.Document docHtml = Jsoup.parse(descriptionHtml);
		
		this.parseTexts(descriptions, docHtml);
		this.parseImages(descriptions, docHtml);
		this.parseLinks(descriptions, docHtml);
		
		return descriptions;
	}

	private void parseTexts(List<ItemDescriptionVO> descriptions, org.jsoup.nodes.Document docHtml) {
		Elements texts = docHtml.select("p");
		for (org.jsoup.nodes.Element text : texts) {
			String content = text.text().replace("\u00a0", StringUtils.EMPTY).trim();
			
			if(StringUtils.isNotBlank(content)) {
				ItemDescriptionVO id = new ItemDescriptionVO();
				id.setType(DescriptionType.TEXT);
				id.setContent(content);
				descriptions.add(id);
			}
		}
	}

	private void parseImages(List<ItemDescriptionVO> descriptions, org.jsoup.nodes.Document docHtml) {
		Elements images = docHtml.select("div img");
		for (org.jsoup.nodes.Element img : images) {
			ItemDescriptionVO id = new ItemDescriptionVO();
			String src = img.attr("src");
			
			id.setType(DescriptionType.IMAGE);
			id.setContent(src);
			descriptions.add(id);
		}
	}

	private void parseLinks(List<ItemDescriptionVO> descriptions, org.jsoup.nodes.Document docHtml) {
		Elements links = docHtml.select("div ul");
		for (org.jsoup.nodes.Element link : links) {
			Elements linksElement = link.select("li a");
			
			if(!linksElement.isEmpty()) {
				ItemDescriptionVO id = new ItemDescriptionVO();
				id.setType(DescriptionType.LINKS);
				List<String> linksUrl = new ArrayList<>();
				
				for(org.jsoup.nodes.Element linkElement : linksElement) {
					linksUrl.add(linkElement.attr("href"));
				}
				id.setContent((Serializable) linksUrl);
				descriptions.add(id);
			}
		}
	}
}
