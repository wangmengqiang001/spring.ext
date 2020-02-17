package com.crossbridge.kernel.spring.processor;

import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;

public interface ModuleContextAware {

	void setModuleContext(CustomizeWebApplicationContext appCtx);

	CustomizeWebApplicationContext getModuleContext();

}
