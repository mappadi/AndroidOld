package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDOfflineScreen {

	
	public static  By objYouAreOffline = By.xpath("//*[@text='You are offline']");
	
	public static By objGoToDownloads = By.xpath("//*[@id='goto_offline_screen_button']");
	
	public static By objDownloadScreen = By.xpath("//*[@text='Downloads']");
	
	public static By objTryAgain = By.xpath("//*[@id='try_again_button']");
	
	public static  By objDescription = By.xpath("//*[@text='Please connect to the internet and try again.']");
}
