package com.noqwerty.sqw.adapters;

import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.noqwerty.sqw.AppController;
import com.noqwerty.sqw.R;
import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.datamodels.Event;
import com.noqwerty.sqw.utils.FileUtil;
import com.noqwerty.sqw.utils.ViewUtil;
import com.squareup.picasso.Picasso;

public class EventThumbnailsAdapter extends
		RecyclerView.Adapter<EventThumbnailsAdapter.ViewHolder> {
	private Activity activity;
	private Event event;
	private int layoutResourceId;

	class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView imageThumbnail;
		public TextView textPosition;

		public ViewHolder(View view) {
			super(view);
			imageThumbnail = (ImageView) view
					.findViewById(R.id.image_thumbnail);
			textPosition = (TextView) view.findViewById(R.id.text_position);
		}
	}

	public EventThumbnailsAdapter(Activity activity, Event event,
			int layoutResourceId) {
		this.activity = activity;
		this.event = event;
		this.layoutResourceId = layoutResourceId;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		final View view = LayoutInflater.from(activity).inflate(
				layoutResourceId, parent, false);
		ViewHolder holder = new ViewHolder(view);

		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.textPosition.setText("" + position);
		ViewUtil.showView(holder.imageThumbnail, true, 0);
		event.getImagesVisible()[position] = true;

		// check if image loaded in runtime
		if (event.getImages()[position] != null) {
			// set it
			holder.imageThumbnail.setImageBitmap(event.getImages()[position]);
		} else {
			// check if image exists in HD or not
			String imagePath = activity.getFilesDir() + "/"
					+ Constants.FOLDER_EVENTS_IMAGES + "/"
					+ event.getImageNames()[position];
			File file = new File(imagePath);

			if (file.exists()) {
				// load it from HD
				new ImageLoader(position, file, holder).execute();
			} else {
				// download it first
				new ImageDownloader(position, holder).execute();
			}
		}

		holder.imageThumbnail.setImageBitmap(event.getImages()[position]);
	}

	@Override
	public int getItemCount() {
		return event.getImageNames().length;
	}

	@Override
	public void onViewDetachedFromWindow(ViewHolder holder) {
		super.onViewDetachedFromWindow(holder);

		int position;
		try {
			position = Integer.parseInt(holder.textPosition.getText()
					.toString());
		} catch (NumberFormatException e) {
			position = 0;
		}

		event.getImagesVisible()[position] = false;
	}

	/*
	 * used to load image from HD
	 */
	private class ImageLoader extends AsyncTask<Void, Void, Void> {
		private int imagePosition;
		private File imageFile;
		private ViewHolder viewHolder;

		private Bitmap image;

		public ImageLoader(int imagePosition, File imageFile,
				ViewHolder viewHolder) {
			this.imagePosition = imagePosition;
			this.imageFile = imageFile;
			this.viewHolder = viewHolder;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			ViewUtil.showView(viewHolder.imageThumbnail, false);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				image = Picasso.with(activity).load(imageFile).get();
			} catch (Exception e) {
				e.printStackTrace();
				image = BitmapFactory.decodeResource(activity.getResources(),
						R.drawable.def_photo);
			}

			event.getImages()[imagePosition] = image;

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			// check if item is visible
			if (event.getImagesVisible()[imagePosition]) {
				viewHolder.imageThumbnail.setImageBitmap(image);
				ViewUtil.showView(viewHolder.imageThumbnail, true);
			}
		}
	}

	/*
	 * used to download image from server
	 */
	private class ImageDownloader extends AsyncTask<Void, Void, Void> {
		private int imagePosition;
		private ViewHolder viewHolder;

		private Bitmap image;

		public ImageDownloader(int imagePosition, ViewHolder viewHolder) {
			this.imagePosition = imagePosition;
			this.viewHolder = viewHolder;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			ViewUtil.showView(viewHolder.imageThumbnail, false);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				int width = (int) activity.getResources().getDimension(
						R.dimen.events_twowayview_thumbnail_width);
				int height = (int) activity.getResources().getDimension(
						R.dimen.events_twowayview_thumbnail_height);
				
				image = Picasso
						.with(activity)
						.load(AppController.EVENT_IMAGES_LINK
								+ event.getImageNames()[imagePosition])
						.resize(width, height).get();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			// check if image has been downloaded or not
			if (image != null) {
				// downloaded >> save it
				FileUtil.saveImage(activity, image,
						Constants.FOLDER_EVENTS_IMAGES,
						event.getImageNames()[imagePosition]);
			} else {
				// download failed >> get default image
				image = BitmapFactory.decodeResource(activity.getResources(),
						R.drawable.def_photo);
			}

			event.getImages()[imagePosition] = image;

			// check if item visible
			if (event.getImagesVisible()[imagePosition]) {
				viewHolder.imageThumbnail.setImageBitmap(image);
				ViewUtil.showView(viewHolder.imageThumbnail, true);
			}
		}
	}
}
