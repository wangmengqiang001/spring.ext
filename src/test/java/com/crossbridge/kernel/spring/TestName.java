package com.crossbridge.kernel.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.crossbridge.kernel.spring.config.ApplicationConfig;


class TestName {

	@Test
	void test() {
		ApplicationContext applicationContext = new
				ClassPathXmlApplicationContext("classpath:beans.xml");

		ApplicationConfig applicationConfig = (ApplicationConfig)
				applicationContext.getBean("bridgeTestApplication");

		System.out.println("applicationConfig = "+applicationConfig);
		assertEquals(applicationConfig.getName(),"bridgeTestApplication");
		assertEquals(applicationConfig.getOwner(),"leishu@glmapper");
	}
	@Test
	public void testCustomizeSql() {
		try {
		ApplicationContext applicationContext = new
				ClassPathXmlApplicationContext("classpath:beansql.xml");
		
		SqlMapClientInstance st = (SqlMapClientInstance)applicationContext.getBean("sqlMapClientTemplate");
		assertTrue(false);
		System.out.println("client template: "+ st);
		assertEquals(st.getDataSource().getUsername(),"root");

		assertEquals(st.getSqlMapClient().getConfigLocation(),"SqlMapCommonConfig.xml");
		}catch(BeanDefinitionParsingException e) { // no http\://www.crossbridge.com/schema/tags in spring.handlers
			assertTrue(true);
			
		}catch(XmlBeanDefinitionStoreException ex) {// no  http\://www.crossbridge.com/schema/tags/mysql.xsd in spring.schemas
			assertTrue(true);
		}
		
	}
	@Test
	public void testCustomizeResolver () {
		CustomizeWebApplicationContext applicationContext = new
				CustomizeWebApplicationContext("classpath:beansql.xml");
		
		//applicationContext.getResource("classpath:beans.xml");
		ApplicationConfig applicationConfig = (ApplicationConfig)
				applicationContext.getBean("bridgeTestApplication");

		System.out.println("applicationConfig = "+applicationConfig);
		assertEquals(applicationConfig.getName(),"bridgeTestApplication");
		assertEquals(applicationConfig.getOwner(),"leishu@glmapper");
	}

	
	
	@Test
	public void testCustomizeSqlResolver () {
		CustomizeWebApplicationContext applicationContext = new
				CustomizeWebApplicationContext("classpath:beansql.xml");
		
		SqlMapClientInstance st = (SqlMapClientInstance)applicationContext.getBean("sqlMapClientTemplate");
		
		assertEquals(st.getDataSource().getUsername(),"root");

		assertEquals(st.getSqlMapClient().getConfigLocation(),"SqlMapCommonConfig.xml");
		
	}
	@Test
	public void testPatternResource() throws IOException{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] s=null;
		try {
			s = resolver.getResources("classpath:META-INF/*.schemas");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties mappings = new Properties();
		for(Resource r:s) {
			System.out.println(r.getURI());	
			
			mappings.load(r.getInputStream());	
			
		}
		System.out.println(mappings);
		
		
	}
}
