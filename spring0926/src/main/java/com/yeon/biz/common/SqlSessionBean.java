package com.yeon.biz.common;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionBean {
	// Mybatis로 DAO 클래스의 CRUD 메서드를 사용하려면, (BoardDAO3 만들어서 사용)
	// Mybatis에서 제공하는 SqlSession 객체를 사용해야한다!
	// -> Factory 패턴 이 먼저 필요해서 아래에 null로 미리 생성
	private static SqlSessionFactory sessionFactory=null;
	static {
		try {
			if(sessionFactory==null) {
				// 이곳의 작업은 스트림(파일을 읽어들이는 작업들)을 사용하고 있고
				// 외부요인으로 인한 에러가 자주 발생하기 때문에 try-catch로 예외처리함! ex) 메모리, 성능 등의 에러
				
				// builder를 이용해서 SSF 객체를 생성할 예정
				// builder는 Mybatis 설정파일(sql-map-config.xml)을 로딩하면서
				// SSf객체를 생성함
				
				// 설정파일 로딩을 위해 입력스트림(Reader)을 사용
				Reader reader=Resources.getResourceAsReader("sql-map-config.xml");
				sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		return sessionFactory.openSession();
	}

}
