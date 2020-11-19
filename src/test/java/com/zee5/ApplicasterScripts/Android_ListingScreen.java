package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_ListingScreen {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching andriod App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void Login(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 1)	 
	@Parameters({ "userType" })
	public void ListingCollection_EntryPoints(String userType) throws Exception {
	    ZEE5ApplicasterBusinessLogic.ListingCollectionValidationFromLandingScreen(userType);
	    ZEE5ApplicasterBusinessLogic.ListingCollectionValidationFromConsumptionScreen(userType);
	}

	@Test(priority = 2)	 
	@Parameters({ "userType" })
	public void ListingCollection(String userType) throws Exception {
	    ZEE5ApplicasterBusinessLogic.ListingCollectionVerification(userType);
	}

	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nExecution Complete - Closing the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
