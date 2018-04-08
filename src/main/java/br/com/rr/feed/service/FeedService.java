package br.com.rr.feed.service;

import br.com.rr.feed.vo.FeedVO;
import br.com.rr.feed.vo.ItemVO;

public interface FeedService {

    void initFeed(String feedUrl);
    FeedVO getFeed();
    void addItem(ItemVO itemVO);
}
