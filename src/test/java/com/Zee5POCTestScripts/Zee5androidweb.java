package com.Zee5POCTestScripts;

import java.net.MalformedURLException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class Zee5androidweb {
	
	private ZeeBusinessLogic zeeBusinesscaller;
	
	@BeforeTest
	public void before() throws MalformedURLException, InterruptedException
	{
		System.out.println("BEFORE");
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("deviceName", "SM-M205F");
////		caps.setCapability("udid", "3201c352b10a16e9"); //Give Device ID of your mobile phone
//		caps.setCapability("platformName", "Android");
//		caps.setCapability("platformVersion", "6.0.1");
//		caps.setCapability("appPackage", "com.vodafone.vodafoneplay");
//		caps.setCapability("appActivity", "com.myplex.vodafone.ui.activities.MainActivity");
//	//	caps.setCapability("browserName", "Chrome");
//		caps.setCapability("noReset", true);
//        // Open the app.
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
////		driver.get("https://newpwa.zee5.com/");
//		Thread.sleep(5000);
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}
	@Test
	public void test() throws InterruptedException
	{
		zeeBusinesscaller.testVadofone();
//		System.out.println(driver.getCurrentUrl());
//		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//		System.out.println(cap.getBrowserName());
//		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
//		System.out.println(cap.getBrowserName());
		
	}
	@AfterTest
	public void after()
	{
		System.out.println("AFTER");
		zeeBusinesscaller.tearDown();
	}

}
