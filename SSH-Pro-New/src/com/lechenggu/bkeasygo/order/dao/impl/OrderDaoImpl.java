package com.lechenggu.bkeasygo.order.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.lechenggu.bkeasygo.announcement.pojo.AnnouncementBean;
import com.lechenggu.bkeasygo.order.dao.OrderDao;
import com.lechenggu.bkeasygo.order.pojo.OrderBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.StringUtil;

public class OrderDaoImpl implements OrderDao {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	//订单新增
	@Override
	public int OrderAdd(OrderBean ob) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 新增订单
		Integer i = (Integer) session.save(ob);
		// 提交事务
		ts.commit();
		return i;
	}

	@Override
	public List<OrderBean> queryByCondition(String condition, Object[] obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderBean> queryOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	//查询总页数
	@Override
	public int queryCount() {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		//创建hql语句
		String hql="select count(*) from OrderBean";
		Query query=session.createQuery(hql);
		List<Object> lo=query.list();
		long i=(long) lo.get(0);
		int count=(int) i;
		//提交事务
		ts.commit();
		return count;
		
	}
	//分页查询
	@Override
	public List<OrderBean> queryByPage(int page, int n) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		//创建hql语句
		String hql="from OrderBean";
		Query query=session.createQuery(hql);
		query.setFirstResult(page == 0 ? 0 : n * (page - 1));
		query.setMaxResults(n);
		List<OrderBean> lob=query.list();
		//提交事务
		ts.commit();
		return lob;
	}
	//根据id查询订单
	@Override
	public OrderBean queryOrderById(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		//创建hql语句
		String hql="from OrderBean where oid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<OrderBean> lob=query.list();
		//提交事务
		ts.commit();
		OrderBean ob = new OrderBean();
		if (lob.size() > 0) {
			ob= lob.get(0);
			return ob;
		}
			return ob;
	}
	//修改订单
	@Override
	public boolean updateOrderById(OrderBean ob) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		session.update(ob);
		//提交事务
		ts.commit();
		return true;
	}
	//删除订单
	@Override
	public boolean deleteOrderById(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		//创建hql语句
		String hql="from OrderBean where oid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<OrderBean> lob=query.list();
		OrderBean ob=lob.get(0);
		session.delete(ob);
		//提交事务
		ts.commit();
		return true;
	}
	//模糊查询
	@Override
	public List<OrderBean> queryOrderByIdAndReceman(int oid, String receman) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		String hql=" from OrderBean as order where order.oid like '%"+oid+"%' and order.receman like '%"+receman+"%'";
		Query query=session.createQuery(hql);
		List<OrderBean> lob=query.list();
		//提交事务
		ts.commit();
		return lob;
	}
	/*@Override
	public List<OrderBean> queryOrderByIdAndReceman(int oid, String receman) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		String hql=" from OrderBean as order where order.oid like :id and order.receman like :receman";
		Query query=session.createQuery(hql);
		query.setString("id", "%"+oid+"%");
		query.setString("receman", "%"+receman+"%");
		List<OrderBean> lob=query.list();
		//提交事务
		ts.commit();
		return lob;
	}*/

	/*DBUtil db = new DBUtil();
	*//**
	 * 新增订单
	 * 
	 *//*
	public int OrderAdd(OrderBean ob) {
		String sql = "insert into  torder values(?,63,sysdate,?,?,?,?,?,sysdate,?,sysdate)";
		Object[] obj = { ob.getOid(), ob.getReceman(), ob.getReceaddress(), ob.getOmoney(), ob.getOstate(),
				ob.getCreateman(), ob.getUpdateman() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}// OrderAdd

	*//**
	 * 通用的查询方法
	 *//*
	public List<OrderBean> queryByCondition(String condition, Object[] obj) {
		String sql = "select * from torder where 1=1 " + (condition == null ? "" : condition);
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<OrderrBean>集合
		List<OrderBean> lob = new ArrayList<OrderBean>();
		for (Map<String, String> map : lm) {
			// 创建OrderBean
			OrderBean ob = new OrderBean();
			ob.setOid(StringUtil.strToInt(map.get("OID")));
			ob.setOmoney(StringUtil.strToDou(map.get("OMONEY")));
			ob.setOstate(map.get("OSTATE"));
			ob.setOtime(map.get("OTIME"));
			ob.setReceaddress(map.get("RECEADDRESS"));
			ob.setReceman(map.get("RECEMAN"));
			ob.setUpdateman(StringUtil.strToInt(map.get("UPDATEMAN")));
			ob.setUpdatetime(map.get("UPDATETIME"));
			ob.setUserid(StringUtil.strToInt(map.get("USERID")));
			lob.add(ob);
		}
		return lob;
	}// queryByCondition

	*//**
	 * 查询所有订单
	 *//*
	public List<OrderBean> queryOrder() {
		return queryByCondition(null, null);
	}

	
	 * 查询总页数
	 
	public int queryCount() {
		String sql = "select count(*) count from torder";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		int count = StringUtil.strToInt(lm.get(0).get("COUNT"));
		return count;
	}// querycount

	
	 * 查询每页的记录数
	 
	public List<OrderBean> queryByPage(int page, int n) {
		String sql = "select s.* from(select t.*,rownum rn from (select * from torder order by oid)t)s where rn>? and rn<=?";
		// 利用三木运算符判断page==0的情况
		Object[] obj = { page == 0 ? 0 : n * (page - 1), n * page };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<OrderrBean>集合
		List<OrderBean> lob = new ArrayList<OrderBean>();
		for (Map<String, String> map : lm) {
			// 创建OrderBean
			OrderBean ob = new OrderBean();
			ob.setOid(StringUtil.strToInt(map.get("OID")));
			ob.setOmoney(StringUtil.strToDou(map.get("OMONEY")));
			ob.setOstate(map.get("OSTATE"));
			ob.setOtime(map.get("OTIME"));
			ob.setReceaddress(map.get("RECEADDRESS"));
			ob.setReceman(map.get("RECEMAN"));
			ob.setUpdateman(StringUtil.strToInt(map.get("UPDATEMAN")));
			ob.setUpdatetime(map.get("UPDATETIME"));
			ob.setUserid(StringUtil.strToInt(map.get("USERID")));
			lob.add(ob);
		}
		return lob;
	}// queryByPage

	*//**
	 * 根据id查询订单
	 *//*
	public OrderBean queryOrderById(int id) {
		String condition = " and oid=?";
		Object[] obj = { id };
		List<OrderBean> lob = queryByCondition(condition, obj);
		OrderBean ob = new OrderBean();
		if (lob.size() > 0) {
			ob = lob.get(0);
			return ob;
		}
		return ob;
	}// queryOrderById

	
	 * 根据id修改订单
	 
	public int updateOrderById(OrderBean ob) {
		String sql = "update torder set oid=?,receman=?,receaddress=?,omoney=?,ostate=? where oid=?";
		Object[] obj = { ob.getOid(), ob.getReceman(), ob.getReceaddress(), ob.getOmoney(), ob.getOstate(),
				ob.getOid() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}

	*//**
	 * 根据id删除大分类
	 *//*
	public int deleteOrderById(int id) {
		String sql = "delete from torder where oid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteOrderById

	
	 * 查询订货号与订货人
	 
	public List<OrderBean> queryOrderByIdAndReceman(int selectoid,String selectreceman) {
		String condition = " and  oid like ? or receman like ? ";
		Object[] obj = { "%" + selectoid + "%", "%" + selectreceman + "%" };
		List<OrderBean> lob = queryByCondition(condition, obj);
		return lob;
	}*/

}
