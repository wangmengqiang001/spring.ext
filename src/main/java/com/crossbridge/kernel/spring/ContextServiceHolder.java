package com.crossbridge.kernel.spring;

import java.io.Serializable;

public class ContextServiceHolder implements Serializable {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

	private int level = 0;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContextServiceHolder =\n {\"level\":\"" + level + "\", name\":\"" + name + "\", id\":\"" + id
				+ "\", beanId\":\"" + beanId + "\", targetName\":\"" + targetName + "\", ranking\":\"" + ranking
				+ "\", includeBundle\":\"" + includeBundle + "\", excludeBundle\":\"" + excludeBundle
				+ "\", distributed\":\"" + distributed + "}";
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name="provider";
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4415857368065337878L;

	/**
	 * bean-id
	 */
	private String id = null;

	/**
	 * ͬ��id
	 */
	private String beanId = null;

	/**
	 * �ӿ���
	 */
	private String targetName = null;

	/**
	 * ���ȼ�
	 */
	private String ranking = null;

	/**
	 * �÷����ܴ�����ģ��
	 */
	private String includeBundle = null;

	/**
	 * �÷��񲻴�����ģ��
	 */
	private String excludeBundle = null;

	/**
	 * �Ƿ��Ƿֲ�ʽ����
	 */
	private String distributed = "false";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getIncludeBundle() {
		return includeBundle;
	}

	public void setIncludeBundle(String includeBundle) {
		this.includeBundle = includeBundle;
	}

	public String getExcludeBundle() {
		return excludeBundle;
	}

	public void setExcludeBundle(String excludeBundle) {
		this.excludeBundle = excludeBundle;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getDistributed() {
		return distributed;
	}

	public void setDistributed(String distributed) {
		this.distributed = distributed;
	}
}
