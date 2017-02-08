package com.netbook.count.pojo;
/**
 * 统计类
 * @author Administrator
 *
 */
public class Count {
	//交易的数量
	private int countNum;
	//交易的书名
	private String bname;
	
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getCountNum() {
		return countNum;
	}
	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}
	/*
	 *  select bname,count(*) from (select bname from t_book c,(select t.bid from t_orderitem t,t_order o where t.oid=o.oid and ordertime like '2014-%') s where c.bid=s.bid) as h group by bname;
	 */
	/**
	 * select * from (select bname,count(*) as count from (select bname from t_book c,(select t.bid from t_orderitem t,t_order o where t.oid=o.oid and ordertime like '2014-01%') s where c.bid=s.bid) as h group by bname) s order by count desc limit 0,10;
	 */
}
