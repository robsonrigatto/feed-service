package br.com.rr.feed.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Feed {

	@JsonProperty("feed")
	private	List<Item> items = new ArrayList<>();

	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
