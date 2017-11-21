package com.lechenggu.bkeasygo.login.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginExitAction extends ActionSupport {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	public String loginexit() {
		//利用直接方式获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("adminbean");
		// 直接访问servletApi获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		// 创建cookie对象 名字相同会覆盖之前的
		Cookie cname = new Cookie("cname", null);
		// 设置cookie存活时间
		cname.setMaxAge(0);
		response.addCookie(cname);
		Cookie cpwd = new Cookie("cpwd", null);
		cpwd.setMaxAge(0);
		response.addCookie(cpwd);
		return SUCCESS;
	}

}
