package com_SCM_basepagetest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com_SCM_utilities_listeners.TestListner;
import com_SCM_utilities_webdriver.UtilityObjectHelper;

public class BasePage extends TestListner {
public WebDriver driver=null;
	@BeforeClass
	public void openBrowsers(String Browser)
	{
		if(Browser.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser.equals("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		UtilityObjectHelper.setDriver(driver);
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}
}
