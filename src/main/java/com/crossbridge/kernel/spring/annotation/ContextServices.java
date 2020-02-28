package com.crossbridge.kernel.spring.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE })
public @interface ContextServices {
	public String name() default "provider";
	public int level() ;
	
	String id() default "" ;
	
	String beanId() default "";
	
	String targetName() default "";
	
	String ranking() default "";
	
	String includeBundle() default "";
	
	String excludeBundle() default "";
	

}
