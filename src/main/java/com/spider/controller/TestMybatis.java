package com.spider.controller;

import com.spider.dao.SQLUtils;
import com.spider.domain.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.spider.controller.JsoupSpider.logger;

/**
 * <p>
 * Mybatis测试类
 * <p>
 *
 * @author 2018/3/30 0030
 **/
public class TestMybatis
{
	//获取数据库sqlSession链接
	public SqlSessionFactory getSqlSession() throws IOException
	{
		//mybatis配置文件
		String rescource = "Mybatis.xml";
		//穿件文件流
		InputStream is = Resources.getResourceAsStream(rescource);
		//创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}

	@Test
	public void testMybatis() throws IOException
	{
		//通过工厂得到sqlSession
		SqlSession ssf = this.getSqlSession().openSession();

		//Map<Object, Object> book = ssf.selectOne("test.findBookById", 15);
		Book book1 = ssf.selectOne("test.findBookById", 15);
		//logger.info("SQL"+book);
		System.out.println("书名：" + book1.getBookName() + book1.getBookPrice());
		//关闭占用的资源
		ssf.close();
	}
}
