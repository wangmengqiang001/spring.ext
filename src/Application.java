import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.crossbridge.kernel.spring.config.ApplicationConfig;

public class Application {

	public static void main(String[] args) {
	    ApplicationContext applicationContext = new
	    ClassPathXmlApplicationContext("classpath:beans.xml");
	    
	    ApplicationConfig applicationConfig = (ApplicationConfig)
	    applicationContext.getBean("bridgeTestApplication");
	    
	    System.out.println("applicationConfig = "+applicationConfig);
	}

}
