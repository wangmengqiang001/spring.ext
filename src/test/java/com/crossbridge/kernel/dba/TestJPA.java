package com.crossbridge.kernel.dba;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crossbridge.kernel.dba.bean.Topic;

class TestJPA {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
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

}
