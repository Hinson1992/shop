<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <meta charset="utf-8" />
		<title></title>
		<script type="text/javascript" src="adminjsps/admin/js/count/count.js" ></script>
		<script type="text/javascript" src="adminjsps/admin/js/count/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="adminjsps/admin/js/count/jscharts.js"></script>
		<script type="text/javascript">
			window.onload = function(){
				//ask();
				var btn = document.getElementById("btn");
				var graph = document.getElementById("graph");
				document.getElementById("year").value = new Date().getFullYear();
				//alert(graph.value);
				ask(year,graph.value);
				btn.onclick = function(){
					var year = document.getElementById("year").value;
					year = new Number(year);
					var date = new Date().getFullYear();
					//如果输入的年份大于系统当前的时间就改为系统当前时间
					if(year>date){
						year=date;
					}
					ask(year,graph.value);	
				};
			};
			</script>
	</head>
	<body>
	<center>
		请输入年份：<input type="text" name="year" id="year"/>
			<select name="graph" id="graph" > 
				<option value="line">线性(单一)</option> 
				<option value="bar">柱形(单一)</option> 
				<option value="pie">饼形</option> 
			</select> 
			<button id="btn" name="btn">确定</button>
	</center>
	
	<div id='canvasDiv' style="width: 100%;" ></div>
	</body>
</html>
