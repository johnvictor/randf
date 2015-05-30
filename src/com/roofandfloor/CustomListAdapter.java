package com.roofandfloor;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.roofandfloor.model.ProjectDocument;
import com.roofandfloor.service.DownloadImageTask;
import com.roofandfloor.service.NetworkConnection;

public class CustomListAdapter extends BaseAdapter {

	Context context;
	private static LayoutInflater inflater = null;
	List<ProjectDocument> projectDocument;

	public CustomListAdapter(ProjectDetailsActivity projectDetailsActivity,
			List<ProjectDocument> projectDocument) {
		// TODO Auto-generated constructor stub
		this.projectDocument = projectDocument;
		context = projectDetailsActivity;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return projectDocument.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class Holder {
		TextView tv;
		ImageView img;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = new Holder();
		View rowView;
		rowView = inflater.inflate(R.layout.custom_list_item, null);
		holder.tv = (TextView) rowView.findViewById(R.id.tv);
		holder.img = (ImageView) rowView.findViewById(R.id.iv);
		holder.tv.setText(projectDocument.get(position).getText());

		if (NetworkConnection.isNetworkConnected(context)
				|| NetworkConnection.isInternetAvailable(context)) {
			new DownloadImageTask(holder.img).execute(projectDocument.get(
					position).getReference());
		}

		return rowView;
	}
}
