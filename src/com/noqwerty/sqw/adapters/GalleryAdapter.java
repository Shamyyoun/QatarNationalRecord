package com.noqwerty.sqw.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.noqwerty.sqw.R;

public class GalleryAdapter extends BaseAdapter {
	private Activity activity;
	private int[] imagesResources;

	public GalleryAdapter(Activity activity, int[] imagesResources) {
		this.activity = activity;
		this.imagesResources = imagesResources;
	}

	public int getCount() {
		return imagesResources.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(activity);
		imageView.setLayoutParams(new Gallery.LayoutParams((int) activity
				.getResources().getDimension(R.dimen.gallery_image_width),
				(int) activity.getResources().getDimension(
						R.dimen.gallery_image_width)));
		imageView.setImageResource(imagesResources[position]);
		return imageView;
	}
}