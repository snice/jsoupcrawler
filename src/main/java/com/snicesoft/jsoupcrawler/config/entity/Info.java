package com.snicesoft.jsoupcrawler.config.entity;

import java.util.List;

import com.snicesoft.jsoupcrawler.utils.JSUtils;

public class Info {
	private String id;
	private String url;
	private String rootTag;
	private Object data;
	private String finalUrl;// 最终执行的url

	public void setFinalUrl(String finalUrl) {
		this.finalUrl = finalUrl;
	}

	public String getFinalUrl() {
		return finalUrl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		this.finalUrl = url;
	}

	public String getRootTag() {
		return rootTag;
	}

	public void setRootTag(String rootTag) {
		this.rootTag = rootTag;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public List<Column> getColumns() {
		if (data == null)
			return null;
		List<Column> columns = JSUtils.getList(data, Column.class);
		return columns;
	}

	@Override
	public String toString() {
		return "Info [url=" + url + ", rootTag=" + rootTag + ", data=" + getColumns() + "]";
	}

}
