package com.crossbridge.kernel.spring;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.NamespaceHandlerResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.xml.sax.EntityResolver;

import com.crossbridge.kernel.spring.processor.ModuleContextAwareProcessor;

public class CustomizeWebApplicationContext extends AbstractXmlApplicationContext {
	//AbstractRefreshableApplicationContext {


	
	public CustomizeWebApplicationContext(String configLocations) {
		super();
		setConfigLocations(new String[] {configLocations});
		// TODO Auto-generated constructor stub
		this.refresh();
	}



	@Override
	protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// TODO Auto-generated method stub
		super.postProcessBeanFactory(beanFactory);
		beanFactory.addBeanPostProcessor(new ModuleContextAwareProcessor(this));
	}

	@Override
	protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {


		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
				beanFactory);
		beanDefinitionReader.setResourceLoader(this);
		NamespaceHandlerResolver namespaceHandlerResolver = this.handlerNameSpaceResolver();
		//new CustomizeNamespaceResolver();
		beanDefinitionReader.setNamespaceHandlerResolver(namespaceHandlerResolver);
		
		//beanDefinitionReader.s
		EntityResolver entityResolver = this.handleEntityResolver();
		beanDefinitionReader.setEntityResolver(entityResolver);
		
		
		initBeanDefinitionReader(beanDefinitionReader);
		loadBeanDefinitions(beanDefinitionReader);

	}
	
	protected NamespaceHandlerResolver handlerNameSpaceResolver() {
		
		return new CustomizeNamespaceResolver();
	}

	protected EntityResolver handleEntityResolver() {
		
		return new CustomizeSchemaResolver(this);
	}

	
	

}
