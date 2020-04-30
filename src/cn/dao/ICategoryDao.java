package cn.dao;

import java.util.List;

import cn.entity.Category;

public interface ICategoryDao {
	//书籍类别 工具接口
	
	public boolean addCategory(Category category);
	
	public Category getCategoryById(String id);
	
	public List<Category> getAllCategories();
	
	public boolean deleteCategory(String id);
	
	public boolean updateCategory(Category category);
	
}
