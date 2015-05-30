package com.roofandfloor.manager;

import java.util.List;

import com.roofandfloor.MyListViewFragment;
import com.roofandfloor.model.ProjectList;

public class ProjectListManager {
	MyListViewFragment myListViewFragment;

	public MyListViewFragment getMyListViewFragment() {
		return myListViewFragment;
	}

	public void setMyListViewFragment(MyListViewFragment myListViewFragment) {
		this.myListViewFragment = myListViewFragment;
	}

	public interface ProjectListListener {
		public void setProjectList(List<ProjectList> projectList);
	}

	public void populateProjectList(String response) {
		ProjectList projectList = null;
		if (response != null) {
			projectList = new ProjectList();
		}

		if (myListViewFragment != null) {
			myListViewFragment.setProjectList(projectList
					.getProjectList(response));
		}

	}

}
