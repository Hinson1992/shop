/**NetBook
   2016��12��29��
   CustomerDao
       �����ˣ�xiayin
 * 
 */
package com.netbook.user.dao;

import com.netbook.user.pojo.User;

/**
 * @author xiayin
 *
 */
public interface UserDao {
	
	/**
	 * 
	 * 查找用户
	 * @return 用户存在数
	 */
	public int findUser(String loginname);
	
	/**
	 * 
	 * 添加用户
	 * @return 添加用户后用户存在数
	 */
	public int addUser(String addname,String addpass,String email,String activationCode);
	/**
	 * 修改密码
	 *  @return 修改的记录数
	 */
	public int modifyPwd(String uid,String password);
	
	/**
	 * 查找密码
	 *  @return 查找的记录数
	 */
	public String userPass(String uid);
}
