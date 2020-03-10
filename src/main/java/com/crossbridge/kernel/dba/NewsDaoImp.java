package com.crossbridge.kernel.dba;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.crossbridge.kernel.dba.bean.News;

/**
 * It access db via sessionfactory 
 * 
 * 
 * 
 * */
public class NewsDaoImp implements NewsDao {

	private SessionFactory sessionFactory=null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Collection<News> loadNews() {
		// TODO Auto-generated method stub
		Session session =this.sessionFactory.openSession();
		
		Criteria cri = session.createCriteria(News.class);
		List<News> list = cri.list();
		
		
		session.close();
		return list;
	}
	@Override
	public void add(News news) {
		// TODO Auto-generated method stub
		Session session =this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		tx.begin();
		session.saveOrUpdate(news);
		tx.commit();
		session.close();
	}

}
