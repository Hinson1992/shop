package com.netbook.bigmal;

public interface Userdao {
	/* 按用户名和密码查询*/
	User findByLoginnameAndLoginpass(String loginname, String loginpwd);
	
	boolean findByUidAndPassword(String uid, String loginpass);
	
	void updatePassword(String uid, String loginpass);
	
	User findByCode(String code);
	
	void updateStatus(String uid, boolean status);
	
	boolean ajaxValidateEmail(String email);
	
	void add(User user);
	
	boolean ajaxValidateLoginname(String loginname);
	
	
	
}
