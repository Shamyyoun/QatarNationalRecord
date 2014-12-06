package com.noqwerty.sqw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.utils.FragmentUtil;

public class AboutQatarFragment extends Fragment implements OnClickListener {
	public static final String TAG = "about_qatar_fragment";

	// fragment objects
	private MainActivity activity;
	private FragmentUtil fragmentUtil;

	// tabs objects
	private TextView textManufacuring;
	private TextView textHealth;
	private TextView textEducation;
	private TextView textCulture;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about_qatar, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		fragmentUtil = new FragmentUtil(activity, false);

		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.about_qatar));
		activity.buttonPlay.setVisibility(View.GONE);

		// init tabs objects
		textManufacuring = (TextView) rootView
				.findViewById(R.id.text_manufacturing);
		textHealth = (TextView) rootView.findViewById(R.id.text_health);
		textEducation = (TextView) rootView.findViewById(R.id.text_education);
		textCulture = (TextView) rootView.findViewById(R.id.text_culture);

		// add listeners
		textManufacuring.setOnClickListener(this);
		textHealth.setOnClickListener(this);
		textEducation.setOnClickListener(this);
		textCulture.setOnClickListener(this);

		// goto first tab as default
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.KEY_CATEGORY_TYPE,
				Constants.HOME_CATEGORY_CULTURE);
		fragmentUtil.gotoFragment(R.id.container_about_qatar,
				new AboutQatarCategoryFragment(), AboutQatarCategoryFragment.TAG, bundle);
	}

	@Override
	public void onClick(View v) {
		Bundle bundle = null;
		switch (v.getId()) {
		case R.id.text_manufacturing:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE,
					Constants.HOME_CATEGORY_MANUFACTURING);
			break;

		case R.id.text_health:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE,
					Constants.HOME_CATEGORY_HEALTH);
			break;

		case R.id.text_education:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE,
					Constants.HOME_CATEGORY_EDUCATION);
			break;

		case R.id.text_culture:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE,
					Constants.HOME_CATEGORY_CULTURE);
			break;

		default:
			break;
		}

		if (bundle != null) {
			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarCategoryFragment(), AboutQatarCategoryFragment.TAG,
					bundle);
		}
	}
}
