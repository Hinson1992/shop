package com.netbook.cart.domain;

import java.math.BigDecimal;

import com.netbook.book.domain.Book;



/**
 * 购物车条目类
 * 
 * 
 */
public class CartItem {
	private Book book;// 商品
	private int quantity;// 数量
    private String uid;// 用户id
    private int oederBy;
    private String cartItemId; //商品的购买id
    private int  cartStatus ; //商品购买状态   0--未购买  1--已购买 
    private String  bid;  //书的id
	

	/**
	 * 小计方法      
	 * @return
	 * 处理了二进制运算误差问题
	 */
	public double getSubtotal() {//小计方法，但它没有对应的成员！
		BigDecimal d1 = new BigDecimal(book.getCurrPrice() + "");
		BigDecimal d2 = new BigDecimal(quantity + "");
		return d1.multiply(d2).doubleValue();
	}
    
	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}
	public int getCartStatus() {
		return cartStatus;
	}


	public void setCartStatus(int cartStatus) {
		this.cartStatus = cartStatus;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public int getOederBy() {
		return oederBy;
	}


	public void setOederBy(int oederBy) {
		this.oederBy = oederBy;
	}


	public String getCartItemId() {
		return cartItemId;
	}


	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}


	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", quantity=" + quantity + ", uid="
				+ uid + ", oederBy=" + oederBy + ", cartItemId=" + cartItemId
				+ ", cartStatus=" + cartStatus + ", bid=" + bid + "]";
	}


}
