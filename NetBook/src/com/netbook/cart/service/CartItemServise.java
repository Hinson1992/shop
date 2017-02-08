package com.netbook.cart.service;

import java.util.List;

import com.netbook.book.domain.Book;
import com.netbook.cart.dao.factory.CartDaoFactory;
import com.netbook.cart.domain.Cart;
import com.netbook.cart.domain.CartItem;

public class CartItemServise {
     public static List<CartItem> findCartList(String uid){
    	 List<CartItem> listCart =CartDaoFactory.getIcartDao().findCartList(uid);
    	
    	 return  listCart;
     }
     /**
 	 * 根据购物ID 删除其购物车的信息
 	 * @return
 	 */
 	public static boolean removeCartList(String cid){
 		return CartDaoFactory.getIcartDao().removeCartList(cid)==1?true:false;
 	}
 	/**
 	 * 根据数据  得到购物的信息
 	 * @return
 	 */
 	public static Cart getCart(String data){
 	//return CartDaoFactory.getIcartDao().removeCartList(cid)==1?true:false;
 		String[] splitStr=data.split("[*]");
 		Cart cart=new Cart();
		for(int x=0;x<splitStr.length;x++){
			String splitbook[]=splitStr[x].split(",");
			Book book=null;
			book=CartDaoFactory.getIcartDao().getCartBook(splitbook[0]);
			CartItem cartitem=new CartItem();
			cartitem.setBook(book);
			//System.out.println(cartitem.getBook());
			cartitem.setQuantity(Integer.parseInt(splitbook[1]));
			cartitem.setCartItemId(splitbook[2]);
			cart.add(cartitem);
		}
 	return cart;
 	}
 	 /**
 	 * 根据购物ID 批量删除其购物车的信息
 	 * @return
 	 */
 	public static boolean removeCarts(String data){
 		String[] splitStr=data.split("[*]");
 		int y=0;
 		for(int x=0;x<splitStr.length;x++){
 		 CartDaoFactory.getIcartDao().removeCartList(splitStr[x]);
 			//System.out.println(splitStr[x]);
 		 y++;
 		}
 		if(y==splitStr.length){
 			return true;
 		}else{
 			return false;
 		}
 	}
	/**
	 * 根据客户ID 存储其购物信息
	 * @return
	 */
	public static boolean  addCart(CartItem c){
		return  CartDaoFactory.getIcartDao().addCart(c)==1?true:false;
	}
}
