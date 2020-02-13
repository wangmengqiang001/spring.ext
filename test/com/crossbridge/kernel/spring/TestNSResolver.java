package com.crossbridge.kernel.spring;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

class TestNSResolver {

	@Test
	void testLoadPropertiesPathPattern() {
		//fail("Not yet implemented");
		
		CustomizeNamespaceResolver rs = new CustomizeNamespaceResolver();
		
		try {
			Properties prp = rs.loadPropertiesPathPattern(CustomizeNamespaceResolver.DEFAULT_HANDLER_MAPPINGS_LOCATION, null);
			assertNotNull(prp);
			assertTrue(prp.values().size()>=2);
			assertTrue(prp.values().contains("com.crossbridge.kernel.spring.TagsNamespaceHandler"));
			assertTrue(prp.values().contains("com.crossbridge.kernel.spring.BridgeNamespaceHandler"));
			
		} catch (IOException e) {
			assertFalse(true);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void testLoadPropertiesPathPattern_schema() {
		//fail("Not yet implemented");
		
		CustomizeSchemaResolver rs = new CustomizeSchemaResolver(null);
		
		try {
			Properties prp = rs.loadPropertiesPathPattern(CustomizeSchemaResolver.SPRING_SCHEMAS_LOCATION, null);
			assertNotNull(prp);
			
			assertTrue(prp.values().size()>=2);
			assertTrue(prp.values().contains("META-INF/application.xsd"));
			assertTrue(prp.values().contains("META-INF/mysql.xsd"));
			
		} catch (IOException e) {
			assertFalse(true);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
