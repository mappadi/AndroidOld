package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_Settings {

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
		ZEE5ApplicasterBusinessLogic.ZeeApplicasterLoginForSettings(userType);
	}

	@Test(priority = 1)
	@Parameters({ "userType", "displayLanguageSelection1", "displayLanguageSelection2", "searchKeyword",
			"searchKeyword1", })
	public void Settings_Screen(String userType, String displayLanguageSelection1, String displayLanguageSelection2,
			String searchKeyword, String searchKeyword1) throws Exception {
		ZEE5ApplicasterBusinessLogic.settings(userType);
		ZEE5ApplicasterBusinessLogic.videoStreamingValidation(userType);
		ZEE5ApplicasterBusinessLogic.downloadSettingsValidation();
		ZEE5ApplicasterBusinessLogic.DownloadOverWiFiOnlyONValidation(userType, searchKeyword1);
		ZEE5ApplicasterBusinessLogic.parentalPinValidation(userType, searchKeyword);
		ZEE5ApplicasterBusinessLogic.settings_Language(displayLanguageSelection1, displayLanguageSelection2);
		ZEE5ApplicasterBusinessLogic.searchHistoryValidation(userType);
		ZEE5ApplicasterBusinessLogic.authenticateDeviceValidation(userType);
		ZEE5ApplicasterBusinessLogic.resetSettingsValidation(userType);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("Quit the App");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
