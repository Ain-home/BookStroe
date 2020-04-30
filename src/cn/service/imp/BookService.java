package cn.service.imp;

import java.util.List;

import cn.dao.IBookDao;
import cn.dao.imp.BookDao;
import cn.entity.Book;
import cn.entity.Page;
import cn.service.IBookService;

public class BookService implements IBookService {
	//�鼮����ʵ����
	
	IBookDao bookDao = new BookDao();

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.addBook(book);
	}

	@Override
	public Book getBookById(String id) {
		// TODO Auto-generated method stub
		return bookDao.getBookById(id);
	}

	@Override
	public Page getPageBooks(String pageNum) {
		// TODO Auto-generated method stub
		//��ǰҳ
		Page page = null;
		if(pageNum==null) {
			page = new Page(1,bookDao.getTotalBooks());
		}else {
			page = new Page(Integer.valueOf(pageNum),bookDao.getTotalBooks());
		}
		List<Book> books = bookDao.getPageBooks(page.getStartIndex(), page.getPageSize());
		page.setList(books);
		
		return page;
	}

	@Override
	public Page getPageBooks(String currentPageCount, String category_id) {
		// TODO Auto-generated method stub
		Page page = null;
		if(currentPageCount==null) {
			page = new Page(1,bookDao.getTotalBooks(category_id));
		}else {
			page = new Page(Integer.valueOf(currentPageCount),bookDao.getTotalBooks(category_id));
		}
		List<Book> books = bookDao.getPageBooks(page.getStartIndex(), page.getPageSize(),category_id);
		page.setList(books);
		
		return page;
	}

	@Override
	public long getTotalBooks() {
		// TODO Auto-generated method stub
		return bookDao.getTotalBooks();
	}

	@Override
	public long getTotalBooks(String category_id) {
		// TODO Auto-generated method stub
		return bookDao.getTotalBooks(category_id);
	}

}
