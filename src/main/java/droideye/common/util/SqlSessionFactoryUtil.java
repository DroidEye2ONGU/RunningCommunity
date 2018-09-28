package droideye.common.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	private SqlSessionFactoryUtil() {
		
	}
	
	private static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			try {
				InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession(boolean autoCommit) {
		return getSqlSessionFactory().openSession(autoCommit);
	}
	
	public static SqlSession getSqlSession() {
		return getSqlSession(false);
	}
	
}
