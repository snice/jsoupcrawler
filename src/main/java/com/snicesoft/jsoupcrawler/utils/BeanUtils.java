package com.snicesoft.jsoupcrawler.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtils {
	public static <T> T map2Object(Map<?, ?> map, Class<T> clazz) {
		T bean = null;
		try {
			bean = clazz.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(bean, map);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
