package com.zee5.ApplicasterScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.business.zee.Zee5ApplicasterBusinessLogic;

public class DeepLinkValidation {

	private Zee5ApplicasterBusinessLogic ZEE5ApplicasterBusinessLogic;

	@BeforeTest
	public void AppLaunch() throws InterruptedException {
		ZEE5ApplicasterBusinessLogic = new Zee5ApplicasterBusinessLogic("settings");
	}

	@Test
	public void ValidatingDeepLink() {
		ZEE5ApplicasterBusinessLogic.HeaderChildNode("Validating deeplink");
		ZEE5ApplicasterBusinessLogic.deepLinks("Home");
		ZEE5ApplicasterBusinessLogic.deepLinks("signin");
	}

	@AfterTest
	public void tearDownApp() {
		ZEE5ApplicasterBusinessLogic.tearDown();
	}
}
