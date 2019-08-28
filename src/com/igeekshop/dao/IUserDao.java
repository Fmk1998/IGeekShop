package com.igeekshop.dao;

import java.util.List;

import com.igeekshop.domain.User;

public interface IUserDao {
	User selectUserByName(String username);
	User selectUserByCode(String code);
	int insertUser(User user);
	int updateUser(User user);
	List<User> selectAllUser();
}
