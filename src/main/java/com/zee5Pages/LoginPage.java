package com.zee5Pages;

import org.openqa.selenium.By;

public class LoginPage {
	
	//Password field
	public static By objPasswordField= By.xpath("//*[@text='Password']");
	
	//Entered password field
	public static By objEnteredPasswordfield = By.xpath("//*[@resource-id='com.graymatrix.did:id/txtET_password_input']");
	
	//Password Eye Icon
	public static By objEyeIcon =  By.xpath("//*[@resource-id='com.graymatrix.did:id/text_input_password_toggle']");
	
	//'Forgot password?' Link
	public static By objForgotPasswordLink = By.xpath("//*[@text='Forgot Password?']");
	
	//Login Button
	public static By objLoginBtn = By.xpath("//*[@id='btnLogin']");
	
}
