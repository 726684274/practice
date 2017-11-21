package com.lechenggu.bkeasygo.order.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 173854428061167356L;
	private int oid;
	private int userid;
	private Date otime;
	private String receman;
	private String receaddress;
	private double omoney;
	private String ostate;
	private int createman;
	private Date createtime;
	private int updateman;
	private String updatetime;

	private int page;

	// 模糊查询 订单号和订单人
	private int selectoid;
	private String selectreceman;

	public int getSelectoid() {
		return selectoid;
	}

	public void setSelectoid(int selectoid) {
		this.selectoid = selectoid;
	}

	public String getSelectreceman() {
		return selectreceman;
	}

	public void setSelectreceman(String selectreceman) {
		this.selectreceman = selectreceman;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	

	public Date getOtime() {
		return otime;
	}

	public void setOtime(Date otime) {
		this.otime = otime;
	}

	public String getReceman() {
		return receman;
	}

	public void setReceman(String receman) {
		this.receman = receman;
	}

	public String getReceaddress() {
		return receaddress;
	}

	public void setReceaddress(String receaddress) {
		this.receaddress = receaddress;
	}

	public double getOmoney() {
		return omoney;
	}

	public void setOmoney(double omoney) {
		this.omoney = omoney;
	}

	public String getOstate() {
		return ostate;
	}

	public void setOstate(String ostate) {
		this.ostate = ostate;
	}

	public int getUpdateman() {
		return updateman;
	}

	public void setUpdateman(int updateman) {
		this.updateman = updateman;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}
