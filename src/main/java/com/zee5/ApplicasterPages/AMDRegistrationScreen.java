package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Vinay
public class AMDRegistrationScreen {
	
	//Login screen
	public static By objScreenTitle = By.xpath("//*[@id='screen_title']");
	public static By objSkipText = By.xpath("//*[@id='skip_link']");
	public static By objBackBtn = By.xpath("//*[@id='icon_back']");
	public static By objEmailIDTextField  = By.xpath("//*[@id='edit_email']");
	public static By objGoogleIcon = By.xpath("//*[@id='btnGoogle']");
	public static By objFacebookIcon = By.xpath("//*[@id='btnFacebook']");
	public static By objTwitterIcon = By.xpath("//*[@id='btnTwitter']");
	public static By objInvalidEmailIDErrorMsg = By.xpath("//*[@id='txtMessage']");
	public static By objProceedBtn  = By.xpath("//*[@id='btnProceed']");
	
	//Register screen 
	public static By objEmailIDHeaderTxt = By.xpath("//*[@id='txtHeader']");
	public static By objFirstNameTxtField = By.xpath("//*[@id='first_name_input']");
	public static By objFirstNameErrorMsg = By.xpath("//*[@id='textinput_error']");
	public static By objLastNameTxtField = By.xpath("//*[@id='last_name_input']");
	public static By objDOBTxtField = By.xpath("//*[@id='dobTextView']");
	public static By objDOBCalenderBtn = By.xpath("//*[@id='dobClalander']");
	public static By objGederTxtField = By.xpath("//*[@id='genderTextView']");
	public static By objSelectGenderText = By.xpath("//*[@id='selector_screen_title']");
	public static By objXMark = By.xpath("//*[@id='selector_selection']");
	public static By objMale = By.xpath("//*[@text='Male']");
	public static By objFemale = By.xpath("//*[@text='Female']");
	public static By objSelecteGender = By.xpath("//*[@id='selectionImageSelector']");
	public static By objPasswordTxtField = By.xpath("//*[@id='password_input']");
	public static By objShowPWDIcon = By.xpath("//*[@id='text_input_password_toggle']");
	public static By objRegisterBtn = By.xpath("//*[@id='registerProgress']");
	public static By objSelecteDOBPopup = By.xpath("//*[@resource-id='android:id/datePicker']");
	public static By objDateSelection= By.xpath("//*[@text='1']");
	public static By objOkButtonInCalenderPopUp= By.xpath("//*[@resource-id='android:id/button1']");
	public static By objOTPScreen= By.xpath("//*[@resource-id='com.graymatrix.did:id/otpSentAndChangeNumberTextViewId']");
	public static By objOTPTimer= By.xpath("//*[@resource-id='com.graymatrix.did:id/countDownTimerTextViewId']");
	public static By objVerifyOtpButton= By.xpath("//*[@resource-id='com.graymatrix.did:id/verifyButton']");
	public static By objResendOTP= By.xpath("//*[@resource-id='com.graymatrix.did:id/didntGetTheOTPId']");
	public static By objOTPField1= By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText1']");
	public static By objOTPField2= By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText2']");
	public static By objOTPField3= By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText3']");
	public static By objOTPField4= By.xpath("//*[@resource-id='com.graymatrix.did:id/otpEditText4']");
	public static By objNumericKeyBoard= By.xpath("//*[@id='keyb']");
	public static By objAlphaKeyBoard= By.xpath("//*[@contentDescription='a']");
	public static By objNumericKeypad = By.xpath("//*[contains(@id, 'keyb')]//following::*");
	public static By objNumericKeys= By.xpath("//*[contains(@id, 'keyb')]");
	
	public static By objCalenderPopUp = By.xpath("//*[@resource-id='android:id/datePicker']");
	public static By objSelectedGenderName = By.xpath("//*[@id='selectionImageSelector']//following-sibling::*");
	public static By objPasswordErrorMsg = By.xpath("//*[@text='Password has to be a minimum of 6 characters']");
	public static By objEyeIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/text_input_password_toggle']");
	public static By objTermsOfUseAndPrivacyPolicy = By.xpath("//*[@text='By creating this account you agree to our Terms of Use and Privacy Policy.']");
	public static By objTitleOfTermsOfUseAndPrivacyPolicy = By.xpath("(//*[@resource-id='iframe_div']//child::*)[1]");
	public static By objHeaderYearTxt = By.xpath("//*[@id='date_picker_header_year']");
	public static By objMonthDayDate = By.xpath("//*[@id='date_picker_header_date']");
	public static By objCancelBtn = By.xpath("//*[@text='CANCEL']");
	public static By objOkBtn = By.xpath("//*[@text='OK']");
	

}
