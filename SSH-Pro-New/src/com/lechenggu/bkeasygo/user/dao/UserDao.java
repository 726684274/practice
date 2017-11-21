package com.lechenggu.bkeasygo.user.dao;

import java.util.List;

import com.lechenggu.bkeasygo.user.pojo.UserBean;

public interface UserDao {
	// 通用的sql语句查询方法
	/*public List<UserBean> QueryByCondition(String condition, Object[] obj);*/

	/*
	 * 根据用户名查询用户
	 */
	public List<UserBean> QueryuserByName(String username);

	/*
	 * 用户新增
	 */
	public int UserAdd(UserBean ub);

	/**
	 * 查询所有用户
	 */
	public List<UserBean> userQuery();
	/**
	 * 查询总记录数
	 */
	public int querycount();

	/**
	 * 查询每一页的记录
	 */

	public List<UserBean> queryByPage(int page, int n);

	/**
	 * 根据id删除用户
	 */
	public boolean deleteUserById(int id);

	/**
	 * 根据id解冻用户
	 */
	public boolean freezeUserById(int id);

	/**
	 * 根据id冻结用户
	 */
	public boolean freeUserById(int id);

	/**
	 * 根据id查询的用户
	 */
	public UserBean queryUserById(int id);

	/*
	 * 修改时根据用户名和id查询用户
	 */
	public List<UserBean> QueryuserByNameAndId(UserBean ub);

	/**
	 * 修改用户信息
	 */
	public boolean updateUser(UserBean ub);

}

