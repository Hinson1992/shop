
/*
 * 校验密码
 */
function validateLoginpass(tag_name) {
	//http://localhost:8080/NetBook/jsps/user/login.jsp
	//$("#loginpassError").css("display", "none");
	var val = tag_name.value;
	var str = "action=valPass&password="+val;
	var tag_error = document.getElementById("loginpassError");
	var error_message = "";
	var regExp = /\s+/g;
	var httpRequest = createHttpRequest1();
	httpRequest.open('POST','user.do',true);
	httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	httpRequest.send(str);
	var flag=false;
	httpRequest.onreadystatechange=function()
	  {
	  if (httpRequest.readyState==4 && httpRequest.status==200)
	    {
		 var text = httpRequest.responseText;
		 var boo = eval("(" + text + ")");
		 if(boo.flag=='true'){
			 flag = true;
		 }
		 if((val == null || val == undefined) || val.length <= 0 || regExp.test(val))
			{
				error_message = "密码不能为空!";
			}else if(flag)
			{
				error_message = "";
			}else{
				error_message = "密码错误";
			}						
		 tag_error.innerText = error_message;
	    }
	  };	
}
	
//验证重置密码
function val_password(tag_password){
	var val = tag_password.value;
	var tag_error = document.getElementById("newpassError");
	var regExp = /\s+/g;
	var error_message = "";
	if((val == null || val == undefined) || val.length <= 0 || regExp.test(val))
	{
		error_message = "密码不能为空!";
	}else{
		regExp.compile(/[a-zA-Z][a-zA-Z0-9]{2,15}/g);
		if(!regExp.test(val)){
			error_message = "以字母、数字组合，必须字母开头，长度为3-16位";
		}				
	}
	tag_error.innerText = error_message;
}
//验证确认密码
function val_repassword(tag_repassword){
	var val = tag_repassword.value;
	var password = document.getElementById("newpass");
	//alert(val==password.value);
	var tag_error = document.getElementById("reloginpassError");
	var regExp = /\s+/g;
	var error_message = "";
	if((val == null || val == undefined) || val.length <= 0 || regExp.test(val))
	{
		error_message = "确认密码不能为空!";
	}else{		
		if(val!=password.value){
			error_message = "两次输入的密码不一致！";
		}				
	}
	tag_error.innerText = error_message;
}

/*
 * 校验验证码
 */
function validateVerifyCode() {
	var bool = true;
	$("#verifyCodeError").css("display", "none");
	var value = $("#verifyCode").val();
	if(!value) {//非空校验
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("验证码不能为空！");
		bool = false;
	} else if(value.length != 4) {//长度不为4就是错误的
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("错误的验证码！");
		bool = false;
	} else {//验证码是否正确
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {method: "ajaxvalidateVerifyCode", verifyCode: value},
			url: "/UserServlet1",
			success: function(flag) {
				if(!flag) {
					$("#verifyCodeError").css("display", "");
					$("#verifyCodeError").text("错误的验证码！");
					bool = false;					
				}else if(flag){
					$("#verifyCodeError").css("display", "");
					$("#verifyCodeError").text("");
				}
			}
		});
	}
	return bool;
}

/**
 *用来验证所填的空不为空,没有错误信息 
 */
function val(){
	var boo = false;
	var loginpassE = document.getElementById("loginpassError");
	var newpassE = document.getElementById("newpassError");
	var repassE = document.getElementById("reloginpassError");
	var codeE = document.getElementById("verifyCodeError");
	//获取输入框的值
	var loginpass = document.getElementById("loginpass");
	var newpass = document.getElementById("newpass");
	var repass = document.getElementById("reloginpass");
	var code = document.getElementById("verifyCode");
	if(loginpass.value.length>0&&newpass.value.length>0&&repass.value.length>0&&code.value.length>0){
		if(loginpassE.innerText==""&&newpassE.innerText==""&&repassE.innerText==""&&validateVerifyCode()){
			boo = true;
		};
	}
	if(boo){
		submit_btn();
	}
}

/**
 * 提交修改密码
 * 
 */
function submit_btn(){
	var newpass = document.getElementById("newpass").value;
	var str = "action=modify&password="+newpass;
	$.ajax({
		type:"post",
		url:"user.do?"+str,
		dateType:"text/html",
		success:function(data,state,xhr){
			if(xhr.readyState==4&&xhr.status==200){
				var statestr = eval("("+data+")");
				if(statestr.flag=='true'){	
					alert("修改成功！");
					//window.location.href="jsps/user/login.jsp";
					//用于框架集的整体跳转页面
					top.document.location="jsps/user/login.jsp";
				}else if(statestr.flag=='false'){
					alert("修改失败！");
				}					
			}	
		}
	});	
}	
/**
 * 创建请求对象
 * @returns {___anonymous4529_4539}
 */
function createHttpRequest1()
{
	var httpRequest = null;
    if(window.XMLHttpRequest)
    {
        httpRequest = new XMLHttpRequest();	
    }else{
    	httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return httpRequest;
}

