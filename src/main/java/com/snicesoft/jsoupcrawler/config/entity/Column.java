package com.snicesoft.jsoupcrawler.config.entity;

public class Column extends AbstractJS {
	private String tag;
	private String name;
	private String attr;
	private String fun;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getFun() {
		return fun;
	}

	public void setFun(String fun) {
		this.fun = fun;
	}

}
