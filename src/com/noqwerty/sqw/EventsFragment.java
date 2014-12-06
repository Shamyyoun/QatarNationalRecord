package com.noqwerty.sqw;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.noqwerty.sqw.adapters.EventsAdapter;
import com.noqwerty.sqw.customviews.SlideExpandableListView;
import com.noqwerty.sqw.datamodels.Event;

public class EventsFragment extends Fragment {
	public static final String TAG = "events_fragment";

	// fragment objects
	private MainActivity activity;
	private SlideExpandableListView listEvents;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_events, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		listEvents = (SlideExpandableListView) rootView
				.findViewById(R.id.list_events);

		// set list adapter
		// --- TEST ---
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < 20; i++) {
			int[] imagesResources = new int[6];
			imagesResources[0] = R.drawable.factory;
			imagesResources[1] = R.drawable.factory;
			imagesResources[2] = R.drawable.factory;
			imagesResources[3] = R.drawable.factory;
			imagesResources[4] = R.drawable.factory;
			imagesResources[5] = R.drawable.factory;

			Event event = new Event(
					"اسم الفاعلية",
					"وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف وصف ",
					imagesResources);
			events.add(event);
		}
		EventsAdapter adapter = new EventsAdapter(activity,
				R.layout.list_events_item, R.layout.list_events_subitem, events);
		listEvents.setAdapter(adapter);
		
		// add listeners
		listEvents.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				
				if (listEvents.isGroupExpanded(groupPosition)) {
					listEvents.collapseGroupWithAnimation(groupPosition);
				} else {
					listEvents.expandGroupWithAnimation(groupPosition);
				}
				
				return true;
			}
		});
	}
}
