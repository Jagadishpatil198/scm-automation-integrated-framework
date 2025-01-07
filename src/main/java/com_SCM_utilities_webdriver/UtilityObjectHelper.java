package com_SCM_utilities_webdriver;


import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityObjectHelper {

	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver>  driver=new ThreadLocal<WebDriver>();
	public static ExtentTest getTest()
	{
		return test.get();
	}
	public static void setTest(ExtentTest actTest)
	{
		test.set(actTest);
	}
	public static WebDriver getdriver()
	{
		return driver.get();
	}
	public static void setDriver(WebDriver actdriver)
	{
		driver.set(actdriver);
	}
	
}
