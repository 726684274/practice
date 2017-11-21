package com.lechenggu.bkeasygo.sub.dao;

import java.util.List;

import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;

public interface SubCategoryDao {
	/*
	 * 小分类新增的方法
	 */
	public int addSub(SubCategoryBean scb);
	/*
	 * 通用的查询方法
	 */

	public List<SubCategoryBean> queryByCondition(String condition, Object[] obj);
	/*
	 * 查询所有的小分类
	 */
	public List<SubCategoryBean> querySub();
	/**
	 * 获得总页数
	 */
	public int queryCount();

	/**
	 * 获得每页的数据
	 */
	public List<Object> queryByPage(int page, int n);

	/*
	 * 根据id查询小分类
	 */
	public List<Object> querySubById(int id);

	/**
	 * 根据id修改小分类
	 */
	public boolean updateSubById(SubCategoryBean sb);

	/**
	 * 根据supid删除大分类
	 */
	public boolean deleteSubBySupid(int id);

	/**
	 * 根据id删除大分类
	 */
	public boolean deleteSubById(int id);

	/**
	 * 查询小分类的名字
	 */
	public List<SubCategoryBean> queryBySubname(int supid);

}
