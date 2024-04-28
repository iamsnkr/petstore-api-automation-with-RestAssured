package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import api.payload.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserEndPoint {

	// for CURD implemenataions
	/*
	 * RequestSpecification req = new RequestSpecBuilder()
	 * .setBaseUri(Routes.post_url) .setContentType(ContentType.JSON)
	 * .setAccept(ContentType.JSON) .build();
	 * 
	 */

	public static Response creatUser(User payload) {
		
	             Response responce = given()
	                    .accept(ContentType.JSON)
	                    .contentType(ContentType.JSON)
	                    .body(payload)
	                    .when()
	                    .post(Routes.post_url);
	             
	             return responce;
	                 
		
	}
	
	public static Response readUser(String username) {
		Response responce = given()
				                .pathParam("username", username) 
				                 .when()
				                 .get(Routes.get_url);

		return responce;

	}
	
	public static Response updateUser(User payload, String username) {
		Response responce = given().contentType(ContentType.JSON)
				                   .accept(ContentType.JSON)
				                   .pathParam("username", username) 
				                   .body(payload)
				                 .when()
				                 .put(Routes.update_url);

		return responce;

	}
	
	public static Response deleteUser(String username) {
		Response responce = given()
				                .pathParam("username", username) 
				                 .when()
				                 .delete(Routes.delete_url);

		return responce;

	}

}
