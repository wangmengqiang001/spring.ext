package com.crossbridge.kernel.dba;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.crossbridge.kernel.dba.bean.Forum;

public class ForumDaoImp implements ForumDao {
	
	private SessionFactory sessionFactory;

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Forum> loadForums() {
		
		Collection<Forum> list=null;
		Session se = this.sessionFactory.openSession();
		Criteria cri = se.createCriteria(Forum.class);
		
		list = (Collection<Forum>)cri.list();
		
		se.close();
				
		return list;
	}

	@Override
	public void add(Forum forum) {
		Session se = this.sessionFactory.openSession();
		Transaction tx = se.beginTransaction();
		
		se.save(forum);
		
		tx.commit();
		se.close();

	}

}
