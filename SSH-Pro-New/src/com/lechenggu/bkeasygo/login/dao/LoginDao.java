package com.lechenggu.bkeasygo.login.dao;

import java.util.List;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;

public interface LoginDao {
	public List<AdminBean> QueryByCondition(String condition,Object[] obj);
	public AdminBean CKAdminByNP(AdminBean ab);
}
