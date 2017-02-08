<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		window.onload = function(){
			if("${param.error}"=="update_error"){
				alert("修改一级分类失败");
			}
		}
		
		function checkForm() {
			if(!$("#cname").val()) {
				alert("分类名不能为空！");
				return false;
			}
			if(!$("#desc").val()) {
				alert("分类描述不能为空！");
				return false;
			}
			return true;
		}
	</script>
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h3>修改1级分类</h3>
    <h1></h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="categary.do?m=edit" method="post" onsubmit="return checkForm()">
    	<input type="hidden" name="cid" value=""/>
    	<input type="hidden" name="method" value="editOneLevel"/>
    	分类名称：<input type="text" name="cname" id="cname" value="${requestScope.cname}"/><br/><br/>
    	<p style="position: relative;top: -10px">分类描述：</p><textarea rows="5" cols="50" id="desc" name="desc">${requestScope.description}</textarea><br/><br/>
    	<input type="submit" value="修改分类"/>
    	<input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
  </body>
</html>