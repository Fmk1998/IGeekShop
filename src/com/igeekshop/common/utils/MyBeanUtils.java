package com.igeekshop.common.utils;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class MyBeanUtils {
	public static <T> T populate(Class<T> beanClass,Map<String, String[]> properties) {
		try {
			ConvertUtils.register(new Converter() {
				public Object convert(Class clazz, Object value) {
//					clazz是转换完了的结果   value是需要转换的值
					Date date = CommonUtils.parseDate((String)value);
					return date;
				}
			}, Date.class);
			T t = beanClass.newInstance();
			BeanUtils.populate(t, properties);
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
