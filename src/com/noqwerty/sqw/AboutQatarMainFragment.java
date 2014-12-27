package com.noqwerty.sqw;

import com.noqwerty.sqw.datamodels.AppData;
import com.noqwerty.sqw.utils.RawUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutQatarMainFragment extends Fragment {
	public static final String TAG = "about_qatar_main_fragment";

	// fragment objects
	private MainActivity activity;
	private TextView textAboutQatar;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about_qatar_main,
				container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		textAboutQatar = (TextView) rootView.findViewById(R.id.text_aboutQatar);

		// customize activity
		activity.textTitle.setText(activity.getString(R.string.about_qatar));
		activity.buttonPlay.setVisibility(View.GONE);
		activity.spinner.setVisibility(View.GONE);
		activity.stopMusic();
		
		// set about qatar data
		textAboutQatar.setText(Html.fromHtml(AppData.aboutQatar));
	}
}










