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
	 * ��Ҫ�ķ���Ľӿ���
	 */
	private String targetName = null;

	/**
	 * ������Ŀǰ���еķ����rankingֵ���������������Ϊ�˺��Ժ��µķ�����бȽ�
	 * 
	 * ��������£��ڷ���ע��󣬸�ֵҪ���ڻ����0������-1�Ŀ�����ֻ������:1. �����û�û�з��� 2. ��������һ������
	 */
	private long ranking = -1;

	/**
	 * filterԼ��
	 */
	private String filter = null;

	/**
	 * ������Ŀǰ���еķ����Ƿ���������Ҫ���filterԼ������Ҳ��Ϊ�˺��·�����бȽ�
	 */
	private boolean matchFilter = false;

	/**
	 * ������Ŀǰ���еķ����id
	 */
	private String serviceId = null;

	/**
	 * ������Ŀǰ���еķ����beanId
	 */
	private String serviceBeanId = null;

	/**
	 * ������Ŀǰ���еķ���������ģ����
	 */
	private String moduleNamefromCurrentService = null;

	/**
	 * ����������������ģ����
	 */
	private String ownerModuleName = null;

	/**
	 * Ҫ���õķֲ�ʽ�����Զ�˷����ַ
	 */
	private String[] remoteAddresses = null;

	/**
	 * ��������õ���С������0��ʾ����û�У�1��ʾ����Ҫ��һ����n��ʾ�Ǽ�������
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
