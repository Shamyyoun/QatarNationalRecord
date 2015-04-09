package com.noqwerty.sqw.datamodels;

public class Project {
	private String name;
	private String description;
	private String link;
	private int imageResource;

	public Project(String name, String description, String link,
			int thumbnailResource) {
		this.name = name;
		this.description = description;
		this.link = link;
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

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}

	public int getImageResource() {
		return imageResource;
	}
}
