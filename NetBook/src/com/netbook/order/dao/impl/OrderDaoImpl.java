package com.netbook.order.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.netbook.comm.ConnectionPool;
import com.netbook.comm.PageUtils;
import com.netbook.order.dao.OrderDao;
import com.netbook.order.domain.Order;
import com.netbook.order.domain.OrderItem;
import com.netbook.order.domain.PageBean;
public class OrderDaoImpl implements OrderDao{
	private QueryRunner qr1= new TxQueryRunner();
	@Override
	public int addOrder(Order order) {
		int rowCount=0;
		/*QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());*/
		System.out.println(order.getOid()+"*"+order.getOrdertime()+"*"+order.gettotal()+"*"+order.getStatus());		
		String sql="insert into t_order(oid,ordertime,total,status,address,uid)  values(?,?,?,?,?,?)";
		Object[] params={order.getOid(),order.getOrdertime(),order.gettotal(),order.getStatus(),
				order.getAddress(),order.getUser().getUid()};
		try {
			rowCount=qr1.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	@Override
	public int addOrderItemList(List<OrderItem> orderItemList) {
		int rowCount=0;
		try {
			/*QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());*/
			
			 

			String sql="insert into t_orderitem(orderItemId,quantity,subtotal,bid,bname,currPrice,image_b,oid)  values(?,?,?,?,?,?,?,?)";
			Object[][] params=new Object[orderItemList.size()][];
			//
			for(int i=0;i<orderItemList.size();i++){
				OrderItem orderItem=orderItemList.get(i);
				params[i]=new Object[]{orderItem.getOrderItemId(),orderItem.getQuantity(),
						orderItem.getSubtotal(),orderItem.getBid().getBid(),orderItem.getBid().getBname(), orderItem.getCurrPrice(),orderItem.getBid().getImage_b(),orderItem.getOid().getOid()
				};	 
			};
			rowCount=qr1.batch(sql, params).length;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rowCount;
	}
	@Override
	public Order OrderDaoFindByUid(String uid,String oid) {
		QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
		String sql="select oid,ordertime,address ,total from t_order where oid=?";
		String uSql="select image_b,currPrice,bname,subtotal,quantity from t_orderitem a,t_order b where b.oid=a.oid and b.oid=?";

		List<OrderItem> orderItem=null;
		ResultSetHandler<List<OrderItem>> bl=new BeanListHandler<OrderItem>(OrderItem.class);
		ResultSetHandler<Order> orderNum=new BeanHandler<Order>(Order.class);
		Order order=new Order();

		try {
			order=qr.query(sql,orderNum,oid);
			orderItem=qr.query(uSql, bl,oid);		
			order.setOrderItem(orderItem);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	@Override
	public List<Order> OrdderDaoFindByAll() {
		QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
		List<Order> orderList=null;
		//String sql="select o.oid,status,ordertime,status,total,image_b from t_orderitem,t_order o";
		String sql="select o.oid,status,ordertime,status,total,item.image_b from t_order o left join (select * from t_orderitem where orderItemId  in (select max(orderItemId) from t_orderitem GROUP BY oid)) item on item.oid=o.oid";
		ResultSetHandler<List<Order>> bl=new BeanListHandler<Order>(Order.class);
		try {
			orderList=qr.query(sql,bl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	} 
	/**
	 * 取得所有客户信息
	 * @return 返回客户信息列表List
	 */
	public List<Order> findOrderList(PageUtils pageUtils,String findName,String findPhone){

		List<Order> cusList=null;
		try {
			//================================================
			String condition="";
			if(findName!=null && !findName.equals("")){
				condition+=" and cus_name like '%"+findName+"%'";
			}
			if(findPhone!=null && !findPhone.equals("")){
				condition+=" and cus_phone like '%"+findPhone+"%'";
			}
			//================================================
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			ResultSetHandler<List<Order>> handler =new BeanListHandler<Order>(Order.class);
			cusList=qr.query("" +
					"select * from ( " +
					"	select a.*,rownum rn from " +
					"	( " +
					"		select * from Order where cus_status!=? "+condition+" order by cus_id desc " +
					"	) a where rownum<=?)b " +
					"	where rn>?",handler,6,pageUtils.getEndRow(),pageUtils.getStartRow());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cusList;
	}

	/**
	 * 查询总行数
	 */
	public int findOrderListCount(String findName,String findPhone){
		int rowCount=0;
		try {
			//================================================
			String condition="";
			if(findName!=null && !findName.equals("")){
				condition+=" and cus_name like '%"+findName+"%'";
			}
			if(findPhone!=null && !findPhone.equals("")){
				condition+=" and cus_phone like '%"+findPhone+"%'";
			}
			//================================================
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			rowCount=((BigDecimal) qr.query("select count(*) from Order where cus_status!=? "+condition, new ColumnListHandler(),6).get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	/**
	 *  按照状态查询
	 */
	@Override
	public int updateOrderStatus(int status,String oid) {
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			String sql="update t_order set status=? where oid=?";
			Object[] param={status,oid};
			qr.update(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	} 
	//Mysql 分页查询 
	public PageBean<Order> query(Order orderObj, int pc, int ps) {
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			PageBean<Order> pb = new PageBean<Order>();
			pb.setPc(pc);
			pb.setPs(ps);
			StringBuilder cntSql = new StringBuilder("select count(*) from t_Order");
			StringBuilder whereSql = new StringBuilder(" where 1=1");
			List<Object> params = new ArrayList<Object>();
			if(orderObj.getStatus()>0){
				System.out.println(">0>>>>>>>>>>>>>>>>>>>>>>>>>>....");
				whereSql.append(" and status=?");
				params.add(orderObj.getStatus());
			}
			Number num = (Number)qr.query(cntSql.append(whereSql).toString(), 
					new ScalarHandler(), params.toArray());
			int tr = num.intValue();
			pb.setTr(tr);
			StringBuilder sql=new StringBuilder("select o.oid,status,ordertime,status,total,item.image_b from t_order o left join (select * from t_orderitem where orderItemId  in (select max(orderItemId) from t_orderitem GROUP BY oid)) item on item.oid=o.oid");
			// 我们查询beanList这一步，还需要给出limit子句
			StringBuilder limitSql = new StringBuilder(" limit ?,?");
			// params中需要给出limit后两个问号对应的值
			params.add((pc-1)*ps);
			params.add(ps);
			// 执行之
			List<Order> beanList = qr.query(sql.append(whereSql).append(limitSql).toString(), 
					new BeanListHandler<Order>(Order.class), 
					params.toArray());
			pb.setBeanList(beanList);
			return pb;		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询所有
	 * @return
	 */
	public PageBean<Order> findAll(int pc, int ps) {
		try {
			QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
			/*
			 * 1. 他都PageBean对象pb
			 * 2. 设置pb的pc和ps
			 * 3. 得到tr，设置给pb
			 * 4. 得到beanList，设置给pb
			 * 5. 返回pb
			 */
			PageBean<Order> pb = new PageBean<Order>();
			pb.setPc(pc);
			pb.setPs(ps);
			/*
			 * 得到tr
			 */
			String sql = "select count(*) from t_Order";
			Number num = (Number)qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			/*
			 * 得到beanList
			 */
			/*		sql = "select * from t_Order order by  cname limit ?,?";*/
			/*sql = "select o.oid,status,ordertime,status,total,image_b from t_orderitem,t_order o limit ?,?";*/
			sql="select o.oid,status,ordertime,status,total,item.image_b from t_order o left join (select * from t_orderitem where orderItemId  in (select max(orderItemId) from t_orderitem GROUP BY oid)) item on item.oid=o.oid limit ?,?";
			System.out.println((pc-1)*ps+ "  "+ ps);			
			List<Order> beanList = qr.query(sql, 
					new BeanListHandler<Order>(Order.class), 
					(pc-1)*ps, ps);
			pb.setBeanList(beanList);
			return pb;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Order> OrderDaoFindById(String userId) {
		QueryRunner qr=new QueryRunner(ConnectionPool.getDataSource());
		List<Order> orderList=null;
		//String sql="select o.oid,status,ordertime,status,total,image_b from t_orderitem,t_order o";
		String sql="select o.oid,status,ordertime,status,total,item.image_b from t_order o left join (select * from t_orderitem where orderItemId  in (select max(orderItemId) from t_orderitem GROUP BY oid)) item on item.oid=o.oid where uid=?";
		ResultSetHandler<List<Order>> bl=new BeanListHandler<Order>(Order.class);
		try {
			orderList=qr.query(sql,bl,userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}


}


