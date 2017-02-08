package com.netbook.book.dao;

import java.util.List;

import com.netbook.book.domain.Book;
import com.netbook.cart.domain.CartItem;
import com.netbook.categary.domain.Categary;

public interface IBookDao {
	/**
	 * 根据一级类别查其所有的 2 级类别 
	 * @return
	 */
    public List<Categary> findCategaryList(String pid);
    /**
	 * 添加图书；
	 * @return
	 */
    public int addBook(Book book);
    /**
  	 * 取得所有图书信息
  	 * @return 返回客户信息列表List
  	 */
  	public List<Book> findBookList(String findbName,String findauthor);
  	/**
	 * 根据ID 查询书的详细信息
	 * @return
	 */
    public Book findBook(String  bid);
	/**
	 * 根据ID 删除书本
	 * @return
	 */
    public int romoveBook(String  bid);
    /**
	 * 保存修改后的书本信息
	 * @return
	 */
    public int alterBook(Book book);
}
