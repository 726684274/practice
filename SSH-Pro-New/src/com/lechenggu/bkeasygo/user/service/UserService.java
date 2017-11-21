package com.lechenggu.bkeasygo.user.service;

import java.util.List;

import com.lechenggu.bkeasygo.excpetion.SysException;
import com.lechenggu.bkeasygo.user.pojo.UserBean;

public interface UserService {
	// 查询用户名是否存在
	public int CKUserName(String username);
	/*
	 * 判断用户新增是否成功方法
	 */

	public int AddUser(UserBean ub) throws SysException;
	/*
	 * 查询所有用户
	 */
	public List<UserBean> userQuery();
	/**
	 * 查询总页数
	 */
	public int queryCountPage(int n);

	/**
	 * 查询每一个的数据
	 */
	public List<UserBean> queryByPage(int page, int n);

	/**
	 * 根据id调用dao层的方法 删除用户
	 */
	public boolean deleteUserById(int id);

	/**
	 * 根据id调用dao层的方法 解冻用户
	 */
	public boolean freezeUserById(int id);

	/**
	 * 根据id调用dao层的方法 冻结用户
	 */
	public boolean freeUserById(int id);

	/**
	 * 根据id调用dao层的方法，查询用户
	 */
	public UserBean queryUserById(int id);

	// 修改时查询用户名是否存在
	public int UCKUserName(UserBean ub);

	/*
	 * 修改方法
	 */
	public boolean updateUser(UserBean ub) throws SysException;
}
