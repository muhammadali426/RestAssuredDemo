package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;

public class StepDefinition extends utils {

	
	RequestSpecification	res;
	ResponseSpecification	resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;

	
	
	
	@Given("Add Place PayLoad with {string} {string} {string}")
	public void add_Place_PayLoad_with(String name, String language, String address) throws IOException {
	    
		res =given().spec(requestSpecification()).body(data.AddPlacePayload(name,language,address));
	}
	
	
	@When("User calls the {string} with {string} http request")
	public void user_calls_the_with_http_request(String resource, String httpMethod) {
	
		
		// constructor will pass of ApiResources enum class with value of resource which you pass
	APIResources resourceAPI =	APIResources.valueOf(resource);
	System.out.println(resourceAPI.getResource());
	
		  resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON). build();
		  
		  if(httpMethod.equalsIgnoreCase("POST"))
		  {
		  response = res.when().post(resourceAPI.getResource());
		  }
		  else if(httpMethod.equalsIgnoreCase("GET"))
		  {
		  response = res.when().get(resourceAPI.getResource());
		  }
		  
		
	}
	@Then("the API call got success with the code {int}")
	public void the_API_call_got_success_with_the_code(Integer int1) {
	    
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String key, String ExpectedValue) {
		
		
		
		assertEquals(getJsonPath(response, key),ExpectedValue );
		
	    
	    
	}
	
	@Then("verify the place_id created maps to {string} using {string}")
	public void verify_the_place_id_created_maps_to_using(String name, String resource) throws IOException {
	 
		//requestSpec
		
		place_id = getJsonPath(response, "place_id");
		res =given().spec(requestSpecification()).queryParam("place_id",place_id );
		user_calls_the_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
        assertEquals(actualName, name);                
	
	}
	
	
	@Given("DeletePlace PayLoad")
	public void deleteplace_PayLoad() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

	
	

	
		

}
