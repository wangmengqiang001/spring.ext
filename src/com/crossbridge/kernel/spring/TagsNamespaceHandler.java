package com.crossbridge.kernel.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class TagsNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		 //自定义标签中的element标签名为client解析注册使用MysqlMapClientPraser进行.  
        registerBeanDefinitionParser("client", new MysqlMapClientPraser());  

	}

}
