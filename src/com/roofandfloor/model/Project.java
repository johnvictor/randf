package com.roofandfloor.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Project {

	private String id;
	private String projectName;
	private double lat;
	private double lon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public List<Project> getProjectList(String response) {
		List<Project> projectList = new ArrayList<Project>();

		try {
			JSONArray jsonArray = new JSONArray(response);
			for (int n = 0; n < jsonArray.length(); n++) {
				JSONObject object = jsonArray.getJSONObject(n);

				Project proj = new Project();
				proj.setId(object.getString("id"));
				proj.setProjectName(object.getString("projectName"));
				proj.setLat(object.getDouble("lat"));
				proj.setLon(object.getDouble("lon"));

				projectList.add(proj);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return projectList;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return projectName;
	}

}
