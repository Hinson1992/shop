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
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/left.css'/>">
<script language="javascript">
var bar = new Q6MenuBar("bar", "肖大胖网上商城");
window.onload=function(){
	bar.colorStyle = 4;
	bar.config.imgDir = "<c:url value='/menu/img/'/>";
	bar.config.radioButton=true;
	/* for(var key in ${requestScope.map.keySet()}){
		for(var value in ${requestScope.map.get(key)}){
			bar.add("${key.cname}", "${value.cname}", "/NetBook/jsps/book/list.jsp", "body");
		}
	} */
	<c:forEach items="${requestScope.map.keySet()}" var="fcate">
    	<c:forEach items="${requestScope.map.get(fcate)}" var="scate">
    		bar.add("${fcate.cname}", "${scate.cname}", "book.do?n=list&cid=${scate.cid}", "body");
      	</c:forEach>
	</c:forEach>
	
	$("#menu").html(bar.toString());
}
</script>
</head>
  
<body>  
  <div id="menu"></div>
</body>
</html>
