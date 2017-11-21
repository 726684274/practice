package com.lechenggu.bkeasygo.user.service.impl;

import java.util.List;
import java.util.Map;

import com.lechenggu.bkeasygo.excpetion.SysException;
import com.lechenggu.bkeasygo.user.dao.UserDao;
import com.lechenggu.bkeasygo.user.dao.impl.UserDaoImpl;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.user.service.UserService;
import com.lechenggu.bkeasygo.utils.DBUtil;
import com.lechenggu.bkeasygo.utils.ValidationUtil;

public class UserServiceImpl implements UserService {
	// 关联DBUtil类
	DBUtil db = new DBUtil();
	// 关联UserAddDao  SS整合   不在new对象  将其注入到Spring容器中
	private UserDao uad ;
	
	public UserDao getUad() {
		return uad;
	}

	public void setUad(UserDao uad) {
		this.uad = uad;
	}

	// 查询用户名是否存在
	public int CKUserName(String username) {
		// 调用UserAddDao中的方法
		List<UserBean> lm = uad.QueryuserByName(username);
		return lm.size();

	}// CKUserName
	/*
	 * 判断用户新增是否成功方法
	 */

	public int AddUser(UserBean ub) throws SysException {
		// 判断两次密码是否一致
		if (!ub.getRepwd().equals(ub.getPassword())) {
			throw new SysException(1002, "两次密码输入不一致");
		}
		/*// 判断的用户名是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getUsername(), ValidationUtil.CHKNAME)) {
			throw new SysException(1003, "用户名中必须包含字母");
		}
		// 判断密码是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getPassword(), ValidationUtil.CHKPWD)) {
			throw new SysException(1004, "密码必须包含字母数字，且长度不少于6位");
		}
		// 判断真是姓名是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getTruename(), ValidationUtil.CHKTRUENAME)) {
			throw new SysException(1005, "用户真实姓名必须为汉字，且至少两位");
		}
		// 判断出生日期是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getBirth(), ValidationUtil.CHKBIRTHDAY)) {
			throw new SysException(1006, " 出生日期格式:yyyy-mm-dd");
		}
		// 判断邮箱格式是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getEmail(), ValidationUtil.CHKEMAIL)) {
			throw new SysException(1007, " 邮箱格式不正确");
		}
		// 判断电话格式是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getTel(), ValidationUtil.CHKPHONE)) {
			throw new SysException(1008, " 电话格式为11位数，以13578开头");
		}
		// 判断身份证号格式是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getCardid(), ValidationUtil.CHKIDCARD)) {
			throw new SysException(1009, "身份证号为18位数");
		}*/
		// 用户新增 调用UserAddDao中的用户新增方法
		int rows = uad.UserAdd(ub);
		return rows;
	}// AddUser

	/*
	 * 查询所有用户
	 */
	public List<UserBean> userQuery(){
		return uad.userQuery();
	}
	/**
	 * 查询总页数
	 */
	public int queryCountPage(int n) {
		int count = uad.querycount();
		return (count%n==0?(count/n):(count/n + 1));
	}// queryAllPage

	/**
	 * 查询每一个的数据
	 */
	public List<UserBean> queryByPage(int page, int n) {

		return uad.queryByPage(page, n);
	}

	/**
	 * 根据id调用dao层的方法 删除用户
	 */
	public boolean deleteUserById(int id) {
		 
		return uad.deleteUserById(id);
	}

	/**
	 * 根据id调用dao层的方法 解冻用户
	 */
	public boolean freezeUserById(int id) {
		return  uad.freezeUserById(id);
	}

	/**
	 * 根据id调用dao层的方法 冻结用户
	 */
	public boolean freeUserById(int id) {
		return uad.freeUserById(id);
	}

	/**
	 * 根据id调用dao层的方法，查询用户
	 */
	public UserBean queryUserById(int id) {
		return uad.queryUserById(id);
	}

	// 修改时查询用户名是否存在
	public int UCKUserName(UserBean ub) {
		// 调用UserAddDao中的方法
		List<UserBean> lu = uad.QueryuserByNameAndId(ub);
		return lu.size();
	}// UCKUserName

	/*
	 * 修改方法
	 */
	public boolean updateUser(UserBean ub) throws SysException {
		// 判断两次密码是否一致
		/*if (!ub.getRepwd().equals(ub.getPassword())) {
			throw new SysException(1002, "两次密码输入不一致");

		}
		// 判断的用户名是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getUsername(), ValidationUtil.CHKNAME)) {
			throw new SysException(1003, "用户名中必须包含字母");
		}
		// 判断密码是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getPassword(), ValidationUtil.CHKPWD)) {
			throw new SysException(1004, "密码必须包含字母数字，且长度不少于6位");
		}
		// 判断真是姓名是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getTruename(), ValidationUtil.CHKTRUENAME)) {
			throw new SysException(1005, "用户真实姓名必须为汉字，且至少两位");
		}
		// 判断出生日期是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getBirth(), ValidationUtil.CHKBIRTHDAY)) {
			throw new SysException(1006, " 出生日期格式:yyyy-mm-dd");
		}
		// 判断邮箱格式是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getEmail(), ValidationUtil.CHKEMAIL)) {
			throw new SysException(1007, " 邮箱格式不正确");
		}
		// 判断电话格式是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getTel(), ValidationUtil.CHKPHONE)) {
			throw new SysException(1008, " 电话格式为11位数，以13578开头");
		}
		// 判断身份证号格式是否符合正则表达式
		if (!ValidationUtil.ifMatch(ub.getCardid(), ValidationUtil.CHKIDCARD)) {
			throw new SysException(1009, "身份证号为18位数");
		}*/
		 
		return uad.updateUser(ub);
	}// updateUser

}
