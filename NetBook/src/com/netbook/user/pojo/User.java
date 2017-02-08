/**NetBook
   2016年12月29日
   User
       创建人：xiayin
 * 
 */
package com.netbook.user.pojo;

/**
 * 用户类
 * 
 * @author xiayin
 *
 */
public class User {
	//用户唯一标识
	private int uid;
	//用户名
	private String loginname;
	//用户密码
	private String loginpass;
	//邮箱
	private String email;
	//状态 0.在线 1.离线
	private int status;
	//激活码
	private String activationCode;
	
	public User(){};
	
	public User(String loginname, String loginpass) {
		this.loginname = loginname;
		this.loginpass = loginpass;
	}

	public User(String loginname, String loginpass, String email) {
		this.loginname = loginname;
		this.loginpass = loginpass;
		this.email = email;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getLoginname() {
		return loginname;
	}
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	public String getLoginpass() {
		return loginpass;
	}
	
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getActivationCode() {
		return activationCode;
	}
	
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname
				+ ", loginpass=" + loginpass + ", email=" + email + ", status="
				+ status + ", activationCode=" + activationCode + "]";
	}
	
	


}
