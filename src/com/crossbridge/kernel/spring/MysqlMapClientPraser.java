package com.crossbridge.kernel.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Element;

public class MysqlMapClientPraser extends AbstractSimpleBeanDefinitionParser {  
	  
    /** 
     * element �൱�ڶ�Ӧ��elementԪ�� parserContext ������������ builder ���ڸñ�ǩ��ʵ�� 
     */  
    @Override  
    protected void doParse(Element element, ParserContext parserContext,  
            BeanDefinitionBuilder builder) {  
  
        // �ӱ�ǩ��ȡ����Ӧ������ֵ  
        String dbname = element.getAttribute("dbname");  
        String datasouceip = element.getAttribute("datasouceip");  
        String username = element.getAttribute("username");  
        String password = element.getAttribute("password");  
        String characterEncoding = element.getAttribute("characterEncoding");  
        String configLocation = element.getAttribute("configLocation");  
        final String driverClassName = "com.mysql.jdbc.Driver";  
  
	     System.out.println("dbname" + dbname);  
	     System.out.println("datasouceip" + datasouceip);  
	     System.out.println("username" + username);  
	     System.out.println("password" + password);  
	     System.out.println("characterEncoding" + characterEncoding);  
	     System.out.println("configLocation" + configLocation);  
  
        final StringBuffer url = new StringBuffer("jdbc:mysql://");  
        url.append(datasouceip).append("/").append(dbname).append(  
                "?useUnicode=true").append("&amp;").append(  
                "characterEncoding=" + characterEncoding).append(  
                "&amp;autoReconnect=true");  
  
        // ���� datasourceʵ��  
        DriverDataSource datasource = new DriverDataSource();  
        datasource.setDriverClassName(driverClassName);  
        // System.out.println(url.toString());  
        datasource.setUrl(url.toString());  
        datasource.setUsername(username);  
        datasource.setPassword(password);  
  
        // ����SqlMapClientFactoryBeanʵ��  
        SqlClientBean sqlmapclient = new SqlClientBean();  
        sqlmapclient.setDataSource(datasource);  
        sqlmapclient.setConfigLocation(configLocation);  
      
        // �Ѵ������ʵ����Ӧ�Ĵ����ñ�ǩ��ʵ�ֵ���Ӧ������  
        builder.addPropertyValue("dataSource", datasource);  
        builder.addPropertyValue("sqlMapClient", sqlmapclient);  
        
    }  
  
    @Override  
    protected Class getBeanClass(Element element) {  
        // ���ظñ�ǩ���������ʵ��,��������Ϊ�˴�����SqlMapClientTemplate����  
        return SqlMapClientInstance.class;  
    }  
  
}  
