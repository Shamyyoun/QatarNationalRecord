package com.noqwerty.sqw.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.noqwerty.sqw.R;
import com.noqwerty.sqw.datamodels.Video;

public class VideosAdapter extends ArrayAdapter<Video> {
	Activity activity;
	int layoutResourceId;
	List<Video> data;

	public VideosAdapter(Activity activity, int layoutResourceId,
			List<Video> data) {
		super(activity, layoutResourceId, data);
		this.activity = activity;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		final ViewHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.textTitle = (TextView) row.findViewById(R.id.text_title);

			row.setTag(holder);

		} else {
			holder = (ViewHolder) row.getTag();
		}

		final Video video = data.get(position);

		holder.textTitle.setText(Html.fromHtml("<u>" + video.getTitle()
				+ "</u>"));

		return row;
	}

	static class ViewHolder {
		TextView textTitle;
	}
}
