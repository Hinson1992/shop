package com.netbook.categary.domain;

/**
 * 图书类别分类
 * 
 * @author Administrator
 * 
 */
public class Categary {
	// 图书类别编号
	private String cid;
	// 图书类别名称
	private String cname;
	// 一级类别编号
	private String pid;
	// 描述
	private String description;
	//
	private int orderBy;

	public Categary() {
		super();
	}

	public Categary(String cid, String cname, String pid, String description,
			int orderBy) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.pid = pid;
		this.description = description;
		this.orderBy = orderBy;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "Categary [cid=" + cid + ", cname=" + cname + ", pid=" + pid
				+ ", description=" + description + ", orderBy=" + orderBy + "]";
	}

}
