package com.zup.comic.client;

public class Items {

	String resourceURI;
	String name;
	String role;

	public Items(String resourceURI, String name, String role) {
		this.resourceURI = resourceURI;
		this.name = name;
		this.role = role;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

}
