package com.crossbridge.kernel.spring;

public class DriverDataSource {
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	private String url;
	private String username;
	private String password;
	private String driverClassName;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DriverDataSource =\n {\"\nurl\":\"");
		builder.append(url);
		builder.append("\", \nusername\":\"");
		builder.append(username);
		builder.append("\", \npassword\":\"");
		builder.append(password);
		builder.append("\", \ndriverClassName\":\"");
		builder.append(driverClassName);
		builder.append("\n}");
		return builder.toString();
	}
	

}
