package com.noqwerty.sqw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.noqwerty.sqw.datamodels.AppData;
import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.utils.FragmentUtil;

public class AboutQatarCategoryFragment extends Fragment {
	public static final String TAG = "about_qatar_category_fragment";

	// fragment objects
	private MainActivity activity;
	private FragmentUtil fragmentUtil;
	private int categoryType;

	// main view objects
	private ImageView imageImage;
	private TextView textDescription;

	// flags
	private boolean firstAppear = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_about_qatar_category, container, false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		fragmentUtil = new FragmentUtil(activity, false);
		categoryType = getArguments().getInt(Constants.KEY_CATEGORY_TYPE);

		// init main views
		imageImage = (ImageView) rootView.findViewById(R.id.image_image);
		textDescription = (TextView) rootView
				.findViewById(R.id.text_description);

		// set initials
		if (categoryType == Constants.ABOUT_QATAR_CATEGORY_PAST) {
			generatePastFragment();

		} else if (categoryType == Constants.ABOUT_QATAR_CATEGORY_FUTURE) {
			generateFutureFragment();

		} else if (categoryType == Constants.ABOUT_QATAR_CATEGORY_MANUFACTURING) {
			generateManufacturingFragment();

		} else if (categoryType == Constants.ABOUT_QATAR_CATEGORY_EDUCATION) {
			generateEducationFragment();

		} else if (categoryType == Constants.ABOUT_QATAR_CATEGORY_SPORT) {
			generateSportFragment();

		} else if (categoryType == Constants.ABOUT_QATAR_CATEGORY_TRADE) {
			generateTradeFragment();

		} else if (categoryType == Constants.ABOUT_QATAR_CATEGORY_TOURISM) {
			generateTourismFragment();
		}
	}

	/*
	 * used to customize this fragment as past fragment
	 */
	private void generatePastFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.past));
		activity.spinner.setVisibility(View.VISIBLE);

		imageImage.setImageResource(R.drawable.qatar_old);
		textDescription.setText(Html.fromHtml(AppData.qatarOld));

		// customize spinner
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				activity, R.array.past_categories,
				R.layout.spinner_about_qatar_category_selected_item);

		adapter.setDropDownViewResource(R.layout.spinner_about_qatar_category_item);
		activity.spinner.setAdapter(adapter);

		activity.spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {

						switch (position) {
						case 0:
							// past
							if (!firstAppear) {
								Bundle bundle = new Bundle();
								bundle.putInt(Constants.KEY_CATEGORY_TYPE,
										Constants.ABOUT_QATAR_CATEGORY_PAST);

								fragmentUtil.gotoFragment(
										R.id.container_about_qatar,
										new AboutQatarCategoryFragment(),
										AboutQatarCategoryFragment.TAG, bundle);

							} else {
								firstAppear = false;
							}
							break;

						case 1:
							// our presidents
							fragmentUtil.gotoFragment(
									R.id.container_about_qatar,
									new PresidentsFragment(),
									PresidentsFragment.TAG);
							break;

						default:
							break;
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	/*
	 * used to customize this fragment as manufacturing fragment
	 */
	private void generateManufacturingFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.present));
		activity.spinner.setVisibility(View.VISIBLE);

		imageImage.setImageResource(R.drawable.qatar_industry);
		textDescription.setText(Html.fromHtml(AppData.qatarIndustry));

		// show category spinner in the actionbar
		activity.spinner.setVisibility(View.VISIBLE);

		// customize spinner
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				activity, R.array.present_categories,
				R.layout.spinner_about_qatar_category_selected_item);
		adapter.setDropDownViewResource(R.layout.spinner_about_qatar_category_item);
		activity.spinner.setAdapter(adapter);

		activity.spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {

						Bundle bundle = null;

						switch (position) {
						case 0:
							// manufacturing
							if (!firstAppear) {
								bundle = new Bundle();
								bundle.putInt(
										Constants.KEY_CATEGORY_TYPE,
										Constants.ABOUT_QATAR_CATEGORY_MANUFACTURING);
							} else {
								firstAppear = false;
							}
							break;

						case 1:
							// trade
							bundle = new Bundle();
							bundle.putInt(Constants.KEY_CATEGORY_TYPE,
									Constants.ABOUT_QATAR_CATEGORY_TRADE);
							break;

						case 2:
							// tourism
							bundle = new Bundle();
							bundle.putInt(Constants.KEY_CATEGORY_TYPE,
									Constants.ABOUT_QATAR_CATEGORY_TOURISM);

							break;

						case 3:
							// sport
							bundle = new Bundle();
							bundle.putInt(Constants.KEY_CATEGORY_TYPE,
									Constants.ABOUT_QATAR_CATEGORY_SPORT);

							break;

						case 4:
							// education
							bundle = new Bundle();
							bundle.putInt(Constants.KEY_CATEGORY_TYPE,
									Constants.ABOUT_QATAR_CATEGORY_EDUCATION);
							break;

						default:
							break;
						}

						if (bundle != null) {
							fragmentUtil.gotoFragment(
									R.id.container_about_qatar,
									new AboutQatarCategoryFragment(),
									AboutQatarCategoryFragment.TAG, bundle);
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	/*
	 * used to customize this fragment as future fragment
	 */
	private void generateFutureFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.future));
		activity.spinner.setVisibility(View.GONE);

		imageImage.setImageResource(R.drawable.qatar_future);
		textDescription.setText(Html.fromHtml(AppData.qatarFuture));
	}

	/*
	 * used to customize this fragment as trade fragment
	 */
	private void generateTradeFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.present));

		imageImage.setImageResource(R.drawable.qatar_trade);
		textDescription.setText(Html.fromHtml(AppData.qatarTrade));
	}

	/*
	 * used to customize this fragment as education fragment
	 */
	private void generateEducationFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.present));

		imageImage.setImageResource(R.drawable.qatar_education);
		textDescription.setText(Html.fromHtml(AppData.qatarEducation));
	}

	/*
	 * used to customize this fragment as sport fragment
	 */
	private void generateSportFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.present));

		imageImage.setImageResource(R.drawable.qatar_sports);
		textDescription.setText(Html.fromHtml(AppData.qatarSport));
	}

	/*
	 * used to customize this fragment as tourism fragment
	 */
	private void generateTourismFragment() {
		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.present));

		imageImage.setImageResource(R.drawable.qatar_tourism);
		textDescription.setText(Html.fromHtml(AppData.qatarTourism));
	}

}
