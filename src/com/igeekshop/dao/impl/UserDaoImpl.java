package com.igeekshop.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.igeekshop.common.utils.JDBCUtils;
import com.igeekshop.dao.IUserDao;
import com.igeekshop.domain.User;

public class UserDaoImpl implements IUserDao {
	@Override
	public User selectUserByName(String username) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ?";
		User user = null;
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public int insertUser(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user(username,password,name,email,birthday,sex,state,code) values(?,?,?,?,?,?,'0',?)";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getBirthday(),user.getSex(),user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<User> selectAllUser() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user";
		List<User> users = null;
		try {
			users = queryRunner.query(sql,new BeanListHandler<User>(User.class));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
	}

	@Override
	public User selectUserByCode(String code) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where Code = ?";
		User user = null;
		try {
			user = queryRunner.query(sql, new BeanHandler<User>(User.class),code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public int updateUser(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username=?,password=?,name=?,email=?,birthday=?,sex=?,state=? where code=?";
		int rs = 0;
		try {
			rs = queryRunner.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
