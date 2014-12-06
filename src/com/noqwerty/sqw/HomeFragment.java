package com.noqwerty.sqw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
	public static final String TAG = "home_fragment";

	// fragment objects
	private MainActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home,
				container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();

		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.home));
		activity.buttonIcon.setImageResource(R.drawable.actionbar_menu_icon);
		activity.buttonPlay.setVisibility(View.VISIBLE);
		
		// play music
		activity.onPlay();
	}
}










