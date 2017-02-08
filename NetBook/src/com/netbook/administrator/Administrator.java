package com.netbook.administrator;

/**
 * 
 * @author 蔡文涛 管理员信息
 */
public class Administrator {
	private int adminId;// 用户ID
	private String adminName;// 用户名
	private String adminPassword;// 用户密码
	private int adminstatus;// 用户权限

	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrator(int adminId, String adminName, String adminPassword,
			int adminstatus) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminstatus = adminstatus;
	}

	

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public int getAdminstatus() {
		return adminstatus;
	}

	public void setAdminstatus(int adminstatus) {
		this.adminstatus = adminstatus;
	}

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", adminName=" + adminName
				+ ", adminPassword=" + adminPassword + ", adminstatus="
				+ adminstatus + "]";
	}

}
