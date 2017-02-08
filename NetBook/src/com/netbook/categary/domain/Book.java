package com.netbook.categary.domain;

/**
 * 图书类
 * 
 * @author Administrator
 * 
 */
public class Book {
	// 图书编号
	private String bid;
	// 图书名称
	private String bname;
	// 作者
	private String author;
	// 定价
	private double price;
	// 折后价格
	private double currPrice;
	// 折扣
	private double discount;
	// 出版社
	private String press;
	// 出版时间
	private String publishtime;
	// 版次
	private int edition;
	// 页数
	private int pageNum;
	// 字数
	private int wordNum;
	// 印刷时间
	private String printtime;
	// 开本
	private int booksize;
	// 纸张
	private String paper;
	// 类型编号
	private String cid;
	// 大号图片
	private String image_w;
	// 小号图片
	private String image_b;
	//
	private int orderBy;

	public Book(String bid, String bname, String author, double price,
			double currPrice, double discount, String press,
			String publishtime, int edition, int pageNum, int wordNum,
			String printtime, int booksize, String paper, String cid,
			String image_w, String image_b, int orderBy) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.author = author;
		this.price = price;
		this.currPrice = currPrice;
		this.discount = discount;
		this.press = press;
		this.publishtime = publishtime;
		this.edition = edition;
		this.pageNum = pageNum;
		this.wordNum = wordNum;
		this.printtime = printtime;
		this.booksize = booksize;
		this.paper = paper;
		this.cid = cid;
		this.image_w = image_w;
		this.image_b = image_b;
		this.orderBy = orderBy;
	}

	public Book() {
		super();
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getWordNum() {
		return wordNum;
	}

	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}

	public String getPrinttime() {
		return printtime;
	}

	public void setPrinttime(String printtime) {
		this.printtime = printtime;
	}

	public int getBooksize() {
		return booksize;
	}

	public void setBooksize(int booksize) {
		this.booksize = booksize;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getImage_w() {
		return image_w;
	}

	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}

	public String getImage_b() {
		return image_b;
	}

	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", author=" + author
				+ ", price=" + price + ", currPrice=" + currPrice
				+ ", discount=" + discount + ", press=" + press
				+ ", publishtime=" + publishtime + ", edition=" + edition
				+ ", pageNum=" + pageNum + ", wordNum=" + wordNum
				+ ", printtime=" + printtime + ", booksize=" + booksize
				+ ", paper=" + paper + ", cid=" + cid + ", image_w=" + image_w
				+ ", image_b=" + image_b + ", orderBy=" + orderBy + "]";
	}

}
