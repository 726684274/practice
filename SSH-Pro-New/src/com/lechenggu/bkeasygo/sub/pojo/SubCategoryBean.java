package com.lechenggu.bkeasygo.sub.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;

public class SubCategoryBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8023993181217852045L;
	 	private int subid;               
	    private String subname;              
	    private int supid;                
	    private int createman;            
	    private Date createtime;
	    private String supname;
	    private int page;
	    private int supidhidden;
	    //引用大分类，表示一的一方；
	    private SupBean sup;
	    //表示多的一方
	    private Set<GoodsBean> goods;
	    
		public Set<GoodsBean> getGoods() {
			return goods;
		}
		public void setGoods(Set<GoodsBean> goods) {
			this.goods = goods;
		}
		public SupBean getSup() {
			return sup;
		}
		public void setSup(SupBean sup) {
			this.sup = sup;
		}
		public int getSupidhidden() {
			return supidhidden;
		}
		public void setSupidhidden(int supidhidden) {
			this.supidhidden = supidhidden;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getSubid() {
			return subid;
		}
		public void setSubid(int subid) {
			this.subid = subid;
		}
		public String getSubname() {
			return subname;
		}
		public void setSubname(String subname) {
			this.subname = subname;
		}
		public int getSupid() {
			return supid;
		}
		public void setSupid(int supid) {
			this.supid = supid;
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
		public String getSupname() {
			return supname;
		}
		public void setSupname(String supname) {
			this.supname = supname;
		}
		@Override
		public String toString() {
			return "SubCategoryBean [subid=" + subid + ", subname=" + subname + ", supid=" + supid + ", createman="
					+ createman + ", createtime=" + createtime + ", page=" + page + ", supidhidden=" + supidhidden
					+ ", sup=" + sup + "]";
		}

}
