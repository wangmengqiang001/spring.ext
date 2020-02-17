package com.crossbridge.kernel.spring.processor;

import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;

public class AModuleContextAware implements ModuleContextAware {

	CustomizeWebApplicationContext moduleContext;
	@Override
	public void setModuleContext(CustomizeWebApplicationContext appCtx) {
		
		this.moduleContext=appCtx;	

	}
	@Override
	public CustomizeWebApplicationContext getModuleContext() {
		
		return moduleContext;
	}
}
