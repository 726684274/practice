package com.lechenggu.bkeasygo.sub.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.sup.service.impl.SupServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
public class AjaxSupnameAction extends ActionSupport{
	//SS整合   不在new对象  将其注入到Spring容器中
	private SupService ss;
	
	public SupService getSs() {
		return ss;
	}
	public void setSs(SupService ss) {
		this.ss = ss;
	}
	
	private String myJSON;
	public String getMyJSON() {
		return myJSON;
	}
	public void setMyJSON(String myJSON) {
		this.myJSON = myJSON;
		
	}
	//查询大分类的名字
	public String querySupname(){
		
		List<SupBean> lsb=ss.queryBySupname();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
        for (SupBean s : lsb) {
        	Map<String, Object> maps = new HashMap<String, Object>();
        	maps.put("id", s.getSupid());
        	maps.put("admin",s.getCreateman());
        	maps.put("name", s.getSupname());
        	resultList.add(maps);
        }
        JSONArray ja = JSONArray.fromObject(resultList);
        myJSON = ja.toString();
        return SUCCESS;
	}
	
}
