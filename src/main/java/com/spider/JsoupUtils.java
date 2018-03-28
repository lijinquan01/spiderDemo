package com.spider;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 * 爬虫工具方法集
 * <p>
 *
 * @author 2018/3/28 0028
 **/
public class JsoupUtils
{
	public void downloadDoc(String url,String name) throws MalformedURLException
	{
		URL httpUrl = new URL(url);
		File file = new File("D:/");
		try
		{
			FileUtils.copyURLToFile(httpUrl,new File(file+"//"+name));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
