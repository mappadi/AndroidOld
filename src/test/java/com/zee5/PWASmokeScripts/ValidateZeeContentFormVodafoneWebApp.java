package com.zee5.PWASmokeScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASmokeAndroidBusinessLogic;

public class ValidateZeeContentFormVodafoneWebApp {

	private Zee5PWASmokeAndroidBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException {
		zeeBusinesscaller = new Zee5PWASmokeAndroidBusinessLogic("Chrome");
	}

	@Test
	public void launch() throws Exception {
		// Validation of Zee5 Playback by navigating from VODAFONEPLAY Web App to ZEE5
		// PWA
		zeeBusinesscaller.enterURLInBrowser("chrome", "https://www.vodafoneplay.in/");
		Thread.sleep(3000);
		zeeBusinesscaller.ValidateVodafonePlayFunction();

	}

	@AfterTest
	public void tearDown() {
		zeeBusinesscaller.tearDown();
	}

}
