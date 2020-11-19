package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Subscription {
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 1)
	@Parameters({ "userType", })
	public void SubscriptionScreenValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.subscribeEntryPointsValidations(userType);
	}
		
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App\n");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
