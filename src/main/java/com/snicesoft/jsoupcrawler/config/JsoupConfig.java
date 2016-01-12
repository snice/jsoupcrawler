package com.snicesoft.jsoupcrawler.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptEngine;

import com.snicesoft.jsoupcrawler.config.entity.Info;
import com.snicesoft.jsoupcrawler.utils.BeanUtils;
import com.snicesoft.jsoupcrawler.utils.JSUtils;
import com.snicesoft.jsoupcrawler.utils.StringUtils;

import jdk.nashorn.api.scripting.JSObject;

@SuppressWarnings("restriction")
public class JsoupConfig {
	private JsoupConfig() {

	}

	public static JsoupConfig load(ScriptEngine engine, String configName) {
		JsoupConfig jsoupConfig = new JsoupConfig();
		List<Info> aList = new ArrayList<Info>();
		jsoupConfig.setInfos(aList);
		InputStream is = ClassLoader.getSystemResourceAsStream(configName);
		String js = StringUtils.toString(is);
		Object object = JSUtils.loadJS(engine, js);
		JSObject mirror = (JSObject) JSUtils.get(object, "infos");
		List<Object> infos = (List<Object>) mirror.values();
		for (Object object2 : infos) {
			Bindings bindings = (Bindings) object2;
			aList.add(BeanUtils.map2Object(bindings, Info.class));
		}
		return jsoupConfig;
	}

	private List<Info> infos;

	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}

	public List<Info> getInfos() {
		return infos;
	}
}
