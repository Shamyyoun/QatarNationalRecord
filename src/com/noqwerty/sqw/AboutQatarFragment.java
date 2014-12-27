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
	private int tab = -1;

	// tabs objects
	private TextView textAboutQatar;
	private TextView textPast;
	private TextView textPresent;
	private TextView textFuture;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_about_qatar,
				container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		fragmentUtil = new FragmentUtil(activity, false);
		Bundle bundle = getArguments();
		if (bundle != null) {
			tab = bundle.getInt(Constants.KEY_TAB, -1);
		}

		// customize activity
		activity.textTitle.setText(activity.getString(R.string.about_qatar));
		activity.buttonPlay.setVisibility(View.GONE);
		activity.spinner.setVisibility(View.GONE);
		activity.stopMusic();

		// init tabs objects
		textAboutQatar = (TextView) rootView.findViewById(R.id.text_aboutQatar);
		textPast = (TextView) rootView.findViewById(R.id.text_past);
		textPresent = (TextView) rootView.findViewById(R.id.text_present);
		textFuture = (TextView) rootView.findViewById(R.id.text_future);

		// add listeners
		textAboutQatar.setOnClickListener(this);
		textPast.setOnClickListener(this);
		textPresent.setOnClickListener(this);
		textFuture.setOnClickListener(this);

		// check if tab sent in arguments
		if (tab != -1) {
			// goto tab
			Bundle tabBundle = new Bundle();
			tabBundle.putInt(Constants.KEY_CATEGORY_TYPE, tab);
			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarCategoryFragment(), AboutQatarCategoryFragment.TAG, tabBundle);
			
			// selected desired tab
			switch (tab) {
			case Constants.ABOUT_QATAR_CATEGORY_FUTURE:
				selectTab(textFuture);
				break;

			default:
				break;
			}
		} else  {
			// goto about Qatar main as default
			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarMainFragment(), AboutQatarMainFragment.TAG);
			
			selectTab(textAboutQatar);
		}
		
	}

	@Override
	public void onClick(View v) {
		Bundle bundle = null;
		switch (v.getId()) {
		case R.id.text_aboutQatar:
			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarMainFragment(), AboutQatarMainFragment.TAG);
			
			selectTab(textAboutQatar);
			break;

		case R.id.text_past:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE, Constants.ABOUT_QATAR_CATEGORY_PAST);

			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarCategoryFragment(),
					AboutQatarCategoryFragment.TAG, bundle);
			
			selectTab(textPast);
			break;

		case R.id.text_present:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE,
					Constants.ABOUT_QATAR_CATEGORY_MANUFACTURING);

			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarCategoryFragment(),
					AboutQatarCategoryFragment.TAG, bundle);
			
			selectTab(textPresent);
			break;

		case R.id.text_future:
			bundle = new Bundle();
			bundle.putInt(Constants.KEY_CATEGORY_TYPE,
					Constants.ABOUT_QATAR_CATEGORY_FUTURE);

			fragmentUtil.gotoFragment(R.id.container_about_qatar,
					new AboutQatarCategoryFragment(),
					AboutQatarCategoryFragment.TAG, bundle);
			
			selectTab(textFuture);
			break;

		default:
			break;
		}
	}
	
	private void selectTab(TextView textView) {
		// unselect all first
		textAboutQatar.setSelected(false);
		textPast.setSelected(false);
		textPresent.setSelected(false);
		textFuture.setSelected(false);
		
		// select desired item
		textView.setSelected(true);
	}
}
