package com.crossbridge.kernel.exts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

}
