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
    <title>图书详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/desc.css'/>">
	<script src="<c:url value='/jsps/js/book/desc.js'/>"></script>
  </head>
  
  <body>
  <div class="divBookName">${requestScope.book.bname}</div>
  <div>
    <img align="top" src="<c:url value='${requestScope.book.image_w}'/>" class="img_image_w"/>
    <div class="divBookDesc">
	    <ul>
	    	<li>商品编号：${requestScope.book.bid}</li>
	    	<li>当前价：<span class="price_n">&yen;${requestScope.book.currPrice}</span></li>
	    	<li>定价：<span class="spanPrice">&yen;${requestScope.book.price}</span>　折扣：<span style="color: #c30;">${requestScope.book.discount}</span>折</li>
	    </ul>
		<hr class="hr1"/>
		<table>
			<tr>
				<td colspan="3">
					作者：${requestScope.book.author} 著
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：${requestScope.book.press}
				</td>
			</tr>
			<tr>
				<td colspan="3">出版时间：${requestScope.book.publishtime}</td>
			</tr>
			<tr>
				<td>版次：${requestScope.book.edition}</td>
				<td>页数：${requestScope.book.pageNum}</td>
				<td>字数：${requestScope.book.wordNum}</td>
			</tr>
			<tr>
				<td width="180">印刷时间：${requestScope.book.printtime}</td>
				<td>开本：${requestScope.book.booksize} 开</td>
				<td>纸张：${requestScope.book.paper}</td>
			</tr>
		</table>
		<div class="divForm">
			<form id="form1" action="CartServlert.do" method="post">
				<input type="hidden" name="action" value="addcart"/>
				<input type="hidden" name="bid" value="${requestScope.book.bid}"/>
  				我要买：<input id="cnt" style="width: 40px;text-align: center;" type="text" name="quantity" value="1"/>件
  				<input type="submit" value="加入购物车">
  			</form>
  		</div>	
	</div>
  </div>
  </body>
</html>
