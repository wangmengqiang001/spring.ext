package com.crossbridge.kernel.dba.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_FORUM")
public class Forum implements Serializable {
	
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Forum [forumId=" + forumId + ", forumName=" + forumName + ", forumDesc=" + forumDesc + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "FORUM_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int forumId;
    
    @Column(name = "FORUM_NAME")
    private String forumName;
    
    @Column(name = "FORUM_DESC")
    private String forumDesc;

	/**
	 * @return the forumId
	 */
	public int getForumId() {
		return forumId;
	}

	/**
	 * @param forumId the forumId to set
	 */
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	/**
	 * @return the forumName
	 */
	public String getForumName() {
		return forumName;
	}

	/**
	 * @param forumName the forumName to set
	 */
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	/**
	 * @return the forumDesc
	 */
	public String getForumDesc() {
		return forumDesc;
	}

	/**
	 * @param forumDesc the forumDesc to set
	 */
	public void setForumDesc(String forumDesc) {
		this.forumDesc = forumDesc;
	}

}
