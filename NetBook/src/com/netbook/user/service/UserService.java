package com.netbook.user.service;

import com.netbook.user.dao.UserDao;
import com.netbook.user.dao.UserDaoImpl;
/**
 * 用户业务类
 * @author Administrator
 *
 */
public class UserService {
	public static UserDao userDao = new UserDaoImpl();
	
	/**
	 * 用来用户密码是否修改成功
	 */
	public static boolean modifyPwd(String uid,String password){	
		return userDao.modifyPwd(uid, password)==1?true:false;
	}
	
	/**
	 * 用来判断用户的密码是否输入真确
	 */
	public static boolean valPass(String uid,String password){
		String password1 = userDao.userPass(uid);
		if(password.equals(password1)){
			return true;
		}else{
			return false;	
		}
	}
}
