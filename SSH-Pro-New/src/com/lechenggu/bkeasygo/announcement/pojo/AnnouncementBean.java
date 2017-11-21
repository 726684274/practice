package com.lechenggu.bkeasygo.announcement.pojo;

import java.io.Serializable;
import java.util.Date;

public class AnnouncementBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -564053955424414173L;
	
	private int bid;
	private String btitile;
	private String binner;
	private int createman;
	private Date createtime;
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBtitile() {
		return btitile;
	}
	public void setBtitile(String btitile) {
		this.btitile = btitile;
	}
	public String getBinner() {
		return binner;
	}
	public void setBinner(String binner) {
		this.binner = binner;
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
