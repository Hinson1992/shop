package com.netbook.bigmal;


public class Userservice {
	private Userdaoimpl userdaoimpl = new Userdaoimpl();
	/**
	 * 登录功能
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userdaoimpl.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
	}
	/*
	 * 校验用户名
	 */
	public boolean ajaxValidateLoginname(String loginname) {		
		 return userdaoimpl.ajaxValidateLoginname(loginname);
	}
	
}
	

