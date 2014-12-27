package com.noqwerty.sqw;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.noqwerty.sqw.adapters.PresidentsAdapter;
import com.noqwerty.sqw.customviews.SlideExpandableListView;
import com.noqwerty.sqw.datamodels.AppData;
import com.noqwerty.sqw.datamodels.President;

public class PresidentsFragment extends Fragment {
	public static final String TAG = "presidents_fragment";

	// fragment objects
	private MainActivity activity;
	private SlideExpandableListView listOurPresidents;

	private ArrayList<President> presidents;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_our_presidents,
				container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		listOurPresidents = (SlideExpandableListView) rootView
				.findViewById(R.id.list_ourPresidents);

		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.past));

		// load presidents data
		loadPresidentsData();

		// set list adapter
		PresidentsAdapter adapter = new PresidentsAdapter(activity,
				R.layout.list_our_presidents_item,
				R.layout.list_our_presidents_sub_item, presidents);
		listOurPresidents.setAdapter(adapter);

		// add listeners
		listOurPresidents.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {

				if (listOurPresidents.isGroupExpanded(groupPosition)) {
					listOurPresidents.collapseGroupWithAnimation(groupPosition);
				} else {
					listOurPresidents.expandGroupWithAnimation(groupPosition);
				}

				return true;
			}
		});
	}

	/*
	 * load presidents data from xml resources and text files
	 */
	private void loadPresidentsData() {
		presidents = new ArrayList<President>();

		President president1 = new President(activity.getString(R.string.ch1),
				AppData.ourPresidentsDescriptions[0],
				R.drawable.national_day_ch1);
		President president2 = new President(activity.getString(R.string.ch2),
				AppData.ourPresidentsDescriptions[1],
				R.drawable.national_day_ch2);
		President president3 = new President(activity.getString(R.string.ch3),
				AppData.ourPresidentsDescriptions[2],
				R.drawable.national_day_ch3);
		President president4 = new President(activity.getString(R.string.ch4),
				AppData.ourPresidentsDescriptions[3],
				R.drawable.national_day_ch4);
		President president5 = new President(activity.getString(R.string.ch5),
				AppData.ourPresidentsDescriptions[4],
				R.drawable.national_day_ch5);
		President president6 = new President(activity.getString(R.string.ch6),
				AppData.ourPresidentsDescriptions[5],
				R.drawable.national_day_ch6);
		President president7 = new President(activity.getString(R.string.ch7),
				AppData.ourPresidentsDescriptions[6],
				R.drawable.national_day_ch7);

		presidents.add(president1);
		presidents.add(president2);
		presidents.add(president3);
		presidents.add(president4);
		presidents.add(president5);
		presidents.add(president6);
		presidents.add(president7);
	}
}
