package com.snicesoft.jsoupcrawler.core;

public class Crawler extends ICrawler {
	@Override
	public void start() {
		execute("jsoup.js", "fun");
	}
}
