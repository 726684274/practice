package com.lechenggu.bkeasygo.announcement.dao;

import java.util.List;

import com.lechenggu.bkeasygo.announcement.pojo.AnnouncementBean;

public interface AnnouncementDao {
	// 公告新增方法
		public int announcementAdd(AnnouncementBean acb);
		/*
		 * 通用的sql查询语句
		 */
		public List<AnnouncementBean> queryByCondition(String condition, Object[] obj);
		/**
		 * 查询所有公告
		 */
		public List<AnnouncementBean> queryAnnouncement();
		/*
		 * 查询总记录数
		 */
		public int queryCount();
		/*
		 * 查询每页的记录数
		 */
		public List<AnnouncementBean> queryCountByPage(int page, int n);
		/**
		 * 根据id查询公告
		 */
		public AnnouncementBean queryAnnouncementById(int id);
		/**
		 * 根据id删除公告
		 */
		public boolean deleteAnnouncementById(int id);
		/*
		 * 根据id修改公告
		 */
		public boolean updateAnnouncement(AnnouncementBean ab);
}
