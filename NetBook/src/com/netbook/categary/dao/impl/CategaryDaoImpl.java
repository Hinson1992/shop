package com.netbook.categary.dao.impl;

/**
 * 客户数据处理层实现类
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.commons.CommonUtils;

import com.netbook.categary.dao.CategaryDao;
import com.netbook.categary.dao.Sql;
import com.netbook.categary.domain.Categary;
import com.netbook.categary.service.CategaryService;
import com.netbook.comm.ConnectionPool;

public class CategaryDaoImpl implements CategaryDao {
	Connection connection = null;
	Statement statement = null;
	PreparedStatement pStatement = null;
	ResultSet resultSet = null;

	/**
	 * 取得所有一级分类名
	 * 
	 * @return 返回一级分类列表List
	 */
	@Override
	public List<Categary> findFirstCate() {
		List<Categary> fCategary = new ArrayList<Categary>();
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Sql.SQL_QUERY_FIRST_CATE);
			while (resultSet.next()) {
				Categary categary = new Categary();
				categary.setCid(resultSet.getString("cid"));
				categary.setCname(resultSet.getString("cname"));
				categary.setPid(resultSet.getString("pid"));
				categary.setDescription(resultSet.getString("description"));
				categary.setOrderBy(resultSet.getInt("orderBy"));
				fCategary.add(categary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}
		return fCategary;
	}

	/**
	 * 取得所有二级分类名
	 * 
	 * @return 返回二级分类列表List
	 */
	@Override
	public List<Categary> findSecondCate() {
		List<Categary> sCategary = new ArrayList<Categary>();
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Sql.SQL_QUERY_SECOND_CATE);
			while (resultSet.next()) {
				Categary categary = new Categary();
				categary.setCid(resultSet.getString("cid"));
				categary.setCname(resultSet.getString("cname"));
				categary.setPid(resultSet.getString("pid"));
				categary.setDescription(resultSet.getString("description"));
				categary.setOrderBy(resultSet.getInt("orderBy"));
				sCategary.add(categary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}
		return sCategary;
	}

	/**
	 * 取得所有一级分类id
	 * 
	 * @return 返回一级分类id列表List
	 */
	@Override
	public List<String> findFirstCName() {
		List<String> cidList = new ArrayList<String>();
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(Sql.SQL_QUERY_FIRST_CID);
			while (resultSet.next()) {
				cidList.add(resultSet.getString("cid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return cidList;
	}

	/**
	 * 插入一级分类
	 * 
	 * @param 一级分类名
	 *            描述
	 * @return 影响行数
	 */
	@Override
	public int addFirstCate(String cname, String description) {
		CategaryService cService = new CategaryService();
		String cid = cService.maxCid();
		int count = 0;
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_ADD_FIRST_CATE);
			pStatement.setObject(1, cid);
			pStatement.setObject(2, cname);
			pStatement.setObject(3, description);
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}
		// System.out.println(cid);
		return count;
	}

	/**
	 * 插入二级分类
	 * 
	 * @param 二级分类名
	 *            描述
	 * @return 影响行数
	 */
	@Override
	public int addSecondCate(String cname, String pid, String description) {
		String cid = CommonUtils.uuid();
		int count = 0;
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_ADD_SECOND_CATE);
			pStatement.setObject(1, cid);
			pStatement.setObject(2, cname);
			pStatement.setObject(3, pid);
			pStatement.setObject(4, description);
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}
		return count;
	}

	/**
	 * 修改一级分类
	 * 
	 * @param 一级分类名
	 *            ,描述,一级分类id
	 * @return 返回影响行数
	 */
	@Override
	public int updateFirstCate(String cname, String description, String cid) {
		int count = 0;
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_UPDATE_FIRST_CATE);
			pStatement.setObject(1, cname);
			pStatement.setObject(2, description);
			pStatement.setObject(3, cid);
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return count;
	}

	/**
	 * 修改二级分类
	 * 
	 * @param 二级分类名
	 *            ,一级分类id ,描述,二级分类id
	 * @return 返回影响行数
	 */
	@Override
	public int updateSecondCate(String cname, String pid, String description,
			String cid) {
		int count = 0;
		try {
			connection =ConnectionPool.getDataSource().getConnection();
			pStatement = connection
					.prepareStatement(Sql.SQL_UPDATE_SECOND_CATE);
			pStatement.setObject(1, cname);
			pStatement.setObject(2, pid);
			pStatement.setObject(3, description);
			pStatement.setObject(4, cid);
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return count;
	}

	/**
	 * 删除分类
	 * 
	 * @param 分类id
	 * @return 返回影响行数
	 */
	@Override
	public int delCate(String cid) {
		int count = 0;
		try {
			connection =ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_DEL_CATE);
			pStatement.setObject(1, cid);
			count = pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return count;
	}

	/**
	 * 根据类型id查询对应书籍数量
	 * 
	 * @param 二级分类id
	 * @return 书籍数量
	 */
	@Override
	public int findBookNum(String cid) {
		int count = 0;
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_QUERY_BOOK_COUNT);
			pStatement.setObject(1, cid);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				count = Integer.parseInt(resultSet.getString("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return count;
	}

	/**
	 * 根据一级分类id查询对应的二级分类数量
	 * 
	 * @param 一级分类id
	 * @return 二级分类数量
	 */
	@Override
	public int findSCateNum(String cid) {
		int count = 0;
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_QUERY_SCATE_BYID);
			pStatement.setObject(1, cid);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				count = Integer.parseInt(resultSet.getString("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return count;
	}

	/**
	 * 根据一级分类id查询对应的所有二级分类id
	 * 
	 * @param 一级分类id
	 * @return 二级分类id集合
	 */
	@Override
	public List<String> findSCateId(String cid) {
		List<String> list = new ArrayList<String>();
		try {
			connection = ConnectionPool.getDataSource().getConnection();
			pStatement = connection.prepareStatement(Sql.SQL_QUERY_SCATE_ID);
			pStatement.setObject(1, cid);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("cid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionPool.closeConn(connection);
		}

		return list;
	}
}
