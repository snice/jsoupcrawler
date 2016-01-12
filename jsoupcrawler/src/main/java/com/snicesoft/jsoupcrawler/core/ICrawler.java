package com.snicesoft.jsoupcrawler.core;

import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.snicesoft.jsoupcrawler.config.JsoupConfig;
import com.snicesoft.jsoupcrawler.config.entity.Column;
import com.snicesoft.jsoupcrawler.config.entity.Info;
import com.snicesoft.jsoupcrawler.listener.DataAcceptListener;

public abstract class ICrawler {
	protected static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	private DataAcceptListener listener;
	protected ScriptEngine engine;
	protected javax.script.Invocable invocable;

	public ICrawler() {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByName("nashorn");
		invocable = (Invocable) engine;
	}

	public final void setListener(DataAcceptListener listener) {
		this.listener = listener;
	}

	public final DataAcceptListener getListener() {
		return listener;
	}

	public final String getUrl(Info info) {
		if (getParams(info) == null)
			return info.getUrl();
		info.setUrl(String.format(info.getUrl(), getParams(info)));
		return info.getUrl();
	}

	public final Object[] getParams(Info info) {
		if (getListener() != null) {
			return getListener().getParams(info);
		}
		return null;
	}

	protected final void execute(String configFile, String funName) {
		try {
			JsoupConfig config = JsoupConfig.load(engine, configFile);
			if (getListener() != null)
				getListener().start();
			for (Info info : config.getInfos()) {
				Document document = Jsoup.connect(getUrl(info)).userAgent(userAgent()).get();
				Elements elements = null;
				if (info.getRootTag() != null && !"".equals(info.getRootTag())) {
					elements = document.select(info.getRootTag()).first().children();
				} else {
					elements = document.children();
				}
				for (Element ele : elements) {
					Map<String, Object> map = new HashMap<String, Object>();
					for (Column column : info.getColumns()) {
						Object data = null;
						if (column.getAttr() == null || "html".equals(column.getAttr()))
							data = ele.select(column.getTag()).html();
						else
							data = ele.select(column.getTag()).attr(column.getAttr());
						if (column.getFun() != null && !"".equals(column.getFun()))
							data = column.function(invocable, funName, data);
						map.put(column.getName(), data);
					}
					if (getListener() != null) {
						getListener().accept(info, map);
					}
				}
			}
			if (getListener() != null)
				getListener().stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String userAgent() {
		return DEFAULT_USER_AGENT;
	}

	public abstract void start();

}