package com.roofandfloor.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProjectDetail {

	private String addressLine1;
	private String addressLine2;
	private String locality;
	private String city;
	private String description;
	private String builderName;
	private String waterTypes;
	private List<ProjectDocument> projectDocumentList;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuilderName() {
		return builderName;
	}

	public void setBuilderName(String builderName) {
		this.builderName = builderName;
	}

	public String getWaterTypes() {
		return waterTypes;
	}

	public void setWaterTypes(String waterTypes) {
		this.waterTypes = waterTypes;
	}

	public ProjectDetail getProjectDetail(String response) {
		ProjectDetail projectDetail = new ProjectDetail();

		try {
			JSONObject object = new JSONObject(response);
			projectDetail.setAddressLine1(object.getString("addressLine1"));
			projectDetail.setAddressLine2(object.getString("addressLine2"));
			projectDetail.setLocality(object.getString("locality"));
			projectDetail.setCity(object.getString("city"));
			projectDetail
					.setDescription(object.getString("description") != null ? object
							.getString("description") : "---");
			projectDetail
					.setWaterTypes(object.getString("waterTypes") != null ? object
							.getString("waterTypes") : "---");
			projectDetail
					.setBuilderName(object.getString("builderName") != null ? object
							.getString("builderName") : "---");

			JSONArray documentArray = object.getJSONArray("documents");
			projectDocumentList = new ArrayList<ProjectDocument>();
			for (int i = 0; i < documentArray.length(); i++) {
				JSONObject doc = documentArray.getJSONObject(i);
				ProjectDocument projectDocument = new ProjectDocument();
				projectDocument.setText(doc.getString("text"));
				projectDocument.setReference(doc.getString("reference"));
				projectDocumentList.add(projectDocument);
			}
			projectDetail.setProjectDocumentList(projectDocumentList);
			return projectDetail;

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return projectDetail;
	}

	public List<ProjectDocument> getProjectDocumentList() {
		return projectDocumentList;
	}

	public void setProjectDocumentList(List<ProjectDocument> projectDocumentList) {
		this.projectDocumentList = projectDocumentList;
	}

}
