package com.bookStore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.entity.Comment;

//评论数据持久化类
@Repository
@Transactional
public class CommentDAO {
	//将sessionFactory自动注入
	@Autowired
	private SessionFactory sessionFactory;
	
	//评价方法，在数据库中添加评价
	public void commentBook(Comment comment){
		sessionFactory.getCurrentSession().save(comment);
	}
	
	//根据图书的主键id找到图书的评论
	@SuppressWarnings("unchecked")
	public List getCommentByBookID(int bookId){
		String hql = "from Comment c where c.bookID = " + bookId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
