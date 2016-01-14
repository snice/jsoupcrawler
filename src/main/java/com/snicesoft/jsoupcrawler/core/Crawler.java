package com.snicesoft.jsoupcrawler.core;

public class Crawler extends ICrawler {
	@Override
	public void start(ParseType parseType) {
		execute(parseType, "jsoup.js", "fun");
	}
}
