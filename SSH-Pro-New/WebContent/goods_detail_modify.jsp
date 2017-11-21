<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>

    <title>易购——goods_detail_add</title>
    <meta charset="utf-8">
    <link type="text/css" href="css/goods_detail_add.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
        <script type="text/javascript">
    $(function(){
    	//回显大分类的值
    	
    		//var supid=$("#bigClass").val();
    		$.ajax({
    		   type:'post',
    		   url:'querysupaction',
    		   dataType:'json',
    		   success:function(data){
    		    //清空，在选择大分类之后，对应小分类不重复
    		    $("#bigClass").html('<option value=-1>请选择</option>');
    		    //遍历小分类
    		    $.each($.parseJSON(data),function(index , map){
    		    	$("#bigClass").append('<option value="'+map.id+'">'+map.name+'</option>');
    		    })
    		    //回显大分类的值
    		    	$("#bigClass").val($("#supid").val());
            	//回显小分类的值
        	    	$("#smallClass").append('<option value="'+$("#subid").val()+'">'+$("#subname").val()+'</option>');
        	    	$("#smallClass").val($("#subid").val());
    		    }
    		});
    	
    	$("#bigClass").change(function(){
    		var supid=$("#bigClass").val();
    		$.ajax({
    		   type:'post',
    		   url:'querysubaction',
    		   dataType:'json',
    		   data:'supid='+supid,
    		   success:function(data){
    		    //清空，在选择大分类之后，对应小分类不重复
    		    $("#smallClass").html('<option value=-1>请选择</option>');
    		    //遍历小分类
    		    $.each($.parseJSON(data),function(index , map){
    		    	$("#smallClass").append('<option value="'+map.subid+'">'+map.subname+'</option>');
    		    	$("#smallClass").val($("#subid").val());
    		    })
    		    }
    		});
    	});
    })
    	
    </script>
    <!-- <script type="text/javascript">
    	$(function(){
    		var b=false;
    		$("[name='smallClass']").blur(function(){
    		var mark=false;
    			var options=$(this).find("option");
    			$.each(options,function(index,dom){
    				if($(dom).prop("selected")&&$(dom).val()!=-1){
    					mark=true;
    				}
    			});
    			if(mark){
    				$("#resultSmallClass").html("√");
    				b=true;
    			}else{
    				    $("#resultSmallClass").html("请选择分类");
	    				$("#resultSmallClass").css("fontSize","10px");
	    				b=false;
    			}
    		});
    		var b1=false;
    		$("[name='name']").blur(function(){
    			var newName=$(this).val();
    			if(newName!=""){
    				var rex=new RegExp("[a-z0-9_\u4e00-\u9fa5]{1,}","gi");
    				if(rex.test(newName)){
    					$("#resultName").html("√");
    					b1=true;
    				}else{
	    				$("#resultName").html("请正确输入商品名称");
	    				$("#resultName").css("fontSize","10px");
	    				b1=false;	
    				}
    			}else{
    				$("#resultName").html("商品名称不能为空");
    				$("#resultName").css("fontSize","10px");
    				b1=false;
    			}
    		});
    		var b2=false;
    		$(function(){
    			$("[name='price']").blur(function(){
    				var price=$(this).val();
    				if(price!=""){
	    				 var rex=new RegExp("[0-9]{1,8}\.[0-9]{1,2}");
	    				if(rex.test(price)){
	    					$("#resultPrice").html("√");
	    					b2=true;
	    				}else{
	    				   	$("#resultPrice").html("价格必须带小数点");
	    				   	$("#resultPrice").css("fontSize","10px");
	    					b2=false;
	    				}
    				}else{
    						$("#resultPrice").html("价格不能为空");
    						$("#resultPrice").css("fontSize","10px");
	    					b2=false;
    				}

    			});
    		});
    		var b3=false;
    		$("[name='surplus']").blur(function(){
    			var surplus=$(this).val();
    			if(surplus!=""){
	    			if(!isNaN(surplus)){
	    				if(parseInt(surplus)>0){
		    				$("#resultSurplus").html("√");
		    				b3=true;
		    			}else{
		    			    $("#resultSurplus").html("数量必须大于0");
		    			    $("#resultSurplus").css("fontSize","10px");
		    				b3=false;
		    			}
	    			}else{	    					
		    			    $("#resultSurplus").html("数量必须为数字");
		    			    $("#resultSurplus").css("fontSize","10px");
		    				b3=false;
	    			}

    			}else{
    				    $("#resultSurplus").html("数量不能为空");
    				    $("#resultSurplus").css("fontSize","10px");
	    				b3=false;
    			}

    		});
    		$("#submit").click(function(){
    			//获取上传文件的名字
    			var img=$("#file").val();
    			if(""==img){
    				$("#resultFile").html("图片不能为空");
				    $("#resultFile").css("fontSize","10px");
				    return false;
    			}else{
    				 var point = img.lastIndexOf(".");  
        			 var type = img.substr(point+1);  
        			 //判断上传文件后缀
        			if(type=="png"||type=="jpg"||type=="gif"){
        				if(b&&b1&&b2&&b3){
            				return true;
            			}else{
            				return false;
            			}
        			}else{
        				$("#resultFile").html("图片后缀只能为png,jpg,gif");
    				    $("#resultFile").css("fontSize","10px");
    				    return false;
        			}
    				
    				
    			}
    				
    				
    		});
    	});
    	//调用小分类下拉列表
    	function smallClassF(value){
    		var smallClass= $("#smallClass");
    		//每次拼装前清空下拉列表
			smallClass.empty();
			smallClass.append("<option value=-1>请选择</option>");
    		if(value!=-1){
    			//通过id查询小分类对应信息
    			 $.post("/yigoumanage/ajaxGoods",
						  {id:value},
						  function(data){
							  if(data!=""){
								  $(data).each(function(){
									  //循环取出小分类数据     获取id   和小分类名字
									  smallClass.append("<option value='"+this.id+"'>"+this.smallClass+"</option>");
								  }); 
							  }
						  },
						  "json");
    		}
    	}
    </script> -->
    <script type="text/javascript">
    </script>
</head>
<body>
<main>
   
        <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>添加商品详细信息</span></li>
                     <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${UGMSC }</span></li>
                </ul>
            </section>
            <div class="line"></div>
            <section class="right_middle">
            <form action="updategoods" method="post" enctype="multipart/form-data"> 
                <table>
                <tr>
                	 	<td class="text"></td>
                        <td></td>
                    	<td></td>
                    </tr>
                    <tr>
                        <td class="text">商品名称：</td>
                        <td><input type="text" class="GG" name="gb.gname" value="${gb.gname }"></td>
                    	<td id="resultName"></td>
                    </tr>
                    <tr>
                        <td class="text">所属分类：</td>
                        <td >大分类<select name="gb.supid" value="${gb.supid }" id="bigClass" onchange="smallClassF(this.value)">
                       		 <option value=-1>请选择</option>
                        	<%-- <c:forEach items="${ls }" var="sup">
                         	 <option value="${sup.supid }">${sup.supname }</option>
                         	</c:forEach> --%>
                           
                        </select>小分类<select name="gb.subid" value="${gb.subid }" id="smallClass">
                       		 <option value=-1>请选择</option>
                        </select>
                        
                        </td>
                        <td id="resultSmallClass"></td>
                    </tr>
                    <tr>
                        <td class="text">价格：</td>
                        <td><input type="text" class="GG" name="gb.gprice" value="${gb.gprice }"></td>
                    	<td id="resultPrice"></td>
                    </tr>
                    <tr>
                        <td class="text">剩余数量：</td>
                        <td><input type="text" class="GG" name="gb.gnumber" value="${gb.gnumber }"></td>
                   		<td id="resultSurplus"></td>
                    </tr>
                    <tr>
                        <td class="text">图片：</td>
                        <td><input type="file" id="file" name="gbigpic" value="${gb.gbigpic }"><span id="resultFile"></span></td>
                    </tr>
                    <tr><td><input type="hidden" class="GG" name="gb.gid" value="${gb.gid }"></td>
                    <td colspan="2" class="right_bottom"><input type="image" id="submit" src="images/submit.gif"></td>
                	<td><input type="hidden" id="supid" value="${gb.supid }"></td>
                	<td><input type="hidden" id="supname" value="${gb.supname }"></td>
                	<td><input type="hidden" id="subname" value="${gb.subname }"></td>
                	<td><input type="hidden" id="subid" value="${gb.subid }"></td>
                	</tr>
                </table>
               </form> 
            </section>
        </section>

</main>
</body>
</html>