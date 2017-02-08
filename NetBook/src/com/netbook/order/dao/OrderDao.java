package com.netbook.order.dao;
import java.util.List;

import com.netbook.comm.PageUtils;
import com.netbook.order.domain.Order;
import com.netbook.order.domain.OrderItem;
import com.netbook.order.domain.PageBean;
/**
 * 
 *  订单处理接口
 *
 */
public interface OrderDao {
	/**
	 * 添加订单
	 * @param order 需要添加的订单对象
	 * @return    添加了几条信息
	 */
	int addOrder(Order order);

	/**
	 * 添加订订单条目 
	 * @param orderItemList
	 * @return  添加了几条订单条目
	 */
	int  addOrderItemList(List<OrderItem> orderItemList);

	/**
	 * 通过用户获取id
	 */
	Order OrderDaoFindByUid(String uid,String oid);

	/**
	 * 查看所有用户的订单
	 */
	List<Order> OrdderDaoFindByAll();
	/**
	 * 
	 */
	List<Order> OrderDaoFindById(String userId);
	public List<Order> findOrderList(PageUtils pageUtils,String findName,String findPhone);

   
	public int findOrderListCount(String findName,String findPhone);


	public int updateOrderStatus(int status,String oid);

	public PageBean<Order> query(Order criteria, int pc, int ps);

	/**
	 * 查询所有
	 * @return
	 */
	public PageBean<Order> findAll(int pc, int ps);
	
}
