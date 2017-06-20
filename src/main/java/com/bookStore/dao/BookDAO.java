package com.bookStore.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.entity.Book;

//书籍数据持久化类
@Repository
@Transactional
public class BookDAO {
	//将sessionFactory自动注入
	@Autowired
	private SessionFactory sessionFactory;
	
	//获取所有书籍的列表
	@SuppressWarnings("unchecked")
	public List showAllBooks(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
		return criteria.list();
	}
	
	//根据书籍的主键id得到书籍的列表
	@SuppressWarnings("unchecked")
	public List showBookDetail(int bookID){
		String hql = "from Book b where b.id="+bookID;
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//根据书籍的id找到书籍的方法
	@SuppressWarnings("unchecked")
	public List findByBookID(int bookID){
		String hql = "from Book b where b.id = " + bookID;
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//更新书籍表格中书籍的库存量
	public void updateBookRepertory(Book book){
		this.sessionFactory.getCurrentSession().update(book);
	}
	
	//根据图书的种类搜索图书
	@SuppressWarnings("unchecked")
	public List findBookByType(int type){
		String hql = "from Book b where b.type = " + type;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
