package com.noqwerty.sqw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.noqwerty.sqw.adapters.VideosAdapter;
import com.noqwerty.sqw.datamodels.Video;

public class VideosFragment extends Fragment {
	public static final String TAG = "videos_fragment";

	// fragment objects
	private MainActivity activity;
	private ListView listVideos;

	private List<Video> videos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_videos, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		listVideos = (ListView) rootView.findViewById(R.id.list_videos);

		loadEventsData();

		// set list adapter
		VideosAdapter adapter = new VideosAdapter(activity,
				R.layout.list_videos_item, videos);
		listVideos.setAdapter(adapter);

		// add click listener
		listVideos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Video video = videos.get(position);
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(video.getLink()));
				activity.startActivity(browserIntent);
			}
		});
	}

	private void loadEventsData() {
		InputStream is = activity.getResources().openRawResource(R.raw.videos);

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		videos = new ArrayList<Video>();

		Video video;
		String title = null;
		try {
			while ((title = reader.readLine()) != null) {
				String link = reader.readLine();

				video = new Video(title, link);
				videos.add(video);
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
}
