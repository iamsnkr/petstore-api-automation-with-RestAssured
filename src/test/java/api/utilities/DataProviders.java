package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
 
	
	@DataProvider(name="userdataprovider")
	public Object[][] getAllData() {
		Object[][] testData = null;
		ExcelReader excelData = new ExcelReader("D:\\RestAssured\\pet-store-api-automation\\testData\\userData.xlsx");
		testData = excelData.getTestCaseDataSetsAsHashMap("UsersAPI", "UserDetails");
		return testData;

	}
}
