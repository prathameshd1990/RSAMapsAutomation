package stepdefination;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import Utilties.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;

public class ValidatePlaceAPI extends Utility{
	RequestSpecification requestSpec;
	Response actuaResponse;
	TestDataBuild data = new TestDataBuild();
	static String placeID;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws FileNotFoundException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		requestSpec = given().spec(requestSpecification()).
				      body(data.addPlacePayload(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		if(httpMethod.equalsIgnoreCase("POST"))
			actuaResponse = requestSpec.when().post(resourceAPI.getResource());
		else if(httpMethod.equalsIgnoreCase("GET"))
			actuaResponse = requestSpec.when().get(resourceAPI.getResource());
	}
	
	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(200, actuaResponse.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(actuaResponse,key), value);
	}

	@Then("validate place_Id created maps to {string} using {string}")
	public void validate_place_id_created_maps_to_using(String expectedName, String resourceName) throws FileNotFoundException, IOException {
	   /*
	    *  1) Prepare requestSpec as Get call will require the place id as query params
	    */
		placeID = getJsonPath(actuaResponse, "place_id");
		requestSpec = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_with_http_request(resourceName,"GET");
		String actualName = getJsonPath(actuaResponse,"name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() throws FileNotFoundException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		requestSpec = given().spec(requestSpecification()).
				      body(data.deletePlacePayload(placeID));
	}

}
