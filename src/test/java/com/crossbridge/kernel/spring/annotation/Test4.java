package com.crossbridge.kernel.spring.annotation;


public class Test4 {
	@ModuleReference(filter = "abc", id = "publicmsg", targetName = "com.crossbridge.kernel.spring.annotation.ContextServiceScannerParser")
	public String msg;
	
	@ModuleReference(filter = "hello", id = "privateHello", targetName = "com.crossbridge.kernel.spring.annotation.Test3")
	private String showHello;
	
	@ModuleReference(filter = "showmsg", id = "protectedmsg", targetName = "com.crossbridge.kernel.spring.annotation.Test3")
	protected String showmsg;
	
	@ModuleReference(filter = "showmsg", id = "showmsgpackage", targetName = "com.crossbridge.kernel.spring.annotation.Test3")
	 String showmsgpackage;
	
}
