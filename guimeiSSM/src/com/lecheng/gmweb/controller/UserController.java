package com.lecheng.gmweb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lecheng.gmweb.pojo.UserBean;
import com.lecheng.gmweb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="getUser.do")
	public String getUserByNP(UserBean user,HttpServletRequest req){
		UserBean u = userService.getUserByNP(user);
		HttpSession session = req.getSession();
		if(u!=null){
		session.setAttribute("user", u);
		return "index";
		}else{
			return "login";
		}
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
