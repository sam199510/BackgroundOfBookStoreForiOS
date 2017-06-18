package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.dao.UserDAO;
import com.bookStore.entity.User;

//用户业务类
@Repository
@Transactional
public class UserService {
	//将用户DAO自动注入
	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	//检查登录是否正确的业务
	public List<User> isLoginRight(String userName, String password){
		List<User> users = this.userDAO.isLoginRight(userName, password);
		return users;
	}
	
	//检查用户名是否存在业务
	public List<User> checkUserName(String userName){
		List<User> users = this.userDAO.checkUserName(userName);
		return users;
	}
	
	//注册用户业务
	public void registUser(User user){
		this.userDAO.registUser(user);
	}
	
	//根据用户名得到用户旧地址的业务
	public List<User> getUserCurrentAddress(String userName){
		List<User> users = this.userDAO.getUserCurrentAddress(userName);
		return users;
	}
	
	//更新用户地址的业务
	public void updateUserAddress(User user){
		this.userDAO.updatePersonalAddress(user);
	}
	
	//根据用户名找到用户的信息的业务
	public List<User> findUserByUserName(String userName){
		List<User> users = this.userDAO.findUserByUserName(userName);
		return users;
	}
	
	//根据用户名找到用户手机号码的业务
	public List<User> getUserCurrentMobile(String userName){
		List<User> users = this.userDAO.getUserCurrentMobile(userName);
		return users;
	}
	
	//检查用户旧密码是否正确的业务
	public List<User> checkUserPassword(String userName, String password){
		List<User> users = this.userDAO.checkUserPassword(userName, password);
		return users;
	}
	
	//检查用户支付密码是否争取的方法
	public List<User> isPayPasswordRight(String userName, String password) {
		List<User> users = this.userDAO.isPayPasswordRight(userName, password);
		return users;
	}
}
