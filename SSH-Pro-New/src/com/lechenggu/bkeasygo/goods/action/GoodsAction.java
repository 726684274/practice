package com.lechenggu.bkeasygo.goods.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.lechenggu.bkeasygo.goods.pojo.GoodsBean;
import com.lechenggu.bkeasygo.goods.service.GoodsService;
import com.lechenggu.bkeasygo.goods.service.impl.GoodsServiceImpl;
import com.lechenggu.bkeasygo.login.pojo.AdminBean;
import com.lechenggu.bkeasygo.sub.pojo.SubCategoryBean;
import com.lechenggu.bkeasygo.sub.service.SubCategoryService;
import com.lechenggu.bkeasygo.sup.pojo.SupBean;
import com.lechenggu.bkeasygo.sup.service.SupService;
import com.lechenggu.bkeasygo.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class GoodsAction extends ActionSupport {
	//文件上传
	private File gbigpic;
	private String gbigpicFileName;
	private String gbigpicContentType;
	//属性驱动
	private GoodsBean gb ;
	//SS整合   不在new对象  将其注入到Spring容器中
	private GoodsService gs;
	private SupService ss;
	private SubCategoryService scs;
	
	public SubCategoryService getScs() {
		return scs;
	}

	public void setScs(SubCategoryService scs) {
		this.scs = scs;
	}

	public SupService getSs() {
		return ss;
	}

	public void setSs(SupService ss) {
		this.ss = ss;
	}

	public GoodsService getGs() {
		return gs;
	}

	public void setGs(GoodsService gs) {
		this.gs = gs;
	}

	public GoodsBean getGb() {
		return gb;
	}

	public void setGb(GoodsBean gb) {
		this.gb = gb;
	}

	public String addGoods() {
		//获取新文件上传之后的路径
		//String path=ServletActionContext.getServletContext().getRealPath("upload");
		
		System.out.println(gbigpic);
		//具体的业务逻辑处理
				//文件名重名时 在文件名前加前缀，保证前缀不重复
				String newFileName=UUID.randomUUID().toString()+"_"+gbigpicFileName;
				//获取文件上传之后的路径
				String path="E:\\advancedworkspace\\SSH-Pro02\\WebContent\\image";
				String newpath=path+"\\"+newFileName;
				//实例化新的对象
				File file=new File(newpath);
				//创建输入输出流
				FileInputStream fis=null;
				FileOutputStream fos=null;
				
				try {
					//将旧文件输入到通道里
					fis=new FileInputStream(gbigpic);
					//将内容输出到新文件
					fos=new FileOutputStream(file);
					//读取内容
					IOUtils.copy(fis, fos);
					//关闭资源
					fis.close();
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		// 利用直接方式获得session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 获得外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		gb.setCreateman(adminbean.getId());
		gb.setGbigpic(newFileName);
		gb.setCreatetime(StringUtil.getDate());
		SupBean sp = ss.querySupById(gb.getSupid());
		gb.setSup(sp);
		SubCategoryBean sub=scs.querySubById(gb.getSubid());
		gb.setSub(sub);
		int rows = gs.goodsAdd(gb);
		if (rows>0) {
			// 用户新增成功重定向到用户管理的列表项页面
			ActionContext ac=ActionContext .getContext();
			ac.getSession().put("GSUC", "商品新增成功");
			return SUCCESS;
		} else {
			// 用户新增失败重定向到添加页面
			ServletActionContext.getRequest().setAttribute("GMSG", "商品新增失败");
			return ERROR;
		}

	}//addGoods方法
	
	//分页查询商品
	public String queryGoodsPage(){
		HttpServletRequest request=ServletActionContext.getRequest();
		//获取前台的页数
		int page=gb.getPage();
		System.out.println(page);
		//int page=StringUtil.strToInt(request.getParameter("page"));
		//设置每页显示的数据
		int n=5;
		//调用service中的方法  查询总页数
		int count=gs.queryCount(n);
		//调用service中的方法  查询每一个数据
		List<GoodsBean> lg=gs.queryByPage(page, n);
		//设置属性
		request.setAttribute("page", page);
		request.setAttribute("count", count);
		request.setAttribute("lg", lg);
		return SUCCESS;
	}

	public File getGbigpic() {
		return gbigpic;
	}

	public void setGbigpic(File gbigpic) {
		this.gbigpic = gbigpic;
	}

	public String getGbigpicFileName() {
		return gbigpicFileName;
	}

	public void setGbigpicFileName(String gbigpicFileName) {
		this.gbigpicFileName = gbigpicFileName;
	}

	public String getGbigpicContentType() {
		return gbigpicContentType;
	}

	public void setGbigpicContentType(String gbigpicContentType) {
		this.gbigpicContentType = gbigpicContentType;
	}
	
	//删除商品
	public String deleteGoods(){
		int page=gb.getPage();
		int gid=gb.getGid();
		//调用servlce中的删除方法
		boolean rows=gs.deleteGoodsById(gid);
		//rows等于1表示删除成功   刷新当前页面
		if(!rows){
			ActionContext  ac=ActionContext.getContext();
			ac.getSession().put("DGMSC", "商品删除失败");
			return ERROR;
		}else{
			ActionContext  ac=ActionContext.getContext();
			ac.getSession().put("DGSUC", "商品删除成功");
			return SUCCESS;
		}
		
	}
	//根据id查询商品
	public String goodsById(){
		int gid=gb.getGid();
		System.out.println("~~~~~~~~~");
		GoodsBean gb=gs.queryGoodsById(gid);
		ServletActionContext.getRequest().setAttribute("gb", gb);
		return SUCCESS;
	}
	//修改商品
	public String updateGoods(){
		System.out.println(gbigpic);
		//具体的业务逻辑处理
				//文件名重名时 在文件名前加前缀，保证前缀不重复
				String newFileName=UUID.randomUUID().toString()+"_"+gbigpicFileName;
				//获取文件上传之后的路径
				String path="E:\\advancedworkspace\\SSH-Pro02\\WebContent\\image";
				String newpath=path+"\\"+newFileName;
				//实例化新的对象
				File file=new File(newpath);
				//创建输入输出流
				FileInputStream fis=null;
				FileOutputStream fos=null;
				
				try {
					//将旧文件输入到通道里
					fis=new FileInputStream(gbigpic);
					//将内容输出到新文件
					fos=new FileOutputStream(file);
					//读取内容
					IOUtils.copy(fis, fos);
					//关闭资源
					fis.close();
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		// 利用直接方式获得session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 获得外键
		AdminBean adminbean = (AdminBean) session.getAttribute("adminbean");
		gb.setCreateman(adminbean.getId());
		gb.setGbigpic(newFileName);
		SupBean sp = ss.querySupById(gb.getSupid());
		gb.setSup(sp);
		SubCategoryBean sub=scs.querySubById(gb.getSubid());
		gb.setSub(sub);
//		调用service中的修改方法
		boolean rows=gs.updateGoodsById(gb);
		if(!rows){
			ActionContext ac=ActionContext.getContext();
			ac.getSession().put("UGMSC", "商品修改失败");
			return ERROR;
		}else{
			ActionContext ac=ActionContext.getContext();
			ac.getSession().put("UGSUC", "商品修改成功");
			return SUCCESS;
		}

	}
	
}
