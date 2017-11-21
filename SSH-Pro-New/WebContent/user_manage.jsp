<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>易购——user_manage</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
      <script type="text/javascript" src="js/changeBGC.js"></script>
    <link type="text/css" href="css/user_manage.css" rel="stylesheet">
    <script type="text/javascript" src="js/check1.js" charset="utf-8"></script>
</head>
<body>
<main>
       <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>用户管理</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${SUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${DSUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${FMSG }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${FFMSG }</span></li>
                    
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${FSUC }</span></li>
                    
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${FFSUC }</span></li>
                </ul>
            </section>
            <div class="line"></div>
            <section class="right_middle">
                <table>
                    <tr class="title">
                        <th>用户编号</th>
                        <th>用户名</th>
                        <th>真实姓名</th>
                        <th>性别</th>
                        <th>Email</th>
                        <th>手机</th>
                        <th>冻结/解冻</th>
                        <th class="right_border">操作</th>
                    </tr>
                    <c:forEach items="${lu }" var="user">
	                    <tr class="neirong">
	                        <td>${user.userid }</td>
	                        <td>${user.username }</td>
	                        <td>${user.truename }</td>
	                        <td>
	                        	<c:if test="${user.sex==1 }">男</c:if>
	                        	<c:if test="${user.sex==0 }">女</c:if>
	                        </td>
	                        <td>${user.email }</td>
	                        <td>${user.tel }</td>
	                        <td>
	                        <!-- 默认的状态为解冻状态 -->
	                        <c:if test="${user.ifuse==0 }">
	                        <img src="images/jiedong.png">
	                        <a href="freezeuser?userid=${user.userid }&page=${page}">解冻</a>
	                        </c:if>
	                        <c:if test="${user.ifuse==1 }">
	                        <img src="images/dongjie.png">
	                        <a href="freeuser?userid=${user.userid }&page=${page}">冻结</a>
	                        </c:if>
	                        </td>
	                        <td class="right_border">
	                        <img src="images/update1.png">
	                        <a href="queryuserbyid?userid=${user.userid }&page=${page}" target="center">修改</a>
	                        <img src="images/delete.png">
	                        <a href="userdelete?userid=${user.userid }&page=${page}" onclick="return confirm('确认删除吗？')">删除</a>
	                        </td>
	                    </tr>
                    </c:forEach>
                </table>
                
                <div class="fanye">[${page } / ${count }]<a href="queryuserpage?page=1">首页</a>&nbsp;
               <c:if test="${page>1 }">
               	<a href="queryuserpage?page=${page-1 }">上一页</a>&nbsp;
               </c:if>
               <c:if test="${page<count }">
               <a href="queryuserpage?page=${page+1 }">下一页</a>&nbsp;
               </c:if>
                
                <a href="queryuserpage?page=${count }">尾页</a></div>
            </section>
        </section>
</main>
</body>
</html>