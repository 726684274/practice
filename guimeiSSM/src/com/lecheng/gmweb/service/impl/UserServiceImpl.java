package com.lecheng.gmweb.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lecheng.gmweb.mapper.UserMapper;
import com.lecheng.gmweb.pojo.UserBean;
import com.lecheng.gmweb.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Override
	public UserBean getUserByNP(UserBean user) {
		SqlSession ss = sqlSessionFactory.openSession();
		UserMapper um = ss.getMapper(UserMapper.class);
		UserBean u = um.getUserByNP(user);
		return u;
	}
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}
