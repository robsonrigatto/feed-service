package br.com.rr.feed.vo;

import br.com.rr.feed.enumeration.DescriptionType;
import br.com.rr.feed.jackson.ItemDescriptionDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize(using = ItemDescriptionDeserializer.class)
public class ItemDescriptionVO {

	@JsonProperty("type")
	private DescriptionType type;

	@JsonProperty("content")
	private Serializable content;

	public ItemDescriptionVO() { }

	public ItemDescriptionVO(DescriptionType type, Serializable content) {
		this.type = type;
		this.content = content;
	}

	public DescriptionType getType() {
		return type;
	}
	public void setType(DescriptionType type) {
		this.type = type;
	}
	public Serializable getContent() {
		return content;
	}
	public void setContent(Serializable content) {
		this.content = content;
	}
}
