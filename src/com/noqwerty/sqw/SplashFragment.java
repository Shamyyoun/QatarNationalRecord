package com.noqwerty.sqw;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.noqwerty.sqw.utils.FragmentUtil;

public class SplashFragment extends Fragment {
	public static final String TAG = "splash_fragment";
	private static final int SPLASH_TIME = 6000;

	// fragment objects
	private MainActivity activity;
	private FragmentUtil fragmentUtil;

	// splash objects
	private Handler handler;
	private Runnable runnable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_splash, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		fragmentUtil = new FragmentUtil(activity, true);

		// init splash handler and runnable
		handler = new Handler();
		runnable = new Runnable() {
			@Override
			public void run() {
				// enable window after splash
				activity.getWindow().clearFlags(
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

				fragmentUtil.gotoFragment(R.id.container_main,
						new HomeFragment(), HomeFragment.TAG);
			}
		};

		handler.postDelayed(runnable, SPLASH_TIME);
	}
}
