package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWASignupPage {
	// SignUpTxt
	public static By objSignUpTxt = By.xpath("//h1[contains(text(),'Sign up for FREE')]");

	// GenderMaleBtn
	public static By objGenderMaleBtn = By.xpath("//span[contains(text(),'Male')]");

	// GenderFemaleBtn
	public static By objGenderFemaleBtn = By.xpath("//span[contains(text(),'Female')]");

	// GenderOtherBtn
	public static By objGenderOtherBtn = By.xpath("//span[contains(text(),'Other')]");

	// TermsOfService
	public static By objTermsOfService = By.xpath("//span[contains(text(),'Terms of Services')]");

	// PrivacyPolicy
	public static By objPrivacyPolicy = By.xpath("//span[contains(text(),'Privacy Policy')]");

//		====================================================================================================
	public static By objSignUpButtonNotHighlighted = By.xpath("//*[@class='noSelect buttonGradient ']");
	public static By objSignUpButtonHighlighted = By.xpath("//*[@class='noSelect buttonGradient ']");
	public static By objChangeNumberLink = By.xpath("//*[@class='noSelect redirectLink']");
	public static By objOTPTimer = By.xpath("//*[@class='singleRow-left']");
	public static By objResendOtpOption = By.xpath("//button[contains(text(),'Resend')]");
	public static By objOTP1 = By.xpath("//input[@name='otp1']");
	public static By objOTP2 = By.xpath("//input[@name='otp2']");
	public static By objOTP3 = By.xpath("//input[@name='otp3']");
	public static By objOTP4 = By.xpath("//input[@name='otp4']");
	public static By objPasswordErrorMessage = By.xpath("//div[contains(text(),'Minimum 6 characters')]");
	public static By objPasswordIcon = By.xpath("//span[contains(@class,'noSelect pwdIcon')]");
	public static By objPasswordHiddenField = By.xpath("//input[@type='password']");
	public static By objPasswordFieldShow = By.xpath("//input[@type='text' and @name='password']");

//		ONBOARDING
	public static By objYearPickerTabValueNotActive = By.xpath("//*[contains(text(),'2020')]");

	// DayPickerTab
	public static By objDayPickerTab = By.xpath("(//div[@class='floatingLabelSelect  '])[1]");
	public static By objDayPickerTabValue = By.xpath("//span[contains(text(),'02')]");

	// MonthPickerTab
	public static By objMonthPickerTab = By.xpath("(//div[@class='floatingLabelSelect  '])[2]");
	public static By objMonthPickerTabValue = By.xpath("//*[text()='MAR']");

	// YearPickerTab
	public static By objYearPickerTab = By.xpath("(//div[@class='floatingLabelSelect  '])[3]");
	public static By objYearPickerTabValue = By.xpath("//*[contains(text(),'2001')]");
	public static By objGoogleOtpVerification = By.xpath("//*[@resource-id='headingSubtext']");

	public static By objSignUpButtonNotHighlightedWeb = By.xpath("//div[@class='regBottom']/following-sibling::div[@class='buttonContainer']/child::*/child::*");
	
	public static By objSignUpButtonHighlightedWeb = By.xpath("//div[@class='regBottom']/following-sibling::div[@class='buttonContainer']/child::*/child::*");

	public static By objVerifyBtnWeb = By.xpath("//div[@class='verifyBtn']/child::*");
}
