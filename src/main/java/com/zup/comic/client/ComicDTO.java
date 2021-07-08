package com.zup.comic.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicDTO {

	private Data data;

	public Data getData() {
		return data;
	}
}

//@JsonIgnoreProperties(ignoreUnknown = true)
//class Data {
//
//	List<Result> results;
//
//	public List<Result> getResults() {
//		return results;
//	}
//}

//@JsonIgnoreProperties(ignoreUnknown = true)
//class Result {
//
//	Long id;
//	String title;
//	String description;
//	String isbn;
//	
//	List<ComicPrice> prices;
//	
//	ComicCreators creators;
//
//	public Result(Long id, String title, String description, String isbn, List<ComicPrice> prices,
//			ComicCreators creators) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.description = description;
//		this.isbn = isbn;
//		this.prices = prices;
//		this.creators = creators;
//	}
//
//	public Result() {
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public String getIsbn() {
//		return isbn;
//	}
//
//	public List<ComicPrice> getPrices() {
//		return prices;
//	}
//
//	public ComicCreators getCreators() {
//		return creators;
//	}
//	
//	
//	
//	
//
//	
//	
//}