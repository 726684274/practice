package com.lechenggu.bkeasygo.user.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.lechenggu.bkeasygo.user.service.UserService;
import com.lechenggu.bkeasygo.user.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CKUsernameAction extends ActionSupport{
	//SS整合   不在new对象  将其注入到Spring容器中
	private UserService us;
	
	public UserService getUs() {
		return us;
	}
	public void setUs(UserService us) {
		this.us = us;
	}
	//接收提示信息   并返回给页面
	private InputStream myMSG;
	private String  ckname;
	public InputStream getMyMSG() {
		return myMSG;
	}
	public void setMyMSG(InputStream myMSG) {
		this.myMSG = myMSG;
	}
	public String getCkname() {
		return ckname;
	}
	public void setCkname(String ckname) {
		this.ckname = ckname;
	}
	@Override
	public String execute() throws Exception {
		int i=us.CKUserName(getCkname());
		if(i>0){
			myMSG=new ByteArrayInputStream("用户名已经存在！".getBytes("UTF-8"));
		}
		return SUCCESS;
	}
}
