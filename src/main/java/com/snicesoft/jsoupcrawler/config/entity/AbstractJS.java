package com.snicesoft.jsoupcrawler.config.entity;

import javax.script.Invocable;
import javax.script.ScriptException;

public class AbstractJS implements IJS {
	protected Object jsObject;

	@Override
	public final void setObject(Object object) {
		jsObject = object;
	}

	@Override
	public final Object getObject() {
		return jsObject;
	}

	@Override
	public Object function(Invocable invocable, String name, Object... args) {
		try {
			return invocable.invokeMethod(getObject(), name, args);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return null;
	}

}
