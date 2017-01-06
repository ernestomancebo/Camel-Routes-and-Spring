package com.test.springinvokescamel;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CamelCaller implements ApplicationContextAware {

	private final static CamelCaller INSTANCE = new CamelCaller(); 
	private ApplicationContext applicationContext = null;
	
	private CamelCaller() {}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public Object callCamelRoute(String route, Object body) throws Exception {
		CamelContext camelContext = SpringCamelContext.springCamelContext(applicationContext, false);
		Exchange outEx = null;
		Object returnValue = null;
		try {
			ProducerTemplate template = camelContext.createProducerTemplate();
			Exchange exchange = new DefaultExchange(camelContext);
			exchange.getIn().setBody(body);
			
			outEx = template.send(route, exchange);
		} catch (CamelExecutionException ex) {
			ex.printStackTrace();
		}
		
		if (outEx != null) {
			returnValue = outEx.getOut().getBody();
		}
		
		return returnValue;
	}
	
	public static CamelCaller getInstance() {
		return INSTANCE;
	}
	
}
