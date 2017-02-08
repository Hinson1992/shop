/**
 * 项目名:NetBook
   文件名称：RegistServlet.java
   创建人：Administrator
   创建时间：2017年1月1日
 */
package com.netbook.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netbook.user.dao.UserDaoImpl;

/**
 * @author Administrator
 *
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取页面请求参数
		String method = request.getParameter("m");
		if("regist".equalsIgnoreCase(method)){
			UserDaoImpl uImpl = new UserDaoImpl();
			//获取页面输入信息
			String findname = request.getParameter("loginname");
			//得到dao层数据
			int userNum = uImpl.findUser(findname);
			//返回大于0表示用户已存在
			if(userNum>0){
				//设置属性
				request.setAttribute("error", "error_exist");
				//页面跳转
				request.getRequestDispatcher("jsps/user/regist.jsp").forward(request, response);
			//用户不存在
			}else{
				UserDaoImpl newuImpl=new UserDaoImpl();
				//再次获取页面输入信息
				String findname1=request.getParameter("loginname");
			    String addpass=request.getParameter("loginpass");
			    String email=request.getParameter("email");
			    String activationCode=request.getParameter("verifyCode");
			    //得到dao层数据
			    int userNum1=newuImpl.addUser(findname1, addpass, email,activationCode);
			    System.out.println(findname1+addpass+email+activationCode);
			    //定义userNum1来接收sql语句执行结果
			    if(userNum1>0){
			    	request.setAttribute("success", "success_regist");
			    	request.getRequestDispatcher("jsps/user/regist.jsp").forward(request, response);
			    }else{
			    	request.setAttribute("defeat", "defeat_regist");
			    	request.getRequestDispatcher("jsps/user/regist.jsp").forward(request, response);
			    }
			    
			    
			}
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
