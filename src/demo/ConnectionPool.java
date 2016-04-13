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
            //ͨ�����봴��C3P0���ݿ����ӳ�
            /*ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcstudy");
            ds.setUser("root");
            ds.setPassword("XDP");
            ds.setInitialPoolSize(10);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(20);*/
            
            //ͨ����ȡC3P0��xml�����ļ���������Դ��C3P0��xml�����ļ�c3p0-config.xml�������srcĿ¼��
            //ds = new ComboPooledDataSource();//ʹ��C3P0��Ĭ����������������Դ
           System.out.println("��ʼǰ");
        	ds = new ComboPooledDataSource("MySQL");//ʹ��C3P0��������������������Դ
         System.out.println("��ʼ��");
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
		
			    System.out.println("��ʼ��ȡ����");
			    Connection c= ds.getConnection();
			    System.out.println(c);
			    return c;
	
	}
}
