package stepdefination;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws FileNotFoundException, IOException {
		//write that will give place id
		// execute this code only when place id is null
		ValidatePlaceAPI place = new ValidatePlaceAPI();
		if(ValidatePlaceAPI.placeID == null) {
			String name = "Jamie Den";
			place.add_place_payload_with(name, "Spanish", "Barcelona,Spain");
			place.user_calls_with_http_request("addPlaceAPI", "POST");
			place.validate_place_id_created_maps_to_using(name,"getPlaceAPI");
		}
	}

}
