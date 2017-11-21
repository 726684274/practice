package com.lechenggu.bkeasygo.user.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.excpetion.SysException;
import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.user.pojo.UserBean;
import com.lechenggu.bkeasygo.user.service.UserService;
import com.lechenggu.bkeasygo.user.service.impl.UserServiceImpl;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;

public class UserAction extends ActionSupport implements ModelDriven<UserBean> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5298082504319345419L;
	//模型驱动
	private UserBean ub = new UserBean();
	//SS整合   不在new对象  将其注入到Spring容器中
	private UserService us;
	
	public UserService getUs() {
		return us;
	}

	public void setUs(UserService us) {
		this.us = us;
	}
	private String myJSON;
	
	public String getMyJSON() {
		return myJSON;
	}

	public void setMyJSON(String myJSON) {
		this.myJSON = myJSON;
	}

	public UserBean getUb() {
		return ub;
	}

	public void setUb(UserBean ub) {
		this.ub = ub;
	}

	@Override
	public UserBean getModel() {
		// TODO Auto-generated method stub
		return ub;
	}
	//用户新增
	public String addUser() {
		UserBean userbean = getModel();
		System.out.println(userbean.getIfuse());
		// 利用直接方式ServletActionContext获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		// 设置外键
		userbean.setCreateman(adminbean.getId());
		userbean.setCreatetime(StringUtil.getDate());
		int i = us.CKUserName(userbean.getUsername());
		try {
			// 如果查询到的数据则为1，不执行if里的方法，只有在没有数据的情况下i<1会执行if里的语句
			//if (i < 1) {
				// 调用用户新增方法
				int rows = us.AddUser(userbean);
				if (rows<0) {
					// 用户新增失败转发到添加页面
					ServletActionContext.getRequest().setAttribute("MSG", "用户新增失败");
					return ERROR;
				} else {
					// 用户新增成功重定向到用户管理的列表项页面
					ActionContext ac=ActionContext.getContext();
					ac.getApplication().put("SUC", "用户新增成功")	;	
					//ServletActionContext.getRequest().setAttribute("SUC", "用户新增成功");
					return SUCCESS;
				} // 用户新增的if--else
			//} else {
				//ServletActionContext.getRequest().setAttribute("MSG", "用户名已存在");
				//return ERROR;
			//} // 用户名是否重名的if--else
		} catch (SysException e) {
			return ERROR;
		} // try--catch

	}// addUser()

	// 查询用户名是否存在
	public String CKUserName() {
		UserBean userbean = getModel();
		int i = us.CKUserName(userbean.getUsername());
		if (i > 0) {
			UserBean ub=new UserBean();
			ub.setCkname("此用户名已存在，重新输入");
			myJSON=JSONObject.fromObject(ub).toString();	
		}
		return SUCCESS;
	}
	//查询所有用户
	public String queryUser() {

		List<UserBean> lu = us.userQuery();
		ActionContext.getContext().put("lu", lu);
		return SUCCESS;
	}// queryUser()

	// 分页查询用户
	public String queryUserPage() {
		// 静态方法获取jsp中的内置对象request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前段的页数的参数
		int page = StringUtil.strToInt(request.getParameter("page"));
		// 每页展示的数据
		int n = 5;
		// 获得总页数
		int count = us.queryCountPage(n);
		// 获得每一页
		List<UserBean> lu = us.queryByPage(page, n);
		// 设置属性
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("lu", lu);
		return SUCCESS;
	}// queryUser()

	// 删除用户
	public String deleteUser() {
		int page = getModel().getPage();
		int id = getModel().getUserid();
		boolean i = us.deleteUserById(id);
		// 判断 如果i>0表示删除成功，重定向到当前页，即刷新当前页
		if (i) {
			ActionContext ac=ActionContext.getContext();
			ac.getApplication().put("DSUC", "删除用户成功");
			//ServletActionContext.getRequest().setAttribute("DSUC", "删除用户成功");
			return SUCCESS;
		} else {
			ActionContext ac=ActionContext.getContext();
			ac.getApplication().put("DMSG", "删除用户失败");
			//ServletActionContext.getRequest().setAttribute("DMSG", "删除用户失败");
			return ERROR;
		}
		
	}

	// 冻结用户
	public String freeUser() {
		int id = getModel().getUserid();
		int page = getModel().getPage();
		// 调用service中的方法
		boolean i = us.freeUserById(id);
		// 判断 如果i>0表示冻结成功，重定向到当前页，即刷新当前页
		if (i ) {
			ActionContext ac=ActionContext.getContext();
			ac.getApplication().put("FFSUC", "冻结成功");
			//ServletActionContext.getRequest().setAttribute("FFSUC", "解冻成功");
		} else {
			ActionContext ac=ActionContext.getContext();
			ac.getApplication().put("FFMSG", "冻结失败");
			//ServletActionContext.getRequest().setAttribute("FFMSG", "解冻失败");
		}
		return SUCCESS;
	}

	// 解冻用户
	public String freezeUser() {
		int id = getModel().getUserid();
		int page = getModel().getPage();
		// 调用service中的方法
		boolean i = us.freezeUserById(id);
		// 判断 如果i>0表示冻结成功，重定向到当前页，即刷新当前页
		if (i ) {
			ActionContext ac=ActionContext.getContext();
			ac.getApplication().put("FSUC", "解冻成功");
			//ServletActionContext.getRequest().setAttribute("FSUC", "冻结成功");
		} else {
			ActionContext ac=ActionContext.getContext();
			ac.getApplication().put("FMSG", "解冻失败");
			//ServletActionContext.getRequest().setAttribute("FMSG", "冻结失败");
		}
		return SUCCESS;
	}
	//根据id查询用户
	public String queryUserById(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//int id=StringUtil.strToInt(request.getParameter("userid"));
		int id = getModel().getUserid();
		UserBean ub=us.queryUserById(id);
		//将ub中的用户信息进行存放在request中  设置属性，在user_modify.jsp页面中显示的默认的要修改的用户信息
		request.setAttribute("ub", ub);
		return SUCCESS;
	}
	//修改用户
	public String updateUser(){
		UserBean userbean = getModel();
		// 利用直接方式ServletActionContext获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		// 设置外键
		userbean.setCreateman(adminbean.getId());
		int i = us.UCKUserName(userbean);
		try {
			// 如果查询到的数据则为1，不执行if里的方法，只有在没有数据的情况下i<1会执行if里的语句
			if (i < 1) {
				// 调用用户修改方法
				boolean rows = us.updateUser(userbean);
				if (rows) {
					ActionContext ac=ActionContext.getContext();
					ac.getSession().put("SUC", "用户修改成功");
					//ServletActionContext.getRequest().setAttribute("SUC", "用户修改成功");
					return SUCCESS;
				} else {
					// 用户修改失败重定向到信息页面
					ActionContext ac=ActionContext.getContext();
					ac.getSession().put("MSG", "用户修改失败");
					//ServletActionContext.getRequest().setAttribute("MSG", "用户修改失败");
					return ERROR;
				} // 用户新增的if--else
			} else {
				ServletActionContext.getRequest().setAttribute("MSG", "用户名已存在");
				return ERROR;
			} // 用户名是否重名的if--else
		} catch (SysException e) {
			return ERROR;
		} // try--catch
	}
}