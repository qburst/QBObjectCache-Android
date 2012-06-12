package com.qburst.datacache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.qburst.datacache.model.QBCacheObject;
import com.qburst.datacache.utilities.QBUtility;

public class QBDataCache {

	Context ctx;
	Activity _mActivity;
	private static final String CACHE_DIRECTORY = "QB-DataCache";
	public static final String DATA_PREFERENCE = "DATA_CACHE";

	File fileDir;

	public QBDataCache(Activity activity) {
		_mActivity = activity;
		ctx = activity.getApplicationContext();
		fileDir = new File(
				android.os.Environment.getExternalStorageDirectory(),
				CACHE_DIRECTORY);
	}

	/* Activities <START> */

	public void writeActivityCache(QBCacheObject modelObj, String fileName) {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			Boolean duplicate = false;
			List<QBCacheObject> list = null;
			QBCacheObject[] modelClass;
			File file = null;

			try {

				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}

				file = new File(fileDir, fileName);
				
			
				if (file.exists()) {

					FileInputStream fis = ctx.openFileInput(file.getName());
					ObjectInputStream is = new ObjectInputStream(fis);
					list = (List<QBCacheObject>) is.readObject();
					Log.i("<<<<list size>>>>> ", "" + list.size());
					is.close();

					if (list.size() != 0) {
						modelClass = new QBCacheObject[list.size()];

						for (int i = 0; i < list.size(); i++) {
							modelClass[i] = list.get(i);
							if (modelClass[i].getId().trim()
									.equals(modelObj.getId().trim())) {
								duplicate = true;
								QBUtility
										.showToast(
												"Duplicate file found. Could not cache data",
												_mActivity);
							}
						}
					}

				} else {
					file.createNewFile();
					list = new Vector<QBCacheObject>();
				}

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			FileOutputStream fos = null;
			ObjectOutputStream os = null;
			try {

				if (list != null && duplicate == false) {

					list.add(modelObj);
					fos = ctx.openFileOutput(file.getName(),
							Context.MODE_PRIVATE);
					os = new ObjectOutputStream(fos);
					os.writeObject(list);
					os.close();
					Log.i("<<<<WRITE CACHE>>>>>", "Success");
					QBUtility.showDialogOkWithGoBack("QBDataCacheDemo",
							"Successfully cached data.", _mActivity);
				}

			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
				QBUtility.showToast("File Not Found.", _mActivity);
			} catch (IOException e1) {

				e1.printStackTrace();
				QBUtility.showToast("Error occurred.", _mActivity);
			}

		} else {
			QBUtility.showToast("SD card not mounted.", _mActivity);
			Log.i("<<<SD CARD>>>>", "NOT MOUNTED");
		}
	}

	public List<QBCacheObject> readActivityCache(String fileName) {

		List<QBCacheObject> list = null;

		File file = null;

		try {

			if (!fileDir.exists()) {
				return null;
			}

			file = new File(fileDir, fileName);
			if (!file.exists()) {
				return null;
			}

			FileInputStream fis = ctx.openFileInput(file.getName());
			ObjectInputStream is = new ObjectInputStream(fis);
			list = (List<QBCacheObject>) is.readObject();
			is.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	/* Activities <END> */
	
	
	
	public void clearCache() {
		try {

			if (fileDir.exists()) {
			// clear SD cache
			File[] files = fileDir.listFiles();
			if (files != null) {
				for (File f : files)
					f.delete();
			}
			fileDir.delete();
			}
		} catch (Exception e) {

		}
		
		try {
		SharedPreferences keyValues = ctx.getApplicationContext().getSharedPreferences(DATA_PREFERENCE,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor keyValuesEditor = keyValues.edit();
		
		for (String key : keyValues.getAll().keySet()) {
			keyValuesEditor.remove(key);
			keyValuesEditor.commit();
			Log.i("Data>>>>REMOVE>>>>>", key );
		}
		} catch (Exception e) {

		}
	}

}
