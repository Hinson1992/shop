package com.netbook.order.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//得到方法名
		String methodName=req.getParameter("method");
		System.out.println(methodName);		
		if(methodName==null||methodName.trim().isEmpty()){
			throw new RuntimeException("你还没有给方传递参数  method=?");
		}
		//基于反反射找到对应的类
		Class clz=this.getClass();
		Method method=null;
		try {
			method=clz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("没有找到与之对应的方法"); 
		} 
		if(null==method){
			return ;
		}		
		String result=null;
		try {
			result=(String)method.invoke(this,req,resp);
System.out.println(result);			
			//先分割前面部分和后面部分
			if(result==null){
				return;
			}
			if(result.contains(":")){
				int index=result.indexOf(":");
				String startInfo=result.substring(0,index);
				String endInfo=result.substring((index+1));			   
				if(startInfo.equalsIgnoreCase("f")){
				     req.getRequestDispatcher(endInfo).forward(req, resp);
					
				}else if(startInfo.equalsIgnoreCase("d")){
					resp.sendRedirect(req.getContextPath()+result);
				}else{
					throw new RuntimeException("你指定的操作：" + startInfo + "，当前版本还不支持！");
				}
			}else{
				req.getRequestDispatcher(result).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
