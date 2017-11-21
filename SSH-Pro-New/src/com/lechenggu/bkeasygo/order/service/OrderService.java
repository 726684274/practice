package com.lechenggu.bkeasygo.order.service;

import java.util.List;

import com.lechenggu.bkeasygo.excpetion.SysException;
import com.lechenggu.bkeasygo.order.pojo.OrderBean;

public interface OrderService {
	/**
	 * 调用OrderDao中的新增方法
	 * 
	 * @throws SysException
	 */
	public int OrderAdd(OrderBean ob);
	
	/**
	 * 查询所有的订单
	 */
	public List<OrderBean> queryOrder();
	
	/*
	 * 调用dao层中的方法查询总页数
	 */
	public int queryCount(int n);

	/*
	 * 调用dao层中的方法查询查询每页的记录数
	 */
	public List<OrderBean> queryByPage(int page, int n);

	/**
	 * 调用dao层中的根据id查询订单
	 */
	public OrderBean queryOrderById(int id);

	/*
	 * 调用Dao层中的修改方法
	 */
	public boolean updateOrderById(OrderBean ob);

	/*
	 * 调用Dao中的删除方法
	 */
	public boolean deleteOrderById(int id);

	/**
	 * 调用Dao层中的查询订单号和订货人
	 */
	public List<OrderBean> queryOrderByIdAndReceman(int oid,String receman);
}
