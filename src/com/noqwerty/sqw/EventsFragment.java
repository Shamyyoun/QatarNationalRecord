package com.noqwerty.sqw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.noqwerty.sqw.adapters.EventsAdapter;
import com.noqwerty.sqw.customviews.SlideExpandableListView;
import com.noqwerty.sqw.datamodels.Event;

public class EventsFragment extends Fragment {
	public static final String TAG = "events_fragment";

	// fragment objects
	private MainActivity activity;
	private SlideExpandableListView listEvents;

	private List<Event> events;

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

		// load events data
		loadEventsData();

		setListEventsAdapter();

	}

	private void setListEventsAdapter() {
		// set list adapter
		final EventsAdapter adapter = new EventsAdapter(activity,
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

	private void loadEventsData() {
		InputStream is = activity.getResources().openRawResource(R.raw.events);

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		events = new ArrayList<Event>();

		Event event;
		String title = null;
		try {
			int i = 0;
			while ((title = reader.readLine()) != null) {
				int imagesCount = Integer.parseInt(reader.readLine());
				String[] imageNames = new String[imagesCount];

				for (int j = 0; j < imageNames.length; j++) {
					imageNames[j] = (i + 1) + "." + j + ".jpg";
				}

				event = new Event(title, imageNames);
				events.add(event);

				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
