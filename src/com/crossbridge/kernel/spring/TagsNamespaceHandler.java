package com.crossbridge.kernel.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class TagsNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		 //�Զ����ǩ�е�element��ǩ��Ϊclient����ע��ʹ��MysqlMapClientPraser����.  
        registerBeanDefinitionParser("client", new MysqlMapClientPraser());  

	}

}
