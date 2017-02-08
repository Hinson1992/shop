package com.netbook.count.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netbook.count.service.CountService;
/**
 * 用来统计销售情况
 * @author Administrator
 *
 */
public class CountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		String action = request.getParameter("action");
		//System.out.println(action);
		String jsonstr = null;
		if("count".equals(action)){
			String year = request.getParameter("year");
			PrintWriter pw = response.getWriter();
			if(year!=null&&year.length()>0){
				year=year+"-%";
				jsonstr = CountService.getCountJson(year);
			}
			pw.println(jsonstr);
			pw.flush();
			pw.close();
		}
	}

}
