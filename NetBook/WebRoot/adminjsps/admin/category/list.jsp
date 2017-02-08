<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>分类列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/category/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>">
	
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		window.onload = function(){
			if("${requestScope.delStatus}"=="success"){
				alert("删除成功!");
			}else if("${requestScope.delStatus}"=="fail"){
				alert("删除失败!");
			}else if("${requestScope.error}"=="error"){
				alert("该类型下还有书籍,删除失败!");
			}else if("${requestScope.delStatus}"=="success2"){
				alert("删除该分类以及之下所有二级分类成功!");
			}else if("${requestScope.error}"=="error2"){
				alert("该类型下二级分类还有书籍,删除失败!");
			}
		}
	</script>
	
  </head>
  
  <body>
    <h2 style="text-align: center;">分类列表</h2>
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<caption class="captionAddOneLevel">
    	  <a href="<c:url value='/adminjsps/admin/category/add.jsp'/>">添加一级分类</a>
    	</caption>
    	<tr class="trTitle">
    		<th>分类名称</th>
    		<th>描述</th>
    		<th>操作</th>
    	</tr>
    	
    	<c:forEach items="${requestScope.map.keySet()}" var="fcate">
    	<tr class="trOneLevel">
    		<td width="200px;">${fcate.cname}</td>
    		<td>${fcate.description}</td>
    		<td width="200px;">
    		  <a href="categary.do?m=list_add2">添加二级分类</a>
    		  <a href="categary.do?m=list_edit&dcid=${fcate.cid}&dname=${fcate.cname}&ddesc=${fcate.description}">修改</a>
    		  <a onclick="return confirm('您是否真要删除该一级分类？')" href="categary.do?m=del&delid=${fcate.cid}">删除</a>
    		</td>
    		<c:forEach items="${requestScope.map.get(fcate)}" var="scate">
    		<tr class="trTwoLevel">
	    		<td>${scate.cname}</td>
	    		<td>${scate.description}</td>
	    		<td width="200px;" align="right">
	    		  <a href="categary.do?m=list_edit2&dcid=${scate.cid}&dname=${scate.cname}&ddesc=${scate.description}&dcid2=${fcate.cid}&dname2=${fcate.cname}">修改</a>
	    		  <a onclick="return confirm('您是否真要删除该二级分类？')" href="categary.do?m=del2&delid2=${scate.cid}">删除</a>
	    		</td>
    		</tr>
      		</c:forEach>
    	</tr>
    	</c:forEach>
    	
    </table>
  </body>
</html>
