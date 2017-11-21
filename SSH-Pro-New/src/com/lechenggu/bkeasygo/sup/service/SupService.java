package com.lechenggu.bkeasygo.sup.service;

import java.util.List;

import com.lechenggu.bkeasygo.sup.pojo.SupBean;

public interface SupService {
	/*
	 * 判空方法
	 */
	public boolean isNull(String className);

	/*
	 * 添加分类名称方法
	 */
	public int ClassNameAdd(SupBean sb);
	/**
	 * 查询所有大分类
	 */
	public List<SupBean> querySup();
	/**
	 * 查询总页数
	 */
	public int queryCountPage(int n);

	/**
	 * 查询每一页的记录
	 */
	public List<SupBean> queryByPage(int page, int n);

	/**
	 * 根据id调用dao层的方法，查询大分类
	 */
	public SupBean querySupById(int id);

	/**
	 * 修改的方法
	 */
	public boolean updateSup(SupBean sb);

	/*
	 * 调用Dao中的删除方法
	 */
	public boolean deleteSupById(int id);

	/**
	 * 查询大分类的名字
	 * 
	 * @return
	 */
	public List<SupBean> queryBySupname();
}
