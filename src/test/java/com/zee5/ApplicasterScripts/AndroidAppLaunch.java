package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;

public class AndroidAppLaunch {

	private Zee5ApplicasterBusinessLogic objZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching andriod App");
		objZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
		
	}
	
	@Test
	@Parameters({ "userType", })
	public void accessDeviceLocation(String userType) throws Exception {
		System.out.println("Functionality check");
		objZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow",userType);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		objZEE5ApplicasterBusinessLogic.tearDown();
	}
}
