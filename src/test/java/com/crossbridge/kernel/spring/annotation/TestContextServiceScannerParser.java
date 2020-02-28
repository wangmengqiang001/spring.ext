package com.crossbridge.kernel.spring.annotation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crossbridge.kernel.spring.ContextServiceHolder;
import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;
import com.crossbridge.kernel.spring.config.ApplicationConfig;

class TestContextServiceScannerParser {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		CustomizeWebApplicationContext applicationContext = new
				CustomizeWebApplicationContext("classpath:beans.xml");
		
		//applicationContext.getResource("classpath:beans.xml");
		

		String[] names = applicationContext.getBeanDefinitionNames();
		
		for(String n:names) {
			System.out.println("bean defined: " + n);
			Object be = applicationContext.getBean(n);
			
			System.out.println("Bean Object = "+be);
			
		}
		
		ContextServiceHolder bean = (ContextServiceHolder)
				applicationContext.getBean("serviceHolder");
		
		System.out.println("ContextServiceHolder = "+bean);
		assertEquals("teacherProvider",
				bean.getName());
		
	}

}
