package com.crossbridge.kernel.dba;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.crossbridge.kernel.dba.bean.Forum;
import com.crossbridge.kernel.dba.bean.News;
import com.crossbridge.kernel.dba.bean.Topic;

class TestNews {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
/**
 * 测试案例：直接通过sessionFactory 进行数据库的操作 
 * 
 * 
 * 
 * 
 */
	@Test
	void test() {

		try {
			ApplicationContext applicationContext = new
					ClassPathXmlApplicationContext("classpath:beansdb.xml");

			NewsDao sf  = (NewsDao)applicationContext.getBean("newsDao");


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

		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 测试: 通过HibernateTemplate 访问数据库	
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	void testTmplate() {

		try {
			ApplicationContext applicationContext = new
					ClassPathXmlApplicationContext("classpath:beansdb.xml");

			NewsDao sf  = (NewsDao)applicationContext.getBean("newsDaoTmpl");


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

		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * 测试: 通过HibernateTemplate 访问数据库	
	 * 	
	 * 
	 */
	@Test
	void testTmplateForum() {

		try {
			ApplicationContext applicationContext = new
					ClassPathXmlApplicationContext("classpath:beansdb.xml");

			ForumDao sf  = (ForumDao)applicationContext.getBean("forumTmpl");
			
			assertNotNull(sf);
			
			//test save and list
			for(int n=0; n< 10; n++) {
			Forum f = new Forum();
			f.setForumName("Hello");
			f.setForumDesc("forum description");
			
			sf.add(f);
			
			}
			Collection<Forum> c = sf.loadForums();
			
			assertTrue(c.size() > 0);
			
			for(Forum m: c) {
				System.out.println("Forum : "+ m);
				
			}
			
			
			
			
			
		}catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	//topicDao
	@Test
	void testTmplateTopic() {

		try {
			ApplicationContext applicationContext = new
					ClassPathXmlApplicationContext("classpath:beansdb.xml");

			TopicDao sf  = (TopicDao)applicationContext.getBean("topicDao");
			
			assertNotNull(sf);
			
			//test save and list
			for(int n=0; n< 10; n++) {
			Topic f = new Topic("title","descrpiton");
			
			sf.add(f);
			
			}
			Collection<Topic> c = sf.load();
			
			assertTrue(c.size() > 0);
			
			for(Topic m: c) {
				System.out.println("Forum : "+ m);				
			}
			
			
			
			
			
		}catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
