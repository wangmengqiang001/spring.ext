package com.crossbridge.kernel.dba;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.crossbridge.kernel.dba.bean.News;

public class NewsDaoImpSupport extends HibernateDaoSupport implements NewsDao {

	@Override
	public Collection<News> loadNews() {
		// TODO Auto-generated method stub
		//this.f
		return this.getHibernateTemplate().loadAll(News.class);
	}

	@Override
	public void add(News news) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(news);
	}

}
