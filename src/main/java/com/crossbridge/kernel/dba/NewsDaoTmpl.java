package com.crossbridge.kernel.dba;

import java.util.Collection;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.crossbridge.kernel.dba.bean.News;

public class NewsDaoTmpl implements NewsDao {
	
	private HibernateTemplate hibernateTemplate=null;
	

	@Override
	public Collection<News> loadNews() {
		// TODO Auto-generated method stub
		return this.hibernateTemplate.find("from News");
	}

	@Override
	public void add(News news) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(news);

	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
