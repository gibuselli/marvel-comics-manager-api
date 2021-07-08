package com.zup.comic.client;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicPrice {

	String type;
	BigDecimal price;	
	
	public ComicPrice(String type, BigDecimal price) {
		this.type = type;
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	
	
}
