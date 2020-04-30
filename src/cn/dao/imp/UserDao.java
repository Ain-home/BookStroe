package cn.dao.imp;

import java.sql.SQLException;

import cn.dao.IUserDao;
import cn.entity.User;
import cn.util.DBUtil;

public class UserDao implements IUserDao {
	//用户工具实现
	
	/*
	 * 1.根据id查找用户
	 * 2.用户登陆
	 * 3.用户注册
	 */

	//1.根据id查找用户
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String sql = "select * from users where id = ?";
			Object[] params = {id};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//如果存在该类别
				//获取除id外的其他信息
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

	//2.用户登陆(返回null表示不存在该用户)
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			String sql = "select * from users where name = ? and password = ?";
			Object[] params = {name,password};
			DBUtil.executeQuery(sql, params);
			if(DBUtil.rs.next()) {
				//如果存在该类别
				//获取除id外的其他信息
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

	//3.用户注册（返回true表示成功）
	public boolean register(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into users values(?,?,?)";
		
		//书籍信息
		Object[] params = {user.getId(),user.getName(),user.getPassword()};
		
		return DBUtil.executeUpdate(sql,params);
	}

}
