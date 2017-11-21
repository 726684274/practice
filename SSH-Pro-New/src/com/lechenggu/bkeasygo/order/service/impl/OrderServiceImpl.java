package com.lechenggu.bkeasygo.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lechenggu.bkeasygo.excpetion.SysException;
import com.lechenggu.bkeasygo.order.dao.OrderDao;
import com.lechenggu.bkeasygo.order.dao.impl.OrderDaoImpl;
import com.lechenggu.bkeasygo.order.pojo.OrderBean;
import com.lechenggu.bkeasygo.order.service.OrderService;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;

public class OrderServiceImpl implements OrderService {
	// 关联OrderDao  SS整合   不在new对象  将其注入到Spring容器中
	private OrderDao od ;

	public OrderDao getOd() {
		return od;
	}

	public void setOd(OrderDao od) {
		this.od = od;
	}

	/**
	 * 调用OrderDao中的新增方法
	 * 
	 * @throws SysException
	 */
	public int OrderAdd(OrderBean ob) {
		// 判断订单人姓名必须为汉字的正则
		// if(!ValidationUtil.ifMatch(ob.getReceman(),
		// ValidationUtil.CHKTRUENAME)){
		// throw new SysException(10001, "订单人姓名只能为汉字，且至少两位");
		// }
		// if(!ValidationUtil.ifNotNull(ob.getReceman())){
		// throw new SysException(10002, "订单人姓名不能为空");
		// }

		int rows = od.OrderAdd(ob);
		return rows;
	}// OrderAdd
	
	/**
	 * 查询所有的订单
	 */
	public List<OrderBean> queryOrder(){
		return od.queryOrder();
	}
	/*
	 * 调用dao层中的方法查询总页数
	 */

	public int queryCount(int n) {
		int count = od.queryCount();
		return (count % n == 0 ? (count / n) : (count / n + 1));
	}

	/*
	 * 调用dao层中的方法查询查询每页的记录数
	 */
	public List<OrderBean> queryByPage(int page, int n) {

		return od.queryByPage(page, n);
	}

	/**
	 * 调用dao层中的根据id查询订单
	 */
	public OrderBean queryOrderById(int id) {
		return od.queryOrderById(id);
	}

	/*
	 * 调用Dao层中的修改方法
	 */
	public boolean updateOrderById(OrderBean ob) {
		return od.updateOrderById(ob);
	}

	/*
	 * 调用Dao中的删除方法
	 */
	public boolean deleteOrderById(int id) {
		boolean i = od.deleteOrderById(id);
		return i;
	}// deleteSubById

	/**
	 * 调用Dao层中的查询订单号和订货人
	 */
	public List<OrderBean> queryOrderByIdAndReceman(int oid, String receman) {
		/*List<Object> lo=od.queryOrderByIdAndReceman(oid, receman);
		List<OrderBean> lob=new ArrayList<OrderBean>();
		for (int i = 0; i < lo.size(); i++) {
			Object[] obj = (Object[]) lo.get(i);
			OrderBean ob = new OrderBean();
			if (obj.length > 0) {
				ob.setOid((int) obj[0]);
				ob.setReceman((String) obj[1]);
			}
			lob.add(ob);
		}*/
		return od.queryOrderByIdAndReceman(oid, receman);

	}

}
