<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>订单管理</title>
    <meta charset="utf-8">
	<link type="text/css" href="css/order_manage.css" rel="stylesheet">
	
</head>
<body>
    <section class="main_right">
        <dl>
            <dt></dt>
            <dd>订单管理&nbsp;&nbsp;&nbsp;&nbsp;</dd>
            <dd></dd>
        </dl>
        <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${OSUC }</font>
        <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${UOSUC }</font>
        <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${DOSUC }</font>
        <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${DOMSG }</font>
        <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${QBIARSUC }</font>
        <form action="queryoidandnames" method="post">
            <ul>
                <li>订单号：<label><input type="text" name="selectoid" required=""></label>
                   	 订货人：<label><input type="text" name="selectreceman" required=""></label>
                    <label><input class="submit" type="submit" value="查询"></label></li>
            </ul>
        </form>
        <table>
            <tr class="tr1">
                <td class="td1">编号</td>
                <td class="td2">姓名</td>
                <td class="td3">发货地址</td>
                <td class="td4">状态</td>
                <td class="td5">操作</td>
            </tr>
            <c:forEach items="${lob }" var="order">
            <tr class="tr2">
                <td>${order.oid }</td>
                <td>${order.receman }</td>
                <td>${order.receaddress }</td>
                <td>${order.ostate }</td>
                <td id="td"><a href="OrderQueryById?id=${order.oid}&page=1">修改</a>&nbsp;
                <a href="OrderDeleteServlet?id=${order.oid }&page=${page}" onclick="return confirm('确定删除此订单吗？')">删除</a></td>
            </tr>
            </c:forEach>
        </table>
        <%--   <ul>
		<div style="position :absolute ;top:300px ;left:650px">
		<font style="font-size:12px">
		[${page } / ${count }]<a href="OrderQueryServlet?page=1">首页</a>&nbsp;
        	<c:if test="${page>1 }">
            	<a href="OrderQueryServlet?page=${page-1 }">上一页</a>&nbsp;
        	</c:if>
        	<c:if test="${page<count }">
            	<a href="OrderQueryServlet?page=${page+1 }">下一页</a>&nbsp;
        	</c:if> 
             <a href="OrderQueryServlet?page=${count }">尾页</a></div>
       </font>
       </ul> --%>
</section>

</body>
</html>