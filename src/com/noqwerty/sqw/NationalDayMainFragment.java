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

public class NationalDayMainFragment extends Fragment {
	public static final String TAG = "national_day_main_fragment";

	// fragment objects
	private MainActivity activity;
	private TextView textDesc;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_national_day_main,
				container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		textDesc = (TextView) rootView.findViewById(R.id.text_description);

		// customize activity
		activity.textTitle.setText(activity.getString(R.string.national_day));
		activity.buttonPlay.setVisibility(View.GONE);
		activity.spinner.setVisibility(View.GONE);
		activity.stopMusic();
		
		// set text data
		textDesc.setText(Html.fromHtml(AppData.nationalDay));
	}
}










