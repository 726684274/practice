<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<html>
  <head>
    <meta charset='utf-8'>
    
    <title>易购</title>
<link type="text/css" href="css/index.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
  </head>
  
  <body>
   <main class="left">
        <table>
            <tr class="Text" >
                <td style="width:190px;" colspan="3" onclick="none_block('tr1')">用户管理</td>
            </tr>
          <tr class="tex" id="tr1"  style="display: block">
                <td class="img"></td>
                <td>&nbsp;<span><a href="queryuserpage?page=1" onclick="changeT('添加用户','1')" target="center" >
                	用户管理
                </a></span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;<span><a href="user_add.jsp" onclick="changeT('添加用户','1')" target="center">
                	&nbsp;&nbsp;新增
                </a></span></td>
            </tr>
            <tr class="Text">
                <td colspan="3" onclick="none_block('tr2','tr3')">商品分类管理</td>
            </tr>
            <tr class="tex" id="tr2"  style="display: block">
                <td class="img"></td>
                <td>&nbsp;<span><a href="supquerypage?page=1" onclick="changeT('大分类管理','2')" target="center">
                	大分类管理
                </a></span></td>
                <td>&nbsp;&nbsp;&nbsp;<span><a href="class_add.jsp" onclick="changeT('添加大分类','2')" target="center">
                	新增
                </a></span></td>
            </tr>
            <tr class="tex" id="tr3"  style="display: block">
                <td class="img"></td>
                <td>&nbsp;<span><a href="subquerypage?page=1" onclick="changeT('小分类管理','2')" target="center">
                	小分类管理
                </a></span></td>
                <td>&nbsp;&nbsp;&nbsp;<span><a href="category_add.jsp" onclick="changeT('添加小分类','2')" target="center">
                	新增
                </a></span></td>
            </tr>
            <tr class="Text" onclick="none_block('tr4')" >
                <td colspan="3">订单管理</td>
            </tr>
            <tr class="tex" id="tr4"  style="display: block">
                <td class="img"></td>
                <td>&nbsp;<span><a href="orderqueryquery?page=1" onclick="changeT('订单管理','3')" target="center">
                	订单管理
                </a></span></td>
            <td>&nbsp;&nbsp;&nbsp;<span><a href="order_add.jsp" onclick="changeT('修改订单','3')" target="center">
                	新增
                </a></span></td>
                
            </tr>
            <tr class="Text" onclick="none_block('tr5')">
                <td colspan="3">商品详细管理</td>
            </tr>
            <tr class="tex" id="tr5"  style="display: block">
                <td class="img"></td>
                <td>&nbsp;<span><a href="goodsquerypage?gb.page=1" onclick="changeT('商品详细管理','4')" target="center">商品详细管理</a></span></td>
                <td>&nbsp;&nbsp;<span><a href="goods_add.jsp" onclick="changeT('添加商品详细信息','4')" target="center">新增</a></span></td>
            </tr>
            <tr class="Text"onclick="none_block('tr6')" >
                <td colspan="3">公告管理</td>
            </tr>
            <tr class="tex" id="tr6" style="display: block" >
                <td class="img"></td>
                <td>&nbsp;<span><a href="queryannouncementpage?page=1" onclick="changeT('公告管理','5')" target="center">公告管理</a></span></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;<span><a href="announcement_add.jsp" onclick="changeT('添加公告','5')" target="center">
                	&nbsp;&nbsp;&nbsp;新增
                </a></span></td>
            </tr>
        </table>
</main>
  </body>
  <script type="text/javascript">
	  function changeT(t,num){
	 	 $(window.parent.frames["head"]).contents().find("#positio").html(t);
	   	 	var conl= $(window.parent.frames["head"]).contents().find("li");
			for(var i=0;i<conl.length;i++){	
				if(i==num){
					conl[i].className="indexOn";
				}else{
					conl[i].className="index";
				}
			}
	  }
  	function none_block(id){
  		for(var i=0;i<arguments.length;i++){
  		  	if(document.getElementById(arguments[i]).style.display=="block"){
	  			document.getElementById(arguments[i]).style.display="none";
	  		}else{
	  			document.getElementById(arguments[i]).style.display="block";
	  		}
  		}

  		
  	}
  </script>
</html>
