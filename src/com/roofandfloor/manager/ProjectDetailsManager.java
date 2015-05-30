package com.roofandfloor.manager;

import java.util.List;

import com.roofandfloor.model.ProjectList;

public class ProjectDetailsManager {
	
	public interface ProjectDetailsListener{
		public void setProjectDetails(List<ProjectList> projectList);
	}

}
