package com.crossbridge.kernel.spring.annotation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

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

	
	/*
	 * 测试通过扫描类的field, 找到带有指定的annotation field, 把annotation 带回
	 * 
	 */
	@Test
	void testReference() throws ClassNotFoundException {
		String clsName="com.crossbridge.kernel.spring.annotation.Test3";
		
		Class<?> x = this.getClass().getClassLoader().loadClass(clsName);
		
		ExtendedAnnotationScanner scanner= new ExtendedAnnotationScanner(new DefaultListableBeanFactory(), ModuleReference.class);
		
		Set<ModuleReference> y = scanner.listReferences(x);
		
		assertTrue(y.size() ==1);
		
		for(ModuleReference e:y) {
			System.out.println("MR:" + e);
			
			assertEquals("com.crossbridge.kernel.spring.annotation.ContextServiceScannerParser",e.targetName());
		}
		
		
		
	}
	@Test
	void testTwoReference() throws ClassNotFoundException {
		String clsName="com.crossbridge.kernel.spring.annotation.Test4";
		
		Class<?> x = this.getClass().getClassLoader().loadClass(clsName);
		
		ExtendedAnnotationScanner scanner= new ExtendedAnnotationScanner(new DefaultListableBeanFactory(), ModuleReference.class);
		
		Set<ModuleReference> y = scanner.listReferences(x);
		
		assertEquals(4,y.size());
		
		for(ModuleReference e:y) {
			System.out.println("MR:" + e);
						
		}
		
		
		
	}

}
