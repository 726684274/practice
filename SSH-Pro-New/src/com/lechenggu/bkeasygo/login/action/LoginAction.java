package com.lechenggu.bkeasygo.login.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.login.service.LoginService;
import com.lechenggu.bkeasygo.login.service.impl.LoginServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("")
public class LoginAction extends ActionSupport implements ModelDriven<AdminBean>{

	/**
	 * 添加序列化
	 */
	private static final long serialVersionUID = 6133396134869445630L;
	
	//关联service类 SS整合   不在new对象  将其注入到Spring容器中
	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	//私有化AdminBean
	private AdminBean ab=new AdminBean();
	//实现ModelDriven<AdminBean>的getModel()方法
	@Override
	public AdminBean getModel() {
		// TODO Auto-generated method stub
		return ab;
	}
	//登录方法
	public String login() throws Exception {
		AdminBean adminbean=getModel();
		//调用Dao层中的方法查询用户名和密码
		AdminBean abean=loginService.CkAdmin(adminbean);
		//间接访问servletAPI获取session对象
		Map<String, Object> map=ActionContext.getContext().getSession();
		//直接访问servletApi获取response对象
		HttpServletResponse response=ServletActionContext.getResponse();
		//判断adminbean
		if(abean!=null&&abean.getId()!=0){
			map.put("adminbean", abean);
			if(ab.getJzmm()!=null&&ab.getJzmm().equals("Y")){
//				创建cookie向浏览器发送用户名与密码
				Cookie cname=new Cookie("cname", ab.getName());
//				设置cookie存活时间
				cname.setMaxAge(60*60*24);
				response.addCookie(cname);
				Cookie cpwd=new Cookie("cpwd",ab.getPassword());
				cpwd.setMaxAge(60*60*24);
				response.addCookie(cpwd);
			}
			return SUCCESS;
		}else{
			return ERROR;
		}
	}//execute方法
}
