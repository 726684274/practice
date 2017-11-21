package com.lechenggu.bkeasygo.announcement.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lechenggu.bkeasygo.announcement.dao.AnnouncementDao;
import com.lechenggu.bkeasygo.announcement.pojo.AnnouncementBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.StringUtil;

public class AnnouncementDaoImpl implements AnnouncementDao{
	private SessionFactory sf;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	//新增公告
	@Override
	public int announcementAdd(AnnouncementBean acb) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//用户新增
		Integer i=(Integer) session.save(acb);
		//提交事务
		ts.commit();
		return i;
	}

	@Override
	public List<AnnouncementBean> queryByCondition(String condition, Object[] obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnnouncementBean> queryAnnouncement() {
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
		String hql = "select count(*) from AnnouncementBean";
		Query query=session.createQuery(hql);
		List<Object> lo=query.list();
		long i=(long) lo.get(0);
		int count=(int) i;
		//int count = new Long((Long)lo.get(0)).intValue();
		//提交事务
		ts.commit();
		return count;
	}
	//查询每一页的数据
	@Override
	public List<AnnouncementBean> queryCountByPage(int page, int n) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		String hql="from AnnouncementBean";
		Query query=session.createQuery(hql);
		//分页
		query.setFirstResult(page == 0 ? 0 : n * (page - 1));
		query.setMaxResults(n);
		List<AnnouncementBean> lab=query.list();
		//提交事务
		ts.commit();
		return lab;
	}
	//根据id查询公告
	@Override
	public AnnouncementBean queryAnnouncementById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from AnnouncementBean where bid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<AnnouncementBean> lab=query.list();
		//提交事务
		ts.commit();
		AnnouncementBean ab = new AnnouncementBean();
		if (lab.size() > 0) {
			ab= lab.get(0);
			return ab;
		}
			return ab;
	}
	//修改公告
	@Override
	public boolean updateAnnouncement(AnnouncementBean ab) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		session.update(ab);;
		//提交事务
		ts.commit();
		return true;
	}
	//删除公告
	@Override
	public boolean deleteAnnouncementById(int id) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from AnnouncementBean where bid=?";
		Query query=session.createQuery(hql);
		query.setInteger(0, id);
		List<AnnouncementBean> lab=query.list();
		AnnouncementBean ab=lab.get(0);
		session.delete(ab);
		//提交事务
		ts.commit();
		return true; 
	}
	
	
	// 关联DBUtil
	/*DBUtil db = new DBUtil();
	// 公告新增方法
	public int announcementAdd(AnnouncementBean acb) {
		String sql = "insert into tbulletin values(sequence_tbulletin.nextval,?,?,?,sysdate)";
		Object[] obj = { acb.getBinner(), acb.getBtitile(), acb.getCreateman() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}// announcementAdd

	
	 * 通用的sql查询语句
	 
	public List<AnnouncementBean> queryByCondition(String condition, Object[] obj) {
		String sql = "select * from tbulletin where 1=1 " + (condition == null ? "" : condition);
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<AnnouncementBean>集合
		List<AnnouncementBean> lab = new ArrayList<AnnouncementBean>();
		for (Map<String, String> map : lm) {
			AnnouncementBean ab = new AnnouncementBean();
			ab.setBid(StringUtil.strToInt(map.get("BID")));
			ab.setBinner(map.get("BINNER"));
			ab.setBtitile(map.get("BTITILE"));
			ab.setCreateman(StringUtil.strToInt(map.get("CREATEMAN")));
			ab.setCreatetime(map.get("CREATETIME"));
			lab.add(ab);
		}
		return lab;
	}
	*//**
	 * 查询所有公告
	 *//*
	public List<AnnouncementBean> queryAnnouncement(){
		return queryByCondition(null,null);
	}
	
	 * 查询总记录数
	 

	public int queryCount() {
		String sql = "select count(*)count from tbulletin";
		Object[] obj = null;
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		int count = StringUtil.strToInt(lm.get(0).get("COUNT"));
		return count;
	}// queryCount

	
	 * 查询每页的记录数
	 
	public List<AnnouncementBean> queryCountByPage(int page, int n) {
		String sql = "select s.* from(select t.*,rownum rn from (select * from tbulletin order by bid)t)s where rn>? and rn<=?";
		// 利用三木运算符判断page==0的情况
		Object[] obj = { page == 0 ? 0 : n * (page - 1), n*page };
		List<Map<String, String>> lm = db.executeQuery(sql, obj);
		// 将数据库中查询到的List<Map<String, String>>集合转换为list<AnnouncementBean>集合
		List<AnnouncementBean> lab = new ArrayList<AnnouncementBean>();
		for (Map<String, String> map : lm) {
			AnnouncementBean ab = new AnnouncementBean();
			ab.setBid(StringUtil.strToInt(map.get("BID")));
			ab.setBinner(map.get("BINNER"));
			ab.setBtitile(map.get("BTITILE"));
			ab.setCreateman(StringUtil.strToInt(map.get("CREATEMAN")));
			ab.setCreatetime(map.get("CREATETIME"));
			lab.add(ab);
		}
		return lab;
	}// queryCountByPage

	*//**
	 * 根据id查询公告
	 *//*
	public AnnouncementBean queryAnnouncementById(int id) {
		String condition = " and bid=?";
		Object[] obj = { id };
		List<AnnouncementBean> lu = queryByCondition(condition, obj);
		AnnouncementBean ab = new AnnouncementBean();
		if (lu.size() > 0) {
			ab = lu.get(0);
			return ab;
		}
		return ab;
	}//

	*//**
	 * 根据id删除公告
	 *//*
	public int deleteAnnouncementById(int id) {
		String sql = "delete from tbulletin where bid=?";
		Object[] obj = { id };
		int i = db.executeUpdate(sql, obj);
		return i;
	}// deleteAnnouncementById

	
	 * 根据id修改公告
	 
	public int updateAnnouncement(AnnouncementBean ab) {
		String sql = "update tbulletin set btitile=?,binner=? where bid=?";
		Object[] obj = { ab.getBtitile(), ab.getBinner(), ab.getBid() };
		int rows = db.executeUpdate(sql, obj);
		return rows;
	}*/
}
