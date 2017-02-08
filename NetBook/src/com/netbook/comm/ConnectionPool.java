package com.netbook.comm;
import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 数据库工具类
 * @author wenber
 *
 */

public  class ConnectionPool {
	//连接池
		private static BasicDataSource data;
		/**
		 * 取得连接池
		 * @return
		 */
		public static BasicDataSource getDataSource(){
			return data;
		}
		static{
			try {
			    data=new BasicDataSource();
				data.setDriverClassName("com.mysql.jdbc.Driver");
				data.setUrl("jdbc:mysql://localhost:3306/goods");
				data.setUsername("root");
				data.setPassword("123456");
		        data.setMaxActive(20);    
		        data.setMaxIdle(3);
		        data.setMaxWait(1000);
		       Connection  conn= data.getConnection(); 
		      
		     System.out.println(conn.getClass().getName());
				
				if(conn!=null){
					System.out.println("连接成功");
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		/**
		 * 关闭数据库连接
		 * @param conn 需要关闭的连接
		 */
		public static void closeConn(Connection conn){
			try {
				if(conn!=null && !conn.isClosed()){
					conn.setAutoCommit(true);
					//conn.setAutoCommit(false)==>conn.commit();
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*public static void main(String[] args) {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			List<Book> list=null;
			ResultSetHandler<List<Book>> bl=new BeanListHandler<Book>(Book.class);
			String sql ="select * from t_book t,( select bid from t_cartitem where uid='123' and cartStatus=0) c where  t.bid=c.bid";
			
			try {
			list=qr.query(sql, bl);
			System.out.println(list.size());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}*/
}

