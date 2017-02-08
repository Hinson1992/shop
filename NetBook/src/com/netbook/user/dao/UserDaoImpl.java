package com.netbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;







import cn.itcast.commons.CommonUtils;

import com.netbook.comm.ConnectionPool;
import com.netbook.user.pojo.User;

public class UserDaoImpl implements UserDao{
	Connection conn=null;
	PreparedStatement st=null;
	ResultSet rs= null;

	@Override
	public int findUser(String findname) {
		String sql="select count(*) count from t_user where loginname = ? ";
		int count=0;
		try {
		  conn=ConnectionPool.getDataSource().getConnection();
		  st=conn.prepareStatement(sql);
		  st.setObject(1,findname);
		  rs=st.executeQuery();
		  if(rs.next()){
			  count=Integer.parseInt(rs.getString("count"));
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}



   public int addUser(String addname,String addpass,String email,String activationCode){
	   String uid = CommonUtils.uuid();
	   String sql="insert into t_user values(?,?,?,?,1,?)";
	   int count=0;
	   try {
		conn=ConnectionPool.getDataSource().getConnection();
		st= conn.prepareStatement(sql);
		st.setObject(1, uid);
		st.setObject(2, addname);
		st.setObject(3, addpass);
		st.setObject(4, email);
		st.setObject(5, activationCode);
		count=st.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return count;   
   }
   
   
   /**
	 * 修改密码
	 *  @return 修改的记录数
	 */
	public int modifyPwd(String uid,String password){
		String sql = "update t_user set loginpass=? where uid=?";
		QueryRunner qr = new QueryRunner(ConnectionPool.getDataSource());
		int count = 0;
		try {
			count = qr.update(sql,password,uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查找密码
	 *  @return 查找的记录数
	 */
	public String userPass(String uid){
		String sql = "select loginpass from t_user where uid=?";
		QueryRunner qr = new QueryRunner(ConnectionPool.getDataSource());
		ResultSetHandler<User> rsh = new BeanHandler<User>(User.class);
		User user = new User();
		try {
			user = qr.query(sql,rsh,uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user.getLoginpass();
	}
}
