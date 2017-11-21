<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>

    <title>易购——goods_detail_manage</title>
    <meta charset="utf-8">
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <link type="text/css" href="css/goods_detail_manage.css" rel="stylesheet">
     <script type="text/javascript" src="js/changeBGC.js"></script>
</head>
<body>
<main>
    
        <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>商品详细信息管理</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${GSUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${DGMSC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${DGSUC }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${UGSUC }</span></li>
                </ul>
            </section>
            <div class="line"></div>
            <section class="right_middle">
                <table>
                    <tr class="title">
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>所属大分类</th>
                        <th>所属小分类</th>
                        <th>价格</th>
                        <th>剩余数量</th>
                        <th>图片</th>
                        <th class="right_border">操作</th>
                    </tr>
                    <c:forEach items="${lg }" var="goods">
                    <tr class="neirong">
                        <td>${goods.gid }</td>
                        <td>${goods.gname }</td>
                        <td>
                     		${goods.supname }
                        </td>
                        <td>
                     		${goods.subname }
                        </td>
                        <td>${goods.gprice }</td>
                        <td>${goods.gnumber }</td>
                        <td>
                        <img src="image/${goods.gbigpic }"  style="width: 35px;height: 20px;margin-top:10px">
                        </td>
                        <td class="right_border">
                        <img src="images/update1.png">
                        <a href="goodsquerybyid?gb.gid=${goods.gid }&gb.page=1" target="center">修改</a>
                        <img src="images/delete.png">
                        <a href="deletegoods?gb.gid=${goods.gid }&gb.page=${page}" onclick="return confirm('删除商品，确认删除吗？')">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                  
                </table>
               <div class="fanye">[${page } / ${count }]<a href="goodsquerypage?gb.page=1">首页</a>&nbsp;
               <c:if test="${page>1 }">
               	<a href="goodsquerypage?gb.page=${page-1 }">上一页</a>&nbsp;
               </c:if>
               <c:if test="${page<count }">
               <a href="goodsquerypage?gb.page=${page+1 }">下一页</a>&nbsp;
               </c:if>
                
                <a href="goodsquerypage?gb.page=${count }">尾页</a></div>
           </section>
        </section>

</main>
</body>
</html>