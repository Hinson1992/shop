package com.netbook.order.dao.factory;

import com.netbook.order.dao.OrderDao;
import com.netbook.order.dao.impl.OrderDaoImpl;
/**
 * 工厂
 * @author Administrator
 *
 */
public abstract class DaoFactory {
	/**
	 *  
	 * @return 返回订单的实现类  add  addItem
	 */
   public static OrderDao getOrderDao(){
	   return new OrderDaoImpl();    
   }
}
