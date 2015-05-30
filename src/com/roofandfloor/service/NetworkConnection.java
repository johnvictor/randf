package com.roofandfloor.service;

import java.net.InetAddress;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkConnection {
	
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			Toast.makeText(context, "Please check network connection",
					Toast.LENGTH_SHORT).show();
			return false;
		} else
			return true;
	}

	public static boolean isInternetAvailable(Context context) {
		try {
			InetAddress ipAddr = InetAddress.getByName("google.com");

			if (ipAddr.equals("")) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

	}
}
