package demo;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class JdbcPool implements DataSource {


	
	private static LinkedList<Connection> listConnections = new LinkedList<Connection>();

	static {
		// 类静态加载
		// 创建10个连接
		try {
			Connection conn = null;
			for (int i = 0; i < 10; i++) {

				conn = SqlHelper.GetConnection();
				listConnections.add(conn);
				System.out.println("获取到了链接" + conn);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if (listConnections.size() == 0)
			return null;
		final Connection conn = listConnections.removeFirst();
		System.out.println("数据库连接数大小是" + listConnections.size());
		return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub

						if (method.getName() == "close") {
							// 如果是关闭 则将链接还给连接池
							listConnections.add(conn);
							System.out.println(conn + "被还给listConnections数据库连接池了！！");
							System.out.println("listConnections数据库连接池大小为" + listConnections.size());
					          return null;
						} else {
							// 如果不是关闭 直接调用原方法
							return method.invoke(conn, args);
						}
					

					}
				});

	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
