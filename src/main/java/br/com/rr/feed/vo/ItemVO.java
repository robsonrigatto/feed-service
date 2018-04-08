package br.com.rr.feed.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;

@JsonRootName("item")
public class ItemVO {

	private String title;
	private String link;

	@JsonProperty("description")
	private List<ItemDescriptionVO> descriptions;

	public ItemVO() { descriptions = new ArrayList<>();	}

	public ItemVO(String title, String link) {
		this();
		this.title = title;
		this.link = link;
	}

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
	public List<ItemDescriptionVO> getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(List<ItemDescriptionVO> descriptions) {
		this.descriptions = descriptions;
	}
}
