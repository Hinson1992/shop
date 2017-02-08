package com.netbook.book.dao.factory;

import com.netbook.book.dao.IBookDao;
import com.netbook.book.dao.impl.BookDapImpl;
import com.netbook.cart.dao.IcartDao;
import com.netbook.cart.dao.impl.CartDaoImpl;

/**
 * bookdao工厂
 * @return
 */
public class BookDaoFactory {
	/**
	 * 返回IbookDao的实现类
	 * @return
	 */
	public static IBookDao getIBookDap()
	{
		return new BookDapImpl();
	}
}
