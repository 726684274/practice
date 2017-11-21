package com.lechenggu.bkeasygo.goods.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lechenggu.bkeasygo.goods.dao.GoodsDao;
import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.StringUtil;

public class GoodsDaoImpl implements GoodsDao {
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	//商品新增
	@Override
	public int goodsAdd(GoodsBean gBean) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 用户新增
		Integer i = (Integer) session.save(gBean);
		// 提交事务
		ts.commit();
		return i;
	}

	@Override
	public List<GoodsBean> queryByCondition(String condition, Object[] obj) {
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
		// 创建hql
		String hql = "select count(*) from GoodsBean";
		Query query = session.createQuery(hql);
		List<Object> lo = query.list();
		// (老师)int count=(int) (long)lo.get(0);
		long i = (long) lo.get(0);
		int count = (int) i;
		// int count = new Long((Long)lo.get(0)).intValue();
		// 提交事务
		ts.commit();
		return count;
	}
	//分页查询
	@Override
	public List<Object> queryByPage(int page, int n) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		String hql = "select goods.gid,goods.gname,goods.gprice,goods.gnumber,goods.gbigpic,goods.gnumberroduce,goods.iftop,sup.supid,sup.supname,sub.subid,sub.subname from GoodsBean goods inner join goods.sup sup inner join goods.sub sub";
		Query query = session.createQuery(hql);
		// 分页
		query.setFirstResult(page == 0 ? 0 : n * (page - 1));
		query.setMaxResults(n);
		List<Object> lo = query.list();
		// 提交事务
		ts.commit();
		return lo;
	}
	//根据id查询商品
	@Override
	public List<Object> queryGoodsById(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 创建hql
		String hql = "select goods.gid,goods.gname,goods.gprice,goods.gnumber,goods.gbigpic,goods.gnumberroduce,goods.iftop,sup.supid,sup.supname,sub.subid,sub.subname from GoodsBean goods inner join goods.sup sup inner join goods.sub sub where gid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<Object> lo = query.list();
		// 提交事务
		ts.commit();
		return lo;
	}
	
	@Override
	public boolean deleteGoodsBySupid(int supid) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 创建hql
		String hql = "from GoodsBean where supid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, supid);
		List<GoodsBean> lgb = query.list();
		GoodsBean goodsbean = lgb.get(0);
		session.delete(goodsbean);
		// 提交事务
		ts.commit();
		return true;
	}
	//删除商品
	@Override
	public boolean deleteGoodsById(int id) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 创建hql
		String hql = "from GoodsBean where gid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		List<GoodsBean> lgb = query.list();
		GoodsBean goodsbean = lgb.get(0);
		session.delete(goodsbean);
		// 提交事务
		ts.commit();
		return true;
	}
	//修改商品
	@Override
	public boolean updateGoodsById(GoodsBean gb) {
		// 获得session对象
		Session session = sf.getCurrentSession();
		// 开启事务
		Transaction ts = session.beginTransaction();
		// 商品修改
		session.update(gb);
		// 提交事务
		ts.commit();
		return true;
	}
	
	// 关联DBUtil
	/*DBUtil db = new DBUtil();
	
	 * 商品新增的方法
	 
	public int goodsAdd(GoodsBean gBean) {
		String sql = "insert into tgoods values(sequence_tgoods.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] obj = { gBean.getGname(), gBean.getSupid(), gBean.getSubid(), gBean.getGprice(), gBean.getGnumber(),gBean.getGbigpic(), gBean.getGnumberroduce(),gBean.getIftop(), gBean.getCreateman() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}// goodsAdd

	
	 * 通用的查询方法
	 
	public List<GoodsBean> queryByCondition(String condition, Object[] obj) {
		String sql = "select tgoods.*,tsup.supname supname ,tsub.subname subname from tgoods inner join tsup on tgoods.supid=tsup.supid inner join tsub on tgoods.subid=tsub.subid"
				+ (condition == null ? "" : condition);
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		List<GoodsBean> lg = new ArrayList<GoodsBean>();
		for (Map<String, String> map : lm) {
			// 封装到GoodsBean中
			GoodsBean gb = new GoodsBean();
			gb.setGid(StringUtil.strToInt(map.get("GID")));
			gb.setGname(map.get("GNAME"));
			gb.setGnumber(StringUtil.strToInt(map.get("GNUMBER")));
			gb.setGbigpic(map.get("GBIGPIC"));
			gb.setGprice(StringUtil.strToDou(map.get("GPRICE")));
			gb.setGintroduce(map.get("GNUMBERRODUCE"));
			gb.setCreateman(StringUtil.strToInt(map.get("CREATEMAN")));
			gb.setCreatetime(map.get("CREATETIME"));
			gb.setSubid(StringUtil.strToInt(map.get("SUBID")));
			gb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			gb.setSupname(map.get("SUPNAME"));
			gb.setSubname(map.get("SUBNAME"));
			lg.add(gb);
		}
		return lg;
	}

	*//**
	 * 查询总页数
	 *//*
	public int queryCount() {
		String sql = "select count(*) count from tgoods";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		int count = StringUtil.strToInt(lm.get(0).get("COUNT"));
		return count;
	}// queryCount

	*//**
	 * 查询每页的记录数
	 *//*
	public List<GoodsBean> queryByPage(int page, int n) {
		// 完整的sql语句 select * from tgoods where 1=1 limit m,n;
		String sql = "select s.* from"
				+ "(select t.*,rownum rn from "
				+ "(select tgoods.*,tsup.supname supname ,tsub.subname subname from tgoods "
				+ "inner join tsup on tgoods.supid=tsup.supid "
				+ "inner join tsub on tgoods.subid=tsub.subid order by gid)t)s where rn>? and rn<=?";
		// 利用三木运算符判断page==0的情况
		Object[] obj = { page == 0 ? 0 : n * (page - 1), n*page };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		List<GoodsBean> lg = new ArrayList<GoodsBean>();
		for (Map<String, String> map : lm) {
			// 封装到GoodsBean中
			GoodsBean gb = new GoodsBean();
			gb.setGid(StringUtil.strToInt(map.get("GID")));
			gb.setGname(map.get("GNAME"));
			gb.setGnumber(StringUtil.strToInt(map.get("GNUMBER")));
			gb.setGbigpic(map.get("GBIGPIC"));
			gb.setGprice(StringUtil.strToDou(map.get("GPRICE")));
			gb.setGintroduce(map.get("GNUMBERRODUCE"));
			gb.setCreateman(StringUtil.strToInt(map.get("CREATEMAN")));
			gb.setCreatetime(map.get("CREATETIME"));
			gb.setSubid(StringUtil.strToInt(map.get("SUBID")));
			gb.setSupid(StringUtil.strToInt(map.get("SUPID")));
			gb.setSupname(map.get("SUPNAME"));
			gb.setSubname(map.get("SUBNAME"));
			lg.add(gb);
		}
		return lg;
	}// queryByPage

	
	 * 根据id查询商品
	 
	public GoodsBean queryGoodsById(int id) {
		String condition = " and gid=?";
		Object[] obj = { id };
		List<GoodsBean> lu = queryByCondition(condition, obj);
		GoodsBean gb = new GoodsBean();
		if (lu.size() > 0) {
			gb = lu.get(0);
			return gb;
		}
		return gb;
	}// queryGoodsById

	*//**
	 * 根据supid删除商品
	 *//*
	public int deleteGoodsBySupid(int id) {
		String sql = "delete from tgoods where supid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteUserById

	*//**
	 * 根据id删除商品
	 *//*
	public int deleteGoodsById(int id) {
		String sql = "delete from tgoods where gid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteUserById

	
	 * 根据商品的id修改商品详情
	 
	public int updateGoodsById(GoodsBean gb) {
		String sql = "update tgoods set gname=?,supid=?,subid=?,gprice=?,gnumber=?,gbigpic=? where gid=?";
		Object[] obj = { gb.getGname(), gb.getSupid(), gb.getSubid(), gb.getGprice(), gb.getGnumber(), gb.getGbigpic(),gb.getGid()};
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}
*/
}
