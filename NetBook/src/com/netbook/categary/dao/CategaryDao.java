package com.netbook.categary.dao;

/**
 * 客户数据处理层接口
 */
import java.util.List;

import com.netbook.categary.domain.Categary;

public interface CategaryDao {
	/**
	 * 取得所有一级分类
	 * 
	 * @return 返回一级分类列表List
	 */
	public List<Categary> findFirstCate();

	/**
	 * 取得所有二级分类
	 * 
	 * @return 返回二级分类列表List
	 */
	public List<Categary> findSecondCate();

	/**
	 * 插入一级分类
	 * 
	 * @param 一级分类名
	 *            描述
	 * @return 影响行数
	 */
	public int addFirstCate(String cname, String description);

	/**
	 * 插入二级分类
	 * 
	 * @param 二级分类名
	 *            描述
	 * @return 影响行数
	 */
	public int addSecondCate(String cname, String pid, String description);

	/**
	 * 取得所有一级分类id
	 * 
	 * @return 返回一级分类id列表List
	 */
	public List<String> findFirstCName();

	/**
	 * 修改一级分类
	 * 
	 * @param 一级分类名
	 *            ,描述,一级分类id
	 * @return 返回影响行数
	 */
	public int updateFirstCate(String cname, String description, String cid);

	/**
	 * 修改二级分类
	 * 
	 * @param 二级分类名
	 *            ,一级分类id ,描述,二级分类id
	 * @return 返回影响行数
	 */
	public int updateSecondCate(String cname, String pid, String description,
			String cid);

	/**
	 * 删除分类
	 * 
	 * @param 分类id
	 * @return 返回影响行数
	 */
	public int delCate(String cid);

	/**
	 * 根据类型id查询对应书籍数量
	 * 
	 * @param 二级分类id
	 * @return 书籍数量
	 */
	public int findBookNum(String cid);

	/**
	 * 根据一级分类id查询对应的二级分类数量
	 * 
	 * @param 一级分类id
	 * @return 二级分类数量
	 */
	public int findSCateNum(String cid);

	/**
	 * 根据一级分类id查询对应的所有二级分类id
	 * 
	 * @param 一级分类id
	 * @return 二级分类id集合
	 */
	public List<String> findSCateId(String cid);
}
