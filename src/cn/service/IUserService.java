package cn.service;

import cn.entity.User;

public interface IUserService {
	//用户功能
	
	public User getUserById(String id);
	
	public User login(String name,String password);
	
	public boolean register(User user);

}
