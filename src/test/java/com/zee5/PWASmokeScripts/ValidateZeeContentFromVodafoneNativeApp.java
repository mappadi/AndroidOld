package com.zee5.PWASmokeScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASmokeAndroidBusinessLogic;

public class ValidateZeeContentFromVodafoneNativeApp {

	private Zee5PWASmokeAndroidBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException {
		zeeBusinesscaller = new Zee5PWASmokeAndroidBusinessLogic("Chrome");
	}

	@Test
	public void launch() throws Exception {
		// Validation of Zee5 Playback by navigating from VODAFONEPLAY APP to ZEE5 PWA
		
		// prerequisites :
		// make sure Vodafone app is installed and registered
		// make sure zee Android app is uninstalled
		// add the vodafone app, pakage and version details in the property file
		// comment the code tlDriver.get().get(handler.getproperty("URL")); in DriverInstance class
		// comment capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome"); in DriverInstance class
		zeeBusinesscaller.ValidateVodafonePlayFunctionFromNativeApp();
	}

	@AfterTest
	public void tearDown() {
		zeeBusinesscaller.tearDown();
	}

}
