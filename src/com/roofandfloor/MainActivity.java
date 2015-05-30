package com.roofandfloor;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private String _fragmentTag = "dynamicFragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyListViewFragment theFragment = new MyListViewFragment();
		FragmentManager fm = getFragmentManager();

		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.group1, theFragment, _fragmentTag);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getItemId() == R.id.menu_list) {
			FragmentManager fm = getFragmentManager();
			Fragment oldFragment = fm.findFragmentByTag(_fragmentTag);

			FragmentTransaction ft = fm.beginTransaction();
			if (oldFragment != null) {
				ft.remove(oldFragment);
				MyListViewFragment newFragment = new MyListViewFragment();
				ft.add(R.id.group1, newFragment, _fragmentTag);
				ft.commit();
			}

		} else if (item.getItemId() == R.id.menu_map) {
			FragmentManager fm = getFragmentManager();
			Fragment oldFragment = fm.findFragmentByTag(_fragmentTag);

			FragmentTransaction ft = fm.beginTransaction();
			if (oldFragment != null) {
				ft.remove(oldFragment);
				MyMapViewFragment newFragment = new MyMapViewFragment();
				ft.add(R.id.group1, newFragment, _fragmentTag);
				ft.commit();
			}

		}
		return super.onOptionsItemSelected(item);
	}

}
