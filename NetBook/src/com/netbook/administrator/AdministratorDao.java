package com.netbook.administrator;

/**
 * 
 * @author 蔡文涛 管理员数据处理层
 */
public interface AdministratorDao {
	/**
	 * 取得指点定客户信息
	 * 
	 * @return 返回客户信息
	 */
	public Administrator findAdministrator(String adminname,
			String adminpassword);
    
}
