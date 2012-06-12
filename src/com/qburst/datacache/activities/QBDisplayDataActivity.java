package com.qburst.datacache.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qburst.datacache.QBDataCache;
import com.qburst.datacache.R;
import com.qburst.datacache.model.QBCacheObject;
import com.qburst.datacache.utilities.QBUtility;

public class QBDisplayDataActivity extends Activity {

	String _filename = "test_cache3";

	private List<QBCacheObject> _cacheList;

	private TextView _username;
	private TextView _password;
	private TextView _cardNumber;
	private TextView _ccvNumber;
	private TextView _expiryDate;
	private Button _viewData;
	private EditText _IDFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_data);

		retreiveDataFromQBDataCache();
		initViews();
		initButtonClickListener();

	}

	private void retreiveDataFromQBDataCache() {
		QBDataCache _showData = new QBDataCache(QBDisplayDataActivity.this);
		_cacheList = _showData.readActivityCache(_filename);
	}

	private void initViews() {

		_username = (TextView) findViewById(R.id.username);
		_password = (TextView) findViewById(R.id.password);
		_cardNumber = (TextView) findViewById(R.id.cardNumber);
		_ccvNumber = (TextView) findViewById(R.id.CCVNumber);
		_expiryDate = (TextView) findViewById(R.id.expiryDate);
		_viewData = (Button) findViewById(R.id.viewDetails);
		_IDFile = (EditText) findViewById(R.id.getFileId);
	}

	private void initButtonClickListener() {
		_viewData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				displayCachedData();

			}
		});
	}

	private void displayCachedData() {

		try {
			int file_id = Integer.parseInt(_IDFile.getText().toString());
			if (_cacheList.size() > 0) {

				for (int i = 0; i < _cacheList.size(); i++) {
					QBCacheObject cache = _cacheList.get(i);
					if (Integer.parseInt(cache.getId()) == file_id) {
						_username.setText(cache.getUsername());
						_password.setText(cache.getPassword());
						_cardNumber.setText(cache.getCardNumber());
						_ccvNumber.setText(cache.getCVVNumber());
						_expiryDate.setText(cache.getExpiryDate());
					} else{
						
						QBUtility.showToast("Cache not found.", QBDisplayDataActivity.this);
					}

				}

			} else {
				QBUtility.showToast("Cache is empty.", QBDisplayDataActivity.this);
			}
		} catch (Exception e) {
			QBUtility.showToast("Cache not found.", QBDisplayDataActivity.this);

		}
	}
}
