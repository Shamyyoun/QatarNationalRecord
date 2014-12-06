package com.noqwerty.sqw.datamodels;

import android.graphics.Bitmap;

public class Sponsor {
	private String name;
	private int thumbnailResource;

	public Sponsor(String name, int thumbnailResource) {
		this.name = name;
		this.thumbnailResource = thumbnailResource;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setThumbnailResource(int thumbnailResource) {
		this.thumbnailResource = thumbnailResource;
	}

	public int getThumbnailResource() {
		return thumbnailResource;
	}
}
