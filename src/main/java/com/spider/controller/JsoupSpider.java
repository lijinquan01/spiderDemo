package com.spider.controller;

import com.spider.JsoupUtils;
import com.spider.dao.SQLUtils;
import com.spider.domain.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 主函数启动爬虫
 * <p>
 *
 * @author 2018/3/28 0028
 **/
public class JsoupSpider
{
	static final Log logger = LogFactory.getLog(JsoupSpider.class);

	public static void main(String[] args)
	{
		//初始化一个httpclient
		HttpClient client = new DefaultHttpClient();
		//我们要爬取的一个地址，这里可以从数据库中抽取数据，然后利用循环，可以爬取一个URL队列
		String url = "http://search.jd.com/search?keyword=Python&enc=utf-8&qrst=1&rt=1&stop=1&book=y&vt=2&wq=Python&page=3&s=61&click=0";
		//抓取的数据

		List<Book> bookdatas = null;
		try
		{
			bookdatas = JsoupUtils.URLParser(client, url);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		//使用log4j缺省环境
		BasicConfigurator.configure();
		//循环输出抓取的数据
		for (Book jd : bookdatas)
		{
			logger.info("bookID:" + jd.getBookId() + "\t" + "bookPrice:" + jd.getBookPrice() + "\t"
					+ "bookName:" + jd.getBookName());
		}
		//将抓取的数据插入数据库
		SQLUtils.bantchInsert(bookdatas);
	}
}
