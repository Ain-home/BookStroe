package cn.service.imp;

import cn.dao.IUserDao;
import cn.dao.imp.UserDao;
import cn.entity.User;
import cn.service.IUserService;

public class UserService implements IUserService {
	//用户功能实现类
	
	IUserDao userDao = new UserDao();

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public User login(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.login(name, password);
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return userDao.register(user);
	}
	
	
	

}
