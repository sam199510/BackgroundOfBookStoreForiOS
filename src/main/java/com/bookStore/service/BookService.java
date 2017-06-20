package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.dao.BookDAO;
import com.bookStore.entity.Book;

//书籍业务类
@Repository
@Transactional
public class BookService {
	//将书籍DAO自动注入
	@Autowired
	private BookDAO bookDAO;
	
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	
	//显示所有图书业务
	public List<Book> showAllBooks(){
		List<Book> books = this.bookDAO.showAllBooks();
		return books;
	}
	
	//根据书籍id显示书籍详细信息的业务
	public List<Book> showBookDetail(int bookID){
		List<Book> books = this.bookDAO.showBookDetail(bookID);
		return books;
	}
	
	//根据书籍id显示书籍的业务
	public List<Book> findByBookID(int bookID){
		List<Book> books = this.bookDAO.findByBookID(bookID);
		return books;
	}
	
	//更新书籍的库存量的业务
	public void updateBookRepertory(Book book){
		this.bookDAO.updateBookRepertory(book);
	}
	
	//根据图书的种类搜索图书
	public List<Book> findBookByBookType(int type){
		List<Book> books = this.bookDAO.findBookByType(type);
		return books;
	}
}
