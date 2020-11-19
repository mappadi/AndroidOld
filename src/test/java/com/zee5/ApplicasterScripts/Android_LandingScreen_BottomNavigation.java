package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_LandingScreen_BottomNavigation {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}

	@Test(priority = 0)
	@Parameters({ "userType" }) 	// Hitesh
	public void accessDeviceLocation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
	}

	
	@Test(priority = 1)
	@Parameters({ "userType" })		// Manasa
	public void Login(String userType) throws Exception {
		System.out.println("\nVerify Display Language Screen and login flow for various usertypes");
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);
	}
	
	@Test(priority = 2)		// Manasa
	public void upcomingScreenValidation() throws Exception {
		System.out.println("\nVerify Upcoming Screen");
		ZEE5ApplicasterBusinessLogic.upcomingSectionValidation();
		ZEE5ApplicasterBusinessLogic.upcomingContentValidationWithAPIData();
	}
	
	@Test(priority = 3)	
	@Parameters({ "userType","searchKeyword" })	// Manasa
	public void parentalPinValidation(String userType,String searchKeyword) throws Exception {
		System.out.println("\nParental Pin Validation");
		ZEE5ApplicasterBusinessLogic.parentalPinValidation(userType, searchKeyword);
	}
	
	@Test(priority = 4)		// Manasa
	public void moreScreenValidation() throws Exception {
		System.out.println("\nVerify More Screen");
		ZEE5ApplicasterBusinessLogic.moreSectionValidation();
	}
	

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
