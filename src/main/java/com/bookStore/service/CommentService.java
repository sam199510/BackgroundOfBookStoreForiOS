package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.dao.CommentDAO;
import com.bookStore.entity.Comment;

//评价业务类
@Repository
@Transactional
public class CommentService {
	//将评价DAO自动注入
	@Autowired
	private CommentDAO commentDAO;
	
	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	//评价业务
	public void commentBook(Comment comment) {
		this.commentDAO.commentBook(comment);
	}
	
	//显示评价业务
	public List<Comment> getCommentByBookID(int bookID){
		List<Comment> comments = this.commentDAO.getCommentByBookID(bookID);
		return comments;
	}
}
