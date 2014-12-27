package com.noqwerty.sqw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.TwoWayView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.noqwerty.sqw.adapters.SponsorsAdapter;
import com.noqwerty.sqw.datamodels.Constants;
import com.noqwerty.sqw.datamodels.Sponsor;
import com.noqwerty.sqw.utils.FileUtil;
import com.noqwerty.sqw.utils.InternetUtil;
import com.noqwerty.sqw.utils.ViewUtil;
import com.squareup.picasso.Picasso;

public class SponsorsFragment extends Fragment {
	public static final String TAG = "sponsors_fragment";

	// fragment objects
	private MainActivity activity;
	private TwoWayView twoWaySponsors;
	private Dialog congrateDialog;
	private ImageView imageCongrate;
	private AlertDialog noInternetDialog;

	private ArrayList<Sponsor> sponsors;
	private CongrateTask congrateTask;

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
		twoWaySponsors = (TwoWayView) rootView
				.findViewById(R.id.twoWayView_sponsors);
		congrateDialog = new Dialog(activity,
				android.R.style.Theme_Translucent_NoTitleBar);
		congrateDialog.setContentView(R.layout.dialog_congrates);
		imageCongrate = (ImageView) congrateDialog
				.findViewById(R.id.image_congrate);
		noInternetDialog = new AlertDialog.Builder(activity)
				.setMessage(activity.getString(R.string.no_internet))
				.setPositiveButton(activity.getString(R.string.close),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						}).create();

		// customize activity
		activity.textTitle.setText(activity.getString(R.string.sponsors));
		activity.buttonPlay.setVisibility(View.GONE);
		activity.spinner.setVisibility(View.GONE);
		activity.stopMusic();

		// customize dialog
		congrateDialog.setCanceledOnTouchOutside(true);
		Window window = congrateDialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		window.setGravity(Gravity.CENTER);
		// dim the parent view of dialog by 70%
		LayoutParams dialogParentLP = window.getAttributes();
		dialogParentLP.dimAmount = 0.7f;
		dialogParentLP.flags = LayoutParams.FLAG_DIM_BEHIND;
		congrateDialog.getWindow().setAttributes(dialogParentLP);

		// load sponsors data
		loadData();

		// set list adapter
		SponsorsAdapter adapter = new SponsorsAdapter(activity, sponsors,
				R.layout.twowayview_sponsors_item);
		twoWaySponsors.setAdapter(adapter);

		// add click listener
		final ItemClickSupport itemClick = ItemClickSupport
				.addTo(twoWaySponsors);

		itemClick
				.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
					@Override
					public void onItemClick(RecyclerView parent, View child,
							int position, long id) {
						onSponsor(sponsors.get(position));
					}
				});
	}

	private void loadData() {
		InputStream is = activity.getResources().openRawResource(
				R.raw.sponsors_names);

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		sponsors = new ArrayList<Sponsor>();

		Sponsor sponsor;
		String name = null;
		try {
			int i = 0;
			while ((name = reader.readLine()) != null) {
				String logoName = (i + 1) + ".jpg";
				String congratName = (i + 1) + ".1.jpg";
				sponsor = new Sponsor(name, logoName, congratName);

				sponsors.add(sponsor);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void onSponsor(Sponsor sponsor) {
		// get image path
		String imagePath = activity.getFilesDir() + "/"
				+ Constants.FOLDER_SPONSORS_IMAGES + "/"
				+ sponsor.getCongratName();
		File file = new File(imagePath);

		// check if image exists or not
		if (file.exists()) {
			// display image in dialog
			Picasso.with(activity).load(file).into(imageCongrate);
			ViewUtil.showView(imageCongrate, true, 0);

			congrateDialog.show();
		} else {
			// cancel running task
			if (congrateTask != null) {
				congrateTask.cancel(true);
			}

			// check internet
			if (InternetUtil.isConnected(activity)) {
				// download it
				congrateTask = new CongrateTask(sponsor.getCongratName());
				congrateTask.execute();

			} else {
				// show error message
				noInternetDialog.show();
			}
		}
	}

	private class CongrateTask extends AsyncTask<Void, Void, Void> {
		private String imageName;

		private Bitmap image;

		public CongrateTask(String imageName) {
			this.imageName = imageName;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			ViewUtil.showView(imageCongrate, false, 0);
			congrateDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				image = Picasso.with(activity)
						.load(AppController.SPONSORS_INAGES_LINK + imageName)
						.get();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			// ensure that image is not null
			if (image != null) {
				FileUtil.saveImage(activity, image,
						Constants.FOLDER_SPONSORS_IMAGES, imageName);

				if (!isCancelled()) {
					// display it to dialog
					imageCongrate.setImageBitmap(image);
				}
			} else {
				imageCongrate.setImageResource(R.drawable.def_photo);
			}

			ViewUtil.showView(imageCongrate, true);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (congrateTask != null) {
			congrateTask.cancel(true);
		}
	}
}
