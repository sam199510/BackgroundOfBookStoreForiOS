package com.bookStore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

//订单ORM类
@Entity
@Table(name="indent")
public class Indent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;//主键id
	@Column private int buyerID;//购买者id，外键
	@Column private int bookID;//书籍id，外键
	@Column private Date bargainTime;//成交时间，以yyyy-MM-dd HH:mm:ss为单位
	@Column private String buyerAddress;//收货地址
	@Column private String bookName;//书籍书名
	@Column private String bookCover;//书籍封面
	@Column private float bookPrice;//书籍价格
	@Column private String bookPublisher;//书籍出版社
	@Column private int commentState;//书籍评价状态
	@Column private long buyerMobile;//购买者手机号
	@Column private String buyerName;//购买者姓名
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public long getBuyerMobile() {
		return buyerMobile;
	}

	public void setBuyerMobile(long buyerMobile) {
		this.buyerMobile = buyerMobile;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public Date getBargainTime() {
		return bargainTime;
	}

	public void setBargainTime(Date bargainTime) {
		this.bargainTime = bargainTime;
	}

	

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public int getCommentState() {
		return commentState;
	}

	public void setCommentState(int commentState) {
		this.commentState = commentState;
	}
	
}
