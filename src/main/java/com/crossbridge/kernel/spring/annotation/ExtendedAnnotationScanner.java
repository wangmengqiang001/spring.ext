package com.crossbridge.kernel.spring.annotation;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;

public class ExtendedAnnotationScanner extends ClassPathBeanDefinitionScanner {
	private Class type = null;
	
	
	public ExtendedAnnotationScanner(BeanDefinitionRegistry registry,Class<? extends Annotation> type) {
		super(registry,false); //don't use the default filters
		this.type=type;
		registerTypeFilter();
	}
	
	
	public Set<BeanDefinition> scanCandidates(String... basePackages) {
//		// TODO Auto-generated method stub
		//return super.doScan(basePackages);
		Assert.notEmpty(basePackages, "At least one base package must be specified");
		Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
			beanDefinitions.addAll(candidates);
		
		}
		return beanDefinitions;
	}

	private void registerTypeFilter(){
        addIncludeFilter(new AnnotationTypeFilter(type));
     }

}
