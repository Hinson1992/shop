package com.netbook.categary.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netbook.categary.dao.factory.DaoFactory;
import com.netbook.categary.domain.Categary;

public class CategaryServlet extends HttpServlet {
	String cidString = null;
	String cid = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = request.getParameter("m");

		if ("list".equalsIgnoreCase(method)) {
			Map<Categary, List<Categary>> map = getMap();
			request.setAttribute("map", map);
			request.getRequestDispatcher("adminjsps/admin/category/list.jsp")
					.forward(request, response);
		} else if ("list_add2".equalsIgnoreCase(method)) {
			Map<Categary, List<Categary>> map = getMap();
			request.setAttribute("map", map);
			request.getRequestDispatcher("adminjsps/admin/category/add2.jsp")
					.forward(request, response);
		} else if ("add".equalsIgnoreCase(method)) {
			String cname = request.getParameter("cname");
			String description = request.getParameter("desc");
			// System.out.println(cname + description);
			int count = DaoFactory.getCategaryDao().addFirstCate(cname,
					description);
			// System.out.println(count);
			if (count > 0) {
				request.getRequestDispatcher("categary.do?m=list").forward(
						request, response);
			} else {
				response.sendRedirect("adminjsps/admin/category/add.jsp?error=insert_error");
			}
		} else if ("add2".equalsIgnoreCase(method)) {
			String cname = request.getParameter("cname");
			String pid = request.getParameter("pid");
			String description = request.getParameter("desc");
			// System.out.println(pid);
			int count = DaoFactory.getCategaryDao().addSecondCate(cname, pid,
					description);
			if (count > 0) {
				request.getRequestDispatcher("categary.do?m=list").forward(
						request, response);
			} else {
				response.sendRedirect("adminjsps/admin/category/add2.jsp?error=insert_error");
			}
		} else if ("list_edit".equalsIgnoreCase(method)) {
			// System.out.println(data);
			
			String cname = request.getParameter("dname");
		   
			String description = request.getParameter("ddesc");
			cidString = request.getParameter("dcid");
			System.out.println(description+":"+cname+":"+cidString);
			//String desc=new String(description.getBytes("ISO-8859-1"),"gb2312");
			//String name = new String(cname.getBytes("ISO-8859-1"),"gb2312");
			String desc=URLDecoder.decode(description, "UTF-8");
			String name =URLDecoder.decode(cname, "UTF-8");
			
			System.out.println(desc+":"+name+":"+cidString);
			request.setAttribute("cname", name);
			request.setAttribute("description", desc);
			request.getRequestDispatcher("adminjsps/admin/category/edit.jsp")
					.forward(request, response);
		} else if ("list_edit2".equalsIgnoreCase(method)) {
			Map<Categary, List<Categary>> map = getMap();
			request.setAttribute("map", map);

			String cname = request.getParameter("dname");
			String description = request.getParameter("ddesc");
			cidString = request.getParameter("dcid");
			String cidString2 = request.getParameter("dcid2");
			String cname2 = request.getParameter("dname2");

			request.setAttribute("cid", cidString2);
			request.setAttribute("cname", cname);
			request.setAttribute("description", description);
			request.setAttribute("cname2", cname2);

			request.getRequestDispatcher("adminjsps/admin/category/edit2.jsp")
					.forward(request, response);
		} else if ("edit".equalsIgnoreCase(method)) {

			String cname = request.getParameter("cname");
			String description = request.getParameter("desc");
			int count = DaoFactory.getCategaryDao().updateFirstCate(cname,
					description, cidString);
			// System.out.println(cidString + ":" + cname + ":" + description);
			if (count > 0) {
				request.getRequestDispatcher("categary.do?m=list").forward(
						request, response);
			} else {
				response.sendRedirect("adminjsps/admin/category/edit.jsp?error=update_error");
			}
		} else if ("edit2".equalsIgnoreCase(method)) {

			String cname = request.getParameter("cname");
			String description = request.getParameter("desc");

			String pid = request.getParameter("pid");

			int count = DaoFactory.getCategaryDao().updateSecondCate(cname,
					pid, description, cidString);
			// System.out.println(cidString + ":" + cname + ":" + description);
			// System.out.println(pid);
			if (count > 0) {
				request.getRequestDispatcher("categary.do?m=list").forward(
						request, response);
			} else {
				response.sendRedirect("adminjsps/admin/category/edit2.jsp?error=update_error");
			}
		} else if ("del2".equalsIgnoreCase(method)) {
			String cid = request.getParameter("delid2");
			delFCate(request, response, cid);
		} else if ("del".equalsIgnoreCase(method)) {
			cid = request.getParameter("delid");
			List<String> list = DaoFactory.getCategaryDao().findSCateId(cid);
			int book_num = 0;
			for (int i = 0; i < list.size(); i++) {
				String cid1 = list.get(i);
				book_num += DaoFactory.getCategaryDao().findBookNum(cid1);
				if (book_num > 0) {
					break;
				}
			}
			// System.out.println("书籍数量:" + book_num);
			if (book_num <= 0) {
				int count = DaoFactory.getCategaryDao().delCate(cid);
				for (int i = 0; i < list.size(); i++) {
					DaoFactory.getCategaryDao().delCate(list.get(i));
				}
				if (count >= 1) {
					Map<Categary, List<Categary>> map = getMap();
					request.setAttribute("map", map);
					request.setAttribute("delStatus", "success2");
					// System.out.println(request.getAttribute("delStatus"));
					request.getRequestDispatcher(
							"adminjsps/admin/category/list.jsp").forward(
							request, response);
				} else {
					Map<Categary, List<Categary>> map = getMap();
					request.setAttribute("map", map);
					request.setAttribute("delStatus", "fail");
					request.getRequestDispatcher(
							"adminjsps/admin/category/list.jsp").forward(
							request, response);
				}
			} else {
				Map<Categary, List<Categary>> map = getMap();
				request.setAttribute("map", map);
				request.setAttribute("error", "error2");
				request.getRequestDispatcher(
						"adminjsps/admin/category/list.jsp").forward(request,
						response);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	// 删除分类
	public void delFCate(HttpServletRequest request,
			HttpServletResponse response, String cid1) throws ServletException,
			IOException {
		int boook_num = DaoFactory.getCategaryDao().findBookNum(cid1);
		if (boook_num <= 0) {
			int count = DaoFactory.getCategaryDao().delCate(cid1);
			if (count > 0) {
				Map<Categary, List<Categary>> map = getMap();
				request.setAttribute("map", map);
				request.setAttribute("delStatus", "success");
				request.getRequestDispatcher(
						"adminjsps/admin/category/list.jsp").forward(request,
						response);
			} else {
				Map<Categary, List<Categary>> map = getMap();
				request.setAttribute("map", map);
				request.setAttribute("delStatus", "fail");
				request.getRequestDispatcher(
						"adminjsps/admin/category/list.jsp").forward(request,
						response);
			}
		} else {
			Map<Categary, List<Categary>> map = getMap();
			request.setAttribute("map", map);
			request.setAttribute("error", "error");
			request.getRequestDispatcher("adminjsps/admin/category/list.jsp")
					.forward(request, response);
		}
	}

	// 得到key为一级分类对象,value为二级分类对象集合的map
	public Map<Categary, List<Categary>> getMap() {
		Map<Categary, List<Categary>> map = new LinkedHashMap<Categary, List<Categary>>();

		List<Categary> fCategary = DaoFactory.getCategaryDao().findFirstCate();
		List<Categary> sCategary = DaoFactory.getCategaryDao().findSecondCate();

		for (Categary fcategary : fCategary) {
			String fid = fcategary.getCid();
			List<Categary> cList = new ArrayList<Categary>();
			for (Categary scategary : sCategary) {
				String sid = scategary.getPid();
				if (fid.equals(sid)) {
					cList.add(scategary);
				}
			}
			map.put(fcategary, cList);
		}
		return map;
	}

}
