package com.noqwerty.sqw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.noqwerty.sqw.datamodels.Constants;

public class AboutQatarCategoryFragment extends Fragment {
	public static final String TAG = "about_qatar_category_fragment";

	// fragment objects
	private MainActivity activity;
	private int categoryType;

	// main view objects
	private ImageView imageImage;
	private TextView textDescription;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about_qatar_category,
				container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		categoryType = getArguments().getInt(Constants.KEY_CATEGORY_TYPE);

		// init tabs objects
		imageImage = (ImageView) rootView.findViewById(R.id.image_image);
		textDescription = (TextView) rootView
				.findViewById(R.id.text_description);
		
		// set initials
		if (categoryType == Constants.HOME_CATEGORY_MANUFACTURING) {
			// manufacturing fragment
			imageImage.setBackgroundResource(R.drawable.factory);
			textDescription.setText("صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة صناعة ");
		} else if (categoryType == Constants.HOME_CATEGORY_EDUCATION) {
			// education fragment
			imageImage.setBackgroundResource(R.drawable.factory);
			textDescription.setText("تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم تعليم ");
		} else if (categoryType == Constants.HOME_CATEGORY_HEALTH) {
			// health fragment
			imageImage.setBackgroundResource(R.drawable.factory);
			textDescription.setText("صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة صحة ");
		} else if (categoryType == Constants.HOME_CATEGORY_CULTURE) {
			// culture fragment
			imageImage.setBackgroundResource(R.drawable.factory);
			textDescription.setText("ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ثقافة ");
		}
	}
}










