package com.crossbridge.kernel.spring;

public class SqlMapClientInstance {
	public DriverDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DriverDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public SqlClientBean getSqlMapClient() {
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlClientBean sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	private DriverDataSource dataSource;
	private SqlClientBean sqlMapClient;
	@Override
	public String toString() {
		return "SqlMapClientInstance =\n {\"dataSource\":\"" + dataSource + "\", \nsqlMapClient\":\"" + sqlMapClient
				+ "\n}";
	}
	

}
