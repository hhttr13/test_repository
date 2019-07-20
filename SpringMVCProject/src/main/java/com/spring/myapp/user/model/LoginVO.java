package com.spring.myapp.user.model;

public class LoginVO {
	private String userId;
	private String userPw;
	private boolean autoLogin;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public boolean isAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	@Override
	public String toString() {
		return "LoginVO [userId=" + userId + ", userPw=" + userPw + ", autoLogin=" + autoLogin + "]";
	}
	
	
}
