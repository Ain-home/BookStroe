package cn.service.imp;

import java.util.List;

import cn.dao.ICategoryDao;
import cn.dao.imp.CategoryDao;
import cn.entity.Category;
import cn.service.ICategoryService;

public class CategoryService implements ICategoryService {
	//类别 功能实现类
	
	ICategoryDao categoryDao = new CategoryDao();

	@Override
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.addCategory(category);
	}

	@Override
	public Category getCategoryById(String id) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryById(id);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryDao.getAllCategories();
	}

	@Override
	public boolean deleteCategory(String id) {
		// TODO Auto-generated method stub
		return categoryDao.deleteCategory(id);
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.updateCategory(category);
	}

}
