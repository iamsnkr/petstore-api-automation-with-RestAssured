package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	User userPayload;
	private final static Logger logger = LogManager.getLogger(UserTests2.class);

	@BeforeClass
	public void setupDate() throws JsonProcessingException {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		// Java to JSON Serialization
		ObjectMapper obj = new ObjectMapper();
		String vaue = obj.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);
		System.out.println(vaue);
		
		// JSON to Java De-Serialization
		
		// obj.readValue(JOSN, ClassName.class);
	
		
		logger.info("UserPayload : " + userPayload.toString());
		logger.trace("This is a Trace");
        logger.debug("This is a Debug");
        logger.info("This is an Info");
        logger.warn("This is a Warn");
        logger.error("This is an Error");
        logger.fatal("This is a Fatal");

	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("=============BeforePostUser================");
		Response responce = UserEndPoint2.creatUser(userPayload);
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterPostUser================");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("=============BeforetestGetUserByName================");
		Response responce = UserEndPoint2.readUser(userPayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AftertestGetUserByName================");
	}

	@Test(priority = 3)
	public void testUpdateUser() {
		// Update using Playload data
		logger.info("=============BeforeUpdateUser================");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response responce = UserEndPoint2.updateUser(userPayload, userPayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);

		// Checking Data after updation
		Response responceAfterUpdate = UserEndPoint2.readUser(userPayload.getUsername());
		responceAfterUpdate.then().log().all();
		Assert.assertEquals(responceAfterUpdate.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterUpdateUser================");
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("=============BeforeDeleteUser================");
		Response responce = UserEndPoint2.deleteUser(userPayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterDeleteUser================");
	}
}
