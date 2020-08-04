package Utilties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utility {
	public static RequestSpecification request;
	public static ResponseSpecification response;
	
	public RequestSpecification requestSpecification() throws IOException,FileNotFoundException {
		
		/*
		 * If it is a parameterized data driven test the each time it will 
		 * create  new log file so check if the file is already create then 
		 * avoid the code of creating file and simply return the request object
		 * and also declare request and response specification static so that
		 * it will not create another instance
		 */
		if(request == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			request = new RequestSpecBuilder().setBaseUri(getGlobalParameter("baseURI")).
					   addFilter(RequestLoggingFilter.logRequestTo(log)).
					   addFilter(ResponseLoggingFilter.logResponseTo(log)).
					   addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					   .build();
			return request;
		}
		return request;
	}
	
	public ResponseSpecification responseSpecification() {
		if(response == null) {
			response = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
					   expectStatusCode(200).build();
			return response;
		}
		return response;
	}
	
	public String getGlobalParameter(String key)throws IOException,FileNotFoundException {
		Properties configuration = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
				"\\src\\main\\java\\resources\\global.properties");
		configuration.load(fis);
		return configuration.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String jsonReponse = response.asString();
		JsonPath json = new JsonPath(jsonReponse);
		return json.get(key).toString();
	}

}
