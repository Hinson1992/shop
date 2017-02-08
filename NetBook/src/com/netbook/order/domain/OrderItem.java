package com.netbook.order.domain;

import com.netbook.book.domain.Book;

/**
 * 订单条目
 * @author Administrator
 *
 */
public class OrderItem {
	//订单条目的编号
	private String orderItemId; 
	//小计
	private int  quantity;
	//价格
	private double subtotal;
	//书的编号
	private Book bid;
	//书的名字
	private String bname;
	//书的当前价格
	private double currPrice;
	//图片的路径
	private String image_b;
	//所属订单
	private Order oid;

	public OrderItem() {
		super();
	}

	public OrderItem(String orderItemId, int quantity, double subtotal, Book bid,
			String bname, double currPrice, String image_b, Order oid) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.bid = bid;
		this.bname = bname;
		this.currPrice = currPrice;
		this.image_b = image_b;
		this.oid = oid;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Book getBid() {
		return bid;
	}

	public void setBid(Book bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public String getImage_b() {
		return image_b;
	}

	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}

	public Order getOid() {
		return oid;
	}

	public void setOid(Order oid) {
		this.oid = oid;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", quantity="
				+ quantity + ", subtotal=" + subtotal + ", bid=" + bid
				+ ", bname=" + bname + ", currPrice=" + currPrice
				+ ", image_b=" + image_b + ", oid=" + oid + "]";
	}

}
