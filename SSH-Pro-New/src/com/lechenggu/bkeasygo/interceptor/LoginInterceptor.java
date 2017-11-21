package com.lechenggu.bkeasygo.interceptor;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation ai) throws Exception {
		//获得session对象，并获得session中的adminbean
		AdminBean ab=(AdminBean)ActionContext.getContext().getSession().get("adminbean");
		if(ab==null){
			return "reLogin";
		}else{
			return ai.invoke();
		}
		
	}

}
