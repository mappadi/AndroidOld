package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Onboarding_Suite3 {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;
	
	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
		Utilities.relaunch = true;	// Clear App Data on First Launch
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("zee");
	}
	
	@Test(priority = 1)
	@Parameters({ "userType"})	//Shreenidhi==Scenarios only for Guest user
	public void subscribeNow(String userType) throws Exception {
		System.out.println("\nVerify Subscribe Now screen");
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.subscribeNowSceanrios(userType);
	}
	

	@Test(priority = 2)	// Shreenidhi and Bindu==Scenarios only for Guest user
	@Parameters({ "userType", "loginThrough" }) 
	public void mobileRegistration(String userType, String loginThrough) throws Exception {
		System.out.println("\nMOBILE REGISTRATION");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.mobileRegistration(loginThrough);
	}
	
	
	@Test(priority = 3)		// Manasa
	@Parameters({ "loginThrough" })
	public void socialLoginValidation(String loginThrough) throws Exception {
		System.out.println("\nVerify Social login");
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", "Guest");
		ZEE5ApplicasterBusinessLogic.socialLoginValidation(loginThrough);
	}
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
	
	
	
}
