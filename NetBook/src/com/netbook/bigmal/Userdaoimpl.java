package com.netbook.bigmal;

import java.sql.SQLException;

import oracle.sql.NUMBER;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class Userdaoimpl implements Userdao {
	private QueryRunner qr = new TxQueryRunner();
	
	@Override
	/**
	 * 按用户名和密码查询
	 * @param loginname
	 * @param loginpass
	 */
	public User findByLoginnameAndLoginpass(String loginname, String loginpass) {
		User user=null;
		String sql = "select * from t_user where loginname=? and loginpass=?";
		try {
			user=qr.query(sql, new BeanHandler<User>(User.class), loginname, loginpass);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	@Override
	/**
	 * 按uid和password查询
	 * @param uid
	 * @param password
	 */
	public boolean findByUidAndPassword(String uid, String loginpass) {
		Number number=0;
		int flag=0;
		String sql= "select count(*) from t_user where uid=? and loginpass=?";
		try {
			number=(Number) qr.query(sql, new ScalarHandler(),uid,loginpass);
			flag=number.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag>0){
			return true;
		}else{
			return false;
		}
			}
	
	@Override
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 */
	public void updatePassword(String uid, String loginpass) {
		String sql = "update t_user set loginpass=? where uid=?";
		try {
			qr.update(sql, loginpass, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	/**
	 * 通过激活码查询用户
	 * @param code
	 */
	public User findByCode(String code) {
		String sql = "select * from t_user where activationCode=?";
		User user=null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	/**
	 * 修改用户状态
	 * @param uid
	 * @param status
	 */
	public void updateStatus(String uid, boolean status) {
		String sql = "update t_user set status=? where uid=?";
		try {
			qr.update(sql, status, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	/**
	 * 校验Email是否注册
	 * @param email
	 */
	public boolean ajaxValidateEmail(String email) {
		String sql = "select count(1) from t_user where email=?";
		Number number=0;
		try {
			number = (Number)qr.query(sql, new ScalarHandler(), email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number.intValue() == 0;
	}
	@Override
	/**
	 * 添加用户
	 * @param user
	 */
	public void add(User user) {
		String sql = "insert into t_user values(?,?,?,?,?,?)";
		Object[] params = {user.getUid(), user.getLoginname(), user.getLoginpass(),
				user.getEmail(), user.isStatus(), user.getActivationCode()};
		       try {
				qr.update(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	/**
	 * 校验用户名是否注册
	 * @param loginname
	 * @return
	 */
	public boolean ajaxValidateLoginname(String loginname) {
		String sql = "select count(1) from t_user where loginname=?";
		Number number=0;
		try {
			number = (Number)qr.query(sql, new ScalarHandler(), loginname);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return number.intValue() == 0;

	}
}
