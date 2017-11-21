package com.lechenggu.bkeasygo.sup.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lechenggu.bkeasygo.sup.dao.SupDao;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.StringUtil;

public class SupDaoImpl implements SupDao {
	//获得session 工厂
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	//添加大分类
	@Override
	public int addClassName(SupBean sb) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//新增大分类
		Integer i=(Integer) session.save(sb);
		//提交事务
		ts.commit();
		return i;
	}
	@Override
	public List<SupBean> querySup() {
		// TODO Auto-generated method stub
		return null;
	}
	//查询总页数
	@Override
	public int queryCount() {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql = "select count(*) from SupBean";
		Query query=session.createQuery(hql);
		List<Object> lo=query.list();
		long i=(long) lo.get(0);
		int count=(int) i;
		//int count = new Long((Long)lo.get(0)).intValue();
		//提交事务
		ts.commit();
		return count;
	}
	//查询每一条数据
	@Override
	public List<SupBean> queryByPage(int page, int n) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		String hql="from SupBean";
		Query query=session.createQuery(hql);
		//分页
		query.setFirstResult(page == 0 ? 0 : n * (page - 1));
		query.setMaxResults(n);
		List<SupBean> lsb=query.list();
		//提交事务
		ts.commit();
		return lsb;
	}
	//根据id查询大分类
	@Override
	public SupBean querySupById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql语句
		String hql="from SupBean where supid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<SupBean> ls=query.list();
		//提交事务
		ts.commit();
		SupBean supbean=new SupBean();
		if (ls.size() > 0) {
		supbean = ls.get(0);
			return supbean;
		}
		return supbean;
	}
	//修改大分类
	@Override
	public boolean updateSup(SupBean sb) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//用户修改
		session.update(sb);;
		//提交事务
		ts.commit();
		return true;
	}
	//删除大分类
	@Override
	public boolean deleteSupById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from SupBean where supid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<SupBean> lu=query.list();
		SupBean supbean=lu.get(0);
		session.delete(supbean);
		//提交事务
		ts.commit();
		return true; 
	}
	//查询大分类的名字
	@Override
	public List<SupBean> querySupname() {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from SupBean";
		Query query=session.createQuery(hql);
		List<SupBean> ls=query.list();
		//提交事务
		ts.commit();
		return ls;
	}

	
	/*DBUtil db = new DBUtil();
	//新增大分类
	public int addClassName(SupBean sb) {
		String sql = "insert into tsup values(sequence_tsup.nextval,?,?,sysdate)";
		Object[] obj = { sb.getSupname(), sb.getCreateman() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}

	// 通用的sql语句查询方法
	public List<SupBean> QuerySupByCondition(String condition, Object[] obj) {
		// where 1=1后加空格 condition="and username=?"
		String sql = "select * from tsup where 1=1 " + (condition == null ? "" : condition);
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<UserBean>集合
		List<SupBean> ls = new ArrayList<SupBean>();
		for (Map<String, String> map : lm) {
			// 创建UserBean
			SupBean sb = new SupBean();
			sb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			sb.setCreatetime(map.get("CREATTIME"));
			sb.setCreateman(StringUtil.strToInt(map.get("CREATMAN")));
			sb.setSupname(map.get("SUPNAME"));
			ls.add(sb);
		}
		return ls;
	}
	
	 * 查询所有大分类
	 
	public List<SupBean> querySup(){
		return QuerySupByCondition(null,null);
	}
	*//**
	 * 查询大分类的总页数
	 *//*
	public int queryCount() {
		String sql = "select count(*) count from tsup";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		int i = StringUtil.strToInt(lm.get(0).get("COUNT"));
		return i;
	}// queryCount

	*//**
	 * 查询每一个的记录
	 *//*
	public List<SupBean> queryByPage(int page, int n) {
		// 完整的sql语句 select * from tsup limit m,n;
		String sql = "select s.* from(select t.*,rownum rn from (select * from tsup order by supid)t)s where rn>? and rn<=?";
		Object[] obj = { page == 0 ? 0 : n * (page - 1), n*page };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<UserBean>集合
		List<SupBean> ls = new ArrayList<SupBean>();
		for (Map<String, String> map : lm) {
			// 创建UserBean
			SupBean sb = new SupBean();
			sb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			sb.setCreatetime(map.get("CREATTIME"));
			sb.setCreateman(StringUtil.strToInt(map.get("CREATMAN")));
			sb.setSupname(map.get("SUPNAME"));
			ls.add(sb);
		}
		return ls;
	}// queryByPage

	*//**
	 * 根据id查询大分类
	 *//*
	public SupBean querySupById(int id) {
		String condition = " and supid=?";
		Object[] obj = { id };
		List<SupBean> ls = QuerySupByCondition(condition, obj);
		SupBean sb = new SupBean();
		if (ls.size() > 0) {
			sb = ls.get(0);
			return sb;
		}
		return sb;

	} // querySupById

	*//**
	 * 修改的方法
	 *//*
	public int updateSup(SupBean sb) {
		String sql = "update tsup set supname=? where supid=?";
		Object[] obj = { sb.getSupname(), sb.getSupid() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}

	*//**
	 * 根据id删除大分类
	 *//*
	public int deleteSupById(int id) {
		// 删除大分类需要删除商品 和小分类

		// 删除商品
		// GoodsDao gd=new GoodsDao();
		// int goodsrows=gd.deleteGoodsBySupid(id);
		// 删除小分类
		// SubCategoryDao scd=new SubCategoryDao();
		// int subrows=scd.deleteSubBySupid(id);

		String sql = "delete from tsup where supid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteSupById
	
	//查询大分类名字
	public List<SupBean> querySupname() {
		String sql = "select * from tsup";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<UserBean>集合
		List<SupBean> ls = new ArrayList<SupBean>();
		for (Map<String, String> map : lm) {
			// 创建UserBean
			SupBean sb = new SupBean();
			sb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			sb.setCreatetime(map.get("CREATTIME"));
			sb.setCreateman(StringUtil.strToInt(map.get("CREATMAN")));
			sb.setSupname(map.get("SUPNAME"));
			ls.add(sb);
		}
		return ls;
	}*/

}
