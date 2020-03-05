package com.crossbridge.kernel.dba;

import java.util.Collection;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.crossbridge.kernel.dba.bean.Topic;


public class TopicDaoImp implements TopicDao {
	
	private HibernateTemplate hibernateTemplate;
	

	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void add(Topic a) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(a);

	}

	@Override
	public Collection<Topic> load() {
		
		return (Collection<Topic>)this.getHibernateTemplate().find("from Topic");
	}

}
