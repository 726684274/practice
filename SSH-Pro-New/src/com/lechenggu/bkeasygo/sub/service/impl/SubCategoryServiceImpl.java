package com.lechenggu.bkeasygo.sub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lechenggu.bkeasygo.sub.dao.SubCategoryDao;
import com.lechenggu.bkeasygo.sub.dao.impl.SubCategoryDaoImpl;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sub.service.SubCategoryService;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;

public class SubCategoryServiceImpl implements SubCategoryService{
	// 关联SubCategoryDao
	private SubCategoryDao scd;
	
	public SubCategoryDao getScd() {
		return scd;
	}

	public void setScd(SubCategoryDao scd) {
		this.scd = scd;
	}

	/*
	 * 调用SubCategoryDao中的新增方法
	 */
	public int addSubCategoryDao(SubCategoryBean scb) {
		int rows = scd.addSub(scb);
		return rows;
	}

	/*
	 * 判空方法
	 */
	public boolean isNull(String smallClass) {
		if (!smallClass.equals(" ") && smallClass != null) {
			return true;
		} else {

			return false;
		}
	}// isnull
	/*
	 * 查询所有小分类
	 */
	public List<SubCategoryBean> querySub(){
		return scd.querySub();
	}
	
	/**
	 * 调用Dao层的方法 查询总页数
	 */
	public int quertCount(int n) {
		int count = scd.queryCount();
		return (count % n == 0 ? (count / n) : (count / n + 1));
	}

	/**
	 * 调用Dao层的方法 查询每页的数据
	 */
	public List<SubCategoryBean> queryByPage(int page, int n) {
		List<Object> lo=scd.queryByPage(page, n);
		List<SubCategoryBean> lsb=new ArrayList<SubCategoryBean>();
		for (int i = 0; i < lo.size(); i++) {
			Object[] obj = (Object[]) lo.get(i);
			SubCategoryBean scb = new SubCategoryBean();
			if (obj.length > 0) {
				scb.setSubid((int) obj[0]);
				scb.setSubname((String) obj[1]);
				//scb.setSupid((int) obj[2]);
				scb.setSupname((String) obj[2]);
			}
			lsb.add(scb);
		}
		return lsb;

	}
		
	/*
	 * 调用dao层中的根据id查询小分类
	 */
	public SubCategoryBean querySubById(int id) {
		List<Object> lo=scd.querySubById(id);
		Object[] obj = (Object[]) lo.get(0);
		SubCategoryBean scb = new SubCategoryBean();
		if (obj.length > 0) {
			scb.setSubid((int) obj[0]);
			scb.setSubname((String) obj[1]);
			scb.setSupid((int) obj[2]);
			scb.setSupname((String) obj[3]);
		}
		
		return scb;
	}

	/**
	 * 调用Dao层中的根据id修改小分类的方法
	 */
	public boolean updateSubById(SubCategoryBean sb) {
		return scd.updateSubById(sb);
	}

	/*
	 * 调用Dao中的删除方法
	 */
	public boolean deleteSubById(int id) {
		return scd.deleteSubById(id);
		
	}// deleteSubById

	/**
	 * 查询小分类的名字
	 */
	public List<SubCategoryBean> queryBySubname(int supid) {
		return scd.queryBySubname(supid);
	}
}
