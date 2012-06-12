package com.qburst.datacache.model;

import java.io.Serializable;

public class QBCacheObject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	private String _username;
	private String _password;
	private String _cardNumber;
	private String _ccvNumber;
	private String _expirydate;
	
	public String getUsername() {
		return _username;
	}
	public void setUsername(String username) {
		_username = username;
	}
	public String getPassword() {
		return _password;
	}
	public void setPassword(String password) {
		_password = password;
	}
	public String getCardNumber() {
		return _cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		_cardNumber = cardNumber;
	}
	public String getCVVNumber() {
		return _ccvNumber;
	}
	public void setCVVNumber(String ccvNumber) {
		_ccvNumber = ccvNumber;
	}
	public String getExpiryDate() {
		return _expirydate;
	}
	public void setExpiryDate(String expirydate) {
		_expirydate = expirydate;
	}
	public void setId(String _id) {
		this._id = _id;
	}
	public String getId() {
		return _id;
	}
	
	

}
