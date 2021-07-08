package com.zup.comic.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

	Long id;
	String title;
	String description;
	String isbn;
	
	List<ComicPrice> prices;
	
	ComicCreators creators;

	public Result(Long id, String title, String description, String isbn, List<ComicPrice> prices,
			ComicCreators creators) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.prices = prices;
		this.creators = creators;
	}

	public Result() {
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getIsbn() {
		return isbn;
	}

	public List<ComicPrice> getPrices() {
		return prices;
	}

	public ComicCreators getCreators() {
		return creators;
	}
	
	
	
	

	
	
}