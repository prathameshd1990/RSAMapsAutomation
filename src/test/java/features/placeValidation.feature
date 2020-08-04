Feature: Validate Place API

@AddPlace @Regression
Scenario Outline: Validate if place is being added successfully
        Given Add Place Payload with "<name>" "<language>" "<address>"
        When User calls "addPlaceAPI" with "POST" http request
        Then The API call got success with status code 200
        And "status" in response body is "OK"
        And "scope" in response body is "APP"
        And validate place_Id created maps to "<name>" using "getPlaceAPI"
        
Examples:
	| name 			| language		| address 				|
	| Tata House	| English-IN	| Juhu Scheme, Mumbai	|
#	| Ambani House	| Hindi-IN	 	| Pali Hill, Mumbai 	|
#	| Galaxy Apt.	| Tamil-IN		| Bandra Recl., Mumbai	|

@DeletePlace @Regression
Scenario: Validate if delete place functionality is working
	Given Delete place payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then The API call got success with status code 200
    And "status" in response body is "OK"