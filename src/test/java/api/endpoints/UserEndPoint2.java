package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndPoint2 {

	// for CURD implemenataions
	/*
	 * RequestSpecification req = new RequestSpecBuilder()
	 * .setBaseUri(Routes.post_url) .setContentType(ContentType.JSON)
	 * .setAccept(ContentType.JSON) .build();
	 * 
	 */
	
	
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Loads Routes.properties file
	    return routes;
	}

	public static Response creatUser(User payload) {
		String post_url =getURL().getString("post_url");
	             Response responce = given()
	                    .accept(ContentType.JSON)
	                    .contentType(ContentType.JSON)
	                    .body(payload)
	                    .when()
	                    .post(post_url);
	             
	             return responce;
	                 
		
	}
	
	public static Response readUser(String username) {
		
		String get_url =getURL().getString("get_url");
		Response responce = given()
				                .pathParam("username", username) 
				                 .when()
				                 .get(get_url);

		return responce;

	}
	
	public static Response updateUser(User payload, String username) {
		String update_url =getURL().getString("update_url");
		Response responce = given().contentType(ContentType.JSON)
				                   .accept(ContentType.JSON)
				                   .pathParam("username", username) 
				                   .body(payload)
				                 .when()
				                 .put(update_url);

		return responce;

	}
	
	public static Response deleteUser(String username) {
		String delete_url =getURL().getString("delete_url");
		Response responce = given()
				                .pathParam("username", username) 
				                 .when()
				                 .delete(delete_url);

		return responce;

	}

}
