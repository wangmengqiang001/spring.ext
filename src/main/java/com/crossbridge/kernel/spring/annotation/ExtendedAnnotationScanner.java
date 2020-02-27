package com.crossbridge.kernel.spring.annotation;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;

public class ExtendedAnnotationScanner extends ClassPathBeanDefinitionScanner {
	private Class type = null;
	public ExtendedAnnotationScanner(BeanDefinitionRegistry registry,Class<? extends Annotation> type) {
		super(registry,false); //don't use the default filters
		this.type=type;
		registerTypeFilter();
	}
	
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
//		// TODO Auto-generated method stub
		return super.doScan(basePackages);
//		Assert.notEmpty(basePackages, "At least one base package must be specified");
//		Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<BeanDefinitionHolder>();
//		for (String basePackage : basePackages) {
//			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
//			for (BeanDefinition candidate : candidates) {
//				ScopeMetadata scopeMetadata = this.scopeMetadataResolver.resolveScopeMetadata(candidate);
//				candidate.setScope(scopeMetadata.getScopeName());
//				String beanName = this.beanNameGenerator.generateBeanName(candidate, this.registry);
//				if (candidate instanceof AbstractBeanDefinition) {
//					postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName);
//				}
//				if (candidate instanceof AnnotatedBeanDefinition) {
//					AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
//				}
//				if (checkCandidate(beanName, candidate)) {
//					BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate, beanName);
//					definitionHolder = AnnotationConfigUtils.applyScopedProxyMode(scopeMetadata, definitionHolder, this.registry);
//					beanDefinitions.add(definitionHolder);
//					registerBeanDefinition(definitionHolder, this.registry);
//				}
//			}						
//		}
//		return beanDefinitions;
	}

	private void registerTypeFilter(){
        addIncludeFilter(new AnnotationTypeFilter(type));
     }

}
