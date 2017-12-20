package br.com.rr.feed.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	private String title;
	private String link;
	
	@JsonProperty("description")
	private List<ItemDescription> descriptions;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<ItemDescription> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<ItemDescription> descriptions) {
		this.descriptions = descriptions;
	}
}
