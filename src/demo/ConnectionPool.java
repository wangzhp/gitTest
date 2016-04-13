package demo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
    
	private static ComboPooledDataSource ds; 
	private static ConnectionPool pool;
	static{
        try{
            //通过代码创建C3P0数据库连接池
            /*ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcstudy");
            ds.setUser("root");
            ds.setPassword("XDP");
            ds.setInitialPoolSize(10);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(20);*/
            
            //通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
            //ds = new ComboPooledDataSource();//使用C3P0的默认配置来创建数据源
           System.out.println("开始前");
        	ds = new ComboPooledDataSource("MySQL");//使用C3P0的命名配置来创建数据源
         System.out.println("开始后");
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
	
	public static final  ConnectionPool GetInstance(){
		    if(pool==null){
		    	pool=new ConnectionPool();
		    }
		    return pool;
	}
	
	public static     Connection  GetConnection() throws SQLException{
		
			    System.out.println("开始获取链接");
			    Connection c= ds.getConnection();
			    System.out.println(c);
			    return c;
	
	}
}
