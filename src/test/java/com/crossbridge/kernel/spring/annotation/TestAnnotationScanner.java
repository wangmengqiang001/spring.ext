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
	        
	        assertEquals(1,setBeansH.size());
	        
	        try {
				BeanDefinition y = context.getBeanDefinition("teacherServiceSecond");
				System.out.println("definition :"+y);
			} catch (NoSuchBeanDefinitionException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				assertTrue(false);
			}
	        assertTrue(true);
	}
	 @Test
	    public void testSimpleScan() {
	        String BASE_PACKAGE = "com.crossbridge.kernel.spring.annotation";
	        GenericApplicationContext context = new GenericApplicationContext();
	        
	        ExtendedAnnotationScanner scanner = new ExtendedAnnotationScanner(context,ContextServices.class);
	     //   ExtendedAnnotationScanner myClassPathDefinitonScanner = new ExtendedAnnotationScanner(context, ContextServices.class);
	// ×¢²á¹ýÂËÆ÷
	        //scanner.registerTypeFilter();
	        //int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
	        
	        Set<BeanDefinition> setBeans = scanner.findCandidateComponents(BASE_PACKAGE);
	        
	        assertEquals(1, setBeans.size());
	        BeanDefinition[] aryb = setBeans.toArray(new BeanDefinition[1]);
	        
	       BeanDefinition bean = aryb[0];
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
				e.printStackTrace();
			}
	        
	        
	        
//	        for(BeanDefinitionHolder b:setBeans) {
//	        	System.out.println("bean:" + b);
//	        	System.out.println("define:"+b.getBeanDefinition());
//	        }
	    }


}
