package cn.service;

import java.util.List;

import cn.entity.Category;

public interface ICategoryService {
	//�����  �ӿ�
	
	public boolean addCategory(Category category);
	
	public Category getCategoryById(String id);
	
	public List<Category> getAllCategories();
	
	public boolean deleteCategory(String id);
	
	public boolean updateCategory(Category category);

}
