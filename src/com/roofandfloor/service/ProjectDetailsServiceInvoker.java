package com.roofandfloor.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

import com.roofandfloor.manager.ProjectDetailsManager;
import com.roofandfloor.model.Project;

public class ProjectDetailsServiceInvoker extends AsyncTask<Void, Void, String> {

	private String baseUrl = "http://54.254.240.217:8080/";
	private String app_api = "app-task/";
	private String urlToRead;

	private ProjectDetailsManager projectDetailsManager;

	public ProjectDetailsServiceInvoker(
			ProjectDetailsManager projectDetailsManager, Project project) {
		this.projectDetailsManager = projectDetailsManager;
		urlToRead = baseUrl + app_api + "projects/" + project.getId();
	}

	@Override
	protected String doInBackground(Void... params) {
		Log.d("---------------> ", "doin in background");
		return getResponse(urlToRead);
	}

	@Override
	protected void onPostExecute(String result) {
		Log.d("---------------> ", "post execite " + result);
		projectDetailsManager.populateProjectDetails(result);
	}

	private String getResponse(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

}
