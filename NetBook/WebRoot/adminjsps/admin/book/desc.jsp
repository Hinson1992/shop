<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>book_desc.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/desc.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

<script type="text/javascript" src="<c:url value='/adminjsps/admin/js/book/desc.js'/>"></script>

<script type="text/javascript">

$(function() {
	$("#box").attr("checked", false);
	$("#formDiv").css("display", "none");
	$("#show").css("display", "");	
	
	// 操作和显示切换
	$("#box").click(function() {
		if($(this).attr("checked")) {
			$("#show").css("display", "none");
			$("#formDiv").css("display", "");
		} else {
			$("#formDiv").css("display", "none");
			$("#show").css("display", "");		
		}
	});
	
	
	$("#publishtime").datepick({dateFormat:"yy-mm-dd"});
	$("#printtime").datepick({dateFormat:"yy-mm-dd"});
	
	
	/* $(".btn").click(function() {
	
	   
	}); */
});
// 用来判断支持不同的浏览器
function createHttpRequest() {
	var httpRequest = null;
	if (window.XMLHttpRequest) {
		httpRequest = new XMLHttpRequest();
	} else {
		httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return httpRequest;
}
function loadChildren(){
    var options=$("#pid option:selected");
    var httpRequest = createHttpRequest();
     httpRequest.open("post", "BookServlert.do?", true);
	 httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	  httpRequest.send("action=pid&pids="+options.val());
		httpRequest.onreadystatechange = function() {
			if (httpRequest.readyState == 4 && httpRequest.status == 200) {
		      var jsonStr = httpRequest.responseText;
		         list = eval("(" + jsonStr + ")");
		         var str="";
		        for (var i=0; i<list.length; i++)
				  {
				    var s="<option value='"+list[i].cid+"' >"+list[i].cname+"</option>";
				    str=str+s;
				  } 
				   var s=$("#cid");
				    s.html(str);

			}
		}
}
function editForm() {
    if(btnclick()){
    alert("123");
	$("#action").attr("value", "alter");
	alert($("#action").val());
	$("#form").submit();
	
	}
}
function delForm() {
  
	$("#action").attr("value", "remove");
    alert($("#action").val());
	$("#form").submit();
	
}
function btnclick() {
	    var bid=$("#bid").val();
		var bname = $("#bname").val();
		var currPrice = $("#currPrice").val();
		var price = $("#price").val();
		var discount = $("#discount").val();
		var author = $("#author").val();
		var press = $("#press").val();
		var pid = $("#pid").val();
		var cid = $("#cid").val();
		//alert(bid+"1-"+bname+"2-"+currPrice+"3-"+price+"4-"+discount+"5-"+author+"6-"+press+"7-"+pid+"8-"+cid+"9-");
		if(!bname || !currPrice || !price || !discount || !author || !press || !pid || !cid || !bid) {
			alert("图名、当前价、定价、折扣、作者、出版社、1级分类、2级分类、都不能为空！");
			return false;
		}else 	if(isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
			alert("当前价、定价、折扣必须是合法小数！");
			return false;
		}else {
		return true;
		}
}
</script>
  </head>
  
  <body>
    <input type="checkbox" id="box"><label for="box">编辑或删除</label>
    <br/>
    <br/>
  <div id="show">
  
    <div class="sm">${requestScope.book.bname}</div>
    <img align="top" src="<c:url value='${requestScope.book.image_w}'/>" class="tp"/>
    <div id="book" style="float:left;">
	    <ul>
	    	<li>商品编号：${requestScope.book.bid}</li>
	    	<li>当前价：<span class="price_n">&yen;${requestScope.book.currPrice}</span></li>
	    	<li>定价：<span style="text-decoration:line-through;">&yen;${requestScope.book.price}</span>　折扣：<span style="color: #c30;">${requestScope.book.discount}</span>折</li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table class="tab">
			<tr>
				<td colspan="3">
					作者：${requestScope.book.author}著
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：${requestScope.book.press}</a>
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
				<td>开本：${requestScope.book.booksize}开</td>
				<td>纸张：${requestScope.book.paper}</td>
			</tr>
		</table>
	</div>
  </div>
  
  
  <div id='formDiv'>
   <div class="sm">&nbsp;</div>
   <form action="BookServlert.do" method="post" id="form" >
   	<input type="hidden" id ="action" name="action" value=""/>
   	<input type="hidden" id="bid" name="bid" value="${requestScope.book.bid}"/>
   	<input type="hidden" name="image_w" value=""/>
   	<input type="hidden" name="image_b" value=""/>
    <img align="top" src="<c:url value='${requestScope.book.image_w}'/>" class="tp"/>
    <div style="float:left;">
	    <ul>
	    	<li>商品编号：${requestScope.book.bid}</li>
	    	<li>书名：　<input id="bname" type="text" name="bname" value="${requestScope.book.bname}" style="width:500px;"/></li>
	    	<li>当前价：<input id="currPrice" type="text" name="currPrice" value="${requestScope.book.currPrice}" style="width:50px;"/></li>
	    	<li>定价：　<input id="price" type="text" name="price" value="${requestScope.book.price}" style="width:50px;"/>
	    	折扣：<input id="discount" type="text" name="discount" value="${requestScope.book.discount}" style="width:30px;"/>折</li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table class="tab">
			<tr>
				<td colspan="3">
					作者：　　<input id="author" type="text" name="author" value="${requestScope.book.author}" style="width:150px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					出版社：　<input id="press" type="text" name="press" value="${requestScope.book.press}" style="width:200px;"/>
				</td>
			</tr>
			<tr>
				<td colspan="3">出版时间：<input id="publishtime" type="text" name="publishtime" value="${requestScope.book.publishtime}" style="width:100px;"/></td>
			</tr>
			<tr>
				<td>版次：　　<input id="edition" type="text" name="edition" value="${requestScope.book.edition}" style="width:40px;"/></td>
				<td>页数：　　<input id="pageNum" type="text" name="pageNum" value="${requestScope.book.pageNum}" style="width:50px;"/></td>
				<td>字数：　　<input id="wordNum" type="text" name="wordNum" value="${requestScope.book.wordNum}" style="width:80px;"/></td>
			</tr>
			<tr>
				<td width="250px">印刷时间：<input id="printtime" type="text" name="printtime" value="${requestScope.book.printtime}" style="width:100px;"/></td>
				<td width="250px">开本：　　<input id="booksize" type="text" name="booksize" value="${requestScope.book.booksize}" style="width:30px;"/></td>
				<td>纸张：　　<input id="paper" type="text" name="paper" value="${requestScope.book.paper}" style="width:80px;"/></td>
			</tr>
			<tr>
				<td>
					一级分类：<select name="pid" id="pid" onchange="loadChildren()">
						<option value="" selected='selected'>==请选择1级分类==</option>
			    			<c:forEach items="${requestScope.map.keySet()}" var="map1" varStatus="st">			
						<option value="${map1.cid}">${map1.cname}</option>
						</c:forEach>

					</select>
				</td>
				<td>
					二级分类：<select name="cid" id="cid">
						<option value="">==请选择2级分类==</option>
					</select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<input onclick="editForm()" type="button" name="method" id="editBtn" class="btn" value="编　　辑">
					<input onclick="delForm()" type="button" name="method" id="delBtn" class="btn" value="删　　除">
				</td>
				<td></td>
			</tr>
		</table>
	</div>
   </form>
  </div>

  </body>
</html>
