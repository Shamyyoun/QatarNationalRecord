package com.noqwerty.sqw.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.noqwerty.sqw.R;

public class EventThumbnailsAdapter extends
		RecyclerView.Adapter<EventThumbnailsAdapter.ViewHolder> {
	private Activity activity;
	private int[] imagesResources;
	private int layoutResourceId;

	class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView imageThumbnail;

		public ViewHolder(View view) {
			super(view);
			imageThumbnail = (ImageView) view
					.findViewById(R.id.image_thumbnail);
		}
	}

	public EventThumbnailsAdapter(Activity activity, int[] imagesResources,
			int layoutResourceId) {
		this.activity = activity;
		this.imagesResources = imagesResources;
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
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.imageThumbnail.setBackgroundResource(imagesResources[position]);
	}

	@Override
	public int getItemCount() {
		return imagesResources.length;
	}
}
