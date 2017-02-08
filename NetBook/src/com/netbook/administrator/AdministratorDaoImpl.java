package com.netbook.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;


public class AdministratorDaoImpl implements AdministratorDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public Administrator findAdministrator(String adminname,
			String adminpassword) {
		Administrator admin = new Administrator();

		try {
			int i = 0;
			String sql = "select * from t_admin where adminname=? and adminpassword=?";
			conn = (Connection) ConnectionPool.getDataSource()
					.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adminname);
			ps.setString(2, adminpassword);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin.setAdminId(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("adminname"));
				admin.setAdminPassword(rs.getString("adminpassword"));
				admin.setAdminstatus(rs.getInt("adminstatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(conn);
		}
		return admin;
	}
}