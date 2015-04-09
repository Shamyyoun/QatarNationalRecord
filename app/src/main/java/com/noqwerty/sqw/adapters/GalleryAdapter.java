package com.noqwerty.sqw.adapters;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.noqwerty.sqw.R;
import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.datamodels.Event;
import com.squareup.picasso.Picasso;

public class GalleryAdapter extends BaseAdapter {
	private Activity activity;
	private Event event;

	public GalleryAdapter(Activity activity, Event event) {
		this.activity = activity;
		this.event = event;
	}

	public int getCount() {
		return event.getImageNames().length;
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
		imageView.setAdjustViewBounds(true);
		imageView.setScaleType(ScaleType.FIT_XY);

		// get image path
		String imagePath = activity.getFilesDir() + "/"
				+ Constants.FOLDER_EVENTS_IMAGES + "/" + event.getImageNames()[position];
		File file = new File(imagePath);
		
		// check if image exists or not
		if (file.exists()) {
			Picasso.with(activity).load(file).into(imageView);
		} else {
			Picasso.with(activity).load(R.drawable.def_photo).into(imageView);
		}

		return imageView;
	}
}