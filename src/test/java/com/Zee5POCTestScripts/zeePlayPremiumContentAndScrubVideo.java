package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class zeePlayPremiumContentAndScrubVideo {

	private ZeeBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException {

		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}

	@Test
	public void Verify_entitlement_for_user_attempting_to_play_any_content() throws Exception {

		zeeBusinesscaller.ZeeLogin("Email");
		zeeBusinesscaller.NavigatFromIntermediateScreen("Home");
		zeeBusinesscaller.scroll("Top ZEE5 Movies in Kannada");
		zeeBusinesscaller.NonPremiumVideo();
		zeeBusinesscaller.scrubVideo(2);
		zeeBusinesscaller.potraitToMiniplayer();
		zeeBusinesscaller.PlayPremiumVideo();
	}

	@AfterTest
	public void tearDown() {
		zeeBusinesscaller.tearDown();
	}
}
