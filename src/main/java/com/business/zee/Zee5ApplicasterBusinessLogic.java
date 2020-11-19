package com.business.zee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.deviceDetails.DeviceDetails;
import com.driverInstance.CommandBase;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.ApplicasterPages.*;
import com.zee5.PWAPages.PWAPlayerPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Zee5ApplicasterBusinessLogic extends Utilities {

	public Zee5ApplicasterBusinessLogic(String Application) {
		new CommandBase(Application);
		init();
	}

	private int timeout;

	/** Retry Count */
	private int retryCount;
	ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
//	final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	@Override
	public int getTimeout() {
		return timeout;
	}

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	GmailInbox gmail = new GmailInbox();

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
//		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new Zee5ApplicasterBusinessLogic("zee");
	}

	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}

	String pUserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
	String RegisteredEmail = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("RegisteredEmail");
	String RegisteredEmailPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("RegisteredEmailPassword");
	String UnRegisteredMobile = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("UnRegisteredMobile");
	String RegisteredMobile = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("RegisteredMobile");
	String RegisteredMobilePassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("RegisteredMobilePassword");
	String PromoCode = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("PromoCode");
	String NonsubscribedUserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedUserName");
	String NonsubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedPassword");
	String SubscribedUserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedUserName");
	String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedPassword");
	String FirstName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("FirstName");
	String LastName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("LastName");

	String RSVODUser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("RSVODUser");
	String RSVODPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("RSVODPassword");

	String content1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("searchcontent1");
	String content2 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("searchcontent2");
	String content3 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("searchcontent3");
	String content4 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("searchcontent4");
	String content5 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("searchcontent5");
	String content6 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("searchcontent6");
	String pVideoQuality = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("VideoQuality");
	String pMovie = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("MovieName");

	// Retrieve the Mobile Device Name
	String getOEMName = DeviceDetails.OEM;

	public void accessDeviceLocationPopUp(String permission, String userType) throws Exception {
		extent.HeaderChildNode("Access Device Location PopUp");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		System.out.println("Access Device Location PopUp");
		if (verifyElementExist(AMDOnboardingScreen.objAllowBtn, "Device Location Permision")) {
			Wait(5000);
			verifyElementPresent(AMDOnboardingScreen.objAllowBtn, "Allow button");
			verifyElementPresent(AMDOnboardingScreen.objDenyBtn, "Deny button");

			if (permission.equalsIgnoreCase("Allow")) {
				click(AMDOnboardingScreen.objAllowBtn, "Allow button");
			} else {
				click(AMDOnboardingScreen.objDenyBtn, "Deny button");
			}
		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: SHREE NIDHI
	 * -------------------------------
	 * 
	 * List of Functions Created - contentLanguage(String userType) -
	 * mobileRegistration(String loginThrough) - subscribeNowSceanrios() -
	 * unRegisteredEmailSubscribe() - subscribeFLowMobileNumber() -
	 * passwordScenario(String UserType) - otpScenarios() -
	 * checkScreenAfterRelaunch(String userType, String ScreenName)
	 * =============================================================================
	 * ========
	 */

	public void contentLanguage(String userType) throws Exception {
		extent.HeaderChildNode("Content language screen functionlity");
		extent.extentLogger("User Type", "UserType : " + userType);
		logger.info("UserType : " + userType);
		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button in display language");
		verifyElementExist(AMDOnboardingScreen.objScreenTitle, "Screen header");
		verifyElementExist(AMDOnboardingScreen.objContentLanguagePageTitle, "Page title");
		verifyElementExist(AMDOnboardingScreen.objContentLanguageContainer, "Content language");
		verifyElementExist(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		verifyElementExist(AMDOnboardingScreen.objBackBtn, "Back button in content language screen");
		click(AMDOnboardingScreen.objSelectContentLang("English"), "Unselected English language");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		waitTime(3000);
		if (verifyElementExist(AMDOnboardingScreen.objLoginLnk, "Login button")) {
			logger.info("User is navigated to intro screen");
			extent.extentLogger("Navigation", "user is navigated to intro screen");
			Back(1);
		}
		PartialSwipe("UP", 1);
		click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Unselected Kannada language");
		PartialSwipe("UP", 1);
		if (verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language")) {
			click(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language");
			logger.info("Clicked on malayalam language");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		} else {
			Swipe("UP", 1);
			verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language");
			click(AMDOnboardingScreen.objSelectContentLang("Malayalam"), "Malayalam language");
			logger.info("Clicked on malayalam language");
			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button in content language screen");
		}
		waitTime(2000);
		String ContentLanguagetitle = getText(AMDOnboardingScreen.objContentLanguagePageTitle);
		logger.info(ContentLanguagetitle);
		if (ContentLanguagetitle.contains("please select at least one more language")) {
			logger.info("Select atleast one language screen is displayed");
			extent.extentLogger("Screen", "Select atleast one language screen is displayed");
		}

		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Hindi"), "Hindi language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("English"), "English language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Marathi"), "Marathi language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Telugu"), "Telugu language");
		Swipe("UP", 1);
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada language");
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Tamil"), "Tamil language");
		Swipe("UP", 1);
		verifyElementExist(AMDOnboardingScreen.objSelectContentLang("Bengali"), "Bengali language");
		verifyElementExist(AMDOnboardingScreen.objBackBtn, "Back button in content language screen");

		click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada language");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
				"Continue button in content language screen");
		waitTime(2000);
		if (verifyElementExist(AMDOnboardingScreen.objLoginLnk, "Login button")) {
			logger.info("User is navigated to intro screen");
			extent.extentLogger("Navigation", "user is navigated to intro screen");
		}
	}

	public void mobileRegistration(String loginThrough) throws Exception {
		extent.HeaderChildNode("Mobile Registration From Intro screen loginlink");
		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
				"Continue button in content language screen");
		navigateToRegisterScreen(loginThrough);
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "EmailField");
		type(AMDLoginScreen.objEmailIdField, UnRegisteredMobile, "Mobile");
		hideKeyboard();
		click(AMDLoginScreen.objProceedBtn, "Proceed icon");
		registerForFreeScreen("Mobile");
		waitTime(3000);
		otpScenarios();
		Back(1);
		waitTime(3000);
		hideKeyboard();
		Back(1);
		int lenText = findElement(AMDLoginScreen.objEmailIdField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(AMDLoginScreen.objEmailIdField).sendKeys(Keys.BACK_SPACE);
		}
		type(AMDLoginScreen.objEmailIdField, RegisteredMobile, "Mobile number field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		type(AMDLoginScreen.objPasswordField, RegisteredMobilePassword, "Password field");
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home tab");
	}

	public void subscribeNowSceanrios(String userType) throws Exception {
		extent.HeaderChildNode("SubscribeNow Functionality for Registered email-id");

		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
				"Continue button in content language screen");
		if (userType.equals("Guest")) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objSubscribeNowBtn, "Subscribe now button");
			subscribePageValidation();
			passwordScenario("Registered");
			unRegisteredEmailSubscribe();
			subscribeFLowMobileNumber();
		}
		if (userType.equals("NonSubscribedUser")) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login button");
			click(AMDLoginScreen.objEmailIdField, "Email field");
			verifyElementExist(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, NonsubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, NonsubscribedPassword, "Password field");
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
			verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
			relaunch(false);
			waitTime(2000);
			if (verifyElementExist(AMDHomePage.objHomeTab, "Home tab")) {
				logger.info(
						"When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
				extent.extentLogger("Relaunch",
						"When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
			}

		}
		if (userType.equals("SubscribedUser")) {
			verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login button");
			click(AMDLoginScreen.objEmailIdField, "Email field");
			verifyElementExist(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
			verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
			relaunch(false);
			waitTime(2000);
			if (verifyElementExist(AMDHomePage.objHomeTab, "Home tab")) {
				logger.info(
						"When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
				extent.extentLogger("Relaunch",
						"When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
			}
		}

	}

	public void unRegisteredEmailSubscribe() throws Exception {
		extent.HeaderChildNode("SubscribeNow Functionality for UnRegistered email-id");
		System.out.println("\nSubscribeNow Functionality for UnRegistered email-id");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
		verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
		verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
		Swipe("UP", 3);
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		verifyElementExist(AMDSubscibeScreen.objAccountInfoHeader, "Account info screen");
		hideKeyboard(); // Added by Kushal
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header");
		click(AMDSubscibeScreen.objEmailID, "Email id");
		type(AMDSubscibeScreen.objEmailID, RandomStringGenerator(5) + "@gmail.com", "Email");
		hideKeyboard();
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		passwordScenario("UnRegistered");

	}

	public void subscribeFLowMobileNumber() throws Exception {
		extent.HeaderChildNode("SubscribeNow Functionality for UnRegistered Mobile number");
		System.out.println("\nSubscribeNow Functionality for UnRegistered Mobile number");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
		closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD - HANDLED HERE
		verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
		verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
		PartialSwipe("UP", 4);
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		verifyElementExist(AMDSubscibeScreen.objAccountInfoHeader, "Account info screen");
		hideKeyboard();
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header");
		click(AMDSubscibeScreen.objEmailID, "Email id");
		type(AMDSubscibeScreen.objEmailID, UnRegisteredMobile, "Email");
		hideKeyboard();
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		verifyElementExist(AMDSubscibeScreen.objVerifyOTPScreen, "OTP screen");
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");

		click(AMDRegistrationScreen.objOTPField3, "OTP field 3");
		// Added a generic object to handle the numeric keyboard across various devices
//		if (verifyElementExist(AMDRegistrationScreen.objNumericKeys, "Numeric Keypad")) {
//			logger.info("Numeric keyboard is displayed in OTP screen");
//			extent.extentLogger("Numeric", "Numeric keyboard is displayed in OTP screen");
//		}
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");

		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDSubscibeScreen.objVerifyOTPScreenProceed).isEnabled() == true) {
			logger.info("Verify Button is highlighted");
			extent.extentLogger("Verify", "Verify Button is highlighted");
		}
		Back(1);

		extent.HeaderChildNode("SubscribeNow Functionality for Registered Mobile number");
		int lenText = findElement(AMDLoginScreen.objEmailIdField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(AMDLoginScreen.objEmailIdField).sendKeys(Keys.BACK_SPACE);
		}
		click(AMDSubscibeScreen.objEmailID, "Email");
		type(AMDSubscibeScreen.objEmailID, RegisteredMobile, "Email");

		hideKeyboard();
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		verifyElementExist(AMDSubscibeScreen.objEnterPassword, "Enter Password PopUp");
		verifyElementExist(AMDSubscibeScreen.objPasswordTextField, "Password field");
		verifyElementPresentAndClick(AMDSubscibeScreen.objGetOTP, "Get OTP");
		verifyElementExist(AMDSubscibeScreen.objVerifyOTPScreen, "OTP screen");

		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");

		// Added a generic object to handle the numeric keyboard across various devices
		click(AMDRegistrationScreen.objOTPField3, "OTP field 3");
//		if (verifyElementExist(AMDRegistrationScreen.objNumericKeys, "Numeric Keypad")) {
//			logger.info("Numeric keyboard is displayed in OTP screen");
//			extent.extentLogger("Numeric", "Numeric keyboard is displayed in OTP screen");
//		}
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");

		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDSubscibeScreen.objVerifyOTPScreenProceed).isEnabled() == true) {
			logger.info("Verify Button is highlighted");
			extent.extentLogger("Verify", "Verify Button is highlighted");
		}
		Back(1);
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		passwordScenario("Registered");
		Wait(2000);
		Back(1);
		closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD - HANDLED HERE
	}

	public void closeInterstitialAd(By byLocator, int iTimeOut) throws Exception {
		try {

			if (checkcondition(byLocator)) {
				click(byLocator, "Interstitial Ad_Close button");
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		}
	}

	public void passwordScenario(String UserType) throws Exception {

		System.out.println("\nPassword Scenario");
		verifyElementExist(AMDSubscibeScreen.objPasswordTextField, "Password field");
		click(AMDSubscibeScreen.objPasswordTextField, "Password");
		type(AMDSubscibeScreen.objPasswordTextField, "User", "Password field");
		hideKeyboard();
		System.out.println("DEVICE NAME : " + getOEMName);
		if (getOEMName.contains("vivo")) {
			hidePwdKeyboard();
		}
		verifyElementExist(AMDSubscibeScreen.objPasswordErrorMessage, "Error message");
		if (verifyElementExist(AMDSubscibeScreen.objPasswordHidden, "Toggle icon")) {
			logger.info("Passowrd is not shown");
			extent.extentLogger("password", "Passowrd is not shown");
		}
		click(AMDSubscibeScreen.objShowIcon, "Toggle password icon");
		if (verifyElementExist(AMDSubscibeScreen.objPasswordDisplayed, "Toggle icon")) {
			logger.info("Passowrd is shown after clicking on toggle passowrd icon");
			extent.extentLogger("password", "Passowrd is shown after clicking on toggle passowrd icon");
		}
		click(AMDSubscibeScreen.objShowIcon, "Toggle password icon");
		if (UserType == "Registered") {
			verifyElementExist(AMDSubscibeScreen.objForgotPassword, "forgot password");
			Wait(1000);
			click(AMDSubscibeScreen.objForgotPassword, "forgot password");
			verifyElementExist(AMDSubscibeScreen.objForgotPasswordPageHeader, "Forgot password page");
			Back(1);
			click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		}
		if (UserType == "UnRegistered") {
			logger.info("Forgot password is not displayed for unregistered user");
			verifyElementExist(AMDSubscibeScreen.objTermsandPrivacyLink, "Terms and privacy links");
		}
		verifyElementExist(AMDSubscibeScreen.objPasswordTextField, "Password field");
		click(AMDSubscibeScreen.objPasswordTextField, "Password");
		type(AMDSubscibeScreen.objPasswordTextField, RegisteredMobilePassword, "Password field");
		hideKeyboard();
		System.out.println("DEVICE NAME : " + getOEMName);
		if (getOEMName.contains("vivo")) {
			hidePwdKeyboard();
		}
		verifyElementPresentAndClick(AMDSubscibeScreen.objProceedPWDScreen, "Proceed button in password popup");
		waitTime(3000);
		PartialSwipe("DOWN", 3);
		verifyElementExist(AMDSubscibeScreen.objPaymentText, "Payment page");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in Payment page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in Payment page");
		Back(2);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		Swipe("UP", 2);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home tab");
		verifyElementPresentAndClick(AMDHomePage.objSubscribeTeaser, "Subscribe tab");
	}

	public void otpScenarios() throws Exception {
		verifyElementExist(AMDRegistrationScreen.objOTPScreen, "OTP screen");
		verifyElementExist(AMDRegistrationScreen.objOTPTimer, "OTP timer");
		String OTPTimer1 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer1);
		click(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(10000);
		String OTPTimer2 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer2);
		boolean Time = OTPTimer1.equals(OTPTimer2);
		if (Time == false) {
			logger.info("The Otp timer is in reverse order");
			extentLogger("OtpTimer", "The Otp timer is in reverse order");
		}

		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		if (verifyElementExist(AMDRegistrationScreen.objNumericKeyBoard, "Alphakeyboard")) {
			logger.info("Numeric keyboard is displayed in OTP screen");
			extent.extentLogger("Numeric", "Numeric keyboard is displayed in OTP screen");
		}
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDRegistrationScreen.objVerifyOtpButton).isEnabled() == true) {
			logger.info("Verify Button is highlighted");
			extent.extentLogger("Verify", "Verify Button is highlighted");
		}
	}

	public void checkScreenAfterRelaunch(String userType, String ScreenName) throws Exception {
		extent.HeaderChildNode("Validating that" + ScreenName + "is not present when app is relaunched");
		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue");

		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginLnk, "Login button");
		click(AMDLoginScreen.objEmailIdField, "Email field");
		verifyElementExist(AMDLoginScreen.objEmailIdField, "Email field");

		if (userType.equals("NonSubscribedUser")) {
			type(AMDLoginScreen.objEmailIdField, NonsubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, NonsubscribedPassword, "Password field");
		} else if (userType.equals("SubscribedUser")) {
			type(AMDLoginScreen.objEmailIdField, SubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			waitTime(2000);
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
		}

		if (userType.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip button");
		} else {
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		}

		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
		relaunch(false);
		waitTime(2000);

		if (userType.contains("Guest")) {
			if (verifyElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "Intro Screen")) {
				logger.info("When " + userType + " relaunch the app " + ScreenName + " is skipped");
				extent.extentLogger("Relaunch", "When" + userType + " relaunch the app" + ScreenName + " is skipped");
			} else {
				logger.error("When " + userType + " relaunch the app " + ScreenName + " is displayed");
				extent.extentLoggerFail("Relaunch",
						"When" + userType + " relaunch the app" + ScreenName + " is displayed");
			}
		} else if (verifyElementExist(AMDHomePage.objHomeTab, "Home tab")) {
			logger.info("When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
			extent.extentLogger("Relaunch",
					"When " + userType + " relaunch the app Display/Content language and Intro screen is skipped");
		} else {
			logger.error("When " + userType + " relaunch the app user is not redirected to Home page");
			extent.extentLoggerFail("Relaunch",
					"When " + userType + " relaunch the app user is not redirected to Home page");
		}
	}

	public void registerForFreeScreen(String user) throws Exception {
		extent.HeaderChildNode("Register for free Page");
		if (user.equals("Email")) {
			type(AMDRegistrationScreen.objEmailIDTextField, generateRandomString(5) + "@gmail.com", "Email field");
			verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
		} else if (user.equals("Mobile")) {
			logger.info("Mobile registration");
		}
		verifyElementExist(AMDRegistrationScreen.objScreenTitle, "Register for free title");
		type(AMDRegistrationScreen.objFirstNameTxtField, FirstName, "First name field");
		hideKeyboard();
		type(AMDRegistrationScreen.objLastNameTxtField, LastName, "Last name field");
		hideKeyboard();
		click(AMDRegistrationScreen.objDOBTxtField, "calender field");
		verifyElementPresent(AMDRegistrationScreen.objSelecteDOBPopup, "calender selection popup");
		verifyElementPresentAndClick(AMDRegistrationScreen.objDateSelection, "Date in calender popup");
		click(AMDRegistrationScreen.objOkButtonInCalenderPopUp, "Ok button");
		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");
		click(AMDRegistrationScreen.objPasswordTxtField, "Passowrd");
		type(AMDRegistrationScreen.objPasswordTxtField, "User@123", "Password field");
		click(AMDRegistrationScreen.objFirstNameTxtField, "First name"); // Clicking on First Name field to get device
																			// keyboard
		hideKeyboard();
		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		if (user.equals("Email")) {
			verifyElementExist(AMDHomePage.objHomeTab, "Home Screen");
		} else if (user.equals("Mobile")) {
			logger.info("Mobile registration");
		} else {
			logger.info("Prepaid PopUp after registration");
		}
	}

	public void subscribeEntryPointsValidations(String userType) throws Exception {
		HeaderChildNode("Entry points");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Display continue");
		click(AMDOnboardingScreen.objContent_ContinueBtn, " Content Continue");
		click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");
		if (userType.equals("Guest")) {
			hideKeyboard();
			click(AMDLoginScreen.objSkipButton, "Skip button");
			waitTime(10000);
//			closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD HANDLED HERE
			verifyElementExist(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			click(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(5000);
			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
			waitTime(5000);
//			closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000); // INTERSTITIAL AD - HANDLED HERE
			PartialSwipe("UP", 1);
			waitTime(5000);
			if (verifyElementExist(AMDHomePage.objBeforeTVTray, "BeforeTV tray")) {
				waitTime(5000);
				String beforeTVtrayName = findElement(AMDGenericObjects.objTrayTitle("Before")).getText();
				click(AMDGenericObjects.objViewAllBtn(beforeTVtrayName), "View All_Before TV Show");
				waitTime(4000);
				click(AMDHomePage.objBeforeTVContent, "BeforeTV content");
				waitTime(5000);
				verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
				Swipe("UP", 1);
				click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
				verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");

			} else {
				logger.info("Before TV tray is not displayed");
				extent.extentLogger("TV", "Before TV tray is not displayed");
			}
			Back(1);
			waitTime(3000);
			Back(1);
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.HomeIcon, "Home Tab");
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			subscribePageValidation();
			passwordScenario("Registered");
			unRegisteredEmailSubscribe();
			subscribeFLowMobileNumber();
			waitTime(10000);
//			click(AMDHomePage.objSearchBtn, "Search button");
//			waitTime(5000);
//			click(AMDSearchScreen.objSearchEditBox, "Search box");
//			type(AMDSearchScreen.objSearchBoxBar, "Natasaarvabhowma trailer\n", "Search box");
//			hideKeyboard();
//			waitTime(6000);
//			click(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"), "Search result");
//			waitTime(5000);
//			verifyElementExist(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"),
//					"Content name below the player");
//			waitTime(30000);
//			verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
//			click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
//			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
		}

		else if (userType.equals("NonSubscribedUser")) {
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, NonsubscribedUserName, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
			type(AMDLoginScreen.objPasswordField, NonsubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
			waitTime(1000);
			verifyElementExist(AMDHomePage.objHomeBtn, "Home button");
			click(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDHomePage.objMyProfileIcon, "profile icon");

			if (verifyElementExist(AMDHomePage.objEditProfile, "Edit profile")) {
				logger.info("User is logged in successfully");
				extent.extentLogger("Edit", "User is logged in successfully");
			}
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home tab");
			waitTime(4000);
			verifyElementExist(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			click(AMDHomePage.objGetPremiumCTAOnCarosel, "Get Premium CTA on carosel");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
//			waitTime(5000);                 NEED CLARIFICATION
//			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
//			waitTime(5000);
//			PartialSwipe("UP", 1);
//			waitTime(5000);
//			if (verifyElementExist(AMDHomePage.objBeforeTVTray, "BeforeTV tray")) {
//				Wait(10000);
//				click(AMDHomePage.objBeforeTVTray, "BeforeTV tray");
//				waitTime(4000);
//				click(AMDHomePage.objBeforeTVContent, "BeforeTV content");
//				waitTime(5000);
//				verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
//				click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
//				verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
//
//			} else {
//				logger.info("Before TV tray is not displayed");
//				extent.extentLogger("TV", "Before TV tray is not displayed");
//			}
//			Back(1);
//			waitTime(3000);
//			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home Tab");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More Menu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("My Subscription"), "My Subscription");
			verifyElementPresentAndClick(AMDHomePage.objSubscribeNowInMySubscription, "Subscribe now CTA");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(3000);
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("My Transactions"), "My Transactions");
			verifyElementPresentAndClick(AMDHomePage.objSubscribeNowInMyTransaction, "Subscribe now CTA");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(3000);
			Back(1);
			click(AMDHomePage.objHomeBtn, "Home Tab");
			verifyElementPresentAndClick(AMDHomePage.objSubscribeTeaser, "Subscribe button");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
			verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
			verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
			if (verifyElementExist(AMDSubscibeScreen.objApplybuttonNotHighlighted, "Apply button")) {
				logger.info("Apply button is not highlighted by default");
				extent.extentLogger("Highlighted", "Apply button is not highlighted by default");
			}
			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, PromoCode, "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo code applied successfully text");
			if (verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo")) {
				logger.info("Discounted price is displayed after promo code is applied");
				extent.extentLogger("Promo", "Discounted price is displayed after promo code is applied");
			}
			click(AMDSubscibeScreen.objCancelPromoCode, "Cancel promo");
			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "zee5flat5000", "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objInvalidPromoCodeText, "Invalid promo code error message");
			Swipe("UP", 2);
			PartialSwipe("UP", 2);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objAllAccessTab, "All access pack tab");
			verifyElementExist(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD pack tab");
			Swipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.obj30daysPack, "30 days premium plan tab");
			verifyElementExist(AMDSubscibeScreen.obj180daysPack, "180 days premium plan tab");
			verifyElementExist(AMDSubscibeScreen.obj365daysPack, "365 days premium plan tab");
			Swipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.objContinueBtn, "Continue button in subscribe page");
			if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLogger("Highlighted", "Continue button is highlighted");
			}
			Swipe("DOWN", 1);
//			click(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD Pack tab");
//			verifyElementPresentAndClick(AMDSubscibeScreen.objRSVODPack1, "RSVOD Plan for 30 days");
//			PartialSwipe("UP", 2);
//			verifyElementExist(AMDSubscibeScreen.objRSVODPack2, "RSVOD Plan for 365 days");
			Swipe("Down", 1);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			Swipe("Up", 2);
			if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLogger("Highlighted", "Continue button is highlighted");
			}
			click(AMDSubscibeScreen.objContinueBtn, "Continue button");
			verifyElementExist(AMDSubscibeScreen.objPaymentText, "Payment page");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in Payment page");
			verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in Payment page");
			Back(1);
			waitTime(3000);
			Back(1);
			waitTime(5000);
			click(AMDHomePage.objSearchBtn, "Search button");
			waitTime(5000);
			click(AMDSearchScreen.objSearchEditBox, "Search box");
			type(AMDSearchScreen.objSearchBoxBar, "Natasaarvabhowma trailer\n", "Search box");
			hideKeyboard();
			waitTime(6000);
			click(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"), "Search result");
			waitTime(5000);
			verifyElementExist(AMDSearchScreen.objContentNameInPlayer("Natasaarvabhowma - Trailer"),
					"Content name below the player");
			waitTime(30000);
			verifyElementExist(AMDHomePage.objGetPremiumPopUP, "PremiumPopUp");
			Swipe("UP", 1);
			click(AMDHomePage.objGetPremiumPopUPProceedButton, "Proceed button");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");

		}

		if (userType.equals("SubscribedUser")) {
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, RSVODUser, "Email field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
			type(AMDLoginScreen.objPasswordField, RSVODPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginButton, "Login button");
			waitTime(1000);
			verifyElementExist(AMDHomePage.objHomeBtn, "Home button");
			click(AMDHomePage.objMoreMenu, "More menu");
			verifyElementPresentAndClick(AMDHomePage.objMyProfileIcon, "profile icon");

			if (verifyElementExist(AMDHomePage.objEditProfile, "Edit profile")) {
				logger.info("User is logged in successfully");
				extent.extentLogger("Edit", "User is logged in successfully");
			}
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objHomeBtn, "Home tab");
			waitTime(4000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuBtn, "More Menu");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("My Subscription"), "My Subscription");
			verifyElementExist(AMDHomePage.objPackAmount, "Purchased pack details");
			verifyElementExist(AMDHomePage.objCancelRenewal, "Cancel Renewal option");
			verifyElementPresentAndClick(AMDHomePage.objBrowseAllPack, "Browse all packs button");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Back(1);
			waitTime(2000);
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
			verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
			verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
			if (verifyElementExist(AMDSubscibeScreen.objApplybuttonNotHighlighted, "Apply button")) {
				logger.info("Apply button is not highlighted by default");
				extent.extentLogger("Highlighted", "Apply button is not highlighted by default");
			}
			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, PromoCode, "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo code applied successfully text");
			if (verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo")) {
				logger.info("Discounted price is displayed after promo code is applied");
				extent.extentLogger("Promo", "Discounted price is displayed after promo code is applied");
			}
			click(AMDSubscibeScreen.objCancelPromoCode, "Cancel promo");
			click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
			type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "zee5flat5000", "Promo code");
			hideKeyboard();
			click(AMDSubscibeScreen.objApply, "Apply button");
			verifyElementExist(AMDSubscibeScreen.objInvalidPromoCodeText, "Invalid promo code error message");
			Swipe("UP", 1);
			PartialSwipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			verifyElementExist(AMDSubscibeScreen.objAllAccessTab, "All access pack tab");
			verifyElementExist(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD pack tab");
			Swipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.obj30daysPack, "30 days premium plan tab");
			verifyElementExist(AMDSubscibeScreen.obj180daysPack, "180 days premium plan tab");
			verifyElementExist(AMDSubscibeScreen.obj365daysPack, "365 days premium plan tab");
			Swipe("UP", 1);
			verifyElementExist(AMDSubscibeScreen.objContinueBtn, "Continue button in subscribe page");
			if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLogger("Highlighted", "Continue button is highlighted");
			}
			Swipe("DOWN", 1);
			click(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD Pack tab");
			verifyElementPresentAndClick(AMDSubscibeScreen.objRSVODPack1, "RSVOD Plan for 30 days");
			PartialSwipe("UP", 2);
			verifyElementExist(AMDSubscibeScreen.objRSVODPack2, "RSVOD Plan for 365 days");
			Swipe("Down", 1);
			verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
			Swipe("UP", 2);
			if (findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
				logger.info("Continue button is highlighted");
				extent.extentLogger("Highlighted", "Continue button is highlighted");
			}
			click(AMDSubscibeScreen.objContinueBtn, "Continue button");
			if (verifyElementExist(AMDHomePage.objHomeBtn, "Home tab")) {
				logger.info(
						"Subscribed user is navigated to home page after tapping on buy subscription continue button");
				extent.extentLogger("Home",
						"Subscribed user is navigated to home page after tapping on buy subscription continue button");
			}

			waitTime(5000);
			click(AMDHomePage.objSearchBtn, "Search button");
			waitTime(5000);
			click(AMDSearchScreen.objSearchEditBox, "Search box");
			type(AMDSearchScreen.objSearchBoxBar, "Chintu ka Birthday\n", "Search field");
			hideKeyboard();
			waitTime(7000);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchResultPremiunm("Chintu Ka Birthday"),
					"Search result premium content");
			waitTime(5000);
			verifyElementExist(AMDSearchScreen.objContentNameInPlayer("Chintu Ka Birthday"), "Content name in player");
			waitTime(25000);
			verifyElementExist(AMDSearchScreen.objUpgradePopup, "Upgrade popup for RSVOD user");
			verifyElementExist(AMDSearchScreen.objUpgradePopupTitle, "Upgrade text in upgrade popup");
			verifyElementExist(AMDSearchScreen.objUpgradePopupDescription, "Upgrade popup description");
			logger.info(getText(AMDSearchScreen.objUpgradePopupDescription));
			verifyElementExist(AMDSearchScreen.objUpgradePopUpPacks("INR 99 for 30 days"),
					"30 days pack in upgrade popup");
			verifyElementExist(AMDSearchScreen.objUpgradePopUpPacks("INR 299 for 90 days"),
					"90 days pack in upgrade popup");
			verifyElementExist(AMDSearchScreen.objUpgradePopUpPacks("INR 599 for 180 days"),
					"180 days pack in upgrade popup");
			verifyElementExist(AMDSearchScreen.objUpgradePopUpPacks("INR 999 for 365 days"),
					"365 days pack in upgrade popup");
			Swipe("UP", 1);
			PartialSwipe("UP", 1);
			verifyElementExist(AMDSearchScreen.objTermsOfUse, "Terms of use link");
			verifyElementExist(AMDSearchScreen.objPrivacyPolicy, "Privacy policy");
			if (findElement(AMDSearchScreen.objUpgradePopupProceedButton).isEnabled()) {
				logger.info("Proceed button is enebled when user select any pack in upgrade popup");
				extent.extentLogger("Proceed", "Proceed button is enebled when user select any pack in upgrade popup");
			}
			click(AMDSearchScreen.objUpgradePopupProceedButton, "Proceed");
			verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
			Swipe("DOWN", 1);
			verifyElementExist(AMDSearchScreen.objPlanPrice, "Plan price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objDiscount, "Discount price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objRoundoff, "Round off price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objTotalPayableAmount, "Total payable price detials in Subscribe page");
			verifyElementExist(AMDSearchScreen.objAccountInfo, "Account info detials in Subscribe page");
			Swipe("UP", 1);
			verifyElementExist(AMDSearchScreen.objYouWillBeChargedInfo, "Recurring amount detials in Subscribe page");
			relaunch(true);
			accessDeviceLocationPopUp("Allow", userType);
			subscribeAllaccessFunctionality();

		}
	}

	public void subscribeAllaccessFunctionality() throws Exception {
		HeaderChildNode("Subscribe All access functionality");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Display continue");
		click(AMDOnboardingScreen.objContent_ContinueBtn, " Content Continue");
		click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");

		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
		type(AMDLoginScreen.objEmailIdField, SubscribedUserName, "Email field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginButton, "Login button");
		waitTime(1000);
		verifyElementExist(AMDHomePage.objHomeBtn, "Home button");
		click(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDHomePage.objMyProfileIcon, "profile icon");

		if (verifyElementExist(AMDHomePage.objEditProfile, "Edit profile")) {
			logger.info("User is logged in successfully");
			extent.extentLogger("Edit", "User is logged in successfully");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenuOptions("Buy Subscription"), "Buy Subscription");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page");
		Swipe("UP", 1);
		waitTime(3000);
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDSubscibeScreen.obj365daysPack, "365 days all access pack");
		Swipe("UP", 1);
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		if (verifyElementExist(AMDHomePage.objHomeBtn, "Home tab")) {
			logger.info(
					"Subscribed user is navigated to home page after tapping on all access plan subscription continue button");
			extent.extentLogger("Home",
					"Subscribed user is navigated to home page after tapping on all access plan subscription continue button");
		}
	}

	public void subscribePageValidation() throws Exception {
		System.out.println("\nVerifying Subscribe Page");
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header in subscription page");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in subscribe page");
		verifyElementExist(AMDSubscibeScreen.objAdbanner, "Carosual in subscription page");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo code in subscribe page");
		verifyElementPresent(AMDSubscibeScreen.objApply, "Apply button is subscribe page");
		if (verifyElementExist(AMDSubscibeScreen.objApplybuttonNotHighlighted, "Apply button")) {
			logger.info("Apply button is not highlighted by default");
			extent.extentLogger("Highlighted", "Apply button is not highlighted by default");
		}
		click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
		type(AMDSubscibeScreen.objApplyPromoCodeTextbox, PromoCode, "Promo code");
		hideKeyboard();
		click(AMDSubscibeScreen.objApply, "Apply button");
		verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo code applied successfully text");
		if (verifyElementExist(AMDSubscibeScreen.objApplyPromoCodeappliedText, "Promo")) {
			logger.info("Discounted price is displayed after promo code is applied");
			extent.extentLogger("Promo", "Discounted price is displayed after promo code is applied");
		}
		click(AMDSubscibeScreen.objCancelPromoCode, "Cancel promo");
		click(AMDSubscibeScreen.objApplyPromoCodeTextbox, "Promo");
		type(AMDSubscibeScreen.objApplyPromoCodeTextbox, "zee5flat5000", "Promo code");
		hideKeyboard();
		click(AMDSubscibeScreen.objApply, "Apply button");
		verifyElementExist(AMDSubscibeScreen.objInvalidPromoCodeText, "Invalid promo code error message");
		Swipe("UP", 1);
		verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
		verifyElementExist(AMDSubscibeScreen.objAllAccessTab, "All access pack tab");
		verifyElementExist(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD pack tab");
		Swipe("UP", 1);
		verifyElementExist(AMDSubscibeScreen.obj30daysPack, "30 days premium plan tab");
		verifyElementExist(AMDSubscibeScreen.obj180daysPack, "180 days premium plan tab");
		verifyElementExist(AMDSubscibeScreen.obj365daysPack, "365 days premium plan tab");
		PartialSwipe("UP", 2);
		verifyElementExist(AMDSubscibeScreen.objContinueBtn, "Continue button in subscribe page");
		if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
			logger.info("Continue button is highlighted");
			extent.extentLogger("Highlighted", "Continue button is highlighted");
		}
		Swipe("DOWN", 1);
		click(AMDSubscibeScreen.objRSVODPremiumPack, "RSVOD Pack tab");
		verifyElementPresentAndClick(AMDSubscibeScreen.objRSVODPack1, "RSVOD Plan for 30 days");
		PartialSwipe("UP", 1);
		verifyElementExist(AMDSubscibeScreen.objRSVODPack2, "RSVOD Plan for 365 days");
		verifyElementExist(AMDSubscibeScreen.objDescriptionText, "Premium Description in subscribe page");
		Swipe("UP", 1);
		if (getDriver().findElement(AMDSubscibeScreen.objContinueBtn).isEnabled()) {
			logger.info("Continue button is highlighted");
			extent.extentLogger("Highlighted", "Continue button is highlighted");
		}
		click(AMDSubscibeScreen.objContinueBtn, "Continue button");
		verifyElementExist(AMDSubscibeScreen.objAccountInfoHeader, "Account info screen");
		hideKeyboard();// ADDED By Kushal
		verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe header");
		verifyElementExist(AMDSubscibeScreen.objSubscribePageBackButton, "Back button in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objSelectedPackDesc, "Selected pack description in account info screen");
		verifyElementExist(AMDSubscibeScreen.objEmailID, "Email id field in Account info screen");
		hideKeyboard();
		Swipe("UP", 1);
		verifyElementExist(AMDSubscibeScreen.objORSeperator, "OR seperator in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objFacebookIcon, "FB icon in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objGoogleIcon, "Google icon in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objTwitterIcon, "Twitter icon in Account info screen");
		verifyElementExist(AMDSubscibeScreen.objProceedButtonNothighlighted, "Proceed button dehighlighted by default");
		click(AMDSubscibeScreen.objEmailID, "Email");
		type(AMDSubscibeScreen.objEmailID, RegisteredEmail, "Email field");
		hideKeyboard();
		String Email = getText(AMDSubscibeScreen.objEmailID);
		if (Email != null) {
			logger.info("User can enter email/mobile number in email id field");
			extent.extentLogger("Email", "User can enter email/mobile number in email id field");
		}
		verifyElementExist(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		click(AMDSubscibeScreen.objProceedBtn, "Proceed button");
		verifyElementExist(AMDSubscibeScreen.objEnterPassword, "Enter Password PopUp");
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: BINDU
	 * -------------------------------
	 * 
	 * List of Functions Created - BrowseForFreeAndMobileRegistration() -
	 * VerifyLoginPage() - SearchBox(String userType) -
	 * verifySearchLandingScreen(String userType) - verifySearchOption(String
	 * userType)
	 * =============================================================================
	 * ========
	 */

	public void BrowseForFreeAndMobileRegistration() throws Exception {

		extent.HeaderChildNode("Validating BrowseForFree Button on the intro screen");
		waitTime(5000);
		verifyElementPresent(AMDLoginScreen.objBrowseForFreeBtn, "BrowseForFreee Button");
		waitTime(5000);
		String BrowseForFree = getText(AMDLoginScreen.objBrowseForFreeBtn);
		System.out.println(BrowseForFree);
		if (BrowseForFree.concat("English") != null) {
			logger.info("Browse For Free text is displayed in Selected Language");
			extent.extentLogger("Login/Register Screen", "Browse For Free text is displayed in Selected Language");
		} else {
			logger.info("Browse For Free text is not displayed in Selected Language");
			extent.extentLogger("Login/Register Screen", "Browse For Free text is not displayed in Selected Language");

		}

		verifyElementPresentAndClick(AMDLoginScreen.objBrowseForFreeBtn, "BrowseForFreee Button");
		waitTime(6000);
		logger.info("User navigated to Login/Register screen tapping on the Browser For Free Button");
		extent.extentLogger("Login/Register Screen",
				"User navigated to Login/Register screen tapping on the Browser For Free Button");

		extent.HeaderChildNode("Validating user navigated to OTP Verification Screen");
		verifyElementPresent(AMDLoginScreen.objEmailIdField, "Email Field");
		click(AMDLoginScreen.objEmailIdField, "Email Field");
		hideKeyboard();
		type(AMDLoginScreen.objEmailIdField, "1234567891", "Email Field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
		waitTime(4000);
		verifyElementPresentAndClick(AMDRegistrationScreen.objFirstNameTxtField, "FirstName Field");
		hideKeyboard();
		type(AMDRegistrationScreen.objFirstNameTxtField, "Zeetest", "FirstName Field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objLastNameTxtField, "LastName Field");
		hideKeyboard();
		type(AMDRegistrationScreen.objLastNameTxtField, "Test", "LastName Field");

		verifyElementPresentAndClick(AMDRegistrationScreen.objDOBTxtField, "Date Of Birth Field");

		// verifyElementPresentAndClick(AMDRegistrationScreen.objDateAccept, "Date Of
		// Birth");

		verifyElementPresentAndClick(AMDRegistrationScreen.objDateSelection, "Date in calender popup");
		click(AMDRegistrationScreen.objOkButtonInCalenderPopUp, "Ok button");

//    verifyElementPresentAndClick(AMDEditProfileScreen.objGenderDropdown, "Gender Dropdown");
//    waitTime(3000);
//    verifyElementPresentAndClick(AMDRegistrationScreen.objFemale, "Female");        
//    waitTime(2000);

		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objMale, "Gender male");

		verifyElementPresentAndClick(AMDRegistrationScreen.objPasswordTxtField, "Password Field");
		waitTime(2000);
		hideKeyboard();
		type(AMDRegistrationScreen.objPasswordTxtField, "adcbdefg", "Password Field");
		waitTime(2000);

//    verifyElementPresentAndClick(AMDRegistrationScreen.objRegister, "Register Field");
//    waitTime(6000);
//    verifyElementPresent(AMDRegistrationScreen.objVerifyOTPScreen, "OTP Verificatin Page");

		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
		waitTime(3000);

		logger.info("User navigated to OTP Verification Page");
		extent.extentLogger("OTP Verification Screen", "User navigated to OTP Verification screen");
		otpScenarios();
		Back(1);
		waitTime(3000);
		hideKeyboard();
		Back(1);
		int lenText = findElement(AMDLoginScreen.objEmailIdField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getDriver().findElement(AMDLoginScreen.objEmailIdField).sendKeys(Keys.BACK_SPACE);
		}
		type(AMDLoginScreen.objEmailIdField, RegisteredMobile, "Mobile number field");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		type(AMDLoginScreen.objPasswordField, RegisteredMobilePassword, "Password field");
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login button");
		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");

	}

	public void VerifyLoginPage() throws Exception {

		extent.HeaderChildNode(
				"Validating the Navigation to Login or Register Screen Tapping on the Login link available in Intro Screen");
		// waitTime(8000);
		verifyElementPresent(AMDLoginScreen.objLoginLnk, "Login Link");
		click(AMDLoginScreen.objLoginLnk, "Login Link");
		verifyElementPresent(AMDLoginScreen.objLoginPage, "Login Page");
		logger.info("User navigated to Login/Register Screen Tapping on the Login link present on the Intro Screen");
		extent.extentLogger("Login/Register Screen",
				"User is navigated to Login/register Screen Tapping on the Login link present on the Intro Screen");
		verifyElementPresentAndClick(AMDLoginScreen.objSkipButton, "Skip button");
		// waitTime(4000);
		if (verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab")) {
			logger.info("User navigated to Home Tab by clicking on the Skip button");
			extent.extentLogger("Home Tab", "User navigated to Home Tab by clicking on the Skip button");
		} else {
			logger.info("User not navigated to Home Tab by clicking on the Skip button");
			extent.extentLoggerFail("Home Tab", "User not navigated to Home Tab by clicking on the Skip button");

		}

		waitTime(2000);
		extent.HeaderChildNode(
				"Validating the Navigation to Login or Register Screen Tapping on the Login link available in Menu Screen");

		waitTime(2000);
		verifyElementPresent(AMDLoginScreen.objMenu, "Menu icon");
		click(AMDLoginScreen.objMenu, "Menu icon");
		verifyElementPresent(AMDLoginScreen.objProfileIcon, "Login Button");
		click(AMDLoginScreen.objProfileIcon, "Login Button");
		if (verifyElementPresent(AMDLoginScreen.objLoginPage, "Login Page")) {
			logger.info(
					"User is navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
			extent.extentLogger("Login/Register Screen",
					"User is navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
		} else {

			logger.info(
					"User not  navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
			extent.extentLoggerFail("Login/Register Screen",
					"User not navigated to Login/register Screen Tapping on the Login link present in the Menu Screen");
		}

		extent.HeaderChildNode("Validating UI/UX of Login Page");

		WebElement element = findElement(AMDLoginScreen.objLoginOrRegisterPageTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Login/Register screen title is displayed at center of the screen");
			extent.extentLogger("Screen Title", "Login/Register screen title is displayed at center of the screen");
		} else {
			logger.error("Login/Register screen title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title",
					"Login/Register screen title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		WebElement elementSkipBtn = findElement(AMDLoginScreen.objLoginLnk);
		int SkipBtnRightX = elementSkipBtn.getLocation().getX();
		System.out.println(SkipBtnRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizeee);

		if (SkipBtnRightX >= sizeee) {
			logger.info("Skip button is displayed at top right of the screen");
			extent.extentLogger("Skip button", "Skip button is displayed at top right of the screen");
		} else {
			logger.error("Skip button is not displayed at top right of the screen");
			extent.extentLoggerFail("Skip button", "Skip button is not displayed at top right of the screen");
		}

		verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Google Button");
		verifyElementPresent(AMDLoginScreen.objfbBtn, "Facebook Button");
		verifyElementPresent(AMDLoginScreen.objtwitterBtn, "Twitter Button");
		logger.info("Validated the UI/UX of Login Page");
		extent.extentLogger("Login/Register Screen", "Validated the UI/UX of Login page");

		extent.HeaderChildNode("Validating EmailID/Mobile No field is displayed on Login/Register screen");
		if (verifyElementPresent(AMDLoginScreen.objEmailIdField, "Email Field")) {
			logger.info("EmailID/Mobile No field is dispalyed");
			extent.extentLogger("Login/Register Screen", "EmailID/Mobile No field is dispalyed");
		} else {
			logger.info("EmailID/Mobile No field is not dispalyed");
			extent.extentLoggerFail("Login/Register Screen", "EmailID/Mobile No field is not dispalyed");
		}

		extent.HeaderChildNode("Validating usen can enter EmailID or Mobile NO");
		click(AMDLoginScreen.objEmailIdField, "Email Field");
		hideKeyboard();

		type(AMDLoginScreen.objEmailIdField, "zeetest123@gmail.com", "Email Field");
		if (verifyElementPresent(AMDLoginScreen.objProceedBtn, "Proceed Button")) {
			logger.info("Proceed button is displayed , user entered the correct EmailID format");
			extent.extentLogger("Login/Register Screen",
					"Proceed button is displayed , user entered the correct EmailID format");
		} else {
			logger.info("Proceed button is not displayed , user not entered the correct EmailID format");
			extent.extentLogger("Login/Register Screen",
					"Proceed button is not displayed , user not entered the correct EmailID format");

		}

		// Check Proceed Button in highlighted

		if (getAttributValue("clickable", AMDRegistrationScreen.objProceedBtn).equals("true")) {
			logger.info("Proceed CTA is displayed and is highlated");
			extent.extentLogger("Proceed button", "Proceed CTA is displayed and is highlighted");
		} else {
			logger.error("Proceed CTA is not activated");
			extent.extentLoggerFail("Proceed button", "Proceed CTA is not activated");
		}

		extent.HeaderChildNode("Validating UI/UX of Login Page post changing the Display Language");
		waitTime(4000);
		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		verifyElementPresentAndClick(AMDLoginScreen.objSettings, "Settings Button");
		verifyElementPresentAndClick(AMDLoginScreen.objDisplayLang, "Display Language");
		waitTime(3000);
		verifyElementPresentAndClick(AMDLoginScreen.objLangHindi, "Display Language Hindi");
		verifyElementPresentAndClick(AMDLoginScreen.objLanguageContinueBtn, "Display Language Continue");
		waitTime(2000);
		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		waitTime(6000);
		verifyElementPresentAndClick(AMDLoginScreen.objMenuHindi, "Menu icon");
		verifyElementPresentAndClick(AMDLoginScreen.objProfileIcon, "Profile Icon");
		waitTime(3000);

		// verifyElementPresent(AMDLoginScreen.objLoginTextChanged, "Login Text");

		Dimension size1 = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size1.width) / 2)) {
			logger.info("Login/Register screen title is displayed at center of the screen");
			extent.extentLogger("Screen Title", "Login/Register screen title is displayed at center of the screen");
		} else {
			logger.error("Login/Register screen title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title",
					"Login/Register screen title is not displayed at center of the screen");
		}

		WebElement elementBackBtn1 = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX1 = elementBackBtn1.getLocation().getX();
		int BAckBtnrightX1 = BackBtnleftX1 + elementBackBtn1.getSize().getWidth();
		int BackBtnmiddleX1 = (BAckBtnrightX1 + BackBtnleftX1) / 2;

		if (BackBtnmiddleX1 <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		WebElement elementSkipBtn1 = findElement(AMDLoginScreen.objLoginLnk);
		int SkipBtnRightX1 = elementSkipBtn1.getLocation().getX();
		System.out.println(SkipBtnRightX1);
		Dimension sizee1 = getDriver().manage().window().getSize();
		System.out.println(sizee1.getWidth());
		int sizeee1 = sizee1.getWidth() - 300;
		System.out.println(sizeee1);

		if (SkipBtnRightX1 >= sizeee1) {
			logger.info("Skip button is displayed at top right of the screen");
			extent.extentLogger("Skip button", "Skip button is displayed at top right of the screen");
		} else {
			logger.error("Skip button is not displayed at top right of the screen");
			extent.extentLoggerFail("Skip button", "Skip button is not displayed at top right of the screen");
		}
		verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Google Button");
		verifyElementPresent(AMDLoginScreen.objfbBtn, "Facebook Button");
		verifyElementPresent(AMDLoginScreen.objtwitterBtn, "Twitter Button");
		logger.info("Validated the UI/UX of Login Page post changing the Display Language");
		extent.extentLogger("Login/Register Screen",
				"Validated the UI/UX of Login page post changing the Display Language");

		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		verifyElementPresentAndClick(AMDLoginScreen.objSettingsHindi, "Settings Button");
		verifyElementPresentAndClick(AMDLoginScreen.objDisplayLangHindi, "Display Language");
		waitTime(3000);
		verifyElementPresentAndClick(AMDLoginScreen.objLangEnglish, "Display Language English");
		verifyElementPresentAndClick(AMDLoginScreen.objLanguageContinueBtn, "Display Language Continue");
		waitTime(2000);
		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
	}

	public void SearchBox(String userType) throws Exception {
		extent.HeaderChildNode("validating the UI/UX of search Landing screen");
		System.out.println("\nValidating the UI/UX of search Landing screen");
		verifyElementPresent(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchIcon, "Search icon");
		waitTime(6000);

		verifyElementExist(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");
		verifyElementExist(AMDSearchScreen.objUpcomingOption, "Bottom bar Upcoming Option");
		verifyElementExist(AMDSearchScreen.objDownloadsOption, "Bottom bar Downloads Option");
		verifyElementExist(AMDSearchScreen.objMoreOption, "Bottom bar More Option");

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX1 = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX1 <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		WebElement elementVoiceIcon = findElement(AMDSearchScreen.objVoiceicon);
		int VoiceIconRightX = elementVoiceIcon.getLocation().getX();
		System.out.println(VoiceIconRightX);
		Dimension sizee = getDriver().manage().window().getSize();
//		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizee.getWidth() + " X " + sizeee);

		if (VoiceIconRightX >= sizeee) {
			logger.info("Microphone search icon is displayed at top right of the Search screen");
			extent.extentLogger("Microphone icon",
					"Microphone search icon is displayed at top right of the Search screen");
		} else {
			logger.error("Microphone icon is not displayed at top right of the Search screen");
			extent.extentLoggerFail("Microphone icon",
					"Microphone icon is not displayed at top right of the Search screen");
		}

		click(AMDSearchScreen.objVoiceicon, "Microphone Icon");
		// wait(2000);
		String MicrophoneAccessPopup = getDriver().findElement(AMDSearchScreen.objMicroPhone).getText();
		System.out.println(MicrophoneAccessPopup);

		if (MicrophoneAccessPopup.equalsIgnoreCase(
				"ZEE5 requires your permission to access your device's microphone to enable voice search. The voice data is not stored with ZEE5.")) {
			logger.info("Device Microphone access permission pop up is displayed");
			extent.extentLogger("Voice Search Icon", "Device Microphone access permission pop up is displayed");
		} else {
			logger.error("Device Microphone access permission pop up is not displayed");
			extent.extentLoggerFail("Voice Search icon", "Device Microphone access permission pop up is not displayed");
		}

		verifyElementExist(AMDSearchScreen.objVoiceSearchBackButton, "Back button");
		click(AMDSearchScreen.objVoiceSearchBackButton, "Back button");

		waitTime(2000);

		verifyElementExist(AMDSearchScreen.objsearchBox, "Search Box");
		logger.info("Search box is available on top section of search landing screen");
		extent.extentLogger("Search box", "Search box is available on top section of search landing screen");

		String SearchBoxText = getDriver().findElement(AMDSearchScreen.objsearchBox).getText();

		System.out.println(SearchBoxText);
		logger.info(SearchBoxText);

		if (SearchBoxText.equalsIgnoreCase("Search for Movies, Shows, Channels etc.")) {
			logger.info("Water Marked Text is displayed by default in Search Box");
			extent.extentLogger("Search box", "Water Marked Text is displayed by default in Search Box");
		} else {
			logger.error("Water Marked Text is not displayed by default in Search Box");
			extent.extentLoggerFail("Search Box", "Water Marked Text is not displayed by default in Search Box");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab");

		logger.info("User navigated to previous screen on tapping of back button available on the search screen");
		extent.extentLogger("Previous screen",
				"User navigated to previous screen on tapping of back button available on the search screen");

		verifyElementPresent(AMDSearchScreen.objSearchIcon, "Search icon");
		click(AMDSearchScreen.objSearchIcon, "Search icon");

//		waitTime(2000);
//		if(verifyElementExist(AMDSearchScreen.objTrendingSearchOverlay,"Trending Search Overlay"))
//		{
//		logger.info("Trending search overlay is displayed");
//		extent.extentLogger("Search screen","Trending search overlay is displayed");
//		}else{
//			logger.info("Trending search overlay is not displayed");
//			extent.extentLoggerFail("Search screen","Trending search overlay is not displayed");
//		}
//		waitTime(2000);
//		if(verifyElementExist(AMDSearchScreen.objTopSearchOverlay,"Top search Overlay"))
//		{
//			logger.info("Top search overlay is displayed");
//			extent.extentLogger("Search screen","Top search overlay is displayed");
//		}else{
//			logger.info("Top search overlay is not displayed");
//			extent.extentLoggerFail("Search screen","Top search overlay is not displayed");
//			
//		}

//		click(AMDSearchScreen.objSearchEditBox, "Search box");
//		if(verifyElementExist(AMDSearchScreen.objVirtualKeyboard, "Keyboard"))
//		{
//			logger.info("Keyboard is displayed");
//			extent.extentLogger("Search screen","Keyboard is displayed");
//		}
//		else
//		{
//			logger.error("Keyboard is not displayed");
//			extent.extentLoggerFail("Search Screen", "Keyboard is not displayed");
//			
//		}
//		
//		hideKeyboard();	
		verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");

	}

	public void verifySearchLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validating " + userType + " user navigates to Search landing screen");
		System.out.println("\nValidating " + userType + "user navigates to Search landing screen");
		waitTime(10000);
		verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab");
		boolean liveTV = false;

		int noOfTabs = getCount(AMDHomePage.objTitle);
		for (int i = 1; i <= 10; i++) {
			String tabName = null;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();

			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();
			}

			waitTime(2000);

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Search Icon", tabName + " tab is displayed and clicked on " + tabName + " tab");

			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			waitTime(2000);

			if (verifyElementExist(AMDSearchScreen.objSearchEditBox, "Search box")) {
				logger.info("User navigated to Search Landing screen");
				extent.extentLogger("Search Screen", "User navigated to Search Landing screen");
			} else {
				logger.error("User not navigated to Search Landing screen");
				extent.extentLoggerFail("Search Screen", "User not navigated to Search Landing screen");
			}

			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
			waitTime(2000);

			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}
		}

		verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");

	}

	public void verifySearchOption(String userType) throws Exception {
		extent.HeaderChildNode("Verify Search Icon on Landing pages as : " + userType);
		System.out.println("\nVerify Search Icon on Landing pages as " + userType);
		waitTime(10000);
		verifyElementPresent(AMDLoginScreen.objHomeTab, "Home Tab");
		boolean liveTV = false;

		int noOfTabs = getCount(AMDHomePage.objTitle);
		System.out.println("HOME PAGE HEADERS: " + noOfTabs);
		for (int i = 1; i <= 10; i++) {

			String tabName = null;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();

			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				tabName = eleTab.getText();
				System.out.println(tabName);
				eleTab.click();
			}

			waitTime(2000);

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Search Icon", tabName + " tab is displayed and clicked on " + tabName + " tab");

			WebElement elementSearchIcon = findElement(AMDSearchScreen.objSearchIcon);
			int SearchIconRightX = elementSearchIcon.getLocation().getX();
			System.out.println(SearchIconRightX);
			Dimension sizee = getDriver().manage().window().getSize();
//			System.out.println(sizee.getWidth());
			int sizeee = sizee.getWidth() - 300;
			System.out.println(sizee.getWidth() + " X " + sizeee);

			if (SearchIconRightX >= sizeee) {
				logger.info("Search icon is displayed at top right of the " + tabName + " tab ");
				extent.extentLogger("Search icon", "Search icon is displayed at top right of the " + tabName + " tab ");
			} else {
				logger.error("Search icon is not displayed at top right of the " + tabName + " tab ");
				extent.extentLoggerFail("Search icon",
						"Search icon is not displayed at top right of the " + tabName + " tab ");
			}
			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}

		}

//		verifyElementPresent(AMDLoginScreen.objMenu, "Menu icon");
//		click(AMDLoginScreen.objMenu, "Menu icon");
//		
//       if(verifyElementExist(AMDSearchScreen.objSearchIcon, "Search Icon"))
//       {
//        
//        logger.info("Search icon is displayed at top right of the More Screen");
//		extent.extentLogger("Search icon", "Search icon is displayed at top right of the More Screen");
//       }
//       else
//       {
//    	   logger.info("Search icon is not displayed at top right of the More Screen");
//			   extent.extentLoggerFail("Search icon", "Search icon is not displayed at top right of the More Screen");
//       }
//		
		verifyElementPresentAndClick(AMDSearchScreen.objHomeOption, "Bottom bar Home Option");

	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: SUSHMA
	 * -------------------------------
	 * 
	 * List of Functions Created - DisplayLanguagePopUp(String userType, String
	 * displaylanguage1, String displaylanguage2) -
	 * DisplayLanguagePopUpValidation(String, String) - loginOrRegisterScreen(String
	 * inValidPhnNo, String validPhnNo, String loginThrough, String userType) -
	 * loginScreen(String validPassword) - OtpScreen(String otp1, String otp2,
	 * String otp3, String otp4) - TopSearches() -
	 * =============================================================================
	 * ========
	 */

	public void DisplayLanguagePopUpValidation(String displayLanguageSelection1, String displayLanguageSelection2)
			throws Exception {

		extent.HeaderChildNode("Display Language PopUp Validation");

		verifyElementPresent(AMDLoginScreen.objDisplayLanguageScreenTitle, "Display language screen Header");
		verifyElementPresent(AMDLoginScreen.objPageTitle, "Display language page title");
		verifyElementPresent(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresent(AMDOnboardingScreen.objBackBtn, "Back button");

		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage, "Selected display language");
		String selectedlanguage = getText(AMDLoginScreen.objSelectedDisplayLanguage);
		logger.info(selectedlanguage + " language is selected by Default");
		extentLogger("Selected language", selectedlanguage + " language is selected by Default");

		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection2), "language");
		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage, "Selected display language");
		int totalSelectedLanguages = getDriver().findElements(AMDLoginScreen.objSelectedDisplayLanguage).size();
		logger.info(totalSelectedLanguages);

		if (totalSelectedLanguages == 1) {
			logger.info("User is able to select only one language from the display language list");
			extentLogger("Select one language",
					"User is able to select only one language from the display language list");
		}

		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1), "English language");

		String pos1 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		String pos2 = null;

		HashSet<String> h = new HashSet<String>();

		for (int i = 0; i < 3; i++) {
			int totallangs = getDriver().findElements(By.xpath("//*[@id='display_language_content']")).size();
			for (int j = 1; j <= totallangs; j++) {
				String lang = getDriver().findElement(By.xpath("(//*[@id='display_language_content'])[" + j + "]"))
						.getText();
				h.add(lang);
			}
			Swipe("UP", 1);
			pos2 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		}
		System.out.println(h.size());
		if (h.size() == 11) {
			logger.info("Display language screen is displayed with all the display languages");
			extentLogger("Display Languages", "Display language screen is displayed with all the display languages");
		} else {
			logger.info("Display language screen is not displayed with all the display languages");
			extentLoggerFail("Display Languages",
					"Display language screen is not displayed with all the display languages");
		}
		if (pos1 != pos2) {
			logger.info("User is able to scroll up and down in the language list");
			extentLogger("Swipe", "User is able to scroll up and down in the language list");
		} else {
			logger.info("User is not able to scroll up and down in the language list");
			extentLoggerFail("Swipe", "User is not able to scroll up and down in the language list");
		}

		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresent(AMDLoginScreen.objContentLanguageScreenTitle, "Content Language screen");
		Back(1);
//	verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Content Language PopUp Continue button");
//	relaunch();
//	if(getDriver().findElement(AMDOnboardingScreen.objBrowseForFreeBtn).isDisplayed())
//	{
//		logger.info("Display langauge screen is displayed only when user launch the app for the first time");
//		extentLogger("Display language", "Display langauge screen is displayed only when user launch the app for the first time");
//	}
	}

	public void loginOrRegisterScreen(String inValidPhnNo, String validPhnNo, String loginThrough, String userType)
			throws Exception {
		navigateToRegisterScreen(loginThrough);
//	verifyElementPresent(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free button");
//	String Browseforfreetxt = getText(AMDOnboardingScreen.objBrowseForFreeBtn);
//	logger.info(Browseforfreetxt);
//	click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free button");
		extent.HeaderChildNode("Validating Login/Registration screen");
		if (userType.equals("Guest")) {
			WebElement element = findElement(AMDLoginScreen.objLoginOrRegisterPageTitle);
			int leftX = element.getLocation().getX();
			int rightX = leftX + element.getSize().getWidth();
			int middleX = (rightX + leftX) / 2;
			Dimension size = getDriver().manage().window().getSize();
			if (middleX == Integer.valueOf((size.width) / 2)) {
				logger.info("Login/Register screen title is displayed at center of the screen");
				extent.extentLogger("Screen Title", "Login/Register screen title is displayed at center of the screen");
			} else {
				logger.error("Login/Register screen title is not displayed at center of the screen");
				extent.extentLoggerFail("Screen Title",
						"Login/Register screen title is not displayed at center of the screen");
			}

			WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
			int BackBtnleftX = elementBackBtn.getLocation().getX();
			int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
			int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

			if (BackBtnmiddleX <= 200) {
				logger.info("Back button is displayed at top left of the screen");
				extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
			} else {
				logger.error("Back button is not displayed at top left of the screen");
				extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
			}

			WebElement elementSkipBtn = findElement(AMDLoginScreen.objLoginLnk);
			int SkipBtnRightX = elementSkipBtn.getLocation().getX();
			System.out.println(SkipBtnRightX);
			Dimension sizee = getDriver().manage().window().getSize();
			System.out.println(sizee.getWidth());
			int sizeee = sizee.getWidth() - 300;
			System.out.println(sizeee);

			if (SkipBtnRightX >= sizeee) {
				logger.info("Skip button is displayed at top right of the screen");
				extent.extentLogger("Skip button", "Skip button is displayed at top right of the screen");
			} else {
				logger.error("Skip button is not displayed at top right of the screen");
				extent.extentLoggerFail("Skip button", "Skip button is not displayed at top right of the screen");
			}

			verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Goole icon");
			verifyElementPresent(AMDLoginScreen.objfbBtn, "Facebook icon");
			verifyElementPresent(AMDLoginScreen.objtwitterBtn, "Twitter icon");

			type(AMDLoginScreen.objEmailIdField, inValidPhnNo, "Email Id or Mobile Number field");
			hideKeyboard();
			verifyElementPresent(AMDLoginScreen.objErrorTxt, "Invalid Mobile number error message");

//			int ele=findElements(AMDLoginScreen.objProceedBtn).size();
			if (verifyElementPresent(AMDLoginScreen.objContinueWithTxt, "Continue with social login")) {
				logger.info("Proceed button is not displayed when user enters invalid mobile number");
				extentLogger("Proceed button",
						"Proceed button is not displayed when user enters invalid mobile number");
			}
			clearField(AMDLoginScreen.objEmailIdField, "Email Id");

			type(AMDLoginScreen.objEmailIdField, validPhnNo, "Email Id or Mobile Number field");
			hideKeyboard();
			verifyElementPresent(AMDLoginScreen.objProceedBtn, "Proceed button");

			boolean proceedbtn = getDriver().findElement(AMDLoginScreen.objProceedBtn).isEnabled();

			if (proceedbtn == true) {
				logger.info("Proceed button is highlighted when user enter valid Mobile number");
				extentLogger("ProceedButton", "Proceed button is highlighted when user enter valid Mobile number");
			}
			click(AMDLoginScreen.objProceedBtn, "Proceed button");
			if (verifyElementExist(AMDLoginScreen.objLoginScreenTitle, "Login screen title")) {
				logger.info("Proceed button is functional");
				extentLogger("Proceed button functionality", "Proceed button is functional");
			} else if (verifyElementExist(AMDLoginScreen.objRegistrationScreenTitle, "Registration screen title")) {
				logger.info("Proceed button is functional");
				extentLogger("Proceed button functionality", "Proceed button is functional");
			} else {
				logger.info("Proceed button is not functional");
				extentLoggerFail("Proceed button functionality", "Proceed button is not functional");
			}
		}
	}

	public void loginScreen(String validPassword) throws Exception {
		extent.HeaderChildNode("Login screen");
		verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");

		WebElement element = findElement(AMDLoginScreen.objLoginScreenTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Login screen title is displayed at center of the screen");
			extent.extentLogger("Screen Title", "Login screen title is displayed at center of the screen");
		} else {
			logger.error("Login screen title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title", "Login screen title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		click(AMDLoginScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objLoginOrRegisterPageTitle, "Login/Register screen title");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");

		String cursorAvailability = getAttributValue("focused", AMDLoginScreen.objPasswordField);
		if (cursorAvailability.equalsIgnoreCase("true")) {
			logger.info("Cursor is displayed on the Password field by default");
			extentLogger("Cursor", "Cursor is displayed on the Password field by default");
		} else {
			logger.info("Cursor is not displayed on the Password field by default");
			extentLoggerFail("Cursor", "Cursor is not displayed on the Password field by default");
		}

		String showpassword = getAttributValue("checked", AMDLoginScreen.objShowPwdBtn);
		if (showpassword.equalsIgnoreCase("false")) {
			logger.info("Password field text is hidden by default");
			extentLogger("Password hidden", "Password field text is hidden by default");
		} else {
			logger.info("Password field text is not hidden by default");
			extentLoggerFail("Password hidden", "Password field text is not hidden by default");
		}

		click(AMDLoginScreen.objShowPwdBtn, "Show password icon");

		findElement(
				By.xpath("//*[@resource-id='com.graymatrix.did:id/text_input_password_toggle' and @checked='true']"));
		logger.info("User can hide or unhide password using eye icon");
		extentLogger("Password field", "User can hide or unhide password using eye icon");

		click(AMDLoginScreen.objShowPwdBtn, "Show password icon");

		if (!(findElement(AMDLoginScreen.objLoginBtn).isEnabled())) {

			logger.info("Login CTA is displayed and is dehighlighted by default");
			extentLogger("Login button", "Login CTA is displayed and is dehighlighted by default");
		} else {
			logger.info("Login CTA is displayed and is not dehighlighted by default");
			extentLoggerFail("Login button", "Login CTA is displayed and is not dehighlighted by default");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objForgetPwdBtn, "Forgot password link");
		verifyElementPresent(AMDLoginScreen.objForgotPasswordScreenTitle, "Forfot Password screen");
		click(AMDLoginScreen.objBackBtn, "Back button");

		type(AMDLoginScreen.objPasswordField, validPassword, "Password field");
//		hideKeyboard();
		String password = getText(AMDLoginScreen.objPasswordField);
		System.out.println(password.length());
		if (password.length() >= 6) {
			logger.info("Password field accepts minimum of six characters");
			extentLogger("Password field", "Password field accepts minimum of six characters");

			if (getDriver().findElement(AMDLoginScreen.objLoginBtn).isEnabled()) {
				logger.info("Login button is highlighted when user enters a minimum of 6 characters in password field");
				extentLogger("Login button",
						"the Login button is highlighted when user enters a minimum of 6 characters in password field");
			} else {
				logger.error(
						"Login button is not highlighted when user enters a minimum of 6 characters in password field");
				extentLoggerFail("Login button",
						"the Login button is not highlighted when user enters a minimum of 6 characters in password field");
			}
		} else {
			logger.info("Password field is not accepting minimum of six characters");
			extentLogger("Password field", "Password field is not accepting minimum of six characters");
		}
		hideKeyboard();
		click(AMDGenericObjects.objText("OR"), "OR text");
	}

	public void OtpScreen(String otp1, String otp2, String otp3, String otp4) throws Exception {
		extent.HeaderChildNode("Otp screen Validation");
		verifyElementPresentAndClick(AMDLoginScreen.objLoginWithOTPLink, "Login with OTP Link");

		WebElement element = findElement(AMDLoginScreen.objOtpScreenTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Verify mobile screen title is displayed at center of the screen");
			extent.extentLogger("Screen Title", "Verify mobile screen title is displayed at center of the screen");
		} else {
			logger.error("Verify mobile screen title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title",
					"Verify mobile screen title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDLoginScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		String cursorAvailability = getAttributValue("focused", AMDLoginScreen.objOtpEditBox1);
		if (cursorAvailability.equalsIgnoreCase("true")) {
			logger.info("Cursor is displayed on the first otp field by default");
			extentLogger("Cursor", "Cursor is displayed on the first otp field by default");
		} else {
			logger.info("Cursor is not displayed on the first otp field by default");
			extentLoggerFail("Cursor", "Cursor is not displayed on the first otp field by default");
		}
		click(AMDRegistrationScreen.objOTPField1, "First otp field");

//		if (findElements(AMDRegistrationScreen.objNumericKeypad).size() <= 20) {
//			logger.info("Numeric keypad is displayed in OTP screen");
//			extent.extentLogger("Numeric Keypad", "Numeric Keybpad is displayed in OTP screen");
//		}else {
//			logger.error("Numeric keypad is displayed in OTP screen");
//			extent.extentLoggerFail("Numeric Keypad", "Numeric keypad is not displayed in OTP screen");
//		}

		hideKeyboard();

		if (!(getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled())) {
			logger.info("Verify CTA is dehighlighted by default");
			extentLogger("Verify button", "Verify CTA is dehighlighted by default");
		} else {
			logger.info("Verify CTA is not dehighlighted by default");
			extentLoggerFail("Verify button", "Verify CTA is not dehighlighted by default");
		}

		if (getDriver().findElement(AMDLoginScreen.objResendOtpLink).isDisplayed()) {
			logger.info("Didn't get OTP text is displayed with Resend CTA");
			extentLogger("Resend button", "Didn't get OTP text is displayed with Resend CTA");
		} else {
			logger.info("Didn't get OTP text is not displayed with Resend CTA");
			extentLoggerFail("Resend button", "Didn't get OTP text is not displayed with Resend CTA");
		}

		if (getDriver().findElement(AMDLoginScreen.objOtp).isDisplayed()) {
			if (verifyElementExist(AMDLoginScreen.objCountDownTimer, "Count down Timer")) {
				logger.info("OTP is sent to the registered mobile number");
				extent.extentLogger("Otp", "OTP is sent to the registered mobile number");
			}

		}

		type(AMDLoginScreen.objOtpEditBox1, otp1, "first otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled() == false) {
			logger.info("Verify CTA is not highlighted");
			extentLogger("Verify button", "Verify CTA is not highlighted");
		}

		type(AMDLoginScreen.objOtpEditBox2, otp2, "second otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled() == false) {
			logger.info("Verify CTA is not highlighted");
			extentLogger("Verify button", "Verify CTA is not highlighted");
		}

		type(AMDLoginScreen.objOtpEditBox3, otp3, "third otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled() == false) {
			logger.info("Verify CTA is not highlighted");
			extentLogger("Verify button", "Verify CTA is not highlighted");
		}

		type(AMDLoginScreen.objOtpEditBox4, otp4, "fourth otp field");
		hideKeyboard();
		if (getDriver().findElement(AMDLoginScreen.objVerifyBtn).isEnabled()) {
			logger.info("Verify CTA is highlighted");
			extentLogger("Verify button", "Verify CTA is highlighted");
		} else {
			logger.info("Verify CTA is not highlighted");
			extentLoggerFail("Verify button", "Verify CTA is not highlighted");
		}

		click(AMDLoginScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objLoginScreenTitle, "Login screen title");
		Back(1);
	}

	public void TopSearches(String usertype) throws Exception {
		extent.HeaderChildNode("Top Searches module");
		waitTime(5000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search icon");
		if (usertype.equalsIgnoreCase("NonSubscribedUser") || usertype.equalsIgnoreCase("SubscribedUser")) {

			int noOfTrays = findElements(AMDSearchScreen.objNoOftraysInSearchpage).size();
			System.out.println(noOfTrays);
			boolean TopSearchFound = false;
			for (int i = 1; i <= noOfTrays; i++) {
				String traytitle = getDriver()
						.findElement(
								By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_primary_text'])[" + i + "]"))
						.getText();

				if (traytitle.equalsIgnoreCase("Top Searches")) {
					TopSearchFound = true;
					verifyElementExist(AMDSearchScreen.objTopSearches, "Top searches tray");

					verifyElementPresent(AMDSearchScreen.objContentCardTitleOfTopSearchesTray,
							"Content card title of Top searches tray");

					getText(AMDSearchScreen.objContentCardTitleOfTopSearchesTray);

//					click(AMDSearchScreen.objContentCardTitleOfTopSearchesTray, "Content card of Top searches tray");
//					waitForElementDisplayed(AMDSearchScreen.objConsumptionScreenTitle, 10);
//					
//					verifyElementPresent(AMDSearchScreen.objConsumptionScreenTitle, "Title in Consumption screen");
//					
//
//						String consumptionScreenTitle = getText(AMDSearchScreen.objConsumptionScreenTitle);
//						if(contentCardTitleofTopSearches.equalsIgnoreCase(consumptionScreenTitle))
//						{
//						    logger.info("user navigated to respective consumption/Landing screen post tapping on any Top searches carousel");	
//						    extent.extentLogger("Title", "user navigated to respective consumption/Landing screen post tapping on any Top searches carousel");
//						}
//						else
//						{
//							logger.info("user is not navigated to respective consumption/Landing screen post tapping on any Top searches carousel");	
//						    extent.extentLoggerFail("Title", "user is not navigated to respective consumption/Landing screen post tapping on any Top searches carousel");
//						}

					break;
				}
			}

			if (TopSearchFound == false) {
				logger.info("Top searches is not displayed");
				extentLoggerFail("Top searches tray", "Top searches is not displayed");
			}
			Back(1);
		} else {
			Back(1);
			selectContentLang_MoreMenu("Hindi");
		}
	}

	public void TrendingSearches() throws Exception {
		extent.HeaderChildNode("Trending Searches module");

		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search icon");

		int noOfTrays = findElements(AMDSearchScreen.objNoOftraysInSearchpage).size();
		System.out.println(noOfTrays);
		boolean TrendingSearchFound = false;
		for (int i = 1; i <= noOfTrays; i++) {
			String traytitle = getDriver()
					.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_primary_text'])[" + i + "]"))
					.getText();

			if (traytitle.equalsIgnoreCase("Trending Searches")) {
				TrendingSearchFound = true;
//				    verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
//				    type(AMDSearchScreen.objSearchBar, "Milana", "Search bar");
//					
//					verifyElementPresentAndClick(AMDSearchScreen.objSearchResultFirstContent, "content");
//					verifyElementExist(AMDSearchScreen.objConsumptionScreenTitle, "Title in Consumption screen");
//					Back(2);
//					waitTime(3000);
//					verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search icon");
//					
//					verifyElementPresent(AMDSearchScreen.objTrendingSearches, "Trending Searches tray");

				verifyElementPresent(AMDSearchScreen.objContentCardTitleOfTrendingSearchesTray,
						"Content card title of Trending searches tray");

				getText(AMDSearchScreen.objContentCardTitleOfTrendingSearchesTray);

//					click(AMDSearchScreen.objContentCardTitleOfTrendingSearchesTray, "Content card of Trending searches tray");
//					waitForElementDisplayed(AMDSearchScreen.objConsumptionScreenTitle, 10);
//					
//					verifyElementPresent(AMDSearchScreen.objConsumptionScreenTitle, "Title in Consumption screen");
//					
//						String consumptionScreenTitle = getText(AMDSearchScreen.objConsumptionScreenTitle);
//						if(contentCardTitleofTrendingSearches.equalsIgnoreCase(consumptionScreenTitle))
//						{
//						    logger.info("user navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");	
//						    extent.extentLogger("Title", "user navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");
//						}
//						else
//						{
//							logger.info("user is not navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");	
//						    extent.extentLoggerFail("Title", "user is not navigated to respective consumption/Landing screen post tapping on any Trending searches carousel");
//						}

				break;
			}
		}

		if (TrendingSearchFound == false) {
			logger.info("Trending searches is not displayed");
			extentLoggerFail("Trending searches tray", "Trending searches is not displayed");
		}
		Back(1);
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: VINAY
	 * ---------------------------------
	 * 
	 * List of Functions Created - IntroScreen(String userType) - -
	 * =============================================================================
	 * ========
	 */
	public void IntroScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validating Intro screen module");
		// Verify user is navigated to intro screen
		if (userType.equals("Guest")) {
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("ZEE5")) {
				String ZEE5 = getDriver().findElement(AMDOnboardingScreen.objScreenTitle).getText();
				extent.extentLogger("Verify user is navigated to Login/Registration screen",
						"User is navigated to " + ZEE5 + " Screen");
				logger.info("User is navigated to " + ZEE5 + " Screen after clicking on Browse for free");
			} else {
				extent.extentLoggerFail("Verify user is navigated to Login/Registration screen",
						"Intro screeen in not displayed");
				logger.info("Intro screeen in not displayed");
			}
			// Verify back button is displayed
			verifyElementPresent(AMDOnboardingScreen.objBackBtn, "Back button");
			// Verify user is navigated to Content language screeen post tapping back button
			click(AMDOnboardingScreen.objBackBtn, "Back button");
			String contentLang = getDriver().findElement(AMDOnboardingScreen.objScreenTitle).getText();
			if (contentLang.equals("Content Language")) {
				extent.extentLogger("Verify user is navigated to Content language screen",
						"User is navigated to " + contentLang + " scree");
				logger.info("User is navigated to " + contentLang + " screen");
			} else {
				extent.extentLogger("Verify user is navigated to Content language screen",
						"Failed to navigate into content language screen");
				logger.info("Failed to navigate into content language screen");
			}

			// Covered few TC's and Updated Code by Kushal

			click(AMDOnboardingScreen.objBackBtn, "Back button");
			verifyElementPresent(AMDGenericObjects.objCheckTitle("Display Language"), "Display language screen");
			click(AMDOnboardingScreen.objSelectDisplayLang("Kannada"), "Kannada language");
			click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");

			String strLang = findElement(AMDOnboardingScreen.objScreenTitle).getText();
			if (strLang != "Content Language") {

				extent.extentLogger("Content language screen", "Content language is displayed in Kannada language");
				logger.info("Title of the screen : " + strLang + " is displayed in selected language");
			} else {
				extent.extentLoggerFail("Content language screen",
						"Failed to display the content language in selected language");
				logger.error("Failed to display the content language in selected language");
			}

			click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue Button");

			if (findElement(AMDOnboardingScreen.objBrowseForFreeBtn).getText() != "Browse for Free") {
				String strBrwsforFree = findElement(AMDOnboardingScreen.objBrowseForFreeBtn).getText();
				extent.extentLogger("Intro screen",
						"Browse for free button is displayed in selected launguage: " + strBrwsforFree);
				logger.info("Browse for free button is displayed in selected launguage : " + strBrwsforFree);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Browse for free button failed to displayed in selected launguage");
				logger.error("Browse for free button failed to displayed in selected launguage");
			}
			String strSubsNow = findElement(AMDOnboardingScreen.objSubscribeNowBtn).getText();
			if (strSubsNow != "Subscribe Now") {
				extent.extentLogger("Intro screen",
						"Subscribe Now button is displayed in selected launguage: " + strSubsNow);
				logger.info("Subscribe Now button is displayed in selected launguage : " + strSubsNow);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Subscribe Now button failed to displayed in selected launguage: " + strSubsNow);
				logger.error("Subscribe Now button failed to displayed in selected launguage: " + strSubsNow);
			}
			String strHavePrepaidCode = findElement(AMDOnboardingScreen.objHavePrepaidBtn).getText();
			if (strHavePrepaidCode != "Have a prepaid code") {
				extent.extentLogger("Intro screen",
						"Have a Prepaid code CTA is displayed in selected launguage: " + strHavePrepaidCode);
				logger.info("Have a Prepaid code CTA is displayed in selected launguage: " + strHavePrepaidCode);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Have a Prepaid code CTA failed to displayed in selected launguage: " + strHavePrepaidCode);
				logger.error(
						"Have a Prepaid code CTA failed to displayed in selected launguage: " + strHavePrepaidCode);
			}
			String strLoginCTA = findElement(AMDOnboardingScreen.objLoginLnk).getText();
			if (strHavePrepaidCode != "Login") {
				extent.extentLogger("Intro screen", "Login CTA is displayed in selected launguage: " + strLoginCTA);
				logger.info("Login CTA is displayed in selected launguage: " + strLoginCTA);
			} else {
				extent.extentLoggerFail("Intro screen",
						"Login CTA failed to displayed in selected launguage: " + strLoginCTA);
				logger.error("Login CTA failed to displayed in selected launguage: " + strLoginCTA);
			}
			Back(2);
			if (findElement(AMDOnboardingScreen.objSelectedDisplayLang).getText() != "English") {
				click(AMDOnboardingScreen.objSelectDisplayLang("English"), "English language");
				click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");
			}
			// ***********************************************************

			click(AMDOnboardingScreen.objContent_ContinueBtn, "[Content Language] Continue button");
			// Verify Login button is displayed
			verifyElementExist(AMDOnboardingScreen.objLoginLnk, "Login button");

			// Verify user is navigated to Login Registration screen
			click(AMDOnboardingScreen.objLoginLnk, "Login button");
			hideKeyboard();
			// Click operation is used for the device which do not identify the keyboard
			click(AMDLoginScreen.objContinueWithTxt, "Or Continue with");
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("Login/Register")) {
				extent.extentLogger("Verify Navigation on clicking Login button",
						"User is navigated to" + findElement(AMDOnboardingScreen.objScreenTitle).getText() + " screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objScreenTitle).getText()
						+ " screen");
			} else {
				extent.extentLogger("Verify Navigation on clicking Login button",
						"Failed to navigate into Login/Register screen post tapping Login button");
				logger.info("Failed to navigate into Login/Register screen post tapping Login button");
			}
			click(AMDOnboardingScreen.objBackBtn, "Back button");
			// Verify that content feature carousel is displayed
			verifyElementExist(AMDOnboardingScreen.objFeatureCarousel, "Feature carousel rail");
			// Verify that Preminum member benifits section is displayed
			verifyElementExist(AMDOnboardingScreen.objBenefitsTextSection, "Benifits of premium member section");
			// Verify that pagination dots are displayed
			verifyElementExist(AMDOnboardingScreen.objDotsIndicator, "Pagination dots");
			// Verify Browse for free is displayed
			verifyElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");

			// Verify user is navigated to Login/Registration page post tapping Browse for
			// free
			click(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for free");
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("Login/Register")) {
				extent.extentLogger("Verify Navigation on clicking Browse for Free button",
						"User is navigated to" + findElement(AMDOnboardingScreen.objScreenTitle).getText() + " screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objScreenTitle).getText()
						+ " screen");
			} else {
				extent.extentLogger("Verify Navigation on clicking Browse for Free button",
						"Failed to navigate into Login/Register screen post tapping Browse for Free button");
				logger.info("Failed to navigate into Login/Register screen post tapping Browse for Free button");
			}
			click(AMDOnboardingScreen.objBackBtn, "Back button");

			// Verify that Subscribe Now button is available
			verifyElementPresentAndClick(AMDOnboardingScreen.objSubscribeNowBtn, "Subcribe Now button");

			// Verify user is navigated to Subscribe now screen

			waitTime(2000);
			if (findElement(AMDOnboardingScreen.objScreenTitle).getText().equals("Subscribe")) {
				extent.extentLogger("Verify Navigation on clicking Subscribe Now button",
						"User is navigated to" + findElement(AMDOnboardingScreen.objScreenTitle).getText() + " screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objScreenTitle).getText()
						+ " screen");
			} else {
				extent.extentLogger("Verify Navigation on clicking Subcribe button",
						"Failed to navigate into Subscribe screen post tapping Subscribe Now button");
				logger.info("Failed to navigate into Subscribe screen post tapping Subcribe Now button");
			}
			click(AMDOnboardingScreen.objBackBtn, "Back button");

			// Verify that Have a prepaid code? is displayed
			verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a preapaid code? button");

			// Verify Prepaid code screen is displayed after tapping on Prepaid code button
			if (findElement(AMDOnboardingScreen.objPrepaidPopupLabel).isDisplayed()) {
				extent.extentLogger("Verify navigation post tapping Prepaid code button", "User is navigated to "
						+ findElement(AMDOnboardingScreen.objPrepaidPopupLabel).getText() + " Screen");
				logger.info("User is navigated to " + findElement(AMDOnboardingScreen.objPrepaidPopupLabel).getText()
						+ " Screen");
			} else {
				extent.extentLogger("Verify navigation post tapping Prepaid code button",
						"Failed to navigate into Prepaid screen post tapping Have a prepaid code button");
				logger.info("Failed to navigate into Prepaid screen post tapping Have a prepaid code button");
			}
			Back(1);
		} else {
			extent.extentLogger("Intro Screen", "Intro Screen is not displayed for Susbcribed/Non-Subscribed user");
			logger.info("Intro Screen is not displayed for Susbcribed/Non-Subscribed user");
			System.out.println("Intro Screen is not displayed for Susbcribed/Non-Subscribed user");
		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: HITESH
	 * -------------------------------
	 * 
	 * Functions Created list - navigateToRegisterScreen(String loginThrough) -
	 * validateRegister(String firstName, String secoundName) -
	 * checkRegisterButton() - deepLinks(String tabName) - relaunch() -
	 * registerForFreeScreen()
	 * =============================================================================
	 * ========
	 */

	public void navigateToRegisterScreen(String loginThrough) throws Exception {
		if (loginThrough.equals("BrowseForFree")) {
			HeaderChildNode("Validate Browse for Free Button functionality");
			verifyElementExist(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free button");
			logger.info("Browse for Free button is displayed in language : "
					+ getText(AMDOnboardingScreen.objBrowseForFreeBtn));
			extent.extentLogger("Browse for Free button", "Browse for Free button is displayed in language : "
					+ getText(AMDOnboardingScreen.objBrowseForFreeBtn));

			verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");

			if (verifyElementExist(AMDLoginScreen.objLoginLnk, "Login link")) {
				logger.info("Login/Register Screen is displayed");
				extent.extentLogger("Login/Register Screen", "Login/Register Screen is displayed");
			} else {
				logger.info("Login/Register Screen is not displayed");
				extent.extentLogger("Login/Register Screen", "Login/Register Screen is not displayed");
			}

		} else if (loginThrough.equals("login")) {
			HeaderChildNode("Validate Login/Register Screen");
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		}

	}

	public void validateRegister(String firstName, String secoundName) throws Exception {
		waitTime(8000);
		String pEmailID = generateRandomString(5) + "@gmail.com";
		type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");
		verifyElementPresent(AMDRegistrationScreen.objScreenTitle, "Register for Free screen");
		HeaderChildNode("Validate fields of Register screen");
		WebElement element = findElement(AMDRegistrationScreen.objScreenTitle);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Register for Free Title is displayed at center of the screen");
			extent.extentLogger("Screen Title", "Register for Free Title is displayed at center of the screen");
		} else {
			logger.error("Register for Free Title is not displayed at center of the screen");
			extent.extentLoggerFail("Screen Title", "Register for Free Title is not displayed at center of the screen");
		}

		WebElement elementBackBtn = findElement(AMDRegistrationScreen.objBackBtn);
		int BackBtnleftX = elementBackBtn.getLocation().getX();
		int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
		int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

		if (BackBtnmiddleX <= 200) {
			logger.info("Back button is displayed at top left of the screen");
			extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
		} else {
			logger.error("Back button is not displayed at top left of the screen");
			extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
		}

		if (getAttributValue("focused", AMDRegistrationScreen.objFirstNameTxtField).equals("true")) {
			logger.info("First Name field is focused");
			extent.extentLogger("First Name field", "First Name field is focused");
		} else {
			logger.error("First Name field is not focused");
			extent.extentLoggerFail("First Name field", "First Name field is not focused");
		}

		checkRegisterButton();
		verifyElementExist(AMDRegistrationScreen.objEmailIDTextField, "Email ID or Mobile number");

		if (!findElement(AMDRegistrationScreen.objEmailIDTextField).isEnabled()) {
			logger.info("“Email ID or Mobile number” field is grayed out");
			extent.extentLogger("“Email ID or Mobile number”", "“Email ID or Mobile number” field is grayed out");
		} else {
			logger.error("“Email ID or Mobile number” field is not grayed out");
			extent.extentLoggerFail("“Email ID or Mobile number”",
					"“Email ID or Mobile number” field is not grayed out");
		}

		String EmailBeforeType = getText(AMDRegistrationScreen.objEmailIDTextField);
		type(AMDRegistrationScreen.objEmailIDTextField, pEmailID, "Email field");
		String EmailAfterType = getText(AMDRegistrationScreen.objEmailIDTextField);
		if (EmailBeforeType.equalsIgnoreCase(EmailAfterType)) {
			logger.info("User is not allowed to edit the Email field");
			extent.extentLogger("Email field", "User is not allowed to edit the Email field");
		} else {
			logger.error("User is allowed to edit the Email field");
			extent.extentLoggerFail("Email field", "User is allowed to edit the Email field");
		}

		verifyElementExist(AMDRegistrationScreen.objFirstNameTxtField, "First Name field");
		verifyElementExist(AMDRegistrationScreen.objLastNameTxtField, "Last Name field");

		type(AMDRegistrationScreen.objFirstNameTxtField, firstName, "First Name field");
		checkRegisterButton();
		type(AMDRegistrationScreen.objLastNameTxtField, secoundName, "Last Name field");
		checkRegisterButton();
		verifyElementExist(AMDRegistrationScreen.objDOBCalenderBtn, "DOB Icon");
		click(AMDRegistrationScreen.objDOBTxtField, "Date of Birth");
		verifyElementExist(AMDRegistrationScreen.objCalenderPopUp, "Calender PopUp");

		// get a calendar instance, which defaults to "now"
		Calendar calendar = Calendar.getInstance();
		// add one day to the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		// now get "tomorrow"
		Date tomorrow = calendar.getTime();

		verifyElementPresent(AMDOnboardingScreen.objNextDate(tomorrow.toString().split(" ")[2].replace("0", "")),
				"Future date " + tomorrow.toString().split(" ")[2]);
//	
//    if(verifyElementExist(AMDOnboardingScreen.objNextBtnPopUpInCalender, "Next Button in calender")) 
//    {
//    	logger.info("Next Button in calender is not available to select Future date ");
//		extent.extentLogger("Next button", "Next Button in calender is not available to select Future date ");
//    }else {
//    	logger.info("Next Button in calender is available to select Future date ");
//		extent.extentLogger("Next button", "Next Button in calender is available to select Future date ");
//    }

		String DMD = getText(AMDRegistrationScreen.objMonthDayDate);
		System.out.println(DMD + " " + (tomorrow.toString().substring(0, 11)));
		if (!DMD.contains(tomorrow.toString().substring(0, 11))) {
			logger.info("Future date is not allowed for select");
			extent.extentLogger("Future button", "Future date is not allowed for select");
		} else {
			logger.info("Future date is allowed for select");
			extent.extentLogger("Future button", "Future date is allowed for select");
		}

		LocalDate currentDate = LocalDate.now();
		int dom = currentDate.getDayOfMonth();
		Month month = currentDate.getMonth();
		int currentyear = currentDate.getYear();

		int year = Integer.valueOf(getText(AMDRegistrationScreen.objHeaderYearTxt));

		String DayMonth = DMD.split(",")[1];
		if (DayMonth.toLowerCase().contains(month.toString().substring(0, 2).toLowerCase())) {
			logger.info("Current Month is displayed");
			extent.extentLogger("Current Month", "Current Month is displayed");
		} else {
			logger.error("Current Month is not displayed");
			extent.extentLoggerFail("Current month", "Current Month is not displayed");
		}

		if (DayMonth.contains(String.valueOf(dom))) {
			logger.info("Current date is displayed");
			extent.extentLogger("Current date", "Current date is displayed");
		} else {
			logger.error("Current date is not displayed");
			extent.extentLoggerFail("Current date", "Current date is not displayed");
		}
		if ((currentyear - 18) == year) {
			logger.info("Current year is displayed");
			extent.extentLogger("Current year", "Current year is displayed");
		} else {
			logger.error("Current year is not displayed");
			extent.extentLoggerFail("Current year", "Current year is not displayed");
		}

		verifyElementExist(AMDRegistrationScreen.objCancelBtn, "Cancel button in calender popUp");
		verifyElementPresentAndClick(AMDRegistrationScreen.objOkBtn, "Ok button in calender popUp");

		verifyElementPresentAndClick(AMDRegistrationScreen.objGederTxtField, "Gender field");
		verifyElementExist(AMDRegistrationScreen.objSelectGenderText, "Select your Gender screen");

		WebElement GenderTotleElement = findElement(AMDRegistrationScreen.objSelectGenderText);
		int GenderTotleleftX = GenderTotleElement.getLocation().getX();
		int GenderTotlerightX = GenderTotleleftX + GenderTotleElement.getSize().getWidth();
		int GenderTotlemiddleX = (GenderTotlerightX + GenderTotleleftX) / 2;
		Dimension windowSize = getDriver().manage().window().getSize();
		if (GenderTotlemiddleX == Integer.valueOf((windowSize.width) / 2)) {
			logger.info("Select your gender screen title is displayed at the center of the screen");
			extent.extentLogger("Screen Title",
					"Select your gender screen title is displayed at the center of the screen");
		} else {
			logger.error("Select your gender screen title is not displayed at the center of the screen");
			extent.extentLoggerFail("Screen Title",
					"Select your gender screen title is not displayed at the center of the screen");
		}

		verifyElementExist(AMDRegistrationScreen.objSelecteGender, "Tick mark on the selected option");
		String selectedGender = getText(AMDRegistrationScreen.objSelectedGenderName);
		logger.info("Selected Gender : " + selectedGender);
		extent.extentLogger("Select Gender", "Selected Gender : " + selectedGender);

		WebElement CloseIconElement = findElement(AMDRegistrationScreen.objXMark);
		int CloseIconupperY = CloseIconElement.getLocation().getY();
		int CloseIconlowerY = CloseIconupperY + CloseIconElement.getSize().getHeight();
		int CloseIconmiddleY = (CloseIconupperY + CloseIconlowerY) / 2;

		Dimension windowsSize = getDriver().manage().window().getSize();

		if (CloseIconmiddleY >= ((windowsSize.getHeight() / 2) + 100)) {
			logger.info("X icon appears on the bottom of the screen");
			extent.extentLogger("Select Gender", "X icon appears on the bottom of the screen");
		} else {
			logger.error("X icon is not appears on the bottom of the screen");
			extent.extentLoggerFail("Select Gender", "X icon is not appears on the bottom of the screen");
		}
		click(AMDRegistrationScreen.objMale, "Gender : Male");
		checkRegisterButton();

		verifyElementExist(AMDRegistrationScreen.objPasswordTxtField, "Set Password");
		click(AMDRegistrationScreen.objPasswordTxtField, "Set Password");
		type(AMDRegistrationScreen.objPasswordTxtField, generateRandomString(4), "entered 5 charecter in set password");
		hideKeyboard();
		verifyElementExist(AMDRegistrationScreen.objPasswordErrorMsg,
				"“Password must be a minimum of 6 characters” error message");
		type(AMDRegistrationScreen.objPasswordTxtField, generateRandomString(6), "entered 6 charecter in set password");

		for (int i = 0; i < 2; i++) {
			String eyeIcon = getAttributValue("checked", AMDRegistrationScreen.objEyeIcon);
			if (eyeIcon.equals("false")) {
				logger.info("Password is hide");
				extent.extentLogger("Password hide eye icon", "Password is hide");
			} else {
				logger.info("Password is visible");
				extent.extentLogger("Password hide eye icon", "Password is visible");
			}
			click(AMDRegistrationScreen.objEyeIcon, "Eye Icon");
		}
		click(AMDRegistrationScreen.objFirstNameTxtField, "First name"); // Clicking on First Name field to get device
																			// keyboard
		hideKeyboard();
		checkRegisterButton();
		verifyElementPresent(AMDRegistrationScreen.objTermsOfUseAndPrivacyPolicy, "Terms and condition text ");
//	if(relaunch) {
//		verifyElementPresentAndClick(AMDRegistrationScreen.objRegisterBtn, "Register button");
//		verifyElementPresent(AMDHomePage.objHomeTab, "Home page");
//	}
	}

	public void checkRegisterButton() throws Exception {
		if (getAttributValue("clickable", AMDRegistrationScreen.objRegisterBtn).equals("false")) {
			logger.info("Register CTA is displayed and is dehighlated by default");
			extent.extentLogger("Register button", "Register CTA is displayed and is dehighlated by default");
		} else {
			logger.info("Register CTA is Activated");
			extent.extentLogger("Register button", "Register CTA is Activated");
		}
	}

	public void deepLinks(String tabName) {
		try {
			getDriver().close();
			waitTime(5000);
			String cmd3 = ("/usr/local/bin/adb  shell am start -W -a android.intent.action.VIEW -d  \"https://www.zee5.com/" + tabName
					+ "\" com.graymatrix.did");
			Process process = Runtime.getRuntime().exec(cmd3);
			new BufferedReader(new InputStreamReader(process.getInputStream()));
			waitTime(12000);
			HeaderChildNode("DeepLink");
			if (tabName.contains("Home")) {
				verifyElementExist(AMDHomePage.objHomeTab, "Home screen");
				if (!verifyElementExist(AMDLoginScreen.objPageTitle, "Display language")) {
					if (!verifyElementExist(AMDOnboardingScreen.objContentLanguagePageTitle, "Content language")) {
						logger.info("Display Language and Content Language not displayed for deeplink");
						extent.extentLogger("Language popup",
								"Display Language and Content Language not displayed for deeplink");
					}
				}

			} else {
				if (verifyElementExist(AMDLoginScreen.objLoginLnk, "Login/Register Screen")) {
					logger.info("Login/Register Screen is displayed for deeplink");
					extent.extentLogger("Language popup", "Login/Register Screen is displayed for deeplink");
				}
			}
			waitTime(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: MANASA
	 * ---------------------------------
	 * 
	 * List of Functions Created - ZeeApplicasterLogin(String LoginMethod) -
	 * offlineScreenValidation() - socialLoginValidation(String loginThrough) -
	 * =============================================================================
	 * =========
	 */

	public void ZeeApplicasterLogin(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");

		String UserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		}
	}

	public void offlineScreenValidation() throws Exception {

		extent.HeaderChildNode("Offline Screen Validation");
		System.out.println("\nOffline Screen Validation");

		Runtime.getRuntime().exec("/usr/local/bin/adb  shell svc wifi disable");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}

		if (pUserType.contains("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objMoviesTab, "Movies tab");
		}

		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");
		verifyElementExist(AMDOfflineScreen.objTryAgain, "Try Again");
		verifyElementExist(AMDOfflineScreen.objGoToDownloads, "Go to Downloads");
		verifyElementExist(AMDHomePage.objHome, "Home");
		verifyElementExist(AMDHomePage.objUpcoming, "Upcoming");
		verifyElementExist(AMDHomePage.objDownload, "Download");
		verifyElementExist(AMDHomePage.objMoreMenu, "More Menu");

		click(AMDHomePage.objUpcoming, "Upcoming");
		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");
		click(AMDHomePage.objMoreMenu, "More Menu");
		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");

		// verifyElementPresentAndClick(AMDOfflineScreen.objGoToDownloads, "Go to
		// Downloads");

		verifyElementPresentAndClick(AMDHomePage.objDownload, "Download");
		if (verifyElementExist(AMDOfflineScreen.objDownloadScreen, "Download Section")) {
			logger.info("Navigated to Download Section");
			extent.extentLogger("Download Section", "Navigated to Download Section");
		} else {
			logger.info("Not navigated to Download Section");
			extent.extentLogger("Download Section", "Not navigated to Download Section");
		}
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home Tab");
		verifyElementPresentAndClick(AMDOfflineScreen.objTryAgain, "Try Again");
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi enable");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objUpcoming, "Upcoming tab");

		waitForElementDisplayed(AMDUpcomingPage.objContentCardTitle, 10);
		if (verifyElementExist(AMDUpcomingPage.objContentCardTitle, "Upcoming Page Content Card")) {
			logger.info("Appropriate page is loaded");
			extent.extentLogger("Page", "Appropriate page is loaded");
		} else {
			logger.info("Appropriate page is not loaded");
			extent.extentLogger("Page", "Appropriate page is not loaded");
		}
	}

	public void Wifi_TurnOFFnON() throws Exception {
		Runtime.getRuntime()
				.exec("/usr/local/bin/adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
		waitTime(2000);
//		Runtime.getRuntime().exec("adb shell input keyevent 23");
		verifyElementPresentAndClick(AMDGenericObjects.objWifiToggle, "Wifi-Toggle button");
		waitTime(2000);
		Runtime.getRuntime().exec("/usr/local/bin/adb shell monkey -p com.graymatrix.did -c android.intent.category.LAUNCHER 1");
	}

	public void socialLoginValidation(String loginThrough) throws Exception {
		extent.HeaderChildNode("Social Login Validation");
		verifyElementPresentAndClick(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button");
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn,
				"Continue button in content language screen");
		navigateToRegisterScreen(loginThrough);
		// verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Gmail icon");

		if (verifyElementExist(AMDLoginScreen.objGmailSignIn, "Gmail Sign In")) {
			verifyElementPresentAndClick(AMDLoginScreen.objGmailEmailField, "Email Field");
			type(AMDLoginScreen.objGmailEmailField, "zeetest55@gmail.com", "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");
			verifyElementPresentAndClick(AMDLoginScreen.objGmailPasswordField, "Password Field");
			type(AMDLoginScreen.objGmailPasswordField, "zeetest123", "Password Field");
			verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");

			if (verifyElementExist(AMDLoginScreen.objGmailAddPhoneNumber, "Add Phone Number")) {
				verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip Button");
			}
			if (verifyElementExist(AMDLoginScreen.objAgreeBtn, "Agree Button")) {
				click(AMDLoginScreen.objAgreeBtn, "Agree Button");
			}

			if (verifyElementExist(AMDLoginScreen.objAcceptBtn, "Accept Button")) {
				click(AMDLoginScreen.objAcceptBtn, "Accept Button");
			}
		}

		if (verifyElementExist(AMDLoginScreen.objGmailAccount, "Gmail Account")) {
			click(AMDLoginScreen.objGmailAccount, "Gmail Account");
			waitTime(5000);
		}
		if (verifyElementExist(AMDOnboardingScreen.objTellUsMore, "More info Screen")) {
			if (verifyElementExist(AMDLoginScreen.objEmailIdField, "Email Id field")) {
				type(AMDLoginScreen.objEmailIdField, "zeetest@gmail.com", "Email Id field");
			}

			verifyElementPresentAndClick(AMDLoginScreen.objDOB, "Date of Birth");
			verifyElementPresentAndClick(AMDLoginScreen.objDate, "Date");
			verifyElementPresentAndClick(AMDLoginScreen.objDateOK, "OK button");
			verifyElementPresentAndClick(AMDLoginScreen.objGender, "Gender Field");
			verifyElementPresentAndClick(AMDLoginScreen.objGenderMale, "Male");
			verifyElementExist(AMDLoginScreen.objSubmitButton, "Submit Button");
			Back(1);
		}

		if (verifyElementExist(AMDHomePage.objHome, "Home Tab")) {
			logger.info("User logged in successfully");
			extent.extentLogger("Login", "User logged in successfully");

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			Swipe("UP", 2);

			verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
			verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
			waitTime(5000);
			Swipe("Down", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");

		}

		verifyElementPresentAndClick(AMDLoginScreen.objtwitterBtn, "Twitter icon");
		waitTime(5000);

		if (verifyElementExist(AMDLoginScreen.objTwitterAuthorize, "Authorize app")) {
			click(AMDLoginScreen.objTwitterAuthorize, "Authorize app");
		}

		if (verifyElementExist(AMDLoginScreen.objTwitterEmail, "Email Id field")) {
			click(AMDLoginScreen.objTwitterEmail, "Email Id field");
			type(AMDLoginScreen.objTwitterEmail, "zee5latest@gmail.com", "Email Id field");
			verifyElementPresentAndClick(AMDLoginScreen.objTwitterPassword, "Password Field");
			type(AMDLoginScreen.objTwitterPassword, "User@123", "Password field");
			verifyElementPresentAndClick(AMDLoginScreen.objTwitterLoginBtn, "Login Button");

			waitTime(5000);
			if (verifyElementExist(AMDHomePage.objHome, "Home Tab")) {
				logger.info("User logged in successfully");
				extent.extentLogger("Login", "User logged in successfully");

				verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
				Swipe("UP", 2);

				verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
				verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
				waitTime(5000);
				Swipe("Down", 2);
				verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");

			}
		}

		if (verifyElementExist(AMDOnboardingScreen.objTellUsMore, "More info Screen")) {
			if (verifyElementExist(AMDLoginScreen.objEmailIdField, "Email Id field")) {
				type(AMDLoginScreen.objEmailIdField, "zeetest@gmail.com", "Email Id field");
			}

			verifyElementPresentAndClick(AMDLoginScreen.objDOB, "Date of Birth");
			verifyElementPresentAndClick(AMDLoginScreen.objDate, "Date");
			verifyElementPresentAndClick(AMDLoginScreen.objDateOK, "OK button");
			verifyElementPresentAndClick(AMDLoginScreen.objGender, "Gender Field");
			verifyElementPresentAndClick(AMDLoginScreen.objGenderMale, "Male");
			verifyElementExist(AMDLoginScreen.objSubmitButton, "Submit Button");
		}

		if (verifyElementExist(AMDHomePage.objHome, "Home Tab")) {
			logger.info("User logged in successfully");
			extent.extentLogger("Login", "User logged in successfully");

			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
			Swipe("UP", 2);

			verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
			verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
			waitTime(5000);
			Swipe("Down", 2);
			verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");

		}

		verifyElementPresentAndClick(AMDLoginScreen.objfbBtn, "Facebook icon");
		waitTime(5000);

		if (verifyElementExist(AMDLoginScreen.objFBLogIntoAnotherAccount, "Log Into Another Account")) {
			click(AMDLoginScreen.objFBLogIntoAnotherAccount, "Log Into Another Account");
			if (verifyElementExist(AMDLoginScreen.objFBEmail, "Email Id field")) {
				click(AMDLoginScreen.objFBEmail, "Email Id field");
				type(AMDLoginScreen.objFBEmail, "igszeefive@gmail.com", "Email Id field");
				verifyElementPresentAndClick(AMDLoginScreen.objFBPassword, "Password Field");
				type(AMDLoginScreen.objFBPassword, "zeefive@igs", "Password field");
				verifyElementPresentAndClick(AMDLoginScreen.objFBLoginBtn, "Login Button");
			}
		} else if (verifyElementExist(AMDLoginScreen.objFBEmail, "Email Id field")) {
			click(AMDLoginScreen.objFBEmail, "Email Id field");
			type(AMDLoginScreen.objFBEmail, "igszeefive@gmail.com", "Email Id field");
			verifyElementPresentAndClick(AMDLoginScreen.objFBPassword, "Password Field");
			type(AMDLoginScreen.objFBPassword, "zeefive@igs", "Password field");
			verifyElementPresentAndClick(AMDLoginScreen.objFBLoginBtn, "Login Button");
		}

		if (verifyElementExist(AMDHomePage.objHome, "Home Tab")) {
			logger.info("User logged in successfully");
			extent.extentLogger("Login", "User logged in successfully");
		}

		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		Swipe("UP", 1);

		verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout");
		verifyElementPresentAndClick(AMDMoreMenu.objLogoutBtn, "Logout Button");
		waitTime(5000);
		Swipe("Down", 2);
		verifyElementPresentAndClick(AMDMoreMenu.objProfile, "Login/Register");

		if (verifyElementExist(AMDOnboardingScreen.objTellUsMore, "More info Screen")) {
			if (verifyElementExist(AMDLoginScreen.objEmailIdField, "Email Id field")) {
				type(AMDLoginScreen.objEmailIdField, "zeetest@gmail.com", "Email Id field");
			}
			verifyElementPresentAndClick(AMDLoginScreen.objDOB, "Date of Birth");
			verifyElementPresentAndClick(AMDLoginScreen.objDate, "Date");
			verifyElementPresentAndClick(AMDLoginScreen.objDateOK, "OK button");
			verifyElementPresentAndClick(AMDLoginScreen.objGender, "Gender Field");
			verifyElementPresentAndClick(AMDLoginScreen.objGenderMale, "Male");
			verifyElementExist(AMDLoginScreen.objSubmitButton, "Submit Button");
			Back(1);
		}
	}

	@SuppressWarnings("deprecation")
	public void searchResultsAllTabs(String searchModuleKeyword) throws Exception {

		extent.HeaderChildNode("Validating that user is able to find the searched content in all the tabs");

		// type(AMDSearchScreen.objSearchBoxBar, searchModuleKeyword + "\n", "Search
		// bar");
		getDriver().getKeyboard().sendKeys(searchModuleKeyword);

		hideKeyboard();

		waitForElementDisplayed(AMDSearchScreen.objAllTab, 20);

		boolean allTabHighlighted = findElement(AMDSearchScreen.objAllTab).isSelected();

		if (allTabHighlighted == true) {
			logger.info("All Tab is highlighted by default");
			extent.extentLogger("All Tab", "All Tab is highlighted by default");
		} else {
			logger.error("All Tab is not highlighted by default");
			extent.extentLogger("All Tab", "All Tab is not highlighted by default");
		}

		waitTime(5000);

		swipeByElements(findElement(AMDSearchScreen.objMusicTabIndx), findElement(AMDSearchScreen.objShowsTabIndx));

		waitTime(3000);
		swipeByElements(findElement(AMDSearchScreen.objMusicTabIndx), findElement(AMDSearchScreen.objVideosTab));

		logger.info("User is able to scroll through the tabs");
		extent.extentLogger("Tabs", "User is able to scroll through the tabs");

		waitTime(2000);
		List<WebElement> tabs = getDriver().findElements(AMDSearchScreen.objTabs);
		System.out.println(tabs.size());
		boolean News = false;
		for (int i = 1; i <= tabs.size(); i++) {
			String tabName = null;

			System.out.println("i : " + i);

			if (i == 5) {

				if (!News) {
					i = 4;
				}

				WebElement eleTab = findElement(By.xpath(
						"((//*[@resource-id='com.graymatrix.did:id/tabLayout'])[2]/child::*/child::*/child::*/child::*)["
								+ i + "]"));
				tabName = findElement(By.xpath(
						"((//*[@resource-id='com.graymatrix.did:id/tabLayout'])[2]/child::*/child::*/child::*/child::*)["
								+ i + "]")).getText();

				System.out.println(tabName);
				eleTab.click();

			} else {
				WebElement eleTab = findElement(By.xpath(
						"((//*[@resource-id='com.graymatrix.did:id/tabLayout'])[2]/child::*/child::*/child::*/child::*)["
								+ i + "]"));
				tabName = findElement(By.xpath(
						"((//*[@resource-id='com.graymatrix.did:id/tabLayout'])[2]/child::*/child::*/child::*/child::*)["
								+ i + "]")).getText();
				System.out.println(tabName);
				eleTab.click();
			}
			waitTime(5000);
			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (verifyIsElementDisplayed(AMDMoreMenu.objSearchResult(searchModuleKeyword))) {
				String searchResults = findElement(AMDMoreMenu.objSearchResult(searchModuleKeyword)).getText();
				System.out.println("Search result : " + searchResults);

				if (searchResults.contains(searchModuleKeyword)) {
					logger.info("Related search results are displayed in " + tabName + " tab");
					extent.extentLogger("Related search results",
							"Related search results are displayed in " + tabName + " tab");
				} else {
					logger.info("Related search results are not displayed in  " + tabName + " tab");
					extent.extentLogger("Related search results",
							"Related search results are not displayed in  " + tabName + " tab");
				}

			} else {
				logger.info("Related search results are not displayed in  " + tabName + " tab");
				extent.extentLogger("Related search results",
						"Related search results are not displayed in  " + tabName + " tab");
			}

			if (News) {
				break;
			}
			if (tabName.equalsIgnoreCase("News")) {
				News = true;
			}
		}
		Back(1);
		if (verifyElementExist(AMDSearchScreen.objMicrophoneIcon, "Microphone Icon")) {
			logger.info(
					"Microphone icon is displayed when user navigates back to Search landing screen from Search Result screen");
			extent.extentLogger("Microphone icon",
					"Microphone icon is displayed when user navigates back to Search landing screen from Search Result screen");
		} else {
			logger.error(
					"Microphone icon is not displayed when user navigates back to Search landing screen from Search Result screen");
			extent.extentLoggerFail("Microphone icon",
					"Microphone icon is not displayed when user navigates back to Search landing screen from Search Result screen");
		}
	}

	@SuppressWarnings("deprecation")
	public void noSearchResults(String invalidSearchKeyword) throws Exception {
		extent.HeaderChildNode(
				"Validating the category tab is not displayed if the searched keyword don’t have results for the particular category");

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Icon");

		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		getDriver().getKeyboard().sendKeys(invalidSearchKeyword);
		hideKeyboard();
		// type(AMDSearchScreen.objSearchBoxBar, invalidSearchKeyword + "\n", "Search
		// bar");
		if (verifyElementExist(AMDSearchScreen.objNoSearchResults, "No Search Results message")) {
			String noResults = getText(AMDSearchScreen.objNoSearchResults);
			logger.info(noResults + " message is displayed");
			extent.extentLogger("Search Results message", noResults + " message is displayed");

			logger.info("Searched keyword does not have results for the particular category");
			extent.extentLogger("Search Results message",
					"Searched keyword does not have results for the particular category");
		} else {
			logger.info("Searched keyword has results for the particular category");
			extent.extentLogger("Search Results message", "Searched keyword has results for the particular category");

		}
		verifyElementPresentAndClick(AMDSearchScreen.objClearSearch, "Clear Search");
	}

	public void swipeByElements(WebElement webElement, WebElement webElement2) {

		touchAction = new TouchAction<>(getDriver());

		int startX = webElement.getLocation().getX() + (webElement.getSize().getWidth() / 2);
		int startY = webElement.getLocation().getY() + (webElement.getSize().getHeight() / 2);

		int endX = webElement2.getLocation().getX() + (webElement2.getSize().getWidth() / 2);
		int endY = webElement2.getLocation().getY() + (webElement2.getSize().getHeight() / 2);
		touchAction.press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(endX, endY))
				.release().perform();
	}

	@SuppressWarnings("deprecation")
	public void searchPageValidation(String partlySpeltSearchKeyword) throws Exception {
		extent.HeaderChildNode("Search Result Screen Validation");
		getDriver().getKeyboard().sendKeys(partlySpeltSearchKeyword);
//		type(AMDSearchScreen.objSearchBoxBar, partlySpeltSearchKeyword + "\n", "Search bar");
//		verifyElementExist(AMDSearchScreen.objSearchResultPage, "Search Result Screen");

//		if(verifyElementExist(AMDSearchScreen.objRecentSearch, "Recent Search Overlay")==false) {
//			extent.extentLogger("Recent Search Overlay","Recent Search Overlay is not available in search results screen");
//			logger.info("Recent Search Overlay is not available in search results screen");
//		}else {
//			extent.extentLoggerFail("Recent Search Overlay", "Recent Search Overlay is available in search results screen");
//			logger.error("Recent Search Overlay is available in search results screen");
//		}

		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 20);

//		String enteredValue = getAttributValue("value", AMDSearchScreen.objSearchBoxBar);
		System.out.println(partlySpeltSearchKeyword.length());
		waitTime(20000);
		if (partlySpeltSearchKeyword.length() >= 3) {

			System.out.println(getDriver().findElements(AMDSearchScreen.objRelatedSearchResult).size());

			if (getDriver().findElements(AMDSearchScreen.objRelatedSearchResult).size() > 0) {
				logger.info("Search result screen is displayed once user enters 3rd character in the search box.");
				extent.extentLogger("Search result screen",
						"Search result screen is displayed once user enters 3rd character in the search box.");

			} else {
				logger.info(
						"Search result screen is not displayed when user enters less than 3 characters in the search box.");
				extent.extentLogger("Search result screen",
						"Search result screen is not displayed when user enters less than 3 characters in the search box.");
			}
		}

		PartialSwipe("UP", 2);
		logger.info("User is able to scroll down the search results");
		extent.extentLogger("Search results", "User is able to scroll down the search results");
		verifyElementPresentAndClick(AMDSearchScreen.objClearSearch, "Clear Search");
		hideKeyboard();
	}

	public void voiceSearchDenyValidation() throws Exception {
		extent.HeaderChildNode("Voice Search Access Deny Validation");
		System.out.println("Voice Search Access Deny Validation");
		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Icon");
		verifyElementPresentAndClick(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
		verifyElementPresentAndClick(AMDSearchScreen.objProceedBtn, "Proceed Button");
		if (verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp, "Audio Permission Popup")) {
			verifyElementPresentAndClick(AMDSearchScreen.objDeny, "Deny Option");

			if (verifyElementExist(AMDSearchScreen.objMicrophoneIcon, "Microphone Icon")) {
				logger.info("Search landing screen is displayed after denying audio permission");
				extent.extentLogger("Search landing screen",
						"Search landing screen is displayed after denying audio permission");

			} else {
				logger.info("Search landing screen is not displayed after denying audio permission");
				extent.extentLogger("Search landing screen",
						"Search landing screen is not displayed after denying audio permission");
			}

//			verifyElementPresentAndClick(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
//			verifyElementExist(AMDSearchScreen.objVoiceSearchPermission,"Microphone access permission popup");
//			verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp,"Audio Permission Popup");

		}
	}

	public void voiceSearchValidation() throws Exception {
		extent.HeaderChildNode("Voice Search Validation");
		System.out.println("Voice Search Validation");
		// verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search Icon");

		WebElement elementMicrophoneIcon = findElement(AMDSearchScreen.objMicrophoneIcon);
		int iconRightX = elementMicrophoneIcon.getLocation().getX();
		System.out.println(iconRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizeee);

		if (iconRightX >= sizeee) {
			logger.info("Microphone icon is displayed on right side of the search box");
			extent.extentLogger("Microphone icon", "Microphone icon is displayed on right side of the search box");
		} else {
			logger.error("Microphone icon is not displayed on right side of the search box");
			extent.extentLoggerFail("Microphone icon",
					"Microphone icon is not displayed on right side of the search box");
		}
		click(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
		if (verifyElementExist(AMDSearchScreen.objVoiceSearchPermission, "Microphone access permission popup")) {
			verifyElementExist(AMDSearchScreen.objMicrophoneIconLogo, "Microphone icon");
			verifyElementExist(AMDSearchScreen.objTermsAndConditions, "Terms of Use and Privacy Policy Message");
			verifyElementExist(AMDSearchScreen.objProceedBtn, "Proceed Button");
			verifyElementExist(AMDSearchScreen.objBackBtn, "Back button");

			WebElement elementBackBtn = findElement(AMDSearchScreen.objBackBtn);
			int BackBtnleftX = elementBackBtn.getLocation().getX();
			int BAckBtnrightX = BackBtnleftX + elementBackBtn.getSize().getWidth();
			int BackBtnmiddleX = (BAckBtnrightX + BackBtnleftX) / 2;

			if (BackBtnmiddleX <= 200) {
				logger.info("Back button is displayed at top left of the screen");
				extent.extentLogger("Back button", "Back button is displayed at top left of the screen");
			} else {
				logger.error("Back button is not displayed at top left of the screen");
				extent.extentLoggerFail("Back button", "Back button is not displayed at top left of the screen");
			}

			click(AMDSearchScreen.objProceedBtn, "Proceed Button");
			if (verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp, "Audio Permission Popup")) {
				verifyElementPresentAndClick(AMDSearchScreen.objAllow, "Allow Option");
				verifyElementExist(AMDSearchScreen.objVoiceSearchScreen, "Voice Search Screen");
				verifyElementExist(AMDSearchScreen.objMicrophoneLogoInVoiceSearch, "Microphone icon");
				verifyElementExist(AMDSearchScreen.objSeeUrTextMsg, "See your text here message");
				verifyElementPresentAndClick(AMDSearchScreen.objCloseBtn, "Close Button");

//				verifyElementPresentAndClick(AMDSearchScreen.objMicrophoneIcon, "Microphone icon");
//				verifyElementExist(AMDSearchScreen.objVoiceSearchPermission,"Microphone access permission popup");
//				verifyElementExist(AMDSearchScreen.objAudioPermissionPopUp,"Audio Permission Popup");

			}

		}

	}

	/*
	 * =============================================================================
	 * ===== ------------------------------ Script Author: KUSHAL
	 * ---------------------------------
	 * 
	 * List of Functions Created - navigateToLoginScreen_DisplaylangScreen() -
	 * navigateToIntroScreen_DisplaylangScreen() - LoginWithEmailID(String pEmailId,
	 * String pPassword) - verifyUIPresentInLoginPage() -
	 * verifyLoginScreenUIFunctionality() - VerifySkipLoginRegistrationScreen() -
	 * VerifyLoginWithEmailId(String pUserName, String pPassword) -
	 * verifyHaveAPrepaidCodePopUp() -
	 * verifyInvalidPrepaidCodePopUpAfterLogin(String pEmailID, String pPassword) -
	 * verifyInvalidPrepaidCodePopUpAfterRegistration() -
	 * verifyCongratulationPopupAppearsforValidPrepaidCode(String pCode, String
	 * pUserName, String pPassword)
	 * =============================================================================
	 * =========
	 */

	public void navigateToLoginScreen_DisplaylangScreen() throws Exception {
		extent.HeaderChildNode("Navigation to Login Screen");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresentAndClick(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
	}

	public void navigateToIntroScreen_DisplaylangScreen() throws Exception {
		extent.HeaderChildNode("Navigation to Intro Screen");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresent(AMDOnboardingScreen.objBrowseForFreeBtn, "Browse for Free");
	}

	public void LoginWithEmailID(String pEmailId, String pPassword) throws Exception {
//		extent.HeaderChildNode("Log into ZEE5 with registered Email account");
		verifyElementPresent(AMDLoginScreen.objEmailIdLabel, "Login/Register screen");
		type(AMDLoginScreen.objEmailIdField, pEmailId, "Email-Id/Phone");
		click(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, pPassword, "Password");
		hideKeyboard();
		click(AMDLoginScreen.objLoginBtn, "Login Button");
	}

	public void verifyUIPresentInLoginPage() throws Exception {
		verifyElementPresent(AMDLoginScreen.objBackBtn, "Back button");
		verifyElementPresent(AMDLoginScreen.objLoginLnk, "Skip button");
		verifyElementPresent(AMDLoginScreen.objGoogleBtn, "Google login button");
		verifyElementPresent(AMDLoginScreen.objfbBtn, "facebook login button");
		verifyElementPresent(AMDLoginScreen.objtwitterBtn, "twitter login button");
	}

	public void verifyLoginScreenUIFunctionality() throws Exception {
		extent.HeaderChildNode("Veirfy UI elements present in Login Screen");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Login"), "Login Screen");

		String pwdEncript = getAttributValue("checked", AMDLoginScreen.objShowPwdBtn);
		if (pwdEncript.contains("false")) {
			extent.extentLogger("Default Show Password", "Password is Encripted");
			logger.info("Password is Encripted");
		} else {
			extent.extentLoggerFail("Default Show Password", "Password is not Encripted");
			logger.error("Password is not Encripted");
		}

		click(AMDLoginScreen.objShowPwdBtn, "Show Password");
		String checkPwdVisible = getAttributValue("checked", AMDLoginScreen.objShowPwdBtn);
		if (checkPwdVisible.contains("true")) {
			extent.extentLogger("Click Show Password", "Password is Visible");
			logger.info("Password is Visible");
		} else {
			extent.extentLoggerFail("Click Show Password", "Password is not Visible");
			logger.error("Password is not Visible");
		}

		verifyElementPresentAndClick(AMDLoginScreen.objForgetPwdBtn, "Forgot Password CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Forgot Password"), "Forgot Password Screen");
		click(AMDLoginScreen.objBackBtn, "Back button");
	}

	public void VerifySkipLoginRegistrationScreen() throws Exception {
		extent.HeaderChildNode("Browse for Free - Skip Login/Register screen to Home page");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Login/Register"), "Login/Register title");
		verifyUIPresentInLoginPage();
		verifyElementPresent(AMDLoginScreen.objEmailIdLabel, "Email ID or Mobile Number");
		type(AMDLoginScreen.objEmailIdField, "testmessage.com", "EmailId");
		verifyElementPresent(AMDLoginScreen.objErrInvalidID, getText(AMDLoginScreen.objErrInvalidID));
		clearField(AMDLoginScreen.objEmailIdField, "EmailId");
		type(AMDLoginScreen.objEmailIdField, "987654321", "Phone Number");
		verifyElementPresent(AMDLoginScreen.objErrInvalidID, getText(AMDLoginScreen.objErrInvalidID));
		clearField(AMDLoginScreen.objEmailIdField, "EmailId");
		type(AMDLoginScreen.objEmailIdField, "sample@zee5.com", "EmailId");
		hideKeyboard();
		verifyElementPresent(AMDLoginScreen.objProceedBtn, "Proceed button");
		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip button");
		verifyElementPresent(AMDHomePage.objHome, "Home Page");
	}

	public void VerifyLoginWithEmailId(String pUserName, String pPassword) throws Exception {

		extent.HeaderChildNode("Login With Email-ID");
		System.out.println("Login With Email-ID");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Login/Register"), "Login/Register");
		type(AMDLoginScreen.objEmailIdField, pUserName, "Email-ID");
		verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed button");
		if (getText(AMDLoginScreen.objEmailIdField).equalsIgnoreCase(pUserName)) {
			extent.extentLogger("Email-Id Retained", "Email Id is retained in Login screen EmailId Field");
			logger.info("Email Id is retained in Login screen EmailId Field");
		} else {
			extent.extentLoggerFail("Email-Id Retained", "Email Id failed to retained in Login screen EmailId Field");
			logger.error("Email Id failed to retained in Login screen EmailId Field");
		}
		String getPropertyValue = getAttributValue("enabled", AMDLoginScreen.objEmailIdField);
		if (getPropertyValue.equalsIgnoreCase("false")) {
			extent.extentLogger("EmailId field is disabled/grayed out", " User cannot edit emailid field");
			logger.info("EmailId field is disabled/grayed out and user cannot edit emailid field");
		} else {
			extent.extentLoggerFail("EmailId field is not grayed out", " User is able to edit emailid field");
			logger.error("EmailId field is not grayed out and user is able to edit emailid field");
		}
		verifyLoginScreenUIFunctionality();

		verifyElementPresent(AMDLoginScreen.objPasswordField, "Password field");
		type(AMDLoginScreen.objPasswordField, "xcvzc", "Password");
		verifyElementPresent(AMDLoginScreen.objErrorTxtMsg, "Password must be a minimum of 6 characters");
		clearField(AMDLoginScreen.objPasswordField, "Password");
		type(AMDLoginScreen.objPasswordField, pPassword, "Password");
		hideKeyboard();
		verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
		Wait(2000);
		if (verifyElementPresent(AMDHomePage.objHomeTab, "Home page")) {
			extent.extentLogger("Login with EmailId", pUserName + " : is logged in successfully");
			logger.info(pUserName + " is logged in  successfully");
		} else {
			extent.extentLoggerFail("Login with EmailId", pUserName + " failed to login");
			logger.error(pUserName + " failed to login");
		}
	}

	public void verifyHaveAPrepaidCodePopUp() throws Exception {
		extent.HeaderChildNode("Verify Have a prepaid code PopUp UI is displayed");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
		verifyElementPresent(AMDOnboardingScreen.objPrepaidPopupLabel, "Prepaid Code PopUp Header");
		verifyElementPresent(AMDOnboardingScreen.objWhatIsPrepaidCodeBtn, "What is a Prepaid Code?");
		verifyElementPresent(AMDOnboardingScreen.objApplyBtn, "Apply button");

		String getPropertyValue = getAttributValue("enabled", AMDOnboardingScreen.objApplyBtn);
		if (getPropertyValue.equalsIgnoreCase("false")) {
			extent.extentLogger("Apply button", "Apply Button is by default dehighlighted");
			logger.info("Apply Button is by default dehighlighted");
		} else {
			extent.extentLoggerFail("Apply buttont", "Apply Button fails to dehighlight by default");
			logger.error("Apply Button failed to dehighlight by default");
		}

		verifyElementPresent(AMDOnboardingScreen.objPrepaidCodeField, "Prepaid Code Field");
		if (getText(AMDOnboardingScreen.objPrepaidCodeField).equalsIgnoreCase("Prepaid Code")) {
			extent.extentLogger("Prepaid Code field", "Prepaid Code text is displayed in the edit field");
			logger.info("Prepaid Code text is displayed in the edit field");
		} else {
			extent.extentLoggerFail("Prepaid Code field", "Prepaid Code text is not displayed in the edit field");
			logger.error("Prepaid Code text is not displayed in the edit field");
		}

		verifyElementPresentAndClick(AMDOnboardingScreen.objWhatIsPrepaidCodeBtn, "What is Prepaid Code? CTA");
		verifyElementPresent(AMDGenericObjects.objCheckTitle("About Prepaid Code"), "About Prepaid Code Screen");
		verifyElementPresent(AMDOnboardingScreen.objDescriptionTxt, "About Prepaid code description");
		verifyElementPresentAndClick(AMDOnboardingScreen.objBackBtn, "Back Button");
		verifyElementPresent(AMDOnboardingScreen.objPrepaidPopupLabel, "Prepaid Popup");
		verifyElementPresentAndClick(AMDOnboardingScreen.objPopUpDivider, "Popup Horizontal line");
		verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");

		click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaid code field");
		Wait(1000);
		type(AMDOnboardingScreen.objPrepaidCodeField, "ZNA2020", "Prepaid Code field");
		hideKeyboard();
		String getPropertyValue2 = getAttributValue("enabled", AMDOnboardingScreen.objApplyBtn);
		if (getPropertyValue2.equalsIgnoreCase("true")) {
			extent.extentLogger("Apply button", "Apply Button is highlighted");
			logger.info("Apply Button is highlighted");
		} else {
			extent.extentLoggerFail("Apply buttont", "Apply Button fails to highlight after entering code");
			logger.error("Apply Button fails to highlight after entering code");
		}

		click(AMDOnboardingScreen.objApplyBtn, "Apply button");
		verifyElementPresent(AMDOnboardingScreen.objFaceIcon, "Oops! label");
		String textMessage = getText(AMDOnboardingScreen.objSuccessDesc);
		if (textMessage.contains("You are not logged in")) {
			extent.extentLogger("Invalid Code Message", "You are not logged in to apply prepaid code... message");
			logger.info("You are not logged in to apply prepaid code... message is displayed");
		} else {
			extent.extentLoggerFail("Invalid Code Message", "You are not logged in to apply prepaid code... message");
			logger.error("You are not logged in to apply prepaid code... message is not displayed");
		}

		verifyElementPresent(AMDOnboardingScreen.objRegisterBtn, "Register button");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginBtn, "Login button");
		verifyElementPresent(AMDLoginScreen.objEmailIdField, "Login/Register screen");
	}

	public void verifyInvalidPrepaidCodePopUpAfterLogin(String pEmailID, String pPassword) throws Exception {

		extent.HeaderChildNode("Verify Invalid prepaid code PopUp UI is displayed after successful login");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
		click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaidcode Field");
		type(AMDOnboardingScreen.objPrepaidCodeField, "CODE2020", "Prepaid Code field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDOnboardingScreen.objApplyBtn, "Apply button");
		verifyElementPresentAndClick(AMDOnboardingScreen.objLoginBtn, "Login button");
		LoginWithEmailID(pEmailID, pPassword);
		Wait(1000);
		verifyElementPresent(AMDOnboardingScreen.objSuccessTitle("Invalid Prepaid Code"),
				"Invalid Prepaid Code Pop up");
		String getPopUpDesc = getText(AMDOnboardingScreen.objSuccessDesc);
		if (getPopUpDesc.contains("Please Provide Valid Coupon code")) {
			extent.extentLogger("Invalid Pop up", getPopUpDesc + "  message is displayed");
			logger.info(getPopUpDesc + "is displayed in PopUp screen");
		} else {
			extent.extentLoggerFail("Invalid Pop up", "Please provide valid coupon code message is not displayed");
			logger.error("Please provide valid coupon code is not displayed");
		}

		verifyElementPresentAndClick(AMDOnboardingScreen.objDoneBtn, "Done button");
		if (verifyElementPresent(AMDHomePage.objHome, "Home Screen")) {
			extent.extentLogger("Login with registered user", pEmailID + " :  is logged in Successfully");
			logger.info(pEmailID + " is logged in  successfully");
		} else {
			extent.extentLoggerFail("Login with registered user", pEmailID + " failed to login");
			logger.error(pEmailID + " failed to login");
		}

	}

	public void verifyInvalidPrepaidCodePopUpAfterRegistration() throws Exception {

		extent.HeaderChildNode("Verify Invalid prepaid code PopUp UI is displayed after successful login");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		verifyElementPresentAndClick(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
		click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaidcode Field");
		type(AMDOnboardingScreen.objPrepaidCodeField, "CODE2020", "Prepaid Code field");
		hideKeyboard();
		verifyElementPresentAndClick(AMDOnboardingScreen.objApplyBtn, "Apply button");
		verifyElementPresentAndClick(AMDOnboardingScreen.objRegisterBtn, "Register button");
		Wait(1000);
		type(AMDRegistrationScreen.objEmailIDTextField, generateRandomString(6) + "@yopmail.com", "Email field");
		verifyElementPresentAndClick(AMDRegistrationScreen.objProceedBtn, "Proceed button");

		// ---Register new user
		registerForFreeScreen("Prepaid");

		verifyElementPresent(AMDOnboardingScreen.objSuccessTitle("Invalid Prepaid Code"),
				"Invalid Prepaid Code Pop up");
		String getPopUpDesc = getText(AMDOnboardingScreen.objSuccessDesc);
		if (getPopUpDesc.contains("Please Provide Valid Coupon code")) {
			extent.extentLogger("Invalid Pop up", getPopUpDesc + " is displayed in PopUp screen");
			logger.info(getPopUpDesc + "is displayed in PopUp screen");
		} else {
			extent.extentLoggerFail("Invalid Pop up", "Please provide valid coupon code is not displayed");
			logger.error("Please provide valid coupon code is not displayed");
		}

		verifyElementPresentAndClick(AMDOnboardingScreen.objDoneBtn, "Done button");
		if (verifyElementPresent(AMDHomePage.objHome, "Home Screen")) {
			extent.extentLogger("Register New user", "User is registered to ZEE5 successfully");
			logger.info("User is registered to ZEE5 successfully");
		} else {
			extent.extentLoggerFail("Register New user", "User failed to register");
			logger.error("User failed to register");
		}

	}

	// --------- Need a VALID Prepaid Code to verify Congratulations Popup screen
	// -----------

	public void verifyCongratulationPopupAppearsforValidPrepaidCode(String pCode, String pUserName, String pPassword)
			throws Exception {
		extent.HeaderChildNode("Verify Congratulation PopUp is displayed for valid Prepaid Code");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
		click(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button (Content-LanguageScreen)");
		click(AMDOnboardingScreen.objHavePrepaidBtn, "Have a prepaid code? CTA");
		click(AMDOnboardingScreen.objPrepaidCodeField, "Prepaidcode Field");
		type(AMDOnboardingScreen.objPrepaidCodeField, pCode, "Valid Prepaid Code");
		hideKeyboard();
		verifyElementPresentAndClick(AMDOnboardingScreen.objApplyBtn, "Apply button");
		click(AMDOnboardingScreen.objLoginBtn, "Login button");
		LoginWithEmailID(pUserName, pPassword);

		verifyElementPresent(AMDOnboardingScreen.objSuccessTitle("Congratulations!"), "Congratulations! Pop up screen");
		String getPopUpDesc = getText(AMDOnboardingScreen.objSuccessDesc);
		if (getPopUpDesc.contains("Prepaid code applied successfully")) {
			extent.extentLogger("Congrats Pop up", getPopUpDesc + " - message is displayed");
			logger.info(getPopUpDesc + " is displayed in PopUp screen");
		} else {
			extent.extentLoggerFail("Congrats Pop up", "Message - Prepaid code applied successfully is not displayed");
			logger.error("Message - Prepaid code applied successfully is not displayed");
		}

		verifyElementPresentAndClick(AMDOnboardingScreen.objWatchNowBtn, "Watch Now button");

		if (verifyElementPresent(AMDHomePage.objHomeTab, "Home page")) {
			extent.extentLogger("Login with EmailId", pUserName + " : is logged in successfully");
			logger.info(pUserName + " is logged in  successfully");
		} else {
			extent.extentLoggerFail("Login with EmailId", pUserName + " failed to login");
			logger.error(pUserName + " failed to login");
		}
	}

	/**
	 * Author : Sushma
	 * 
	 * @throws Exception
	 */
	public void homeLandingScreen(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Navigating to Home screen and verifing the Subscribe icon");
		System.out.println("Home Landing screen and verifing the subscribe icon");

		// Selecting HOME tab from Top Navigation
		SelectTopNavigationTab(tabName);

		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase(tabName)) {
			logger.info(userType + " is able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLogger(tabName + " Tab", userType + " is able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		} else {
			logger.info(userType + " is not able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerFail(tabName + " Tab", userType + " is not able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		}
		waitTime(10000);
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed");
				extent.extentLogger("Subscribe icon", "Subscribe icon is dislayed");
			} else {
				logger.info("subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is NOT dislayed");
			}
		} else {
			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("subscribe icon is NOT dislayed");
				extent.extentLogger("Subscribe icon", "Subscribe icon is NOT dislayed");
			} else {
				logger.info("subscribe icon is dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is dislayed");
			}
		}

		String courselContentTitle = carouselValidationWithApi(userType, "homepage");
		carouselValidation(userType, tabName, courselContentTitle);
		verifyElementPresentAndClick(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");

		waitTime(5000);
		if (checkElementExist(AMDHomePage.objSubscribePopup)) {
//			Back(1);
			click(AMDGenericObjects.objPopUpDivider, "Pop Up divider");
		}
		if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
			if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
				logger.info(
						"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				extentLogger("Trailer",
						"Content playback is not initiated for the user post tapping on premium content which is having trailer");

			} else {
				logger.info(
						"Content playback is initiated for the user post tapping on premium content which is having trailer");
				extentLogger("Trailer",
						"Content playback is initiated for the user post tapping on premium content which is having trailer");
			}
			Back(1);
		} else {
			if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
				logger.info(
						"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				extentLogger("Trailer",
						"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
			} else {
				logger.info(
						"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				extentLoggerFail("Trailer",
						"Content playback is initiated for the user post tapping on premium content which is not having trailer");
			}
			Back(1);
		}
		extent.HeaderChildNode("Verifing the availability of trays in the screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Continue Watching"), AMDHomePage.objCarouselConetentCard,
				"Continue watching tray", "MastheadCarousel", userType, tabName);
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Trending on"), AMDHomePage.objCarouselConetentCard,
				"Trending on Zee5 tray", "MastheadCarousel", userType, tabName);
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("Trending Trailers"), AMDHomePage.objCarouselConetentCard,
				"Trending Trailers and Teasers tray", "Mastheadcarousel", userType, tabName);
		if (userType.equalsIgnoreCase("Guest")) {
			selectContentLang_MoreMenu2("English,Malayalam");
			waitTime(5000);
			closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			findingTrayInscreen(25, AMDHomePage.objTrayTitle("Live Channels"), AMDHomePage.objCarouselConetentCard,
					"Live Channels tray", "Mastheadcarousel", userType, tabName);
			findingTrayInscreen(25, AMDHomePage.objTrayTitle("Malayalam Movie Bonanza"),
					AMDHomePage.objCarouselConetentCard, "Malayalam Movie Bonanza tray", "Mastheadcarousel", userType,
					tabName);
			deselectContentLang_MoreMenuAndSelectDefaultLanguage("English,Malayalam");
		}
	}

	public void selectContentLang_MoreMenu2(String planguage) throws Exception {

		click(AMDHomePage.HomeIcon, "Home button");
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Content Language"), "Content language screen");

		// ***** UnSelecting default content languages *****
		if (pUserType.contains("Guest")) {
			click(AMDOnboardingScreen.objSelectContentLang("English"), "English");
			PartialSwipe("UP", 2);
			waitTime(1000);
			click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada");
			Swipe("DOWN", 1);
		}

		// ***** Selecting required language *****
		if (planguage.contains(",")) {
			Swipe("DOWN", 1);
			String[] pLanguages = planguage.split(",");
			int n = pLanguages.length;
			for (int i = 0; i < n; i++) {

				for (int j = 0; j < 5; j++) {
					if (getDriver().findElements(AMDOnboardingScreen.objSelectContentLang(pLanguages[i])).size() == 0) {
						PartialSwipe("UP", 1);
					} else {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(pLanguages[i]),
								pLanguages[i]);
						break;
					}
				}
			}
		} else {
			outerLoop: for (int i = 1; i <= 4; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (planguage.equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(planguage), planguage);
						break outerLoop;
					}
				}
				PartialSwipe("UP", 1);
			}
		}

		waitTime(1000);
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button");
		waitTime(1000);
//	click(AMDGenericObjects.objBackBtn, "Back button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	public static String carouselValidationWithApi(String userType, String pagenameforApi) {
		Response respPage = ResponseInstance.getResponseForApplicasterPages(userType, pagenameforApi);
//	System.out.println(respPage);

		List<String> bucketsSize = respPage.jsonPath().getList("buckets");
		logger.info("bucketsSize: " + bucketsSize.size());
		String carouselContentTitle = null;
		main: for (int i = 0; i < bucketsSize.size(); i++) {
			String description = respPage.jsonPath().getString("buckets[" + i + "].short_description");

			if ((description.equalsIgnoreCase("Home Page Slider")) | (description.equalsIgnoreCase("Movies Banner"))) {
				List<String> carouselItems = respPage.jsonPath().getList("buckets[" + i + "].items");
				logger.info("carouselItems: " + carouselItems.size());

				for (int j = 0; j < 7; j++) {
					carouselContentTitle = respPage.jsonPath().getString("buckets[" + i + "].items[" + j + "].title");
					logger.info(carouselContentTitle);

					String CarouselContentBusinessType = respPage.jsonPath()
							.getString("buckets[" + i + "].items[" + j + "].business_type");
					logger.info(CarouselContentBusinessType);

					if (CarouselContentBusinessType.equalsIgnoreCase("premium_downloadable")) {
						break main;
					}
				}
			}
		}
		return carouselContentTitle;
	}

	public void deselectContentLang_MoreMenuAndSelectDefaultLanguage(String planguage) throws Exception {

		click(AMDHomePage.HomeIcon, "Home button");
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Content Language"), "Content language screen");

		// ***** deSelecting selected language *****
		if (planguage.contains(",")) {
			Swipe("DOWN", 1);
			String[] pLanguages = planguage.split(",");
			int n = pLanguages.length;
			System.out.println(n);
			for (int i = 0; i < n; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (pLanguages[i].equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(pLanguages[i]),
								pLanguages[i]);
					}
				}
				PartialSwipe("UP", 3);
			}
		} else {
			outerLoop: for (int i = 1; i <= 4; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (planguage.equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(planguage), planguage);
						break outerLoop;
					}
				}
				PartialSwipe("UP", 1);
			}
		}

		// ***** Selecting default content languages *****
		if (pUserType.contains("Guest")) {
			Swipe("DOWN", 4);
			click(AMDOnboardingScreen.objSelectContentLang("English"), "English");
			PartialSwipe("UP", 2);
			waitTime(1000);
			click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada");
			Swipe("DOWN", 1);
		}
		waitTime(1000);
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button");
		waitTime(1000);
//	click(AMDGenericObjects.objBackBtn, "Back button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	public void carouselValidation(String UserType, String tabName, String contentTitle) throws Exception {
		extent.HeaderChildNode("Carousel validation");

		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);
		waitTime(10000);

		if (verifyElementDisplayed(AMDHomePage.objBannerAd)) {
			verifyElementPresent(AMDHomePage.objCarouselUnitwithmastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		} else {
			verifyElementPresent(AMDHomePage.objCarouselUnitwhenNomastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		}

		// Validating Carousel manual swipe
		String width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);

		String bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];

		waitTime(3000);
		String Carouseltitle1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle1);
		extentLogger("Carousel Title", Carouseltitle1);
		carouselCardsSwipe("LEFT", 1, width, height);

		String Carouseltitle2 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle2);
		extentLogger("Carousel Title", Carouseltitle2);
		carouselCardsSwipe("RIGHT", 1, width, height);

		String Carouseltitle3 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle3);
		extentLogger("Carousel Title", Carouseltitle3);

		if (!Carouseltitle1.equalsIgnoreCase(Carouseltitle2)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLogger("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else if (Carouseltitle3.equalsIgnoreCase(Carouseltitle1)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLogger("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else {
			logger.info(UserType + " is not able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerFail("Carousel swipe",
					UserType + " is NOT able to manually swipe banners from right to left or vice versa.");
		}

		// Validating Pagination dot, Play icon and GetPremium on Carousel
		int noofCarouselContents = findElements(AMDHomePage.objCarouselDots).size();
		for (int i = 0; i < noofCarouselContents; i++) {
			logger.info(getText(AMDHomePage.objCarouselTitle1));

			// To Verify PAGINATION DOT displayed
			if (checkElementExist(AMDHomePage.objCarouselDots, "Pagination dot")) {
				logger.info("Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLogger("Pagination dot",
						"Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.info(
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Pagination dot",
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			// To Verify PLAY ICON displayed
			if (checkElementExist(AMDHomePage.objPlayBtn, "Play icon")) {
				logger.info("Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLogger("Play icon",
						"Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.info("Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Play icon",
						"Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			carouselCardsSwipe("LEFT", 1, width, height);
		}

		if ((UserType.equalsIgnoreCase("Guest")) | (UserType.equalsIgnoreCase("NonSubscribedUser"))) {
			for (int i = 0; i < noofCarouselContents; i++) {

				if (tabName.equals("Music")) {
					extent.extentLogger("Verify Get Premium tag",
							"Get Premium tag is not configured for " + tabName + " tab");
					logger.info("Get Premium tag is not configured for " + tabName + " tab");
					break;

				} else {
					verifyElementExist(AMDHomePage.objGetPremium, "GetPremium tag");
					logger.info(getText(AMDHomePage.objCarouselTitle1));
					carouselCardsSwipe("LEFT", 1, width, height);
				}
				click(AMDHomePage.objGetPremium, "GetPremium tag");
				verifyElementPresent(AMDSubscibeScreen.objSubscribeHeader, "Subscription screen");
				Back(1);
			}
		} else {
			for (int i = 0; i < noofCarouselContents; i++) {

				System.out.println(getText(AMDHomePage.objCarouselTitle1));
				if (verifyElementIsNotDisplayed(AMDHomePage.objGetPremium)) {
					logger.info("GetPremium tag is NOT displayed");
					extentLogger("Get premium Tag", "GetPremium tag is NOT displayed");
				} else {
					logger.info("GetPremium tag is displayed");
					extentLoggerFail("Get premium Tag", "GetPremium tag is displayed");
				}
				carouselCardsSwipe("LEFT", 1, width, height);
			}

		}

		// navigation to consumption screen of selected content

		verifyElementPresentAndClick(AMDHomePage.objContentTitle(contentTitle), "Carousel content");

		waitTime(5000);
		if (checkElementExist(AMDHomePage.objSubscribePopup)) {
			Back(1);
			// click(AMDGenericObjects.objPopUpDivider, "Pop Up divider");
		}

		verifyElementPresent(AMDHomePage.objConsumptionScreenTitle, "Consumption screen");
		String consumptionScreenTitle = getText(AMDHomePage.objConsumptionScreenTitle);

		if (contentTitle.equalsIgnoreCase(consumptionScreenTitle)) {
			logger.info("Consumption Screen is displayed for the selected content");
			extent.extentLogger("Consumption screen", "Consumption Screen is displayed for the selected content");
		} else {
			logger.info("Consumption Screen is not displayed for the selected content");
			extent.extentLoggerFail("Consumption screen",
					"Consumption Screen is not displayed for the selected content");
		}
		Back(1);

		// Validating Carousel Auto scroll
		String title1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(title1);
		extentLogger("Carousel Title", title1);
		waitTime(4000);
		String title2 = getText(AMDHomePage.objCarouselTitle2);
		logger.info(title2);
		extentLogger("Carousel Title", title2);
		waitTime(4000);
		String title3 = getText(AMDHomePage.objCarouselTitle3);
		logger.info(title3);
		extentLogger("Carousel Title", title3);

		if (!(title1.equalsIgnoreCase(title2))) {
			if (!(title2.equalsIgnoreCase(title3))) {
				logger.info(
						"Banners available in feature carousel unit rotates from right to left at a fixed time interval");
				extentLogger("Carousel unit Autorotation",
						"Banners available in feature carousel unit rotate from right to left at a fixed time interval");
			} else {
				logger.info(
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
				extentLoggerFail("Carousel unit Autorotation",
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			}
		} else {
			logger.info(
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			extentLoggerFail("Carousel unit Autorotation",
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
		}
	}

	@SuppressWarnings("rawtypes")
	public void carouselCardsSwipe(String direction, int count, String width, String height) throws Exception {
		touchAction = new TouchAction(getDriver());

		try {

			int yCordinate;
			if (verifyElementIsNotDisplayed(AMDHomePage.objAdBannerAboveCarousel)) {
				yCordinate = (int) ((Integer.valueOf(height)) * 0.4);
			} else {
				yCordinate = (int) ((Integer.valueOf(height)) * 0.5);
			}

			if (direction.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {

					int startx = (Integer.valueOf(width));
					startx = (int) (startx * 0.8);
					int endx = (int) (startx * 0.1);

					int starty = (Integer.valueOf(height)) + yCordinate;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");

					System.out.println("\n<<< Swipe <<<");
					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
				}
			} else if (direction.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					int startx = (int) ((Integer.valueOf(width)) * 0.1);
					int endx = (int) ((Integer.valueOf(width)) * 0.8);
					int starty = (Integer.valueOf(height)) + yCordinate;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();

					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");

					System.out.println("\n>>> Swipe >>>");
					System.out.println("[X,Y]: [" + startx + "," + starty + "] ===> [" + endx + "," + starty + "]");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public void carouselSwipe(String direction, int count, String width, String height) throws Exception {
		touchAction = new TouchAction(getDriver());

		try {
			if (direction.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {

					int startx = (Integer.valueOf(width)) - 200;
					int endx = 100;
					int starty = (Integer.valueOf(height)) + 300;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();
					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
					extent.extentLogger("SwipeLeft",
							"Swiping screen in " + " " + direction + " direction" + " " + (i + 1) + " times");
				}
			} else if (direction.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					int startx = 100;
					int endx = (Integer.valueOf(width)) - 200;
					int starty = (Integer.valueOf(height)) + 300;
					touchAction.press(PointOption.point(startx, starty))
							.waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
							.moveTo(PointOption.point(endx, starty)).release().perform();

					logger.info("Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
					extent.extentLogger("SwipeRight",
							"Swiping screen in " + " " + direction + " direction" + " " + (j + 1) + " times");
				}
			}
		} catch (Exception e) {
			logger.error(e);

		}
	}

	public void findingTrayInscreen(int j, By byLocator1, By byLocator2, String str1, String str2, String userType,
			String tabName) throws Exception {
		boolean tray = false;
		for (int i = 0; i < j; i++) {
			if (!((verifyIsElementDisplayed(byLocator1)))) {
				Swipe("UP", 1);
			} else {
				verifyElementExist(byLocator1, str1);
				tray = true;
				if (tabName.equalsIgnoreCase("Home")) {
					if (str1.equalsIgnoreCase("Continue watching tray")) {

						Response respCW = ResponseInstance.getRespofCWTray(userType);

						List<String> ApinoOfContentsInCW = respCW.jsonPath().getList("array");
						logger.info("no.of contents in CW tray in Api " + ApinoOfContentsInCW.size());

						ArrayList<String> listOfContentsInCW = new ArrayList<String>();

						for (int k = 0; k < ApinoOfContentsInCW.size(); k++) {

							String title = respCW.jsonPath().getString("[" + k + "].title");
							listOfContentsInCW.add(title);
						}

						logger.info(listOfContentsInCW);

						for (int p = 0; p < ApinoOfContentsInCW.size(); p++) {

							verifyElementExist(AMDHomePage.objContentTitleOfCWTray(listOfContentsInCW.get(p)),
									"content title");

							if (verifyElementDisplayed(AMDHomePage.objLeftTimeOfFirstContentOfCWTray)) {
								logger.info("Left watch time info on cards is available");
								extent.extentLogger("Left watch time info",
										"Left watch time info on cards is available");
							} else {
								logger.info("Left watch time info on cards is not available");
								extent.extentLoggerFail("Left watch time info",
										"Left watch time info on cards is not available");
							}
							if (verifyElementDisplayed(AMDHomePage.objProgressBarOfFirstContentOfCWTray)) {
								logger.info("Progress bar is displayed to indicate the content watched portion");
								extent.extentLogger("Progress bar",
										"Progress bar is displayed to indicate the content watched portion");
							} else {
								logger.info("Progress bar is not displayed to indicate the content watched portion");
								extent.extentLoggerFail("Progress bar",
										"Progress bar is not displayed to indicate the content watched portion");
							}
							if (p != (ApinoOfContentsInCW.size() - 1)) {
								SwipeRail(AMDHomePage.objContentTitleOfCWTray(listOfContentsInCW.get(p + 1)));
							}
						}
					}
				}
				break;
			}
		}
		if (tray == false) {
			if (userType.equalsIgnoreCase("Guest")) {
				if (str1.equalsIgnoreCase("Continue watching tray")) {
					logger.info(str1 + " is not displayed");
					extent.extentLogger("Tray", str1 + " is not displayed");
				} else {
					logger.info(str1 + " is not displayed");
					extent.extentLoggerFail("Tray", str1 + " is not displayed");
				}
			} else {
				if (tabName.equalsIgnoreCase("Home")) {

					if (str1.equalsIgnoreCase("Continue watching tray")) {

						Response respCW = ResponseInstance.getRespofCWTray(userType);

						List<String> ApinoOfContentsInCW = respCW.jsonPath().getList("array");
						logger.info("no.of contents in CW tray in Api " + ApinoOfContentsInCW.size());

						if (ApinoOfContentsInCW.size() == 0) {

							logger.info(str1 + " is not displayed for this user");
							extent.extentLogger("Tray", str1 + " is not displayed for this user");
						} else {
							logger.info(str1 + " is not displayed for this user");
							extent.extentLoggerFail("Tray", str1 + " is not displayed for this user");
						}
					}
					logger.info(str1 + " is not displayed");
					extent.extentLoggerFail("Tray", str1 + " is not displayed");
				}
			}
		}
		for (int i = 0; i < j; i++) {
			if (!(verifyIsElementDisplayed(byLocator2))) {
				Swipe("DOWN", 1);
			} else {
				verifyElementExist(byLocator2, str2);
				break;
			}
		}
	}

	public void adBannerVerify(String tabname) throws Exception {
		extent.HeaderChildNode("Masthead and Display Ad verification");
		System.out.println("Masthead and Display Ad verification");
		waitTime(20000);
		closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 10000);

		getDriver().findElement(By.xpath("//*[@id='title' and @text='" + tabname + "']")).click();
		waitTime(10000);

		if (verifyElementDisplayed(AMDHomePage.objBannerAd)) {
			logger.info("Masthead and Display ads are displayed properly in " + tabname + " screen");
			extent.extentLogger("Ads", "Masthead and Display ads are displayed properly in " + tabname + " screen");
		} else {
			logger.info("Masthead and Display ads are not displayed properly in " + tabname + " screen");
			extent.extentLoggerFail("Ads",
					"Masthead and Display ads are not displayed properly in " + tabname + " screen");
		}
	}

	/**
	 * Author : Bhavana Module : Download Screen Validation
	 */

	public void EmptystateScreenValidation(String userType) throws Exception {
		extent.HeaderChildNode("Downloads screen Empty-state validation as " + userType);
		System.out.println("\nDownloads screen Empty-state validation as: " + userType);
		click(AMDHomePage.objDownloadBtn, "Downloads button");
		waitTime(3000);
		if (verifyElementExist(AMDDownloadPage.objDwnloadsHeader,
				"Downloads header at the top on center of the screen")) {
			extent.extentLogger("Downloads tab",
					"User is navigated to Downloads screen on tapping Downloads button present in the bottom navigation bar");
			logger.info(
					"User is navigated to Downloads screen on tapping Downloads button present in the bottom navigation bar");
		} else {
			extent.extentLoggerFail("Downloads tab", "User fails to navigate to Downloads screen");
			logger.error("User fails to navigate to Downloads screen");
		}
		Back(1);
		click(AMDHomePage.objDownloadBtn, "Downloads button");

		if (verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn,
				"Browse to Download CTA in Empty-state screen to download")) {
			extent.extentLogger("Downloads screen",
					"User is navigated to Empty-state screen when no downloaded contents are present");
			logger.info("User is navigated to Empty-state screen when no downloaded contents are present");
		} else {
			extent.extentLoggerFail("Downloads screen",
					"User fails to navigate to Empty-state screen when no downloaded contents are present");
			logger.error("User fails to navigate to Empty-state screen when no downloaded contents are present");
		}
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn,
				"Browse to Download CTA in Empty-state screen");
		waitTime(3000);
		String getPropertyshows = getAttributValue("enabled", AMDDownloadPage.objshowstab);
		if (getPropertyshows.equalsIgnoreCase("true")) {
			extent.extentLogger("Shows tab",
					"“Browse to Download” button is be tappable in the Empty-state screen to Download the content ");
			logger.info(
					"“Browse to Download” button is be tappable in the Empty-state screen to Download the contents");
		} else {
			extent.extentLoggerFail("Shows tab", "User fails to tap the 'Browse to Download' button");
			logger.error("User fails to tap the 'Browse to Download' button");
		}
		click(AMDHomePage.objDownloadBtn, "Downloads button");
	}

	public void validationofDownloadingContent() throws Exception {
		extent.HeaderChildNode("Validating funtionality of Pause and Continue Download CTA call-Out option");
		System.out.println("\nValidating funtionality of Pause and Continue Download CTA call-Out option");

		DownloadContent(content2, pVideoQuality, true);
		if (checkElementExist(AMDDownloadPage.objDownloadingText)) {
			extent.extentLogger("Donwloading Content", "Downloading content is displayed in Downloads screen");
			logger.info("Downloading content is displayed in Donwloads screen");
		} else {
			extent.extentLoggerFail("Donwloading Content", "Downloading content is not displayed in Downloads screen");
			logger.error("Downloading content is not displayed in Downloads screen");
		}
		verifyElementExist(AMDDownloadPage.objDownloadingText, "'Downloading' text");
		click(AMDDownloadPage.objDownloadingText, "Downloading text");
		verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar");
		click(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar");
		if (verifyElementExist(AMDDownloadPage.objPauseDownloadoption, "Call out with Pause option")) {
			extent.extentLogger("Pause", "Pause option is displayed when user taps Downloding content");
			logger.info("Pause option is displayed when user taps Downloding content");
		} else {
			extent.extentLoggerFail("Pause", "Pause option is NOT displayed when user taps Downloding content");
			logger.error("Pause option is NOT displayed when user taps Downloding content");
		}
		waitTime(2000);
		if (verifyElementExist(AMDDownloadPage.objCancelDownloadOption, "Call out with Cancel Download option")) {
			extent.extentLogger("Pause", "Cancel Download is displayed when user taps Downloding content");
			logger.info("Cancel Download is displayed when user taps Downloding content");
		} else {
			extent.extentLoggerFail("Pause", "Cancel Download is NOT displayed when user taps Downloding content");
			logger.error("Cancel Download NOT is displayed when user taps Downloding content");
		}
		click(AMDDownloadPage.objPauseDownloadoption, "Pause option");
		verifyElementExist(AMDDownloadPage.objPausedText, "'Paused' text");
		if (verifyElementExist(AMDDownloadPage.objPausedBar, "Paused bar")) {
			extent.extentLogger("Pause", "User is able to Pause the Downloading content on tapping 'Pause' option");
			logger.info("User is able to Pause the Downloading content on tapping 'Pause' option");
		} else {
			extent.extentLoggerFail("Pause",
					"User is fails to Pause the Downloading content on tapping 'Pause' option");
			logger.error("User fails to Pause the Downloading content on tapping 'Pause' option");
		}
		click(AMDDownloadPage.objPausedBar, "Paused bar");
		verifyElementPresentAndClick(AMDDownloadPage.objContinueOption, "Continue option");
		if (verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar")) {
			extent.extentLogger("Re-start", "User is able to re-start the Paused content");
			logger.info("User is able to re-start the Paused content");
		} else {
			extent.extentLoggerFail("Re-start", "User fails to re-start the Paused content");
			logger.error("User fails to re-start the Paused content");
		}
	}

	public void DownloadContent(String str) throws Exception {
		System.out.println("\nInitiate Download : " + str);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search Icon");
//		verifyElementPresentAndClick(AMDDownloadPage.objSearchIcon, "Search Icon");
		waitTime(3000);
		click(AMDSearchScreen.objSearchEditBox, "Search edit");
		type(AMDSearchScreen.objSearchBoxBar, str, "Search Field");
		waitTime(3000);
		hideKeyboard();
		click(AMDSearchScreen.objSelectFirstEpisodeResult, "Searched Show");
		waitTime(5000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
		verifyElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download video quality Pop up");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
		waitTime(2000);
		Back(1);
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
	}

	public void pauseAllAndCancelDownload() throws Exception {
		extent.HeaderChildNode("Validating Call-Out options with Pause All and Cancel Download CTA");
		System.out.println("\nValidating Call-Out options with Pause All and Cancel Download CTA");

		verifyElementPresentAndClick(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
		verifyElementExist(AMDDownloadPage.objCallOutwithPauseAll, "Call out with Pause and Cancel Download CTAs");
		verifyElementExist(AMDDownloadPage.objPauseAllOption, "Pause All CTA");
		verifyElementExist(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA");
		click(AMDDownloadPage.objPauseAllOption, "Pause All CTA");
		if (verifyElementExist(AMDDownloadPage.objPausedText, "'Paused' text")) {
			extent.extentLogger("Pause All", "Queued contents are Paused and in Paused state");
			logger.info("Queued contents are Paused and in Paused state");
		}
		click(AMDDownloadPage.objPausedIcon("Comedy Kiladigalu Championship Episode 24"), "Paused icon");
		click(AMDDownloadPage.objRetryCTA, "Continue option");
		int totalEpisodesList = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
		logger.info(totalEpisodesList);
		click(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
		click(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA");
		waitTime(3000);
		int totalEpisodesList2 = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
		logger.info(totalEpisodesList2);
		if (totalEpisodesList != totalEpisodesList2) {
			extent.extentLogger("Cancel Download", "Downloading content is deleted");
			logger.info("Downloading content is deleted");
		} else {
			extent.extentLoggerFail("Cancel Download",
					"Downloading content is not deleted on tapping Cancel Download CTA");
			logger.error("Downloading content is not deleted on tapping Cancel Download CTA");
		}
	}

	public void DownloadsSection() throws Exception {
		extent.HeaderChildNode("Validating Downloads Page section");
		System.out.println("\nValidating Downloads Page section");

		verifyElementExist(AMDDownloadPage.objTitleoftheShow, "Title of the show");
		System.out.println("Title of the Show is " + getText(AMDDownloadPage.objTitleoftheShow));
		logger.info("Title of the Show is " + getText(AMDDownloadPage.objTitleoftheShow));
		extent.extentLogger("Title", "Title of the Show is " + getText(AMDDownloadPage.objTitleoftheShow));
		verifyElementExist(AMDDownloadPage.objNoOfEpisodes, "Number of Episodes");
		System.out.println("Number of Episodes are " + getText(AMDDownloadPage.objNoOfEpisodes));
		logger.info("Number of Episodes are " + getText(AMDDownloadPage.objNoOfEpisodes));
		extent.extentLogger("Episodes", "Number of Episodes are " + getText(AMDDownloadPage.objNoOfEpisodes));
		verifyElementExist(AMDDownloadPage.objSizeOfEpiodes, "Size of Episodes");
		System.out.println("Size of Episodes is " + getText(AMDDownloadPage.objSizeOfEpiodes));
		logger.info("Size of Episodes is " + getText(AMDDownloadPage.objSizeOfEpiodes));
		extent.extentLogger("Size", "Size of Episodes is " + getText(AMDDownloadPage.objSizeOfEpiodes));
		verifyElementExist(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		verifyElementExist(AMDDownloadPage.objThumbnailOfShows, "Thumbnail of the Show");
		waitTime(2000);
		click(AMDDownloadPage.objTitleoftheShow, "Title of the show");
		if (verifyElementExist(AMDDownloadPage.objDownloadMoreCTA, "Download More CTA")) {
			extent.extentLogger("Download More", "Show name is Tappable");
			logger.info("Show name is Tappable");
		} else {
			extent.extentLoggerFail("Download More", "Show name NOT is Tappable");
			logger.error("Show name is NOT Tappable");
		}
		Back(1);
		click(AMDDownloadPage.objThumbnailOfShows, "Thumbnail of the Show");
		if (verifyElementExist(AMDDownloadPage.objDownloadMoreCTA, "Download More CTA")) {
			extent.extentLogger("Download More", "Thumbnail is Tappable");
			logger.info("Thumbnail is Tappable");
		} else {
			extent.extentLoggerFail("Download More", "Thumbnail is NOT Tappable");
			logger.error("Thumbnail is NOT Tappable");
		}
		Back(1);
		click(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		if (verifyElementExist(AMDDownloadPage.objDownloadMoreCTA, "Download More CTA")) {
			extent.extentLogger("Download More", "Right Arrow Tappable");
			logger.info("Right Arrow is Tappable");
		} else {
			extent.extentLoggerFail("Download More", "Right Arrow Tappable");
			logger.error("Right Arrow is Tappable");
		}
	}

	public void LatestEpisode() throws Exception {
		waitTime(3000);
		extent.HeaderChildNode("Validating New Episode");
		System.out.println("Validating New Episode");
		if (checkElementExist(AMDDownloadPage.objNewEpisodeContent, "New Episode on top")) {
			extent.extentLogger("New Epiosde on top ", "New Episode on top is displayed");
			logger.info("New Episode on top is displayed");
		} else {
			extent.extentLoggerFail("New Epiosde on top ", "New Episode on top is NOT displayed");
			logger.error("New Episode on top is NOT displayed");
		}
		String titleOfNewEpisode = getText(AMDDownloadPage.objtitleofNewEpisode);
		System.out.println(titleOfNewEpisode);
		if (checkElementExist(AMDDownloadPage.objNewEpisodeTag, "'New Episode' text")) {
			extent.extentLogger("New Epiosde text", "New Episode text is displayed");
			logger.info("New Episode text is displayed");
		} else {
			extent.extentLoggerFail("New Epiosde text", "New Episode text is NOT displayed");
			logger.error("New Episode text is NOT displayed");
		}
		if (checkElementExist(AMDDownloadPage.objThumbnailOfLatestEpisode, "Thumbnail of Latest Episode")) {
			extent.extentLogger("Thumbnail of Latest Episode", "Thumbnail of Latest Episode is displayed");
			logger.info("Thumbnail of Latest Episode is displayed");
		} else {
			extent.extentLoggerFail("Thumbnail of Latest Episode", "Thumbnail of Latest Episode NOT is displayed");
			logger.error("Thumbnail of Latest Episode is NOT displayed");
		}
		verifyElementExist(AMDDownloadPage.objDownloadNowbbtn, "Download Now button");
		verifyElementExist(AMDDownloadPage.objClosebtn, "Cancel button (X)");
		waitTime(2000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadNowbbtn, "Download Now button");
		if (checkElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download popup")) {
			extent.extentLogger("Download Now ", "Download Now button is functional");
			logger.info("Download Now button is functional");
		} else {
			extent.extentLoggerFail("Download Now ", "Download Now button is NOT functional");
			logger.error("Download Now button is NOT functional");
		}
		Back(1);
		click(AMDDownloadPage.objClosebtn, "Cancel button (X)");
		if ((checkElementExist(AMDDownloadPage.objNewEpisodeTag, "New Episode")) == false) {
			extent.extentLogger("Cancel button", "Cancel button is Tappable");
			logger.info("Cancel button is Tappable");
		} else {
			extent.extentLoggerFail("Cancel button", "Cancel button is NOT Tappable");
			logger.error("Cancel button is NOT Tappable");
		}
		if ((checkElementExist(AMDDownloadPage.objThumbnailOfLatestEpisode, "Latest Episode")) == false) {
			extent.extentLogger("New Episode", "Latest Epiosde is not displayed on the top");
			logger.info("Latest Epiosde is not displayed on the top");
		} else {
			extent.extentLoggerFail("New Episode", "Latest Epiosde is not displayed on the top");
			logger.error("Latest Epiosde is not displayed on the top");
		}
		waitTime(2000);
		Back(1);
		click(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		waitTime(2000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadNowbbtn, "Download Now button");
		click(AMDDownloadPage.objDownloadVideoQualityPopup, "Download video quality Pop up");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
		Back(1);
		verifyElementExist(AMDDownloadPage.objDownloadingText, "Downloading text");
		click(AMDDownloadPage.objDownloadingText, "Downloading text");
		if (checkElementExist(AMDDownloadPage.objEpisodesList, "Episoed List")) {
			extent.extentLogger("Episodes list", "Episodes list is displayed");
			logger.info("Episodes list is displayed");
		} else {
			extent.extentLoggerFail("Episodes list", "Episodes list is NOT displayed");
			logger.error("Episodes list is NOT displayed");
		}
		if ((checkElementExist(AMDDownloadPage.objClosebtn, "Cross (X) icon")) == false) {
			extent.extentLogger("Close button",
					"Cross (X) icon is not displayed when New Episode download is in progress");
			logger.info("Cross (X) icon is not displayed when New Episode download is in progress");
		} else {
			extent.extentLogger("Close button", "Cross (X) icon is displayed when New Episode download is in progress");
			logger.info("Cross (X) icon is displayed when New Episode download is in progress");
		}
		if (checkElementExist(AMDDownloadPage.objtitleofNewEpisode, titleOfNewEpisode)) {
			extent.extentLogger("Episodes list", "New Episode on top of the screen is displayed");
			logger.info("New Episode on top is displayed");
		} else {
			extent.extentLoggerFail("Episodes list", "New Episode on top of the screen is NOT displayed");
			logger.error("New Episode on top is NOT displayed");
		}
	}

	public void DownloadingOffline() throws Exception {
		extent.HeaderChildNode("Validation Downloads in Offline mode");
		System.out.println("\nValidation Downloads in Offline mode");

		// *** Verifying download in offline Mode
		setWifiConnectionToONOFF("Off");

		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
			waitTime(2000);
		}
		waitForElementDisplayed(AMDDownloadPage.objDownloadFailedText, 2000);
		verifyElementExist(AMDDownloadPage.objDownloadFailedText, "Download Failed text");
		verifyElementExist(AMDDownloadPage.objDownloadErrorText, "Download Error text");
		click(AMDDownloadPage.objDownloadErrorText, "Download Error text");
		verifyElementExist(AMDDownloadPage.objRetryCTA, "Retry CTA Call-Out");
		verifyElementExist(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA Call-Out");

		setWifiConnectionToONOFF("ON");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
			waitTime(7000);
			waitForElementDisplayed(AMDDownloadPage.objRetryCTA, 2000);
		}
		click(AMDDownloadPage.objRetryCTA, "Call out with Retry CTA");
		waitTime(5000);
		if (verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar")) {
			extent.extentLogger("Re-start", "User is able to tap the Retry CTA and resumed downloading the content");
			logger.info("User is able to tap the Retry CTA and resumed downloading the content");
		} else {
			extent.extentLoggerFail("Re-start",
					"User fails to tap the Retry CTA and fail to resume downloading the content");
			logger.error("User fails to tap the Retry CTA and fail to resume downloading the content");
		}
	}

	public void verifyDownloadsSubscribeUser() throws Exception {
		extent.HeaderChildNode("Verify the download screen");
		System.out.println("\nVerify the download screen");
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		waitTime(2000);
		String SubscriptionExpierstext = getDriver().findElement(AMDDownloadPage.objPackExpiredText).getText();
		System.out.println(SubscriptionExpierstext);
		if (SubscriptionExpierstext.equalsIgnoreCase("Your Subscription expires in 10 days")) {
			logger.info("Your Premium subscription expires in XX days text message is displayed");
			extent.extentLogger("Download Screen",
					"Your Premium subscription expires in XX days text message is displayed");
		} else {
			logger.error("Your Premium subscription expires in XX days text message is displayed");
			extent.extentLoggerFail("Download Screen",
					"Your Premium subscription expires in XX days text message is displayed");
		}
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search Icon");
		waitTime(3000);
		click(AMDSearchScreen.objSearchEditBox, "Search edit");
		type(AMDSearchScreen.objSearchBoxBar, "Dil Bechara - Title Track", "Search Field");
		waitTime(5000);
		hideKeyboard();
		click(AMDDownloadPage.objSearchedContent, "Searched Show");
		waitTime(3000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
		waitTime(2000);
		click(AMDDownloadPage.objDownloadVideoQualityPopup, "Download video quality Pop up");
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
		waitTime(1000);
		Back(1);
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		verifyElementExist(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen ");
		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objDownloadedTickMark, "Tick Mark on the downloaded content");
		click(AMDDownloadPage.objDownloadedTickMark, "Tick Mark on the downloaded content");
		verifyElementExist(AMDDownloadPage.objDeleteDownloadOption, "Delete downloaded content");
		click(AMDDownloadPage.objDeleteDownloadOption, "Delete downloaded content");
		waitTime(4000);
		if (verifyElementExist(AMDDownloadPage.objDownloadTextIcon, "Download Icon")) {
			logger.info("Content is deleted by tapping on delete download");
			extent.extentLogger("Download Screen", "Content is deleted by tapping on delete download");
		} else {
			logger.error("Content is deleted by tapping on delete download");
			extent.extentLoggerFail("Download Screen", "Content is deleted by tapping on delete download");
		}
		verifyElementExist(AMDDownloadPage.objRemaindMeLater, "Remaind Me Later");
		click(AMDDownloadPage.objRemaindMeLater, "Remaind Me Later");
		if (verifyElementExist(AMDDownloadPage.objPackExpiredText, "Premium pack expires Text")) {
			logger.info("Premium pack expires text is not displayed by tapping on Remaind Me Later");
			extent.extentLogger("Download Screen",
					"Premium pack expires text is not displayed by tapping on Remaind Me Later");
		} else {
			logger.error("Premium pack expires text is not displayed by tapping on Remaind Me Later");
			extent.extentLoggerFail("Download Screen",
					"Premium pack expires text is not displayed by tapping on Remaind Me Later");
		}

	}

	public void DownloadScreenValidation(String userType) throws Exception {
		System.out.println("\nDownload Landing Screen Validation");
		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {

			EmptystateScreenValidation(userType);
			verifyDownloadsWithSingleTire();
			verifyMovieContentInDownloadsScreen(pMovie, pVideoQuality);
			validationofDownloadingContent();

			int totalEpisodesList = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
			logger.info("Content Cards: " + totalEpisodesList);
			// --- Download check in Offline Mode
			DownloadingOffline();

			extent.HeaderChildNode("Verify Deleted Content from Downloads screen");
			System.out.println("\nVerify Deleted Content from Downloads screen");

			click(AMDDownloadPage.objDownloadingCircularBar, "Downloading circular bar");
			click(AMDDownloadPage.objCancelDownloadOption, "Cancel Download CTA");

			int totalEpisodesList2 = getDriver().findElements(AMDDownloadPage.objNoOfEpisodeList).size();
			logger.info("Content Cards: " + totalEpisodesList2);
			if (totalEpisodesList != totalEpisodesList2) {
				extent.extentLogger("Cancel Download", "Downloading content is deleted");
				logger.info("Downloading content is deleted");
			} else {
				extent.extentLoggerFail("Cancel Download", "Downloading content is NOT deleted");
				logger.error("Downloading content is NOT deleted");
			}
			waitTime(2000);
			Back(1);
			String getProperty1 = getAttributValue("enabled", AMDHomePage.objDownloadBtn);
			if (getProperty1.equalsIgnoreCase("true")) {
				extent.extentLogger("Downloads Tab", "Downloads active page without content downloading is displayed");
				logger.info("Downloads active page without content downloading is displayed");
			} else {
				extent.extentLoggerFail("Downloads tab",
						"No Downloads active page without content downloading is NOT displayed");
				logger.error("No Downloads active page without content downloading is NOT displayed");
			}

			extent.HeaderChildNode("Verify multiple downloading content in Downloads screen");
			System.out.println("\nVerify multiple downloading content in Downloads screen");

			// **** Download Episode content3
			DownloadContent(content3, pVideoQuality, true);
			if (checkElementExist(AMDDownloadPage.objDownloadingText)) {
				extent.extentLogger("Downloading Content", "Downloading content is displayed in Downloads screen");
				logger.info("Downloading content is displayed in Donwloads screen");
			} else {
				extent.extentLoggerFail("Downloading Content",
						"Downloading content is not displayed in Downloads screen");
				logger.error("Downloading content is not displayed in Downloads screen");
			}

			// **** Download Episode content4
			DownloadContent(content4, "Better", true);
			if (checkElementExist(AMDDownloadPage.objDownloadingText)) {
				extent.extentLogger("Donwloading Content", "Downloading content is displayed in Downloads screen");
				logger.info("Downloading content is displayed in Donwloads screen");
			} else {
				extent.extentLoggerFail("Donwloading Content",
						"Downloading content is not displayed in Downloads screen");
				logger.error("Downloading content is not displayed in Downloads screen");
			}
			if (checkElementExist(AMDDownloadPage.objShowsDownloadPage)) {
				extent.extentLogger("ShowsList", "Shows list is displayed in the upfront tab");
				logger.info("Shows list is displayed in the upfront tab");
			} else {
				extent.extentLoggerFail("ShowsList", "Shows list is NOT displayed in the upfront tab");
				logger.error("Shows list is NOT displayed in the upfront tab");
			}
			waitTime(3000);
			click(AMDDownloadPage.objDownloadingText, "Downloading text");
			if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar)) {
				extent.extentLogger("Queued", "User is able to Download only one content at a time");
				logger.info("User is able to Download only one content at a time");
			} else {
				extent.extentLoggerFail("Queued", "User fails to Download only one content at a time");
				logger.error("User fails to Download only one content at a time");
			}
			if (verifyElementExist(AMDDownloadPage.objQueuedbar("Comedy Kiladigalu Championship Episode 9"),
					"Paused icon")) {
				extent.extentLogger("Queued", "Contents are Queued up in a line ");
				logger.info("Contents are Queued up in a line ");
			} else {
				extent.extentLoggerFail("Queued", "Contents are NOT Queued up in a line ");
				logger.error("Contents are NOT Queued up in a line ");
			}
			pauseAllAndCancelDownload();
			click(AMDDownloadPage.objPausedBar, "Paused icon");
			click(AMDDownloadPage.objRetryCTA, "Continue option");
			waitTime(2000);
			Back(1);

			// **** Download Episode content5
			DownloadContent(content5, pVideoQuality, true);
			waitTime(1000);
			click(AMDDownloadPage.objDownloadingText, "Downloading text");
			if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar)) {
				extent.extentLogger("Downloading", "Incomplete Downloads are available");
				logger.info("Incomplete Downloads are available");
			}
			click(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon");
			if (checkElementExist(AMDDownloadPage.objCallOutwithPauseAll)) {
				extent.extentLogger("Downloading", "Incomplete Downloads are NOT allowed to be Play");
				logger.info("Incomplete Downloads are NOT allowed to be Play");
			} else {
				extent.extentLoggerFail("Downloading", "Incomplete Downloads are allowed to be Play");
				logger.error("Incomplete Downloads are allowed to be Play");
			}
//			switchNetworkWifiToData();
			Back(2);

			// **** Download Episode content6
			DownloadContent(content6, "Best", true);
			DownloadsSection();
			LatestEpisodeOnTheTop();
			click(AMDHomePage.HomeIcon, "Home Icon");
		}
		if (userType.contentEquals("SubscribedUser")) {
			ZNALogoutMethod();
			ValidateSubscriptionExpireBanner();
		}
	}

	public void DownloadContent(String contentName, String Quality, boolean checkAlwaysAskOption) throws Exception {
		System.out.println("\nInitiate Download : " + contentName);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon2, "Search Icon");
		waitTime(3000);
		click(AMDSearchScreen.objSearchEditBox, "Search edit");
		type(AMDSearchScreen.objSearchBoxBar, contentName, "Search Field");
		waitTime(3000);
		hideKeyboard();
		click(AMDSearchScreen.objSelectFirstEpisodeResult, "Searched Show");
		waitForElementDisplayed(AMDDownloadPage.objPauseIconOnPlayer, 2000);
		waitTime(3000);
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadIcon, "Download button");
		waitTime(2000);
		DownloadVideoQualityPopUp(Quality, checkAlwaysAskOption);
		waitTime(2000);
		Back(1);
		click(AMDHomePage.objDownloadBtn, "Downloads Tab");
	}

	public void DownloadVideoQualityPopUp(String Quality, boolean checkAlwaysAsk) throws Exception {

		verifyElementExist(AMDDownloadPage.objDownloadVideoQualityPopup, "Download Video Quality PopUp screen");
		click(AMDDownloadPage.objDownloadQualityOptions(Quality), Quality);

		String getValue = getAttributValue("checked", AMDDownloadPage.objAlwaysAskCheckBox);
		if (checkAlwaysAsk) {
			if (!getValue.contains("true")) {
				click(AMDDownloadPage.objAlwaysAskCheckBox, "Always ask quality for every download");
			}
		} else {
			if (getValue.contains("true")) {
				click(AMDDownloadPage.objAlwaysAskCheckBox, "Always ask quality for every download");
			}
		}
		click(AMDDownloadPage.objStartDownloadCTA, "Start Download CTA");
	}

	public void switchNetworkWifiToData() throws Exception {
		extent.HeaderChildNode("Validating Downloading resumes after switching network");
		System.out.println("\nValidating Downloading resumes after switching network");

		// Switching Network from Wifi -> Data
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi disable");
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc data enable");
		if (verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon")) {
			extent.extentLogger("Downloading", "User is able to continue the download on shuffling wifi to data");
			logger.info("User is able to continue the download on shuffling wifi to data");
		}
		waitTime(2000);
		// Switching Network from Data -> Wifi
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc data disable");
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi enable");
		if (verifyElementExist(AMDDownloadPage.objDownloadingCircularBar, "Downloading Icon")) {
			extent.extentLogger("Downloading", "User is able to continue the download on shuffling data to wifi");
			logger.info("User is able to continue the download on shuffling wifi to data");
		}
	}

	public void LatestEpisodeOnTheTop() throws Exception {
		extent.HeaderChildNode("Validating New Episode Overlay on the Top");
		System.out.println("\nValidating New Episode Overlay on the Top");

		if (checkElementExist(AMDDownloadPage.objNewEpisodeContent)) {
			extent.extentLogger("New Epiosde on top ", "New Episode content card is displayed on the Top");
			logger.info("New Episode on top is displayed");
		} else {
			extent.extentLoggerWarning("New Epiosde on top ", "New Episode content card is NOT displayed on the Top");
			logger.info("New Episode on top is NOT displayed");
		}

		if (checkElementExist(AMDDownloadPage.objNewEpisodeTag)) {
			String titleOfNewEpisode = getText(AMDDownloadPage.objtitleofNewEpisode);
			extent.extentLogger("New Epiosde title", "Episode title is: " + titleOfNewEpisode);
			logger.info("Episode title is: " + titleOfNewEpisode);
		} else {
			extent.extentLoggerWarning("New Epiosde title", "Episode title is NOT displayed");
			logger.error("Episode title is NOT displayed");
		}

		if (checkElementExist(AMDDownloadPage.objThumbnailOfLatestEpisode)) {
			extent.extentLogger("Thumbnail of Latest Episode", "Thumbnail Image of Latest Episode is displayed");
			logger.info("Thumbnail of Latest Episode is displayed");
		} else {
			extent.extentLoggerWarning("Thumbnail of Latest Episode", "Thumbnail of Latest Episode NOT is displayed");
			logger.error("Thumbnail Image of Latest Episode is NOT displayed");
		}
		verifyElementExist(AMDDownloadPage.objDownloadNowbbtn, "Download Now CTA");
		verifyElementPresentAndClick(AMDDownloadPage.objClosebtn, "Close button (X)");
		Back(1);
		click(AMDDownloadPage.objRightArrowinDownloads, "Right Arrow");
		if (checkElementExist(AMDDownloadPage.objDownloadNowbbtn)) {
			extent.extentLogger("Latest Epiosde", "Latest Epiosde is displayed on the top");
			logger.info("Latest Epiosde is not displayed on the top");
		} else {
			extent.extentLoggerWarning("Latest Epiosde", "Latest Epiosde is NOT displayed on the top");
			logger.error("Latest Epiosde is displayed on the top");
		}
		Back(1);
	}

	public void verifyMovieContentInDownloadsScreen(String MovieName, String Quality) throws Exception {
		extent.HeaderChildNode("Validating the downloading content in Movies tab");
		System.out.println("\nValidating the downloading content in Movies tab");

		DownloadContent(MovieName, Quality, true);
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objmoviestab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLogger("MOVIES Tab is highlighted",
					"User is taken to MOVIES tab by default, since download is in-progress..");
			logger.info("User is taken to MOVIES tab by default, since download is in-progress..");
		} else {
			extent.extentLoggerFail("MOVIES Tab",
					"User is NOT taken to MOVIES tab by default, even after initiating Movie content download");
			logger.error("User is NOT taken to MOVIES tab by default, even after initiating Movie content download");
		}

		if (checkElementExist(AMDDownloadPage.objDownloadingCircularBar)) {
			String getMovieName = getText(AMDDownloadPage.objDownloadedContent);
			click(AMDDownloadPage.objDownloadedContent, getMovieName);
			click(AMDDownloadPage.objCancelDownloadOption, "Cancel CTA");
			extent.extentLogger("Downloading Movie", getMovieName + " movie is downloading in the Downloads screen");
			logger.info(getMovieName + " movie is downloading in Downloads screen");
		} else {
			extent.extentLoggerFail("Donwloading Movie",
					"Downloading " + MovieName + " is NOT displayed in the Downloads screen");
			logger.error("Downloading " + MovieName + " is NOT displayed in Downloads screen");
		}
	}

	/**
	 * Author : Bhavana Module : Download Screen Validation
	 */
	public void DownloadScreenUIUXValidation(String userType) throws Exception {
		extent.HeaderChildNode("Verify the UI/UX of Download landing screen as " + userType);
		System.out.println("\nVerify the UI/UX of Download landing screen as " + userType);
		waitTime(5000);
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		waitTime(3000);
		verifyElementExist(AMDDownloadPage.objDwnloadsHeader, "Downloads header at the top on center of the screen");
		verifyElementExist(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementExist(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen ");
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objshowstab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLogger("Shows tab", "Shows tab is by default highlighted");
			logger.info("Shows tab is by default highlighted");
		} else {
			extent.extentLoggerFail("Shows tab", "Shows tab fails to highlight by default");
			logger.error("Shows tab fails to highlight by default");
		}

		verifyElementPresentAndClick(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab");
		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
		verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Videos tab");
	}

	public void BrowseToDownloadFunctionality(String userType) throws Exception {
		extent.HeaderChildNode("Verify Browse to Download CTA functionality as " + userType);
		System.out.println("\nVerify Browse to Download CTA functionality as " + userType);
		String getSelectedTabName;
		verifyElementPresentAndClick(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Shows")) {
			extent.extentLogger("Shows tab", "User is navigated to Shows landing page");
			logger.info("User is navigated to Shows landing page");
		} else {
			extent.extentLoggerFail("Shows tab", "User fails to navigate to Shows landing page and instead displayed : "
					+ getSelectedTabName + " landing screen");
			logger.error("User fails to navigate to Shows landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Movies")) {
			extent.extentLogger("Movies tab", "User is navigated to Movies landing page");
			logger.info("User is navigated to Movies landing page");
		} else {
			extent.extentLoggerFail("Movies tab",
					"User fails to navigate to Movies landing page and instead displayed : " + getSelectedTabName
							+ " landing screen");
			logger.error("User fails to navigate to Movies landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads landing screen");
		verifyElementPresentAndClick(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Videos tab");
		waitTime(3000);
		getSelectedTabName = getText(AMDHomePage.objSelectedTab);
		if (getSelectedTabName.equalsIgnoreCase("Videos")) {
			extent.extentLogger("Videos tab", "User is navigated to videos landing page");
			logger.info("User is navigated to Videos landing page");
		} else {
			extent.extentLoggerFail("Movies tab",
					"User fails to navigate to Videos landing page and instead displayed : " + getSelectedTabName
							+ " landing screen");
			logger.error("User fails to navigate to Videos landing page and instead displayed : " + getSelectedTabName
					+ " landing screen");
		}
	}

	public void ZNALogoutMethod() throws Exception {
		verifyElementExist(AMDHomePage.objHomeTab, "Home tab");
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
		waitTime(2000);
		PartialSwipe("UP", 2);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		verifyElementPresentAndClick(AMDHomePage.objHome, "Home tab");
	}

	public void ValidateSubscriptionExpireBanner(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Verify the download screen");
		System.out.println("\nVerify the download screen");
		ZeeApplicasterLogin(LoginMethod);
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		waitTime(2000);
		String SubscriptionExpiersText = getDriver().findElement(AMDDownloadPage.objPackExpiredText).getText();
		System.out.println(SubscriptionExpiersText);
		if (SubscriptionExpiersText.contains("Your Subscription expires in")) {
			logger.info(SubscriptionExpiersText + "text message is displayed");
			extent.extentLogger("Download Screen", SubscriptionExpiersText + "text message is displayed");
		} else {
			logger.error(SubscriptionExpiersText + "text message is displayed");
			extent.extentLoggerFail("Download Screen", SubscriptionExpiersText + "text message is not displayed");
		}
		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		click(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		if (verifyElementNotPresent(AMDDownloadPage.objPackExpiredText, 2)) {
			logger.info("Premium pack expires text is not displayed by tapping on Remaind Me Later");
			extent.extentLogger("Download Screen",
					"Premium pack expires text is not displayed by tapping on Remaind Me Later");
		}
	}

	public void verifyDownloadsWithSingleTire() throws Exception {
		extent.HeaderChildNode(
				"Validating Video DownloadScreen and Content playback of downloaded Video with Single tier content");
		System.out.println(
				"\nValidating Video DownloadScreen and Content playback of downloaded Video with Single tier content");

		verifyElementPresentAndClick(AMDSearchScreen.objDownloadsOption, "Downloading Icon");
		waitTime(2000);
		DownloadContent(content1, "Good", true);
		String DownloadedContentText = getDriver().findElement(AMDDownloadPage.objDownloadedVideoContent).getText();
		System.out.println(DownloadedContentText);
		if (DownloadedContentText.contains(content1)) {
			logger.info("Downloaded Video content is displayed in the Upfront screen of the Videos Tab");
			extent.extentLogger("Download Screen",
					"Downloaded Video content is displayed in the Upfront screen of the Videos Tab");
		} else {
			logger.error("Downloaded Video content is NOT displayed in the Upfront screen of the Videos Tab");
			extent.extentLoggerFail("Download Screen",
					"Downloaded Video content is NOT displayed in the Upfront screen of the Videos Tab");
		}
		Back(1);
		waitTime(3000);
		click(AMDSearchScreen.objDownloadsOption, "Downloading Icon");
		String getPropertyValue = getAttributValue("enabled", AMDDownloadPage.objvideostab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			extent.extentLogger("Videos tab",
					"Videos tab is by default highlighted, User is taken to VIDEOS tab by default, hence Shows and Movies tab do not have any downloaded content");
			logger.info(
					"Videos tab is by default highlighted, User is taken to VIDEOS tab by default, hence Shows and Movies tab do not have any downloaded content");
		} else {
			extent.extentLoggerFail("Videos tab",
					"Videos tab fails to highlight by default, User is NOT taken to VIDEOS tab by default, even though Shows/Movies has no downloaded content");
			logger.error(
					"Videos tab fails to highlight by default, User is NOT taken to VIDEOS tab by default, even though Shows/Movies has no downloaded content");
		}

		verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab in Downloads screen");
		verifyElementPresentAndClick(AMDDownloadPage.objDownloadedVideoContent, content1);
		verifyElementPresentAndClick(AMDDownloadPage.objPlayDownloadedContent, "Play Call-out");

		if (verifyElementDisplayed(AMDDownloadPage.objPauseIconOnPlayer)) {
			extent.extentLogger("Video Content", "Playback Started for Video Content : " + content1);
			logger.info("Playback Started for Video Content : " + content1);
		} else {
			logger.error("Playback failed to Start for Video Content - " + content1);
			extent.extentLoggerFail("Download Screen", "Playback failed to Start for the Video Content - " + content1);
		}
		Back(1);

		verifyElementPresentAndClick(AMDDownloadPage.objmoviestab, "Movies tab in Downlaods landing screen");
		if (verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Movies tab")) {
			extent.extentLogger("Movies tab", "Movies Tab not having any downloaded content");
			logger.info("Movies Tab not having any downloaded content");
		}

		verifyElementPresentAndClick(AMDDownloadPage.objshowstab, "Shows tab in Downloads landing screen");
		if (verifyElementExist(AMDDownloadPage.objBrowseToDownloadBtn, "Browse to Download CTA under Shows tab")) {
			extent.extentLogger("Shows tab", "Shows Tab not having any downloaded content");
			logger.info("Shows Tab not having any downloaded content");
		}
	}

	public void ValidateSubscriptionExpireBanner() throws Exception {
		extent.HeaderChildNode("Validating Subscription Expiry banner in Donwloads Screen");
		System.out.println("\nValidating Subscription Expiry banner in Donwloads Screen");
		click(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfile, "Profile");

		String SubscribedUserWith15daysExpiryUsername = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("SubscribedUserWith15daysExpiryUsername");
		String SubscribedUserWith15daysExpiryPassword = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("SubscribedUserWith15daysExpiryPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

		LoginWithEmailID(SubscribedUserWith15daysExpiryUsername, SubscribedUserWith15daysExpiryPassword);
		waitTime(3000);
		verifyElementExist(AMDHomePage.objDownloadBtn, "Downloads tab at the bottom navigation bar");
		click(AMDHomePage.objDownloadBtn, "Downloads tab");
		click(AMDDownloadPage.objmoviestab, "Movies tab");

		if (verifyIsElementDisplayed(AMDDownloadPage.objPackExpiredText)) {
			String SubscriptionExpiersText = getDriver().findElement(AMDDownloadPage.objPackExpiredText).getText();
			System.out.println(SubscriptionExpiersText);
			if (SubscriptionExpiersText.contains("Your Subscription expires in")) {
				logger.info(SubscriptionExpiersText + "  is displayed");
				extent.extentLogger("Downloads Screen", SubscriptionExpiersText + " is displayed");
			} else {
				logger.error("Your Subscription expires in X-days is NOT displayed");
				extent.extentLoggerFail("Downloads Screen", "Your Subscription expires in X-days is NOT displayed");
			}
		} else {
			logger.error("Subscription is about to Expire message is NOT displayed");
			extent.extentLoggerWarning("Your Subscription expires",
					"Subscription is about to Expire message is NOT displayed");
		}

		waitTime(2000);
		verifyElementExist(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		click(AMDDownloadPage.objRemaindMeLater, "Remind Me Later");
		if (checkElementExist(AMDDownloadPage.objIcon)) {
			logger.info("Premium pack expires message is not displayed on tapping - Remaind Me Later");
			extent.extentLogger("Downloads Screen",
					"Premium pack expires text is not displayed on tapping - Remaind Me Later");
		}
		click(AMDHomePage.HomeIcon, "Home Icon");
	}

	/**
	 * Author : Bindu Module : Shows Screen
	 */
	public void verifyShowsScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify user navigates to shows screen");
		System.out.println("\nVerify User navigated to Shows Screen");

		// Selecting Shows tab from Top Navigation
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");

		String getPropertyValue = getAttributValue("enabled", AMDHomePage.objShowsTab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			logger.info(userType
					+ " is able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
			extent.extentLogger("Shows Tab", userType
					+ " is able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
		} else {
			logger.info(userType
					+ " is not able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
			extent.extentLoggerFail("Shows Tab", userType
					+ " is not able to navigate to Shows screen by tapping on Shows tab displayed in the top navigation bar");
		}

		if ((userType.equalsIgnoreCase("Guest")) | (userType.equalsIgnoreCase("NonSubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed on the header of the Shows Landing Screen");
				extent.extentLogger("Subscribe icon",
						"Subscribe icon is dislayed on the header of the Shows Landing Screen");
			} else {
				logger.info("Subscribe icon is not dislayed on the header of the Shows Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the Shows Landing Screen");
			}
		} else {

			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is not dislayed on the header of the Shows Landing Screen");
				extent.extentLogger("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the Shows Landing Screen");

			} else {
				logger.info("Subscribe icon is dislayed on the header of the Shows Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is dislayed on the header of the Shows Landing Screen");
			}
		}
		carouselValidationforShowsAndNews(userType, "Shows");
		String CarouselTitle = ShowsScreenValidationwithApiData(userType);
		if (CarouselTitle == null) {
			logger.info("No premium content present in the carousel to validate Content Playback");
		} else {
			verifyElementPresentAndClick(AMDHomePage.objContentTitle(CarouselTitle), "Carousel content");
			if (!(verifyElementIsNotDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen))) {
				if (userType.equals("Guest")) {
					if (verifyElementIsNotDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
						logger.info(
								"Content playback is initiated for the guest user post tapping on premium content which is having trailer");
						extentLogger("Trailer",
								"Content playback is initiated for the guest user post tapping on premium content which is having trailer");

					} else {
						logger.info(
								"Content playback is not initiated for the guest user post tapping on premium content which is having trailer");
						extentLoggerFail("Trailer",
								"Content playback is not initiated for the guest user post tapping on premium content which is having trailer");
					}
					Back(1);
				}
				if (userType.equals("NonSubscribedUser")) {
					if (verifyElementIsNotDisplayed(AMDShowsScreen.objSubscribeNowlink)) {
						logger.info(
								"Content playback is initiated  tapping on premium content which is having trailer");
						extentLogger("Trailer",
								"Content playback is initiated  tapping on premium content which is having trailer");
					} else {
						logger.info(
								"Content playback is not initiated  tapping on premium content which is having trailer");
						extentLoggerFail("Trailer",
								"Content playback is not initiated  tapping on premium content which is having trailer");
					}
					Back(1);
				}
			} else {
				logger.info("Content playback is initiated for the SubscribedUser post tapping on Premium Content");
				extentLogger("Trailer",
						"Content playback is initiated for the SubscribedUser post tapping on Premium Content");
			}
			Back(1);
		}

		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Continue Watching"), AMDHomePage.objCarouselConetentCard,
				"Continue watching tray", "MastheadCarousel", userType, "Shows");
		findingTrayInscreen(6, AMDHomePage.objTrayTitle("Before"), AMDHomePage.objCarouselConetentCard,
				"Before TV tray", "MastheadCarousel", userType, "Shows");
		findingTrayInscreen(3, AMDHomePage.objTrayTitle("Trending Shows"), AMDHomePage.objCarouselConetentCard,
				"Trending shows tray", "MastheadCarousel", userType, "Shows");
	}

	public void verifyCarouselCollectionListingscreen(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify user navigated to collection Listing screen post tapping on any collection available on carousel");
		System.out.println(
				"Verify user navigated to collection Listing screen post tapping on any collection available on carousel");
		if (userType.equals("Guser")) {
			selectContentLang_MoreMenu("Hindi");
			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		}
		waitTime(1000);

		click(AMDNewsPage.objCarouselCollectionContent, "Carousel content");
		verifyElementPresent(AMDNewsPage.objListingScreen, "Screen Header");
		logger.info("Listing Collection Screen is displayed post tapping on any collection available on corousel");
		extent.extentLogger("Listing Collection screen",
				"Listing Collection Screen is displayed post tapping on any collection available on corousel");
		verifyElementPresent(AMDLoginScreen.objBackBtn, "Back Button");
		click(AMDLoginScreen.objBackBtn, "Back Button");
		if (userType.equals("Guest")) {
			selectContentLang_MoreMenu("Hindi");
			verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		}
	}

	public void verifyConsumptionScreen(String userType) throws Exception {
		extent.HeaderChildNode(
				"Verify user navigated to consumption screen post tapping on any where on the banner section");
		System.out
				.println("Verify user navigated to consumption scree post tapping on any where on the banner section");
		PartialSwipe("UP", 1);
		verifyElementPresentAndClick(AMDShowsScreen.objcontentCard, "Banner card");
		waitTime(6000);

		if (verifyElementExist(AMDShowsScreen.objPlayer, "Player")) {
			logger.info("User is navigated to Consumption Screen");
			extent.extentLogger("Consumption Screen", "User is navigated to Consumption Screen");

		} else {
			logger.info("User is not navigated to Consumption Screen");
			extent.extentLoggerFail("Consumption Screen", "User is not navigated to Consumption Screen");
		}

		Back(1);
		extent.HeaderChildNode("Verify Watch Next tray is available on Shows screen");
		System.out.println("\nVerify Watch Nest Tray is available on Shows screen");
		PartialSwipe("UP", 1);

		if (verifyElementExist(AMDShowsScreen.objWatchNextTray, "Watch Next Tray")) {
			logger.info("Watch Next tray is displayed in Shows landing screen");
			extent.extentLogger("Shows Screen", "Watch Next tray is displayed in Shows landing screen");
		} else {
			logger.info("Watch Next tray is not displayed in Shows landing screen");
			extent.extentLoggerFail("Shows Screen", "Watch Next tray is not displayed in Shows landing screen");
		}
	}

	public void verifyConsumptionScreenOfBeforeTVContent(String userType) throws Exception {
		extent.HeaderChildNode("Verify content Playback in Shows Before TV");
		System.out.println("\nVerify content Playback in Shows Before TV");

		// Selecting SHOWS tab from Top Navigation
		verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		waitTime(6000);

		for (int i = 1; i < 6; i++) {
			if (!(verifyIsElementDisplayed(AMDHomePage.objBeforeTVTray))) {
				Swipe("UP", 1);
				// break;
			} else {
				waitTime(5000);
//				String beforeTVtrayName = findElement(AMDGenericObjects.objTrayTitle("Before TV")).getText();
//				click(AMDGenericObjects.objViewAllBtn(beforeTVtrayName), "View All_Before TV Show");
				click(AMDHomePage.objBeforeTVTray, "BeforeTV tray");
				waitTime(4000);
				click(AMDShowsScreen.objBeforeTVContent1, "BeforeTV content");
				waitTime(5000);
				if (verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe page")) {
					Back(1);
				}

				if (userType.equals("Guest")) {

					if (verifyElementExist(AMDShowsScreen.objLoginLink, "Login link")) {
						logger.info(
								"User navigated to consumption screen with login link on the player tapping on Before TV Content");
						extent.extentLogger("Consumption Screen",
								"User navigated to consumption screen with login link on the player tapping on Before TV Content");
					} else {
						logger.info(
								"User not navigated to consumption screen with login link on the player tapping on Before TV Content");
						extent.extentLoggerFail("Consumption Screen",
								"User not navigated to consumption screen with login link on the player tapping on Before TV Content");
					}
				} else if (userType.equals("NonSubscribedUser")) {
					if (verifyElementExist(AMDShowsScreen.objSubscribeNowlink, "Subscribe Now link")) {
						logger.info(
								"User navigated to consumption screen with Subscribe Now link on the player tapping on Before TV Content");
						extent.extentLogger("Consumption Screen",
								"User navigated to consumption screen with Subscribe Now link on the player tapping on Before TV Content");
					} else {
						logger.info(
								"User not navigated to consumption screen with Subscribe Now link on the player tapping on Before TV Content");
						extent.extentLoggerFail("Consumption Screen",
								"User not navigated to consumption screen with Subscribe Now link on the player tapping on Before TV Content");
					}
				} else {
					logger.info(
							"Content playback is initiated for the subscribed user on tapping any Before Tv Content");
					extent.extentLogger("Consumption Screen",
							"Content playback is initiated for the subscribed user on tapping any Before Tv Content");
				}
				break;
			}
		}
//      verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
//		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		Back(2);
	}

	@SuppressWarnings("unused")
	public String ShowsScreenValidationwithApiData(String userType) throws Exception {
		extent.HeaderChildNode("Shows Premium Content validation with API Data");
		// verifyElementPresentAndClick(AMDHomePage.objShowsTab, "Shows Tab");
		Response resp = ResponseInstance.getResponseForApplicasterPages(userType, "tvshows");
		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		String CarouselTitle = null;
		boolean Carousel = false;
		for (int i = 0; i < 7; i++) {

			String businessType = resp.jsonPath().getString("buckets[" + i + "].items");
			if (businessType.equalsIgnoreCase("premium_downloadable")) {
				Carousel = true;
				CarouselTitle = resp.jsonPath().getString("buckets[" + i + "].items[" + i + "].title");
				System.out.println(CarouselTitle);
				break;
			}
		}
		return CarouselTitle;
	}

	/**
	 * Author : Manasa
	 */
	public void upcomingSectionValidation() throws Exception {
		extent.HeaderChildNode("Upcoming Screen Validation");
		// waitTime(5000);
		waitForElementDisplayed(AMDHomePage.objHomeTab, 10);
		verifyElementPresentAndClick(AMDHomePage.objUpcoming, "Upcoming tab");

		WebElement element = findElement(AMDHomePage.objUpcoming);
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		Dimension size = getDriver().manage().window().getSize();
		if (middleX == Integer.valueOf((size.width) / 2)) {
			logger.info("Upcoming text is displayed at the center of the screen");
			extent.extentLogger("Title", "Upcoming text is displayed at center of the screen");
		} else {
			logger.error("Upcoming text is not displayed at center of the screen");
			extent.extentLoggerFail("Title", "Upcoming text is not displayed at center of the screen");
		}

		WebElement searchIcon = findElement(AMDHomePage.objSearchBtn);
		int searchIconRightX = searchIcon.getLocation().getX();
		System.out.println(searchIconRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;
		System.out.println(sizeee);

		if (searchIconRightX >= sizeee) {
			logger.info("Search icon is displayed at top right of the screen");
			extent.extentLogger("Search icon", "Search icon is displayed at top right of the screen");
		} else {
			logger.error("Search icon is not displayed at top right of the screen");
			extent.extentLoggerFail("Search icon", "Search icon is not displayed at top right of the screen");
		}
		Response resp = ResponseInstance.getResponseForUpcomingPage();

		String titleWithTrailer = resp.jsonPath().getString("buckets[0].items[0].original_title");
		System.out.println("API Title " + titleWithTrailer);

		verifyElementPresentAndClick(AMDUpcomingPage.objContentCard1, "Content Card");

		String titleConsumptionScreen = getText(AMDUpcomingPage.objContentCardTitle);
		System.out.println(titleConsumptionScreen);

		if (titleConsumptionScreen.contains(titleWithTrailer)) {
			logger.info("Navigated to appropriate consumption screen on tapping anywhere on any content card");
			extent.extentLogger("Title",
					"Navigated to appropriate consumption screen on tapping anywhere on any content card");
			logger.info("Trailer/Teaser playback is played");
			extent.extentLogger("Trailer/Teaser", "Trailer/Teaser playback is played");

		} else {
			logger.info("Not navigated to appropriate consumption screen on tapping anywhere on any content card");
			extent.extentLoggerFail("Title",
					"Not navigated to appropriate consumption screen on tapping anywhere on any content card");
			logger.info("Trailer/Teaser playback is not played");
			extent.extentLoggerFail("Trailer/Teaser", "Trailer/Teaser playback is not played");
		}

		verifyElementExist(AMDMoreMenu.objDownloadIcon, "Download icon");

		Back(1);

		verifyElementPresentAndClick(AMDUpcomingPage.objContentCardTitle, "Metadata");

		if (titleConsumptionScreen.contains(titleWithTrailer)) {
			logger.info("Navigated to appropriate consumption screen on tapping anywhere on the metadata");
			extent.extentLogger("Title",
					"Navigated to appropriate consumption screen on tapping anywhere on the metadata");
		} else {
			logger.info("Not navigated to appropriate consumption screen on tapping anywhere on the metadata");
			extent.extentLoggerFail("Title",
					"Not navigated to appropriate consumption screen on tapping anywhere on the metadata");
		}

		Back(1);

		verifyElementPresentAndClick(AMDHomePage.objSearchBtn, "Search icon");

		if (verifyElementExist(AMDSearchScreen.objMicrophoneIcon, "Microphone Icon")) {
			logger.info("Search landing screen is displayed after denying audio permission");
			extent.extentLogger("Search landing screen",
					"Search landing screen is displayed after denying audio permission");
		} else {
			logger.info("Search landing screen is not displayed after denying audio permission");
			extent.extentLogger("Search landing screen",
					"Search landing screen is not displayed after denying audio permission");
		}
		Back(1);
	}

	public void moreSectionValidation() throws Exception {
		extent.HeaderChildNode("More Screen Validation");
		System.out.println("\nMore Section Validation");

		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi disable");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}

		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");

		verifyElementExist(AMDOfflineScreen.objYouAreOffline, "You are Offline");
		verifyElementExist(AMDOfflineScreen.objDescription, "Please connect to the internet and try again");
		verifyElementExist(AMDOfflineScreen.objTryAgain, "Try Again");
		verifyElementExist(AMDOfflineScreen.objGoToDownloads, "Go to Downloads");

		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi enable");
		if (getOEMName.equalsIgnoreCase("Sony")) {
			Wifi_TurnOFFnON();
		}
		waitTime(5000);
		verifyElementPresentAndClick(AMDOfflineScreen.objTryAgain, "Try Again");
		waitTime(2000);
		Swipe("DOWN", 1);
		verifyElementExist(AMDMoreMenu.objProfile, "Profile icon");
		verifyElementExist(AMDMoreMenu.objBuySubscription, "Buy Subscription option");
		verifyElementExist(AMDMoreMenu.objMySubscription, "My Subscription option");
		verifyElementExist(AMDMoreMenu.objMyTransactions, "My Transactions option");
		verifyElementExist(AMDMoreMenu.objWatchlist, "Watchlist option");
		verifyElementExist(AMDMoreMenu.objMyRemainders, "My Reminders option");
		verifyElementExist(AMDMoreMenu.objHaveaPrepaidCode, "Have a prepaid code option");
		Swipe("UP", 1);
		verifyElementExist(AMDMoreMenu.objSettings, "Settings option");
		verifyElementExist(AMDMoreMenu.objInviteAFriend, "Invite a Friend option");
		verifyElementExist(AMDMoreMenu.objAboutUs, "About Us option");
		verifyElementExist(AMDMoreMenu.objHelpCentre, "Help Centre option");
		Swipe("UP", 1);
		verifyElementExist(AMDMoreMenu.objTermsOfUse, "Terms of Use");
		verifyElementExist(AMDMoreMenu.objPrivacyPolicy, "Privacy policy");
		verifyElementExist(AMDMoreMenu.objBuildVersion, "Build Version");
	}

	@SuppressWarnings("deprecation")
	public void parentalPinValidation(String userType, String searchKeyword) throws Exception {

		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Parental Pin Validation");
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSettingsScreen.objDownloadOverWifiToggle, "Wifi Off toggle");
			Back(1);
			waitTime(3000);
			Back(1);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword + "\n", "Search bar");
			waitTime(2000);
			hideKeyboard();
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			verifyElementPresentAndClick(AMDMoreMenu.objSearchResult(searchKeyword), "Search result");

			verifyElementPresentAndClick(AMDMoreMenu.objDownloadIcon, "Download icon");
			verifyElementPresentAndClick(AMDMoreMenu.objDataSaver, "Data Saver option");
			verifyElementPresentAndClick(AMDMoreMenu.objStartDownload, "Start Download");

			String wifi = "";
			String cmd = "/usr/local/bin/adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"";
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			wifi = br.readLine();
			logger.info("Wifi status :: " + wifi.toString());

			if (wifi.equalsIgnoreCase("Wi-Fi is enabled")) {
				logger.info("Content is downloading on Wifi network");
				extent.extentLogger("Download", "Content is downloading on Wifi network");
			} else {
				logger.info("Content is not downloading on Wifi network");
				extent.extentLoggerFail("Download", "Content is not downloading on Wifi network");
			}
			Back(1);

			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");
			String password = "";
			if (userType.equals("NonSubscribedUser")) {
				password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);

			String state = getText(AMDMoreMenu.objNoRestriction);
			System.out.println(state);
			if (state.equalsIgnoreCase("No Restriction")) {
				logger.info(state + " is selected by default");
				extent.extentLogger("Parental Pin", state + " is selected by default");

			} else {
				logger.info(state + " is not selected by default");
				extent.extentLoggerFail("Parental Pin", state + " is not selected by default");

			}

			verifyElementPresentAndClick(AMDMoreMenu.objRestrictAllContent, "Restrict All Content option");
			verifyElementPresentAndClick(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);

			verifyElementExist(AMDMoreMenu.objSetPin, "Set Pin");
			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			waitTime(4000);
			verifyElementPresentAndClick(AMDMoreMenu.objSetPinContinueBtn, "Continue Button");
			waitTime(2000);

			verifyElementPresentAndClick(AMDMoreMenu.objParentalLockDone, "Done Button");
			Back(1);
			waitTime(3000);
			Back(1);

			verifyElementPresentAndClick(AMDHomePage.objDownload, "Downloads tab");
			verifyElementPresentAndClick(AMDDownloadPage.objvideostab, "Videos tab");
			waitForElementDisplayed(AMDMoreMenu.objDownloadDoneIcon, 20);
			verifyElementPresentAndClick(AMDDownloadPage.objDownloadedContent, "Downloaded Content");
			verifyElementPresentAndClick(AMDDownloadPage.objPlayDownloadedContent, "Play Downloaded Content");

			waitTime(2000);
			Back(1);
			verifyElementPresentAndClick(AMDDownloadPage.objEnterPinCTA, "Enter Pin CTA");

			type(AMDMoreMenu.objParentalLockPin1, "1", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin2, "2", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin3, "3", "ParentalLockPin");
			hideKeyboard();
			type(AMDMoreMenu.objParentalLockPin4, "4", "ParentalLockPin");
			hideKeyboard();
			click(AMDMoreMenu.objSetPinContinueBtn, "Continue button");
			waitTime(5000);
			Back(1);
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");
			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(5000);
			Swipe("UP", 1);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalControl, "Parental Control");
			verifyElementExist(AMDMoreMenu.objPasswordField, "Password field");

			if (userType.equals("NonSubscribedUser")) {
				password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SettingsNonsubscribedPassword");
			} else if (userType.equals("SubscribedUser")) {
				password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SettingsSubscribedPassword");
			}
			click(AMDMoreMenu.objPasswordField, "Password field");
			getDriver().getKeyboard().sendKeys(password);

			hideKeyboard();
			if (getOEMName.contains("vivo")) {
				hidePwdKeyboard();
			}
			click(AMDMoreMenu.objPasswordContinueBtn, "Continue button");
			waitTime(2000);
			verifyElementPresentAndClick(AMDMoreMenu.objNoRestriction, "No Restriction");
			verifyElementPresentAndClick(AMDMoreMenu.objContinueBtn, "Continue Button");
			waitTime(2000);
			verifyElementPresentAndClick(AMDMoreMenu.objParentalLockDone, "Done Button");
			// Back(1);
		}
	}

	/**
	 * Author : Vinay Module : Premium tab screen validations
	 * 
	 * @param UserType
	 */

	/** ===========Premium tab screen validations=========== */

	public void PremiumTabScreen(String UserType) throws Exception {

		extent.HeaderChildNode("Verifying Premium tab screen");
		System.out.println("Navigated to Premium page");
		// Verify user is navigated to Premium tab
		click(AMDHomePage.objPremiumTab, "Premium tab");
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase("Premium")) {
			extent.extentLogger("Verify user is navigated to Premium tab",
					"User is navigated to Premium tab on clicking premium tab");
			logger.info("User is navigated to Premium tab on clicking premium tab");
		} else {
			extent.extentLoggerFail("Verify user is navigated to Premium tab",
					"Failed to navigate to Premium tab on clicking premium tab");
			logger.info("Failed to navigate to Premium tab on clicking premium tab");
		}

		// Verify Subscribe icon is displayed
		// verifyElementPresent(AMDHomePage.objSubscribeIcon, "Subscribe icon in premium
		// tab");
		if (!(UserType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("subscribe icon is dislayed");
				extent.extentLogger("Subscribe icon", "subscribe icon is dislayed");
			} else {
				logger.info("subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "subscribe icon is not dislayed");
			}
		}
//		else {
//			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
//				logger.info("subscribe icon is dislayed");
//				extent.extentLoggerFail("Subscribe icon", "subscribe icon is dislayed");
//			} else {
//				logger.info("subscribe icon is not dislayed");
//				extent.extentLogger("Subscribe icon", "subscribe icon is not dislayed");
//			}
//		}

		// Verify continue watching tray is not displayed for guest user
		/*
		 * if(UserType.equals("Guest")) {
		 * if(verifyElementExist(AMDHomePage.objContinueWatchingTray,
		 * "Continue Watching") == false) {
		 * extent.extentLogger("Verify continue watching tray ",
		 * "Continue Watching tray is not displayed for guest user");
		 * logger.info("Continue Watching tray is not displayed for guest user");
		 * 
		 * }else { extent.extentLoggerFail("Verify continue watching tray ",
		 * "Continue Watching tray is displayed for guest user");
		 * logger.info("Continue Watching tray is displayed for guest user"); } }
		 */
		// Verify Trending now tray is displayed
		// verifyElementPresent(AMDHomePage.objTrendingNowTray, "Trending Now tray");
		carouselValidation(UserType, "Premium", "");
	}

	/** ========Kids tab validations======== */
	public void KidsTabScreen(String UserType) throws Exception {

		extent.HeaderChildNode("Verifying Kids tab screen");
		// Selecting Marathi language as content language
		selectContentLang_MoreMenu2("Marathi");
		// selectContentLang_MoreMenu("Marathi");

		// Verify user is navigated to Premium tab
		click(AMDHomePage.objKidsTab, "Kids tab");
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase("Kids")) {
			extent.extentLogger("Verify user is navigated to Kids tab",
					"User is navigated to Kids tab on clicking Kids tab");
			logger.info("User is navigated to Kids tab on clicking Kids tab");
		} else {
			extent.extentLoggerFail("Verify user is navigated to Kids tab",
					"Failed to navigate to Kids tab on clicking Kids tab");
			logger.info("Failed to navigate to Kids tab on clicking Kids tab");
		}

		// Verify Subscribe icon is displayed
		verifyElementPresent(AMDHomePage.objSubscribeIcon, "Subscribe icon in Kids tab");
		// Verify Trending on ZEE5 tray is displayed
		// verifyElementPresent(AMDHomePage.objTrendingOnZee5Tray, "Trending on Zee5");
		// Verify Play icon is displayed
		carouselValidation(UserType, "Kids", "");
		ResetSettings();
	}

	public void ResetSettings() throws Exception {
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objResetDefault, "Reset settings to Default");
		click(AMDMoreMenu.objYesBtnResetDefault, "Yes button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	/**
	 * Author : Bindu Module : News Landing Page
	 */
	public void verifyNewsLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify user navigates to News screen");
		System.out.println("\nVerify User navigated to News Screen");

		// Navigate to required page from top navigation
		waitForElementDisplayed(AMDHomePage.objHomeTab, 2000);
		SelectTopNavigationTab("News");

		getText(AMDHomePage.objSelectedTab);
		String getPropertyValue = getAttributValue("enabled", AMDHomePage.objNewsTab);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			logger.info(
					"user is able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
			extent.extentLogger("Shows Tab",
					"user is able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
		} else {
			logger.info(
					"user is not able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
			extent.extentLoggerFail("News Tab",
					"user is not able to navigate to News screen by tapping on News tab displayed in the top navigation bar");
		}

		if ((userType.equalsIgnoreCase("Guest")) | (userType.equalsIgnoreCase("NonSubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed on the header of the News Landing Screen");
				extent.extentLogger("Subscribe icon",
						"Subscribe icon is dislayed on the header of the News Landing Screen");
			} else {
				logger.info("Subscribe icon is not dislayed on the header of the News Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the News Landing Screen");
			}
		} else {

			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is not dislayed on the header of the News Landing Screen");
				extent.extentLogger("Subscribe icon",
						"Subscribe icon is not dislayed on the header of the News Landing Screen");

			} else {
				logger.info("Subscribe icon is dislayed on the header of the News Landing Screen");
				extent.extentLoggerFail("Subscribe icon",
						"Subscribe icon is dislayed on the header of the News Landing Screen");
			}
		}
		carouselValidationforShowsAndNews(userType, "News");
	}

	public void carouselValidationforShowsAndNews(String UserType, String tabName) throws Exception {
		extent.HeaderChildNode("Carousel validation");
		System.out.println("\nCarousel validation");

		waitForElementDisplayed(AMDHomePage.objCarouselDots, 10);
		if (!UserType.equalsIgnoreCase("SubscribedUser")) {
			waitTime(10000);
		}

		if ((verifyElementIsNotDisplayed(AMDHomePage.objBannerAd))) {
			verifyElementPresent(AMDHomePage.objCarouselUnitwhenNomastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		} else {
			verifyElementPresent(AMDHomePage.objCarouselUnitwithmastHeadAdbanner,
					"Carousel unit as first unit on " + tabName + " screen");
		}

		// Validating Carousel manual swipe
		String width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);

		String bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];
		// System.out.println(height);
		waitTime(4000);

		carouselCardsSwipe("RIGHT", 1, width, height);
		String Carouseltitle1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle1);
		extentLogger("Carousel Title", Carouseltitle1);

		carouselCardsSwipe("LEFT", 1, width, height);
		String Carouseltitle2 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle2);
		extentLogger("Carousel Title", Carouseltitle2);

		carouselCardsSwipe("RIGHT", 1, width, height);
		String Carouseltitle3 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(Carouseltitle3);
		extentLogger("Carousel Title", Carouseltitle3);

		// Verifying if the carousel banneer can be manually swipe
		if (!Carouseltitle1.equalsIgnoreCase(Carouseltitle2)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLogger("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else if (Carouseltitle3.equalsIgnoreCase(Carouseltitle1)) {
			logger.info(UserType + " is able to manually swipe banners from right to left or vice versa.");
			extent.extentLogger("Carousel swipe",
					UserType + " is able to manually swipe banners from right to left or vice versa.");
		} else {
			logger.info(UserType + " is not able to manually swipe banners from right to left or vice versa.");
			extent.extentLoggerFail("Carousel swipe",
					UserType + " is NOT able to manually swipe banners from right to left or vice versa.");
		}

		// Validating Pagination dot, Play icon and GetPremium on Carousel
		int noofCarouselContents = findElements(AMDHomePage.objCarouselDots).size();
		for (int i = 0; i < noofCarouselContents; i++) {
			logger.info(getText(AMDHomePage.objCarouselTitle1));

			// To Verify PAGINATION DOT displayed
			if (checkElementExist(AMDHomePage.objCarouselDots, "Pagination dot")) {
				logger.info("Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLogger("Pagination dot",
						"Pagination dot is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.info(
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Pagination dot",
						"Pagination dot is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			// To Verify PLAY ICON displayed
			if (checkElementExist(AMDHomePage.objPlayBtn, "Play icon")) {
				logger.info("Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLogger("Play icon",
						"Play icon is displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			} else {
				logger.info("Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
				extent.extentLoggerFail("Play icon",
						"Play icon is NOT displayed for carousel card: " + getText(AMDHomePage.objCarouselTitle1));
			}
			carouselCardsSwipe("LEFT", 1, width, height);
		}

		if ((tabName.equals("Kids")) | (tabName.equals("Music"))) {
			extent.extentLogger("Verify Get Premium tag", "Get Premium tag is not configured for " + tabName + " tab");
			logger.info("Get Premium tag is not configured for " + tabName + " tab");
		}
//		else {
//			if ((UserType.equalsIgnoreCase("Guest")) | (UserType.equalsIgnoreCase("NonSubscribedUser"))) {
//				for (int i = 0; i < noofCarouselContents; i++) {
//
//					logger.info(getText(AMDHomePage.objCarouselTitle1));
//					verifyElementExist(AMDHomePage.objGetPremium, "GetPremium tag");
//					carouselSwipe("LEFT", 1, width, height);
//				}
//				click(AMDHomePage.objGetPremium, "GetPremium tag");
//				verifyElementPresent(AMDSubscibeScreen.objSubscribeHeader, "Subscription screen");
//				Back(1);
//			}
		else {
			for (int i = 0; i < noofCarouselContents; i++) {

				System.out.println(getText(AMDHomePage.objCarouselTitle1));
				if (verifyElementIsNotDisplayed(AMDHomePage.objGetPremium)) {
					logger.info("Get Premium tag is not displayed");
					extent.extentLogger("GetPremium tag", "Get Premium tag is not displayed");
				} else {
					logger.info("Get Premium tag is displayed");
					extent.extentLoggerFail("GetPremium tag", "Get Premium tag is displayed");
				}
				carouselCardsSwipe("LEFT", 1, width, height);
			}
		}

		// navigation to consumption screen of selected content
		String CarouselTitle = getText(AMDHomePage.objCarouselPlayIconContentCard);
		click(AMDHomePage.objCarouselPlayIconContentCard, "Carousel content");

		if (verifyElementExist(AMDHomePage.objSubscribePopup, "Subscribe Popup")) {
			Back(1);
		}
		waitTime(5000);

		verifyElementPresent(AMDHomePage.objConsumptionScreenTitle, "Consumption screen");
		String consumptionScreenTitle = getText(AMDHomePage.objConsumptionScreenTitle);
		System.out.println("\n" + CarouselTitle);
		System.out.println(consumptionScreenTitle);

		if (CarouselTitle.equalsIgnoreCase(consumptionScreenTitle)) {
			logger.info("Consumption Screen is displayed for the selected content");
			extent.extentLogger("Consumption screen", "Consumption Screen is displayed for the selected content");
		} else {
			logger.info("Consumption Screen is not displayed for the selected content");
			extent.extentLoggerFail("Consumption screen",
					"Consumption Screen is not displayed for the selected content");
		}
		Back(1);

		// Validating Carousel Auto scroll
		String title1 = getText(AMDHomePage.objCarouselTitle1);
		logger.info(title1);
		extentLogger("Carousel Title", title1);
		waitTime(4000);
		String title2 = getText(AMDHomePage.objCarouselTitle2);
		logger.info(title2);
		extentLogger("Carousel Title", title2);
		waitTime(4000);
		String title3 = getText(AMDHomePage.objCarouselTitle3);
		logger.info(title3);
		extentLogger("Carousel Title", title3);

		if (!(title1.equalsIgnoreCase(title2))) {
			if (!(title2.equalsIgnoreCase(title3))) {
				logger.info(
						"Banners available in feature carousel unit rotates from right to left at a fixed time interval");
				extentLogger("Carousel unit Autorotation",
						"Banners available in feature carousel unit rotate from right to left at a fixed time interval");
			} else {
				logger.info(
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
				extentLoggerFail("Carousel unit Autorotation",
						"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			}
		} else {
			logger.info(
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
			extentLoggerFail("Carousel unit Autorotation",
					"Banners available in feature carousel unit are not rotating from right to left at a fixed time interval");
		}
	}

	public void verifyTraysInNewsScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify Trays Present in News Landing Screen");
		System.out.println("Verify Trays Present in News Landing Screen");
		waitTime(6000);
		if (userType.equals("Guest")) {
			selectContentLang_MoreMenu("Hindi");
		}
		click(AMDHomePage.objPremiumTab, "Premium tab");
		verifyElementPresentAndClick(AMDHomePage.objNewsTab, "News Tab");

		findingTrayInscreen(10, AMDHomePage.objTrayTitle("Today's Headlines"), AMDHomePage.objCarouselConetentCard,
				"Today's Headlines tray", "MastheadCarousel", userType, "News");
		findingTrayInscreen(10, AMDHomePage.objTrayTitle("Entertainment News"), AMDHomePage.objCarouselConetentCard,
				"Entertainment News tray", "MastheadCarousel", userType, "News");
		findingTrayInscreen(4, AMDHomePage.objTrayTitle("Live News"), AMDHomePage.objCarouselConetentCard,
				"Live News tray", "MastheadCarousel", userType, "News");
		findingTrayInscreen(7, AMDHomePage.objTrayTitle("Top Stories"), AMDHomePage.objCarouselConetentCard,
				"Top Stories tray", "MastheadCarousel", userType, "News");

		if (userType.equals("Guest")) {
			deselectContentLang_MoreMenuAndSelectDefaultLanguage("Hindi");
		}
		click(AMDHomePage.objPremiumTab, "Premium tab");
	}

	public void verifyListingCollectionScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verify user navigated to Listing collection Screen");
		System.out.println("Verify user navigated to Listing collection Screen");

		if (userType.equalsIgnoreCase("Guest")) {
			verifyElementPresentAndClick(AMDHomePage.objNewsTab, "News Tab");
			waitTime(6000);
			PartialSwipe("UP", 1);
			verifyElementPresent(AMDNewsPage.objRightArrowBtn, "Right arrow");
			click(AMDNewsPage.objRightArrowBtn, "Right arrow");
			waitTime(4000);
			if (verifyElementPresent(AMDNewsPage.objListingScreen, "Screen Header")) {
				logger.info("Listing Collection Screen is displayed by clicking on right arrow for the trays");
				extent.extentLogger("Listing Collection screen",
						"Listing Collection Screen is displayed by clicking on right arrow for the trays");
			} else {
				logger.info("Listing Collection Screen is not displayed by clicking on right arrow for the trays");
				extent.extentLoggerFail("Listing Collection screen",
						"Listing Collection Screen is not displayed by clicking on right arrow for the trays");
			}
			if (!(verifyIsElementDisplayed(AMDNewsPage.objMetaData))) {
				logger.info("MetaData like Title,Year,Duration is not displayed in listing collection screen");
				extent.extentLogger("Listing Collection screen",
						"MetaData like Title,Year,Duration is not displayed in listing collection screen");
			} else {
				logger.info("MetaData like Title,Year,Duration is displayed in listing collection screen");
				extent.extentLoggerFail("Listing Collection screen",
						"MetaData like Title,Year,Duration is displayed in listing collection screen");
			}
			Swipe("UP", 1);
			if (verifyElementExist(AMDNewsPage.objNextContentImg, "Next Content")) {
				logger.info(
						"User can able to swipe the screen to view the next content in the Collection Listing Screen");
				extent.extentLogger("Listing Collection screen",
						"User can able to swipe the screen to view the next content");
			} else {
				logger.info("No content present on the Collection Listing Screen after swiping the screen");
				extent.extentLoggerFail("Listing Collection screen",
						"No content present on the Collection Listing Screen after swiping the screen");
			}
			Swipe("DOWN", 1);
			verifyElementPresent(AMDNewsPage.objThumbnailImg1, "Thumbnail Image");
			// waitTime(6000);
			click(AMDNewsPage.objThumbnailImg1, "Thumbnail Image");
			waitTime(2000);
			if (verifyElementPresent(AMDNewsPage.objConsumptionScreenTitle, "Consumption Screen Title")) {
				logger.info(
						"User can able to Tap on Thumbnail imagee and user navigated to consumption screen tapping on thumbnail image in listing screen");
				extent.extentLogger("Consumption Screen",
						"User can able to Tap on Thumbnail imagee and user navigated to consumption screen tapping on thumbnail image in listing screen");
			} else {
				logger.info(
						"User cannot able to Tap on Thumbnail imagee and user not navigated to consumption screen tapping on thumbnail image in listing screen");
				extent.extentLoggerFail("Consumption Screen",
						"User cannot able to Tap on Thumbnail imagee and user not navigated to consumption screen tapping on thumbnail image in listing screen");
			}
			Back(1);
			verifyElementPresent(AMDLoginScreen.objBackBtn, "Back Button");
			click(AMDLoginScreen.objBackBtn, "Back Button");
			waitTime(2000);
			if (verifyElementPresent(AMDHomePage.objNewsTab, "News Tab")) {
				logger.info("User navigated to News Landing screen tapping on back icon available on listing screen");
				extent.extentLogger("Listing Screen",
						"User navigated to News Landing screen tapping on back icon available on listing screen");
			} else {
				logger.info(
						"User not navigated to News Landing screen tapping on back icon available on listing screen");
				extent.extentLoggerFail("Listing Screen",
						"User not navigated to News Landing screen tapping on back icon available on listing screen");
			}
		} else {
			logger.info("Listing Collection Screen validation is not applicable for " + userType);
			extent.extentLogger("Listing Screen",
					"Listing Collection Screen validation is not applicable for " + userType);
		}
	}

	/**
	 * Author : Sushma Module : Movies
	 */
	public void moviesLandingScreen(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Navigating to Home screen and verifing the Subscribe icon");
		System.out.println("Movies Landing screen and checking the Subscribe icon");

		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDHomePage.objMoviesTab, 2000);

		// Selecting MOVIES tab from Top Navigation
		SelectTopNavigationTab(tabName);

		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase(tabName)) {
			logger.info(userType + " is able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLogger(tabName + " Tab", userType + " is able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		} else {
			logger.info(userType + " is not able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerFail(tabName + " Tab", userType + " is not able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		}
		waitTime(10000);
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed");
				extent.extentLogger("Subscribe icon", "Subscribe icon is dislayed");
			} else {
				logger.info("Subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not dislayed");
			}
		} else {
			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("subscribe icon is not dislayed");
				extent.extentLogger("Subscribe icon", "subscribe icon is not dislayed");
			} else {
				logger.info("subscribe icon is dislayed");
				extent.extentLoggerFail("Subscribe icon", "subscribe icon is dislayed");
			}
		}

		waitTime(3000);
		String courselContentTitle = carouselValidationWithApi(userType, "movies");
		carouselValidation(userType, tabName, courselContentTitle);
		verifyElementPresentAndClick(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");

		waitTime(5000);
		if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
			if (!(verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen))) {
				logger.info(
						"Content playback is initiated for the user post tapping on premium content which is having trailer");
				extentLogger("Trailer",
						"Content playback is initiated for the user post tapping on premium content which is having trailer");
			} else {
				logger.info(
						"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				extentLoggerFail("Trailer",
						"Content playback is not initiated for the user post tapping on premium content which is having trailer");
			}
			Back(1);
		} else {
			if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
				logger.info(
						"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				extentLogger("Trailer",
						"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
			} else {
				logger.info(
						"Content playback is initiated for the  user post tapping on premium content which is not having trailer");
				extentLoggerFail("Trailer",
						"Content playback is initiated for the  user post tapping on premium content which is not having trailer");
			}
			Back(1);
		}
		extent.HeaderChildNode("Verifing the availability of tray in the screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Continue Watching"), AMDHomePage.objCarouselConetentCard,
				"Continue watching tray", "MastheadCarousel", userType, tabName);
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Trending Movies"), AMDHomePage.objCarouselConetentCard,
				"Trending Movies tray", "MastheadCarousel", userType, tabName);
	}

	/**
	 * Author : Kushal
	 */
	public void selectContentLang_MoreMenu(String planguage) throws Exception {

		click(AMDHomePage.HomeIcon, "Home button");
		click(AMDHomePage.MoreMenuIcon, "More Menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings CTA");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Settings"), "Settings Screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objContentLang, "Content language");
		verifyElementPresent(AMDGenericObjects.objScreenTitleName("Content Language"), "Content language screen");

		// ****** UnSelecting default content languages ******
		if (pUserType.contains("Guest")) {
			click(AMDOnboardingScreen.objSelectContentLang("English"), "English");
			PartialSwipe("UP", 2);
			waitTime(1000);
			click(AMDOnboardingScreen.objSelectContentLang("Kannada"), "Kannada");
			Swipe("DOWN", 1);
		}

		// ****** Selecting required language ******
		if (planguage.contains(",")) {
			Swipe("DOWN", 1);
			String[] pLanguages = planguage.split(",");
			int n = pLanguages.length;
			for (int i = 0; i < n; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (pLanguages[i].equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(pLanguages[i]),
								pLanguages[i]);
					}
				}
				PartialSwipe("UP", 1);
			}
		} else {
			outerLoop: for (int i = 1; i <= 4; i++) {
				int totalLanguages = getCount(AMDOnboardingScreen.objContentLangBtns);
				for (int j = 1; j <= totalLanguages; j++) {
					String visibleLang = getText(AMDOnboardingScreen.objgetContentLangName(j));
					if (planguage.equalsIgnoreCase(visibleLang)) {
						verifyElementPresentAndClick(AMDOnboardingScreen.objSelectContentLang(planguage), planguage);
						break outerLoop;
					}
				}
				PartialSwipe("UP", 1);
			}
		}

		waitTime(1000);
		verifyElementPresentAndClick(AMDOnboardingScreen.objContent_ContinueBtn, "Continue button");
		waitTime(1000);
//	click(AMDGenericObjects.objBackBtn, "Back button");
		Back(1);
		click(AMDHomePage.HomeIcon, "Home button");
	}

	/**
	 * Author :Manasa Module : UpComing Screen
	 */

	public void upcomingContentValidationWithAPIData() throws Exception {

		extent.HeaderChildNode("Upcoming Content Validation With API Data");
		waitForElementDisplayed(AMDHomePage.objHomeTab, 10);
		verifyElementPresentAndClick(AMDHomePage.objUpcoming, "Upcoming tab");
		Response resp = ResponseInstance.getResponseForUpcomingPage();
		List<String> apiTitle = new LinkedList<String>();
		List<String> apiMetadata = new LinkedList<String>();
		List<String> contentList = resp.jsonPath().getList("buckets[0].items");
		System.out.println(contentList.size());

		for (int i = 0; i < contentList.size(); i++) {
			String title = resp.jsonPath().getString("buckets[0].items[" + i + "].title");
			System.out.println("Show Title " + title);

			apiTitle.add(title);

			String metadata = resp.jsonPath().getString("buckets[0].items[" + i + "].description");
			System.out.println("API Metadata " + metadata);
			apiMetadata.add(metadata);

			String releaseDate = resp.jsonPath().getString("buckets[0].items[" + i + "].release_date");
			String[] releaseDateSplit = releaseDate.split("T");

			String contentName = apiTitle.get(i);
			System.out.println(contentName);

			String convertedXpath = titleToXpath(contentName);
			System.out.println(convertedXpath);

			String contentDescription = apiMetadata.get(i);

			String UIMetadata = getDriver().findElementByXPath(AMDUpcomingPage.objContentCardTitle(convertedXpath))
					.getText();

			System.out.println(UIMetadata);

			if (verifyElementExist(AMDUpcomingPage.objTitle(convertedXpath), "Show Title : " + title)) {
				logger.info("Title on the content card matches with Api data");
				extent.extentLogger("Title", "Title on the content card matches with Api data");
			} else {
				logger.info("Title on the content card does not match with Api data");
				extent.extentLoggerFail("Title", "Title on the content card does not match with Api data");
			}

			if (UIMetadata.contains(contentDescription)) {
				logger.info("Metadata on the content card matches with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card matches with Api data");
			} else {
				logger.info("Metadata on the content card does not match with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card does not match with Api data");
			}

			String genre = getDriver().findElementByXPath(AMDUpcomingPage.objContentGenre(convertedXpath)).getText();
			logger.info("Content Genre : " + genre);
			extent.extentLogger("Genre", "Content Genre : " + genre);

			String certificate = getDriver().findElementByXPath(AMDUpcomingPage.objContentCertificate(convertedXpath))
					.getText();
			logger.info("Content Certificate : " + certificate);
			extent.extentLogger("Certificate", "Content Certificate : " + certificate);

			System.out.println("Release Date " + releaseDateSplit[0]);
			logger.info("Release Date " + releaseDateSplit[0]);
			extent.extentLogger("Release Date", "Release Date " + releaseDateSplit[0]);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date upcomingReleaseDate = sdf.parse(releaseDateSplit[0]);
			Date dateToday = sdf.parse(date());

			if (upcomingReleaseDate.compareTo(dateToday) <= 0) {
				logger.info("Previously released content is listed in the upcoming section");
				extent.extentLoggerFail("Release Date",
						"Previously released content is listed in the upcoming section");

			} else {
				logger.info("Previously released content is not listed in the upcoming section");
				extent.extentLogger("Release Date",
						"Previously released content is not listed in the upcoming section");
			}
			swipeByElements(findElement(AMDUpcomingPage.objGenre), findElement(AMDHomePage.objSearchBtn));
			waitTime(3000);
		}
	}

	public String titleToXpath(String trayTitle) throws Exception {
		String xPath = null;
		StringBuffer Sbuffer1 = new StringBuffer();
		// System.out.println(trayTitle);
		if (trayTitle.contains("'")) {
			// System.out.println("Length of the title is: "+trayTitle.length());
			String[] S = trayTitle.split("'");
			// System.out.println("The size of the array is: "+S.length);
			// for(String x : S)
			// System.out.println(x);
			//
			Sbuffer1.append("//*[@text=concat(\"");
			Sbuffer1.append(S[0]);
			// System.out.println(Sbuffer1);
			for (int i = 1; i < S.length; i++) {
				Sbuffer1.append("\",\"'\",\"" + S[i]);
			}
			Sbuffer1.append("\")]");
			// System.out.println("The Xpath is:"+Sbuffer1.toString());
			xPath = Sbuffer1.toString();

		} else {
			Sbuffer1.append("//*[@text=\"" + trayTitle + "\"]");
			// System.out.println("The Xpath is:"+Sbuffer1.toString());
			xPath = Sbuffer1.toString();
		}
		return xPath;
	}

	public static String date() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		strDate = formatter.format(date);
		System.out.println(strDate);

		return strDate;
	}

	/**
	 * Author : Hitesh Module : Live TV
	 * 
	 * @throws Exception
	 */
	public void LiveTV(String UserType) throws Exception {
		extent.HeaderChildNode("Verifying Live TV Landing screen");
		System.out.println("\nVerifying Live TV Landing screen");
		// swipeByElements(findElement(AMDHomePage.objNewsTab),
		// findElement(AMDHomePage.objHomeTab));
		verifyElementPresentAndClick(AMDHomePage.objLiveTvTab, "Live Tv");
		waitTime(2000);
		if (verifyElementDisplayed(AMDLiveTVScreen.objChannelGuide)) {
			logger.info("Navigated to Live TV Screen");
			extentLogger("Navigation to Live TV", "Navigated to Live TV Screen");
		} else {
			logger.info("Unable to navigated to Live TV Screen");
			extentLoggerFail("Navigation to Live TV", "Unable to navigated to Live TV Screen");
		}

		if (UserType.equals("Guest")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		} else if (UserType.equals("NonSubscribedUser")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		}
//		else if (UserType.equals("SubscribedUser")) {
//			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
//		}
		verifyElementExist(AMDLiveTVScreen.objChannelGuide, "Channel Guide toggle option");
		click(AMDLiveTVScreen.objChannelGuide, "Channel Guide");
		if (findElement(AMDLiveTVScreen.objChannelGuide).isSelected()) {
			logger.info("Channel Guide is Selected");
			extentLogger("Verify Toogle options", "Channel Guide is Selected");
		} else {
			logger.info("Channel Guide is not Selected");
			extentLoggerFail("Verify Toogle options", "Channel Guide is not Selected");
		}
		click(AMDLiveTVScreen.onjLiveTvToggle, "Channel Guide");

		for (int i = 0; i < findElements(AMDLiveTVScreen.objContentInLiveTV).size(); i++) {
			if (verifyElementDisplayed(AMDLiveTVScreen.objImageIcon)) {
				logger.info("Image Icon is Displayed");
			} else {
				logger.info("Image Icon is Not Displayed");
			}
		}

		verifyElementPresentAndClick(AMDLiveTVScreen.objFirstContent, "Clicked on Live Tv content");
		verifyElementExist(AMDLiveTVScreen.objLiveTV, "Live Icon on Player");
		Back(1);
		verifyElementExist(AMDLiveTVScreen.objTray("FREE Channels"), "FREE Channels tray");
		verifyElementExist(AMDLiveTVScreen.objTray("Music"), "Music tray");
		PartialSwipe("UP", 1);
		verifyElementExist(AMDLiveTVScreen.objTray("News"), "News tray");

//		VerifyDuplicateTrays(AMDLiveTVScreen.objTray("FREE Channels"), "FREE Channels tray");
//		VerifyDuplicateTrays(AMDLiveTVScreen.objTray("Music"), "Music tray");
//		VerifyDuplicateTrays(AMDLiveTVScreen.objTray("News"), "News tray");
	}

	public void channelGuideScreenValidation(String UserType) throws Exception {
		extent.HeaderChildNode("Verifying Channel Guide screen in Live TV Landing screen");
		System.out.println("\nVerifying Channel Guide screen in Live TV Landing screen");
		// swipeByElements(findElement(AMDHomePage.objNewsTab),
		// findElement(AMDHomePage.objHomeTab));
		verifyElementPresentAndClick(AMDHomePage.objLiveTvTab, "Live Tv");
		verifyElementExist(AMDLiveTVScreen.objChannelGuide, "Channel Guide toggle option");
		click(AMDLiveTVScreen.objChannelGuide, "Channel Guide");
		if (findElement(AMDLiveTVScreen.objChannelGuide).isSelected()) {
			logger.info("Channel Guide is Selected");
			extentLogger("Verify Toogle options", "Channel Guide is Selected");
		} else {
			logger.info("Channel Guide is not Selected");
			extentLoggerFail("Verify Toogle options", "Channel Guide is not Selected");
		}
		if (UserType.equals("Guest")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		} else if (UserType.equals("NonSubscribedUser")) {
			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
		}
//		else if (UserType.equals("SubscribedUser")) {
//			verifyElementExist(AMDLiveTVScreen.objSubscribeIcon, "Subscribe Icon for " + UserType + " User");
//		}
		if (findElements(AMDLiveTVScreen.objChannelLogo).size() > 0) {
			logger.info("list of channels are displayed in Channel Guide screen");
			extentLogger("Channel Guide Screen", "list of channels are displayed in Channel Guide screen");
		} else {
			logger.error("list of channels are not displayed in Channel Guide screen");
			extentLoggerFail("Channel Guide Screen", "list of channels are not displayed in Channel Guide screen");
		}
		verifyElementPresentAndClick(AMDHomePage.objMusicTab, "Music Tab");
		if (findElement(AMDHomePage.objMusicTab).isSelected()) {
			logger.info("User is able to navigate to any screen from channel guide screen");
			extentLogger("Screen", "User is able to navigate to any screen from channel guide screen");
		} else {
			logger.info("User is not able to navigate to any screen from channel guide screen");
			extentLoggerFail("Screen", "User is not able to navigate to any screen from channel guide screen");
		}
	}

	public void VerifyDuplicateTrays(By tray, String trayName) {
		for (int i = 0; i < 4; i++) {
			if ((findElements(tray).size()) > 1) {
				logger.error("Duplicate tray :" + trayName);
			} else {
				logger.info("No Duplicate tray :" + trayName);
			}
			Swipe("UP", 1);
		}
		for (int i = 0; i < 4; i++) {
			Swipe("Down", 1);
		}
	}

	/**
	 * Author : Manasa Module :
	 */
	public void verifySubscriptionReminderInDownloads() throws Exception {
		extent.HeaderChildNode("Verify Subscription Reminder In Downloads");
		System.out.println("\nVerify Subscription Reminder In Downloads");
		waitTime(5000);
		verifyElementPresentAndClick(AMDHomePage.objDownload, "Downloads tab");
		String subscriptionExpiry = getText(AMDDownloadPage.objSubscriptionExpiry);
		logger.info("Subscription Expiry Message : " + subscriptionExpiry + " is displayed");
		extent.extentLogger("Subscription Expiry Message",
				"Subscription Expiry Message : " + subscriptionExpiry + " is displayed");
		verifyElementExist(AMDDownloadPage.objSubscriptionExpiryMessage, "Subscription Renewal Message");
		verifyElementPresentAndClick(AMDDownloadPage.objRemindMeLaterCTA, "Remind Me Later CTA");
//		if(verifyElementExist(AMDDownloadPage.objSubscriptionExpiryMessage, "Subscription Reminder Message")) {
//			logger.info("Subscription Reminder Message is hidden");
//			extent.extentLogger("Subscription Reminder Message","Subscription Reminder Message is hidden");
//		}else {
//			logger.info("Subscription Reminder Message is not hidden");
//			extent.extentLogger("Subscription Reminder Message","Subscription Reminder Message is not hidden");	
//		}
	}

	/**
	 * Author : Vinay Module : Music Landing screen
	 * 
	 */
	public void MusicLandingScreen(String userType) throws Exception {
		// Verify user is navigated to Music landing screen
		click(AMDHomePage.objMusicTab, "Music tab");
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		// Verify user is navigated to Premium tab
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase("Music")) {
			extent.extentLogger("Verify user is navigated to Music tab",
					"User is navigated to Music tab on clicking Music tab");
			logger.info("User is navigated to Music tab on clicking Music tab");
		} else {
			extent.extentLoggerFail("Verify user is navigated to Music tab",
					"Failed to navigate to Music tab on clicking Music tab");
			logger.info("Failed to navigate to Music tab on clicking Music tab");
		}
		// Verify Subscribe icon is displayed on Music landing screen
		// verifyElementPresent(AMDHomePage.objSubscribeIcon, "Subscribe icon in premium
		// tab");

		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is dislayed");
				extent.extentLogger("Subscribe icon", "Subscribe icon is dislayed");
			} else {
				logger.info("Subscribe icon is not dislayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not dislayed");
			}
		}
//		else {
//			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
//				logger.info("Subscribe icon is dislayed");
//				extent.extentLogger("Subscribe icon", "Subscribe icon is dislayed");
//			} else {
//				logger.info("Subscribe icon is not dislayed");
//				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not dislayed");
//			}
//		}
		// Verify ZEE5 Original Music tray is displayed
		// verifyElementPresent(AMDHomePage.objZEEOriginalMusicTray, "ZEE5 Original
		// Music tray");
		carouselValidation(userType, "Music", "");
	}

	/**
	 * Author : Manasa Module : ZeeOriginals
	 */
	public void zee5OriginalsLandingScreen(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Navigating to Zee5 Originals screen and verifing the Subscribe icon");
		System.out.println("Zee5 Originals Landing screen and verifing the subscribe icon");
//		 swipeByElements(findElement(AMDHomePage.objNewsTab),
//		 findElement(AMDHomePage.objHomeTab));
		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.objZee5OriginalsTab, tabName + " Tab");
		String activeTab = getText(AMDHomePage.objSelectedTab);
		if (activeTab.equalsIgnoreCase(tabName)) {
			logger.info("User is able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLogger(tabName + " Tab", "User is able to navigate to " + tabName + " screen by tapping on "
					+ tabName + " tab displayed in the top navigation bar");
		} else {
			logger.info("User is not able to navigate to " + tabName + " screen by tapping on " + tabName
					+ " tab displayed in the top navigation bar");
			extent.extentLoggerFail(tabName + " Tab", "User is not able to navigate to " + tabName
					+ " screen by tapping on " + tabName + " tab displayed in the top navigation bar");
		}
		waitTime(10000);
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			if (verifyElementDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is displayed");
				extent.extentLogger("Subscribe icon", "Subscribe icon is displayed");
			} else {
				logger.info("Subscribe icon is not displayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is not displayed");
			}
		} else {
			if (verifyElementIsNotDisplayed(AMDHomePage.objSubscribeTeaser)) {
				logger.info("Subscribe icon is not displayed");
				extent.extentLogger("Subscribe icon", "Subscribe icon is not displayed");

			} else {
				logger.info("Subscribe icon is displayed");
				extent.extentLoggerFail("Subscribe icon", "Subscribe icon is displayed");
			}
		}

		String courselContentTitle = zeeOriginalsCarouselValidationWithApi(userType, "zeeoriginals");
		carouselValidation(userType, tabName, courselContentTitle);
		verifyElementPresentAndClick(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");
		waitTime(5000);
		if (checkElementExist(AMDHomePage.objSubscribePopup)) {
//			Back(1);
			click(AMDGenericObjects.objPopUpDivider, "Pop Up divider");
		}
		if (verifyIsElementDisplayed(AMDHomePage.objWatchTrailerIconOnPlayerscreen)) {
			if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
				logger.info(
						"Content playback is not initiated for the user post tapping on premium content which is having trailer");
				extentLogger("Trailer",
						"Content playback is not initiated for the guest user post tapping on premium content which is having trailer");

			} else {
				logger.info(
						"Content playback is initiated for the user post tapping on premium content which is having trailer");
				extentLogger("Trailer",
						"Content playback is initiated for the user post tapping on premium content which is having trailer");
			}
			Back(1);
		} else {
			if (verifyIsElementDisplayed(AMDHomePage.objLoginButtonOnPlayerscreen)) {
				logger.info(
						"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
				extentLogger("Trailer",
						"Content playback is not initiated for the user post tapping on premium content which is not having trailer");
			} else {
				logger.info(
						"Content playback is initiated for the user post tapping on premium content which is not having trailer");
				extentLoggerFail("Trailer",
						"Content playback is initiated for the user post tapping on premium content which is not having trailer");
			}
			Back(1);
		}
		Back(1);
		extent.HeaderChildNode("Verifying the availability of trays in the screen");
		findingTrayInscreen(2, AMDHomePage.objTrayTitle("Best of ZEE5 Originals"), AMDHomePage.objCarouselConetentCard,
				"Best of ZEE5 Originals", "MastheadCarousel", userType, tabName);
		// PartialSwipe("UP", 1);
		findingTrayInscreen(25, AMDHomePage.objTrayTitle("ZEE5 Original Music"), AMDHomePage.objCarouselConetentCard,
				"ZEE5 Original Music tray", "Mastheadcarousel", userType, tabName);
	}

	public static String zeeOriginalsCarouselValidationWithApi(String userType, String pagenameforApi) {
		Response respPage = ResponseInstance.getResponseForApplicasterPages(userType, pagenameforApi);
		List<String> items = respPage.jsonPath().getList("buckets[0].items");
		logger.info("bucketsSize: " + items.size());
		String carouselContentTitle = null;
		main: for (int i = 0; i < items.size(); i++) {
			carouselContentTitle = respPage.jsonPath().getString("buckets[0].items[" + i + "].title");
			logger.info(carouselContentTitle);

			String CarouselContentBusinessType = respPage.jsonPath()
					.getString("buckets[0].items[" + i + "].business_type");
			logger.info(CarouselContentBusinessType);
			if (CarouselContentBusinessType.equalsIgnoreCase("premium_downloadable")) {
				break main;
			}
		}
		return carouselContentTitle;
	}

	/**
	 * Author : Sushma Module : Settings
	 */
	public void settings(String usertype) throws Exception {
		extent.HeaderChildNode("Settings screen validation");

		// Setting screen validation
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		if (verifyElementExist(AMDMoreMenu.objSettingsScreenTitle, "Setting screen title")) {
			logger.info("Settings screen is displayed when user taps on the Settings option from the More menu");
			extentLogger("Settings",
					"Settings screen is displayed when user taps on the Settings option from the More menu");
		} else {
			logger.info("Settings screen is not displayed when user taps on the Settings option from the More menu");
			extentLoggerFail("Settings",
					"Settings screen is not displayed when user taps on the Settings option from the More menu");
		}
		verifyElementPresent(AMDMoreMenu.objVideoStreamingMenuTitle, "Video streaming menu");
		verifyElementPresent(AMDMoreMenu.objDownloadsMenuTitle, "Downloads menu");

		String pos1 = getAttributValue("bounds", AMDMoreMenu.objDownloadsMenuTitle);
		String pos2 = null;
		PartialSwipe("UP", 1);
		pos2 = getAttributValue("bounds", AMDMoreMenu.objDownloadsMenuTitle);
		if (pos1 != pos2) {
			logger.info("Settings screen is scrollable");
			extentLogger("Swipe", "Settings screen is scrollable");
		} else {
			logger.info("Settings screen is not scrollable");
			extentLoggerFail("Swipe", "Settings screen is not scrollable");
		}

		Swipe("UP", 2);
		verifyElementPresent(AMDMoreMenu.objLanguageMenuTitle, "Languages menu");
		if (!(usertype.equalsIgnoreCase("Guest"))) {
			verifyElementPresent(AMDMoreMenu.objParentalControl, "Parental Control menu");
			verifyElementPresent(AMDSettingsScreen.objAuthenticateDevice, "Authenticate Device menu");
		}
		verifyElementPresent(AMDMoreMenu.objSearchHistroyLabel, "Search histroy Label");
		verifyElementPresent(AMDMoreMenu.objResetSettingsToDefault, "Reset Settings to default Label");
		Swipe("DOWN", 2);

		// Back button validation
		click(AMDMoreMenu.objBackButtonInSettingsScreen, "Back button in settings screen");
		if (verifyElementPresent(AMDMoreMenu.objSettings, "Settings option")) {
			logger.info(
					"on tapping on the Back button from settings screen, user is navigated to the screen from which More screen was launched");
			extentLogger("Back button",
					"on tapping on the Back button from settings screen, user is navigated to the screen from which More screen was launched");
		} else {
			logger.info(
					"on tapping on the Back button from settings screen, user is not navigated to the screen from which More screen was launched");
			extentLoggerFail("Back button",
					"on tapping on the Back button from settings screen, user is not navigated to the screen from which More screen was launched");
		}

		click(AMDMoreMenu.objSettings, "Settings option");
	}

	@SuppressWarnings("unused")
	public void videoStreamingValidation(String userType) throws Exception {
		extent.HeaderChildNode("Video Streaming validation");
		System.out.println("\nVideo Streaming validation");
		// Video streaming menus validation
		verifyElementPresent(AMDMoreMenu.objvideoQualityOption, "Video Quality option");
		verifyElementPresent(AMDMoreMenu.objVideoStreamOverWifiOnlyOption, "Stream over wifi only option");
		verifyElementPresent(AMDMoreMenu.objVideoAutoPlay, "Video Autoplay option");

		click(AMDMoreMenu.objVideo_Quality("Auto"), "Video quality option");

		// video quality screen validation
		verifyElementPresent(AMDMoreMenu.objVideoQualityScreenTitle, "Video quality screen title");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityBest, "option Best");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityBetter, "option Better");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityGood, "option Good");
		verifyElementPresent(AMDSettingsScreen.objVideoQualityDatasaver, "option Datasaver");
		verifyElementPresent(AMDMoreMenu.objAutoOption, "option Auto");
		verifyElementPresent(AMDMoreMenu.objCloseButtonInVideoQualityScreen, "Close button");
		if (verifyElementPresent(AMDMoreMenu.objSelectedVideoQualityOption("Auto"), "Auto option tick mark")) {
			logger.info("the default selection in the Select Video Quality is 'Auto' option");
			extentLogger("Default selected Video quality option",
					"the default selection in the Select Video Quality is 'Auto' option");
		} else {
			logger.info("the default selection in the Select Video Quality is not 'Auto' option");
			extentLoggerFail("Default selected Video quality option",
					"the default selection in the Select Video Quality is not 'Auto' option");
		}

		click(AMDMoreMenu.objCloseButtonInVideoQualityScreen, "Close button");
		if (verifyElementPresent(AMDMoreMenu.objvideoQualityOption, "Video Quality option")) {
			logger.info("'X' button in Select Video Quality screen is functional");
			extentLogger("Close button", "'X' button in Select Video Quality screen is functional");
		} else {
			logger.info("'X' button in Select Video Quality screen is not functional");
			extentLoggerFail("Close button", "'X' button in Select Video Quality screen is not functional");
		}

		click(AMDMoreMenu.objVideo_Quality("Auto"), "Video quality option");
		click(AMDSettingsScreen.objVideoQualityBetter, "option Better");
		if (verifyElementPresent(AMDMoreMenu.objVideo_Quality("Better"), "Better option tick mark")) {
			logger.info("Select Video Quality screen with tick mark is displayed");
			extentLogger("Video quality option", "Select Video Quality screen with tick mark is displayed");
		} else {
			logger.info("Select Video Quality screen with tick mark is not displayed");
			extentLoggerFail("Video quality option", "Select Video Quality screen with tick mark is not displayed");
		}

		click(AMDMoreMenu.objVideo_Quality("Better"), "Video quality option");
		click(AMDMoreMenu.objAutoOption, "option Auto");

		// Stream over wifi only option validation
		WebElement wifitoggle = findElement(AMDMoreMenu.objVideo_WifiOnly);
		int wifitoggleX = wifitoggle.getLocation().getX();
		int wifitoggleY = wifitoggle.getLocation().getY();

		WebElement wifitext = findElement(AMDMoreMenu.objVideoStreamOverWifiOnlyOption);
		int wifitextX = wifitext.getLocation().getX();
		int wifitextY = wifitoggle.getLocation().getY();

		if (wifitextY < (wifitoggleY + 10)) {
			if (wifitoggleX > wifitextX) {
				logger.info("On/Off toggle is displayed at the right side of the Stream over WiFi only option");
				extent.extentLogger("wifi On/Off toggle",
						"On/Off toggle is displayed at the right side of the Stream over WiFi only option");
			} else {
				logger.error("On/Off toggle is not displayed at the right side of the Stream over WiFi only option");
				extent.extentLoggerFail("wifi On/Off toggle",
						"On/Off toggle is not displayed at the right side of the Stream over WiFi only option");
			}
		}

		String wifitoggleStatus = getText(AMDMoreMenu.objVideo_WifiOnly);
		if (wifitoggleStatus.equalsIgnoreCase("OFF")) {
			logger.info("the default state of the 'Stream over WiFi only' option is in off state.");
			extentLogger("Stream over WiFi only",
					"the default state of the 'Stream over WiFi only' option is in off state.");
		} else {
			logger.info("the default state of the 'Stream over WiFi only' option is not in off state.");
			extentLoggerFail("Stream over WiFi only",
					"the default state of the 'Stream over WiFi only' option is not in off state.");
		}

		Back(1);
		waitTime(3000);
		Back(1);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		}

		verifyElementExist(AMDHomePage.objHomeTab, "Home Tab");
		waitTime(10000);
		String courselContentTitle = carouselValidationWithApi(userType, "homepage");
		System.out.println(courselContentTitle);
		if (courselContentTitle != null) {
			for (int i = 0; i < 2; i++) {
				if (verifyElementPresent(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content")) {
					break;
				}

			}
			click(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");
		} else {
			click(AMDGenericObjects.objCarouselTitle("Shivaji Surathkal"), "Carousel content");
		}

		String wifi = "";
		String cmd = ("/usr/local/bin/adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"");
		Process p = Runtime.getRuntime().exec(cmd);
		System.out.println(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		wifi = br.readLine();
		logger.info("Wifi status :: " + wifi.toString());

		if (wifi.equalsIgnoreCase("Wi-Fi is enabled")) {
			if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
				waitTime(3000);
				if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
					Back(1);
				}
				if (verifyElementDisplayed(AMDHomePage.objPlayerScreen)) {
					logger.info("Content playback is playing on Wifi network");
					extent.extentLogger("Play", "Content playback is playing on Wifi network");
				} else {
					logger.info("Content playback is not playing on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
				}
			} else {
				waitTime(10000);
				click(AMDHomePage.objPlayerScreen, "Player screen");
//				waitForElementDisplayed(AMDHomePage.objMaximizeIcon, 10);
				if (verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn)) {
					logger.info("Content playback is playing on Wifi network");
					extent.extentLogger("Play", "Content playback is playing on Wifi network");
				} else {
					logger.info("Content playback is not playing on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
				}
			}
		} else {
			logger.info("Content playback is not playing on Wifi network");
			extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		click(AMDMoreMenu.objVideo_WifiOnly, "wifi toggle");
		Back(1);
		waitTime(3000);
		Back(1);
		if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		}

		verifyElementExist(AMDHomePage.objHomeTab, "Home Tab");
		waitTime(10000);
		if (courselContentTitle != null) {
			for (int i = 0; i < 2; i++) {
				if (verifyElementPresent(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content")) {
					break;
				}

			}
			click(AMDHomePage.objContentTitle(courselContentTitle), "Carousel content");
		} else {
			click(AMDGenericObjects.objCarouselTitle("Shivaji Surathkal"), "Carousel content");
		}

		String wifii = "";
		String cmdd = ("/usr/local/bin/adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"");
		Process pp = Runtime.getRuntime().exec(cmdd);
		System.out.println(cmdd);
		BufferedReader brr = new BufferedReader(new InputStreamReader(pp.getInputStream()));
		wifii = brr.readLine();
		logger.info("Wifi status :: " + wifii.toString());

		if (wifii.equalsIgnoreCase("Wi-Fi is enabled")) {
			if (!(userType.equalsIgnoreCase("SubscribedUser"))) {
				waitTime(3000);
				if (verifyIsElementDisplayed(AMDHomePage.objSubscribePopup)) {
					Back(1);
				}
				if (verifyElementDisplayed(AMDHomePage.objPlayerScreen)) {
					logger.info("Content playback is playing only on Wifi network");
					extent.extentLogger("Play", "Content playback is playing only on Wifi network");
				} else {
					logger.info("Content playback is not playing only on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing only on Wifi network");
				}
			} else {
				waitTime(10000);
				click(AMDHomePage.objPlayerScreen, "Player screen");
//				waitForElementDisplayed(AMDHomePage.objMaximizeIcon, 10);
				if (verifyIsElementDisplayed(AMDConsumptionScreen.objShareBtn)) {
					logger.info("Content playback is playing only on Wifi network");
					extent.extentLogger("Play", "Content playback is playing only on Wifi network");
				} else {
					logger.info("Content playback is not playing only on Wifi network");
					extent.extentLoggerFail("Play", "Content playback is not playing only on Wifi network");
				}
			}
		} else {
			logger.info("Content playback is not playing on Wifi network");
			extent.extentLoggerFail("Play", "Content playback is not playing on Wifi network");
		}
		Back(1);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More menu icon");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		click(AMDMoreMenu.objVideo_WifiOnly, "wifi toggle");

		// Auto play option valdation
		WebElement autoPlaytoggle = findElement(AMDMoreMenu.objVideo_Autoply);
		int autoPlaytoggleX = wifitoggle.getLocation().getX();
		int autoPlaytoggleY = wifitoggle.getLocation().getY();

		WebElement autoPlaytext = findElement(AMDMoreMenu.objVideoStreamOverWifiOnlyOption);
		int autoPlaytextX = wifitext.getLocation().getX();
		int autoPlaytextY = wifitoggle.getLocation().getY();

		if (autoPlaytextY < (autoPlaytoggleY + 10)) {
			if (autoPlaytoggleX > autoPlaytextX) {
				logger.info("On/Off toggle is displayed at the right side of the 'Auto Play' option");
				extent.extentLogger("Auto Play On/Off toggle",
						"On/Off toggle is displayed at the right side of the 'Auto Play' option");
			} else {
				logger.error("On/Off toggle is not displayed at the right side of the 'Auto Play' option");
				extent.extentLoggerFail("Auto Play On/Off toggle",
						"On/Off toggle is not displayed at the right side of the 'Auto Play' option");
			}
		}
		String elementAutoPlayToggleStatus = getText(AMDMoreMenu.objVideo_Autoply);
		if (elementAutoPlayToggleStatus.equalsIgnoreCase("ON")) {
			logger.info("the default state of the 'Auto Play' option is in ON state");
			extentLogger("Video Auto Play", "the default state of the 'Auto Play' option is in ON state");
		} else {
			logger.info("the default state of the 'Auto Play' option is not in ON state");
			extentLoggerFail("Video Auto Play", "the default state of the 'Auto Play' option is not in ON state");
		}
	}

	public void settings_Language(String displayLanguageSelection1, String displayLanguageSelection2) throws Exception {
		extent.HeaderChildNode("Display language settings validation");

		waitTime(3000);
		Swipe("UP", 1);
		// Display language option validation
		WebElement selectedDisplayLanguages = findElement(AMDMoreMenu.objDisplayLang);
		int selectedDisplayLanguagesX = selectedDisplayLanguages.getLocation().getX();
		int selectedDisplayLanguagesY = selectedDisplayLanguages.getLocation().getY();

		WebElement displayLanguagetext = findElement(AMDMoreMenu.objDisplayLanguageOption);
		int displayLanguagetextX = displayLanguagetext.getLocation().getX();
		int displayLanguagetextY = displayLanguagetext.getLocation().getY();

		if (displayLanguagetextY < (selectedDisplayLanguagesY + 10)) {
			if (selectedDisplayLanguagesX > displayLanguagetextX) {
				logger.info("Selected Display language is displayed at the right side of the option");
				extent.extentLogger("Display Language",
						"Selected Display language is displayed at the right side of the option");
			} else {
				logger.error("Selected Display language is not displayed at the right side of the option");
				extent.extentLoggerFail("Display Language",
						"Selected Display language is not displayed at the right side of the option");
			}
		}

		// Display language screen functionality validation
		click(AMDMoreMenu.objDisplayLang, "Display language");

		verifyElementPresent(AMDLoginScreen.objSelectedDisplayLanguage, "Selected display language");
		String selectedlanguage1 = getText(AMDLoginScreen.objSelectedDisplayLanguage);

		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection2), "language");

		WebElement selectedDisplayLanguage = findElement(AMDLoginScreen.objSelectedDisplayLanguage);
		int selectedDisplayLanguageX = selectedDisplayLanguage.getLocation().getX();
		int selectedDisplayLanguageY = selectedDisplayLanguage.getLocation().getY();

		WebElement tickMark = findElement(AMDLoginScreen.objTickmarkforSelectedDisplayLanguage);
		int tickMarkX = tickMark.getLocation().getX();
		int tickMarkY = tickMark.getLocation().getY();

		if (tickMarkY < (selectedDisplayLanguageY + 20)) {
			if (tickMarkX < selectedDisplayLanguageX) {
				logger.info("Selected Display Language screen with tick mark at the left side is displayed");
				extent.extentLogger("Selected Display Language",
						"Selected Display Language screen with tick mark at the left side is displayed");
			} else {
				logger.error("Selected Display Language screen with tick mark at the left side is not displayed");
				extent.extentLoggerFail("Selected Display Language",
						"Selected Display Language screen with tick mark at the left side is not displayed");
			}
		}

		String selectedlanguage2 = getText(AMDLoginScreen.objSelectedDisplayLanguage);

		if (selectedlanguage1 != selectedlanguage2) {
			logger.info("Display Language screen is functional");
			extentLogger("Display language screen", "Display Language screen is functional");
		} else {
			logger.info("Display Language screen is not functional");
			extentLoggerFail("Display language screen", "Display Language screen is not functional");
		}

		int totalSelectedLanguages = getDriver().findElements(AMDLoginScreen.objSelectedDisplayLanguage).size();
		logger.info(totalSelectedLanguages);

		if (totalSelectedLanguages == 1) {
			logger.info("user can select only one display language");
			extentLogger("Select one language", "user can select only one display language");
		}

		// Validation of swipe functionality in the display language screen
		click(AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1), "language");

		String pos1 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		String pos2 = null;
		Swipe("UP", 1);
		pos2 = getAttributValue("bounds", AMDOnboardingScreen.objSelectDisplayLang(displayLanguageSelection1));
		if (pos1 != pos2) {
			logger.info("Display Language screen is scrollable");
			extentLogger("Swipe", "Display Language screen is scrollable");
		} else {
			logger.info("Display Language screen is not scrollable");
			extentLoggerFail("Swipe", "Display Language screen is not scrollable");
		}

		verifyElementPresent(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue CTA");
		Back(1);
		Swipe("UP", 1);

		extent.HeaderChildNode("Content language settings validation");

		// content language option validation
		WebElement selectedContentLanguages = findElement(AMDMoreMenu.objContentLang);
		int selectedContentLanguagesX = selectedContentLanguages.getLocation().getX();
		int selectedContentLanguagesY = selectedContentLanguages.getLocation().getY();

		WebElement contentLanguagetext = findElement(AMDMoreMenu.objContentLanguageOption);
		int contentLanguagetextX = contentLanguagetext.getLocation().getX();
		int contentLanguagetextY = contentLanguagetext.getLocation().getY();

		if (contentLanguagetextY < (selectedContentLanguagesY + 10)) {
			if (selectedContentLanguagesX > contentLanguagetextX) {
				logger.info("Selected Content language is displayed at the right side of the option");
				extent.extentLogger("Content Language",
						"Selected Content language is displayed at the right side of the option");
			} else {
				logger.error("Selected Content language is not displayed at the right side of the option");
				extent.extentLoggerFail("Content Language",
						"Selected Content language is not displayed at the right side of the option");
			}
		}

		click(AMDMoreMenu.objContentLang, "Content language");

		verifyElementExist(AMDOnboardingScreen.objContentLanguageContainer, "Content language screen");

		String position1 = getAttributValue("bounds", AMDOnboardingScreen.objSelectContentLang("Telugu"));
		String position2 = null;
		PartialSwipe("UP", 1);
		position2 = getAttributValue("bounds", AMDOnboardingScreen.objSelectContentLang("Telugu"));
		if (position1 != position2) {
			logger.info("Content Language screen is scrollable");
			extentLogger("Content language", "Content Language screen is scrollable");
		} else {
			logger.info("Content Language screen is not scrollable");
			extentLoggerFail("Content language", "Content Language screen is not scrollable");
		}
		Back(1);
	}

	/**
	 * Author : Bhavana
	 */

	public void AboutUsScreenValidation(String userType) throws Exception {
		extent.HeaderChildNode("Verifying About Us screen as \" + userType");
		System.out.println("Verifying About Us screen as " + userType);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		verifyElementPresentAndClick(AMDMoreMenu.objAboutUs, "About Us option in More menu");
		verifyElementExist(AMDMoreMenu.objAboutUsHeader, "About Us Header");
		if (checkElementExist(AMDMoreMenu.objAboutUsHeader, "About Us Header")) {
			logger.info("User is navigated to About Us screen");
			extent.extentLogger("About Us", "User is navigated to About Us screen");
		} else {
			logger.error("User is unable to navigate to About Us screen");
			extent.extentLoggerFail("About Us", "User is unable to navigate to About Us screen");
		}
		verifyElementExist(AMDMoreMenu.objcloseButton, "Close button in About Us Screen");
		verifyElementExist(AMDMoreMenu.objAboutUsDescription,
				"Breif Description about the Application in About Use Screen");
		verifyElementExist(AMDMoreMenu.objHyperLinkInAboutUsScreen, "Hyper link in About Us screen");
		String str = getText(AMDMoreMenu.objHyperLinkInAboutUsScreen);
		System.out.println("Hyper link in About us Sceen is " + str);
		click(AMDMoreMenu.objHyperLinkInAboutUsScreen, "Hyper link");
		if (verifyElementExist(AMDMoreMenu.objPageNotFoundMsg, "ERROR,Page not found")) {
			logger.error("On clicking the Hyper link,user is unable to navigate to the respective page of Hyper link");
			extent.extentLoggerFail("Hyper Link",
					"On clicking the Hyper link,user is unable to navigate to the respective page of Hyper link");
		} else {
			logger.info("User is navigated to the respective page of the Hyper link");
			extent.extentLogger("Hyper link", "User is navigated to the respective page of the Hyper link");
		}
		Back(1);
	}

	@SuppressWarnings("unused")
	public void LogoutValidation(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Logout option as " + userType);
		System.out.println("Validation of Logout option as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 1);
		if (userType.contentEquals("Guest")) {
			if (verifyElementIsNotDisplayed(AMDMoreMenu.objLogout)) {
				logger.info("Logout option is NOT displayed for " + userType + "user");
				extent.extentLogger("Log out", "Logout option is NOT displayed for " + userType + "user");
			} else {
				logger.error("Logout option is displayed for " + userType + "user");
				extent.extentLoggerFail("Log out", "Logout option is displayed for " + userType + "user");
			}
		}
		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {
			verifyElementPresentAndClick(AMDMoreMenu.objLogout, "Logout option in More menu");
			verifyElementExist(AMDMoreMenu.objLogoutPopup, "Logout Confirmation Popup");
			verifyElementExist(AMDMoreMenu.objCancelButton, "Cancel button");
			verifyElementExist(AMDMoreMenu.objLogoutButton, "Logout button");
			String getPropertyValue = getAttributValue("enabled", AMDMoreMenu.objCancelButton);
			if (getPropertyValue.equalsIgnoreCase("true")) {
				logger.info("Cancel button is by default highlighted");
				extent.extentLogger("Cancel button", "Cancel button is by default highlighted");
			} else {
				logger.error("Cancel button is by default highlighted");
				extent.extentLoggerFail("Cancel button", "Cancel button is by default highlighted");
			}
			click(AMDMoreMenu.objCancelButton, "Cancel button");
			String getPropertyValue2 = getAttributValue("enabled", AMDHomePage.objMoreMenu);
			if (getPropertyValue.equalsIgnoreCase("true")) {
				logger.info("Cancel button is tappable and functional");
				extent.extentLogger("Cancel button", "Cancel button is tappable and functional");
			} else {
				logger.error("Cancel button is NOT tappable and NOT functional");
				extent.extentLogger("Cancel button", "Cancel button is NOT tappable and NOT functional");
			}
			click(AMDMoreMenu.objLogout, "Logout option in More menu");
			click(AMDMoreMenu.objLogoutButton, "Logout button");
			Swipe("DOWN", 1);
			String value = getText(AMDMoreMenu.objProfileHeader);
			System.out.println(value);
			if (value.equalsIgnoreCase("Guest")) {
				logger.info("Logout button is tappable and user is logged out successfully");
				extent.extentLogger(" Logout button", "Logout button is tappable and user is logged out successfully");
			} else {
				logger.error("Logout button is NOT tappable and user is unable to logout");
				extent.extentLoggerFail(" Logout button", "Logout button is NOT tappable and user is unable to logout");
			}
		}
	}

	public void TermsOfUseScreen(String userType) throws Exception {
		extent.HeaderChildNode("Verifying Terms of Use screen as " + userType);
		System.out.println("Verifying Terms of Use screen as " + userType);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objTermsOfUse, "Terms of Use option in More menu");
		verifyElementExist(AMDMoreMenu.objTermsOfUseHeader, "Terms Of Use Header");
		if (checkElementExist(AMDMoreMenu.objTermsOfUseHeader, "Terms Of Use Header")) {
			logger.info("User is navigated to Terms of Use screen");
			extent.extentLogger("Terms of Use", "User is navigated to Terms of Use screen");
		} else {
			logger.error("User is unable to navigate to Terms of Use screen");
			extent.extentLoggerFail("Terms of Use", "User is unable to navigate to Terms of Use screen");
		}
		verifyElementExist(AMDMoreMenu.objcloseButton, "Close button in Terms of Use Screen");
		verifyElementExist(AMDMoreMenu.objTermsDescription,
				"Breif information of the Application in Terms Of Use Screen");
		Swipe("UP", 6);
		// hyperlink 1
		String str1 = getText(AMDMoreMenu.objsupportHyperlinkInTermsOfUse);
		System.out.println(str1);
		verifyElementExist(AMDMoreMenu.objsupportHyperlinkInTermsOfUse, str1 + " Hyperlink");
		click(AMDMoreMenu.objsupportHyperlinkInTermsOfUse, "Hyperlink " + str1);
		if (verifyElementExist(AMDMoreMenu.objInternetErrormsg,
				"Although network is connected , 'Oops! Your are not connected to internet' text")) {
			logger.error("On clicking the Hyperlink " + str1
					+ " User is unable to navigate to the respective page of Hyperlink");
			extent.extentLoggerFail("HyperLink", "On clicking the Hyperlink " + str1
					+ " User is unable to navigate to the respective page of Hyperlink");
		} else {
			logger.info("User is navigated to the respective page of the Hyperlink " + str1);
			extent.extentLogger("Hyperlink", "User is navigated to the respective page of the Hyperlink " + str1);
		}
		Back(1);
		Swipe("UP", 10);
		// hyperlink 2
		String str2 = getText(AMDMoreMenu.objfeedbackLinkInTermsOfUse);
		System.out.println(str2);
		verifyElementExist(AMDMoreMenu.objfeedbackLinkInTermsOfUse, str2 + " Hyperlink");
		click(AMDMoreMenu.objfeedbackLinkInTermsOfUse, "Hyperlink " + str2);
		if (verifyElementExist(AMDMoreMenu.objInternetErrormsg,
				"Although network is connected , 'Oops! Your are not connected to internet' text")) {
			logger.error("On clicking the Hyperlink " + str2
					+ " User is unable to navigate to the respective page of Hyperlink");
			extent.extentLoggerFail("HyperLink", "On clicking the Hyperlink " + str2
					+ " User is unable to navigate to the respective page of Hyperlink");
		} else {
			logger.info("User is navigated to the respective page of the Hyperlink " + str2);
			extent.extentLogger("Hyperlink", "User is navigated to the respective page of the Hyperlink " + str2);
		}
		Back(1);
		// hyper link 3
		Swipe("UP", 45);
		String str3 = getText(AMDMoreMenu.objsubscribelinkInTermsofUse);
		System.out.println(str3);
		verifyElementExist(AMDMoreMenu.objsubscribelinkInTermsofUse, str3 + " Hyperlink");
		click(AMDMoreMenu.objsubscribelinkInTermsofUse, "Hyperlink " + str3);
		if (verifyElementExist(AMDSubscibeScreen.objSubscribeHeader, "Subscribe Header")) {
			logger.info("On clicking the Hyperlink " + str3 + " User is navigated succesfully to the respective page: "
					+ getText(AMDSubscibeScreen.objSubscribeHeader));
			extent.extentLogger("HyperLink",
					"On clicking the Hyperlink " + str3 + " User is navigated succesfully to the respective page: "
							+ getText(AMDSubscibeScreen.objSubscribeHeader));
		} else {
			logger.error("User is unable to navigate to the respective page of the Hyperlink " + str3);
			extent.extentLoggerFail("Hyperlink",
					"User is unable to navigate to the respective page of the Hyperlink " + str3);
		}
		Back(1);
		// hyper link 4
		Swipe("UP", 45);
		String str4 = getText(AMDMoreMenu.objzee5HyperlinkinTermsOfUse);
		System.out.println(str4);
		verifyElementExist(AMDMoreMenu.objzee5HyperlinkinTermsOfUse, str4 + " Hyperlink");
		click(AMDMoreMenu.objzee5HyperlinkinTermsOfUse, "Hyperlink " + str4);
		if (verifyElementExist(AMDHomePage.objHomeTab, "Home Tab")) {
			logger.info("On clicking the Hyperlink " + str4 + " User is navigated succesfully to the respective page: "
					+ getText(AMDHomePage.objHomeTab));
			extent.extentLogger("HyperLink", "On clicking the Hyperlink " + str4
					+ " User is navigated succesfully to the respective page: " + getText(AMDHomePage.objHomeTab));
		} else {
			logger.error("User is unable to navigate to the respective page of the Hyperlink " + str4);
			extent.extentLoggerFail("Hyperlink",
					"User is unable to navigate to the respective page of the Hyperlink " + str4);
		}
		Back(1);
	}

	/**
	 * Author : MANASA Module : Download
	 */
	public void downloadSettingsValidation() throws Exception {
		extent.HeaderChildNode("To verify if Quality in Downloads is set to Ask Everytime by default");
//		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More Menu");
//		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
		String quality = getText(AMDMoreMenu.objDownloads_Quality);
		System.out.println(quality);
		if (quality.equalsIgnoreCase("Ask each time")) {
			logger.info("Download Quality is set to " + quality + " by default");
			extent.extentLogger("Download Quality", "Download Quality is set to " + quality + " by default");

		} else {
			logger.info("Download Quality is not set to Ask Each Time  by default");
			extent.extentLoggerFail("Download Quality", "Download Quality is not set to Ask Each Time  by default");
		}

		String state = getText(AMDMoreMenu.objDownloads_WifiOnly);
		System.out.println(state);
		if (state.equalsIgnoreCase("OFF")) {
			logger.info("Download over WiFi only option is in  " + state + " state by default");
			extent.extentLogger("On/Off", "Download over WiFi only option is in  " + state + " state by default");

		} else {
			logger.info("Download over WiFi only option is not in OFF state by default ");
			extent.extentLoggerFail("On/Off", "Download over WiFi only option is not in OFF state by default ");
		}

		WebElement onOffToggle = findElement(AMDMoreMenu.objDownloads_WifiOnly);
		int toggleRightX = onOffToggle.getLocation().getX();
		System.out.println(toggleRightX);
		Dimension sizee = getDriver().manage().window().getSize();
		System.out.println(sizee.getWidth());
		int sizeee = sizee.getWidth() - 300;

		if (toggleRightX >= sizeee) {
			logger.info("On/Off toggle is displayed at right side of the option");
			extent.extentLogger("On/Off Toggle", "On/Off toggle is displayed at right side of the option");
		} else {
			logger.error("On/Off toggle is not displayed at right side of the option");
			extent.extentLoggerFail("On/Off Toggle", "On/Off toggle is not displayed at right side of the option");
		}
		verifyElementPresentAndClick(AMDMoreMenu.objDownloads_Quality, "Download Quality Settings option");
		extent.HeaderChildNode("To verify if Select Download Video Quality screen is displayed");
		if (verifyElementExist(AMDSettingsScreen.objSelectVideoQualityLabel, "Select Video Download Quality")) {
			logger.info("Navigated to Select Download Video Quality screen");
			extent.extentLogger("Quality", "Navigated to Select Download Video Quality screen");
		} else {
			logger.error("Not navigated to Select Download Video Quality screen");
			extent.extentLoggerFail("Quality", "Not navigated to Select Download Video Quality screen");
		}
		verifyElementExist(AMDSettingsScreen.objVideoQualityBest, "Best Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityBetter, "Better Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityGood, "Good Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityDatasaver, "Data Saver Quality Option");
		verifyElementExist(AMDSettingsScreen.objVideoQualityAskEachTime, "Ask Each Time Option");
		verifyElementExist(AMDSettingsScreen.objTickMark, "Tick mark");
		verifyElementPresentAndClick(AMDSettingsScreen.objXButton, "X Button");
	}

	public void searchHistoryValidation(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Search History Validation");
			waitTime(3000);
			Swipe("UP", 2);
			waitTime(2000);
			WebElement clear = findElement(AMDSettingsScreen.objClearSearchHistory);
			int clearRightX = clear.getLocation().getX();
			Dimension sizee = getDriver().manage().window().getSize();
			int sizeee = sizee.getWidth() / 2;
			if (clearRightX >= sizeee) {
				logger.info("Clear option is displayed at the right side of the Search History");
				extent.extentLogger("Clear", "Clear option is displayed at the right side of the Search History");
			} else {
				logger.error("Clear option is not displayed at the right side of the Search History");
				extent.extentLoggerFail("Clear",
						"Clear option is not displayed at the right side of the Search History");
			}
			verifyElementPresentAndClick(AMDSettingsScreen.objClearSearchHistory, "Clear Search History");
		}
	}

	public void authenticateDeviceValidation(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Authenticate Device Validation");
			verifyElementPresentAndClick(AMDSettingsScreen.objAuthenticateDevice, "Authenticate Device");

			if (verifyElementExist(AMDSettingsScreen.objAuthenticateScreen, "Authenticate Screen")) {
				logger.info("Navigated to Authenticate Screen");
				extent.extentLogger("Authenticate Screen", "Navigated to Authenticate Screen");
			} else {
				logger.error("Not navigated to Authenticate Screen");
				extent.extentLoggerFail("Authenticate Screen", "Not navigated to Authenticate Screen");
			}
			verifyElementPresentAndClick(AMDSettingsScreen.objAuthenticateCloseBtn, "Close Button");
		}
	}

	public void resetSettingsValidation(String userType) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Reset Settings Validation");
			verifyElementExist(AMDSettingsScreen.objResetSettings, "Reset Setting to Default");
			verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
			verifyElementExist(AMDSettingsScreen.objResetSettingPopUp,
					"Are you sure you want to reset your settings?  popup");
			verifyElementPresentAndClick(AMDSettingsScreen.objNoCTA, "No CTA");
			verifyElementPresentAndClick(AMDSettingsScreen.objDefaultSetting, "Default Setting Link");
			verifyElementPresentAndClick(AMDSettingsScreen.objYesCTA, "Yes CTA");
//			verifyElementExist(AMDSettingsScreen.objLoadingAnimator, "Loading Animator");
//			verifyElementExist(AMDSettingsScreen.objUpdateSettingsMessage,
//					"Please wait while we update your settings message");
		}
	}

	/**
	 * Author : Bindu Module : Exit_PopUp
	 */
	public void verifyDisplayLanguageScreenExitPopup(String userType) throws Exception {
		extent.HeaderChildNode("Verify Display Language Screen");
		System.out.println("\nVerify Display Language Screen Exit Popup");
		if (userType.equalsIgnoreCase("Guest")) {

			relaunch(true);
			accessDeviceLocationPopUp("Allow", userType);
			verifyElementExist(AMDOnboardingScreen.objDiplay_ContinueBtn, "Continue button (Display-LanguageScreen)");
			Back(1);
			waitTime(1000);

			extent.HeaderChildNode("Verify Exit Popup comprises of text message");
			verifyElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup is displayed");
			String Exitpopup = getDriver().findElement(AMDOnboardingScreen.objExitPopup).getText();
			System.out.println(Exitpopup);
			if (Exitpopup.equalsIgnoreCase("Are you sure you want to exit ZEE5?")) {
				logger.info("Exit popup is displayed with message Are you sure you want to exit Zee5?");
				extent.extentLogger("Display Language Screen",
						"Exit popup is displayed with message Are you sure you want to exit Zee5?");
			} else {
				logger.info("Exit popup is not displayed with message Are you sure you want to exit Zee5?");
				extent.extentLoggerFail("Display Language Screen",
						"Exit popup is displayed with message Are you sure you want to exit Zee5?");
			}
			verifyElementExist(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
			verifyElementExist(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");

			extent.HeaderChildNode("Verify Exip popup closes on tapping on NO CTA");
			click(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");
			// waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes when user taps on No CTA");
				extent.extentLogger("Display Language Screen", "Exit popup closes when user taps on No CTA");
			} else {
				logger.info("Exit Popup not closed when user taps on No CTA");
				extent.extentLoggerFail("Display Language Screen", "Exit Popup not closed when user taps on No CTA");
			}

			extent.HeaderChildNode("Verify Exit Popup closes by pulling down the Popup manually");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
			waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit popup closes by pulling down the popup manually");
				extent.extentLogger("Display Language Screen", "Exit popup closes by pulling down the popup manually");
			} else {
				logger.info("Exit popup not closed by pulling down the popup manually");
				extent.extentLoggerFail("Display Language Screen",
						"Exit popup not closed by pulling down the popup manually");
			}

			extent.HeaderChildNode(
					"Verify Exit Popup closes by tapping on the Horizontal line bar which appears on the popup");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupHorizontalLinebar,
					"Exit Popup Horizontal LineBar");
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes by tapping on the Horizontal Line Bar");
				extent.extentLogger("Display Language Screen",
						"Exit Popup closes by tapping on the Horizontal Line Bar");
			} else {
				logger.info("Exit Popup not closed by tapping on the Horizontal Line Bar");
				extent.extentLoggerFail("Display Language Screen",
						"Exit Popup not closed by tapping on the Horizontal Line Bar");
			}

			extent.HeaderChildNode("Verify Exit Popup closes by tapping on the Screen");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
			waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes by tapping on the screen");
				extent.extentLogger("Display Language Screen", "Exit Popup closes by tapping on the screen");
			} else {
				logger.info("Exit Popup not closed by tapping on the screen");
				extent.extentLoggerFail("Display Language Screen", "Exit Popup not closed by tapping on the screen");
			}

			extent.HeaderChildNode(" Verify Exit Popuop displayed on tapping device back button");
			Back(1);
			if (verifyElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup is displayed")) {
				logger.info("Exit Popup appears when user taps on device back button");
				extent.extentLogger("Display Language Screen",
						"Exit Popup appears when user taps on device back button");
			} else {
				logger.info("Exit Popup not appeared when user taps on device back button");
				extent.extentLoggerFail("Display Language Screen",
						"Exit Popup not appeared when user taps on device back button");

			}

			extent.HeaderChildNode("Verify Exit Popup closes when user taps on device back button");
			Back(1);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("Exit Popup closes by tapping on the device back button");
				extent.extentLogger("Display Language Screen",
						"Exit Popup closes by tapping on the device back button");
			} else {
				logger.info("Exit Popup not closed by tapping on the device back button");
				extent.extentLoggerFail("Display Language Screen",
						"Exit Popup not closed by tapping on the device back button");
			}

			extent.HeaderChildNode("Verify that user exists the app on tapping the Yes CTA in the Exit Popup");
			Back(1);
			verifyElementPresentAndClick(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
			waitTime(2000);
			if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
				logger.info("User exists from the app when user taps on Yes CTA");
				extent.extentLogger("Display Language Screen", "User exists from the app when user taps on Yes CTA");
			} else {
				logger.info("User not exists from the app when user taps on Yes CTA");
				extent.extentLoggerFail("Display Language Screen",
						"User not exists from the app when user taps on Yes CTA");
			}
			relaunch(true);

		} else {
			logger.info("Display Language Screen validation is not applicable for " + userType);
			extent.extentLogger("Display Language Screen",
					"Display Language Screen validation is not applicable for " + userType);

		}
	}

	public void LoginAfterLogout(String userType) throws Exception {

		if (userType.contentEquals("NonSubscribedUser")) {
			click(AMDMoreMenu.objProfile, "Profile");

			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, Username, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, Password, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
		}

		if (userType.contentEquals("SubscribedUser")) {
			click(AMDMoreMenu.objProfile, "Profile");

			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");

			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
		}
	}

	public void logoutOfflineValidation(String userType) throws Exception {

		if (userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")) {
			extent.HeaderChildNode("Offline Validation of Logout option as " + userType);
			System.out.println("Offline Validation of Logout option as " + userType);
			click(AMDHomePage.objMoreMenu, "More menu");
			Swipe("UP", 1);
			click(AMDMoreMenu.objLogout, "Logout option in More menu");
			setWifiConnectionToONOFF("Off");
			verifyElementExist(AMDMoreMenu.objNetworkerrormsg, "Internet connectivity error message");
			if (checkElementExist(AMDMoreMenu.objNetworkerrormsg, "Internet connectivity error message")) {
				logger.info(
						"Internet connectivity ERROR message is displayed on clicking Logout button without Internet");
				extent.extentLogger(" Logout button",
						"Internet connectivity ERROR message is displayed on clicking Logout button without Internet");
			} else {
				logger.error(
						"Internet connectivity ERROR message is NOT displayed on clicking Logout button without Internet");
				extent.extentLoggerFail(" Logout button",
						"Internet connectivity ERROR message is NOT displayed on clicking Logout button without Internet");
			}
			setWifiConnectionToONOFF("On");
			Back(1);
			click(AMDHomePage.HomeIcon, "Home Icon");
		}
	}

	public void PrivacyPolicyScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Privacy Policy Screen as " + userType);
		System.out.println("Validation of Privacy Policy Screen as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objPrivacyPolicy, "Privacy Policy option in More menu");
		waitTime(4000);
		verifyElementExist(AMDMoreMenu.objPrivacyPolicyHeaderText, "Privacy Policy Header");
		if (checkElementExist(AMDMoreMenu.objPrivacyPolicyHeaderText, "Privacy Policy Header")) {
			logger.info("User is navigated to Privacy Policy screen");
			extent.extentLogger("Privacy Policy", "User is navigated to Privacy Policy screen");
		} else {
			logger.error("User is unable to navigate to Privacy Policy screen");
			extent.extentLoggerFail("Privacy Policy", "User is unable to navigate to Privacy Policy screen");
		}
		verifyElementExist(AMDMoreMenu.objcloseButton, "Close button in Privacy Policy Screen");
		verifyElementExist(AMDMoreMenu.objPrivacyDescription,
				"Breif Description about the Application in Privacy Policy Screen");
		verifyElementExist(AMDMoreMenu.objHyperlinkInPrivacyPolicy, "Hyper link in Privacy Policy");
		String str1 = getText(AMDMoreMenu.objHyperlinkInPrivacyPolicy);
		System.out.println(str1);
		verifyElementExist(AMDMoreMenu.objHyperlinkInPrivacyPolicy, str1 + " Hyperlink");
		click(AMDMoreMenu.objHyperlinkInPrivacyPolicy, "Hyperlink " + str1);
		if (verifyElementExist(AMDMoreMenu.objPrivacyPolicyPageWithinbrowser, "Privacy Policy Page")) {
			logger.info("On clicking the Hyperlink " + str1 + " , User is able to navigate to the respective page");
			extent.extentLogger("HyperLink",
					"On clicking the Hyperlink " + str1 + " , User is able to navigate to the respective page");
		} else {
			logger.error("User is unable to navigate to the respective page of the Hyperlink " + str1);
			extent.extentLoggerFail("Hyperlink",
					"User is unable to navigate to the respective page of the Hyperlink " + str1);
		}
		waitTime(2000);
		Back(1);
		Swipe("UP", 12);
		String str2 = getText(AMDMoreMenu.objsupportlinkInprivacypolicy);
		System.out.println(str2);
		verifyElementExist(AMDMoreMenu.objsupportlinkInprivacypolicy, str2 + " Hyperlink");
		waitTime(2000);
		click(AMDMoreMenu.objsupportlinkInprivacypolicy, "Hyperlink " + str2); // defect
		if (verifyElementExist(AMDMoreMenu.objInternetErrormsg,
				"Although network is connected , 'Oops! Your are not connected to internet' text")) {
			logger.error("On clicking the Hyperlink " + str2
					+ " User is unable to navigate to the respective page of Hyperlink");
			extent.extentLoggerFail("HyperLink", "On clicking the Hyperlink " + str2
					+ " User is unable to navigate to the respective page of Hyperlink");
		} else {
			logger.info("User is navigated to the respective page of the Hyperlink " + str2);
			extent.extentLogger("Hyperlink", "User is navigated to the respective page of the Hyperlink " + str2);
		}
		Back(1);
		click(AMDMoreMenu.objcloseButton, "Close button");
		click(AMDHomePage.HomeIcon, "Home icon");
	}

	public void staticPagesInDisplayLanguage() throws Exception {
		click(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objSettings, "Settings");
		click(AMDMoreMenu.objDisplayLang, "Display Language");
		click(AMDOnboardingScreen.objSelectDisplayLang("Kannada"), "Kannada language");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");
		click(AMDMoreMenu.objBackbtnInSettings, "Back button");
		// About Us
		verifyElementPresentAndClick(AMDMoreMenu.objAboutUsInKannada, "About Us option in Kannada");
		if (checkElementExist(AMDMoreMenu.objTextInAboutUsScreen)) {
			logger.error("Content of the About Us Page is not according to the display language set by user");
			extent.extentLoggerFail("About Us",
					"Content of the About Us Page is not according to the display language set by user");
		} else {
			logger.info("Content of the About Us page is according to display language set by user");
			extent.extentLogger("About Us",
					"Content of the About Us page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		Swipe("UP", 1);
		// Help Center
		verifyElementPresentAndClick(AMDMoreMenu.objHelpCenterInKannada, "Help Center option in Kannada");
		waitTime(3000);
		String title = getText(AMDMoreMenu.objHelpCenterHeader);
		if (title.contains("Help Center")) {
			logger.info("Title of the page displayed: " + title);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title);
			logger.error("Content of Help Center page is not according to the display language set by user");
			extent.extentLoggerFail("Help Center",
					"Content of Help Center page is not according to the display language set by user");
		} else {
			logger.info("Content of the Help Center page is according to display language set by user");
			extent.extentLogger("About Us",
					"Content of the Help Center page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		Swipe("UP", 1);
		// Terms of Use
		verifyElementPresentAndClick(AMDMoreMenu.objTermsInKannada, "Terms of Use option in Kannada");
		String title2 = getText(AMDMoreMenu.objTermsOfUseHeader);
		if (title2.contains("Terms of Use")) {
			logger.info("Title of the page displayed: " + title2);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title2);
			logger.error("Content of the Terms of Use page is not according to the display language set by user");
			extent.extentLoggerFail("Terms of Use",
					"Content of the Terms of Use page is not according to the display language set by user");
		} else {
			logger.info("Content of the Terms of Use page is according to display language set by user");
			extent.extentLogger("About Us",
					"Content of the Terms of Use page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		// Privacy Policy
		verifyElementPresentAndClick(AMDMoreMenu.objPrivacyPolicyInKannada, "Privacy Policy option in Kannada");
		String title3 = getText(AMDMoreMenu.objPrivacyPolicyHeader);
		if (title3.contains("Terms of Use")) {
			logger.info("Title of the page displayed: " + title3);
			extent.extentLogger("pagetitle", "Title of the page displayed: " + title3);
			logger.error("Content of the Privacy Policy page is not according to the display language set by user");
			extent.extentLoggerFail("Privacy Policy",
					"Content of the Privacy Policy page is not according to the display language set by user");
		} else {
			logger.info("Content of the Privacy Policy page is according to display language set by user");
			extent.extentLogger("About Us",
					"Content of the Privacy Policy page is according to display language set by user");
		}
		click(AMDMoreMenu.objcloseButton, "Close button");
		// Changing to English
		click(AMDMoreMenu.objSettingsInKannada, "Settings");
		click(AMDMoreMenu.objDisplayLang, "Display Language");
		click(AMDOnboardingScreen.objSelectDisplayLang("English"), "English language");
		click(AMDOnboardingScreen.objDiplay_ContinueBtn, "[Display Language] Continue Button");
		Back(1);
	}

	/**
	 * Author : Kushal
	 * 
	 * @throws Exception
	 */

	public void ListingCollectionValidationFromLandingScreen(String userType) throws Exception {
		extent.HeaderChildNode("Listing Collection validation from Landing Screen");
		System.out.println("\nListing Collection validation from Landing Screen");

		verifyElementPresentAndClick(AMDHomePage.objHome, "Home button");

		waitTime(5000); // To Load the landing page completely
		boolean liveTV = false, loadingIcon = false, navigationFlag = false;
		int noOfTabs = getCount(AMDHomePage.objTitle);
		String getTrayName = null, getPageTitle;

		System.out.println("\nTop Nagivation Tabs: " + noOfTabs);
		for (int i = 1; i <= 10; i++) {
			String tabName = null;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			}

			System.out.println(getText(AMDHomePage.objSelectedTab) + " Landing Screen");

			waitTime(3000);
			if (verifyIsElementDisplayed(AMDGenericObjects.objFirstTrayTitle)) {
				loadingIcon = false;
			} else if (verifyIsElementDisplayed(AMDGenericObjects.objPageLoadingIcon)) {
				loadingIcon = true;
				System.out.println("\nContinuous Loading screen displayed");
			}

			if (!loadingIcon) {
				if (userType.equalsIgnoreCase("Guest")) {
					getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
					click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
				} else {
					if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray)
							| verifyIsElementDisplayed(AMDHomePage.objBannerAd)) {
						Swipe("UP", 1);
						waitTime(2000);
					}
					PartialSwipe("UP", 1);
					waitTime(2000);
					int noOfTrays = getCount(AMDGenericObjects.objNoOfTrays);
					if (noOfTrays > 0) {
						if (tabName.contains("Live TV")) {
							getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
						} else {
							getTrayName = getText(AMDGenericObjects.objTrayTitleByIndx(noOfTrays));
						}
						click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
					}
				}

				getPageTitle = getText(AMDHomePage.objTitle);
				if (getTrayName.contains(getPageTitle)) {
					navigationFlag = true;
					extent.extentLogger("Listing Collection", tabName + ": " + userType
							+ " is able to navigate to listing collection: " + getTrayName + " Screen");
					logger.info(
							userType + " User is able to navigate to listing collection: " + getTrayName + " Screen");
				} else {
					extent.extentLoggerFail("Listing Collection", tabName + ": " + userType
							+ " failed to navigate to listing collection: " + getTrayName + " Screen");
					logger.error(
							userType + " User failed to navigate to listing collection: " + getTrayName + " Screen");
				}

				// Navigates back to landing screen
				if (navigationFlag) {
					Back(1);
				}
			} else {
				extent.extentLoggerFail("Listing Collection", tabName + ": Failed to load the Page");
				logger.error(tabName + ": FAILED to Load the page");
			}

			// Following code is to break the loop after last tab validation in the landing
			// screen
			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}

		}
	}

	@SuppressWarnings("unused")
	public void ListingCollectionValidationFromConsumptionScreen(String userType) throws Exception {
		extent.HeaderChildNode("Listing Collection Screen validation from ConsumptionScreen");
		System.out.println("\nListing Collection Screen validation from ConsumptionScreen");

		verifyElementPresentAndClick(AMDHomePage.objHome, "Home button");

		waitTime(5000); // To Load the landing page completely
		boolean liveTV = false, loadingIcon = false;
		int noOfTabs = getCount(AMDHomePage.objTitle);
		String getTrayName = null, getPageTitle;
		String width = null, height = null, bounds, getboundvalue;

		System.out.println("\nTop Nagivation Tabs: " + noOfTabs);
		for (int i = 1; i <= 10; i++) {
			String tabName = null, pageNameAPI = null;
			boolean navigationFlag = false;
			if (i == noOfTabs) {
				if (!liveTV) {
					i = noOfTabs - 1;
				}
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			} else {
				WebElement eleTab = getDriver()
						.findElement(By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[" + i + "]"));
				eleTab.click();
				tabName = getText(AMDHomePage.objSelectedTab);
			}

			System.out.println(getText(AMDHomePage.objSelectedTab) + " Landing Screen");

			waitTime(6000);
			if (verifyIsElementDisplayed(AMDGenericObjects.objFirstTrayTitle)) {
				loadingIcon = false;
				if (!tabName.equalsIgnoreCase("Live TV")) {
					width = getAttributValue("width", AMDHomePage.objCarouselConetentCard);
					bounds = getAttributValue("bounds", AMDHomePage.objCarouselConetentCard);
					getboundvalue = bounds.replaceAll(",", " ").replaceAll("]", " ");
					height = getboundvalue.split(" ")[1];
				}

			} else if (verifyIsElementDisplayed(AMDGenericObjects.objPageLoadingIcon)) {
				loadingIcon = true;
				System.out.println("\nContinuous Loading screen displayed");
			}

			switch (tabName) {
			case "Home":
				pageNameAPI = "homepage";
				break;

			case "Shows":
				pageNameAPI = "tvshows";
				break;

			case "Movies":
				pageNameAPI = "movies";
				break;

			case "Premium":
				pageNameAPI = "premiumcontents";
				break;

			case "Club":
				pageNameAPI = null;
				break;

			case "News":
				pageNameAPI = "626";
				break;

			case "Kids":
				pageNameAPI = "3673";
				break;

			case "Music":
				pageNameAPI = "2707";
				break;

			case "Live TV":
				pageNameAPI = null;
				break;

			case "ZEE5 Originals":
				pageNameAPI = "zeeoriginals";
				break;

			default:
				break;
			}

			if (!loadingIcon) {

//				if(pageNameAPI!=null) {
//					String courselContentTitle = carouselValidationWithApi(userType, pageNameAPI);
//					System.out.println("API Card : "+courselContentTitle);
//					
//					for(int k=1;k<=7; k++) {
//						if (verifyIsElementDisplayed(AMDGenericObjects.objCarouselTitle(courselContentTitle))) {
//							click(AMDGenericObjects.objCarouselTitle(courselContentTitle), courselContentTitle);
//							break;
//						} else {
//							carouselCardsSwipe("LEFT", 1, width, height);
//						}
//					}
//				}else {
//					click(AMDHomePage.objCarouselTitle, getText(AMDHomePage.objCarouselTitle));
//				}

				if (tabName.equalsIgnoreCase("Live TV")) {
					waitTime(2000);
					waitForElementDisplayed(AMDGenericObjects.objNoOfTrays, 3000);
					click(AMDHomePage.objFirstChannelCard, "First available channel");
				} else if (tabName.equalsIgnoreCase("Kids")) {
					click(AMDHomePage.objKidsContentCard, "Kids Content card");
				} else {
					click(AMDHomePage.objCarouselTitle, getText(AMDHomePage.objCarouselTitle));
				}
				if (userType.contains("Guest") | userType.contains("NonSubscribedUser")) {
					waitTime(2000);
					if (verifyIsElementDisplayed(AMDGenericObjects.objPopUpDivider)) {
						click(AMDGenericObjects.objPopUpDivider, "Subcription Pop Up");
						extent.extentLogger("Subscription PopUp", userType
								+ "Subcription PopUp is displayed in the comsumption screen and popup is closed");
						logger.info("Subcription PopUp is displayed in the comsumption screen and popup is closed");
					}
				}
				if (tabName.contains("News") | tabName.contains("Live TV")) {
					extent.extentLogger("Listing Trays", "Listing Trays are unavailable in NEWS Consumption screen");
					logger.info("Listing Trays are unavailable in NEWS Consumption screen");
					navigationFlag = true;
				} else {

					if (userType.equalsIgnoreCase("Guest")) {
						getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
						click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
					} else {
						PartialSwipe("UP", 1);
						waitTime(2000);
						int noOfTrays = getCount(AMDGenericObjects.objNoOfTrays);
						if (noOfTrays > 0) {
							if (tabName.contains("Live TV")) {
								getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
							} else {
								getTrayName = getText(AMDGenericObjects.objTrayTitleByIndx(noOfTrays));
							}
							click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
						}
					}

					getPageTitle = getText(AMDHomePage.objTitle);
					if (getTrayName.contains(getPageTitle)) {
						navigationFlag = true;
						extent.extentLogger("Listing Collection", tabName + ": " + userType
								+ " is able to navigate to listing collection: " + getTrayName + " Screen");
						logger.info(tabName + ": " + userType + " User is able to navigate to listing collection: "
								+ getTrayName + " Screen");
					} else {
						extent.extentLoggerFail("Listing Collection", tabName + ": " + userType
								+ " failed to navigate to listing collection: " + getTrayName + " Screen");
						logger.error(tabName + ": " + userType + " User failed to navigate to listing collection: "
								+ getTrayName + " Screen");
					}
				}
				waitTime(2000);
				// Navigates back to landing screen
				if (navigationFlag) {
					Back(1);
				}
			} else {
				extent.extentLoggerFail("Listing Collection", tabName + ": Failed to load the Page");
				logger.error(tabName + ": FAILED to Load the page");
			}

			// Following code is to break the loop after last tab validation in the landing
			// screen
			if (liveTV) {
				break;
			}
			if (tabName.equalsIgnoreCase("Live TV")) {
				liveTV = true;
			}
		}
	}

	/**
	 * Author : Bindu
	 * 
	 * @param userType
	 * @throws Exception
	 */
	public void verifyExitPopupInAnyOfTheLandingScreen(String userType) throws Exception {

		if (userType.equalsIgnoreCase("Guest")) {
			accessDeviceLocationPopUp("Allow", userType);
			navigateToIntroScreen_DisplaylangScreen();
			navigateToHomecreenFromIntroScreen();
		}
		extent.HeaderChildNode("Validating ExitPopup in any of the Landing Screen");
		System.out.println("\nverify Landing Screen Exit Popup");
		verifyElementPresent(AMDHomePage.objHomeTab, "Home Tab");
		Back(1);
		if (verifyElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup")) {
			logger.info("Exit Popup appears when user taps on device back button");
			extent.extentLogger("Landing Screen", "Exit Popup appears when user taps on device back button");
		} else {
			logger.info("Exit Popup not appeared when user taps on device back button");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not appeared when user taps on device back button");

		}

		extent.HeaderChildNode("Verify Exit Popup comprises of text message");
		verifyElementExist(AMDOnboardingScreen.objExitPopup, "Exit Popup is displayed");
		String Exitpopup = getDriver().findElement(AMDOnboardingScreen.objExitPopup).getText();
		System.out.println(Exitpopup);
		if (Exitpopup.equalsIgnoreCase("Are you sure you want to exit ZEE5?")) {
			logger.info("Exit popup is displayed with message Are you sure you want to exit Zee5?");
			extent.extentLogger("Landing Screen",
					"Exit popup is displayed with message Are you sure you want to exit Zee5?");
		} else {
			logger.info("Exit popup is not displayed with message Are you sure you want to exit Zee5?");
			extent.extentLoggerFail("Landing Screen",
					"Exit popup is displayed with message Are you sure you want to exit Zee5?");
		}
		verifyElementExist(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
		verifyElementExist(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");

		extent.HeaderChildNode("verify Exit Popup closes when user taps on No CTA");
		click(AMDOnboardingScreen.objExitNo, "Exit Popup with No CTA");
		// waitTime(2000);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes when user taps on No CTA");
			extent.extentLogger("Landing Screen", "Exit popup closes when user taps on No CTA");
		} else {
			logger.info("Exit Popup not closed when user taps on No CTA");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed when user taps on No CTA");
		}

		extent.HeaderChildNode("Verify Exit Popup closes by pulling down the Popup manually");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
		waitTime(2000);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit popup closes by pulling down the popup manually");
			extent.extentLogger("Landing Screen", "Exit popup closes by pulling down the popup manually");
		} else {
			logger.info("Exit popup not closed by pulling down the popup manually");
			extent.extentLoggerFail("Landing Screen", "Exit popup not closed by pulling down the popup manually");
		}

		extent.HeaderChildNode(
				"Verify Exit Popup closes by tapping on the Horizontal line bar which appears on the popup");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupHorizontalLinebar,
				"Exit Popup Horizontal LineBar");
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes by tapping on the Horizontal Line Bar");
			extent.extentLogger("Landing Screen", "Exit Popup closes by tapping on the Horizontal Line Bar");
		} else {
			logger.info("Exit Popup not closed by tapping on the Horizontal Line Bar");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed by tapping on the Horizontal Line Bar");
		}

		extent.HeaderChildNode("Verify Exit Popup closes by tapping on the Screen");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitPopupDivider, "ExitPopup Divider");
		waitTime(2000);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes by tapping on the screen");
			extent.extentLogger("Landing Screen", "Exit Popup closes by tapping on the screen");
		} else {
			logger.info("Exit Popup not closed by tapping on the screen");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed by tapping on the screen");
		}

		extent.HeaderChildNode("Verify Exit Popup closes when user taps on device back button");
		Back(2);
		if (!(verifyIsElementDisplayed(AMDOnboardingScreen.objExitPopup))) {
			logger.info("Exit Popup closes by tapping on the device back button");
			extent.extentLogger("Landing Screen", "Exit Popup closes by tapping on the device back button");
		} else {
			logger.info("Exit Popup not closed by tapping on the device back button");
			extent.extentLoggerFail("Landing Screen", "Exit Popup not closed by tapping on the device back button");
		}

		extent.HeaderChildNode("Verify that user exists the app on tapping the Yes CTA in the Exit Popup");
		Back(1);
		verifyElementPresentAndClick(AMDOnboardingScreen.objExitYes, "Exit Popup with Yes CTA");
		waitTime(2000);
		if (verifyElementIsNotDisplayed(AMDOnboardingScreen.objExitPopup)) {
			logger.info("User Exits from the app when user taps on Yes CTA");
			extent.extentLogger("Landing Screen", "User Exits from the app when user taps on Yes CTA");
		} else {
			logger.error("User fails to exit from the app when user taps on Yes CTA");
			extent.extentLoggerFail("Landing Screen", "User fails to exit from the app when user taps on Yes CTA");
		}
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : My Watchlist
	 * 
	 * @throws Exception
	 */

	public void myWatchList(String userType) throws Exception {
		HeaderChildNode("Validate My Watchlist");
		switch (userType) {
		case "Guest":
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "My Watchlist");
			if (verifyElementDisplayed(AMDLoginScreen.objLoginOrRegisterPageTitle)) {
				logger.info("User navigated to login or register screen post taping on my watchlist");
				extent.extentLogger("watchlist",
						"User navigated to login or register screen post taping on my watchlist");
			} else {
				logger.error("User is not navigated to login or register screen post taping on my watchlist");
				extent.extentLoggerFail("watchlist",
						"User is not navigated to login or register screen post taping on my watchlist");
			}
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back button");
			if (verifyElementDisplayed(AMDMoreMenu.objWatchlist)) {
				logger.info("User is navigate back to more menu");
				extent.extentLogger("watchlist", "User is navigate back to more menu");
			} else {
				logger.error("User is not navigate back to more menu");
				extent.extentLoggerFail("watchlist", "User is not navigate back to more menu");
			}
			break;
		case "NonSubscribedUser":
			VerifyWatchListScreen();
			break;
		case "SubscribedUser":
			VerifyWatchListScreen();
			break;
		}
	}

	@SuppressWarnings("deprecation")
	public void VerifyWatchListScreen() throws Exception {
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objWatchlist, "My Watchlist");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objTabs(1))) {
			String selectedTab = findElement(AMDWatchlistPage.objSelectedTab).getText();
			if (selectedTab.equals("Shows")) {
				logger.info("Shows tab is selected default");
				extent.extentLogger("watchlist", "Shows tab is selected default");
			} else {
				logger.error("Shows tab is not selected as default tab");
				extent.extentLoggerFail("watchlist", "Shows tab is not selected as default tab");
			}
		}
		if (verifyIsElementDisplayed(AMDWatchlistPage.objWatchlistTitle)) {
			logger.info("User navigated to my watchlist screen");
			extent.extentLogger("watchlist", "User navigated to my watchlist screen");
		} else {
			logger.error("User not navigated to my watchlist screen");
			extent.extentLoggerFail("watchlist", "User not navigated to my watchlist screen");
		}
		if (verifyIsElementDisplayed(AMDWatchlistPage.objNoReminderIcon)) {
			logger.info("watchlist screen displayed with Empty Watchlist icon");
			extent.extentLogger("watchlist", "watchlist screen displayed with Empty Watchlist icon");
			verifyElementPresent(AMDWatchlistPage.objNoReminderIcon, "No Reminder Icon");
			verifyElementPresent(AMDWatchlistPage.objNoReminderTxt, "No Reminder Text");
			Back(2);
			click(AMDHomePage.objSearchBtn, "Search icon");
			click(AMDSearchScreen.objSearchEditBox, "Search Box");
			getDriver().getKeyboard().sendKeys("Aditya-Paarvathi challenge themselves for fun - Paaru");
			click(AMDSearchScreen.objSearchResultFirstContent, "content");
			waitTime(3000);
			verifyElementPresentAndClick(AMDWatchlistPage.objWatchlistIcon, "Watchlist icon");
			waitTime(3000);
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objWatchlist, "My Watchlist");
			Back(1);
		} else {
			logger.info("watchlist screen has some content");
			extent.extentLogger("watchlist", "watchlist screen has some content");
		}
		click(AMDWatchlistPage.objBackBtn, "Back button");
		if (verifyIsElementDisplayed(AMDMoreMenu.objWatchlist)) {
			logger.info("User successfully navigated to previous screen");
			extent.extentLogger("watchlist", "User successfully navigated to previous screen");
		} else {
			logger.info("User not navigated to previous screen");
			extent.extentLogger("watchlist", "User not navigated to previous screen");
		}
		click(AMDMoreMenu.objWatchlist, "watchlist");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objNoReminderIcon)) {

		}
		verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
		verifyElementPresent(AMDWatchlistPage.objSelectAllIcon, "Select All");
		click(AMDWatchlistPage.objSelectAllIcon, "Select All");
		for (int i = 1; i <= findElements(AMDWatchlistPage.objSelectCheckBox).size(); i++) {
			if (findElement(AMDWatchlistPage.objSelectContentByIndex(i)).getAttribute("checked").equals("true")) {
				logger.info(i + "st content is selected");
				extent.extentLogger("watchlist", i + "st content is selected");
			} else {
				logger.error(i + "st content is not selected");
				extent.extentLoggerFail("watchlist", i + "st content is not selected");
			}
		}
		if (verifyIsElementDisplayed(AMDWatchlistPage.objDeleteAllBtn)) {
			logger.info("Clicking Select All button will select all the contents in the current screen for deletion");
			extent.extentLogger("watchlist", "User successfully navigated to previous screen");
		} else {
			logger.info("User not navigated to previous screen");
			extent.extentLogger("watchlist", "User not navigated to previous screen");
		}
		click(AMDWatchlistPage.objCloseIcon, "Close icon");
		for (int i = 1; i <= findElements(AMDWatchlistPage.objNumberOfTabs).size(); i++) {
			String tabName = findElement(AMDWatchlistPage.objTabs(i)).getText();
			if (i == 1) {
				logger.info(tabName + " is selected by default");
				extent.extentLogger("watchlist", tabName + " is selected by default");
			} else {
				click(AMDWatchlistPage.objTabs(i), i + "nd tab");
				if (findElement(AMDWatchlistPage.objTabs(i)).isSelected()) {
					logger.info(tabName + " is selected");
					extent.extentLogger("watchlist", tabName + " is selected");
				} else {
					logger.error(tabName + " is not selected");
					extent.extentLoggerFail("watchlist", tabName + " is not selected");
				}
			}
		}
		click(AMDWatchlistPage.objTabs(1), "Shows tab");

		for (int i = 1; i < findElements(AMDWatchlistPage.objContentThumbnail).size(); i++) {
			String title = findElement(AMDWatchlistPage.objIterateTitle(i)).getText();
			String episode = findElement(AMDWatchlistPage.objIterateEpisode(i)).getText();
			logger.info(title + " is added to watchlist, it contains " + episode);
			extent.extentLogger("watchlist", title + " is added to watchlist, it contains " + episode);
		}
		verifyElementPresentAndClick(AMDWatchlistPage.objIterateEpisode(1), "First content in shows tab");
		String duration = findElement(AMDWatchlistPage.objDurationtxt).getText();
		logger.info("Duration of episode is : " + duration);
		extent.extentLogger("watchlist", "Duration of episode is : " + duration);
		click(AMDWatchlistPage.objBackBtn, "Back button");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objTitle)) {
			logger.info("User navigated to previous screen");
			extent.extentLogger("watchlist", "User navigated to previous screen");
		} else {
			logger.error("User is not navigated to previous screen");
			extent.extentLoggerFail("watchlist", "User is not navigated to previous screen");
		}
		findElements(AMDWatchlistPage.objForwardIcon).get(0).click();
		verifyElementPresentAndClick(AMDWatchlistPage.objEditBtn, "Edit button");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objSelectAllIcon)) {
			logger.info("Content of the Screen is editable");
			extent.extentLogger("watchlist", "Content of the Screen is editable");
		} else {
			logger.error("Content of the Screen is not editable");
			extent.extentLoggerFail("watchlist", "Content of the Screen is not editable");
		}
		for (int i = 1; i <= findElements(AMDWatchlistPage.objSelectCheckBox).size(); i++) {
			if (findElement(AMDWatchlistPage.objSelectContentByIndex(i)).isDisplayed()) {
				logger.info("Check box is displayed for the " + i + "st content");
				extent.extentLogger("watchlist", "Check box is displayed for the " + i + "st content");
			} else {
				logger.error("Check box is not displayed for the " + i + "st content");
				extent.extentLoggerFail("watchlist", "Check box is not displayed for the " + i + "st content");
			}
		}
		click(AMDWatchlistPage.objCloseIcon, "close icon");
		if (verifyIsElementDisplayed(AMDWatchlistPage.objDurationtxt)) {
			String contentDuration = findElement(AMDWatchlistPage.objDurationtxt).getText();
			String contentTitle = findElement(AMDWatchlistPage.objTitleTxt).getText();
			logger.info("Content Duration : " + contentDuration + "\n Content title : " + contentTitle);
			extent.extentLogger("watchlist",
					"Content Duration : " + contentDuration + "\n Content title : " + contentTitle);
		} else {
			logger.error("Content Duration and Content title is not displayed");
			extent.extentLoggerFail("watchlist", "Content Duration and Content title is not displayed");
		}
		verifyElementPresentAndClick(AMDWatchlistPage.objTitleTxt, "First content of Shows screen");
		waitTime(3000);
		if (verifyIsElementDisplayed(AMDWatchlistPage.objPlayerScreen)) {
			logger.info("Navigated to Consumption screen");
			extent.extentLogger("watchlist", "Navigated to Consumption screen");
		} else {
			logger.error("unable to navigated to Consumption screen");
			extent.extentLoggerFail("watchlist", "unable to navigated to Consumption screen");
		}
		Back(1);
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : My Reminders
	 */

	public void myReminders(String userType) throws Exception {
		switch (userType) {
		case "Guest":
			HeaderChildNode("Validate My Reminder");
			verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
			click(AMDMoreMenu.objMyRemainders, "My Remainders");
			if (verifyElementDisplayed(AMDLoginScreen.objLoginOrRegisterPageTitle)) {
				logger.info("User navigated to login or register screen post taping on My Remainders");
				extent.extentLogger("My Remainders",
						"User navigated to login or register screen post taping on My Remainders");
			} else {
				logger.error("User is not navigated to login or register screen post taping on My Remainders");
				extent.extentLoggerFail("My Remainders",
						"User is not navigated to login or register screen post taping on My Remainders");
			}
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back button");
			if (verifyElementDisplayed(AMDMoreMenu.objMyRemainders)) {
				logger.info("User is navigate back to more menu");
				extent.extentLogger("watchlist", "User is navigate back to more menu");
			} else {
				logger.error("User is not navigate back to more menu");
				extent.extentLoggerFail("watchlist", "User is not navigate back to more menu");
			}
			break;
		case "NonSubscribedUser":
			validateMyReminder();
			break;
		case "SubscribedUser":
			validateMyReminder();
			break;
		}
	}

	public void validateMyReminder() throws Exception {
		HeaderChildNode("Validate My Reminder screen");
		waitTime(3000);
		verifyElementPresentAndClick(AMDHomePage.objMoreMenu, "More menu");
		click(AMDMoreMenu.objMyRemainders, "My Remainders");
		if (verifyElementDisplayed(AMDMyReminderPage.objReminberHeaderTitle)) {
			logger.info("User navigated to login or register screen post taping on My Remainders");
			extent.extentLogger("My Remainders",
					"User navigated to login or register screen post taping on My Remainders");
		} else {
			logger.error("User is not navigated to login or register screen post taping on My Remainders");
			extent.extentLoggerFail("My Remainders",
					"User is not navigated to login or register screen post taping on My Remainders");
		}

		if (!verifyElementDisplayed(AMDMyReminderPage.objNoReminderIcon)) {
			verifyElementPresent(AMDMyReminderPage.objEditBtn, "Edit button");
			for (int i = 0; i < findElements(AMDMyReminderPage.objTitleOfContentTxt).size(); i++) {
				if (findElements(AMDMyReminderPage.objTitleOfContentTxt).get(i).isDisplayed()) {
					logger.info("Title of the content in reminder screen is displayed");
					extent.extentLogger("My Remainders", "Title of the content in reminder screen is displayed");
				} else {
					logger.error("Title of the content in reminder screen is not displayed");
					extent.extentLoggerFail("My Remainders",
							"Title of the content in reminder screen is not displayed");
				}
				if (findElements(AMDMyReminderPage.objEpisodeDatetxt).get(i).isDisplayed()) {
					logger.info("Date of the content in reminder screen is displayed");
					extent.extentLogger("My Remainders", "Date of the content in reminder screen is displayed");
				} else {
					logger.error("Date of the content in reminder screen is not displayed");
					extent.extentLoggerFail("My Remainders", "Date of the content in reminder screen is not displayed");
				}
			}
			click(AMDMyReminderPage.objEditBtn, "Edit button");
			verifyElementPresent(AMDMyReminderPage.objSelectAllIcon, "Select All");
			click(AMDMyReminderPage.objSelectAllIcon, "Select All");
			if (findElements(AMDMyReminderPage.objCheckBox).size() > 1) {
				findElements(AMDMyReminderPage.objCheckBox).get(0).click();
			}
			verifyElementPresent(AMDMyReminderPage.objDeleteAllIcon, "Delete Icon");
			for (int i = 0; i < findElements(AMDMyReminderPage.objCheckBox).size(); i++) {
				if (findElements(AMDMyReminderPage.objCheckBox).get(0).getAttribute("checked").equals("true")) {
					logger.info("Check box is checked");
					extent.extentLogger("My Remainders", "Check box is checked");
				} else {
					logger.info("Check box is not checked");
					extent.extentLogger("My Remainders", "Check box is not checked");
				}
				findElements(AMDMyReminderPage.objCheckBox).get(0).click();
			}
			click(AMDMyReminderPage.objCloseIcon, "Close icon");
			if (verifyElementDisplayed(AMDMyReminderPage.objEditBtn)) {
				logger.info("User navigated to edit screen");
				extent.extentLogger("My Remainders", "User navigated to edit screen");
			} else {
				logger.error("User not navigated to edit screen ");
				extent.extentLoggerFail("My Remainders", "User not navigated to edit screen ");
			}
		} else {
			logger.info("Reminder is empty");
			extent.extentLoggerWarning("My Remainders", "Reminder is empty");
			verifyElementPresent(AMDMyReminderPage.objNoReminderIcon, "No Reminder icon");
			verifyElementPresent(AMDMyReminderPage.objNoReminderTxt, "No Reminder text");
		}
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : Have a prepaid code
	 * 
	 * @throws Exception
	 */
	public void Haveaprepaidcode(String userType) throws Exception {
		HeaderChildNode("Validate Have a prepaid code");
		verifyElementPresentAndClick(AMDMoreMenu.objHaveaPrepaidCode, "Have a prepaid code");
		verifyElementPresent(AMDMoreMenu.objPrepaidCodePopUp, "Have a prepaid code pop up");
		Back(1);
	}

	/**
	 * Author : Hitesh Module : More Screen Screen : Have a prepaid code
	 * 
	 * @throws Exception
	 */
	public void Settings(String userType) throws Exception {
		HeaderChildNode("Validate Settings");
		verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings");
		verifyElementPresent(AMDSettingsScreen.objSettingsScreenTitle, "Settings screen");
		Back(1);
	}

	/**
	 * Author : Bhavana Module : More screen
	 */
	public void HelpCenterScreen(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Help Center Screen as " + userType);
		System.out.println("Validation of Help Center Screen as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objHelpCentre, "Help Center option in More menu");
		waitTime(5000);
		verifyElementExist(AMDMoreMenu.objHelpCenterHeader, "Help Center Header");
		if (checkElementExist(AMDMoreMenu.objHelpCenterHeader, "Help Center Header")) {
			logger.info("User is navigated to Help Center screen");
			extent.extentLogger("Help Center", "User is navigated to Help Center screen");
		} else {
			logger.error("User is unable to navigate to Help Center screen");
			extent.extentLoggerFail("Help Center", "User is unable to navigate to Help Center screen");
		}
		verifyElementPresentAndClick(AMDMoreMenu.objcloseButton, "Close button in Help Center Screen");
		if (checkElementExist(AMDHomePage.objMoreMenu, "More menu")) {
			logger.info("User is navigated to the Previous screen on clicking the Close button in Help Center screen");
			extent.extentLogger("Help Center",
					"User is navigated to the Previous screen on clicking the Close button in Help Center screen");
		} else {
			logger.error(
					"User is unable to navigate to the Previous screen on clicking the Close button in Help Center screen");
			extent.extentLoggerFail("Help Center",
					"User is unable to navigate to the Previous screen on clicking the Close button in Help Center screen");
		}
		click(AMDMoreMenu.objHelpCentre, "Help Center option");
		waitTime(5000);
		verifyElementExist(AMDMoreMenu.objSearchBarInHelpCenter, "Search bar to enter help queries");
		GettingStartedValidation();
		MyAccountValidation();
		QuickLinksValidation();
		Back(1);
		click(AMDHomePage.HomeIcon, "Home icon");

	}

	public void GettingStartedValidation() throws Exception {

		verifyElementExist(AMDMoreMenu.objGettingStartedHeader, "'Getting Started' Header");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("What is ZEE5"), "'What is ZEE5?'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("What is ZEE5"), "Article title 'What is ZEE5?'")) {
			logger.info("User is navigated to 'What is ZEE5?' page");
			extent.extentLogger("Article", "User is navigated to 'What is ZEE5?' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Registering with ZEE5"), "'Registering with ZEE5'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("Registering with ZEE5"),
				"Article title 'Registering with ZEE5'")) {
			logger.info("User is navigated to 'Registering with ZEE5' page");
			extent.extentLogger("Article", "User is navigated to 'Registering with ZEE5' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Purchasing a subscription"),
				"'Purchasing a subscription'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("Purchasing a subscription"),
				"Article title 'Purchasing a subscription'")) {
			logger.info("User is navigated to 'Purchasing a subscription' page");
			extent.extentLogger("Article", "User is navigated to 'Purchasing a subscription' page");
		}
		Back(1);
		waitTime(3000);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("How do I watch ZEE5"),
				"'How do I watch ZEE5 on my television'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("How do I watch ZEE5 on my television"),
				"Article title 'How do I watch ZEE5 on my television?'")) {
			logger.info("User is navigated to 'How do I watch ZEE5 on my television?' page");
			extent.extentLogger("Article", "User is navigated to 'How do I watch ZEE5 on my television?' page");
		}
		Back(1);
		waitTime(3000);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		waitTime(3000);
	}

	public void MyAccountValidation() throws Exception {
		verifyElementExist(AMDMoreMenu.objMyAccountHeader, "'My Account' Header");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Managing your Subscription"),
				"'Managing your Subscription'");
		if (verifyElementPresent(AMDMoreMenu.objArticleTitle("Managing your Subscription"),
				"Article title 'Managing your Subscription'")) {
			logger.info("User is navigated to 'Managing your Subscription' page");
			extent.extentLogger("Article", "User is navigated to 'Managing your Subscription' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I can"), "I can't sign in to ZEE5");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("t sign in to ZEE5"),
				"Article title 'I can't sign in to ZEE5'")) {
			logger.info("User is navigated to 'I can't sign in to ZEE5' page");
			extent.extentLogger("Article", "User is navigated to 'I can't sign in to ZEE5' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I made a payment but"),
				"'I made a payment but my subscription isn't active / My subscription is missing'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("I made a payment but my subscription"),
				"Article title 'I made a payment but my subscription isn't active / My subscription is missing'")) {
			logger.info(
					"User is navigated to 'I made a payment but my subscription isn't active / My subscription is missing' page");
			extent.extentLogger("Article",
					"User is navigated to 'I made a payment but my subscription isn't active / My subscription is missing' page");
		}
		Back(1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("I want to update my"),
				"I want to update my profile information");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("I want to update my profile information"),
				"Article title 'I want to update my profile information'")) {
			logger.info("User is navigated to 'I want to update my profile information' page");
			extent.extentLogger("Article", "User is navigated to 'I want to update my profile information' page");
		}
		Back(1);
		waitTime(2000);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		waitTime(3000);
	}

	public void QuickLinksValidation() throws Exception {
		// verifyElementPresent(AMDMoreMenu.objQueriesHeader("Quick Links"), "'Quick
		// Links' tab");
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("How Can I"), "'How Can I?'");
		if (verifyElementPresent(AMDMoreMenu.objArticleTitle("How Can I"), "Article title 'How Can I?'")) {
			logger.info("User is navigated to 'How Can I?' page");
			extent.extentLogger("Article", "User is navigated to 'How Can I?' page");
		}
		Back(1);
		waitTime(2000);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Offers"), "'Offers & Partnerships'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("Offers"), "Article title 'Offers & Partnerships'")) {
			logger.info("User is navigated to 'Offers & Partnerships' page");
			extent.extentLogger("Article", "User is navigated to 'Offers & Partnerships' page");
		}
		Back(1);
		waitTime(2000);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Contests"), "'Games, Quizzes & Contests'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("Contests"), "Article title 'Games, Quizzes & Contests'")) {
			logger.info("User is navigated to 'Games, Quizzes & Contests' page");
			extent.extentLogger("Article", "User is navigated to 'Games, Quizzes & Contests' page");
		}
		Back(1);
		waitTime(2000);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Before TV"), "'Before TV'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("Before TV"), "Article title 'Before TV'")) {
			logger.info("User is navigated to 'Before TV' page");
			extent.extentLogger("Article", "User is navigated to 'Before TV' page");
		}
		Back(1);
		waitTime(2000);
		PartialSwipe("UP", 1);
		PartialSwipe("UP", 1);
		waitTime(3000);
		verifyElementPresentAndClick(AMDMoreMenu.objQueriesHeader("Cancel Subscription"), "'Cancel Subscription'");
		if (verifyElementExist(AMDMoreMenu.objArticleTitle("How do I cancel my ZEE5 Subscription"),
				"Article title 'How do I cancel my ZEE5 Subscription'")) {
			logger.info("User is navigated to 'How do I cancel my ZEE5 Subscription?' page");
			extent.extentLogger("Article", "User is navigated to 'How do I cancel my ZEE5 Subscription?' page");
		}
		Back(1);
		waitTime(2000);
	}

	/**
	 * Author : Kushal Module : listing screen
	 */

	public void ListingCollectionVerification(String pUserType) throws Exception {
		extent.HeaderChildNode("Listing Collection validation as: " + pUserType);
		System.out.println("\nListing Collection validation as: " + pUserType);

		String getTrayName = null, getPageTitle;

		verifyElementPresentAndClick(AMDHomePage.objHome, "Home button");
		waitForElementDisplayed(AMDHomePage.objCarouselTitle, 2000);

		waitTime(2000);
		if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray)
				| verifyIsElementDisplayed(AMDHomePage.objBannerAd)) {
			Swipe("UP", 1);
			waitTime(2000);
		}
		// Swipe Tray content cards
		SwipeRailContentCards(AMDGenericObjects.objFirstTrayTitle);

		if (pUserType.equalsIgnoreCase("Guest")) {
			getTrayName = getText(AMDGenericObjects.objFirstTrayTitle);
			click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
		} else {
			if (verifyIsElementDisplayed(AMDHomePage.objContinueWatchingTray)
					| verifyIsElementDisplayed(AMDHomePage.objBannerAd)) {
				Swipe("UP", 1);
				waitTime(2000);
			}
			PartialSwipe("UP", 1);
			waitTime(2000);
			int noOfTrays = getCount(AMDGenericObjects.objNoOfTrays);
			if (noOfTrays > 0) {
				getTrayName = getText(AMDGenericObjects.objTrayTitleByIndx(noOfTrays));
				click(AMDHomePage.objViewAllBtn(getTrayName), getTrayName + " - View All button");
			}
		}

		getPageTitle = getText(AMDHomePage.objTitle);
		if (getTrayName.contains(getPageTitle)) {
			extent.extentLogger("Listing Collection", "Collection screen is dislayed for: " + getPageTitle);
			logger.info("Collection screen is dislayed for: " + getPageTitle);
		} else {
			extent.extentLoggerFail("Listing Collection", "Collection screen is not dislayed");
			logger.error("Collection screen is not dislayed");
		}

		if (verifyElementDisplayed(AMDHomePage.objBackIcon)) {
			logger.info("Back button is displayed in listing Collection screen");
			extent.extentLogger("Listing Collection Screen", "Back button is displayed in listing Collection screen");
		} else {
			logger.error("Back button is not displayed in the listing Collection screen");
			extent.extentLoggerFail("Listing Collection Screen",
					"Back button is not displayed in the listing Collection screen");
		}

		if (verifyElementDisplayed(AMDGenericObjects.objPremiumTags)) {
			logger.info("Premium tags are displayed on top-left of content cards in listing Collection screen");
			extent.extentLogger("Premium Tags",
					"Premium tags are displayed on top-left of content cards in listing Collection screen");
		} else {
			logger.info("Premium tags are displayed on top-left of content cards in listing Collection screen");
			extent.extentLoggerFail("Premium Tags",
					"Premium tags are not displayed on top-left of content cards in listing Collection screen");
		}

		// To click on any content card displayed under collection screen
		findElements(AMDGenericObjects.objPremiumTags).get(0).click();

		if (verifyElementDisplayed(AMDConsumptionScreen.objWatchlistBtn)) {
			extent.extentLogger("Consumption Screen",
					"Consumption screen appeared on selecting content card from listing collection");
			logger.info("Consumption screen appeared on selecting content card from listing collection");
		} else {
			logger.error("Consumption screen failed to appear on selecting content card from listing collection");
			extent.extentLoggerFail("Consumption Screen",
					"Consumption screen failed to appear on selecting content card from listing collection");
		}
		Back(1);
		getPageTitle = getText(AMDHomePage.objTitle);
		if (getTrayName.contains(getPageTitle)) {
			extent.extentLogger("Listing Collection",
					"Collection listing screen is dislayed on navigating back from Consumption screen");
			logger.info("Collection listing screen is dislayed on navigating back from Consumption screen");
		} else {
			extent.extentLoggerFail("Listing Collection",
					"Collection listing screen failed to display on navigating back from Consumption screen");
			logger.error("Collection listing screen failed to display on navigating back from Consumption screen");
		}
		Back(1);
	}

	/**
	 * Author : Yashaswini Module : More screen Screen : Invite a friend
	 */
	public void Invite_a_Friend(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Invite a Friend Screen as " + userType);
		System.out.println("Validation of Invite a Friend Screen as " + userType);
		click(AMDHomePage.objMoreMenu, "More menu");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDMoreMenu.objInviteAFriend, "Invite a Friend in More menu");
		waitTime(5000);

		if (checkElementExist(AMDMoreMenu.objshareOptions, "Share option panel")) {
			logger.info("User is navigated share options screen");
			extent.extentLogger("Share through options screen", "User is navigated to share options screen");
		} else {
			logger.info("User unable to navigate share options screen");
			extent.extentLogger("Share through options screen", "User is not navigated to share options screen");
		}
		verifyElementPresentAndClick(AMDMoreMenu.objWhatsAppMessage, "Share through WhatsAppMessage");

	}

	/**
	 * Author : Bindu Module : More Screen Screen : My Transactions
	 */
	public void verifyMyTransactions(String userType) throws Exception {
		if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			ZNALogoutMethod();
			click(AMDHomePage.objMoreMenu, "More Menu");
			click(AMDMoreMenu.objProfile, "Profile");
			waitTime(2000);

			String NonsubscribedUserWithInactivePackUserName = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackUsername");
			String NonsubscribedUserWithInActivePackPassword = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

			LoginWithEmailID(NonsubscribedUserWithInactivePackUserName, NonsubscribedUserWithInActivePackPassword);
		}
		extent.HeaderChildNode("Verify My transactions Screen");
		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
		waitTime(2000);
		if (verifyElementExist(AMDMoreMenu.objMyTransactions, "My TransactionsOption")) {
			logger.info("My Transactions option is availabel on More button");
			extent.extentLogger("MoreMenu Screen", "My Transactions option is availabel on More button");

		} else {
			logger.info("My Transactions option is not availabel on More button");
			extent.extentLoggerFail("MoreMenu Screen", "My Transactions option is not availabel on More button");
		}
		click(AMDMoreMenu.objMyTransactions, "My TransactionsOption");
		waitTime(2000);

		if (verifyElementExist(AMDMoreMenu.objMyTransactionsHeader, "My Transactions Page")) {

			logger.info("User navigated to My Transactions Page on tapping My Transactions");
			extent.extentLogger("MyTransactions Screen",
					"User navigated to My Transactions Page on tapping My Transactions");
		} else {
			logger.info("User not navigated to My Transactions Page on tapping My Transactions");
			extent.extentLoggerFail("MyTransactions Screen",
					"User not navigated to My Transactions Page on tapping My Transactions");
		}
		MyTransactionsWithDetails();

		extent.HeaderChildNode("Verify Date of Transaction for every transaction done by the user");
		String TransactionDate = getDriver().findElement(AMDMoreMenu.objTransactionDate1).getText();
		logger.info(TransactionDate);
		if (verifyElementExist(AMDMoreMenu.objTransactionDate1, "Transaction date")) {
			logger.info(TransactionDate + " : Date of Transaction is shown for the transactions done by the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionDate + " :Date of Transaction is shown for the transactions done by the user ");
		} else {

			logger.info(TransactionDate + " : Date of Transaction is not shown for the transactions done by the user ");
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionDate + " :Date of Transaction is not shown for the transactions done by the user ");
		}

		extent.HeaderChildNode("verify My Transactions pack details");
		String TransactionPackName = getDriver().findElement(AMDMoreMenu.objTransactionPackName1).getText();
		logger.info(TransactionPackName);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackName1, "Transaction Pack name")) {
			logger.info(TransactionPackName + " : Transaction pack name is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackName + " : Transaction pack name is displayed for the user ");
		} else {

			logger.info(TransactionPackName + " : Transaction pack name is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackName + " : Transaction pack name is not displayed for the user ");
		}

		String TransactionPackduration = getDriver().findElement(AMDMoreMenu.objTransactionPackDuration1).getText();
		logger.info(TransactionPackduration);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackDuration1, "Transaction Pack duration")) {
			logger.info(TransactionPackduration + " : Transaction pack duration is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackduration + " : Transaction pack duration is displayed for the user ");
		} else {

			logger.info(TransactionPackduration + " : Transaction pack duration is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackduration + " : Transaction pack duration is not displayed for the user ");
		}

		String TransactionPackRental = getDriver().findElement(AMDMoreMenu.objTransactionPackRental1).getText();
		logger.info(TransactionPackRental);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackRental1, "Transaction PackRental")) {
			logger.info(TransactionPackRental + " : Transaction pack rental is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackRental + " : Transaction pack rental is displayed for the user ");
		} else {

			logger.info(TransactionPackRental + " : Transaction pack rental is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackRental + " : Transaction pack rental is not displayed for the user ");
		}

		extent.HeaderChildNode("Verify Payment mode mentioned for the Transaction done by the user");
		String TransactionPackPaymentMode = getDriver().findElement(AMDMoreMenu.objTransactionPackPaymentMode1)
				.getText();
		logger.info(TransactionPackPaymentMode);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackPaymentMode1, "Transaction Pack PaymentMode")) {
			logger.info(TransactionPackPaymentMode + " : Transaction pack payment mode is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackPaymentMode + " : Transaction pack payment mode is displayed for the user ");
		} else {

			logger.info(TransactionPackPaymentMode + " : Transaction pack payment mode is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackPaymentMode + " : Transaction pack payment mode is not displayed for the user ");
		}

		String TransactionPackCountryDetails = getDriver().findElement(AMDMoreMenu.objTransactionPackCountry1)
				.getText();
		logger.info(TransactionPackCountryDetails);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackCountry1, "Transaction Pack Country Details")) {
			logger.info(
					TransactionPackCountryDetails + " : Transaction pack Country details is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackCountryDetails + " : Transaction pack Country details is displayed for the user ");
		} else {

			logger.info(TransactionPackCountryDetails
					+ " : Transaction pack Country details is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen", TransactionPackCountryDetails
					+ " : Transaction pack Country details is not displayed for the user ");
		}

		String TransactionPackAutoRenewal = getDriver().findElement(AMDMoreMenu.objTransactionPackAutoRenewal1)
				.getText();
		logger.info(TransactionPackAutoRenewal);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackAutoRenewal1, "Transaction Pack Auto-renewal status")) {
			logger.info(
					TransactionPackAutoRenewal + " : Transaction pack Auto-renewal details is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackAutoRenewal + " : Transaction pack Auto-renewal details is displayed for the user ");
		} else {

			logger.info(TransactionPackAutoRenewal
					+ " : Transaction pack Auto-renewal details is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen", TransactionPackAutoRenewal
					+ " : Transaction pack Auto-renewal details is not displayed for the user ");
		}

		extent.HeaderChildNode("Verify status of Transaction is Shown according to the duration of the pack");
		String TransactionPackStatus = getDriver().findElement(AMDMoreMenu.objTransactionPackStatus1).getText();
		logger.info(TransactionPackStatus);
		if (verifyElementExist(AMDMoreMenu.objTransactionPackStatus1, "Transaction Pack Status")) {
			logger.info(TransactionPackStatus + " : Transaction pack Status details is displayed for the user ");
			extent.extentLogger("MyTransactions Screen",
					TransactionPackStatus + " : Transaction pack Status details is displayed for the user ");
		} else {

			logger.info(TransactionPackStatus + " : Transaction pack Status details is not displayed for the user ");
			extent.extentLoggerFail("MyTransactions Screen",
					TransactionPackStatus + " : Transaction pack Status details is not displayed for the user ");
		}

		extent.HeaderChildNode("Verify download Invoice CTA below My transactions");
		if (verifyElementExist(AMDMoreMenu.objDownloadInvoice1, "Download invoice")) {
			logger.info("Download invoice CTA is displayed under My Transactions Screen");
			extent.extentLogger("MyTransactions Screen",
					"Download invoice CTA is displayed under My Transactions Screen");
		} else {
			logger.info("Download invoice CTA is not displayed under My Transactions Screen");
			extent.extentLoggerFail("MyTransactions Screen",
					"Download invoice CTA is not displayed under My Transactions Screen");
		}
		click(AMDMoreMenu.objDownloadInvoice1, "Download invoice");
		Back(3);

		ZNALogoutMethod();
		ValidateMyTransactionScreenWithSubscribeNowCTA();
		ZNALogoutMethod();
		ValidateDiscountPack(userType);
	}

	public void MyTransactionsWithDetails() throws Exception {
		extent.HeaderChildNode("Verify My Transactions with details");
		for (int i = 0; i < findElements(AMDMoreMenu.objTransactionDate).size(); i++) {
			if (findElements(AMDMoreMenu.objTransactionDate).get(i).isDisplayed()) {
				logger.info("Date of transaction in My Transaction page is displayed");
				extent.extentLogger("My Transactions", "Date of transaction in My Transaction page is displayed");
			} else {
				logger.error("Date of transaction in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions", "Date of transaction in My Transaction page is displayed");
			}
		}
		for (int j = 0; j < findElements(AMDMoreMenu.objTransactionPackName).size(); j++) {
			if (findElements(AMDMoreMenu.objTransactionPackName).get(j).isDisplayed()) {
				logger.info("Transaction pack name in My Transaction page is displayed");
				extent.extentLogger("My Transactions", "Transaction pack name in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack name in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions", "Transaction pack name in My Transaction page is displayed");
			}
		}
		for (int k = 0; k < findElements(AMDMoreMenu.objTransactionPackDuration).size(); k++) {
			if (findElements(AMDMoreMenu.objTransactionPackDuration).get(k).isDisplayed()) {
				logger.info("Transaction pack duration in My Transaction page is displayed");
				extent.extentLogger("My Transactions", "Transaction pack duration in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack duration in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack duration in My Transaction page is displayed");
			}
		}
		Swipe("UP", 1);

		for (int l = 0; l < findElements(AMDMoreMenu.objTransactionPackRental).size(); l++) {
			if (findElements(AMDMoreMenu.objTransactionPackRental).get(l).isDisplayed()) {
				logger.info("Transaction pack rental details in My Transaction page is displayed");
				extent.extentLogger("My Transactions",
						"Transaction pack rental details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack rental details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transaction",
						"Transaction pack rental details in My Transaction page is displayed");
			}
		}
		for (int m = 0; m < findElements(AMDMoreMenu.objTransactionPackPaymentMode).size(); m++) {
			if (findElements(AMDMoreMenu.objTransactionPackPaymentMode).get(m).isDisplayed()) {
				logger.info("Transaction pack payment mode details in My Transaction page is displayed");
				extent.extentLogger("My Transactions",
						"Transaction pack payment mode details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack payment mode details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack payment mode details in My Transaction page is displayed");
			}
		}

		for (int n = 0; n < findElements(AMDMoreMenu.objTransactionPackCountry).size(); n++) {
			if (findElements(AMDMoreMenu.objTransactionPackCountry).get(n).isDisplayed()) {
				logger.info("Transaction pack Country details in My Transaction page is displayed");
				extent.extentLogger("My Transactions",
						"Transaction pack Country details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack Country details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack Country details in My Transaction page is displayed");
			}
		}
		for (int o = 0; o < findElements(AMDMoreMenu.objTransactionPackAutoRenewal).size(); o++) {
			if (findElements(AMDMoreMenu.objTransactionPackAutoRenewal).get(o).isDisplayed()) {
				logger.info("Transaction pack auto renewal details in My Transaction page is displayed");
				extent.extentLogger("My Transactions",
						"Transaction pack auto renewal details in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack auto renewal details in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack auto renewal details in My Transaction page is displayed");
			}
		}
		for (int p = 0; p < findElements(AMDMoreMenu.objTransactionPackStatus).size(); p++) {
			if (findElements(AMDMoreMenu.objTransactionPackStatus).get(p).isDisplayed()) {
				logger.info("Transaction pack status in My Transaction page is displayed");
				extent.extentLogger("My Transactions", "Transaction pack status in My Transaction page is displayed");
			} else {
				logger.error("Transaction pack status in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions",
						"Transaction pack status in My Transaction page is displayed");
			}
		}
		for (int q = 0; q < findElements(AMDMoreMenu.objDownloadInvoice).size(); q++) {
			if (findElements(AMDMoreMenu.objDownloadInvoice).get(q).isDisplayed()) {
				logger.info("Download Invoice CTA My in Transaction page is displayed");
				extent.extentLogger("My Transactions", "Download Invoice CTA in My Transaction page is displayed");
			} else {
				logger.error("Download Invoice CTA in My Transaction page is displayed");
				extent.extentLoggerFail("My Transactions", "Download Invoice CTA My in Transaction page is displayed");
			}
		}
		Swipe("DOWN", 1);
	}

	public void ValidateMyTransactionScreenWithSubscribeNowCTA() throws Exception {
		extent.HeaderChildNode("Verrify Subscribe Now CTA in My Transactions Screen");
		click(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfile, "Profile");
		waitTime(2000);

		String NonsubscribedUserWithNoTransactionUsername = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithNoTransactionUsername");
		String NonsubscribedUserWithNoTransactionPassword = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithNoTransactionPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

		LoginWithEmailID(NonsubscribedUserWithNoTransactionUsername, NonsubscribedUserWithNoTransactionPassword);
		waitTime(2000);
		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
		waitTime(2000);
		verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My TransactionsOption");
		waitTime(2000);

		if (verifyElementExist(AMDMoreMenu.objSubscribeNowCTA, "Subscribe Now CTA")) {
			logger.info(
					"Subscribe Now CTA is displayed under My Transactions Screen for no transaction done by the registered user");
			extent.extentLogger("MyTransactions Screen",
					"Subscribe Now CTA is displayed under My Transactions Screen for no transaction done by the registered user");
		} else {
			logger.info(
					"Subscribe Now CTA is not displayed under My Transactions Screen for no transaction done by the registered user");
			extent.extentLoggerFail("MyTransactions Screen",
					"Subscribe Now CTA is not displayed under My Transactions Screen for no transaction done by the registered user");
		}
		Back(2);
	}

	public void ValidateDiscountPack(String userType) throws Exception {
		extent.HeaderChildNode("Verify Discount rentals shown for the purchased pack");
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			click(AMDHomePage.objMoreMenu, "More Menu");
			click(AMDMoreMenu.objProfile, "Profile");
			waitTime(2000);

			String PackwithDiscountAmountUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("PackWithDiscountUsername");
			String PackwithDiscountAmountPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("PackWithDiscountPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

			LoginWithEmailID(PackwithDiscountAmountUsername, PackwithDiscountAmountPassword);

			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			waitTime(2000);
			verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My TransactionsOption");
			waitTime(2000);

			String TransactionPackRental = getDriver().findElement(AMDMoreMenu.objTransactionPackRental1).getText();
			logger.info(TransactionPackRental);
			if (verifyElementExist(AMDMoreMenu.objTransactionPackRental1, "Transaction PackRental")) {
				logger.info(TransactionPackRental + " : Discount Transaction pack rental is displayed for the user ");
				extent.extentLogger("MyTransactions Screen",
						TransactionPackRental + " : Discount Transaction pack rental is displayed for the user ");
			} else {

				logger.info(
						TransactionPackRental + " : Discount Transaction pack rental is not displayed for the user ");
				extent.extentLoggerFail("MyTransactions Screen",
						TransactionPackRental + " : Discount Transaction pack rental is not displayed for the user ");
			}
			Back(2);
		} else {
			logger.info("Discount rental pack details is not applicable for the " + userType);
			extent.extentLogger("MyTransactions Screen",
					"Discount rental pack details is not applicable for the " + userType);
		}

	}

	public void ValidateMyTransactionWithInActivePack() throws Exception {
		extent.HeaderChildNode("Validating My Transactions Screen with Active Pack");
		click(AMDHomePage.objMoreMenu, "More Menu");
		click(AMDMoreMenu.objProfile, "Profile");
		waitTime(2000);

		String NonsubscribedUserWithInactivePackUserName = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackUsername");
		String NonsubscribedUserWithInActivePackPassword = Reporter.getCurrentTestResult().getTestContext()
				.getCurrentXmlTest().getParameter("NonsubscribedUserWithInActivePackPassword");
		verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

		LoginWithEmailID(NonsubscribedUserWithInactivePackUserName, NonsubscribedUserWithInActivePackPassword);
	}

	public void navigateToHomecreenFromIntroScreen() throws Exception {
		System.out.println("\nNavigation to Home Screen");

		if (pUserType.equals("Guest")) {
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
			waitTime(3000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			verifyElementPresent(AMDHomePage.objHomeTab, "Home landing screen");
		} else {
			logger.info(pUserType + " is already in the Home screen");
			extent.extentLogger("Home Screen", pUserType + " is already in the Home screen");
		}
	}

	public void SelectTopNavigationTab(String pTabname) throws Exception {
		System.out.println("\nSelecting " + pTabname + " from Top navigation tabs");

		verifyElementPresentAndClick(AMDHomePage.objHome, "Home button");
		int noOfTabs = getCount(AMDHomePage.objTitle);
		System.out.println("\nTop Navigation Tabs: " + noOfTabs);
		for (int k = 1; k <= noOfTabs; k++) {
			if (verifyIsElementDisplayed(AMDGenericObjects.objPageTitle(pTabname))) {
				click(AMDGenericObjects.objPageTitle(pTabname), pTabname);
				break;
			} else {
				List<WebElement> element = getDriver().findElements(By.xpath("//*[@id='title']"));
				element.get(noOfTabs - 1).click();
				waitTime(1000);
			}
		}
	}

	/**
	 * Author : Manasa Module : Settings
	 */

	public void DownloadOverWiFiOnlyONValidation(String userType, String searchKeyword1) throws Exception {
		if (!(userType.equalsIgnoreCase("Guest"))) {
			extent.HeaderChildNode("Download over WiFi only ON state Validation");

//			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu tab");
//			verifyElementPresentAndClick(AMDMoreMenu.objSettings, "Settings option");
			waitTime(2000);
			verifyElementPresentAndClick(AMDSettingsScreen.objDownloadOverWifiToggle, "Wifi On toggle");
			Back(1);
			waitTime(2000);
			Back(1);
			verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
			verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
			type(AMDSearchScreen.objSearchBoxBar, searchKeyword1 + "\n", "Search bar");
			waitTime(2000);
			hideKeyboard();
			// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
			waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

			verifyElementPresentAndClick(AMDMoreMenu.objSearchResult(searchKeyword1), "Search result");

			verifyElementPresentAndClick(AMDMoreMenu.objDownloadIcon, "Download icon");
			verifyElementPresentAndClick(AMDMoreMenu.objDataSaver, "Data Saver option");
			verifyElementPresentAndClick(AMDMoreMenu.objStartDownload, "Start Download");

			String wifi = "";
			String cmd = ("/usr/local/bin/adb shell dumpsys \"wifi | grep 'Wi-Fi is'\"");
			Process p = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			wifi = br.readLine();
			logger.info("Wifi status :: " + wifi.toString());

			if (wifi.equalsIgnoreCase("Wi-Fi is enabled")) {
				logger.info("Content is downloading on Wifi network");
				extent.extentLogger("Download", "Content is downloading on Wifi network");
			} else {
				logger.info("Content is not downloading on Wifi network");
				extent.extentLoggerFail("Download", "Content is not downloading on Wifi network");
			}
			Back(1);
		}
	}

	/**
	 * Author : Sushma Module : Player
	 */
	public void PlayerPotrait() throws Exception {
		extent.HeaderChildNode("Potrait mode validation");

		// ----Searching for content to navigate to consumption page----//
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, "Shivaji Surathkal" + "\n", "Search bar");
		hideKeyboard();
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		verifyElementPresentAndClick(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(3000);

		// ----verifying Player screen-----//
		if (verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen")) {
			waitTime(3000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresent(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(8000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objPlayer)) {
				logger.info("Player controls are not auto hide after keeping the player ideal for few sec's");
				extentLoggerFail("Player controls Auto hide",
						"Player controls are not auto hide after keeping the player ideal for few sec's");
			} else {
				logger.info("Player controls are auto hide after keeping the player ideal for few sec's");
				extentLogger("Player controls Auto hide",
						"Player controls are auto hide after keeping the player ideal for few sec's");
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			verifyElementPresent(AMDPlayerScreen.objPlayIcon, "Play icon");

			// ----------verifying Full screen icon--------//
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Full screen icon");
			waitTime(3000);
			GetAndVerifyOrientation("Landscape");
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Minimize icon");
			waitTime(3000);

			// -----------verifying Elapsed timer--------//
			String time1 = getText(AMDPlayerScreen.objTimer);
			int elapsedTime = timeToSec(time1);
			String time2 = getText(AMDPlayerScreen.objTotalDuration);
			int totalTime = timeToSec(time2);
			if (elapsedTime < totalTime) {
				logger.info("Elapsed timer is displayed");
				extentLogger("Elapsed timer", "Elapsed timer is displayed");
			} else {
				logger.info("Elapsed timer is not displayed");
				extentLoggerFail("Elapsed timer", "Elapsed timer is not displayed");
			}

			WebElement elementElapsedBtn = findElement(AMDPlayerScreen.objTimer);
			int etimeBtnleftX = elementElapsedBtn.getLocation().getX();
			int etimeBtnrightX = etimeBtnleftX + elementElapsedBtn.getSize().getWidth();
			int etimemiddleX = (etimeBtnrightX + etimeBtnleftX) / 2;

			if (etimemiddleX <= 200) {
				logger.info("Elapsed timer on left corner is displayed");
				extent.extentLogger("Elapsed time", "Elapsed timer on left corner is displayed");
			} else {
				logger.error("Elapsed timer on left corner is not displayed");
				extent.extentLoggerFail("Elapsed timer", "Elapsed timer on left corner is not displayed");
			}

			// -----------verifying Total Content Duration---------//
			WebElement elementTotDur = findElement(AMDPlayerScreen.objTotalDuration);
			int eleTotDurRightX = elementTotDur.getLocation().getX();
			Dimension sizee = getDriver().manage().window().getSize();
			int sizeee = sizee.getWidth() - 500;
			if (eleTotDurRightX >= sizeee) {
				logger.info("Content duration on right corner is displayed");
				extent.extentLogger("Content duration", "Content duration on right corner is displayed");
			} else {
				logger.info("Content duration on right corner is not displayed");
				extent.extentLoggerFail("Content duration", "Content duration on right corner is not displayed");
			}

			ForwardAndRewindThePlayerByDoubleTapping(1);
			waitTime(3000);
			ForwardAndRewindThePlayerByDoubleTapping(2);

			click(AMDPlayerScreen.objPlayIcon, "Play icon");

			verifyPlaybackAfterLockAndUnlock();
			waitTime(5000);
			verifyPlaybackAfterMinimzeAndMaximizeAppFromBackground();
			waitTime(5000);
			verifyPlaybackAfterNetworkInterruption();

			// ------verifying the seek functionality of the Progressbar------//

			seekVideo(AMDPlayerScreen.objProgressBar);
			waitTime(3000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			click(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(3000);
			seekVideoTillLast(AMDPlayerScreen.objProgressBar);
			click(AMDPlayerScreen.objPlayIcon, "Play icon");
			verifyElementExist(AMDPlayerScreen.objGetPremiumPopUp,
					"Get Premium popup at the end of the non premum trailer content playback");
//			verifyElementPresentAndClick(AMDPlayerScreen.objBackButton, "Back button");
//			if(verifyIsElementDisplayed(AMDSearchScreen.objFirstContentInSearchResult)) {
//				logger.info("User is navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
//				extentLogger("Back button", "User is navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
//			}else {
//				logger.info("User is not navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
//				extentLoggerFail("Back button", "User is not navigated back to the previous screen from where the content is accessed post tapping on Back button in Player screen");
//			}
		}
	}

	public void verifyPlaybackAfterNetworkInterruption() throws Exception {
		extent.HeaderChildNode("Validation of content playback after Network Interruption");
		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);
		System.out.println(startTime);
		logger.info("Time before network interruption : " + startTime);
		extentLogger("Time", "Time before network interruption : " + startTime);
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi disable");
		waitTime(5000);
		verifyElementPresent(AMDPlayerScreen.objRetryBtn, "Retry CTA");
		Runtime.getRuntime().exec("/usr/local/bin/adb shell svc wifi enable");
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);
		logger.info("Time after network is connected : " + elapsedTime);
		extentLogger("Time", "Time after network is connected : " + elapsedTime);
		if (elapsedTime > startTime) {
			logger.info("Content playback is resumed after network is connected");
			extentLogger("Elapsed time", "Content playback is resumed after network is connected");
		} else {
			logger.info("Content playback is not resumed after network is connected");
			extentLoggerFail("Elapsed time", "Content playback is not resumed after network is connected");
		}

	}

	public void movieWithoutTrailer() throws Exception {
		extent.HeaderChildNode("InLineSubscription link");
		waitTime(2000);
		clearField(AMDSearchScreen.objSearchEditBox, "Search Box");
		waitTime(2000);
		type(AMDSearchScreen.objSearchBoxBar, "Chemistry of Kariyappa" + "\n", "Search bar");
		hideKeyboard();
		// closeInterstitialAd(AMDGenericObjects.objCloseInterstitialAd, 2000);
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);
		verifyElementPresentAndClick(AMDSearchScreen.objFirstContentInSearchResult, "Search result");
		waitTime(3000);
		verifyElementExist(AMDPlayerScreen.objPremiumTextOnPlayer, "Subscription required text");
		verifyElementPresentAndClick(AMDPlayerScreen.objSubscribeNowLinkOnPlayer, "Subscribe Now Link");
		if (verifyElementExist(AMDPlayerScreen.objGetPremiumPopUp, "Get Premium popUp")) {
			logger.info("User is navigated to Get premium popup post tapping on Subscribe Now Link");
			extentLogger("GetPremium popUp",
					"User is navigated to Get premium popup post tapping on Subscribe Now Link");
		} else {
			logger.info("User is not navigated to Get premium popup post tapping on Subscribe Now Link");
			extentLoggerFail("GetPremium popUp",
					"User is not navigated to Get premium popup post tapping on Subscribe Now Link");
		}
		Back(1);
		verifyElementExist(AMDPlayerScreen.objLoginTextOnPlayer, "Login required text");
		verifyElementPresentAndClick(AMDPlayerScreen.objLoginLinkOnPlayer, "Login link");
		if (verifyElementExist(AMDLoginScreen.objLoginOrRegisterPageTitle, "Login/Register screen")) {
			logger.info("User is navigated to Login/Register screen post tapping on Login link");
			extentLogger("Login/Register screen",
					"User is navigated to Login/Register screen post tapping on Login link");
		} else {
			logger.info("User is navigated to Login/Register screen post tapping on Login link");
			extentLoggerFail("Login/Register screen",
					"User is navigated to Login/Register screen post tapping on Login link");
		}
		Back(1);
		waitTime(2000);
		Back(1);
	}

	public void seekVideoTillLast(By byLocator1) {
		WebElement element = getDriver().findElement(byLocator1);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		int startX = startx + 100;
		System.out.println(startX);
		SwipeAnElement(element, startX, 0);
	}

	@SuppressWarnings("rawtypes")
	public void ForwardAndRewindThePlayerByDoubleTapping(int n) throws Exception {

		Dimension sizee = getDriver().manage().window().getSize();
		int rightXValue = sizee.getWidth() - 100;
		int YValue = Integer.valueOf(getAttributValue("y", AMDPlayerScreen.objNextIcon));
		touchAction = new TouchAction(getDriver());

		// --------verifying Fast rewind funcionality of the player------//
		String time11 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time11);
		int timebeforeRewind = timeToSec(time11);
		logger.info("Time before rewind in seconds: " + timebeforeRewind);
		extentLogger("Rewind", "Time before rewind in seconds: " + timebeforeRewind);

		for (int i = 0; i < n; i++) {
			touchAction.press(PointOption.point(100, YValue)).release().perform().press(PointOption.point(100, YValue))
					.release().perform();
		}

		verifyElementExist(AMDPlayerScreen.objTimer, "Elapsed timer is displayed");
		String time12 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time12);
		int timeAfterRewind = timeToSec(time12);
		logger.info("Time after rewind in seconds: " + timeAfterRewind);
		extentLogger("Rewind", "Time after rewind in seconds: " + timeAfterRewind);
		int diffInTime = timebeforeRewind - timeAfterRewind;
		if (diffInTime == (n * 10)) {
			logger.info("user is able to fast rewind the playback on double tapping " + n
					+ "times in the player screen for " + (n * 10) + "secs");
			extentLogger("Rewind the playback", "user is able to fast rewind the playback on double tapping " + n
					+ "times in the player screen for " + (n * 10) + "secs");
		} else {
			logger.info("user is not able to fast rewind the playback on double tapping " + n
					+ "times in the player screen for " + (n * 10) + "secs");
			extentLoggerFail("Rewind the playback", "user is not able to fast rewind the playback on double tapping "
					+ n + "times in the player screen for " + (n * 10) + "secs");
		}
		waitTime(5000);

		// --------verifying Fast forward funcionality of the player------//
		String time21 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time21);
		int timebeforeforward = timeToSec(time21);
		logger.info("Time before forward in seconds: " + timebeforeforward);
		extentLogger("Rewind", "Time before forward in seconds: " + timebeforeforward);
		System.out.println("Value :" + rightXValue + YValue);
		for (int i = 0; i < n; i++) {
			touchAction.press(PointOption.point(rightXValue, YValue)).release().perform()
					.press(PointOption.point(rightXValue, YValue)).release().perform();
		}

		verifyElementExist(AMDPlayerScreen.objTimer, "Elapsed timer is displayed");
		String time22 = getText(AMDPlayerScreen.objTimer);
		System.out.println(time22);
		int timeAfterforward = timeToSec(time22);
		logger.info("Time after forward in seconds: " + timeAfterforward);
		extentLogger("Forward", "Time after forward in seconds: " + timeAfterforward);

		diffInTime = timeAfterforward - timebeforeforward;
		if (diffInTime == (n * 10)) {
			logger.info("user is able to fast forward the playback on double tapping " + n
					+ "times in the player screen for " + (n * 10) + "secs");
			extentLogger("Forward the playback", "user is able to fast forward the playback on double tapping " + n
					+ "times in the player screen for " + (n * 10) + "secs");
		} else {
			logger.info("user is not able to fast forward the playback on double tapping " + n
					+ "times in the player screen for " + (n * 10) + "secs");
			extentLoggerFail("Forward the playback", "user is not able to fast forward the playback on double tapping "
					+ n + "times in the player screen for " + (n * 10) + "secs");
		}
	}

	/**
	 * Author : Manasa Module : Player
	 */
	public void playerValidationInFullScreenMode(String userType, String searchKeyword2) throws Exception {
		extent.HeaderChildNode("Player Validation in Fullscreen Mode");
		waitTime(5000);
		verifyElementPresentAndClick(AMDSearchScreen.objSearchIcon, "Search icon");
		verifyElementPresentAndClick(AMDSearchScreen.objSearchEditBox, "Search Box");
		type(AMDSearchScreen.objSearchBoxBar, searchKeyword2 + "\n", "Search bar");
		waitTime(2000);
		hideKeyboard();
		waitForElementDisplayed(AMDSearchScreen.objAllTab, 10);

		verifyElementPresentAndClick(AMDMoreMenu.objSearchResult(searchKeyword2), "Search result");
		waitTime(5000);
		// waitForPlayerAdToComplete("Video Player");
		waitTime(5000);
		// mandatoryRegistrationPopUp("Guest");
		if (verifyElementExist(AMDPlayerScreen.objPlayer, "Player screen")) {
			waitTime(3000);
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");
			verifyElementPresent(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(5000);
			if (verifyIsElementDisplayed(AMDPlayerScreen.objPlayer)) {
				logger.info("Player controls does not auto hide after keeping the player idle for few sec's");
				extentLoggerFail("Player controls Auto hide",
						"Player controls does not auto hide after keeping the player idle for few sec's");
			} else {
				logger.info("Player controls auto hide after keeping the player idle for few sec's");
				extentLogger("Player controls Auto hide",
						"Player controls auto hide after keeping the player idle for few sec's");
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player screen");

			verifyElementPresentAndClick(AMDPlayerScreen.objPauseIcon, "Pause icon");
			waitTime(2000);
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Maximize Icon");
			verifyElementPresent(AMDPlayerScreen.objProgressBar, "Progress/Seek Bar");
			verifyElementPresent(AMDPlayerScreen.objNextIcon, "Next Icon");
			verifyElementPresent(AMDPlayerScreen.objChromeCastIcon, "Chromecast Icon");

			if (findElement(AMDPlayerScreen.objChromeCastIcon).isEnabled() == false) {
				logger.info("Chrome cast icon is displayed in disable state");
				extent.extentLogger("Chrome cast", "Chrome cast icon is displayed in disable state");
			} else {
				logger.error("Chrome cast icon is not displayed in disable state");
				extent.extentLoggerFail("Chrome cast", "Chrome cast icon is not displayed in disable state");
			}

			verifyElementPresent(AMDPlayerScreen.objTitleOnPlayer, "Content Title on player");

			WebElement elementElapsedBtn = findElement(AMDPlayerScreen.objTimer);
			int etimeBtnleftX = elementElapsedBtn.getLocation().getX();
			int etimeBtnrightX = etimeBtnleftX + elementElapsedBtn.getSize().getWidth();
			int etimemiddleX = (etimeBtnrightX + etimeBtnleftX) / 2;

			if (etimemiddleX <= 200) {
				logger.info("Elapsed timer is displayed on left corner ");
				extent.extentLogger("Elapsed time", "Elapsed timer is displayed on left corner ");
			} else {
				logger.error("Elapsed timer is not displayed on left corner ");
				extent.extentLoggerFail("Elapsed timer", "Elapsed timer is not displayed on left corner ");
			}

			WebElement elementTotDur = findElement(AMDPlayerScreen.objTotalDuration);
			int eleTotDurRightX = elementTotDur.getLocation().getX();
			Dimension sizee = getDriver().manage().window().getSize();
			int sizeee = sizee.getWidth() - 500;
			if (eleTotDurRightX >= sizeee) {
				logger.info("Content duration is displayed on right corner");
				extent.extentLogger("Content duration", "Content duration is displayed on right corner");
			} else {
				logger.info("Content duration is not displayed  on right corner");
				extent.extentLoggerFail("Content duration", "Content duration is not displayed  on right corner");
			}

			verifyElementPresentAndClick(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with three dots");
			String quality = getText(AMDPlayerScreen.objQuality);
			if (quality.contains("Auto")) {
				logger.info("Video Quality is set to Auto by default");
				extent.extentLogger("Video Quality", "Video Quality is set to Auto by default");

			} else {
				logger.info("Video Quality is not set to Auto by default");
				extent.extentLoggerFail("Video Quality", "Video Quality is not set to Auto by default");
			}

			click(AMDPlayerScreen.objQuality, "Quality");

			List<WebElement> qualityOptions = findElements(AMDPlayerScreen.objQualityOptions);
			System.out.println(qualityOptions.size());
			for (int i = 0; i < qualityOptions.size(); i++) {
				String options = getText(AMDPlayerScreen.objQualityOptions(i + 1));
				logger.info("Quality option : " + options);
				extent.extentLogger("Video Quality", "Quality option : " + options);
			}
			Back(1);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with three dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objAddToWatchlist, "Add to Watchlist option");

			if (userType.equalsIgnoreCase("Guest")) {
				if (verifyElementExist(AMDOnboardingScreen.objScreenTitle, "Login/Register Page")) {
					logger.info("Navigated to Login/Registration screen");
					extent.extentLogger("Login page", "Navigated to Login/Registration screen");
					Back(1);
				} else {
					logger.info("Not Navigated to Login/Registration screen");
					extent.extentLoggerFail("Login page", "Not navigated to Login/Registration screen");
				}
			}
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
			click(AMDPlayerScreen.objThreeDotsOnPlayer, "Player option with three dots");
			verifyElementPresentAndClick(AMDPlayerScreen.objPlaybackRate, "Playback Rate option");

			List<WebElement> playbackRate = findElements(AMDPlayerScreen.objQualityOptions);

			for (int i = 0; i < playbackRate.size(); i++) {
				String options = getText(AMDPlayerScreen.objQualityOptions(i + 1));
				logger.info("Playback Rate option : " + options);
				extent.extentLogger("Video Quality", "Quality options : " + options);
			}
			String rate = getText(AMDPlayerScreen.objPlaybackRateSelected);
			System.out.println(rate);
			if (rate.equalsIgnoreCase("1.0X")) {
				logger.info("Playback rate is set to " + rate + " by default");
				extent.extentLogger("Video Quality", "Playback rate is set to " + rate + " by default");
			} else {
				logger.info("Playback rate is not set to 1.0X by default");
				extent.extentLoggerFail("Playback rate", "Playback rate is not set to 1.0X by default");
			}
			Back(1);
			click(AMDPlayerScreen.objPlayerScreen, "Player Frame");

			verifyPlaybackAfterMinimzeAndMaximizeAppFromBackground();
			waitTime(5000);
			verifyPlaybackAfterLockAndUnlock();
			waitTime(5000);
			verifyPlaybackAfterNetworkInterruption();

			click(AMDPlayerScreen.objShareIcon, "Share Icon");
			verifyElementPresent(AMDPlayerScreen.objSharePopUp, "Share Pop Up");
			verifyElementPresentAndClick(AMDPlayerScreen.objFacebook, "Facebook Icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDPlayerScreen.objFacebookPost, "Post Icon");
			waitTime(5000);
			click(AMDPlayerScreen.objShareIcon, "Share Icon");
			verifyElementPresentAndClick(AMDPlayerScreen.objTwitter, "Twitter Icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDPlayerScreen.objTweetButton, "Tweet Icon");
			waitTime(5000);
			verifyElementPresentAndClick(AMDPlayerScreen.objFullscreenIcon, "Minimize Icon");
			waitTime(2000);
			seekVideo(AMDPlayerScreen.objProgressBar);
			GetAndVerifyOrientation("Portrait");

		}
	}

	public void adPlay() throws Exception {
		waitTime(5000);
		if (verifyIsElementDisplayed(AMDPlayerScreen.objAd)) {
			logger.info("Ad play is in progress");
			extent.extentLogger("AdPlayInProgress", "Ad play is in progress");

			waitForElementDisplayed(AMDPlayerScreen.objProgressBar, 120);
		} else {
			logger.info("Ad did not play");
			extent.extentLogger("Ad", "Ad did not play");
		}
	}

	/**
	 * Seek by element
	 * 
	 * @param byLocator
	 */

	public void seekVideo(By byLocator) throws Exception {
		click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		String beforeSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time before seeking : " + timeToSec(beforeSeek));
		extent.extentLogger("Seek", "Current time before seeking in seconds: " + timeToSec(beforeSeek));
		WebElement element = getDriver().findElement(byLocator);
		Dimension size = element.getSize();
		int startx = (int) (size.width);
		click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		SwipeAnElement(element, startx, 0);
		logger.info("Scrolling the seek bar");
		extent.extentLogger("Seek", "Scrolling the seek bar");
		waitTime(2000);
		String afterSeek = findElement(AMDPlayerScreen.objTimer).getText();
		logger.info("Current time after seeking in seconds : " + timeToSec(afterSeek));
		extent.extentLogger("Seek", "Current time after seeking in seconds : " + timeToSec(afterSeek));

		if (timeToSec(afterSeek) > timeToSec(beforeSeek)) {
			logger.info("Seek bar is functional");
			extent.extentLogger("Seek", "Seek bar is functional");
		} else {
			logger.info("Seek bar is not functional");
			extent.extentLoggerFail("Seek", "Seek bar is not functional");
		}
	}

	public void SwipeAnElement(WebElement element, int posx, int posy) {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(posx, posy))
				.release().perform();
	}

	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		// System.out.println(t.length);
		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}
		return num;
	}

	/**
	 * Video Player or Live Player Ad verify
	 * 
	 * @param playerType
	 * @throws Exception
	 */
	public void waitForPlayerAdToComplete(String playerType) throws Exception {
		boolean adWasDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		waitTime(5000);
		main: for (int trial = 0; trial < 120; trial++) {
			try {
				findElement(PWAPlayerPage.objAd);
				adWasDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
					try {
						getWebDriver().findElement(PWAPlayerPage.objAd);
					} catch (Exception e) {
					}
				}
				if (Math.floorMod(trial, 15) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);

//				//SkipAD
//				if(verifyElementExist(PWAPlayerPage.objSkipAd, "SkipAd")){
//					Thread.sleep(5000);
//					click(PWAPlayerPage.objSkipAd, "SkipButton");					
//				}
//				else
//				{
//					System.out.println("No Skip Button Displayed");
//				}

			} catch (Exception e) {
				try {
					if (playerType.equals("Live Player")) {
						findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						findElement(PWAPlayerPage.objPlayerSeekBar);
					}
					playerDisplayed = true;
					confirmCount++;
					if (confirmCount == 1) {
						if (adWasDisplayed == false) {
							logger.info("Ad did not play");
							extent.extentLogger("AdDidNotPlay", "Ad did not play");
						} else {
							logger.info("Ad play complete");
							extent.extentLogger("AdPlayComplete", "Ad play complete");
						}
						break main;
					}
				} catch (Exception e1) {
				}
			}
		}
		if (playerDisplayed == false && adWasDisplayed == false) {
			logger.error("Ad did not play");
			extent.extentLogger("Ad", "Ad did not play");
		}
	}

	/**
	 * Get the Mobile Orientation
	 */
	public void GetAndVerifyOrientation(String Orientation) {
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		System.out.println(ScreenOrientation);
		if (Orientation.equalsIgnoreCase(ScreenOrientation)) {
			logger.info("The screen Orientation is " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
		} else {
			logger.error("The screen Orientation is not " + ScreenOrientation);
			extent.extentLoggerFail("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
		}
	}

	/**
	 * Author : Vinay Module : More screen Screen : Account details , Buy
	 * subscription, My subscription , My Transaction
	 */
	public void AccountDetails(String userType) throws Exception {
		if (userType.equals("Guest")) {
			MoreScreenGuestUserValidations(userType);
		} else {
			AccountDetailsLoggedInUser(userType);
		}
	}

	public void MoreScreenGuestUserValidations(String userType) throws Exception {
		/*
		 * Account Details section
		 */
		extent.HeaderChildNode("Account Details Validation for User type : " + userType);
		// Click on More button
		click(AMDHomePage.MoreMenuIcon, "More icon");
		// Verify Login/Register for better experience is displayed in Profile screen
		verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Login/Register for best experience text");
		// Verify user is navigated to Login/Register screen post tapping Login/Register
		// link
		click(AMDMoreMenu.objLoginRegisterText, "Login/Registet link");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Login/Register")) {
			extent.extentLogger("Verify navigation", "User is navigated to " + header
					+ " screen post tapping Login/Register link from more menu screen ");
			logger.info("User is navigated to " + header
					+ " screen post tapping Login/Register link from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping Login/Register link from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping Login/Register link from more menu screen");
		}

		// Verify user is navigated back to previous screen on clicking back button
		// click back button
		click(AMDGenericObjects.objBackBtn, "Back button");
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the previous screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the previous screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the previous screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the previous screen post tapping back button from Login/Register screen");
		}

		BuySubscription(userType);

		/*
		 * My Subscription section
		 */
		extent.HeaderChildNode("My Subscription screen validations for user type : " + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Subscription
		extent.HeaderChildNode("My Subscription screen");
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		if (header.equals("Login/Register")) {
			extent.extentLogger("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping My Subscription from more menu screen");
		}

		// Verify user is navigated back to More menu screen on clicking back button
		// click back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
		}

		/*
		 * My Transactions section
		 */
		extent.HeaderChildNode("My Transactions screen validations for user type : " + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Transactions
		extent.HeaderChildNode("My Transactions screen");
		verifyElementPresentAndClick(AMDMoreMenu.objMyTransactions, "My Transactions");
		if (header.equals("Login/Register")) {
			extent.extentLogger("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Transactions from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Transactions from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to Login/Register screen post tapping My Transactions from more menu screen ");
			logger.info(
					"User is not navigated to Login/Register screen post tapping My Transactions from more menu screen");
		}

		// Verify user is navigated back to More menu screen on clicking back button
		// click back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Login/Register screen");
		}

	}

	/**
	 * Buy subscription section
	 */
	public void BuySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Buy Subscription screen validations for user type :" + userType);

		// Verify User is navigated to subscribe screen post tapping Buy Subscription
		verifyElementPresentAndClick(AMDMoreMenu.objBuySubscription, "Buy Subscription");
		String screenTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (screenTitle.equals("Subscribe")) {
			extent.extentLogger("Verify Navigation",
					"User is navigated to Subscribe screen post tapping Buy subscription from more menu");
			logger.info("User is navigated to Subscribe screen post tapping Buy subscription from more menu");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to Subscribe screen post tapping Buy subscription from more menu");
			logger.info("User is not navigated to Subscribe screen post tapping Buy subscription from more menu");
		}
		// Verify the back button functionality

		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to More menu screen post tapping back button from Subscribe screen");
			logger.info("User is navigate back to More menu screen post tapping back button from Subscribe screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from Subscribe screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from Subscribe screen");
		}
	}

	public void AccountDetailsLoggedInUser(String userType) throws Exception {
		extent.HeaderChildNode("More Menu - Profile validations for user type : " + userType);
		// Verify that Profile details are displayed
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");

		// Verify user name is displayed
		if (verifyElementPresent(AMDMoreMenu.objUserName, "User name")) {
			String profileName = getText(AMDMoreMenu.objUserName);
			extent.extentLogger("Verify profile name is displayed",
					"Profile name for the logged in user is :" + profileName);
			logger.info("Profile name for the logged in user is :" + profileName);
		} else {
			extent.extentLoggerFail("Verify profile name is displayed",
					"Profile name is not displayed for the logged in user");
			logger.info("Profile name is not diaplayed for the logged in user");
		}
		// Verify Email id displayed
		if (verifyElementPresent(AMDMoreMenu.objLoginRegisterText, "Email ID")) {
			String EmailID = getText(AMDMoreMenu.objLoginRegisterText);
			extent.extentLogger("Verify Email ID is displayed", "Email ID for the logged in user is :" + EmailID);
			logger.info("Email ID for the logged in user is :" + EmailID);
		} else {
			extent.extentLoggerFail("Verify Email ID is displayed", "Email ID is not displayed for the logged in user");
			logger.info("Emial ID is not diaplayed for the logged in user");
		}
		// click on profile
		verifyElementPresentAndClick(AMDMoreMenu.objLoginRegisterText, "Profile Icon");
		if (userType.equals("SubscribedUser")) {
			extent.HeaderChildNode("Plan Info unit validations for" + userType);
			// verify plan info unit is displayed
			if (verifyIsElementDisplayed(AMDMyProfileScreen.aobjAccessPackDetailsInfo)) {
				extent.extentLogger("Verify access details pack unit",
						"Plan Info unit is displayed for the Subscribed user");
				logger.info("Plan Info unit is displayed for the Subscribed user");

				// Verify Plan Info unit
				String planType = getText(AMDMyProfileScreen.objPlanType);
				String duration = getText(AMDMyProfileScreen.objPlanDuration);
				String price = getText(AMDMyProfileScreen.objPlanPrice);
				String validity = getText(AMDMyProfileScreen.objPackStatus);
				extent.extentLogger("Verify the plan type", "The Paln type :" + planType);
				logger.info("The Paln type :" + planType);
				extent.extentLogger("Verify the plan duration", "The  duration of the Paln  :" + duration);
				logger.info("The  duration of the Paln  : " + duration);
				extent.extentLogger("Verify the plan price", "The Paln price :" + price);
				logger.info("The Paln price :" + price);
				extent.extentLogger("Verify the plan validity", "The Paln validity :" + validity);
				logger.info("The Paln validity :" + validity);

				// Verify Details CTA
				verifyElementPresentAndClick(AMDMyProfileScreen.objDetailsCTA, "Details CTA");
				// Verify user is navigated to My subscription page
				String header = getText(AMDGenericObjects.objgetScreenTitle);
				if (header.equals("My Subscriptions")) {
					extent.extentLogger("Verify Navigation", "User is navigated to  screen : " + header);
					logger.info("User is navigated to  screen : " + header);
				} else {
					extent.extentLoggerFail("Verify Navigation",
							"User is navigated not navigated to My Subscription screen");
					logger.info("User is navigated not navigated to My Subscription screen");
				}

			} else {
				extent.extentLoggerFail("Verify access details pack unit",
						"Plan Info unit is not displayed for the Subscribed user");
				logger.info("Plan Info unit is  not displayed for the Subscribed user");
			}
			Back(1);
		}

		ChangePasswordScreen();
		MobileAndOTPScreenValidations();
		Back(1);
		Swipe("Down", 1);
		MyProfileScreen(userType);
		EditProfileScreen(userType);
		SocialLoginValidationsForEditProfileScreen(userType);
		Back(1);
		Swipe("Down", 1);
		BuySubscription(userType);
		if (userType.equals("SubscribedUser")) {
			verifyMySubscription(userType);
		}
		if (userType.equals("NonSubscribedUser")) {
			MySubscriptionValidations(userType);
		}
		verifyMyTransactions(userType);
	}

	/**
	 * Change Password screen
	 */
	public void ChangePasswordScreen() throws Exception {
		// Verify Change password screen
		extent.HeaderChildNode("Change Password Screen validations");
		// click(AMDMoreMenu.objLoginRegisterText,"Profile");
		verifyElementPresentAndClick(AMDMyProfileScreen.objChangePassword, "Change Password");
		// Verify the header
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("Change Password")) {
			extent.extentLogger("Verify Header", "The header of the screen is : " + header);
			logger.info("The header of the screen is : " + header);
		} else {
			extent.extentLoggerFail("Verify Header", "Change Password header is not displayed");
			logger.info("Change Password header is not displayed");
		}
		// verify back button is displayed
		verifyElementPresent(AMDGenericObjects.objBackBtn, "Back button");
		// Verify Current, New and Confirm fields are displayed
		verifyElementPresent(AMDChangePasswordScreen.objCurrentPwdField, "Current Password field");
		verifyElementPresent(AMDChangePasswordScreen.objNewPwdField, "New Password field");
		verifyElementPresent(AMDChangePasswordScreen.objConfirmPwdField, "Confirm Password field");
		// Verify user can enter password in all the text fields
		// Old Password field
		VerifySpecialCharactersInString(AMDChangePasswordScreen.objCurrentPwdField, "1234%23abcd", "Current Password");
		// New Password field
		VerifySpecialCharactersInString(AMDChangePasswordScreen.objNewPwdField, "&*&1234abcd", "New Password");
		// Confirm password field
		VerifySpecialCharactersInString(AMDChangePasswordScreen.objConfirmPwdField, "1234@343%slv", "Confirm Password");
		Back(1);
	}

	/**
	 * My Profile screen validations
	 */

	public void MyProfileScreen(String userType) throws Exception {
		extent.HeaderChildNode("My Profile Screen validations");
		// Verify that profile detail screen is displayed post tapping Profile
		// click on profile
		click(AMDMoreMenu.objLoginRegisterText, "Profile");
		// Verify user is navigated to My Profile screen
		String headerTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (headerTitle.equals("My Profile")) {
			extent.extentLogger("Verify Navigation",
					"User is navigated to " + headerTitle + " post tapping profile form more menu");
			logger.info("User is navigated to " + headerTitle + " post tapping profile form more menu");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to My Profile screen post tapping profile form more menu");
			logger.info("User is not navigated to My Profile screen post tapping profile form more menu");
		}
		// Verify user profile information
		// Verify user name is displayed
		if (verifyElementDisplayed(AMDMyProfileScreen.objUserName)) {
			String profileName = getText(AMDMyProfileScreen.objUserName);
			extent.extentLogger("Verify Profile Name is displayed",
					"Profile Name :" + profileName + " is displayed for the logged in user");
			logger.info("Profile Name :" + profileName + " is displayed for the logged in user");
		} else {
			extent.extentLoggerFail("Verify Profile Name is displayed",
					"Profile Name is not displayed for logged in user");
			logger.info("Profile Name is not displayed for logged in user");
		}
		// Verify user ID is displayed
		if (verifyElementDisplayed(AMDMyProfileScreen.objUserID)) {
			String userID = getText(AMDMyProfileScreen.objUserID);
			extent.extentLogger("Verify User ID is displayed",
					"User ID :" + userID + " is displayed for the logged in user");
			logger.info("User ID :" + userID + " is displayed for the logged in user");
		} else {
			extent.extentLoggerFail("Verify User ID is displayed", "User ID is not displayed for logged in user");
			logger.info("User ID is not displayed for logged in user");
		}
		// Verify Edit CTA button
		if (verifyElementDisplayed(AMDMyProfileScreen.objEditProfileButton)) {
			extent.extentLogger("Verify Edit CTA", "Edit CTA is displayed in My Profile screen");
			logger.info("Edit CTA is displayed in My Profile screen");
		} else {
			extent.extentLoggerFail("Verify Edit CTA", "Edit CTA is not displayed in My Profile screen");
			logger.info("Edit CTA is  not displayed in My Profile screen");
		}
		// Verify user is navigated to Edit profile screen post tapping Edit CTA button
		// Click on Edit CTA
		click(AMDMyProfileScreen.objEditProfileButton, "Edit CTA button");
		// Verify user is navigated to Edit profile screen
		String headerName = getText(AMDGenericObjects.objgetScreenTitle);
		if (headerName.equals("Edit Profile")) {
			extent.extentLogger("Verify User navigation", "User is navigated to " + headerName + " screen");
			logger.info("User is navigated to " + headerName + " screen");
		} else {
			extent.extentLoggerFail("Verify User navigation", "User is not navigated screen");
			logger.info("User is not navigated to Edit Profile screen");
		}
		Back(1);
	}

	public void MobileAndOTPScreenValidations() throws Exception {
		extent.HeaderChildNode("Mobile Screen and OTP validataions");
		// Verify user can see the Country code drop down
		// Click on edit
		verifyElementPresentAndClick(AMDMyProfileScreen.objEditProfileButton, "Edit CTA");
		verifyElementPresent(AMDEditProfileScreen.objMobileNOCountry, "Country drop down");
		// Verify different country code are displayed
		click(AMDEditProfileScreen.objMobileNOCountry, "Country drop down");
		int size = findElements(By.xpath("//*[@id='selector_content']")).size();
		for (int i = 1; i <= size; i++) {
			if (verifyIsElementDisplayed(AMDEditProfileScreen.objDifferentCountries(i))) {
				extent.extentLogger("Verify Country codes",
						"Country code : " + getText(AMDEditProfileScreen.objDifferentCountries(i)) + " is displayed");
				logger.info(
						"Country code :" + getText(AMDEditProfileScreen.objDifferentCountries(i)) + " is displayed");
			} else {
				extent.extentLoggerFail("Verify Country code", "Country code is not displayed in drop down menu");
				logger.info("Country code is not displayed in drop down menu");
			}

		}
		click(AMDEditProfileScreen.objClosedropDownBtn, "Close button");
		hideKeyboard();
		// Verify user can edit the Mobile number field
		String OldNUmber = getText(AMDEditProfileScreen.objMobileNumberField);
		click(AMDEditProfileScreen.objMobileNumberField, "Mobile number field");
		type(AMDEditProfileScreen.objMobileNumberField, "9980353990", "Mobile Number Field");
		hideKeyboard();
		String NewNumber = getText(AMDEditProfileScreen.objMobileNumberField);
		if (!OldNUmber.equals(NewNumber)) {
			extent.extentLogger("Verify Mobile Number field", "User can edit Mobile number field");
			extent.extentLogger("Verify Mobile Number field",
					"Old Mobile number is : " + OldNUmber + " is updated to new number :" + NewNumber);
			logger.info("Old Mobile number is : " + OldNUmber + " is updated to new number :" + NewNumber);
		} else {
			extentLoggerFail("Verify Mobile Number field", "User can not edit the mobile number field");
			logger.info("User can not edit the mobile number field");
		}
		// Set Text
		clearField(AMDEditProfileScreen.objFirstNameField, "FirstName");
		type(AMDEditProfileScreen.objFirstNameField, "FirstName", "First Name");
		hideKeyboard();
		// Verify Save Changes is highlighted
		String saveChanges = getElementPropertyToString("enabled", AMDEditProfileScreen.objSaveChanges,
				"Save Changes CTA");
		if (saveChanges.equals("true")) {
			extent.extentLogger("Verify Save Changes CTA", "Save Changes CTA is Highlighted");
			logger.info("Save Changes CTA is Highlighted");
		} else {
			extent.extentLoggerFail("Verify Save Changes CTA", "Save Changes CTA is not Highlighted");
			logger.info("Save Changes CTA is not Highlighted");
		}
		click(AMDEditProfileScreen.objSaveChanges, "Save Changes CTA");
		// Verify user is navigated to Verify Mobile screen
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		extent.extentLogger("Verify user navigation", "User is navigated to " + header);
		logger.info("User is navigated to " + header);
		// Verify the OTP Validations

		verifyElementExist(AMDRegistrationScreen.objOTPTimer, "OTP timer");
		String OTPTimer1 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer1);
		click(AMDRegistrationScreen.objVerifyOtpButton, "Verify button");
		waitTime(10000);
		String OTPTimer2 = getText(AMDRegistrationScreen.objOTPTimer);
		logger.info(OTPTimer2);
		boolean Time = OTPTimer1.equals(OTPTimer2);
		if (Time == false) {
			logger.info("The Otp timer is displayed in Verify mobile screen");
			extentLogger("OtpTimer", "The Otp timer is displayed in Verify mobile screen");
		} else {
			logger.info("The Otp timer is not displayed in Verify mobile screen");
			extentLoggerFail("OtpTimer", "The Otp timer is not displayed in Verify mobile screen");
		}
		type(AMDRegistrationScreen.objOTPField1, "1", "OTP box1");
		type(AMDRegistrationScreen.objOTPField2, "1", "OTP box2");
		type(AMDRegistrationScreen.objOTPField3, "1", "OTP box3");
		type(AMDRegistrationScreen.objOTPField4, "1", "OTP box4");
		hideKeyboard();
		waitTime(2000);
		if (findElement(AMDRegistrationScreen.objVerifyOtpButton).isEnabled() == true) {
			logger.info("Verify Button is highlighted");
			extent.extentLogger("Verify", "Verify Button is highlighted");
		}

		Back(1);
	}

	public void VerifySpecialCharactersInString(By by, String pwd, String field) {
		getDriver().findElement(by).sendKeys(pwd);
		String text = getDriver().findElement(by).getText();
		Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher passWord = p.matcher(text);
		boolean b = passWord.find();
		if (b) {
			System.out.println("There is a special character in my string");
			extent.extentLogger("Verify password field",
					"User can enter Numbers, Special characters and Characters into " + field + " field");
			logger.info("User can enter Numbers, Special characters and Characters into " + field + " Passworrd field");
		} else {
			extent.extentLogger("Verify password field",
					"User can not enter Numbers, Special characters and Characters into " + field + " field");
			logger.info("User can not enter Numbers, Special characters and Characters into" + field + " field");
		}
	}

	/*
	 * Edit Profile screen validations
	 */
	public void EditProfileScreen(String userType) throws Exception {
		extent.HeaderChildNode("Edit Profile screen validations for user type :" + userType);
		verifyElementPresentAndClick(AMDMyProfileScreen.objEditProfileButton, "Edit CTA");
		// Verify header
		verifyElementPresent(AMDEditProfileScreen.objEditProfileTitle, "Edit Profile header");
		// Text field First name
		verifyElementPresent(AMDEditProfileScreen.objFirstNameField, "First Name text field");
		// Text field Last name
		verifyElementPresent(AMDEditProfileScreen.objLastNameField, "Last Name text field");
		// Email text field
		verifyElementPresent(AMDEditProfileScreen.objEmailIDField, "Email text field");
		// Mobile number text field
		verifyElementPresent(AMDEditProfileScreen.objMobileNoField, "Mobile number text field");
		// Date of Birth field
		verifyElementPresent(AMDEditProfileScreen.objDOBTxtField, " DOB text field");
		// Gender field
		verifyElementPresent(AMDEditProfileScreen.objGenderDropdown, "Gender field");
		// Verify Save changes button
		verifyElementPresent(AMDEditProfileScreen.objSaveChanges, "Save Changes button");
		// Verify Save changes button is disabled
		String disabled = getElementPropertyToString("enabled", AMDEditProfileScreen.objSaveChanges, "Save Changes");
		if (disabled.equals("false")) {
			extent.extentLogger("Verify Save Changes is disabled", "Save Changes CTA is disabled");
			logger.info("Save Changes CTA is disabled");
		} else {
			extent.extentLoggerFail("Verify Save Changes is disabled", "Save Changes CTA is enabled");
			logger.info("Save Changes CTA is enabled");
		}
		// Verify First name is displayed
		String firstName = getText(AMDEditProfileScreen.objFirstNameField);
		if (!firstName.equalsIgnoreCase("null")) {
			extent.extentLogger("Verify First name is displayed",
					"First name :" + firstName + " is displayed in the First name text field");
			logger.info("First name :" + firstName + " is displayed in the First name text field");
		} else {
			extent.extentLoggerFail("Verify First name is displayed",
					"First name is not displayed in the First name text field");
			logger.info("First name is not displayed in the First name text field");
		}
		// Verify Last name is displayed for the registered user
		String lastName = getText(AMDEditProfileScreen.objLastNameField);
		if (!lastName.equalsIgnoreCase("null")) {
			extent.extentLogger("Verify Last name is displayed",
					"Last name :" + lastName + " is displayed in the First name text field");
			logger.info("Last name :" + lastName + " is displayed in the Last name text field");
		} else {
			extent.extentLoggerFail("Verify Last name is displayed",
					"Last name is not displayed in the Last name text field");
			logger.info("Last name is not displayed in the Last name text field");
		}
		// Back(1);
		// Verify the back button functionality
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		String headerTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (headerTitle.equals("My Profile")) {
			extent.extentLogger("Verify Navigation",
					"User is navigated to " + headerTitle + " post tapping back button from Edit Profile screen");
			logger.info("User is navigated to " + headerTitle + " post tapping back button from Edit Profile screen");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to My Profile screen post tapping back button from Edit Profile screen");
			logger.info("User is not navigated to My Profile post tapping back button from Edit Profile screen");
		}
		Back(1);

	}

	/*
	 * Social Login Validations
	 */
	public void SocialLoginValidationsForEditProfileScreen(String userType) throws Exception {
		extent.HeaderChildNode("Social Login Validations for Edit Profile screen");
		Swipe("UP", 1);
		verifyElementPresentAndClick(AMDHomePage.objLogout, "Logout");
		verifyElementPresentAndClick(AMDHomePage.objLogoutPopUpLogoutButton, "Logout button");
		Swipe("DOWN", 1);
		click(AMDMoreMenu.objLoginRegisterText, "Login/Register link");
		LoginMethod("SocialLogin");
		click(AMDMoreMenu.objMoreMenuIcon, "More tab screen");
		click(AMDMoreMenu.objLoginRegisterText, "Profile");
		click(AMDMyProfileScreen.objEditProfileButton, "Edit profile");

		// Social Login validations

		VerifyFieldsAreEditable(AMDEditProfileScreen.objFirstNameField, "First Name", "NewFirstName");
		VerifyFieldsAreEditable(AMDEditProfileScreen.objLastNameField, "Last Name", "NewLastName");
		// Verify Email field is in proper format
		String email = getText(AMDEditProfileScreen.objEmailFieldForEmailLogin);
		boolean isEmailValid = emailValidator(email);
		if (isEmailValid) {
			extent.extentLogger("Veridy email", "Email field is in proper format");
			logger.info("Email field is in proper format");

		} else {
			extent.extentLoggerFail("Verify Email", "Email field is not in proper format");
			logger.info("Email field is not in proper format");
		}
		VerifyFieldsAreEditable(AMDEditProfileScreen.objEmailFieldForEmailLogin, "Email ID", "newemail@gmail.com");

		// Verify DOB field is editable

		String OldDate = getText(AMDEditProfileScreen.objDOBTxtField);
		// Verify the given DOB is displayed in DD/MM/YYYY format
		System.out.println(OldDate);
		/*
		 * //boolean dateFormat = isValidDateFormat("DD/MM/YYYY",
		 * OldDate,Locale.ENGLISH); if(dateFormat) {
		 * extent.extentLogger("Verify DOB field"
		 * ,"The DOB field is displayed in DD/MM/YYYY format");
		 * logger.info("The DOB field is displayed in DD/MM/YYYY format"); }else {
		 * extent.extentLoggerFail("Verify DOB format"
		 * ,"The DOB field is not displayed in DD/MM/YYYY format");
		 * logger.info("The DOB field is not displayed in DD/MM/YYYY format"); }
		 */
		click(AMDEditProfileScreen.objDOBTxtField, "DOB field");
		// Verify the calendar is displayed on clicking calendar option
		verifyElementPresent(AMDEditProfileScreen.objCalenderPopUp, "Calendar option");

		// Select New date
		type(AMDEditProfileScreen.objYearField, "1950", "Year field");
		click(AMDEditProfileScreen.objOkBtnCalender, "OK button");
		String newDate = getText(AMDEditProfileScreen.objDOBTxtField);
		if (!OldDate.equals(newDate)) {
			extent.extentLogger("Verify DOB field", "DOB field can be editable");
			extent.extentLogger("DOB", "The DOB : " + OldDate + " is updated to new DOB : " + newDate);
			logger.info("DOB field can be editable");
			logger.info("The DOB : " + OldDate + " is updated to new DOB : " + newDate);
		} else {
			extent.extentLoggerFail("Verify DOB field", "DOB field can not be editable");
			logger.info("DOB field can not be editable");
		}
		// Verify the Gender field is editable
		String oldGender = getText(AMDEditProfileScreen.objGederTxtField);
		System.out.println(oldGender);
		click(AMDEditProfileScreen.objGederTxtField, "Gender field");
		if (oldGender.equals("Male")) {
			click(AMDEditProfileScreen.objFemale, "Female gender option");
		} else {
			click(AMDEditProfileScreen.objMale, "Male gender option");
		}
		String NewGender = getText(AMDEditProfileScreen.objGederTxtField);
		if (!oldGender.equals(NewGender)) {
			extent.extentLogger("Verify Gender field", "Gender field can be editable");
			extent.extentLogger("Gender", "The gender : " + oldGender + " is updated to new gender : " + NewGender);
			logger.info("Gender field can be editable");
			logger.info("The gender : " + oldGender + " is updated to new gender : " + NewGender);
		} else {
			extent.extentLoggerFail("Verify Gender field", "Gender field can not be editable");
			logger.info("Gender field can not be editable");
		}
		Back(1);
	}

	public void LoginMethod(String loginMethod) throws Exception {

		switch (loginMethod) {
		case "Email":
			extent.HeaderChildNode("Login through Email ID");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, "zee5latest@gmail.com", "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		case "Mobile":
			extent.HeaderChildNode("Login through registered Mobile number");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, "9880710182", "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, "User@123", "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SocialLogin": // extent.HeaderChildNode("Social Media login - Google account login");
			verifyElementPresentAndClick(AMDLoginScreen.objGoogleBtn, "Gmail icon");
			if (verifyElementExist(AMDLoginScreen.objGmailAccount, "Gmail Account")) {
				click(AMDLoginScreen.objGmailAccount, "Gmail Account");
				waitTime(5000);
			} else if (verifyElementPresent(AMDLoginScreen.objGmailSignIn, "Gmail Sign In")) {
				verifyElementPresentAndClick(AMDLoginScreen.objGmailEmailField, "Email Field");
				type(AMDLoginScreen.objGmailEmailField, "zeetest55@gmail.com", "Email Field");
				verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");
				verifyElementPresentAndClick(AMDLoginScreen.objGmailPasswordField, "Password Field");
				type(AMDLoginScreen.objGmailPasswordField, "zeetest123", "Password Field");
				verifyElementPresentAndClick(AMDLoginScreen.objGmailNextBtn, "Next Button");

				if (verifyElementPresent(AMDLoginScreen.objGmailAddPhoneNumber, "Add Phone Number")) {
					verifyElementPresentAndClick(AMDLoginScreen.objSkipBtn, "Skip Button");
				}
				if (verifyElementPresent(AMDLoginScreen.objAgreeBtn, "Agree Button")) {
					click(AMDLoginScreen.objAgreeBtn, "Agree Button");
				}

				if (verifyElementPresent(AMDLoginScreen.objAcceptBtn, "Accept Button")) {
					click(AMDLoginScreen.objAcceptBtn, "Accept Button");
				}
			}

			if (verifyElementExist(AMDHomePage.objHome, "Home Tab")) {
				logger.info("User logged in successfully");
				extent.extentLogger("Login", "User logged in successfully using Google account");
			}
			break;

		}
	}

	public void VerifyFieldsAreEditable(By by, String fieldType, String text) {
		String oldText = getDriver().findElement(by).getText();
		getDriver().findElement(by).click();
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(text);
		String newText = getDriver().findElement(by).getText();
		if (oldText.equals(newText)) {
			extent.extentLoggerFail("Verify Fields are editable", "The field " + fieldType + " is not editable");
			logger.info("The field " + fieldType + " is not editable");
		} else {
			extent.extentLogger("Verify Fields are editable", "The field " + fieldType + " is editable");
			extent.extentLogger("Verify the fields",
					"The old text is : " + oldText + " updated to New text : " + newText);
			logger.info("The old text is : " + oldText + " updated to New text : " + newText);
			logger.info("The field " + fieldType + " is editable");
		}
	}

	public static boolean emailValidator(String email) {

		// Get an EmailValidator
		EmailValidator validator = EmailValidator.getInstance();
		// Validate specified String containing an email address
		if (!validator.isValid(email)) {
			return false;
		}
		return true;
	}

	public void verifyMySubscription(String userType) throws Exception {
		extent.HeaderChildNode("Verify My Subscription Screen");
//		verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
//		waitTime(2000);
		ZNALogoutMethod();
		ZeeApplicasterLogin(userType);
		if (verifyIsElementDisplayed(AMDMoreMenu.objMySubscription)) {
			logger.info("My subscription option is available on More menu screen");
			extent.extentLogger("Mysubscription Screen", "My subscription option is available on More menu screen");
		} else {
			logger.info("My subscription option is not available on More menu screen");
			extent.extentLoggerFail("Mysubscription Screen",
					"My subscription option is not available on More menu screen");
		}

		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		waitTime(2000);
		if (verifyElementExist(AMDMoreMenu.objMySubscriptionsHeader, "My Transactions Page")) {
			logger.info("User navigated to My Subscriptions Page on tapping My Subscriptions");
			extent.extentLogger("MySubscriptions Screen",
					"User navigated to My Subscriptions Page on tapping My Subscriptions");
		} else {
			logger.info("User navigated to My Subscriptions Page on tapping My Subscriptions");
			extent.extentLoggerFail("MySubscriptions Screen",
					"User navigated to My Subscriptions Page on tapping My Subscriptions");
		}

		String SubscriptionPackStatus = getDriver().findElement(AMDMoreMenu.objsubscriptionPackStatus).getText();
		if (verifyElementExist(AMDMoreMenu.objsubscriptionPackStatus, "My subscription Pack Status")) {
			logger.info(
					SubscriptionPackStatus + " : My Subscription pack Status details is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackStatus + " : My Subscription pack Status details is displayed for the " + userType);
		} else {

			logger.info(
					SubscriptionPackStatus + " : My Subscription pack Status details is displayed for the " + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackStatus + " : My Subscription pack Status details is displayed for the " + userType);
		}

		verifyElementPresentAndClick(AMDLoginScreen.objBackBtn, "Back Button");
		if (verifyElementExist(AMDHomePage.MoreMenuIcon, "More Menu")) {
			logger.info("User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
			extent.extentLogger("MySubscriptions Screen",
					"User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
		} else {
			logger.info("User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
			extent.extentLoggerFail("MySubscriptions Screen",
					"User navigated to prevoius screen on tapping back icon present in My Subscriptions screen");
		}

		extent.HeaderChildNode("verify My Subscription pack details");
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		String SubscriptionPackPrice = getDriver().findElement(AMDMoreMenu.objSubscriptionPackPrice).getText();

		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackPrice, "My subscription Pack Price")) {
			logger.info(SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackPrice + " : My Subscription pack price is displayed for the " + userType);
		}

		String SubscriptionPackDuration = getDriver().findElement(AMDMoreMenu.objSubscriptionPackDuration).getText();

		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackDuration, "My subscription Pack Duration")) {
			logger.info(SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackDuration + " : My Subscription pack duration is displayed for the " + userType);
		}

		Swipe("Up", 1);

		String SubscriptionPackExpiryDate = getDriver().findElement(AMDMoreMenu.objSubscriptionPackExpiryDate)
				.getText();

		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackExpiryDate, "My subscription Pack Expiry Date")) {
			logger.info(SubscriptionPackExpiryDate + " : My Subscription pack expiry date is displayed for the "
					+ userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackExpiryDate + " : My Subscription pack expiry date is displayed for the" + userType);
		} else {

			logger.info(SubscriptionPackExpiryDate + " : My Subscription pack expiry date is displayed for the "
					+ userType);
			extent.extentLoggerFail("MySubscription Screen", SubscriptionPackExpiryDate
					+ " : My Subscription pack expiry date is displayed for the " + userType);
		}

		Swipe("DOWN", 1);

		String SubscriptionPackCountryDetails = getDriver().findElement(AMDMoreMenu.objSubscriptionPackCountry)
				.getText();

		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackCountry, "My subscription Pack Country")) {
			logger.info(SubscriptionPackCountryDetails + " : My Subscription pack Country details is displayed for the "
					+ userType);
			extent.extentLogger("MySubscription Screen", SubscriptionPackCountryDetails
					+ " : My Subscription pack Country details is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackCountryDetails + " : My Subscription pack Country details is displayed for the "
					+ userType);
			extent.extentLoggerFail("MySubscription Screen", SubscriptionPackCountryDetails
					+ " : My Subscription pack Country details is displayed for the " + userType);
		}

		String SubscriptionPackPaymentMode = getDriver().findElement(AMDMoreMenu.objSubscriptionPackPaymentMode)
				.getText();

		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackPaymentMode, "My subscription Pack Payment Mode")) {
			logger.info(SubscriptionPackPaymentMode + " : My Subscription pack payment mode is displayed for the "
					+ userType);
			extent.extentLogger("MySubscription Screen", SubscriptionPackPaymentMode
					+ " : My Subscription pack payment mode is displayed for the " + userType);
		} else {

			logger.info(SubscriptionPackPaymentMode + " : My Subscription pack payment mode is displayed for the "
					+ userType);
			extent.extentLoggerFail("MySubscription Screen", SubscriptionPackPaymentMode
					+ " : My Subscription pack payment mode is displayed for the " + userType);
		}

		String SubscriptionPackOfferings = getDriver().findElement(AMDMoreMenu.objSubscriptionPackOfferings).getText();

		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackOfferings, "My subscription pack Offerings")) {
			logger.info(
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the " + userType);
			extent.extentLogger("MySubscription Screen",
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the " + userType);
		} else {

			logger.info(
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the" + userType);
			extent.extentLoggerFail("MySubscription Screen",
					SubscriptionPackOfferings + " : My Subscription pack offerings is displayed for the" + userType);
		}
		Back(2);
		verifySubscribeToMultipleAccessPacks(userType);
	}

	@SuppressWarnings("unused")
	public void verifySubscribeToMultipleAccessPacks(String userType) throws Exception {

		if (userType.equalsIgnoreCase("SubscribedUser")) {
			ZNALogoutMethod();

			click(AMDHomePage.objMoreMenu, "More Menu");
			click(AMDMoreMenu.objProfile, "Profile");
			waitTime(2000);

			String SubscriptionPackwithMultipleAccessPaclsUsername = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("SubscriptionPackwithMultipleAccessPaclsUsername");
			String SubscriptionPackwithMultipleAccessPaclsPassword = Reporter.getCurrentTestResult().getTestContext()
					.getCurrentXmlTest().getParameter("SubscriptionPackwithMultipleAccessPaclsPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");

			LoginWithEmailID(SubscriptionPackwithMultipleAccessPaclsUsername,
					SubscriptionPackwithMultipleAccessPaclsPassword);
			waitTime(2000);
			verifyElementPresentAndClick(AMDHomePage.MoreMenuIcon, "More Menu");
			waitTime(2000);
			verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
			waitTime(4000);
		}

		extent.HeaderChildNode("Verify user can able to see the packs by Swiping card left to right");
		String width = getAttributValue("width", AMDMoreMenu.objMySubscriptionPack);
		String bounds = getAttributValue("bounds", AMDMoreMenu.objMySubscriptionPack);
		String b = bounds.replaceAll(",", " ").replaceAll("]", " ");
		String height = b.split(" ")[1];

		Swipe("LEFT", 1);
		// carouselCardsSwipe("LEFT", 1, width, height);
		// carouselSwipe("Right", 1, width, height);

		waitTime(2000);
		Swipe("UP", 1);
		verifyElementExist(AMDMoreMenu.objsubscriptionPackDotBelowTheCard, "Dot below the Subscription Pack Card");

		String getPropertyValue = getAttributValue("enabled", AMDMoreMenu.objsubscriptionPackDotBelowTheCard);
		if (getPropertyValue.equalsIgnoreCase("true")) {
			logger.info(userType
					+ " User can able to swipe the card, dot below the card is moved as per the position of the card");
			extent.extentLogger("MySubscription Screen", userType
					+ " User can able to swipe the card, dot below the card is moved as per the position of the card");
		} else {
			logger.info(userType
					+ " User cannot able to swipe the card, dot below the card is not moved as per the position of the card");
			extent.extentLoggerFail("MySubscription Screen", userType
					+ " User cannot able to swipe the card, dot below the card is not moved as per the position of the card");
		}

		Swipe("RIGHT", 1);
		// carouselCardsSwipe("Right", 1, width, height);
		// carouselSwipe("Left", 1, width, height);
		Swipe("UP", 1);
		String getPropertyValue1 = getAttributValue("enabled", AMDMoreMenu.objsubscriptionPackDotBelowTheSecondCard);
		if (getPropertyValue1.equalsIgnoreCase("true")) {
			logger.info(userType
					+ " User can able to swipe the card in reverse direction, dot below the card is moved as per the position of the card");
			extent.extentLogger("MySubscription Screen", userType
					+ " User can able to swipe the card in reverse direction, dot below the card is moved as per the position of the card");
		} else {
			logger.info(userType
					+ " User cannot able to swipe the card in reverse direction, dot below the card is not moved as per the position of the card");
			extent.extentLoggerFail("MySubscription Screen", userType
					+ " User cannot able to swipe the card in reverse direction, dot below the card is not moved as per the position of the card");
		}
		Swipe("LEFT", 1);
		Swipe("UP", 1);

		extent.HeaderChildNode("Verify Cancel Renewal CTA is available");
		if (verifyElementExist(AMDMoreMenu.objSubscriptionPackCancelRenewal, "Cancel Renewal CTA")) {
			logger.info("Cancel Renewal CTA is available based on the pack Selection");
			extent.extentLogger("MySubscription Screen", "Cancel Renewal CTA is available based on the pack Selection");
		} else {

			logger.info("Cancel Renewal CTA is not available based on the pack Selection");
			extent.extentLogger("MySubscription Screen",
					"Cancel Renewal CTA is not available based on the pack Selection");
		}

		extent.HeaderChildNode("Verify Browse All Packs CTA is displayed on the Screen");
		if (verifyElementExist(AMDMoreMenu.objBrowseAllPacks, "Browse All Packs CTA")) {
			logger.info("Browse All Packs CTA is present in the bottom of the Screen");
			extent.extentLogger("MySubscription Screen", "Browse All Packs CTA is present in the bottom of the Screen");
		} else {

			logger.info("Browse All Packs CTA is present in the bottom of the Screen");
			extent.extentLogger("MySubscription Screen", "Browse All Packs CTA is present in the bottom of the Screen");
		}

		Back(2);
	}

	/**
	 * My Subscription screen validations
	 */
	public void MySubscriptionValidations(String userType) throws Exception {

		extent.HeaderChildNode("My Subscriptions screen validations for user type :" + userType);
		// Verify User is navigated to Login/Register screen post tapping My
		// Subscription
		verifyElementPresentAndClick(AMDMoreMenu.objMySubscription, "My Subscription");
		String header = getText(AMDGenericObjects.objgetScreenTitle);
		if (header.equals("My Subscriptions")) {
			extent.extentLogger("Verify navigation",
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is navigated to " + header + " screen post tapping My Subscription from more menu screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated to My Subscriptions screen post tapping My Subscription from more menu screen ");
			logger.info(
					"User is not navigated to My Subscriptions screen post tapping My Subscription from more menu screen");
		}
		// Verify Subscribe now and No Active subscription is displayed
		if (verifyIsElementDisplayed(AMDMoreMenu.objNoActivePlans)) {
			String NoactivePlans = getText(AMDMoreMenu.objNoActivePlans);
			extent.extentLogger("Verify No active Subscription",
					"The message : " + NoactivePlans + " is displayed in My Subscription screen");
			logger.info("The message : " + NoactivePlans + " is displayed in My Subscription screen");
		} else {
			extent.extentLoggerFail("Verify No active Subscription",
					"The message No Active Subscription is not displayed in My Subscription screen");
			logger.info("The message No Active Subscription is not displayed in My Subscription screen");
		}
		if (verifyIsElementDisplayed(AMDMoreMenu.objSubscribeNowCTA)) {
			extent.extentLogger("Verify No active Subscription",
					"Subscribe Now CTA is displayed in My Subscription screen");
			logger.info("Subscribe Now CTA is displayed in My Subscription screen");
		} else {
			extent.extentLoggerFail("Verify No active Subscription",
					"Subscribe Now CTA is not displayed in My Subscription screen");
			logger.info("Subscribe Now CTA is not displayed in My Subscription screen");
		}
		// Verify User is navigated to subscribe screen post tapping Subscribe Now CTA
		verifyElementPresentAndClick(AMDMoreMenu.objSubscribeNowCTA, "Subscribe Now CTA");
		String screenTitle = getText(AMDGenericObjects.objgetScreenTitle);
		if (screenTitle.equals("Subscribe")) {
			extent.extentLogger("Verify Navigation",
					"User is navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
			logger.info(
					"User is navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User is not navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
			logger.info(
					"User is not navigated to Subscribe screen post tapping Subscribe Now CTA from My Subscription screen");
		}
		Back(1);
		// Verify user is navigated back to More menu screen on clicking back button
		verifyElementPresentAndClick(AMDGenericObjects.objBackBtn, "Back button");
		if (verifyElementExist(AMDMoreMenu.objProfile, "Profile icon")) {
			extent.extentLogger("Verify navigation",
					"User is navigate back to the More menu screen post tapping back button from My Subscription screen");
			logger.info(
					"User is navigate back to the More menu screen post tapping back button from Login/Register screen");
		} else {
			extent.extentLoggerFail("Verify navigation",
					"User is not navigated back to the More menu screen post tapping back button from My Subscription screen");
			logger.info(
					"User is not navigated back to the More menu screen post tapping back button from My Subscription screen");
		}
	}

	/**
	 * Author : Manasa
	 */
	public void ZeeApplicasterLoginForSettings(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");
		String UserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
		}
		verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Login link");
		waitTime(3000);

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(1000);
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginLnk, "Skip link");
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User for Settings");
			String SUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SettingsNonsubscribedUserName");
			String SPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SettingsNonsubscribedPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User for Settings");
			String SettingsSubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SettingsSubscribedUserName");
			String SettingsSubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SettingsSubscribedPassword");
			verifyElementPresentAndClick(AMDLoginScreen.objEmailIdField, "Email field");
			type(AMDLoginScreen.objEmailIdField, SettingsSubscribedUsername, "Email Field");
			verifyElementPresentAndClick(AMDLoginScreen.objProceedBtn, "Proceed Button");
			verifyElementPresentAndClick(AMDLoginScreen.objPasswordField, "Password Field");
			type(AMDLoginScreen.objPasswordField, SettingsSubscribedPassword, "Password field");
			hideKeyboard();
			verifyElementPresentAndClick(AMDLoginScreen.objLoginBtn, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Author : Manasa
	 */
	public void verifyPlaybackAfterMinimzeAndMaximizeAppFromBackground() throws Exception {
		extent.HeaderChildNode("Validation of content playback after minimize and maximize app from background");

		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);

		logger.info("Time before minimizing app from background : " + startTime);
		extentLogger("Time", "Time before minimizing app from background : " + startTime);

		getDriver().runAppInBackground(Duration.ofSeconds(10));

		click(AMDPlayerScreen.objPlayerScreen, "Player Frame");

		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);

		logger.info("Time after maximizing app from background : " + elapsedTime);
		extentLogger("Time", "Time after maximizing app from background : " + elapsedTime);

		if (elapsedTime > startTime) {
			logger.info("Content playback is resumed after maximizing the app from background");
			extentLogger("Elapsed time", "Content playback is resumed on maximizing the app from background");
		} else {
			logger.info("Content playback is not resumed after maximizing the app from background");
			extentLoggerFail("Time", "Content playback is resumed on maximizing the app from background");
		}
	}

	public void verifyPlaybackAfterLockAndUnlock() throws Exception {
		extent.HeaderChildNode("Validation of content playback after lock and unlocking the device screen");
		String time1 = getText(AMDPlayerScreen.objTimer);
		int startTime = timeToSec(time1);
		System.out.println(startTime);
		logger.info("Time before locking the device screen : " + startTime);
		extentLogger("Time", "Time before locking the device screen : " + startTime);
		adbKeyevents(26);
		waitTime(7000);
		adbKeyevents(26);
		waitTime(3000);
		Swipe("Up", 1);
		waitTime(5000);
		click(AMDPlayerScreen.objPlayerScreen, "Player Frame");
		String time2 = getText(AMDPlayerScreen.objTimer);
		int elapsedTime = timeToSec(time2);

		logger.info("Time after unlocking the device screen : " + elapsedTime);
		extentLogger("Time", "Time before unlocking the device screen : " + elapsedTime);

		if (elapsedTime > startTime) {
			logger.info("Content playback is resumed after unlocking the device screen");
			extentLogger("Elapsed time", "Content playback is resumed after unlocking the device screen");
		} else {
			logger.info("Content playback is not resumed after unlocking the device screen");
			extentLoggerFail("Elapsed time", "Content playback is not resumed after unlocking the device screen");
		}
	}

	/**
	 * @param keyevent pass the android key event value to perform specific action
	 * 
	 */
	public void adbKeyevents(int keyevent) {

		try {
			String cmd = ("/usr/local/bin/adb shell input keyevent" + " " + keyevent);
			Runtime.getRuntime().exec(cmd);
			logger.info("Performed the Keyevent" + keyevent);
			extent.extentLogger("adbKeyevent", "Performed the Keyevent" + keyevent);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}