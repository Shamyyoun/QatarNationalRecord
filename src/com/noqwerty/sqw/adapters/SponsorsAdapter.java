package com.noqwerty.sqw.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.noqwerty.sqw.R;
import com.noqwerty.sqw.datamodels.Sponsor;

public class SponsorsAdapter extends ArrayAdapter<Sponsor> {
	Context context;
	int layoutResourceId;
	List<Sponsor> data;

	public SponsorsAdapter(Context context, int layoutResourceId,
			List<Sponsor> data) {
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
			holder.textName = (TextView) row
					.findViewById(R.id.text_name);
			holder.imageThumbnail = (ImageView) row
					.findViewById(R.id.image_thumbail);
	
			row.setTag(holder);

		} else {
			holder = (ViewHolder) row.getTag();
		}

		Sponsor sponsor = data.get(position);

		holder.textName.setText(sponsor.getName());
		holder.imageThumbnail.setImageResource(sponsor.getThumbnailResource());


		return row;
	}

	static class ViewHolder {
		TextView textName;
		ImageView imageThumbnail;
	}
}
