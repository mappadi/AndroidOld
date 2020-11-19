package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Search {
	
	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		System.out.println("Launching Andriod App");
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
	public void SearchScreen(String userType) throws Exception {

	    ZEE5ApplicasterBusinessLogic.verifySearchOption(userType);
		ZEE5ApplicasterBusinessLogic.verifySearchLandingScreen(userType);
		ZEE5ApplicasterBusinessLogic.SearchBox(userType);
	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void  TopAndTrendingSearches(String UserType) throws Exception {
		ZEE5ApplicasterBusinessLogic.TopSearches(UserType);
		ZEE5ApplicasterBusinessLogic.TrendingSearches();
	}
	
	@Test(priority = 3)		// Manasa
	@Parameters({ "userType"})		
	public void searchResultScreenValidation(String userType) throws Exception {
		System.out.println("\nVerify Search Result screen");
		ZEE5ApplicasterBusinessLogic.noSearchResults("@$_-(;_+");
		ZEE5ApplicasterBusinessLogic.searchPageValidation("Kam");
		ZEE5ApplicasterBusinessLogic.searchResultsAllTabs("Kamali");
	}
	
	@Test(priority = 4)		// Manasa
	@Parameters({ "userType" })		
	public void voiceSearchValidation(String userType) throws Exception {
		System.out.println("\nVerify Voice Search functionality");
		ZEE5ApplicasterBusinessLogic.voiceSearchValidation();
		ZEE5ApplicasterBusinessLogic.relaunch(true);
		ZEE5ApplicasterBusinessLogic.accessDeviceLocationPopUp("Allow", userType);
		ZEE5ApplicasterBusinessLogic.navigateToIntroScreen_DisplaylangScreen();
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLogin(userType);
		ZEE5ApplicasterBusinessLogic.voiceSearchDenyValidation();
	}
	

	
	
	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App\n");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}

}
