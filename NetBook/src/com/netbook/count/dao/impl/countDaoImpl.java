package com.netbook.count.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.netbook.comm.ConnectionPool;
import com.netbook.count.dao.CountDao;
import com.netbook.count.pojo.Count;

public class countDaoImpl implements CountDao {
	
	/**
	 * 取得所有交易的数量
	 * @return 返回交易信息列表List
	 */
	@Override
	public List<Count> findCustomerList(String year) {

		List<Count> list=null;
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			String sql = "select bname,countNum from (select bname,count(*) as countNum from (select bname from t_book c,(select t.bid from t_orderitem t,t_order o where t.oid=o.oid and ordertime like ?) s where c.bid=s.bid) as h group by bname) s order by countNum desc limit 0,10";
			ResultSetHandler<List<Count>> handler =new BeanListHandler<Count>(Count.class);
			list = qr.query(sql,handler,year);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		countDaoImpl c = new countDaoImpl();
		System.out.println(c.findCustomerList("2014-01%").size());
	}
}
