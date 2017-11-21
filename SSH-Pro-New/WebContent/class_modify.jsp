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
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${SMSG }</span></li>
                    <li><span style="color:red">&nbsp;&nbsp;&nbsp;&nbsp;${SSMG }</span></li>
                    
                </ul>
            </section>
            <div class="line"></div>
            <form action="updatesup" method="post" id="Add">
            <section class="right_middle">            
                <table>
                    <tr>
                        <td class="text">分类名称：</td>
                        <td><input type="text" class="GG" name="supname" value="${sb.supname }"></td>
                        <td><input type="hidden" class="GG" name="supid" value="${sb.supid }"></td>
                 		<td id="result"></td>
                    </tr>
                    <tr><td></td><td class="right_bottom"><input type="image" src="images/submit.gif" onclick="return checkAll()"/></td></tr>
                </table>            
            </section>
          </form> 
        </section>

</main>
</body>
<!-- <script type="text/javascript">
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
</script> -->
</html>