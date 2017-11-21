package com.lechenggu.bkeasygo.goods.service;

import java.util.List;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;

public interface GoodsService {
	/*
	 * 调用GoodsDao中的商品新增方法
	 */
	public int goodsAdd(GoodsBean gb);

	/**
	 * 调用dao层中的方法查询总页数
	 */

	public int queryCount(int n);

	/**
	 * 调用Dao层的方法查询每页的记录
	 */
	public List<GoodsBean> queryByPage(int page, int n);

	/*
	 * 调用Dao层中的根据id查询商品查询方法
	 */
	public GoodsBean queryGoodsById(int id);

	/**
	 * 调用Dao层中的删除方法
	 */
	public boolean deleteGoodsById(int id);

	/*
	 * 调用Dao层中的根据id修改商品详情
	 */
	public boolean updateGoodsById(GoodsBean gb);
}
