package com.noqwerty.sqw;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.noqwerty.sqw.adapters.VideosAdapter;
import com.noqwerty.sqw.datamodels.Video;

public class VideosFragment extends Fragment {
	public static final String TAG = "videos_fragment";

	// fragment objects
	private MainActivity activity;
	private ListView listVideos;

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

		// set list adapter
		// --- TEST ---
		List<Video> videos = new ArrayList<Video>();
		for (int i = 0; i < 20; i++) {
			Video video = new Video("رابط الفيديو", "http://www.youtube.com");
			videos.add(video);
		}
		VideosAdapter adapter = new VideosAdapter(activity,
				R.layout.list_videos_item, videos);
		listVideos.setAdapter(adapter);
	}
}
