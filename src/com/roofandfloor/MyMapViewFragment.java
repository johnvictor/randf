package com.roofandfloor;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roofandfloor.manager.ProjectListManager;
import com.roofandfloor.manager.ProjectListManager.ProjectListListener;
import com.roofandfloor.model.Project;
import com.roofandfloor.service.NetworkConnection;
import com.roofandfloor.service.ServiceInvoker;

public class MyMapViewFragment extends Fragment implements ProjectListListener {

	MapView mMapView;
	private GoogleMap googleMap;
	private List<Project> projectList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// inflat and return the layout
		View v = inflater.inflate(R.layout.map_view_fragment, container, false);
		mMapView = (MapView) v.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);

		mMapView.onResume();// needed to get the map to display immediately

		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

		googleMap = mMapView.getMap();
		googleMap.getUiSettings().setZoomControlsEnabled(true);
		googleMap.setMyLocationEnabled(true);

		googleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				marker.showInfoWindow();
				LatLng latlng = marker.getPosition();

				for (int i = 0; i < projectList.size(); i++) {
					Project project = projectList.get(i);
					Log.d(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ",
							"OS project.getLat()" + project.getLat()
									+ " latlng.latitude " + latlng.latitude
									+ " project.getLon() " + project.getLon()
									+ " latlng.longitude" + latlng.longitude);
					if (project.getLat() == latlng.latitude
							&& project.getLon() == latlng.longitude) {
						Intent intent = new Intent(getActivity(),
								ProjectDetailsActivity.class);
						intent.putExtra("id", project.getId());
						intent.putExtra("projectName", project.getProjectName());
						intent.putExtra("lat", project.getLat());
						intent.putExtra("lon", project.getLon());
						startActivity(intent);
					}
				}

				return true;
			}

		});

		ProjectListManager projectListManager = new ProjectListManager();
		projectListManager.setProjectListListener(this);
		if (!NetworkConnection.isNetworkConnected(getActivity())) {
			return null;
		}
		new ServiceInvoker(projectListManager).execute();

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	@Override
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
		Log.d("--------------------->", " map " + projectList.toString());
		drawMarkers();
	}

	private void drawMarkers() {

		if (projectList != null && projectList.size() > 0) {

			Marker marker = null;
			for (int i = 0; i < projectList.size(); i++) {
				Project project = projectList.get(i);

				MarkerOptions markerOptions = new MarkerOptions()
						.position(
								new LatLng(project.getLat(), project.getLon()))
						.title(project.getProjectName())
						.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
								.decodeResource(getResources(),
										R.drawable.ic_home)));
				marker = googleMap.addMarker(markerOptions);
			}

			// marker.setDraggable(true);
			marker.showInfoWindow();

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(projectList.get(0).getLat(), projectList
							.get(0).getLon())).zoom(14).build();
			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
		}

	}
}
