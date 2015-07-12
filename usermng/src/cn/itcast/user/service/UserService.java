package cn.itcast.user.service;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public User login(String loginname, String loginpass) {
		return userDao.findByNameAndPass(loginname, loginpass);
	}
}
