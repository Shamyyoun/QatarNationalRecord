package com.noqwerty.sqw.datamodels;


public class President {
	private String name;
	private String description;
	private int imageResource;

	public President(String name, String description, int thumbnailResource) {
		this.name = name;
		this.description = description;
		this.imageResource = thumbnailResource;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}
	
	public int getImageResource() {
		return imageResource;
	}
}
