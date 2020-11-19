package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Kushal

public class AMDLoginScreen {

	// Back Icon
	public static By objBackBtn = By.xpath("//*[@id='icon_back']|//*[@id='action_icon']");

	// Skip Button in Login Screen
	public static By objLoginLnk = By.xpath("//*[@id='skip_link']");

	// Get Email Id label description
	public static By objEmailIdLabel = By.xpath("//*[@id='txtHeader']");

	// Email Id field
	public static By objEmailIdField = By.xpath("//*[@id='edit_email']");

	// Social Login buttons
	public static By objContinueWithTxt = By.xpath("//*[@id='tvContinueWith']");
	public static By objGoogleBtn = By.xpath("//*[@id='btnGoogle']");
	public static By objfbBtn = By.xpath("//*[@id='btnFacebook']");
	public static By objtwitterBtn = By.xpath("//*[@id='btnTwitter']");

	// Get capture Error message AND Get Password reset description
	public static By objErrorTxt = By.xpath("//*[@id='txtMessage']");

	// Error Messages
	public static By objErrorTxtMsg = By
			.xpath("//*[@id='textinput_error' and @text='Password must be a minimum of 6 characters']");
	public static By objErrInvalidID = By.xpath("//*[@id='txtMessage' and @text='Invalid email ID or mobile number']");

	// Proceed Button
	public static By objProceedBtn = By.xpath("//*[@id='btnProceed']");

	// Password field
	public static By objPasswordField = By.xpath("//*[@id='txtET_password_input']");

	// Show Password toggle button
	public static By objShowPwdBtn = By.xpath("//*[@id='text_input_password_toggle']");

	// Forgot Password button
	public static By objForgetPwdBtn = By.xpath("//*[@id='tv_forgotPassword']");

	// Login button
	public static By objLoginBtn = By.xpath("//*[@id='btnLogin']");

	public static By objLoginButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/btnLogin']");

	// Forgot Password screen Proceed Button
	public static By objForgot_ProceedBtn = By.xpath("//*[@id='btnFPSendResetLink']");

	public static By objLoginPage = By.xpath("//*[@id='loginRegistrationContainer']");

	public static By objBrowseForFreeBtn = By.xpath("//*[@id='browseForFreeButton']");

	public static By objSkipBtn = By.xpath("//*[@id='skip_link']");

	public static By objLoginText = By.xpath("//*[@text='Login/Register']");

	public static By objPasswordTextHidden = By.xpath("//*[@id='text_input_password_toggle']");

	public static By objDisplayLang = By.xpath("//*[@id='displayLanguageValue']");

	public static By objLangHindi = By.xpath("//*[@text='हिन्दी']");

	public static By objLanguageContinueBtn = By.xpath("//*[@id='dl_language_selection']");

	public static By objProfileIcon = By.xpath("//*[@text='P']");

	public static By objSettings = By.xpath("//*[@text='Settings']");

	public static By objSettingsHindi = By.xpath("//*[@text='सेटिंग्स']");

	public static By objBelowSelectedLanguage = By.xpath("//*[@text='या जारी रखें:']");

	public static By objLoginTextChanged = By.xpath("//*[@text='लॉग इन / पंजीकरण']");

	public static By objHomeTab = By.xpath("//*[@text='Home' and @id='title']");

	public static By objSkipButton = By.xpath("//*[@text='Skip']");

	public static By objMenu = By
			.xpath("//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='More']]");

	public static By objMenuHindi = By
			.xpath("(//*[@id='bb_bottom_bar_item_container']/*/*[@id='bb_bottom_bar_icon'])[4]");

	public static By objDisplayLangHindi = By.xpath("//*[@text='हिन्दी']");

	public static By objLangEnglish = By.xpath("//*[@text='English']");
	public static By objLoginScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Login']");
	public static By objLoginOrRegisterPageTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Login/Register']");

	// Forgot password screen title
	public static By objForgotPasswordScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Forgot Password']");
	// Login with OTP link
	public static By objLoginWithOTPLink = By.xpath("//*[@resource-id='com.graymatrix.did:id/tvLoginWithOTP']");

	public static By objOtpScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Verify Mobile']");
	public static By objOtpEditBox1 = By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText1']");
	public static By objOtpEditBox2 = By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText2']");
	public static By objOtpEditBox3 = By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText3']");
	public static By objOtpEditBox4 = By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText4']");
	public static By objResendOtpLink = By.xpath("//*[@resource-id='com.graymatrix.did:id/didntGetTheOTPId']");
	public static By objVerifyBtn = By.xpath("//*[@resource-id='com.graymatrix.did:id/verifyButton']");
	public static By objOtp = By.xpath("//*[@resource-id='com.graymatrix.did:id/otpSentAndChangeNumberTextViewId']");
	public static By objCountDownTimer = By.xpath("//*[@resource-id='com.graymatrix.did:id/countDownTimerTextViewId']");
	public static By objRegistrationScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Register for FREE']");
	public static By objDisplayLanguageScreenTitle = By.xpath("//*[@id='screen_title' and @text='Display Language']");
	public static By objPageTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/dl_screen_title']");
	public static By objContentLanguageScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Content Language']");
	public static By objSelectedDisplayLanguage = By.xpath("//*[@id='selectionImage']/following-sibling::*");
	public static By objGmailAccount = By.xpath("(//*[@id='account_name'])[1]");

	public static By objDOB = By.xpath("//*[@id='dobTextView']");

	public static By objGender = By.xpath("//*[@id='genderDropDown']");

	public static By objGenderMale = By.xpath("//*[@text='Male']");

	public static By objDate = By.xpath("//*[@text='1']");

	public static By objDateOK = By.xpath("//*[@text='OK']");

	public static By objSubmitButton = By.xpath("//*[@text='Submit']");

	public static By objTwitterAuthorize = By.xpath("//*[@text='AUTHORIZE APP']");

	public static By objGmailSignIn = By.xpath("//*[@text='Sign in']");

	public static By objGmailEmailField = By.xpath("//*[@id='identifierId']");

	public static By objGmailNextBtn = By.xpath("//*[@text='Next']");

	public static By objGmailPasswordField = By.xpath("//*[@class='android.widget.EditText']");

	public static By objGmailPswdNextBtn = By.xpath("//*[@text='Next']");

	public static By objGmailAddPhoneNumber = By.xpath("//*[@id='headingText']");

	public static By objAgreeBtn = By.xpath("//*[@text='I agree']");

	public static By objAcceptBtn = By.xpath("//*[@text='ACCEPT']");

	public static By objFBEmail = By.xpath("//*[@class='android.widget.EditText' and @text='Phone or email']");

	public static By objFBPassword = By.xpath("//*[@class='android.widget.EditText' and @text='Password']");

	public static By objFBLoginBtn = By.xpath("//*[@contentDescription='Log In' and @class='android.view.ViewGroup']");

	public static By objFBLogIntoAnotherAccount = By.xpath("//*[@text='Log Into Another Account']");

	public static By objTwitterEmail = By
			.xpath("//*[@class='android.widget.EditText' and @resource-id='username_or_email']");

	public static By objTwitterPassword = By.xpath("//*[@class='android.widget.EditText' and @resource-id='password']");

	public static By objTwitterLoginBtn = By.xpath("//*[@class='android.widget.Button' and @text='Authorize app']");
	
	public static By objTickmarkforSelectedDisplayLanguage = By.xpath("//*[@resource-id='com.graymatrix.did:id/selectionImage']");

}
