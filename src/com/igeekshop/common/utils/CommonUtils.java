package com.igeekshop.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {
//	日期转换工具
	public static Date parseDate(String date) {
		SimpleDateFormat sdf = 
				new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = null;
		try {
			parseDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parseDate;
	}
	
//  获取UUID
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
