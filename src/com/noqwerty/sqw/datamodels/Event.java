package com.noqwerty.sqw.datamodels;


public class Event {
	private String title;
	private String description;
	private int[] imagesResources;

	public Event(String title, String description, int[] imagesResources) {
		this.title = title;
		this.description = description;
		this.imagesResources = imagesResources;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setImagesResources(int[] imagesResources) {
		this.imagesResources = imagesResources;
	}

	public int[] getImagesResources() {
		return imagesResources;
	}
}
