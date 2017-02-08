package com.netbook.categary.dao.factory;

/**
 * 工厂
 */
import com.netbook.categary.dao.BookDao;
import com.netbook.categary.dao.CategaryDao;
import com.netbook.categary.dao.impl.BookDaoImpl;
import com.netbook.categary.dao.impl.CategaryDaoImpl;

public abstract class DaoFactory {
	public static CategaryDao getCategaryDao() {
		return new CategaryDaoImpl();
	}

	public static BookDao getBookDao() {
		return new BookDaoImpl();
	}
}
