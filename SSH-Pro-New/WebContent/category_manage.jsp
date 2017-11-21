<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>易购——CategorySecond</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <link type="text/css" href="css/category_manage.css" rel="stylesheet">
</head>
<body>
<main>
        <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>小分类管理</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;${SCSUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;${SUSUC}</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;${DSSUC}</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;${DSMSG}</span></li>
                </ul>
            </section>
            <div class="line"></div>
            <section class="right_middle">
                <table>
                    <tr class="title">
                        <th>编号</th>
                        <th>商品小分类</th>
                        <th>所属类别</th>
                        <th class="right_border">操作</th>
                    </tr>
                 <c:forEach items="${lsb }" var="sub">  
                    <tr class="neirong">
                        <td>${sub.subid }</td>
                        <td class="Title">
                   		  ${sub.subname }
                        </td>
                        <td>${sub.supname }</td>
                        <td class="right_border">
                        <img src="images/update1.png">
                        <a href="subquerybyid?subid=${sub.subid }&page=${page}" target="center">修改</a>
                        <img src="images/delete.png">
                        <a href="deletesub?subid=${sub.subid }&page=${page}" onclick="return confirm('删除小分类，对应的商品也会删除吗？')">删除</a></td>
                    </tr>
                </c:forEach>    
                </table>
               <div class="fanye">[${page } / ${count }]<a href="subquerypage?page=1">首页</a>&nbsp;
               <c:if test="${page>1 }">
               	<a href="subquerypage?page=${page-1 }">上一页</a>&nbsp;
               </c:if>
               <c:if test="${page<count }">
               <a href="subquerypage?page=${page+1 }">下一页</a>&nbsp;
               </c:if>
                
                <a href="subquerypage?page=${count }">尾页</a></div>
            </section>
        </section>

</main>
</body>
</html>