<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>

    <title>易购——categoryAdd</title>
    <meta charset="utf-8">
    <link type="text/css" href="css/class_add.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.11.0.js"></script>
</head>
<body>
<main>
    
        <section class="right">
            <section class="right_top">
                <ul>
                    <li><img src="images/houtai1.png"></li>
                    <li><span>添加大分类</span></li>
                    <li><span>${SSMG }</span></li>
                </ul>
            </section>
            <div class="line"></div>
            <form action="supaddaction" method="post" id="Add">
            <section class="right_middle">            
                <table>
                    <tr>
                    <td ><td></td></td>
                        <td class="text">分类名称：</td>
                        <td><input type="text" class="GG" name="supname" id="className" onblur="checkClass()" ></td><td ><font class="font1">${SSMG }</font></td>
                 		<td id="result"></td>
                    </tr>
                    <tr>
                    <td></td><td class="right_bottom">
                    <div class="div1"><input type="image" src="images/submit.gif" />
                    </div>
                    </td>
                    
                    </tr>
                </table>            
            </section>
          </form> 
        </section>
	<script type="text/javascript">
		function checkClass(){
			var classNamevalue=document.getElementById('className').value;
		    var spanobj=document.getElementById('smallClassMSG');
			var regex=new RegExp(/^\W{1,}$/ig);
			var b = regex.test(classNamevalue);
			if(b){
				//输入信息符合正则判断
				$("#result").html("√");
				$("#result").css("color","green");
				return true;
			}else{
				//输入信息符合正则判断
				$("#result").html("名称不能为空");
				$("#result").css("color","red");
				return false;
			}
		}
	</script>
</main>
</body>
	
<!--  <script type="text/javascript">
	function checkClass(){
		var b=false;
		var value=$("[name='className']").val();
		if(value==""){
			$("#result").html("分类不能为空");
			$("#result").css("fontSize","10px");
			b=false;
		}else{
			var data={"className":value};
			$.ajax({
				url:"ajaxCategory",
				data:data,
				datatye:"text",
				type:"post",
				async:false,
				success:function(data){
					if(data=='error'){
						$("#result").html("该分类已存在");
						$("#result").css("fontSize","10px");
						$("[name='className']").val("");
						b=false;
					}else{
						$("#result").html("√");
						$("#result").css("fontSize","10px");
						b=true;
					}
				},error:function(data,status){
					alert(status);
				}
			});
		}
		return b;
	}
	function checkAll(){
		return checkClass();
	}
</script>  -->
</html>