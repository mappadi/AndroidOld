package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASmokeAndroidBusinessLogic;

public class PWAScript {

	private Zee5PWASmokeAndroidBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException {
		zeeBusinesscaller = new Zee5PWASmokeAndroidBusinessLogic("zee");
	}

	@Test
	public void testPwa() throws Exception {
//		zeeBusinesscaller.Login();

//		zeeBusinesscaller.testFrameworkClicks();

		zeeBusinesscaller.navigateToAnyScreen("Live TV");
	}

	@AfterTest
	public void tearDown() {
		zeeBusinesscaller.tearDown();
	}
}
