package com.crossbridge.kernel.dba;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.crossbridge.kernel.dba.bean.Topic;


public class TopicDaoImp implements TopicDao {
	
	@Autowired(required=true)
	@Qualifier(value="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	

	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
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
