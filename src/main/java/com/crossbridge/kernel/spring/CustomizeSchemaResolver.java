package com.crossbridge.kernel.spring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;





public class CustomizeSchemaResolver implements EntityResolver {
	
	private static final Log logger = LogFactory
			.getLog(CustomizeSchemaResolver.class);

	/**
	 * springĬ��schema��Դ��Ĭ�ϴ�ŵ�
	 */
	public static final String SPRING_SCHEMAS_LOCATION = "classpath*:META-INF/*.schemas";
	private Map<String, Object> schemaMappings = null;

	private String mappingsLocation=null;

	private ClassLoader classLoader=null;	
	private EntityResolver defaultResolver = null;
	

	public CustomizeSchemaResolver(ResourceLoader resourceLoader) {
		//super();
		this(null, SPRING_SCHEMAS_LOCATION,resourceLoader);
		
	}
	public CustomizeSchemaResolver(ClassLoader classLoader,ResourceLoader resourceLoader) {
		this(classLoader, SPRING_SCHEMAS_LOCATION,resourceLoader);
	}
	
	public CustomizeSchemaResolver(ClassLoader classLoader, String mappingsLocation,ResourceLoader resourceLoader) {
		Assert.notNull(mappingsLocation, "Handler mappings location must not be null");
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
		this.mappingsLocation = mappingsLocation;
		this.defaultResolver = null; //(resourceLoader !=null? (new ResourceEntityResolver(resourceLoader)):null);
	}
	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
		logger.debug("���Խ���XMLԪ�ص� publicId [" + publicId + "] �Լ� systemId ["
				+ systemId + "]");

		if (systemId != null) {
			if(this.defaultResolver != null) {
				InputSource spring = defaultResolver.resolveEntity(publicId, systemId);
				if(spring != null) {
					return spring;
				}
			}
		
			Map<String, Object> sm = this.getSchemaResourceMappings();
			
			Resource resource = this.getSchemaResource(sm,systemId);

			// Ҫ�Ƕ����ǵĻ������׳��쳣
			if (sm == null||resource==null) {
				logger.error("����� [" + systemId + "] û���ҵ����ʵ�XSDԪ��");
				return null;
			}

			InputStream in = resource.getInputStream();
			InputSource source = new InputSource(in);
			source.setPublicId(publicId);
			source.setSystemId(systemId);

			logger.debug("�ɹ�����XMLԪ�ص� publicId [" + publicId + "] �Լ� systemId ["
					+ systemId + "]");

			return source;
		}
		return null;
	}




	private Map<String, Object> getSchemaResourceMappings() {
		if (this.schemaMappings == null) {
			synchronized (this) {
				if (this.schemaMappings == null) {
					try {
						Properties props =
								loadPropertiesPathPattern(this.mappingsLocation, this.classLoader);
						if (logger.isDebugEnabled()) {
							logger.debug("Loaded NamespaceHandler mappings: " + props);
						}
						Map<String, Object> mappings = new ConcurrentHashMap<String, Object>();
						CollectionUtils.mergePropertiesIntoMap(props, mappings);
						this.schemaMappings = mappings;
					}
					catch (IOException ex) {
						throw new IllegalStateException(
								"Unable to load NamespaceHandler mappings from location [" + this.schemaMappings + "]", ex);
					}
				}
			}
		}
		return this.schemaMappings;
	}
	
	private Resource getSchemaResource(Map<String, Object> sm, String systemId) {
		

		if (!sm.containsKey(systemId)) {
			return null;
		}

		Object schemaXSD = sm.get(systemId);

		// ���schemaXSDû�а����ָ������ô˵�������Լ�������schema������Ҫȥ������Bundle�в���
		if (schemaXSD instanceof Resource) {

			return (Resource)schemaXSD;
		} else {
			Resource resource=null;
		
			synchronized (this) {

				ResourceLoader resourceLoader = new DefaultResourceLoader();
				resource = resourceLoader.getResource((String) schemaXSD);
				if(resource != null) {
					sm.put(systemId, resource);
				}
			}
				
			// bundleNameAndSchema[1]��ζ��schema�ĵ�ַ
			return resource;
		}
		
	}


	Properties loadPropertiesPathPattern(String resourceName, ClassLoader cl) throws IOException {
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
