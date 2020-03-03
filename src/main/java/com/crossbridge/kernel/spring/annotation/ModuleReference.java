package com.crossbridge.kernel.spring.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ModuleReference {
	String id();
	
	String targetName();
	
	String filter() ;
	
	String minOccurs() default "0";
	
	String remoteAddress() default "";
	
	String finder() default "";
}
