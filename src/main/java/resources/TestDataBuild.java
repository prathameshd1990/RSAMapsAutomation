package resources;

import java.util.ArrayList;
import java.util.List;

import pojobuilder.AddPlaceRequest;
import pojobuilder.Location;

public class TestDataBuild {
	
	public AddPlaceRequest addPlacePayload(String name, String language, String address) {
		AddPlaceRequest addPlace = new AddPlaceRequest();
		addPlace.setAddress(address);
		addPlace.setName(name);
		addPlace.setAccuracy(50);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("https://rahulshettyacademy.com/");
		addPlace.setLanguage(language);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		addPlace.setTypes(myList);

		Location locate = new Location();
		locate.setLat(-38.383494);
		locate.setLng(33.427362);
		addPlace.setLocation(locate);
		return addPlace;
	}
	
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
