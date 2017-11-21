package com.lechenggu.bkeasygo.sub.service;

import java.util.List;

import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;

public interface SubCategoryService {
	/*
	 * 调用SubCategoryDao中的新增方法
	 */
	public int addSubCategoryDao(SubCategoryBean scb);

	/*
	 * 判空方法
	 */
	public boolean isNull(String smallClass);
	/*
	 * 查询所有小分类
	 */
	public List<SubCategoryBean> querySub();
	/**
	 * 调用Dao层的方法 查询总页数
	 */
	public int quertCount(int n);

	/**
	 * 调用Dao层的方法 查询每页的数据
	 */
	public List<SubCategoryBean> queryByPage(int page, int n);

	/*
	 * 调用dao层中的根据id查询小分类
	 */
	public SubCategoryBean querySubById(int id);

	/**
	 * 调用Dao层中的根据id修改小分类的方法
	 */
	public boolean updateSubById(SubCategoryBean sb);

	/*
	 * 调用Dao中的删除方法
	 */
	public boolean deleteSubById(int id);

	/**
	 * 查询小分类的名字
	 */
	public List<SubCategoryBean> queryBySubname(int supid);
}
