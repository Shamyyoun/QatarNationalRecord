package com.noqwerty.sqw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noqwerty.sqw.utils.FragmentUtil;

public class NationalDayFragment extends Fragment implements OnClickListener {
	public static final String TAG = "national_day_fragment";

	// fragment objects
	private MainActivity activity;
	private FragmentUtil fragmentUtil;

	// tabs objects
	private TextView textVideos;
	private TextView textEvents;
	private TextView textNationalDay;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_national_day, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		fragmentUtil = new FragmentUtil(activity, true);

		// customize activity
		activity.textTitle.setText(activity.getString(R.string.national_day));
		activity.buttonPlay.setVisibility(View.GONE);
		activity.spinner.setVisibility(View.GONE);
		activity.stopMusic();

		// init tabs objects
		textVideos = (TextView) rootView
				.findViewById(R.id.text_videos);
		textEvents = (TextView) rootView.findViewById(R.id.text_events);
		textNationalDay = (TextView) rootView.findViewById(R.id.text_nationalDay);

		// add listeners
		textVideos.setOnClickListener(this);
		textEvents.setOnClickListener(this);
		textNationalDay.setOnClickListener(this);

		// goto first tab as default
		fragmentUtil.gotoFragment(R.id.container_national_day,
				new NationalDayMainFragment(), NationalDayMainFragment.TAG);
		selectTab(textNationalDay);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_videos:
			fragmentUtil.gotoFragment(R.id.container_national_day,
					new VideosFragment(), VideosFragment.TAG);
			
			selectTab(textVideos);
			break;
			
		case R.id.text_events:
			fragmentUtil.gotoFragment(R.id.container_national_day,
					new EventsFragment(), EventsFragment.TAG);
			
			selectTab(textEvents);
			break;
			
		case R.id.text_nationalDay:
			fragmentUtil.gotoFragment(R.id.container_national_day,
					new NationalDayMainFragment(), NationalDayMainFragment.TAG);
			
			selectTab(textNationalDay);
			break;

		default:
			break;
		}
	}
	
	protected void selectTab(TextView textView) {
		// unselect all first
		textEvents.setSelected(false);
		textVideos.setSelected(false);
		textNationalDay.setSelected(false);
		
		// select desired item
		textView.setSelected(true);
	}
}
