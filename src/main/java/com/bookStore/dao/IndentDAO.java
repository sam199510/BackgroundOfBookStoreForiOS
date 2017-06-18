package com.bookStore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.entity.Indent;

//订单数据持久化类
@Repository
@Transactional
public class IndentDAO {
	//将sessionFactory自动注入
	@Autowired
	private SessionFactory sessionFactory;
	
	//创建订单方法
	public void createOrder(Indent indent){
		sessionFactory.getCurrentSession().save(indent);
	}
	
	//更新订单是否评论方法
	public void updateIndentCommentState(Indent indent){
		sessionFactory.getCurrentSession().update(indent);
	}
	
	//删除订单方法
	public void deleteIndentByIndentId(int indentID){
		String hql = "delete from Indent i where i.id = " + indentID;
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
	}
	
	//根据买书者主键id找到订单的列表
	@SuppressWarnings("unchecked")
	public List showIndent(int buyerID){
		String hql = "from Indent i where i.buyerID = " + buyerID;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	//根据订单主键id找到是否评论的方法
	@SuppressWarnings("unchecked")
	public List checkIsComment(int indentID){
		String hql = "from Indent i where i.id = " + indentID;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	//更具订单主键id找到订单详情的方法
	@SuppressWarnings("unchecked")
	public List getIndentDetailInfo(int indentId){
		String hql = "from Indent i where i.id = " + indentId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
}
