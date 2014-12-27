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
import android.widget.ImageView;
import android.widget.TextView;

import com.noqwerty.sqw.R;
import com.noqwerty.sqw.customviews.SlideExpandableListView.AnimatedExpandableListAdapter;
import com.noqwerty.sqw.datamodels.Project;

public class ProjectsAdapter extends AnimatedExpandableListAdapter {

	private Activity activity;
	private int groupLayoutResourceId;
	private int childLayoutResourceId;
	private List<Project> data;

	private LayoutInflater inflater;

	public ProjectsAdapter(Activity activity, int groupLayoutResourceId,
			int childLayoutResourceId, List<Project> data) {
		this.activity = activity;
		this.groupLayoutResourceId = groupLayoutResourceId;
		this.childLayoutResourceId = childLayoutResourceId;
		this.data = data;

		inflater = LayoutInflater.from(activity);
	}

	@Override
	public Project getChild(int groupPosition, int childPosition) {
		return data.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getRealChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		ChildHolder holder;
		final Project project = getChild(groupPosition, childPosition);

		if (convertView == null) {
			holder = new ChildHolder();

			convertView = inflater
					.inflate(childLayoutResourceId, parent, false);
			holder.textDescription = (TextView) convertView
					.findViewById(R.id.text_description);
			holder.textLink = (TextView) convertView
					.findViewById(R.id.text_link);

			convertView.setTag(holder);
		} else {
			holder = (ChildHolder) convertView.getTag();
		}

		holder.textDescription.setText(Html.fromHtml(project.getDescription()));
		holder.textLink.setText(Html.fromHtml("<u>" + project.getLink()
				+ "</u>"));

		// add listener
		holder.textLink.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(project.getLink()));
				activity.startActivity(browserIntent);
			}
		});

		return convertView;
	}

	@Override
	public int getRealChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Project getGroup(int groupPosition) {
		return data.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return data.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		final GroupHolder holder;
		final Project project = getGroup(groupPosition);

		if (convertView == null) {
			holder = new GroupHolder();
			convertView = inflater
					.inflate(groupLayoutResourceId, parent, false);

			holder.imageImage = (ImageView) convertView
					.findViewById(R.id.image_image);
			holder.textName = (TextView) convertView
					.findViewById(R.id.text_name);

			convertView.setTag(holder);
		} else {
			holder = (GroupHolder) convertView.getTag();
		}

		// set data
		holder.imageImage.setImageResource(project.getImageResource());
		holder.textName.setText(project.getName());

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	private static class GroupHolder {
		ImageView imageImage;
		TextView textName;
	}

	private static class ChildHolder {
		TextView textDescription;
		TextView textLink;
	}
}
