package com.netbook.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.netbook.user.service.UserService;
/**
 * 用来修改密码的selvlet
 * @author Administrator
 *
 */
public class ModifyPwdServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println(action+"-----");
		if("modifyPage".equals(action)){
			//获取从页面传过来的uid存放在session里面，以便后面的调用
			String uid = request.getParameter("uid");
			session.setAttribute("uid", uid);
			request.getRequestDispatcher("/jsps/user/pwd.jsp").forward(request, response);
		}else if("modify".equals(action)){
			modifyPassword(request, response);
		}else if("valPass".equals(action)){
			valPassword(request, response, (String)(session.getAttribute("uid")));
		}
	}

	/**
	 * 验证密码
	 * @param request
	 * @param response
	 * @param uid
	 * @throws IOException
	 */
	private void valPassword(HttpServletRequest request,
			HttpServletResponse response, String uid) throws IOException {
		String password = request.getParameter("password");
		boolean flag = UserService.valPass(uid, password);
		PrintWriter pw = response.getWriter();
		if(flag){
			pw.println("{'flag':'true'}");
		}else{
			pw.println("{'flag':'false'}");
		}
		pw.flush();
		pw.close();
	}

	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void modifyPassword(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String uid = (String)(request.getSession().getAttribute("uid"));
		String password = request.getParameter("password");
		boolean flag = UserService.modifyPwd(uid, password);
		response.setContentType("text/html");
		System.out.println(flag+"------");
		PrintWriter pw = response.getWriter();
		if(flag){
			pw.println("{'flag':'true'}");
		}else{
			pw.println("{'flag':'false'}");
		}
		pw.flush();
		pw.close();
	}

}
