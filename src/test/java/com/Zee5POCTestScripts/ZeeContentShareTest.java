package com.Zee5POCTestScripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.business.zee.ZeeBusinessLogic;

public class ZeeContentShareTest {

	private ZeeBusinessLogic zeeBusinesscaller;
	
	@BeforeTest
	public void init() throws InterruptedException{
		zeeBusinesscaller = new ZeeBusinessLogic("zee");
	}
	
	@Test
	public void FacebookShare() throws Exception{
//		zeeBusinesscaller.ZeeFaceBookShare();
//		zeeBusinesscaller.ZeeTwitterContentShare();
	}
	
	@AfterTest
	public void tearDown(){
		zeeBusinesscaller.tearDown();

	}
	
	
}
