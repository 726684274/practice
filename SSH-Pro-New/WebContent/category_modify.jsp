<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>易购——category_add.css</title>
    <meta charset="utf-8">
    <link type="text/css" href="css/category_add.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
    <script type="text/javascript">
    $(function(){
    	
    	 $.ajax({
 		   type:'post',
 		   url:'querysupaction1',
 		   dataType:'json',
 		   success:function(data){
 		    //清空，在选择大分类之后，对应小分类不重复
 		    $("#bigClass").html('<option value=-1>请选择</option>');
 		   
 		    //遍历小分类
 		    $.each($.parseJSON(data),function(index , map){
 		    	$("#bigClass").append('<option value="'+map.id+'">'+map.name+'</option>');
 		    })
 		    //大分类信息回显
 		   $("#bigClass").val($("#supid").val()); 
 		    }
    	
 		}); 
    	//$("#bigClass").val($("#supid").val());
    })
    </script>
</head>
<body>
<main>
 <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>添加小分类</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;${SUMSG}</span></li>
                </ul>
            </section>
            <div class="line"></div>
            <section class="right_middle">
            <form action="updatesub" method="post">
                <table style="width: 700px;">
                    <tr>
                        <td class="text">商品类别名称（*）：</td>
                        <td>
                        <input type="text" class="GG" name="subname" value="${scb.subname }" onblur="checkClass()"></td>
                        <td id="result"></td>
                    </tr>
                    <tr>
                        <td class="text">所属大分类：</td>
                         <td><select  id="bigClass" name="supid">
                          <option value="${scb.supid }" id="supid">请选择</option>
                       <%--  <c:forEach items="${ls }" var="sup">
                         	<option value="${sup.supid }">${sup.supname }</option>
                        </c:forEach> --%> 
                         
                         </select></td>
                         <td id="result1"><input type="hidden" class="GG" name="subid" value="${scb.subid }"></td>
                         <td id="result1"> <input type="hidden" class="GG" id="supid" name="supidhidden" value="${scb.supid }"></td>
                   
                    </tr>
                    <tr><td colspan="2" class="right_bottom"><input type="image" src="images/submit.gif" ></td><td style="width: 100px;"></td></tr>
                </table>
               </form> 
            </section>
        </section>

</main>
<!-- <script type="text/javascript">

	function checkClass(){
		var b=false;
		var smallClass=$("[name='smallClass']").val();
		if(smallClass!=""){
			var datr={"smallClass":smallClass};
			$.ajax({
				url:"ajaxCategorySecond?r="+Math.random(),
				data:datr,
				datatype:"text",
				type:"post",
				async:false,
				success:function(data){
					if(data=='error'){
						$("#result").html("该小分类已存在");
						$("#result").css("fontSize","10px");
						b=false;
					}else{
						$("#result").html("√");
						b=true;
					}
				},error:function(data,status){
					alert(status);
				}
			});
		}else{
			$("#result").html("请输入小分类");
			$("#result").css("fontSize","10px");
			b=false;
		}
		return b;
	}
	function checkSelect(){
		var value=$("[name='bigClass']").find("option");
		var b=false;
		$.each(value,function(index,dom){
			if($(dom).prop("selected")&&index!=0){
				b=true;
			}
			
		});
		if(!b){
			$("#result1").html("请选择大分类");
			$("#result1").css("fontSize","10px");
		}else{
			$("#result1").html("√");
			$("#result1").css("fontSize","10px");
		}
		return b;
	}
	function checkAll(){
		return checkSelect()&&checkClass();
	}
</script> -->
</body>

</html>