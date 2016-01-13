package com.snicesoft.jsoupcrawler;

import java.util.Map;

import org.junit.Test;

import com.snicesoft.jsoupcrawler.config.entity.Info;
import com.snicesoft.jsoupcrawler.core.Crawler;
import com.snicesoft.jsoupcrawler.listener.BaseDataListener;

public class CrawlerTest {
	@Test
	public void test() {
		Crawler crawler = new Crawler();
		crawler.setListener(new BaseDataListener() {

			@Override
			public void stop() {
				System.out.println("解析结束");
			}

			@Override
			public void start() {
				System.out.println("解析开始");
			}

			@Override
			public void accept(Info info, int position, Map<String, Object> data) {
				System.out.println(position + ":" + info.getFinalUrl() + ":" + data);
			}

			@Override
			public void initParas() {
				addParam("oschina", new Object[] { "industry" });
				addParam("oschina", new Object[] { "project" });
			}
		});
		crawler.start();
	}
}
