package com.roofandfloor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.roofandfloor.manager.ProjectDetailsManager;
import com.roofandfloor.manager.ProjectDetailsManager.ProjectDetailsListener;
import com.roofandfloor.model.Project;
import com.roofandfloor.model.ProjectDetail;
import com.roofandfloor.service.NetworkConnection;
import com.roofandfloor.service.ProjectDetailsServiceInvoker;

public class ProjectDetailsActivity extends Activity implements
		ProjectDetailsListener {

	private TextView tv;
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.project_details_activity);

		tv = (TextView) findViewById(R.id.tv);
		lv = (ListView) findViewById(R.id.documentsList);

		Intent intent = getIntent();
		Project project = new Project();
		project.setId(intent.getStringExtra("id"));
		project.setProjectName(intent.getStringExtra("projectName"));
		project.setLat(intent.getDoubleExtra("lat", 0d));
		project.setLon(intent.getDoubleExtra("lon", 0d));

		setTitle(project.getProjectName());

		ProjectDetailsManager projectDetailsManager = new ProjectDetailsManager();
		projectDetailsManager.setProjectDetailsActivity(this);

		if (!NetworkConnection.isNetworkConnected(this)
				|| !NetworkConnection.isInternetAvailable(this)) {
			finish();
		}
		new ProjectDetailsServiceInvoker(projectDetailsManager, project)
				.execute();
	}

	@Override
	public void setProjectDetails(ProjectDetail projectDetail) {
		String text = "<b> Address </b><br/><br/>"
				+ projectDetail.getAddressLine1() + " "
				+ projectDetail.getAddressLine2() + "<br/>"
				+ projectDetail.getLocality() + "<br/>"
				+ projectDetail.getCity() + "<br/><br/>"
				+ "<b> Description </b><br/><br/>"
				+ projectDetail.getDescription() + "<br/><br/>"
				+ "<b> Water Types </b><br/><br/>"
				+ projectDetail.getWaterTypes() + "<br/><br/>"
				+ "<b> Builder </b><br/><br/>" + projectDetail.getBuilderName();

		tv.setText(Html.fromHtml(text));

		lv.setAdapter(new CustomListAdapter(this, projectDetail
				.getProjectDocumentList()));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
