<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">
    <title>订单列表</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/list.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
    <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>
  </head>
  
  <body>
<div class="divMain">
	<div class="divTitle">
		<span style="margin-left: 150px;margin-right: 280px;">商品信息</span>
		<span style="margin-left: 40px;margin-right: 38px;">金额</span>
		<span style="margin-left: 50px;margin-right: 40px;">订单状态</span>
		<span style="margin-left: 50px;margin-right: 50px;">操作</span>
	</div>
	<br/>
 	<c:forEach items="${listAll}" var="order"> 
	<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">

		<tr class="tt">
			<td width="320px">订单号：<a  href="<c:url value='/jsps/order/desc.jsp'/>">${order.oid}</a></td>
			<td width="200px">下单时间： ${order.ordertime}	</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr style="padding-top: 10px; padding-bottom: 10px;">
			<td colspan="2">



	<a class="link2" href="<c:url value='${order.image_b}'/>">
	    <img border="0" width="70" src="<c:url value='/book_img/23254532-1_b.jpg'/>"/>
	</a>
	

	<%-- <a class="link2" href="<c:url value='/jsps/book/desc.jsp'/>">
	    <img border="0" width="70" src="<c:url value='/book_img/23254532-1_b.jpg'/>"/>
	</a>
	<a class="link2" href="<c:url value='/jsps/book/desc.jsp'/>">
	    <img border="0" width="70" src="<c:url value='/book_img/23254532-1_b.jpg'/>"/>
	</a> --%>

			</td>
			<td width="115px">
				<span class="price_t">&yen;${order.total}</span>
			</td>
			<td width="142px">
			<c:choose>
			  <c:when test="${order.status==1}">
			  (等待付款)
			  </c:when>
			  <c:when test="${order.status==2}">
			  (准备发货)
			  </c:when>
			  <c:when test="${order.status==3}">
			  (等待确认)
			  </c:when>
			  <c:when test="${order.status==4}">
			  (交易成功)
			  </c:when>
              <c:when test="${order.status==5}">
			  (已取消)
			   </c:when>
			</c:choose>

 
			</td>
			<td>
			<a href="<c:url value='order/OrderServlet?method=searchUerOrderAll&find=${order.oid}'/>">查看</a><br/>
				<a href="<c:url value='/jsps/order/desc.jsp'/>">支付</a><br/>
				<a href="<c:url value='order/OrderServlet?method=updateOrderStatus&m=cancel&status=${order.status}&status_id=${order.oid}'/>">取消</a><br/>						
				<a href="<c:url value='order/OrderServlet?method=updateOrderStatus&m=affirm&status=${order.status}&status_id=${order.oid}'/>">确认收货</a><br/>
			</td>
		</tr>
	</table>
 	</c:forEach> 
	<br/>
</div>
  </body>
</html>
