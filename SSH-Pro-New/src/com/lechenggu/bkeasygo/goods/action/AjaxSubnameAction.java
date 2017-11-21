package com.lechenggu.bkeasygo.goods.action;

import java.util.List;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sub.service.SubCategoryService;
import com.lechenggu.bkeasygo.sub.service.impl.SubCategoryServiceImpl;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.sup.service.impl.SupServiceImpl;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

@SuppressWarnings("serial")
public class AjaxSubnameAction extends ActionSupport{
	SupService ss=new SupServiceImpl();
	SubCategoryService scb=new SubCategoryServiceImpl();
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
	public String querySubname(){
		//int supid=StringUtil.strToInt(request.getParameter("supid"));
		int  supid=gb.getSupid();
		List<SubCategoryBean> lsb=scb.queryBySubname(supid);
		JSONArray ja=JSONArray.fromObject(lsb);
		myJSON=ja.toString();
		return SUCCESS;
	}
}
