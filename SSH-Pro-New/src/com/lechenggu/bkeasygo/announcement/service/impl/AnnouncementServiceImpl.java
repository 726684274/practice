package com.lechenggu.bkeasygo.announcement.service.impl;

import java.util.List;

import com.lechenggu.bkeasygo.announcement.dao.AnnouncementDao;
import com.lechenggu.bkeasygo.announcement.dao.impl.AnnouncementDaoImpl;
import com.lechenggu.bkeasygo.announcement.pojo.AnnouncementBean;
import com.lechenggu.bkeasygo.announcement.service.AnnouncementService;
import com.lechenggu.bkeasygo.utils.DBUtil;

public class AnnouncementServiceImpl implements AnnouncementService {
	// 关联DBUtil
	DBUtil db = new DBUtil();
	// 关联AnnouncementAddDao  SS整合，不在new对象，将其注入到Spring容器中
	private AnnouncementDao aad;
	
	public AnnouncementDao getAad() {
		return aad;
	}

	public void setAad(AnnouncementDao aad) {
		this.aad = aad;
	}

	/*
	 * 判断标题和内容不能为空，并且不能为空字符串
	 */
	public boolean isNull(String title, String inner) {
		if (title != null && inner.equals("")) {
			return false;
		} else {
			if ((!title.equals("") && title != null) && (!inner.equals("") && inner != null)) {
				return true;
			}
			return false;
		}
	}

	// 调用Dao层中的新增方法
	public int addAnnouncement(AnnouncementBean acb) {
		int rows = aad.announcementAdd(acb);
		return rows;
	}// addAnnouncement
	/*
	 * 查询所有公告
	 */
	public List<AnnouncementBean> queryAnnouncement(){
		return aad.queryAnnouncement();
	}
	/**
	 * 调用Dao层中的方法查询总页数
	 */
	public int queryCount(int n) {
		int count = aad.queryCount();
		return (count % n == 0 ? (count / n) : (count / n + 1));
	}

	/**
	 * 调用Dao层中的方法查询每页的数据
	 */
	public List<AnnouncementBean> queryByPage(int page, int n) {

		return aad.queryCountByPage(page, n);
	}

	/*
	 * 根据id调用dao层的方法，查询公告
	 */
	public AnnouncementBean queryAnnouncementById(int id) {
		return aad.queryAnnouncementById(id);
	}

	/**
	 * 根据id调用dao层的方法 删除公告
	 */
	public boolean deleteAnnouncementById(int id) {
		return aad.deleteAnnouncementById(id);
	}// deleteAnnouncementById

	/*
	 * 根据id调用dao层的方法 修改公告
	 */
	public boolean updateAnnouncement(AnnouncementBean ab) {
		return aad.updateAnnouncement(ab);
	}

}
