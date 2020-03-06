package com.crossbridge.kernel.dba;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		for(int n=0; n< 100; n++) {
			Topic topic=new Topic("HelloTopic","topDescription");
			mgr.persist(topic);
			assertTrue(mgr.contains(topic));
		}
		
		Topic t = mgr.find(Topic.class,1);
		assertNotNull(t);
	}

}
