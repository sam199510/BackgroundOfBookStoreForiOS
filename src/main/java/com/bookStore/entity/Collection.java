package com.bookStore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

//收藏ORM类
@Entity
@Table(name = "collection")
public class Collection {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//收藏主键id
	@Column private int collectorID;//收藏者id，外键
	@Column private int bookID;//书籍id，外键
	@Column private String bookName;//收藏的书籍的书名
	@Column private float bookPrice;//收藏的书籍的价格
	@Column private String bookCover;//收藏的书籍的封面
	
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

	public int getCollectorID() {
		return collectorID;
	}

	public void setCollectorID(int collectorID) {
		this.collectorID = collectorID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookCover() {
		return bookCover;
	}

	public void setBookCover(String bookCover) {
		this.bookCover = bookCover;
	}
	
	
}
