package com.noqwerty.sqw.datamodels;

import android.graphics.Bitmap;

public class Event {
	private String title;
	private String[] imageNames;
	private Bitmap[] images;
	
	// flag array for list view
	private boolean[] imagesVisible;

	public Event(String title, String[] imageNames) {
		this.title = title;
		this.imageNames = imageNames;
		
		images = new Bitmap[imageNames.length];
		imagesVisible = new boolean[imageNames.length];
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setImageNames(String[] imageNames) {
		this.imageNames = imageNames;
	}

	public String[] getImageNames() {
		return imageNames;
	}
	
	public Bitmap[] getImages() {
		return images;
	}
	
	public boolean[] getImagesVisible() {
		return imagesVisible;
	}
}
