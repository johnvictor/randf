package com.roofandfloor.manager;

import java.util.List;

import com.roofandfloor.MyListViewFragment;
import com.roofandfloor.model.Project;

public class ProjectListManager {
	MyListViewFragment myListViewFragment;

	public MyListViewFragment getMyListViewFragment() {
		return myListViewFragment;
	}

	public void setMyListViewFragment(MyListViewFragment myListViewFragment) {
		this.myListViewFragment = myListViewFragment;
	}

	public interface ProjectListListener {
		public void setProjectList(List<Project> projectList);
	}

	public void populateProjectList(String response) {
		Project projectList = null;
		if (response != null) {
			projectList = new Project();
		}

		if (myListViewFragment != null) {
			myListViewFragment.setProjectList(projectList
					.getProjectList(response));
		}

	}

}
