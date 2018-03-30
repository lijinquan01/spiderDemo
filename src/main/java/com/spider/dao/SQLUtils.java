package com.spider.dao;

import com.spider.domain.Book;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * 数据库操作类
 * <p>
 *
 * @author 2018/3/29 0029
 **/
public class SQLUtils
{
	static DataSource ds = SQLSource.getDataScoure("jdbc:mysql://127.0.0.1:3306/book");
	static QueryRunner qr = new QueryRunner(ds);

	//批量插入数据库
	public static void bantchInsert(List<Book> books)
	{
		Object[][] params = new Object[books.size()][3];
		for (int i = 0; i < params.length; i++)
		{
			params[i][0] = books.get(i).getBookId();
			params[i][1] = books.get(i).getBookName();
			params[i][2] = books.get(i).getBookPrice();

		}
		try
		{
			qr.batch("insert into JD_book(book_id,book_name,book_price)" + "values(?,?,?)", params);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.print("导入了" + books.size() + "条数据");
	}
}
