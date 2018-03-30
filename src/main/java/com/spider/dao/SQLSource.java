package com.spider.dao;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

/**
 * <p>
 * 数据库链接信息
 * <p>
 *
 * @author 2018/3/29 0029
 **/
public class SQLSource
{
	public static DataSource getDataScoure(String url)
	{
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUsername("root");
		bds.setPassword("root");
		bds.setUrl(url);
		return bds;
	}
}
