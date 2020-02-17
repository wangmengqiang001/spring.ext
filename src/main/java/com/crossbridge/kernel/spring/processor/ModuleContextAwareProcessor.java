package com.crossbridge.kernel.spring.processor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;


public class ModuleContextAwareProcessor implements BeanPostProcessor {
	
	private CustomizeWebApplicationContext appCtx = null;
	private static final Log logger = LogFactory
			.getLog(ModuleContextAwareProcessor.class);
	

	public ModuleContextAwareProcessor(CustomizeWebApplicationContext appCtx) {
		super();
		this.appCtx = appCtx;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// do nothing, just return
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof ModuleContextAware) {
			((ModuleContextAware) bean).setModuleContext(appCtx);

			logger.debug("Œ™ bean = " + beanName + " …Ë÷√¡ÀBundleContext");
		}
		return bean;
	}

}
