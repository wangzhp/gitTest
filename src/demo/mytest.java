package demo;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;


public class mytest {
    
	 @Test
	 public void test() throws SQLException{
		  System.out.println("��ӡ��ʼ");
		  Connection  conn=	ConnectionPool.GetConnection();
	            String sql="insert into demo values('�����尡',11)";
	         int len=   conn.createStatement().executeUpdate(sql);
	         
			  System.out.println(len);
		
			
	 }
}
