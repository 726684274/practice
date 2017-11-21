package com.lechenggu.bkeasygo.login.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.lechenggu.bkeasygo.login.dao.LoginDao;
import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.HibernateUtil;

/**
 * 利用hql操作数据库
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public class LoginDaoImpl implements LoginDao{
//	关联HibernateUtil获得session工厂
	private SessionFactory sf;

	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	//根据查询用户和密码
		@Override
		@Autowired
		public AdminBean CKAdminByNP(AdminBean ab) {
			//获得session对象
			Session session=sf.getCurrentSession();
			//开启事务
			Transaction ts=session.beginTransaction();
			//创建hql语句
			String hql="from AdminBean where name=? and password=?";
			//查询
			Query query=session.createQuery(hql);
			query.setString(0, ab.getName());
			query.setString(1, ab.getPassword());
			List<AdminBean> la=query.list();
			//提交事务
			ts.commit();
			AdminBean adminbean=new AdminBean();
			if(la.size()>0){
				adminbean=la.get(0);
				return adminbean;
			}
			return adminbean;
		}
		
	//通用的查询方法
	@Override
	public List<AdminBean> QueryByCondition(String condition, Object[] obj) {
		//获得session对象
		Session session=sf.getCurrentSession();
		//开启事务
		Transaction ts=session.beginTransaction();
		//创建hql
		String hql="from AdminBean where 1=1 "+(condition==null?"":condition);
		//查询
		Query query=session.createQuery(hql);
		
		return null;
	}
	

/*	DBUtil db=new DBUtil();
//	通用的sql查询方法
	public List<AdminBean> QueryByCondition(String condition,Object[] obj){
		String sql="select * from admin where 1=1 "+(condition==null?"":condition);
		List<Map<String, String>> lm=db.executeQuery(sql, obj);
//		将将数据库中查询到的List<Map<String, String>>集合转换为list<AdminBean>集合
		List<AdminBean> la=new ArrayList<AdminBean>();
//		遍历List<Map<String, String>> 集合
		for (Map<String, String> map : lm) {
//   将信息封装到AdminBean中
			AdminBean ab=new AdminBean();
			ab.setId(Integer.parseInt(map.get("ID")));
			ab.setName(map.get("NAME"));
			ab.setPassword(map.get("PASSWORD"));
			ab.setRealname(map.get("REALNAME"));
			la.add(ab);
		}
		return la;
	}//QueryByCondition
	
//根据用户名和密码查询用户
	public AdminBean CKAdminByNP(AdminBean ab){
		String condition="and name=? and password=?";
		Object[] obj={ab.getName(),ab.getPassword()};
		List<AdminBean>la=QueryByCondition(condition, obj);
		AdminBean adminbean=new AdminBean();
		if(la.size()>0){
			adminbean=la.get(0);
			return adminbean;
		}
		return adminbean;
	}
*/
}
