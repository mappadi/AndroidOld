package com.Zee5POCTestScripts;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class DFPAdVerify {

	private ZeeBusinessLogic zeeBusinesscaller;
//	private MyProxySelector proxy = new MyProxySelector();

	@SuppressWarnings("static-access")
	@BeforeTest
	public void init() throws InterruptedException, IOException {
		zeeBusinesscaller.relaunchFlag = true;
//		proxy.openCharles();
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}

	@Test
	public void DFPValidation() throws Exception {
		zeeBusinesscaller.ZeeLogin("Skip");
		zeeBusinesscaller.NavigatFromIntermediateScreen("HOME");
		zeeBusinesscaller.searchVideo();
		zeeBusinesscaller.scrubVideo(2);
//		zeeBusinesscaller.scrubVideo(1);
	}

	@AfterTest
	public void tearDown() throws Exception {
		zeeBusinesscaller.tearDown();
	}
	
}
