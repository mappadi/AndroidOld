package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5ApplicasterBusinessLogic;
import com.utility.Utilities;

public class Android_MoreScreen {
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
	
	@Test(priority = 1) //Vinay
	@Parameters({ "userType" })
	public void AccountDetails(String userType) throws Exception {
		
		ZEE5ApplicasterBusinessLogic.AccountDetails(userType);
	
	}
	@Test(priority = 2)
	@Parameters({ "userType" })
	public void MyWatchlist(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.myWatchList(userType);
	}
	
	@Test(priority = 3) //Hitesh
	@Parameters({ "userType" })
	public void MyReminders(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.myReminders(userType);
	}
	
	@Test(priority = 4) //Hitesh
	@Parameters({ "userType" })
	public void Haveaprepaidcode(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.Haveaprepaidcode(userType);
	}
	@Test(priority = 5)  //Hitesh
	@Parameters({ "userType" })
	public void Settings(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.Settings(userType);
	}
	
//	@Test(priority = 6)
	@Parameters({ "userType" }) // in-progress
	public void InviteAFriend(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.Invite_a_Friend(userType);
	}
	
	@Test(priority = 7)
	@Parameters({ "userType" })
	public void AboutUsValidation(String userType) throws Exception {
		 ZEE5ApplicasterBusinessLogic.AboutUsScreenValidation(userType);
	}
	
	
	@Test(priority = 8)
	@Parameters({ "userType" })
	public void HelpCenterValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.HelpCenterScreen(userType);
	}
	
	@Test(priority = 9)
	@Parameters({ "userType" })
	public void LogoutValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.LogoutValidation(userType);
		ZEE5ApplicasterBusinessLogic.LoginAfterLogout(userType);
		ZEE5ApplicasterBusinessLogic.logoutOfflineValidation(userType);//
	}

	@Test(priority = 10)
	@Parameters({ "userType" })
	public void PrivacyPolicyValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.PrivacyPolicyScreen(userType);
	}

	@Test(priority = 11)
	@Parameters({ "userType" })
	public void StaticPagesInDisplayLanguage(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.staticPagesInDisplayLanguage();
	}
	
	@Test(priority = 12)
	@Parameters({ "userType" })
	public void TermsOfUseValidation(String userType) throws Exception {
		ZEE5ApplicasterBusinessLogic.TermsOfUseScreen(userType);
	}

	@AfterTest
	public void tearDownApp() {
		System.out.println("\nQuit the App\n");
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
