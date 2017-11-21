package com.lechenggu.bkeasygo.sub.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lechenggu.bkeasygo.sub.dao.SubCategoryDao;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.StringUtil;

public class SubCategoryDaoImpl implements SubCategoryDao {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	//新增小分类
	@Override
	public int addSub(SubCategoryBean scb) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		scb.setSupid(scb.getSupid());
		//新增大分类
		Integer i=(Integer) session.save(scb);
		//提交事务
		ts.commit();
		return i;
	}

	@Override
	public List<SubCategoryBean> queryByCondition(String condition, Object[] obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubCategoryBean> querySub() {
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
		String hql = "select count(*) from SubCategoryBean";
		Query query=session.createQuery(hql);
		List<Object> lo=query.list();
		long i=(long) lo.get(0);
		int count=(int) i;
		//int count = new Long((Long)lo.get(0)).intValue();
		//提交事务
		ts.commit();
		return count;
	}
	//查询每页数据
	@Override
	public List<Object> queryByPage(int page, int n) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		String hql="select sub.subid,sub.subname,sup.supname from SubCategoryBean sub inner join sub.sup sup";
		Query query=session.createQuery(hql);
		//分页
		query.setFirstResult(page == 0 ? 0 : n * (page - 1));
		query.setMaxResults(n);
		List<Object> lo=query.list();
		//提交事务
		ts.commit();
		return lo;
	}
	//根据id查询小分类
	@Override
	public List<Object> querySubById(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		//创建hql
		String hql ="select sub.subid,sub.subname,sup.supid,sup.supname from SubCategoryBean sub inner join sub.sup sup where subid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<Object> lo = query.list();
		// 提交事务
		ts.commit();
		/*SubCategoryBean scb = new SubCategoryBean();
		if (lsb.size() > 0) {
			scb = lsb.get(0);
			return scb;
		}*/
		return lo;
		
	}
	//修改小分类
	@Override
	public boolean updateSubById(SubCategoryBean sb) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		/*String hql="update tsub set subname=subname, supid=supid where  subid=subid";
		Query query=session.createQuery(hql);
		query.setParameter("subname", sb.getSubname());
		query.setParameter("supid", sb.getSupid());
		query.setParameter("subid", sb.getSubid());
		query.executeUpdate();*/
		//小分类修改
		session.update(sb);;
		//提交事务
		ts.commit();
		return true;
	}
	//删除大分类
	@Override
	public boolean deleteSubBySupid(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 创建hql
		String hql = "from SupBean where supid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<SupBean> lu = query.list();
		SupBean supbean = lu.get(0);
		session.delete(supbean);
		// 提交事务
		ts.commit();
		return true;
	}
	//删除小分类
	@Override
	public boolean deleteSubById(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 创建hql
		String hql = "from SubCategoryBean where subid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<SubCategoryBean> lsb = query.list();
		SubCategoryBean subbean = lsb.get(0);
		session.delete(subbean);
		// 提交事务
		ts.commit();
		return true;
	}
	//根据大分类的id查询小分类的名字
	@Override
	public List<SubCategoryBean> queryBySubname(int supid) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 创建hql
		String hql = "from SubCategoryBean where supid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, supid);
		List<SubCategoryBean> lsb = query.list();
		//提交事务
		ts.commit();
		return lsb;
	}
	/*// 关联DBUtil
	DBUtil db = new DBUtil();
	
	 * 小分类新增的方法
	 
	public int addSub(SubCategoryBean scb) {
		String sql = "insert into tsub values(sequence_tsub.nextval,?,?,?,sysdate)";
		Object[] obj = { scb.getSubname(), scb.getSupid(), scb.getCreateman() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}// addsub
	
	 * 通用的查询方法
	 
	public List<SubCategoryBean> queryByCondition(String condition, Object[] obj) {
		// 在小分类管理中要用到大分类的名字，所以，在查询时需要用到多表联查（内连接）
		String sql = "select tsub.*,tsup.supname from tsub inner join tsup on tsub.supid=tsup.supid"
				+ (condition == null ? "" : condition);
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<SubCategoryBean>集合
		List<SubCategoryBean> lsb = new ArrayList<SubCategoryBean>();
		for (Map<String, String> map : lm) {
			SubCategoryBean sb = new SubCategoryBean();
			sb.setSubid(StringUtil.strToInt(map.get("SUBID")));
			sb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			sb.setSubname(map.get("SUBNAME"));
			sb.setCreateman(StringUtil.strToInt(map.get("CREATMAN")));
			sb.setCreatetime(map.get("CREATETIME"));
			sb.setSupname(map.get("SUPNAME"));// 大分类的名字
			lsb.add(sb);
		}
		return lsb;
	}

	
	 * 查询所有的小分类
	 
	public List<SubCategoryBean> querySub() {
		return queryByCondition(null, null);
	}

	*//**
	 * 获得总页数
	 *//*
	public int queryCount() {
		String sql = "select count(*) count from tsub";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		int count = StringUtil.strToInt(lm.get(0).get("COUNT"));
		return count;
	}

	*//**
	 * 获得每页的数据
	 *//*
	public List<SubCategoryBean> queryByPage(int page, int n) {
		String sql ="select s.* from(select t.*,rownum rn from"
				+ "(select tsub.*,tsup.supname  from tsub "
				+ "inner join tsup on tsub.supid=tsup.supid order by subid)t)s where rn>? and rn<=?";
		// 利用三木运算符判断page==0的情况
		Object[] obj = { page == 0 ? 0 : n * (page - 1), n * page };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<SubCategoryBean>集合
		List<SubCategoryBean> lsb = new ArrayList<SubCategoryBean>();
		for (Map<String, String> map : lm) {
			SubCategoryBean sb = new SubCategoryBean();
			sb.setSubid(StringUtil.strToInt(map.get("SUBID")));
			sb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			sb.setSubname(map.get("SUBNAME"));
			sb.setCreateman(StringUtil.strToInt(map.get("CREATMAN")));
			sb.setCreatetime(map.get("CREATETIME"));
			sb.setSupname(map.get("SUPNAME"));// 大分类的名字
			lsb.add(sb);
		}
		return lsb;
	}

	
	 * 根据id查询小分类
	 
	public SubCategoryBean querySubById(int id) {
		String condition = " and subid=?";
		Object[] obj = { id };
		List<SubCategoryBean> lsb = queryByCondition(condition, obj);
		SubCategoryBean sb = new SubCategoryBean();
		if (lsb.size() > 0) {
			sb = lsb.get(0);
			return sb;
		}
		return sb;
	}// querySubById

	*//**
	 * 根据id修改小分类
	 *//*
	public int updateSubById(SubCategoryBean sb) {
		String sql = "update tsub set subname=?, supid=? where  subid=?";
		Object[] obj = {sb.getSubname(),sb.getSupid(),sb.getSubid()};
		int rows = db.executeUpdate(sql, obj);
		System.out.println("*************************");
		return rows;
	}

	*//**
	 * 根据supid删除大分类
	 *//*
	public int deleteSubBySupid(int id) {
		String sql = "delete from tsub where supid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteSubById

	*//**
	 * 根据id删除大分类
	 *//*
	public int deleteSubById(int id) {
		String sql = "delete from tsub where subid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteSubById

	*//**
	 * 查询小分类的名字
	 *//*
	public List<SubCategoryBean> queryBySubname(int supid) {
		String sql = "select * from tsub where supid=?";
		Object[] obj = { supid };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<SubCategoryBean>集合
		List<SubCategoryBean> lsb = new ArrayList<SubCategoryBean>();
		for (Map<String, String> map : lm) {
			SubCategoryBean sb = new SubCategoryBean();
			sb.setSubid(StringUtil.strToInt(map.get("SUBID")));
			sb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			sb.setSubname(map.get("SUBNAME"));
			sb.setCreateman(StringUtil.strToInt(map.get("CREATMAN")));
			sb.setCreatetime(map.get("CREATETIME"));
			lsb.add(sb);
		}
		return lsb;
	}*/

}
