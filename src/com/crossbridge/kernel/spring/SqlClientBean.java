package com.crossbridge.kernel.spring;

public class SqlClientBean {
private String configLocation;
public String getConfigLocation() {
	return configLocation;
}
public void setConfigLocation(String configLocation) {
	this.configLocation = configLocation;
}
public DriverDataSource getDataSource() {
	return dataSource;
}
public void setDataSource(DriverDataSource dataSource) {
	this.dataSource = dataSource;
}
private DriverDataSource dataSource;
@Override
public String toString() {
	return "SqlClientBean =\n {\"\n\tconfigLocation\":\"" + configLocation + "\", \n\tdataSource\":\"" + dataSource + "\n\t}";
}
}
