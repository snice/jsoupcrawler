package com.snicesoft.jsoupcrawler;

import java.util.Map;

import org.junit.Test;

import com.snicesoft.jsoupcrawler.config.entity.Info;
import com.snicesoft.jsoupcrawler.core.Crawler;
import com.snicesoft.jsoupcrawler.listener.DataAcceptListener;

public class CrawlerTest {
	@Test
	public void test() {
		Crawler crawler = new Crawler();
		crawler.setListener(new DataAcceptListener() {
			@Override
			public void accept(Info info, Map<String, Object> data) {
				System.out.println(data);
			}

			@Override
			public Object[] getParams(Info info) {
				Object[] params = null;
				switch (info.getId()) {
				case "oschina":
					params = new Object[] { "industry" };
					break;
				default:
					break;
				}
				return params;
			}

			@Override
			public void start() {
				System.out.println("解析开始");
			}

			@Override
			public void stop() {
				System.out.println("解析结束");
			}
		});
		crawler.start();
	}
}
