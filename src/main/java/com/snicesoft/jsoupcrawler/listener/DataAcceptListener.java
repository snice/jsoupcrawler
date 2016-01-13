package com.snicesoft.jsoupcrawler.listener;

import java.util.Map;

import com.snicesoft.jsoupcrawler.config.entity.Info;

public interface DataAcceptListener {
	long getCount(Info info);

	void accept(Info info, int position, Map<String, Object> data);

	void start();

	void stop();

	Object[] getParams(Info info, int position);
}
