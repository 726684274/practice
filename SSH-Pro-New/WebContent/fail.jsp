<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset='utf-8'>
    <title>易购-成功页面</title>
    
</head>
<body style="margin: 0 auto">
<header  style="margin: 0 auto">
    <iframe src="header.jsp" name="headFrame" id="head" height="160px" frameborder="0" scrolling="no" width="100%"></iframe>
</header>
<div  style="margin: 0 auto;width: 980px">
    <div style="width: 190px;float: left;padding-left:2px;border: 1px solid #cccccc;margin-bottom:30px">
        <iframe src="main_left.jsp" height="300px" frameborder="0" scrolling="no" width="100%"></iframe>
    </div>
    <div style="width: 785px;float: right">
        <iframe src="center.jsp"  frameborder="0" scrolling="no" width="100%" name="center"
                onload="this.height=this.contentWindow.document.documentElement.scrollHeight"> fail</iframe>
    </div>

</div>
<footer  style="margin: 0 auto">
    <iframe src="footer.jsp" height="200px" frameborder="0" scrolling="no" width="100%"></iframe>
</footer>
</body>
</html>