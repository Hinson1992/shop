package com.netbook.categary.dao;

/**
 * SQL语句
 * 
 * @author Administrator
 * 
 */
public interface Sql {
	// 分类模块sql语句
	// 查询出所有一级分类
	public static final String SQL_QUERY_FIRST_CATE = "select cid,cname,pid,description,orderBy from t_category where pid is null and isdel=0 order by cid";
	// 查询出所有二级分类
	public static final String SQL_QUERY_SECOND_CATE = "select cid,cname,pid,description,orderBy from t_category where pid is not null and isdel=0";
	// 查询出所有一级分类的id
	public static final String SQL_QUERY_FIRST_CID = "select cid from t_category where pid is null and isdel=0";
	// 插入一级分类
	public static final String SQL_ADD_FIRST_CATE = "insert into t_category values(?,?,null,?,66,0)";
	// 插入二级分类
	public static final String SQL_ADD_SECOND_CATE = "insert into t_category values(?,?,?,?,66,0)";
	// 修改一级分类
	public static final String SQL_UPDATE_FIRST_CATE = "update t_category set cname=?,description=? where cid=?";
	// 修改二级分类
	public static final String SQL_UPDATE_SECOND_CATE = "update t_category set cname=?,pid=?,description=? where cid=?";
	// 根据类型id查询对应书籍数量
	public static final String SQL_QUERY_BOOK_COUNT = "select count(*) count from t_book where cid = ? and isdel = 0 ";
	// 根据一级分类id查询对应的二级分类数量
	public static final String SQL_QUERY_SCATE_BYID = "select count(*) count from t_category where pid = ? and isdel = 0 ";
	// 根据一级分类id查询对应的所有二级分类id
	public static final String SQL_QUERY_SCATE_ID = "select cid from t_category where pid = ? and isdel = 0 ";
	// 删除分类
	public static final String SQL_DEL_CATE = "update t_category set isdel=1 where cid=?";

	// 图书模块sql语句
	// 根据分类cid取得所有图书信息
	public static final String SQL_QUERY_BOOK_BYCID = "select * from t_book where isdel=0 and cid = ? ";
	// 取得所有图书信息
	public static final String SQL_QUERY_BOOK_BYBNAME = "select * from t_book where isdel=0 ";
	// 根据图书编号bid取得所有图书信息
	public static final String SQL_QUERY_BOOK_BYBID = "select * from t_book where isdel=0 and bid = ? ";
}
