package com.crossbridge.kernel.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.crossbridge.kernel.spring.config.ApplicationConfig;

public class ApplicationBeanDefinitionParser implements BeanDefinitionParser {

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext)  {
		//beanDefinition
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(ApplicationConfig.class);
        beanDefinition.setLazyInit(false);
        //½âÎöid
        String id = element.getAttribute("id");
        beanDefinition.getPropertyValues().add("id", id);
        //½âÎöname
        beanDefinition.getPropertyValues().add("name",
        element.getAttribute("name"));
        //½âÎöversion
        beanDefinition.getPropertyValues().add("version",
        element.getAttribute("version"));
        //owner
        beanDefinition.getPropertyValues().add("owner",
        element.getAttribute("owner"));
        //organization
        beanDefinition.getPropertyValues().add("organization",
        element.getAttribute("organization"));
    
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        return beanDefinition;


	}

}
