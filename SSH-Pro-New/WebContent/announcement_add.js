function checktitile(){
			var titilevalue=document.getElementById('titile').value;
		    var spanobj=document.getElementById('titileMSG');
			var regex=new RegExp(/^\W{1,}$/ig);
			var b = regex.test(titilevalue);
			if(b){
				//输入信息符合正则判断
				spanobj.innerHTML='√';
				spanobj.style='color:green'; 
				return true;
			}else{
				//输入信息符合正则判断
			    spanobj.innerHTML='公告标题不能为空'; 
				spanobj.style='color:red';
				return false;
			}
		}
		function checkinner(){
			var innervalue=document.getElementById('inner').value;
			var spanobj=document.getElementById('innerMSG');
			var regex=new RegExp(/^\W{1,}$/ig);
			if(regex.test(innervalue)){
				//输入信息符合正则判断
			    spanobj.innerHTML='√';
				spanobj.style='color:green'; 
				return true;
			}else{
				//输入信息符合正则判断
				spanobj.innerHTML='公告内容不能为空'; 
				spanobj.style='color:red'; 
				return false;
			}
		}