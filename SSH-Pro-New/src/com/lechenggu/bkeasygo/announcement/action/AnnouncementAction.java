package com.lechenggu.bkeasygo.announcement.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.announcement.pojo.AnnouncementBean;
import com.lechenggu.bkeasygo.announcement.service.AnnouncementService;
import com.lechenggu.bkeasygo.announcement.service.impl.AnnouncementServiceImpl;
import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AnnouncementAction extends ActionSupport implements ModelDriven<AnnouncementBean>{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	//模型驱动
	private AnnouncementBean ab=new AnnouncementBean();
	//SS整合  不在new对象，将其注入到Spring容器中
	private AnnouncementService as;
	public AnnouncementService getAs() {
		return as;
	}

	public void setAs(AnnouncementService as) {
		this.as = as;
	}

	public AnnouncementBean getAb() {
		return ab;
	}

	public void setAb(AnnouncementBean ab) {
		this.ab = ab;
	}

	@Override
	public AnnouncementBean getModel() {
		return ab;
	}
	//公告新增
	public String addAnnouncement(){
		AnnouncementBean aBean = getModel();
		//利用直接方式ServletActionContext获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//设置外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		//
		aBean.setCreateman(adminbean.getId());
		aBean.setCreatetime(StringUtil.getDate());
		boolean isnull=as.isNull(aBean.getBtitile(), aBean.getBinner());
		if(isnull){
			int rows=as.addAnnouncement(aBean);
			if(rows>0){
				ActionContext ac=ActionContext.getContext();
				Map<String, Object> sess=ac.getApplication();
				sess.put("ASUC", "公告新增成功");
				//ServletActionContext.getRequest().setAttribute("ASUC", "公告新增成功");
//			用户新增成功转发到用户管理的列表项页面
				return SUCCESS;
			}else{
				
				ServletActionContext.getRequest().setAttribute("AMSG", "公告新增失败");
//				用户新增失败转发到添加页面
				return ERROR;
			}
		}else{
			ServletActionContext.getRequest().setAttribute("AMSG", "标题或内容不能为空");
			return ERROR;
		}
	}//addAnnouncement()
	//查询所有公告
	public String queryAnnouncement(){
		List<AnnouncementBean> lab=as.queryAnnouncement();
		ActionContext.getContext().put("lab", lab);
		return SUCCESS;
	}
	//分页查询公告
	public String queryAnnouncementPage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//获得前台的页数
		int page=StringUtil.strToInt(request.getParameter("page"));
		//设置每页显示的数据数
		int n=5;
		//调用service中的获得总页数的方法
		int count=as.queryCount(n);
		//调用service中的分页查询的方法
		List<AnnouncementBean> lab=as.queryByPage(page, n);
		//设置属性
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("lab", lab);
		return SUCCESS;
	}
	//删除公告
	public String deleteAnnouncement(){
		int id=getModel().getBid();
		int page=getModel().getPage();
		//调用service中的删除方法
		boolean rows=as.deleteAnnouncementById(id);
		//判断  如果rows>0表示删除成功，转发到当前页，即刷新当前页
		if(rows){
			ActionContext ac=ActionContext.getContext();
			Map<String, Object> se=ac.getApplication();
			se.put("DASUC", "公告删除成功");
			//ServletActionContext.getRequest().setAttribute("DASUC", "公告删除成功");
			return SUCCESS;
		}else{
			ActionContext ac=ActionContext.getContext();
			Map<String, Object> se=ac.getApplication();
			se.put("DAMSG", "公告删除失败");
			//ServletActionContext.getRequest().setAttribute("DAMSG", "公告删除失败");
			return ERROR;
		}
		
	}
	
	//根据id查询公告
	public String announcementById(){
		HttpServletRequest request=ServletActionContext.getRequest();
		int bid=getModel().getBid();
		AnnouncementBean ab=as.queryAnnouncementById(bid);
		request.setAttribute("ab", ab);
		return SUCCESS;
	}
	//修改公告
	public String updateAnnouncement(){
		AnnouncementBean aBean = getModel();
		//利用直接方式ServletActionContext获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//设置外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		//
		aBean.setCreateman(adminbean.getId());
		boolean isnull=as.isNull(aBean.getBtitile(),aBean.getBinner());
		if(isnull){
			boolean rows=as.updateAnnouncement(ab);
			if(!rows){
				ActionContext ac=ActionContext.getContext();
				ac.getSession().put("AAMSG", "公告修改失败");
				return ERROR;
			}else{
				ActionContext ac=ActionContext.getContext();
				ac.getSession().put("AASUC", "公告修改成功");
				return SUCCESS;
			}//判断是否修改成功
		}else{
			ActionContext ac=ActionContext.getContext();
			ac.getSession().put("AAMSG", "公告修改失败");
			return ERROR;
		}//判空的if--else
		
	}
}
