package com.netbook.book.service;

import java.util.List;

import com.netbook.book.dao.factory.BookDaoFactory;
import com.netbook.book.domain.Book;
import com.netbook.cart.dao.factory.CartDaoFactory;
import com.netbook.cart.domain.CartItem;
import com.netbook.categary.domain.Categary;

public class BookService {
	/**
	 * 根据一级类别查其所有的 2 级类别 
	 * @return
	 */
	public static List<Categary> findCategaryList(String pid){
   	 List<Categary> list =BookDaoFactory.getIBookDap().findCategaryList(pid);
   	 return  list;
    }
	/**
	 * 添加图书；
	 * @return
	 */
    public static boolean addBook(Book book){
    	return BookDaoFactory.getIBookDap().addBook(book)==1?true:false;
    }
    /**
  	 * 取得所有图书信息
  	 * @return 返回客户信息列表List
  	 */
  	public static List<Book> findBookList(String findbName,String findauthor){
  		 return BookDaoFactory.getIBookDap().findBookList(findbName, findauthor);
  	}
  	/**
	 * 根据ID 查询书的详细信息
	 * @return
	 */
    public static  Book findBook(String  bid){
    	return  BookDaoFactory.getIBookDap().findBook(bid);
    }
    /**
   	 * 根据ID 删除书本
   	 * @return
   	 */
      public static boolean  romoveBook(String  bid){
    	  return  BookDaoFactory.getIBookDap().romoveBook(bid)==1?true:false;
      }
      /**
  	 * 保存修改后的书本信息
  	 * @return
  	 */
      public static boolean alterBook(Book book){
    	  return  BookDaoFactory.getIBookDap().alterBook(book)==1?true:false;
      }
}
