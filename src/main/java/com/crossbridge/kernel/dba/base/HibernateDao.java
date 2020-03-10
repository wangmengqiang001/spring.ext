package com.crossbridge.kernel.dba.base;

import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateDao<T> implements IBaseDao<T> {
	
	
	HibernateDaoSupport daoSupport;
	

	/**
	 * @param daoSupport the daoSupport to set
	 */
	public void setDaoSupport(HibernateDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

	@Override
	public boolean save(Class<T> t) {
		// TODO Auto-generated method stub
		
		daoSupport.getHibernateTemplate().save(t);
		return true;
	}

	@Override
	public boolean remove(Class<T> t) {
		// TODO Auto-generated method stub
		daoSupport.getHibernateTemplate().delete(t);
		return true;
	}

	@Override
	public Collection<Class<T>> find(String query) {
		Collection<Class<T>> rs = null;
		
		
		rs = (Collection<Class<T>> )daoSupport.getHibernateTemplate().find(query);
		return rs;
	}

	@Override
	public boolean update(Class<T> t) {
		daoSupport.getHibernateTemplate().update(t);
		return false;
	}

}
