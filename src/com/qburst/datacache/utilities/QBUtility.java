package com.qburst.datacache.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.widget.Toast;

public class QBUtility {
	
	public static void showDialogOk(String title, String message,
			Context context) {
		if(context!=null) {
			Dialog dlg = new AlertDialog.Builder(context).setTitle(title)
			.setMessage(message)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

				}
			}).create();
			dlg.setVolumeControlStream(AudioManager.STREAM_MUSIC);
			dlg.show();
		}
	}

	public static void showDialogOkWithGoBack(String title, String message,
			final Activity activity) {
		if(activity.getApplicationContext()!=null) { 
		AlertDialog.Builder adb = new AlertDialog.Builder(activity);
			adb.setTitle(title);
			adb.setMessage(message);
			adb.setCancelable(false); 
			adb.setNeutralButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					//activity.onBackPressed();
				}
			});
			AlertDialog ad = adb.create();
			ad.setVolumeControlStream(AudioManager.STREAM_MUSIC);
			ad.show();
		}
	}

	public static void showToast(String message, Context context) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
