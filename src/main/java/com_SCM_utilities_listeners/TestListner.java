package com_SCM_utilities_listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com_SCM_utilities_webdriver.UtilityObjectHelper;

public class TestListner implements ITestListener,ISuiteListener {

	private static final Logger logger = LogManager.getLogger(TestListner.class);
	private static ExtentReports report;
	private ExtentTest test;
	private ExtentTest test1;
	/**
	 * This method invokes when suite start
	 */
	@Override
	public void onStart(ISuite Suite)
	{
		// setup report configuration
		ExtentSparkReporter spark=new ExtentSparkReporter("./advanceReport/report.html");
		       spark.config().setDocumentTitle("SCM test suite Results");
		       spark.config().setReportName("SCM Reprot");
		       spark.config().setTheme(Theme.DARK);
		       
		       // Initialize the report
		        report=new ExtentReports();
		      report.attachReporter(spark);
		      report.setSystemInfo("OS", "Window-10");
		      report.setSystemInfo("Browser", "chrome-121.11");
		      
	}
	/**
	 * This method invoked when suite finishes
	 */
	@Override
	public void onFinish(ISuite Suite)
	{
		if(report!=null)
		{
			report.flush();
		}
	}
	/**
	 * This method is invoked when a test starts.
     * @param result ITestResult instance for the test method.
	 */
	@Override
	public void onTestStart(ITestResult result)
	{
		      ExtentTest test = report.createTest(result.getMethod().getMethodName());
		     UtilityObjectHelper.setTest(test);
		      test1 = UtilityObjectHelper.getTest();
		      test1.log(Status.INFO, result.getMethod().getMethodName()+" started");
	}
	/**
	 *  This method is invoked when a test succeeds.
     * @param result ITestResult instance for the test method.
	 */
	@Override
	public void onTestSuccess(ITestResult result)
	{
		// log the success status in the report 
		if(test1!=null)
		{
			test1.log(Status.PASS, "Test Passed Successfully");
		}
	}
	/**
	 *  This method is invoked when a test fails.
     * @param result ITestResult instance for the test method.
	 */
	@Override
	public void onTestFailure(ITestResult result)
	{
		        String testName = result.getMethod().getMethodName();
		        TakesScreenshot ts=(TakesScreenshot)UtilityObjectHelper.getdriver();
		     
		      
		      try {
		    	  // capture the screenshort in base64 format
		       String screenshotBase64=ts.getScreenshotAs(OutputType.BASE64);
		        CaptureTimeHelper captureTime=new CaptureTimeHelper();
		      // Add screenshort in Base format
		      if(test1!=null)
		      {
		    	  test1.addScreenCaptureFromBase64String(screenshotBase64, testName+captureTime.getCurrentTime());
		      }
		      }catch (Exception e) {
				logger.error("Error capturing screenshot for test ",e);
			}
	}
	 /**
     * This method is invoked when a test is skipped.
     * @param result ITestResult instance for the test method.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        if (test1 != null) {
            test1.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        }
    }
}
