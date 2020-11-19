package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class VerifyDownloadsAndNextButton {

	private ZeeBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException{ 
		ZeeBusinessLogic.relaunchFlag = true;
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}

	@Test(priority = 1)
	public void Verify_for_Next_Button_On_player() throws Exception {
		zeeBusinesscaller.ZeeLogin("Email");
		zeeBusinesscaller.NavigatFromIntermediateScreen("Home");
		zeeBusinesscaller.VerifyNextBtn();
		zeeBusinesscaller.Back(1);
		zeeBusinesscaller.waitTime(5000);
		zeeBusinesscaller.Back(1);
	}
//	@Test(priority = 2)
	public void Verify_for_Download_Feature_on_the_portrait_player() throws Exception
	{
		zeeBusinesscaller.VerifyDowloadFunctonality();
		zeeBusinesscaller.Back(1);
	}

	@Test(priority = 3)
	public void To_check_entitlement_for_user_attempting_to_play_any_content() throws Exception {
		
		zeeBusinesscaller.scroll("Top ZEE5 Movies in Kannada");
		zeeBusinesscaller.NonPremiumVideo();
		zeeBusinesscaller.VerifyTrailerBtn();

	}

	@AfterTest
	public void tearDown() {
		zeeBusinesscaller.tearDown();
	}
}
