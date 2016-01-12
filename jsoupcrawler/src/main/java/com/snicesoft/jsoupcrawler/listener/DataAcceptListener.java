package com.snicesoft.jsoupcrawler.listener;

import java.util.Map;

import com.snicesoft.jsoupcrawler.config.entity.Info;

public interface DataAcceptListener {
	void accept(Info info, Map<String, Object> data);

	void start();

	void stop();

	Object[] getParams(Info info);
}
