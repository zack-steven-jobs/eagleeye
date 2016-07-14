package com.cmsz.eagleeye.command;

import java.io.Serializable;

import com.cmsz.eagleeye.model.PageModel;

public class UserInfoCommand extends PageModel<UserInfoCommand> implements Serializable{

	private static final long serialVersionUID = 9081250349839684010L;
	//行号
	private int userId;
	//用户帐号名
	private String account;
	//用户名
	private String userName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "UserInfoModel [userId=" + userId + ", account=" + account
				+ ", userName=" + userName + "]";
	}
	
	
}
