package com.brook.weather.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Stay
 * @version create time：Sep 15, 2014 12:56:14 PM
 */
public class L {
	private static final String TAG = "kaikaibao";
	public static boolean DEBUG = true;

	public static void d(String msg) {
		if (DEBUG) {
			Log.d(TAG, msg);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			Log.e(TAG, msg);
		}
	}

	public static void showToast(Context mContext, String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context mContext, String msg, int mode) {
		Toast.makeText(mContext, msg, mode).show();
	}

	public static void showToast(Context mContext, int msgId) {
		Toast.makeText(mContext, msgId, Toast.LENGTH_SHORT).show();
	}
}
