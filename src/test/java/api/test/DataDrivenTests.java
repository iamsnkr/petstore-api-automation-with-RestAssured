package api.test;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {

	// userID userName firstName lastName email password phone
	
	private final static Logger logger = LogManager.getLogger(DataDrivenTests.class);


	@Test(priority = 1, dataProvider = "userdataprovider", dataProviderClass = api.utilities.DataProviders.class)
	public void testPostUser(Map<String, String> userTestData) {
		logger.info("=============BeforePostUser================");
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userTestData.get("userID")));
		userPayload.setUsername(userTestData.get("userName"));
		userPayload.setFirstName(userTestData.get("firstName"));
		userPayload.setLastName(userTestData.get("lastName"));
		userPayload.setEmail(userTestData.get("email"));
		userPayload.setPassword(userTestData.get("password"));
		userPayload.setPhone(userTestData.get("phone"));
		
		

		Response responce = UserEndPoint.creatUser(userPayload);
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterPostUser================");
	}
	
	@Test(priority = 4, dataProvider = "userdataprovider", dataProviderClass = api.utilities.DataProviders.class)
	public void testDeleteUserByName(Map<String, String> userTestData) {
		logger.info("=============BeforeDeleteUser================");
		Response responce = UserEndPoint.deleteUser(userTestData.get("userName"));
		responce.then().log().all();
		Assert.assertEquals(responce.getStatusCode(), 200);
		logger.debug(responce.then().log().all());
		logger.info("=============AfterDeleteUser================");
	}

}
