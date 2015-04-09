package com.noqwerty.sqw.datamodels;

import android.graphics.Bitmap;


public class Sponsor {
	private String name;
	private String logoName;
	private String congratName;
	private Bitmap logo;
	
	// flag for listview 
	private boolean visible;

	public Sponsor(String name, String logoName, String congratName) {
		this.name = name;
		this.logoName = logoName;
		this.congratName = congratName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	
	public String getLogoName() {
		return logoName;
	}
	
	public void setCongratName(String congratName) {
		this.congratName = congratName;
	}
	
	public String getCongratName() {
		return congratName;
	}
	
	public void setLogo(Bitmap logo) {
		this.logo = logo;
	}
	
	public Bitmap getLogo() {
		return logo;
	}
	
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
}
