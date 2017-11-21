<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
<meta charset='utf-8'>
    
    <title>易购——Category</title>
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
      <script type="text/javascript" src="js/changeBGC.js"></script>
	<link type="text/css" href="css/class_manage.css" rel="stylesheet">
 </head>
  
  <body>
    <main>
    
        <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>大分类管理</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${SSUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${DSSUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${DSMSG }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${SUSUC }</span></li>
                </ul>
            </section>
            <div class="line"><font class="font1"></font></div>
            <div class="line"><font class="font1"></font></div>
            <section class="right_middle">
                <table id="result">
                    <tr class="title">
                        <th>编号</th>
                        <th>分类名称</th>
                        <th class="right_border">操作</th>
                    </tr>
             		<c:forEach items="${ls }" var="sup">
             			<tr class="neirong">
             			<td>${sup.supid }</td>
                        <td class="Titl">${sup.supname }</td>
                        <td class="right_border">
                        <img src="images/update1.png">
                        <a href="supquerybyid?supid=${sup.supid }&page=${page}" target="center">修改</a>
                        <img src="images/delete.png">
                        <a href="deletesup?supid=${sup.supid }&page=${page}" onclick="return confirm('删除大分类，对应小分类也会删除，以及所属小分类的商品，确认删除吗？')">删除</a>
                        </td>
             			</tr>
             		</c:forEach>	
                </table>
                <div class="fanye">[${page } / ${count }]<a href="supquerypage?page=1">首页</a>&nbsp;
               <c:if test="${page>1 }">
               <a href="supquerypage?page=${page-1 }">上一页</a>&nbsp; 
               </c:if>
               <c:if test="${page<count }">
               <a href="supquerypage?page=${page+1 }">下一页</a>&nbsp;
               </c:if>
                <a href="supquerypage?page=${count }">尾页</a></div>
            </section>
        </section>
</main>
  </body>
</html>
