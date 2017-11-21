package com.lechenggu.bkeasygo.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.order.pojo.OrderBean;
import com.lechenggu.bkeasygo.order.service.OrderService;
import com.lechenggu.bkeasygo.order.service.impl.OrderServiceImpl;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<OrderBean> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	//模型驱动
	private OrderBean ob = new OrderBean();
	//SS项目整合    不在new对象  将其注入到Spring容器中
	private OrderService os ;
	public OrderService getOs() {
		return os;
	}

	public void setOs(OrderService os) {
		this.os = os;
	}

	public OrderBean getOb() {
		return ob;
	}

	public void setOb(OrderBean ob) {
		this.ob = ob;
	}

	@Override
	public OrderBean getModel() {
		// TODO Auto-generated method stub
		return ob;
	}
	//订单新增
	public String addOrder() {
		OrderBean orderbean = getModel();
		// 利用直接方式获得session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 获得外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		orderbean.setCreateman(adminbean.getId());
		orderbean.setCreatetime(StringUtil.getDate());
		orderbean.setUserid(63);
		int rows = os.OrderAdd(orderbean);
		if (rows>0) {
			// 用户新增成功重定向到用户管理的列表项页面
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("OSUC", "订单新增成功");
			// ServletActionContext.getRequest().setAttribute("OSUC", "订单新增成功");
			return SUCCESS;
		} else {
			// 用户新增失败转发到添加页面
			ServletActionContext.getRequest().setAttribute("OMSG", "订单新增失败");
			return ERROR;
		}

	} // addOrder()
	
	// 查询所有订单
	public String queryOrder() {
		List<OrderBean> lob = os.queryOrder();
		ActionContext.getContext().put("lob", lob);
		return SUCCESS;
	}

	// 分页查询订单
	public String queryOrderPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前台页数
		int page = StringUtil.strToInt(request.getParameter("page"));
		// 设置每页显示的数据数
		int n = 5;
		// 调用service中的方法 获得总页数
		int count = os.queryCount(n);
		// 调用service中的分页查询的方法
		List<OrderBean> lob = os.queryByPage(page, n);
		// 设置属性
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("lob", lob);
		return SUCCESS;
	}

	// 删除订单
	public String deleteOrder() {
		int oid = getModel().getOid();
		int page = getModel().getPage();
		boolean rows = os.deleteOrderById(oid);
		if (rows) {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("DOSUC", "订单删除成功");
			// ServletActionContext.getRequest().setAttribute("DOSUC",
			// "订单删除成功");
			return SUCCESS;
		} else {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("DOMSG", "订单删除失败");
			// ServletActionContext.getRequest().setAttribute("DOMSG",
			// "订单删除失败");
			return ERROR;
		}

	}

	// 根据id查询订单
	public String orderById() {
		int oid = getModel().getOid();

		// 调用service中的查询方法
		OrderBean ob = os.queryOrderById(oid);
		// 设置属性
		ServletActionContext.getRequest().setAttribute("ob", ob);
		return SUCCESS;
	}

	// 修改订单
	public String updateOrder() {
		OrderBean orderbean = getModel();
		// 利用直接方式获得session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 获得外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		orderbean.setCreateman(adminbean.getId());
		orderbean.setUserid(63);
		// 调用service中的修改方法
		boolean rows = os.updateOrderById(orderbean);
		if (!rows) {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("UOMSG", "订单修改失败");
		} else {
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("UOSUC", "订单修改成功");
		} // 判断是否修改成功
		return SUCCESS;
	}

	// 模糊查询 查询订单号和订单人
	public String queryOidAndName() {
		OrderBean orderbean = getModel();
		// 调用service中的方法
		List<OrderBean> lob = os.queryOrderByIdAndReceman(orderbean.getOid(), orderbean.getReceman());
		ServletActionContext.getRequest().setAttribute("lob", lob);
		if (lob != null) {
			ServletActionContext.getRequest().setAttribute("QBIARSUC", "查询成功");
			return SUCCESS;
		} else {
			ActionContext ac=ActionContext.getContext();
			ac.getSession().put("QBIARMSG", "没有此条订单");
			return ERROR;
		}
		
	}
}
