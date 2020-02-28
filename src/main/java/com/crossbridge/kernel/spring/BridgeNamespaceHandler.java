package com.crossbridge.kernel.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.crossbridge.kernel.spring.annotation.ContextServiceScannerParser;

public class BridgeNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		 registerBeanDefinitionParser("application", 
			        new ApplicationBeanDefinitionParser());
		 this.registerBeanDefinitionParser("contextservice-scan", new ContextServiceScannerParser());

	}

}
