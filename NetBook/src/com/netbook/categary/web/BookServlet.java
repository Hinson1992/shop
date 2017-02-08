package com.netbook.categary.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netbook.categary.dao.factory.DaoFactory;
import com.netbook.categary.domain.Book;
import com.netbook.categary.domain.Categary;

public class BookServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("n");
		// System.out.println("======" + method);
		if ("left".equalsIgnoreCase(method)) {
			Map<Categary, List<Categary>> map = new CategaryServlet().getMap();
			request.setAttribute("map", map);
			request.getRequestDispatcher("jsps/left.jsp").forward(request,
					response);
		} else if ("list".equalsIgnoreCase(method)) {
			String cid = request.getParameter("cid");
			List<Book> books = DaoFactory.getBookDao().findBookBycid(cid);
			// System.out.println(books.get(0).getImage_b());
			request.setAttribute("books", books);
			request.getRequestDispatcher("jsps/book/list.jsp").forward(request,
					response);
		} else if ("search".equalsIgnoreCase(method)) {
			String bname = request.getParameter("bname");
			// System.out.println(bname + ":" + method);
			List<Book> books = DaoFactory.getBookDao().findBookBybname(bname);
			request.setAttribute("books", books);
			request.getRequestDispatcher("jsps/book/list.jsp").forward(request,
					response);
		} else if ("search2".equalsIgnoreCase(method)) {
			String bname = request.getParameter("bname");
			String author = request.getParameter("author");
			String press = request.getParameter("press");
			List<Book> books = DaoFactory.getBookDao().findSomeBook(bname,
					author, press);
			request.setAttribute("books", books);
			request.getRequestDispatcher("jsps/book/list.jsp").forward(request,
					response);
		} else if ("desc".equalsIgnoreCase(method)) {
			String bid = request.getParameter("bid");
			// System.out.println(method + ":" + bid);
			Book book = DaoFactory.getBookDao().findBookBybid(bid);
			request.setAttribute("book", book);
			request.getRequestDispatcher("jsps/book/desc.jsp").forward(request,
					response);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
