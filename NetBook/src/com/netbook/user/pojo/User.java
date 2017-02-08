/**NetBook
   2016��12��29��
   User
       �����ˣ�xiayin
 * 
 */
package com.netbook.user.pojo;

/**
 * �û���
 * 
 * @author xiayin
 *
 */
public class User {
	//�û�Ψһ��ʶ
	private int uid;
	//�û���
	private String loginname;
	//�û�����
	private String loginpass;
	//����
	private String email;
	//״̬ 0.���� 1.����
	private int status;
	//������
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
