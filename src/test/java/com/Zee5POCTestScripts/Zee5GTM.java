package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.business.zee.ZeeBusinessLogic;

public class Zee5GTM {
	private ZeeBusinessLogic zeeBusinesscaller;
	
	@BeforeTest
	public void init() throws Exception{
		ZeeBusinessLogic.relaunchFlag = true;
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
		zeeBusinesscaller.captureGTMCall("Login");
	}
	
	@Test(priority = 1)
	public void GTM() throws Exception {
		zeeBusinesscaller.ZeeInvalidLogin("Email");
		zeeBusinesscaller.ZeeInvalidLogin("Skip");
		zeeBusinesscaller.ZeeUnsuccessfullRegistration("Email");
		zeeBusinesscaller.ZeeUnsuccessfullRegistration("Skip");
		zeeBusinesscaller.ZeeRegistration("Email");
		zeeBusinesscaller.NavigatFromIntermediateScreen("HOME");
		zeeBusinesscaller.scroll("Top ZEE5 Movies in Kannada");
		zeeBusinesscaller.PlayNonPremiumVideo();
		zeeBusinesscaller.Back(2);
		zeeBusinesscaller.ZeeLogout();
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		zeeBusinesscaller.verifyGTMCall("Login", "onLoginFailed");
		zeeBusinesscaller.verifyGTMCall("Login", "onLoginSkip");
		zeeBusinesscaller.verifyGTMCall("Login", "onRegistrationFailed");
		zeeBusinesscaller.verifyGTMCall("Login", "onRegistrationSkip");
		zeeBusinesscaller.verifyGTMCall("Login", "onRegistrationSuccess");
		zeeBusinesscaller.verifyGTMCall("Login", "onAdView");
		zeeBusinesscaller.verifyGTMCall("Login", "onVideoClick");
	}
	
	@AfterTest
	public void tearDown(){
		zeeBusinesscaller.tearDown();
	}
}
