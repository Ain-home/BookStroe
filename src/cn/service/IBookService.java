package cn.service;

import cn.entity.Book;
import cn.entity.Page;

public interface IBookService {
	//Êé¼®¹¦ÄÜ
	
	public boolean addBook(Book book);
	
	public Book getBookById(String id);
	
	public Page getPageBooks(String pageNum);
	
	public Page getPageBooks(String currentPageCount,String category_id);
	
	public long getTotalBooks();
	
	public long getTotalBooks(String category_id);

}
