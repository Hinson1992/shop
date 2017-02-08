package com.netbook.order.domain;

import java.util.List;

import com.netbook.bigmal.User;

/**
 * 订单实体类
 *
 */
public class Order {
	private String oid; //订单编号
	private String ordertime; //订单时间
	private double total;  //数量
	private String address; //地址 
  	private User user;    //用户信息
  	private String uid;
  	private int status;  //订单状态
  	private String image_b;//小图片
  	
  	private List<OrderItem> orderItem;
	public Order() {
		super();
	}
    	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}



	public double getTotal() {
		return total;
	}

    
	public void setTotal(double total) {
		this.total = total;
	}


	public String getImage_b() {
		return image_b;
	}


	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}


	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public Order(String oid, String ordertime, double total, String address,
			User user) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.address = address;
		this.user = user;
	}
    
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public double gettotal() {
		return total;
	}

	public void settotal(double total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}
	

	public void setUser(User  user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total="
				+ total + ", address=" + address + ", user=" + user
				+ ", status=" + status + ", image_b=" + image_b
				+ ", orderItem=" + orderItem + "]";
	}

	
    

}
