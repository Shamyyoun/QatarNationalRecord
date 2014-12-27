package com.noqwerty.sqw.adapters;

import java.util.List;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.ItemClickSupport.OnItemClickListener;
import org.lucasr.twowayview.TwoWayView;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Gallery;
import android.widget.TextView;

import com.noqwerty.sqw.R;
import com.noqwerty.sqw.customviews.SlideExpandableListView.AnimatedExpandableListAdapter;
import com.noqwerty.sqw.datamodels.Event;

public class EventsAdapter extends AnimatedExpandableListAdapter {

	private Activity activity;
	private int groupLayoutResourceId;
	private int childLayoutResourceId;
	private List<Event> data;

	private LayoutInflater inflater;

	public EventsAdapter(Activity activity, int groupLayoutResourceId,
			int childLayoutResourceId, List<Event> data) {
		this.activity = activity;
		this.groupLayoutResourceId = groupLayoutResourceId;
		this.childLayoutResourceId = childLayoutResourceId;
		this.data = data;

		inflater = LayoutInflater.from(activity);
	}

	@Override
	public Event getChild(int groupPosition, int childPosition) {
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
		final Event event = getChild(groupPosition, childPosition);

		if (convertView == null) {
			holder = new ChildHolder();

			convertView = inflater
					.inflate(childLayoutResourceId, parent, false);
			holder.twoWayViewThumbnails = (TwoWayView) convertView
					.findViewById(R.id.twoWayView_thumbnails);

			convertView.setTag(holder);
		} else {
			holder = (ChildHolder) convertView.getTag();
		}

		// set thumbnails adapter
		EventThumbnailsAdapter adapter = new EventThumbnailsAdapter(activity,
				event, R.layout.twowayview_thumbnails_item);
		holder.twoWayViewThumbnails.setAdapter(adapter);

		// add listenr to twoway thumbails
		ItemClickSupport itemClick = ItemClickSupport
				.addTo(holder.twoWayViewThumbnails);
		itemClick.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(RecyclerView parent, View view,
					int position, long id) {
				
				// create gallery dialog
				Dialog dialog = new Dialog(activity,
						android.R.style.Theme_Translucent_NoTitleBar);
				dialog.setContentView(R.layout.dialog_gallery);

				// customize dialog
				dialog.setCanceledOnTouchOutside(true);
				Window window = dialog.getWindow();
				window.setLayout(LayoutParams.MATCH_PARENT,
						LayoutParams.WRAP_CONTENT);
				window.setGravity(Gravity.CENTER);

				// dim the parent view of dialog by 70%
				LayoutParams dialogParentLP = window.getAttributes();
				dialogParentLP.dimAmount = 0.7f;
				dialogParentLP.flags = LayoutParams.FLAG_DIM_BEHIND;
				dialog.getWindow().setAttributes(dialogParentLP);

				// get gallery and customize it
				Gallery gallery = (Gallery) dialog
						.findViewById(R.id.gallery_images);
				gallery.setSpacing(1);

				// create and set adapter
				GalleryAdapter adapter = new GalleryAdapter(activity, event);
				gallery.setAdapter(adapter);
				gallery.setSelection(position, true);

				// show dialog
				dialog.show();
			}
		});

		return convertView;
	}

	@Override
	public int getRealChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Event getGroup(int groupPosition) {
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
		final Event event = getGroup(groupPosition);

		if (convertView == null) {
			holder = new GroupHolder();
			convertView = inflater
					.inflate(groupLayoutResourceId, parent, false);

			holder.textTitle = (TextView) convertView
					.findViewById(R.id.text_title);

			convertView.setTag(holder);
		} else {
			holder = (GroupHolder) convertView.getTag();
		}

		// set data
		holder.textTitle.setText(event.getTitle());

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
		TextView textTitle;
	}

	private static class ChildHolder {
		TwoWayView twoWayViewThumbnails;
	}
}
