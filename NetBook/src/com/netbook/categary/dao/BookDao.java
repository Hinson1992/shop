package com.netbook.categary.dao;

import java.util.List;

import com.netbook.categary.domain.Book;

/**
 * 图书接口
 * 
 * @author Administrator
 * 
 */
public interface BookDao {
	/**
	 * 根据类型编号查找所有图书信息
	 * 
	 * @param cid
	 *            类型编号
	 * @return 图书信息
	 */
	public List<Book> findBookBycid(String cid);

	/**
	 * 根据图书名查找所有图书信息
	 * 
	 * @param bname
	 *            图书名
	 * @return 图书信息
	 */
	public List<Book> findBookBybname(String bname);

	/**
	 * 根据图书名,作者,出版社查找所有图书信息
	 * 
	 * @param cname
	 *            图书名,作者,出版社
	 * @return 图书信息
	 */
	public List<Book> findSomeBook(String bname, String author, String press);

	/**
	 * 根据图书编号查找所有图书信息
	 * 
	 * @param bid
	 *            图书编号
	 * @return 图书信息
	 */
	public Book findBookBybid(String bid);
}
