<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>易购——login</title>
	<meta charset='utf-8'>
    <link type="text/css" href="css/login.css" rel="stylesheet">
    <script type="text/javascript" src="jquery-1.11.0.js"></script>
    <style>
    	.YZM{
    	color: blue; 
    	text-decoration: underline;"
    	}
    </style>
    <script type="text/javascript">
    function getNow(){
		var date=new Date();
		var year=date.getFullYear();
		var month=date.getMonth()+1;
		var day=date.getDate();
		var hour=date.getHours();
		var minute=date.getMinutes();
		var second=date.getSeconds();
		var result=year+"年"+month+"月"+day+"日     "+hour+"点"+minute+"分"+second+"秒";
		document.getElementById("timeSpan").innerHTML=result;
		//window.setTimeout("getNow()",1000);
		setInterval("getNow()",1000);
	} 
    window.onload=function(){
    	
    	getNow();
    }
  //点击验证码图片或者'看不清'时触发changeK()方法生成新的验证码  
      function changeK(){
      	var imgLabel=document.getElementById("kaptchaImge");
      	imgLabel.src ="kaptcha.jpg?random="+Math.random();
      	//$('#kaptchaImge').prop('src',"kaptcha.jpg?random="+Math.random());
      } 
     /* //点击验证码图片或者'看不清'时触发changeK()方法生成新的验证码     
      //JQuery写法：
        function changeK(){
        	$('#kaptchaImge').prop('src',"kaptcha.jpg?random="+Math.random());
        }  */
    </script>
</head>
<%--设置导航栏中的时间 --%>
	<%Date date=new Date(); 
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String time=sdf.format(date);
	%>
<%--设置用户名与密码 --%>
	<%-- <%
	String cname="";
	String cpwd="";
	Cookie[] cs=request.getCookies();
	for(int i=0;i<cs.length;i++){
		if(cs[i].getName().equals("cname")){
			cname=cs[i].getValue();
		}
		if(cs[i].getName().equals("cpwd")){
			cpwd=cs[i].getValue();
		}
		
	}
	
	%> --%>
<body>
<header>
    <div class="pic">
    <div>
        <ul>
            <li class="index"><a href="">首页</a></li>
            <li class="yonghu"><a href="">用户</a></li>
            <li class="shopping"><a href="">商品分类</a></li>
            <li class="dingdan"><a href="">订单</a></li>
            <li class="shopX"><a href="">商品详细</a></li>
            <li class="gonggao"><a href="">公告</a></li>
        </ul>
    </div>
    </div>
    <div class="TiShi">管理员您好，今天是<span id="timeSpan"></span>，欢迎来到贝壳•易购商城后台管理系统。</div>
    <div class="address">您现在的位置：<a href="#" target="_parent" > 贝壳易购•商城</a> &gt; 后台管理</div>
</header>
<center>
<div>
	
    <article>
        <section class="top">
        <ul>
            <li></li>
            <li><span>&nbsp;&nbsp;管理首页</span></li>
        </ul></section>
        <div class="line"></div>
        <section class="bottom">
            <section class="top">欢迎登录易购•商城系统</section>
</center>            
            <form action="loginaction" method="post" name="login">
            <section class="middle">
                    <table>
                    
                    	<tr>
                    		<td></td>
                            <td style="color:red"></td>
                        	 <td></td>
                    	</tr>
                        <tr>
                            <td>用户名：</td>
                                                         <%-- cookie.cookiename.value 获取cookiename的值 --%>
                            <td><input type="text" name="name" value="${cookie.cname.value }" ></td>
                        	 <td id="resuName"></td>
                        </tr>
                        <tr>
                            <td>登录密码：</td>
                            <td><input type="password" name="password" value="${cookie.cpwd.value }" ></td>
                        	 <td id="resuPwd"></td>
                        </tr>
                        <tr>
						<td>验证码：</td>
							<td><input type="text" id="identifying" name="identifying"></td>
							<td><img id="kaptchaImge" src="kaptcha.jpg" onclick="changeK()"></td>
							<td><span id="resuImg" onclick="changeK()" class="YZM">看不清,换一张</span></td>
						</tr>
						<tr>
						<td></td>
							<td><font color="red">${YZMMSG }</font></td>
						</tr>
                        <tr>
                        	<td></td>
                            <td><input type="checkbox" name="jzmm" value="Y">&nbsp;&nbsp;是否记住密码</td>
                        </tr>
                       <tr> <td></td> 
                        <td><input type="image" src="images/login.gif" onclick="return checkAll()" ></td> 
                        <td><a href="forget_pwd.jsp"></a> <a href="reset_pwd.jsp"></a></td>
                        </tr>  
                    </table>

            </section>
            </form>
        </section>
    </article>
</div>
<!-- <script type="text/javascript">
    document.getElementById("kaptchaImge").onclick=function(){
    	document.getElementById("kaptchaImge").src="kaptcha.jpg?t="+Math.floor(Math.random()*1000);
    };
	function checkName(){
		var cc=new RegExp("^[a-z][a-z0-9]{5,}$","ig");
		var invalue=document.getElementsByName("userName")[0].value;
		if(cc.test(invalue)){
			document.getElementById("resuName").innerHTML="√".fontcolor("green");
			return true;
		}else{
			document.getElementById("resuName").innerHTML="请正确输入".fontcolor("red");
			return false;
		}
	}
	function checkPwd(){
		var cc=new RegExp("^[0-9a-z_]{6,}$","ig");
		var invalue=document.getElementsByName("pwd")[0].value;
		if(cc.test(invalue)){
			document.getElementById("resuPwd").innerHTML="√".fontcolor("green");
			return true;
		}else{	
			document.getElementById("resuPwd").innerHTML="请正确输入".fontcolor("red");
			return false;
		}
	}
	function checkImage(){
		var invalue=document.getElementById("idenfitying").value;
		if(invalue!=""){
			document.getElementById("resuImg").innerHTML="√".fontcolor("green");
			return true;
		}else{	
			document.getElementById("resuImg").innerHTML="验证码不能为空".fontcolor("red");
			return false;
		}
	}
	function checkAll(){
		if(checkPwd()&&checkName()&&checkImage()){
			return true;
		}else{
			return false;
		}
	}
	function gogo(name){
		document.getElementById(name).innerHTML="";
	}
</script> -->
<footer>
    <div>
        <div class="footer_top">
            <ul>
                <li>友情链接：</li>
                <li><a href="">百度</a>|</li>
                <li><a href="">Google</a>|</li>
                <li><a href="">雅虎</a>|</li>
                <li><a href="">淘宝</a>|</li>
                <li><a href="">拍拍</a>|</li>
                <li><a href="">易趣</a>|</li>
                <li><a href="">当当</a>|</li>
                <li><a href="">京东商城</a>|</li>
                <li><a href="">迅雷</a>|</li>
                <li><a href="">新浪</a>|</li>
                <li><a href="">网易</a>|</li>
                <li><a href="">搜&nbsp;狐</a>|</li>
                <li><a href="">猫扑</a>|</li>
                <li><a href="">开心网</a>|</li>
                <li><a href="">新华网</a>|</li>
                <li><a href="">凤凰网</a></li>
            </ul>
        </div>
        <div class="middle">
            <div class="line2"></div>
            <ul>
                <li>CORYRIGHT•2014-2015北京市贝壳•易购商城有限公司（北京分部） ALL RIGHT RESERVED</li>
                <li>华宇互联（北京分部）研发部</li>
                <li>热线：400-88-1388 Email:service@ibochy.com.cn</li>
                <li>ICP:京ICP备02121211</li>
            </ul>
        </div>
        <div class="footer_bottom">
          <img src="images/img1.gif"><img src="images/img2.gif">
            <img src="images/img3.gif">
            <img src="images/img4.gif">
        </div>
    </div>
</footer>
</body>
</html>