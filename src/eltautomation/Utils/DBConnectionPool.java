package eltautomation.Utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import snaq.db.ConnectionPool;

public class DBConnectionPool {
	private static ConnectionPool connpool = null;
	private static int conntimeout = 0;
	
	public static void createDBPool(ConfigFile cf)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException {
		
		DBConnectionPool.createDBPool(cf.getDBDriver(), cf.getDBUrl(), cf.getDBUser(),
				cf.getDBPword(), cf.getPoolName(), cf.getThreads(), cf.getPoolTimeout(), cf.getConnectionTimeout());
	}
	
	public static void createDBPool(String strdriver, String jdbcurl, String uname,
			String pword, String poolname, int minconn, int pooltimeout, int conntimeout)
			throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException {
		
		if (DBConnectionPool.connpool == null) {
			DBConnectionPool.conntimeout = (conntimeout * 1000);
			
			Driver dr = (Driver) Class.forName(strdriver).newInstance();
			DriverManager.registerDriver(dr);
			
			DBConnectionPool.setPool(new ConnectionPool(poolname, minconn,
					minconn*2, minconn*4, pooltimeout, jdbcurl, uname, pword));
		}
	}
	
	private static void setPool(ConnectionPool pool) {
		DBConnectionPool.connpool = pool;
		DBConnectionPool.connpool.setValidator(new LinkValidator());
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		
		conn = DBConnectionPool.connpool.getConnection(DBConnectionPool.conntimeout);
		if (conn == null) {
			throw new SQLException("Custom exception: Connection timed out.");
		}
		
		return conn;
	}
	
	public static void closePool() {
		if (DBConnectionPool.connpool != null) {
			DBConnectionPool.connpool.release();
			DBConnectionPool.connpool = null;
		}
	}
}
