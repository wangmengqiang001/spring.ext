package com.crossbridge.kernel.spring.processor;

import com.crossbridge.kernel.spring.CustomizeWebApplicationContext;

public class BModuleContextAware implements ModuleContextAware {

	CustomizeWebApplicationContext moduleContext;
	@Override
	public void setModuleContext(CustomizeWebApplicationContext appCtx) {
		// TODO Auto-generated method stub
		this.moduleContext=appCtx;
		

	}
	@Override
	public CustomizeWebApplicationContext getModuleContext() {
		
		return moduleContext;
	}

}
