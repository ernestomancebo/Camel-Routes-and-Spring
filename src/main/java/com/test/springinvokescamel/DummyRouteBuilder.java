package com.test.springinvokescamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class DummyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:blogEntryTest")
			.process(new Processor() {

				@Override
				public void process(Exchange exchange) throws Exception {
					
				}
				
			})
		.end();
	}

}