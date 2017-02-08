package com.netbook.cart.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.netbook.book.domain.Book;
import com.netbook.cart.dao.IcartDao;
import com.netbook.cart.domain.CartItem;
import com.netbook.comm.ConnectionPool;


public class CartDaoImpl implements IcartDao {
	/**
	 * 根据用户ID 查询出其购物车的信息
	 * @return
	 */
	public List<CartItem> findCartList(String uid) {
		QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
		List<CartItem> list=null;
		//CartItem cart;
		ResultSetHandler<List<CartItem>> bl=new BeanListHandler<CartItem>(CartItem.class);
		String sql ="select cartItemId,quantity,bid from t_cartitem where uid=? ";
		try {
		list=qr.query(sql, bl,uid);
		
		for(int x=0;x<list.size();x++){
			Book book=null;
			book=getCartBook(list.get(x).getBid());
			if(book!=null){
				list.get(x).setBook(book);
			}	
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据购物ID 删除其购物车的信息
	 * @return
	 */
	public int removeCartList(String cid){
		int row=0;
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			row=qr.update("delete from t_cartitem  where cartItemId=?",cid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
   /* public static void main(String[] args) {
    	new CartDaoImpl().findCartList("123");
	}*/
	/**
	 * 根据购物中的图书ID 得到其图书信息的信息
	 * @return
	 */
	public Book getCartBook(String bid){
		   QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			Book book=null;
			String str="select * from t_book  where  bid=?";
			ResultSetHandler<Book> b2=new BeanHandler<Book>(Book.class);
			try {
				book=qr.query(str, b2,bid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return book;
	}
	/**
	 * 根据客户ID 存储其购物信息
	 * @return
	 */
	public int addCart(CartItem c){
		int row=0;
	    
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			String sql="insert into t_cartitem  values (?,?,?,?,?,?)";
		    row=qr.update(sql,c.getCartItemId(),c.getQuantity(),c.getBid(),c.getUid(),c.getOederBy(),0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
