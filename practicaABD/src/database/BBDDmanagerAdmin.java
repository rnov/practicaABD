package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class BBDDmanagerAdmin {
private MysqlDataSource mysqlDataSource;
	
	public BBDDmanagerAdmin() {
		 mysqlDataSource = new MysqlDataSource();
		 mysqlDataSource.setServerName("localhost");
		 mysqlDataSource.setDatabaseName("practica1");
		 mysqlDataSource.setPort(3306);
		 mysqlDataSource.setUser("administrador");
		 mysqlDataSource.setPassword("administrador");
		
	}
	public MysqlDataSource getMysqlDataSource() {
		return mysqlDataSource;
	}
	public void setMysqlDataSource(MysqlDataSource mysqlDataSource) {
		this.mysqlDataSource = mysqlDataSource;
	}
}
