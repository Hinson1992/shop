<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="adminjsps/css/xgxt_login.css" />
<title>用户登录-商城后台管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/adminjsps/admin/js/book/index.js'/>"></script>

</head>

<body>
	<script type="text/javascript">
		window.onload = function() {
		    if ("${param.error}" == "zhanghaohuomimacuowu") {
				alert("账号或密码错误");
			}
			if ("${param.error}" == "denglucuowu") {
				alert("权限不足，无法登陆");
			}
			if ("${param.error}" == "zhanghaobucunzai") {
				alert("此用户不存在");
			}
		}
		function checkForm() {

			if (document.getElementById("adminname").value == "") {
				document.getElementById("name").innerHTML="请输入账号"
				return false;
			}
			if (document.getElementById("adminpwd").value == "") {
				document.getElementById("pwd").innerHTML="请输入密码"
				return false;
			}
			return true;
		}
	</script>
	<form action="AdministratorServlet?m=login" method="post"
		onsubmit="return checkForm()">
		<div id="header">
			<div class="header_title">
				<span class="title_con">电商后台管理系统</span>
			</div>
		</div>

		<div id="content">
			<center>
				<div class="con">
					<div class="con_title">
						<span class="con_title_sp">欢迎登录电商后台系统</span>
					</div>
					<div class="con_panel">
						<div class="con_input">
							<span>用户名：</span><input type="text" id="adminname"
								placeholder="工号/用户名" name="findName"/><span id="name" style="color:#ff0000;position: absolute;"></span>
						</div>
						<div class="con_input">
							<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password"
								id="adminpwd" placeholder="密码" name="findPwd"/><span id="pwd" style="color:#ff0000;position: absolute;"></span>
						</div>
						<div class="con_select">
							<input type="radio" name="t1" value="订单管理员" id="t1"
								checked="checked" />订单管理员 <input type="radio" name="t1"
								value="仓库管理员" />仓库管理员
						</div>
						<input type="submit" value="登    录" class="submit-btn" />
					</div>
				</div>
			</center>
		</div>

		<div style="text-align:center;"></div>
	</form>
</body>
</html>