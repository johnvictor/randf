package com.roofandfloor.manager;

import com.roofandfloor.ProjectDetailsActivity;
import com.roofandfloor.model.ProjectDetail;

public class ProjectDetailsManager {

	private ProjectDetailsActivity projectDetailsActivity;

	public ProjectDetailsActivity getProjectDetailsActivity() {
		return projectDetailsActivity;
	}

	public void populateProjectDetails(String response) {
		ProjectDetail projectDetail;
		
		if (projectDetailsActivity != null) {
			projectDetail = new ProjectDetail();
			projectDetailsActivity.setProjectDetails(projectDetail.getProjectDetail(response));
		}

	}

	public void setProjectDetailsActivity(
			ProjectDetailsActivity projectDetailsActivity) {
		this.projectDetailsActivity = projectDetailsActivity;
	}

	public interface ProjectDetailsListener {
		public void setProjectDetails(ProjectDetail projectDetail);
	}

}
