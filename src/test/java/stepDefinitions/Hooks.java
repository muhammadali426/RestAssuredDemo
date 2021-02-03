package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		
		
		 StepDefinition s = new StepDefinition();
		 
		 if(StepDefinition.place_id==null)
		 {
		 s.add_Place_PayLoad_with("Muhammad Ali", "English", "P D Khan");
		 s.user_calls_the_with_http_request("AddPlaceApi", "POST");
		 s.verify_the_place_id_created_maps_to_using("Muhammad Ali", "getPlaceApi");
		 }
	}
}
