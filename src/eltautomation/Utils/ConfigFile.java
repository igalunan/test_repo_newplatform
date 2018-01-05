package eltautomation.Utils;

public class ConfigFile {
	private String db_user, db_pword, db_url, db_driver, pool_name;
	private int threads, pool_timeout, connection_timeout;
	
	public String getDBUser() {
		return this.db_user;
	}
	
	public String getDBPword() {
		return this.db_pword;
	}
	
	public int getThreads() {
		return this.threads;
	}
	
	public int getPoolTimeout() {
		return this.pool_timeout;
	}
	
	public int getConnectionTimeout() {
		return this.connection_timeout;
	}
	
	public String getDBUrl() {
		return this.db_url;
	}
	
	public String getDBDriver() {
		return this.db_driver;
	}
	
	public String getPoolName() {
		return this.pool_name;
	}
}
