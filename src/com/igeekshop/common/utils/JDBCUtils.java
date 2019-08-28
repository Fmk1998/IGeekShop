package com.igeekshop.common.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
	
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource() {
		return comboPooledDataSource;
	}
	
	public static Connection getConnection(){
		try {
			Connection conn = threadLocal.get();
			if(conn == null) {
				conn = comboPooledDataSource.getConnection();
				threadLocal.set(conn);
			}
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
	}
}
