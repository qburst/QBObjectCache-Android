package com.qburst.datacache.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.qburst.datacache.QBDataCache;
import com.qburst.datacache.model.QBCacheObject;
import com.qburst.datacache.R;

public class QBCacheActivity extends Activity {

	String _filename = "test_cache3";

	private EditText _username;
	private EditText _password;
	private EditText _cardNumber;
	private EditText _ccvNumber;
	private EditText _expiryDate;
	private EditText _fileID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_data);

		initViews();
	}

	private void initViews() {
		_username = (EditText) findViewById(R.id.username);
		_password = (EditText) findViewById(R.id.password);
		_cardNumber = (EditText) findViewById(R.id.cardNumber);
		_ccvNumber = (EditText) findViewById(R.id.CCVNumber);
		_expiryDate = (EditText) findViewById(R.id.expiryDate);
		_fileID=(EditText)findViewById(R.id.setFileId);

		Button saveData = (Button) findViewById(R.id.saveDetails);
		saveData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				writeToQBDataCache();

			}
		});
	}

	private void writeToQBDataCache() {
		QBCacheObject cache = new QBCacheObject();
		cache.setId(_fileID.getText().toString());
		cache.setUsername(_username.getText().toString());
		cache.setPassword(_password.getText().toString());
		cache.setCardNumber(_cardNumber.getText().toString());
		cache.setCVVNumber(_ccvNumber.getText().toString());
		cache.setExpiryDate(_expiryDate.getText().toString());

		QBDataCache dataCache = new QBDataCache(QBCacheActivity.this);

		dataCache.writeActivityCache(cache, _filename);
		
	}

}
