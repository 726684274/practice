package com.lechenggu.bkeasygo.goods.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sub.service.SubCategoryService;
import com.lechenggu.bkeasygo.sub.service.impl.SubCategoryServiceImpl;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.sup.service.impl.SupServiceImpl;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
public class AjaxSupSubnameAction extends ActionSupport {
	//SS整合   不在new对象  将其注入到Spring容器中
	private SupService ss;
	private SubCategoryService scb;
	
	public SupService getSs() {
		return ss;
	}

	public void setSs(SupService ss) {
		this.ss = ss;
	}

	public SubCategoryService getScb() {
		return scb;
	}

	public void setScb(SubCategoryService scb) {
		this.scb = scb;
	}
	private String myJSON;
	private GoodsBean gb;
	public GoodsBean getGb() {
		return gb;
	}

	public void setGb(GoodsBean gb) {
		this.gb = gb;
	}

	public String getMyJSON() {
		return myJSON;
	}

	public void setMyJSON(String myJSON) {
		this.myJSON = myJSON;
	}

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
	public String querySubname(){
		//使用ServletActionContext的静态方法：直接方式获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		int  supid=StringUtil.strToInt(request.getParameter("supid"));
		List<SubCategoryBean> lsb=scb.queryBySubname(supid);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (SubCategoryBean s : lsb) {
        	Map<String, Object> maps = new HashMap<String, Object>();
        	maps.put("subid", s.getSubid());
        	maps.put("subname", s.getSubname());
        	resultList.add(maps);
        }
		JSONArray ja=JSONArray.fromObject(resultList);
		myJSON=ja.toString();
		return SUCCESS;
	}

}
