package cn.dao.imp;

import java.sql.SQLException;

import cn.dao.IUserDao;
import cn.entity.User;
import cn.util.DBUtil;

public class UserDao implements IUserDao {
	//�û�����ʵ��
	
	/*
	 * 1.����id�����û�
	 * 2.�û���½
	 * 3.�û�ע��
	 */

	//1.����id�����û�
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String sql = "select * from users where id = ?";
			Object[] params = {id};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//������ڸ����
				//��ȡ��id���������Ϣ
				String name = DBUtil.rs.getString("name");
				String password = DBUtil.rs.getString("password");
			
				user = new User(id,name,password);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return user;
	}

	//2.�û���½(����null��ʾ�����ڸ��û�)
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String sql = "select * from users where name = ? and password = ?";
			Object[] params = {name,password};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//������ڸ����
				//��ȡ��id���������Ϣ
				String id = DBUtil.rs.getString("id");
			
				user = new User(id,name,password);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll();
		}
		return user;
	}

	//3.�û�ע�ᣨ����true��ʾ�ɹ���
	public boolean register(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into users values(?,?,?)";
		
		//�鼮��Ϣ
		Object[] params = {user.getId(),user.getName(),user.getPassword()};
		
		return DBUtil.executeUpdate(sql,params);
	}

}
