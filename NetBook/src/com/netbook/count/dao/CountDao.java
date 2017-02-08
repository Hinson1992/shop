package com.netbook.count.dao;

import java.util.List;
import com.netbook.count.pojo.Count;
/**
 * 统计数据处理层
 * @author Administrator
 *
 */
public interface CountDao {
	/**
	 * 取得所有交易的信息
	 * @return 返回交易信息列表List
	 */
	public List<Count> findCustomerList(String year);
}
