package com.lechenggu.bkeasygo.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lechenggu.bkeasygo.goods.dao.GoodsDao;
import com.lechenggu.bkeasygo.goods.dao.impl.GoodsDaoImpl;
import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.goods.service.GoodsService;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;

public class GoodsServiceImpl implements GoodsService{
	// 关联GoodsDao SS整合   不在new对象  将其注入到Spring容器中
	GoodsDao gd ;
	public GoodsDao getGd() {
		return gd;
	}

	public void setGd(GoodsDao gd) {
		this.gd = gd;
	}

	/*
	 * 调用GoodsDao中的商品新增方法
	 */
	public int goodsAdd(GoodsBean gb) {

		return gd.goodsAdd(gb);
	}// goodsAdd

	/**
	 * 调用dao层中的方法查询总页数
	 */

	public int queryCount(int n) {
		int count = gd.queryCount();
		return (count % n == 0 ? (count / n) : (count / n + 1));
	}// queryCount

	/**
	 * 调用Dao层的方法查询每页的记录
	 */
	public List<GoodsBean> queryByPage(int page, int n) {
		List<Object> lo=gd.queryByPage(page, n);
		List<GoodsBean> lgb=new ArrayList<GoodsBean>();
		for (int i = 0; i < lo.size(); i++) {
			Object[] obj = (Object[]) lo.get(i);
			GoodsBean gb = new GoodsBean();
			if (obj.length > 0) {
				gb.setGid((int) obj[0]);
				gb.setGname((String) obj[1]);
				gb.setGprice((double) obj[2]);
				gb.setGnumber((int) obj[3]);
				gb.setGbigpic((String) obj[4]);
				gb.setGnumberroduce((String) obj[5]);
				gb.setIftop((int) obj[6]);
				gb.setSupid((int) obj[7]);
				gb.setSupname((String) obj[8]);
				gb.setSubid((int) obj[9]);
				gb.setSubname((String) obj[10]);
				/*scb.setSubid((int) obj[0]);
				scb.setSubname((String) obj[1]);
				scb.setSupname((String) obj[2]);*/
			}
			lgb.add(gb);
		}
		return lgb;
	}

	/*
	 * 调用Dao层中的根据id查询商品查询方法
	 */
	public GoodsBean queryGoodsById(int id) {
		List<Object> lo=gd.queryGoodsById(id);
		Object[] obj = (Object[]) lo.get(0);
		GoodsBean gb = new GoodsBean();
		if (obj.length > 0) {
			gb.setGid((int) obj[0]);
			gb.setGname((String) obj[1]);
			gb.setGprice((double) obj[2]);
			gb.setGnumber((int) obj[3]);
			gb.setGbigpic((String) obj[4]);
			gb.setGnumberroduce((String) obj[5]);
			gb.setIftop((int) obj[6]);
			gb.setSupid((int) obj[7]);
			gb.setSupname((String) obj[8]);
			gb.setSubid((int) obj[9]);
			gb.setSubname((String) obj[10]);
		}
		
		return gb;
	}

	/**
	 * 调用Dao层中的删除方法
	 */
	public boolean deleteGoodsById(int id) {
		boolean rows = gd.deleteGoodsById(id);
		return rows;
	}// deleteGoodsById

	/*
	 * 调用Dao层中的根据id修改商品详情
	 */
	public boolean updateGoodsById(GoodsBean gb) {
		return gd.updateGoodsById(gb);
	}
}
