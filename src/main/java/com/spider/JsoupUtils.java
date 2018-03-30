package com.spider;

import com.spider.domain.Book;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 爬虫工具方法集
 * <p>
 *
 * @author 2018/3/28 0028
 **/
public class JsoupUtils
{
	public void downloadDoc(String url, String name) throws MalformedURLException
	{
		URL httpUrl = new URL(url);
		File file = new File("D:/");
		try
		{
			FileUtils.copyURLToFile(httpUrl, new File(file + "//" + name));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param client
	 * @param url
	 * @return
	 */
	public static List<Book> URLParser(HttpClient client, String url) throws IOException
	{
		List<Book> books = new ArrayList<>();
		//获取请求的返回值
		HttpResponse response = HttpUtils.getRawHtml(client, url);
		//获取请求代码
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200)
		{
			String entity = EntityUtils.toString(response.getEntity(), "utf-8");
			books = JsoupUtils.getData(entity);
			EntityUtils.consume(response.getEntity());
		} else
		{
			//不是正常请求代码就消耗实体
			EntityUtils.consume(response.getEntity());
		}
		return books;

	}

	/**
	 * 转换数据类型利用Jsoup获取数据并封装
	 *
	 * @param entity
	 * @return
	 */
	public static List<Book> getData(String entity)
	{
		List<Book> bookList = new ArrayList<>();
		Document doc = Jsoup.parse(entity);
		Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
		for (Element e : elements)
		{
			//获取一本书的数据并封装成一个实体放入list中
			String bookID = e.attr("data-sku");
			String bookPrice = e.select("div[class=p-price]").select("strong").select("i").text();
			String bookName = e.select("div[class=p-name]").select("em").text();
			Book book = new Book();
			book.setBookId(bookID);
			book.setBookName(bookName);
			book.setBookPrice(bookPrice);
			bookList.add(book);
		}
		return bookList;
	}
}
