package com.zup.comic.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	List<Result> results;

	public List<Result> getResults() {
		return results;
	}
}