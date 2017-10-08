package com.mathi.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {
			performLogin();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
	private static void performLogin(){
		String path = "/user/";
		String input = "{\"id\":\"1\",\"company\":\"1\",\"displayName\":\"test3\",\"email\":\"knavaneethan@gmail.com\",\"password\":\"pass\"}";
		restCall(getURI(),path,input);
	}
	private static String getURI(){
		return "http://localhost:8080/";
	}

	private static void restCall(String URI,String path,String input) {
		Client client = Client.create();
		WebResource webResource = client
				.resource(URI+path);		

		ClientResponse response = webResource.type("application/json")
				.post(ClientResponse.class, input);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	}

}
