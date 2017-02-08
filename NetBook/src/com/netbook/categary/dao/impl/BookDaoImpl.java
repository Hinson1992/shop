package com.netbook.categary.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.netbook.categary.dao.BookDao;
import com.netbook.categary.dao.Sql;
import com.netbook.categary.domain.Book;
import com.netbook.comm.ConnectionPool;

public class BookDaoImpl implements BookDao {

	Connection connection = null;
	Statement statement = null;
	PreparedStatement pStatement = null;
	ResultSet resultSet = null;

	/**
	 * 根据类型编号查找所有图书信息
	 * 
	 * @param cid
	 *            类型编号
	 * @return 图书信息
	 */
	@Override
	public List<Book> findBookBycid(String cid) {
		List<Book> books = new ArrayList<Book>();
		try {
			connection =ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_QUERY_BOOK_BYCID);
			pStatement.setObject(1, cid);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Book book = new Book();
				book.setBid(resultSet.getString("bid"));
				book.setBname(resultSet.getString("bname"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getDouble("price"));
				book.setCurrPrice(resultSet.getDouble("currPrice"));
				book.setDiscount(resultSet.getDouble("discount"));
				book.setPress(resultSet.getString("press"));
				book.setPublishtime(resultSet.getString("publishtime"));
				book.setEdition(resultSet.getInt("edition"));
				book.setPageNum(resultSet.getInt("pageNum"));
				book.setWordNum(resultSet.getInt("wordNum"));
				book.setPrinttime(resultSet.getString("printtime"));
				book.setBooksize(resultSet.getInt("booksize"));
				book.setPaper(resultSet.getString("paper"));
				book.setImage_w(resultSet.getString("image_w"));
				book.setImage_b(resultSet.getString("image_b"));
				book.setOrderBy(resultSet.getInt("orderBy"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return books;
	}

	/**
	 * 根据图书名查找所有图书信息
	 * 
	 * @param bname
	 *            图书名
	 * @return 图书信息
	 */
	@Override
	public List<Book> findBookBybname(String bname) {
		List<Book> books = new ArrayList<Book>();
		String sql = Sql.SQL_QUERY_BOOK_BYBNAME + " and bname like '%" + bname
				+ "%'";
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = new Book();
				book.setBid(resultSet.getString("bid"));
				book.setBname(resultSet.getString("bname"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getDouble("price"));
				book.setCurrPrice(resultSet.getDouble("currPrice"));
				book.setDiscount(resultSet.getDouble("discount"));
				book.setPress(resultSet.getString("press"));
				book.setPublishtime(resultSet.getString("publishtime"));
				book.setEdition(resultSet.getInt("edition"));
				book.setPageNum(resultSet.getInt("pageNum"));
				book.setWordNum(resultSet.getInt("wordNum"));
				book.setPrinttime(resultSet.getString("printtime"));
				book.setBooksize(resultSet.getInt("booksize"));
				book.setPaper(resultSet.getString("paper"));
				book.setImage_w(resultSet.getString("image_w"));
				book.setImage_b(resultSet.getString("image_b"));
				book.setOrderBy(resultSet.getInt("orderBy"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return books;
	}

	/**
	 * 根据图书名,作者,出版社查找所有图书信息
	 * 
	 * @param cname
	 *            图书名,作者,出版社
	 * @return 图书信息
	 */
	@Override
	public List<Book> findSomeBook(String bname, String author, String press) {
		List<Book> books = new ArrayList<Book>();
		String sql = Sql.SQL_QUERY_BOOK_BYBNAME + " and bname like '%" + bname
				+ "%'" + " and author like '%" + author + "%'"
				+ " and press like '%" + press + "%'";
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Book book = new Book();
				book.setBid(resultSet.getString("bid"));
				book.setBname(resultSet.getString("bname"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getDouble("price"));
				book.setCurrPrice(resultSet.getDouble("currPrice"));
				book.setDiscount(resultSet.getDouble("discount"));
				book.setPress(resultSet.getString("press"));
				book.setPublishtime(resultSet.getString("publishtime"));
				book.setEdition(resultSet.getInt("edition"));
				book.setPageNum(resultSet.getInt("pageNum"));
				book.setWordNum(resultSet.getInt("wordNum"));
				book.setPrinttime(resultSet.getString("printtime"));
				book.setBooksize(resultSet.getInt("booksize"));
				book.setPaper(resultSet.getString("paper"));
				book.setImage_w(resultSet.getString("image_w"));
				book.setImage_b(resultSet.getString("image_b"));
				book.setOrderBy(resultSet.getInt("orderBy"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return books;
	}

	/**
	 * 根据图书编号查找所有图书信息
	 * 
	 * @param bid
	 *            图书编号
	 * @return 图书信息
	 */
	@Override
	public Book findBookBybid(String bid) {
		Book book = new Book();
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_QUERY_BOOK_BYBID);
			pStatement.setObject(1, bid);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				book.setBid(resultSet.getString("bid"));
				book.setBname(resultSet.getString("bname"));
				book.setAuthor(resultSet.getString("author"));
				book.setPrice(resultSet.getDouble("price"));
				book.setCurrPrice(resultSet.getDouble("currPrice"));
				book.setDiscount(resultSet.getDouble("discount"));
				book.setPress(resultSet.getString("press"));
				book.setPublishtime(resultSet.getString("publishtime"));
				book.setEdition(resultSet.getInt("edition"));
				book.setPageNum(resultSet.getInt("pageNum"));
				book.setWordNum(resultSet.getInt("wordNum"));
				book.setPrinttime(resultSet.getString("printtime"));
				book.setBooksize(resultSet.getInt("booksize"));
				book.setPaper(resultSet.getString("paper"));
				book.setImage_w(resultSet.getString("image_w"));
				book.setImage_b(resultSet.getString("image_b"));
				book.setOrderBy(resultSet.getInt("orderBy"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return book;
	}

}
