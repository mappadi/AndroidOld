package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWALoginPage {
	// LoginBtn
	public static By objLoginBtn = By.xpath("//span[contains(text(),'Login')]");

	// SignUpBtn
	public static By objSignUpBtn = By.xpath("//span[contains(text(),'Sign up for FREE')]");

	// LoginTXT
	public static By objLoginTxt = By.xpath("//div[contains(@class,'backBtn')]//h1[contains(text(),'Login')]");

	// EmailField
	public static By objEmailField = By.xpath("//input[@id='textField']");

	// EmailErrorMessage
	public static By objEmailErrorMessage = By.xpath("//div[contains(text(),'Invalid Email ID')]");

	// PasswordField
	public static By objPasswordField = By.xpath("//input[@type='password']");

	// ForgotPsswrdTxt
	public static By objForgotPasswordTxt = By.xpath("//div[contains(@class,'redirectLinkContainer')]//span");

	// PasswordErrorMessage
	public static By objPasswordErrorMessage = By.xpath("//div[contains(text(),'Minimum 6 characters')]");

	// NotAMemberTxt
	public static By objNotAMemberTxt = By.xpath("//span[contains(text(),'Not A Member Yet ?')]");

	// SignUpTxt
	public static By objSignUpTxt = By.xpath("//span[contains(text(),'Sign up for FREE')]");

	// GoogleIcon
	public static By objGoogleIcon = By.xpath("//div[@id='gbtn']");

	// Facebookicon
	public static By objFacebookIcon = By.xpath("//div[@class='noSelect facebookLoginIcon socialLoginIcon']");

	// TwitterIcon
	public static By objTwitterIcon = By.xpath("//div[@class='noSelect twitterLoginIcon socialLoginIcon']");

	// PasswordfieldinPhonenumberpage
	public static By objpasswordphno = By.xpath("//button[@class='noSelect buttonGradient accountInfo whiteColorBtn']");

	// Proceed button in phonenumberpasswordpage
	public static By objproceedphno = By.xpath("//div[@class='popupBtn']");
	// Subscription Page Text
	public static By objsubscription = By.xpath("//*[@class='pageTitleSmall']");
	// Subscripyion Plan
	public static By objsubscriptionplans = By.xpath("(//*[@class='subscriptionCardWrapper'])[2]");
	// Subscription Continue
	public static By objsubscriptioncontinue = By.xpath("//*[@class='noSelect buttonGradient null']");
	// Forgot password page Text
	public static By objForgotPassswordPage = By.xpath("//h1[contains(text(),'Forgot Password')]");
	// Facebook page Verification
	public static By objFacebookPageVerification = By
			.xpath("//div[text()='Log in to your Facebook account to connect to ZEE5.com']");
	// Facebook email field
	public static By objFacebookLoginEmail = By.xpath("//input[@id='m_login_email']");
	// Facebook Password field
	public static By objFacebookLoginpassword = By.xpath("//input[@id='m_login_password']");
	// Facebook page continue
	public static By objFacebookLoginButtonInFbPage = By.xpath("//button[@name='login']");
	// Facebook continue as
	public static By objFbCountinueBtn = By.xpath("//*[contains(@text,'Continue as ')]");
	// Facebook Page Confirm
	public static By objFacebookConfirmButton = By.xpath("//button[@text='Confirm']");
	// Gmail page verification
	public static By objGmailsignInVerification = By.xpath("//span[contains(text(),'Sign in')]");
	// Gmaild email field
	public static By objGmailEmailField = By.xpath("//input[@id='identifierId']");
	// Gmail next button
	public static By objGmailNextButton = By.xpath("//span[text()='Next']");
	// Gmail password field
	public static By objGmailPasswordField = By.xpath("//input[@name='password']");
	// Twitter page verification
	public static By objTwitterVerificationPage = By.xpath("//h2[contains(text(),'to access your account?')]");
	// Twitter email field
	public static By objTwitterEmaildField = By.xpath("//input[ @class='text']");
	// Twitter password field
	public static By objTwitterPasswordField = By.xpath("//input[@id='password']");
	// Twitter conitue button
	public static By objTwitterSignInButton = By.xpath("//input[@id='allow']");
	// Twitter page autorize app
	public static By objTwitterAuthorizeButton = By.xpath("//input[@value='Authorize app']");
	// Forgot password page cotinue button
	public static By objForgotPasswordLinkButton = By.xpath("//button[@class='noSelect buttonGradient ']");
	//public static By objResetPasswordLinkButton = By.xpath("//span[text()='Reset Password']");
	public static By objResetPasswordLinkButton = By.xpath("//button");
	// Reset bpage text
	public static By objForgotNextPageText = By.xpath("//h1[contains(text(),'Reset Password')]");
	// Reset page password btn
	public static By objForgotNextPagePwsswordFielfd = By.xpath("//input[@name='password']");
	// Reset page confirm password
	public static By objForgotNextPageConfirmPasswordField = By.xpath("//input[@name='confirmpwd']");
	
	
	//webLogin
	public static By objWebLoginBtn = By.xpath("//a[@href='/signin' and text()='Login']");
	public static By objWebLoginPageText = By.xpath("//h1[.='Login']");
	public static By objWebLoginButton = By.xpath("//button//span[.='Login']");

	public static By objLoginButton = By.xpath("//a[@href='/signin']//span[.='Login']");
	
	public static By objSignUpBtnWEB = By.xpath("//a[contains(text(),'Sign up for FREE')]");


	public static By objForgotPasswordLinkButtonWEB = By.xpath("//div[@class='pwdgradientBtnContainer']//button[@class='noSelect buttonGradient ']");

	
	public static By objForgotNextPageTextWEB = By.xpath("//div[@class='formHeader']");
	
	public static By objForgotNextPageResetPaswwordButtonWEB = By.xpath("//div[@class='gradientBtnContainer']//button[@class='noSelect buttonGradient ']");
	
	public static By objLoginPageLoginBtnWEB = By.xpath("//div[@class='gradientBtnContainer']//span[contains(text(),'Login')]");

    public static By objLoginBtnWEB = By.xpath("//*[@class='loginBtn noSelect' and text()='Login']");

	public static By objFacebookLoginEmailWEB = By.xpath("//*[@id='email']");
	
	public static By objFacebookLoginpasswordWEB = By.xpath("//*[@id='pass']");
	
	public static By objFacebookLoginButtonInFbPageWEB = By.xpath("//*[@id='loginbutton']");
	
	public static By objLoginPageLoginBtn = By.xpath("//div[@class='gradientBtnContainer']//span[contains(text(),'Login')]");
	
//	====================================================================================================
	
	public static By objAlertPopUp = By.xpath("//*[@id='negative_button']");
	
//	NETWORK
	public static By objSpinnerInLogin = By.xpath("//div[@class='spinner spinnerAnimation']");
	
//	ONBOARDING
	public static By objDateOfBirthField = By.xpath("//div[contains(@class,'itemWrapper traditonalReg')]");
	
	public static By objCalenderPopUp = By.xpath("//div[@class='react-calendar']");
	
	public static By objSelectDateInCalender = By.xpath("//div[contains(@class,'react-calendar__viewContainer')]//button[1]");
	
	public static By objSignUpHeaderInSignUpPage = By.xpath("//h1[contains(text(),'Sign up for FREE')]");
	
	public static By objIncorrectPhoneNumberMessage =By.xpath("//div[contains(text(),'Incorrect Mobile Number')]");
	
	public static By objForgotPasswordMessage = By.xpath("//*[@class='forgotPasswordMessage']");
	
	public static By objNewPasswordField = By.xpath("//input[@name='newPassword']");
	
	public static By objConfirmNewPasswordField = By.xpath("//input[@name='confirmPassword']");
	
	public static By objOTPVerifyPage = By.xpath("//div[@class='mainContentInfo contentTop forgotPasswordVerify']");
	
	public static By objLoginPageText = By.xpath("(//h1[.='Login'])[1]");
	
	public static By objSignUpHeaderInSignUpPageWeb = By.xpath("//div[contains(text(),'Sign up for FREE') and @class='regHeader']");

	public static By objLoginPageheader = By.xpath("//h1[@class='formHeader']");

	public static By objFacebookPageVerificationWeb = By.xpath("//div[@class='fb_content clearfix']");

	public static By objFacebookLoginEmailWeb = By.xpath("//input[@id='email']");

	public static By objFacebookLoginpasswordWeb = By.xpath("//input[@id='pass']");

	public static By objFacebookLoginButtonInFbPageWeb = By.xpath("//input[@name='login']");

	public static By objSetNewPasswordButton = By.xpath("//span[text()='Set New Password']");
	
	public static By objCountryCode = By.xpath("//*[@class='countryfield']");
	
	public static By objCountryCodeIndia = By.xpath("//*[@class='react-dropdown-select-type-single' and text()='91']");
	
	public static By objCountryCodeDropDown = By.xpath("//*[@class='dropDownWrapper']");
	
	public static By objCountryCodeAlgeria = By.xpath("//*[@class='react-dropdown-select-type-single' and text()='213']");
	
	public static By objCountryCodeAndoora = By.xpath("//*[@class='react-dropdown-select-type-single' and text()='376']");
	
	public static By objShowPasswordButton = By.xpath("//span[contains(@class,'showPwd')]");
	// PasswordField entered
	public static By objPasswordFieldFilled = By.xpath("//input[@name='password']");
}
