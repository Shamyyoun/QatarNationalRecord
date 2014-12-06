package com.noqwerty.sqw.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.noqwerty.sqw.R;
import com.noqwerty.sqw.datamodels.Video;

public class VideosAdapter extends ArrayAdapter<Video> {
	Context context;
	int layoutResourceId;
	List<Video> data;

	public VideosAdapter(Context context, int layoutResourceId, List<Video> data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View row = convertView;
		final ViewHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.textTitle = (TextView) row.findViewById(R.id.text_title);

			row.setTag(holder);

		} else {
			holder = (ViewHolder) row.getTag();
		}

		Video video = data.get(position);

		holder.textTitle.setText(Html.fromHtml("<html><u>" + video.getTitle()
				+ "</u></html>"));

		return row;
	}

	static class ViewHolder {
		TextView textTitle;
	}
}
