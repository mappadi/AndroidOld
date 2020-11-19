package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.business.zee.ZeeBusinessLogic;

public class Zee5Launch {

	private ZeeBusinessLogic zeeBusinesscaller;

	@BeforeTest
	public void init() throws InterruptedException{
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}
	
	@AfterTest
	public void tearDown(){
		zeeBusinesscaller.tearDown();
	}
	
}
