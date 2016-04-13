package demo;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;


public class mytest {
    
	 @Test
	 public void test() throws SQLException{
		  System.out.println("打印开始");
		  Connection  conn=	ConnectionPool.GetConnection();
	            String sql="insert into demo values('王大叔啊',11)";
	         int len=   conn.createStatement().executeUpdate(sql);
	         
			  System.out.println(len);
		
			
	 }
}
