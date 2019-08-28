package com.igeekshop.service;

import java.util.List;

import com.igeekshop.domain.User;

public interface IUserService {
	List<User> selectAllUser();
	User login(String username);
	boolean register(User user);
	User jihuo(String code);
	boolean gengxin(User user);
}
