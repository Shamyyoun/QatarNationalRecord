package com.noqwerty.sqw;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.noqwerty.sqw.datamodels.AppData;
import com.noqwerty.sqw.utils.FragmentUtil;
import com.noqwerty.sqw.utils.RawUtil;

public class SplashFragment extends Fragment {
	public static final String TAG = "splash_fragment";
	private static final int SPLASH_TIME = 6000;

	// fragment objects
	private MainActivity activity;
	private FragmentUtil fragmentUtil;

	// splash objects
	private Handler handlerSplash;
	private Runnable runnableSplash;

	private Handler handlerDataLoader;
	private Runnable runnableDataLoader;

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
		handlerSplash = new Handler();
		runnableSplash = new Runnable() {
			@Override
			public void run() {
				// enable window after splash
				activity.getWindow().clearFlags(
						WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

				fragmentUtil.gotoFragment(R.id.container_main,
						new HomeFragment(), HomeFragment.TAG);
				
				System.out.println("true");
			}
		};

		// init data loader handler and runnable
		handlerDataLoader = new Handler();
		runnableDataLoader = new Runnable() {
			@Override
			public void run() {
				// load app data
				AppData.aboutQatar = RawUtil.readFileInHTMLFormat(activity, R.raw.about_qatar);
				AppData.mediaCenter = RawUtil.readFileInHTMLFormat(activity, R.raw.media_center);
				AppData.nashid = RawUtil.readFileInHTMLFormat(activity, R.raw.nashid);
				AppData.nationalDay = RawUtil.readFileInHTMLFormat(activity, R.raw.national_day);
				AppData.qatarEducation = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_education);
				AppData.qatarFuture = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_future);
				AppData.qatarIndustry = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_industry);
				AppData.qatarOld = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_old);
				AppData.qatarSport = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_sport);
				AppData.qatarTrade = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_trade);
				AppData.qatarTourism = RawUtil.readFileInHTMLFormat(activity, R.raw.qatar_tourism);
				
				AppData.ourPresidentsDescriptions = new String[7];
				AppData.ourPresidentsDescriptions[0] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_1_desc);
				AppData.ourPresidentsDescriptions[1] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_2_desc);
				AppData.ourPresidentsDescriptions[2] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_3_desc);
				AppData.ourPresidentsDescriptions[3] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_4_desc);
				AppData.ourPresidentsDescriptions[4] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_5_desc);
				AppData.ourPresidentsDescriptions[5] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_6_desc);
				AppData.ourPresidentsDescriptions[6] = RawUtil.readFileInHTMLFormat(activity, R.raw.ch_7_desc);
				
				AppData.projectsDescriptions = new String[5];
				AppData.projectsDescriptions[0] = RawUtil.readFileInHTMLFormat(activity, R.raw.proj_1_desc);
				AppData.projectsDescriptions[1] = RawUtil.readFileInHTMLFormat(activity, R.raw.proj_2_desc);
				AppData.projectsDescriptions[2] = RawUtil.readFileInHTMLFormat(activity, R.raw.proj_3_desc);
				AppData.projectsDescriptions[3] = RawUtil.readFileInHTMLFormat(activity, R.raw.proj_4_desc);
				AppData.projectsDescriptions[4] = RawUtil.readFileInHTMLFormat(activity, R.raw.proj_5_desc);
			}
		};

		handlerDataLoader.post(runnableDataLoader);
		handlerSplash.postDelayed(runnableSplash, SPLASH_TIME);
	}
}
