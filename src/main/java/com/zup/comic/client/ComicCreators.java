package com.zup.comic.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicCreators {

	int available;
	
	String collectionURI;
	
	List<Items> items;

	public ComicCreators(int available, String collectionURI, List<Items> items) {
		this.available = available;
		this.collectionURI = collectionURI;
		this.items = items;
	}

	public int getAvailable() {
		return available;
	}

	public String getCollectionURI() {
		return collectionURI;
	}

	public List<Items> getItems() {
		return items;
	}
	
	
}
