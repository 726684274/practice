package com.lechenggu.bkeasygo.sub.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sub.service.SubCategoryService;
import com.lechenggu.bkeasygo.sub.service.impl.SubCategoryServiceImpl;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SubCategoryAction extends ActionSupport implements ModelDriven<SubCategoryBean> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	//模型驱动
	private SubCategoryBean scb = new SubCategoryBean();
	//SS整合   不在new对象  将其注入到Spring容器中
	private SubCategoryService scs ;
	
	private SupService ss;
	
	
	public SupService getSs() {
		return ss;
	}

	public void setSs(SupService ss) {
		this.ss = ss;
	}

	public SubCategoryService getScs() {
		return scs;
	}

	public void setScs(SubCategoryService scs) {
		this.scs = scs;
	}

	public SubCategoryBean getScb() {
		return scb;
	}

	public void setScb(SubCategoryBean scb) {
		this.scb = scb;
	}

	@Override
	public SubCategoryBean getModel() {
		// TODO Auto-generated method stub
		return scb;
	}
	//新增小分类
	public String addSub() {
		SubCategoryBean subbean = getModel();
		System.out.println("==="+subbean);
		System.out.println("*************************8");
		System.out.println(subbean.getSupid());
		// 利用直接方式ServletActionContext获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		/* SupBean supbean=(SupBean)session.getAttribute("supbean"); */
		// 设置外键
		subbean.setCreateman(adminbean.getId());
		subbean.setCreatetime(StringUtil.getDate());
		SupBean sp = ss.querySupById(subbean.getSupid());
		subbean.setSup(sp);
		/* subbean.setSupid(supbean.getSupid()); */
		boolean isnull = scs.isNull(subbean.getSubname());
		if (isnull) {
			int rows = scs.addSubCategoryDao(subbean);
			if (rows>0) {
				ActionContext ac = ActionContext.getContext();
				ac.getSession().put("SCSUC", "小分类名称新增成功");
				// ServletActionContext.getRequest().setAttribute("SCSUC",
				// "小分类名称新增成功");
				return SUCCESS;
			} else {
				ServletActionContext.getRequest().setAttribute("SCSMG", "小分类名称新增失败");
				return ERROR;
			} // 新增的if--else
		} else {
			ServletActionContext.getRequest().setAttribute("SCSMG", "小分类名称不能为空");
			return ERROR;
		} // 判空的if--else

	}// addSub()

	// 查询所有小分类
	public String querySub() {
		List<SubCategoryBean> lsb = scs.querySub();
		ActionContext.getContext().put("lsb", lsb);
		return SUCCESS;
	}

	// 分页查询小分类
	public String querySubPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获取前台的数据
		int page = StringUtil.strToInt(request.getParameter("page"));
		// 设置每页显示的数据
		int n = 5;
		// 调用service中的查询总页数方法
		int count = scs.quertCount(n);
		// 查询每页数据的方法
		List<SubCategoryBean> lsb = scs.queryByPage(page, n);
		// 设置属性
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("lsb", lsb);
		return SUCCESS;
	}

	// 删除小分类
	public String deleteSub() {
		int page = getModel().getPage();
		int subid = getModel().getSubid();
		boolean rows = scs.deleteSubById(subid);
		if (rows) {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("DSSUC", "小分类删除成功");
			// ServletActionContext.getRequest().setAttribute("DSSUC",
			// "小分类删除成功");
			return SUCCESS;
		} else {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("DSMSG", "小分类删除失败");
			// ServletActionContext.getRequest().setAttribute("DSMSG",
			// "小分类删除失败");
			return ERROR;
		}

	}

	// 根据id查询小分类
	public String subById() {
		int subid = getModel().getSubid();
		SubCategoryBean scb = scs.querySubById(subid);
		ServletActionContext.getRequest().setAttribute("scb", scb);
		return SUCCESS;
	}

	// 修改小分类
	public String updateSub() {
		System.out.println("***");
		SubCategoryBean subbean = getModel();
		int supid=getModel().getSupid();
		// 利用直接方式ServletActionContext获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		// 设置外键
		subbean.setCreateman(adminbean.getId());
		SupBean sp = ss.querySupById(subbean.getSupid());
		subbean.setSup(sp);
		// 调用service中的方法
		boolean isnull = scs.isNull(subbean.getSubname());
		if (isnull) {
			boolean rows = scs.updateSubById(subbean);
			if (!rows) {
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
