package com.spider.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

/**
 * <p>
 * log4j测试类
 * <p>
 *
 * @author 2018/3/29 0029
 **/
public class log4j
{
	static final Log logger = LogFactory.getLog(log4j.class);

	public static void main(String args[])
	{
		System.out.println("hello");
		//自动使用缺省的log4j环境

		// BasicConfigurator.configure();
		logger.info("hello word");
		logger.info("warn");
	}
}
