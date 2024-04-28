package api.test;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoint;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	Faker faker;
	User userPayload;
    private final static Logger logger = LogManager.getLogger(UserTests.class);

	@BeforeClass
	public void setupDate() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	

      


      
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
		Response responce = UserEndPoint.creatUser(userPayload);
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterPostUser================");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("=============BeforetestGetUserByName================");
		Response responce = UserEndPoint.readUser(userPayload.getUsername());
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
		Response responce = UserEndPoint.updateUser(userPayload, userPayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);

		// Checking Data after updation
		Response responceAfterUpdate = UserEndPoint.readUser(userPayload.getUsername());
		responceAfterUpdate.then().log().all();
		Assert.assertEquals(responceAfterUpdate.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterUpdateUser================");
	}

	@Test(priority = 4)
	public void testDeleteUserByName() {
		logger.info("=============BeforeDeleteUser================");
		Response responce = UserEndPoint.deleteUser(userPayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterDeleteUser================");
	}
}
