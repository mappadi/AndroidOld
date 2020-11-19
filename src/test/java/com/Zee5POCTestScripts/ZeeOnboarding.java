package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class ZeeOnboarding {

	private ZeeBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException {
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}

	@Test(priority = 1)
	public void GivePermission() throws Exception {
		zeeBusinesscaller.GivePermission();
	}

	@Test(priority = 2)
	public void WelcomeScreen() throws Exception {
		zeeBusinesscaller.WelcomeScreenValidation();
	}

	@Test(priority = 3)
	public void First_time_Welcome_to_Zee5_Screen_Verify_Update_language_Screen() throws Exception {
		zeeBusinesscaller.LanguageValidation();
	}

	@Test(priority = 4)
	public void Permission_shall_not_be_displayed_for_subsequently_Login() throws Exception {
		zeeBusinesscaller.RelaunchApp();
	}

	@Test(priority = 5)
	public void Verify_The_UI_onboardingScreen_and_Login() throws Exception {
		System.out.println("Verify_The_UI_onboardingScreen_and_Login");
		zeeBusinesscaller.ZeeLogin("EMAIL");
	}

	@Test(priority = 6)
	public void verify_IntermediateScreen_Appears_for_All_Users_and_trays_and_UI_OnHomePage() throws Exception {
		System.out.println("verify_IntermediateScreen_Appears_for_All_Users_and_trays_and_UI_OnHomePage");
		zeeBusinesscaller.verifyIntermediateScreen();
		zeeBusinesscaller.NavigatFromIntermediateScreen("HOME");
	}

	@Test(priority = 7)
	public void Verify_UpdateLanguage_From_HomePage() throws Exception {
		zeeBusinesscaller.SelectedContentLanguageValidation();
	}

	@AfterTest
	public void tearDown() {
		zeeBusinesscaller.tearDown();
	}

}
