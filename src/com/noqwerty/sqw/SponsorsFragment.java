package com.noqwerty.sqw;

import java.util.ArrayList;
import java.util.List;

import com.noqwerty.sqw.adapters.SponsorsAdapter;
import com.noqwerty.sqw.datamodels.Sponsor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SponsorsFragment extends Fragment {
	public static final String TAG = "sponsors_fragment";

	// fragment objects
	private MainActivity activity;
	private ListView listSponsors;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sponsors, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		listSponsors = (ListView) rootView.findViewById(R.id.list_sponsors);

		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.sponsors));
		activity.buttonPlay.setVisibility(View.GONE);

		// set list adapter
		// --- TEST ---
		List<Sponsor> sponsors = new ArrayList<Sponsor>();
		for (int i = 0; i < 20; i++) {
			Sponsor sponsor = new Sponsor("المركز المصري الاعلامي", R.drawable.egyptian_media_center_logo);
			sponsors.add(sponsor);
		}
		SponsorsAdapter adapter = new SponsorsAdapter(activity,
				R.layout.list_sponsors_item, sponsors);
		listSponsors.setAdapter(adapter);
	}
}
