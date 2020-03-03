package com.crossbridge.kernel.spring.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import com.crossbridge.kernel.spring.ContextServiceHolder;
import com.crossbridge.kernel.spring.ModuleReferenceHolder;



public class ContextServiceScannerParser implements BeanDefinitionParser {

	private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		
		System.out.println("Found element contextservice-scanner");
		
		
		//beanDefinition
       // RootBeanDefinition bdf = toBeanHolder(element, parserContext);
        
     // TODO to create and execute the scanner to scan the annotations 
		// and register them into beanRegistry
        
		// invoke scanner to scan the base-package
        
    	String[] basePackages = StringUtils.tokenizeToStringArray(element.getAttribute(BASE_PACKAGE_ATTRIBUTE),
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);

		// Actually scan for bean definitions and register them.
    	ExtendedAnnotationScanner scanner = configureScanner(parserContext, element);
		Set<BeanDefinition> beanDefinitions = scanner.scanCandidates(basePackages);
		registerBeanDefinitions(parserContext.getReaderContext(), beanDefinitions);
		
		//fieldAnnotations = scanner.findReferenceAnnotations(basePackages);
		Set<ModuleReference> moduleRefs = scanner.scanReferenceAnnotations(basePackages);
		
		registerReferenceBeanDefinitions(parserContext.getReaderContext(),moduleRefs);

		return null;
		
		
	}

	private void registerReferenceBeanDefinitions(XmlReaderContext readerContext, Set<ModuleReference> moduleRefs) {
		for(ModuleReference ref: moduleRefs) {
			registerReference(readerContext.getRegistry(),ref);
		}
		
	}

	private void registerReference(BeanDefinitionRegistry registry, ModuleReference ref) {
		Assert.notNull(ref);
		
		if(registry.containsBeanDefinition(ref.id())) {
			return; //avoid registry the same bean
		}
		
		AbstractBeanDefinition refBeanDef = new RootBeanDefinition();
		
		refBeanDef.getPropertyValues().add("id",ref.id());
		refBeanDef.getPropertyValues().add("filter",ref.filter());
		
		refBeanDef.getPropertyValues().add("targetName",ref.targetName());
		refBeanDef.getPropertyValues().add("minOccurs",ref.minOccurs());
		refBeanDef.getPropertyValues().add("remoteAddresses",new String[]{ref.remoteAddress()});
		refBeanDef.getPropertyValues().add("finder",ref.finder());
				
		refBeanDef.setBeanClass(ModuleReferenceHolder.class);
		
		registry.registerBeanDefinition(ref.id(),refBeanDef);
		
	}

	protected void registerBeanDefinitions(XmlReaderContext readerContext, Set<BeanDefinition> beanDefinitions) {
		// TODO Auto-generated method stub
		for(BeanDefinition beandef:beanDefinitions ) {
			
			System.out.println(beandef.toString());
			//readerContext.getRegistry()
			registerBeanDefinition(readerContext.getRegistry(),beandef);
		}		
		
	}
	
	

	private void registerBeanDefinition(BeanDefinitionRegistry registry, BeanDefinition beanDef) {
		if( beanDef instanceof ScannedGenericBeanDefinition) {
			
			ScannedGenericBeanDefinition scanedBeanDef = (ScannedGenericBeanDefinition) beanDef;
			AnnotationMetadata meta = scanedBeanDef.getMetadata();

			AbstractBeanDefinition svcBeanDef = new RootBeanDefinition();
			String attributeName = "id";
			
			Method[] fs = ContextServices.class.getDeclaredMethods();
	        for(Method f:fs) {
	        	System.out.println("field: "+f.getName());
	        	addBeanDefinitionHolderAttribute(meta, svcBeanDef,f.getName());
	        }
			
			String id = (String)getAttributeFromAnnoMeta(meta, attributeName);
			/*
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "beanId");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "excludeBundle");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "id");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "includeBundle");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "level");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "name");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "ranking");
			addBeanDefinitionHolderAttribute(meta, svcBeanDef, "targetName");
			*/
			
		
			svcBeanDef.setBeanClass(ContextServiceHolder.class);		
			
			
			registry.registerBeanDefinition(id, svcBeanDef);
		}
	}

	protected void addBeanDefinitionHolderAttribute(AnnotationMetadata meta, AbstractBeanDefinition svcBeanDef,
			String attributeName) {
		Object value = getAttributeFromAnnoMeta(meta, attributeName);
		//svcBeanDef.setBeanClassName(value);
		if(value != null) {
			svcBeanDef.getPropertyValues().addPropertyValue(
					attributeName, value);
		}
	}


	protected Object getAttributeFromAnnoMeta(AnnotationMetadata meta, String attributeName) {
		
		return meta.getAnnotationAttributes(ContextServices.class.getName()).get(attributeName);
	}
	

	protected ExtendedAnnotationScanner configureScanner(ParserContext parserContext, Element element) {
		XmlReaderContext readerContext = parserContext.getReaderContext();

		boolean useDefaultFilters = false;
		

		// Delegate bean definition registration to scanner class.
		ClassPathBeanDefinitionScanner scanner = createScanner(readerContext, useDefaultFilters);
		scanner.setResourceLoader(readerContext.getResourceLoader());
		scanner.setEnvironment(parserContext.getDelegate().getEnvironment());
		scanner.setBeanDefinitionDefaults(parserContext.getDelegate().getBeanDefinitionDefaults());
		scanner.setAutowireCandidatePatterns(parserContext.getDelegate().getAutowireCandidatePatterns());		

		return (ExtendedAnnotationScanner) scanner;
	}

	private ClassPathBeanDefinitionScanner createScanner(XmlReaderContext readerContext, boolean useDefaultFilters) {
		// TODO Auto-generated method stub
		return new ExtendedAnnotationScanner(readerContext.getRegistry(),ContextServices.class);
	}

	private RootBeanDefinition toBeanHolder(Element element, ParserContext parserContext) {
		RootBeanDefinition bdf = new RootBeanDefinition();
        bdf.setBeanClass(CtxServiceHolderTest.class);
        bdf.setLazyInit(false);
        
        bdf.getPropertyValues().add("id", "serviceHolder");
        String val = element.getAttribute(BASE_PACKAGE_ATTRIBUTE);
        bdf.getPropertyValues().add("basePackage", val);         
        
        parserContext.getRegistry().registerBeanDefinition("serviceHolder", bdf);
		return bdf;
	}

}
