package cn.itcast.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public User login(String loginname, String loginpass) {
		return userDao.findByNameAndPass(loginname, loginpass);
	}

	public List<User> findByAllUser(User user) {
		return userDao.findByCondition(user);
	}
	
	public Map<Object, Object> genMap(){
		
		return new HashMap<Object, Object>();
	}

	public User findByUid(String uid) {
		return userDao.findByUid(uid);
	}

	public void delUser(String uid) {
		userDao.delUser(uid);
	}

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void edit(User user) {
		userDao.updateUser(user);
	}
	
}
