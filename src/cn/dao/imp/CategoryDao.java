package cn.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.ICategoryDao;
import cn.entity.Category;
import cn.util.DBUtil;

public class CategoryDao implements ICategoryDao {
	//书籍类别实体类
	
	/*
	 * 1.添加类别
	 * 2.根据id查找类别
	 * 3.获取所有类别
	 * 4.根据id删除该类别
	 * 5.修改类别信息
	 */

	//1.添加类别（返回true表示添加成功）
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		String sql = "insert into category values(?,?,?)";
		
		//类别信息
		String id = category.getId();
		String name = category.getName();
		String description = category.getDescription();
		
		Object[] params = {id,name,description};
		
		return DBUtil.executeUpdate(sql,params);
	}

	//2.根据id查找类别（返回null表示查找失败，否则成功）
	public Category getCategoryById(String id) {
		// TODO Auto-generated method stub
		Category category = null;
		try {
			String sql = "select * from category where id = ?";
			Object[] params = {id};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//如果存在该类别
				//获取除id外的其他信息
				String name = DBUtil.rs.getString("name");
				String description = DBUtil.rs.getString("description");
				
				category = new Category(id,name,description);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return category;
	}

	//3.查找返回所有类别
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = new ArrayList<Category>();
		Category category = null;
		try {
			String sql = "select * from category";
			DBUtil.executeQuery(sql, null);
			while(DBUtil.rs.next()) {
				//用户信息
				String id = DBUtil.rs.getString("id");
				String name = DBUtil.rs.getString("name");
				String description = DBUtil.rs.getString("description");
				
				category = new Category(id,name,description);
				categories.add(category);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return categories;	
	}

	//4.删除类别（返回true表示成功）
	public boolean deleteCategory(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from category where id = ?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql,params);
	}

	//5.修改类别信息（返回true表示成功）
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		String sql = "update category set name = ?,description = ? where id = ?";
		
		String id = category.getId();
		String name = category.getName();
		String description = category.getDescription();
		
		Object[] params = {name,description,id};
		return DBUtil.executeUpdate(sql,params);
	}

}
