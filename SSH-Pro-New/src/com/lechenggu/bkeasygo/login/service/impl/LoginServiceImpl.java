package com.lechenggu.bkeasygo.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lechenggu.bkeasygo.login.dao.LoginDao;
import com.lechenggu.bkeasygo.login.dao.impl.LoginDaoImpl;
import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
//		关联LoginDao SS整合   不在new对象  将其注入到Spring容器中
		private LoginDao loginDao;

		public LoginDao getLoginDao() {
			return loginDao;
		}
		@Autowired
		public void setLoginDao(LoginDao loginDao) {
			this.loginDao = loginDao;
		}


		@SuppressWarnings("")
		@Override
		@Transactional(rollbackFor=Exception.class,readOnly=true)
		//根据用户名和密码查询管理员
		public AdminBean CkAdmin(AdminBean ab){
		return loginDao.CKAdminByNP(ab);
		}
}		
