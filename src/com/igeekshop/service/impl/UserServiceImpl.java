package com.igeekshop.service.impl;

import java.util.List;

import com.igeekshop.dao.IUserDao;
import com.igeekshop.dao.impl.UserDaoImpl;
import com.igeekshop.domain.User;
import com.igeekshop.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private IUserDao userDaoImpl = new UserDaoImpl();
	@Override
	public User login(String username) {
		return userDaoImpl.selectUserByName(username);
	}
	@Override
	public boolean register(User user) {
		return userDaoImpl.insertUser(user)>0;
	}
	@Override
	public User jihuo(String code) {
		// TODO Auto-generated method stub
		return userDaoImpl.selectUserByCode(code);
	}
	@Override
	public boolean gengxin(User user) {
		// TODO Auto-generated method stub
		return userDaoImpl.updateUser(user)>0;
	}
	@Override
	public List<User> selectAllUser() {
		// TODO Auto-generated method stub
		return userDaoImpl.selectAllUser();
	}

}
