package com.zee5Pages;

import org.openqa.selenium.By;

public class PermissionPage {

	//Permission Screen
	public static By objBackIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/img_back']");
	
	public static By objExplanationText = By.xpath("//*[@resource-id='com.graymatrix.did:id/explanation']");

	public static By objGivePermissionBtn = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_proceed']");
	
	//Permission Popup
	public static By objPopupMsg = By.xpath("//*[@resource-id='com.android.packageinstaller:id/permission_message']");
	
	public static By objAllowBtn = By.xpath("//*[@resource-id='com.android.packageinstaller:id/permission_allow_button']");
	
	public static By objDenyBtn = By.xpath("//*[@resource-id='com.android.packageinstaller:id/permission_deny_button']");
	
//	Splash Screen
	public static By objVerifySplashScreenDisappear = By.xpath("//*[@text='GIVE PERMISSION'] | //*[@text='Login via Email ID'] | //*[@id='user_like']");
	
}
