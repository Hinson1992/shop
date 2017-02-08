package com.netbook.cart.dao.factory;

import com.netbook.cart.dao.IcartDao;
import com.netbook.cart.dao.impl.CartDaoImpl;

/**
 * cartdao工厂
 * @return
 */
public class CartDaoFactory {
	/**
	 * 返回IcartDao的实现类
	 * @return
	 */
	public static IcartDao getIcartDao()
	{
		return new CartDaoImpl();
	}
}
