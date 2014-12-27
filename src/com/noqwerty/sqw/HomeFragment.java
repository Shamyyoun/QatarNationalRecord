package com.noqwerty.sqw;

import com.noqwerty.sqw.datamodels.AppData;
import com.noqwerty.sqw.utils.RawUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	public static final String TAG = "home_fragment";

	// fragment objects
	private MainActivity activity;

	private TextView textNashid;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		textNashid = (TextView) rootView.findViewById(R.id.text_nashid);

		// customize actvity
		activity.textTitle.setText(activity.getString(R.string.home));
		activity.buttonIcon.setImageResource(R.drawable.actionbar_menu_icon);
		activity.buttonPlay.setVisibility(View.VISIBLE);
		activity.spinner.setVisibility(View.GONE);

		// stop music first
		activity.stopMusic();
		
		// if not paused play music
		if (!activity.isPaused()) {
			activity.playMusic();
		}

		// set nashid text
		textNashid.setText(Html.fromHtml(AppData.nashid));

		// start nashid animation
		Animation anim = AnimationUtils.loadAnimation(activity,
				R.anim.nashid_slide_up);
		textNashid.startAnimation(anim);
	}
}
