package com.catic.mobilehos.service.vo;

public class LoginUserVO {
	
	private String userId;
	
	private String userName;
	
	/**
	 * 用户是否可以有效登陆
	 */
	private boolean valid;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	

	
	

}
