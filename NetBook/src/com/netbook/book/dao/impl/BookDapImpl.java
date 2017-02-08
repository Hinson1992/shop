package com.netbook.book.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.netbook.book.dao.IBookDao;
import com.netbook.book.domain.Book;
import com.netbook.cart.domain.CartItem;
import com.netbook.categary.domain.Categary;
import com.netbook.comm.ConnectionPool;



public class BookDapImpl implements IBookDao {
	/**
	 * 根据一级类别查其所有的 2 级类别 
	 * @return
	 */
	public List<Categary> findCategaryList(String pid) {
		QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
		List<Categary> list=null;
		//CartItem cart;
		ResultSetHandler<List<Categary>> bl=new BeanListHandler<Categary>(Categary.class);
		String sql ="select cid,cname from t_category where  pid=? and isdel=0";
		try {
		list=qr.query(sql, bl,pid);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 添加图书；
	 * @return
	 */
    public int addBook(Book b){
    	int row=0;
    
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			String sql="insert into t_book  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		    row=qr.update(sql,b.getBid(),b.getBname(),b.getAuthor(), b.getPrice(),b.getCurrPrice(),b.getDiscount(),b.getPress(),b.getPublishtime(),b.getEdition(),b.getPageNum(),b.getWordNum(),b.getPrinttime(),b.getBooksize(),b.getPaper(),b.getCid(),b.getImage_w(),b.getImage_b(),b.getOrderBy(),0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
    }
    /**
	 * 取得所有图书信息
	 * @return 返回客户信息列表List
	 */
	public List<Book> findBookList(String findbName,String findauthor){
		
		List<Book> bookList=null;
		try {
			//================================================
			String condition="";
			if(findbName!=null && !findbName.equals("")){
				condition+=" and bname like '%"+findbName+"%'";
			}
			if(findauthor!=null && !findauthor.equals("")){
				condition+=" and author like '%"+findauthor+"%'";
			}
			//================================================
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			ResultSetHandler<List<Book>> handler =new BeanListHandler<Book>(Book.class);
			bookList=qr.query("	select * from t_book where isdel=0 "+condition,handler);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	/**
	 * 根据ID 查询书的详细信息
	 * @return
	 */
    public Book findBook(String  bid){
    	QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
		Book book=null;
		//CartItem cart;
		ResultSetHandler<Book> bl=new BeanHandler<Book>(Book.class);
		String sql ="select * from t_book where  bid=? and isdel=0";
		try {
			book=qr.query(sql, bl,bid);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
    }
    /**
	 * 根据ID 删除书本
	 * @return
	 */
    public int romoveBook(String  bid){
    	int row=0;
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			row=qr.update("update t_book set isdel=? where bid=?",1,bid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
    }
    /**
	 * 保存修改后的书本信息
	 * @return
	 */
    public int alterBook(Book b){
    	int row=0;
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			String sql="update t_book set bname=?,currPrice=?,price=?,discount=?,author=?,press=?,publishtime=?,edition=?,pageNum=?,wordNum=?,printtime=?,booksize=?,paper=?,cid=? where bid=?";
			row=qr.update(sql,b.getBname(),b.getCurrPrice(),b.getPrice(),b.getDiscount(),b.getAuthor(),b.getPress(),b.getPublishtime(),b.getEdition(),b.getPageNum(),b.getWordNum(),b.getPrinttime(),b.getBooksize(),b.getPaper(),b.getCid(),b.getBid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
    }
}
