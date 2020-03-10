package com.crossbridge.kernel.dba;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.crossbridge.kernel.dba.bean.News;
import com.crossbridge.kernel.dba.bean.Topic;

class TestJPA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/****
	 * Use JPA in hibernate (version 3.6.10.Final)
	 * 
	 * 
	 * 
	 **/
	@Test
	void testJPA() {
		EntityManagerFactory per = Persistence.createEntityManagerFactory("myJPAUnit");
		EntityManager mgr = per.createEntityManager();
		EntityTransaction tx = mgr.getTransaction();
		tx.begin();
		for(int n=0; n< 100; n++) {
			Topic topic=new Topic("HelloTopic","topDescription");

			mgr.persist(topic);
			assertTrue(mgr.contains(topic));
		}
		mgr.flush();
		tx.commit();
		//Topic t = mgr.find(Topic.class,1);
		String qlString="from Topic";
		TypedQuery<Topic> query = mgr.createQuery(qlString, Topic.class);
		List<Topic> list = query.getResultList();
		assertNotNull(list);
		assertTrue(list.size() > 0);

		for(Topic tp: list) {
			System.out.println("tp= "+ tp);
		}		

		mgr.close();
		per.close();
	}
	@Test
	void testJPADao() {

		ApplicationContext applicationContext = new
				ClassPathXmlApplicationContext("classpath:beansdb.xml");

		NewsDao sf  = (NewsDao)applicationContext.getBean("newsdaoJpa");


		assertNotNull(sf);
		final int maxCount = 10;
		for(int n = 0; n< maxCount; n++) {
			//sf.add(news);
			News news = new News();
			//news.setId(100+1);
			news.setTitle("标题e ");
			news.setContant("这是内容");
			news.setCreatedate(new Date());
			sf.add(news);
		}

		Collection<News> list = sf.loadNews();
		//assertEquals(maxCount,list.size());
		assertTrue(list.size()>= maxCount);

		for(News n: list) {
			System.out.println(n);
		}




}

}
