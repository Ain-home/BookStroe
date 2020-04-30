package cn.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.IBookDao;
import cn.entity.Book;
import cn.util.DBUtil;

public class BookDao implements IBookDao {
	//�鼮ʵ��
	
	/*
	 * 1.����鼮
	 * 2.����id�����鼮
	 * 3.��÷�ҳ���鼮
	 * 4.��ð���������ķ�ҳ�鼮
	 * 5.����鼮���ܼ�¼��
	 * 6.���ĳ�����鼮�ܼ�¼��
	 */

	//1.����鼮
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		String sql = "insert into book values(?,?,?,?,?,?,?)";
		
		//�鼮��Ϣ
		Object[] params = {book.getId(),book.getName(),book.getDescription(),book.getAuthor(),
				book.getPrice(),book.getImage(),book.getCategory_id()};
		
		return DBUtil.executeUpdate(sql,params);
	}

	//2.����id�����鼮
	public Book getBookById(String id) {
		// TODO Auto-generated method stub
		Book book = null;
		try {
			String sql = "select * from book where id = ?";
			Object[] params = {id};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//������ڸ����
				//��ȡ��id���������Ϣ
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

	//3.��÷�ҳ���鼮
	public List<Book> getPageBooks(int start, int end) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		try {
			String sql = "select * from book limit ?,?";
			Object[] params = {start,end};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//��ȡ�鼮����
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

	//4.��ð���������ķ�ҳ�鼮
	public List<Book> getPageBooks(int start, int end, String category_id) {
		// TODO Auto-generated method stub
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		try {
			String sql = "select * from book where category_id = ? limit ?,?";
			Object[] params = {category_id,start,end};
			DBUtil.executeQuery(sql, params);
			while(DBUtil.rs.next()) {
				//��ȡ�鼮����
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

	//5.����鼮���ܼ�¼��
	public long getTotalBooks() {
		// TODO Auto-generated method stub
		String sql = "select count(1) from book";  //count(1)��count(*)Ч�ʸ���
		
		return DBUtil.getTotalCount(sql);
	}

	//6.���ĳ�����鼮�ܼ�¼��
	public long getTotalBooks(String category_id) {
		// TODO Auto-generated method stub
		String sql = "select count(1) from book where category_id = ?"; //count(1)��count(*)Ч�ʸ���
		Object[] params = {category_id};
		
		return DBUtil.getTotalCount(sql,params);
	}

}
