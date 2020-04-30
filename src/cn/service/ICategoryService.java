package cn.service;

import java.util.List;

import cn.entity.Category;

public interface ICategoryService {
	//类别功能  接口
	
	public boolean addCategory(Category category);
	
	public Category getCategoryById(String id);
	
	public List<Category> getAllCategories();
	
	public boolean deleteCategory(String id);
	
	public boolean updateCategory(Category category);

}
