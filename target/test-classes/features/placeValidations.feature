Feature: Validating AddPlace API's
 
 @AddPlace @Regression
 Scenario Outline: Verify  if Place is being Successfully added using AddPlaceAPI
    Given Add Place PayLoad with "<name>" "<language>" "<address>"
    When User calls the "AddPlaceApi" with "Post" http request 
    Then the API call got success with the code 200
    And "status" in the response body is "OK"
    And "scope" in the response body is "APP"
    And verify the place_id created maps to "<name>" using "getPlaceApi" 
    
Examples: 
    |name    |language |address                           |
    |AAhouse |English  |Mohalla Karimabad Pind Dadan Khan |
#   |ABhouse |Urdu     |Mohalla Karimabad Pind Dadan Khan | 



@DeletePlace @Regression
Scenario: Verify the Delete Place functionality is working
    Given DeletePlace PayLoad
    When User calls the "deletePlaceApi" with "POST" http request 
    Then the API call got success with the code 200
    And "status" in the response body is "OK"
    
    
    