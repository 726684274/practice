<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>公告管理</title>
    <meta charset="utf-8">
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
            /*border: 1px solid red;*/
        }
        iframe{
            border: none;
        }
        .header{
            width: 1024px;
            height: 150px;
            margin-left: 170px;
        }
        .main{
            width: 1024px;
            height: 380px;
            margin-left: 170px;
        }
        .main_left{
            width: 181px;
            height: 342px;
            margin-left: 26px;
            float: left;
        }
        .main_right{
            width: 796px;
            height: 342px;
            float: left;
            margin-top: 10px;
            margin-left: 10px;
        }
        .main_right dl{
            height: 24px;
            border-bottom: 2px solid #fc883b;
        }
        .main_right dl dt{
            background-image: url("../images/bg.png");
            background-position: -236px -107px;
            width: 13px;
            height: 14px;
            float: left;
            margin-right: 6px;
        }
        .main_right dl dd{
            line-height: 14px;
            font-weight: bold;
            font-size: 15px;
        }
        .main_right table{
            border-top: 2px solid #dfc9b2;
            border-bottom: 2px solid #dfc9b2;
            border-collapse: collapse;
            margin-top: 20px;
            margin-left: 20px;
            text-align: center;
        }
        .tr1{
            height: 25px;
            font-size: 14px;
            color: #8a7152;
            font-weight: bold;
            background-color: #f7f4eb;
        }
        .tr2{
            height: 32px;
            font-size: 13px;
        }
        .td1{
            width: 130px;
            border-right: 0;
        }
        .td2{
            width: 540px;
        }
        .td3{
            width: 80px;
        }
        .td4{
            text-align: left;
            border-left: 1px solid #e1e1e1;
            border-right: 1px solid #e1e1e1;
            border-top: 1px solid #dfc9b2;
        }
        .td5{
            border-top: 1px solid #dfc9b2;
        }
        .td6{
            border-top: 1px solid #dfc9b2;
            border-right: 0;
        }
        .main_right table tr td a{
            text-decoration: none;
        }
        .main_right ul{
            float: right;
            margin-right: 22px;
            margin-top: 18px;
            font-size: 15px;
        }
        .main_right ul li{
            list-style-type: none;
            text-align: center;
            float: left;
            width: 14px;
            height: 20px;
            border: 1px solid #eeeeee;
            margin-right: 5px;
        }
        .main_right ul li a{
            text-decoration: none;
        }
        .footer{
            width: 1024px;
            height: 31px;
            margin-left: 170px;
        }
        .font2{
        color:red;
       font-weight: bold;
        }
    </style>
</head>
<body>
    <section class="main_right">
        <dl>
            <dt></dt>
            <dd>公告管理</dd>
            <dd></dd>
        </dl>
       <div>
       <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${ASUC }</font>
       <font class="font2" style="color:red">&nbsp;&nbsp;&nbsp;&nbsp; ${DASUC }</font>
       <font class="font2" style="color:red"> &nbsp;&nbsp;&nbsp;&nbsp;${DAMSG }</font>
       <font class="font2" style="color:red"> &nbsp;&nbsp;&nbsp;&nbsp;${AASUC }</font>
       </div>
        <table>
            <tr class="tr1">
                <td class="td1">编号</td>
                <td class="td2">公告标题</td>
                <td class="td3">操作</td>
            </tr>
            <c:forEach items="${lab }" var="ann">
	            <tr class="tr2">
	                <td class="td5">${ann.bid }</td>
	                <td class="td4">&nbsp;${ann.btitile }</td>
	                <td class="td6"><a href="queryannouncementbyid?bid=${ann.bid}&page=${page}">修改</a>&nbsp;
	                <a href="deleteannouncement?bid=${ann.bid}&page=${page}" onclick="return confirm('确认删除吗？')">删除</a>
	                </td>
	            </tr>
         	</c:forEach>
        </table>
        <ul>
       		 [${page } / ${count }]<a href="queryannouncementpage?page=1">首页</a>&nbsp;
               <c:if test="${page>1 }">
               	<a href="queryannouncementpage?page=${page-1 }">上一页</a>&nbsp;
               </c:if>
              
               <c:if test="${page<count }">
               <a href="queryannouncementpage?page=${page+1 }">下一页</a>&nbsp;
               </c:if>
               
                <a href="queryannouncementpage?page=${count }">尾页</a>
            <!-- <li><a href="AnnouncementQueryServlet?page=1">首页</a></li>
            <li><a href="#">2</a></li> -->

        </ul>
    </section>


</body>
</html>