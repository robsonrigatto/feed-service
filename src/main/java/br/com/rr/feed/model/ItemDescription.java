package br.com.rr.feed.model;

import java.io.Serializable;

import br.com.rr.feed.enumeration.DescriptionType;

public class ItemDescription {

	private DescriptionType type;
	private Serializable content;
	
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
