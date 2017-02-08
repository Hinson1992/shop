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
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>">

	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
	<script type="text/javascript">
		window.onload=function(){
			if("${requestScope.error}"=="error_exist"){
				alert("该用户已存在");
			}else if("${requestScope.success}"=="success_regist"){
			    alert("用户注册成功");
			}else if("${requestScope.defeat}"=="defeat_regist"){
			    alert("用户注册失败，请联系管理员");
			}
		}
	</script>
  </head>
  
  <body>
<div id="divMain">
  <div id="divTitle">
    <span id="spanTitle">新用户注册</span>
  </div>
  <div id="divBody">
  <form action="regist.do?m=regist" method="post">
    <table id="tableForm">
      <tr>
        <td class="tdText">用户名：</td>
        <td class="tdInput">
          <input class="inputClass" type="text" name="loginname" id="loginname"/>
        </td>
        <td class="tdError">
          <label class="errorClass" id="loginnameError">用户名不能为空！</label>
        </td>
      </tr>
      <tr>
        <td class="tdText">登录密码：</td>
        <td>
          <input class="inputClass" type="password" name="loginpass" id="loginpass"/>
        </td>
        <td>
          <label class="errorClass" id="loginpassError"></label>
        </td>
      </tr>
      <tr>
        <td class="tdText">确认密码：</td>
        <td>
          <input class="inputClass" type="password" name="reloginpass" id="reloginpass"/>
        </td>
        <td>
          <label class="errorClass" id="reloginpassError"></label>
        </td>
      </tr>
      <tr>
        <td class="tdText">Email：</td>
        <td>
          <input class="inputClass" type="text" name="email" id="email"/>
        </td>
        <td>
          <label class="errorClass" id="emailError"></label>
        </td>
      </tr>
      <!--  <tr>
        <td class="tdText">验证码：</td>
        <td>
          <input class="inputClass" type="text" name="verifyCode" id="verifyCode"/>
        </td>
        <td>
          <label class="errorClass" id="verifyCodeError"></label>
        </td>
      </tr>
      <tr>
        <td></td>
        <td>
          <div id="divVerifyCode"><img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>"/></div>
        </td>
        <td>
          <label><a href="javascript:_hyz()">换一张</a></label>
        </td>
      </tr>-->
      <tr>
        <td></td>
       <%--  <td>
          <input type="image" src="<c:url value='/images/regist1.jpg'/>" id="submitBtn"/>
        </td> --%>
        <td>
          <center><input type="submit" value="提交"></center>
        </td>
      </tr>
    </table>
    </form>
  </div>
</div>
  </body>
</html>
