package com.lechenggu.bkeasygo.sup.dao;

import java.util.List;

import com.lechenggu.bkeasygo.sup.pojo.SupBean;

public interface SupDao {
	// 新增大分类
	public int addClassName(SupBean sb);

	// 通用的sql语句查询方法
	/*public List<SupBean> QuerySupByCondition(String condition, Object[] obj);*/
	/*
	 * 查询所有大分类
	 */
	public List<SupBean> querySup();

	/**
	 * 查询大分类的总页数
	 */
	public int queryCount();

	/**
	 * 查询每一个的记录
	 */

	public List<SupBean> queryByPage(int page, int n);

	/**
	 * 根据id查询大分类
	 */
	public SupBean querySupById(int id);

	/**
	 * 修改的方法
	 */
	public boolean updateSup(SupBean sb);

	/**
	 * 根据id删除大分类
	 */
	public boolean deleteSupById(int id);

	// 查询大分类名字
	public List<SupBean> querySupname();

}
