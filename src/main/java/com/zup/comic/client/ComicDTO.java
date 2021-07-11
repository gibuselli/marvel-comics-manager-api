package com.zup.comic.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicDTO {

	private Data data;

	public Data getData() {
		return data;
	}
}