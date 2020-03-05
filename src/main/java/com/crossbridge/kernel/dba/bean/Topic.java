package com.crossbridge.kernel.dba.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topic {
	protected Topic() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", topicDescription=" + topicDescription
				+ "]";
	}
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int topicId;
	public Topic(String topicName, String topicDescription) {
		super();
		this.topicName = topicName;
		this.topicDescription = topicDescription;
	}
	private String topicName;
	private String topicDescription;

}
