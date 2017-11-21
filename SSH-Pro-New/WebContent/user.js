function checkUser(){
		var uservalue=document.getElementById('username').value;
		var spanobj=document.getElementById('userMSG');
		//正则表达式
		var regex=new RegExp(/^[a-zA-z]\w{5,}$/ig);
		//正则判断
		if(regex.test(uservalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='用户名必须为6位以上的数字和字母,并且以字母开头';
			spanobj.style='color:red';
			return false;
		}
	}
	function checkPwd(){
		var pwdvalue=document.getElementById('pwd').value;
		var spanobj=document.getElementById('pwdMSG');
		//正则表达式
		var regex=new RegExp(/^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,}$/ig);
		//正则判断
		if(regex.test(pwdvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='密码必须包含字母和数字,长度最少6位';
			spanobj.style='color:red';
			return false;
		}
	}
	function checkRepwd(){
		var repwdvalue=document.getElementById('repwd').value;
		var spanobj=document.getElementById('repwdMSG');
		//正则表达式
		var regex=new RegExp(/^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{6,}$/ig);
		//正则判断
		if(regex.test(repwdvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='两次密码必须一致';
			spanobj.style='color:red';
			return false;
		}
	}
	function checkReal(){
		var realvalue=document.getElementById('realname').value;
		var spanobj=document.getElementById('realMSG');
		//正则表达式
		var regex=new RegExp(/^[\u4e00-\u9fa5]{2,}$/ig);
		//正则判断
		if(regex.test(realvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='用户真实姓名必须为汉字,且长度最少两位';
			spanobj.style='color:red';
			return false;
		}
	}
	function checkBirthday(){
		var birthvalue=document.getElementById('birthday').value;
		var spanobj=document.getElementById('birthMSG');
		//正则表达式
		var regex=new RegExp(/^([0-9]{4})-(0?[1-9]|1[0-2])-(0?[1-9]|1[0-9]|2[0-9]|3[0-1])$/ig);
		//正则判断
		if(regex.test(birthvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='出生日期格式:yyyy-mm-dd';
			spanobj.style='color:red';
			return false;
		}
	}
	function checkUser_id(){
		var useridvalue=document.getElementById('user_id').value;
		var spanobj=document.getElementById('user_idMSG');
		//正则表达式
		var regex=new RegExp(/^\d{18}$|^\d{17}(\d|X|x)$/ig);
		//正则判断
		if(regex.test(useridvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='身份证号必须为18位数';
			spanobj.style='color:red';
			return false;
		}
	} 
	function checkEmail(){
		var emailvalue=document.getElementById('email').value;
		var spanobj=document.getElementById('emailMSG');
		//正则表达式
		var regex=new RegExp(/^([a-zA-Z0-9]{1,})@([a-z0-9]{2,3})(\.[a-z]{2,3})$/ig);
		//正则判断
		if(regex.test(emailvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='邮箱中必须包含@符号和.'; 
			spanobj.style='color:red';
			return false;
		}
	} 
	function checkPhone(){
		var phonevalue=document.getElementById('phone').value;
		var spanobj=document.getElementById('phoneMSG');
		//正则表达式
		var regex=new RegExp(/^[1][3,5,7,8][0-9]{9}$/ig);
		//正则判断
		if(regex.test(phonevalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='电话呢必须11位数，并且以13578开头'; 
			spanobj.style='color:red';
			return false;
		}
	} 
	function checkAddress(){
		var addressvalue=document.getElementById('address').value;
		var spanobj=document.getElementById('addressMSG');
		//正则表达式
		var regex=new RegExp(/^\S$/ig);
		//正则判断
		if(regex.test(addressvalue)){
			//输入信息符合正则判断
			spanobj.innerHTML='√';
			spanobj.style='color:green';
			return true;
		}else{
			//输入信息符合正则判断
			spanobj.innerHTML='地址不能为空'; 
			spanobj.style='color:red';
			return false;
		}
	} 