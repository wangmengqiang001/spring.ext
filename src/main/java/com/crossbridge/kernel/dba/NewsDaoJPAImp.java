package com.crossbridge.kernel.dba;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import com.crossbridge.kernel.dba.bean.News;

public class NewsDaoJPAImp implements NewsDao {

	private EntityManagerFactory emf;

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Collection<News> loadNews() {
		EntityManager mgr = emf.createEntityManager();
		List<News> ls = null;
			try {
				TypedQuery<News> query = mgr.createQuery("from News", News.class);
				ls = query.getResultList();
			} finally {
				mgr.close();
			}
		return ls;
	}

	@Override
	public void add(News news) {
		EntityManager mgr = emf.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		try {
			tx.begin();
			mgr.persist(news);
			mgr.flush();
			tx.commit();
	
		} finally {
			
			
			mgr.close();
		}
		

	}

}
