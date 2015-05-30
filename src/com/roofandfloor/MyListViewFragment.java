package com.roofandfloor;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.roofandfloor.manager.ProjectListManager;
import com.roofandfloor.manager.ProjectListManager.ProjectListListener;
import com.roofandfloor.model.ProjectList;
import com.roofandfloor.service.ServiceInvoker;

public class MyListViewFragment extends Fragment implements
		ProjectListListener, OnItemClickListener {

	private ListView lv;
	private List<ProjectList> projectList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View theView = inflater.inflate(R.layout.list_view_fragment, container,
				false);
		lv = (ListView) theView.findViewById(R.id.project_list);
		lv.setOnItemClickListener(this);

		ProjectListManager projectListManager = new ProjectListManager();
		projectListManager.setMyListViewFragment(this);

		new ServiceInvoker(projectListManager).execute();
		Log.d("------------------> ", "Service invoked");
		return theView;
	}

	private void refreshView() {
		ArrayAdapter<ProjectList> adapter = new ArrayAdapter<ProjectList>(
				getActivity(), android.R.layout.simple_list_item_1, projectList);
		lv.setAdapter(adapter);
	}

	@Override
	public void setProjectList(List<ProjectList> projectList) {
		this.projectList = projectList;

		Log.d("project list ", projectList.toString());
		refreshView();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getActivity(), projectList.get(position).getProjectName(), Toast.LENGTH_SHORT).show();
	}

}
