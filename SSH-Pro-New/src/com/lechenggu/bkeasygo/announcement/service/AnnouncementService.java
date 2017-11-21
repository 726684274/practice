package com.lechenggu.bkeasygo.announcement.service;

import java.util.List;

import com.lechenggu.bkeasygo.announcement.pojo.AnnouncementBean;

public interface AnnouncementService {
	/*
	 * 判断标题和内容不能为空，并且不能为空字符串
	 */
	public boolean isNull(String title, String inner);

	// 调用Dao层中的新增方法
	public int addAnnouncement(AnnouncementBean acb);
	/*
	 * 查询所有公告
	 */
	public List<AnnouncementBean> queryAnnouncement();
	/**
	 * 调用Dao层中的方法查询总页数
	 */
	public int queryCount(int n);

	/**
	 * 调用Dao层中的方法查询每页的数据
	 */
	public List<AnnouncementBean> queryByPage(int page, int n);

	/*
	 * 根据id调用dao层的方法，查询公告
	 */
	public AnnouncementBean queryAnnouncementById(int id);

	/**
	 * 根据id调用dao层的方法 删除公告
	 */
	public boolean deleteAnnouncementById(int id);

	/*
	 * 根据id调用dao层的方法 修改公告
	 */
	public boolean updateAnnouncement(AnnouncementBean ab);
}
