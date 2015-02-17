package database;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class BBDDmanager {
	private MysqlDataSource mysqlDataSource;
	
	public BBDDmanager(){
		 mysqlDataSource = new MysqlDataSource();
		 mysqlDataSource.setServerName("localhost");
		 mysqlDataSource.setDatabaseName("practica1");
		 mysqlDataSource.setPort(3306);
		 mysqlDataSource.setUser("users");
		 mysqlDataSource.setPassword("users");
		
	}
	public MysqlDataSource getMysqlDataSource() {
		return mysqlDataSource;
	}
	public void setMysqlDataSource(MysqlDataSource mysqlDataSource) {
		this.mysqlDataSource = mysqlDataSource;
	}
	
	
}

 