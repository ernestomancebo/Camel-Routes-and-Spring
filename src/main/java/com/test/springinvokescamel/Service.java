package com.test.springinvokescamel;

import spark.servlet.SparkApplication;
import static spark.Spark.*;

public class Service implements SparkApplication {

	@Override
	public void init() {
		
		get("/ping", (req, res) -> {
			return "pong";
		});
		
		get("/api/count", (req, res) -> {
			String input = req.queryParams("input");
			Object count = CamelCaller.getInstance().callCamelRoute("direct:blogEntryTest", input);
			int countLength = Integer.parseInt(count.toString());
			
			return String.format("'%s' lenght: %d", input, countLength);
		});
		
	}

}
