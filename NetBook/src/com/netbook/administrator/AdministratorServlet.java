package com.netbook.administrator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet("/AdministratorServlet")
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdministratorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		AdministratorDaoImpl admin = new AdministratorDaoImpl();

		// 登录的业务流程
		// 1.获取前端输入的数据
		String username = request.getParameter("findName");
		String userpwd = request.getParameter("findPwd");
		// 取得值
		int status = admin.findAdministrator(username, userpwd)
				.getAdminstatus();
		String adminpassword = admin.findAdministrator(username, userpwd)
				.getAdminPassword();
		String adminname = admin.findAdministrator(username, userpwd)
				.getAdminName();
		if (adminname!=null || adminpassword!=null) {
			if (status == 1) {
				response.sendRedirect("adminjsps/index.jsp?error=denglucuowu");
			} else if (status == 2 || status == 3) {
				session.setAttribute("username", adminname);
				session.setAttribute("password", adminpassword);
				request.getRequestDispatcher("adminjsps/admin/main.jsp?panduan=yes")
						.forward(request, response);
			}
		 } else if(adminname ==null || adminpassword ==null){
			    response.sendRedirect("adminjsps/index.jsp?error=zhanghaohuomimacuowu");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,NullPointerException {
		String m = request.getParameter("m");
		if ("login".equals(m)) {
			try {
				login(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}