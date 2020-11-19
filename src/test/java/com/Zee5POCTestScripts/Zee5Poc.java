package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class Zee5Poc {
	
	private ZeeBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException{
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
			
			zeeBusinesscaller.ZeeLogin("Email");
		}

		@Test(priority = 6)
		public void verify_IntermediateScreen_Appears_for_All_Users_and_trays_and_UI_OnHomePage() throws Exception {
			zeeBusinesscaller.verifyIntermediateScreen();
//			zeeBusinesscaller.waitTime(30000);
			zeeBusinesscaller.NavigatFromIntermediateScreen("HOME");
		}

		@Test(priority = 7)
		public void Verify_UpdateLanguage_From_HomePage() throws Exception {
			zeeBusinesscaller.SelectedContentLanguageValidation();
		}
		
		@Test(priority = 8)
		public void Play_NonPremiumVideo() throws Exception {
			
			zeeBusinesscaller.waitTime(6000);
			zeeBusinesscaller.scroll("Top ZEE5 Movies in Kannada");
			zeeBusinesscaller.waitTime(6000);
			zeeBusinesscaller.NonPremiumVideo();
		}
		
		@Test(priority = 9)
		public void Verify_the_UI_of_portrait_player_for_subcribed_user() throws Exception {
			
			zeeBusinesscaller.ThreeDotOptionValidation();// popup appears
			zeeBusinesscaller.ThreeDotPopupOptionSelection("Quality");
			zeeBusinesscaller.captureGTMCall("videoQuality");
			zeeBusinesscaller.QualityFunctionality();
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			zeeBusinesscaller.GTMCallForVideoQuality("videoQuality");
		}
		
		@Test(priority = 10)
		public void Verify_functionality_of_Subtitle_from_3_dot_menu() throws Exception {
			zeeBusinesscaller.ThreeDotPopupOptionSelection("Subtitles/CC");// selects Quality from popup
			zeeBusinesscaller.SubtitleFunctionality();// performs quality functionality
		}
		
		@Test(priority = 11)
		public void verify_AudioLangauge_Fnctionality() throws Exception {
			zeeBusinesscaller.ThreeDotPopupOptionSelection("Audio Language");// selects Quality from popup
			zeeBusinesscaller.AudioLanguageFunctionality();// performs quality functionality
		}
		
		
		@Test(priority = 12)
		public void Verify_AddTo_WatchList() throws Exception {
			zeeBusinesscaller.ThreeDotPopupOptionSelection("Add to Watchlist");// selects Quality from popup
			zeeBusinesscaller.AddToWatchListFunctionality();
			zeeBusinesscaller.RemoveFromWatchList();
			zeeBusinesscaller.Back(1);
		}
		
//		@Test(priority = 13)
		public void Verify_for_Next_Button_On_player() throws Exception {
			zeeBusinesscaller.VerifyNextBtn();
			zeeBusinesscaller.Back(2);			
		}
	//	@Test(priority = 14)
		public void Verify_for_Download_Feature_on_the_portrait_player() throws Exception
		{
			zeeBusinesscaller.VerifyDowloadFunctonality();
			zeeBusinesscaller.Back(1);
		}

//		@Test(priority = 15)
		public void To_check_entitlement_for_user_attempting_to_play_any_content() throws Exception {
			zeeBusinesscaller.waitTime(8000);
			zeeBusinesscaller.scroll("Top ZEE5 Movies in Kannada");
			zeeBusinesscaller.NonPremiumVideo();
			zeeBusinesscaller.VerifyTrailerBtn();
		}

		@AfterTest
		public void tearDown(){
			zeeBusinesscaller.tearDown();
		}


}

	

