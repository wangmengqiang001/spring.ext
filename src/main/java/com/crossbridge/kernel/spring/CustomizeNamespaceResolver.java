package com.crossbridge.kernel.spring;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandler;
/*
 * 
 * */
import org.springframework.beans.factory.xml.NamespaceHandlerResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

public class CustomizeNamespaceResolver  implements NamespaceHandlerResolver { 
	
	public static final String DEFAULT_HANDLER_MAPPINGS_LOCATION = "classpath*:META-INF/*.handlers";


	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	/** ClassLoader to use for NamespaceHandler classes */
	private final ClassLoader classLoader;
	
	private String handlerMappingsLocation=null;	


	public CustomizeNamespaceResolver() {
		//super();
		this(null, DEFAULT_HANDLER_MAPPINGS_LOCATION);
	}
	public CustomizeNamespaceResolver(ClassLoader classLoader) {
		this(classLoader, DEFAULT_HANDLER_MAPPINGS_LOCATION);
	}
	
	public CustomizeNamespaceResolver(ClassLoader classLoader, String handlerMappingsLocation) {
		Assert.notNull(handlerMappingsLocation, "Handler mappings location must not be null");
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
		this.handlerMappingsLocation = handlerMappingsLocation;
	}



	private Map<String, Object> handlerMappings = null;	
	

	@Override
	public NamespaceHandler resolve(String namespaceUri) {
		// TODO Auto-generated method stub
		//NamespaceHandler nh = super.resolve(namespaceUri);
		System.out.println("resolve hander: "+ namespaceUri);
		Map<String, Object> handlerMappings = getHandlerMappings();
		Object handlerOrClassName = handlerMappings.get(namespaceUri);
		if (handlerOrClassName == null) {
			return null;
		}
		else if (handlerOrClassName instanceof NamespaceHandler) {
			return (NamespaceHandler) handlerOrClassName;
		}
		else {
			String className = (String) handlerOrClassName;
			try {
				Class<?> handlerClass = ClassUtils.forName(className, this.classLoader);
				if (!NamespaceHandler.class.isAssignableFrom(handlerClass)) {
					throw new FatalBeanException("Class [" + className + "] for namespace [" + namespaceUri +
							"] does not implement the [" + NamespaceHandler.class.getName() + "] interface");
				}
				NamespaceHandler namespaceHandler = (NamespaceHandler) BeanUtils.instantiateClass(handlerClass);
				namespaceHandler.init();
				handlerMappings.put(namespaceUri, namespaceHandler);
				return namespaceHandler;
			}
			catch (ClassNotFoundException ex) {
				throw new FatalBeanException("NamespaceHandler class [" + className + "] for namespace [" +
						namespaceUri + "] not found", ex);
			}
			catch (LinkageError err) {
				throw new FatalBeanException("Invalid NamespaceHandler class [" + className + "] for namespace [" +
						namespaceUri + "]: problem with handler class file or dependent class", err);
			}
		}
		
	}
	private Map<String, Object> getHandlerMappings() {
		if (this.handlerMappings == null) {
			synchronized (this) {
				if (this.handlerMappings == null) {
					try {
						Properties mappings =
								loadPropertiesPathPattern(this.handlerMappingsLocation, this.classLoader);
						if (logger.isDebugEnabled()) {
							logger.debug("Loaded NamespaceHandler mappings: " + mappings);
						}
						Map<String, Object> handlerMappings = new ConcurrentHashMap<String, Object>();
						CollectionUtils.mergePropertiesIntoMap(mappings, handlerMappings);
						this.handlerMappings = handlerMappings;
					}
					catch (IOException ex) {
						throw new IllegalStateException(
								"Unable to load NamespaceHandler mappings from location [" + this.handlerMappingsLocation + "]", ex);
					}
				}
			}
		}
		return this.handlerMappings;
	}
	protected Properties loadPropertiesPathPattern(String resourceName, ClassLoader cl) throws IOException {
		Assert.notNull(resourceName, "Resource name must not be null");
		ClassLoader clToUse = cl;
		if (clToUse == null) {
			clToUse = ClassUtils.getDefaultClassLoader();
		}
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] s=null;
	
		Properties mappings = new Properties();
		s = resolver.getResources(resourceName);
		for(Resource r:s) {
			System.out.println(r.getURI());			
			
			mappings.load(r.getInputStream());
			
			
			
		}
		System.out.println(mappings);	
		
	
		return mappings;
	}

}
