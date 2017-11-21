<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
	<%-- <%
	AdminBean ab=(AdminBean)session.getAttribute("adminbean");
	String realname=ab.getRealname();
	%> --%>
	<%
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String time=sdf.format(date);
	%>
   <meta charset="utf-8">
    <title>易购-头部</title>
 <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <link type="text/css" href="css/index.css" rel="stylesheet">
    <script type="text/javascript">
    function getNow(){
			var date=new Date();
			var year=date.getFullYear();
			var month=date.getMonth()+1;
			var day=date.getDate();
			var hour=date.getHours();
			var minute=date.getMinutes();
			var second=date.getSeconds();
			var result=year+"年"+month+"月"+day+"日     "+hour+"点"+minute+"分"+second+"秒";
			document.getElementById("timeSpan").innerHTML=result;
			window.setTimeout("getNow()",1000);
		}
    window.onload=function(){
    	
    	getNow();
    }
    </script>
</head>
<body>
<header>
    <div class="pic">
        <div>
            <ul>
                <li class="index"><a href="center.jsp" target="center">首页</a></li>
                <li class="index"><a href="userqueryservlet?page=1" target="center">用户</a></li>
                <li class="index"><a href="SubCategoryQueryServlet?page=1" target="center">商品分类</a></li>
                <li class="index"><a href="OrderQueryServlet?page=1" target="center">订单</a></li>
                <li class="index"><a href="GoodsQueryServlet?page=1" target="center">商品详细</a></li>
                <li class="index"><a href="AnnouncementQueryServlet?page=1" target="center">公告</a></li>
            </ul>
        </div>
    </div>
                     <%--获取对象的属性值   ${对象名.对象的属性名} --%>
    <div class="TiShi">管理员${adminbean.realname }您好，今天是<span id="timeSpan"></span>，欢迎来到贝壳•易购商城后台管理系统。[<a href="loginexit" target="_parent">注销</a>]</div>
    <div class="address">您现在的位置：<a href="../../yigoumanage" target="_parent" > 贝壳易购•商城</a> &gt; <span id="positio">后台管理</span></div>
</header>
</body>
<script type="text/javascript">
	$(function(){
		$("li").click(function(){
			$(this).prop("class","indexOn");
			$(this).prevAll().prop("class","index")
					.end()
					.nextAll().prop("class","index");
			$("#positio").html($(this).children().html());
		});
	});
	/*var conl=document.getElementsByTagName("li");
	for(var i=0;i<conl.length;i++){	
		conl[i].onmouseout=function(){	
			this.className="index";
			
		};
		conl[i].onmouseover=function(){
		$("li:not(this)").attr("class","index");
			this.className="indexOn";
		};
	}*/
</script>
</html>