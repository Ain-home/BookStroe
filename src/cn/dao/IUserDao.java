package cn.dao;

import cn.entity.User;

public interface IUserDao {
	//�û�����  �ӿ�
	
	public User getUserById(String id);
	
	public User login(String name,String password);
	
	public boolean register(User user);

}
