package com.netbook.count.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.netbook.count.dao.CountDao;
import com.netbook.count.dao.impl.countDaoImpl;
import com.netbook.count.factory.DaoFactory;
import com.netbook.count.pojo.Count;

/**
 * count服务类
 * @author Administrator
 *
 */
public class CountService {
	
	public static CountDao countDao = new countDaoImpl();
	
	/**
	 * 从数据库获得数据
	 */
	public static String getCountJson(String year){
		List<Count> list = new ArrayList<Count>();
		list = countDao.findCustomerList(year);	
		return JSON.toJSONString(list);
	}
}
