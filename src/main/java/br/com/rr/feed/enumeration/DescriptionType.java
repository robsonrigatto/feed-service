package br.com.rr.feed.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DescriptionType {
	
	IMAGE,
	TEXT,
	LINKS;
	
	@JsonValue
	public String getValue() {
		return this.name().toLowerCase();
	}

}
