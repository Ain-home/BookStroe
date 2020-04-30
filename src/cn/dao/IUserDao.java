package cn.dao;

import cn.entity.User;

public interface IUserDao {
	//用户工具  接口
	
	public User getUserById(String id);
	
	public User login(String name,String password);
	
	public boolean register(User user);

}
