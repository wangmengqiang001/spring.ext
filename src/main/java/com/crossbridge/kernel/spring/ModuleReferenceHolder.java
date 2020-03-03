package com.crossbridge.kernel.spring;

import java.io.Serializable;

public class ModuleReferenceHolder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7932652193381107640L;

	public static final String MIN_OCCURS_0 ="0";  
	
	public static final String MIN_OCCURS_1 ="1";  
	
	public static final String MIN_OCCURS_N ="n";  

	/**
	 * bean-id
	 */
	private String id = null;

	/**
	 * 需要的服务的接口名
	 */
	private String targetName = null;

	/**
	 * 该引用目前持有的服务的ranking值，保留这个属性是为了和以后新的服务进行比较
	 * 
	 * 正常情况下，在服务注入后，该值要大于或等于0，等于-1的可能性只有两种:1. 该引用还没有服务 2. 该引用是一个集合
	 */
	private long ranking = -1;

	/**
	 * filter约束
	 */
	private String filter = null;

	/**
	 * 该引用目前持有的服务是否满足它所要求的filter约束，这也是为了和新服务进行比较
	 */
	private boolean matchFilter = false;

	/**
	 * 该引用目前持有的服务的id
	 */
	private String serviceId = null;

	/**
	 * 该引用目前持有的服务的beanId
	 */
	private String serviceBeanId = null;

	/**
	 * 该引用目前持有的服务所属的模块名
	 */
	private String moduleNamefromCurrentService = null;

	/**
	 * 该引用自身所属的模块名
	 */
	private String ownerModuleName = null;

	/**
	 * 要引用的分布式服务的远端服务地址
	 */
	private String[] remoteAddresses = null;

	/**
	 * 代表该引用的最小数量，0表示可以没有，1表示最少要有一个，n表示是集合引用
	 */
	private String minOccurs = null;
	
	private String finder = null;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getRanking() {
		return ranking;
	}

	public void setRanking(long ranking) {
		this.ranking = ranking;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public boolean isMatchFilter() {
		return matchFilter;
	}

	public void setMatchFilter(boolean matchFilter) {
		this.matchFilter = matchFilter;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getModuleNamefromCurrentService() {
		return moduleNamefromCurrentService;
	}

	public void setModuleNamefromCurrentService(
			String moduleNamefromCurrentService) {
		this.moduleNamefromCurrentService = moduleNamefromCurrentService;
	}

	public String getOwnerModuleName() {
		return ownerModuleName;
	}

	public void setOwnerModuleName(String ownerModuleName) {
		this.ownerModuleName = ownerModuleName;
	}

	public String[] getRemoteAddresses() {
		return remoteAddresses;
	}

	public void setRemoteAddresses(String[] remoteAddresses) {
		this.remoteAddresses = remoteAddresses;
	}

	public String getServiceBeanId() {
		return serviceBeanId;
	}

	public void setServiceBeanId(String serviceBeanId) {
		this.serviceBeanId = serviceBeanId;
	}

	public String getMinOccurs() {
		return minOccurs;
	}

	public void setMinOccurs(String minOccurs) {
		this.minOccurs = minOccurs;
	}

	public String getFinder() {
		return finder;
	}

	public void setFinder(String finder) {
		this.finder = finder;
	}
}
