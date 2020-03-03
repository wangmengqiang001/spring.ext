package com.crossbridge.kernel.spring.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.context.support.GenericApplicationContext;

import com.crossbridge.kernel.spring.ContextServiceHolder;
import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;

/**
 * @author wmqiang
 *
 */
class TestAnnotationScanner {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	// TODO to execute doScan, but not register the bean definition to registry 
	@Test 
	public void testScan() {

		String BASE_PACKAGE = "com.crossbridge.kernel.spring.annotation";
		GenericApplicationContext context = new GenericApplicationContext();

		ExtendedAnnotationScanner scanner = new ExtendedAnnotationScanner(context,ContextServices.class);

		// scanner.set
		Set<BeanDefinition> setBeansH =scanner.scanCandidates(BASE_PACKAGE);

		assertEquals(2,setBeansH.size());

		try {
			String[] names = context.getBeanDefinitionNames();
			for(BeanDefinition bf: setBeansH) {
				System.out.println("bean: " + bf);
			}
			BeanDefinition y = context.getBeanDefinition("teacherOrigin");
			System.out.println("definition :"+y);


		} catch (NoSuchBeanDefinitionException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			assertTrue(true);
		}
		assertTrue(true);
	}
	@Test
	public void testSimpleScan() {
		String BASE_PACKAGE = "com.crossbridge.kernel.spring.annotation";
		GenericApplicationContext context = new GenericApplicationContext();

		ExtendedAnnotationScanner scanner = new ExtendedAnnotationScanner(context,ContextServices.class);
		//   ExtendedAnnotationScanner myClassPathDefinitonScanner = new ExtendedAnnotationScanner(context, ContextServices.class);
		// 注册过滤器
		//scanner.registerTypeFilter();
		//int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);

		Set<BeanDefinition> setBeans = scanner.findCandidateComponents(BASE_PACKAGE);

		assertEquals(2, setBeans.size());
		BeanDefinition[] aryb = setBeans.toArray(new BeanDefinition[1]);

		BeanDefinition bean = aryb[1];
		System.out.println("find bean: "+bean);
		assertEquals("com.crossbridge.kernel.spring.annotation.TeacherServiceSecond",bean.getBeanClassName());

		context.refresh();
		// String[] beanDefinitionNames = context.getBeanDefinitionNames();

		// System.out.println(beanCount);
		//	        for (String beanDefinitionName : beanDefinitionNames) {
		//	            System.out.println(beanDefinitionName);
		//	        }


		try {
			BeanDefinition y = context.getBeanDefinition("teacherServiceSecond");
			System.out.println("definition :"+y);
		} catch (NoSuchBeanDefinitionException e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			//e.printStackTrace();
		}



		//	        for(BeanDefinitionHolder b:setBeans) {
		//	        	System.out.println("bean:" + b);
		//	        	System.out.println("define:"+b.getBeanDefinition());
		//	        }
	}
	/******************************************************************************
	 * 测试增加了扫描base-package的处理后对指定的路径下扫描 ContextService 注解
	 * 
	 * 
	 * */

	@Test
	void testAnnotationScanner() {
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
