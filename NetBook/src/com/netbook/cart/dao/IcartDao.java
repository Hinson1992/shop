package com.netbook.cart.dao;

import java.util.List;

import com.netbook.book.domain.Book;
import com.netbook.cart.domain.CartItem;
/**
 * 购物车数据处理层
 * @return
 */
public interface IcartDao {
	/**
	 * 根据用户ID 查询出其购物车的信息
	 * @return
	 */
public List<CartItem> findCartList(String uid);
/**
 * 根据购物ID 删除其购物车的信息
 * @return
 */
public int removeCartList(String cid);
/**
 * 根据购物中的图书ID 得到其图书信息的信息
 * @return
 */
public Book getCartBook(String bid);
/**
 *  存储购物信息
 * @return
 */
public int addCart(CartItem c);
}
