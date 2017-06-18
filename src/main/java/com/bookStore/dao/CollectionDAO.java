package com.bookStore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.entity.Collection;

//收藏数据持久化类
@Repository
@Transactional
public class CollectionDAO {
	//将sessionFactory自动注入至类中
	@Autowired
	private SessionFactory sessionFactory;

	//根据书本的id和收藏者的id找到书籍的列表
	@SuppressWarnings("unchecked")
	public List checkIsCollect(int bookID,int collectorID){
		String hql = "from Collection c where c.collectorID = " + collectorID + " and c.bookID = " + bookID;
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//收藏图书
	public void collectBook(Collection collection){
		sessionFactory.getCurrentSession().save(collection);
	}
	
	//取消收藏图书
	public void cancelToCollectBook(int bookID, int collectorID){
		String hql = "delete from Collection c where c.collectorID = " + collectorID + " and c.bookID = " + bookID;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	//根据收藏者的id找到该收藏家收藏的图书
	@SuppressWarnings("unchecked")
	public List showCollectBooks(int collectorID){
		String hql = "from Collection c where c.collectorID = " + collectorID;
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
}
