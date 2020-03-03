package com.crossbridge.kernel.spring.annotation;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.BeanDefinitionStoreException;
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
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

public class ExtendedAnnotationScanner extends ClassPathBeanDefinitionScanner {

	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	private final String resourcePattern = DEFAULT_RESOURCE_PATTERN;
	private Class<? extends Annotation> typeFiltered = null;
	
	
	
	public ExtendedAnnotationScanner(BeanDefinitionRegistry registry,Class<? extends Annotation> type) {
		super(registry,false); //don't use the default filters
		this.typeFiltered=type;
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
	
	/**
	 * Scan the class path for candidate components.
	 * @param basePackage the package to check for annotated classes
	 * @return a corresponding Set of autodetected bean definitions
	 */
	public Set<ModuleReference> findReferenceAnnotations(String basePackage) {
		return findAnnotations(basePackage,ModuleReference.class);
	}


	protected <T extends Annotation> Set<T> findAnnotations(String basePackage, Class<T> annoType) {
		Set<T> candidates = new LinkedHashSet<T>();
		try {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
					resolveBasePackage(basePackage) + "/" + this.resourcePattern;
			Resource[] resources = ((ResourcePatternResolver)this.getResourceLoader()).getResources(packageSearchPath);
			boolean traceEnabled = logger.isTraceEnabled();
			for (Resource resource : resources) {
				if (traceEnabled) {
					logger.trace("Scanning " + resource);
				}
				if (resource.isReadable()) {
					try {
						
						MetadataReader reader = this.getMetadataReader(resource);
						String className = reader.getClassMetadata().getClassName();
						Class<?> bundleclass = this.getResourceLoader().getClassLoader().loadClass(className);
						
						
						Set<T> moduleRefs = listReferencesFields(bundleclass,annoType);
						
						if(!moduleRefs.isEmpty()) {
							candidates.addAll(moduleRefs);
						}
						
						
					}
					catch (Throwable ex) {
						throw new BeanDefinitionStoreException(
								"Failed to read candidate component class: " + resource, ex);
					}
				}
				else {
					if (traceEnabled) {
						logger.trace("Ignored because not readable: " + resource);
					}
				}
			}
		}
		catch (IOException ex) {
			throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
		}
		return candidates;
	}

	public <T extends Annotation> Set<T> listReferencesFields( Class<?> bundleclass,Class<T> annotation) {
		
		Set<T>  moduleRefs = new LinkedHashSet<T>();
		//FieldFilter ff;
		//retrieve all fields with the specified annotation 
		ReflectionUtils.doWithFields(bundleclass, new FieldCallback() {

			@Override
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				// TODO Auto-generated method stub
				 T ref = field.getAnnotation(annotation);
				//reader.g
				Assert.notNull(ref);
				moduleRefs.add(ref);
				
			
			}
			
		}, new FieldFilter() {

			@Override
			public boolean matches(Field field) {
				T ref = field.getAnnotation(annotation);
				return ref!=null;
			}});
		return moduleRefs;
	}
	@SuppressWarnings("rawtypes")
	public Set<ModuleReference> listReferences(Class bundleclass) {
		return listReferencesFields(bundleclass,ModuleReference.class );
	}
	


	private MetadataReader getMetadataReader(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}


	private void registerTypeFilter(){
        addIncludeFilter(new AnnotationTypeFilter(typeFiltered));
     }

}
