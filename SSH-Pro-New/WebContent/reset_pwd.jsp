<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
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
            height: 200px;
            margin-left: 170px;
        }
        .main{
            width: 770px;
            height: 300px;
            margin-left: 280px;
            margin-top: 10px;
        }
        .main dl{
            height: 24px;
            border-bottom: 2px solid #fc883b;
        }
        .main dl dt{
            background-image: url("../images/bg.png");
            background-position: -236px -107px;
            width: 13px;
            height: 14px;
            float: left;
            margin-right: 6px;
        }
        .main dl dd{
            line-height: 14px;
            font-weight: bold;
            font-size: 15px;
        }
        .main p{
            height: 60px;
            text-align: center;
            font-size: 28px;
            margin-top: 10px;
            padding-top: 20px;
        }
        .main form table{
            margin-left: 264px;
        }
        .main form table tr td{
            height: 26px;
        }
        .td{
            text-align: center;
        }
        span{
            font-size: 12px;
            line-height: 26px;
        }
        .login{
            background-image: url("../images/submit.gif");
            width: 80px;
            height: 34px;
            border: 0;
        }
        .footer{
            width: 1024px;
            height: 300px;
            margin-left: 170px;
        }
    </style>
</head>
<body>
<iframe class="header" src="header.html"></iframe>
<section class="main">
    <dl>
        <dt></dt>
        <dd>管理首页</dd>
    </dl>
    <p>密码重置</p>
    <form>
        <table>
            <tr>
                <td class="td"><label>用户名：</label></td>
                <td><label><input id="login_name" name="login_name" type="text"/></label></td>
                <td><span id="login_name_span"></span></td>
            </tr>
            <tr>
                <td class="td"><label>真实姓名：</label></td>
                <td><label><input id="name" name="name" type="text"/></label></td>
                <td><span id="name_span"></span></td>
            </tr>
            <tr>
                <td class="td"><label>新密码：</label></td>
                <td><label><input id="pwd" name="pwd" type="password"/></label></td>
                <td><span id="pwd_span"></span></td>
            </tr>
            <tr>
                <td class="td"><label>确认密码：</label></td>
                <td><label><input id="pwd2" name="pwd2" type="password"/></label></td>
                <td><span id="pwd2_span"></span></td>
            </tr>
            <tr>
                <td></td>
                <td><label><input class="login" type="submit" value="提 交"></label></td>
            </tr>
        </table>
    </form>
</section>
<iframe class="footer" src="footer.jsp"></iframe>
</body>
<script type="text/javascript">
    document.getElementById('login_name').onblur = function() {
        var login_name = this.value;
        var login_name_span = document.getElementById('login_name_span');
        var regex = /^[a-zA-z][a-zA-Z0-9]*$/;
        login_name_span.style.color = 'red';
        if (login_name == null || login_name == '') {
            login_name_span.innerHTML = '用户名不能为空';
        } else if (!regex.test(login_name)) {
            login_name_span.innerHTML = '用户名只能包含字母和数字，不能数字开头';
        }
    };
    document.getElementById('login_name').onfocus = function() {
        document.getElementById('login_name_span').innerHTML = '';
    };
    document.getElementById('name').onblur = function() {
        var name = this.value;
        var name_span = document.getElementById('name_span');
        var regex = /^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$/;
        name_span.style.color = 'red';
        if (name == '') {
            name_span.innerHTML = '真实姓名不能为空';
        } else if (!regex.test(name)) {
            name_span.innerHTML = '真实姓名必须为中文';
        }
    };
    document.getElementById('name').onfocus = function() {
        document.getElementById('name_span').innerHTML = '';
    };
    document.getElementById('pwd').onblur = function() {
        var pwd = this.value;
        var pwd_span = document.getElementById('pwd_span');
        pwd_span.style.color = 'red';
        if (pwd == null || pwd == '') {
            pwd_span.innerHTML = '密码不能为空';
        } else if (pwd.length < 6) {
            pwd_span.innerHTML = '密码的长度不能小于6';
        }
    };
    document.getElementById('pwd').onfocus = function() {
        document.getElementById('pwd_span').innerHTML = '';
    };
    document.getElementById('pwd2').onblur = function() {
        var pwd = document.getElementById('pwd').value;
        var pwd2 = this.value;
        var pwd2_span = document.getElementById('pwd2_span');
        pwd2_span.style.color = 'red';
        if (pwd2 == '') {
            pwd2_span.innerHTML = '确认密码不能为空';
        } else if (pwd != pwd2) {
            pwd2_span.innerHTML = '密码不一致';
        }
    };
    document.getElementById('pwd2').onfocus = function() {
        document.getElementById('pwd2_span').innerHTML = '';
    };
</script>
</html>