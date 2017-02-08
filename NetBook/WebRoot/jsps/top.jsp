<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
     <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #15B69A;
		margin: 0px;
		color: #ffffff;
	}
	a {
		text-decoration:none;
		color: #ffffff;
		font-weight: 900;
	} 
	a:hover {
		text-decoration: underline;
		color: #ffffff;
		font-weight: 900;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">肖大胖网上商城系统</h1>
<div style="font-size: 10pt; line-height: 10px;">
<c:choose>
	<c:when test="${empty sessionScope.sessionUser }">
	  <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">会员登录</a> |&nbsp; 
		<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册会员</a>
	</c:when>
	<c:otherwise>      会员：${sessionScope.sessionUser.loginname}&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='CartServlert.do?action=list'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='order/OrderServlet?method=searchOrderAllForward&userId=${sessionScope.sessionUser.uid}'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='user.do?action=modifyPage&uid=${sessionScope.sessionUser.uid}'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="http://www.itcast.cn/channel/contact.shtml" target="_top">联系我们</a></c:otherwise>
	</c:choose>

		
		
</div>
  </body>
</html>
