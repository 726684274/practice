package com.lechenggu.bkeasygo.goods.dao;

import java.util.List;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;

public interface GoodsDao {
	/*
	 * 商品新增的方法
	 */
	public int goodsAdd(GoodsBean gBean);

	/*
	 * 通用的查询方法
	 */
	public List<GoodsBean> queryByCondition(String condition, Object[] obj);

	/**
	 * 查询总页数
	 */
	public int queryCount();

	/**
	 * 查询每页的记录数
	 */
	public List<Object> queryByPage(int page, int n);

	/*
	 * 根据id查询商品
	 */
	public List<Object> queryGoodsById(int id);

	/**
	 * 根据supid删除商品
	 */
	public boolean deleteGoodsBySupid(int id);

	/**
	 * 根据id删除商品
	 */
	public boolean deleteGoodsById(int id);

	/*
	 * 根据商品的id修改商品详情
	 */
	public boolean updateGoodsById(GoodsBean gb);
}
