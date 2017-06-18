package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.dao.CollectionDAO;
import com.bookStore.entity.Collection;

//收藏业务类
@Repository
@Transactional
public class CollectionService {
	//将收藏DAO自动注入
	@Autowired
	private CollectionDAO collectionDAO;
	
	public void setCollectionDAO(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}
	
	//检查是否收藏的业务
	public List<Collection> checkIsCollect(int bookID, int collectorID){
		List<Collection> collections = this.collectionDAO.checkIsCollect(bookID, collectorID);
		return collections;
	}
	
	//收藏业务
	public void collectBook(Collection collection){
		this.collectionDAO.collectBook(collection);
	}
	
	//取消收藏业务
	public void cancelToCollectBook(int collectorID, int bookID){
		this.collectionDAO.cancelToCollectBook(bookID, collectorID);
	}
	
	//显示收藏图书的业务
	public List<Collection> showCollectBooks(int collectorID){
		List<Collection> collections = this.collectionDAO.showCollectBooks(collectorID);
		return collections;
	}
	
}
