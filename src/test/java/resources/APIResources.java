package resources;

//enum is the special class in the java which has collection of methods or constants 

public enum APIResources {

	
	AddPlaceApi("/maps/api/place/add/json"),
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	private String resource; 

	APIResources(String resource) {
		this.resource =resource;
	}
	
	public String getResource() {
		return resource;
	}
	
	
}
