package com.crossbridge.kernel.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class BridgeNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		 registerBeanDefinitionParser("application", 
			        new ApplicationBeanDefinitionParser());

	}

}
