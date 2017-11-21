package com.lechenggu.bkeasygo.order.dao;

import java.util.List;

import com.lechenggu.bkeasygo.order.pojo.OrderBean;

public interface OrderDao {
	/**
	 * 新增订单
	 * 
	 */
	public int OrderAdd(OrderBean ob);

	/**
	 * 通用的查询方法
	 */
	public List<OrderBean> queryByCondition(String condition, Object[] obj);
	
	/**
	 * 查询所有订单
	 */
	public List<OrderBean> queryOrder();
	
	/*
	 * 查询总页数
	 */
	public int queryCount();

	/*
	 * 查询每页的记录数
	 */
	public List<OrderBean> queryByPage(int page, int n);

	/**
	 * 根据id查询订单
	 */
	public OrderBean queryOrderById(int id);

	/*
	 * 根据id修改订单
	 */
	public boolean updateOrderById(OrderBean ob);

	/**
	 * 根据id删除大分类
	 */
	public boolean deleteOrderById(int id);

	/*
	 * 查询订货号与订货人
	 */
	public List<OrderBean> queryOrderByIdAndReceman(int oid,String receman);
}
