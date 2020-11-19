package com.zee5Pages;

import org.openqa.selenium.By;

public class TraditionalLoginPage {

//	Login Via EmailID ::
	   
//	EmailId Field
	public static By objEmailId = By.xpath("//*[@id='input_email_text']");
	
//	Password Field
	public static By objPassword = By.xpath("//*[@id='input_password_text']");
	
//	Login Button
	public static By objLoginBtn = By.xpath("//*[@text='LOGIN']");
	
	
//	Login Via Mobile Number ::
	
//	Login Header
	public static By objLoginHeader = By.xpath("//*[@id='login_heading']");
	
//	Country Name Drop Down
	public static By objCountryDD = By.xpath("//*[@id='input_country_text']");
	
//	Mobile Number Field
	public static By objMobileNumbertxtField = By.xpath("//*[@id='input_country_text']");
	
//	Password Field
	public static By objPasswordTxtField = By.xpath("//*[@id='input_password_text']");
	
//	Forgot Password Text
	public static By objForgotPasswordTxt = By.xpath("//*[@id='forgot_password']");
	
//	Login Button
	public static By objLogintn = By.xpath("//*[@id='mobile_action_button']");
	
	
}
