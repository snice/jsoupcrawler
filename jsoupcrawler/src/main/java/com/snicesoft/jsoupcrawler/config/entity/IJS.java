package com.snicesoft.jsoupcrawler.config.entity;

public interface IJS {
	void setObject(Object object);

	Object getObject();

	Object function(javax.script.Invocable invocable, String name, Object... args);
}
