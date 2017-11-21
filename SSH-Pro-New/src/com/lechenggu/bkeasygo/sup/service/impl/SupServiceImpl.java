package com.lechenggu.bkeasygo.sup.service.impl;

import java.util.List;

import com.lechenggu.bkeasygo.sup.dao.SupDao;
import com.lechenggu.bkeasygo.sup.dao.impl.SupDaoImpl;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.utils.DBUtil;

public class SupServiceImpl implements SupService {
	// 关联DBUtil
	DBUtil db = new DBUtil();
	// 关联SupDao类   SS整合   不在new对象  将其注入到Spring容器中
	private SupDao sd;

	public SupDao getSd() {
		return sd;
	}

	public void setSd(SupDao sd) {
		this.sd = sd;
	}

	/*
	 * 判空方法
	 */
	public boolean isNull(String className) {
		if (!className.equals("") && className != null) {
			return true;
		} else {

			return false;
		}
	}// isnull

	/*
	 * 添加分类名称方法
	 */
	public int ClassNameAdd(SupBean sb) {
		int rows = sd.addClassName(sb);
		return rows;
	}
	/**
	 * 查询所有大分类
	 */
	public List<SupBean> querySup(){
		return sd.querySup();
	}
	/**
	 * 查询总页数
	 */
	public int queryCountPage(int n) {
		int count = sd.queryCount();
		return (count % n == 0 ? (count / n) : (count / n + 1));
	}// queryCountPage

	/**
	 * 查询每一页的记录
	 */
	public List<SupBean> queryByPage(int page, int n) {

		return sd.queryByPage(page, n);
	}

	/**
	 * 根据id调用dao层的方法，查询大分类
	 */
	public SupBean querySupById(int id) {
		return sd.querySupById(id);
	}

	/**
	 * 修改的方法
	 */
	public boolean updateSup(SupBean sb) {
		return sd.updateSup(sb);
	}

	/*
	 * 调用Dao中的删除方法
	 */
	public boolean deleteSupById(int id) {
		return sd.deleteSupById(id);
	}// deleteSupById

	/**
	 * 查询大分类的名字
	 * 
	 * @return
	 */
	public List<SupBean> queryBySupname() {
		List<SupBean> ls = sd.querySupname();
		return ls;
	}

}
