package com.bookStore.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bookStore.dao.IndentDAO;
import com.bookStore.entity.Book;
import com.bookStore.entity.Collection;
import com.bookStore.entity.Comment;
import com.bookStore.entity.Indent;
import com.bookStore.entity.User;
import com.bookStore.service.BookService;
import com.bookStore.service.CollectionService;
import com.bookStore.service.CommentService;
import com.bookStore.service.IndentService;
import com.bookStore.service.UserService;

//控制类
@Controller
@RequestMapping("/")
public class BSController {
	//将request对象自动注入
	@Autowired
	private HttpServletRequest request;
	
	//将response对象自动注入
	@Autowired
	private HttpServletResponse response;
	
	//将session对象自动注入
	@Autowired
	private HttpSession session;
	
	//将用户Service自动注入
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//将书籍Service自动注入
	@Autowired
	private BookService bookService;
	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	//将收藏Service自动注入
	@Autowired
	private CollectionService collectionService;
	
	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
	//将订单Service自动注入
	@Autowired
	private IndentService indentService;
	
	public void setIndentService(IndentService indentService) {
		this.indentService = indentService;
	}
	
	//将评价Service自动注入
	@Autowired
	private CommentService commentService;
	
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	//日期转换类
	@InitBinder
	public void InitBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	//检查用户是否登录正确的方法
	@RequestMapping(value="login",method=RequestMethod.GET)
	@ResponseBody
	public int isLoginRight(String userName, String password){
		//调用用户Service中的检查用户名和密码是否正确的方法
		List<User> loginUsers = this.userService.isLoginRight(userName, password);
		//正确，返回1；否则，返回0
		if (loginUsers.size() == 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	//显示所有图书的方法
	@RequestMapping(value="showAllBooks",method=RequestMethod.GET)
	@ResponseBody
	public List<Book> showAllBooks(){
		List<Book> books = this.bookService.showAllBooks();
		return books;
	}
	
	//根据书籍的主键id显示书籍的详细信息的方法
	@RequestMapping(value="showBookDetailInfo", method = RequestMethod.GET)
	@ResponseBody
	public List<Book> showBookDetailInfo(int bookID){
		List<Book> books = this.bookService.showBookDetail(bookID);
		return books;
	}
	
	//检查用户名是否存在的方法
	@RequestMapping(value="checkUserName" , method = RequestMethod.GET)
	@ResponseBody
	public int checkUserName(String userName){
		List<User> checkUsers = this.userService.checkUserName(userName);
		//若存在，返回1；若不存在，返回0
		if (checkUsers.size() == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//注册方法
	@RequestMapping(value="regist" , method = RequestMethod.GET)
	@ResponseBody
	public String registUser(User user){
		this.userService.registUser(user);
		return "注册成功";
	}
	
	//得到用户旧地址的方法
	@RequestMapping(value="getUserCurrentAddress", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserCurrentAddress(String userName){
		List<User> users = this.userService.getUserCurrentAddress(userName);
		return users;
	}
	
	//更新用户新地址的方法
	@RequestMapping(value="updateUserAddress", method = RequestMethod.GET)
	@ResponseBody
	public String updateUserAddress(String userName, String address){
		//获取用户信息
		User u = this.userService.findUserByUserName(userName).get(0);
		//设置用户的新地址
		u.setAddress(address);
		//更新用户地址
		this.userService.updateUserAddress(u);
		return "修改地址成功";
	}
	
	//得到用户当前的手机号码的方法
	@RequestMapping(value="getUserCurrentMobile", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserCurrentMobile(String userName){
		List<User> users = this.userService.getUserCurrentMobile(userName);
		return users;
	}
	
	//更新用户新手机号码的方法
	@RequestMapping(value="updateUserMobile", method = RequestMethod.GET)
	@ResponseBody
	public String updateUserMobile(String userName, long mobile){
		//获取用户信息
		User u = this.userService.findUserByUserName(userName).get(0);
		//设置用户新手机号码
		u.setMobile(mobile);
		//更新用户手机号码
		this.userService.updateUserAddress(u);
		return "修改手机号成功";
	}
	
	//检查旧密码是否正确方法
	@RequestMapping(value="checkUserPassword", method = RequestMethod.GET)
	@ResponseBody
	public int checkUserPassword(String userName,String password){
		List<User> users = this.userService.checkUserPassword(userName, password);
		//如果正确，返回1；如果错误，返回0
		if (users.size() == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//更新用户新密码的方法
	@RequestMapping(value="updateUserPassword", method=RequestMethod.GET)
	@ResponseBody
	public String updateUserPassword(String userName, String password){
		//获取用户信息
		User u = this.userService.findUserByUserName(userName).get(0);
		//设置新密码
		u.setPassword(password);
		//更新用户密码
		this.userService.updateUserAddress(u);
		return "修改密码成功";
	}
	
	//检查是否收藏
	@RequestMapping(value="checkIsCollect", method = RequestMethod.GET)
	@ResponseBody
	public int checkIsCollect(int bookID,String userName){
		//获取用户信息及收藏者id的方法
		User user = this.userService.findUserByUserName(userName).get(0);
		int collectorID = user.getId();
		//调用检查是否已经收藏的方法
		List<Collection> collections = this.collectionService.checkIsCollect(bookID, collectorID);
		//如果已经收藏，返回1；如果没有收藏，返回0
		if (collections.size() == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//收藏图书方法
	@RequestMapping(value="collectBook",method=RequestMethod.GET)
	@ResponseBody
	public String collectBook(String userName, int bookID){
		//获取收藏者的信息
		User u = this.userService.findUserByUserName(userName).get(0);
		int collectorID = u.getId();
		//获取收藏图书信息
		Book b = this.bookService.findByBookID(bookID).get(0);
		int bookId = b.getId();
		String bookName = b.getBookName();
		float bookPrice = b.getPrice();
		String bookCover = b.getCover();
		
		//设置收藏对象并收藏图书
		Collection collection = new Collection();
		collection.setCollectorID(collectorID);
		collection.setBookID(bookId);
		collection.setBookName(bookName);
		collection.setBookPrice(bookPrice);
		collection.setBookCover(bookCover);
		
		this.collectionService.collectBook(collection);
		
		return "收藏成功";
	}
	
	//取消收藏图书
	@RequestMapping(value="cancelToCollectBook",method=RequestMethod.GET)
	@ResponseBody
	public String cancelToCollectBook(String userName, int bookID){
		//获取收藏者id
		User u = this.userService.findUserByUserName(userName).get(0);
		int collectorId = u.getId();
		//获取图书的id
		Book b = this.bookService.findByBookID(bookID).get(0);
		int bookId = b.getId();
		//取消收藏图书
		this.collectionService.cancelToCollectBook(collectorId, bookId);
		
		return "取消收藏成功";
	}
	
	//显示收藏的图书的方法
	@RequestMapping(value="showCollectBooks",method = RequestMethod.GET)
	@ResponseBody
	public List<Collection> showCollectBooks(String userName){
		User user = this.userService.findUserByUserName(userName).get(0);
		int collectorID = user.getId();
		List<Collection> collections = this.collectionService.showCollectBooks(collectorID);
		return collections;
	}
	
	//显示配送信息方法
	@RequestMapping(value="getDeliveredInfo",method=RequestMethod.GET)
	@ResponseBody
	public List<User> getDeliveredInfo(String userName){
		List<User> users = this.userService.findUserByUserName(userName);
		return users;
	}
	
	//显示配送图书的详细信息的方法
	@RequestMapping(value="getBookDetailInfo",method=RequestMethod.GET)
	@ResponseBody
	public List<Book> getBookDetailInfo(int bookID) {
		List<Book> books = this.bookService.findByBookID(bookID);
		return books;
	}
	
	//检查用户的支付密码是否正确的方法
	@RequestMapping(value="isPayPasswordRight",method = RequestMethod.GET)
	@ResponseBody
	public int isPayPasswordRight(String userName, String password){
		List<User> users = this.userService.isPayPasswordRight(userName, password);
		//如果正确，返回1；如果不正确，返回0
		if (users.size() == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	//创建订单方法
	@RequestMapping(value = "createOrder", method = RequestMethod.GET)
	@ResponseBody
	public String createOrder(String userName, int bookID){
		//调用获取购买者信息的方法
		User u = this.userService.findUserByUserName(userName).get(0);
		int buyerID = u.getId();
		long buyerMobile = u.getMobile();
		String buyerAddress = u.getAddress();
		String buyerName = u.getUserName();
		//调用获取购买的图书的信息的方法
		Book book = this.bookService.findByBookID(bookID).get(0);
		int bookId = book.getId();
		String bookName = book.getBookName();
		String bookCover = book.getCover();
		float bookPrice = book.getPrice();
		String bookPublisher = book.getPublisher();
		
		//获取库存信息
		int bookRepertory = book.getRepertory();
		//获取当前时间的类
		Date date = new Date();
		//新建一个订单对象，并将订单信息插入到数据库中
		Indent order = new Indent();
		order.setBuyerID(buyerID);
		order.setBookID(bookId);
		order.setBargainTime(date);
		order.setBuyerAddress(buyerAddress);
		order.setBookName(bookName);
		order.setBookCover(bookCover);
		order.setBookPrice(bookPrice);
		order.setBookPublisher(bookPublisher);
		order.setCommentState(0);
		order.setBuyerMobile(buyerMobile);
		order.setBuyerName(buyerName);
		this.indentService.createIndent(order);
		
		//更新库存量，并将信息在数据库中做更新
		book.setRepertory(bookRepertory - 1);
		this.bookService.updateBookRepertory(book);
		
		return "创建订单成功";
	}
	
	//显示订单的方法
	@RequestMapping(value="showIndent",method=RequestMethod.GET)
	@ResponseBody
	public List<Indent> showIndent(String userName){
		User user = this.userService.findUserByUserName(userName).get(0);
		int buyerID = user.getId();
		List<Indent> indents = this.indentService.showIndent(buyerID);
		return indents;
	}
	
	//检查是否评价的方法
	@RequestMapping(value="checkIsComment",method=RequestMethod.GET)
	@ResponseBody
	public int checkIsComment(int indentId){
		Indent indents = this.indentService.checkIsComment(indentId).get(0);
		int isComment = indents.getCommentState();
		//如果已评价，返回1；如果没评价，返回0
		if (isComment == 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	//显示订单详情的方法
	@RequestMapping(value="getIndentDetailInfo",method=RequestMethod.GET)
	@ResponseBody
	public List<Indent> getIndentDetailInfo(int indentId){
		List<Indent> indents = this.indentService.getIndentDetailInfo(indentId);
		return indents;
	}
	
	//评论图书的方法
	@RequestMapping(value="commentBook",method = RequestMethod.GET)
	@ResponseBody
	public String commentBook(String userName, int bookId, String content,int indentId){
		User user = this.userService.findUserByUserName(userName).get(0);
		int buyerID = user.getId();
		String buyerName = user.getUserName();
		
		Book book = this.bookService.findByBookID(bookId).get(0);
		int bookID = book.getId();
		
		Indent indent = this.indentService.getIndentDetailInfo(indentId).get(0);
		
		Date commentDate = new Date();
		
		Comment comment = new Comment();
		comment.setBookID(bookID);
		comment.setBuyerID(buyerID);
		comment.setCommentTime(commentDate);
		comment.setContent(content);
		comment.setBuyerName(buyerName);
		this.commentService.commentBook(comment);
		
		//在订单表中更新评价状态
		indent.setCommentState(1);
		this.indentService.updateIndentCommentState(indent);
		
		return "评论图书成功";
	}
	
	//更具书籍的id找到书籍的评价方法
	@RequestMapping(value="getCommentByBookID",method=RequestMethod.GET)
	@ResponseBody
	public List<Comment> getCommentByBookID(int bookId){
		List<Comment> comments = this.commentService.getCommentByBookID(bookId);
		return comments;
	}
	
	//删除订单方法
	@RequestMapping(value="deleteIndentByIndentId", method=RequestMethod.GET)
	@ResponseBody
	public String deleteIndentByIndentId(int indentId){
		Indent indent = this.indentService.getIndentDetailInfo(indentId).get(0);
		int indentID = indent.getId();
		
		this.indentService.deleteIndentByIndentId(indentID);
		
		return "删除订单成功";
	}
	
	//根据图书种类查找图书
	@RequestMapping(value="findBookByBookType",method=RequestMethod.GET)
	@ResponseBody
	public List<Book> findBookByBookType(int type){
		List<Book> books = this.bookService.findBookByBookType(type);
		return books;
	}
}
