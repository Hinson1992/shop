package com.netbook.count.factory;

import com.netbook.count.dao.CountDao;
import com.netbook.count.dao.impl.countDaoImpl;

/**
 * 获得实现类的工厂
 * @author Administrator
 *
 */
public class DaoFactory {
	//获得countDao的实现类
	public static CountDao getCountDao(){
		return new countDaoImpl();
	}
}
