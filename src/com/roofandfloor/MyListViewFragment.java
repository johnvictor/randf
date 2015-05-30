package com.roofandfloor;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.roofandfloor.manager.ProjectListManager;
import com.roofandfloor.manager.ProjectListManager.ProjectListListener;
import com.roofandfloor.model.Project;
import com.roofandfloor.service.NetworkConnection;
import com.roofandfloor.service.ServiceInvoker;

public class MyListViewFragment extends Fragment implements
		ProjectListListener, OnItemClickListener {

	private ListView lv;
	private List<Project> projectList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View theView = inflater.inflate(R.layout.list_view_fragment, container,
				false);
		lv = (ListView) theView.findViewById(R.id.project_list);
		lv.setOnItemClickListener(this);

		ProjectListManager projectListManager = new ProjectListManager();
		projectListManager.setMyListViewFragment(this);
		if (!NetworkConnection.isNetworkConnected(getActivity())
				|| !NetworkConnection.isInternetAvailable(getActivity())) {
			getActivity().finish();
		}
		new ServiceInvoker(projectListManager).execute();
		Log.d("------------------> ", "Service invoked");
		return theView;
	}

	private void refreshView() {
		ArrayAdapter<Project> adapter = new ArrayAdapter<Project>(
				getActivity(), android.R.layout.simple_list_item_1, projectList);
		lv.setAdapter(adapter);
	}

	@Override
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;

		Log.d("project list ", projectList.toString());
		refreshView();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getActivity(),
				projectList.get(position).getProjectName(), Toast.LENGTH_SHORT)
				.show();
		Project project = projectList.get(position);
		Intent intent = new Intent(getActivity(), ProjectDetailsActivity.class);
		intent.putExtra("id", project.getId());
		intent.putExtra("projectName", project.getProjectName());
		intent.putExtra("lat", project.getLat());
		intent.putExtra("lon", project.getLon());
		startActivity(intent);
	}

}
