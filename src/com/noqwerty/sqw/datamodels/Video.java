package com.noqwerty.sqw.datamodels;

public class Video {
	private String title;
	private String link;

	public Video(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLink() {
		return link;
	}
}
