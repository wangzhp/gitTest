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
		// �ྲ̬����
		// ����10������
		try {
			Connection conn = null;
			for (int i = 0; i < 10; i++) {

				conn = SqlHelper.GetConnection();
				listConnections.add(conn);
				System.out.println("��ȡ��������" + conn);
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
		System.out.println("���ݿ���������С��" + listConnections.size());
		return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub

						if (method.getName() == "close") {
							// ����ǹر� �����ӻ������ӳ�
							listConnections.add(conn);
							System.out.println(conn + "������listConnections���ݿ����ӳ��ˣ���");
							System.out.println("listConnections���ݿ����ӳش�СΪ" + listConnections.size());
					          return null;
						} else {
							// ������ǹر� ֱ�ӵ���ԭ����
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
