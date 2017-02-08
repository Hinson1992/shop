<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
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
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/order/desc.css'/>">
  </head>
  
<body>
	<div class="divOrder">

		<span>订单号：${userList.oid}
	<c:choose>
			  <c:when test="${userList.status==1}">
			  (等待付款)
			  </c:when>
			  <c:when test="${userList.status==2}">
			  (准备发货)
			  </c:when>
			  <c:when test="${userList.status==3}">
			  (等待确认)
			  </c:when>
			  <c:when test="${userList.status==4}">
			  (交易成功)
			  </c:when>
              <c:when test="${userList.status==5}">
			  (已取消)
			  </c:when>
			</c:choose>
		　　　下单时间：${userList.ordertime}</span>
	</div>
	<div class="divContent">
		<div class="div2">
			<dl>
				<dt>收货人信息</dt>
				<dd>${userList.address}</dd>
			</dl>
		</div>
		<div class="div2">
			<dl>
				<dt>商品清单</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名称</th>
							<th class="tt" align="left">单价</th>
							<th class="tt" align="left">数量</th>
							<th class="tt" align="left">小计</th>
						</tr>

                      <c:forEach items="${userList.orderItem}" var="list">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="<c:url value='${list.image_b}'/>"/>
								  <a href="<c:url value='/jsps/book/desc.jsp'/>">${list.bname} </a>
								</div>
							</td>
							<td class="td" >
								<span>&yen;${list.currPrice}</span>
							</td>
							<td class="td">
								<span>${list.quantity}</span>
							</td>
							<td class="td">
								<span>&yen;${list.subtotal}</span>
							</td>			
						</tr>
                     </c:forEach>
					</table>
				</dd>
			</dl>
		</div>
		<div style="margin: 10px 10px 10px 550px;">
			<span style="font-weight: 900; font-size: 15px;">合计金额：</span>
			<span class="price_t">&yen;${userList.total}</span><br/>

	<a href="<c:url value='/jsps/order/pay.jsp'/>" class="pay"></a><br/>
    <a id="cancel" href="javascript:alert('订单已取消！');">取消订单</a><br/>
	<a id="confirm" href="javascript:alert('交易成功！');">确认收货</a><br/>	
		</div>
	</div>
</body>
</html>

