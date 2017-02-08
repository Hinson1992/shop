package com.netbook.order.web;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import com.netbook.bigmal.User;
import com.netbook.cart.domain.Cart;
import com.netbook.cart.domain.CartItem;
import com.netbook.order.domain.Order;
import com.netbook.order.domain.OrderItem;
import com.netbook.order.domain.PageBean;
import com.netbook.order.service.OrderService;
public class OrderServlet extends BaseServlet{
	private OrderService orderService=new OrderService();
	public String addOrder(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		//创建Order对象
		Order order=new Order();
		//从session获取到购物车里面的信息
		Cart cartList=(Cart)request.getSession().getAttribute("cartList");	
		//通过list获取到单价
		//获取到前提发过来的地址
		String address=request.getParameter("address");
		if(address==null||address==""){
			//反回到前台
			order.setAddress("xxxxxxx");
		
		}else{
			order.setAddress(address);
		}		  
		//设置总的价格
		order.settotal(cartList.getTotal());
		//产生订单号
		order.setOid(CommonUtils.uuid());
		Date date=new Date();
		//产生订单时间
		String time=date.toLocaleString();
		order.setOrdertime(time); 
		order.setStatus(1);
		//设置用户的状态
		User user=(User)request.getSession().getAttribute("sessionUser");
		if(user==null){
			 user.setUid("");
		}
		order.setUser(user);
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for(CartItem item:cartList.getCartItems()){
			//订单条目对象 
			OrderItem orderItem=new OrderItem();
			//订单
			orderItem.setOid(order);
			//小计
			orderItem.setSubtotal(item.getSubtotal());
			//订单编号
			orderItem.setOrderItemId(CommonUtils.uuid());
			//订单里面添加书
			orderItem.setBid(item.getBook());
			//设置条目的数量
			orderItem.setQuantity(item.getQuantity());

			orderItemList.add(orderItem);
		}
		order.setOrderItem(orderItemList);
		//存放订单到Service层
		orderService.add(order);	
		cartList.clear();
		//清空购购物车
		Order userList=orderService.OrderDaoFindByUid(null,order.getOid());		   
		request.getSession().setAttribute("userList",userList); 
		
		return "f:/jsps/order/desc.jsp";
	}

	public String searchOrderAll(HttpServletRequest request,HttpServletResponse response){
		//查询所有信息 
		List<Order> orderAll=orderService.OrdderDaoFindByAll();
		request.getSession().setAttribute("listAll", orderAll); 
		return "f:/jsps/order/list.jsp";
	}
    
	public String searchOrderAllForward(HttpServletRequest request,HttpServletResponse response){
		//查询所有信息 
		 String userId= request.getParameter("userId");
		 
		List<Order> orderAll=orderService.OrdderDaoFindByallFarWard(userId);
		request.getSession().setAttribute("listAll", orderAll); 
		return "f:/jsps/order/list.jsp";
	}
	
	
	public String searchUerOrderAll(HttpServletRequest request,HttpServletResponse response){
		String uId=request.getParameter("find");		   
		Order userList=orderService.OrderDaoFindByUid(null, uId);		   
		request.getSession().setAttribute("userList",userList); 
	//System.out.println(userList  +"service*******************");	
		return "f:/jsps/order/desc.jsp";		 	
	}

	public String updateOrderStatus(HttpServletRequest request,HttpServletResponse response){
		String status=request.getParameter("status");
		int  orderStatus=Integer.parseInt(status);
		String m=request.getParameter("m").trim();
		if("cancel".equalsIgnoreCase(m)){
			switch(orderStatus){
			case 2: 
			case 3:	
			case 4:		
			case 5:return "f:/jsps/order/list.jsp"; 
			case 1: 
				System.out.println("cancel");
				orderStatus=5;
				String oid=request.getParameter("status_id");
				System.out.println(oid);				
				orderService.updateOrderStatus(orderStatus,oid);
				List<Order> orderAll=orderService.OrdderDaoFindByAll();
				request.setAttribute("listAll", orderAll); 
				return "f:/jsps/order/list.jsp";
			default:return "f:/jsps/order/list.jsp";
			}
		}else if("affirm".equalsIgnoreCase(m)){
			switch(orderStatus){
			case 1:	
			case 2: 
			case 4:		
			case 5:return "f:/jsps/order/list.jsp"; 
			case 3: 
				orderStatus=4;
				String oid=request.getParameter("status_id");
				System.out.println(oid);				
				orderService.updateOrderStatus(orderStatus,oid);
				List<Order> orderAll=orderService.OrdderDaoFindByAll();
				request.setAttribute("listAll", orderAll); 	
				return "f:/jsps/order/list.jsp";
			default:return "f:/jsps/order/list.jsp";
			}

		}else if("pay".equalsIgnoreCase(m)){
			System.out.println(m);
			switch(orderStatus){
			case 2:	
			case 3: 
			case 4:		
			case 5:return "f:/jsps/order/list.jsp"; 
			case 1: 
				orderStatus=2;
				String oid=request.getParameter("status_id");
				//System.out.println(oid);				
				orderService.updateOrderStatus(orderStatus,oid);
				List<Order> orderAll=orderService.OrdderDaoFindByAll();
				request.setAttribute("listAll", orderAll); 	
				return "f:/jsps/order/list.jsp";
			default:return "f:/jsps/order/list.jsp";
			}
		}else{
			return "f:/jsps/order/list.jsp";
		}

	}	

	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pc = getPc(request);//得到pc
		int ps = 10;//给定ps的值，第页10行记录 
		PageBean<Order> pb = orderService.findAll(pc, ps);//传递pc, ps给Service，得到PageBean
		// 设置url
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);//保存到request域中
		return "f:/jsps/order/list.jsp";//转发到list.jsp
	}

	private int getPc(HttpServletRequest request) {
		String value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(value);
	}

	private String getUrl(HttpServletRequest request) {
		String contextPath = request.getContextPath();//获取项目名
		String servletPath = request.getServletPath();//获取servletPath，即/CustomerServlet
		String queryString = request.getQueryString();//获取问号之后的参数部份
		//  判断参数部份中是否包含pc这个参数，如果包含，需要截取下去，不要这一部份。
		if(queryString.contains("&pc=")) {
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		return contextPath + servletPath + "?" + queryString;
	}
	/**
	 *  后台
	 * @param request  
	 * @param response
	 * @return  "f:/adminjsps/admin/order/list.jsp"
	 */
	public String searchOrderAllBack(HttpServletRequest request,HttpServletResponse response){
		//查询所有信息 
		String currentPage=request.getParameter("currentPage");
		String status=request.getParameter("status");
		Order order=null;
		if(status!=null){
			order=new Order();
			int stus=Integer.parseInt(status);			  
			order.setStatus(stus);
		}else{
			order=new Order();
		}
		//判断当前的状态

		if(currentPage==null){
			PageBean<Order> orderAll=orderService.findAll(1, 5);
			request.getSession().setAttribute("listAll", orderAll); 
		}else{

			int cupage=Integer.parseInt(currentPage);
			System.out.println(cupage);
			PageBean<Order>	 pageOrder=new PageBean();
			pageOrder.setPc(cupage);
			PageBean<Order> orderAll=orderService.query(order,pageOrder.getPc(),5);
			request.getSession().setAttribute("listAll", orderAll); 
		}			
		return "f:/adminjsps/admin/order/list.jsp";
	} 
	/**
	 *  后台的取消
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateOrderStatusBack(HttpServletRequest request,HttpServletResponse response){
		String status=request.getParameter("status");
		int  orderStatus=Integer.parseInt(status);
		String uId=request.getParameter("find");

		int currentPage=1;
		if(uId!=null){
			currentPage=Integer.parseInt(uId);
		}
		if(orderStatus!=2&&orderStatus!=4){

			orderStatus=5;
			String oid=request.getParameter("status_id");
			orderService.updateOrderStatus(orderStatus,oid);	
		}	

		/*List<Order> orderAll=orderService.OrdderDaoFindByAll();*/
		/*Order orderAll=orderService.OrderDaoFindByUid(null, uId);*/
		PageBean<Order> orderAll=orderService.findAll(currentPage,5);
		request.getSession().setAttribute("listAll", orderAll); 
		return "f:/adminjsps/admin/order/list.jsp";	
	}	
	/**
	 *  后台的发货
	 * @param request
	 * @param response
	 * @return f:/adminjsps/admin/order/list.jsp
	 */

	public String updateOrderStatusSendBack(HttpServletRequest request,HttpServletResponse response){
		String status=request.getParameter("status");
		int  orderStatus=Integer.parseInt(status);
		String uId=request.getParameter("find");

		int currentPage=1;
		if(uId!=null){
			currentPage=Integer.parseInt(uId);
		}
		if(orderStatus==2){

			orderStatus=3;
			String oid=request.getParameter("status_id");
			orderService.updateOrderStatus(orderStatus,oid);	
		}	
		PageBean<Order> orderAll=orderService.findAll(currentPage,5);
		request.getSession().setAttribute("listAll", orderAll); 
		return "f:/adminjsps/admin/order/list.jsp";	
	}	

	public String searchUerOrderAllBack(HttpServletRequest request,HttpServletResponse response){
		String uId=request.getParameter("find");		   
		Order userList=orderService.OrderDaoFindByUid(null, uId);		   
		request.getSession().setAttribute("userList",userList); 
		return "f:/adminjsps/admin/order/desc.jsp";		 	
	}
}
