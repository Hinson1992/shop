package com.netbook.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;

import com.netbook.comm.PageUtils;
import com.netbook.order.dao.OrderDao;
import com.netbook.order.dao.factory.DaoFactory;
import com.netbook.order.domain.Order;
import com.netbook.order.domain.OrderItem;
import com.netbook.order.domain.PageBean;

public class OrderService {
	private  OrderDao orderDao=DaoFactory.getOrderDao(); 
    public	void add(Order order){
 
    
    	   try{
    		 //开启事务
    		 JdbcUtils.beginTransaction();
    		 System.out.println(JdbcUtils.getDataSource().getConnection()+"service");
    		 //添加订单进入数据库
    		  orderDao.addOrder(order);
    		  orderDao.addOrderItemList(order.getOrderItem());
    		  //提交事务
    		  JdbcUtils.commitTransaction();
    		 
    	 }catch(Exception e){
    		try {
    			//回滚事务
    			JdbcUtils.rollbackTransaction();
    		} catch (SQLException e1){
    			e1.printStackTrace();
    		}
    		throw new RuntimeException();
    	 } 		

    }

    /**
     * 搜索所有订单
     */
     public List<Order> OrdderDaoFindByAll() {
    	 return orderDao.OrdderDaoFindByAll();	 
     }
      
     public List<Order> OrdderDaoFindByallFarWard(String userId){
    	 
    	    return orderDao.OrderDaoFindById(userId);
     }
     /**
      *  查询订单列表
      * @param pageUtils  分页工具
      * @param findName   
      * @param findPhone
      * @return
      */
     public List<Order> findOrderList(PageUtils pageUtils,String findName,String findPhone){
    	  return orderDao.findOrderList(pageUtils, findName, findPhone);
     }
     /**
      * 
      * @param findName
      * @param findPhone
      * @return
      */
     public int findOrderListCount(String findName,String findPhone){
    	   return orderDao.findOrderListCount(findName, findPhone);
     }
     /**
      * 
      * 根据用户名查找用户信息
      * @param uid
      * @return
      */
     public Order OrderDaoFindByUid(String uid,String oid){
    	   return orderDao.OrderDaoFindByUid(uid,oid);
     }
     /**
      * 按照状态查询
      * @param status  3.确认收货  5 取消
      * @return
      */
     public int updateOrderStatus(int status,String oid){
    	  return orderDao.updateOrderStatus(status,oid);
     }
     /**
      * 分页查询
      * @param criteria
      * @param pc
      * @param ps
      * @return
      */
     public PageBean<Order> query(Order orderObj, int pc, int ps) {
 		return orderDao.query(orderObj, pc, ps);
 	}
     /**
      * 搜索全部
      * @param pc
      * @param ps
      * @return
      */
     public PageBean<Order> findAll(int pc, int ps) {
 		return orderDao.findAll(pc, ps);
 	}
}
