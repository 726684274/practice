package com.lechenggu.bkeasygo.sup.action;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.sup.service.impl.SupServiceImpl;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SupAction extends ActionSupport implements ModelDriven<SupBean> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private SupBean supbean = new SupBean();
	//SS整合   不在new对象  将其注入到Spring容器中
	private SupService ss ;
	
	public SupService getSs() {
		return ss;
	}

	public void setSs(SupService ss) {
		this.ss = ss;
	}

	public SupBean getSupbean() {
		return supbean;
	}

	public void setSupbean(SupBean supbean) {
		this.supbean = supbean;
	}

	@Override
	public SupBean getModel() {

		return supbean;
	}
	//大分类新增
	public String addSup() {
		SupBean sbean = getModel();
		// 利用直接方式获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 设置外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		sbean.setCreateman(adminbean.getId());
		sbean.setCreatetime(StringUtil.getDate());
		boolean isnull = ss.isNull(supbean.getSupname());
		if (isnull) {
			int rows = ss.ClassNameAdd(sbean);
			if (rows>0) {
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("SSUC", "大分类名称新增成功");
				// ServletActionContext.getRequest().setAttribute("SSUC",
				// "大分类名称新增成功");
				return SUCCESS;
			} else {
				ServletActionContext.getRequest().setAttribute("SSMG", "大分类名称新增失败");
				return ERROR;
			} // 新增的if--else
		} else {
			ServletActionContext.getRequest().setAttribute("SSMG", "大分类名称不能为空");
			return ERROR;
		} // 判空的if--else

	}// addSup()

	// 查询所有大分类
	public String querySup() {
		List<SupBean> ls = ss.querySup();
		ActionContext.getContext().put("ls", ls);
		return SUCCESS;
	}// querySup

	// 分页查询大分类
	public String querySupPage() {
		// 直接方式获得request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前段的页数的参数
		int page = StringUtil.strToInt(request.getParameter("page"));
		// 每页展示的数据
		int n = 5;
		// 获得总页数
		int count = ss.queryCountPage(n);
		// 获得每一页
		List<SupBean> ls = ss.queryByPage(page, n);
		// 设置属性
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("ls", ls);
		return SUCCESS;
	}

	// 删除大分类
	public String deleteSup() {
		int page = getModel().getPage();
		int id = getModel().getSupid();
		boolean rows = ss.deleteSupById(id);
		if (rows) {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("DSSUC", "大分类删除成功");
			// ServletActionContext.getRequest().setAttribute("DSSUC",
			// "大分类删除成功");
			return SUCCESS;
		} else {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("DSMSG", "大分类删除失败");
			// ServletActionContext.getRequest().setAttribute("DSMSG",
			// "大分类删除失败");
			return ERROR;
		}

	}

	// 根据id查询大分类
	public String supById() {
		int supid = getModel().getSupid();
		SupBean sb = ss.querySupById(supid);
		ServletActionContext.getRequest().setAttribute("sb", sb);
		return SUCCESS;
	}

	// 修改大分类
	public String updateSUP() {
		SupBean sbean = getModel();
		// 利用直接方式获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 设置外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		sbean.setCreateman(adminbean.getId());
		// 标题内容不能为空
		boolean isnull = ss.isNull(sbean.getSupname());
		if (isnull) {
			// 调用SupService中的修改方法
			boolean rows = ss.updateSup(sbean);
			if (!rows) {
				// 大分类修改失败
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("SMSG", "大分类修改失败");
				return ERROR;
			} else {
				// 大分类修改成功
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("SUSUC", "大分类修改成功");
				return SUCCESS;
			} // 大分类修改的if--else
		} else {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("SSMG", "大分类名称不能为空");
			return ERROR;
		}
	}
}
