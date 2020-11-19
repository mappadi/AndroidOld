package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDChangePasswordScreen {

	//Back Icon
	public static By objBackBtn = By.xpath("//*[@id='icon_back']");
		
	//Current Password field
	public static By objCurrentPwdField = By.xpath("//*[@id='txtET_current_password_input']");
	public static By objShowCurrentPwdBtn = By.xpath("(//*[@id='text_input_password_toggle'])[1]");
	
	//New Password field
	public static By objNewPwdField = By.xpath("//*[@id='txtET_new_password_input']");
	public static By objShowNewtPwdBtn = By.xpath("(//*[@id='text_input_password_toggle'])[2]");
		
	//Confirm New Password field
	public static By objConfirmPwdField = By.xpath("//*[@id='txtET_confirm_password_input']");
	public static By objShowConfirmPwdBtn = By.xpath("(//*[@id='text_input_password_toggle'])[3]");
	
	//Get Error text from Change Password screen
	public static By objErrTxt = By.xpath("//*[@id='textinput_error']");
	
	//Update Button
	public static By objUpdateBtn = By.xpath("//*[@id='btnUpdate']");
	
}
