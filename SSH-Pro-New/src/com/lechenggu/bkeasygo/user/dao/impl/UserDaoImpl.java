package com.lechenggu.bkeasygo.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.lechenggu.bkeasygo.user.dao.UserDao;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.HibernateUtil;
import com.lechenggu.bkeasygo.utils.StringUtil;

public class UserDaoImpl implements UserDao{
	// 关联hibernateUtil  获得session 工厂
	private  SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	//新增时查询用户名是否存在
	@Override
	public List<UserBean> QueryuserByName(String username) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql语句
		String hql="from UserBean where username=?";
		Query query=session.createQuery(hql);
		query.setString(0, username);
		List<UserBean> lu=query.list();
		//提交事务
		ts.commit();
		return lu;
	}
	//新增用户
	@Override
	public int UserAdd(UserBean ub) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//用户新增
		Integer i=(Integer) session.save(ub);
		//提交事务
		ts.commit();
		return i;
	}
	//查询所有用户
	@Override
	public List<UserBean> userQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	//查询总页数
	@Override
	public int querycount() {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql = "select count(*) from UserBean";
		Query query=session.createQuery(hql);
		List<Object> lo=query.list();
		//(老师)int count=(int) (long)lo.get(0);
		long i=(long) lo.get(0);
		int count=(int) i;
		//int count = new Long((Long)lo.get(0)).intValue();
		//提交事务
		ts.commit();
		return count;
	}
	//查询每一条数据
	@Override
	public List<UserBean> queryByPage(int page, int n) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		String hql="from UserBean";
		Query query=session.createQuery(hql);
		//分页
		query.setFirstResult(page == 0 ? 0 : n * (page - 1));
		query.setMaxResults(n);
		List<UserBean> lu=query.list();
		//提交事务
		ts.commit();
		return lu;
	}
	//删除用户
	@Override
	public boolean deleteUserById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from UserBean where userid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<UserBean> lu=query.list();
		UserBean ub=lu.get(0);
		session.delete(ub);
		//提交事务
		ts.commit();
		return true; 
	}
	//根据id解冻用户
	@Override
	public boolean freezeUserById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		String hql="from UserBean where userid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<UserBean> lu=query.list();
		UserBean ub=lu.get(0);
		ub.setIfuse(1);
		session.update(ub);
		//提交事务
		ts.commit();
		return true;
	}
	//冻结用户
	@Override
	public boolean freeUserById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		String hql="from UserBean where userid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<UserBean> lu=query.list();
		UserBean ub=lu.get(0);
		ub.setIfuse(0);
		session.update(ub);
		//提交事务
		ts.commit();
		return true;
	}
	//根据id查询用户
	@Override
	public UserBean queryUserById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//
		String hql="from UserBean where userid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<UserBean> lu=query.list();
		//提交事务
		ts.commit();
		UserBean userbean = new UserBean();
		if (lu.size() > 0) {
			userbean = lu.get(0);
			return userbean;
		}
		return userbean;
	}
	//修改时根据用户名和id查询用户
	@Override
	public List<UserBean> QueryuserByNameAndId(UserBean ub) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from UserBean where username=? and userid!=?";
		Query query=session.createQuery(hql);
		query.setString(0, ub.getUsername());
		query.setInteger(1, ub.getUserid());
		List<UserBean> lu=query.list();
		//提交事务
		ts.commit();
		return lu;
	}
	//修改用户
	@Override
	public boolean updateUser(UserBean ub) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//用户修改
		session.update(ub);;
		//提交事务
		ts.commit();
		return true;
	}
	
	
	
	/*DBUtil db = new DBUtil();
	// 通用的sql语句查询方法
	public List<UserBean> QueryByCondition(String condition, Object[] obj) {
		// where 1=1后加空格 condition="and username=?"
		String sql = "select * from tuser where 1=1 " + (condition == null ? "" : condition);
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<UserBean>集合
		List<UserBean> lu = new ArrayList<UserBean>();
		for (Map<String, String> map : lm) {
			// 创建UserBean
			UserBean ub = new UserBean();
			ub.setUserid(StringUtil.strToInt(map.get("USERID")));
			ub.setUsername(map.get("USERNAME"));
			ub.setTruename(map.get("TRUENAME"));
			ub.setPassword(map.get("PASSWORD"));
			ub.setSex(StringUtil.strToInt(map.get("SEX")));
			ub.setBirth(map.get("BIRTH"));
			ub.setCardid(map.get("CARDID"));
			ub.setEmail(map.get("EMAIL"));
			ub.setTel(map.get("TEL"));
			ub.setAddress(map.get("ADDRESS"));
			ub.setIfuse(StringUtil.strToInt(map.get("IFUSE")));
			ub.setCreateman(StringUtil.strToInt(map.get("CREATEMAN")));
			ub.setCreatetime(map.get("CREATETIME"));
			ub.setUpdateman(StringUtil.strToInt(map.get("UPDATEMAN")));
			ub.setUpdatetime(map.get("UPDATETIME"));
			ub.setRepwd(map.get("REPWD"));
			lu.add(ub);
		}
		return lu;
	}

	
	 * 根据用户名查询用户
	 
	public List<UserBean> QueryuserByName(String username) {
		String condition = "and username=?";
		Object[] obj = { username };
		List<UserBean> lu = QueryByCondition(condition, obj);
		return lu;
	} // QueryuserByName
	*//**
	 * 查询所有用户
	 *//*
	public List<UserBean> userQuery(){
		return QueryByCondition(null,null);
	}
	
	 * 用户新增
	 
	public int UserAdd(UserBean ub) {
		String sql = "insert into tuser(userid,username,truename,password,sex,birth,cardid,email,tel,address,createman,createtime)"
				+ "values(sequence_tuser.nextval,?,?,?,?,?,?,?,?,?,?,sysdate)";
		Object[] obj = {ub.getUsername(), ub.getTruename(),ub.getPassword(), ub.getSex(), ub.getBirth(),ub.getCardid(), ub.getEmail(), ub.getTel(), ub.getAddress(), ub.getCreateman()};
		// 调用BDUtil中的通用新增方法
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}// UserAdd

	*//**
	 * 查询总记录数
	 *//*
	public int querycount() {
		String sql = "select count(*) count from tuser";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		int count = StringUtil.strToInt(lm.get(0).get("COUNT"));
		return count;
	}// querycount

	*//**
	 * 查询每一页的记录
	 *//*

	public List<UserBean> queryByPage(int page, int n) {
		// 完整的sql语句 select * from tuser where 1=1 limit m,n;
		//String condition = " limit ?,?";
		String sql="select s.* from(select t.*,rownum rn from (select * from tuser order by userid)t)s where rn>? and rn<=?";
		// 利用三木运算符判断page==0的情况
		//Object[] obj = { page == 0 ? 0 : n * (page - 1), n };
		Object[] obj = { page == 0 ? 0 : n * (page - 1), n*page };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<UserBean>集合
		List<UserBean> lu = new ArrayList<UserBean>();
		for (Map<String, String> map : lm) {
			// 创建UserBean
			UserBean ub = new UserBean();
			ub.setUserid(StringUtil.strToInt(map.get("USERID")));
			ub.setUsername(map.get("USERNAME"));
			ub.setTruename(map.get("TRUENAME"));
			ub.setPassword(map.get("PASSWORD"));
			ub.setSex(StringUtil.strToInt(map.get("SEX")));
			ub.setBirth(map.get("BIRTH"));
			ub.setCardid(map.get("CARDID"));
			ub.setEmail(map.get("EMAIL"));
			ub.setTel(map.get("TEL"));
			ub.setAddress(map.get("ADDRESS"));
			ub.setIfuse(StringUtil.strToInt(map.get("IFUSE")));
			ub.setCreateman(StringUtil.strToInt(map.get("CREATEMAN")));
			ub.setCreatetime(map.get("CREATETIME"));
			ub.setUpdateman(StringUtil.strToInt(map.get("UPDATEMAN")));
			ub.setUpdatetime(map.get("UPDATETIME"));
			ub.setRepwd(map.get("REPWD"));
			lu.add(ub);
		}
		return lu;
		
	}// queryByPage

	*//**
	 * 根据id删除用户
	 *//*
	public int deleteUserById(int id) {
		String sql = "delete from tuser where userid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteUserById

	*//**
	 * 根据id解冻用户
	 *//*
	public int freezeUserById(int id) {
		String sql = "update tuser set ifuse=? where userid=?";
		Object[] obj = { 1, id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// freezeUserById

	*//**
	 * 根据id冻结用户
	 *//*
	public int freeUserById(int id) {
		String sql = "update tuser set ifuse=? where userid=?";
		Object[] obj = { 0, id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// freeUserById

	*//**
	 * 根据id查询的用户
	 *//*
	public UserBean queryUserById(int id) {
		String condition = " and userid=?";
		Object[] obj = { id };
		List<UserBean> lu = QueryByCondition(condition, obj);
		UserBean userbean = new UserBean();
		if (lu.size() > 0) {
			userbean = lu.get(0);
			return userbean;
		}
		return userbean;
	}

	
	 * 修改时根据用户名和id查询用户
	 
	public List<UserBean> QueryuserByNameAndId(UserBean ub) {
		String condition = "and username=? and userid!=?";
		Object[] obj = { ub.getUsername(), ub.getUserid() };
		List<UserBean> lu = QueryByCondition(condition, obj);
		return lu;
	} // QueryuserByNameAndId

	*//**
	 * 修改用户信息
	 *//*
	public int updateUser(UserBean ub) {
		String sql = "update tuser set username=?,password=?,truename=?,sex=?,birth=?,cardid=?,email=?,tel=?,address=?,updateman=? where userid=?";
		Object[] obj = { ub.getUsername(), ub.getPassword(), ub.getTruename(), ub.getSex(), ub.getBirth(),
				ub.getCardid(), ub.getEmail(), ub.getTel(), ub.getAddress(), ub.getUpdateman(), ub.getUserid() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}*/

}
