package com.lechenggu.bkeasygo.goods.pojo;

import java.io.Serializable;
import java.util.Date;

import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;

public class GoodsBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 73708507194541725L;
	
	private int gid;
	private String gname;
	private int supid;
	private int subid;
	private double gprice;
	private int gnumber;
	private String gbigpic;
	//private String gintroduce;
	private int iftop;
	private int createman;
	private Date createtime;
	private String supname;
	private String subname;
	private String gnumberroduce;
	private String myFiles;
	private int page;
	//一的一方
	private SupBean sup;
	private SubCategoryBean sub;
	
	public SubCategoryBean getSub() {
		return sub;
	}
	public void setSub(SubCategoryBean sub) {
		this.sub = sub;
	}
	public SupBean getSup() {
		return sup;
	}
	public void setSup(SupBean sup) {
		this.sup = sup;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getMyFiles() {
		return myFiles;
	}
	public void setMyFiles(String myFiles) {
		this.myFiles = myFiles;
	}
	
	public String getGnumberroduce() {
		return gnumberroduce;
	}
	public void setGnumberroduce(String gnumberroduce) {
		this.gnumberroduce = gnumberroduce;
	}
	public String getSupname() {
		return supname;
	}
	public void setSupname(String supname) {
		this.supname = supname;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getSupid() {
		return supid;
	}
	public void setSupid(int supid) {
		this.supid = supid;
	}
	public int getSubid() {
		return subid;
	}
	public void setSubid(int subid) {
		this.subid = subid;
	}
	public double getGprice() {
		return gprice;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public int getGnumber() {
		return gnumber;
	}
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}
	public String getGbigpic() {
		return gbigpic;
	}
	public void setGbigpic(String gbigpic) {
		this.gbigpic = gbigpic;
	}
	/*public String getGintroduce() {
		return gintroduce;
	}
	public void setGintroduce(String gintroduce) {
		this.gintroduce = gintroduce;
	}*/
	public int getIftop() {
		return iftop;
	}
	public void setIftop(int iftop) {
		this.iftop = iftop;
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
