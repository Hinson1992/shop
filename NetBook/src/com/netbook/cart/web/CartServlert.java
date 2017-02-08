package com.netbook.cart.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.netbook.bigmal.User;
import com.netbook.book.domain.Book;
import com.netbook.book.util.ParamUtils;
import com.netbook.cart.dao.factory.CartDaoFactory;
import com.netbook.cart.domain.Cart;
import com.netbook.cart.domain.CartItem;
import com.netbook.cart.service.CartItemServise;

public class CartServlert extends HttpServlet {
    
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action= request.getParameter("action");
		User user=(User)request.getSession().getAttribute("sessionUser");
		if("list".equals(action)){
			
			List<CartItem> list=CartItemServise.findCartList(user.getUid());
			request.setAttribute("list", list);
		    request.getRequestDispatcher("jsps/cart/list.jsp").forward(request, response);
			//response.sendRedirect("jsps/cart/list.jsp");	
		}else if("cart".equals(action)){
			String data=request.getParameter("data");
			Cart cart=CartItemServise.getCart(data);
			request.getSession().setAttribute("cartList", cart);
			response.sendRedirect("jsps/cart/showitem.jsp");
			
		}else if("remove".equals(action)){
		  String cartItemId=request.getParameter("cartItemId");
		 // System.out.println(cartItemId);
		  //request.getRequestDispatcher("CartServlert.do?action=list").forward(request, response);
		 if(CartItemServise.removeCartList(cartItemId)){
		  List<CartItem> list=CartItemServise.findCartList(user.getUid());
		  request.setAttribute("list", list);
		  request.getRequestDispatcher("jsps/cart/list.jsp").forward(request, response);
		 }
		}else if("remove_s".equals(action)){
			String cartItemIds=request.getParameter("removes");
			if(CartItemServise.removeCarts(cartItemIds)){
				 List<CartItem> list=CartItemServise.findCartList(user.getUid());
				 request.setAttribute("list", list);
				 request.getRequestDispatcher("jsps/cart/list.jsp").forward(request, response);
			}
		}else if("addcart".equals(action)){
			
			CartItem cart =ParamUtils.getObject(CartItem.class, request);
			cart.setCartItemId(CommonUtils.uuid());
			cart.setOederBy(0);
			cart.setUid(user.getUid());
			if(CartItemServise.addCart(cart)){
				 List<CartItem> list=CartItemServise.findCartList(user.getUid());
				 request.setAttribute("list", list);
				 request.getRequestDispatcher("jsps/cart/list.jsp").forward(request, response);
			}
		}
	
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
			
	}

}
