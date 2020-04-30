package cn.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.dao.ICategoryDao;
import cn.entity.Category;
import cn.util.DBUtil;

public class CategoryDao implements ICategoryDao {
	//�鼮���ʵ����
	
	/*
	 * 1.������
	 * 2.����id�������
	 * 3.��ȡ�������
	 * 4.����idɾ�������
	 * 5.�޸������Ϣ
	 */

	//1.�����𣨷���true��ʾ��ӳɹ���
	public boolean addCategory(Category category) {
		// TODO Auto-generated method stub
		String sql = "insert into category values(?,?,?)";
		
		//�����Ϣ
		String id = category.getId();
		String name = category.getName();
		String description = category.getDescription();
		
		Object[] params = {id,name,description};
		
		return DBUtil.executeUpdate(sql,params);
	}

	//2.����id������𣨷���null��ʾ����ʧ�ܣ�����ɹ���
	public Category getCategoryById(String id) {
		// TODO Auto-generated method stub
		Category category = null;
		try {
			String sql = "select * from category where id = ?";
			Object[] params = {id};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//������ڸ����
				//��ȡ��id���������Ϣ
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

	//3.���ҷ����������
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categories = new ArrayList<Category>();
		Category category = null;
		try {
			String sql = "select * from category";
			DBUtil.executeQuery(sql, null);
			while(DBUtil.rs.next()) {
				//�û���Ϣ
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

	//4.ɾ����𣨷���true��ʾ�ɹ���
	public boolean deleteCategory(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from category where id = ?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql,params);
	}

	//5.�޸������Ϣ������true��ʾ�ɹ���
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
