package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> schTest = new ThreadLocal<ExtentTest>();

	String repName;

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub﻿

		test = extent.createTest(result.getName());
		schTest.set(test);
		schTest.get().assignCategory(result.getMethod().getGroups());
		schTest.get().createNode(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test = extent.createTest(result.getName());
		schTest.get().log(Status.PASS, "Test Passed");
		//extent.createTest()

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		//test = extent.createTest(result.getName());
		//schTest.set(test);
		//schTest.get().createNode(result.getName());
		//schTest.get().assignCategory(result.getMethod().getGroups());
		schTest.get().log(Status.FAIL, "Test Failed");
		schTest.get().log(Status.FAIL, result.getThrowable().getMessage());

	}

	public void onTestSkipped(ITestResult result) {
		//test = extent.createTest(result.getName());
		//schTest.set(test);
		//schTest.get().createNode(result.getName());
		//schTest.get().assignCategory(result.getMethod().getGroups());
		schTest.get().log(Status.SKIP, "Test Skipped");
		schTest.get().log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		
		extent.flush();
	}

	public void onStart(ITestContext context) {
		String timeStamp = CalendarDt.getCurrentDate();
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("RestAssuredAutomation Project"); // Title of report 
		sparkReporter.config().setReportName("Pet Store Users API"); // name of the report 
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Pest Store Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name")); 
		extent.setSystemInfo("User Name", System.getProperty("user.name")); 
		extent.setSystemInfo("Environemnt", "QA");
	}

}
