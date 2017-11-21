package com.lechenggu.bkeasygo.sub.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sub.service.SubCategoryService;
import com.lechenggu.bkeasygo.sub.service.impl.SubCategoryServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class SubCategoryUpdateAction extends ActionSupport{
	SubCategoryService scs=new SubCategoryServiceImpl();
	private SubCategoryBean scb;
	
	public SubCategoryBean getScb() {
		return scb;
	}

	public void setScb(SubCategoryBean scb) {
		this.scb = scb;
	}
	//修改小分类
	public String updateSub(){
		// 利用直接方式ServletActionContext获取session对象
				HttpSession session = ServletActionContext.getRequest().getSession();
				AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
				// 设置外键
				scb.setCreateman(adminbean.getId());
				// 调用service中的方法
				boolean isnull = scs.isNull(scb.getSubname());
				if (isnull) {
					boolean rows = scs.updateSubById(scb);
					if (rows) {
						// 小分类修改失败
						ActionContext ac = ActionContext.getContext();
						ac.getSession().put("SUMSG", "小分类修改失败");
						return ERROR;
					} else {
						// 小分类修改成功
						ActionContext ac = ActionContext.getContext();
						ac.getSession().put("SUSUC", "小分类修改成功");
						return SUCCESS;
					} // 小分类修改的if--else
				} else {
					ActionContext ac = ActionContext.getContext();
					ac.getSession().put("SUSMG", "小分类名称不能为空");
					return ERROR;
				}
	}
}
