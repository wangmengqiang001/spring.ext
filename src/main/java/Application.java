import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class Application {

	public static void main(String[] args) {
//	    ApplicationContext applicationContext = new
//	    ClassPathXmlApplicationContext("classpath:beans.xml");
//	    
//	    ApplicationConfig applicationConfig = (ApplicationConfig)
//	    applicationContext.getBean("bridgeTestApplication");
//	    
//	    System.out.println("applicationConfig = "+applicationConfig);
		
//		CustomizeWebApplicationContext applicationContext = new
//				CustomizeWebApplicationContext("classpath:beansql.xml");
//		
//		SqlMapClientInstance st = (SqlMapClientInstance)applicationContext.getBean("sqlMapClientTemplate");
//		
//		assertEquals(st.getDataSource().getUsername(),"root");
//
//		assertEquals(st.getSqlMapClient().getConfigLocation(),"SqlMapCommonConfig.xml");
		
		try {
			new Application().testPatternResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testPatternResource() throws IOException{
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] s=null;
		try {
			s = resolver.getResources("classpath*:META-INF/*.schemas");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties mappings = new Properties();
		for(Resource r:s) {
			System.out.println(r.getURI());	
			
			mappings.load(r.getInputStream());	
			
		}
		System.out.println(mappings);
		
		
	}

}
