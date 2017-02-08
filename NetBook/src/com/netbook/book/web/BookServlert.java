package com.netbook.book.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





















import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
















import cn.itcast.commons.CommonUtils;

import com.alibaba.fastjson.JSON;
import com.netbook.book.domain.Book;
import com.netbook.book.service.BookService;
import com.netbook.book.util.ParamUtils;
import com.netbook.categary.dao.factory.DaoFactory;
import com.netbook.categary.domain.Categary;
import com.netbook.categary.web.CategaryServlet;


public class BookServlert extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String action= request.getParameter("action");
		
		if("book".equals(action)){
		 Book book=getbook(request, response);
		// System.out.println(book);
			 if(BookService.addBook(book)){
				 request.getRequestDispatcher("adminjsps/admin/book/body.jsp").forward(request, response);
			 }
		 
	    }else if("categary".equals(action)){
	    	Map<Categary, List<Categary>> map = getMap();
	    	//request.setAttribute("map", map);
			request.getSession().setAttribute("map",map);
	    	//System.out.println(map);
			request.getRequestDispatcher("adminjsps/admin/book/add.jsp").forward(request, response);
			//response.sendRedirect("adminjsps/admin/book/add.jsp");		
	    }else if("pid".equals(action)){
	      String pid=request.getParameter("pids");
	      List<Categary> list= BookService.findCategaryList(pid);
	     // System.out.println(JSON.toJSONString(list));	
	      response.setContentType("text/json");
		  response.setCharacterEncoding("UTF-8");			
		  PrintWriter writer = response.getWriter();
	       writer.println(JSON.toJSONString(list));
	       writer.flush();
		   writer.close();
	    }else if("find".equals(action)){
	    	String findbName=new String(request.getParameter("bname").getBytes("iso-8859-1"),"UTF-8");
	    	String findauthor=new String(request.getParameter("author").getBytes("iso-8859-1"),"UTF-8");
            System.out.println(findbName+"---"+findauthor);
	    	List<Book> booklist=BookService.findBookList(findbName, findauthor);
	    	System.out.println(booklist);
	    	//存在乱码
	    }else if("a_book".equals(action)){
	    	String bid=request.getParameter("bid");
	    	//String bid="000A18FDB38F470DBE9CD0972BADB23F";
	    	Book book=BookService.findBook(bid);
	    	System.out.println(book);
	    	request.setAttribute("book", book);
	    	Map<Categary, List<Categary>> map = getMap();
	    	request.setAttribute("map", map);
	    	request.getRequestDispatcher("adminjsps/admin/book/desc.jsp").forward(request, response);
	    	
	    }else if("remove".equals(action)){
	    	 String bid=request.getParameter("bid");
	    	 if(BookService.romoveBook(bid)){
	    		 request.getRequestDispatcher("adminjsps/admin/book/body.jsp").forward(request, response);
	    	 }
	    }else if("alter".equals(action)){
	    	Book book=ParamUtils.getObject(Book.class, request);
	    	 System.out.println(book);
	    	 if(BookService.alterBook(book)){
	    		 request.getRequestDispatcher("adminjsps/admin/book/body.jsp").forward(request, response);
	    	 }
	 		
	    }else if ("left".equalsIgnoreCase(action)) {
			Map<Categary, List<Categary>> map = new CategaryServlet().getMap();
			request.setAttribute("map", map);
			request.getRequestDispatcher("adminjsps/admin/book/left.jsp").forward(request,
					response);
		}else if("list".equalsIgnoreCase(action)){
			String cid = request.getParameter("cid");
			List<com.netbook.categary.domain.Book> books = DaoFactory.getBookDao().findBookBycid(cid);
			// System.out.println(books.get(0).getImage_b());
			request.setAttribute("books", books);
			request.getRequestDispatcher("adminjsps/admin/book/list.jsp").forward(request,
					response);
		}

	}
	public Book getbook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		/**
		 * 把数据封装到book中去;
		 * @return
		 */ 
		Book book=null;
		//创建工厂
		DiskFileItemFactory  factory=new DiskFileItemFactory();
		//得到解析器
		ServletFileUpload suf=new ServletFileUpload(factory);
		 try {
			List<FileItem> fileItemlist =suf.parseRequest(request);
			Map<String , String>  map=new HashMap<String,String>();
			for(FileItem list: fileItemlist ){
				 if(list.isFormField()){
					//得到普通字段值 
					 map.put(list.getFieldName(), list.getString("UTF-8"));
				 }
			 }
			 book=CommonUtils.toBean(map, Book.class);
			//System.out.println("1-----"+book);
			//上传文件
			String savepath=this.getServletContext().getRealPath("/book_img");
			String filename_w=fileItemlist.get(1).getName();
			String filename_b=fileItemlist.get(2).getName();
			
			File  desFile_w=new File(savepath,filename_w);
			File  desFile_b=new File(savepath,filename_b);
			fileItemlist.get(1).write(desFile_w);
			fileItemlist.get(2).write(desFile_b);
			book.setImage_b("book_img/"+filename_b);
			book.setImage_w("book_img/"+filename_w);
		    book.setBid(CommonUtils.uuid());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return book;
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
