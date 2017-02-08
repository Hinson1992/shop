<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function _go() {
		var pc = $("#pageCode").val();//获取文本框中的当前页码
		var pageCount=new Number("${listAll.tp}");
		var count=new Number(pc);
		if(/^[1-9]\d*$/.test(pc)) {//对当前页码进行整数校验
			if(pc<=pageCount)
			location = "order/OrderServlet?method=searchOrderAllBack&currentPage="+pc; 
			return;
		}	
	 }
	 $(function(){
		//前一页
		var currentPage=new Number("${listAll.pc}");  //当前页面
		var pageCount=new Number("${listAll.tp}");
		$("#next").click(function(){
			if(currentPage>0&&currentPage<pageCount){
				currentPage=currentPage+1;
			}else{
				currentPage=pageCount;
			}
			location = "order/OrderServlet?method=searchOrderAllBack&currentPage="+currentPage; 
		});	
		$("#prv_1").click(function(){
			if(currentPage>1&&currentPage<=pageCount){
				currentPage=currentPage-1;
			}else{
				currentPage=1;
			}
			location = "order/OrderServlet?method=searchOrderAllBack&currentPage="+currentPage; 
	});	
});	
</script>


<div class="divBody">
	<div class="divContent">
		<%--上一页 --%>
	<!-- 	<span class="spanBtnDisabled" id="prv">上一页</span>  -->
		<a href="javascript:void(0)" id="prv_1" class="aBtn bold">上一页</a>
		<%-- 计算begin和end --%>
		<%-- 如果总页数<=6，那么显示所有页码，即begin=1 end=${pb.tp} --%>
		<%-- 设置begin=当前页码-2，end=当前页码+3 --%>
		<%-- 如果begin<1，那么让begin=1 end=6 --%>
		<%-- 如果end>最大页，那么begin=最大页-5 end=最大页 --%>


		<%-- 显示页码列表 --%>
		<c:if test="${listAll.tp<2}">
		</c:if>
		<c:choose>
		  <c:when test="${listAll.tp<2}"></c:when>
		  <c:when test="${listAll.tp<3}"></c:when>	
		  <c:when test="${listAll.tp<4}"></c:when>	
		  <c:when test="${listAll.tp<5}"></c:when>	
		  <c:when test="${listAll.tp<6}"></c:when>	
		  <c:when test="${listAll.tp<7}"></c:when>			
		</c:choose>
	     <span class="spanBtnSelect">1</span>
		 <a href="order/OrderServlet?method=searchOrderAllBack&currentPage=2" class="aBtn">2</a> 
		 <a href="order/OrderServlet?method=searchOrderAllBack&currentPage=3" class="aBtn">3</a> 
		 <a href="order/OrderServlet?method=searchOrderAllBack&currentPage=4"  class="aBtn">4</a>
		 <a href="order/OrderServlet?method=searchOrderAllBack&currentPage=5" class="aBtn">5</a> 
		 <a href="order/OrderServlet?method=searchOrderAllBack&currentPage=6" class="aBtn">6</a>
		<%-- 显示点点点 --%>
		<span class="spanApostrophe">...</span>


		<%--下一页 --%>
		<!-- <span class="spanBtnDisabled" id="next">下一页</span>  -->
		<a href="javascript:void(0)" class="aBtn bold" id="next">下一页</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		<%-- 共N页 到M页 --%>
		<span>共${listAll.tp}页</span> <span>到</span> <input type="text"
			class="inputPageCode" id="pageCode" value="${listAll.pc}" /> <span>页</span> <a
			href="javascript:_go();" class="aSubmit">确定</a>
	</div>
</div>