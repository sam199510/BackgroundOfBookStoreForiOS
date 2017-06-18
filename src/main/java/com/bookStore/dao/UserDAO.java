package com.bookStore.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.entity.User;

//用户数据持久化类
@Repository
@Transactional
public class UserDAO {
	//将sessionFactory自动注入
	@Autowired
	private SessionFactory sessionFactory;
	
	//检查是否登录争取的方法
	@SuppressWarnings("unchecked")
	public List isLoginRight(String userName, String password){
		String hql = "from User u where u.userName = '" + userName + "' and u.password = '" + password + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//检查用户名是否存在的方法
	@SuppressWarnings("unchecked")
	public List checkUserName(String userName){
		String hql = "from User u where u.userName = '" + userName + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//注册用户方法
	public void registUser(User user){
		sessionFactory.getCurrentSession().save(user);
	}
	
	//根据用户名得到用户当前地址的方法
	@SuppressWarnings("unchecked")
	public List getUserCurrentAddress(String userName){
		String hql = "from User u where u.userName = '" + userName + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//更新用户信息的方法
	public void updatePersonalAddress(User user){
		sessionFactory.getCurrentSession().update(user);
	}
	
	//根据用户名得到用户的信息的方法
	@SuppressWarnings("unchecked")
	public List findUserByUserName(String userName){
		String hql = "from User u where u.userName = '" + userName + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//根据用户名得到用户手机号码的方法
	@SuppressWarnings("unchecked")
	public List getUserCurrentMobile(String userName){
		String hql = "from User u where u.userName = '" + userName + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	//检查用户的原密码是否正确的方法
	@SuppressWarnings("unchecked")
	public List checkUserPassword(String userName, String password){
		String hql = "from User u where u.userName = '" + userName + "' and u.password = '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	//检查支付密码是否正确的方法
	@SuppressWarnings("unchecked")
	public List isPayPasswordRight(String userName, String password){
		String hql = "from User u where u.userName = '" + userName + "' and u.password = '" + password + "'";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
}
