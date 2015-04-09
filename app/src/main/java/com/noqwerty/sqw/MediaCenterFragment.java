package com.noqwerty.sqw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noqwerty.sqw.utils.FragmentUtil;

public class MediaCenterFragment extends Fragment implements OnClickListener {
	public static final String TAG = "media_center_fragment";

	// fragment objects
	private MainActivity activity;
	private FragmentUtil fragmentUtil;

	// tabs objects
	private TextView textMediaCenter;
	private TextView textProjects;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_media_center,
				container, false);
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
		textMediaCenter = (TextView) rootView
				.findViewById(R.id.text_mediaCenter);
		textProjects = (TextView) rootView.findViewById(R.id.text_projects);

		// add listeners
		textMediaCenter.setOnClickListener(this);
		textProjects.setOnClickListener(this);

		// goto first tab as default
		fragmentUtil.gotoFragment(R.id.container_media_center,
				new MediaCenterMainFragment(), MediaCenterMainFragment.TAG);
		selectTab(textMediaCenter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_mediaCenter:
			fragmentUtil.gotoFragment(R.id.container_media_center,
					new MediaCenterMainFragment(), MediaCenterMainFragment.TAG);

			selectTab(textMediaCenter);
			break;

		case R.id.text_projects:
			fragmentUtil.gotoFragment(R.id.container_media_center,
					new ProjectsFragment(), ProjectsFragment.TAG);

			selectTab(textProjects);
			break;

		default:
			break;
		}
	}

	protected void selectTab(TextView textView) {
		// unselect all first
		textProjects.setSelected(false);
		textMediaCenter.setSelected(false);

		// select desired item
		textView.setSelected(true);
	}
}
