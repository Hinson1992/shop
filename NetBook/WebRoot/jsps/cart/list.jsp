<%@ page language="java" import="java.util.*,com.netbook.book.domain.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>cartlist.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script src="<c:url value='/js/round.js'/>"></script>
	
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/list.css'/>">
<script type="text/javascript">
var boo=true;//用来控制结算跳转
$(function() {
	showTotal();//显示合计
	// 给全选按钮添加点击事件
	$("#selectAll").click(function() {
		var flag = $(this).attr("checked");//获取全选的状态
		if(flag==true){
		boo=true;
		}else{
		boo=false;
		}
		setAll(flag);//让所有条目复选框与全选同步
		setJieSuanStyle(flag);//让结算按钮与全选同步
	});
	
	// 给条目复选框添加事件
	$(":checkbox[name=checkboxBtn]").click(function() {
		var selectedCount = $(":checkbox[name=checkboxBtn][checked=true]").length;//被勾选复选框个数
		var allCount = $(":checkbox[name=checkboxBtn]").length;//所有条目复选框个数
		if(selectedCount == allCount) {//全选了
			$("#selectAll").attr("checked", true);//勾选全选复选框
			setJieSuanStyle(true);//使结算按钮可用
			boo=true;
		} else if(selectedCount == 0) {//全撤消了
			$("#selectAll").attr("checked", false);//撤消全选复选框
			setJieSuanStyle(false);//使结算按钮不可用
			boo=false;			
		} else {//未全选
			$("#selectAll").attr("checked", false);//撤消全选复选框
			setJieSuanStyle(true);//使结算按钮可用
			boo=true;
		}
		showTotal();//重新计算合计
	}); 
	
	// 给jia、jian添加事件
	$(".jian").click(function() {
		var cartItemId = $(this).attr("id").substring(0, 1);
		var quantity = Number($("#" + cartItemId + "Quantity").val());
		var name=$(this).parent().prev().prev().prev().prev().text();
		if(quantity == 1) {
			if(confirm("您是否真要删除该条目？")) {
			location.href="CartServlert.do?action=remove&cartItemId="+name.trim();
				//alert("删除成功！"+name);
				//alert("${requestScope.list[0].book.bname}");			
			}
		} else {
			sendUpdate(cartItemId, quantity-1);
		}
	});
	$(".jia").click(function() {
		var cartItemId = $(this).attr("id").substring(0, 1);
		var quantity = Number($("#" + cartItemId + "Quantity").val());
		sendUpdate(cartItemId, quantity+1);
	});
});

// 异步请求，修改数量
function sendUpdate(cartItemId, quantity) {
	/*
	 1. 通过cartItemId找到输入框元素
	 2. 通过cartItemId找到小计元素
	*/
	var input = $("#" + cartItemId + "Quantity");
	var subtotal = $("#" + cartItemId + "Subtotal");
	var currPrice = $("#" + cartItemId + "CurrPrice");

	input.val(quantity);
	subtotal.text(round(currPrice.text() * quantity, 2));
	showTotal();
}

// 设置所有条目复选框
function setAll(flag) {
	$(":checkbox[name=checkboxBtn]").attr("checked", flag);//让所有条目的复选框与参数flag同步
	showTotal();//重新设置合计
}

// 设置结算按钮的样式
function setJieSuanStyle(flag) {
	if(flag) {// 有效状态
		$("#jiesuan").removeClass("kill").addClass("jiesuan");//切换样式
		$("#jiesuan").unbind("click");//撤消“点击无效”
	} else {// 无效状态
		$("#jiesuan").removeClass("jiesuan").addClass("kill");//切换样式
		$("#jiesuan").click(function() {//使其“点击无效”
			return false;
		});
	}
}

// 显示合计
function showTotal() {
	var total = 0;//创建total，准备累加
	/*
	1. 获取所有被勾选的复选框，遍历之
	*/
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		/*
		2. 通过复选框找到小计
		*/
		var subtotal = Number($("#" + $(this).val() + "Subtotal").text());
		total += subtotal;
	});
	/*
	3. 设置合计
	*/
	$("#total").text(round(total, 2));
}
// 得到勾选的信息
function getCart() {
    if(boo){
	var allStr = "";//创建var，准备累加
	/*
	1. 获取所有被勾选的复选框，遍历之
	*/
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		/*
		2. 通过复选框找到其所有内容
		*/
		var id = $("#" + $(this).val() + "id").text();
		var cid = $("#" + $(this).val() + "cid").text();
		var Quantity = $("#" + $(this).val() + "Quantity").val();
		
		
		
		allStr=allStr+id.trim()+","+Quantity.trim()+","+cid.trim()+"*";
	
		
	});
   // var httpRequest = createHttpRequest();
	location.href="CartServlert.do?action=cart&data="+allStr;
	/* httpRequest.open("post", "CartServlert.do", true);
	httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	httpRequest.send("action=cart&data="+allStr);
		httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState == 4 && httpRequest.status == 200) {
		alert("123"); */
		//location.href="jsps/cart/showitem.jsp";
		
		//}
		}
	
};
// 得到勾选的信息的cid
function removecart() {
 
	var allStr = "";//创建allStr，准备累加
	/*
	1. 获取所有被勾选的复选框，遍历之
	*/
	$(":checkbox[name=checkboxBtn][checked=true]").each(function() {
		/*
		2. 通过复选框找到其所有内容
		*/
		var id = $("#" + $(this).val() + "cid").text();
		allStr=allStr+id.trim()+"*";
	
	});
  
	location.href="CartServlert.do?action=remove_s&removes="+allStr;
}

</script>
  </head>
  <body>

  <c:if test="${requestScope.list==null}">
	<table width="95%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<img align="top" src="<c:url value='/images/icon_empty.png'/>"/>
			</td>
			<td>
				<span class="spanEmpty">您的购物车中暂时没有商品</span>
			</td>
		</tr>
	</table>  
  </c:if>
<br/>
<br/>

<c:if test="${requestScope.list!=null}">
<table width="95%" align="center" cellpadding="0" cellspacing="0">
	<tr align="center" bgcolor="#efeae5">
		<td align="left" width="50px">
			<input type="checkbox" id="selectAll" checked="checked"/><label for="selectAll">全选</label>
		</td>
		
		<td colspan="2">商品名称</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
		<td>操作</td>
	</tr>
    <c:forEach items="${requestScope.list }" var="list" varStatus="st">
	<tr align="center">
	    
		<td align="left">
			<input value="${st.index}" type="checkbox" name="checkboxBtn" checked="checked"/>
		</td>
		<td  hidden="hidden" >
	    <sanp id="${st.index}id" >${list.bid}</span>
	    </td>
	    <td  hidden="hidden" >
	    <sanp id="${st.index}cid" >${list.cartItemId}</span>
	    </td>
		<td align="left" width="70px">
			<a class="linkImage" href="<c:url value='/jsps/book/desc.jsp'/>"><img border="0" width="54" align="top" src="<c:url value='${list.book.image_b}'/>"/></a>
		</td>
		
		<td align="left" width="400px">
		    <a href="<c:url value='/jsps/book/desc.jsp'/>"><span>${list.book.bname}</span></a>
		</td>
		<td><span>&yen;<span class="currPrice" id="${st.index}CurrPrice">${list.book.currPrice}</span></span></td>
		<td>
			<a class="jian" id="${st.index}Jian"></a><input class="quantity" readonly="readonly" id="${st.index}Quantity" type="text" value="${list.quantity}"/><a class="jia" id="${st.index}Jia"></a>
		</td>
		<td width="100px">
			<span class="price_n">&yen;<span class="subTotal" id="${st.index}Subtotal">${list.book.currPrice*list.quantity}</span></span>
		</td>                          
		<td>
			<a href="<c:url value='CartServlert.do?action=remove&cartItemId=${list.cartItemId}'/>">删除</a>
		</td>
	
	</tr>
</c:forEach>



	<tr>
		<td colspan="4" class="tdBatchDelete">
			<a href="javascript:void(0);"  onclick="removecart()" >批量删除</a>
		</td>
		<td colspan="3" align="right" class="tdTotal">
			<span>总计：</span><span class="price_t">&yen;<span id="total"></span></span>
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right">
			<a  href="javascript:void(0);" onclick="getCart()"  id="jiesuan" class="jiesuan"  ></a>
		</td>
	</tr>
</table>
</c:if>
	<form id="form1" action="<c:url value='/jsps/cart/showitem.jsp'/>" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		<input type="hidden" name="method" value="loadCartItems"/>
	</form>


  </body>
</html>