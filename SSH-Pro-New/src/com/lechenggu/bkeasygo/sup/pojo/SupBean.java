package com.lechenggu.bkeasygo.sup.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;

public class SupBean implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = -3219700071262210747L;
	
	   private int supid;
	   private String supname ;          
	   private int createman ;          
	   private Date createtime;
	   private int page;
	   //引用小分类，表示一个大分类下面的所有小分类
	  private Set<SubCategoryBean> sub;
	  private Set<GoodsBean> goods;
	   
	public Set<GoodsBean> getGoods() {
		return goods;
	}
	public void setGoods(Set<GoodsBean> goods) {
		this.goods = goods;
	}
	public Set<SubCategoryBean> getSub() {
		return sub;
	}
	public void setSub(Set<SubCategoryBean> sub) {
		this.sub = sub;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSupid() {
		return supid;
	}
	public void setSupid(int supid) {
		this.supid = supid;
	}
	public String getSupname() {
		return supname;
	}
	public void setSupname(String supname) {
		this.supname = supname;
	}
	public int getCreateman() {
		return createman;
	}
	public void setCreateman(int createman) {
		this.createman = createman;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	   
}