package com.crossbridge.kernel.spring.processor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;

@TestMethodOrder(OrderAnnotation.class)
class TestProcessors {

	CustomizeWebApplicationContext applicationContext = null;

	@BeforeEach
	void setup() {
		applicationContext = new
				CustomizeWebApplicationContext("classpath:beanprocessor.xml");
	}
	

	@Order(0)
	@Test	
	void testAProcessor() {


		//applicationContext.getResource("classpath:beans.xml");
		ModuleContextAware applicationConfig = (ModuleContextAware)
				applicationContext.getBean("aprocessor");

		System.out.println("applicationConfig = "+applicationConfig);
		CustomizeWebApplicationContext x = applicationConfig.getModuleContext();
		assertNotNull(x);

	}
	
	@Order(10)
	@Test	
	void testBProcessor() {

		//applicationContext.getResource("classpath:beans.xml");
		ModuleContextAware applicationConfig = (ModuleContextAware)
				applicationContext.getBean("bprocessor");

		System.out.println("applicationConfig = "+applicationConfig);
		CustomizeWebApplicationContext x = applicationConfig.getModuleContext();
		assertNotNull(x);

	}

	@Order(20)
	@Test
	void testBothProcessor() {


		ModuleContextAware a = (ModuleContextAware)
				applicationContext.getBean("aprocessor");

		
		CustomizeWebApplicationContext x = a.getModuleContext();
		assertNotNull(x);
		
		ModuleContextAware b = (ModuleContextAware)
				applicationContext.getBean("bprocessor");

		System.out.println("applicationConfig = "+x);
		CustomizeWebApplicationContext y = b.getModuleContext();
		assertEquals(x,y);

	}


	@AfterEach
	void teardown() {
		applicationContext = null;
	}

}
