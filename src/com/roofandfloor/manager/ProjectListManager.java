package com.roofandfloor.manager;

import java.util.List;

import com.roofandfloor.MyListViewFragment;
import com.roofandfloor.model.Project;

public class ProjectListManager {
	private ProjectListListener projectListListener;

	public ProjectListListener getProjectListListener() {
		return projectListListener;
	}

	public void setProjectListListener(ProjectListListener projectListListener) {
		this.projectListListener = projectListListener;
	}

	public interface ProjectListListener {
		public void setProjectList(List<Project> projectList);
	}

	public void populateProjectList(String response) {
		Project projectList = null;
		if (response != null) {
			projectList = new Project();
		}

		if (projectListListener != null) {
			projectListListener.setProjectList(projectList
					.getProjectList(response));
		}

	}

}
