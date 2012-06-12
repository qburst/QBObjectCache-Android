package com.qburst.datacache.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.qburst.datacache.QBDataCache;
import com.qburst.datacache.R;
import com.qburst.datacache.model.QBCacheObject;
import com.qburst.datacache.utilities.QBUtility;

public class QBHomeActivity extends Activity {

	private Button _saveButton;
	private Button _viewButton;
	private Button _deleteAllButton;
	String _filename = "test_cache3";
	QBDataCache _delData;
	List<QBCacheObject> _cacheList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initViews();
		initClickListeners();

	}

	private void initViews() {
		_saveButton = (Button) findViewById(R.id.saveButton);
		_viewButton = (Button) findViewById(R.id.viewButton);
		_deleteAllButton = (Button) findViewById(R.id.deleteAllButton);
	}

	private void initClickListeners() {
		_saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(QBHomeActivity.this,
						QBCacheActivity.class);
				startActivity(intent);

			}
		});

		_viewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent newIntent = new Intent(QBHomeActivity.this,
						QBDisplayDataActivity.class);
				startActivity(newIntent);
			}
		});

		_deleteAllButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				deleteAllContent();
			}
		});
	}

	private void deleteAllContent() {

		_delData = new QBDataCache(QBHomeActivity.this);
		_delData.clearCache();

		QBUtility.showToast("Successfully deleted cache.", QBHomeActivity.this);

	}
}