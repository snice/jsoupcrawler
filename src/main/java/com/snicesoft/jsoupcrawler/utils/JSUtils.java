package com.snicesoft.jsoupcrawler.utils;

import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.beanutils.BeanUtils;

import com.snicesoft.jsoupcrawler.config.entity.AbstractJS;

import jdk.nashorn.api.scripting.JSObject;

@SuppressWarnings("restriction")
public class JSUtils {
	/**
	 * 直接载入js
	 * 
	 * @param engine
	 * @param js
	 */
	public static void load(ScriptEngine engine, String js) {
		try {
			engine.eval(js);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 载入js后，var config = {js}
	 * 
	 * @param engine
	 * @param js
	 * @return
	 */
	public static Object loadJS(ScriptEngine engine, String js) {
		load(engine, "config = " + js);
		return engine.get("config");
	}

	public static Object get(Object object, String name) {
		javax.script.Bindings bindings = (Bindings) object;
		return bindings.get(name);
	}

	public static Object get(javax.script.Bindings bindings, String name) {
		return bindings.get(name);
	}

	public static <T extends AbstractJS> List<T> getList(Object data, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		JSObject jsObject = (JSObject) data;
		try {
			if (jsObject.isArray()) {
				List<Object> arr = (List<Object>) jsObject.values();
				for (Object object : arr) {
					T t = clazz.newInstance();
					t.setObject(object);
					BeanUtils.populate(t, (Bindings) object);
					list.add(t);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
