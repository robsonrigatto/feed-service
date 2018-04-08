package br.com.rr.feed.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedVO {

	@JsonProperty("feed")
	private	List<ItemVO> items = new ArrayList<>();

	public List<ItemVO> getItems() {
		return items;
	}
	public void setItems(List<ItemVO> items) {
		this.items = items;
	}
}
