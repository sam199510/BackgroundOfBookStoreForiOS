package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.dao.IndentDAO;
import com.bookStore.entity.Indent;

//订单业务类
@Repository
@Transactional
public class IndentService {
	//将订单DAO自动注入
	@Autowired
	private IndentDAO indentDAO;
	
	public void setIndentDAO(IndentDAO indentDAO) {
		this.indentDAO = indentDAO;
	}
	
	//创建订单业务
	public void createIndent(Indent indent){
		this.indentDAO.createOrder(indent);
	}
	
	//更新订单评价状态业务
	public void updateIndentCommentState(Indent indent){
		this.indentDAO.updateIndentCommentState(indent);
	}
	
	//删除订单业务
	public void deleteIndentByIndentId(int IndentId){
		this.indentDAO.deleteIndentByIndentId(IndentId);
	}
	
	//根据购买者id显示订单的业务
	public List<Indent> showIndent(int buyerID){
		List<Indent> indents = this.indentDAO.showIndent(buyerID);
		return indents;
	}
	
	//检查是否评价的业务
	public List<Indent> checkIsComment(int indentID){
		List<Indent> indents = this.indentDAO.checkIsComment(indentID);
		return indents;
	}
	
	//根据订单id找到订单详细详细的业务
	public List<Indent> getIndentDetailInfo(int indentId) {
		List<Indent> indents = this.indentDAO.getIndentDetailInfo(indentId);
		return indents;
	}
}
