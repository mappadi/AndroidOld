package com.Zee5POCTestScripts;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class PlayerValidation {
	
	
	private ZeeBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException{
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}
	
	@Test(priority = 10)
	public void Play_NonPremiumVideo() throws Exception {
		zeeBusinesscaller.ZeeLogin("Email");
		zeeBusinesscaller.verifyIntermediateScreen();
		zeeBusinesscaller.NavigatFromIntermediateScreen("HOME");
		zeeBusinesscaller.waitTime(5000);
//		zeeBusinesscaller.MultiLanguage();
		zeeBusinesscaller.scroll("Top ZEE5 Movies in Kannada");
		zeeBusinesscaller.NonPremiumVideo();
	}
	
	@Test(priority = 11)
	public void Verify_the_UI_of_portrait_player_for_subcribed_user() throws Exception {
		zeeBusinesscaller.ThreeDotOptionValidation();// popup appears
		zeeBusinesscaller.ThreeDotPopupOptionSelection("Quality");
		zeeBusinesscaller.captureGTMCall("videoQuality");
		zeeBusinesscaller.QualityFunctionality();
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		zeeBusinesscaller.GTMCallForVideoQuality("videoQuality");
	}
	
	@Test(priority = 12)
	public void Verify_functionality_of_Subtitle_from_3_dot_menu() throws Exception {
		zeeBusinesscaller.ThreeDotPopupOptionSelection("Subtitles/CC");// selects Subtitle from popup
		zeeBusinesscaller.SubtitleFunctionality();// performs quality functionality
	}
	
	@Test(priority = 13)
	public void verify_AudioLanguage_Fnctionality() throws Exception {
		zeeBusinesscaller.ThreeDotPopupOptionSelection("Audio Language");// selects Audio Language from popup
		zeeBusinesscaller.AudioLanguageFunctionality();// performs quality functionality
	}
	
	
	@Test(priority = 14)
	public void Verify_Add_To_WatchList() throws Exception {
		zeeBusinesscaller.ThreeDotPopupOptionSelection("Add to Watchlist");// selects Add To WatchList from popup
		zeeBusinesscaller.AddToWatchListFunctionality();
		zeeBusinesscaller.RemoveFromWatchList();
	}
	
	@AfterTest
	public void tearDown() throws IOException{
		zeeBusinesscaller.tearDown();
	}
}


