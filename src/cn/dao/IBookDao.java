package cn.dao;

import java.util.List;

import cn.entity.Book;

public interface IBookDao {
	//�鼮 ���߽ӿ�
	
	public boolean addBook(Book book);
	
	public Book getBookById(String id);
	
	public List<Book> getPageBooks(int start,int end);
	
	public List<Book> getPageBooks(int start,int end,String category_id);
	
	public long getTotalBooks();
	
	public long getTotalBooks(String category_id);

}
