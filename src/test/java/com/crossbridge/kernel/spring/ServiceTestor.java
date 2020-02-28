package com.crossbridge.kernel.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crossbridge.kernel.spring.annotation.ContextServices;

class ServiceTestor {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		//TeacherService t = new TeacherService();
		ContextServices x =TeacherService.class.getAnnotation(ContextServices.class);
		assertTrue(x != null);
		//assertEquals(1,x.length);
//		for(Annotation y:x) {
//			System.out.println("Annotation: "+ y);
//		}
		System.out.println("x :" + x);
	}

}
