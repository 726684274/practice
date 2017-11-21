package com.lechenggu.bkeasygo.sub.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.sup.service.impl.SupServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;


public class QuerySupnameAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//关联大分类的service
	SupService ss=new SupServiceImpl();
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	//查询大分类的名字
	public String querySupname(){
		/*List<Map<String, Object>> ls=ss.queryBySupname();
		//设置作用域
		ServletActionContext.getRequest().setAttribute("ls", ls);*/
		if(type==1){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
}
