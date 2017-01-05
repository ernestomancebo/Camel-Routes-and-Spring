package com.test.springinvokescamel;

import spark.servlet.SparkApplication;
import static spark.Spark.*;

public class Service implements SparkApplication {

	@Override
	public void init() {
		
		get("/api/count", (req, res) -> {
			return "testing";
		});
		
	}

}
