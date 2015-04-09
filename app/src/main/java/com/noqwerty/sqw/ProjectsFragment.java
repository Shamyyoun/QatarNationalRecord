package com.noqwerty.sqw;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.noqwerty.sqw.adapters.ProjectsAdapter;
import com.noqwerty.sqw.customviews.SlideExpandableListView;
import com.noqwerty.sqw.datamodels.AppData;
import com.noqwerty.sqw.datamodels.Project;

public class ProjectsFragment extends Fragment {
	public static final String TAG = "projects_fragment";

	// fragment objects
	private MainActivity activity;
	private SlideExpandableListView listProjects;

	private ArrayList<Project> projects;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_projects, container,
				false);
		initComponents(rootView);
		return rootView;
	}

	private void initComponents(View rootView) {
		activity = (MainActivity) getActivity();
		listProjects = (SlideExpandableListView) rootView
				.findViewById(R.id.list_projects);

		// customize actionbar
		activity.textTitle.setText(activity.getString(R.string.projects));

		// load projects data
		loadProjectsData();

		// set list adapter
		ProjectsAdapter adapter = new ProjectsAdapter(activity,
				R.layout.list_projects_item, R.layout.list_projects_sub_item,
				projects);
		listProjects.setAdapter(adapter);

		// add listeners
		listProjects.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {

				if (listProjects.isGroupExpanded(groupPosition)) {
					listProjects.collapseGroupWithAnimation(groupPosition);
				} else {
					listProjects.expandGroupWithAnimation(groupPosition);
				}

				return true;
			}
		});
	}

	/*
	 * load presidents data from xml resources and text files
	 */
	private void loadProjectsData() {
		projects = new ArrayList<Project>();

		Project project1 = new Project(activity.getString(R.string.proj1_name),
				AppData.projectsDescriptions[0],
				activity.getString(R.string.proj1_link), R.drawable.proj1);
		Project project2 = new Project(activity.getString(R.string.proj2_name),
				AppData.projectsDescriptions[1],
				activity.getString(R.string.proj2_link), R.drawable.proj2);
		Project project3 = new Project(activity.getString(R.string.proj3_name),
				AppData.projectsDescriptions[2],
				activity.getString(R.string.proj3_link), R.drawable.proj3);
		Project project4 = new Project(activity.getString(R.string.proj4_name),
				AppData.projectsDescriptions[3],
				activity.getString(R.string.proj4_link), R.drawable.proj4);
		Project project5 = new Project(activity.getString(R.string.proj5_name),
				AppData.projectsDescriptions[4],
				activity.getString(R.string.proj5_link), R.drawable.proj5);

		projects.add(project1);
		projects.add(project2);
		projects.add(project3);
		projects.add(project4);
		projects.add(project5);
	}
}
