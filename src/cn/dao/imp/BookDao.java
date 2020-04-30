package cn.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.IBookDao;
import cn.entity.Book;
import cn.util.DBUtil;

public class BookDao implements IBookDao {
	//书籍实体
	
	/*
	 * 1.添加书籍
	 * 2.根据id查找书籍
	 * 3.获得分页的书籍
	 * 4.获得按照类别分类的分页书籍
	 * 5.获得书籍的总记录数
	 * 6.获得某类别的书籍总记录数
	 */

	//1.添加书籍
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		String sql = "insert into book values(?,?,?,?,?,?,?)";
		
		//书籍信息
		Object[] params = {book.getId(),book.getName(),book.getDescription(),book.getAuthor(),
				book.getPrice(),book.getImage(),book.getCategory_id()};
		
		return DBUtil.executeUpdate(sql,params);
	}

	//2.根据id查找书籍
	public Book getBookById(String id) {
		// TODO Auto-generated method stub
		Book book = null;
		try {
			String sql = "select * from book where id = ?";
			Object[] params = {id};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//如果存在该类别
				//获取除id外的其他信息
				String name = DBUtil.rs.getString("name");
				String description = DBUtil.rs.getString("description");
				String author = DBUtil.rs.getString("author");
				double price = DBUtil.rs.getDouble("price");
				String image = DBUtil.rs.getString("image");
				String category_id = DBUtil.rs.getString("category_id");
			
				book = new Book(id,name,description,author,price,image,category_id);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return book;
	}

	//3.获得分页的书籍
	public List<Book> getPageBooks(int start, int end) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		try {
			String sql = "select * from book limit ?,?";
			Object[] params = {start,end};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//获取书籍属性
				String id = DBUtil.rs.getString("id");
				String name = DBUtil.rs.getString("name");
				String description = DBUtil.rs.getString("description");
				String author = DBUtil.rs.getString("author");
				double price = DBUtil.rs.getDouble("price");
				String image = DBUtil.rs.getString("image");
				String category_id = DBUtil.rs.getString("category_id");
			
				book = new Book(id,name,description,author,price,image,category_id);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return books;
	}

	//4.获得按照类别分类的分页书籍
	public List<Book> getPageBooks(int start, int end, String category_id) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		try {
			String sql = "select * from book where category_id = ? limit ?,?";
			Object[] params = {category_id,start,end};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//获取书籍属性
				String id = DBUtil.rs.getString("id");
				String name = DBUtil.rs.getString("name");
				String description = DBUtil.rs.getString("description");
				String author = DBUtil.rs.getString("author");
				double price = DBUtil.rs.getDouble("price");
				String image = DBUtil.rs.getString("image");
			
				book = new Book(id,name,description,author,price,image,category_id);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return books;
	}

	//5.获得书籍的总记录数
	public long getTotalBooks() {
		// TODO Auto-generated method stub
		String sql = "select count(1) from book";  //count(1)比count(*)效率更高
		
		return DBUtil.getTotalCount(sql);
	}

	//6.获得某类别的书籍总记录数
	public long getTotalBooks(String category_id) {
		// TODO Auto-generated method stub
		String sql = "select count(1) from book where category_id = ?"; //count(1)比count(*)效率更高
		Object[] params = {category_id};
		
		return DBUtil.getTotalCount(sql,params);
	}

}
