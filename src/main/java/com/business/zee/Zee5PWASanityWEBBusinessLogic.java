package com.business.zee;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.driverInstance.CommandBase;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.metadata.getResponseUpNextRail;
import com.metadata.responseWatchlist;
import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;
import com.zee5.PWAPages.*;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import static com.jayway.restassured.RestAssured.given;

public class Zee5PWASanityWEBBusinessLogic extends Utilities {

	public Zee5PWASanityWEBBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	} 

	private int timeout;

	/** Retry Count */
	private int retryCount;

	ExtentReporter extent = new ExtentReporter();

	private SoftAssert softAssert = new SoftAssert();

	/** The Constant logger. */
	final static Logger logger = Logger.getLogger("rootLogger");

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	Set<String> hash_Set = new HashSet<String>();

	@SuppressWarnings("unused")
	private String LacationBasedLanguge;

	List<String> LocationLanguage = new ArrayList<String>();

	List<String> DefaultLanguage = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInWelcomscreen = new ArrayList<String>();

	List<String> SelectedCONTENTLanguageInHamburgerMenu = new ArrayList<String>();

	/**
	 * ================================Fetching URL from
	 * pwaSanityWeb.xml==================================
	 * 
	 */
	String URL = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("url");

	String BROWSER = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserType");

	String NonSubUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedUserName");
	String NonSubPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("NonsubscribedPassword");
	String SubUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedUserName");
	String SubPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("SubscribedPassword");

	String ExpiredUserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("ExpiredUserName");
	String ExpiredUserPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("ExpiredUserPassword");
	String audioTrackContent = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
			.getParameter("audioTrackContent");

	Response resp;
	String PresentTitle;
	String AdValue = "AdnotPlayed";

	ArrayList<String> MastheadTitleApi = new ArrayList<String>();

	public static boolean relaunchFlag = false;
	public static boolean appliTools = false;

	public static boolean PopUp = false;

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	/**
	 * Initiate Property File.
	 *
	 * @param byLocator the by locator
	 */

//	public void scroll1() {
//		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
//		WebElement Element = getWebDriver().findElement(By.xpath("//h2[.='Trending on ZEE5']"));
//		js.executeScript("arguments[0].scrollIntoView();", Element);
//	}

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void ZeeWEBPWALogin(String LoginMethod) throws Exception {
		String UserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		extent.HeaderChildNode("Login Functionality for User-Type : " + UserType);

		if (UserType.equals("Guest")) {
			extent.extentLogger("userType", "UserType : Guest");
			return;
		}

		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");

		switch (LoginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			getWebDriver().navigate().back();
			System.out.println(getWebDriver().manage().window().getSize());
			dismissDisplayContentLanguagePopUp();
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");

			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, Username, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, Password, "Password field");

			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Login as Subscribed User");

			String SubscribedUsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedUserName");
			String SubscribedPassword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");

			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SubscribedUsername, "Email Field");

			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubscribedPassword, "Password field");

			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(3000);
			break;
		}
	}

	/**
	 * Generic PWALogin function.
	 */
	public void ZeeWEBPWALogin1(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login through " + LoginMethod);
		verifyElementPresent(PWALoginPage.objLoginBtnWEB, "Login button");
		click(PWALoginPage.objLoginBtnWEB, "Login button");
		waitTime(3000);
		switch (LoginMethod) {
		case "E-mail":

			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, "User@123", "Password field");
			waitTime(5000);
			click(PWALoginPage.objLoginPageLoginBtnWEB, "Login Button");
			waitTime(3000);
			break;

		case "Mobile":
			extent.HeaderChildNode("Login through mobile number");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, "8095248549", "Email Field");
			verifyElementPresentAndClick(PWALoginPage.objLoginPageLoginBtnWEB, "Login butotn");
			waitTime(8000);
			verifyElementPresentAndClick(PWALoginPage.objpasswordphno, "Password field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "password-field");
			type(PWALoginPage.objPasswordField, "deepakgowda", "password-field");
			waitTime(2000);
			click(PWALoginPage.objproceedphno, "Proceed button");
			waitTime(5000);
			break;

		case "Facebook":
			extent.HeaderChildNode("Login through Facebook");
			verifyElementPresentAndClick(PWALoginPage.objFacebookIcon, "Facebook Icon");
			switchToWindow(2);
			waitTime(7000);
			if (verifyElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}

			else if (verifyElementExist(PWALoginPage.objFacebookLoginEmailWEB, "Facebook page")) {
				verifyElementPresent(PWALoginPage.objFacebookLoginEmailWEB, " Email Field");
				type(PWALoginPage.objFacebookLoginEmailWEB, "igszeetesttest@gmail.com", "Emial Field");
				verifyElementPresent(PWALoginPage.objFacebookLoginpasswordWEB, " Password Field");
				type(PWALoginPage.objFacebookLoginpasswordWEB, "Igs$123Zee", "Password Field");
				verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPageWEB, " Login Button");
				waitTime(9000);
				switchToParentWindow();
				if (verifyElementExist(PWASignupPage.objSignUpTxt, "SignUp")) {
					regestrationfromSocialMedia();
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				} else {
					waitTime(8000);
					verifyElementPresent(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}

			} else if (verifyElementPresent(PWALoginPage.objFbCountinueBtn, "Continue button") == true) {
				click(PWALoginPage.objFbCountinueBtn, "Continue fb");
				if (verifyElementPresent(PWASignupPage.objSignUpTxt, "SignUp") == true) {
					regestrationfromSocialMedia();
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				} else {
					waitTime(7000);
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				}
			}

			break;

		case "Gmail":
			extent.HeaderChildNode("Login through Gmail");
			verifyElementPresentAndClick(PWALoginPage.objGoogleIcon, "Google Icon");
			switchToWindow(2);
			waitTime(4000);
			if (verifyElementExist(PWALoginPage.objGmailEmailField, " Email Field")) {
				type(PWALoginPage.objGmailEmailField, "Zee5latest@gmail.com", "Emial Field");
				hideKeyboard();
				verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
				waitTime(3000);
				verifyElementPresent(PWALoginPage.objGmailPasswordField, " Password Field");
				type(PWALoginPage.objGmailPasswordField, "User@123", "Password Field");
				hideKeyboard();
				verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
				waitTime(5000);
				switchToParentWindow();
				waitTime(5000);
				if (verifyElementExist(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon")) {
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				} else {
					logger.info("User not  Logged in Successfully");
					extent.extentLogger("Logged in", "User not Logged in Successfully");
				}
			}
			break;

//		case "Twitter":
//			extent.HeaderChildNode("Login through Twitter");
//			verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
//			waitTime(7000);
//			switchToWindow(2);
//			waitTime(5000);
//			if (verifyElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
//				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
//				verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
//				logger.info("User Logged in Successfully");
//				extent.extentLogger("Logged in", "User Logged in Successfully");
//			} else if (verifyElementExist(PWALoginPage.objTwitterAuthorizeButton, "Authorize app")) {
//				click(PWALoginPage.objTwitterAuthorizeButton, "Authorize app");
//				regestrationfromSocialMedia();
//			} else if (verifyElementExist(PWALoginPage.objTwitterEmaildField, "Twitter Email field")) {
//				type(PWALoginPage.objTwitterEmaildField, "Zee5latest@gmail.com", "Email Field");
//				hideKeyboard();
//				verifyElementPresentAndClick(PWALoginPage.objTwitterPasswordField, "Twitter Password field");
//				type(PWALoginPage.objTwitterPasswordField, "User@123", "Password field");
//				click(PWALoginPage.objTwitterSignInButton, "Sign in button");
//				waitTime(5000);
//				verifyElementExist(PWAHomePage.objZeeLogo, "Home page");
//				waitTime(5000);
//				verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login");
//				verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
//				waitTime(10000);
//				verifyElementPresent(PWAHamburgerMenuPage.objProfileIconWEB, "Profile");
//				logger.info("User Logged in Successfully");
//				extent.extentLogger("Logged in", "User Logged in Successfully");
//			}
//			break;
		}
	}

	/**
	 * Function to Enter DOB and Gender in SIGNUP Page.
	 */
	public void regestrationfromSocialMedia() throws Exception {
		extent.HeaderChildNode("Regestration Screen");
		click(PWASignupPage.objDayPickerTab, "Day Tab");
		verifyElementPresentAndClick(PWASignupPage.objDayPickerTabValue, "Day option");
		verifyElementPresentAndClick(PWASignupPage.objMonthPickerTab, "Month Tab");
		verifyElementPresentAndClick(PWASignupPage.objMonthPickerTabValue, "Month option");
		verifyElementPresentAndClick(PWASignupPage.objYearPickerTab, "year Tab");
		verifyElementPresentAndClick(PWASignupPage.objYearPickerTabValue, "year option");
		verifyElementPresentAndClick(PWASignupPage.objGenderMaleBtn, "Gender tab");
		verifyElementPresentAndClick(PWALoginPage.objSignUpBtn, "signUp button");
		waitTime(10000);
		verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
		logger.info("User Logged in Successfully");
		extent.extentLogger("Logged in", "User Logged in Successfully");
	}

//	public void WEBPWAValidatingSubscriptionAndTransaction(String userType) throws Exception {
//		extent.HeaderChildNode("Validation of Subscription and Transaction");
//		waitTime(5000);
//		waitTime(5000);
//
//		extent.HeaderChildNode("Validation of Get Premium CTA on Carousel");
//		List<WebElement> ele = getWebDriver().findElements(By.xpath(
//				"(//div[@class='slick-slide slick-active slick-center slick-current']//div[.='Get premium'][1])[2]"));
//		System.out.println(ele.size());
//		if (ele.size() == 0) {
//			System.out.println("Get Premium CTA on Carousel is not displayed");
//			logger.info("Get Premium CTA on Carousel is not displayed");
//			extent.extentLogger("<b>" + "Get Premium CTA on Carousel is not displayed..",
//					"Get Premium CTA on Carousel is not displayed.");
//		} else {
//			System.out.println("Get Premium CTA on Carousel is displayed");
//			logger.info("Get Premium CTA on Carousel is displayed");
//			extent.extentLogger("<b>" + "Get Premium CTA on Carousel is displayed..",
//					"Get Premium CTA on Carousel is displayed.");
//			for (int i = 1; i < ele.size(); i++) {
//				verifyElementExist1(ele.get(i), "Get Premium CTA on Carousel");
//			}
//		}
//
//		extent.HeaderChildNode("Validating Subscribe Button");
//		verifyElementExist(PWAHomePage.objSubscribeBtn, "Subscribe Button");
//
//		extent.HeaderChildNode("Validating BuySubscription and HaveAPrepaidCode under MyPlans");
//		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
//		Thread.sleep(3000);
//		if (verifyElementExist(PWAHamburgerMenuPage.objPlans, "My Plans")) {
//			verifyElementExist(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription");
//			verifyElementExist(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code");
//		}
//		verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
//		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
//		Thread.sleep(3000);
//		if (verifyElementExist(PWALandingPages.objWebProfileIcon, "Profile Icon")) {
//			//verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
//			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
//		}
//
//		extent.HeaderChildNode("Validating MyAccount");
//		boolean myAccountPresent = verifyElementExist(PWAHamburgerMenuPage.objWEBMyAccount, "My Account");
//		if (myAccountPresent == true) {
//
//			extent.HeaderChildNode("Validating MySubscription Button Under MyAccount Menu");
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMySubscription, "MySubscription");
//			Thread.sleep(3000);
//
//			extent.HeaderChildNode("Validating MySubscription Screen");
//			verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPage, "MySubscription Page");
//			Thread.sleep(5000);
//
//			extent.HeaderChildNode("Validating the Active Subscription Plans in MySubscription Screen");
//			boolean NoSubscriptionActivePresent = verifyElementExist(PWAHamburgerMenuPage.objNoActiveSubscription,
//					"No Active Subscription");
//			if (NoSubscriptionActivePresent) {
//				verifyElementExist(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item");
//				verifyElementExist(PWAHamburgerMenuPage.objMySubscriptionPackName, "MySubscription Name");
//				verifyElementExist(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus, "My Subscription Status");
//			} else {
//				if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item") == true) {
//					if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPackName,
//							"MySubscription Name")) {
//						System.out.println(getText(PWAHamburgerMenuPage.objMySubscriptionPackName));
//						logger.info(
//								"Subscription PackName : " + getText(PWAHamburgerMenuPage.objMySubscriptionPackName));
//						extent.extentLogger(
//								"<b>" + "Subscription PackName : "
//										+ getText(PWAHamburgerMenuPage.objMySubscriptionPackName),
//								"Subscription pack name");
//					}
//					if (verifyElementPresent(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus,
//							"My Subscription Status")) {
//						System.out.println(getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus));
//						logger.info(
//								"Subscription Status : " + getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus));
//						extent.extentLogger(
//								"<b>" + "Subscription Status : "
//										+ getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus),
//								"Subscription ActiveStatus");
//					}
//				}
//			}
//
//			getWebDriver().navigate().back();
//
//			verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
//
//			Thread.sleep(5000);
//			extent.HeaderChildNode("Validating MyAccount");
//			verifyElementExist(PWAHamburgerMenuPage.objWEBMyAccount, "My Account");
//
//			extent.HeaderChildNode("Validating MyTransactions Button Under MyAccount Menu");
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyTransactions, "MyTransaction");
//			Thread.sleep(3000);
//
//			extent.HeaderChildNode("Validating MyTransaction Screen");
//			verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPage, "MyTransaction Page");
//			Thread.sleep(5000);
//
//			extent.HeaderChildNode("Validating the Active and Expired Plans in MyTransaction Screen");
//			boolean NoTransactionPresent = verifyElementExist(PWAHamburgerMenuPage.objNoTransaction, "No Transactions");
//			if (NoTransactionPresent == true) {
//				verifyElementExist(PWAHamburgerMenuPage.objMyTransactionDate, "MyTransaction Date");
//				verifyElementExist(PWAHamburgerMenuPage.objMyTransactionPackName, "MyTransaction Name");
//				verifyElementExist(PWAHamburgerMenuPage.objMyTransactionPackStatus, "MyTransaction Status");
//			} else {
//				if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionDate, "MyTransaction Date") == true) {
//					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackName,
//							"MyTransaction Name") == true) {
//						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackName));
//						logger.info(
//								"MyTransactionPackName : " + getText(PWAHamburgerMenuPage.objMyTransactionPackName));
//						extent.extentLogger(
//								"<b>" + "MyTransactionPackName : "
//										+ getText(PWAHamburgerMenuPage.objMyTransactionPackName),
//								"objMyTransactionPackName");
//					}
//					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackStatus,
//							"MyTransaction Status") == true) {
//						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackStatus));
//						logger.info("MyTransactionPackStatus : "
//								+ getText(PWAHamburgerMenuPage.objMyTransactionPackStatus));
//						extent.extentLogger(
//								"<b>" + "MyTransactionPackStatus : "
//										+ getText(PWAHamburgerMenuPage.objMyTransactionPackStatus),
//								"objMyTransactionPackStatus");
//					}
//					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus,
//							"MyTransaction AutoRenewal Status") == true) {
//						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus));
//						logger.info("MyTransactionAutoRenewalStatus : "
//								+ getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus));
//						extent.extentLogger(
//								"<b>" + "MyTransactionAutoRenewalStatus : "
//										+ getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus),
//								"objMyTransactionAutoRenewalStatus");
//					}
//				}
//			}
//		}
//		validateDisplayLanguagePopup();
//		getWebDriver().navigate().refresh();
////		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
//		waitTime(5000);
//	}

	public void WEBPWAValidatingSubscriptionAndTransaction(String userType) throws Exception {
		if ((userType.equals("Guest") || (userType.equals("NonSubscribedUser")))) {
			extent.HeaderChildNode("Validation of Get Premium CTA on Carousel");
			List<WebElement> ele = getWebDriver().findElements(PWAHomePage.objGetPremiumWeb);
			System.out.println(ele.size());
			if (ele.size() == 0) {
				System.out.println("Get Premium CTA on Carousel is not displayed");
				logger.info("Get Premium CTA on Carousel is not displayed");
				extent.extentLogger("<b>" + "Get Premium CTA on Carousel is not displayed..",
						"Get Premium CTA on Carousel is not displayed.");
			} else {
				System.out.println("Get Premium CTA on Carousel is displayed");
				logger.info("Get Premium CTA on Carousel is displayed");
				extent.extentLogger("<b>" + "Get Premium CTA on Carousel is displayed..",
						"Get Premium CTA on Carousel is displayed.");
				for (int i = 1; i < ele.size(); i++) {
					verifyElementExist1(ele.get(i), "Get Premium CTA on Carousel");
				}
			}
			if (userType.equals("Guest")) {
				extent.HeaderChildNode("Validating BuySubscription and HaveAPrepaidCode under MyPlans");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
				if (verifyElementExist(PWAHamburgerMenuPage.objPlans, "My Plans")) {
					verifyElementExist(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription");
					verifyElementExist(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code");
				}
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHomeInOpenMenuTab, "Home");
			}
		}
	}

	/**
	 * Method to wait for the element and click on it once displayed. The method
	 * will wait for the element to be located for a maximum of given seconds. The
	 * method terminates immediately once the element is located
	 */

	public void waitForElementAndClick(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator).click();
				logger.info("Clicked element " + message);
				extent.extentLogger("clickedElement", "Clicked element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to click element " + message);
					extent.extentLoggerFail("failedClickElement", "Failed to click element " + message);
				}
			}
		}
	}

//	public void WEBPWAValidatingSubscribeLinks(String userType) throws Exception {
//		extent.HeaderChildNode(" Validating Subscription Link");
//		Thread.sleep(10000);
//		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
//		waitTime(2000);
//		type(PWASearchPage.objSearchEditBox, "Bhinna", "Search Field");
//		waitTime(3000);
//		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
//		waitTime(10000);
//		if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Close in Register Pop Up")) {
//			click(PWASearchPage.objCloseRegisterDialog, "Close in Register Pop Up");
//			logger.info("clicked on popup close button");
//			extent.extentLogger("clicked on popup close button", "clicked on popup close button");
//		} else {
//			logger.info("Register Popup not displayed");
//			extent.extentLogger("Register Popup not displayed", "Register Popup not displayed");
//		}
//		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//			verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
//		}
//		Thread.sleep(5000);
//		try {
//			waitForElementDisplayed(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, 30);
//
//			// Validating GET PREMIUM CTA BUTTON below Player
//			extent.HeaderChildNode("Validating Get Premium CTA below the player");
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer,
//					"GET PREMIUM CTA BELOW PLAYER ") == true) {
//				click(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, "GET PREMIUM CTA BELOW PLAYER");
//
//				Thread.sleep(3000);
//				extent.HeaderChildNode("Validating Get Premium Popup");
//				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
//				}
//
//			} else {
//				extent.HeaderChildNode("Validating Get Premium Popup");
//				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
//				}
//
//			}
//		} catch (Exception e) {
//			System.out.println("GetPremiumCTAbelowPlayer is not displayed");
//		}
//		waitTime(2000);
//		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//		}
//		click(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
//		waitTime(5000);
//		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
//			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
//		}
//
//		if(userType.equals("Guest") || userType.equals("NonSubscribedUser"))
//		{
//			extent.HeaderChildNode("Validating Player In-line Subscribe link");
//			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
//			waitTime(2000);
//			type(PWASearchPage.objSearchEditBox, "Londonalli Lambodara", "Search Field");
//			waitTime(3000);
//			click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
//			waitTime(10000);
//
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//			}
//
//			verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Player In-line Subscribe link");
//
//			waitTime(2000);
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//			}
//			click(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
//			waitTime(5000);
//
//			if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
//				click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
//			}
//			
//		}
//
//	}

	public void WEBPWAValidatingSubscribeLinks(String userType) throws Exception {
		extent.HeaderChildNode(" Validating Subscription Link");
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			extent.HeaderChildNode("Validating Player In-line Subscribe link");
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
			waitTime(2000);
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieNoTrailer2");
			mandatoryRegistrationPopUp(userType);
			type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
			waitTime(3000);
			click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
			waitTime(10000);
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}

			if (verifyElementExist(PWAPlayerPage.objSubscribeNowLink, "Player In-line Subscribe link")) {
				System.out.println("Player In-Line Subscribe link is displayed");
				extent.extentLogger("Player In-Line Subscribe link is displayed",
						"Player In-Line Subscribe link is displayed");
			} else {
				System.out.println("Player In-Line Subscribe link is not displayed");
				extent.extentLoggerFail("Player In-Line Subscribe link is not displayed",
						"Player In-Line Subscribe link is not displayed");
			}

			waitTime(2000);
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "ZeeLogo");
			waitTime(5000);
		}
	}

	/**
	 * Function to enter url
	 */
	public void enterURLInWEBBrowser(String browser, String url) {
		extent.HeaderChildNode("Enter Browser URL");
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				getWebDriver().get(url);
				extent.extentLogger("enteredURL", "Entered " + url + " in " + browser + " browser");
				logger.info("Entered " + url + " in " + browser + " browser");
			} catch (Exception e) {
				extent.extentLogger("failToEnterURL", "Failed to enter " + url + " in " + browser + " browser");
			}
		}
	}

	public void verifyUIofHomePage() throws Exception {
		extent.HeaderChildNode("Validation of UI of Homepage");
		waitTime(5000);
		String tab = getText(PWAHomePage.objActiveTab);
		System.out.println(tab);
		extent.HeaderChildNode("Validating user is landing on Homepage by default");
		if (tab.equalsIgnoreCase("Home")) {
			logger.info("Navigated to Home page");
			extent.extentLogger("Home Page", "Navigated to Home page");
		} else {
			logger.info("Not navigated to Home page");
			extent.extentLogger("Home Page", "Not navigated to Home page");
		}
		extent.HeaderChildNode("Validating Zee Logo on Homepage");
		verifyElementPresent(PWAHomePage.objZeeLogo, "Zee Logo");
		extent.HeaderChildNode("Validating Search button on Homepage");
		verifyElementPresent(PWAHomePage.objSearchBtn, "Search button");
		extent.HeaderChildNode("Validating Language Selection option on Homepage");
		verifyElementExist(PWAHomePage.objLanguageBtn, "Language Selection Button");
		extent.HeaderChildNode("Validating Subscribe button on Homepage");
		verifyElementExist(PWAHomePage.objSubscribeBtn, "Subscribe button");
		extent.HeaderChildNode("Validating Hamburger menu on Homepage");
		verifyElementPresent(PWAHomePage.objHamburgerMenu, "Hamburger menu");
		extent.HeaderChildNode("Validating Login button on Homepage");
		verifyElementExist(PWALoginPage.objWebLoginBtn, "Login button");
		extent.HeaderChildNode("Validating Sign Up for free button on Homepage");
		verifyElementExist(PWALoginPage.objSignUpBtnWEB, "Sign Up for free");
	}

	public void verifyLiveTvAndChannelGuideScreen() throws Exception {
		extent.HeaderChildNode("Validation of UI of Live Tv and Channel Guide");
		waitTime(15000);
		System.out.println(getText(PWAHomePage.objActiveTab));
		// validateDisplayLanguagePopup();
		partialScroll();
		waitTime(2000);
		if (verifyElementExist(PWAHomePage.objHomeBarText("Live TV"), "Live TV Tab")) {
			click(PWAHomePage.objHomeBarText("Live TV"), "Live TV Tab");
		} else if (verifyElementExist(PWAHomePage.objMoreMenuIcon, "More Menu Icon") == true) {
			verifyElementPresentAndClick(PWAHomePage.objMoreMenuIcon, "More Menu Icon");
			waitTime(5000);
			verifyElementPresent(PWAHomePage.objMoreMenuTabs("Live TV"), "Live TV Tab");
			click(PWAHomePage.objMoreMenuTabs("Live TV"), "Live TV Tab");
		} else {
			click(PWALiveTVPage.objLiveTVMenu, "Live TV Tab");
		}

		waitTime(10000);
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
		String channelTitle;
		channelTitle = getWebDriver().findElement(PWALiveTVPage.objLiveChannelCardTitle).getText();
		System.out.println(channelTitle);
		extent.HeaderChildNode(
				"Validating that user is navigated to respective Live Channel consumption screen post tapping on Live Channel Card");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveChannelCardProgressBar, "Live Channel Card");
		waitTime(5000);
		if (verifyElementExist(PWALiveTVPage.objGoHomeLink, "GO HOME ") == true) {
			BackButton(1);
			Thread.sleep(5000);
			channelTitle = getWebDriver().findElement(PWALiveTVPage.objLiveChannelCardTitle1).getText();
			System.out.println(channelTitle);
			verifyElementPresentAndClick(PWALiveTVPage.objLiveChannelCard1, "Live Channel Card");
		}
		String playerPageChannelTitle = getWebDriver().findElement(PWALiveTVPage.objLiveChannelConsumptionPageTitle)
				.getText();
		System.out.println(playerPageChannelTitle);
		if (channelTitle.equalsIgnoreCase(playerPageChannelTitle)) {
			softAssert.assertEquals(channelTitle.equalsIgnoreCase(playerPageChannelTitle), false,
					"Navigated to respective Live Channel Consumption screen");
			logger.info("Navigated to respective Live Channel Consumption screen");
			extent.extentLogger("Live Channel Page", "Navigated to respective Live Channel Consumption screen");
		} else {
			softAssert.assertEquals(true, true, "Not navigated to respective Live Channel Consumption screen");
			softAssert.assertAll();
			logger.info("Not navigated to respective Live Channel Consumption screen");
			extent.extentLogger("Live Channel Page", "Not navigated to respective Live Channel Consumption screen");
		}
		waitTime(2000);
		BackButton(1);
		waitTime(5000);
		extent.HeaderChildNode("Validating Live Tv Language Filter Option");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTvFilterOption, "Live TV Language Filter");
		verifyElementPresentAndClick(PWALiveTVPage.objHindiFiltrOptn, "Language Filter");
		String selectedLanguage = getWebDriver().findElement(PWALiveTVPage.objNoOfLangSelectedText).getText();
		System.out.println(selectedLanguage);
		verifyElementPresentAndClick(PWALiveTVPage.objResetBtn, "Reset Button");
		String selectedLang2 = getWebDriver().findElement(PWALiveTVPage.objNoOfLangSelectedText).getText();
		System.out.println(selectedLang2);
		verifyElementPresentAndClick(PWALiveTVPage.objApplyBtn, "Apply Button");
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTVToggleInactive, "Live TV Toggle");
		extent.HeaderChildNode("Validating UI of Channel Guide Screen");
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
		verifyElementExist(PWALiveTVPage.objChannelDayStrip, "Channel/Day Strip");
		JSClick(PWALiveTVPage.objChannelDayStrip, "Channel/Day Strip");
		JSClick(PWALiveTVPage.objUpcomingLiveProgramDate, "Upcoming Live Program Date");
		extent.HeaderChildNode("Validating Channel Guide Sort Option");
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideSortOption, "Sort Option");
		verifyElementPresent(PWALiveTVPage.objSortByPopularity, "Sort By Popularity Option");
		verifyElementPresent(PWALiveTVPage.objSortByAZ, "Sort by A-Z Option");
		extent.HeaderChildNode("Validating Channel Guide Filter Option");
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideFilterOption, "Filter Option");
		verifyElementPresentAndClick(PWALiveTVPage.objHindiFiltrOptn, "Language Filter");
		String selectedLang = getWebDriver().findElement(PWALiveTVPage.objNoOfLangSelectedText).getText();
		System.out.println(selectedLang);
		verifyElementPresentAndClick(PWALiveTVPage.objResetBtn, "Reset Button");
		String selectedLang1 = getWebDriver().findElement(PWALiveTVPage.objNoOfLangSelectedText).getText();
		System.out.println(selectedLang1);
		verifyElementPresentAndClick(PWALiveTVPage.objApplyBtn, "Apply Button");
		waitTime(10000);
		extent.HeaderChildNode("Validating share functionality for Upcoming Live Program");
//		verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgram, "Upcoming Live Program");
		verifyElementPresent(PWALiveTVPage.objUpcomingLiveProgram, "Upcoming Live Program");
		waitTime(3000);
		JSClick(PWALiveTVPage.objUpcomingLiveProgram, "Upcoming Live Program");
		waitTime(10000);
		verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgramShareBtn, "Share button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALiveTVPage.objFacebookShareBtn, "Share to Facebook");
		waitTime(3000);
		verifyAlert();
		switchToWindow(2);
		if (!verifyElementExist(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(4000);
		}
		verifyElementPresentAndClick(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		waitTime(7000);
		acceptAlert();
		waitTime(3000);
		switchToParentWindow();
		waitTime(3000);
		if (verifyElementExist(PWALiveTVPage.objUpcomingLiveProgramCloseBtn, "Popup Close Button")) {
			verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgramCloseBtn, "Popup Close Button");
		}
		waitTime(3000);
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
	}

	// ---------------------------------------------------------------

	// Sushma

	public void searchResultScreen(String title) throws Exception {

		searchEpisode("Anika tries to be careful");
		searchMovie("Gooli");
		searchShow("The toy box");
		searchActive();
		extent.HeaderChildNode("Validating that user is able to enter keys in search box.");
		type(PWASearchPage.objSearchEditBox, title, "Search bar");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		extent.HeaderChildNode(
				"Validating that search result screen is displayed once user enters 3rd character in the search box.");
		String enteredValue = getAttributValue("value", PWASearchPage.objSearchEditBox);
		if (enteredValue.length() >= 3) {
			if (verifyElementExist(PWASearchPage.objSearchResultScreen, "Search result screen")) {
				logger.info("Search result screen is displayed once user enters 3rd character in the search box.");
				extent.extentLogger("Search result screen",
						"Search result screen is displayed once user enters 3rd character in the search box.");
			} else {
				logger.info("Search result screen is not displayed");
				extent.extentLogger("Search result screen",
						"Search result screen is not displayed when user enters less than 3 characters in the search box.");
			}
		} else {
			logger.info(
					"Search result screen is not displayed when user enters less than 3 characters in the search box.");
		}
		waitTime(10000);

		extent.HeaderChildNode("Validating that related search results are available under each tabs");

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Episodes"), "Episodes Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Movies"), "Movies Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("News"), "News Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Music"), "Music Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Videos"), "Videos Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
	}

	public void searchEpisode(String title) throws Exception {
		HeaderChildNode("Validating that search result displayed when user search for episode");
		type(PWASearchPage.objSearchEditBox, title, "Search bar");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		if (!verifyElementExist(PWASearchPage.objVoiceSearchButton, "Mic")) {
			logger.info("Voice search icon is not present while search result is present");
			extent.extentLogger("Mic", "Voice search icon is not present while search result is present");
		}
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Episodes"), "Episodes Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		clearField(PWASearchPage.objSearchEditBox, "Search field");
	}

	public void searchShow(String title) throws Exception {
		HeaderChildNode("Validating that search result displayed when user search for show");
		type(PWASearchPage.objSearchEditBox, title, "Search bar");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		clearField(PWASearchPage.objSearchEditBox, "Search field");
	}

	public void searchMovie(String title) throws Exception {
		HeaderChildNode("Validating that search result displayed when user search for Movie");
		type(PWASearchPage.objSearchEditBox, title, "Search bar");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Movies"), "Movies Tab");
		verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result");
		clearField(PWASearchPage.objSearchEditBox, "Search field");
	}

	public void searchActive() throws Exception {
		HeaderChildNode("Validating Search result displayed when user search for Active Program");
		Back(1);
		waitTime(5000);
		// partialScroll();
		waitTime(2000);
		if (verifyElementExist(PWAHomePage.objHomeBarText("Live TV"), "Live TV Tab")) {
			click(PWAHomePage.objHomeBarText("Live TV"), "Live TV Tab");
		} else if (verifyElementExist(PWAHomePage.objMoreMenuIcon, "More Menu Icon") == true) {
			verifyElementPresentAndClick(PWAHomePage.objMoreMenuIcon, "More Menu Icon");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHomePage.objMoreMenuTabs("Live TV"), "Live TV Tab");
		} else {
			click(PWALiveTVPage.objLiveTVMenu, "Live TV Tab");
		}
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
		waitTime(8000);
		verifyElementExist(PWASearchPage.objActivePrograme, "Active program");
		String ActiveProgram = getText(PWASearchPage.objActivePrograme);
		System.out.println(ActiveProgram);
		click(PWAHomePage.objZeelogo1, "Home page");
		waitTime(3000);
		click(PWAHomePage.objSearchBtn, "Search");
		type(PWASearchPage.objSearchEditBox, ActiveProgram, "Edit field");
		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		if (verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result")) {
			logger.info("Active program Search result is present in all tab");
			extent.extentLogger("Result", "Active program Search result is present in all tab");
		} else {
			logger.info("Active program Search result is not present in all tab");
			extent.extentLogger("Result", "Active program Search result is not present in all tab");
		}
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("News"), "News Tab");
		if (verifyElementExist(PWASearchPage.objAssetTitleSearchNavigationTab, "related search result")) {
			logger.info("Search result is present in news tab");
			extent.extentLogger("Result", "Search result is present in news tab");
		} else {
			logger.info("Search result is not present in news tab");
			extent.extentLogger("Result", "Search result is not present in news tab");
		}
		clearField(PWASearchPage.objSearchEditBox, "Search field");
	}

	public void landingOnSearchScreen() throws Exception {
		extent.HeaderChildNode("Validating that user lands on search landing screen");

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		if (verifyElementExist(PWASearchPage.objSearchEditBox, "Search EditBox")) {
			logger.info("User landed on Search landing screen post tapping on search icon");
			extent.extentLogger("Search landingscreen",
					"User landed on Search landing screen post tapping on search icon");
		}

		extent.HeaderChildNode("Validating that voice search icon is displayed on Search box ( Microphone icon)");

		verifyElementExist(PWASearchPage.objVoiceSearchButton, "Voice Search icon");
	}

	public void liveTv(String title) throws Exception {
		extent.HeaderChildNode(
				"Validating that Live TV card is displayed when user searches by any On Going Live TV content name");
		waitTime(3000);

		type(PWASearchPage.objSearchEditBox, title, "Search bar");
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		waitTime(10000);
		if (verifyElementExist(PWALiveTVPage.objLivelogo, "Live logo")) {
			logger.info("Live Tv card is displayed");
			extent.extentLogger("Live Tv card", "Live Tv card is displayed");
		} else {
			logger.info("Live Tv card is not displayed");
			extent.extentLogger("Live Tv card", "Live Tv card is not displayed");
		}
		waitTime(3000);
		click(PWALiveTVPage.objLivelogo, "Live logo");
		waitTime(10000);
		Back(1);
		waitTime(5000);
		extent.HeaderChildNode("Validating that the Recent Searches overlay is available on Search landing screen");
		verifyElementExist(PWASearchPage.objRecentSearchesOverlay, "Recent Searches overlay");
	}

	public void navigationToConsumptionScreenThroughTrendingSearches(String userType) throws Exception {

		extent.HeaderChildNode("Navigation to Consumption Screen through Trending Searches");
		waitTime(3000);
		mandatoryRegistrationPopUp(userType);
		if (verifyElementExist(PWASearchPage.objTrendingSearchesTray, "Trending Searches tray")) {

			verifyElementExist(PWASearchPage.objFirstAssetThumbnailTrendingSearch,
					"First asset thumbnail of Trending searches tray");

			verifyElementExist(PWASearchPage.objFirstAssetTitleTrendingSearch,
					"First asset title of Trending searches tray");

			if (verifyElementExist(PWAPlayerPage.objCloseBtnLoginPopup, "Login Pop-up")) {
				click(PWAPlayerPage.objCloseBtnLoginPopup, "Login Pop-up");
			}

			String searchScreenTitle = getElementPropertyToString("innerText",
					PWASearchPage.objFirstAssetTitleTrendingSearch, "FirstAssetTitleTrending Search");
			System.out.println(searchScreenTitle);
			click(PWASearchPage.objFirstAssetThumbnailTrendingSearch,
					"First asset thumbnail of Trending searches tray");
			waitTime(6000);
			waitTime(6000);

			if (verifyElementExist(PWAPlayerPage.objCloseRegisterDialog, "Register popup")) {
				waitTime(2000);
				click(PWAPlayerPage.objCloseRegisterDialog, "Register popup close icon");
			}

			if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt,
					"Complete Your Profile pop up")) {
				click(CompleteYourProfilePopUp.objCloseBtn, "Complete your profile popup Close Button");

			}

			if (verifyElementExist(PWAPlayerPage.subscribePopUp, "Subscription popup")) {
				waitTime(3000);
				click(PWAPlayerPage.ObjSubscriptionpopupCloseIcon, "Subscription popup close icon");
			}

			if (verifyElementExist(PWASearchPage.objShowTitleInConsumptionPage, "Show title In Consumption")) {
				String ConsumptionScreenShowTitle = getText(PWASearchPage.objShowTitleInConsumptionPage);
				waitTime(3000);
				System.out.println(searchScreenTitle + " " + ConsumptionScreenShowTitle);
				if (searchScreenTitle.contains(ConsumptionScreenShowTitle)) {
					logger.info("user is navigated to respective consumption screen");
					extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
				} else {
					logger.info("user is not navigated to respective consumption screen");
					extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
				}
			} else {
				String showtitle = getText(PWASearchPage.objShowTitle(searchScreenTitle));
				waitTime(3000);

				if (searchScreenTitle.contains(showtitle)) {
					logger.info("user is navigated to respective consumption screen");
					extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
				} else {
					logger.info("user is not navigated to respective consumption screen");
					extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
				}
			}
		} else {
			logger.info("Trending searched tray is not displayed");
			extent.extentLogger("Search Screen", "Trending searched tray is not displayed");
		}
		Back(1);
	}

	public String fetchLiveContent() throws Exception {
		extent.HeaderChildNode("Fetching the Live Content name from Live Tab");

		getWebDriver().get(URL);
		waitTime(5000);
		partialScroll();
		waitTime(2000);
		if (verifyElementExist(PWAHomePage.objHomeBarText("Live TV"), "Live TV Tab")) {
			click(PWAHomePage.objHomeBarText("Live TV"), "Live TV Tab");
		} else if (verifyElementExist(PWAHomePage.objMoreMenuIcon, "More Menu Icon") == true) {
			verifyElementPresentAndClick(PWAHomePage.objMoreMenuIcon, "More Menu Icon");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHomePage.objMoreMenuTabs("Live TV"), "Live TV Tab");
		} else {
			click(PWALiveTVPage.objLiveTVMenu, "Live TV Tab");
		}

		waitTime(15000);
		String liveTVContentName = getText(PWALiveTVPage.objCardTitle);
		System.out.println(liveTVContentName);

		return liveTVContentName;

	}

	/**
	 * Function to Onboarding scenarios to their respective Test scenario.
	 */
	public void OnboardingScenario(String userType) throws Exception {
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest user ");
			navigationToMyPlanFromHome("NewRegister");
			navigationToMyPlanFromHome("Logged in");
			playerInLineLoginCheck();
			navigationToCTAInPlayerFromSearch(userType);
			if (verifyElementExist(PWALoginPage.objLoginBtnWEB, "Login")) {
				extent.extentLogger("Not Logged in", "User is not logged in");
				logger.info("User is not logged in");
				noLogoutOption();
				forgotPassword();
				waitTime(5000);
				getWebDriver().get(URL);
				waitTime(8000);
				if (!verifyElementExist(PWALoginPage.objLoginBtnWEB, "Login")) {
					logout();
				}
				waitTime(4000);
				// SANITY
				phoneNumberRegistration();
				emailRegistration();
				facebookLogin();
//				twitterLogin();
				forgotPasswordEmail();
				forgotPasswordMobileNumber();
			}
			break;
		case "NonSubscribedUser":
			extent.HeaderChildNode("Non-Subscribed user ");
			waitTime(3000);
			myaccountOptionsVerification();
			NavigateToMyProfilePage();
			verificationsInMyProfilePage();
			editProfileFuncionality();
			subscribeCTAFuncionality();
			changePasswordFuncionality();
			break;
		case "SubscribedUser":
			extent.HeaderChildNode("Subscribed user ");
			waitTime(3000);
			myaccountOptionsVerification();
			NavigateToMyProfilePage();
			verificationsInMyProfilePage();
			editProfileFuncionality();
			myPlanVerification();
			changePasswordFuncionality();
		}
	}

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToMyPlanFromHome(String UserType) throws Exception {
		extent.HeaderChildNode(
				"Validating user navigated to signin screen from my plans screen through logged in and NewRegister email id");
		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscription button");
		waitTime(3000);
		if (verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Subscription page")) {
			logger.info("User is navigated to Subscription page");
			extent.extentLogger("Subscription page", "User is navigated to Subscription page");
			if (UserType == "Logged in") {
				navigationToSignInFromMyplans("Logged in");
			}
			if (UserType == "NewRegister") {
				navigationToSignInFromMyplans("NewRegister");
			}
		} else {
			logger.info("User is not navigated to Subscription page");
			extent.extentLogger("Subscription page", "User is not navigated to Subscription page");
		}
	}

	public void playerInLineLoginCheck() throws Exception {
		extent.HeaderChildNode("Login page check from Player Inline popup");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer2");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(6000);
		String user = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		mandatoryRegistrationPopUp(user);
		click(PWASearchPage.objPremiumSearchResult(keyword), "Premium content");
		waitTime(12000);
		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			click(PWAPremiumPage.objClosePremiumPopup, "close button");
		}
		verifyElementPresent(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
		JSClick(PWASubscriptionPages.objLoginLinkInPlayer, "Login link");
		waitTime(8000);
		if (verifyElementExist(PWALoginPage.objLoginPageheader, "Login")) {
			logger.info("User is redirected to login page");
			extent.extentLogger("Login", "User is redirected to login page");
		}
		Back(1);
		waitTime(8000);
		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			click(PWAPremiumPage.objClosePremiumPopup, "close button");
		}
		click(PWAHomePage.objZeeLogo, "Zee logo");
	}

	public void gmailLogin() throws Exception {
		extent.HeaderChildNode("Login through Gmail");
		// verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger
		// menu");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		System.out.println(getWebDriver().getCurrentUrl());
		System.out.println(getWebDriver().getWindowHandles());
		verifyElementExist(PWALoginPage.objGoogleIcon, "Google Icon");
		Actions act = new Actions(getWebDriver());
		act.click(getWebDriver().findElement(By.id("gbtn"))).build().perform();
		waitTime(1000);
		act.click(getWebDriver().findElement(By.id("gbtn"))).build().perform();
		waitTime(10000);
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window("CDwindow-2");
		waitTime(4000);
		if (verifyElementExist(PWALoginPage.objGmailEmailField, " Email Field")) {
			type(PWALoginPage.objGmailEmailField, "newzee5igs@gmail.com", "Emial Field");
			hideKeyboard();
			verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objGmailPasswordField, " Password Field");
			type(PWALoginPage.objGmailPasswordField, "User@123", "Password Field");
			hideKeyboard();
			verifyElementPresentAndClick(PWALoginPage.objGmailNextButton, "clicked on next button");
			waitTime(7000);
			getDriver().switchTo().window("CDwindow-0");
			String url = getDriver().getCurrentUrl();
			System.out.println(url);
			if (url.contains("https://accounts.google.com")) {
				getDriver().switchTo().window("CDwindow-0");
				logger.info("Google verification page displayed");
				extent.extentLogger("Otp", "Google verification page displayed");
				Back(1);
			} else {
				getDriver().switchTo().window("CDwindow-0");
				if (verifyElementExist(PWALoginPage.objLoginTxt, "Login text")) {
					logger.info("Google verification page displayed");
					extent.extentLogger("Login", "Google verification page displayed");
					Back(1);
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
						click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
						logout();
					}
				} else {
					verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					if (verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
						logger.info("User Logged in Successfully");
						extent.extentLogger("Logged in", "User Logged in Successfully");
						click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Close button");
						logout();
					}

				}
			}
		}
	}

	/**
	 * Function To check That user is logged in succesfully and Login,SignUP ption
	 * is not displayed for Logged in user.
	 */
	public void verificationOfLoggedIn() throws Exception {
		extent.HeaderChildNode("Verification of Logged in");
		System.out.println("verificationOfLoggedIn");
		waitTime(3000);
		if (verifyElementExist(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon")) {
			logger.info("User is logged in successfully");
			extent.extentLogger("Profile icon", "User is logged in successfully");
		}
		if (!(verifyElementExist(PWALoginPage.objLoginBtnWEB, "Login"))) {
			logger.info("Login button is not displayed");
			extent.extentLogger("Login Button", "Login button is not displayed for logged in user");
		}
		if (!(verifyElementExist(PWALoginPage.objSignUpBtnWEB, "SignUp"))) {
			logger.info("Sign Up button is not displayed");
			extent.extentLogger("Sign Up Button", "SignUp button is not displayed for logged in user");
		}
	}

	/**
	 * Generic function Verification Of Options displayed in MyAccount.
	 */
	public void myaccountOptionsVerification() throws Exception {
		extent.HeaderChildNode("My account options verification");
		System.out.println("myaccountOptionsVerification");
		waitTime(7000);

		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");

		waitTime(7000);
		// verifications
		NavigationsToMySubsccription();
		NavigationsToMyWatchlist();
		NavigationsToMyReminders();
		NavigationsToMyTransactions();

	}

	/**
	 * Function for Navigation to MyWatchlist .
	 */
	public void NavigationsToMyWatchlist() throws Exception {
		extent.HeaderChildNode("My Watchlist in Profile Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Watchlist"), "My watchlist");
		waitTime(4000);
		// verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My
		// Watchlist"), "My Watchlist page");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Watchlist page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyReminders .
	 */
	public void NavigationsToMyReminders() throws Exception {
		extent.HeaderChildNode("My Reminders in Profile Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Reminders"), "My Reminders");
		waitTime(4000);
		// verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My
		// Reminders"), "My Reminders page");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Remainders page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MySubscription.
	 */
	public void NavigationsToMySubsccription() throws Exception {
		extent.HeaderChildNode("My subscriptions in Profile Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");
		waitTime(4000);
		// verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My
		// Subscription"), "My Subscription page");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Subscription page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyTransaction.
	 */
	public void NavigationsToMyTransactions() throws Exception {
		extent.HeaderChildNode("My Transactions in Profile Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Transactions"), "My Transactions");
		waitTime(4000);
		// verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My
		// Transactions"), "My Transactions page");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Transaction page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyProfilePage.
	 */
	public void NavigateToMyProfilePage() throws Exception {
		extent.HeaderChildNode("Navigate To MyProfile Page");
		System.out.println("NavigateToMyProfilePage");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		click(PWAHamburgerMenuPage.objProfileTextWEB, "profile");
	}

	/**
	 * Function To Verifing the options present in MyProfilePage.
	 */
	public void verificationsInMyProfilePage() throws Exception {
		extent.HeaderChildNode("verifications In MyProfile Page");
		System.out.println("verificationsInMyProfilePage");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageNameTxtWEB, "User name");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageUserIdTxt, "User id");
		verifyElementPresent(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
	}

	/**
	 * Function To check the Functionality of EditProfile option .
	 */

	public void editProfileFuncionality() throws Exception {
		extent.HeaderChildNode("Edit Profile Funcionality");
		System.out.println("editProfileFuncionality");
		waitTime(6000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objEditProfileTextWEB, "edit profile page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
		clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "email field");
		type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");
		String firstName = findElement(PWAHamburgerMenuPage.objEditProfileFirstName).getAttribute("value");
		System.out.println(firstName);
		if (firstName.contains("Zee5")) {
			logger.info("User can edit in Edit profile screen");
			extent.extentLogger("Edit", "User can edit in Edit profile screen");
		} else {
			logger.info("User edit functionality in Edit profile screen failed");
			extent.extentLoggerFail("Edit", "User edit functionality in Edit profile screen failed");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "save changes");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "go back button");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
	}

	/**
	 * Function To check the Funcionality of SubscribeCTA option.
	 */
	public void subscribeCTAFuncionality() throws Exception {
		extent.HeaderChildNode("subscribe CTA Funcionality");
		System.out.println("subscribeCTAFuncionality");
		if (verifyElementExist(PWAHamburgerMenuPage.objSubscritionBtn, "Subscribe cta")) {
			verifyElementPresent(PWALoginPage.objsubscription, "subscriptions page");
			// BackButton(1);
			waitTime(5000);
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		} else {
			logger.info("Subscribe Cta is not displayed");
			extent.extentLoggerFail("CTA", "Subscribe Cta is not displayed");
		}
	}

	/**
	 * Function To check the Functionality of ChangePassword option.
	 */
	public void changePasswordFuncionality() throws Exception {
		extent.HeaderChildNode("change Password Funcionality");
		System.out.println("changePasswordFuncionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objChangePasswordBtn, "change password button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordTextWEB, "change password page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objChangeOldPassword, "password field");
		type(PWAHamburgerMenuPage.objChangeOldPassword, "User@123", "Current password field");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objNewPassword, "new password field");
		type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "new password field");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objNewPassword, "confirm password field");
		type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
		waitTime(3000);
		verifyElementPresent(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted, "Update password button");
		waitTime(2000);
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
		waitTime(5000);
	}

	/**
	 * Generic function to Logout.
	 */
	public void logout() throws Exception {
		extent.HeaderChildNode("Logout");
		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
		// click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("Logout"), "Logout option");
		waitTime(3000);
		verifyElementNotPresent(PWAHamburgerMenuPage.objProfileIconWEB, 5);
		if (verifyElementPresent(PWALoginPage.objLoginBtnWEB, "Logout")) {
			logger.info("User successfuly logged out");
			extent.extentLogger("Log out", "User successfuly logged out");
		}
		click(PWAHomePage.objZeeLogo, "Home page");
	}

	/**
	 * Function To check the Funcionality of MyPlan option.
	 */
	public void myPlanVerification() throws Exception {
		extent.HeaderChildNode("My Plan Verification");
		if (verifyElementExist(PWAHamburgerMenuPage.objMyplanText, "My plan")) {
			verifyElementPresent(PWAHamburgerMenuPage.objMyActivePlan, "My active plan");
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		} else {
			logger.info("My plan is not displayed");
			extent.extentLoggerFail("My plan", "My plan is not displayed");
		}

	}

//	/**
//	 * Function To check the SignIn page from MyPlans screen.
//	 */
//	public void navigationToMyPlanFromHome() throws Exception {
//		extent.HeaderChildNode("Validating user navigated to signin screen from my plans screen");
//		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscription button");
//		waitTime(3000);
//		if (verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "subscription page")) {
//			logger.info("User is navigated to Subscription page");
//			extent.extentLogger("Subscription page", "User is navigated to Subscription page");
//			navigationToSignInFromMyplans();
//		}
//
//		else {
//			logger.info("User is not navigated to Subscription page");
//			extent.extentLogger("Subscription page", "User is not navigated to Subscription page");
//
//		}
//	}

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToSignInFromMyplans(String Usertype) throws Exception {
		// Swipe("UP", 1);
		scrollDownWEB();
		if (verifyElementExist(PWASubscriptionPages.objadhocPopupArea, "Adoric Popup")) {
			click(PWASubscriptionPages.objadhocPopupSignUpBtn, "Adoric Popup SignUP Button");
			waitTime(4000);
			verifyElementPresent(PWASubscriptionPages.objadhocPopupRegestrationScreen, "Sign up page");
			waitTime(3000);
			Back(1);
			scrollDownWEB();
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			if (Usertype == "Logged in") {
				type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Email");
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyElementExist(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123", "Password");
				click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				waitTime(3000);
				if (verifyElementExist(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for logged in user");
					extent.extentLogger("Verification", "Verified subscribe flow for logged in user");
				}
			}
			if (Usertype == "NewRegister") {
				type(PWALoginPage.objEmailField, RandomStringGenerator(5) + "@gmail.com", "Email");
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyElementExist(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123", "Password");
				click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				waitTime(10000);
				if (verifyElementExist(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for logged in user");
					extent.extentLogger("Verification", "Verified subscribe flow for logged in user");
				}
			}
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
				logout();
			}
		} else {
			waitTime(3000);
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			if (Usertype == "Logged in") {
				type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Email");
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyElementExist(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123", "Password");
				click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				waitTime(4000);
				if (verifyElementExist(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for logged in user");
					extent.extentLogger("Verification", "Verified subscribe flow for logged in user");
				}
			}
			if (Usertype == "NewRegister") {
				type(PWALoginPage.objEmailField, RandomStringGenerator(5) + "@gmail.com", "Email");
				click(PWASubscriptionPages.objProceedBtnInSubscriptionPage, "Proceed button");
				waitTime(3000);
				verifyElementExist(PWASubscriptionPages.objPasswordPopupInSubscriptionPage, "Password field");
				type(PWASubscriptionPages.objPasswordField, "User@123", "Password");
				click(PWASubscriptionPages.objProceedButtonInPassword, "Proceed");
				waitTime(10000);
				if (verifyElementExist(PWASubscriptionPages.objAccountDetailInSubscription, "Account details")) {
					logger.info("Verified subscribe flow for New Register user");
					extent.extentLogger("Verification", "Verified subscribe flow for New Register user");
				}
			}
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
				logout();
				waitTime(4000);
			}
		}
	}

	public void NavigationsToMySubscription() throws Exception {
		extent.HeaderChildNode("My Subscription");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Subscription"), "My Subscription page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
	}

	/**
	 * Function To check the SignIn page from CTA in playerscreen and Verification
	 * of SubscribePopUP.
	 */
	public void navigationToCTAInPlayerFromSearch(String userType) throws Exception {
		extent.HeaderChildNode("Validating user navigated to signin from Subscribe CTA in player");
		if (verifyElementExist(PWAHomePage.objLanguageChangeContentPopup(), "Language change content PopUp")) {
			click(PWAHomePage.objLanguageChangeContentPopupCloseicon(), "Language change content PopUp close icon");
		}
		waitTime(2000);
		verifyElementExist(PWAHomePage.objSearchBtn, "Search button");
		click(PWAHomePage.objSearchBtn, "Search button");
//		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie2");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(3000);
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		waitTime(10000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		waitTime(3000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumCTAInPlater, "Subscribe CTA in player");
		waitTime(5000);
//		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//		}
		waitTime(3000);
		navigationToSignInFromCTAInPlayer();
	}

	/**
	 * Function To check the SignIn page from CTA in playerscreen and Verification
	 * of SubscribePopUP.
	 */
	public void navigationToSignInFromCTAInPlayer() throws Exception {
		extent.HeaderChildNode("Validating Subscribe popup post tapping Subscribe CTA in player");
		verifyElementPresent(PWASubscriptionPages.objSubscribepopup, "Subscribe popup");
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupPlan, "Subscribe popup plan");
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopipProceed, "Proceed button in Subscribe popup");
		click(PWASubscriptionPages.objGetPremiumPopipProceed, "Proceed button in Subscribe popup");
		waitTime(4000);
		verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
		if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
			logger.info("User is navigated to Home page");
			extent.extentLogger("Home page", "User is navigated to Home page");
		}
	}

	/**
	 * Function To check the Funcionality of ForgotPassword option.
	 */
	public void forgotPassword() throws Exception {
		extent.HeaderChildNode("Forgot password functionality");
		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitTime(5000);
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		waitTime(3000);
		type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Email field");
		// click(PWALoginPage.objForgotPasswordTxt, "forgot password Text");
		click(PWALoginPage.objForgotPasswordLinkButtonWEB, "Reset password button");
		waitTime(60000);
		String url = GmailInbox.readEmail("ZEE5 account password reset request");
		if (!url.isEmpty()) {
			getWebDriver().get(url);
			waitTime(5000);
			verifyElementExist(PWALoginPage.objForgotNextPageTextWEB, "Reset password page");
			type(PWALoginPage.objForgotNextPagePwsswordFielfd, "User@123", "Password");
			waitTime(5000);
			type(PWALoginPage.objForgotNextPageConfirmPasswordField, "User@123", "Confirm password");
			click(PWALoginPage.objForgotNextPageResetPaswwordButtonWEB, "Reset password");
			if (verifyElementExist(PWAPlayerPage.objfasterPopUp, "We are 3x faster(adhoc) popup")) {
				click(PWAPlayerPage.objfasterclosePopUp, "faster Pop up close button");
			}
			waitTime(10000);
			if (verifyElementExist(PWALoginPage.objLoginPageLoginBtn, "LoginButton")) {
				if (verifyElementExist(PWALoginPage.objEmailField, "Login page")) {
					type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Login");
					type(PWALoginPage.objPasswordField, "User@123", "Password");
					waitTime(5000);
					click(PWALoginPage.objLoginPageLoginBtn, "LoginButton");
					waitTime(7000);
				}
				if (verifyElementExist(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon")) {
					logger.info("User is successfully changed password and logged in");
					extent.extentLogger("Logged in", "User is successfully changed password and logged in");
				} else {
					logger.info("User is not logged in");
					extentLoggerWarning("Logged in", "User is not logged in");
				}
			} else {
				logger.info("Reset password link expired");
				extent.extentLoggerWarning("Reset link", "Reset password link expired");
			}
		} else {
			logger.info("User is not received the mail or the mail content is read");
			extent.extentLoggerWarning("Logged in", "User is not received the mail or the mail content is read");
			logger.info("User is not logged in");
			extent.extentLoggerWarning("Logged in", "User is not logged in");
		}
	}

	/**
	 * Function To check That Logout Option is not displayed in Hamburger menu.
	 */
	public void noLogoutOption() throws Exception {
		extent.HeaderChildNode("Checking no Logout option displayed for guest user");

		if ((verifyElementExist(PWALoginPage.objLoginBtnWEB, "Login"))) {
			logger.info("Logout option is not displayed for guest user");
			extent.extentLogger("Logout option", "Logout option is not displayed");
		}
	}

	// vinay

	// -------------------------------------------------------------------------------------------------

	// SATISH

	/**
	 * PWA Subscription Suite
	 */
	public void zeePWASubscriptionSuite(String userType) throws Exception {
		HeaderChildNode("PWA Subscription Scenarios Validation Suite");

		if (userType.equals("SubscribedUser")) {
			System.out.println();
		} else {

			zeePWASubscriptionScenariosValidation(userType, getPlatform());
			zeePWASubscriptionFlowFromHomePageHeaderSubscribeButton(userType, getPlatform());
		}
	}

	/**
	 * Guest User Subscription Flow
	 */
	public void zeePWAGuestUserSubscriptionFlow() throws Exception {
//		HeaderChildNode("PWA Subscription Flow");
		zeePWASelectPackPageValidation();
		zeePWAAccountInfoPageValidation();
		zeePWAPaymentPageValidation();
	}

	public void SubscriptionPopupScenarios(String userType) throws Exception {
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			zeePWAVerifyNavigationToSubscriptionFlowFromSubscriptionPopupFullscreenPlayer(userType);
			zeePWAVerifySubscriptionPopupAfterTrailerPlaybackIsComplete(userType);
		}
	}

	/**
	 * Non-Subscribed User Subscription Flow
	 */
	public void zeePWANonSubscribedUserSubscriptionFlow() throws Exception {
//		HeaderChildNode("PWA Subscription Flow");
		zeePWASelectPackPageValidation();
		zeePWAPaymentPageValidation();
	}

	/**
	 * Subscription Scenarios Validation
	 */
	public void zeePWASubscriptionScenariosValidation(String userType, String platform) throws Exception {
//		HeaderChildNode("PWA Subscription Scenarios Validation");
//		if(userType.equalsIgnoreCase("Non-Subscribed")) {
//			ZeePWALogin("E-mail", userType);
//		}

		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		// Scenario no. 89
		HeaderChildNode("Navigate to Subscription Flow From Home Page Header Subcribe Button");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtnTopHeader, "Subscribe Button in the Header");

		waitTime(2000);
		zeeSubscriptionPageValidationAndNavigateToHomePage();

		// Scenario no. 86
		HeaderChildNode("Navigate to Subscription Flow on playing BeforeTV content");
		waitTime(2000);
		scrollToTheElementWEB(PWAHomePage.objFirstContentCardOfTray("Before"));
		if (verifyElementExist(PWAHomePage.objFirstContentCardOfTray("Before"),
				"First Content Card Of Before TV Tray")) {
			click(PWAHomePage.objFirstContentCardOfTray("Before"), "First Content Card Of Before TV Tray");
			waitForElement(PWASubscriptionPages.objGetPremiumPopupTitle, 40, "Get Premium Popup Title");
			zeeVerifyGetPremiumPopup();
			waitTime(2000);
			if (userType.equalsIgnoreCase("Guest")) {
				zeeAccountInfoPageValidationAndNavigateToHomePage();
			} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
				zeePaymentPageValidationAndNavigateToHomePage();
			}
		} else {
			logger.info("Clicked on " + "Get Premium CTA On MastHead Carousel");
			extent.extentLoggerFail("Scrolling till BeforeTV Rail", "BeforeTV Rail does not exist");
		}

		// Scenario no. 90,98
		HeaderChildNode("Navigate to Subscription Flow from Home Page Get Premium CTA On Carousel");
		waitTime(2000);

		if (platform.equalsIgnoreCase("Android")) {

			verifyElementPresent(PWAHomePage.objGetPremiumWeb, "Get Premium CTA on Carousel");
			clickDirectly(PWAHomePage.objGetPremiumWeb, "Get Premium CTA on Carousel");
		} else if (platform.equalsIgnoreCase("Web")) {

			Actions action = new Actions(getWebDriver());
			action.moveToElement(findElement(PWAHomePage.objMastheadCarouselCurrentContent)).build().perform();

			for (int i = 0; i < 5; i++) {
				try {

					JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
					executor.executeScript("arguments[0].click();", findElement(PWAHomePage.objGetPremiumWeb));
					logger.info("Clicked on " + "Get Premium CTA On MastHead Carousel");
					extent.extentLogger("clickedElement", "Clicked on " + "Get Premium CTA On MastHead Carousel");
					break;
				} catch (Exception e) {
					Thread.sleep(1000);
					logger.error(e);
				}
			}
		}

		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		// Scenario no. 98
		zeePWAPromoCodeValidationInSelectPackPage(platform);
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

		if (userType.equalsIgnoreCase("Guest")) {
			// Scenario no. 96
			HeaderChildNode(
					"Navigate to Subscription Flow From 'Buy Subscription' option under My plans in hamburger menu");
			waitTime(10000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
			waitTime(2000);
//			validateDisplayLanguagePopup();
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
//			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objWebBuySubscriptionOption,
					"Buy Subscribe Option in Hamburger Menu");
			zeeSubscriptionPageValidationAndNavigateToHomePage();

			// Scenario no. 97
			HeaderChildNode(
					"Navigate to Subscription Flow From 'Have a Prepaid code' option under My plans in hamburger menu");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHaveAPrepaidCode,
					"Have A Prepaid Code? Option in Hamburger Menu");
			waitTime(2000);
			zeeSubscriptionPageValidationAndNavigateToHomePage();
		}

		// Scenario no. 91,92,94
		HeaderChildNode("Navigate to Subscription Flow From Adoric Popup/Get Premium popup On Playing Premium Content");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer2");
		zeeSearchForContentAndClickOnFirstResult(keyword);
		waitTime(2000);
		zeeVerifyGetPremiumPopup();
		waitTime(2000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}

		// Scenario no. 93
		HeaderChildNode("Navigate to Subscription Flow From Player In-line Subscribe link on Player");
		String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer2");
		zeeSearchForContentAndClickOnFirstResult(keyword1);
		waitTime(2000);
		verifyElementExist(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
		waitTime(2000);
		verifyElementPresentAndClick(PWAPlayerPage.objSubscribeNowLink, "In-Line Subscribe Link on Player");
		zeeVerifyGetPremiumPopup();
		waitTime(2000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}

		// Scenario no. 95
		HeaderChildNode(
				"Navigate to Subscription Flow From Subscription Get premium CTA below the player at consumption screen");
		zeeSearchForContentAndClickOnFirstResult("Ondh Kathe Hella");
		waitTime(2000);
		waitForElementAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, 30,
				"Get Premium Link below the Player");
		waitTime(2000);
		zeeVerifyGetPremiumPopup();
		waitTime(2000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}
	}

	/**
	 * Search For Content And Click On First Result
	 */
	public void zeeSearchForContentAndClickOnFirstResult(String contentName) throws Exception {
		// handle mandatory pop up
		String user = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		mandatoryRegistrationPopUp(user);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		type(PWASearchPage.objSearchEditBox, contentName + "\n", "Search bar");
		waitTime(5000);
		waitForElementDisplayed(PWASearchPage.objFirstSearchedAssetTitle, 20);
		waitTime(5000);
		String FirstSearchedAssetTitle = findElement(PWASearchPage.objFirstSearchedAssetTitle).getText();
		click(PWASearchPage.objFirstSearchedAssetTitle, "First Searched Asset Title: " + FirstSearchedAssetTitle);
	}

	/**
	 * PWA Subscription Page Validation
	 */
	public void zeeSubscriptionPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Subscription Page Validation and Navigate to Home Page");

		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * PWA Account Info Page Validation
	 */
	public void zeeAccountInfoPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Account Info Page Validation and Navigate to Home Page");

		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * PWA Payment Page Validation
	 */
	public void zeePaymentPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Payment Page Validation and Navigate to Home Page");

		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * Verify Get Premium Popup
	 */
	public void zeeVerifyGetPremiumPopup() throws Exception {
		HeaderChildNode("Verify Get Premium Popup");

		waitTime(2000);
		verifyElementExist(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopup99Plan, "99 Plan in Popup");
		waitTime(2000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Popup Proceed Button");

	}

	/**
	 * Subscription Flow From Home Page Header Subscribe Button Line No 89
	 */
	public void zeePWASubscriptionFlowFromHomePageHeaderSubscribeButton(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Header Subcribe Button");

		// Scenario no. 89
		waitTime(5000);
		click(PWAHomePage.objSubscribeBtnTopHeader, "Subscribe Button in the Header");
//		driver.findElement(PWAHomePage.objSubscribeButton).click();			
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Subscription Flow From Home Page Get Premium CTA on Carousel Line No 90
	 */
	public void zeePWASubscriptionFlowFromHomePageGetPremiumCTAOnCarousel(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Get Premium CTA On Carousel");

		// Scenario no. 90
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objGetPremium, "Get Premium CTA on Carousel");
		clickDirectly(PWAHomePage.objGetPremium, "Get Premium CTA on Carousel");
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Subscription Flow From Adoric Popup Line No 91 Subscription Flow From
	 * Subcribe Button On Playing Premium Content Line No 92 Subscription Flow From
	 * Subscribe popup on playing Before TV content Line No 94
	 */
	public void zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Adoric Popup/Get Premium popup On Playing Premium Content");

		// Scenario no. 91,92,94
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer2");
		zeeSearchForContentAndClickOnFirstResult(keyword);
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAAccountInfoPageValidation();
			zeePWAPaymentPageValidation();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWAPaymentPageValidation();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Subscription Flow From Player In-line Subscribe link Line No 93
	 */
	public void zeePWASubscriptionFlowFromPlayerInlineSubscribelink(String userType, String platform) throws Exception {
		HeaderChildNode("PWA Subscription Flow From Player In-line Subscribe link on Player");

		// Scenario no. 93
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieNoTrailer2");
		zeeSearchForContentAndClickOnFirstResult(keyword);
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAPlayerPage.objSubscribeNowLink, "In-Line Subscribe Link on Player");
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAAccountInfoPageValidation();
			zeePWAPaymentPageValidation();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWAPaymentPageValidation();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Subscription Flow From Subscription Get premium CTA below the player at
	 * consumption screen Line No 95
	 */
	public void zeePWASubscriptionFlowFromSubscriptionGetPremiumCTABelowPlayer(String userType, String platform)
			throws Exception {
		HeaderChildNode(
				"PWA Subscription Flow From Subscription Get premium CTA below the player at consumption screen");

		// Scenario no. 95
		zeeSearchForContentAndClickOnFirstResult("Ondh Kathe Hella");
		waitTime(5000);
		verifyElementPresentAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen,
				"Get Premium Link below the Player");
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAAccountInfoPageValidation();
			zeePWAPaymentPageValidation();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWAPaymentPageValidation();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Subscription Flow From "Buy subscription" option under My plans in hamburger
	 * menu Line No 96
	 */
	public void zeePWASubscriptionFlowFromBuySubscriptionOptionUnderMyPlansInHamburgerMenu(String userType,
			String platform) throws Exception {
		HeaderChildNode("PWA Subscription Flow From Buy subscription option under My plans in hamburger menu");

		// Scenario no. 96
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objBuySubscriptionOption,
				"Buy Subscribe Option in Hamburger Menu");
		waitTime(5000);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Subscription Flow From " Have a Prepaid code" option under My plans in
	 * hamburger menu - Line No 97 Subscription Flow using promo codes to verify if
	 * the user is getting discounted price on plans are not - Line No 98
	 */
	public void zeePWASubscriptionFlowFromHaveAPrepaidCodeOptionUnderMyPlansInHamburgerMenu(String userType,
			String platform) throws Exception {
		HeaderChildNode("PWA Subscription Flow From 'Have a Prepaid code' option under My plans in hamburger menu");

		// Scenario no. 97
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHaveAPrepaidCode,
				"Have A Prepaid Code? Option in Hamburger Menu");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		// Scenario no. 98
		zeePWAPromoCodeValidationInSelectPackPage(platform);
		if (userType.equalsIgnoreCase("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout(platform, userType);

	}

	/**
	 * Promo code Validation in Select Pack Page Subscription Flow using promo codes
	 * to verify if the user is getting discounted price on plans are not - Line No
	 * 98
	 */
	public void zeePWAPromoCodeValidationInSelectPackPage(String platform) throws Exception {
		HeaderChildNode("Scenario: Promo code Validation in Select Pack Page");

		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "'Have A Code?' field");
		waitTime(3000);
		if (platform.equalsIgnoreCase("Android")) {
			type(PWASubscriptionPages.objHaveACode, "PNB20" + "\n", "'Have A Code?' field");
		} else if (platform.equalsIgnoreCase("Web")) {
			type(PWASubscriptionPages.objHaveACode, "PNB20", "'Have A Code?' field");
		}

//		driver.findElement(PWASubscriptionPages.objHaveACode).sendKeys("ZEE5PTM20");
//		hideKeyboard();
		waitTime(5000);
		click(PWASubscriptionPages.objApplyBtn, "Apply Button");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objAppliedSuccessfullyMessage, "Applied Successfully Message");

	}

	/**
	 * Select Pack Page Validation
	 */
	public void zeePWASelectPackPageValidation() throws Exception {
		HeaderChildNode("Select Pack Page Validation");

		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(3000);
		String selectedPackCategory = findElement(PWASubscriptionPages.objPackCategoryTabSelected).getText();
		System.out.println("Selected Pack Category is: " + selectedPackCategory);
		waitTime(3000);
		String defaultSelectedPlan = findElement(PWASubscriptionPages.objSelectedSubscriptionPlanAmount).getText();
		System.out.println("Plan Selected By Default is: " + defaultSelectedPlan);
		waitTime(3000);
		ScrollToElement(PWASubscriptionPages.objContinueBtn, "Continue");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue Button");
		waitTime(5000);

	}

	/**
	 * Account Info Page Validation Validate that selected pack information is
	 * displayed on left side. - Line No. 103 Validate that guest user is able to
	 * sign in/sign up from account info screen - Line No. 104
	 */
	public void zeePWAAccountInfoPageValidation() throws Exception {

		verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
		waitTime(3000);

		// Scenario no. 103
		zeePWASelectedPackDisplayValidation();

		verifyElementPresent(PWASubscriptionPages.objProceedBtnNotHighlighted,
				"Proceed Button in Account Info Page Not Highlighted");

		HeaderChildNode("Validate that guest user is able to sign in/sign up from account info screen");
		// Scenario no. 104
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objEmailIDTextField, "Email ID Text Field");
		waitTime(3000);
		type(PWASubscriptionPages.objEmailIDTextField, "igszee5testing@gmail.com", "Email Id");
//		type(PWASubscriptionPages.objEmailIDTextField, "basavaraj.pn5@gmail.com", "Email Id");
//		type(PWASubscriptionPages.objEmailIDTextField, "igstesting001@gmail.com", "Email Id");

		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnHighlighted,
				"Proceed Button in Account Info Page Highlighted");
		waitTime(3000);
		// Password Popup
		verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objProceedBtnDisabled, "Disabled Proceed Button");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
		waitTime(3000);
		type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
//		type(PWASubscriptionPages.objPasswordFieldHidden, "igsindia123", "Password Field");
//		type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");

		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnEnabled, "Enabled Proceed Button");
		waitTime(3000);

	}

	/**
	 * Selected Pack Display Validation Validate that selected pack information is
	 * displayed on left side. - Line No. 103
	 */
	public void zeePWASelectedPackDisplayValidation() throws Exception {
		HeaderChildNode("Validate that selected pack information is displayed on left side.");

		// Scenario no. 103
		// verifyElementPresent(PWASubscriptionPages.objSelectedPackText, "Selected Pack
		// Text");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectedPackName, "Selected Pack Name");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectedPackDuration, "Selected Pack Duration");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objSelectedPackDescription, "Selected Pack Description");
		waitTime(3000);

	}

	/**
	 * Payment Page Validation Validate that user is navigated to Payment options
	 * screen post successful sign in/sign up - Line No. 105
	 */
	// manas
	public void zeePWAPaymentPageValidation() throws Exception {
		HeaderChildNode("Validate that user is navigated to Payment options screen post successful sign in/sign up");

		// Scenario no. 103
//		waitTime(5000);
		verifyElementExist(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
//		waitTime(3000);
		zeePWASelectedPackDisplayValidation();
//		verifyElementPresent(PWASubscriptionPages.objAccountInfoText, "Account Info Text in Payments Section");
//		waitTime(3000);
		verifyElementExist(PWASubscriptionPages.objAccountInfoDetails, "Account Info Details in Payments Section");
////		waitTime(3000);
//		verifyElementPresent(PWASubscriptionPages.objCreditCardRadioBtn, "Credit Card Radio Button");
////		waitTime(3000);
//		verifyElementPresent(PWASubscriptionPages.objDebitCardRadioBtn, "Debit Card Radio Button");
////		waitTime(3000);
//		verifyElementPresent(PWASubscriptionPages.objPayTMRadioBtn, "PayTm Radio Button");
////		waitTime(3000);
//		ScrollToElement(PWASubscriptionPages.objContinueBtnDisabled, "Continue Button Disabled");
////		waitTime(3000);
//		verifyElementPresent(PWASubscriptionPages.objRecurrenceMessage, "Recurrence Message");
////		waitTime(3000);
//		click(PWASubscriptionPages.objPayTMRadioBtn, "PayTm Radio Button");
//		waitTime(3000);
//		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtnEnabled, "Continue Button Enabled");
////		verifyElementPresent(PWASubscriptionPages.objContinueBtnEnabled, "Continue Button Enabled");

		waitTime(5000);
		WebElement iframeElement = null;
		if (getPlatform().equalsIgnoreCase("Android")) {
			iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);
		}

		verifyElementPresentAndClick(PWASubscriptionPages.objCreditAndDebitCardBtn, "Credit/Debit Card Option");
		waitTime(5000);
		verifyElementExist(PWASubscriptionPages.objEnterCreditAndDebitCardDetails, "Enter Credit/Debit Card Details");
		verifyElementExist(PWASubscriptionPages.objCardNumber, "Enter Card Number Field");
		verifyElementExist(PWASubscriptionPages.objExpiry, "Expiry Field");
		verifyElementExist(PWASubscriptionPages.objCVV, "CVV Field");
//		Back(1);
		waitTime(5000);
		if (getPlatform().equals("Android")) {
			extent.HeaderChildNode("Validating the payment gateway using Paytm");
			getWebDriver().switchTo().frame(iframeElement);
			verifyElementPresentAndClick(PWASubscriptionPages.objPaytmWallet, "Paytm");
			getWebDriver().switchTo().defaultContent();
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			extent.HeaderChildNode("Validating the payment gateway using Wallet");
			verifyElementPresentAndClick(PWASubscriptionPages.objWallets, "Wallets");
			getWebDriver().switchTo().defaultContent();
		}

		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
//		waitTime(5000);
//		// PayTM Page
//		verifyElementPresent(PWASubscriptionPages.objPaytmWalletOption, "PayTM Wallet option");
	}

	public void navigateBackFromPayTmWalletAndLogout(String platform, String userType) throws Exception {
//		HeaderChildNode("Navigate Back from PayTm Wallet and Logout");

		waitTime(5000);
		Back(1);
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		if (userType.equalsIgnoreCase("Guest")) {
			logout();
		}

	}

	public void ScrollToElement(By Locator, String validationText) throws Exception {

		for (int i = 1; i <= 10; i++) {
			if (verifyElementPresent(Locator, validationText) == true) {
				logger.info("Scrolled till element " + validationText);
				extent.extentLogger("Scroll to element", "Scrolled till element " + validationText);
				break;
			}
			waitTime(2000);
			swipeALittle("up", 1);
		}
	}

	public void swipeALittle(String dire, int count) throws Exception {

		if (dire.equalsIgnoreCase("UP")) {

			for (int j = 0; j < count; j++) {
				Dimension size = getDriver().manage().window().getSize();
				int starty = (int) (size.height * 0.40);
				int endy = (int) (size.height * 0.39);
				int startx = size.width / 2;
				// getDriver().swipe(startx, starty, startx, endy, 3000);
				touchAction.press(PointOption.point(startx, starty))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000)))
						.moveTo(PointOption.point(startx, endy)).release().perform();

				logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				extent.extentLogger("SwipeUp",
						"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

			}
		}
	}

	public void dragFromToForDuration(double durationSecond) {
		Dimension size = getDriver().manage().window().getSize();
		int starty = (int) (size.height * 0.40);
		int endy = (int) (size.height * 0.39);
		int startx = size.width / 2;

		Map<String, Object> params = new HashMap<>();
		params.put("duration", 5);
		params.put("fromX", startx);
		params.put("fromY", starty);
		params.put("toX", startx);
		params.put("toY", endy);
		getDriver().executeScript("mobile: dragFromToForDuration", params);

	}

	public void validateDisplayLanguagePopup() throws Exception {

		if (waitForElement(PWAHomePage.objDisplayLanguagePopupTitle, 20, "Display Language Popup")) {

			verifyElementPresentAndClick(PWAHomePage.objDisplayLanguagePopupOption("English"),
					"English option in Display Language popup");
			verifyElementPresentAndClick(PWAHomePage.objDisplayLanguageContinueButton,
					"Continue Button in Display Language popup");

			verifyElementPresent(PWAHomePage.objContentLanguagePopupSelectedOption("English"),
					"English option in Content Language popup");
			verifyElementPresent(PWAHomePage.objContentLanguagePopupSelectedOption("Kannada"),
					"Kannada option in Content Language popup");
			verifyElementPresentAndClick(PWAHomePage.objContentLanguagePopupUnSelectedOption("Hindi"),
					"Hindi option in Content Language popup");
			verifyElementPresentAndClick(PWAHomePage.objDisplayLanguageContinueButton,
					"Continue Button in Content Language popup");
		}

	}

	// ----------------------------------------------------------------------------------------

	// VINAY

	public void ValidatingPlayer(String userType) throws Exception {
		PlayerIconVaidationsWeb();
		playerControlOperations();
		AudioValidation();
		PlayerQuality();
		ShareFunctionality();
		WatchTrailer();
		AddToWatchListGuestUser(userType);
		// WatchCredit(userType);
		upnext(userType);
	}

	public void AddToWatchListLoggedInUser() throws Exception {
		extent.HeaderChildNode("Add to Watch List");
		// Click on Watchlist
		click(PWAPlayerPage.watchListBtn, "WatchList icon");
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
		// Verify the Toast message is displayed
		// verifyElementPresent(PWAAddToWatchListPage.objtoastMessage, "Added to
		// WatchList");
		// Click on My account
		click(PWAHamburgerMenuPage.objProfileIconWEB, "Profile");
		// Click on Watchlist
		click(PWAAddToWatchListPage.objMyWatchList, "Watch list");
		// Click on Movies tab
		click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
		// Verify added Item is present in Watchlist
		verifyElementExist(PWAAddToWatchListPage.objContentsInWatchList, "Content in Watchlist");
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove watchlist");
		waitTime(3000);
		BackButton(1);
		waitTime(5000);
	}

	/*
	 * Function to validate the Player icons
	 */
	public void PlayerIconVaidationsWeb() throws Exception {
		System.out.println("PlayerIconVaidationsWeb");
		extent.HeaderChildNode("Validating Player icons on Player");
		// Click on Movies tab
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
//		click(PWAHomePage.objTabName("Movies"), "Movies tab");
//		// Click on Free content
//		click(PWAMoviesPage.objContentCard(2), "Content card");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie4");
		type(PWASearchPage.objSearchEditBox, keyword, "Search edit");
		waitTime(3000);
		click(PWASearchPage.objSearchMoviesTab, "Movies tab");
		click(PWASearchPage.objspecificSearch, "Searched content");
		Thread.sleep(6000);
		// Verify the Pop up behavior
		// verifyElementNotPresent(PWAPlayerPage.objSpinner, 60);
		if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Popup") == true) {
			click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Pop up close button");
		}

//		if(getWebDriver().findElement(PWAPlayerPage.objWEBCloseBtnLoginPopup).isDisplayed()) {
//			click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Pop up close button");
//		}
		Thread.sleep(1000);
		if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserType")
				.equalsIgnoreCase("Firefox")) {
			click(PWAPlayerPage.objContentTitle, "Content Title");
		}
		// Verify Ad
		// LoadingInProgress(PWAPlayerPage.objPlayerLoader);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
		}

		waitForPlayerLoaderToComplete();
//		waitForPlayerAdToComplete1("Video Player");
//		waitForPlayerAdToComplete1("Video Player");

//		waitTime(3000);
//		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//			verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
//		}
		// click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");

		if (verifyElementExist(PWAPlayerPage.audioBtn, "Audio Button")) {
			Actions actions = new Actions(getWebDriver());
			WebElement player = getWebDriver().findElement(PWAPlayerPage.audioBtn);
			actions.moveToElement(player).perform();
			Thread.sleep(5000);
			WebElement content = getWebDriver().findElement(PWAPlayerPage.objContentTitle);
			actions.moveToElement(content).perform();
			Thread.sleep(3000);
		}

		// clickOnPlayer(PWAPlayerPage.pauseBtn);
		// Click on Video player
		// click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.pauseBtn, "Pause button");
		verifyElementPresent(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.playBtn, "Play icon");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.progressBar, "Progress bar");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.audioBtn, "Audio icon");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total duration time");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.settingsBtn, "Settings icon");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize window icon");
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total time");
		Thread.sleep(10000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresentAndClick(PWAPlayerPage.settingsBtn, "Setting button");
		verifyElementPresentAndClick(PWAPlayerPage.objPlayerQualityButton, "Quality Button");
		click(PWAPlayerPage.objBestQualityOption, "Best quality");
		verifyElementPresentAndClick(PWAPlayerPage.settingsBtn, "Setting button");
		verifyElementPresentAndClick(PWAPlayerPage.objPlayerQualityButton, "Quality Button");
		String SelectedOption = getText(PWAPlayerPage.objPlayerSelectedQuality);
		if (SelectedOption.contains("Best")) {
			logger.info("Best option is selected");
			extent.extentLogger("Quality", "Best option is selected");
		}
		click(PWAPlayerPage.objBetterQualityOption, "Better quality");
		verifyElementPresentAndClick(PWAPlayerPage.settingsBtn, "Setting button");
		verifyElementPresentAndClick(PWAPlayerPage.objPlayerQualityButton, "Quality Button");
		String SelectedOption2 = getText(PWAPlayerPage.objPlayerSelectedQuality);

		if (SelectedOption2.contains("Better")) {
			logger.info("Better option is selected");
			extent.extentLogger("Quality", "Better option is selected");
		}
		verifyElementPresentAndClick(PWAPlayerPage.settingsBtn, "Setting button");
		HeaderChildNode("Player controls validation in Full screen mode");
		verifyElementPresentAndClick(PWAPlayerPage.maximizeBtn, "Maximize window icon");
		verifyElementPresent(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon");
		verifyElementPresent(PWAPlayerPage.playBtn, "Play icon");
		verifyElementPresent(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon");
		verifyElementPresent(PWAPlayerPage.progressBar, "Progress bar");
		verifyElementPresent(PWAPlayerPage.audioBtn, "Audio icon");
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total duration time");
		verifyElementPresent(PWAPlayerPage.settingsBtn, "Settings icon");
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total time");
		verifyElementPresentAndClick(PWAPlayerPage.minimizeBtn, "Minimize button");
		audioTrackSelection();
		// waitForElementAbsence(PWAPlayerPage.forward10SecBtn, 10, "Waiting for element
		// to be not present");
		// Verify after some times the Player icons are not visible
//		verifyElementNotPresent(PWAPlayerPage.playBtn,10);
//		verifyElementNotPresent(PWAPlayerPage.pauseBtn, 10);
//		verifyElementNotPresent(PWAPlayerPage.forward10SecBtn, 10);
//		verifyElementNotPresent(PWAPlayerPage.rewind10SecBtn, 10);
//		verifyElementNotPresent(PWAPlayerPage.progressBar, 10);
//		verifyElementNotPresent(PWAPlayerPage.settingsBtn, 10);
//		verifyElementNotPresent(PWAPlayerPage.audioBtn, 10);
//		verifyElementNotPresent(PWAPlayerPage.maximizeBtn, 10);
	}

	/*
	 * Validating Rewind, Farword 10 seconds icon
	 */
	public void playerControlOperations() throws Exception {
		System.out.println("playerControlOperations");
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
		}
		extent.HeaderChildNode("Validating rewind 10 seconds, farword 10 seconds and Audio icons");

		waitTime(10000);
//		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		JSClick(PWAPlayerPage.pauseBtn, "Pause icon");
		String currentDuration = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"Current duration");
		System.out.println("time fetched before rewind: " + currentDuration);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
		}
		String[] time = currentDuration.split(":");
		int timeDuration = Integer.parseInt(time[1]);
		System.out.println("seconds lapsed before rewind: " + timeDuration);
		int rewindTime = timeDuration - 10;
		// To tap on the player
//		click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
		// Verify Playback is rewinded 10 Seconds back
		// click on pause button
		click(PWAPlayerPage.rewind10SecBtn, "Rewind 10 seconds");
		// Get the current time duration after clicking the rewind button
		click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
		String currentDurationAfter10Sec = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		System.out.println("time fetched after rewind: " + currentDurationAfter10Sec);
		String[] time2 = currentDurationAfter10Sec.split(":");
		int timeDuration2 = Integer.parseInt(time2[1]);
		System.out.println("seconds lapsed after rewind: " + timeDuration2);
		if (rewindTime <= timeDuration2) {
			softAssert.assertEquals(rewindTime <= timeDuration2, true, "Rewinded video playback 10 seconds");
			extent.extentLogger("Verify rewind button", "Playback is rewinded 10 seconds");
			logger.info("Rewinded 10 seconds is passed");
		} else {
			softAssert.assertEquals(rewindTime <= timeDuration2, false, " Can not Rewind video playback 10 seconds");
			softAssert.assertAll();
			extent.extentLoggerFail("Verify rewind button", "Playback can not be rewind 10 seconds");
			logger.info("Rewind 10 sec is failed");
		}

		// Verify Farword 10 seconds icon
//		click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
		pausePlayer();
		String currentDurationF = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		System.out.println("time fetched before Farword : " + currentDuration);
		String[] timeF = currentDurationF.split(":");
		System.out.println(timeF);
		int timeDurationF = Integer.parseInt(timeF[1]);
		System.out.println("seconds lapsed before farword: " + timeDurationF);
		int farwordTimeF = timeDurationF + 10;
		// Verify Playback is farworded 10 Seconds back
		click(PWAPlayerPage.forward10SecBtn, "Farword 10 seconds");
		// Get the current time duration after clicking the rewind button
		// click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
		String currentDurationAfter10SecF = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
				"current duration");
		System.out.println("time fetched after rewind: " + currentDurationAfter10Sec);
		String[] time2F = currentDurationAfter10SecF.split(":");
		int timeDuration2F = Integer.parseInt(time2F[1]);
		System.out.println("seconds lapsed after Farword: " + timeDuration2F);
		if (farwordTimeF >= timeDuration2F) {
			softAssert.assertEquals(farwordTimeF >= timeDuration2F, true, "Farworded video playback 10 seconds");
			extent.extentLogger("Verify rewind button", "Playback is Farword 10 seconds");
			logger.info("Farword 10 seconds is passed");
		} else {
			softAssert.assertEquals(farwordTimeF >= timeDuration2F, false,
					" Can not Farword video playback 10 seconds");
			softAssert.assertAll();
			extent.extentLoggerFail("Verify rewind button", "Playback can not be Farword 10 seconds");
			logger.info("Farword 10 sec is failed");

		}
	}
	/*
	 * Validate the Audio functionality
	 */

	public void AudioValidation() throws Exception {
		System.out.println("AudioValidation");
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "player");
		String Audio = getElementPropertyToString("aria-label", PWAPlayerPage.audioBtn, "Audio button");
		if (Audio.contains("Mute")) {
			softAssert.assertEquals(Audio.contains("Mute"), true, "Playbac is Audible");
			extent.extentLogger("Veridy Playback is audible", "Playback is Audible");
			logger.info("Playback is audible");
		} else {
			softAssert.assertEquals(Audio.contains("Mute"), false, "Video is not audible");
			softAssert.assertAll();
			extent.extentLogger("Veridy Playback is audible", "Playback is not Audible");
			logger.info("Playback is not audible");
		}
		// Verify Audio is muted
		JSClick(PWAPlayerPage.objPlaybackVideoOverlay, "player");
		click(PWAPlayerPage.audioBtn, "Audio button");
		String muteAudio = getElementPropertyToString("aria-label", PWAPlayerPage.audioBtn, "Audio button");
		if (muteAudio.contains("Unmute")) {
			softAssert.assertEquals(muteAudio.contains("Unmute"), true, "Playbac is Muted");
			extent.extentLogger("Veridy Playback is audible", "Playback is Muted");
			logger.info("Playback is Muted");
		} else {
			softAssert.assertEquals(muteAudio.contains("Unmute"), false, "Video is not muted");
			softAssert.assertAll();
			extent.extentLogger("Veridy Playback is audible", "Playback is not Muted");
			logger.info("Playback is not Muted");
		}

	}

	/*
	 * Player Quality validation
	 */

	public void PlayerQuality() throws Exception {
		System.out.println("PlayerQuality");

		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
		}

		extent.HeaderChildNode("Validating Player Quality");

		// click on player
//		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		// Click on Setting
		click(PWAPlayerPage.settingsBtn, "Setting icon");
		// Click on Quality
		click(PWAPlayerPage.qualityBtn, "Quality option");
		// Verify the Quality
		for (int i = 1; i <= getWebDriver().findElements(PWAQualitySettingsPage.objAllQualities).size(); i++) {

			// click on the first quality
			click(PWAQualitySettingsPage.objIndividualQuality(i), "Quality");
			// click on player
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			// Click on Setting
			click(PWAPlayerPage.settingsBtn, "Setting icon");
			// Click on Quality
			click(PWAPlayerPage.qualityBtn, "Quality option");
			if (findElement(PWAQualitySettingsPage.objSelectedQuality(i)).getAttribute("class").contains("tickMark")) {
				String selectedQuality = getWebDriver().findElement(PWAQualitySettingsPage.objIndividualQuality(i))
						.getText();
				System.out.println(selectedQuality);
				// Click on Player
				click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
				// Click on Setting
				click(PWAPlayerPage.settingsBtn, "Setting icon");
				String qualitySelected = getWebDriver().findElement(PWAQualitySettingsPage.objQualityText).getText();
				if (selectedQuality.equals(qualitySelected)) {
					softAssert.assertEquals(selectedQuality, qualitySelected);
					extent.extentLogger("Verify Quality", "The selected Quality is applied");
					logger.info("The Selected quality is " + selectedQuality);
				} else {
					softAssert.assertAll();
					softAssert.assertEquals(selectedQuality, qualitySelected);
					extent.extentLogger("Verify Quality", "The selected Quality is failed");
					logger.info("Quality select is failed");
				}
			}
		}
	}

	/*
	 * Validating share functionality
	 */
	public void ShareFunctionality() throws Exception {

		System.out.println("ShareFunctionality");
		extent.HeaderChildNode("Share functionality Validation");
		// Verify Share option
		verifyElementPresent(PWAPlayerPage.shareBtn, "Share option");
		// Click on the Share option
		// click(PWAPlayerPage.shareBtn, "Share option");
		WebShareFunctionality();
		// Verify the Share options are visible
		// verifyElementPresent(PWAPlayerSharePage.objShareViaText,"Share Via Popup");
		// Navigate back to playback page
		// Back(1);
	}

	/*
	 * Function to validate the Web Share functionality
	 */
	public void WebShareFunctionality() throws Exception {
		// click on share Option
		click(PWAPlayerPage.shareBtn, "Share Option");
		// Verify Facebook share option
		Thread.sleep(2000);
		verifyElementPresent(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		Thread.sleep(2000);

		// Verify Twitter share option
		verifyElementPresent(PWAPlayerPage.twitterShareBtn, "Twitter share option");
		Thread.sleep(2000);
		// Verify Email Share option
		verifyElementPresent(PWAPlayerPage.emailShareBtn, "Email share option");
		Thread.sleep(2000);
		// Click on Facebook Share option
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		Thread.sleep(2000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}

		// Switch to window
		verifyAlert();
		switchToWindow(2);
		Thread.sleep(2000);
		// Verify user is navigate to Facebook page
		if (verifyElementExist(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}

			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
//		waitTime(3000);
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
//		waitTime(3000);
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
		}
		verifyAlert();
		waitTime(2000);
		verifyElementPresentAndClick(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		waitTime(3000);
		verifyAlert();
		switchToWindow(1);
		waitTime(3000);
//		verifyElementPresent(WebSharePage.objFacebookTitle, "Facebook Title on Facebook page");
//		verifyAlert();
//		getWebDriver().close();
		// Switch to default window
//		Thread.sleep(2000);
//		verifyAlert();
//		switchToParentWindow();
		Thread.sleep(2000);
		// Click on Share option
		click(PWAPlayerPage.shareBtn, "Share Option");
		Thread.sleep(2000);
		// Click on Twitter share option
		click(PWAPlayerPage.twitterShareBtn, "Twitter share option");
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}

		Thread.sleep(2000);
		// Verify user is navigated to Twitter page
		switchToWindow(2);
		Thread.sleep(2000);
		verifyAlert();
		// Verify user is navigated to Twitter page
		verifyElementExist(WebSharePage.objTwitterLogo, "Twitter Share page");
		verifyAlert();
		getWebDriver().close();
		switchToParentWindow();
		Thread.sleep(2000);
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
		waitTime(5000);
	}

	/*
	 * Function to validate the Watch trailer
	 */
	public void WatchTrailer() throws Exception {
		System.out.println("WatchTrailer");
		// Click on Search icon
		click(PWAHomePage.objSearchBtn, "Search Button");
		// Enter text which contains Watch Trailer Option
		waitTime(3000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovieWithTrailer");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box");
		Thread.sleep(2000);
//	        type(PWASearchPage.objSearchEditBox, "nna", "Search Edit box");

		// Select the content
//	        click(PWASearchPage.objFirstContentCardNameAfterSearch(1), "First content in search history");

		waitTime(4000);

		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Premium content");
		Thread.sleep(4000);
		// Verify the Pop up behavior

		if (verifyElementExist(PWAPlayerPage.objCloseBtnLoginPopup, "Login popup") == true) {
			click(PWAPlayerPage.objCloseBtnLoginPopup, "Closing Login  Popup");
		}

		Thread.sleep(4000);

		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
//		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//		}

		// Verify Watch trailer option is displayed
		verifyElementExist(PWASearchPage.objWEBWatchTrailerBtn, "Watch Trailer option");
	}

	/*
	 * Function to validate the Add to Watch list as a guest user
	 */
	public void AddToWatchListGuestUser(String userType) throws Exception {

		if (userType.contains("Guest")) {
			System.out.println("AddToWatchListGuestUser");
			extent.HeaderChildNode("Add to Watch List Guest user validations");
			// Verify Add to Watchlist is displayed
			verifyElementPresent(PWAPlayerPage.watchListBtn, "Add to Watchlist");
			// Click on Add to Watchlist option
			click(PWAPlayerPage.watchListBtn, "Add to Watchlist");

			// Verify user is Observed Login pop up
			verifyElementPresent(PWAPlayerPage.objLoginRequiredTxt, "Login Required Pop up");
			// Close the Login Popup
			click(PWAPlayerPage.objCloseBtnLoginPopupWeb, "Close button Login Popup");
			getWebDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		} else {
			System.out.println("AddToWatchListLoggedUser");
			Thread.sleep(4000);

			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			AddToWatchListLoggedInUser();
		}
	}

	/*
	 * Function to Validate the Watch credit option
	 */
	public void WatchCredit(String userType) throws Exception {
		System.out.println("WatchCredit");
		extent.HeaderChildNode("Validating Watch credit button");
		// Click on home page
		click(PWAHomePage.objTabName("Home"), "Home page");
		// Click on search icon
		click(PWAHomePage.objSearchBtn, "Search Button");
		// Enter text
		type(PWASearchPage.objSearchEditBox, "right yaaa wrong", "Search Edit box");
		// type(PWASearchPage.objSearchEditBox, " ", "Search Edit box");
		Thread.sleep(8000);
		// Click on first content
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Content Card");
		// close login up

		if (userType.equalsIgnoreCase("Guest")) {

			if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}

			if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserType")
					.equalsIgnoreCase("Firefox")) {
				click(PWAPlayerPage.objContentTitle, "Content Title");
			}

			// LoadingInProgress(PWAPlayerPage.objPlayerLoader);
//			waitForPlayerLoaderToComplete();
			verifyElementNotPresent(PWAPlayerPage.objAd, 60);
			waitForPlayerAdToComplete1("Video Player");
			// Click on the video playback

			if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup")) {
				click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
			}
			Thread.sleep(2000);
//			verifyElementNotPresent(PWAPlayerPage.objAd, 60);
			waitForPlayerAdToComplete1("Video Player");
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			Thread.sleep(5000);
			getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Scub the video
			WebElement slider = getWebDriver().findElement(PWAPlayerPage.progressBar);
//			System.out.println(slider);
			Actions move = new Actions(getWebDriver());
//			System.out.println(move);
			Action action = (Action) move.dragAndDropBy(slider, 395, 0).build();
			action.perform();
			verifyElementExist(PWAPlayerPage.objWatchCredit, "Watch Credit");
//			click(PWAPlayerPage.playBtn, "Play icon");
//			getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//			// adValidation();
//			waitForPlayerLoaderToComplete();
//			verifyElementNotPresent(PWAPlayerPage.objAd, 60);
//			verifyElementNotPresent(PWAPlayerPage.objAd, 60);
//
//			waitForElementDisplayed(PWAPlayerPage.objWatchCredit, 50);
//
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
//				verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
//			}

			// click on play button
			// Verify Watch credit is displayed
//			verifyElementExist(PWAPlayerPage.objWatchCredit, "Watch Credit");

			// Verify Content cards are displayed
//			verifyElementExist(PWAPlayerPage.objContentCardsOnPlayer, "Upnext Rail on video playback");
			// Click on Watch credit button
			// click(PWAPlayerPage.objWatchCredit, "Watch Credit");

//			Thread.sleep(10000);
//
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
//				verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
//			}
//
//			if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup")) {
//				click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
//			}

		} else {
			Thread.sleep(2000);

			if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}

			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
			}
//			Thread.sleep(2000);
//			if(verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true)
//			{
//				verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
//			}

			if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserType")
					.equalsIgnoreCase("Firefox")) {
				click(PWAPlayerPage.objContentTitle, "Content Title");
			}
			waitForPlayerLoaderToComplete();
			verifyElementNotPresent(PWAPlayerPage.objAd, 60);
//			waitForPlayerAdToComplete1("Video Player");
//			waitForPlayerAdToComplete1("Video Player");

			waitForElementDisplayed(PWAPlayerPage.objWatchCredit, 25);

			click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
			click(PWAPlayerPage.playBtn, "Play icon");

			click(PWAPlayerPage.objWatchCredit, "Watch Credit");
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
			}
			WebElement slider = getWebDriver().findElement(PWAPlayerPage.progressBar);
			System.out.println(slider);
			Actions move = new Actions(getWebDriver());
			System.out.println(move);
			Action action1 = (Action) move.dragAndDropBy(slider, 0, 0).build();
			action1.perform();

			Thread.sleep(5000);
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
			}

		}

	}

	/*
	 * Validating Upnext Rail on Playback
	 */

	public void upnext(String userType) throws Exception {

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeEpisode3");
		type(PWASearchPage.objSearchEditBox, keyword, "Search edit");
		click(PWASearchPage.objspecificSearch, "Searched content");
		if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register pop up") == true) {
			click(PWAPlayerPage.oblClosePopup, "Close button");
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			waitForPlayerAdToComplete("Video");
		}
		Thread.sleep(5000);
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
		Thread.sleep(5000);
		WebElement scrubber = getWebDriver().findElement(By.xpath("//a[@class='playkit-scrubber']"));
//		Actions move = new Actions(getWebDriver());
//		Action action = (Action) move.dragAndDropBy(scrubber, 620, 0).build();
//		action.perform();
		Actions action = new Actions(getWebDriver());
		action.clickAndHold(scrubber);
		action.moveByOffset(40, 0).build().perform();

		// click(PWAPlayerPage.objPlaybackVideoOverlay,"Player");
		verifyElementPresent(PWAPlayerPage.objUpnextCard, "Up Next Rail on player");
		action.clickAndHold(scrubber).release();
//		action.moveToElement(getWebDriver().findElement(PWAHomePage.objMoreMenuIcon)).release();

		// Verify the Upnext content is auto playing
		getResponseUpNextRail.getResponse1();
		String episodeName = getText(PWAPlayerPage.objContentName);
		System.out.println(episodeName);
		String APIData = getResponseUpNextRail.getMediaContentName();
		System.out.println(APIData);
		if (APIData.equals(episodeName)) {
			softAssert.assertEquals(APIData, episodeName);
			extent.extentLogger("Upnext Rail", "The first content Auto played in Upnext rail");
			logger.info("Upnext rail content is auto played");
		} else {
			softAssert.assertAll();
			softAssert.assertNotEquals(APIData, episodeName);
			extent.extentLoggerFail("Verify UpNext Rail", "Upnext content auto play is failed");
			logger.info("Upnext content playabck is failed");
		}
	}

	/*
	 * Function to validate the Ad
	 */
	public void waitForPlayerAdToComplete1(String playerType) throws Exception {
//		getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		WebDriverWait wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.presenceOfElementLocated(PWAPlayerPage.objAd));
		boolean adWasDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		main: for (int trial = 0; trial < 90; trial++) {
			try {
				getWebDriver().findElement(PWAPlayerPage.objAd);
				adWasDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
				}
				if (Math.floorMod(trial, 10) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);
			} catch (Exception e) {
				try {
					if (playerType.equals("Live Player")) {
						getWebDriver().findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						getWebDriver().findElement(PWAPlayerPage.objPlayerSeekBar);
					}
					playerDisplayed = true;
					Thread.sleep(1000);
					confirmCount++;
					if (confirmCount == 3) {
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
					Thread.sleep(1000);
				}
			}
		}
		if (playerDisplayed == false && adWasDisplayed == false) {
			logger.error("Ad play failure");
			extent.extentLoggerFail("failedAd", "Ad play failure");
		}
	}

	// ----------------------------------------------------------------
	// Manas
//	public void verifyAutoroatingOnCarousel(String screen) throws Exception {
//        extent.HeaderChildNode("Verifying autorotating of carousel pages on : " + screen);
//        String firstCarouselTitle = "", secondCarouselTitle = "", thirdCarouselTitle = "";
//        navigateToAnyScreenOnWeb(screen);
//        WebDriverWait w = new WebDriverWait(getWebDriver(), 15);
//        //for (int i = 0; i < 10; i++) {
//            try {
//                firstCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//                System.out.println("title1 >>> "+firstCarouselTitle);
//                
//                try {
//                    //getWebDriver().findElement(PWAHomePage.objWEBContTitleTextCarousel(firstCarouselTitle));
//                    waitTime(7000);
//                    secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//                    System.out.println("title2 >>> " +secondCarouselTitle);
//                }catch(Exception e) {
//                    secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//                    System.out.println("title2 >>> " +secondCarouselTitle);
//                    try {
//                        getWebDriver().findElement(PWAHomePage.objContTitleTextCarousel(secondCarouselTitle));
//                        waitTime(4000);
//                        thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//                        System.out.println("title2 >>> " +thirdCarouselTitle);
//                    }catch(Exception e1) {
//                        thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//                        System.out.println("title2 >>> " +thirdCarouselTitle);
//                    }
//                }
//                
//                
////              w.until(ExpectedConditions.invisibilityOfElementLocated(PWAHomePage.objWEBContTitleTextCarousel(secondCarouselTitle)));
////              //verifyElementNotPresent(PWAHomePage.objWEBContTitleTextCarousel(secondCarouselTitle), 60);
////              thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
////              System.out.println("title3 >>> " +thirdCarouselTitle);
//////                break;
//            } catch (Exception e) {
//                e.getMessage();
//            }
//        //}
//        if (firstCarouselTitle.equals(secondCarouselTitle) == false
//                && secondCarouselTitle.equals(thirdCarouselTitle) == false) {
//            softAssert.assertFalse(firstCarouselTitle.equals(secondCarouselTitle));
//            logger.info("Content is auto rotated ");
//            extent.extentLogger("Autorotating",
//                    "First content title :" + firstCarouselTitle + " and next content title :" + secondCarouselTitle);
//            extent.extentLogger("Autorotating", "Content is auto rotated");
//        } else {
//            softAssert.assertFalse(firstCarouselTitle.equals(secondCarouselTitle));
//            logger.info("Content is not auto rotated");
//            extent.extentLogger("Autorotating", "Content is not auto rotated");
//            softAssert.assertAll();
//        }
//        
//    }

//	====================For Autorotating=======================
	public void verifyAutoroatingOnCarousel(String screen) throws Exception {
		extent.HeaderChildNode("Verifying autorotating of carousel pages on : " + screen);
		String firstCarouselTitle = "", secondCarouselTitle = "", thirdCarouselTitle = "";
		navigateToAnyScreenOnWeb(screen);
		new WebDriverWait(getWebDriver(), 15);
		try {
			firstCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
			waitTime(10000);
			secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
			waitTime(10000);
			thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();

		} catch (Exception e) {
			e.getMessage();
		}
		extent.extentLogger("Autorotating", "First content title :" + firstCarouselTitle + " second content title :"
				+ secondCarouselTitle + " and trird content title :" + thirdCarouselTitle);
		logger.info("First content title :" + firstCarouselTitle + " second content title :" + secondCarouselTitle
				+ " and trird content title :" + thirdCarouselTitle);
		if (firstCarouselTitle.equals(secondCarouselTitle) == false
				&& firstCarouselTitle.equals(thirdCarouselTitle) == false) {
			softAssert.assertFalse(firstCarouselTitle.equals(secondCarouselTitle));
			logger.info("Content is auto rotated");
			extent.extentLogger("Autorotating", "Content is auto rotated");
		} else {
			logger.error("Content is not auto rotated");
			extent.extentLoggerFail("Autorotating", "Content is not auto rotated");
		}
	}

	/**
	 * Function to select any tab
	 * 
	 */
	public boolean navigateToAnyScreenOnWeb(String screen) throws Exception {
		try {
			if (verifyElementExist(PWAHomePage.objHomeBarText(screen), screen + " Tab")) {
				click(PWAHomePage.objHomeBarText(screen), screen + " Tab");
				return true;
			} else {
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
				waitTime(2000);
				try {
					WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuTabs(screen));
					logger.info(screen + " Tab is displayed");
					extent.extentLogger("tabDisplayed", screen + " Tab is displayed");
					executor.executeScript("arguments[0].click();", tab);
					logger.info("Clicked on " + screen + " Tab");
					extent.extentLogger("tabClicked", "Clicked on " + screen + " Tab");
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return false;
	}

	// MANAS script modified by sushma
	public void verifyMetadataOnCarousel(String screen, String pageName) throws Exception {

		extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);

		navigateToAnyScreenOnWeb(screen);

		List<String> metaTitle = new LinkedList<String>();

		String carouselTitle = null;

		verifyElementPresent(PWAHomePage.objCarouselBanner, "carousel for :" + screen);

		for (int i = 0; i < 7; i++) {
			try {
				carouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();

				if (metaTitle.contains(carouselTitle)) {
					break;
				} else {
					metaTitle.add(carouselTitle);
				}
				System.out.println(metaTitle);

				click(PWANewsPage.objRight, "right");
				waitTime(2000);

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		System.out.println(metaTitle);

		List<String> allMetaTitleOnCarousel = ResponseInstance.traysTitleCarousel(pageName);

		System.out.println(allMetaTitleOnCarousel);
		for (int i = 0; i < 7; i++) {
			if (metaTitle.get(i).equalsIgnoreCase(allMetaTitleOnCarousel.get(i))) {
				logger.info("APICarouselTitle " + allMetaTitleOnCarousel.get(i) + " matches with UICarouselTitle "
						+ metaTitle.get(i));
				extent.extentLogger("metadata verification", "APICarouselTitle " + allMetaTitleOnCarousel.get(i)
						+ "matches with UICarouselTitle " + metaTitle.get(i));
			} else {
				logger.info("APICarouselTitle " + allMetaTitleOnCarousel.get(i) + " matches with UICarouselTitle "
						+ metaTitle.get(i));
				extent.extentLogger("metadata verification", "APICarouselTitle " + allMetaTitleOnCarousel.get(i)
						+ "matches with UICarouselTitle " + metaTitle.get(i));
			}

		}
	}

	// MANAS
//    /**
//     * Function to verify Meta data on carousel for different pages
//     * 
//     * @param pagename
//     * @param screenname
//     * @throws Exception
//     */
//    @SuppressWarnings({ "null", "null" })
//public void verifyMetadataOnCarousel(String screen, String pageName) throws Exception {
//        
//        extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);
//        
//        navigateToAnyScreenOnWeb(screen);
//        
//        String doesContainMetadata = "";
//        
//        List<String> statusList = new LinkedList<String>();
//        List<String> metaDataTitleBothOnAPIUI = new LinkedList<String>();
//        List<String> metaTitle = new LinkedList<String>();
//        
//        String carouselTitle = null;
//        
//        verifyElementPresent(PWAHomePage.objCarouselBanner, "carousel for :" + screen);
//        
//            for (int i = 0; i < 7; i++) {
//            try {   
//                    carouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//                    
//                    if (metaTitle.contains(carouselTitle))
//                    {
//                        break;
//                    } 
//                    else 
//                    {
//                        metaTitle.add(carouselTitle);
//                    }
//                    System.out.println(metaTitle);
//                    
//                    click(PWANewsPage.objRight, "right");
//                    waitTime(2000);
//                    
//                 }
//            catch (Exception e) 
//            {
//                    System.out.println(e);
//             }
//            
//          }
//            
//            System.out.println(metaTitle);
//        
//        List<String> allMetaTitleOnCarousel = ResponseInstance.traysTitleCarousel(pageName);
//        
//        System.out.println(allMetaTitleOnCarousel);
//        for(int i=0; i<7; i++)
//        {
//            if (metaTitle.get(i).equalsIgnoreCase(allMetaTitleOnCarousel.get(i))) 
//            {
//                logger.info("APICarouselTitle "+allMetaTitleOnCarousel.get(i)+"matches with UICarouselTitle "+metaTitle.get(i));
//                extent.extentLogger("metadata verification","APICarouselTitle "+allMetaTitleOnCarousel.get(i)+"matches with UICarouselTitle "+metaTitle.get(i));
//            }
//            else
//            {
//                logger.info("APICarouselTitle "+allMetaTitleOnCarousel.get(i)+"matches with UICarouselTitle "+metaTitle.get(i));
//                extent.extentLogger("metadata verification","APICarouselTitle "+allMetaTitleOnCarousel.get(i)+"matches with UICarouselTitle "+metaTitle.get(i));
//            }
//    
//        }
//  }

	/**
	 * Function to verify Play icon functionality
	 * 
	 * @throws Exception
	 */
	public void verifyPlayIconFunctionality(String screen) throws Exception {
		extent.HeaderChildNode("Verifying play icon functionality on carousel for : " + screen);
		waitTime(3000);
		JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		for (int i = 0; i <= 10; i++) {
			try {
				WebElement premiumText = getWebDriver().findElement(PWAHomePage.objWEBPlayBtn);
				executor.executeScript("arguments[0].click();", premiumText);
				break;
			} catch (Exception e) {
				Thread.sleep(2000);
				try {
					getWebDriver().findElement(PWAHomePage.objWEBPlayBtn).click();
					break;
				} catch (Exception e1) {
				}
			}
		}
		waitForElementDisplayed(PWAPlayerPage.objPlayerControlScreen, 10);
		if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
			logger.info("Play icon functionality is verified for " + screen);
			extent.extentLogger("", "Play icon functionality is verified for " + screen);
		} else {
			logger.error("Play icon functionality failed for " + screen);
			extent.extentLoggerFail("", "Play icon functionality failed for " + screen);
		}
		Thread.sleep(5000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "Subscribe Pop Up") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "Subscribe Pop Up Close button");
		}
		click(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
	}

//    public void verifyPremiumIconFunctionality(String screen, String userType) throws Exception {
//        extent.HeaderChildNode("Verifying premium icon functionality On : " + screen + " for " + userType);
//        boolean isNextPageDisplayed = false;
//        navigateToAnyScreenOnWeb(screen);
//        if (userType.equalsIgnoreCase("SubscribedUser")) {
//            List<WebElement> getPremiumTextList = driver.findElements(PWAHomePage.objWEBGetPremium);
//            if (getPremiumTextList.size() == 0) {
//                softAssert.assertTrue(true, "Next page is not displayed");
//                logger.info("Get premium text is not displayed for subscribed users");
//                extent.extentLogger("Premium text for subscribed user",
//                        "Get premium text is not displayed for subscribed users" + screen);
//            }
//        } else {
//            JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
//            try {
//                WebElement premiumText = getWebDriver().findElement(PWAHomePage.objWEBGetPremium);
//                executor.executeScript("arguments[0].click();", premiumText);
//            }catch(Exception e) {
//                Thread.sleep(2000);
//                getWebDriver().findElement(PWAHomePage.objWEBGetPremium).click();
//            }
//        }
//        if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
//            if (verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page")) {
//                isNextPageDisplayed = true;
//                getWebDriver().navigate().back();
//            } else {
//                isNextPageDisplayed = false;
//            }
//        }
//        
//        if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
//            if (isNextPageDisplayed) {
//                softAssert.assertTrue(isNextPageDisplayed, "Next page is displayed");
//                logger.info("Next page is displayed on banner for " + screen);
//                extent.extentLogger("Premium btn validation", "Next page is displayed for " + screen);
//            } else {
//                softAssert.assertTrue(isNextPageDisplayed, "Next page is not displayed");
//                logger.info("Next page is not displayed for " + screen);
//                extent.extentLogger("Premium btn validation", "Next page is not displayed for " + screen);
//                softAssert.assertAll();
//            }
//        }
//    }

	public void verifyPremiumIconFunctionality(String screen, String userType) throws Exception {
		extent.HeaderChildNode("Verifying premium icon functionality on : " + screen + " for " + userType);
		navigateToAnyScreenOnWeb(screen);
		boolean clicked = false;
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			waitTime(4000);
			List<WebElement> getPremiumTextList = getWebDriver().findElements(PWAHomePage.objPlayCarousel);
			if (getPremiumTextList.size() == 0) {
				logger.info("Subscribe Now button is not displayed for Subscribed users, expected behavior");
				extent.extentLogger("",
						"Subscribe Now button is not displayed for Subscribed users, expected behavior");
			} else {
				logger.error("Subscribe Now button is displayed for Subscribed users");
				extent.extentLoggerFail("", "Subscribe Now button is displayed for Subscribed users");
			}
		} else {
			JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
			try {
				WebElement premiumText = getWebDriver().findElement(PWAHomePage.objWEBGetPremium);
				executor.executeScript("arguments[0].click();", premiumText);
				logger.info("Clicked on Subscribe Now button");
				extent.extentLogger("", "Clicked on Subscribe Now button");
				clicked = true;
			} catch (Exception e) {
				Thread.sleep(2000);
				try {
					getWebDriver().findElement(PWAHomePage.objWEBGetPremium).click();
					logger.info("Clicked on Subscribe Now button");
					extent.extentLogger("", "Clicked on Subscribe Now button");
					clicked = true;
				} catch (Exception e1) {
					logger.error("Failed to click on Subscribe Now button");
					extent.extentLoggerFail("", "Failed to click on Subscribe Now button");
				}
			}
		}
		if (clicked == true) {
			if (verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page")) {
				logger.info("Verified Subscribe Now button functionality");
				extent.extentLogger("Premium btn validation", "Verified Subscribe Now button functionality");
				Back(1);
			} else {
				logger.error("Failed to verify Subscribe Now button");
				extent.extentLoggerFail("", "Failed to verify Subscribe Now button");
				click(PWAHomePage.objZeeLogo, "Zee Logo");
			}
		}
	}

	public void verifyLeftRightFunctionality(String screen) throws Exception {
		extent.HeaderChildNode("Verifying left and right functionality");
		String firstCarouselTitle = "", secondCarouselTitle = "", thirdCarouselTitle = "", fourthCarouselTitle = "";
		WebDriverWait w = new WebDriverWait(getWebDriver(), 40);
		navigateToAnyScreenOnWeb(screen);
		w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
		firstCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		logger.info("Carousel Title fetched: " + firstCarouselTitle);
		extent.extentLogger("", "Carousel Title fetched: " + firstCarouselTitle);
		click(PWANewsPage.objRight, "Right Button");
		secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		logger.info("Carousel Title fetched: " + secondCarouselTitle);
		extent.extentLogger("", "Carousel Title fetched: " + secondCarouselTitle);
		if (firstCarouselTitle.equals(secondCarouselTitle)) {
			logger.error("Right button click failed");
			extent.extentLoggerFail("Swipe left and right", "Right button click failed");
		} else {
			logger.info("Verified Right button click");
			extent.extentLogger("Swipe left and right", "Verified Right button click");
		}
		waitTime(2000);
		thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		logger.info("Carousel Title fetched: " + thirdCarouselTitle);
		extent.extentLogger("", "Carousel Title fetched: " + thirdCarouselTitle);
		click(PWANewsPage.objRight, "Left Button");
		fourthCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		fourthCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		logger.info("Carousel Title fetched: " + fourthCarouselTitle);
		extent.extentLogger("", "Carousel Title fetched: " + fourthCarouselTitle);
		if (thirdCarouselTitle.equals(fourthCarouselTitle)) {
			logger.error("Left button click failed");
			extent.extentLoggerFail("", "Left button click failed");
		} else {
			logger.info("Verified Left button click");
			extent.extentLogger("Swipe left and right", "Verified Left button click");
		}

	}

	// ----------------------------------------------------------------------------------------------------

	// TEJAS

	public void WebValidatingLandingPages(String UserType) throws Exception {

		switch (UserType) {

		case "Guest":

			extent.HeaderChildNode("User Type Guest");
			System.out.println("User Type Guest");
			// enterURLInWEBBrowser("chrome", "https://newpwa.zee5.com");
//			waitForPageLoaded();
			waitTime(5000);

			FirstTimeAnonymousUser();
			landingpagePropertiesValidation();
			Back_TO_TopWeb();
			WebHomepageTrayTitleAndContentValidationWithApiData(ResponseInstance.getResponse());
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("User Type Loggedin User");
			System.out.println("User Type Loggedin User");

			// ZeeWEBPWALogin("NonSubscribedUser");
			// verifyElementPresentAndClick(PWALandingPages.obj_WEBPwa_HamburgerMenu1, "Menu
			// button");
			FirstTimeNonSubcribed_Loggedin_User();
			landingpagePropertiesValidation();
			Back_TO_TopWeb();
			WebHomepageTrayTitleAndContentValidationWithApiData(ResponseInstance.getResponse());
//			logout();
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("User Type Subcribed User");
			System.out.println("User Type Subcribed User");
			// ZeeWEBPWALogin("SubscribedUser");
			FirstTimeSubcribed_Loggedin_User();
			landingpageValidation_for_SubcribedUser();
			Back_TO_TopWeb();
			WebHomepageTrayTitleAndContentValidationWithApiDataForSubcribedUser(ResponseInstance.getResponse());
//			logout();

		}

	}

	public void Back_TO_TopWeb() throws Exception {
		extent.HeaderChildNode("Scroll to top button functionality");
//		scrollDown();
//		scrollToBottomOfPage();
//		scroll1();
		scrollDownWEB();
		waitForElementDisplayed(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 20);
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
//		verifyElementPresent(Pwa_LandingPages.obj_Pwa_PlayIcon_Carousal, "Carousal play icon");
		System.out.println("Scrolled back to top using Back to top btn");

	}

	public void FirstTimeAnonymousUser() throws Exception {
		extent.HeaderChildNode("First time user Trenrding on zee5 validation");
		System.out.println("FTAU");
		FirstTimeUser_Trending_on_zee5();
	}

	public void landingpagePropertiesValidation() throws Exception {
		extent.HeaderChildNode("Validating Homepage Properties");
		verifyElementPresent(PWALandingPages.obj_WEBPwa_HamburgerMenu1, "Hamburger Menu");
		verifyElementPresent(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");
		verifyElementPresent(PWALandingPages.obj_Pwa_SearchBtn, "Search");
		verifyElementPresent(PWALandingPages.obj_Pwa_Subcription_teaser_btn, "Subcription button");
	}

	public void webScrollToElement(By Locator, String validationText) throws Exception {
		for (int i = 1; i <= 10; i++) {
			if (verifyElementPresent(Locator, validationText)) {
				break;
			}
			waitTime(2000);
			scrollDownWEB();
		}
	}

	public void webscrollToXpath(By xpath) throws Exception {
		for (int i = 0; i < 5; i++) {
			if (verifyElementExist(xpath, "xapth")) {
				System.out.println("Element Found");
				break;
			} else {
				scrollDownByY(100);

			}
		}
	}

//	public void WebHomepageTrayTitleAndContentValidationWithApiData(Response ApiData) throws Exception {
//
//		extent.HeaderChildNode("Home page validation with Api response");
//		Response resp = ApiData;
//		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
//		System.out.println("The Title of the Tray is " + Tray_Title + "");
//		webscrollToXpath(WebText_To_Xpath(Tray_Title));
//		waitTime(3000);
//		if (verifyElementExist(WebText_To_Xpath(Tray_Title), Tray_Title)) {
//			System.out.println("Tray title Found");
////			Verify_SeeAll_Functionality(Tray_Title);
////			Navigate_to_HomeScreen_using_Zee5Logo();
//		} else {
//			System.out.println("Tray title Not found");
//		}
//
//		String Content_Title = resp.jsonPath().getString("buckets[1].items[0].title");
//		System.out.println("Content Title is " + Content_Title + "");
//		scrollDownWEB();
//		webscrollToXpath(TitleTextToXpath(Content_Title));
//		waitTime(3000);
//		if (verifyElementExist(TitleTextToXpath(Content_Title), Content_Title)) {
//			System.out.println("Content title Found");
//			verifyElementPresent(TitleTextToXpath(Content_Title), "Playable Content");
//		}
//
////			verifyElementPresentAndClick(Text_To_Xpath(Content_Title), "Playable Content");
////			waitForElementDisplayed(Text_To_Xpath(Content_Title), 20);
////			verifyElementPresent(Text_To_Xpath(Content_Title), "Playable content ");
////			Why_Register_POPUP();
////			verifyElementPresentAndClick(Pwa_LandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");
//
//		else {
//			System.out.println("Content_Title Not found");
//		}
//
//	}

	public void WebHomepageTrayTitleAndContentValidationWithApiData(Response ApiData) throws Exception {
		extent.HeaderChildNode("Home page validation with Api response");
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);
		//Response resp = ApiData;
		new LinkedList<String>();
		Response resp =ResponseInstance.getResponseForPages("home", languageSmallText);
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		waitTime(3000);
		partialScroll();

		if (verifyElementExist(WebText_To_Xpath(Tray_Title), Tray_Title)) {
			// System.out.println("Tray title Found");
			logger.info("Title Found in UI" + Tray_Title);
			extent.extentLogger("Title Found in UI", "Title Found in UI" + Tray_Title);
		} else {
			logger.info("Title not Found in UI");
			extent.extentLogger("Title not Found in UI", "Title not Found in UI");
			// System.out.println("Tray title Not found");
		}

		String Content_Title = resp.jsonPath().getString("buckets[1].items[0].title");
		System.out.println("Content Title is " + Content_Title + "");
		scrollDownWEB();
		// webscrollToXpath(TitleTextToXpath(Content_Title));
		waitTime(3000);
		if (verifyElementExist(TitleTextToXpath(Content_Title), Content_Title)) {
			logger.info("Content Found in UI" + Tray_Title);
			extent.extentLogger("Content Found in UI", "Content Found in UI" + Tray_Title);
			verifyElementPresent(TitleTextToXpath(Content_Title), "Playable Content");
		} else {
			logger.info("Content not Found in UI");
			extent.extentLogger("Content not Found in UI", "Content not Found in UI");
		}

		partialScroll();
		waitTime(5000);
		if (verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top")) {
			System.out.println("Navigate back to the Top of Application");
			logger.info("Navigate back to the Top of Application");
			extent.extentLogger("Back to top", "Navigate back to the Top of Application");
		} else {
			logger.info("Didn't Navigate back to the Top of Application");
			extent.extentLogger("Back to top", "Didn't Navigate back to the Top of Application");
		}
	}

	public void WebHomepageTrayTitleAndContentValidationWithApiDataForSubcribedUser(Response ApiData) throws Exception {
		extent.HeaderChildNode("Homepage validation with respect to api response");
		Response resp = ApiData;
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		webscrollToXpath(WebText_To_Xpath(Tray_Title));
		if (verifyElementExist(WebText_To_Xpath(Tray_Title), Tray_Title)) {
			System.out.println("Tray title Found");
//			Verify_SeeAll_Functionality(Tray_Title);
//			Navigate_to_HomeScreen_using_Zee5Logo();
		} else {
			System.out.println("Tray title  found");
		}
		String Content_Title = resp.jsonPath().getString("buckets[1].items[0].title");
		System.out.println("Content Title is " + Content_Title + "");
		scrollDownWEB();
		webscrollToXpath(TitleTextToXpath(Content_Title));
		if (verifyElementExist(TitleTextToXpath(Content_Title), Content_Title)) {
			System.out.println("Content title Found");
			verifyElementPresent(TitleTextToXpath(Content_Title), "Playable Content");
		}

//			verifyElementPresentAndClick(Text_To_Xpath(Content_Title), "Playable Content");
//			waitForElementDisplayed(Text_To_Xpath(Content_Title), 20);
//			verifyElementPresent(Text_To_Xpath(Content_Title), "Playable content ");
//			Why_Register_POPUP();
//			verifyElementPresentAndClick(Pwa_LandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");

		else {
			System.out.println("Content_Title Not found");
		}

	}

	public void landingpageValidation_for_SubcribedUser() throws Exception {
		extent.HeaderChildNode("Validating home page properties for subcribed user");
		verifyElementPresent(PWALandingPages.obj_WEBPwa_HamburgerMenu1, "Hamburger Menu");
		verifyElementPresent(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");
		verifyElementPresent(PWALandingPages.obj_Pwa_SearchBtn, "Search");
		verifyElementNotPresent(PWALandingPages.obj_Pwa_Subcription_teaser_btn, 5);

	}

	public void Loggedin_User(String Rail_Name, String Content_Name, String userType) throws Exception {
		landingpagePropertiesValidation();
		Homepage_Title_with_Api(Rail_Name);
		Homepage_Content_selection_playback_with_Api(Rail_Name, Content_Name);
		Verify_Get_Premium_Trailer(userType);
		Back_TO_TopWeb();
	}

	public void FirstTimeNonSubcribed_Loggedin_User() throws Exception {
		extent.HeaderChildNode("First time loggedin user Trending on zee5 validation");
		FirstTimeUser_Trending_on_zee5();
	}

//public void webscrollToXpath(By xpath) throws Exception
//	{
//		for(int i=0;i<5;i++) {
//			if(verifyElementExist(xpath, "xapth")) {
//				System.out.println("Element Found");
//				break;
//			}
//			else {
//				scrollDown();
//				
//			}
//		}
//	}
	public By TitleTextToXpath(String Title) throws Exception {
//		return By.xpath("//*[@title='"+Title+"']");
		return By.xpath("(//*[@title='" + Title + "' and ./ancestor::div[contains(@class,'movieTrayWrapper')]])[1]");

	}

	public void FirstTimeSubcribed_Loggedin_User() throws Exception {
		extent.HeaderChildNode("First time subcribed user Trending on zee5 validation");
		FirstTimeUser_Trending_on_zee5();
	}

	public void FirstTimeUser_Trending_on_zee5() throws Exception {

		Swipe_till_Zee5IsTrending();
		if (verifyElementExist(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Zee5 Trending")) {
			System.out.println("Trending is found and is a first time user");
		} else {
//      System.out.println("Not a first time user");
			Swipe_till_Zee5IsTrending();
			verifyElementPresent(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Zee5 Trending");
		}
	}

	public void Homepage_Title_with_Api(String RailTitle) throws Exception {
		Response resp = ResponseInstance.getResponse();
		for (int i = 0; i < resp.jsonPath().getList("buckets").size(); i++) {
			if (resp.jsonPath().getString("buckets[" + i + "].title").equals(RailTitle)) {
//          System.out.println(i);
				System.out.println("Tray Title is : " + RailTitle + ", found on API");
				Swipe_till_Text(RailTitle);
				Verify_SeeAll_Functionality(RailTitle);
			}
//      else {
////            System.out.println("["+RailTitle+"] title not found");
//      }
		}
		Navigate_to_HomeScreen_using_Zee5Logo();
	}

	public void Homepage_Content_selection_playback_with_Api(String Rail_Name, String Content_Name) throws Exception {
		Response resp = ResponseInstance.getResponse();
		for (int i = 0; i < resp.jsonPath().getList("buckets").size(); i++) {
			if (resp.jsonPath().getString("buckets[" + i + "].title").equals(Rail_Name)) {
				for (int j = 0; j < resp.jsonPath().getList("buckets[+i+].items").size(); j++) {
					if (resp.jsonPath().getString("buckets[" + i + "].items[" + j + "].title").equals(Content_Name)) {
						System.out.println("Content Name Found : " + Content_Name + " ");
						Swipe_till_Text(Content_Name);
						verifyElementPresentAndClick(Text_To_Xpath(Content_Name), "Playable Content");
						waitForElementDisplayed(Text_To_Xpath(Content_Name), 20);
						verifyElementPresent(Text_To_Xpath(Content_Name), "Playable content ");
						Why_Register_POPUP();
						verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");
					} else {
						System.out.println("Content Not found on Api");
					}
				}
			}
		}
	}

//	public By WebText_To_Xpath(String text) throws Exception {
//
//		return By.xpath("//div[.='" + text + "'] | //*[contains(@text,'" + text + "')]");
//
//	}

	// Yashaswini
	public By WebText_To_Xpath(String text) throws Exception {

		return By.xpath("(//a[@class='titleLink'])[1]");

	}

	public void Verify_Get_Premium_Trailer(String userType) throws Exception {

		WatchTrailer();
		verifyElementPresentAndClick(PWASearchPage.watchTrailerBtn, "watch Trailer");
		waitForElementDisplayed(PWASearchPage.Obj_Pwa_Get_Premium_btn, 60);
		verifyElementPresent(PWASearchPage.Obj_Pwa_Get_Premium_btn, "Get Primium");
		Navigate_to_HomeScreen_using_Zee5Logo();

	}

	public void Swipe_till_Zee5IsTrending() throws Exception {
		waitTime(5000);
		int found = 0;
		for (int i = 0; i <= 2; i++) {
			if (verifyElementPresent(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Zee5 is trending")) {
				System.out.println("element found");
				found = 1;
				break;
			} else {
				webScrollToElement(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Trending on Zee5");
			}
			if (found == 0) {
				System.out.println("Trending on Zee5 not found and not First time user");
			}
		}
	}

	public void Swipe_till_Text(String text) throws Exception {
		waitTime(4000);
		for (int i = 0; i <= 5; i++) {
			if (verifyElementExist(Text_To_Xpath(text), text)) {
				System.out.println("element found");
				break;
			} else {
//			PartialSwipe("up", 1);
				scrollDownWEB();
			}
		}
	}

	public void Verify_SeeAll_Functionality(String s) throws Exception {
		waitTime(3000);
//	verifyElementPresentAndClick(objTrayTitleArrowBtn(s), "view all");
		if (verifyElementExist(PWALandingPages.objTrayTitleArrowBtn(s), s)) {
			waitForElementDisplayed(PWALandingPages.obj_Pwa_Trending_On_Zee5, 10);
			verifyElementPresent(Text_To_Xpath(s), s);
		} else {
			System.out.println("See all Not visible");
		}
	}

	public void Navigate_to_HomeScreen_using_Zee5Logo() throws Exception {
		extent.HeaderChildNode("Navigate to HomeScreen using Zee5 Logo");
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee5 Logo");
		waitForElementDisplayed(PWAHomePage.objContTitleOnCarousel, 20);
		verifyElementPresent(PWAHomePage.objContTitleOnCarousel, "Carousal content title");
	}

	public By Text_To_Xpath(String text) throws Exception {

		return By.xpath("//*[contains(@text,'" + text + "')]");

	}

	public By TextToXpath(String text) throws Exception {
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + text + "')]");
	}

	public void Why_Register_POPUP() throws Exception {

		try {
			if (checkcondition(PWALandingPages.obj_Pwa_WhyRegister_Popup)) {
				verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Popup_Close, "Close btn");
			} else {
				System.out.println("popup not displayed");
			}
		} catch (Exception e) {
			System.out.println("popup not displayed");
		}
	}

//public static Response getResponse() {
//	Response response = given().urlEncodingEnabled(false).when().get(
//			"https://gwapi.zee5.com/content/collection/0-8-homepage?limit=20&page=1&item_limit=20&desc=no&version=6&translation=en&languages=en,kn&country=IN");
//	return response;
//}

//-----------------------------------TANISHA-------------------------------------------------

	/**
	 * Method to verify Consumptions screen tapping on any content card
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void oldverifyConsumptionsScreenTappingOnCard(String userType, String contentType, String contentTitle,
			String devicePin) throws Exception {
		extent.HeaderChildNode("Verify Consumption Page for Content type: " + contentType);
		System.out.println("Verify Consumption Page for Content type: " + contentType);
		String consumptionPageTitle = "";
		if (contentType.equals("Live TV")) {
			if (verifyElementExist(PWAHomePage.objMoreMenuIcon, "More Menu Icon") == true) {
				verifyElementPresentAndClick(PWAHomePage.objMoreMenuIcon, "More Menu Icon");
				waitTime(5000);
				verifyElementPresentAndClick(PWAHomePage.objMoreMenuTabs("Live TV"), "Live TV Tab");
			}
			// verifyElementPresentAndClick(PWAHomePage.objTabName("Live TV"), "Live TV
			// tab");
			waitForElement(PWAShowsPage.objFirstAssetTitleLiveTvCard, 30, "Content title");
			contentTitle = getElementPropertyToString("innerText", PWAShowsPage.objFirstAssetTitleLiveTvCard,
					"Content Title").toString();
//			waitForElement(PWAShowsPage.objFirstAssetImageLiveTvCard, 30, "Live TV Card");

			if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup")) {
				click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
			}
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}

			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}

			verifyElementPresentAndClick(PWAShowsPage.objFirstAssetImageLiveTvCard, "Live TV Card");
			System.out.println("userType : " + userType);
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {

				waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 10, "Close in Register Pop Up");

			} else if (userType.equals("SubscribedUser")) {

				// enterDevicePin(devicePin);

			} else {
				waitForPlayerAdToComplete("Video Player");
				extent.extentLoggerFail("incorrectUserType", "Incorrect User Type entered in script");
				logger.error("Incorrect User Type entered in script");
			}
			waitForElement(PWAPlayerPage.objContentTitleLiveTV, 20, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitleLiveTV,
					"Content Title").toString();
		} else {
//			waitTime(6000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
			// waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10,
			// "Close in Language Pop Up");
//			waitTime(4000);
			type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
//			waitForElement(PWASearchPage.objSearchedResult(contentTitle), 10, "Search Result");
			waitTime(4000);
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {

//				waitForPlayerAdToComplete("Video Player");
				waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 10, "Close in Register Pop Up");
//				waitForPlayerAdToComplete("Video Player");

			} else if (userType.equals("SubscribedUser")) {
				// enterDevicePin(devicePin);
			} else {
				extent.extentLoggerFail("incorrectUserType", "Incorrect User Type entered in script");
				logger.error("Incorrect User Type entered in script");
				System.out.println("Incorrect User Type entered in script");
			}

//			waitForElement(PWAPlayerPage.objContentTitle, 30, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Content Title").toString();
		}

		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			logger.info("Successfully navigated to the correct Consumption page " + consumptionPageTitle);

//			System.out.println("contentType : " + contentType);
//			if (contentType.equals("Live TV")) {
//
////				pausePlayerForLiveTV();
//
//			} else {
////				pausePlayerAndGetLastPlayedTime();
//			}

		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}

	public void verifyConsumptionsScreenTappingOnCard(String userType, String contentType, String contentTitle)
			throws Exception {
		extent.HeaderChildNode("Verify Consumption Page for Content type: " + contentType);
		System.out.println("Verify Consumption Page for Content type: " + contentType);
		mandatoryRegistrationPopUp(userType);
		String consumptionPageTitle = "";
		if (contentType.equals("Live TV")) {
			navigateToAnyScreenOnWeb("Live TV");
			waitForElementAndClickIfPresent(PWAShowsPage.objFirstAssetTitleLiveTvCard, 10, "Live TV Card");
			waitForElement(PWAPlayerPage.objContentTitleLiveTV, 10, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitleLiveTV,
					"Content Title").toString();
		} else {
			waitTime(6000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchedResult(contentTitle), 10, "Search Result");
			mandatoryRegistrationPopUp(userType);
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Content Title").toString();
		}
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			logger.info("Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			/*
			 * if (contentType.equals("Live TV")) { pausePlayerForLiveTV(); } else {
			 * pausePlayerAndGetLastPlayedTime(); }
			 */
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}

	/**
	 * Method to verify share functionality and metadata comparison between Show
	 * Details and Consumption page plays in player
	 * 
	 * @param contentTitle
	 * @throws Exception
	 */
	public void verifyCTAandMetaDataInDetailsAndConsumption(String contentTitle) throws Exception {
		extent.HeaderChildNode(
				"Verify Watch Latest Episode CTA and metadata comparison between Show Details and Consumption page");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitForElement(PWASearchPage.objSearchNavigationTab("Shows"), 30, "Shows tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows tab");
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Details page");
			logger.info("Successfully navigated to the correct Details page");
			verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest CTA button");
			String watchLatestCTAText = getElementPropertyToString("innerText", PWAShowsPage.objWatchLatestCTA,
					"Watch Latest CTA button").toString();
			if (watchLatestCTAText.equals("Watch Latest Episode")) {
				extent.extentLogger("correctButtonText", "Correct button text displayed: " + watchLatestCTAText);
				logger.info("Correct button text displayed: " + watchLatestCTAText);
			} else {
				extent.extentLoggerFail("incorrectButtonText",
						"Incorrect button text displayed: " + watchLatestCTAText);
				logger.error("Incorrect button text displayed: " + watchLatestCTAText);
			}
			partialScroll();
			verifyElementPresentAndClick(PWAShowsPage.objSecondAssetImageFirstRail,
					"Second asset image from first rail");
			// Get API details
			String contentURL = getWebDriver().getCurrentUrl();
			String[] abc = contentURL.split("/");
			String contentID = abc[abc.length - 1].split("\\?")[0];
			System.out.println("contentID fetched from URL: " + contentID);
			Response resp = ResponseInstance.getContentDetails(contentID, "content");
			String titleAPI = resp.jsonPath().get("title").toString();
			extent.extentLogger("apidata", "Episode title fetched from API: " + titleAPI);
			logger.info("Episode title fetched from API: " + titleAPI);

			String showtitleAPI = resp.jsonPath().get("tvshow.title").toString();
			extent.extentLogger("apidata", "Show title fetched from API: " + showtitleAPI);
			logger.info("Show title fetched from API: " + showtitleAPI);

			String episodeNoAPI = resp.jsonPath().get("orderid").toString();
			extent.extentLogger("apidata", "Episode number fetched from API: " + episodeNoAPI);
			logger.info("Episode number fetched from API: " + episodeNoAPI);

			String durationAPI = resp.jsonPath().get("duration").toString();
			extent.extentLogger("apidata", "Duration fetched from API: " + durationAPI);
			logger.info("Duration fetched from API: " + durationAPI);

			String genreAPI = resp.jsonPath().get("genre[0].value").toString();
			extent.extentLogger("apidata", "Genre fetched from API: " + genreAPI);
			logger.info("Genre fetched from API: " + genreAPI);

			String ageRatingAPI = resp.jsonPath().get("age_rating").toString();
			extent.extentLogger("apidata", "Age Rating fetched from API: " + ageRatingAPI);
			logger.info("Age Rating fetched from API: " + ageRatingAPI);

			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
			String episode = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Episode title in Consumption Page").toString();
			String show = getElementPropertyToString("innerText", PWAPlayerPage.objConsumptionsShowTitle,
					"Show title in Consumption Page").toString();
			String episodeNo = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaEpisode,
					"Episode Number in Consumption Page").toString();
			String duration = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaDuration,
					"Duration in Consumption Page").toString();
			String genre = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaGenre,
					"Genre in Consumption Page").toString();
			String ageRating = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaAgeRating,
					"Age Rating in Consumption Page").toString();

			if (titleAPI.equals(episode)) {
				extent.extentLogger("titleMatch", "Consumption page content Title matched with API");
				logger.info("Consumption page content Title matched with API");
			} else {
				extent.extentLoggerFail("titleMismatch", "Consumption page content Title mismatched with API");
				logger.error("Consumption page content Title mismatched with API");
			}

			if (showtitleAPI.equals(show)) {
				extent.extentLogger("showMatch", "Consumption page content Show matched with API");
				logger.info("Consumption page content Show matched with API");
			} else {
				extent.extentLoggerFail("showMismatch", "Consumption page content Show mismatched with API");
				logger.error("Consumption page content Show mismatched with API");
			}
			episodeNo = episodeNo.split("Episode ")[1];
			if (episodeNoAPI.equals(episodeNo)) {
				extent.extentLogger("episodeMatch", "Consumption page content Episode Number matched with API");
				logger.info("Consumption page content Episode Number matched with API");
			} else {
				extent.extentLoggerFail("episodeMismatch",
						"Consumption page content Episode Number mismatched with API");
				logger.error("Consumption page content Episode Number mismatched with API");
			}

			durationAPI = String.valueOf((Integer.parseInt(durationAPI) / 60));
			duration = duration.split("m")[0];
			if (durationAPI.equals(duration)) {
				extent.extentLogger("durationMatch", "Consumption page content Duration matched with API");
				logger.info("Consumption page content Duration matched with API");
			} else {
				extent.extentLoggerFail("durationMismatch", "Consumption page content Duration mismatched with API");
				logger.error("Consumption page content Duration mismatched with API");
			}

			if (genreAPI.equals(genre)) {
				extent.extentLogger("genreMatch", "Consumption page content Genre matched with API");
				logger.info("Consumption page content Genre matched with API");
			} else {
				extent.extentLoggerFail("genreMismatch", "Consumption page content Genre mismatched with API");
				logger.error("Consumption page content Genre mismatched with API");
			}

			if (ageRatingAPI.equals(ageRating)) {
				extent.extentLogger("ageRatingMatch", "Consumption page content Age Rating matched with API");
				logger.info("Consumption page content Age Rating matched with API");
			} else {
				extent.extentLoggerFail("ageRatingMismatch", "Consumption page content Age Rating mismatched with API");
				logger.error("Consumption page content Age Rating mismatched with API");
			}

		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
	}

	/**
	 * Method to pause the player and get the duration lapsed
	 * 
	 * @throws Exception
	 */
	public void pausePlayerAndGetLastPlayedTime() throws Exception {
		if (!waitForElementToLeaveScreen(PWAPlayerPage.objPlayLoader, 10, "Player Loader")) {
			waitForPlayerAdToComplete("Video Player");
			if (pausePlayer() == true) {
				getPlayerDuration();
			} else {
				extent.extentLoggerFail("failedToPause", "Failed to pause Player");
				logger.error("Failed to pause Player");
			}
		}
	}

	/**
	 * Method to get the duration lapsed in the player
	 */
	public void getPlayerDuration() {
		String duration = getElementPropertyToString("innerText", PWAPlayerPage.objPlayerCurrentDuration,
				"Player Current Duration").toString();
		if (duration != null) {
			extent.extentLogger("contentDuration", "Successfully played content " + duration);
			logger.info("Successfully played content " + duration);
		} else {
			extent.extentLoggerFail("durationFailed", "Failed to get Current Duration");
			logger.error("Failed to get Current Duration");
		}
	}

	/**
	 * Method to Pause the Player
	 */
	public boolean pausePlayer() throws InterruptedException {
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			try {
				click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
				// click(PWAPlayerPage.objPlayerPause, "Player Pause");
				try {
					getWebDriver().findElement(PWAPlayerPage.playBtn);
					extent.extentLogger("playerPaused", "Paused the Player");
					logger.info("Paused the Player");
					playerPaused = true;
					break;
				} catch (Exception e) {
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Player");
					logger.error("Error when handling Player");
				}
			}
		}
		return playerPaused;
	}

	@SuppressWarnings("unused")
	public void firefoxpause() throws InterruptedException {
		boolean playerPaused = false;

		// getWebDriver().findElement(By.xpath("//i[@class='playkit-icon
		// playkit-icon-pause']")).click();

		for (int trial = 0; trial <= 4; trial++) {
			try {

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();

				WebElement menuOption1 = getWebDriver().findElement(By.xpath("//div[@class='playkit-overlay-action']"));
				actions.moveToElement(menuOption1).perform();

//			    Thread.sleep(1000);
//			    if(verifyElementExist(PWAPlayerPage.objPlayerPause, "Player Pause"))
//			    {
				click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
//					click(PWAPlayerPage.objPlayerPause, "Player Pause");
//			    }

				try {
					getWebDriver().findElement(PWAPlayerPage.playBtn);
					extent.extentLogger("playerPaused", "Paused the Player");
					logger.info("Paused the Player");
					playerPaused = true;
					break;
				} catch (Exception e) {
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Player");
					logger.error("Error when handling Player");
				}
			}
		}

	}

	public boolean pauseLivePlayer() throws InterruptedException {
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			try {
				click(PWAPlayerPage.objPlayer, "Player");
				// Thread.sleep(1000);
				// (PWAPlayerPage.objPlayerPause, "Player Pause");
				try {
					getWebDriver().findElement(PWAPlayerPage.playBtn);
					extent.extentLogger("playerPaused", "Paused the Player");
					logger.info("Paused the Player");
					playerPaused = true;
					break;
				} catch (Exception e) {
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Player");
					logger.error("Error when handling Player");
				}
			}
		}
		return playerPaused;
	}

	/**
	 * Waits for player loader to complete
	 * 
	 * @throws Exception
	 */
	public void waitForPlayerLoaderToComplete() throws Exception {
		// verifyElementNotPresent(PWAPlayerPage.objPlayerLoader, 60);

		new WebDriverWait(getWebDriver(), 120)
				.until(ExpectedConditions.invisibilityOfElementLocated(PWAPlayerPage.objPlayerLoader));
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
			logger.error("Ad play failure");
			extent.extentLogger("failedAd", "Ad play failure");
		}
	}

	/**
	 * The method will wait for the element to be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located. The
	 * method throws error if the element could not be located within the given
	 * seconds
	 */
	public boolean waitForElement(By locator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator);
				logger.info("Located element " + message);
				extent.extentLogger("locatedElement", "Located element " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to locate element " + message);
					extent.extentLoggerFail("failedLocateElement", "Failed to locate element " + message);
				}
			}
		}
		return false;
	}

	/**
	 * Pause Player for Live TV
	 * 
	 * @throws Exception
	 */
	public void pausePlayerForLiveTV() throws Exception {
		if (!waitForElementToLeaveScreen(PWAPlayerPage.objPlayLoader, 10, "Player Loader")) {
			waitForPlayerAdToComplete("Live Player");
			if (pauseLivePlayer()) {
				try {
					getWebDriver().findElement(PWAPlayerPage.objLivePlayerVolume);
					extent.extentLogger("livePlayerVolume", "Located Live Player Volume");
					logger.info("Located Live Player Volume");
				} catch (Exception e) {
					extent.extentLoggerFail("livePlayerVolume", "Failed to locate Live Player Volume");
					logger.error("Failed to locate Live Player Volume");
				}
			}
		}
	}

	public boolean waitForElementToLeaveScreen(By locator, int seconds, String message) throws Exception {
		waitTime(2000);
		for (int time = 0; time <= seconds; time++) {
			try {
				getWebDriver().findElement(locator);
				Thread.sleep(1000);
				if (time == seconds) {
					logger.info(message + " is displayed");
					extent.extentLogger("element is displayed", message + " is displayed");
					return true;
				}
			} catch (Exception e) {
				logger.info(message + " is not displayed");
				extent.extentLogger("element is displayed", message + " is not displayed");
				return false;
			}
		}
		return false;
	}

	public void enterDevicePin(String devicePin) throws Exception {
		boolean devicePinPresent = false;
// wait and check if device pin box appears
		for (int trial = 0; trial <= 4; trial++) {
			try {
				getWebDriver().findElement(PWAHomePage.objDevicePin1);
				devicePinPresent = true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		if (devicePinPresent == true) {
			for (int trial = 0; trial <= 4; trial++) {
				try {
					getWebDriver().findElement(By.xpath("//input[@id='parentLockId1']"))
							.sendKeys(devicePin.substring(0, 1));
					getWebDriver().findElement(By.xpath("//input[@id='parentLockId2']"))
							.sendKeys(devicePin.substring(1, 2));
					getWebDriver().findElement(By.xpath("//input[@id='parentLockId3']"))
							.sendKeys(devicePin.substring(2, 3));
					getWebDriver().findElement(By.xpath("//input[@id='parentLockId4']"))
							.sendKeys(devicePin.substring(3, 4));
					logger.info("Entered Device PIN : " + devicePin);
					extent.extentLogger("devicePIN", "Entered Device PIN : " + devicePin);
					break;
				} catch (Exception e) {
					Thread.sleep(2000);
					if (trial == 4) {
						logger.error("Failed to enter device PIN");
						extent.extentLoggerFail("devicePINfail", "Failed to enter device PIN");
					}
				}
			}
		}
	}

	/**
	 * Dismiss the Display Language pop up
	 */
	public void dismissDisplayContentLanguagePopUp() throws Exception {
		extent.HeaderChildNode("Dismiss Display and Content Language Pop Ups");
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 90,
				"Continue on Display Language Pop Up");
		Thread.sleep(5000);
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup, 10,
				"Continue on Content Language Pop Up");
	}

	public void verifyWatchLatestEpisodeCTA(String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Watch Latest Episode CTA");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct Details page: " + contentTitle);
			logger.info("Successfully navigated to the correct Details page: " + contentTitle);
			verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest CTA button");
			String watchLatestCTAText = getElementPropertyToString("innerText", PWAShowsPage.objWatchLatestCTA,
					"Watch Latest CTA button").toString();
			if (watchLatestCTAText.equals("Watch Latest Episode")) {
				extent.extentLogger("correctButtonText", "Correct button text displayed: " + watchLatestCTAText);
				logger.info("Correct button text displayed: " + watchLatestCTAText);
			} else {
				extent.extentLoggerFail("incorrectButtonText",
						"Incorrect button text displayed: " + watchLatestCTAText);
				logger.error("Incorrect button text displayed: " + watchLatestCTAText);
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
	}

	public void verifyShareAndMetaDataInDetailsAndConsumption(String contentTitle) throws Exception {
		extent.HeaderChildNode(
				"Verify Share functionality and metadata comparison between Show Details and Consumption page");
		boolean sharePassed = false;
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 60, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct details page: " + contentTitle);
			logger.info("Successfully navigated to the correct Details page: " + contentTitle);
			// Share functionality
			waitForElementAndClickIfPresent(PWAShowsPage.objShareIcon, 5, "Share Icon");
			waitForElementAndClickIfPresent(PWALiveTVPage.objFacebookShareBtn, 5, "Share to Facebook");
			switchToWindow(2);
			if (verifyElementExist(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
				// waitForElementAndClickIfPresent(PWALiveTVPage.objFacebookEmailField, 5,
				// "Facebook Email field");
				click(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
				getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
				verifyElementPresentAndClick(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
				getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
				waitForElementAndClickIfPresent(PWALiveTVPage.objFacebookLoginBtn, 5, "Facebook Login button");
				waitTime(2000);
				verifyAlert();
			}
			waitForElementAndClickIfPresent(PWALiveTVPage.objPostToFacebookBtn, 5, "Post to Facebook");
			waitTime(3000);
			acceptAlert();
			switchToWindow(1);
			waitTime(3000);
			verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title in Zee5 PWA");
			sharePassed = true;
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
		if (sharePassed == true) {
			ScrollToTheElementWEB(PWAShowsPage.objFirstAssetTitleFirstRail);
			String detailsTitle = getElementPropertyToString("innerText", PWAShowsPage.objFirstAssetTitleFirstRail,
					"Content Title in Details Page").toString();
			String detailsEpisode = getElementPropertyToString("innerText", PWAShowsPage.objFirstAssetEpisodeFirstRail,
					"Content Episode number in Details Page").toString();
			String detailsDuration = getElementPropertyToString("innerText",
					PWAShowsPage.objFirstAssetDurationFirstRail, "Content total Duration in Details Page").toString();
			System.out.println("Data fetched from Show details: Title: " + detailsTitle + ", Episode: " + detailsEpisode
					+ ", Duration: " + detailsDuration);
			extent.extentLogger("dataFetched", "Data fetched from Show details: Title: " + detailsTitle + ", Episode: "
					+ detailsEpisode + ", Duration: " + detailsDuration);
			logger.info("Data fetched from Show details: Title: " + detailsTitle + ", Episode: " + detailsEpisode
					+ ", Duration: " + detailsDuration);
			detailsEpisode = detailsEpisode.split("E")[1];
			verifyElementPresentAndClick(PWAShowsPage.objFirstAssetImageFirstRail, "First asset image from first rail");
			waitForElementDisplayed(PWAPlayerPage.objContentTitle, 10);
			String consumptionTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Content Title in Consumption Page").toString();
			String consumptionEpisode = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaEpisode,
					"Content Episode Number in Consumption Page").toString();
			String consumptionDuration = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaDuration,
					"Content Total Duration in Consumption Page").toString();
			System.out.println("Data fetched from Consumptions page: Title: " + consumptionTitle + ", Episode: "
					+ consumptionEpisode + ", Duration: " + consumptionDuration);
			extent.extentLogger("dataFetched", "Data fetched from Consumptions page: Title: " + consumptionTitle
					+ ", Episode: " + consumptionEpisode + ", Duration: " + consumptionDuration);
			logger.info("Data fetched from Consumptions page: Title: " + consumptionTitle + ", Episode: "
					+ consumptionEpisode + ", Duration: " + consumptionDuration);
			consumptionEpisode = consumptionEpisode.split("Episode ")[1];
			if (detailsTitle.equals(consumptionTitle)) {
				extent.extentLogger("titleMatch",
						"Details page and Consumption page content Title matched: " + consumptionTitle);
				logger.info("Details page and Consumption page content Title matched: " + consumptionTitle);
			} else {
				extent.extentLoggerFail("titleMismatch",
						"Details page and Consumption page content mismatched: " + consumptionTitle);
				logger.error("Details page and Consumption page content mismatched: " + consumptionTitle);
			}
			if (detailsEpisode.equals(consumptionEpisode)) {
				extent.extentLogger("episodeMatch",
						"Details page and Consumption page content episode number matched: " + consumptionEpisode);
				logger.info("Details page and Consumption page content episode number matched: " + consumptionEpisode);
			} else {
				extent.extentLoggerFail("episodeMismatch",
						"Details page and Consumption page content episode number mismatched: " + consumptionEpisode);
				logger.error(
						"Details page and Consumption page content episode number mismatched: " + consumptionEpisode);
			}
			if (detailsDuration.equals(consumptionDuration)) {
				extent.extentLogger("durationMatch",
						"Details page and Consumption page content total duration matched: " + consumptionDuration);
				logger.info("Details page and Consumption page content total duration matched: " + consumptionDuration);
			} else {
				extent.extentLoggerFail("durationMismatch",
						"Details page and Consumption page content total duration mismatched: " + consumptionDuration);
				logger.error(
						"Details page and Consumption page content total duration mismatched: " + consumptionDuration);
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}

		if (verifyElementExist(PWAPlayerPage.objWebZeeLogo, "ZeeLogo") == true) {
			click(PWAPlayerPage.objWebZeeLogo, "Zeelogo");
		} else {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ") == true) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			click(PWAPlayerPage.objWebZeeLogo, "Zeelogo");
		}
	}

	public void acceptAlert() {
		try {
			getWebDriver().switchTo().alert().accept();
			logger.info("Dismissed the alert Pop Up");
			extent.extentLogger("Alert PopUp", "Dismissed the alert Pop Up");
		} catch (Exception e) {

		}
	}

	public void verifySubscriptionPopupForPremiumContent(String userType, String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Subscription Popup For Premium Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
		waitForElement(PWASearchPage.objSearchNavigationTab("Movies"), 30, "Movies tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Movies"), "Movies tab");
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 60, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		if (!waitForElementPresence(PWAPremiumPage.objPremiumPopUp, 1, "Premium Pop Up")) {
			if (userType.equals("Guest"))
				waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
			waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
			String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
					"Content Title").toString();
			if (consumptionPageTitle.contains(contentTitle)) {
				extent.extentLogger("correctNavigation",
						"Successfully navigated to the correct Consumption page: " + contentTitle);
				logger.info("Successfully navigated to the correct Consumption page: " + contentTitle);
				if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
					waitForElement(PWAPremiumPage.objPremiumPopUp, 15, "Premium Pop Up");
					waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 1, "Close in Premium Pop Up");
				} else {
					waitForElementAbsence(PWAPremiumPage.objPremiumPopUp, 15, "Premium Pop Up for Subscribed User");
				}
			} else {
				extent.extentLoggerFail("incorrectNavigation",
						"Navigated to incorrect Consumption page: " + consumptionPageTitle);
				logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
			}
		} else
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 1, "Close in Premium Pop Up");
	}

	/**
	 * The method will wait for the element to not be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located and
	 * throws error.
	 */
	public void waitForElementAbsence(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator);
				logger.error("Located element " + message);
				extent.extentLoggerFail("locatedElement", "Located element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.info("Expected behavior: " + message + " is not displayed");
					extent.extentLogger("failedLocateElement", "Expected behavior: " + message + " is not displayed");
				}
			}
		}
	}

	public void verifyNoSubscriptionPopupForFreeContent(String userType, String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify No Subscription Popup For Free Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 60, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		if (userType.equals("Guest")) {
			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
		}
		if (userType.equals("Guest"))
			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
		waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct Consumption page: " + contentTitle);
			logger.info("Successfully navigated to the correct Consumption page: " + contentTitle);
			waitForElementAbsence(PWAPremiumPage.objPremiumPopUp, 45, "Premium Pop Up");
		} else {
			extent.extentLoggerFail("incorrectNavigation",
					"Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}

	public boolean waitForElementAndClickIfPresent(By locator, int seconds, String message)
			throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				findElement(locator).click();
				logger.info("Clicked on " + message);
				extent.extentLogger("locatedElement", "Clicked on " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return false;
	}

	public boolean waitForElementPresence(By locator, int seconds, String message) throws Exception {
		try {
			WebDriverWait w = new WebDriverWait(getWebDriver(), seconds);
			w.until(ExpectedConditions.visibilityOfElementLocated(locator));
			logger.info(message + " is displayed");
			extent.extentLogger("element is displayed", message + " is displayed");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Function Scroll to Element
	 *
	 * @param element
	 * @throws Exception
	 */
	public void ScrollToTheElementWEB(By element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		jse.executeScript("window.scrollBy(0,-250)", "");
	}

	public void tearDown() {
		getWebDriver().quit();
	}

	public void navigateHome() {
		getWebDriver().get("https://newpwa.zee5.com/");
		getWebDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void BackButton(int x) {
		try {
			if (getPlatform().equals("Android")) {
				for (int i = 0; i < x; i++) {
					getDriver().navigate().back();
					logger.info("Back button is tapped");
					extent.extentLogger("Back", "Back button is tapped");
				}
			} else if (getPlatform().equals("Web")) {
				getWebDriver().navigate().back();
				logger.info("Back button is tapped");
				extent.extentLogger("Back", "Back button is tapped");
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public static void partialScroll() {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	public static void scrollDownByY(int y) {
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("window.scrollBy(0," + y + ")", "");
	}

	/**
	 * fetch selected languages
	 * 
	 * @throws Exception
	 */
	public String allSelectedLanguagesWEB() throws Exception {
		waitTime(3000);
		(new WebDriverWait(getWebDriver(), 60))
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PWAHamburgerMenuPage.objLanguageBtnWEB));
		Actions act = new Actions(getWebDriver());
		act.moveToElement(getWebDriver().findElement(PWAHamburgerMenuPage.objLanguageBtnWEB));
		click(PWAHamburgerMenuPage.objLanguageBtnWEB, "language btn");
//    		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "content languages");
		(new WebDriverWait(getWebDriver(), 60)).until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(PWAHamburgerMenuPage.objContentLanguageWrapper));
		List<WebElement> allSelectedLanguages = getWebDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);

		String langtext = "";
		for (int i = 0; i < allSelectedLanguages.size(); i++) {

			langtext = allSelectedLanguages.get(i).getAttribute("for").replace("content_", "") + "," + langtext;
		}
		String finalLangString = langtext.replaceAll(",$", "");
		waitForElementAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, 2, "language btn");
		return finalLangString;
	}

	public void verifyMetadataOnCarousel(String screen, String pageName, String languageSmallText) throws Exception {
		extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);
		navigateToAnyScreenOnWeb(screen);
		waitTime(5000);
		boolean isTitlePresent = false;

		System.out.println("Selected languages : " + languageSmallText);
		List<String> allMetaTitleOnCarouselAPI = ResponseInstance.traysTitleCarousel(pageName, languageSmallText);
		System.out.println("API Data : " + allMetaTitleOnCarouselAPI);

		for (int i = 0; i < allMetaTitleOnCarouselAPI.size(); i++) {
			for (int j = 0; j < 30; j++) {
				isTitlePresent = false;
				try {
					WebElement mastHeadEle = (new WebDriverWait(getWebDriver(), 60))
							.until(ExpectedConditions.presenceOfElementLocated(
									PWAHomePage.objContTitleTextCarouselWeb(allMetaTitleOnCarouselAPI.get(i))));
					isTitlePresent = verifyElementExistUsingWebEl(mastHeadEle, "Carousel Title");
				} catch (Exception e) {
				}
				if (isTitlePresent == true) {
					break;
				} else {
					click(PWANewsPage.objRight, "Right button of Carousel");
				}
			}
			if (isTitlePresent == true) {
				logger.info("API title " + allMetaTitleOnCarouselAPI.get(i) + " is present on UI");
				extent.extentLogger("Metadata validation",
						"API title " + allMetaTitleOnCarouselAPI.get(i) + " is present on UI");
			} else {
				logger.error("API title did not matched with UI title");
				extent.extentLoggerFail("Metadata validation", "API title did not matched with UI title");
			}
		}
	}

	public boolean verifyElementExistUsingWebEl(WebElement ele, String str) throws Exception {
		try {
			WebElement element = ele;
			if (element.isDisplayed()) {
				logger.info("" + str + " is displayed");
				extent.extentLogger("" + str + " is displayed", "" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			logger.info(str + " is not displayed");
			extent.extentLogger("" + str + " is not displayed", "" + str + " is not displayed");
			return false;
		}
		return false;
	}

//--------------------------------------------------------SANITY FUNCUIONALITY----------------------------------------------------------

	/**
	 * ================================BASAVARAJ TIMED
	 * ANCHORS==================================
	 * 
	 */

	/**
	 * ================SHREENIDHI Mandatory registration======================
	 * 
	 */

	public void registerPopUpScenarios(String userType) throws Exception {
		switch (userType) {
		case "Guest":
			extent.HeaderChildNode("Guest user scenario");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			registerPopUpFunctionalityWeb(userType);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("NonSubscribedUser scenario");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			completeProfilePopupWeb();
			ZeeWEBPWALogin(userType);
			break;
		}
	}

	public void registerPopUpFunctionalityWeb(String userType) throws Exception {
		extent.HeaderChildNode("Premium content popup functionality");
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		Thread.sleep(10000);

		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search content");

		Thread.sleep(2000);
		if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Pop Up") == false) {
			logger.info("Register Popup is not displayed for premium content");
			extent.extentLogger("Pop-Up", "Register Popup is not displayed for premium content");

		} else {
			logger.info("Register Popup is displayed for premium content");
			extent.extentLoggerFail("Pop-Up", "Register Popup is displayed for premium content");

		}
		extent.HeaderChildNode("SignUp-PopUp Funtionality");
		Thread.sleep(2000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		type(PWAHomePage.objSearchField, keyword1, "Search");
		waitTime(10000);
		// mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword1), "Search Result");
		waitTime(10000);
		if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Pop Up")) {
			logger.info("Register Popup is displayed");
			extent.extentLogger("Pop-Up", "Register Popup is displayed");
			logger.info("PopUp is verifed in portrait mode");
			extent.extentLogger("Popup", "PopUp is verifed in portrait mode");
			verifyElementExist(PWAHomePage.objPopUpMobileField, "Mobile field in pop up");
			type(PWAHomePage.objPopUpMobileField, "7892215214", "Mobile field");
			verifyElementExist(PWAHomePage.objPopUpPasswordField, "Password field in pop up");
			type(PWAHomePage.objPopUpPasswordField, "User@123", "Password field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objFirstName, "First Name Field");
			type(CompleteYourProfilePopUp.objFirstName, "Test", "First Name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objLastName, "Last Name Field");
			type(CompleteYourProfilePopUp.objLastName, "User", "Last Name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objDOBField, "Date of Birth Field");
			type(CompleteYourProfilePopUp.objDOBField, "17091997", "Date of Birth Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderDropDown, "Gender drop down");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderfemale, "Female option");
			verifyElementPresentAndClick(PWAHomePage.objPopUpProceedButton, "Proceed button");
			try {
				Boolean SavedChangesToastMessage = getWebDriver().getPageSource()
						.contains("//*[@class='toastMessage']");
				if (SavedChangesToastMessage == true) {
					extent.extentLogger("Toast", "User Already exist Toast Message displayed");
					logger.info("User Already exist Toast Message displayed");
				} else {
					extent.extentLogger("Toast", "User Already exist Toast Message is not displayed");
					logger.info("User Already exist Toast Message is not displayed");
				}

			} catch (Exception e) {
				System.out.println("Toast message is not displayed");
			}
			if (verifyElementExist(PWALoginPage.objFacebookIcon, "Facebook icon") == false) {
				logger.info("Social media login is not displayed in register popup");
				extent.extentLogger("Social media icon", "Social media login is not displayed in register popup");
			}
			int lenText = findElement(PWAHomePage.objPopUpMobileField).getAttribute("value").length();
			for (int i = 0; i < lenText; i++) {
				getWebDriver().findElement(PWAHomePage.objPopUpMobileField).sendKeys(Keys.BACK_SPACE);
			}

			waitTime(5000);
			type(PWAHomePage.objPopUpMobileField, "8660863575", "Mobile field");
			verifyElementPresentAndClick(PWAHomePage.objPopUpProceedButton, "Proceed button");
			waitTime(2000);
			if (verifyElementExist(PWAHomePage.objOtpPopUp, "PopUp")) {
				logger.info("Otp screen is displayed");
				extent.extentLogger("Popup", "Otp screen is displayed");
			}
			click(PWASearchPage.objCloseRegisterDialog, "Close button");
			click(PWAHomePage.objZeeLogo, "Zee logo");
			changeLanguageAndVerifyPopUp(userType);
		} else {
			logger.info("Register popup is not displayed");
			extent.extentLoggerFail("Popup", "Register popup is not displayed");
		}
	}

	/**
	 * Validation of Register Popup Functionality after changing the display
	 * language
	 */
	public void changeLanguageAndVerifyPopUp(String userType) throws Exception {
		extent.HeaderChildNode("Change language and verification of SignUp pop up");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageButtonWeb, "Language button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objkannadalanguage, "kannada Language button");
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply buttton");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply buttton");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("freeMovie");
		type(PWAHomePage.objSearchField, keyword, "Search");
		waitTime(5000);
		waitForElement(PWASearchPage.objSearchedResultChangedLanguage(keyword), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResultChangedLanguage(keyword), "Search Result");
		waitTime(4000);
		if (verifyElementExist(PWASearchPage.objRegisterDialogAfterchangedLanguage, "Pop Up")) {
			logger.info("Register Popup is validated after changing language");
			extent.extentLogger("Pop-Up", "Register Popup is validated after changing language");
		}
		click(PWASearchPage.objCloseRegisterDialog, "Close button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInKannada, "More setting");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(3000);
		click(PWAHomePage.objZeeLogo, "Zee logo");
	}

	/**
	 * Validation of Complete Profile Popup Functionality
	 */
	public void completeProfilePopupWeb() throws Exception {
		extent.HeaderChildNode("Complete profile popup functionality");
		logout();
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(2000);
		verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
		waitTime(3000);
		extent.HeaderChildNode("Login through incomplete profile account");
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, "indaus24@gmail.com", "Email Field");
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, "123456", "Password field");
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("freeMovie");
		type(PWAHomePage.objSearchField, keyword, "Search");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHomePage.objSearchResult, "Search result");
		waitTime(3000);
		if ((verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt, "Complete Your Profile") == true)) {
			extent.HeaderChildNode("Verification of complete profile popup in potrait mode");
			logger.info("PopUp is verifed in portrait mode");
			extent.extentLogger("Popup", "PopUp is verifed in portrait mode");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objFirstName, "First Name Field");
			type(CompleteYourProfilePopUp.objFirstName, "Test", "First Name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objLastName, "Last Name Field");
			type(CompleteYourProfilePopUp.objLastName, "User", "Last Name Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objDay, "Day Field");
			click(CompleteYourProfilePopUp.objDateSelector, "Date");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objYear, "Year Field");
			click(CompleteYourProfilePopUp.objDateSelector, "Year");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderFemale, "Gender Field");
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objMobileNo, "Mobile Number");
			type(CompleteYourProfilePopUp.objMobileNo, "95839633299", "Mobile Number");
			waitTime(3000);
			verifyElementPresentAndClick(CompleteYourProfilePopUp.objCloseBtn, "Close Button");
			click(PWAHomePage.objZeeLogo, "Zee logo");
		} else {

			logger.info("Complete Profile pop up is not displayed");
			extent.extentLogger("Complete Profile pop up", "Complete Profile pop up is not displayed");
		}
		logout();
	}

	/**
	 * Validation of Upgrage Popup Functionality for RSVOD user
	 */
	public void rsvodPopupWeb() throws Exception {
		extent.HeaderChildNode("Functionality of Upgarde popup for RSVOD user");
		logout();
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		waitTime(2000);
		verifyElementPresent(PWALoginPage.objWebLoginPageText, "Login page");
		extent.HeaderChildNode("Login through RSVOD User");
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, "sooraj.igs@gmail.com", "Email Field");
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, "igs@2020", "Password field");
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("premiumMovie");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Popup") == false) {
			logger.info("Register popup is not displayed for premium user");
			extent.extentLogger("Popup", "Register popup is not displayed for premium user");
		}
		waitTime(8000);
		if (verifyElementExist(PWASearchPage.objUpgradePopup, "Popup")) {
			extent.HeaderChildNode("Upgrade PopUp Funtionality");
			logger.info("Upgrade popup is displayed for RSVOD user");
			extent.extentLogger("Popup", "Upgrade popup is displayed for RSVOD user");
			extent.HeaderChildNode("Verification of popup in portrait mode");
			logger.info("PopUp is verifed in portrait mode");
			extent.extentLogger("Popup", "PopUp is verifed in portrait mode");
			click(PWASearchPage.objUpgradePopupCloseButton, "Close button");
			logout();
//			validateDisplayLanguagePopup();
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
		}
	}

	/**
	 * ================================SHREENIDHI
	 * Profile==================================
	 * 
	 */

	/**
	 * Validation of Profile Functionality according user types.
	 */
	public void myProfileScenarios(String userType) throws Exception {

		switch (userType) {
		case "NonSubscribedUser":
			extent.HeaderChildNode("Non-Subscribed User Scenario");
			extent.extentLogger("Accessing as Non-Subscribed User", "Accessing as Non-Subscribed User");
			logger.info("Accessing as Non-Subscribed User");
			ProfileSanityWeb();
			ZeeWEBPWALogin(userType);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("Subscribed User scenario");
			extent.extentLogger("Accessing as Subscribed User", "Accessing as Subscribed User");
			logger.info("Accessing as Subscribed User");
			ProfileSanityWeb();
			ZeeWEBPWALogin(userType);
			break;
		}
	}

	public void ProfileSanityWeb() throws Exception {
		extent.HeaderChildNode("My Profile functionality");
		scrollDownWEB();

		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(5000);

		if (verifyElementExist(PWAHamburgerMenuPage.objProfileIconInProfilePage, "Profile Icon") == false) {
			logger.info("User is navigated to previous page");
			extent.extentLogger("Navigation", "User is navigated to previous page");
		}
		click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconInProfilePage,
				"profile icon in My profile dropdown");

		verifyElementExist(PWAHamburgerMenuPage.objMyAccountOptionsText, "My profile page");
		verifyElementExist(PWAHamburgerMenuPage.objUserNameInMyProfileWeb, "User name");
		verifyElementExist(PWAHamburgerMenuPage.objProfilePageUserIdTxt, "User id");
		verifyElementExist(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
		NavigationsToMyWatchlist();
		NavigationsToMyReminders();
		NavigationsToMySubscriptionWeb();
		NavigationsToMyTransactionsWeb();
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileText, "profile text");
		extent.HeaderChildNode("Edit page Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objEditProfileTextWEB, "edit profile page");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileFirstName, "Name field");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileEmailField, "Email field");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileMobileNumber, "Mobile field");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileGender, "Gender field");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileDOB, "Date of birth field");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Go back button");
		verifyElementExist(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes Button");
		getWebDriver().findElement(PWAHamburgerMenuPage.objEditProfileFirstName).clear();
		type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5Igs", "First name");
		waitTime(3000);
		String firstName = findElement(PWAHamburgerMenuPage.objEditProfileFirstName).getAttribute("value");
		System.out.println(firstName);
		if (firstName.contains("Zee5")) {
			logger.info("User can edit in Edit profile screen");
			extent.extentLogger("Edit", "User can edit in Edit profile screen");
		} else {
			logger.info("User edit functionality in Edit profile screen failed");
			extent.extentLoggerFail("Edit", "User edit functionality in Edit profile screen failed");
		}
		click(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes Button");
		try {
			Boolean SavedChangesToastMessage = getWebDriver().getPageSource().contains("//*[@class='toastMessage']");
			if (SavedChangesToastMessage == true) {
				extent.extentLogger("Toast", "Saved Changes Toast Message displayed");
				logger.info("Saved Changes Toast Message displayed");
			} else {
				extent.extentLogger("Toast", "Saved Changes Toast Message not displayed");
				logger.info("Saved Changes Toast Message not displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}
		waitTime(2000);
		click(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Go back button");
//--------------------------------------------------------------------------------------------------------
		extent.HeaderChildNode("Change password page Functionality");
		waitTime(2000);
		click(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		waitTime(2000);
		verifyElementExist(PWAHamburgerMenuPage.objChangePasswordTextWEB, "change password page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Go back button");
		waitTime(2000);
		if (verifyElementExist(PWAHamburgerMenuPage.objMyAccountOptionsText, "My account")) {
			logger.info("User is navigated back to my profile page");
			extent.extentLogger("My profile", "User is navigated back to my profile page");
		}
		click(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		waitTime(2000);
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordTextWEB, "change password page");
		type(PWAHamburgerMenuPage.objChangeOldPassword, "User@123", "Current password field");
		String password = getText(PWAHamburgerMenuPage.objChangeOldPassword);
		if (password != null) {
			logger.info("User is able to enter numbers and special character");
			extent.extentLogger("Password", "User is able to enter numbers and special character");
		}
		type(PWAHamburgerMenuPage.objNewPassword, "abc", "password field");
		click(PWAHamburgerMenuPage.objUpdatePasswordButton, "update button");
		if (verifyElementExist(PWAHamburgerMenuPage.objPasswordErrorText, "Error text")) {
			logger.info("Minimun 6 characters error message is displayed");
			extent.extentLogger("Error message", "Minimun 6 characters error message is displayed");
		}
		if (getWebDriver().findElement(PWAHamburgerMenuPage.objUpdatePasswordButton).isEnabled() == false) {
			logger.info("Updated button is not enabled when password field is empty");
			extent.extentLogger("Update button", "Updated button is not enabled when password field is empty");
		}
		getWebDriver().findElement(PWAHamburgerMenuPage.objNewPassword).clear();
		waitTime(3000);
		type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "password field");
		type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
		if (getWebDriver().findElement(PWAHamburgerMenuPage.objUpdatePasswordButton).isEnabled() == true) {
			logger.info("Updated button is enabled when password field is not empty");
			extent.extentLogger("Update button", "Updated button is enabled when password field is not empty");
		}
		click(PWAHomePage.objZeeLogo, "zee logo");
		logout();
	}

//	/**
//	 * Function for Navigation to MyWatchlist .
//	 */
//	public void NavigationsToMyWatchlist() throws Exception {
//		extent.HeaderChildNode("My Watchlist");
//		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Watchlist"), "My watchlist");
//		waitTime(2000);
//		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Watchlist");
//		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
//	}
//
//	/**
//	 * Function for Navigation to MyReminders .
//	 */
//	public void NavigationsToMyReminders() throws Exception {
//		extent.HeaderChildNode("My Reminders");
//		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Reminders"), "My Reminders");
//		waitTime(2000);
//		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My reminders");
//		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
//	}

	/**
	 * Function for Navigation to MySubscription.
	 */
	public void NavigationsToMySubscriptionWeb() throws Exception {
		extent.HeaderChildNode("My subscriptions");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");
		waitTime(2000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Subscriptions page");
		if (verifyElementExist(PWAHamburgerMenuPage.objSubscribitionPageActivePlan, "My Subscription")) {
			logger.info("Subscription plan is displayed");
			extent.extentLogger("Plan", "Subscription plan is displayed");
		}
		if (verifyElementExist(PWAHamburgerMenuPage.objNoTranscationText, "No Transaction")) {
			logger.info("No Active plan is displayed");
			extent.extentLogger("Plan", "No Active plan is displayed");
		}

		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyTransaction.
	 */
	public void NavigationsToMyTransactionsWeb() throws Exception {
		extent.HeaderChildNode("My Transactions");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Transactions"), "My Transactions");
		waitTime(2000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText, "My Transactions");
		if (verifyElementExist(PWAHamburgerMenuPage.objTransactionPageGrid, "Transaction")) {
			logger.info("Transaction details is displayed");
			extent.extentLogger("Transaction", "Transaction details is displayed");
		}
		if (verifyElementExist(PWAHamburgerMenuPage.objNoTranscationText, "Transaction")) {
			logger.info("No transaction text is displayed");
			extent.extentLogger("Transaction", "No transaction text is displayed");
		}
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * ================================BASAVARAJ TIMED
	 * ANCHORS==================================
	 * 
	 */

	public void TimedAnchors(String userType) throws Exception {

		extent.HeaderChildNode("Timed Anchors Functionality");
		extent.extentLogger("Timed Anchors", "Timed Anchors Functionality");
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(3000);
		String currenturl = getWebDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=60";
		getWebDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);

//		getWebDriver().get(URL + "kids/kids-movies/bablu-dablu-robo-rumble/0-0-54219?t=60");
//		extent.extentLogger("player", "Playing Free Content");
		waitTime(10000);

		// waitForPlayerLoaderToComplete();

		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();

			}
		}

		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAPlayerPage.objCompleteProfile, "Complete Profile popup ")) {
				click(PWAPlayerPage.objCompleteProfileCloseIcon, "Complete Profile Pop up close button");

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}

		waitForPlayerAdToComplete("Video Player");
		System.out.println("pause");

		if (BROWSER.equals("Chrome")) {
			pausePlayer();
		} else {
			firefoxpause();
		}

		String currentDuration = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time: " + currentDuration);
		extent.extentLogger("time", "Current time: " + currentDuration);
		List<WebElement> anchors = getWebDriver().findElements(By.xpath("(//div[@playermarkertag='timer']//div)"));
		// Verify whether important segments are marked in player for Logged In user
		if (verifyElementExist(PWAPlayerPage.objtimedanchors, "TimedAnchor")) {
			System.out.println("TimedAnchor Present");
			extent.extentLogger("", "TimedAnchor Present");
			System.out.println("Number of Timed Anchors segments present :" + anchors.size());
			extent.extentLogger("", "Number of Timed Anchors segments present :" + anchors.size());
			if (anchors.size() >= 2) {

				click(PWAPlayerPage.objtimedAnchor(2), "Inividual timed anchor");
				Thread.sleep(7000);
				System.out.println("Clicked on Timed Anchor at position : "
						+ getAttributValue("style", PWAPlayerPage.objtimedAnchor(2)));
				extent.extentLogger("", "Clicked on Timed Anchor at position : "
						+ getAttributValue("style", PWAPlayerPage.objtimedAnchor(2)));
				String currentTime1 = getText(PWAPlayerPage.objcurrenttime);
				System.out.println(
						"Current time after clicking on the TimedAnchor :" + getText(PWAPlayerPage.objcurrenttime));
				extent.extentLogger("",
						"Current time after clicking on the TimedAnchor :" + getText(PWAPlayerPage.objcurrenttime));
				if (!getAttributValue("style", PWAPlayerPage.objtimedAnchor(1))
						.equals(getAttributValue("style", PWAPlayerPage.objtimedAnchor(2)))) {
					System.out.println("Navigated to the TimeAnchor ");
					extent.extentLogger("Navigated to the TimeAnchor ", "Navigated to the TimeAnchor ");
				} else {
					System.out.println("Not Navigated to the TimeAnchor ");
					extent.extentLoggerFail("Not Navigated to the TimeAnchor ", "Not Navigated to the TimeAnchor ");
				}

				System.out.println(getText(PWAPlayerPage.objcurrenttime));

				if (!currentDuration.equals(currentTime1)) {
					System.out.println("Clicked on TimedAnchor and Time is changed");
					extent.extentLogger("Clicked on TimedAnchor and Time is changed",
							"Clicked on TimedAnchor and Time is changed");
				} else {
					System.out.println("Clicked on TimedAnchor and Time is not changed");
					extent.extentLoggerFail("Clicked on TimedAnchor and Time is not changed",
							"Clicked on TimedAnchor and Time is not changed");
				}

				waitForPlayerAdToComplete("Video Player");
				System.out.println("pause");

				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}

				click(PWAPlayerPage.objPlayerPlay, "Play btn");

				waitForPlayerAdToComplete("Video Player");

				if (AdValue.equals("AdPlayed")) {
					System.out.println("Ad Played as it was present before TimedAnchor");
					extent.extentLogger("Ad Played as it was present before TimedAnchor",
							"Ad Played as it was present before TimedAnchor");
				} else {
					System.out.println("Ad not played as it was not present before TimedAnchor");
					extent.extentLogger("Ad not played as it was not present before TimedAnchor",
							"Ad not played as it was not present before TimedAnchor");
				}

				System.out.println("pause");
				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}

				if (!currentTime1.equals(getText(PWAPlayerPage.objcurrenttime))) {
					System.out.println("Content Played after click on the TimedAnchor");
					extent.extentLogger("Content Played after click on the TimedAnchor",
							"Content Played after click on the TimedAnchor");
				} else {
					System.out.println("Content not Played after click on the TimedAnchor");
					extent.extentLoggerFail("Content not Played after click on the TimedAnchor",
							"Content not Played after click on the TimedAnchor");
				}
			}
		} else {
			System.out.println("TimedAnchor is not present");
			extent.extentLogger("", "TimedAnchor is not present");
		}

	}

	public void ShowsTimeperiodProvided(String userType) throws Exception {
		/*
		 * Verify whether playback for content(wrt shows) starts playing based on the
		 * timeperiod provided in the URL
		 */
		extent.HeaderChildNode(
				"Verify whether playback for content(wrt shows) starts playing based on the timeperiod provided in the URL");
		int timeperiod = 35;
		mandatoryRegistrationPopUp(userType);
		getWebDriver().get(URL);
//		getWebDriver().get(
//				"https://newpwa.zee5.com/tvshows/details/paaru/0-6-1179/paarvathi-kisses-aditya-paaru/0-1-manual_1dr9c5e034t0?t="
//						+ timeperiod + "");

		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorEpisode");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		//hideKeyboard();
		waitTime(5000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(3000);
		String currenturl = getWebDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=" + timeperiod;
		getWebDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);

		if (BROWSER.equals("Chrome")) {

		} else {
			Thread.sleep(10000);
			Thread.sleep(10000);
			Thread.sleep(10000);

		}

		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).build().perform();
			}
		}

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();

				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}

		waitForPlayerAdToComplete("Video Player");
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");

				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();

				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		if (BROWSER.equals("Chrome")) {
			pausePlayer();
		} else {
			firefoxpause();
		}
		Thread.sleep(6000);

		PresentTitle = getWebDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)")).getText();

		System.out.println("Show Title : " + PresentTitle);
		String currentDuration1 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time after appending timedperiod in URL : " + currentDuration1);

		if (timeToSec(currentDuration1) >= timeperiod) {
			System.out.println("Playback started from Appended time");
			extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
		} else {
			System.out.println("Playback not started from Appended time.");
			extent.extentLoggerFail("Playback not started from Appended time",
					"Playback not started from Appended time");
		}
	}

	public void musicTimeperiodProvided(String userType) throws Exception {
		/*
		 * // Verify whether playback for content(wrt music and music-video) starts
		 * playing based on the timeperiod provided in the URL
		 */
		extent.HeaderChildNode(
				"Verify whether playback for content(wrt music and music-video) starts playing based on the timeperiod provided in the URL");
		int timeperiod2 = 25;
		mandatoryRegistrationPopUp(userType);
		getWebDriver().get(URL);
//		getWebDriver().get(
//				"https://newpwa.zee5.com/music-videos/details/appa-lyrical-punith-shetty/0-0-manual_15l5jn9il6o8?t="
//						+ timeperiod2 + "");

		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorMusic");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		//hideKeyboard();
		waitTime(4000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(3000);
		String currenturl = getWebDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=" + timeperiod2;
		getWebDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);

		if (BROWSER.equals("Chrome")) {

		} else {
			Thread.sleep(10000);
			Thread.sleep(10000);
			Thread.sleep(10000);

		}

		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}

		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAPlayerPage.objCompleteProfile, "Complete Profile popup ")) {
				click(PWAPlayerPage.objCompleteProfileCloseIcon, "Complete Profile Pop up close button");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}

		waitForPlayerAdToComplete("Video Player");
//		if (userType.equals("Guest")) {
//			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
//				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
//			}
//		}
//		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
//				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
//			} else {
//				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
//			}
//		}
		if (BROWSER.equals("Chrome")) {
			pausePlayer();
		} else {
			firefoxpause();

		}
		Thread.sleep(6000);

		PresentTitle = getWebDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)")).getText();

		System.out.println("Show Title : " + PresentTitle);
		extent.extentLogger("", "Show Title : " + PresentTitle);
		String currentDuration2 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time after appending timedperiod in URL : " + currentDuration2);
		extent.extentLogger("", "Current time after appending timedperiod in URL : " + currentDuration2);

		if (timeToSec(currentDuration2) >= timeperiod2) {
			System.out.println("Playback started from Appended time");
			extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
		} else {
			System.out.println("Playback not started from Appended time.");
			extent.extentLoggerFail("Playback not started from Appended time",
					"Playback not started from Appended time");
		}
	}

	public void moviesTimeperiodProvided(String userType) throws Exception {
		/*
		 * // Verify whether playback for content(wrt movies) starts playing based on
		 * the timeperiod provided in the URL
		 */
		extent.HeaderChildNode(
				"Verify whether playback for content(wrt movies) starts playing based on the timeperiod provided in the URL");
		int timeperiod3 = 120;
		mandatoryRegistrationPopUp(userType);
//		getWebDriver().get(URL);

		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorMovie");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		//hideKeyboard();
		waitTime(4000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(3000);

		String currenturl = getWebDriver().getCurrentUrl();
		String timedurl = currenturl + "?t=" + timeperiod3;
		getWebDriver().get(timedurl);
		System.out.println("Hit URL : " + timedurl);
		extent.extentLogger("", "Hit URL : " + timedurl);

		// getWebDriver().get(URL +
		// "kids/kids-movies/bablu-dablu-robo-rumble/0-0-54219?t=" + timeperiod3 + "");
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}

		waitForPlayerAdToComplete("Video Player");
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		if (BROWSER.equals("Chrome")) {
			pausePlayer();
		} else {
			firefoxpause();
		}
		Thread.sleep(3000);

		String PresentTitle = getWebDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)"))
				.getText();
		extent.extentLogger("", "Present Title : " + PresentTitle);
		String currentDuration3 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time after appending timedperiod in URL : " + currentDuration3);
		extent.extentLogger("", "Current time after appending timedperiod in URL : " + currentDuration3);
		if (timeToSec(currentDuration3) >= timeperiod3) {
			System.out.println("Playback started from Appended time");
			extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
		} else {
			System.out.println("Playback not started from Appended time.");
			extent.extentLogger("Playback not started from Appended time", "Playback not started from Appended time");
		}

		Thread.sleep(3000);

		// append max time
		getWebDriver().get(URL + "kids/kids-movies/bablu-dablu-robo-rumble/0-0-54219?t=9000");
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}
		waitForPlayerAdToComplete("Video Player");
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
				actions.moveToElement(menuOption).perform();
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAPlayerPage.objSubscribeNowLink, "Subscriptionlink")) {
				System.out.println("Subscribe now link");
			}
		} else {
			if (BROWSER.equals("Chrome")) {
				pausePlayer();
			} else {
				firefoxpause();
			}
		}

		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
				extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
			} else {
				extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
			}
		}
		Thread.sleep(3000);
		String presentTitle2 = getWebDriver().findElement(By.xpath("(//div[@class='consumptionMetaDiv']//h1)"))
				.getText();
		extent.extentLogger("", "Naviagted to : " + presentTitle2);

		if (!PresentTitle.equals(presentTitle2)) {
			System.out.println("Navigated to other Player as the time appended is max ");
			extent.extentLogger("Navigated to other Player as the time appended is max ",
					"Navigated to other Player as the time appended is max ");
		} else {
			System.out.println("Not Navigated to other Player");
			extent.extentLogger("Not Navigated to other Player", "Not Navigated to other Player");
		}
		getWebDriver().get(URL + "kids/kids-movies/bablu-dablu-robo-rumble/0-0-54219?t=60");
		Thread.sleep(5000);
	}

	public void continueWatchingtrayData(String userType) {
		/*
		 * //Verifying ContinueWatching Tray Data
		 */
		extent.HeaderChildNode("Verifying ContinueWatching Tray Data");
		mandatoryRegistrationPopUp(userType);
		getWebDriver().get(URL);

		if (userType.equals("SubscribedUser") || userType.equals("NonSubscribedUser")) {
			try {
				ScrollToTheElementWEB(By.xpath("//div[@class='trayHeader']//h2[contains(text(),'Continue Watching')]"));

				Thread.sleep(2000);
				click(PWAHomePage.objspecificTumbnail2("Continue Watching", 1), "Continue watching content");

				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
					Actions actions = new Actions(getWebDriver());
					WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
					actions.moveToElement(menuOption).perform();
				} else {
					extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
				}

				waitForPlayerAdToComplete("Video Player");

				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}

				String currentDuration4 = getText(PWAPlayerPage.objcurrenttime);
				System.out.println("Current time  : " + currentDuration4);
				extent.extentLogger("", "Current time  : " + currentDuration4);
				String getUrl = getWebDriver().getCurrentUrl();

				int timeperiod4 = 120;
				String modifiedURL = getUrl + "?t=" + timeperiod4 + "";

				getWebDriver().get(modifiedURL);
//			if(userType.equals("NonSubscribedUser")){
//				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
//					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//					extent.extentLogger("GetPremium is displayed", "GetPremium is displayed");
//				} else {
//					extent.extentLogger("GetPremium is not displayed", "GetPremium is not displayed");
//				}
//				
//			}
				waitForPlayerAdToComplete("Video Player");
				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}

				String currentDuration5 = getText(PWAPlayerPage.objcurrenttime);
				System.out.println("Current time after appending timedperiod in URL : " + currentDuration5);
				extent.extentLogger("", "Current time after appending timedperiod in URL : " + currentDuration5);
				if (timeToSec(currentDuration5) >= timeperiod4) {
					System.out.println("Playback started from Appended time");
					extent.extentLogger("Playback started from Appended time", "Playback started from Appended time");
				} else {
					System.out.println("Playback not started from Appended time.");
					extent.extentLoggerFail("Playback not started from Appended time",
							"Playback not started from Appended time");
				}

			} catch (Exception e) {
				System.out.println("No Continue Watching Tray");
			}
		} else {
			extent.extentLogger("", "Its Guest User, So no Continue Watching tray will be displayed");
		}
	}

	/**
	 * ================================SHREENIDHI Menu &
	 * Settings==================================
	 * 
	 */

	public void MenuOrSettingScenarios(String UserType) throws Exception {

		switch (UserType) {
		case "Guest":
			extent.HeaderChildNode("Guest user scenario");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			verificationsOfExploreOptions();
			navigationsFromPlanSectionWeb();
			resetToDefault();
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("NonSubscribedUser scenario");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			resetToDefault();
			parentControlFunctionality("Non-Subscribed");
			authenticationFunctionality();
			verificationsOfExploreOptions();
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("SubscribedUser scenario");
			extent.extentLogger("Accessing as SubscribedUser User", "Accessing as SubscribedUser User");
			logger.info("Accessing as SubscribedUser User");
			verificationsOfExploreOptions();
			resetToDefault();
			parentControlFunctionality("Subscribed");
			authenticationFunctionality();
		}
	}

	public void verificationsOfExploreOptions() throws Exception {
		extent.HeaderChildNode("Verifications of Explore dropdown options in Hamburger Menu");
		waitTime(5000);
		scrollDownWEB();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if (verifyElementExist(PWAHamburgerMenuPage.objExploreBtn, "Explore option")) {
			click(PWAHamburgerMenuPage.objExploreBtn, "Explore option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Premium"), "Premium option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Shows"), "Shows option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Movies"), "Movies option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Kids"), "Kids option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("News"), "News option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Music"), "Music option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Free Movies"), "Free Movies option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Live TV"), "LiveTv option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions(" ZEE5 Originals"), " ZEE5 Originals option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Club"), "Club option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Play"), "Play option");
			verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Stories"), "Stories option");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		}
	}

	public void navigationsFromPlanSectionWeb() throws Exception {
		extent.HeaderChildNode("Functionality of MyPlan options in Hamburger Menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementExist(PWAHamburgerMenuPage.objPlanInHamburger, "Plan option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPlanInsideItemsBtn("Buy Subscription"),
				"Buy Subscription option in Plan section");
		waitTime(3000);
		if (verifyElementExist(PWASubscriptionPages.objZEE5Subscription, "Subscription")) {
			logger.info("User is navigated to subscription page");
			extent.extentLogger("subscription page", "User is navigated to subscription page");
			click(PWAHomePage.objZeeLogo, "zee logo");
			waitTime(4000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPlanInsideItemsBtn("Have a prepaid code ?"),
					"Have a prepaid code ? option in Plan section");
			waitTime(3000);
			if (verifyElementExist(PWASubscriptionPages.objZEE5Subscription, "Subscription")) {
				logger.info("User is navigated to subscription page");
				extent.extentLogger("subscription page", "User is navigated to subscription page");
				click(PWAHomePage.objZeeLogo, "zee logo");
				if (verifyElementExist(PWAHomePage.objSubscripePopupHomePage, "Pop up")) {
					logger.info("Subscribe popup in home page is dislayed");
					extent.extentLogger("Popup", "Subscribe popup in home page is dislayed");
					waitTime(3000);
					click(PWAHomePage.objSubscripePopupCloseButtonHomePage, "Close button in popup");
				}
			}
		}

	}

	public void resetToDefault() throws Exception {
		extent.HeaderChildNode("Reset Settings to default Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInHamburger,
				"More settings in settings section");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, "Language button");
		waitTime(2000);
		click(PWAHamburgerMenuPage.objSelectLanguage, "Language icon");
		if (verifyElementExist(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Language")) {
			logger.info("clicked on hindi language in Display language popup");
			extent.extentLogger("Content language", "clicked on hindi language in Display language popup");
		}
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, "Language button");
		if (verifyElementExist(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Hindi Selected Language") == false) {
			logger.info("Reset to default is success");
			extent.extentLogger("Content language", "Reset to default is success");
			click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
			click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
		} else {
			logger.info("Reset to defualt was unsuccessfull");
			extent.extentLogger("Reset to defualt", "Reset to defualt was unsuccessfull");
		}
	}

	public void parentControlFunctionality(String UserType) throws Exception {
		extent.HeaderChildNode("Parent Control Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
		verifyElementExist(PWALoginPage.objPasswordField, "password field");
		String password = "";
		if (UserType.equals("Non-Subscribed")) {
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("NonsubscribedPassword");
		} else if (UserType.equals("Subscribed")) {
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("SubscribedPassword");
		}
		type(PWALoginPage.objPasswordField, password, "Password field");
		click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
		waitTime(2000);
		verifyElementExist(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
		verifyElementExist(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restrict all option");
		verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
		type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
		type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
		type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
		type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
		waitTime(4000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
		waitTime(2000);
		verifyElementExist(PWAHomePage.objZeeLogo, "zee logo");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		verifyElementExist(PWAHomePage.objSearchField, "Search field");
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("freeMovie2");
		type(PWAHomePage.objSearchField, keyword, "Search");
		waitTime(15000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search content");
		waitTime(5000);
		if (verifyElementExist(PWAHamburgerMenuPage.objParentalLockPopUpInPlayer, "Parent control Popup")) {
			type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
		}
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		if (verifyElementExist(PWAPlayerPage.playBtn, "Pause icon")) {
			logger.info("Playback is played after entering parental lock");
			extent.extentLogger("Playback", "Playback is played after entering parental lock");
		} else {
			logger.info("Playback is not started after entering parental lock");
			extent.extentLogger("Playback", "Playback is not started after entering parental lock");
		}
		click(PWAHomePage.objZeeLogo, "zee logo");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
		verifyElementExist(PWALoginPage.objPasswordField, "password field");
		type(PWALoginPage.objPasswordField, password, "Password field");
		waitTime(2000);
		click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
		waitTime(2000);
		verifyElementExist(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
		click(PWAHamburgerMenuPage.objParentalLockNoRestrictionOption, "No restriction option");
		verifyElementExist(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
	}

	public void authenticationFunctionality() throws Exception {
		extent.HeaderChildNode("Authentication Functionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAuthenticationOption, "Authentication option");
		waitTime(3000);
		verifyElementExist(PWAHamburgerMenuPage.objAuthenticationText, "Authentication Page");
		if (getWebDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled() == false) {
			logger.info("Authenticate button is not highlighted by default");
			extent.extentLogger("Authenticate", "Authenticate button is not highlighted by default");
		}
		type(PWAHamburgerMenuPage.objAuthenticationField, "abcdef", "AuthenticationField");
		String AuthenticationField = getText(PWAHamburgerMenuPage.objAuthenticationField);
		if (AuthenticationField != null) {
			logger.info("User is able to enter the value in AuthenticationField");
			extent.extentLogger("AuthenticationField", "User is able to enter the value in AuthenticationField");
		}

		if (getWebDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled()) {
			logger.info("Authenticate button is highlighted after entering the input in AuthenticationField");
			extent.extentLogger("Authenticate",
					"Authenticate button is highlighted after entering the input in AuthenticationField");
		}

		click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
		try {
			Boolean ExpiredToastMessage = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']"))
					.isDisplayed();
			if (ExpiredToastMessage == true) {
				extent.extentLogger("Toast", "Expired Toast message displayed");
				logger.info("Expired Toast message displayed");
			} else {
				extent.extentLogger("Toast", "Expired Toast message not displayed");
				logger.info("Expired Toast message not displayed");
			}
			int lenText = findElement(PWAHamburgerMenuPage.objAuthenticationField).getAttribute("value").length();
			for (int i = 0; i < lenText; i++) {
				getWebDriver().findElement(PWAHamburgerMenuPage.objAuthenticationField).sendKeys(Keys.BACK_SPACE);
			}
			waitTime(2000);
			type(PWAHamburgerMenuPage.objAuthenticationField, "&!@#$%", "AuthenticationField");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			Boolean NotfounfToastMessage = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']"))
					.isDisplayed();
			if (NotfounfToastMessage == true) {
				extent.extentLogger("Toast", "Not found Toast message displayed");
				logger.info("Not found Toast message displayed");
			} else {
				extent.extentLogger("Toast", "Not found Toast message not displayed");
				logger.info("Not found Toast message not displayed");
			}
		} catch (Exception e) {
			logger.info("Toast message is not displayed");
		}
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
	}

	/**
	 * ================================SUSHMA
	 * Onboarding==================================
	 * 
	 */

	public void phoneNumberRegistration() throws Exception {
		extent.HeaderChildNode(
				"verifing that user is able to enter Mobile number, Password, date of birth, gender in Registration page");
		click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
		waitForElementDisplayed(PWALoginPage.objSignUpHeaderInSignUpPageWeb, 10);
		verifyElementExist(PWALoginPage.objSignUpHeaderInSignUpPageWeb, "SignUp Page");
		waitForElementDisplayed(PWALoginPage.objEmailField, 5);
		verifyElementExist(PWALoginPage.objEmailField, "Email/PhoneNo Field");
		type(PWALoginPage.objEmailField, "7892215", "PhoneNumber Field");
		String PhoneNumberField = getText(PWALoginPage.objEmailField);
		if (PhoneNumberField != null) {
			logger.info("User is allowed to enter PhoneNumber");
			extentLogger("PhoneNumber", "User is allowed to enter PhoneNumber in PhoneNumber Field");
		}
		verifyElementExist(PWALoginPage.objIncorrectPhoneNumberMessage,
				"When User Enter Invalid PhoneNumber, Error Message");
		type(PWALoginPage.objEmailField, "214", "PhoneNumber Field");
		if (verifyElementExist(PWALoginPage.objIncorrectPhoneNumberMessage, "PhoneNumber Error Message") == false) {
			logger.info("User is allowed to enter valid PhoneNumber");
			extent.extentLogger("PhoneNumber", "User is allowed to enter valid PhoneNumber");
		}
		verifyElementExist(PWALoginPage.objCountryCode, "Country code field");
		click(PWALoginPage.objCountryCode, "Country code field");
		verifyElementExist(PWALoginPage.objCountryCodeDropDown, "Drop down of country code");
		click(PWALoginPage.objCountryCodeAlgeria, "Algeria country code");
		click(PWALoginPage.objCountryCode, "Country code field");
		click(PWALoginPage.objCountryCodeAndoora, "Andoora country code");
		click(PWALoginPage.objCountryCode, "Country code field");
		click(PWALoginPage.objCountryCodeIndia, "India country code");
		type(PWALoginPage.objPasswordField, "Igsindia123", "Password Field");
		calenderFunctionality();
		String SelectedDate = getText(PWALoginPage.objDateOfBirthField);
		if (SelectedDate != null) {
			logger.info("Value in date of birth field is entered correctly");
			extent.extentLogger("DateOfField", "Value in date of birth field is entered correctly");
		}
		if (getWebDriver().findElement(PWASignupPage.objSignUpButtonNotHighlightedWeb).isEnabled() == false) {
			logger.info("SignUp button is not highlighted when user does not enter details in all field");
			extent.extentLogger("Continue button",
					"SignUp button is not highlighted when user does not enter details in all field");
		}
		click(PWASignupPage.objGenderMaleBtn, "Gender Option");
//			if(verifyElementExist(PWAHomePage.objWhatWonderingPopUp, "Wondering popUp"))
//			{
//				waitTime(3000);
//				click(PWAHomePage.objWhatWonderingPopUpCloseIcon, "Close icon");
//			}
		if (getWebDriver().findElement(PWASignupPage.objSignUpButtonHighlightedWeb).isEnabled()) {
			logger.info("SignUp button is highlighted");
			extent.extentLogger("Continue button", "SignUp button is highlighted");
		}
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "SignUp Button");
		extent.HeaderChildNode(
				"Verifing that user is allowed to update the mobile number, password, date of birth and gender post navigating back from change number button");
		waitTime(10000);
		click(PWASignupPage.objChangeNumberLink, "Change number link");
		waitForElementDisplayed(PWALoginPage.objSignUpHeaderInSignUpPageWeb, 5);
		verifyElementExist(PWALoginPage.objSignUpHeaderInSignUpPageWeb, "SignUp Page");
		type(PWALoginPage.objEmailField, "7892215214", "PhoneNumber Field");
		type(PWALoginPage.objPasswordField, "Igsindia123", "Password Field");
		calenderFunctionality();
		click(PWASignupPage.objGenderMaleBtn, "Gender Option");
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "Continue Button");
		extent.HeaderChildNode("verifing OTP Screen");
		waitForElementDisplayed(PWASignupPage.objOTPTimer, 5);
		verifyElementExist(PWASignupPage.objOTPTimer, "OTP timer");
		String otpTimer1 = getText(PWASignupPage.objOTPTimer);
		String OtpTimer1 = otpTimer1.substring(3);
		int otp1 = Integer.parseInt(OtpTimer1);
		System.out.println(otp1);
		waitTime(6000);
		String otpTimer2 = getText(PWASignupPage.objOTPTimer);
		String OtpTimer2 = otpTimer2.substring(3);
		int otp2 = Integer.parseInt(OtpTimer2);
		System.out.println(otp2);
		if (otp1 < otp2) {
			logger.info("The Otp timer is in reverse order");
			extentLogger("OtpTimer", "The Otp timer is in reverse order");
		}
		waitTime(60000);
		if (verifyElementPresent(PWASignupPage.objResendOtpOption, "Resend button")) {
			logger.info("ResendOtp option is active after 60seconds");
			extent.extentLogger("ResendOtp", "ResendOtp option is active after 60seconds");
		}
		try {
			String OTP = GmailInbox.readEmail(OTPNotification());
			System.out.println(OTP.length());
			logger.info("OTP is recieved from Telecom service");
			extent.extentLogger("Service provider", "OTP is recieved from Telecom service");
			Response regionResponse = given().urlEncodingEnabled(false).when().get("https://xtra.zee5.com/country");
			String region = regionResponse.getBody().jsonPath().getString("state");
			logger.info("Location :" + region);
			extentLogger("Location", "Location :" + region);
			char Otp1 = OTP.charAt(13);
			char Otp2 = OTP.charAt(14);
			char Otp3 = OTP.charAt(15);
			char Otp4 = OTP.charAt(16);
			String OTP1 = Character.toString(Otp1);
			String OTP2 = Character.toString(Otp2);
			String OTP3 = Character.toString(Otp3);
			String OTP4 = Character.toString(Otp4);
			System.out.println("OTP is" + " " + OTP1 + OTP2 + OTP3 + OTP4);
			type(PWASignupPage.objOTP1, OTP1, "OTP box1");
			type(PWASignupPage.objOTP2, OTP2, "OTP box2");
			type(PWASignupPage.objOTP3, OTP3, "OTP box3");
			type(PWASignupPage.objOTP4, OTP4, "OTP box4");
			// hideKeyboard();
			if (getDriver().findElement(PWASignupPage.objSignUpButtonHighlighted).isEnabled() == true) {
				logger.info("Verify Button is highlighted");
				extent.extentLogger("Verify", "Verify Button is highlighted");
				getDriver().findElement(PWASignupPage.objOTP1).clear();
				getDriver().findElement(PWASignupPage.objOTP2).clear();
				getDriver().findElement(PWASignupPage.objOTP3).clear();
				getDriver().findElement(PWASignupPage.objOTP4).clear();
				waitTime(5000);
			}
		} catch (Exception e) {
			System.out.println("OTP is not recieved");
			Response regionResponse = given().urlEncodingEnabled(false).when().get("https://xtra.zee5.com/country");
			String region = regionResponse.getBody().jsonPath().getString("state");
			logger.info("Location :" + region);
			extentLogger("Location", "Location :" + region);
		}
		type(PWASignupPage.objOTP1, "a", "OTP box1");
		type(PWASignupPage.objOTP2, "b", "OTP box2");
		type(PWASignupPage.objOTP3, "c", "OTP box3");
		type(PWASignupPage.objOTP4, "d", "OTP box4");
		// hideKeyboard();
		waitTime(2000);
		if (getWebDriver().findElement(PWASignupPage.objSignUpButtonHighlighted).isEnabled() == false) {
			logger.info("Verify Button is not highlighted when user enter non numeric value in otp section");
			extent.extentLogger("Verify",
					"Verify Button is not highlighted when user enter non numeric value in otp section");
		}
		type(PWASignupPage.objOTP1, "1", "OTP box1");
		type(PWASignupPage.objOTP2, "2", "OTP box2");
		type(PWASignupPage.objOTP3, "3", "OTP box3");
		type(PWASignupPage.objOTP4, "4", "OTP box4");
		// hideKeyboard();
		waitTime(3000);
		if (getWebDriver().findElement(PWASignupPage.objVerifyBtnWeb).isEnabled() == true) {
			logger.info("Verify Button is highlighted");
			extent.extentLogger("Verify", "Verify Button is highlighted");
			verifyElementPresentAndClick(PWASignupPage.objVerifyBtnWeb, "Verified Button");
			try {
				Boolean Message = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
				if (Message == true) {
					extent.extentLogger("Toast", "Toast message displayed");
					logger.info("Toast message displayed");
				} else {
					System.out.println("Toast message is not displayed");
				}
			} catch (Exception e) {
				System.out.println("Toast message is not displayed");
			}
		}
		Back(2);
	}

	public void emailRegistration() throws Exception {
		extent.HeaderChildNode(
				"verifing that user is able to enter Email-Id, Password, date of birth, gender in Registration page");
		click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
		waitForElementDisplayed(PWALoginPage.objSignUpHeaderInSignUpPageWeb, 10);
		verifyElementExist(PWALoginPage.objSignUpHeaderInSignUpPageWeb, "SignUp Page");
		verifyElementExist(PWALoginPage.objEmailField, "Email/PhoneNo Field");
		if (verifyElementExist(PWALoginPage.objPasswordField, "Password field") == false) {
			logger.info("Password field is not displayed when email field is empty");
			extent.extentLogger("Password Field", "Password field is not displayed when email field is empty");
		}
		type(PWALoginPage.objEmailField, "zee5latest@gmail.com", "Email field");

		if (verifyElementExist(PWALoginPage.objPasswordField, "Password field") == true) {
			logger.info("Password field is displayed when user enter email-id");
			extent.extentLogger("Password Field", "Password field is displayed when user enter email-id");
		}
		type(PWALoginPage.objPasswordField, "abc", "password field");
		click(PWASignupPage.objSignUpButtonNotHighlightedWeb, "SignUp Button");
		verifyElementExist(PWASignupPage.objPasswordErrorMessage, "Password error message");
		int lenText = findElement(PWALoginPage.objEmailField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getWebDriver().findElement(PWALoginPage.objPasswordField).sendKeys(Keys.BACK_SPACE);
		}
		waitTime(2000);
		type(PWALoginPage.objPasswordField, "user@123", "password field");
		if (verifyElementExist(PWASignupPage.objPasswordHiddenField, "password field")) {
			logger.info("Password field is hidden before tapping on password icon");
			extentLogger("Password", "Password field is hidden before tapping on password icon");
		}
		click(PWASignupPage.objPasswordIcon, "Password icon");
		if (verifyElementExist(PWASignupPage.objPasswordFieldShow, "Password")) {
			logger.info("Password field is shown when user taps on password icon");
			extent.extentLogger("Password", "Password field is shown when user taps on password icon");
		}
		click(PWASignupPage.objPasswordIcon, "Password icon");
		calenderFunctionality();
		String SelectedDate = getText(PWALoginPage.objDateOfBirthField);
		if (SelectedDate != null) {
			logger.info("Value in date of field is entered correctly");
			extent.extentLogger("DateOfField", "Value in date of field is entered correctly");
		}
		click(PWASignupPage.objGenderMaleBtn, "Gender Option");
		if (verifyElementExist(PWASignupPage.objSignUpButtonHighlightedWeb, "SignUp button")) {
			logger.info("SignUp button is highlighted");
			extent.extentLogger("Continue button", "SignUp button is highlighted");
		}
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "SignUp button");
		try {
			Boolean Message = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
			if (Message == true) {
				extent.extentLogger("Toast", "The user could not be found message displayed");
				logger.info("The user could not be found message displayed");
			} else {
				System.out.println("Toast message is not displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}
		Back(1);
		waitTime(5000);
	}

	public void facebookLogin() throws Exception {
		extent.HeaderChildNode("Login through Facebook");
		getWebDriver().get(URL);
		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitForElementDisplayed(PWALoginPage.objLoginPageheader, 10);

		waitForElementDisplayed(PWALoginPage.objFacebookIcon, 10);

		verifyElementExist(PWALoginPage.objGoogleIcon, "Google icon");
		waitTime(1000);
		verifyElementExist(PWALoginPage.objTwitterIcon, "Twitter icon");
		waitTime(1000);
		verifyElementExist(PWALoginPage.objFacebookIcon, "Facebook icon");

		waitTime(10000);
		click(PWALoginPage.objFacebookIcon, "Facebook Icon");
		switchToWindow(2);

		if (verifyElementExist(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");

		}

		else {
			verifyElementExist(PWALoginPage.objFacebookPageVerificationWeb, "Facebook page");
			verifyElementPresent(PWALoginPage.objFacebookLoginEmailWeb, " Email Field");
			type(PWALoginPage.objFacebookLoginEmailWeb, "igstesttt@gmail.com", "Emial Field");
			verifyElementPresent(PWALoginPage.objFacebookLoginpasswordWeb, " Password Field");
			type(PWALoginPage.objFacebookLoginpasswordWeb, "Igs123!@#", "Password Field");
			verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPageWeb, "Login Button");
			switchToWindow(1);
			waitForElementDisplayed(PWALandingPages.objWebProfileIcon, 20);
			if (verifyElementPresent(PWALandingPages.objWebProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}
		}
		logout();
	}

	public void forgotPasswordEmail() throws Exception {
		extent.HeaderChildNode("Verifications in Forgot Password page for Email Id");

		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitTime(5000);
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");

		type(PWALoginPage.objEmailField, "igsss12345igs@gmail.com", "Email field");
		click(PWALoginPage.objForgotPasswordLinkButtonWEB, "Reset password button");
		try {
			Boolean Message = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
			if (Message == true) {
				extent.extentLogger("Toast", "The user could not be found message displayed");
				logger.info("The user could not be found message displayed");
			} else {
				System.out.println("Toast message is not displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}
		waitTime(3000);

		int lenText = findElement(PWALoginPage.objEmailField).getAttribute("value").length();
		for (int i = 0; i < lenText; i++) {
			getWebDriver().findElement(PWALoginPage.objEmailField).sendKeys(Keys.BACK_SPACE);
		}

		waitTime(3000);
		type(PWALoginPage.objEmailField, "Zee5latest@gmail", "Email field");

		if (getWebDriver().findElement(PWALoginPage.objForgotPasswordLinkButtonWEB).isEnabled() == false) {
			logger.info("'Send Password reset link' button is not highlighted when user enters a invalid email id.");
			extent.extentLogger("Invalid Email",
					"'Send Password reset link' button is not highlighted when user enters a invalid email id.");
		}

		type(PWALoginPage.objEmailField, ".com", "Email field");

		if (verifyElementExist(PWALoginPage.objForgotPasswordMessage, "Message")) {
			logger.info("Supporting description is displayed");
			extent.extentLogger("Forgot password", "Supporting description is displayed");
		}

		if (getWebDriver().findElement(PWALoginPage.objForgotPasswordLinkButtonWEB).isEnabled() == true) {
			logger.info("'Send Password reset link' button is highlighted when user enters a valid email id.");
			extent.extentLogger("Valid Email",
					"'Send Password reset link' button is highlighted when user enters a valid email id.");
		}
		click(PWALoginPage.objForgotPasswordLinkButtonWEB, "Reset password button");

		try {
			Boolean Message = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
			if (Message == true) {
				extent.extentLogger("Toast",
						"'Password reset link has been sent to your registered Email ID' message displayed");
				logger.info(" 'Password reset link has been sent to your registered Email ID' message displayed");
			} else {
				System.out.println("Toast message is not displayed");
			}
		} catch (Exception e) {
			System.out.println("Toast message is not displayed");
		}

	}

	public void forgotPasswordMobileNumber() throws Exception {
		extent.HeaderChildNode("Verifications in Forgot Password page for Mobile Number");

		JSClick(PWALoginPage.objLoginBtnWEB, "Login button");

		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		waitTime(2000);
		type(PWALoginPage.objEmailField, "789221", "Email field");
		if (getWebDriver().findElement(PWALoginPage.objForgotPasswordLinkButtonWEB).isEnabled() == false) {
			logger.info("Continue button is not highlighted when user enter invalid mobile number");
			extent.extentLogger("Continue", "Continue button is not highlighted when user enter invalid mobile number");
		}

		type(PWALoginPage.objEmailField, "5214", "Email field");
		if (verifyElementExist(PWALoginPage.objForgotPasswordMessage, "Message")) {
			logger.info("Supporting description is displayed");
			extent.extentLogger("Forgot password", "Supporting description is displayed");
		}
		if (getWebDriver().findElement(PWALoginPage.objForgotPasswordLinkButtonWEB).isEnabled() == true) {
			logger.info("Continue button is not highlighted when user enter invalid mobile number");
			extent.extentLogger("Continue", "Continue button is not highlighted when user enter invalid mobile number");
		}
		click(PWALoginPage.objForgotPasswordLinkButtonWEB, "Continue button");
		waitTime(3000);
		verifyElementExist(PWALoginPage.objNewPasswordField, "New password page");
		type(PWALoginPage.objNewPasswordField, "User@123", "Password field");
		type(PWALoginPage.objConfirmNewPasswordField, "User@123", "Confirm Password field");
		click(PWALoginPage.objSetNewPasswordButton, "Continue button");
		if (verifyElementExist(PWALoginPage.objOTPVerifyPage, "OTP verification page")) {
			logger.info("User is redirected to verify otp page");
			extent.extentLogger("OTP", "User is redirected to verify otp page");
		}
		Back(4);
	}

	public void calenderFunctionality() throws Exception {
		if (verifyElementExist(PWASignupPage.objYearPickerTab, "Year")) {
			click(PWASignupPage.objDayPickerTab, "Day picker");
			click(PWASignupPage.objDayPickerTabValue, "Day picker value");
			click(PWASignupPage.objMonthPickerTab, "Month Picker");
			click(PWASignupPage.objMonthPickerTabValue, "Month Picker value");
			click(PWASignupPage.objYearPickerTab, "Year Picker");
			if (verifyElementExist(PWASignupPage.objYearPickerTabValueNotActive, "Further year") == false) {
				logger.info("User is not allowed to select future date/year in calender tab");
				extent.extentLogger("Calender", "User is not allowed to select future date/year in calender tab");
			}
			click(PWASignupPage.objYearPickerTabValue, "Year Picker value");
		} else {
			click(PWALoginPage.objDateOfBirthField, "Date Of Birth Field");
			verifyElementExist(PWALoginPage.objCalenderPopUp, "Calender PopUp");
			click(PWALoginPage.objSelectDateInCalender, "Date in Calender");
		}
	}

	public void twitterLogin() throws Exception {
		extent.HeaderChildNode("Login through Twitter");

		verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login button");
		waitForElementDisplayed(PWALoginPage.objLoginPageheader, 10);

		waitForElementDisplayed(PWALoginPage.objTwitterIcon, 10);
		verifyElementExist(PWALoginPage.objTwitterIcon, "Twitter icon");
		waitTime(1000);

		click(PWALoginPage.objTwitterIcon, "twitter Icon");
		switchToWindow(2);

		if (verifyElementExist(PWALandingPages.objWebProfileIcon, "Profile icon")) {
			logger.info("User Logged in Successfully");
			extent.extentLogger("Logged in", "User Logged in Successfully");
			logout();

		}

		else {
			verifyElementPresent(PWALoginPage.objTwitterEmaildField, " Email Field");
			type(PWALoginPage.objTwitterEmaildField, "Zee5latest@gmail.com", "Emial Field");

			verifyElementPresent(PWALoginPage.objTwitterPasswordField, " Password Field");
			type(PWALoginPage.objTwitterPasswordField, "User@123", "Password Field");

			verifyElementPresentAndClick(PWALoginPage.objTwitterSignInButton, "Login Button");
			getWebDriver().close();
			switchToParentWindow();
			waitForElementDisplayed(PWALandingPages.objWebProfileIcon, 20);
			if (verifyElementExist(PWALandingPages.objWebProfileIcon, "Profile icon")) {
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
				logout();
			} else {
				logger.info("User is not logged in Successfully");
				extent.extentLoggerFail("Logged in", "User is not logged in Successfully");
				Back(1);
			}
		}

	}

	/**
	 * ================================SUSHMA
	 * LiveTV==================================
	 * 
	 */

	public void liveLandingPage(String userType) throws Exception {
		extent.HeaderChildNode("Verifing whether user is able to navigate Live Tv landing page");
		waitTime(10000);
		navigateToAnyScreenOnWeb("Live TV");
		// waitTime(20000);
		// waitForElementDisplayed(PWAHomePage.objHighlightedTab("Live TV"), 30);
		if (verifyElementPresent(PWAHomePage.objActiveTab, "Live TV")) {
			logger.info("Live Tv tab is highlighted, user is able to navigate Live Tv landing page");
			extent.extentLogger("Live Tv landing page",
					"Live Tv tab is highlighted, user is able to navigate Live Tv landing page");
		}
		extent.HeaderChildNode(
				"Verifing that On tapping of 'Right side bottom arrow' user is navigated to top of screen");
		waitTime(5000);
		Back_TO_TopArrow_Web(userType);
		waitForElementDisplayed(PWALiveTVPage.objLiveTvFilterOption, 5);
		if (verifyElementExist(PWALiveTVPage.objLiveTvFilterOption, "Filter option")) {
			logger.info("On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
			extent.extentLogger("Right side bottom arrow",
					"On tapping of 'Right side bottom arrow' user is navigated to top of screen without scrolling");
		}
//			extent.HeaderChildNode("Verifing that Hamburger menu overlay is displayed on mouse hover on header menu option");
		Actions actions = new Actions(getWebDriver());
//			 WebElement hamburgerMenu = getWebDriver().findElement(PWAHomePage.objHamburgerMenu);
//			 actions.moveToElement(hamburgerMenu).perform();
//			 verifyElementPresent(PWAHamburgerMenuPage.objhamburgerMenuOverlayHomeBtnWeb, "hamburger menu overlay");
		extent.HeaderChildNode("Verifing that on content card after mouse hovor, play, share buttons are displayed");
		WebElement contentcard = getWebDriver().findElement(PWALiveTVPage.objCardTitle);
		actions.moveToElement(contentcard).perform();
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objPlayiconAfterMouseHover, "Play icon");
		verifyElementPresent(PWAHomePage.objShareiconAfterMouseHover, "Share icon");
	}

	public void live() throws Exception {
		extent.HeaderChildNode("verifying that multiple languages are given to select with apply and reset button");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTvFilterOption, "Filter option");
		waitTime(2000);
		List<WebElement> languages = getWebDriver()
				.findElements(By.xpath("//div[contains(@class,'language noSelect')]"));
		languages.size();
		if (languages.size() > 1) {
			logger.info("Multiple languages are given for selection");
			extent.extentLogger("Multiple languages", "Multiple languages are given for selection");
		} else {
			logger.info("Multiple languages are not given for selection");
			extent.extentLogger("Multiple languages", "Multiple languages are not given for selection");
		}
		verifyElementPresent(PWALiveTVPage.objApplyBtn, "Apply button");
		verifyElementPresent(PWALiveTVPage.objResetBtn, "Reset button");
		verifyElementPresentAndClick(PWALiveTVPage.objCloseLanguagePopuUpBtn,
				"Close button of Filter language setting window");
	}

	public void premiumPopUp() throws Exception {
		extent.HeaderChildNode(
				"Verifing that Subscribe now or Login pop is displayed when user click on premium content");
//		while (!(verifyElementExist(PWALiveTVPage.objFirstPremiumCardinTray, "Premium Content"))) {
//			scrollDownWEB();
//		}

		for (int scroll = 0; scroll <= 4; scroll++) {
			if (verifyElementExist(PWALiveTVPage.objFirstPremiumCardinTray, "Premium Content")) {
				break;
			} else
				scrollByWEB();
		}

		click(PWALiveTVPage.objFirstPremiumCardinTray, "Premium Content");
		waitForElementDisplayed(PWAPremiumPage.objPremiumPopUp, 15);
		if (verifyElementExist(PWAPremiumPage.objPremiumPopUp, "Premium PopUp")) {
			verifyElementPresentAndClick(PWAPremiumPage.objClosePremiumPopup, "Premium PopUp Close icon");
			extent.HeaderChildNode("Verifing that premium content videos in landscape mode");
			if (verifyElementExist(PWALiveTVPage.objPlayerInlineSubscriptionLink, "Player inline Subscribtion link")) {
				logger.info(
						"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
				extent.extentLogger("Maximize icon",
						"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
			}
		} else {
			extent.HeaderChildNode("Verifing that premium content videos in landscape mode");
			waitForPlayerAdToComplete2("Video Player");
			waitForElementDisplayed(PWAPlayerPage.objPlayerscreen, 120);
			pause();
			// click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
//				if(verifyElementExist(PWAPlayerPage.objPlayerPause, "Pause icon"))
//				{
//					click(PWAPlayerPage.objPlayerPause, "Pause icon");
//				}
			verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize icon");
			click(PWAPlayerPage.maximizeBtn, "Maximize icon");
			for (int i = 0; i < 5; i++) {
				if (verifyElementExist(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
					logger.info("User is able to watch Premium content in landscape mode");
					extent.extentLogger("Landscape mode", "User is able to watch Premium content in landscape mode");
					break;
				} else {
					click(PWAPlayerPage.objPlayer, "player screen");
				}
			}
		}
		Back(1);
		extent.HeaderChildNode("Verifing that user is able to watch the free Content");
		verifyElementPresentAndClick(PWALiveTVPage.objFilterOption("FREE Channels"), "Free Channels filter");
		waitForElementDisplayed(PWALiveTVPage.objFirstfreeContentCard, 5);
		verifyElementPresentAndClick(PWALiveTVPage.objFirstfreeContentCard, "Free Content card");
		// waitForElementDisplayed(PWAPremiumPage.objPremiumPopUp, 5);
		if (!(verifyElementExist(PWAPremiumPage.objPremiumPopUp, "Premium PopUp"))) {
			logger.info("user is able to watch the free Content");
			extent.extentLogger("Free content", "user is able to watch the free Content");
		}
		extent.HeaderChildNode("Verifing that free content videos in landscape mode");
		waitForPlayerAdToComplete2("Video Player");
//			JSClick(PWAPlayerPage.objPlayer, "player screen");
//			if(verifyElementExist(PWAPlayerPage.objPlayerPause, "Pause icon"))
//			{
//				JSClick(PWAPlayerPage.objPlayerPause, "Pause icon");
//			}
		waitForElementDisplayed(PWAPlayerPage.objPlayerscreen, 120);
		pause();
		verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize icon");
		JSClick(PWAPlayerPage.maximizeBtn, "Maximize icon");
		for (int i = 0; i < 5; i++) {
			if (verifyElementExist(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
				logger.info("User is able to watch free content in landscape mode");
				extent.extentLogger("Landscape mode", "User is able to watch free content in landscape mode");
				break;
			} else {
				JSClick(PWAPlayerPage.objPlayer, "player screen");
			}
		}
		Back(1);
	}

	public void pause() {
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(By.xpath("//*[@class='kaltura-player-container']"));
		actions.moveToElement(menuOption).perform();
		getWebDriver().findElement(By.xpath("//i[@class='playkit-icon playkit-icon-pause']")).click();
	}

	public void ChannelGuide(String userType) throws Exception {
		extent.HeaderChildNode("Validating that user is navigated to channel guide screen");
		verifyElementPresentAndClick(PWALiveTVPage.objNothighlightedChannelGuideToggle, "Channel guide toggle");
		waitForElementDisplayed(PWALiveTVPage.objHighlightedChannelGuideToggle, 5);
		if (verifyElementPresent(PWALiveTVPage.objHighlightedChannelGuideToggle, "Channel guide toggle")) {
			logger.info("Channel guide toggle is highlighted, User is navigated to Channel guide screen");
			extent.extentLogger("Channel guide",
					"Channel guide toggle is highlighted, User is navigated to Channel guide screen");
		}
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			extent.HeaderChildNode("Validating that user is able to add to reminders the  Upcoming Live Program");
			remainderOptionOnUpcomingShow();
			click(PWALiveTVPage.objTodayDate, "Today's date");
		}
		extent.HeaderChildNode("Validating that user is able to scroll trough the channel list");
		waitForElementDisplayed(PWALiveTVPage.objFirstOngoingLiveTvShowCard, 20);
		scrollDownWEB();
		scrollToTopOfPageWEB();
		logger.info("user is able to scroll trough the channel list");
		extent.extentLogger("Scroll", "user is able to scroll trough the channel list");
		extent.HeaderChildNode("Validating that On going live show cards are highlighted");
		waitForElementDisplayed(PWALiveTVPage.objFirstOngoingLiveTvShowCard, 20);
		verifyElementExist(PWALiveTVPage.objFirstOngoingLiveTvShowCard, "Ongoing Live Tv show card");
		String ongoingLiveTvcardClass = getAttributValue("class", PWALiveTVPage.objFirstOngoingLiveTvShowCard);
		if (ongoingLiveTvcardClass.contains("active")) {
			logger.info("On going live show cards are highlighted on channel guide screen");
			extent.extentLogger("On going live show card",
					"On going live show cards are highlighted on channel guide screen");
		} else {
			logger.info("On going live show cards are not highlighted on channel guide screen");
			extent.extentLogger("On going live show card",
					"On going live show cards are not highlighted on channel guide screen");
		}
		extent.HeaderChildNode("Validating that user is navigated to respective live TV consumption screen");
		String onGoingLiveTvShowCardTitle = getText(PWALiveTVPage.objOngoingLiveTvShowTitle);
		System.out.println(onGoingLiveTvShowCardTitle);
		verifyElementPresent(PWALiveTVPage.objOngoingLiveTvShowTitle, "Ongoing Live TV Show card");
		JSClick(PWALiveTVPage.objOngoingLiveTvShowTitle, "Ongoing Live TV Show card");
		waitForElementDisplayed(PWASearchPage.objShowTitleInconsumptionPage(onGoingLiveTvShowCardTitle), 5);
		while (!(verifyElementExist(PWASearchPage.objShowTitleInconsumptionPage(onGoingLiveTvShowCardTitle),
				"Title in Consumption Screen"))) {
			scrollDownWEB();
		}
		String ConsumptionScreenShowTitle = getText(
				PWASearchPage.objShowTitleInconsumptionPage(onGoingLiveTvShowCardTitle));
		System.out.println(ConsumptionScreenShowTitle);
		if (ConsumptionScreenShowTitle.contains(onGoingLiveTvShowCardTitle)) {
			logger.info("user is navigated to respective consumption screen");
			extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
		} else {
			logger.info("user is not navigated to respective consumption screen");
			extent.extentLogger("Consumption Screen", "user is not navigated to respective consumption screen");
		}
		Back(2);
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
	}

	public void Back_TO_TopArrow_Web(String usertype) throws Exception {

		scrollToBottomOfPageWEB();
		if (usertype.equalsIgnoreCase("Guest")) {
			if (verifyElementExist(PWAHomePage.objWhatWonderingPopUp, "Wondering popUp")) {
				waitTime(3000);
				click(PWAHomePage.objWhatWonderingPopUpCloseIcon, "Close icon");
			}
		}
		// waitForElementDisplayed(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 20);
		if (verifyElementExist(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top")) {
			click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
			System.out.println("Scrolled back to top using Back to top btn");
		}

	}

	public void remainderOptionOnUpcomingShow() throws Exception {
		// Click on date
		waitTime(10000);
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		waitTime(5000);
		FilterLanguage();
		waitTime(5000);
		while (!(verifyElementExist(PWALiveTVPage.objparticularTime, "choosed time"))) {
			waitTime(1000);
			// click(PWALiveTVPage.objTimeSlotRightArrowMark, "Right Arrow");
			getWebDriver().findElement(By.xpath(
					"//div[@class='outerTimeContainer']/child::div[@class='noSelect iconNavi-ic_back rightArrow']"))
					.click();
		}
		waitTime(5000);
		// Verify Share and Remainder option is available
		click(PWALiveTVPage.objShowNameweb, "Show");
		verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		if (verifyElementExist(PWALiveTVPage.objRemainderButton, "Remainder option for upcoming show ")) {
			click(PWALiveTVPage.objRemainderButton, "Remainder option");

			extent.extentLogger("Remainder option", "User can click on Remainder option");
			logger.info("User can click on Remainder option");
		} else {

			extent.extentLoggerFail("Remainder option", "User can not click on Remainder option");
			logger.info("User can not click on Remainder option");
		}

		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
	}

	public void FilterLanguage() throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			getWebDriver().findElement(By.xpath("//div[contains(@class,'selectedLanguage language noSelect')]"))
					.click();

		}
		click(PWALiveTVPage.objKannadaLang, "Kannada language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");

	}

	/**
	 * ================================MANASA
	 * SUBSCRIPTION==================================
	 * 
	 */

	/*
	 * Validating if selected Content language is displayed in the Regional Pack
	 */
	public void contentLanguageVerify(String userType) throws Exception {
		if (userType.contains("Guest") || userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode("Validating if selected Content language is displayed in the Regional Pack");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, "Language Button");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, "Content Language");
			List<WebElement> selectedLanguages = getWebDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
			System.out.println(selectedLanguages.size());
			ArrayList<String> contentLanguages = new ArrayList<String>();
			for (int i = 1; i <= selectedLanguages.size(); i++) {
				System.out.println("Selected Content Language : " + getWebDriver().findElement(By.xpath(
						"((//div[@class='checkboxWrap checkedHighlight'])//child::*[@class='commonName'])[" + i + "]"))
						.getText());
				contentLanguages.add(getWebDriver().findElement(By.xpath(
						"((//div[@class='checkboxWrap checkedHighlight'])//child::*[@class='commonName'])[" + i + "]"))
						.getText());
			}
			System.out.println(contentLanguages);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplyBtn, "Apply Button");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHomePage.objWEBSubscribeBtn, "Subscribe button");
			waitTime(5000);
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have a Code");
			List<WebElement> selectedRegionalLanguages = getWebDriver().findElements(PWASubscriptionPages.objPackTypes);
			System.out.println("selectedRegionalLanguages size : " + selectedRegionalLanguages.size());
			for (int i = 2; i <= selectedRegionalLanguages.size(); i++) {
				System.out.println(getText(PWASubscriptionPages.objPackType(i)));
				if (contentLanguages.contains(getText(PWASubscriptionPages.objPackType(i)))) {
					System.out.println("Regional pack is displayed as per the selected content language");
					getWebDriver().findElement(By.xpath(
							"(//span[@class='noSelect'][.='" + getText(PWASubscriptionPages.objPackType(i)) + "'])"))
							.click();
					List<WebElement> packs = getWebDriver().findElements(PWASubscriptionPages.objPackTitle);
					System.out.println("Number of packs available " + packs.size());
					for (int k = 1; k <= packs.size(); k++) {
						logger.info("Pack Type : " + getWebDriver()
								.findElement(By.xpath("((//div[@class='planDescription']))[" + k + "]")).getText());
						extent.extentLogger("Pack Type", "Pack Type : " + getWebDriver()
								.findElement(By.xpath("((//div[@class='planDescription']))[" + k + "]")).getText());
						logger.info("Pack Amount : " + getWebDriver()
								.findElement(By.xpath("(//p[@class='currency'])[" + k + "]")).getText());
						extent.extentLogger("Pack Amount", "Pack Amount : " + getWebDriver()
								.findElement(By.xpath("(//p[@class='currency'])[" + k + "]")).getText());
						logger.info("Pack Duration : " + getWebDriver()
								.findElement(By.xpath("(//p[@class='duration'])[" + k + "]")).getText());
						extent.extentLogger("Pack Duration", "Pack Duration : " + getWebDriver()
								.findElement(By.xpath("(//p[@class='duration'])[" + k + "]")).getText());
					}
				}
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	/*
	 * Zee Subscription Page Validation
	 */
	public void verifyUIofZEESubscriptionPage(String userType) throws Exception {
		if (userType.contains("Guest") || userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode("Zee Subscription Page Validation");
			verifyElementPresentAndClick(PWAHomePage.objWEBSubscribeBtn, "Subscribe button");
			extent.HeaderChildNode("Validating if Have a code section is displayed on Zee subscription screen.");
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have a Code section");
			String packTitle = getText(PWASubscriptionPages.objPackCategoryTabSelected);
			logger.info(packTitle);
			extent.extentLogger("Pack Title", packTitle + " is displayed");
			String packDescription = getText(PWASubscriptionPages.objPackDescription);
			logger.info(packDescription);
			extent.extentLogger("Pack Description", packDescription + " is displayed");
			extent.HeaderChildNode("Verifying the Default selected Pack ");
			String defaultSelectedPackTitle = getText(PWASubscriptionPages.objSelectedSubscriptionPlanType);
			logger.info("Default Selected Pack Type " + defaultSelectedPackTitle);
			extent.extentLogger("Default Selected Pack Type", "Default Selected Pack Type " + defaultSelectedPackTitle);
			String defaultSelectedPackAmount = getText(PWASubscriptionPages.objSelectedSubscriptionPlanAmount);
			logger.info("Default Selected Pack Amount " + defaultSelectedPackAmount);
			extent.extentLogger("Default Selected Pack Amount",
					"Default Selected Pack Type " + defaultSelectedPackAmount);
			String defaultSelectedPackDuration = getText(PWASubscriptionPages.objSelectedSubscriptionPlanDuration);
			logger.info("Default Selected Pack Duration " + defaultSelectedPackDuration);
			extent.extentLogger("Default Selected Pack Duration",
					"Default Selected Pack Duration " + defaultSelectedPackDuration);
			extent.HeaderChildNode("Verifying the URL of the page");
			String url = getWebDriver().getCurrentUrl();
			logger.info("URL of the page : " + url);
			extent.extentLogger("URL", "URL of the page : " + url);
			extent.HeaderChildNode("Validating if the Coupon code accepts special characters");
			String code = "zee5pt20@&*";
			type(PWASubscriptionPages.objHaveACode, code, "Prepaid Code");
			String codeVerify = getText(PWASubscriptionPages.objHaveACode);
			System.out.println(codeVerify);
			if (code.equals(codeVerify)) {
				logger.info("Coupon code accepts special characters");
				extent.extentLogger("Coupon code", "Coupon code accepts special characters");
			} else {
				logger.info("Coupon code does not accept special characters");
				extent.extentLogger("Coupon code", "Coupon code does not accept special characters");
			}
			verifyElementPresentAndClick(PWASubscriptionPages.objApplyBtn, "Apply Button");
			waitTime(2000);
			verifyElementPresent(PWASubscriptionPages.objAppliedCodeFailureMessage, "Failure message");
			String failureMsg = getText(PWASubscriptionPages.objAppliedCodeFailureMessage);
			logger.info(failureMsg);
			extent.extentLogger("Failure Message", failureMsg + " is displayed");
			waitTime(2000);
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACodeCloseBtn, "Close Button");
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "Have A Code section");
			type(PWASubscriptionPages.objHaveACode, "PNB20", "Prepaid Code");
			verifyElementPresentAndClick(PWASubscriptionPages.objApplyBtn, "Apply Button");
			waitTime(2000);
			boolean ele1 = verifyElementPresent(PWASubscriptionPages.objAppliedSuccessfullyMessage,
					"Applied Successfully message");
			String successMessage = getText(PWASubscriptionPages.objAppliedSuccessfullyMessage);
			logger.info(successMessage);
			extent.extentLogger("Success Message", successMessage + " is displayed");
			waitTime(2000);
			verifyElementPresentAndClick(PWASubscriptionPages.objHaveACodeCloseBtn, "Close Button");
			type(PWASubscriptionPages.objHaveACode, "PNB20 ", "Prepaid Code");
			verifyElementPresentAndClick(PWASubscriptionPages.objApplyBtn, "Apply Button");
			waitTime(2000);
			boolean ele2 = verifyElementPresent(PWASubscriptionPages.objAppliedSuccessfullyMessage,
					"Applied Successfully message");
			String successMsg = getText(PWASubscriptionPages.objAppliedSuccessfullyMessage);
			logger.info(successMsg);
			extent.extentLogger("Success Message", successMsg + " is displayed");
			waitTime(2000);
			extent.HeaderChildNode("Validating if the Coupon code is case insensitive");
			if (ele1 && ele2 == true) {
				logger.info("Coupon code is case insensitive");
				extent.extentLogger("Coupon code", "Coupon code is case insensitive");
			} else {
				logger.info("Coupon code is case sensitive");
				extent.extentLogger("Coupon code", "Coupon code is case sensitive");
			}
			extent.HeaderChildNode(
					"Validating the plans with discounted amount is displayed if applied code is successful.");
			List<WebElement> discountedPackAmount = getWebDriver().findElements(PWASubscriptionPages.objPackAmount);
			System.out.println(discountedPackAmount.size());
			for (int i = 1; i <= discountedPackAmount.size(); i++) {
				logger.info("Discounted Pack Amount : "
						+ getWebDriver().findElement(By.xpath("(//p[@class='currency'])[" + i + "]")).getText());
				extent.extentLogger("Discounted Pack Amount", "Discounted Pack Amount : "
						+ getWebDriver().findElement(By.xpath("(//p[@class='currency'])[" + i + "]")).getText());
			}
			waitTime(2000);
			verifyElementPresentAndClick(PWASubscriptionPages.objPackAmount1, "Discounted pack");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue Button");
			waitTime(5000);
			paymentPageValidation(userType);
		}
	}

	/*
	 * Validating the UI of My Subscription Page
	 */
	public void verifyUIofMySubscriptionPage(String userType) throws Exception {
		waitTime(10000);
		if (userType.contains("NonSubscribedUser") || (userType.contains("SubscribedUser"))) {
			extent.HeaderChildNode("Validating the UI of My Subscription Page");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile Icon");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMySubscription, "My Subscription");
			verifyElementExist(PWAHamburgerMenuPage.objBrowseAllPacks, "Browse All Packs");
		}
		if (userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode(
					"Validating if Empty state screen is displayed when user has No Active Subscriptions");
			verifyElementExist(PWAHamburgerMenuPage.objEmptyStateScreen, "Empty State Screen");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSubscriptionTeaserBanner,
					"Subscription Teaser Banner");
			waitTime(10000);
			if (verifyElementExist(PWASubscriptionPages.objZEE5Subscription, "Zee Subscription Page") == true) {
				logger.info("Navigated to Zee Subscription Page");
				extent.extentLogger("Subscription Page", "Navigated to Zee Subscription Page");
			} else {
				logger.info("Not navigated to Zee Subscription Page");
				extent.extentLogger("Subscription Page", "Not navigated to Zee Subscription Page");
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/*
	 * Validating the UI of My Transactions Page and Active/Expired Cards
	 */
	public void validatingActiveAndExpiredCardsinMyTransactionPage(String userType) throws Exception {
		if (userType.contains("NonSubscribedUser") || userType.contains("SubscribedUser")) {
			extent.HeaderChildNode("Validating the UI of My Transactions Page");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile Icon");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyTransactions, "MyTransaction");
			Thread.sleep(3000);
			extent.HeaderChildNode(
					"Validating if Empty state screen is displayed when user doesn't have any purchase history");
			verifyElementExist(PWAHamburgerMenuPage.objEmptyStateScreen, "Empty State Screen");
			boolean NoTransactionPresent = verifyElementExist(PWAHamburgerMenuPage.objNoTransaction, "No Transactions");
			if (NoTransactionPresent == false) {
				extent.HeaderChildNode("Validating the UI of Active/Expired Cards");
				List<WebElement> packs = getWebDriver().findElements(By.xpath("//p[@class='packTitle']"));
				System.out.println("No of Packs : " + packs.size());
				if (packs.size() > 1) {
					String status = getText(PWAHamburgerMenuPage.objMyTransactionPackStatus);
					logger.info("Pack Status :" + status);
					extent.extentLogger("Pack Status :", "Pack Status :" + status);
					if (status.equalsIgnoreCase("Active")) {
						String title = getText(PWAHamburgerMenuPage.objPackTitle);
						logger.info("Pack Title :" + title);
						extent.extentLogger("Pack Title :", "Pack Title :" + title);
						String price = getText(PWAHamburgerMenuPage.objPackPrice);
						logger.info("Pack Price :" + price);
						extent.extentLogger("Pack Price :", "Pack Price :" + price);
						String duration = getText(PWAHamburgerMenuPage.objPackDuration);
						logger.info("Pack Duration :" + duration);
						extent.extentLogger("Pack Duration :", "Pack Duration :" + duration);
						String renewalStatus = getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus);
						logger.info("Pack Renewal Status :" + renewalStatus);
						extent.extentLogger("Pack Renewal Status :", "Pack Renewal Status :" + renewalStatus);
					}
					String status1 = getText(PWAHamburgerMenuPage.objMyTransactionPackStatus1);
					logger.info("Pack Status :" + status1);
					extent.extentLogger("Pack Status :", "Pack Status :" + status1);
					if (status1.equalsIgnoreCase("EXPIRED")) {
						String title1 = getText(PWAHamburgerMenuPage.objPackTitle1);
						logger.info("Pack Title :" + title1);
						extent.extentLogger("Pack Title :", "Pack Title :" + title1);
						if (verifyElementExist(PWAHamburgerMenuPage.objPackPrice1, "Pack Price")) {
							String price1 = getText(PWAHamburgerMenuPage.objPackPrice1);
							logger.info("Pack Price :" + price1);
							extent.extentLogger("Pack Price :", "Pack Price :" + price1);
						}
						String duration1 = getText(PWAHamburgerMenuPage.objPackDuration1);
						logger.info("Pack Duration :" + duration1);
						extent.extentLogger("Pack Duration :", "Pack Duration :" + duration1);
						String renewalStatus1 = getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus1);
						logger.info("Pack Renewal Status :" + renewalStatus1);
						extent.extentLogger("Pack Renewal Status :", "Pack Renewal Status :" + renewalStatus1);
					}
				} else {
					String status = getText(PWAHamburgerMenuPage.objMyTransactionPackStatus);
					logger.info("Pack Status :" + status);
					extent.extentLogger("Pack Status :", "Pack Status :" + status);
					String title = getText(PWAHamburgerMenuPage.objPackTitle);
					logger.info("Pack Title :" + title);
					extent.extentLogger("Pack Title :", "Pack Title :" + title);
					if (verifyElementExist(PWAHamburgerMenuPage.objPackPrice1, "Pack Price")) {
						String price = getText(PWAHamburgerMenuPage.objPackPrice);
						logger.info("Pack Price :" + price);
						extent.extentLogger("Pack Price :", "Pack Price :" + price);
					}
					String duration = getText(PWAHamburgerMenuPage.objPackDuration);
					logger.info("Pack Duration :" + duration);
					extent.extentLogger("Pack Duration :", "Pack Duration :" + duration);
					String renewalStatus = getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus);
					logger.info("Pack Renewal Status :" + renewalStatus);
					extent.extentLogger("Pack Renewal Status :", "Pack Renewal Status :" + renewalStatus);
				}
			}
			extent.HeaderChildNode(
					"Validating if user is navigated to Zee Subscription screen post tapping on subscription teaser banner");
			if (verifyElementExist(PWAHamburgerMenuPage.objSubscriptionTeaserBanner,
					"Subscription Teaser Banner") == true) {
				click(PWAHamburgerMenuPage.objSubscriptionTeaserBanner, "Subscription Teaser Banner");
				waitTime(5000);
				if (verifyElementExist(PWASubscriptionPages.objZEE5Subscription, "Zee Subscription Page") == true) {
					logger.info("Navigated to Zee Subscription Page");
					extent.extentLogger("Subscription Page", "Navigated to Zee Subscription Page");
				} else {
					logger.info("Not navigated to Zee Subscription Page");
					extent.extentLogger("Subscription Page", "Not navigated to Zee Subscription Page");
				}
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	/*
	 * Payment Page Validation
	 */
	public void paymentPageValidation(String userType) throws Exception {
		extent.HeaderChildNode("Payment Page Validation");
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWASubscriptionPages.objEmailIDTextField, "Email ID field")) {
				click(PWASubscriptionPages.objEmailIDTextField, "Email ID field");
				type(PWASubscriptionPages.objEmailIDTextField, "igszee5test123g@gmail.com", "Email Id");
				verifyElementPresentAndClick(PWASubscriptionPages.objPaymentPageProceedBtn, "Proceed Button");
				// Password Popup
				verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
				verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
				type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Proceed Button");
			}
		}
		extent.HeaderChildNode("Validating the payment gateway using Credit/Debit Card");
		waitTime(10000);
		WebElement iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
		Thread.sleep(5000);
		Thread.sleep(5000);
		Thread.sleep(5000);
		getWebDriver().switchTo().frame(iframeElement);
//		verifyElementExist(PWASubscriptionPages.objCreditAndDebitCardBtn, "Credit/Debit Card Option");
		verifyElementExist(PWASubscriptionPages.objCreditAndDebitCardBtn, "Credit/Debit Card Option");
		verifyElementPresentAndClick(PWASubscriptionPages.objarrowbtn, "arrow button");
		verifyElementExist(PWASubscriptionPages.objEnterCreditAndDebitCardDetails, "Enter Credit/Debit Card Details");
		verifyElementExist(PWASubscriptionPages.objCardNumber, "Enter Card Number Field");
		verifyElementExist(PWASubscriptionPages.objExpiry, "Expiry Field");
		verifyElementExist(PWASubscriptionPages.objCVV, "CVV Field");
		verifyElementExist(PWASubscriptionPages.objCreditDebitProceedToPay, "Proceed To Pay Button");
		verifyElementPresentAndClick(PWASubscriptionPages.objWallets, "Wallets");
		extent.HeaderChildNode("Validating the payment gateway using Paytm");
		verifyElementPresentAndClick(PWASubscriptionPages.objPaytmWallet, "Paytm");
		verifyElementExist(PWASubscriptionPages.objPaytmProceedToPay, "Proceed To Pay Button");
		getWebDriver().switchTo().defaultContent();
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		if(userType.equals("Guest")) {
		logout();
		}
	}

	/**
	 * ================================MANASA
	 * PremiumPage==================================
	 * 
	 */

	public void landingPagesValidation(String tabName) throws Exception {
		extent.HeaderChildNode(tabName + " Page Validation");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(3000);

		waitTime(5000);
		if (verifyElementExist(PWAHomePage.objActiveTab, "Active tab")) {
			String tab = getText(PWAHomePage.objActiveTab);
			System.out.println(tab);
			logger.info(tab + " tab is highlighted");
			extent.extentLogger("Tab", tab + " tab is highlighted");
		} else {
			Actions actions1 = new Actions(getWebDriver());
			WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
			actions1.moveToElement(tab).build().perform();
			String tab2 = getText(PWAHomePage.objActiveTab);
			System.out.println(tab2);
			logger.info(tab2 + " tab is highlighted");
			extent.extentLogger("Tab", tab2 + " tab is highlighted");
		}
		// check if tray is loaded
		for (int i = 1; i <= 2; i++) {
			if (verifyElementExist(PWAPremiumPage.objTrayTitle(i), "Tray")) {
				System.out.println("Tray is loaded for " + i + " scroll");
				logger.info("Tray is loaded for " + i + " scroll");
				extent.extentLogger("Tray load", "Tray is loaded for " + i + " scroll");
			} else {
				ScrollToTheElement(PWAPremiumPage.objTrayTitle(i));
				verifyElementExist(PWAPremiumPage.objTrayTitle(i), "Tray");
			}
		}
		scrollDownWEB();
		verifyElementPresentAndClick(PWAPremiumPage.objNextArrowBtn, "Next Arrow Button");
		if (verifyElementExist(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button")) {
			logger.info("Tray is rotated");
			extent.extentLogger("Tray is rotated", "Tray is rotated");
		} else {
			logger.info("Tray is not rotated");
			extent.extentLogger("Tray is not rotated", "Tray is not rotated");
		}
		click(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button");
		if (verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");
			if (verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}
		Back(1);
		waitTime(2000);

		waitTime(2000);
		if (verifyElementExist(PWAMusicPage.objArrowToNavigateTop, "Arrow icon")) {
			waitTime(2000);
			click(PWAMusicPage.objArrowToNavigateTop, "Arrow icon");
		}
		waitTime(2000);
		for (int i = 0; i < 5; i++) {
			if (findElements(PWAPremiumPage.objMinuteContent).size() > 0) {
				logger.info("Minute content is displayed");
				extent.extentLogger("Minute content", "Minute content is displayed");
				break;
			} else {
				logger.info("Minute content is not displayed");
				extent.extentLogger("Minute content", "Minute content is not displayed");
				partialScrollDown();
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void landingPagesTrailerAndPopUpValidation(String userType, String tabName) throws Exception {
		extent.HeaderChildNode(tabName + " Page Carousel Validation");
		navigateToAnyScreenOnWeb(tabName);
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHomePage.objZeelogo1);
		actions.moveToElement(menuOption).perform();
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel");
		waitTime(5000);
		if (userType.contains("Guest")) {
			mandatoryRegistrationPopUp(userType);
			if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Why Register Pop Up")) {
				click(PWASearchPage.objCloseRegisterDialog, "Close Button");
			} else {
				logger.info("Why Register pop up is not displayed");
			}
		}
		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
		}
		if (verifyElementExist(PWAPlayerPage.objPlaybackVideoOverlay, "Player")) {
			logger.info("Navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Navigated to Consumption Page");
		} else {
			logger.info("Not navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Not navigated to Consumption Page");
		}
		if (verifyElementExist(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
			watchTrailerButtonFunctionality(userType);
		} else {
			logger.info("Trailer is not available for the selected content");
			extent.extentLogger("Trailer", "Trailer is not available for the selected content");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/*
	 * Validation of Complete Profile/Tell us more about you section
	 */
	public void verifyCompleteYourProfilePopUp() throws Exception {
		extent.HeaderChildNode("Validating if user is able to fill Complete Profile/Tell us more about you section");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objFirstName, "First Name Field");
		type(CompleteYourProfilePopUp.objFirstName, "Test", "First Name Field");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objLastName, "Last Name Field");
		type(CompleteYourProfilePopUp.objLastName, "User", "Last Name Field");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objDay, "Day Field");
		click(CompleteYourProfilePopUp.objDateSelector, "Date");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objMonth, "Month Field");
		click(CompleteYourProfilePopUp.objDateSelector, "Month");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objYear, "Year Field");
		click(CompleteYourProfilePopUp.objDateSelector, "Year");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objGenderFemale, "Gender Field");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objMobileNo, "Mobile Number");
		type(CompleteYourProfilePopUp.objMobileNo, "95839633299", "Mobile Number");
		verifyElementPresentAndClick(CompleteYourProfilePopUp.objCloseBtn, "Close Button");
	}

	@SuppressWarnings("unused")
	public void trayTitleAndContentValidationWithApiData(String tab, String api) throws Exception {
		extent.HeaderChildNode(tab + " page validation with Api response");
		navigateToAnyScreenOnWeb(tab);
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
		actions.moveToElement(menuOption).build().perform();

		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages("home", languageSmallText); // changes
		List<String> apiTitleList = new LinkedList<String>();
		String Tray_Title = resp.jsonPath().getString("buckets[2].title"); // changes
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		List<String> contentList = resp.jsonPath().getList("buckets[2].items"); // changes
		System.out.println(contentList.size());
		// partialScrollDown();
		partialScroll(); // changes
		partialScroll(); // changes
		waitTime(2000); // changes
		List<WebElement> card = getWebDriver().findElements(By.xpath("((//div[@class='slick-list'])[3]//img)")); // changes
		System.out.println(card.size());
		for (int i = 0; i < card.size(); i++) {
			// API DATA
			String title = resp.jsonPath().getString("buckets[1].items[" + i + "].title"); // changes
			String businessType = resp.jsonPath().getString("buckets[2].items[" + i + "].business_type");
			String minuteType_isDRM = null;
			minuteType_isDRM = resp.jsonPath().getString("buckets[2].items[" + i + "].is_drm"); // changes

			apiTitleList.add(title);
			WebElement contentCard = getWebDriver()
					.findElement(By.xpath("((//div[@class='slick-list'])[3]//img)[" + (i + 1) + "]")); // changes
			actions.moveToElement(contentCard).build().perform();

			// to get metadata from content
			String contentMetadata = getAttributValue("title",
					By.xpath("((//div[@class='slick-list'])[3]//img)[" + (i + 1) + "]")); // changes

			// String trayTitle = apiTitleList.get(i);
//			System.out.println("UI data : " + contentMetadata);
//			System.out.println("api data : " + apiTitleList.get(i));
			logger.info("UI data " + contentMetadata); // changes
			logger.info("API data " + apiTitleList.get(i));
			extent.extentLogger("UI data ", "UI data " + contentMetadata);
			extent.extentLogger("API data ", "API data " + apiTitleList.get(i));
			if (contentMetadata.equalsIgnoreCase(apiTitleList.get(i))) {
				logger.info("Metadata on the content card is valid with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is valid with Api data");
			} else {
				logger.info("Metadata on the content card is not valid with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card is not valid with Api data");
			}

			// MINUTELY CONTENT CHECK
			waitTime(1000);
//			System.out.println(minuteType_isDRM);
//			if (minuteType_isDRM == null) {
//				System.out.println("No minute content attached");
//			} else {
//				System.out.println("Minute content present");

			if (verifyElementExist(PWAPremiumPage.specificContentisMinuteimage(Tray_Title, i + 1), "Minute Content")) {
				logger.info("MinuteContent is Present");
				extent.extentLogger("MinuteContent", "MinuteContent is Present");
			} else {
				logger.info("MinuteContent is not Present");
				extent.extentLogger("MinuteContent", "MinuteContent is not Present");
			}
//			}

			// PREMIUM ICON CHECK
			waitTime(1000);
			if (businessType.contains("premium")) {
				if (verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i + 1), "Premium icon")) {
					logger.info("Premium icon is Present");
					extent.extentLogger("Premium icon", "Premium icon is Present");
				} else {
					logger.info("Premium icon is not Present");
					extent.extentLoggerFail("Premium icon", "Premium icon not Present");
				}

			} else if (businessType.contains("advertisement") || businessType.contains("free")) {
				if (!verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i), "Premium icon")) {
					logger.info("premium icon not present and Content is NonPremium");
					extent.extentLogger("Premium icon", "premium icon not present and Content is NonPremium");
				} else {
					logger.info("premium icon is present");
					extent.extentLoggerFail("Premium icon", "premium icon is present");
				}
			}

			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Icon")) {
				logger.info("Play icon is displayed");
				extent.extentLogger("Play", "Play icon is displayed");
			} else {
				logger.info("Play icon is not displayed");
				extent.extentLogger("Play", "Play icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Icon")) {
				logger.info("Share icon is displayed");
				extent.extentLogger("Share", "Share icon is displayed");
			} else {
				logger.info("Share icon is not displayed");
				extent.extentLogger("Share", "Share icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardWatchlistBtn, "Watchlist Icon")) {
				logger.info("Watchlist icon is displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is displayed");
			} else {
				logger.info("Watchlist icon is not displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is not displayed");
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public String allSelectedLanguages() throws Exception {
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, "language btn");
		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "content languages");
		waitTime(2000);
		List<WebElement> allSelectedLanguages = getWebDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
		String langtext = "";
		for (int i = 0; i < allSelectedLanguages.size(); i++) {
			// System.out.println(i);
			langtext = allSelectedLanguages.get(i).getAttribute("for").replace("content_", "") + "," + langtext;
			// System.out.println(langtext.replaceAll(",$",""));
		}
		String finalLangString = langtext.replaceAll(",$", "");
		waitForElementAndClick(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, 2, "Apply Button");
		return finalLangString;
	}

	public void watchTrailerButtonFunctionality(String userType) throws Exception {
		extent.HeaderChildNode("Watch Trailer Button Validation");
		click(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button");
		if (userType.contains("Guest")) {
			if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Why Register Pop Up")) {
				click(PWASearchPage.objCloseRegisterDialog, "Close Button");
			} else {
				logger.info("Why Register pop up is not displayed");
				extent.extentLogger("Register popup", "Why Register pop up is not displayed");
			}
		}
		waitTime(20000);
		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
		}
		if (userType.contains("NonSubscribedUser") || (userType.contains("Guest"))) {
			verifyElementExist(PWAPremiumPage.objSubscribeNowAndGoAdFree, "Subscribe Now And Go Ad Free Message");
			verifyElementExist(PWAPremiumPage.objGetPremium, "Get Premium Button");
		}
	}

	public void premiumPageTrayTitleAndContentValidationWithApiData(String tab, String api) throws Exception {
		extent.HeaderChildNode(tab + " page validation with Api response");
		navigateToAnyScreenOnWeb(tab);
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
		actions.moveToElement(menuOption).build().perform();

		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		List<String> contentList = resp.jsonPath().getList("buckets[1].items");
		System.out.println(contentList.size());
//		ScrollToElement(PWAMusicPage.objTrayTitle(Tray_Title), "Tray title");
		partialScrollDown();
		List<WebElement> card = getWebDriver().findElements(By.xpath("((//div[@class='slick-list'])[2]//img)"));
		System.out.println(card.size());
		for (int i = 0; i < card.size(); i++) {
			// API DATA
			String title = resp.jsonPath().getString("buckets[1].items[" + i + "].title");
			String businessType = resp.jsonPath().getString("buckets[1].items[" + i + "].business_type");

			apiTitleList.add(title);

			WebElement contentCard = getWebDriver()
					.findElement(By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));
			actions.moveToElement(contentCard).build().perform();

			// to get metadata from content
			String contentMetadata = getAttributValue("title",
					By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));

			logger.info("UI data " + contentMetadata);
			logger.info("API data " + apiTitleList.get(i));
			extent.extentLogger("UI data ", "UI data " + contentMetadata);
			extent.extentLogger("API data ", "API data " + apiTitleList.get(i));
			if (contentMetadata.equalsIgnoreCase(apiTitleList.get(i))) {
				logger.info("Metadata on the content card is validated with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is validated with Api data");
			} else {
				logger.info("Metadata on the content card is not validated with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card is not validated with Api data");
			}

			// PREMIUM ICON CHECK
			waitTime(1000);
			if (businessType.contains("premium")) {
				if (verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i + 1), "Premium icon")) {
					logger.info("Premium icon is Present");
					extent.extentLogger("Premium icon", "Premium icon is Present");
				} else {
					logger.info("Premium icon is not Present");
					extent.extentLoggerFail("Premium icon", "Premium icon not Present");
				}

			} else if (businessType.contains("advertisement") || businessType.contains("free")) {
				if (!verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i), "Premium icon")) {
					logger.info("premium icon not present and Content is NonPremium");
					extent.extentLogger("Premium icon", "premium icon not present and Content is NonPremium");
				} else {
					logger.info("premium icon is present");
					extent.extentLoggerFail("Premium icon", "premium icon is present");
				}
			}

			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Icon")) {
				logger.info("Play icon is displayed");
				extent.extentLogger("Play", "Play icon is displayed");
			} else {
				logger.info("Play icon is not displayed");
				extent.extentLogger("Play", "Play icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Icon")) {
				logger.info("Share icon is displayed");
				extent.extentLogger("Share", "Share icon is displayed");
			} else {
				logger.info("Share icon is not displayed");
				extent.extentLogger("Share", "Share icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardWatchlistBtn, "Watchlist Icon")) {
				logger.info("Watchlist icon is displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is displayed");
			} else {
				logger.info("Watchlist icon is not displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is not displayed");
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * ================================MANASA
	 * MusicPage==================================
	 * 
	 */

	public void musicPageValidation(String tabName, String userType, String searchText) throws Exception {
		extent.HeaderChildNode("Music Page Playback Validation for Free Content and Premium Content");
		navigateToAnyScreenOnWeb(tabName);
		Actions actions = new Actions(getWebDriver());
		WebElement player = getWebDriver().findElement(PWAPlayerPage.objPlaybackVideoOverlay);
		if (verifyElementExist(PWAPremiumPage.objWEBMastheadCarousel, "Carousel")) {
			click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel");
			mandatoryRegistrationPopUp(userType);

			waitTime(10000);

			if (BROWSER.equals("Firefox")) {
				waitForPlayerLoaderToComplete();
			}
			waitForPlayerAdToComplete("Video Player");

			if (BROWSER.equals("Chrome")) {
				pausePlayer();
			} else {
				firefoxpause();
			}

			// actions.moveToElement(player).build().perform();
			click(PWAPlayerPage.maximizeBtn, "Maximize button");
			waitTime(1000);
			click(PWAPlayerPage.minimizeBtn, "Minimize button");
			waitTime(1000);

			if (verifyElementExist(PWAMusicPage.objRecommendedVideos, "Recommended tray")) {
				logger.info("Recommended videos is displayed in consumption page");
				extent.extentLogger("Recommended tray", "Recommended videos is displayed in consumption page");
			} else {
				logger.info("Recommended videos  is not displayed in consumption page");
				extent.extentLogger("Recommended tray", "Recommended videos is not displayed in consumption page");
			}
			Back(1);
		} else {
			musicFreeContentPlaybackValidation(userType);
		}
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, searchText, "Search Field");

		click(PWAMusicPage.objMusicTabInSearch, "Music");
		if (findElements(PWAMusicPage.objPremiumTagContentCard(searchText)).size() > 0) {
			logger.info("Premium tag is displayed");
			extent.extentLogger("Premium Tag", "Premium Tag is displayed");
			click(PWAMusicPage.objPremiumTagContentTumbnail(searchText), "Premium");
			if (userType.equals("NonSubscribedUser") || (userType.equals("Guest"))) {
				waitForElementDisplayed(PWASubscriptionPages.objSubscribepopup, 10);
				if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
					verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
				}
			}
			if (userType.equals("SubscribedUser")) {
				if (BROWSER.equals("Firefox")) {
					waitForPlayerLoaderToComplete();
				}
				waitForPlayerAdToComplete("Video Player");
				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}
				actions.moveToElement(player).build().perform();
				click(PWAPlayerPage.maximizeBtn, "Maximize button");
				waitTime(1000);
				click(PWAPlayerPage.minimizeBtn, "Minimize button");
				waitTime(1000);
				Back(1);
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void musicFreeContentPlaybackValidation(String userType) throws Exception {
		extent.HeaderChildNode("Free Content Playback Validation");

		verifyElementPresentAndClick(PWAMusicPage.objFreeMusicContentCard, "Free Content Card");
		mandatoryRegistrationPopUp(userType);

		waitTime(10000);
		if (BROWSER.equals("Firefox")) {
			waitForPlayerLoaderToComplete();
		}
		waitForPlayerAdToComplete("Video Player");

		if (BROWSER.equals("Chrome")) {
			pausePlayer();
		} else {
			firefoxpause();
		}

		// actions.moveToElement(player).build().perform();
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		waitTime(1000);
		click(PWAPlayerPage.minimizeBtn, "Minimize button");
		waitTime(1000);

		if (verifyElementExist(PWAMusicPage.objRecommendedVideos, "Recommended tray")) {
			logger.info("Recommended videos is displayed in consumption page");
			extent.extentLogger("Recommended tray", "Recommended videos is displayed in consumption page");
		} else {
			logger.info("Recommended videos  is not displayed in consumption page");
			extent.extentLogger("Recommended tray", "Recommended videos is not displayed in consumption page");
		}
		Back(1);
	}

	public void trayTitleAndContentValidationWithApiDataGuest(String tab, String api, String userType)
			throws Exception {
		if (userType.equals("Guest")) {
			extent.HeaderChildNode(tab + " page validation with Api response");
			navigateToAnyScreenOnWeb(tab);
			Actions actions = new Actions(getWebDriver());
			WebElement menuOption = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
			actions.moveToElement(menuOption).build().perform();

			String languageSmallText = allSelectedLanguages();
			System.out.println(languageSmallText);

			Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);
			List<String> apiTitleList = new LinkedList<String>();
			String Tray_Title = resp.jsonPath().getString("buckets[1].title");
			System.out.println("The Title of the Tray is " + Tray_Title + "");
			List<String> contentList = resp.jsonPath().getList("buckets[1].items");
			System.out.println(contentList.size());
			// ScrollToElement(PWAMusicPage.objTrayTitle(Tray_Title), "Tray title");
			partialScrollDown();
			List<WebElement> card = getWebDriver().findElements(By.xpath("((//div[@class='slick-list'])[2]//img)"));
			System.out.println(card.size());
			for (int i = 0; i < card.size(); i++) {
				// API DATA
				String title = resp.jsonPath().getString("buckets[1].items[" + i + "].title");
				String businessType = resp.jsonPath().getString("buckets[1].items[" + i + "].business_type");

				apiTitleList.add(title);

				WebElement contentCard = getWebDriver()
						.findElement(By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));
				actions.moveToElement(contentCard).build().perform();

				// to get metadata from content
				String contentMetadata = getAttributValue("title",
						By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));

				System.out.println("UI data : " + contentMetadata);
				System.out.println("api data : " + apiTitleList.get(i));

				logger.info("UI data " + contentMetadata);
				logger.info("API data " + apiTitleList.get(i));
				extent.extentLogger("UI data ", "UI data " + contentMetadata);
				extent.extentLogger("API data ", "API data " + apiTitleList.get(i));
				if (contentMetadata.equalsIgnoreCase(apiTitleList.get(i))) {
					logger.info("Metadata on the content card is validated with Api data");
					extent.extentLogger("Metadata", "Metadata on the content card is validated with Api data");
				} else {
					logger.info("Metadata on the content card is not validated with Api data");
					extent.extentLoggerFail("Metadata", "Metadata on the content card is not validated with Api data");
				}

				// PREMIUM ICON CHECK
				waitTime(1000);
				if (businessType.contains("premium")) {
					if (verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i + 1),
							"Premium icon")) {
						logger.info("Premium icon is Present");
						extent.extentLogger("Premium icon", "Premium icon is Present");
					} else {
						logger.info("Premium icon is not Present");
						extent.extentLoggerFail("Premium icon", "Premium icon not Present");
					}

				} else if (businessType.contains("advertisement") || businessType.contains("free")) {
					if (!verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i), "Premium icon")) {
						logger.info("premium icon not present and Content is NonPremium");
						extent.extentLogger("Premium icon", "premium icon not present and Content is NonPremium");
					} else {
						logger.info("premium icon is present");
						extent.extentLoggerFail("Premium icon", "premium icon is present");
					}
				}

				waitTime(1000);
				if (verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Icon")) {
					logger.info("Play icon is displayed");
					extent.extentLogger("Play", "Play icon is displayed");
				} else {
					logger.info("Play icon is not displayed");
					extent.extentLogger("Play", "Play icon is not displayed");
				}
				waitTime(1000);
				if (verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Icon")) {
					logger.info("Share icon is displayed");
					extent.extentLogger("Share", "Share icon is displayed");
				} else {
					logger.info("Share icon is not displayed");
					extent.extentLogger("Share", "Share icon is not displayed");
				}
				waitTime(1000);
				if (verifyElementExist(PWAPremiumPage.objContentCardWatchlistBtn, "Watchlist Icon")) {
					logger.info("Watchlist icon is displayed");
					extent.extentLogger("Watchlist", "Watchlist icon is displayed");
				} else {
					logger.info("Watchlist icon is not displayed");
					extent.extentLogger("Watchlist", "Watchlist icon is not displayed");
				}
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	public void musicPageTrayTitleAndContentValidationWithApiData(String tab, String api, String userType)
			throws Exception {

		extent.HeaderChildNode(tab + " page validation with Api response");
		waitTime(5000);
		navigateToAnyScreenOnWeb(tab);
		waitTime(5000);
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
		actions.moveToElement(menuOption).build().perform();
		waitTime(5000);

		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();

		String Tray_Title = resp.jsonPath().getString("buckets[2].title");
		logger.info("The Title of the Tray is " + Tray_Title);
		extent.extentLogger("Title", "The Title of the Tray is " + Tray_Title + "");
		List<String> contentList = resp.jsonPath().getList("buckets[2].items");
		System.out.println(contentList.size());

		ScrollToTheElementWEB(PWAMoviesPage.TextToXpath(Tray_Title));
		waitTime(2000);

		List<WebElement> card = findElements(PWAMusicPage.musicTrayContentCards(Tray_Title));
		System.out.println(card.size());
		for (int i = 0; i < card.size(); i++) {
			// API DATA
			String title = resp.jsonPath().getString("buckets[2].items[" + i + "].title");
			String businessType = resp.jsonPath().getString("buckets[2].items[" + i + "].business_type");

			apiTitleList.add(title);

			WebElement contentCard = findElement(PWAMusicPage.musicTrayContentCard(Tray_Title, i + 1));
			actions.moveToElement(contentCard).build().perform();

			// to get metadata from content
			String contentMetadata = getAttributValue("title",
					PWAMusicPage.musicTrayContentCardTitle(Tray_Title, i + 1));

			logger.info("UI data " + contentMetadata);
			logger.info("API data " + apiTitleList.get(i));
			extent.extentLogger("UI data ", "UI data " + contentMetadata);
			extent.extentLogger("API data ", "API data " + apiTitleList.get(i));
			if (contentMetadata.equalsIgnoreCase(apiTitleList.get(i))) {
				logger.info("Metadata on the content card is validated with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is validated with Api data");
			} else {
				logger.info("Metadata on the content card is not validated with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card is not validated with Api data");
			}

			// PREMIUM ICON CHECK
			waitTime(1000);
			if (businessType.contains("premium")) {
				if (verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i + 1), "Premium icon")) {
					logger.info("Premium icon is Present");
					extent.extentLogger("Premium icon", "Premium icon is Present");
				} else {
					logger.info("Premium icon is not Present");
					extent.extentLoggerFail("Premium icon", "Premium icon not Present");
				}

			} else if (businessType.contains("advertisement") || businessType.contains("free")) {
				if (!verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i), "Premium icon")) {
					logger.info("premium icon not present and Content is NonPremium");
					extent.extentLogger("Premium icon", "premium icon not present and Content is NonPremium");
				} else {
					logger.info("premium icon is present");
					extent.extentLoggerFail("Premium icon", "premium icon is present");
				}
			}

			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Icon")) {
				logger.info("Play icon is displayed");
				extent.extentLogger("Play", "Play icon is displayed");
			} else {
				logger.info("Play icon is not displayed");
				extent.extentLogger("Play", "Play icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Icon")) {
				logger.info("Share icon is displayed");
				extent.extentLogger("Share", "Share icon is displayed");
			} else {
				logger.info("Share icon is not displayed");
				extent.extentLogger("Share", "Share icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAMusicPage.musicTrayContentCardWatchListBtn(Tray_Title, i + 1),
					"Watchlist Icon")) {
				logger.info("Watchlist icon is displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is displayed");
			} else {
				logger.info("Watchlist icon is not displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is not displayed");
			}
		}
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

	}

	/**
	 * ================================VINAY Language and Language
	 * Settings==================================
	 * 
	 */

	public void LanguageModule(String userType) throws Exception {
		extent.HeaderChildNode("Language setting Module");
		// Validate language selection option is displayed
		// click on language button
		Thread.sleep(5000);

		partialScroll();
		partialScroll();
		boolean staleElement2 = true;
		while (staleElement2) {
			try {
				Thread.sleep(5000);
				verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language button");
				staleElement2 = false;
			} catch (StaleElementReferenceException e) {
				staleElement2 = true;
			}
		}

		// click(PWAHomePage.objLanguageBtn, "Language button");

		// Verify display language screen is displayed
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
				.contains("headerSelected")) {
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
							.contains("headerSelected"),
					true, "Display screen is displayed on tapping language option");
			extent.extentLogger("Verify Display language screen is displayed",
					"Display screen is displayed on tapping language option");
			logger.info("Display screen is displayed on tapping language option");
		} else {
			softAssert.assertAll();
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
							.contains("headerSelected"),
					false, "Display screen is not displayed on tapping language option");
			extent.extentLogger("Verify Display language screen is displayed",
					"Display screen is nt displayed on tapping language option");
			logger.info("Display screen is not displayed on tapping language option");
		}

		// Verify that default display language is English
		String defaultLang = getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang,
				"Default Language");
		if (defaultLang.contains("checkedHighlight")) {
			String selectedLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("English"), "Language");
			if (selectedLang.equals("English")) {
				softAssert.assertEquals(selectedLang.equals("English"), true,
						selectedLang + " language is selected by default");
				extent.extentLogger("Verify default language", "English is selected by defalut");
				logger.info(selectedLang + " language is selected by default");
			} else {
				softAssert.assertAll();
				extent.extentLoggerFail("Verify default language", "English is selected by defalut");
				logger.info("By default " + selectedLang + " is displayed");
			}
		}

		// Verify user can select desired display language
		// Verify user can Hindi language
		JSClick(PWALanguageSettingsPage.objLanguage("Hindi"), "Hindi display language");
		// Verify selected language
		String HindiLang = getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang,
				"Default Language");
		if (HindiLang.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Hindi"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}
		// Verify user can Marathi language
		JSClick(PWALanguageSettingsPage.objLanguage("Marathi"), "Marathi display language");
		// Verify selected language
		String MaratiLang = getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang,
				"Default Language");
		if (MaratiLang.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Marathi"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}
		// Verify user can Telugu language
		JSClick(PWALanguageSettingsPage.objLanguage("Telugu"), "Telugu display language");
		// Verify selected language
		getElementPropertyToString("class", PWALanguageSettingsPage.objSelectedLang, "Default Language");
		if (MaratiLang.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Telugu"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}

		// Verify selected Display language is applied
		// Select Kannada display language
		JSClick(PWALanguageSettingsPage.objLanguage("Kannada"), "Kannada display language");
		// Verify selected language
		String selectedLangInDisplayScreen = getElementPropertyToString("class",
				PWALanguageSettingsPage.objSelectedLang, "Default Language");
		if (selectedLangInDisplayScreen.contains("checkedHighlight")) {
			String selectedDisplayLang = getElementPropertyToString("innerText",
					PWALanguageSettingsPage.objLanguage("Kannada"), "Language");
			extent.extentLogger("Selected display language", selectedDisplayLang + " is selected in display screen ");
			logger.info(selectedDisplayLang + " is selected from display screen");
		} else {
			extent.extentLoggerFail("Select display language", "Unable to select the display language");
			logger.info("Unable to select the display language");
		}
		// Verify the selected language is applied in home page
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		boolean staleElement = true;
		while (staleElement) {

			try {
				Thread.sleep(5000);
				verifyElementPresent(PWALanguageSettingsPage.objTrayHeader, "Tray title");

			} catch (StaleElementReferenceException e) {
				staleElement = true;
			}

			String trayHeader = getElementPropertyToString("class", PWALanguageSettingsPage.objTrayHeader,
					"Tray header");
			staleElement = false;
			if (trayHeader.contains("kn_regionalLang")) {
				softAssert.assertEquals(trayHeader.contains("kn_regionalLang"), true,
						"The selected display language is applied");
				extent.extentLogger(" Verify selected display Languge",
						"The selected display langguage is applied successfully");
				logger.info("The selected display language is applied successfully");
			} else {
				softAssert.assertAll();
				extent.extentLoggerFail(" Verify selected display Languge",
						"The selected display langguage is not applied successfully");
				logger.info("The selected display language is not applied successfully");
			}
		}
		// Click on Language button
		partialScroll();
		partialScroll();
		JSClick(PWAHomePage.objLanguageBtn, "Language button");
		// Select English
		JSClick(PWALanguageSettingsPage.objEnglishLang, "English language");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		JSClick(PWALanguageSettingsPage.objApplyBtn, "Apply button");
		Thread.sleep(5000);
		partialScroll();
		Thread.sleep(5000);
		partialScroll();
		Thread.sleep(5000);
		boolean staleElement1 = true;
		while (staleElement1) {
			try {

				JSClick(PWAHomePage.objLanguageBtn, "Language button");
				staleElement1 = false;
			} catch (StaleElementReferenceException e) {
				staleElement1 = true;
			}
		}

		// Click on Content language button
		JSClick(PWAHamburgerMenuPage.objContentLanguage, "Content language");
		// Verify user is navigated to Content Language screen post tapping content
		// language option
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
				.contains("headerSelected")) {
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
							.contains("headerSelected"),
					true, "Content language screen is displayed on tapping content language option");
			extent.extentLogger("Verify Content language screen is displayed",
					"Content screen is displayed on tapping Content language option");
			logger.info("Content screen is displayed on tapping Content language option");
		} else {
			softAssert.assertAll();
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
							.contains("headerSelected"),
					false, "Content language screen is not displayed on tapping content language option");
			extent.extentLoggerFail("Verify Content language screen is displayed",
					"Content language screen is not displayed on tapping content language option");
			logger.info("Content language screen is not displayed on tapping Content language option");
		}
		int sele = findElements(PWALanguageSettingsPage.objSelectedLang).size();
		for (int i = 0; i <= sele; i++) {
			Thread.sleep(2000);
			click(PWALanguageSettingsPage.objSelectedLang, "Selected language");
		}
//			JSClick(PWALanguageSettingsPage.objSelectedLang, "Selected language");
//			Thread.sleep(2000);
//			JSClick(PWALanguageSettingsPage.objSelectedLang, "Selected language");

		// Verify user can select multiple Content languages

		for (int i = 1; i <= 3; i++) {
			String language = getElementPropertyToString("innerText", PWALanguageSettingsPage.objAllLangByindex(i),
					"Language");
			Thread.sleep(1000);
			click(PWALanguageSettingsPage.objAllLangByindex(i), language + " Language");
		}
		Thread.sleep(5000);
		int size = getWebDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
		if (size > 1) {
			softAssert.assertEquals(size > 1, true, "User can select multiple languages");
			extent.extentLogger("Selected content languages : ", "Selected content languages : " + size);
			extent.extentLogger("Verify user can select multiple content languages",
					"User can select multiple Content languages");
			logger.info("User can select multiple Content languages");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify user can select multiple content languages",
					"User can not select multiple Content languages");
			logger.info("User can not select multiple Content languages");
		}

		// Verify user should not be able to apply the changes if he deselect all the
		// language.
		int selectedlang = getWebDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
		for (int i = 1; i <= selectedlang; i++) {
			click(PWALanguageSettingsPage.objSelectedLang, "Selected language");
		}

		// Verify apply button is disabled
		String disabledApplyBtn = getElementPropertyToString("class", PWALanguageSettingsPage.objDisabledApplyButton,
				"Apply button");
		if (disabledApplyBtn.contains("disable")) {
			softAssert.assertEquals(disabledApplyBtn.contains("disable"), true,
					"User can not apply changes if he deselect all the content languages");
			extent.extentLogger("Verify Content language screen",
					"User can not apply changes if he deselect all the content languages");
			logger.info("User can not apply changes if he deselect all the content languages");
		} else {
			softAssert.assertEquals(disabledApplyBtn.contains("disable"), false,
					"User can apply changes if he deselect all the content languages");
			extent.extentLogger("Verify Content language screen",
					"User can apply changes if he deselect all the content languages");
			logger.info("User can apply changes if he deselect all the content languages");
		}

		// Verify User able to Switch to Content Language section from Display Language
		// and Content language
		// click on Display language
		JSClick(PWAHamburgerMenuPage.objDisplayLang, "Display language");
		// Verify user is navigated to display screen
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
				.contains("headerSelected")) {
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
							.contains("headerSelected"),
					true, "Display screen is displayed on tapping language option");
			extent.extentLogger("Verify Display language screen is displayed",
					"Display screen is displayed on tapping language option");
			logger.info("Display screen is displayed on tapping language option");
		} else {
			softAssert.assertAll();
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language")
							.contains("headerSelected"),
					false, "Display screen is not displayed on tapping language option");
			extent.extentLogger("Verify Display language screen is displayed",
					"Display screen is nt displayed on tapping language option");
			logger.info("Display screen is not displayed on tapping language option");
		}
		// Verify user is navigated to content language screen post tapping content
		// language screen
		JSClick(PWAHamburgerMenuPage.objContentLanguage, "Content language");
		if (getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
				.contains("headerSelected")) {
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
							.contains("headerSelected"),
					true, "Content language screen is displayed on tapping content language option");
			extent.extentLogger("Verify Content language screen is displayed",
					"Content screen is displayed on tapping Content language option");
			logger.info("Content screen is displayed on tapping Content language option");
		} else {
			softAssert.assertAll();
			softAssert.assertEquals(
					getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language")
							.contains("headerSelected"),
					false, "Content language screen is not displayed on tapping content language option");
			extent.extentLoggerFail("Verify Content language screen is displayed",
					"Content language screen is not displayed on tapping content language option");
			logger.info("Content language screen is not displayed on tapping Content language option");
		}

		// Verify user can close the pop up by clicking anywhere in the application
		// click on home button
		if (userType.equals("NonSubscribedUser")) {
			getWebDriver().findElement(By.xpath("//body")).click();
		} else {
			getWebDriver().findElement(By.xpath("//html")).click();
		}
//			 JSClick(PWALanguageSettingsPage.objRandomClick, "Random clicking");

		// Verify the Language pop up
		if (verifyElementExist(PWAHamburgerMenuPage.objDisplayLang, "Language Pop up") == false) {
			logger.info("Langugae Pop up is closed after clicking anywhere on the application");
			extent.extentLogger("Verify Pop up",
					"Langugae Pop up is closed after clicking anywhere on the application");
		} else {
			logger.info("Langugae Pop up did not closed after clicking anywhere on the application");
			extent.extentLoggerFail("Verify Pop up",
					"Langugae Pop up did not closed after clicking anywhere on the application");
			JSClick(PWAHomePage.objLanguageBtn, "Language button");
		}

		getWebDriver().get(URL);

	}

	/**
	 * ================================VINAY
	 * Kaltura==================================
	 * 
	 */

	public void Kaltura(String userType) throws Exception {
		extent.HeaderChildNode("Kaltura Playability");
		String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie4");
		searchvideoandselect(keyword1, userType, "Movie");

		Thread.sleep(2000);
		String keyword2 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("tvshow");
		searchvideoandselect(keyword2, userType, "ZEE5 Originals");

		Thread.sleep(2000);
		String keyword3 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("trailerOfPremiumMovie");
		searchvideoandselect(keyword3, userType, "trailer");

		Thread.sleep(2000);
		String keyword4 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("music");
		searchvideoandselect(keyword4, userType, "Music");

		Thread.sleep(2000);
		String keyword5 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeEpisode4");
		searchvideoandselect(keyword5, userType, "show");

		Thread.sleep(2000);
		String keyword6 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("news");
		searchvideoandselect(keyword6, userType, "news");

		String Value = null;
		waitTime(5000);

		partialScroll();
		partialScroll();

		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHomePage.objMoreMenuBtn);
		actions.moveToElement(menuOption).perform();
		// click(PWAHomePage.objTabName("Live TV"), "Live TV Tab");
		click(PWAHomePage.objLiveTVtab, "Live TV Tab");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		System.out.println("Selecting Free content from LIVETV tray and validating Kaltura playability");
		extent.extentLogger("Selecting Free content from LIVETV tray and validating Kaltura playability",
				"Selecting Free content from LIVETV tray and validating Kaltura playability");
		Value = checkPremiumORFreeFromLIVETVPageTrayAndSelect("FREE Channels", "FREE");
		System.out.println(Value);
		waitTime(5000);
		System.out.println("Selected Tumbnail Value : " + Value);
		if (Value != null) {
			PremiumFreeZeeOriginalKaltura(Value, userType);
		} else {
			System.out.println("No Tray");
			getWebDriver().get(URL);
		}

	}

	public void searchvideoandselect(String str, String userType, String type) throws Exception {
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, str, "Search Field");
		Thread.sleep(3000);
		if (type.equals("Movie")) {
			click(PWASearchPage.objSearchMoviesTab, "Movies tab");
		}
		Thread.sleep(3000);
		waitTime(3000);
		if (type.equalsIgnoreCase("news")) {
			click(PWASearchPage.objSearchNewsTab, "SearchNewsTab");
			Thread.sleep(1000);
			click(PWASearchPage.objfirstdata, "data");
		} else {

			click(PWASearchPage.objspecificSearch, "Searched content");
		}

		Thread.sleep(5000);
		if (type.equals("ZEE5 Originals") || type.equals("Music")) {
			Thread.sleep(5000);
			if (type.equals("Music")) {
				if (verifyElementExist(PWAPlayerPage.objWhyRegister, "Why Register Popup")) {
					click(PWAPlayerPage.objCloseRegisterDialog, "Close button");
				}
			}

			Actions actions = new Actions(getWebDriver());
			WebElement menuOption = getWebDriver().findElement(PWAHomePage.objMoreMenuBtn);
			actions.moveToElement(menuOption).perform();
			System.out.println("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
			extent.extentLogger("Navigated to tab : " + getText(PWAHomePage.objSeletedTab),
					"Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
			logger.info("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));

		} else if (type.equals("trailer")) {
			extent.extentLogger("Verify Navigation", "User is navigated to Trailer consumption screen");
			logger.info("User is navigated to Trailer consumption screen");
		} else {
			System.out.println("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
			extent.extentLogger("Navigated to tab : " + getText(PWAHomePage.objSeletedTab),
					"Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
			logger.info("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
		}

		if (type.equals("ZEE5 Originals")) {
			if (verifyElementExist(PWAHomePage.objKalGetPremium, "Get Premium")) {
				click(PWAHomePage.objKalGetPremiumPlayicon, "Play Button");
			}
		}

		if (userType.equals("SubscribedUser")) {
			if (verifyElementExist(PWAHomePage.objKalGetFirstEpisode, "Get First Episode")) {
				click(PWAHomePage.objKalGetFirstEpisodePlayicon, "Play Button");
			}
		}

		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
		}

		if (userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt,
					"Complete Your Profile pop up")) {
				click(CompleteYourProfilePopUp.objCloseBtn, "Close Button");
			}
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
		}

		if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
			extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");
		} else {
			extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
		}
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			waitForPlayerAdToComplete("Video Player");
		}
		pausePlayer();
		if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
			System.out.println("Video is playing");
			extent.extentLogger("Verify video Playability", "User is able to play video");
			logger.info("User is able to play video");
		} else {
			System.out.println("Video is not playing");
			extent.extentLoggerFail("Verify video Playability", "Playback video failed");
			logger.info("Video playback failed");
		}

//		if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
//			click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
//		}
//		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
//			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//		}
		if (!verifyElementExist(PWAPlayerPage.objprogressBar, "Progress bar")) {
			logger.info("Progress bar for LiveTv is not present");
			extent.extentLogger("Progress bar", "Progress bar for LiveTv is not present");
		}
		getWebDriver().get(URL);

	}

	public String checkPremiumORFreeFromLIVETVPageTrayAndSelect(String str, String premiumORfree) throws Exception {
//		try {
//			ScrollToTheElement(TextToXpath(str));
//			waitTime(5000);
//			ScrollToTheElement(TextToXpath(str));
//			waitTime(8000);
//		} catch (Exception e) {
//			//Swipe("UP", 1);
//			ScrollToTheElementWEB(TextToXpath(str));
//			waitTime(8000);
//			//ScrollToTheElement(TextToXpath(str));
//		}
		String ValueOfPremiumTumbnail = null;
		System.out.println("Check premium and select");
		List<WebElement> tumnails = getWebDriver()
				.findElements(By.xpath("(((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
						+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']"));
		System.out.println("Number of Tumbnails : " + tumnails.size());
		boolean flag = false;
		for (int j = 1; j <= 5; j++) {
			for (int i = 1; i <= tumnails.size(); i++) {
				WebElement specificTumbnail = getWebDriver().findElement(
						By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
								+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//a[@class='noSelect content'])["
								+ i + "])"));
				if (verifyElementExist(PWAHomePage.objLIVETVIsPremiumTumbnail(str, i), "Premium") == true) {
					System.out.println("premium Tumbnail");
					// System.out.println(getAttributValue("title",
					// PWAHomePage.objTumbnailTitle(str, i)));
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objLIVETvTumbnailTitle(str, i));
					System.out.println("Premium LIVETV Tumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("PREMIUM")) {
						clickByElement(specificTumbnail, "Specific Tumbnail from Premium");
						flag = true;
						break;
					}
				} else if (verifyElementExist(PWAHomePage.objLIVETVIsPremiumTumbnail(str, i), "Premium") == false) {
					System.out.println("No premium Tumbnail");
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objLIVETvTumbnailTitle(str, i));
					System.out.println("Non-Premium LIVETV Tumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("FREE")) {
						clickByElement(specificTumbnail, "Specific Tumbnail from Free");
						flag = true;
						break;
					}
				}
			}
			if (flag == true) {
				break;
			}
			swipeLIVETVTumbnailToLeft(str);
		}
		return ValueOfPremiumTumbnail;
	}

	public void PremiumFreeZeeOriginalKaltura(String Value, String userType) throws Exception {
		// Thread.sleep(10000);
		if (verifyElementExist(PWAHomePage.objKalGetPremium, "Get Premium") == true) { // zee originals
																						// guest&nonSubscribed
			System.out.println("Navigated to Title : " + getText(PWAHomePage.objKalGetTitle));
			extent.extentLogger("Navigated to Title : " + getText(PWAHomePage.objKalGetTitle),
					"Navigated to Title : " + getText(PWAHomePage.objKalGetTitle));
			waitTime(5000);
			click(PWAHomePage.objKalGetPremiumPlayicon, "Play Button");
			waitTime(8000);
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn") == true) {
					click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				}
			}
//				waitForPlayerAdToComplete("Video Player");
//				pausePlayer();
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
				}
			}
			waitTime(5000);
			if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
				extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");

				if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
					waitForPlayerAdToComplete("Video Player");
				}
				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}
				if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
					System.out.println("Video is playing");
					extent.extentLogger("User is able to play video", "User is able to play video");
					logger.info("User is able to play video");
				} else {
					System.out.println("Video is not playing");
					extent.extentLoggerFail("Playback video failed", "Playback video failed");
					logger.info("Video playback failed");
				}

			} else {
				extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
			}
			System.out.println("Playing Episode : " + getText(PWAHomePage.objKalconsumptionMetaDiv));
			System.out.println("Type :" + getText(PWAHomePage.objKalconsumptionMetainfo));
		} else if (verifyElementExist(PWAHomePage.objKalGetFirstEpisode, "Get First Episode") == true) { // zee
																											// originals
																											// subscribed
			System.out.println("Navigated to Title : " + getText(PWAHomePage.objKalGetTitle));
			extent.extentLogger("Navigated to Title : " + getText(PWAHomePage.objKalGetTitle),
					"Navigated to Title : " + getText(PWAHomePage.objKalGetTitle));
			waitTime(5000);
			click(PWAHomePage.objKalGetFirstEpisodePlayicon, "Play Button");
			waitTime(8000);
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn") == true) {
					click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				}
			}
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
				}
			}
			waitTime(5000);
			if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
				extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");

				if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
					waitForPlayerAdToComplete("Video Player");
				}
				if (BROWSER.equals("Chrome")) {
					pausePlayer();
				} else {
					firefoxpause();
				}
				if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
					System.out.println("Video is playing");
					extent.extentLogger("User is able to play video", "User is able to play video");
					logger.info("User is able to play video");
				} else {
					System.out.println("Video is not playing");
					extent.extentLoggerFail("Playback video failed", "Playback video failed");
					logger.info("Video playback failed");
				}

			} else {
				extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
			}
			System.out.println("Playing Episode : " + getText(PWAHomePage.objKalconsumptionMetaDiv));
			System.out.println("Type :" + getText(PWAHomePage.objKalconsumptionMetainfo));
		} else { // MOVIE
			waitTime(8000);
			waitTime(5000);
			if (userType.equals("Guest")) {
				if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn") == true) {
					click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				}
			}
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
				}
			}
			// FOR MOVIE
			if (verifyElementExist(PWAHomePage.objPlaybackMovieTitle(Value), "Title") == true) {
				System.out.println("Navigated to Title : " + getText(PWAHomePage.objPlaybackMovieTitle(Value)));
				extent.extentLogger("Navigated to Title : " + getText(PWAHomePage.objPlaybackMovieTitle(Value)),
						"Navigated to Title : " + getText(PWAHomePage.objPlaybackMovieTitle(Value)));
				if (verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link") == true) { // guest
																													// or
																													// nonsubscribed
					waitTime(5000);
					if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
						extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");

						if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
							waitForPlayerAdToComplete("Video Player");
						}
						if (BROWSER.equals("Chrome")) {
							pausePlayer();
						} else {
							firefoxpause();
						}
						if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
							System.out.println("Video is playing");
							extent.extentLogger("User is able to play video", "User is able to play video");
							logger.info("User is able to play video");
						} else {
							System.out.println("Video is not playing");
							extent.extentLoggerFail("Playback video failed", "Playback video failed");
							logger.info("Video playback failed");
						}

					} else {
						extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
					}
					System.out.println("Playing Movie: " + getText(PWAHomePage.objKalconsumptionMetaDiv));
					System.out.println("Type :" + getText(PWAHomePage.objKalconsumptionMetainfo));
				} else { // subscribed
					waitTime(5000);
					if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
						extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");

						if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
							waitForPlayerAdToComplete("Live Player");
						}
						if (BROWSER.equals("Chrome")) {
							pausePlayer();
						} else {
							firefoxpause();
						}
						if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
							System.out.println("Video is playing");
							extent.extentLogger("User is able to play video", "User is able to play video");
							logger.info("User is able to play video");
						} else {
							System.out.println("Video is not playing");
							extent.extentLoggerFail("Playback video failed", "Playback video failed");
							logger.info("Video playback failed");
						}

					} else {
						extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
					}
					System.out.println("Playing Movie: " + getText(PWAHomePage.objKalconsumptionMetaDiv));
					System.out.println("Type :" + getText(PWAHomePage.objKalconsumptionMetainfo));
				}
			}
			// FOR LIVETV
			if (verifyElementExist(PWAHomePage.objPlaybackLIVETVTitle1, "LiveTV title") == true) {
				System.out.println("Navigated to Title : " + getText(PWAHomePage.objPlaybackLIVETVTitle1));
				extent.extentLogger("Navigated to Title : " + getText(PWAHomePage.objPlaybackLIVETVTitle1),
						"Navigated to Title : " + getText(PWAHomePage.objPlaybackLIVETVTitle1));
				if (verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link") == true) { // guest
																													// or
																													// nonsubscribed
					waitTime(5000);
					if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
						extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");

						if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
							waitForPlayerAdToComplete("Video Player");
						}
						if (BROWSER.equals("Chrome")) {
							pausePlayer();
						} else {
							firefoxpause();
						}
						if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
							System.out.println("Video is playing");
							extent.extentLogger("User is able to play video", "User is able to play video");
							logger.info("User is able to play video");
						} else {
							System.out.println("Video is not playing");
							extent.extentLoggerFail("Playback video failed", "Playback video failed");
							logger.info("Video playback failed");
						}

					} else {
						extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
					}
					System.out.println("Playing : " + getText(PWAHomePage.objKalLivetvPlaying));
					System.out.println("Channel :" + getText(PWAHomePage.objKalLivetvChannel));
				} else { // subscribed
					waitTime(5000);
					if (verifyElementExist(PWAHomePage.objKalKalturaPlayer, "Kaltura Player")) {
						extent.extentLogger("Navigated to Kaltura Player", "Navigated to Kaltura Player");

						if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
							waitForPlayerAdToComplete("Video Player");
						}
						if (BROWSER.equals("Chrome")) {
							pausePlayer();
						} else {
							firefoxpause();
						}
						if (verifyElementExist(PWAPlayerPage.playBtn, "Play icon")) {
							System.out.println("Video is playing");
							extent.extentLogger("User is able to play video", "User is able to play video");
							logger.info("User is able to play video");
						} else {
							System.out.println("Video is not playing");
							extent.extentLoggerFail("Playback video failed", "Playback video failed");
							logger.info("Video playback failed");
						}

					} else {
						extent.extentLoggerFail("Not Navigated to Kaltura Player", "Not Navigated to Kaltura Player");
					}
					System.out.println("Playing : " + getText(PWAHomePage.objKalLivetvPlaying));
					System.out.println("Channel :" + getText(PWAHomePage.objKalLivetvChannel));
				}
			}
		}
		waitTime(3000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn") == true) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}
		}
		Thread.sleep(3000);
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
		}
		try {
			// click(By.xpath("//div[@class='zeeLogo noSelect']//child::*"), "Zee Logo");
			getWebDriver().get(URL);
		} catch (Exception e) {
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				if (verifyElementExist(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register popup close btn") == true) {
					click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
				}
			}
			if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
				}
			}
			click(By.xpath("//div[@class='zeeLogo noSelect']//child::*"), "Zee Logo");
		}
		waitTime(3000);
	}

	public void checkDurationInLivetv(String userType) throws Exception {
		extent.HeaderChildNode("checkDurationInLivetv");
		waitTime(2000);
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("livetv");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(8000);
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSpecificSearch1(keyword), "Searched Show");
		waitTime(10000);
		waitForPlayerAdToComplete("Live Player");
		pausePlayer();
		if (verifyElementExist(PWAPlayerPage.objcurrenttime, "Current time")) {
			extent.extentLoggerFail("Current time is displayed", "Current time is displayed");
			logger.info("Current time is displayed");
		} else {
			extent.extentLogger("Current time is not displayed", "Current time is not displayed");
			logger.info("Current time is not displayed");
		}
		if (verifyElementExist(PWAPlayerPage.objtotaltime, "Total time")) {
			extent.extentLoggerFail("Total time is displayed", "Total time is displayed");
			logger.info("Total time is displayed");
		} else {
			extent.extentLogger("Total time is not displayed", "Total time is not displayed");
			logger.info("Total time is not displayed");
		}
	}

	public void checkDurationandProgressVideo(String userType) throws Exception {
		extent.HeaderChildNode("checkDurationandProgressVideo");
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie4");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(2000);
		click(PWASearchPage.objSearchMoviesTab, "Movies tab");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		Thread.sleep(10000);
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Why Register Popup") == true) {
				click(PWAPlayerPage.objCloseBtn, "Close Register Popup");
			}
		}
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		// Content elapsed time should update with the content playback
		if (userType.equals("Guest")) {
			String currentDuration = getText(PWAPlayerPage.objcurrenttime);
			System.out.println("Current time: " + currentDuration);
		} else {
			String currentDuration = getText(PWAPlayerPage.objcurrenttime);
			System.out.println("Elapsed time: " + currentDuration);
		}
		String totalDuration = getText(PWAPlayerPage.objtotaltime);
		System.out.println("Total time: " + totalDuration);
		String progress = null;
		if (verifyElementExist(PWAPlayerPage.objprogressBar, "ProgressBar")) {
			progress = getAttributValue("style", PWAPlayerPage.objprogressProgress);
			System.out.println("Progress : " + progress);
		}
		Thread.sleep(5000);
		verifyElementPresentAndClick(PWAPlayerPage.forward10SecBtn, "10 sec forward");
		verifyElementPresentAndClick(PWAPlayerPage.playBtn, "Play btn");
		// Waiting for some time
		Thread.sleep(10000);
		Thread.sleep(10000);
		System.out.println("Waited for 5 sec");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		String currentDuration1 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time: " + currentDuration1);
		String totalDuration1 = getText(PWAPlayerPage.objtotaltime);
		System.out.println("Total time: " + totalDuration1);
		String progress1 = getAttributValue("style", PWAPlayerPage.objprogressProgress);
		System.out.println("Progress : " + progress1);
		// Validate the availability and functionality of progress bar button
		if (!progress.equals(progress1)) {
			System.out.println("Progress Bar is functional");
			extent.extentLogger("Progress Bar is functional", "Progress Bar is functional");
			logger.info("Progress Bar is functional");
		} else {
			System.out.println("Progress Bar is not functional");
			extent.extentLoggerFail("Progress Bar is not functional", "Progress Bar is not functional");
			logger.info("Progress Bar is not functional");
		}
	}

	public void swipeLIVETVTumbnailToLeft(String str) throws InterruptedException {
		try {
			WebElement sourceLocator = getWebDriver().findElement(
					By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
							+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//div[@class='noSelect content'])[3])"));
			WebElement targetLocator = getWebDriver().findElement(
					By.xpath("(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
							+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//div[@class='noSelect content'])[2])"));
			Thread.sleep(4000);
			Actions action = new Actions(getWebDriver());
			action.dragAndDrop(sourceLocator, targetLocator).build().perform();
		} catch (Exception e) {
			System.out.println("No trays to swipe");
		}
	}

	/**
	 * ================================SUSHMA
	 * MoviePage==================================
	 * 
	 */

	public void Moviepage(String usertype, String Tabname) throws Exception {

		landingPagesValidationMovie(Tabname, usertype);
		landingPagesTrailerAndPopUpValidationMovie(usertype, Tabname);
		trayTitleAndContentValidationWithApiDataMovie(Tabname, "movies");
		premiumAndFreeMovie(usertype, Tabname);

	}

	public void landingPagesValidationMovie(String tabName, String userType) throws Exception {
		extent.HeaderChildNode(tabName + " Page Validation");
		waitTime(15000);

		getWebDriver()
				.findElement(By.xpath("(//a[contains(@class,'noSelect')][contains(text(),'" + tabName + "')])[1]"))
				.click();
		waitTime(7000);

		if (verifyElementExist(PWAHomePage.objActiveTab, "Active tab")) {

			String tab = getText(PWAHomePage.objActiveTab);
			System.out.println(tab);
			logger.info(tab + " tab is highlighted");
			extent.extentLogger("Tab", tab + " tab is highlighted");
		} else {

			Actions actions = new Actions(getWebDriver());
			WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
			actions.moveToElement(tab).build().perform();
			String tab2 = getText(PWAHomePage.objActiveTab);
			System.out.println(tab2);
			logger.info(tab2 + " tab is highlighted");
			extent.extentLogger("Tab", tab2 + " tab is highlighted");
		}

		// check if tray is loaded

		for (int i = 1; i <= 2; i++) {
			if (verifyElementExist(PWAPremiumPage.objTrayTitle(i), "Tray")) {
				System.out.println("Tray is loaded for " + i + " scroll");
				logger.info("Tray is loaded for " + i + " scroll");
				extent.extentLogger("Tray load", "Tray is loaded for " + i + " scroll");
			} else {
				ScrollToTheElement(PWAPremiumPage.objTrayTitle(i));
				verifyElementExist(PWAPremiumPage.objTrayTitle(i), "Tray");
			}
		}

		// partialScrollDown();

		verifyElementPresentAndClick(PWAPremiumPage.objNextArrowBtn, "Next Arrow Button");

		if (verifyElementExist(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button")) {
			logger.info("Tray is rotated");
			extent.extentLogger("Tray is rotated", "Tray is rotated");
		} else {
			logger.info("Tray is not rotated");
			extent.extentLogger("Tray is not rotated", "Tray is not rotated");

		}

		click(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button");

		if (verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");

			if (verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}

		Back(1);

		waitTime(2000);

		for (int i = 0; i < 5; i++) {
			if (findElements(PWAPremiumPage.objMinuteContent).size() > 0) {
				logger.info("Minute content is displayed");
				extent.extentLogger("Minute content", "Minute content is displayed");
				break;

			} else {
				logger.info("Minute content is not displayed");
				extent.extentLogger("Minute content", "Minute content is not displayed");
				partialScrollDown();
			}

		}

//		dismissDisplayContentLanguagePopUp();
		waitTime(5000);
		partialScrollDown();
		partialScrollDown();
		Back_TO_TopArrow_Web(userType);

		for (int i = 0; i < 5; i++) {
			if (findElements(PWAMusicPage.objPremiumTag).size() > 0) {
				logger.info("Premium tag is displayed");
				extent.extentLogger("Premium Tag", "Premium Tag is displayed");
				break;

			} else {
				logger.info("Premium tag is not displayed");
				extent.extentLogger("Premium Tag", "Premium Tag is not displayed");
				partialScrollDown();
			}

		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void landingPagesTrailerAndPopUpValidationMovie(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Landing Page Carousel Validation");

		waitTime(5000);

		getWebDriver()
				.findElement(By.xpath("(//a[contains(@class,'noSelect')][contains(text(),'" + tabName + "')])[1]"))
				.click();
		waitTime(7000);
		// verifyElementPresentAndClick(PWAHomePage.objTabName(tabName), tabName);
		// waitTime(5000);
		waitForElementDisplayed(PWAZee5OriginalPage.objWEBMastheadCarousel, 10);
		verifyElementExist(PWAZee5OriginalPage.objWEBMastheadCarousel, "Carousel Card");
		mandatoryRegistrationPopUp(userType);
		click(PWAZee5OriginalPage.objWEBMastheadCarousel, "Carousel Card");
		waitTime(5000);
		if (BROWSER.equals("Firefox")) {
			waitForPlayerLoaderToComplete();
		}
		if (verifyElementExist(PWAPlayerPage.objPlayer, "player screen")) {
			logger.info("User is navigated to consumption page after tapping on content in listed collection");
			extent.extentLogger("Consumption page",
					"User is navigated to consumption page after tapping on content in listed collection");
		}

		if (userType.contains("NonSubscribedUser") || (userType.contains("Guest"))) {

			if (verifyElementExist(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
				mandatoryRegistrationPopUp(userType);
				JSClick(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button");
				waitTime(10000);
				if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
					verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");

				}
			} else {
				logger.info("Trailer is not available for the selected content");
				extent.extentLogger("Trailer", "Trailer is not available for the selected content");
				if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
					verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
				}
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void trayTitleAndContentValidationWithApiDataMovie(String tab, String api) throws Exception {
		extent.HeaderChildNode(tab + " page tray asset validation");
		waitTime(5000);
		getWebDriver().findElement(By.xpath("(//a[contains(@class,'noSelect')][contains(text(),'" + tab + "')])[1]"))
				.click();
		// waitTime(7000);
		// navigateToAnyScreenOnWeb(tab);
		waitForElementDisplayed(PWAMusicPage.objPremiumTag, 30);
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);
		Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		List<String> contentList = resp.jsonPath().getList("buckets[1].items");
		System.out.println(contentList.size());
		partialScrollDown();
		for (int i = 0; i < 5; i++) {
			String titles = resp.jsonPath().getString("buckets[1].items[" + i + "].title");
			// System.out.println("Api data " +titles);
			logger.info("Api data " + titles);
			extent.extentLogger("Api data ", "Api data " + titles);
			apiTitleList.add(titles);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver()
					.findElement(By.xpath("(//div[@class='slick-list']//div[@class='content'])[" + (i + 1) + "]"));
			actions.moveToElement(contentCard).build().perform();
			String trayTitle = apiTitleList.get(i);
			logger.info("UI data " + titles);
			extent.extentLogger("UI data ", "UI data " + titles);
			if (trayTitle.equalsIgnoreCase(apiTitleList.get(i))) {
				logger.info("Metadata on the content card is validated with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is validated with Api data");
			} else {
				logger.info("Metadata on the content card is not validated with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is not validated with Api data");
			}
			waitTime(1000);
			verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Button");
			waitTime(1000);
			verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Button");
			waitTime(1000);
			verifyElementEnabled(PWAPremiumPage.objContentCardWatchlistBtn, "Add to Watchlist Button");
		}
	}

	public void premiumAndFreeMovie(String userType, String Tab) throws Exception {
		extent.HeaderChildNode("Verifing movies premium content");
		navigateToAnyScreenOnWeb("Movies"); // changed
		// navigateToAnyScreenOnWeb("Movies");
		Actions actions = new Actions(getWebDriver());
		WebElement contentCard = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
		actions.moveToElement(contentCard).build().perform();

		waitForElementDisplayed(PWAMoviesPage.objPremiumContentCard, 30);
		verifyElementExist(PWAMoviesPage.objPremiumContentCard, "PremiumContent");
		mandatoryRegistrationPopUp(userType);
		JSClick(PWAMoviesPage.objPremiumContentCard, "PremiumContent");
		if (userType.equalsIgnoreCase("Guest") || userType.equalsIgnoreCase("NonSubscribedUser")) {
			// getWebDriver().get("https://newpwa.zee5.com/kids/kids-movies/ramayana/0-0-72648");
			// // changed
			if (BROWSER.equals("Chrome")) {
				if (verifyElementPresent(PWAPlayerPage.objPlayerscreen, "Playback Overlay")) {
					// waitForPlayerLoaderToComplete();
					waitTime(5000);
					moviePausePlayer();
					WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
					waitTime(2000);
					Actions move = new Actions(getWebDriver());
					Action action = (Action) move.dragAndDropBy(scrubber, 580, 0).build();
					action.perform();
					JSClick(PWAPlayerPage.objPlayerPlay, "Play icon");
				}
			} else {
				waitForPlayerLoaderToComplete();
				if (verifyElementPresent(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay")) {
					firefoxpause();
					WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
					waitTime(10000);
					Actions move = new Actions(getWebDriver());
					Action action = (Action) move.dragAndDropBy(scrubber, 610, 0).build();
					action.perform();
					click(PWAPlayerPage.objPlayerPlay, "Play icon");
				}
			}
		}
//		JSClick(PWAMoviesPage.objPremiumContentCard, "PremiumContent"); // changed
		extent.HeaderChildNode("Verifing that premium content videos in landscape mode");
		waitTime(15000);
		if (verifyElementExist(PWAPremiumPage.objPremiumPopUp, "Premium PopUp")) {
			verifyElementPresentAndClick(PWAPremiumPage.objClosePremiumPopup, "Premium PopUp Close icon");
			logger.info(
					"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
			extent.extentLogger("Maximize icon",
					"Maximize icon is not displayed since user is getting Player inline Subscription link on Player screen");
			Back(2);
		} else {
			waitTime(3000);
			// waitForPlayerAdToComplete2("Video Player");
			if (BROWSER.equals("Chrome")) {
				if (verifyElementPresent(PWAPlayerPage.objPlayerscreen, "Playback Overlay")) {
					moviePausePlayer();
				}
			} else {
				waitForPlayerLoaderToComplete();
				if (verifyElementPresent(PWAPlayerPage.objPlayerscreen, "Playback Overlay")) {
					firefoxpause();
				}
			}
			click(PWAPlayerPage.maximizeBtn, "Maximize icon");
			for (int i = 0; i < 5; i++) {
				if (verifyElementExist(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
					if (userType.equalsIgnoreCase("Guest") || userType.equalsIgnoreCase("NonSubscribedUser")) {
						logger.info("User is able to watch Trailer for premium content in landscape mode");
						extent.extentLogger("Landscape mode", "User is able to watch Trailer for premium content in landscape mode");
					}else {
						logger.info("User is able to watch premium content in landscape mode");
						extent.extentLogger("Landscape mode", "User is able to watch premium content in landscape mode");
					}
					
					waitTime(3000);
					click(PWAPlayerPage.minimizeBtn, "Minimize icon");
					break;
				} else {
					click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
				}
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			navigateToAnyScreenOnWeb("Movies");
			waitForElementDisplayed(PWAHomePage.objHighlightedTab("Movies"), 10);
		}
		waitTime(5000);
		extent.HeaderChildNode("Verifing free movie content");
		mandatoryRegistrationPopUp(userType);
		chkPremiumORFreeFromVideosTabAndSelect("Trending Movies", "FREE", userType);
		waitTime(3000);
		if (userType.equalsIgnoreCase("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objRegisterPopUp, "Register PopUp")) {
				verifyElementPresentAndClick(PWAPlayerPage.objCloseRegisterDialog, "Register popup close icon");
			}
		}
		if (userType.equalsIgnoreCase("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objAdultView, "AdultContent")) {
				logger.info("Maximize icon is not displayed since the content is adult view");
				extent.extentLogger("Maximize icon", "Maximize icon is not displayed since the content is adult view");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			} else {
				waitForPlayerAdToComplete2("Video Player");
				if (BROWSER.equals("Chrome")) {
					moviePausePlayer();
				} else {
					if (verifyElementPresent(PWAPlayerPage.objPlayerscreen, "Playback Overlay")) {
						firefoxpause();
					}
				}
				extent.HeaderChildNode("Verifing free movie content in landscape");
				click(PWAPlayerPage.maximizeBtn, "Maximize icon");
				for (int i = 0; i < 5; i++) {
					if (verifyElementExist(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
						logger.info("User is able to watch free content in landscape mode");
						extent.extentLogger("Landscape mode", "User is able to watch free content in landscape mode");
						waitTime(3000);
						click(PWAPlayerPage.minimizeBtn, "Minimize icon");
						break;
					} else {
						click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
					}
				}
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
			}
		} else {
			waitForPlayerAdToComplete2("Video Player");
			if (BROWSER.equals("Chrome")) {
				moviePausePlayer();
			} else {
				if (verifyElementPresent(PWAPlayerPage.objPlayerscreen, "Playback Overlay")) {
					firefoxpause();
				}
			}
			extent.HeaderChildNode("Verifing free movie content in landscape");
			click(PWAPlayerPage.maximizeBtn, "Maximize icon");
			for (int i = 0; i < 5; i++) {
				if (verifyElementExist(PWAPlayerPage.minimizeBtn, "Minimize icon")) {
					logger.info("User is able to watch free content in landscape mode");
					extent.extentLogger("Landscape mode", "User is able to watch free content in landscape mode");
					waitTime(3000);
					click(PWAPlayerPage.minimizeBtn, "Minimize icon");
					break;
				} else {
					click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
				}
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
		}
	}

	/**
	 * Method to Pause the Player
	 */
	public boolean moviePausePlayer() throws InterruptedException {
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			try {
				waitTime(3000);
				Actions actions = new Actions(getWebDriver());
				WebElement menuOption = getWebDriver().findElement(PWAPlayerPage.objPlaybackVideoOverlay);
				actions.moveToElement(menuOption).perform();
				click(PWAPlayerPage.objPlayerPause, "Player Pause");
				try {
					getWebDriver().findElement(PWAPlayerPage.playBtn);
					extent.extentLogger("playerPaused", "Paused the Player");
					logger.info("Paused the Player");
					playerPaused = true;
					break;
				} catch (Exception e) {
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Player");
					logger.error("Error when handling Player");
				}
			}
		}
		return playerPaused;
	}

	/**
	 * Video Player or Live Player Ad verify
	 * 
	 * @param playerType
	 * @throws Exception
	 */
	public void waitForPlayerAdToComplete2(String playerType) throws Exception {
		boolean adWasDisplayed = false;
		boolean playerDisplayed = false;
		int confirmCount = 0;
		main: for (int trial = 0; trial < 90; trial++) {
			try {
				getWebDriver().findElement(PWAPlayerPage.objAd);
				adWasDisplayed = true;
				if (trial == 5) {
					logger.info("Ad play in progress");
					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
				}
				if (Math.floorMod(trial, 10) == 0)
					System.out.println("Ad play in progress");
				Thread.sleep(1000);
			} catch (Exception e) {
				try {
					if (playerType.equals("Live Player")) {
						getWebDriver().findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						if (BROWSER.equals("Chrome")) {
							click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
							verifyElementExist(PWAPlayerPage.maximizeBtn, "Maximize icon");
							click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
						} else {
							Actions actions = new Actions(getWebDriver());
							WebElement menuOption = getWebDriver()
									.findElement(By.xpath("//div[@class='playkit-overlay-action']"));
							actions.moveToElement(menuOption).perform();
							verifyElementExist(PWAPlayerPage.maximizeBtn, "Maximize icon");
						}
						// getWebDriver().findElement(PWAPlayerPage.maximizeBtn);
					}
					playerDisplayed = true;
					// Thread.sleep(1000);
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
					Thread.sleep(1000);
				}
			}
		}
		if (playerDisplayed == false && adWasDisplayed == false) {
			logger.error("Ad play failure");
			extent.extentLoggerFail("failedAd", "Ad play failure");
		}
	}

	public String chkPremiumORFreeFromVideosTabAndSelect(String str, String premiumORfree, String usertype)
			throws Exception {
		ScrollToTheElementWEB(PWAMoviesPage.TextToXpath(str));
		waitTime(5000);
		String ValueOfPremiumTumbnail = null;
		int p = 0;
		main: for (int j = 0; j < 5; j++) {
			List<WebElement> tumnails = findElements(By.xpath("//div[@class='trayHeader']/child::h2[contains(text(),'"
					+ str
					+ "')]/parent::*/following-sibling::*/child::*/child::div/child::*/child::*/child::*/child::*/child::a/child::figure"));
			System.out.println("numberofThumnails:" + tumnails.size());
			for (int i = 1 + p; i <= tumnails.size(); i++) {
				System.out.println(i);
				WebElement specificTumbnail = getWebDriver()
						.findElement(By.xpath("(//div[@class='trayHeader']/child::h2[contains(text(),'" + str
								+ "')]/parent::*/following-sibling::*/child::*/child::div/child::*/child::*/child::*/child::*/child::a/child::figure)["
								+ i + "]"));
				boolean elevisibility = verifyElementExist(PWAHomePage.objVideoIsPremiumTumbnail(str, i), "is Premium");
				if (elevisibility == true) {
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objVideoTumbnailTitle(str, i));
					System.out.println("Premium Tumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("PREMIUM")) {
						clickByElement(specificTumbnail, "Specific Tumbnail from Premium");
						break main;
					}
				} else if (elevisibility == false) {
					ValueOfPremiumTumbnail = getAttributValue("title", PWAHomePage.objVideoTumbnailTitle(str, i));
					System.out.println("Non-Premium Tumbnail Title : " + ValueOfPremiumTumbnail);
					if (premiumORfree.equals("FREE")) {
						clickByElement(specificTumbnail, "Specific Tumbnail from Non-Premium");
						break main;
					}
				}
			}
			p = tumnails.size();
			getWebDriver().findElement(By.xpath("//div[@class='trayHeader']/child::h2[contains(text(),'" + str
					+ "')]/parent::*/following-sibling::*/child::*/child::button[@class='slick-arrow slick-next']"))
					.click();
			waitTime(3000);
		}
		return ValueOfPremiumTumbnail;
	}

	/**
	 * ================================BINDU
	 * Search==================================
	 * 
	 */

	public void SearchResultsScreen(String UserType) throws Exception {

		switch (UserType) {
		case "Guest":
			extent.HeaderChildNode("Guest user");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			landingOnSearchscreen();
			MicrophoneVoiceInput();
			liveTvsearch("Republic TV");
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovie");
			movieSearchResults(keyword);
			PartlySpeltAsset("natasaa", UserType);
			MultipleKeywordsSearch("Natasarvabhowma Kannada Drama");
			LanguageSearch("Kannada");
			GenreSearch("Comedy");

			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("NonSubscribedUser");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			// ZeeWEBPWALogin("NonSubscribedUser");
			landingOnSearchscreen();
			MicrophoneVoiceInput();
			liveTvsearch("Republic TV");
			String keyword1 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovie");
			movieSearchResults(keyword1);
			PartlySpeltAsset("Natasaa", UserType);
			MultipleKeywordsSearch("Natasarvabhowma Kannada Drama");
			LanguageSearch("Kannada");
			GenreSearch("Comedy");

			break;

		case "SubscribedUser":
			extent.HeaderChildNode("SubscribedUser");
			extent.extentLogger("Accessing as SubscribedUser User", "Accessing as SubscribedUser User");
			logger.info("Accessing as SubscribedUser User");
			// ZeeWEBPWALogin("SubscribedUser");
			landingOnSearchscreen();
			MicrophoneVoiceInput();
			liveTvsearch("Republic TV");
			String keyword2 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovie");
			movieSearchResults(keyword2);
			PartlySpeltAsset("Natasaa", UserType);
			MultipleKeywordsSearch("Natasarvabhowma Kannada Drama");
			LanguageSearch("Kannada");
			GenreSearch("Comedy");
			searchemptystateScreen("Natasarvabhowma");
		}
	}

	public void landingOnSearchscreen() throws Exception {
		extent.HeaderChildNode("Validating that user lands on search landing screen");
		waitForElementDisplayed(PWAHomePage.objSearchBtn, 10);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		verifyElementExist(PWASearchPage.objVoiceSearchButton, "Voice seach icon");

		if (verifyElementExist(PWASearchPage.objSearchEditBox, "Search EditBox")) {
			logger.info("User landed on Search landing screen post tapping on search icon");
			extent.extentLogger("Search landingscreen",
					"User landed on Search landing screen post tapping on search icon");
		}
	}

	public void MicrophoneVoiceInput() throws Exception {

		extent.HeaderChildNode("Validating that user is asked to give the voice input post tapping on microphone icon");

		waitTime(5000);

		if (BROWSER.equals("Chrome")) {
			click(PWASearchPage.objVoiceSearchButton, "Voice seach icon");

			logger.info("Voice Search Icon is present in Chrome Browser and clicked on Voice Search Icon ");
			extent.extentLogger("Voice input",
					"Voice Search Icon is present in Chrome Browser and clicked on Voice Search Icon");
			waitTime(8000);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_ENTER);

			String searchBarText = getAttributValue("placeholder", PWASearchPage.objSearchEditBox);
			System.out.println(searchBarText);

			if (searchBarText.equalsIgnoreCase("Speak to Search on ZEE5")) {
				logger.info("User is asked to give the voice input");
				extent.extentLogger("Voice input", "User is asked to give the voice input");
			} else {
				logger.info("User is not asked to give the voice input");
				extent.extentLogger("Voice input", "User is not asked to give the voice input");
			}

		}

		else {
			logger.info("Voice Search Icon is not applicable for Firefox Browser");
			extent.extentLogger("Voice Search icon", "Voice Search Icon is not applicable for Firefox Browser");

		}

		Back(1);
	}

	public void liveTvsearch(String title) throws Exception {
		extent.HeaderChildNode(
				"Validating that Live TV card is displayed when user searches by any On Going Live TV content name");
		waitTime(3000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);

		type(PWASearchPage.objSearchEditBox, title, "Search bar");
		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}
		waitTime(10000);
		if (verifyElementExist(PWALiveTVPage.objLivelogo, "Live logo")) {
			logger.info("Live Channel card is displayed");
			extent.extentLogger("Live Channel card", "Live Tv card is displayed");
		} else {
			logger.info("Live Channel card is not displayed");
			extent.extentLogger("Live Channel card", "Live Tv card is not displayed");
		}
		waitTime(3000);
		click(PWALiveTVPage.objLivelogo, "Live logo");
		waitTime(3000);
		Back(2);

	}

	public void movieSearchResults(String title) throws Exception {

		extent.HeaderChildNode("Validating that user is able to search Movie");

		waitTime(4000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);

		type(PWASearchPage.objSearchEditBox, title, "Search bar");

		waitTime(3000);

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getWebDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (getWebDriver().findElements(By.xpath(
					"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
					.size() > 0) {
				logger.info("Related search results are displayed");
				extent.extentLogger("Related search results", "Related search results are displayed");

			} else {
				logger.info("Related search results are not displayed");
				extent.extentLogger("Related search results", "Related search results are not displayed");
			}
		}

//	verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
//
//	clearField(PWASearchPage.objSearchEditBox, "Search Bar");

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		Back(1);

	}

	public void PartlySpeltAsset(String title, String userType) throws Exception {
		extent.HeaderChildNode("Validating that user is able to search Partly spelt asset name");

		waitTime(6000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);
		mandatoryRegistrationPopUp(userType);
		type(PWASearchPage.objSearchEditBox, title, "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getWebDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (getWebDriver().findElements(By.xpath(
					"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
					.size() > 0) {
				logger.info("Related search results are displayed");
				extent.extentLogger("Partlyspelt Asset search results", "Related search results are displayed");

			} else {
				logger.info("Related search results are not displayed");
				extent.extentLogger("Related search results", "Related search results are not displayed");
			}
		}

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		Back(1);

	}

	public void MultipleKeywordsSearch(String title) throws Exception {
		extent.HeaderChildNode("Validating that user is able to search through MultipleKeywords");

		waitTime(3000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);

		type(PWASearchPage.objSearchEditBox, title, "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getWebDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (getWebDriver()
					.findElements(
							By.xpath("//div[@class='clickWrapper']//div[@class='dateTime']//span[@class='items']"))
					.size() > 0)

			{
				waitTime(4000);
				logger.info("Related search results are displayed");
				extent.extentLogger("Related search Results", "Related search results are displayed");
			}

			else {
				logger.info("Related search results are not displayed");
				extent.extentLogger("Related search Results", "Related search  results are not displayed");
			}

		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");

		Back(1);

	}

	public void LanguageSearch(String language) throws Exception {
		extent.HeaderChildNode("Validating that user is able to search content by Language");

		waitTime(3000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);

		type(PWASearchPage.objSearchEditBox, language, "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getWebDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
//	 tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");
			int k = 1;
			for (k = 1; k <= 3; k++) {
				List<WebElement> AssetMetadata = getWebDriver().findElements(
						By.xpath("(//div[@class='clickWrapper'])[" + k + "]/child::div[@class='dateTime']/child::*"));
				// System.out.println(AssetMetadata.size());

				for (int j = 1; j <= AssetMetadata.size(); j++) {
					String metadata = getWebDriver().findElement(By.xpath("((//div[@class='clickWrapper'])[" + k
							+ "]/child::div[@class='dateTime']/child::*)[" + j + "]")).getText();
					// System.out.println(metadata);
					if (metadata.contains(language)) {
						logger.info("User can search a content by language");
						extent.extentLogger("Language search", "User can search a content by language");
					}

				}
			}
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		Back(1);

	}

	public void GenreSearch(String genre) throws Exception {
		extent.HeaderChildNode("Validating that user is able to search content by Genre");

		waitTime(3000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);

		type(PWASearchPage.objSearchEditBox, genre, "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getWebDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		// tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			waitTime(4000);
			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");
			int k = 1;
			for (k = 1; k <= 3; k++) {
				List<WebElement> AssetMetadata = getWebDriver().findElements(
						By.xpath("(//div[@class='clickWrapper'])[" + k + "]/child::div[@class='dateTime']/child::*"));
				// System.out.println(AssetMetadata.size());

				for (int j = 1; j <= AssetMetadata.size(); j++) {
					String metadata = getWebDriver().findElement(By.xpath("((//div[@class='clickWrapper'])[" + k
							+ "]/child::div[@class='dateTime']/child::*)[" + j + "]")).getText();
					// System.out.println(metadata);
					if (metadata.contains(genre)) {
						logger.info("User can search a content by Genre");
						extent.extentLogger("Genre search", "User can search a content by Genre");
					}
				}
			}
		}
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		Back(1);
	}

//This method is only for Subscribed User
	public void searchemptystateScreen(String title) throws Exception {

		extent.HeaderChildNode("Validating that empty state screen is displayed");

		waitTime(3000);

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 10);

		type(PWASearchPage.objSearchEditBox, title, "Search bar");

		waitForElementDisplayed(PWASearchPage.objSearchNavigationTab("All"), 5);

		if (getPlatform().equals("Android")) {
			getDriver().hideKeyboard();
			logger.info("Hiding keyboard was Successfull");
			extent.extentLogger("hideKeyboard", "Hiding keyboard was Successfull");
		}

		List<WebElement> tabs = getWebDriver().findElements(By.xpath("//div[contains(@class,'noSelect tabMenuItem')]"));
		tabs.size();
		for (int i = 1; i <= tabs.size(); i++) {
			WebElement eleTab = getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'noSelect tabMenuItem')])[" + i + "]"));
			String tabName = eleTab.getText();
			eleTab.click();

			logger.info(tabName + " tab is displayed and clicked on " + tabName + " tab");
			extent.extentLogger("Related search results",
					tabName + " tab is displayed and clicked on " + tabName + " tab");

			if (getWebDriver().findElements(By.xpath(
					"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])"))
					.size() > 0) {
				logger.info("Related search results are displayed");
				extent.extentLogger("Related search results", "Related search results are displayed");

			} else if (getWebDriver().findElements(By.xpath("//h3[contains(@class,'cardTitle')]")).size() > 0) {
				logger.info("Search results are displayed");
				extent.extentLogger("Search results", "Search results are displayed");
			} else {
				verifyElementExist(PWASearchPage.objEmptyStateScreenErrormsg, "Empty state screen");
			}
		}

		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");

		clearField(PWASearchPage.objSearchEditBox, "Search Bar");
		Back(1);

	}

	/**
	 * ================================BINDU
	 * ZeeOriginals==================================
	 * 
	 */

	public void Zee5OriginalsScreen(String UserType, String Tabname) throws Exception {

		switch (UserType) {

		case "Guest":
			extent.HeaderChildNode("Guest user");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			// dismissDisplayContentLanguagePopUp();
			mandatoryRegistrationPopUp(UserType);
			landingPagesValidationZeeoriginals(Tabname);
			zee5originalstrayvalidation(Tabname);
			mandatoryRegistrationPopUp(UserType);
			trayTitleAndContentValidationWithApiDataZeeoriginals(Tabname, "zeeoriginals");
			ValidatingPremiumTag(Tabname);
			mandatoryRegistrationPopUp(UserType);
			ConsumptionScreen(Tabname);
			Subscriptionpopup(UserType, Tabname);

			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("NonSubscribedUser");
			extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
			logger.info("Accessing as NonSubscribedUser User");
			// ZeeWEBPWALogin11("NonSubscribedUser");
			// dismissDisplayContentLanguagePopUp();
			landingPagesValidationZeeoriginals(Tabname);
			zee5originalstrayvalidation(Tabname);
			trayTitleAndContentValidationWithApiDataZeeoriginals(Tabname, "zeeoriginals");
			ValidatingPremiumTag(Tabname);
			ConsumptionScreen(Tabname);
			Subscriptionpopup(UserType, Tabname);
			break;

		case "SubscribedUser":
			extent.HeaderChildNode("SubscribedUser");
			extent.extentLogger("Accessing as SubscribedUser User", "Accessing as SubscribedUser User");
			logger.info("Accessing as SubscribedUser User");
			// ZeeWEBPWALogin11("SubscribedUser");
			// dismissDisplayContentLanguagePopUp();
			landingPagesValidationZeeoriginals(Tabname);
			zee5originalstrayvalidation(Tabname);
			trayTitleAndContentValidationWithApiDataZeeoriginals(Tabname, "zeeoriginals");
			ValidatingPremiumTag(Tabname);
			ConsumptionScreen(Tabname);
			Subscriptionpopup(UserType, Tabname);
		}
	}

//public void dismissDisplayContentLanguagePopUp() throws Exception {
//		extent.HeaderChildNode("Dismiss Display and Content Language Pop Ups");
//		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup,90,"Continue on Display Language Pop Up");
//		Thread.sleep(5000);
//		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup,10,"Continue on Content Language Pop Up");
//	}

	public void landingPagesValidationZeeoriginals(String Tabname) throws Exception {
		extent.HeaderChildNode("ZEE5 Originals Page Validation");

		navigateToAnyScreenOnWeb(Tabname);

		waitTime(5000);

		if (verifyElementExist(PWAHomePage.objActiveTab, "Active tab")) {

			String tab = getText(PWAHomePage.objActiveTab);
			System.out.println(tab);
			logger.info(tab + " tab is highlighted");
			extent.extentLogger("Tab", tab + " tab is highlighted");
		} else {

			Actions actions = new Actions(getWebDriver());
			WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
			actions.moveToElement(tab).build().perform();
			String tab2 = getText(PWAHomePage.objActiveTab);
			System.out.println(tab2);
			logger.info(tab2 + " tab is highlighted");
			extent.extentLogger("Tab", tab2 + " tab is highlighted");
		}

		waitTime(3000);
		verifyElementPresentAndClick(PWAPremiumPage.objNextArrowBtn, "Next Arrow Button");

		if (verifyElementExist(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button")) {
			logger.info("Tray is rotated");
			extent.extentLogger("Tray is rotated", "Tray is rotated");
		} else {
			logger.info("Tray is not rotated");
			extent.extentLogger("Tray is not rotated", "Tray is not rotated");

		}

		click(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button");

		waitTime(3000);
		if (verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");

			if (verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}

		}

		Back(1);

		waitTime(2000);

		for (int i = 0; i < 5; i++) {
			if (findElements(PWAMusicPage.objPremiumTag).size() > 0) {
				logger.info("Premium tag is displayed");
				extent.extentLogger("Premium Tag", "Premium Tag is displayed");
				break;

			} else {
				logger.info("Premium tag is not displayed");
				extent.extentLogger("Premium Tag", "Premium Tag is not displayed");
				partialScrollDown();
			}

		}

		waitTime(2000);

		for (int i = 0; i < 5; i++) {
			if (findElements(PWAPremiumPage.objMinuteContent).size() > 0) {
				logger.info("Minute content is displayed");
				extent.extentLogger("Minute content", "Minute content is displayed");
				break;

			} else {
				logger.info("Minute content is not displayed");
				extent.extentLogger("Minute content", "Minute content is not displayed");
				partialScrollDown();
			}

		}

		waitTime(2000);
		if (verifyElementExist(PWAMusicPage.objArrowToNavigateTop, "Back To Top Arrow icon")) {
			waitTime(2000);
			click(PWAMusicPage.objArrowToNavigateTop, "Back To Top Arrow icon");
		}

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void zee5originalstrayvalidation(String Tabname) throws Exception {
		extent.HeaderChildNode("Verifing the trays displayed in zee5originals Tab");

		// verifyElementPresentAndClick(PWAHomePage.objTabName(tabName), tabName);

		navigateToAnyScreenOnWeb(Tabname);
		String languageSmallText = allSelectedLanguagesWEB();
		System.out.println(languageSmallText);

		List<String> apiTitleList = new LinkedList<String>();

		int q = 1;
		for (int k = q; k <= 6; k++) {
			// System.out.println("q: "+q);
			Response resp = ResponseInstance.getResponseForPages2("zeeoriginals", languageSmallText, q);

			List<String> apitotaltrays = resp.jsonPath().getList("buckets");
			System.out.println(apitotaltrays.size());
			for (int i = 0; i < apitotaltrays.size(); i++) {
				String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
				System.out.println(traytitle);
				apiTitleList.add(traytitle);
			}
			q = q + 1;
		}
		System.out.println("api: " + apiTitleList);

		scrollToBottomOfPageWEB();
		// waitTime(6000);
		if (verifyElementExist(PWAZee5OriginalPage.objWhatWonderingPopUp, "Wondering popUp")) {
			waitTime(3000);
			click(PWAZee5OriginalPage.objWhatWonderingPopUpCloseIcon, "Close icon");
		}
		waitTime(3000);
		// verifyElementExist(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to
		// Top");
		click(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
		waitTime(5000);
		List<String> uiTitleList = new LinkedList<String>();
		List<WebElement> uitotaltrays = getWebDriver().findElements(By.xpath("//div[@class='trayHeader']"));
		System.out.println(uitotaltrays.size());

		int j = 0;
		for (j = 0; j < uitotaltrays.size() - 1; j++) {
			String trayTitle = findElement(By.xpath("(//div[@class='trayHeader'])[" + (j + 1) + "]/child::h2/child::*"))
					.getText();

			System.out.println(trayTitle);
			uiTitleList.add(trayTitle);
			// scrollDown();

			scrollToBottomOfPageWEB();
		}
		System.out.println("UI: " + uiTitleList);

		for (int l = 0; l < uitotaltrays.size() - 1; l++) {
			if (apiTitleList.get(l + 1).equalsIgnoreCase(uiTitleList.get(l))) {
				logger.info(
						"API title: " + apiTitleList.get(l + 1) + " is verified with UI title: " + uiTitleList.get(l));
				extent.extentLogger("Tray validation",
						"API title: " + apiTitleList.get(l + 1) + " is verified with UI title: " + uiTitleList.get(l));

			}
		}

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");

	}

	public void trayTitleAndContentValidationWithApiDataZeeoriginals(String Tabname, String api) throws Exception {
		extent.HeaderChildNode("ZEE5 Originals page tray asset validation");

		navigateToAnyScreenOnWeb(Tabname);
		waitForElementDisplayed(PWAMusicPage.objPremiumTag, 10);
		String languageSmallText = allSelectedLanguagesWEB();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);

		List<String> apiTitleList = new LinkedList<String>();

		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");

		List<String> contentList = resp.jsonPath().getList("buckets[1].items");
		System.out.println(contentList.size());

		partialScrollDown();
		for (int i = 0; i < 5; i++) {

			String titles = resp.jsonPath().getString("buckets[1].items[" + i + "].title");
			// System.out.println("Api data " +titles);
			logger.info("Api data " + titles);

			apiTitleList.add(titles);

			waitTime(6000);
			Actions actions = new Actions(getWebDriver());
			WebElement contentCard = getWebDriver()
					.findElement(By.xpath("(//div[@class='slick-list']//div[@class='content'])[" + (i + 1) + "]"));
			actions.moveToElement(contentCard).build().perform();
			String trayTitle = apiTitleList.get(i);
			logger.info("UI data " + titles);

			if (trayTitle.equalsIgnoreCase(apiTitleList.get(i))) {
				logger.info("Metadata on the content card is validated with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is validated with Api data");
			} else {
				logger.info("Metadata on the content card is not validated with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card is not validated with Api data");
			}

			waitTime(1000);
			verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Button");
			waitTime(1000);
			verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Button");

		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void ValidatingPremiumTag(String Tabname) throws Exception {
		extent.HeaderChildNode("Validating Promo of the Premium Content ");

		navigateToAnyScreenOnWeb(Tabname);
		waitTime(3000);

//		for(int i=0;i<5;i++){
//			if (findElements(PWAMusicPage.objPremiumTag).size() > 0) 
//			{
//				logger.info("Premium tag is displayed");
//				extent.extentLogger("Premium Tag", "Premium Tag is displayed");
//				break;
//				
//			}
//			else
//			{
//				logger.info("Premium tag is not displayed");
//				extent.extentLoggerFail("Premium Tag", "Premium Tag is not displayed");
//				//PartialSwipe("UP",1);
//				JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
//			    jse.executeScript("window.scrollTo(0, 300)");
//				
//			}
//			
//		}

		waitTime(4000);
		if (verifyElementExist(PWAZee5OriginalPage.objPremiumCard, "Premium Card"))

		{

			click(PWAZee5OriginalPage.objPremiumCard, "Premium card");
			waitTime(5000);
			if (verifyElementExist(PWAPlayerPage.objCloseRegisterDialog, "Register popup")) {
				waitTime(3000);
				click(PWAPlayerPage.objCloseRegisterDialog, "Register popup close icon");
			}
			if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt,
					"Complete Your Profile pop up")) {
				click(CompleteYourProfilePopUp.objCloseBtn, "Close Button");

			}

			if (verifyElementExist(PWAPlayerPage.objWatchPromo, "Watch Promo icon")) {

				click(PWAPlayerPage.objWatchPromo, "Watch Promo icon");
				waitTime(2000);
				verifyElementExist(PWAPlayerPage.objPlayerPromoMetadata, "Promo Content");
				logger.info("User can able to watch Promo from the selected content");
				extent.extentLogger("Popup Screen", "User can able to watch Promo from the selected content");
				Back(1);
			}

			Back(1);
		}

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void ConsumptionScreen(String Tabname) throws Exception {
		extent.HeaderChildNode("Validation of the Consumption Screen with content autoplaying");

		navigateToAnyScreenOnWeb(Tabname);
		Thread.sleep(4000);
		verifyElementPresentAndClick(PWAZee5OriginalPage.objCarousel, "Carousel Card");

		if (verifyElementExist(PWAPlayerPage.objCloseRegisterDialog, "Why Register popup")) {
			waitTime(3000);
			click(PWAPlayerPage.objCloseRegisterDialog, "Register popup close icon");
		}

		if (verifyElementExist(PWAPlayerPage.objSubscriptionpopup, "Subscription popup")) {
			waitTime(3000);
			click(PWAPlayerPage.ObjSubscriptionpopupCloseIcon, "Subscription popup close icon");
		}
		if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt, "Complete Your Profile pop up")) {
			click(CompleteYourProfilePopUp.objCloseBtn, "Complete your profile popup Close Button");

		}

		waitTime(3000);

		if (verifyElementExist(PWAPlayerPage.objPlaybackVideoOverlay, "Player")) {
			logger.info("Navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "User is navigated to Consumption Page");
		} else {
			logger.info("Not navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "User is not navigated to Consumption Page");
		}

		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void Subscriptionpopup(String UserType, String Tabname) throws Exception {
		extent.HeaderChildNode("Validating the Subscriptionpopup after some interval of time");

		navigateToAnyScreenOnWeb(Tabname);
		waitTime(20000);

		verifyElementExist(PWAZee5OriginalPage.objPremiumContentCard, "Premium Content");
		click(PWAZee5OriginalPage.objPremiumContentCard, "Premium Content");
		waitTime(3000);
		if (verifyElementExist(PWAPlayerPage.objCloseRegisterDialog, "Register popup")) {
			waitTime(2000);
			click(PWAPlayerPage.objCloseRegisterDialog, "Register popup close icon");
		}

		if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt, "Complete Your Profile pop up")) {
			click(CompleteYourProfilePopUp.objCloseBtn, "Complete your profile popup Close Button");

		}

		if (verifyElementExist(PWAPlayerPage.objSubscriptionpopup, "Subscription popup")) {
			waitTime(3000);
			click(PWAPlayerPage.ObjSubscriptionpopupCloseIcon, "Subscription popup close icon");
		}

		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollTo(0, 200)");

		if (verifyElementExist(PWAZee5OriginalPage.objPremiumContentCard, "Premium Episode")) {
			waitTime(2000);
			click(PWAZee5OriginalPage.objPremiumContentCard, "Premium Episode");

			if (BROWSER.equals("Chrome")) {

				if (verifyElementExist(PWAPlayerPage.objLoginText, "Login Link")) {
					waitTime(2000);
					Back(1);
				}

				if (verifyElementExist(PWAPlayerPage.objSubscribeLink, "Subscribe Link")) {
					waitTime(2000);
					Back(1);
				}

				else {

					moviePausePlayer();
					WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
					waitTime(2000);
					Actions move = new Actions(getWebDriver());
					Action action = (Action) move.dragAndDropBy(scrubber, 580, 0).build();
					action.perform();
					click(PWAPlayerPage.objPlayerPlay, "Play icon");
				}
			} else {

				if (verifyElementExist(PWAPlayerPage.objLoginText, "Login Text")) {

					Back(1);
				}

				if (verifyElementExist(PWAPlayerPage.objSubscribeLink, "Subscribe Link")) {
					waitTime(2000);
					Back(1);
				}

				else {

					waitForPlayerLoaderToComplete();

					firefoxpause();
					WebElement scrubber = getWebDriver().findElement(PWAPlayerPage.objPlayerScrubber);
					waitTime(10000);
					Actions move = new Actions(getWebDriver());
					Action action = (Action) move.dragAndDropBy(scrubber, 580, 0).build();
					action.perform();
					click(PWAPlayerPage.objPlayerPlay, "Play icon");
				}

			}

			if (verifyElementExist(PWAPlayerPage.objSubscriptionpopup, "Subscription popup")) {
				logger.info("Subscription Popup is displayed after some interval of time");
				extent.extentLogger("Subscription Popup",
						"Subscription Popup is displayed after some interval of time");
				click(PWAPlayerPage.objSubscriptionpopup, "Subscription popup close icon");
			} else {
				logger.info("Subscription Popup is not displayed after some interval of time");
				extent.extentLogger("Subscription Popup",
						"Subscription Popup is not displayed after some interval of time");
			}
		}
		Back(2);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * ====================SATISH - SUBSCRIPTION
	 * POPUPSCENARIOS==========================
	 * 
	 */

	/**
	 * Verify Subscription popup after trailer is played for 20 seconds
	 */
	public void zeePWAVerifySubscriptionPopUpAfterTwentySecondsPlayback(String userType) throws Exception {
		HeaderChildNode("Verify Subscription popup after trailer is played");
		System.out.println("Verify Subscription popup after trailer is played");
		click(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		if (userType.equalsIgnoreCase("Guest") || userType.equalsIgnoreCase("NonSubscribedUser")) {
			String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("premiumMovieWithTrailer");
			zeeSearchForContentAndClickOnFirstResult(keyword);
			waitForElement(PWASubscriptionPages.objGetPremiumPopupTitle, 21, "Subscribe Pop Up");
			if (verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Subscribe Pop Up")) {
				click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close button");
			}
		}
	}

	/**
	 * Navigate to Subscription Flow From Subscription Popup full screen player
	 */
	public void zeePWAVerifyNavigationToSubscriptionFlowFromSubscriptionPopupFullscreenPlayer(String userType)
			throws Exception {
		HeaderChildNode("Navigate to Subscription Flow From Subscription Popup in full screen player");
		System.out.println("Navigate to Subscription Flow From Subscription Popup in full screen player");
		click(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		if (userType.equalsIgnoreCase("Guest") || userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeeSearchForContentAndClickOnFirstResult("Ondh Kathe Hella");
			waitTime(10000);
			playerTap();
			verifyElementPresentAndClick(PWAPlayerPage.maximizeBtn, "Maximize window icon");
			verifyElementPresent(PWAPlayerPage.minimizeBtn, "Minimize window icon");
			waitForElement(PWASubscriptionPages.objGetPremiumPopupTitle, 20, "Subscribe Pop Up");
			if (verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Subscribe Pop Up")) {
				verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
				verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Popup Proceed button");
				if (userType.equalsIgnoreCase("NonSubscribedUser")) {
					if (waitForElementDisplayed(PWASubscriptionPages.objPaymentHighlighted, 10)) {
						verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
					}
				} else {
					if (waitForElementDisplayed(PWASubscriptionPages.objAccountInfoHighlighted, 10)) {
						verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
					}
				}
			}
		}
	}

	/**
	 * Verify Subscription Popup After Trailer Playback Is Complete
	 */
	@SuppressWarnings("unused")
	public void zeePWAVerifySubscriptionPopupAfterTrailerPlaybackIsComplete(String userType) throws Exception {
		HeaderChildNode("Verify Subscription Popup After Completion Of Trailer Playback Is Complete");
		click(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		zeeSearchForContentAndClickOnFirstResult("Ondh Kathe Hella");
		waitTime(5000);
		playerTap();
		String totalDuration = findElement(PWAPlayerPage.objPlayerTotalDuration).getText();
		extent.extentLogger("", "Total duration of the trailer: " + totalDuration);
		logger.info("Total duration of the trailer is: " + totalDuration);
		playerTap();
		String currentTime = findElement(PWAPlayerPage.objPlayerCurrentDuration).getText();
		logger.info("Current duration of the trailer is: " + currentTime);
		extent.extentLogger("", "Current duration of the trailer is: " + currentTime);
		for (int i = 0; !(totalDuration.equals(currentTime)); i++) {
			waitTime(5000);
			playerTap();
			currentTime = findElement(PWAPlayerPage.objPlayerCurrentDuration).getAttribute("innerText");
			logger.info("Current duration of the trailer is: " + currentTime);
			extent.extentLogger("", "Current duration of the trailer is: " + currentTime);
			waitTime(10000);
		}
		waitTime(3000);
		if (verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Subscribe Pop Up Title")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close Button");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	@SuppressWarnings("rawtypes")
	public void playerTap() throws Exception {

		if (getPlatform().equalsIgnoreCase("Android")) {
			int deviceWidth = getDriver().manage().window().getSize().width;
			int deviceHeight = getDriver().manage().window().getSize().height;
			int x = deviceWidth / 2;
			int y = deviceHeight / 4;
			TouchAction act = new TouchAction(getDriver());
			act.tap(PointOption.point(x, y)).perform();
			extent.extentLogger("playerTap", "Tapped on the Player");
			logger.info("Tapped on the Player");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			Actions action = new Actions(getWebDriver());
			action.moveToElement(findElement(PWAPlayerPage.objPlaybackVideoOverlay));
			action.perform();
		}

	}

	public void wouldYouLikeToPopupClose() throws Exception {
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
	}

	/**
	 * ====================SATISH - UserActions==========================
	 * 
	 */

	public void AddContentsToWatchList() throws Exception {

		// Adding Episodes to Watch list
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		// Enter an episode
		type(PWASearchPage.objSearchEditBox, "Comedy Kiladigalu Championship - Episode 1 - July 7, 2018 - Full Episode",
				"Search edit box");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
		waitTime(5000);
		// Click on the first episode
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Episode");
		waitTime(3000);
		if (verifyElementExist(PWAAddToWatchListPage.objCompleteProfilePopUp, "Complete profile Popup") == true) {
			click(PWAAddToWatchListPage.objClosePopup, "Close button");
		}
		// Click on Add to watch list
		click(PWAPlayerPage.watchListBtn, "Add to Watch list");
		wouldYouLikeToPopupClose();
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		// Enter an episode
		type(PWASearchPage.objSearchEditBox, "Everyone delighted about Adya's pregnancy - Gattimela",
				"Search edit box");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
		waitTime(5000);
		wouldYouLikeToPopupClose();
		// Click on the first episode
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Episode");
		if (verifyElementExist(PWAAddToWatchListPage.objCompleteProfilePopUp, "Complete profile Popup") == true) {
			click(PWAAddToWatchListPage.objClosePopup, "Close button");
		}
		waitTime(3000);
		// Click on Add to watch list
		click(PWAPlayerPage.watchListBtn, "Add to Watch list");
		wouldYouLikeToPopupClose();
		// Adding movies to Watch list
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		// Enter a movie
		type(PWASearchPage.objSearchEditBox, "Ee Preethi Yeke Bhoomi Melide", "Search edit box");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
		wouldYouLikeToPopupClose();
		waitTime(5000);
		// Click on the first Movie
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Movie");
		if (verifyElementExist(PWAAddToWatchListPage.objCompleteProfilePopUp, "Complete profile Popup") == true) {
			click(PWAAddToWatchListPage.objClosePopup, "Close button");
		}
		waitTime(3000);
		// Click on Add to watch list
		click(PWAPlayerPage.watchListBtn, "Add to Watch list");

		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		// Enter a Movie
		type(PWASearchPage.objSearchEditBox, "doddmane hudga", "Search edit box");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
		wouldYouLikeToPopupClose();
		waitTime(5000);
		// Click on the Movie
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Movie");
		if (verifyElementExist(PWAAddToWatchListPage.objCompleteProfilePopUp, "Complete profile Popup") == true) {
			click(PWAAddToWatchListPage.objClosePopup, "Close button");
		}
		waitTime(3000);
		// Click on Add to watch list
		click(PWAPlayerPage.watchListBtn, "Add to Watch list");

		// Adding Video clip
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		// Enter a Video clip
		type(PWASearchPage.objSearchEditBox, "Gravitas: Why are Sweden-China ties turning hostile?", "Search edit box");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
		waitTime(5000);
		// Click on the first Video
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Video clip");
		waitTime(3000);
		// Click on Add to watch list
		click(PWAPlayerPage.watchListBtn, "Add to Watch list");
		wouldYouLikeToPopupClose();
		// click on home page
		click(PWAHomePage.objTabName("Home"), "Home tab");
		if (verifyElementExist(PWAAddToWatchListPage.objCloseBtnForVideoClipPopup, "Pop up") == true) {
			click(PWAAddToWatchListPage.objCloseBtnForVideoClipPopup, "Close button");
		}
	}

	/*
	 * My Reminder section for subscribed user
	 */
	public void MyReminder() throws Exception {
		extent.HeaderChildNode("MyReminder Scenarios Validations");
		// Verify user is navigate to EPG section from Live TV
		if (getPlatform().equalsIgnoreCase("Android")) {
			navigateToAnyScreen("Live TV");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			navigateToAnyScreenOnWeb("Live TV");
		}
		wouldYouLikeToPopupClose();
		// Click on Channel guide
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide");
		if (findElement(PWALiveTVPage.objChannelGuideToggle).getAttribute("class").contains("active")) {
			extent.extentLogger("Verify Navigation", "User is navigated to EPG section post tapping Channel guide");
			logger.info("User is navigated to EPG section post tapping Channel guide");
		} else {
			extent.extentLoggerFail("Verify Navigation",
					"User did not navigated to EPG section post tapping Channel guide");
			logger.info("User did not navigated to EPG section post tapping Channel guide");
		}

		// Verify EPG section contains Title and the description of the respective show
		waitTime(5000);
		if (verifyElementPresent(PWALiveTVPage.objShowTitle, "Show title") == true) {
			String title = getElementPropertyToString("innerText", PWALiveTVPage.objShowTitle, "Show title");
			extent.extentLogger("Verify Show title", "The show tile is displayed and the show title is : " + title);
			logger.info("The show tile is displayed and the show title is : " + title);
		} else {
			extent.extentLoggerFail("Verify Show title", "The show tile is not displayed");
			logger.info("The show tile is not displayed");
		}
		// click on any show
		click(PWALiveTVPage.objFutureChannelInfo, "Show");
		if (verifyElementExist(PWALiveTVPage.objShowDesc, "Show description") == true) {
			extent.extentLogger("Verify descrption of the show", "The description of the show is :"
					+ getElementPropertyToString("innerText", PWALiveTVPage.objShowDesc, "Show title"));
			logger.info("The description of the show is :"
					+ getElementPropertyToString("innerText", PWALiveTVPage.objShowDesc, "Show title"));
		} else {
			extent.extentLoggerFail("Verify descrption of the show", "Show description is not displayed");
			logger.info("Show description is not displayed");
		}
		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
		// Click on date
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Bengali");
		// Verify Share and Reminder option is available
		click(PWALiveTVPage.objBanglaShow1, "Show detail");
		verifyElementPresent(PWALiveTVPage.objShareOption, "Share option");
		verifyElementPresent(PWALiveTVPage.objRemainderButton, "Reminder option for upcoming show ");

		// Verify user can click on Reminder option
		// click on Reminder option
		click(PWALiveTVPage.objRemainderButton, "Reminder option");
		if (getWebDriver().findElement(PWALiveTVPage.objRemainderButton).getAttribute("class")
				.contains("btnIconActive")) {
			extent.extentLogger("Verify user can Click on Reminder option", "User can click on Reminder option");
			logger.info("User can click on Reminder option");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify user can Click on Reminder option",
					"User can not click on Reminder option");
			logger.info("User can not click on Reminder option");
		}

		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
		waitTime(3000);
		scrollToTopOfPageWEB();
		FilterLanguage("Kannada");
		waitTime(3000);
		if (getPlatform().equalsIgnoreCase("Android")) {
			Swipe("Up", 1);
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			scrollUp();
		}
		waitTime(3000);
		// Verify user can not add Movies to Reminder
		// Select any movie
		click(PWALiveTVPage.objMovieShow, "Movie show");
		if (verifyElementExist(PWALiveTVPage.objRemainderButton, "Reminder button") == false) {
			extent.extentLogger("Verify Movie show don't have Reminder option",
					"Reminder option is not available for Movie show");
			logger.info("Reminder option is not available for Movie show");
		} else {
			extent.extentLoggerFail("Verify Movie show don't have Reminder option",
					"Reminder option is available for Movie show");
			logger.info("Reminder option is available for Movie show");

		}
		// Click on close button
		click(PWALiveTVPage.objPopupCloseButton, "Close button");

		if (getPlatform().equalsIgnoreCase("Android")) {
			Swipe("Down", 1);
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			scrollDownWEB();
		}
		getWebDriver().navigate().refresh();
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage("Malayalam");
		// Select any show to add Reminder
		waitTime(2000);
		click(PWALiveTVPage.objMalayalamShow, "Show ");
		// Click on Reminder
		click(PWALiveTVPage.objRemainderButton, "Reminder");
		click(PWALiveTVPage.objPopupCloseButton, "Close button");
		// Verify user is navigated Reminder screen from Home screen
		if (getPlatform().equalsIgnoreCase("Android")) {
			navigateToAnyScreen("Home");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			navigateToAnyScreenOnWeb("Home");
		}

		// Verify user is navigated to Reminder screen from Home page
		String activeTab = getWebDriver().findElement(PWAHomePage.objActiveTab).getText();
		if (activeTab.equals("Home")) {
			extent.extentLogger("Verify current tab", "User is in " + activeTab + " tab");
			logger.info("User is in Home tab");
		} else {
			extent.extentLoggerFail("Verify current tab", "User is not in Home tab");
			logger.info("User is not in Home tab");
		}
		if (getPlatform().equalsIgnoreCase("Android")) {
			// Click on Hamburger menu
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			// Click on My account
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My account");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			// Click on My account
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
		}
		// Click on My Reminders
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyRemainder, "My Reminder");
		// Verify user is navigated to My Reminder screen
		if (verifyElementExist(PWAMyRemindersPage.objMyReminderHeader, "My Reminder Header") == true) {
			extent.extentLogger("Verify the Navigation ",
					"User is Navigated to "
							+ getWebDriver().findElement(PWAMyRemindersPage.objMyReminderHeader).getText()
							+ " screen from " + activeTab + "page");
			logger.info("User is Navigated to "
					+ getWebDriver().findElement(PWAMyRemindersPage.objMyReminderHeader).getText() + " screen from "
					+ activeTab + "page");
		} else {
			extent.extentLoggerFail("Verify Navigation ",
					"User failed to navigate from Home page to My Reminders screen");
			logger.info("User failed to navigate from Home page to My Reminders screen");
		}
		// Verify that Remove all button is displayed
		if (verifyElementExist(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all option") == true) {
			extent.extentLogger("Verify Remove all option is displayed",
					"Remove all option is displayed in Reminder screen");
			logger.info("Remove all option is displayed in Reminder screen");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify Remove all option is displayed",
					"Remove all option is not displayed in Reminder screen");
			logger.info("Remove all option is not displayed in Reminder screen");
		}
		// Verify that added reminder contents contains title, start and end time
		// verify title is displayed

		int totalContentsInReminder = getWebDriver().findElements(PWAMyRemindersPage.objTotalContentsInReminder).size();
		System.out.println(totalContentsInReminder);
		for (int i = 0; i < totalContentsInReminder; i++) {
			if (findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).isDisplayed()) {
				extent.extentLogger("Verify title", "The content name at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).getText());
				logger.info("The content name at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objTotalContentsInReminder).get(i).getText());
			} else {
				extent.extentLoggerFail("Verify title", "Content title is not displayed in Reminder screen");
				logger.info("Content title is not displayed in Reminder screen");
			}
		}
		// Verify Start and end time
		int dateTime = getWebDriver().findElements(PWAMyRemindersPage.objDateTime).size();
		for (int i = 0; i < dateTime; i++) {
			if (findElements(PWAMyRemindersPage.objDateTime).get(i).isDisplayed()) {
				extent.extentLogger("Verify date and time", "The date and time at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objDateTime).get(i).getText());
				logger.info("The date and time at index " + i + " is "
						+ findElements(PWAMyRemindersPage.objDateTime).get(i).getText());
			} else {
				extent.extentLoggerFail("Verify date and time", "Date and time is not displayed in Reminder screen");
				logger.info("Date and time is not displayed in Reminder screen");
			}
		}
		// Verify user is able to delete the content by tapping on cancel button
		int contentsBeforeDeleting = getWebDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		click(PWAAddToWatchListPage.objCancelBtn(1), " Cancel button");
		int contentsAfterDeleting = getWebDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		if (contentsAfterDeleting < contentsBeforeDeleting) {
			extent.extentLogger("Verify cancel button", "User successfully deleted the content from Reminder screen");
			logger.info("User Successfully deleted the content from Reminder screen");
		} else {
			extent.extentLoggerFail("Verify cancel button",
					"User can not delete the contents from the reminder screen");
			logger.info("User can not delete the contents from the reminder screen");
		}
		// Verify the Remove all functionality
		// click on Remove all
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all");
		// Verify contents are deleted
		if (verifyElementExist(PWAMyRemindersPage.objNoReminderMessage, "No Reminder message") == true) {
			extent.extentLogger("Verify Remove all option",
					"User successfully deleted all the contents from the Reminder screen");
			logger.info("User successfully deleted all the contents from the Reminder screen");
		} else {
			softAssert.assertAll();
			extent.extentLogger("Verify Remove all option",
					"User can not  delete all the contents from the Reminder screen");
			logger.info("User can not  delete all the contents from the Reminder screen");
		}

		// Verify We have nothing to remind you message is displayed
		if (verifyElementExist(PWAMyRemindersPage.objNoReminderMessage, "No Reminder message") == true) {
			extent.extentLogger("Verify No reminder message",
					"The message " + getWebDriver().findElement(PWAMyRemindersPage.objNoReminderMessage).getText()
							+ " is displayed when there is no contents are available");
			logger.info("The message " + getWebDriver().findElement(PWAMyRemindersPage.objNoReminderMessage).getText()
					+ " is displayed when there is no contents are available");
		} else {
			softAssert.assertAll();
			extent.extentLogger("Verify No Reminder message",
					"The message We have nothing to remind you message is not displayed");
			logger.info("The message We have nothing to remind you message is not displayed");
		}

		// verify device back button functionality
		// Click on device back button
		getWebDriver().navigate().back();
		// Verify user is navigated to Home screen
		if (activeTab.equals("Home")) {
			extent.extentLogger("Verify current tab",
					"User is navigated to " + activeTab + " tab after pressing device back button");
			logger.info("User is navigated to previous page after clicking on device back button");
		} else {
			extent.extentLoggerFail("Verify current tab",
					"User did not navigate to previos page after clicking on device back button");
			logger.info("User did not navigate to previos page after clicking on device back button");
		}

	}

	/* ================Logged In user -User action================ */
	public void UserActionLoggedInUser(String userType) throws Exception {
		extent.HeaderChildNode("User Action module- NonSubscribed user Validations");
		watchlistCheck(userType);
		// Verify Continue watching tray is displayed
		if (verifyElementExist(PWAHomePage.objContinueWatchingTray, "Coninue Watching tray") == true) {
			if (getPlatform().equalsIgnoreCase("Android")) {
				extent.extentLogger("Verify Continue watching tray ",
						getDriver().findElement(PWAHomePage.objContinueWatchingTray).getText()
								+ " tray is displayed for Non subscribed user");
				logger.info(getDriver().findElement(PWAHomePage.objContinueWatchingTray).getText()
						+ " tray is displayed for Non subscribed user");
			} else if (getPlatform().equalsIgnoreCase("Web")) {
				extent.extentLogger("Verify Continue watching tray ",
						getWebDriver().findElement(PWAHomePage.objContinueWatchingTray).getText()
								+ " tray is displayed for Non subscribed user");
				logger.info(getWebDriver().findElement(PWAHomePage.objContinueWatchingTray).getText()
						+ " tray is displayed for Non subscribed user");
			}

		} else {
			extent.extentLoggerFail("Verify continue watching tray",
					"Continue watching tray is not displayed for Non subscribed user");
			logger.info("Continue watching tray is not displayed for Non subscribed user");
		}
		// Verify Add to Watch list is displayed in Content consumption screen
		// Search any content
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, "Ondh Kathe Hella", "Search box");
		// Click on content
		click(PWASearchPage.objFirstContentCardNameAfterSearch1(1), "Content");
		String contentName = getElementPropertyToString("innerText", PWAPlayerPage.objContentName, "Title");
		verifyElementPresentAndClick(PWAPlayerPage.watchListBtn, "Add to Watchlist");
		if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browserType")
				.equalsIgnoreCase("Firefox")) {
			waitTime(100000);
		} else {
			waitTime(45000);
		}

		if (verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Popup close button");
		}
		if (getPlatform().equalsIgnoreCase("Android")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			click(PWAHamburgerMenuPage.objMyAccount, "My account");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
//			click(PWAHamburgerMenuPage.objMyAccountWeb,"My account");
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		}

		click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
		String ContentNameInWatchlist = getElementPropertyToString("innerText",
				PWAAddToWatchListPage.objFirstContentInWatchlist, "Content title");
		if (contentName.equals(ContentNameInWatchlist)) {
			extent.extentLogger("Verify Watchlist", "Added content is displayed in Watchlist screen");
			logger.info("Added content is displayed in Watchlist screen");
		} else {
			extent.extentLoggerFail("Verify Watchlist", "Added content is not displayed in Watchlist screen");
			logger.info("Added content is not displayed in Watchlist screen");
		}
		mouseHoverWatchlist();
		System.out.println(contentName);
		if (verifyElementExist(PWAAddToWatchListPage.objTooltip(contentName), "Tooltip")) {
			logger.info("Tooltip is verifed in watchlist page");
			extent.extentLogger("Tooltip", "Tooltip is verifed in watchlist page");
		}
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all");
	}

	public void FilterLanguage(String lang) throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objSelectLang(lang), lang + " language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
//		click(PWALiveTVPage.objApplyBtn,"Apply button");
	}

	/*
	 * ==========================Guest_User_User-Actions_Module====================
	 */

	public void UserActionGuestUser(String userType) throws Exception {
		extent.HeaderChildNode("User Action module- Guest user Validations");
		// Validate Continue watching tray is not displayed for Guest user
		waitTime(3000);
		watchlistCheck(userType);
		Watchlistlogin("NonSubscribe", "Gooli");
		Watchlistlogin("Subscribe", "Gooli");
		registerandCheckCW();
		if (getPlatform().equalsIgnoreCase("Web")) {
			extent.HeaderChildNode("Validating Add to Watchlist icon on tray content card");
			scrollToTheElementWEB(PWAHomePage.objFirstContentCardOfTray("Trending on ZEE5"));
			if (verifyElementExist(PWAHomePage.objFirstContentCardOfTray("Trending on ZEE5"),
					"First Content Card Of Trending on ZEE5 Tray")) {
				// validateDisplayLanguagePopup();
				Actions action = new Actions(getWebDriver());
				action.moveToElement(findElement(PWAHomePage.objFirstContentCardOfTray("Trending on ZEE5")));
				action.perform();

				if (verifyElementExist(PWAHomePage.objAddToWatchlistButtonOnTrayContentCard("Trending on ZEE5"),
						"Add To Watchlist icon on tray 1st content card")) {
					extent.extentLogger("Verify Add To Watchlist icon on tray content card",
							"Add To Watchlist icon on tray content card is displayed for guest user");
					logger.info("Add To Watchlist icon on tray content card is displayed for guest user");
				} else {
					extent.extentLoggerFail("Verify Add To Watchlist icon on tray content card",
							"Add To Watchlist icon on tray content card is not displaying for guest user");
					logger.info("Add To Watchlist icon on tray content card is not displaying for guest user");
				}

				extent.HeaderChildNode(
						"Validating Login popup after clicking on Add to Watchlist icon on tray content card");
				click(PWAHomePage.objAddToWatchlistButtonOnTrayContentCard("Trending on ZEE5"),
						"Add To Watchlist icon on tray 1st content card");
				if (verifyElementExist(PWAHomePage.objLoginRequiredPopUpHeader, "Login Required PopUp Header")) {
					extent.extentLogger(
							"Login popup is displayed when clicked on 'Add to Watchlist' icon on tray content card",
							"Login popup is displayed when clicked on 'Add to Watchlist' icon on tray content card for guest user");
					logger.info(
							"Login popup is displayed when clicked on 'Add to Watchlist' icon on tray content card for guest user");
					click(PWAHomePage.objPopupCloseicon(), "Popup Close icon");
				} else {
					extent.extentLoggerFail(
							"Login popup is not displayed when clicked on 'Add to Watchlist' icon on tray content card",
							"Login popup is not displayed when clicked on 'Add to Watchlist' icon on tray content card");
					logger.info(
							"Login popup is not displayed when clicked on 'Add to Watchlist' icon on tray content card");
				}
			}
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee5 Logo");
		}
		extent.HeaderChildNode("Validating Continue Watching Tray for Guest User");
		waitTime(3000);
		if (verifyElementExist(PWAHomePage.objContinueWatchingTray, "Continue Watching tray") == false) {
			extent.extentLogger("Verify Continue Watching tray",
					"Continue watching tray is not displayed for guest user");
			logger.info("Continue watching tray is not displayed for guest user");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify Continue Watching tray",
					"Continue watching tray is displaying for guest user");
			logger.info("Continue watching tray is displaying for guest user");
		}
//		validateDisplayLanguagePopup();
		extent.HeaderChildNode("Validating Add to Reminder button for Guest User");
		// Verify Add to Reminder is not displayed for guest user
		// Click on live tv tab
		if (getPlatform().equalsIgnoreCase("Android")) {
			navigateToAnyScreen("Live TV");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			navigateToAnyScreenOnWeb("Live TV");
		}
		waitTime(5000);
		// click on channel guide
		click(PWALiveTVPage.objChannelGuideToggle, "Channel guide");
		// Verify Reminder option is not available
		// Click on date
		click(PWALiveTVPage.objTomorrowDate, "Tomorrow date");
		FilterLanguage();

		// Verify Reminder option is available
		click(PWALiveTVPage.objShowName, "Show detail");
		if (verifyElementExist(PWALiveTVPage.objRemainderButton, "Reminder option for upcoming show ") == false) {
			extent.extentLogger("Verify Reminder button for guest user ",
					"Reminder button is not displayed for the Guest user");
			logger.info("Reminder button is not displayed for the Guest user");
		} else {
			extent.extentLoggerFail("Verify Reminder button for guest user ",
					"Reminder button is displayed for the Guest user");
			logger.info("Reminder button is displayed for the Guest user");

		}
	}

	public void ContinueWatching() throws Exception {
		extent.HeaderChildNode(
				"User Action module- Subscribed User Validations - Validating Continue Watching Tray Scenarios");
		partialScroll();
		waitTime(3000);
		// Verify Progress bar is displayed for continue watching tray
		verifyElementPresent(PWAContinueWatchingTrayPage.objProgressBar, "Progress bar");
		// Verify Progress bar is updated accordingly
		String beforePlayingContent = getElementPropertyToString("style",
				PWAContinueWatchingTrayPage.objProgressBarProgress(2), "Progress bar");
		System.out.println(beforePlayingContent);
		String[] originalRatio = beforePlayingContent.split(":");
		String progress = originalRatio[1];
		String[] exactRatio = progress.split("%");
		float progressedTime = Float.parseFloat(exactRatio[0]);

		System.out.println(progressedTime);

		// Left watch time before playing content
		String leftWatchTime = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objTotalTimeLeft(2),
				"Left time before watching content");
		String[] leftTime = leftWatchTime.split("left");
		String timeBeforeWatchingContent = leftTime[0];
		System.out.println(timeBeforeWatchingContent);
		/*
		 * //Verify movie is added to continue watching tray after watching for X
		 * minutes //Click on search click(PWAHomePage.objSearchBtn,"Search box");
		 * //Enter movie name type(PWASearchPage.objSearchEditBox, "Ondh Kathe Hella",
		 * "Search edit"); waitTime(5000); //Click on the first episode
		 * click(PWASearchPage.objFirstContentCardNameAfterSearch1(1),"Movie");
		 * Thread.sleep(10000); //Tap on pause button //Tap on player pausePlayer();
		 * verifyElementPresent(PWAPlayerPage.progressBar, "Progress bar"); WebElement
		 * slider = getDriver().findElement(PWAPlayerPage.objScrubber); Actions move =
		 * new Actions(getDriver()); Action action = (Action) move.dragAndDropBy(slider,
		 * 30, 0).build(); action.perform();
		 */
		// Verify partially watched contents are added or updated
		// Play any content
		String playingContent = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objCardTitle(2),
				"Content");
		// Click on the content
		click(PWAContinueWatchingTrayPage.objCardTitle(2), "Content");
		Thread.sleep(180000);
		// Navigate to home page
		if (getPlatform().equalsIgnoreCase("Android")) {
			navigateToAnyScreen("Home");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			navigateToAnyScreenOnWeb("Home");
		}

		Thread.sleep(4000);
		String updatedContent = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objCardTitle(1),
				"Content");
		if (playingContent.equals(updatedContent)) {
			extent.extentLogger("Verify partially watched content",
					"Partially watched contents are added to Continue watching tray");
			logger.info("Partially watched contents are added to Continue watching tray");
		} else {
			extent.extentLoggerFail("Verify partially watched content",
					"Partially watched contents are not added to Continue watching tray");
			logger.info("Partially watched contents are not added to Continue watching tray");
		}

		// Verify Progress bar is updated after watching any content from CW tray
		String afterPlayingContent = getElementPropertyToString("style",
				PWAContinueWatchingTrayPage.objProgressBarProgress(1), "Progress bar");
		System.out.println(afterPlayingContent);
		String[] originalRatio1 = afterPlayingContent.split(":");
		String progress1 = originalRatio1[1];
		String[] exactRatio1 = progress1.split("%");
		float progressedTimeAfterWatching = Float.parseFloat(exactRatio1[0]);
		System.out.println(progressedTimeAfterWatching);
		if (progressedTime < progressedTimeAfterWatching) {
			extent.extentLogger("Verify progress bar", "Progress bar is updated from " + progress + " to " + progress1);
			logger.info("Progress bar is updated from " + progress + " to " + progress1);
		} else {
			extent.extentLoggerFail("Verify progress bar", "Progress bar is not updated after watching content");
			logger.info("Progress bar is not updated after watching content");
		}

		// Verify left watch time is updated accordingly

		// Left watch time after playing content
		String leftWatchTime1 = getElementPropertyToString("innerText", PWAContinueWatchingTrayPage.objTotalTimeLeft(1),
				"Left time before watching content");
		String[] leftTime1 = leftWatchTime1.split("left");
		String timeAfterWatchingContent = leftTime1[0];
		System.out.println(timeAfterWatchingContent);
		if (!timeBeforeWatchingContent.equals(timeAfterWatchingContent)) {
			extent.extentLogger("Verify left watch time", "The left time Before watching content is " + leftTime[0]
					+ "and the left time after watching content for some time is " + leftTime1[0]);
			logger.info("The left time Before watching content is " + leftTime[0]
					+ "and the left time after watching content for some time is " + leftTime1[0]);
		} else {
			extent.extentLoggerFail("Verify Left watch time",
					"Left Watch time is not getting updated after watching content from Continue watching tray");
			logger.info("Left Watch time is not getting updated after watching content from Continue watching tray");
		}

	}

	/* =======User Action module ---> My Watchlist section for subscribed user */
	public void MyWatchlistSubscribedUser() throws Exception {
		extent.HeaderChildNode("MyWatchlist Scenarios Validations");
		String URL = "https://gwapi.zee5.com/user/v2/watchlist?country=IN&translation=en";
		String username = SubUsername;
		String pwd = SubPassword;
		AddContentsToWatchList();
		if (getPlatform().equalsIgnoreCase("Android")) {
			// Click on Hamburger menu
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			// Click on My account
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My account");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			// Click on My account
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
		}
		// Click on My Watchlist
		click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		// Verify My Watchlist header is displayed
		if (verifyElementExist(PWAAddToWatchListPage.objMyWatchlistHeader, "My Watchlist") == true) {
			extent.extentLogger("Verify My Watchlist header is displayed",
					getWebDriver().findElement(PWAAddToWatchListPage.objMyWatchlistHeader).getText()
							+ " text is displayed");
			logger.info(getWebDriver().findElement(PWAAddToWatchListPage.objMyWatchlistHeader).getText()
					+ " text is displayed");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify My Watchlist header is displayed", "My Watchlist text is not displayed");
			logger.info("My Watchlist text is not displayed");
		}

		// Verify remove all button is displayed
		verifyElementPresent(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		// Verify cancel button is displayed
		int size = getWebDriver().findElements(PWAAddToWatchListPage.objCancelBtn).size();
		for (int i = 1; i <= size; i++) {
			verifyElementPresent(PWAAddToWatchListPage.objCancelBtn(i), "Cancel button for item " + i);
		}
		// Verify if clicking shows will display only the episodes in that episodes
		// fragment
		if (getElementPropertyToString("class", PWAAddToWatchListPage.objEpisodeTab, "Episode tab")
				.contains("active")) {
			extent.extentLogger("Verify Episode tab", "User is in Episode tab");
			logger.info("User is in Episode tab");
			ArrayList<String> asset = new ArrayList<String>();
			boolean value = false;
			responseWatchlist.getRECOResponse(URL, username, pwd);
			asset = responseWatchlist.WatchlistValidationEpisodesTab();
			for (int i = 0; i < asset.size(); i++) {
				if (asset.get(i).equals("episode")) {
					value = true;
				} else {
					value = false;
					break;
				}
			}
			if (value == true) {
				extent.extentLogger("Verify Episode fragment",
						"The contents displayed in Episode fragment are all in Episode format");
				logger.info("Episodes are displayed in Episode tab");
			} else {
				extent.extentLoggerFail("Verify Episode fragment",
						"The contents displayed in Episode fragment are not in Episode format");
				logger.info("The contents displayed in Episode fragment are not in Episode format");
			}
		} else {
			extent.extentLoggerFail("Verify user tab", "User is not in Episode tab");
			logger.info("User is not in Episode tab");
		}
		// Verify if clicking Movies will display only the Movies in that Movies
		// fragment
		// Click on Movies tab
		click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
		if (getElementPropertyToString("class", PWAAddToWatchListPage.objMoviesTab, "Movies tab").contains("active")) {
			extent.extentLogger("Verify Movies tab", "User is in Movies tab");
			logger.info("User is in Movies tab");
			ArrayList<String> asset = new ArrayList<String>();
			boolean value = false;
			responseWatchlist.getRECOResponse(URL, username, pwd);
			asset = responseWatchlist.WatchlistValidationMoviesTab();
			for (int i = 0; i < asset.size(); i++) {
				if (asset.get(i).equals("movie")) {
					value = true;
				} else {
					value = false;
					break;
				}
			}
			if (value == true) {
				extent.extentLogger("Verify Movies fragment",
						"The contents displayed in Movies fragment" + " are all in Movies format");
				logger.info("Moives are displayed in Movies tab");
			} else {
				extent.extentLoggerFail("Verify Movies fragment",
						"The contents displayed in Movies fragment" + " are not in Movies format");
				logger.info("The contents displayed in Movies fragment are not in Movies format");
			}
		} else {
			extent.extentLoggerFail("Verify user tab", "User is not in Movies tab");
			logger.info("User is not in Movies tab");
		}

		// Verify if clicking Videos will display only the Videos in that Videos
		// fragment
		// Click on Videos tab
		click(PWAAddToWatchListPage.objVideoTab, "Video tab");
		if (getElementPropertyToString("class", PWAAddToWatchListPage.objVideoTab, "Video tab").contains("active")) {
			extent.extentLogger("Verify Video tab", "User is in Video tab");
			logger.info("User is in Video tab");
			ArrayList<String> asset = new ArrayList<String>();
			boolean value = false;
			responseWatchlist.getRECOResponse(URL, username, pwd);
			asset = responseWatchlist.WatchlistValidationVideoTab();
			System.out.println("Asset Size:" + asset.size());
			for (int i = 0; i < asset.size(); i++) {
				if (asset.get(i).equals("video")) {
					value = true;
				} else {
					value = false;
					break;
				}
			}
			if (value == true) {
				extent.extentLogger("Verify Video fragment",
						"The contents displayed in Video fragment" + " are all in Video format");
				logger.info("Videos are displayed in Videos tab");
			} else {
				extent.extentLoggerFail("Verify Videos fragment",
						"The contents displayed in Videos fragment" + " are not in Videos format");
				logger.info("The contents displayed in Videos fragment are not in Videos format");
			}
		} else {
			extent.extentLoggerFail("Verify user tab", "User is not in Video tab");
			logger.info("User is not in Videos tab");
		}

		// Verify user is navigated to respective show detail page
		String showName = getElementPropertyToString("innerText", PWAAddToWatchListPage.objFirstContentInWatchlist,
				"Show name");
		System.out.println(showName);
		click(PWAAddToWatchListPage.objFirstContentInWatchlist, "Show name");
		waitTime(5000);
		if (verifyElementExist(PWAAddToWatchListPage.objCompleteProfilePopUp, "Complete profile Popup") == true) {
			click(PWAAddToWatchListPage.objClosePopup, "Close button");
		}
		String showNameInShowdetailPage = getElementPropertyToString("innerText", PWAAddToWatchListPage.objContentName,
				"Content name");
		System.out.println(showNameInShowdetailPage);
		if (showName.equals(showNameInShowdetailPage)) {
			extent.extentLogger("Verify show name",
					"User is naviagted to respective show page and the show name is " + showNameInShowdetailPage);
			logger.info("User is navigated to respective show detail page from Watchlist screen");
		} else {
			extent.extentLoggerFail("Verify show name",
					"User did not naviagted to respective show page and the show name is " + showNameInShowdetailPage);
			logger.info("User did not navigated to respective show detail page from Watchlist screen");

		}
		if (getPlatform().equalsIgnoreCase("Android")) {
			// Click on Hamburger menu
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			// Click on My account
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My account");
		} else if (getPlatform().equalsIgnoreCase("Web")) {
			// Click on My account
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
		}
		// Click on My Watchlist
		click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "wouldyouliketowatch popup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
		// Verify the Cancel button functionality
		int itemsBeforeClickingCancel = findElements(PWAAddToWatchListPage.objTotalContents).size();
		// Click on cancel button
		click(PWAAddToWatchListPage.objCancelBtn(1), "First content cancel button");
		int itemsAfterClickingCancel = findElements(PWAAddToWatchListPage.objTotalContents).size();
		if (itemsAfterClickingCancel < itemsBeforeClickingCancel) {
			extent.extentLogger("Verify cancel button", "The content is deleted successfully from the watch list");
			logger.info("The content is deleted successfully from the watch list");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify cancel button", "The content is not deleted from the watch list");
			logger.info("The content is not deleted from the watch list");
		}
		// click on remove all button
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		// Verify all the contents are deleted
		int contentsAfterRemovedShowsSection = findElements(PWAAddToWatchListPage.objTotalContents).size();
		if (contentsAfterRemovedShowsSection == 0) {
			extent.extentLogger("Verify Remove all functionality",
					"The contents are removed from the Watch list for Shows fragment");
			logger.info("The Contents are removed from the Watch list for shows fragment");
		} else {
			extent.extentLoggerFail("Verify Remove all functionality",
					"The contents are not removed from the Watch list after clicking on remove all button for shows fragment");
			logger.info(
					"The Contents are not removed from the Watch list after clicking on remove all button for shows fragment");
		}
		// Verify the functionality of Remove all button
		click(PWAAddToWatchListPage.objMoviesTab, " Movies tab");
		// Click on Remove all button
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		int contentsAfterRemovedMoviestab = findElements(PWAAddToWatchListPage.objTotalContents).size();
		if (contentsAfterRemovedMoviestab == 0) {
			extent.extentLogger("Verify Remove all functionality",
					"The contents are removed from the Watch list for Movies fragment");
			logger.info("The Contents are removed from the Watch list for Movies fragment");
		} else {
			extent.extentLoggerFail("Verify Remove all functionality",
					"The contents are not removed from the Watch list after clicking on remove all button for Movies fragment");
			logger.info(
					"The Contents are not removed from the Watch list after clicking on remove all button for Movies fragment");
		}

		click(PWAAddToWatchListPage.objVideoTab, "Video tab");
		// Click on Remove all button
		click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove all button");
		int contentsAfterRemovedVideotab = findElements(PWAAddToWatchListPage.objTotalContents).size();
		if (contentsAfterRemovedVideotab == 0) {
			extent.extentLogger("Verify Remove all functionality",
					"The contents are removed from the Watch list for Video fragment");
			logger.info("The Contents are removed from the Watch list for Video fragment");
		} else {
			extent.extentLoggerFail("Verify Remove all functionality",
					"The contents are not removed from the Watch list after clicking on remove all button for Video fragment");
			logger.info(
					"The Contents are not removed from the Watch list after clicking on remove all button for Video fragment");
		}

		// Verify the device back button functionality
	}

	/**
	 * ====================TEJAS Carousel==========================
	 * 
	 */

	public void ValidatingWebPwaCarousalinalltabs(String UserType) throws Exception {

		switch (UserType) {
		case "Guest":
			extent.HeaderChildNode("User Type Guest");
			System.out.println("User Type Guest");
//		enterURLInBrowser("chrome", "https://newpwa.zee5.com");
			carouseldots("carouselDots", "home");
//		carouseldots("carouselDots","kids");

			break;

		case "NonSubcribedUser":
			extent.HeaderChildNode("User Type Loggedin User");
			System.out.println("User Type Loggedin User");
//		ZeePWALogin("Mobile", "Nonsubscribed");
			carouseldots("carouselDots", "home");
			carouseldots("carouselDots", "kids");

			break;

		case "SubcribedUser":
			extent.HeaderChildNode("User Type Subcribed User");
			System.out.println("User Type Subcribed User");
//		ZeePWALogin("E-mail", "Subscribed");
			carouseldots("carouselDots", "home");
			carouseldots("carouselDots", "kids");

		}

	}

	public void carouseldots(String carouselDots, String page) throws Exception {
		extent.HeaderChildNode("Carousal dots functionality");
//System.out.println("22222");
		Response resp = ResponseInstance.getResponseForPages(page, "en,kn");
		if (resp == null) {
			System.out.println("No response");
		} else {
//System.out.println("33333");
//waitTime(3000);
			waitForElementDisplayed(PWAHomePage.objZeeLogo, 10);
//navigateToAnyScreen(page);
			int hits = getCountweb(TextToXpathusingclass(carouselDots));
			System.out.println(hits);
			if (hits == 7) {
				System.out.println("The number of carousal dots are equal to the number of carousals present");
			} else {
				System.out.println("The number of carousal dots are not equal to the number of carousals present");
			}
			for (int i = 1; i < 3; i++) {
				int j = i - 1;
				String Carouseltitle = resp.jsonPath().getString("buckets[0].items[" + j + "].title");
				verifyElementPresentAndClick(TextToXpathusingclasswithindex("carouselDots", i),
						"clicked on carousal dot");
				System.out.println("clicked on " + i + " carousal dot");
				if (verifyElementExist(Text_To_Xpath(Carouseltitle), "carousal metadata for " + i + " index ")) {
					System.out.println("The carousel dot for " + i
							+ "th index is navigating to the respective carousel and verified ");
				} else {
					System.out.println(
							"The carousel dot for " + i + "th index is not navigating to the respective carousel ");
				}
			}
		}
	}

	public By TextToXpathusingclass(String text) throws Exception {
		return By.xpath("//div[@class='" + text + "']");
	}

	public By TextToXpathusingclasswithindex(String text, int i) throws Exception {
		return By.xpath("(//*[@class='" + text + "'])[" + i + "]");
	}

	/**
	 * ====================BHAVANA Static==========================
	 * 
	 */

	/*
	 * Main method of staticPagesandFooterSectionValidation
	 */

	public void staticPagesandFooterSectionValidation(String userType) throws Exception {

		if (userType.contentEquals("Guest") || userType.contentEquals("NonSubscribedUser")) {
			extent.HeaderChildNode(userType + " scenarios");
			extent.extentLogger("Accessing as " + userType, "Accessing as " + userType);
			logger.info("Accessing as " + userType);
			if (userType.contentEquals("NonSubscribedUser")) {
//				ZeeWEBPWALogin("NonSubscribedUser");
			}
			AboutUsScreenValidation();
			HelpCenterScreenValidation();
			TermsOfUseValiadtion();
			PrivacyPolicyValidation();
			BulidVersionValidation();
			FooterSectionValidation();
			contentLanguagewithDisplayLanguage(userType);
		} else if (userType.contentEquals("SubscribedUser")) {
			extent.HeaderChildNode("subscribed scenarios");
			logger.info("Subscribed User");
//			ZeeWEBPWALogin("SubscribedUser");
			SubscribedUserAboutUsScreenValidation();
			HelpCenterScreenValidation();
			SubscribedUserTermsOfUseValiadtion();
			SubscribedUserPrivacyPolicyValidation();
			BulidVersionValidation();
			FooterSectionValidation();
			contentLanguagewithDisplayLanguage(userType);
		}
	}

	/**
	 * Function to verify the About Us screen
	 * 
	 */

	public void AboutUsScreenValidation() throws Exception {
		HeaderChildNode("About us screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			verifyElementPresentAndClick(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
		}
		click(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
		if (verifyElementExist(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page")) {
			logger.info("User is navigated to About Us screen");
			extent.extentLogger("About Us", "User is navigated to About Us screen");
		}
		String aboutUsURL = getWebDriver().getCurrentUrl();
		if (aboutUsURL.contains("aboutus")) {
			logger.info("About Us screen is opened in webview");
			extent.extentLogger("About Us", "About Us screen is opened in webview");

		} else {
			logger.info("About Us screen is not opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsInfo, "Brief information of the application");
		Back(1);
	}

	/**
	 * Function to verify the About Us screen for Subscribed user
	 * 
	 */
	public void SubscribedUserAboutUsScreenValidation() throws Exception {
		HeaderChildNode("About us screen");
		waitTime(3000);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
		if (verifyElementExist(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page")) {
			logger.info("User is navigated to About Us screen");
			extent.extentLogger("About Us", "User is navigated to About Us screen");
		}
		System.out.println("Current URL is " + getWebDriver().getCurrentUrl());
		String aboutUsURL = getWebDriver().getCurrentUrl();
		if (aboutUsURL.contains("aboutus")) {
			logger.info("About Us screen is opened in webview");
			extent.extentLogger("About Us", "About Us screen is opened in webview");

		}
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsInfo, "Brief information of the application");
		partialScroll();
		verifyElementExist(PWAHamburgerMenuPage.objHyperLink, "Hyperlink on About Us Screen");
		logger.info("Hyperlink present on About Us screen is" + getText(PWAHamburgerMenuPage.objHyperLink));
		if (verifyElementExist(PWAHamburgerMenuPage.objHyperLink, "Hyperlink in About Us screen")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHyperLink, "Hyperlink");
			logger.info("User is navigated to respective page " + getWebDriver().getCurrentUrl());
			extent.extentLogger("About Us", "User is navigated to respective page " + getWebDriver().getCurrentUrl());
		}
		Back(1);
		waitTime(4000);
		Back(1);
	}

	/**
	 * Function to verify the Help Center screen
	 * 
	 */

	/**
	 * Function to verify the Help Center screen
	 * 
	 */

	public void HelpCenterScreenValidation() throws Exception {
		HeaderChildNode("Help Center Screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			verifyElementPresentAndClick(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
		}
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
		switchToWindow(2);
		if (verifyElementExist(PWAHamburgerMenuPage.objHelpUsHeader, "Help Center screen")) {
			logger.info("User is navigated to Help Center screen");
			extent.extentLogger("Help Center", "User is navigated to Help Center screen");
		}
		gettingStartedVerifications();
		myAccountVerifications();
		quickLinksVerifications();
		// Write to Us button
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objWritetous, "Write to us button");
		switchToWindow(3);
		if (verifyElementExist(PWAHamburgerMenuPage.objContactUs, "Contact Us page")) {
			logger.info("User is navigated to Contact Us page");
			extent.extentLogger("Contact Us", "User is navigated to Contact Us page");
		}
		WriteToUs();
		getWebDriver().close();
		waitTime(2000);
		switchToWindow(2);
		getWebDriver().close();
		switchToParentWindow();
	}

	/**
	 * Function to Validating Contact Us page in Help Center
	 * 
	 */

	public void WriteToUs() throws Exception {

		waitTime(3000);
		verifyElementExist(PWAHamburgerMenuPage.objContactUs, "Contact Us page");

		verifyElementExist(PWAHamburgerMenuPage.objSelectYourCountry, "Select your country field");

		verifyElementExist(PWAHamburgerMenuPage.objAutofilledcountry, "Auto filled country name");

		verifyElementExist(PWAHamburgerMenuPage.objDropDown, "Select your country drop down");

		verifyElementExist(PWAHamburgerMenuPage.objRegisteredMobileNumber, "Registered mobile number field");

		verifyElementExist(PWAHamburgerMenuPage.objCountryCode, "Auto filled country code");

		verifyElementExist(PWAHamburgerMenuPage.objEmailField, "Email ID field");

		verifyElementExist(PWAHamburgerMenuPage.objEmailIDAsterisk, "Email ID '*' symbol");

		verifyElementExist(PWAHamburgerMenuPage.objText, "Tell us more about you issue text message");

		verifyElementExist(PWAHamburgerMenuPage.objContentOption, "Content radio button");
		verifyElementExist(PWAHamburgerMenuPage.objProductOption, "Product radio button");

		verifyElementExist(PWAHamburgerMenuPage.objEnquiryOption, "Enquiry radio button");

		verifyElementExist(PWAHamburgerMenuPage.objFeedbackOption, "Feedback radio button");

		if (verifyElementExist(PWAHamburgerMenuPage.objContentAsDefault, "Content radio button")) {
			logger.info("Content radio option is enabled by default");
			extent.extentLogger("Content", "Content radio option is enabled by default");
		}

		verifyElementExist(PWAHamburgerMenuPage.objSelectCategory, "Select category field");

		verifyElementExist(PWAHamburgerMenuPage.objVideoNotPlaying, "Video not palying option as default");

		verifyElementExist(PWAHamburgerMenuPage.objErrorMessage, "Error message text");

		verifyElementExist(PWAHamburgerMenuPage.objErrorMessageAsterisk, "Error message '*' symbol");

		verifyElementExist(PWAHamburgerMenuPage.objSubmitButton, "SUBMIT button");

		verifyElementExist(PWAHamburgerMenuPage.objResetButton, "RESET button");

		if (getWebDriver().findElement(PWAHamburgerMenuPage.objSubmitButton).isEnabled() == false) {
			logger.info("Submit is disabled by default");
			extent.extentLogger("Submit", "Submit is disabled by default");
		}

		if (getWebDriver().findElement(PWAHamburgerMenuPage.objResetButton).isEnabled() == true) {
			logger.info("Reset is enabled by default");
			extent.extentLogger("Reset", "Reset is enabled by default");
		}

		verifyElementPresent(PWAHamburgerMenuPage.objErrorMessageAsterisk,
				"Mandatory Email ID field is highlighted by '*' symbol");

		verifyElementPresent(PWAHamburgerMenuPage.objErrorMessageAsterisk,
				"Mandatory Error message field is highlighted by '*' symbol");

		verifyElementPresent(PWAHamburgerMenuPage.objPlatformAsterisk,
				"Mandatory Platform drop down is highlighted by '*' symbol");

		type(PWAHamburgerMenuPage.objEmailField, "bhavana9518@gmail.com", "Email Id");

		type(PWAHamburgerMenuPage.objErrorMessageField, "Video couldn't play", "Error message");

		if (getWebDriver().findElement(PWAHamburgerMenuPage.objSubmitButton).isEnabled() == true) {
			logger.info("Submit is enabled by giving mandatory inputs");
			extent.extentLogger("Submit", "Submit is enabled by giving mandatory inputs");
		}

	}

	/**
	 * Function to verify the Terms of Use in Hamburger menu
	 * 
	 */

	public void TermsOfUseValiadtion() throws Exception {
		HeaderChildNode("Terms of Use screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
		if (verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen")) {
			logger.info("User is navigated to Terms of Use screen");
			extent.extentLogger("Terms of Use", "User is navigated to Terms of Use screen");
		}
		System.out.println("Current URL is " + getWebDriver().getCurrentUrl());
		String termsOfUseURL = getWebDriver().getCurrentUrl();
		if (termsOfUseURL.contains("termsofuse")) {
			logger.info("Terms of Use screen is opened in webview");
			extent.extentLogger("Terms of Use", "Terms of Use screen is opened in webview");

		}
		Back(1);
	}

	/**
	 * Function to verify the Terms of Use screen for Subscribed user
	 * 
	 */
	public void SubscribedUserTermsOfUseValiadtion() throws Exception {
		HeaderChildNode("Terms of Use screen");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
		if (verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen")) {
			logger.info("User is navigated to Terms of Use screen");
			extent.extentLogger("Terms of Use", "User is navigated to Terms of Use screen");
		}
		System.out.println("Current URL is " + getWebDriver().getCurrentUrl());
		String termsOfUseURL = getWebDriver().getCurrentUrl();
		if (termsOfUseURL.contains("termsofuse")) {
			logger.info("Terms of Use screen is opened in webview");
			extent.extentLogger("Terms of Use", "Terms of Use screen is opened in webview");

		}
		verifyElementExist(PWAHamburgerMenuPage.objTermsAndConditions, "Terms and conditions of application");
		verifyElementExist(PWAHamburgerMenuPage.objOfferTermsAndConditions, "Offers Terms and conditions");
		partialScroll();
		scrollDownWEB();
		verifyElementExist(PWAHamburgerMenuPage.objOfferDUration, "Offer duration");
		partialScroll();
		verifyElementExist(PWAHamburgerMenuPage.objCashbackByAmazonPay,
				"Steps to get Cashback for payment by Amazon pay");
		verifyElementExist(PWAHamburgerMenuPage.objCashbackByAnyBankCard,
				"Steps to get 30% Cashback on any Bank Credit/Debit card");
		verifyElementExist(PWAHamburgerMenuPage.objCashbackOnPaytm, "Steps to get 50% Paytm Cashback");
		Back(1);
	}

	/**
	 * Function to verify the Privacy Policy in Hamburger menu
	 * 
	 */
	public void PrivacyPolicyValidation() throws Exception {
		HeaderChildNode("Privacy Policy screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
		if (verifyElementExist(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen")) {
			logger.info("User is navigated to Privacy Policy screen");
			extent.extentLogger("Privacy Policy", "User is navigated to Privacy Policy screen");
		}
		String privacyPolicyURL = getWebDriver().getCurrentUrl();
		if (privacyPolicyURL.contains("privacyPolicyURL")) {
			logger.info("Privacy Policy screen is opened in webview");
			extent.extentLogger("Privacy Policy", "Privacy Policy screen is opened in webview");

		}
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyInfo, "Legal information of the application");
		partialScroll();
		verifyElementPresent(PWAHamburgerMenuPage.objSecurityInfo, "Security Information of the application");
		Back(1);
	}

	/**
	 * Function to verify the Privacy Policy screen for Subscribed user
	 * 
	 */

	public void SubscribedUserPrivacyPolicyValidation() throws Exception {
		HeaderChildNode("Privacy Policy screen");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
		if (verifyElementExist(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen")) {
			logger.info("User is navigated to Privacy Policy screen");
			extent.extentLogger("Privacy Policy", "User is navigated to Privacy Policy screen");
		}
		System.out.println("Current URL is " + getWebDriver().getCurrentUrl());
		String privacyPolicyURL = getWebDriver().getCurrentUrl();
		if (privacyPolicyURL.contains("privacyPolicyURL")) {
			logger.info("Privacy Policy screen is opened in webview");
			extent.extentLogger("Privacy Policy", "Privacy Policy screen is opened in webview");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objLinkOnPrivacyPolicy, "Hyper link in Privacy Policy Screen");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLinkOnPrivacyPolicy, "Hyperlink");
		waitTime(5000);
		String link = getWebDriver().getCurrentUrl();
		if (link.contains("pagenotfound")) {
			logger.info("User is not able to navigate to the Contact Us page");
			extent.extentLoggerFail("Hyper link", "User is not able to navigate to the Contact Us page");
		}
		Back(1);
		partialScroll();
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyInfo, "Legal information of the application");
		verifyElementPresent(PWAHamburgerMenuPage.objSecurityInfo, "Security Information of the application");
		partialScroll();
		Back(1);
	}

	/**
	 * Function to verify the Build version in Hamburger menu
	 * 
	 */

	public void BulidVersionValidation() throws Exception {
		HeaderChildNode("Build version");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementExist(PWAHamburgerMenuPage.objBuildVersion, "Build Version");
		String version = getText(PWAHamburgerMenuPage.objBuildVersion);
		logger.info("Build version is : " + version);
		extent.extentLogger("version", "Build version is : " + version);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	}

	/**
	 * Function to verify the Footer Section of Home page
	 * 
	 */

	public void FooterSectionValidation() throws Exception {
		HeaderChildNode("Footer Section");
		partialScroll();
		scrollDownWEB();
		verifyElementPresent(PWAHomePage.objDownloadApps, "Download Apps text");
		verifyElementPresent(PWAHomePage.objAndroidPlayStoreIcon, "Android play store icon");

		// Instagram
		waitTime(3000);
		verifyElementPresent(PWAHomePage.objInstagramIcon, "Instagram icon");
		for (int i = 0; i < 6; i++) {
			scrollDownWEB();
			waitTime(6000);
			if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
				verifyElementExist(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
				waitTime(4000);
				click(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
				break;
			}
		}
		click(PWAHomePage.objInstagramIcon, "Instagram icon");

		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			waitTime(3000);
			verifyElementPresent(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
			waitTime(5000);
			click(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
		}
		switchToWindow(2);
		if (verifyElementExist(PWAHomePage.objInstagramPage, "Instagram page follow button")) {
			logger.info("User is navigated to Instagram page");
			extent.extentLogger("Instagram", "User is redirected to Instagram page");
		}
		getWebDriver().close();
		switchToParentWindow();
		// Twitter
		verifyElementPresentAndClick(PWAHomePage.objTwitterIcon, "Twitter icon");
		switchToWindow(2);
		if (verifyElementExist(PWAHomePage.objTwitterPage, "Twitter page follow button")) {
			logger.info("User is navigated to Twitter page");
			extent.extentLogger("Twitter", "User is redirected to Twitter page");
		}
		getWebDriver().close();
		switchToParentWindow();
		// Facebook
		verifyElementPresentAndClick(PWAHomePage.objFacebookIcon, "Facebook icon");
		switchToWindow(2);
		String facebook = getWebDriver().getCurrentUrl();
		if (facebook.contains("facebook")) {
			logger.info("User is redirected to facebook page");
			extent.extentLogger("Facebook", "User is redirected to facebook page");
		}
		getWebDriver().close();
		switchToParentWindow();
		verifyElementPresent(PWAHomePage.objCopyRightText, "Copyright text");
		verifyElementPresentAndClick(PWAHomePage.objUpArrow, "Up Arrow");
		if (getWebDriver().findElement(PWAHomePage.objUpArrow).isDisplayed() == false) {
			logger.info("After clicking the Up arrow top of the page is displayed");
			extent.extentLogger("Up Arrow", "After clicking the Up arrow top of the page is displayed");
		}
		// Contact Us screen
		verifyElementPresentAndClick(PWAHomePage.objHelp, "Help Center in footer section");
		switchToWindow(2);
		verifyElementPresentAndClick(PWAHomePage.objwritetous, "Write to us");
		switchToWindow(3);
		if (verifyElementExist(PWAHomePage.objcontactus, "Contact Us page")) {
			logger.info("User is navigated to Contact Us page");
			extent.extentLogger("Contact Us", "User is naviagted to Contact Us page");

		}
		getWebDriver().close();
		waitTime(5000);
		switchToWindow(2);
		getWebDriver().close();
		switchToParentWindow();
		waitTime(5000);
		// android play store
		verifyElementPresentAndClick(PWAHomePage.objAndroidPlayStoreIcon, "Google play store icon");
		switchToWindow(2);
		if (verifyElementExist(PWAHomePage.objGooglePlayLogo, "Android Google Play icon") == true) {
			logger.info("User is navigated to Android Google Play store");
			extent.extentLogger("Google play store", "User is redirected to Google paly store page");
		}
		getWebDriver().close();
		switchToParentWindow();
		waitTime(5000);
		// iOS app store
		verifyElementPresentAndClick(PWAHomePage.objIoSAppStoreIcon, "iOS app store icon");
		switchToWindow(2);
		String iOSURL = getWebDriver().getCurrentUrl();
		if (iOSURL.contains("apple")) {
			logger.info("User is redirected to iOS app store page");
			extent.extentLogger("iOS app store", "User is redirected to iOS app store page");
		}
		getWebDriver().close();
		switchToParentWindow();
	}

	/**
	 * Function to verify the FAQ's in Help Center Screen under Getting started
	 * category
	 */

	public void gettingStartedVerifications() throws Exception {
		waitTime(3000);
		verifyElementExist(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Getting Started"), "Getting started tab");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("What is ZEE5"), "What is zee5");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to What is ZEE5 page");
			extent.extentLogger("Article", "User is navigated to What is ZEE5 page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Registering with ZEE5"),
				"Registering with ZEE5");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Registeing with ZEE5 page");
			extent.extentLogger("Article", "User is navigated to Registering with ZEE5 page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Purchasing a subscription"),
				"Purchasing a subscription");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Purchasing a subscription page");
			extent.extentLogger("Article", "User is navigated to Purchasing a subscription page");
		}
		Back(1);

		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("How do I watch ZEE5 on my television?"),
				"How do I watch ZEE5 on my telivision");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to How do I watch ZEE5 on my telivision page");
			extent.extentLogger("Article", "User is navigated to How do I watch ZEE5 on my telivision page");
		}
		Back(1);
	}

	/**
	 * Function to verify the FAQ's in Help Center Screen under My Account category
	 */

	public void myAccountVerifications() throws Exception {
		verifyElementExist(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("My Account"), "My Account tab");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Managing your Subscription"),
				"Managing your Subscription ");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Managing your Subscription  page");
			extent.extentLogger("Article", "User is navigated to Managing your Subscription  page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I can"),
				"I can't sign in to ZEE5 ");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to I can't sign in to ZEE5 page");
			extent.extentLogger("Article", "User is navigated to I can't sign in to ZEE5 page");
		}
		Back(1);

		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I made a payment but my subscription"),
				"I made a payment but my subscription isn't active / My subscription is missing");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info(
					"User is navigated to I made a payment but my subscription isn't active / My subscription is missing page");
			extent.extentLogger("Article",
					"User is navigated to I made a payment but my subscription isn't active / My subscription is missing page");
		}
		Back(1);

		verifyElementPresentAndClick(
				PWAHamburgerMenuPage.objHelpSectioOptionsHeading("I want to update my profile information"),
				"I want to update my profile information");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to I want to update my profile information  page");
			extent.extentLogger("Article", "User is navigated to I want to update my profile information page");
		}
		Back(1);
	}

	/**
	 * Function to verify the FAQ's in Help Center Screen under Quick Links category
	 */
	public void quickLinksVerifications() throws Exception {
		verifyElementExist(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Quick Links"), "Quick Links tab");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("How Can I"), "How Can I ");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to How Can I page");
			extent.extentLogger("Article", "User is navigated to How Can I page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Offers"),
				"Offers & Partnerships ");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Offers & Partnerships page");
			extent.extentLogger("Article", "User is navigated to Offers & Partnerships page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Contests"),
				"Contests & Quizzes ");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Contests & Quizzes page");
			extent.extentLogger("Article", "User is navigated to Contests & Quizzes page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Before TV"), "Before TV");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Before TV page");
			extent.extentLogger("Article", "User is navigated to Before TV page");
		}
		Back(1);

		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpSectioOptionsHeading("Cancel Subscription"),
				"Cancel Subscription");

		if (verifyElementExist(PWAHamburgerMenuPage.objArticleTitle, "Article title") == true) {
			logger.info("User is navigated to Cancel Subscription page");
			extent.extentLogger("Article", "User is navigated to Cancel Subscription page");
		}
		Back(1);
	}

	/**
	 * Function to verify the Display language and Static pages Content language
	 */
	public void contentLanguagewithDisplayLanguage(String userType) throws Exception {

		// Changing display language to Kannada
		verifyElementPresentAndClick(PWAHomePage.objLanguage, "Language button");
		verifyElementPresentAndClick(PWAHomePage.objKannadaWEB, "Kannada option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApply, "Apply button on display langauge screen");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplybutton, "Apply button on content language screen");
		// About Us
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		if (userType.contains("Guest")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsinKannada, "About Us option in kannada");
		} else {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsinKannada2, "About Us option in kannada");
		}
		String title1 = getText(PWAHamburgerMenuPage.objAboutUsTextInPage);
		System.out.println("Title " + title1);
		if (title1.contains("About Us")) {
			logger.info("Content of the About Us page is not according to the display language");
			extent.extentLoggerFail("About Us",
					"Content of the page About Us is not according to the display language");
		}
		Back(1);
		// Terms of Use
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		if (userType.contains("Guest")) {
			waitForElementAndClick(PWAHamburgerMenuPage.objTermsInKannada, 8, "Terms of Use option in Kannada");
		} else if (userType.contains("NonSubscribedUser") || userType.contains("SubscribedUser")) {
			waitTime(3000);
			verifyElementPresent(PWAHamburgerMenuPage.objTermsInKannada2, "Terms of Use option in Kannada");
			JSClick(PWAHamburgerMenuPage.objTermsInKannada2, "Terms of Use option in Kannada");
		}
		String title2 = getText(PWAHamburgerMenuPage.objTermsOfUseScreen);
		System.out.println("Title " + title2);
		if (title2.contains("Terms of Use")) {
			logger.info("Content of the Terms of Use page is not according to the display language");
			extent.extentLoggerFail("Terms of Use",
					"Content of the Terms of Use page is not according to the display language");
		}
		Back(1);
		// Privacy Policy
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if (userType.contains("Guest")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicyInKannadA,
					"Privacy Policy option in Kannada");
		} else {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicyInKannadA2,
					"Privacy Policy option in Kannada");
		}
		String title3 = getText(PWAHamburgerMenuPage.objPrivacyPolicyScreen);
		System.out.println("Title " + title3);
		if (title3.contains("Privacy Policy")) {
			logger.info("Content of the Privacy Policy page is not according to the display language");
			extent.extentLoggerFail("Privacy Policy",
					"Content of the Privacy Policy page is not according to the display language");
		}
		Back(1);
		// Changing display language to English
		verifyElementPresentAndClick(PWAHomePage.objLanguage, "Language button");
		verifyElementPresentAndClick(PWAHomePage.objEnglishWEB, "English option");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApply, "Apply button on display langauge screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objApplybutton, "Apply button on content language screen");
	}

	/*
	 * =========================================BHAVANA External
	 * Links========================
	 */

	public void LinksValidation(String userType) throws Exception {
		if (userType.contains("Guest")) {
			extent.HeaderChildNode("Guest user scenarios");
			InternalLinksValidation();
			ExternalLinksValidation();
		}

		else if (userType.contains("NonSubscribedUser")) {
			extent.HeaderChildNode("NonSubscribed User scenarios");
			// ZeeWEBPWALogin("NonSubscribedUser");
			InternalLinksValidation();
			ExternalLinksValidation();
		} else if (userType.contains("SubscribedUser")) {
			extent.HeaderChildNode("subscribed scenarios");
			// ZeeWEBPWALogin("SubscribedUser");
			InternalLinksValidation();
			ExternalLinksValidation();
		}
	}

//
	public void InternalLinksValidation() throws Exception {

		extent.HeaderChildNode("Internal Links Validation");
		waitTime(2000);
		verifyElementPresent(PWAHomePage.objAboutUsInFooterSection, "About Us in footer section");
		// for (int i = 0; i < 6; i++) {
		// scrollDownWEB();
		// waitTime(6000);
		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			verifyElementExist(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
			waitTime(4000);
			click(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
			// break;
		}
		// }
		waitTime(2000);
		JSClick(PWAHomePage.objAboutUsInFooterSection, "About Us in footer section");
		waitTime(4000);
		if (verifyElementExist(PWAHomePage.objAboutUs, "About Us screen")) {
			logger.info("User is navigated to About Us Screen");
		}
		Back(1);
		scrollDownWEB();
		// for (int i = 0; i < 6; i++) {
		// scrollDownWEB();
		// waitTime(6000);
		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			verifyElementExist(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
			waitTime(4000);
			click(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
			// break;
		}
		// }
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objHelp, "Help Center in footer section");
		JSClick(PWAHomePage.objHelp, "Help Center in footer section");
		waitTime(8000);
		switchToWindow(2);
		waitTime(5000);
		if (verifyElementExist(PWAHomePage.objHelpScreen, "Help Center screen")) {
			logger.info("User is navigated to Help Center Screen");
		}
		getWebDriver().close();
		switchToParentWindow();
		verifyElementPresent(PWAHomePage.objPrivacyPolicyInFooterSection, "Privacy Policy in footer section");
		scrollDownWEB();
		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			verifyElementPresentAndClick(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
		}
		JSClick(PWAHomePage.objPrivacyPolicyInFooterSection, "Privacy Policy in footer section");
		if (verifyElementExist(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
			logger.info("User is navigated to Privacy Policy Screen");
		}
		Back(1);
		scrollDownWEB();
		verifyElementPresentAndClick(PWAHomePage.objTermsOfUseInfooterSection, "Terms of Use in footer section");
		if (verifyElementExist(PWAHomePage.objTerms, "Terms of Use screen")) {
			logger.info("User is navigated to Terms of Use Screen");
		}
		Back(1);
	}

	/**
	 * Function to verify external links
	 */

	public void ExternalLinksValidation() throws Exception {
		extent.HeaderChildNode("External Links Validation");
		waitTime(5000);
		// Instagram
		scrollDownWEB();
		if (verifyElementExist(PWAHomePage.objWhatToWatchPopUp, "wondering what to watch pop up")) {
			verifyElementPresentAndClick(PWAHomePage.objWhatToWatchCloseButton, "Pop up close button");
		}
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objInstagramIcon, "Instagram icon");
		JSClick(PWAHomePage.objInstagramIcon, "Instagram icon");
		waitTime(3000);
		switchToWindow(2);
		waitTime(3000);
		if (verifyElementExist(PWAHomePage.objInstagramPage, "Instagram page follow button")) {
			logger.info("User is navigated to Instagram page");
		}
		getWebDriver().close();
		switchToParentWindow();
		// Twitter
		partialScroll();
		scrollDownWEB();
		verifyElementPresent(PWAHomePage.objTwitterIcon, "Twitter icon");
		JSClick(PWAHomePage.objTwitterIcon, "Twitter icon");
		waitTime(3000);
		switchToWindow(2);
		if (verifyElementExist(PWAHomePage.objTwitterPage, "Twitter page follow button")) {
			logger.info("User is navigated to Twitter page");
		}
		getWebDriver().close();
		switchToParentWindow();
		partialScroll();
		scrollDownWEB();
		// Facebook
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objFacebookIcon, "Facebook icon");
		JSClick(PWAHomePage.objFacebookIcon, "Facebook icon");
		waitTime(3000);
		switchToWindow(2);
		String facebook = getWebDriver().getCurrentUrl();
		if (facebook.contains("facebook")) {
			logger.info("User is redirected to facebook page");
			extent.extentLogger("Facebook", "User is redirected to facebook page");
		}
		getWebDriver().close();
		switchToParentWindow();
		waitTime(5000);
		// android play store
		verifyElementPresent(PWAHomePage.objAndroidPlayStoreIcon, "Google play store icon");
		JSClick(PWAHomePage.objAndroidPlayStoreIcon, "Google play store icon");
		waitTime(3000);
		switchToWindow(2);
		if (verifyElementExist(PWAHomePage.objGooglePlayLogo, "Android Google Play icon") == true) {
			logger.info("User is navigated to Android Google Play store");
			extent.extentLogger("Google play store", "User is redirected to Google paly store page");
		}
		getWebDriver().close();
		switchToParentWindow();
		waitTime(5000);
		// iOS app store
		verifyElementPresent(PWAHomePage.objIoSAppStoreIcon, "iOS app store icon");
		JSClick(PWAHomePage.objIoSAppStoreIcon, "iOS app store icon");
		waitTime(3000);
		switchToWindow(2);
		String iOSURL = getWebDriver().getCurrentUrl();
		if (iOSURL.contains("apple")) {
			logger.info("User is redirected to iOS app store page");
			extent.extentLogger("iOS app store", "User is redirected to iOS app store page");
		}
		getWebDriver().close();
		switchToParentWindow();
	}

//public static void scrollDownWEB() {
//	JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
//	jse.executeScript("window.scrollBy(0,250)", "");
//}

	/**
	 * ===============================BHAVANA CONTENT
	 * DETAILS============================
	 * 
	 */

	public void contentDetailsValidation(String userType) throws Exception {
		HeaderChildNode("Content Details Module");
		if (userType.contentEquals("Guest")) {
			extent.HeaderChildNode("Guest user scenarios");
			logger.info("Accessing as Guest User");
			ContentDetails("Guest");

		} else if (userType.contentEquals("NonSubscribedUser")) {
			extent.HeaderChildNode("Non subscribed scenarios");
			logger.info("Accessing as Non subscribed User");
			// ZeeWEBPWALogin("NonSubscribedUser");
			ContentDetails("NonSubscribedUser");
			checkDurationandProgressVideocontent("NonSubscribedUser");

		} else if (userType.contentEquals("SubscribedUser")) {
			extent.HeaderChildNode("Subscribed scenarios");
			logger.info("Accessing as Subscribed User");
			// ZeeWEBPWALogin("SubscribedUser");
			ContentDetails("SubscribedUser");
			checkDurationandProgressVideocontent("SubscribedUser");
		}
	}

	public void checkDurationandProgressVideocontent(String userType) throws Exception {
		extent.HeaderChildNode("checkDurationandProgressVideo");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchBtnWEB, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("timedAnchorEpisode");

		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
//	hideKeyboard();
		waitTime(3000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(10000);
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		// Content elapsed time should update with the content playback
		if (userType.equals("Guest")) {
			String currentDuration = getText(PWAPlayerPage.objcurrenttime);
			System.out.println("Current time: " + currentDuration);
		} else {
			String currentDuration = getText(PWAPlayerPage.objcurrenttime);
			System.out.println("Elapsed time: " + currentDuration);
		}
		String totalDuration = getText(PWAPlayerPage.objtotaltime);
		System.out.println("Total time: " + totalDuration);
		String progress = null;
		if (verifyElementExist(PWAPlayerPage.objprogressBar, "ProgressBar")) {
			progress = getAttributValue("style", PWAPlayerPage.objprogressProgress);
			System.out.println("Progress : " + progress);
		}
		Thread.sleep(5000);
		verifyElementPresentAndClick(PWAPlayerPage.forward10SecBtn, "10 sec forward");
		verifyElementPresentAndClick(PWAPlayerPage.playBtn, "Play btn");
		// Waiting for some time
		Thread.sleep(10000);
		Thread.sleep(10000);
		System.out.println("Waited for 5 sec");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		String currentDuration1 = getText(PWAPlayerPage.objcurrenttime);
		System.out.println("Current time: " + currentDuration1);
		String totalDuration1 = getText(PWAPlayerPage.objtotaltime);
		System.out.println("Total time: " + totalDuration1);
		String progress1 = getAttributValue("style", PWAPlayerPage.objprogressProgress);
		System.out.println("Progress : " + progress1);
		// Validate the availabilty and functionality of progress bar button
		if (!progress.equals(progress1)) {
			System.out.println("Progress Bar is functional");
			extent.extentLogger("Progress Bar is functional", "Progress Bar is functional");
			logger.info("Progress Bar is functional");
		} else {
			System.out.println("Progress Bar is not functional");
			extent.extentLoggerFail("Progress Bar is not functional", "Progress Bar is not functional");
			logger.info("Progress Bar is not functional");
		}
		// Content duration should be static on the player
		if (totalDuration.contains(totalDuration1)) {
			System.out.println("Content duration is static");
			extent.extentLogger("Content duration is static", "Content duration is static");
			logger.info("Content duration is static");
		} else {
			System.out.println("Content duration is not static");
			extent.extentLoggerFail("Content duration is not static", "Content duration is not static");
			logger.info("Content duration is not static");
		}
	}

	public void ContentDetails(String userType) throws Exception {

		extent.HeaderChildNode("Content details Validation");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchBtnWEB, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsShow");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(5000);
		mandatoryRegistrationPopUp(userType);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(10000);
		System.out.println("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
		extent.extentLogger("Navigated to tab : " + getText(PWAHomePage.objSeletedTab),
				"Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
		logger.info("Navigated to tab : " + getText(PWAHomePage.objSeletedTab));
		waitTime(5000);
		if (verifyElementExist(PWAShowsPage.objShowdeatilPlayIcon, "ShowDetailPage")) {
			System.out.println("Navigated to ShowdetailPage");
			extent.extentLogger("Navigated to ShowdetailPage", "Navigated to ShowdetailPage"); // any
			logger.info("Navigated to ShowdetailPage");
		}
		if (verifyElementExist(PWAShowsPage.objEpisodeTrayinShowdetailPage, "Episode Tray below Feature carousel")) {
			System.out.println("Episode Tray below Feature carousel is present");
			extent.extentLogger("Episode Tray below Feature carousel is present",
					"Episode Tray below Feature carousel is present");
			logger.info("Episode Tray below Feature carousel is present");
		}
		if (verifyElementExist(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown")) {
			click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
			waitTime(5000);
			List<WebElement> objShowDetailEpisodeDropdownValuesSize = getWebDriver().findElements(By.xpath(
					"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span"));
			waitTime(3000);
			System.out.println("DropDown Size : " + objShowDetailEpisodeDropdownValuesSize.size());
			ArrayList<String> Listofepisode = new ArrayList<String>();
			for (int i = 1; i < objShowDetailEpisodeDropdownValuesSize.size(); i++) {
				Listofepisode.add(getAttributValue("aria-label", PWAShowsPage.objShowDetailEpisodeDropdownValues(i)));
			}
			System.out.println("Episodes Listed in dropdown " + Listofepisode);
		}
		// To scroll elements in drop down
		List<WebElement> objShowDetailEpisodeDropdownValuesSize = getWebDriver().findElements(By.xpath(
				"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span"));
		waitTime(3000);
		if (objShowDetailEpisodeDropdownValuesSize.size() > 6) {
			JavascriptExecutor je = (JavascriptExecutor) getWebDriver();
			je.executeScript("arguments[0].scrollIntoView(true);",
					objShowDetailEpisodeDropdownValuesSize.get(objShowDetailEpisodeDropdownValuesSize.size() - 5));
		}
		waitTime(3000);
		System.out.println("Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		extent.extentLogger("Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown),
				"Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		logger.info("Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		List<WebElement> objShowDetailNonSelectedEpisodeDropdownValues = getWebDriver().findElements(By.xpath(
				"((((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span[@aria-selected='false'])"));
		waitTime(3000);
		ArrayList<String> ListofNonSelectedEpisode = new ArrayList<String>();
		for (int i = 1; i < objShowDetailNonSelectedEpisodeDropdownValues.size(); i++) {
			ListofNonSelectedEpisode
					.add(getAttributValue("aria-label", PWAShowsPage.objShowDetailNonSelectedEpisodeDropdownValues(i)));
		}
		System.out.println("NON Selected Episode : " + ListofNonSelectedEpisode);
		JSClick(PWAShowsPage.objShowDetailNonSelectedEpisodeDropdownValues(1), "Non-Selected Episode");
		JSClick(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		System.out.println("Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		extent.extentLogger("Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown),
				"Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		logger.info("Selected Episode : " + getText(PWAShowsPage.objSelectedEpisodeinDropdown));
		JSClick(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		scrolltillBackToArrowAppears();
		if (verifyElementExist(PWAShowsPage.objShowdeatilPlayIcon, "ShowDetailPage")) {
			System.out.println("Navigated to Top");
			extent.extentLogger("Navigated to Top of page", "Navigated to Top of page");
			logger.info("Navigated to Top");
		}
		verifyElementPresent(PWAShowsPage.objShareIcon, "Share icon");
		waitTime(3000);
		click(PWAShowsPage.objShareIcon, "Share icon");
		WebShareFunctionalityContent();

		// Watchhistory is showing at back-end response properly
		Response resp = ResponseInstance
				.getResponse("https://gwapi.zee5.com/content/tvshow/0-6-1392?translation=en&country=IN");
		extent.extentLogger("BackEnd data : ", "BackEnd data : ");
		logger.info("BackEnd data : ");
		System.out.println("Show : " + resp.jsonPath().getString("original_title"));
		extent.extentLogger("Show : " + resp.jsonPath().getString("original_title"),
				"Show : " + resp.jsonPath().getString("id"));
		logger.info("Show : " + resp.jsonPath().getString("original_title"));
		System.out.println("id : " + resp.jsonPath().getString("id"));
		extent.extentLogger("id : " + resp.jsonPath().getString("id"), "id : " + resp.jsonPath().getString("id"));
		logger.info("id : " + resp.jsonPath().getString("id"));
		System.out.println("duration : " + resp.jsonPath().getString("duration"));
		extent.extentLogger("duration : " + resp.jsonPath().getString("duration"),
				"duration : " + resp.jsonPath().getString("duration"));
		logger.info("duration : " + resp.jsonPath().getString("duration"));
		System.out.println("asset_Type : " + resp.jsonPath().getString("asset_type"));
		extent.extentLogger("asset_Type : " + resp.jsonPath().getString("asset_type"),
				"asset_Type : " + resp.jsonPath().getString("asset_type"));
		logger.info("asset_Type : " + resp.jsonPath().getString("asset_type"));
		// check for reco trays

		if (userType.equals("NonSubscribedUser") || userType.equals("SubscribedUser")) {
			verifyElementExist(PWAShowsPage.objWatchLatestCTA, "Watch latest episode CTA");// i tried this..but it is
																							// not clicking
			// waitTime(3000);
			click(PWAShowsPage.objWatchLatestCTAPlayicon, "Watch latest episode CTA"); // RUN NOW
			waitTime(15000);
			Response recoresp = null;
			if (userType.equals("NonSubscribedUser")) {
				recoresp = ResponseInstance.getRECOResponse(
						"https://gwapi.zee5.com/content/reco?asset_id=0-1-manual_2voun4m1qsh0&country=IN&translation=en&languages=en,kn&version=6&region=KA",
						NonSubUsername, NonSubPassword);
			} else if (userType.equals("SubscribedUser")) {
				recoresp = ResponseInstance.getRECOResponse(
						"https://gwapi.zee5.com/content/reco?asset_id=0-1-manual_2voun4m1qsh0&country=IN&translation=en&languages=en,kn&version=6&region=KA",
						SubUsername, SubPassword);
			}
			String recotray = recoresp.jsonPath().getString("buckets[0].title");
			waitTime(3000);
			System.out.println("recoTray : " + recotray);

			waitTime(3000);
			try {
				scrollToTheElementWEB(TextToXpath(recotray));
				if (verifyElementExist(TextToXpath(recotray), "tray")) {
					extent.extentLogger("RECO Tray", "RECO Tray");
					logger.info("RECO Tray");
				} else {
					extent.extentLoggerFail("RECO Tray", "NO RECO Tray");
					logger.info("NO RECO Tray");
				}

			} catch (Exception e) {
				extent.extentLoggerFail("RECO Tray", "NO RECO Tray");
				logger.info("NO RECO Tray");
			}
		}
	}

	public void scrollToTheElementWEB(By element) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		waitTime(3000);
		jse.executeScript("arguments[0].scrollIntoView(true);", findElement(element));
		jse.executeScript("window.scrollBy(0,-50)", "");
	}

	public void scrolltillBackToArrowAppears() throws Exception {
		// scrollToBottomOfPage();
		scrollDownWEB();
		for (int i = 1; i <= 10; i++) {
			// Swipe("UP", 1);
			partialScroll();
			if (verifyElementExist(PWAShowsPage.objBackToTopArrow, "Back to Top Arrow")) {
				waitTime(2000);
				click(PWAShowsPage.objBackToTopArrow, "BackToTop Arrow");
				break;
			}
		}
	}

	/*
	 * Function to validate the Web Share functionality
	 */
	public void WebShareFunctionalityContent() throws Exception {
		// click on share Option

		// Verify Facebook share option
		Thread.sleep(2000);
		verifyElementPresent(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		Thread.sleep(2000);
		// Verify Twitter share option
		verifyElementPresent(PWAPlayerPage.twitterShareBtn, "Twitter share option");
		Thread.sleep(2000);
		// Verify Email Share option
		verifyElementPresent(PWAPlayerPage.emailShareBtn, "Email share option");
		Thread.sleep(2000);
		// Click on Facebook Share option
		click(PWAPlayerPage.facebookShareBtn, "Facebook share option");
		Thread.sleep(2000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		// Switch to window
		verifyAlert();
		switchToWindow(2);
		Thread.sleep(2000);
		// Verify user is navigate to Facebook page
		if (verifyElementExist(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
			getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
			verifyElementPresentAndClick(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
			waitTime(2000);
			verifyAlert();
			waitTime(2000);
			verifyElementPresentAndClick(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
			waitTime(3000);
			verifyAlert();
			switchToWindow(1);
			waitTime(3000);
		}

		// Click on Share option
		click(PWAPlayerPage.shareBtn, "Share Option");
		Thread.sleep(2000);

		// Click on Twitter share option
		click(PWAPlayerPage.twitterShareBtn, "Twitter share option");
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		Thread.sleep(2000);
		// Verify user is navigated to Twitter page
		switchToWindow(2);
		Thread.sleep(2000);
		verifyAlert();
		waitTime(3000);
		verifyElementExist(PWALiveTVPage.objTwitterEmailField, "Twitter Email field");
		waitTime(2000);
		click(PWALiveTVPage.objTwitterEmailField, "Twitter Email field");
		getWebDriver().findElement(PWALiveTVPage.objTwitterEmailField).sendKeys("zee5latest@gmail.com");
		waitTime(2000);
		verifyElementPresentAndClick(PWALiveTVPage.objTwitterPasswordField, "Twitter Password field");
		getWebDriver().findElement(PWALiveTVPage.objTwitterPasswordField).sendKeys("User@123");
		verifyElementPresentAndClick(PWALiveTVPage.objTwitterLoginButton, "Twitter Login button");
		waitTime(2000);
		verifyAlert();
		waitTime(2000);
		verifyElementPresentAndClick(PWALiveTVPage.objTweetButton, "Tweet button");
		waitTime(2000);
		verifyAlert();
		switchToParentWindow();
		Thread.sleep(2000);
	}

	/**
	 * ===============================YASHASWINI
	 * NewsPage============================
	 * 
	 */

	public void newsPageValidation(String tabName) throws Exception {
		extent.HeaderChildNode("News Page Validation");
		navigateToAnyScreenOnWeb(tabName);
		waitTime(2000);
		String tab = getText(PWAHomePage.objActiveTab);
		System.out.println(tab);
		logger.info(tab + " tab is highlighted");
		extent.extentLogger("Tab", tab + " tab is highlighted");
//	dismissDisplayContentLanguagePopUp();
		waitTime(3000);
		click(PWAPremiumPage.objWEBMastheadCarousel, "Carousel");
		waitForElementDisplayed(PWAPlayerPage.objPlaybackVideoOverlay, 10);
		waitForPlayerAdToComplete("Live Player");
		if (verifyElementExist(PWAPlayerPage.objPlaybackVideoOverlay, "Player")) {
			logger.info("Navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Navigated to Consumption Page");
		} else {
			logger.info("Not navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Not navigated to Consumption Page");
		}
		newsPlayerIconValidations();
		verifyElementExist(PWAPlayerPage.objLiveTag, "Live Tag");
		FullScreen();
		Back(1);
		if (verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");
			if (verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}
		Back(1);
		RotateTrayValidation();
		waitTime(3000);
		newsTrayValidation();
	}

	public void RotateTrayValidation() throws Exception {
		extent.HeaderChildNode("Tray Rotate Icon Validation");
		partialScroll();
		verifyElementPresentAndClick(PWAPremiumPage.objNextArrowBtn, "Next Arrow Button");
		if (verifyElementExist(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button")) {
			logger.info("Tray is rotated");
			extent.extentLogger("Tray is rotated", "Tray is rotated");
		} else {
			logger.info("Tray is not rotated");
			extent.extentLogger("Tray is not rotated", "Tray is not rotated");
		}
		click(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button");
	}

	public void mouseHover() {
		Actions action = new Actions(getWebDriver());
		WebElement player = getWebDriver().findElement(PWAPlayerPage.objPlaybackVideoOverlay);
		action.moveToElement(player).build().perform();
	}

	public void newsPlayerIconValidations() throws Exception {
		extent.HeaderChildNode("Validation of Player Controls");
//	waitForPlayerAdToComplete("Video Player");
		waitTime(5000);
		mouseHover();
		click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
		waitTime(2000);
//	click(PWAPlayerPage.objPlaybackVideoOverlay, "player screen");
		mouseHover();
		verifyElementExist(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon");
		verifyElementExist(PWAPlayerPage.pauseBtn, "Play/Pause icon");
		verifyElementExist(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon");
		verifyElementExist(PWAPlayerPage.settingsBtn, "Settings icon");
	}

	public void FullScreen() throws Exception {
		extent.HeaderChildNode("Validating full screen mode");
//	waitForPlayerAdToComplete("Video Player");
		try {
			mouseHover();
			waitTime(5000);
			JSClick(PWAPlayerPage.maximizeBtn, "Maximize button");
			waitTime(2000);
			mouseHover();
			click(PWAPlayerPage.minimizeBtn, "Minimize button");
		} catch (Exception e) {
		}
	}

	public void newsTrayValidation() throws Exception {
		extent.HeaderChildNode("Verifing the trays displayed in News Tab");
		String languageSmallText = allSelectedLanguagesWEB();
		System.out.println(languageSmallText);
		Response resp = ResponseInstance.getResponseForPages("news", languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		List<String> apitotaltrays = resp.jsonPath().getList("buckets");
		System.out.println(apitotaltrays.size());
		for (int i = 1; i < apitotaltrays.size(); i++) {
			String traytitle = resp.jsonPath().getString("buckets[" + i + "].title");
			apiTitleList.add(traytitle);
		}
		System.out.println("api: " + apiTitleList);
		List<String> uiTitleList = new LinkedList<String>();
		List<WebElement> uitotaltrays = findElements(By.xpath("//*[@class='titleLink']"));
		System.out.println(uitotaltrays.size());
		for (int j = 0; j < 4; j++) {
			// String trayTitle
			// =findElement(By.xpath("(//*[@class='titleLink'])["+(j+1)+"]")).getText();
			String trayTitle = apiTitleList.get(j);
			uiTitleList.add(trayTitle);
			partialScrollDown();
			if (apiTitleList.get(j).equalsIgnoreCase(uiTitleList.get(j))) {
				logger.info("API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));
				extent.extentLogger("Tray validation",
						"API title: " + apiTitleList.get(j) + " is verified with UI title: " + uiTitleList.get(j));
			}
		}
		System.out.println("UI: " + uiTitleList);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	/**
	 * ===============================YASHASWINI
	 * LandingPage============================
	 * 
	 */

	public void ContinuewatchingTray(String userType) throws Exception {
		extent.HeaderChildNode("Landing page module: Continue watching tray");
		partialScroll();
		waitTime(2000);
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAHomePage.objContinueWatchingTray, "Continue watching tray") == false) {
				logger.info("Continue Watching tray is not displayed for Guest User");
				extent.extentLogger("Continue Watching tray", "Continue Watching tray is not displayed for Guest User");
			} else {
				logger.info("Continue Watching tray is displayed for Guest User");
				extent.extentLoggerFail("Continue Watching tray", "Continue Watching tray is displayed for Guest User");
			}
		}
		if ((userType.equals("NonSubscribedUser") || (userType.equals("SubscribedUser")))) {
			if (verifyElementExist(PWAHomePage.objContinueWatchingTray, "Continue watching tray")) {
				logger.info("Continue Watching tray is displayed for logged in User");
				extent.extentLogger("Continue Watching tray", "Continue Watching tray is displayed for logged in User");
			} else {
				logger.info("Continue Watching tray is not displayed for logged in user");
				extent.extentLogger("Continue Watching tray",
						"Continue Watching tray is not displayed for logged in user");
			}
		}
	}

	public void LandingPagegap(String text, String Moviename, String userType) throws Exception {
		HeaderChildNode("Validation functionality of MyProfile option,View All button");
		verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		if (verifyElementExist(PWAHomePage.objHomeInHambugerMenu, "Home button")) {
			logger.info("Home button is highlighted in Hamburger Menu");
			extent.extentLogger("Home", "Home button is highlighted in Hamburger Menu");
		}
		click(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		Actions actions = new Actions(getWebDriver());
		WebElement contentcard = getWebDriver().findElement(PWAHomePage.objTabName("Shows"));
		actions.moveToElement(contentcard).perform();
		waitTime(5000);
		if (verifyElementExist(PWAHomePage.objHoverMenu("Shows"), "Shows menu")) {
			logger.info("Shows tab overlay is displayed when mouse hover is performed");
			extent.extentLogger("Shows", "Shows tab overlay is displayed when mouse hover is performed");
		}
		verifyElementPresentAndClick(PWAHomePage.objOverlayTray, "Overlay Tray");
		String Tray = getText(PWAHomePage.objOverlayTray);
		System.out.println(Tray);
		if (verifyElementExist(PWAHomePage.objOverlayTrayActive(Tray), "Overlay Tray in shows page")) {
			logger.info("Clicked on overlay menu Tray option and tray is highlighted");
			extent.extentLogger("Tray", "Clicked on overlay menu Tray option and tray is highlighted");
		}
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, text, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Zee original");
		waitTime(35000);
		if (verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Subscribe Popup Title")) {
			verifyElementExist(PWASubscriptionPages.objLoginSectionInPopup, "Login section");
			click(PWASubscriptionPages.objLoginButtonInPopup, "Login button");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, SubUsername, "Email Field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, SubPassword, "Password field");

			waitTime(5000);
			click(PWALoginPage.objWebLoginButton, "Login Button");
			waitTime(10000);
			click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
			verifyElementPresent(PWAPlayerPage.pauseBtn, "Pause button");
		}
		click(PWAHomePage.objZeelogo1, "Zee logo");
		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
		verifyElementPresent(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");
		verifyElementPresent(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Watchlist"), "My watchlist");
		verifyElementPresent(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Reminders"), "My Reminders");
		verifyElementPresent(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Transactions"), "My Transactions");
		verifyElementPresent(PWAHamburgerMenuPage.objMyProfileOptionsWEB("Logout"), "Logout option");
		verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
		verifyElementPresentAndClick(PWAPremiumPage.objViewAllBtn, "View all button");
		waitTime(5000);
		scrollByWEB();
		if (verifyElementExist(PWAHomePage.objUpArrow, "Up Arrow")) {
			logger.info("User is able to scroll in view all page");
			extentLogger("View all", "User is able to scroll in view all page");
		}
		click(PWAPremiumPage.obj1stContentInViewAllPage, "Content");
		if (!verifyElementExist(PWAPremiumPage.objViewAllPage, "Veiw all")) {
			logger.info("User is navigated from view all content to respective page");
			extentLogger("View all", "User is navigated from view all content to respective page");
		}
		click(PWAHomePage.objZeelogo1, "Zee logo");
		logout();
		verifyElementPresentAndClick(PWALoginPage.objWebLoginBtn, "Login button");
		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, ExpiredUserName, "Email Field");
		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, ExpiredUserPassword, "Password field");

		waitTime(5000);
		click(PWALoginPage.objWebLoginButton, "Login Button");
		waitTime(7000);
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, Moviename, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Movie");
		waitTime(5000);
		if (verifyElementExist(PWASearchPage.objSubscribepopup, "Subscribepopup")) {
			logger.info("Premium contents are not played for expired user");
			extent.extentLogger("Premium", "Premium contents are not played for expired user");
			click(PWASearchPage.objSubscribepopupCLoseButton, "close button");
		}
		click(PWAHomePage.objZeelogo1, "Zee logo");
		logout();
	}

	@SuppressWarnings("unused")
	public void guesttrayTitleAndContentValidationWithApiData(String tab, String api) throws Exception {
		extent.HeaderChildNode(tab + " page validation with Api response");
		navigateToAnyScreenOnWeb(tab);
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
		actions.moveToElement(menuOption).build().perform();

		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		List<String> contentList = resp.jsonPath().getList("buckets[1].items");
		System.out.println(contentList.size());
		partialScrollDown();
		List<WebElement> card = getWebDriver().findElements(By.xpath("((//div[@class='slick-list'])[2]//img)"));
		System.out.println(card.size());
		for (int i = 0; i < card.size(); i++) {
			// API DATA
			String title = resp.jsonPath().getString("buckets[1].items[" + i + "].title");
			String businessType = resp.jsonPath().getString("buckets[1].items[" + i + "].business_type");
			String minuteType_isDRM = null;
			minuteType_isDRM = resp.jsonPath().getString("buckets[1].items[" + i + "].is_drm");

			apiTitleList.add(title);
			WebElement contentCard = getWebDriver()
					.findElement(By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));
			actions.moveToElement(contentCard).build().perform();

			// to get metadata from content
			String contentMetadata = getAttributValue("title",
					By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));

			// String trayTitle = apiTitleList.get(i);
			System.out.println("UI data : " + contentMetadata);
			System.out.println("api data : " + apiTitleList.get(i));
			logger.info("UI data " + title);
			logger.info("API data " + apiTitleList.get(i));
			extent.extentLogger("UI data ", "UI data " + contentMetadata);
			extent.extentLogger("API data ", "API data " + apiTitleList.get(i));
			if (contentMetadata.equalsIgnoreCase(apiTitleList.get(i))) {
				logger.info("Metadata on the content card is valid with Api data");
				extent.extentLogger("Metadata", "Metadata on the content card is valid with Api data");
			} else {
				logger.info("Metadata on the content card is not valid with Api data");
				extent.extentLoggerFail("Metadata", "Metadata on the content card is not valid with Api data");
			}

			// MINUTELY CONTENT CHECK
			waitTime(1000);
//			System.out.println(minuteType_isDRM);
//			if (minuteType_isDRM == null) {
//				System.out.println("No minute content attached");
//			} else {
//				System.out.println("Minute content present");

			if (verifyElementExist(PWAPremiumPage.specificContentisMinuteimage(Tray_Title, i + 1), "Minute Content")) {
				logger.info("MinuteContent is Present");
				extent.extentLogger("MinuteContent", "MinuteContent is Present");
			} else {
				logger.info("MinuteContent is not Present");
				extent.extentLogger("MinuteContent", "MinuteContent is not Present");
			}
//			}

			// PREMIUM ICON CHECK
			waitTime(1000);
			if (businessType.contains("premium")) {
				if (verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i + 1), "Premium icon")) {
					logger.info("Premium icon is Present");
					extent.extentLogger("Premium icon", "Premium icon is Present");
				} else {
					logger.info("Premium icon is not Present");
					extent.extentLoggerFail("Premium icon", "Premium icon not Present");
				}

			} else if (businessType.contains("advertisement") || businessType.contains("free")) {
				if (!verifyElementExist(PWAPremiumPage.specificContentPremiumIcon(Tray_Title, i), "Premium icon")) {
					logger.info("premium icon not present and Content is NonPremium");
					extent.extentLogger("Premium icon", "premium icon not present and Content is NonPremium");
				} else {
					logger.info("premium icon is present");
					extent.extentLoggerFail("Premium icon", "premium icon is present");
				}
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Icon")) {
				logger.info("Play icon is displayed");
				extent.extentLogger("Play", "Play icon is displayed");
			} else {
				logger.info("Play icon is not displayed");
				extent.extentLogger("Play", "Play icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Icon")) {
				logger.info("Share icon is displayed");
				extent.extentLogger("Share", "Share icon is displayed");
			} else {
				logger.info("Share icon is not displayed");
				extent.extentLogger("Share", "Share icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardWatchlistBtn, "Watchlist Icon")) {
				logger.info("Watchlist icon is displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is displayed");
			} else {
				logger.info("Watchlist icon is not displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is not displayed");
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void FreeContentAndPremiumContent(String userType) throws Exception {
		extent.HeaderChildNode("Landing page module:Free And Premium Content availability");
		verifyElementPresentAndClick(PWASearchPage.objSearchBtn, "Search button");
		waitTime(2000);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("freeMovie2");
		type(PWASearchPage.objSearchEditBox, keyword, "Search Field");
		waitTime(3000);
		click(PWASearchPage.objSpecificSearch(keyword), "Searched Show");
		waitTime(10000);
		if (userType.equals("Guest")) {
			if (verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register popup ")) {
				click(PWAPlayerPage.objWEBCloseBtnLoginPopup, "Register Pop up close button");
			}
		}
		if (verifyElementExist(PWAPlayerPage.objPlaybackVideoOverlay, "Player")) {
			logger.info("Navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Navigated to Consumption Page");
		} else {
			logger.info("Not navigated to Consumption Page");
			extent.extentLoggerFail("Consumption Page", "Not navigated to Consumption Page");
		}
		Back(1);
		PremiumContent(userType);
	}

	public void PremiumContent(String userType) throws Exception {
		type(PWASearchPage.objSearchEditBox, "kurukshetra", "Search Field");
		click(PWASearchPage.objSpecificSearch2("kurukshetra"), "Searched Show");
		waitTime(3000);
		if (userType == "Guest" || userType == "NonSubscribedUser") {
			verifyElementExist(PWAPlayerPage.objGetPremium, "Get Premium Button");
		}
		waitTime(10000);
		if (verifyElementExist(PWAPlayerPage.objPlaybackVideoOverlay, "Player")) {
			logger.info("Navigated to Consumption Page with Trailer");
			extent.extentLogger("Consumption Page", "Premium content with Trailer is displayed");
		} else {
			logger.info("Didn't navigate to Consumption Page");
			extent.extentLoggerFail("Consumption Page", "Premium content with Trailer is not displayed");
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

	public void landingpagePropertiesValidation(String userType) throws Exception {
		extent.HeaderChildNode("Validating Homepage Properties");

		if (verifyElementPresent(PWALandingPages.obj_WEBPwa_HamburgerMenu1, "Hamburger Menu")) {
			logger.info("HamburgerMenu icon is displayed");
			extent.extentLogger("HamburgerMenu", "HamburgerMenu icon is displayed");
		} else {
			logger.info("HamburgerMenu icon is not displayed");
			extent.extentLogger("HamburgerMenu", "HamburgerMenu icon is not displayed");
		}
		// Zee5Logo
		if (verifyElementPresent(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo")) {
			logger.info("Zee5 Logo is displayed");
			extent.extentLogger("Zee5 Logo", "Zee5 Logo is displayed");
		} else {
			logger.info("Zee5 Logo is not displayed");
			extent.extentLogger("Zee5 Logo", "Zee5 Logo is not displayed");
		}

		// Search button
		if (verifyElementPresent(PWALandingPages.obj_Pwa_SearchBtn, "Search")) {
			logger.info("Search button is displayed");
			extent.extentLogger("Search", "Search button is displayed");
		} else {
			logger.info("Search button is not displayed");
			extent.extentLogger("Search", "Search button is not displayed");
		}
		// Subscription_button
		waitTime(2000);
		if (verifyElementPresent(PWALandingPages.obj_Pwa_Subcription_teaser_btn, "Subcription button")) {
			logger.info("Subscription button is displayed");
			extent.extentLogger("Subscription", "Subscription button is displayed");
		} else {
			logger.info("Subscription button is not displayed");
			extent.extentLogger("Subscription", "Subscription button is not displayed");
		}

		partialScroll();

		if (verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");

			if (verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}

		Back(1);
		waitTime(2000);
		WebHomepageTrayTitleAndContentValidationWithApiData(ResponseInstance.getResponse());
	}

	public static void scrollUp() {
		js.executeScript("window.scrollBy(0,-250)", "");
	}

	public void BackWeb(int x) {

		try {

			for (int i = 0; i < x; i++) {
				getWebDriver().navigate().back();
				logger.info("Back button is tapped");
				extent.extentLogger("Back", "Back button is tapped");
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public boolean navigateToAnyScreen(String screen) throws Exception {
		for (int i = 0; i < 3; i++) {
			try {
				verifyElementPresentAndClick(PWAHomePage.objTabName(screen), " selected screen :" + screen);
				break;
			} catch (Exception e) {
				try {
					swipeOnTab("Left");
					verifyElementPresentAndClick(PWAHomePage.objTabName(screen), " selected screen :" + screen);
					break;
				} catch (Exception exc) {
					swipeOnTab("Right");
				}
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public void swipeOnTab(String dire) throws InterruptedException {
		extent.HeaderChildNode("Swipping on tab");
		touchAction = new TouchAction(getDriver());
		Dimension size = getDriver().findElement(PWAHomePage.objTabContBar).getSize();
		if (dire.equalsIgnoreCase("Left")) {
			int startx = (int) (size.width * 0.5);
			int endx = (int) (size.width * 0.1);
			int starty = size.height / 2;
			touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
		} else if (dire.equalsIgnoreCase("Right")) {
			int startx = (int) (size.width * 0.5);
			int endx = (int) (size.width * 0.9);
			int starty = size.height / 2;
			touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
		}
	}

	/**
	 * ===============================TEJAS ShowsPage============================
	 * 
	 */

	public void ShowsValidationWeb(String userType) throws Exception {
		selectLanguages();
		HeaderChildNode("Shows page");
		if (userType.contentEquals("Guest")) {
			extent.HeaderChildNode("Guest user scenarios");
			logger.info("Accessing as Guest User");
			mandatoryRegistrationPopUp(userType);
			landingPagesValidationWeb("Shows");
			landingPagesTrailerAndPopUpValidationWeb("Guest", "Shows");
			mandatoryRegistrationPopUp(userType);
			verifyLandscapeforFreeContentWeb();
			verifyLandscapeforPremiumContentWeb();
			mandatoryRegistrationPopUp(userType);
			navigateToAnyScreen("Shows");
			RotateTrayValidation();
		} else if (userType.contentEquals("NonSubscribedUser")) {
			extent.HeaderChildNode("Non subscribed scenarios");
			logger.info("Accessing as Non subscribed User");
			landingPagesValidationWeb("Shows");
			landingPagesTrailerAndPopUpValidationWeb("NonSubscribedUser", "Shows");
			verifyLandscapeforFreeContentWeb();
			verifyLandscapeforPremiumContentWeb();
			navigateToAnyScreen("Shows");
			RotateTrayValidation();
			VerifyExternalLinkInShowsLandingPageWeb();
		} else if (userType.contentEquals("SubscribedUser")) {
			extent.HeaderChildNode("Subscribed scenarios");
			logger.info("Accessing as Subscribed User");
			landingPagesValidationWeb("Shows");
			landingPagesTrailerAndPopUpValidationWeb("SubscribedUser", "Shows");
			verifyLandscapeforFreeContentWeb();
			verifyLandscapeforPremiumContentWeb();
			navigateToAnyScreen("Shows");
			RotateTrayValidation();
			VerifyExternalLinkInShowsLandingPageWeb();
		}
	}

	public void selectLanguages() throws Exception {
		click(PWAHamburgerMenuPage.objLanguageBtnWEB, "Language Button");
		waitTime(2000);
		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn, 2, "Content Languages");
		click(PWALanguageSettingsPage.objNonSelectedEng, "English");
		click(PWALanguageSettingsPage.objNonSelectedHin, "Hindi");
		click(PWALanguageSettingsPage.objNonSelectedKan, "Kannada");
		click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
		waitTime(3000);
	}

	public void landingPagesValidationWeb(String tabName) throws Exception {
		extent.HeaderChildNode("Landing Pages Validation");
		verifyElementPresentAndClick(PWAHomePage.objTabName(tabName), tabName);
		// waitTime(5000);
		String tab = getText(PWAHomePage.objActiveTab);
		System.out.println(tab);
		logger.info(tab + " tab is highlighted");
		extent.extentLogger("Tab", tab + " tab is highlighted");

		// check if tray is loaded

		if (verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button")) {
			click(PWAPremiumPage.objViewAllBtn, "View All Button");
			if (verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			} else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}
		waitTime(3000);
		Back(1);
		waitTime(4000);
		for (int i = 0; i < 5; i++) {
			if (findElements(PWAMusicPage.objPremiumTag).size() > 0) {
				logger.info("Premium tag is displayed");
				extent.extentLogger("Premium Tag", "Premium Tag is displayed");
				break;
			} else {
				logger.info("Premium tag is not displayed");
				extent.extentLogger("Premium Tag", "Premium Tag is not displayed");
				partialScroll();
			}
		}
		for (int i = 0; i < 5; i++) {
			if (findElements(PWAPremiumPage.objMinuteContent).size() > 0) {
				logger.info("Minute content is displayed");
				extent.extentLogger("Minute content", "Minute content is displayed");
				break;

			} else {
				logger.info("Minute content is not displayed");
				extent.extentLogger("Minute content", "Minute content is not displayed");
				partialScroll();
			}
		}
		partialScroll();
		partialScroll();
		verifyElementPresentAndClick(PWAMusicPage.objArrowToNavigateTop, "Back to Top arrow");
		// waitTime(2000);

	}

//public static void partialScroll() {
//		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
//		jse.executeScript("window.scrollBy(0,250)", "");
//	}

	public void landingPagesTrailerAndPopUpValidationWeb(String userType, String tabName) throws Exception {
		extent.HeaderChildNode("Landing Page Carousel Validation");
		waitTime(2000);
		JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		if (tabName.equals("Shows")) {
			for (int i = 0; i <= 10; i++) {
				try {
					WebElement premiumText = getWebDriver().findElement(PWAHomePage.objWEBShowsPagePlayCarousel);
					executor.executeScript("arguments[0].click();", premiumText);
					logger.info("Clicked on Carousel card");
					extent.extentLogger("", "Clicked on Carousel card");
					break;
				} catch (Exception e) {
					Thread.sleep(2000);
					try {
						getWebDriver().findElement(PWAHomePage.objWEBShowsPagePlayCarousel).click();
						logger.info("Clicked on Carousel card");
						extent.extentLogger("", "Clicked on Carousel card");
						break;
					} catch (Exception e1) {
						logger.error("Failed to click on Carousel card");
						extent.extentLoggerFail("", "Failed to click on Carousel card");
					}
				}
			}
		} else {
			verifyElementPresentAndClick(PWAZee5OriginalPage.objWEBMastheadCarousel, "Carousel Card");
		}
		if (userType.contains("Guest")) {
			if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Sign Up Pop Up")) {
				click(PWASearchPage.objCloseRegisterDialog, "Close Button");
			}
		}
		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
		}
		if (verifyElementExist(PWAPlayerPage.objPlaybackVideoOverlay, "Player")) {
			logger.info("Navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Navigated to Consumption Page");
		} else {
			logger.info("Not navigated to Consumption Page");
			extent.extentLogger("Consumption Page", "Not navigated to Consumption Page");
		}
		if (verifyElementExist(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button")) {
			watchTrailerButtonFunctionalityShows(userType);
		} else {
			logger.info("Trailer is not available for the selected content");
			extent.extentLogger("Trailer", "Trailer is not available for the selected content");
		}
		click(PWAHomePage.objZeelogo1, "Zee Logo");
	}

	public void watchTrailerButtonFunctionalityShows(String userType) throws Exception {
		extent.HeaderChildNode("Watch Trailer Button Validation");
		click(PWAPremiumPage.objWatchTrailerBtn, "Watch Trailer Button");

		if (userType.contains("Guest")) {
			if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Why Register Pop Up")) {
				click(PWASearchPage.objCloseRegisterDialog, "Close Button");

			}

		}

		if (userType.contains("NonSubscribedUser")) {
			if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt,
					"Complete Your Profile pop up")) {
				click(CompleteYourProfilePopUp.objCloseBtn, "Close Button");

			}

		}

		waitTime(20000);
		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
		}

		if (userType.contains("NonSubscribedUser") || (userType.contains("Guest"))) {

			verifyElementExist(PWAPremiumPage.objSubscribeNowAndGoAdFree, "Subscribe Now And Go Ad Free Message");
			verifyElementExist(PWAPremiumPage.objGetPremium, "Get Premium Button");
		}

		if (userType.contains("NonSubscribedUser")) {
			if (verifyElementExist(CompleteYourProfilePopUp.objCompleteYourProfileTxt,
					"Complete Your Profile pop up")) {
				click(CompleteYourProfilePopUp.objCloseBtn, "Close Button");

			}

		}

		if (verifyElementExist(PWASubscriptionPages.objSubscribepopup, "Subscribe popup")) {
			verifyElementPresentAndClick(PWAMusicPage.objGetPremiumCloseBtn, "Close Button");
		}

	}

	public void verifyLandscapeforFreeContentWeb() throws Exception {
		String userType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		mandatoryRegistrationPopUp(userType);
		String keyword = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("consumptionsShow");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, keyword + "\n", "Search Edit box: " + keyword);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows Tab");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(keyword), "Search Result");
		waitTime(4000);
		partialScroll();
		verifyElementExist(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		click(PWAShowsPage.objShowDetailEpisodeDropdown, "Episode Dropdown");
		click(PWAShowsPage.objShowDetailNonSelectedEpisodeDropdownValues(1), "Second Episode set");
		waitTime(2000);
		click(PWAShowsPage.objEpisodeCard, "First Episode Card");
		waitForPlayerAdToComplete("Video Player");
		mouseHover();
		click(PWAPlayerPage.pauseBtn, "Pause button");
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		waitTime(2000);
		mouseHover();
		click(PWAPlayerPage.minimizeBtn, "Minimize button");
		click(PWAHomePage.objZeelogo1, "Zee Logo");
	}

//public void FullScreen() throws Exception {
//		extent.HeaderChildNode("Validating full screen mode");
//	//	waitForPlayerAdToComplete("Video Player");
//		try {
//		mouseHover();
//		waitTime(5000);
//		click(PWAPlayerPage.maximizeBtn, "Maximize button");
//		waitTime(2000);
//		mouseHover();
//		click(PWAPlayerPage.minimizeBtn, "Minimize button");
//		}
//		catch(Exception e) {
//		}
//	}

//public void mouseHover() {
//		Actions action = new Actions(getWebDriver());
//		WebElement player = getWebDriver().findElement(PWAPlayerPage.objPlaybackVideoOverlay);
//		action.moveToElement(player).build().perform();
//	}

//public void waitForPlayerAdToComplete(String playerType) throws Exception {
//		boolean adWasDisplayed = false;
//		boolean playerDisplayed = false;
//		int confirmCount = 0;
//		main: for (int trial = 0; trial < 90; trial++) {
//			try {
//				driver.findElement(PWAPlayerPage.objAd);
//				adWasDisplayed = true;
//				if (trial == 5) {
//					logger.info("Ad play in progress");
//					extent.extentLogger("AdPlayInProgress", "Ad play in progress");
//				}
//				if (Math.floorMod(trial, 10) == 0)
//					System.out.println("Ad play in progress");
//				Thread.sleep(1000);
//			} catch (Exception e) {
//				try {
//					if (playerType.equals("Live Player")) {
//						driver.findElement(PWAPlayerPage.objLivePlayerLiveTag);
//					} else if (playerType.equals("Video Player")) {
//						driver.findElement(PWAPlayerPage.objPlayerSeekBar);
//					}
//					playerDisplayed = true;
//					Thread.sleep(1000);
//					confirmCount++;
//					if (confirmCount == 3) {
//						if (adWasDisplayed == false) {
//							logger.info("Ad did not play");
//							extent.extentLogger("AdDidNotPlay", "Ad did not play");
//						} else {
//							logger.info("Ad play complete");
//							extent.extentLogger("AdPlayComplete", "Ad play complete");
//						}
//						break main;
//					}
//				} catch (Exception e1) {
//					Thread.sleep(1000);
//				}
//			}
//		}
//		if (playerDisplayed == false && adWasDisplayed == false) {
//			logger.error("Ad play failure");
//			extent.extentLoggerFail("failedAd", "Ad play failure");
//		}
//	}

	public void verifyLandscapeforPremiumContentWeb() throws Exception {
		String userType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, "Baarish" + "\n", "Search Edit box Baarish");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows Tab");
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult("Baarish"), "Search Result");
		waitTime(8000);
		partialScroll();
		click(PWAShowsPage.objEpisodeCardTwo, "Second Episode Card");
		waitForPlayerAdToComplete("Video Player");
		mouseHover();
		click(PWAPlayerPage.pauseBtn, "Pause button");
		click(PWAPlayerPage.maximizeBtn, "Maximize button");
		waitTime(2000);
		mouseHover();
		click(PWAPlayerPage.minimizeBtn, "Minimize button");
		click(PWAPlayerPage.playBtn, "Play button");
		if (verifyElementExist(PWASubscriptionPages.objGetPremiumPopupTitle, "Subscribe Pop Up Title")) {
			click(PWASubscriptionPages.objPopupCloseButton, "Subscribe Pop Up Close Button");
		} else {
			logger.info("Trailer is playing");
			extent.extentLogger("", "Trailer is playing");
		}
		click(PWAHomePage.objZeelogo1, "Zee Logo");
	}

	public void VerifyExternalLinkInShowsLandingPageWeb() throws Exception {
		HeaderChildNode("Verify ExternalLink In ShowsLanding Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInHamburger,
				"More settings in settings section");
		verifyElementPresent(PWAHamburgerMenuPage.objLanguageBtn, "Language button");
		waitTime(2000);
		JSClick(PWAHamburgerMenuPage.objLanguageBtn, "Language button");
		waitTime(3000);
//	Swipe("UP", 1);
//	Swipe("DOWN", 1);
//	Swipe("UP", 1);
//	Swipe("DOWN", 1);
//	Swipe("UP", 1);
//	Swipe("DOWN", 1);
		partialScroll();
		partialScroll();
		partialScroll();
		partialScroll();
		scrollDownWEB();
		waitTime(3000);
		if (verifyElementExist(PWAShowsPage.objPlayAndWin, "Play and Win tray")) {
			click(PWAShowsPage.objPlayAndWin, "Play and Win tray");
			click(PWAShowsPage.objGuessScore, "Play and Win");
			if (getDriver().getCurrentUrl().contains("games")) {
				System.out.println("Navigation successfull");
				logger.info("User navigated to SAREGAMAPA game page");
				extent.extentLogger("New web page loaded", "User navigated to new web page");
			} else {
				System.out.println("ViewAll Wrap page not displayed");
				logger.info("User didn't navigated to SAREGAMAPA game page");
				extent.extentLogger("New web page is not loaded", "User didn't navigated to new web page");
			}
		} else {
			logger.info("Play and Win tray is not displayed");
		}
	}

//public void RotateTrayValidation() throws Exception {
//	extent.HeaderChildNode("Tray Rotate Icon Validation");
//		partialScroll();
//	verifyElementPresentAndClick(PWAPremiumPage.objNextArrowBtn, "Next Arrow Button");
//	if(verifyElementExist(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button"))
//	{
//		logger.info("Tray is rotated");
//		extent.extentLogger("Tray is rotated", "Tray is rotated");
//		}else {
//			logger.info("Tray is not rotated");
//			extent.extentLogger("Tray is not rotated", "Tray is not rotated");
//		}
//		click(PWAPremiumPage.objPreviousArrowBtn, "Previous Arrow Button");
//}

//public void navigateToAnyScreen(String screen) throws Exception {
//	for (int i = 0; i < 3; i++) {
//		try {
//			verifyElementPresentAndClick(PWAHomePage.objTabName(screen), " selected screen :" + screen);
//			break;
//		} catch (Exception e) {
//			try {
//				swipeOnTab("Left");
//				verifyElementPresentAndClick(PWAHomePage.objTabName(screen), " selected screen :" + screen);
//				break;
//			} catch (Exception exc) {
//				swipeOnTab("Right");
//			}
//		}
//	}
//}

	public void navigateToHome() {
		String url = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("url");
		getWebDriver().get(url);
	}

	/**
	 * Modified reco scripts
	 */

	/**
	 * ===============================Tanisha Recommendation
	 * Web============================
	 * 
	 */

	public void verificationOfRecoTalamoosWeb(String userType) throws Exception {
		if (userType.equals("Guest")) {
			playContentsToTriggerRecoApiWeb(userType);
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Home", "Trending on ZEE5");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Shows", "Trending Shows");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Movies", "Trending Movies");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Music", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "News", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Premium", "Trending Now");
			verifyRecoTrayAndPlayContentInDetailsPage(userType, "consumptionsPage");
		} else if (userType.equals("NonSubscribedUser")) {
			playContentsToTriggerRecoApiWeb(userType);
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Home", "Trending on ZEE5");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Premium", "Trending Now");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Shows", "Trending Shows");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Movies", "Trending Movies");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Music", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "News", "Recommended for you");
			verifyRecoTrayAndPlayContentInDetailsPage(userType, "consumptionsPage");
		} else if (userType.equals("SubscribedUser")) {
			playContentsToTriggerRecoApiWeb(userType);
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Home", "Trending on ZEE5");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Home", "You may also like");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Home", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Shows", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Home", "Because you watched");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Premium", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Movies", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "News", "Recommended for you");
			verifyRecoTrayAndPlayContentWithoutAPIWeb(userType, "Music", "Recommended for you");
			verifyRecoTrayAndPlayContentInDetailsPage(userType, "consumptionsPage");
		} else {
			logger.error("Incorrect userType passed to method");
			extent.extentLogger("incorrectUser", "Incorrect userType passed to method");
		}
	}

	public void playContentsToTriggerRecoApiWeb(String userType) throws Exception {
		extent.HeaderChildNode("Play different contents to trigger Recommendation API");
		playAContentForRecoWeb("Music",
				Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("musicToTriggerReco"),
				userType);
		playAContentForRecoWeb("Movies",
				Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("movieToTriggerReco"),
				userType);
		playAContentForRecoWeb("Episode", Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("episodeToTriggerReco"), userType);
		playAContentForRecoWeb("News",
				Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("newsToTriggerReco"),
				userType);
	}

	public void verifyRecoTrayAndPlayContentWithoutAPIWeb(String userType, String tabName, String recoTrayTitle)
			throws Exception {
		extent.HeaderChildNode(tabName + " tab: Validation of \"" + recoTrayTitle + "\" tray");
		logger.info(tabName + " tab: Verification of " + recoTrayTitle);
		extent.extentLogger("recoverification", tabName + " : Verification of " + recoTrayTitle);
		String nextPageTitle = "";
		boolean firstAssetClicked = false;
		if (navigateToAnyScreenOnWeb(tabName)) {
			firstAssetClicked = swipeTillTrayAndClickFirstAsset(userType, 15, recoTrayTitle,
					"\"" + recoTrayTitle + "\" tray", tabName);
			if (firstAssetClicked) {
				try {
					nextPageTitle = getText(PWAShowsPage.objShowsTitle);
					logger.info("Shows Details page is displayed");
					extent.extentLogger("showDetails", "Shows Details page is displayed");
				} catch (Exception e) {
					try {
						nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
						logger.info("Player screen is displayed");
						extent.extentLogger("playerScreen", "Player screen is displayed");
					} catch (Exception e1) {
						nextPageTitle = "";
					}
				}
			}
			if (!nextPageTitle.equals("")) {
				logger.info("Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
				extent.extentLogger("playerScreen",
						"Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
				if (!userType.equals("SubscribedUser"))
					try {
						getWebDriver().findElement(PWASearchPage.objClosePremiumDialog).click();
					} catch (Exception e) {
					}
				try {
					getWebDriver().findElement(By.xpath("//a[text()='Home']")).click();
				} catch (Exception e) {
				}
			} else {
				logger.error("Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
				extent.extentLoggerFail("playerScreen",
						"Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
			}
		}
	}

	public void verifyRecoTrayAndPlayContentInDetailsPage(String userType, String page) throws Exception {
		extent.HeaderChildNode("Verification of talamoos trays in : Consumptions page");
		String content = "";
		if (page.equals("detailsPage"))
			content = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("consumptionsShow");
		else
			content = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
					.getParameter("musicToTriggerReco");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, content + "\n", "Search Edit box: " + content);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(content), 10, "Search Result");
		String contentPlayed = "";
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(content), "Search Result");
		if (page.equals("consumptionsPage")) {
			click(PWASearchPage.objCloseRegisterDialog, "Close in Pop Up");
			if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 30, "Player screen")) {
				contentPlayed = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				logger.info("Content played: " + contentPlayed);
				extent.extentLogger("contentPlayed", "Content played: " + contentPlayed);
			}
		}
		if (page.equals("detailsPage")) {
			if (waitForElementPresence(PWAShowsPage.objShowsTitle, 2, "Shows Details page")) {
				contentPlayed = getText(PWAShowsPage.objShowsTitle);
				logger.info("Show Details page displayed: " + contentPlayed);
				extent.extentLogger("showDetails", "Show Details page displayed: " + contentPlayed);
			}
		}
		String contentURL = getWebDriver().getCurrentUrl();
		String[] abc = contentURL.split("/");
		String contentID = abc[abc.length - 1];
		logger.info("Content ID fetched from URL: " + contentID);
		extent.extentLogger("contentPlayed", "Content ID fetched from URL: " + contentID);
		verifyRecoTraysFromDetailsPage(userType, contentID);
		try {
			getWebDriver().findElement(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn).click();
		} catch (Exception e) {
		}
	}

	public void playAContentForRecoWeb(String contentType, String searchKey, String userType) throws Exception {
		logger.info("Playing content to initiate Reco API: " + contentType);
		// handle mandatory pop up
		mandatoryRegistrationPopUp(userType);
		extent.extentLogger("contentplay", "Playing content to initiate Reco API: " + contentType);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, searchKey + "\n", "Search Edit box: " + searchKey);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(searchKey), 10, "Search Result");
		String contentPlayed = "";
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(searchKey), "Search Result");
		if (!userType.equals("SubscribedUser"))
			try {
				getWebDriver().findElement(PWASearchPage.objClosePremiumDialog).click();
			} catch (Exception e) {
			}
		if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 30, "Player screen")) {
			contentPlayed = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
			logger.info("Content played: " + contentPlayed);
			extent.extentLogger("contentPlayed", "Content played: " + contentPlayed);
			waitForPlayerAdToComplete("Video Player");
			logger.info("Playing content for some time to trigger Reco API");
			extent.extentLogger("contentPlayed", "Playing content for some time to trigger Reco API");
			waitTime(30000);
		}
	}

	public boolean swipeTillTrayAndClickFirstAsset(String userType, int noOfSwipes, String trayTitle, String message,
			String tab) throws Exception {
		int swipeCount = 0;
		String trayTitleInUI = "", temp = "";
		boolean found = false, titleDisplayed = false;
		List<WebElement> trays;
		ArrayList<String> titles = new ArrayList<String>();
		for (int i = 0; i <= noOfSwipes; i++) {
			trays = new ArrayList<WebElement>();
			trays = getWebDriver().findElements(PWALandingPages.objTrayTitle);
			for (int tr = 0; tr < trays.size(); tr++) {
				try {
					titles.add(trays.get(tr).getAttribute("innerText"));
				} catch (Exception e) {
				}
			}
			for (int traycount = 0; traycount < titles.size(); traycount++) {
				temp = titles.get(traycount);
				if (temp.toLowerCase().contains(trayTitle.toLowerCase())) {
					trayTitleInUI = temp;
					if (!titleDisplayed) {
						logger.info(trayTitleInUI + " is present in " + tab + " page");
						extent.extentLogger("trayfound", trayTitleInUI + " is present in " + tab + " page");
						titleDisplayed = true;
					}
					if (trayTitle.equals("Shows")) {
						try {
							// handle mandatory pop up
							mandatoryRegistrationPopUp(userType);
							getWebDriver().findElement(PWALandingPages.objFirstAssetInTrayIndex(trayTitleInUI)).click();
							found = true;
						} catch (Exception e) {
						}
					} else {
						// handle mandatory pop up
						mandatoryRegistrationPopUp(userType);
						try {
							if (userType.equals("Guest")) {
								Actions actions = new Actions(getWebDriver());
								WebElement elementLocator = getWebDriver()
										.findElement(PWALandingPages.objFirstAssetInTrayIndex(trayTitleInUI));
								actions.contextClick(elementLocator).perform();
								try {
									getWebDriver()
											.findElement(PWALandingPages.objFirstAssetInTrayPlayIcon(trayTitleInUI))
											.click();
								} catch (Exception e) {
								}
							} else {
								getWebDriver().findElement(PWALandingPages.objFirstAssetInTrayIndex(trayTitleInUI))
										.click();
								try {
									getWebDriver()
											.findElement(PWALandingPages.objFirstAssetInTrayPlayIcon(trayTitleInUI))
											.click();
								} catch (Exception e) {
								}
							}
							if (!userType.equals("SubscribedUser")) {
								try {
									getWebDriver().findElement(PWASearchPage.objClosePremiumDialog).click();
								} catch (Exception e) {
								}
							}
							found = true;
						} catch (Exception e1) {
						}
					}
					if (found == true) {
						logger.info("Located tray : " + trayTitleInUI);
						extent.extentLogger("trayfound", "Located tray : " + trayTitleInUI);
						// handle mandatory pop up
						mandatoryRegistrationPopUp(userType);
						logger.info("Clicked on first card");
						extent.extentLogger("clicked", "Clicked on first card");
						waitTime(2000);
						return true;
					} else {
						scrollDownByY(150);
					}
				}
			}
			scrollDownByY(350);
			waitTime(5000);
			swipeCount++;
			logger.info("Scrolled down");
			extent.extentLogger("scrolled", "Scrolled down");
			if (swipeCount == noOfSwipes) {
				logger.error("Failed to locate reco tray " + trayTitle);
				extent.extentLoggerFail("failedToLocate", "Failed to locate reco card " + trayTitle);
				logger.error("Failed to locate first card");
				extent.extentLoggerFail("failedToLocate", "Failed to locate first card");
			}
		}
		return false;
	}

	public void verifyRecoTraysFromDetailsPage(String userType, String firstAssetID) throws Exception {
		Response recoResp = ResponseInstance.getRecoTraysInDetailsPage(userType, firstAssetID);
		ArrayList<String> recoTraysInDetailsPage = getAllRecoTraysFromDetails(recoResp);
		String trayTitleUI = "", title = "";
		for (int tray = 0; tray < recoTraysInDetailsPage.size(); tray++) {
			String trayTitleAPI = recoTraysInDetailsPage.get(tray);
			trayTitleUI = swipeTillTray(5, trayTitleAPI, "\"" + trayTitleAPI + "\" tray");
			if (tray == 0 && !trayTitleUI.equals("")) {// Verify content play for one reco tray in content details
				try {
					title = getWebDriver().findElement(PWALandingPages.objFirstAssetInTrayTitle(trayTitleUI))
							.getAttribute("data-minutelytitle").toString();
				} catch (Exception e) {
				}
				// handle mandatory pop up
				mandatoryRegistrationPopUp(userType);
				waitForElementAndClick(PWALandingPages.objFirstAssetInTrayIndex(trayTitleAPI), 5,
						"First asset " + title);
				try {
					getWebDriver().findElement(PWASearchPage.objClosePremiumDialog).click();
				} catch (Exception e) {
				}
				String nextPageTitle = "";
				if (waitForElementPresence(PWAShowsPage.objShowsTitle, 2, "Shows Details page")) {
					nextPageTitle = getText(PWAShowsPage.objShowsTitle);
				} else if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 2, "Player screen")) {
					nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				}
				if (nextPageTitle.equals(title)) {
					logger.info("Navigated to the correct consumption/details page: \"" + title + "\"");
					extent.extentLogger("playerScreen",
							"Navigated to the correct consumption/details page: \"" + title + "\"");
				} else {
					logger.error("Failed to navigate to consumption/details page: \"" + title + "\"");
					extent.extentLoggerFail("playerScreen",
							"Failed to navigate to consumption/details page: \"" + title + "\"");
				}
				Back(1);
				try {
					getWebDriver().findElement(PWASearchPage.objClosePremiumDialog).click();
				} catch (Exception e) {
				}
			}
		}
	}

	public ArrayList<String> getAllRecoTraysFromDetails(Response response) {
		int numberOfTrays = 0;
		ArrayList<String> recoTrays = new ArrayList<String>();
		try {
			numberOfTrays = response.jsonPath().get("buckets.size()");
		} catch (Exception e) {
			logger.error("API error observed");
			extent.extentLoggerFail("apValue", "API error observed");
			return null;
		}
		for (int tray = 0; tray < numberOfTrays; tray++) {
			recoTrays.add(response.jsonPath().get("buckets[" + tray + "].title").toString());

		}
		logger.info("Reco trays in details page fetched from API: " + recoTrays);
		extent.extentLogger("apValue", "Reco trays in details page fetched from API: " + recoTrays);
		return recoTrays;
	}

	public String swipeTillTray(int noOfSwipes, String trayTitle, String message) throws Exception {
		boolean foundTray = false;
		int i = 0, j = 0;
		String trayTitleInUI = "";
		main: for (i = 0; i <= noOfSwipes; i++) {
			ArrayList<WebElement> trays = new ArrayList<WebElement>();
			trays = (ArrayList<WebElement>) getWebDriver().findElements(PWALandingPages.objTrayTitle);
			for (int traycount = 0; traycount < trays.size(); traycount++) {
				if (trays.get(traycount).getAttribute("innerText").equalsIgnoreCase(trayTitle)) {
					trayTitleInUI = trays.get(traycount).getText();
					foundTray = true;
					break main;
				}
			}
			scrollDownByY(200);
			waitTime(2000);
			logger.info("Scrolled down");
			extent.extentLogger("scrolled", "Scrolled down");
			if (i == noOfSwipes) {
				logger.error(message + " is not displayed");
				extent.extentLoggerFail("failedToLocate", message + " is not displayed");
			}
		}
		if (foundTray == true) {
			for (j = i; j <= noOfSwipes; j++) {
				if (waitForElementPresence(PWALandingPages.objTrayTitleInUI(trayTitleInUI), 1,
						trayTitleInUI + " tray")) {
					break;
				} else {
					scrollDownByY(150);
					waitTime(2000);
					logger.info("Scrolled down");
					extent.extentLogger("scrolled", "Scrolled down");
					if (j == noOfSwipes) {
						logger.error(message + " is not displayed");
						extent.extentLoggerFail("failedToLocate", message + " is not displayed");
					}
				}
			}
		}
		if (!trayTitleInUI.equals("")) {// Scroll till first card of the tray
			for (int k = j; k <= noOfSwipes; k++) {
				try {
					getWebDriver().findElement(PWALandingPages.objFirstAssetInTrayIndex(trayTitleInUI));
					logger.info("Located first asset under " + trayTitleInUI);
					extent.extentLogger("firstAsset", "Located first asset under " + trayTitleInUI);
					scrollDownByY(150);
					return trayTitleInUI;
				} catch (Exception e) {
					scrollDownByY(150);
					waitTime(2000);
					logger.info("Scrolled down");
					extent.extentLogger("scrolled", "Scrolled down");
				}
			}
		}
		return "";
	}

	public void watchlistCheck(String userType) throws Exception {
		watchlistMovies("Gooli", userType);
		watchlistEpisode("Anika tries to be careful", userType);
		watchlistVideo("Top 10 funny life", userType);
		watchlistMusic("Yennenu soda", userType);
	}

	public void watchlistMovies(String text, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Watchlist icon in Movies Consumption Page for " + userType + " User");
		click(PWAHomePage.objZeelogo1, "zee logo");
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, text, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Movie");
		waitTime(8000);
		if (verifyElementExist(PWASearchPage.objSubscribepopup, "Subscribepopup")) {
			click(PWASearchPage.objSubscribepopupCLoseButton, "close button");
		}
		if (verifyElementExist(PWAPlayerPage.watchListBtn, "Watchlist")) {
			logger.info("Watchlist button is displayed in movie consumption page");
			extent.extentLogger("Watchlist", "Watchlist button is displayed in movie consumption page");
			if (userType.equals("Guest")) {
				click(PWAPlayerPage.watchListBtn, "Watchlist");
				verifyElementExist(PWAPlayerPage.watchListLoginPopup,
						"When guest user tries to click on watchlist login popup");
				click(PWAPlayerPage.watchListLoginPopupCloseButton, "Close button in popup");
			}
			click(PWAHomePage.objZeelogo1, "Zee logo");
		}

	}

	public void watchlistEpisode(String text, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Watchlist icon in Episode Consumption Page for " + userType + " User");
		click(PWAHomePage.objZeelogo1, "zee logo");
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, text, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Episode");
		waitTime(8000);
		if (verifyElementExist(PWASearchPage.objMandatoryPopup, "SignIn popup")) {
			click(PWASearchPage.objMandatoryPopupCloseButton, "close button");
		}
		if (verifyElementExist(PWAPlayerPage.watchListBtn, "Watchlist")) {
			logger.info("Watchlist button is displayed in Episode consumption page");
			extent.extentLogger("Watchlist", "Watchlist button is displayed in Episode consumption page");
			if (userType.equals("Guest")) {
				click(PWAPlayerPage.watchListBtn, "Watchlist");
				verifyElementExist(PWAPlayerPage.watchListLoginPopup,
						"When guest user tries to click on watchlist login popup");
				click(PWAPlayerPage.watchListLoginPopupCloseButton, "Close button in popup");
			}
			click(PWAHomePage.objZeelogo1, "Zee logo");
		}

	}

	public void watchlistVideo(String text, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Watchlist icon in Video Consumption Page for " + userType + " User");
		click(PWAHomePage.objZeelogo1, "zee logo");
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, text, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Video");
		waitTime(8000);
		if (verifyElementExist(PWASearchPage.objSubscribepopup, "Subscribepopup")) {
			click(PWASearchPage.objSubscribepopupCLoseButton, "close button");
		}
		if (verifyElementExist(PWAPlayerPage.watchListBtn, "Watchlist")) {
			logger.info("Watchlist button is displayed in Video consumption page");
			extent.extentLogger("Watchlist", "Watchlist button is displayed in Video consumption page");
			if (userType.equals("Guest")) {
				click(PWAPlayerPage.watchListBtn, "Watchlist");
				verifyElementExist(PWAPlayerPage.watchListLoginPopup,
						"When guest user tries to click on watchlist login popup");
				click(PWAPlayerPage.watchListLoginPopupCloseButton, "Close button in popup");
			}
			click(PWAHomePage.objZeelogo1, "Zee logo");
		}

	}

	public void watchlistMusic(String text, String userType) throws Exception {
		extent.HeaderChildNode("Validation of Watchlist icon in Music Consumption Page for " + userType + " User");
		click(PWAHomePage.objZeelogo1, "zee logo");
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, text, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Music");
		waitTime(8000);
		if (verifyElementExist(PWASearchPage.objMandatoryPopup, "SignIn popup")) {
			click(PWASearchPage.objMandatoryPopupCloseButton, "close button");
		}
		if (verifyElementExist(PWAPlayerPage.watchListBtn, "Watchlist")) {
			logger.info("Watchlist button is displayed in Music consumption page");
			extent.extentLogger("Watchlist", "Watchlist button is displayed in Music consumption page");
			if (userType.equals("Guest")) {
				click(PWAPlayerPage.watchListBtn, "Watchlist");
				verifyElementExist(PWAPlayerPage.watchListLoginPopup,
						"When guest user tries to click on watchlist login popup");
				click(PWAPlayerPage.watchListLoginPopupCloseButton, "Close button in popup");
			}
			click(PWAHomePage.objZeelogo1, "Zee logo");
		}

	}

	public void mouseHoverWatchlist() {
		Actions action = new Actions(getWebDriver());
		WebElement player = getWebDriver().findElement(PWAAddToWatchListPage.objFirstContentInWatchlist);
		action.moveToElement(player).build().perform();
	}

	public void Watchlistlogin(String userType, String searchText) throws Exception {
		extent.HeaderChildNode("Logging in as " + userType + " user on clicking Add to Watchlist icon");
		click(PWAHomePage.objZeelogo1, "zee logo");
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, searchText, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Movie");
		waitTime(8000);
		if (verifyElementExist(PWASearchPage.objSubscribepopup, "Subscribepopup")) {
			click(PWASearchPage.objSubscribepopupCLoseButton, "close button");
		}
		if (verifyElementExist(PWAPlayerPage.watchListBtn, "Watchlist")) {
			logger.info("Watchlist button is displayed in movie consumption page");
			extent.extentLogger("Watchlist", "Watchlist button is displayed in movie consumption page");
			click(PWAPlayerPage.watchListBtn, "Watchlist");
			verifyElementExist(PWAPlayerPage.watchListLoginPopup,
					"When guest user tries to click on watchlist login popup");
			click(PWAPlayerPage.watchListLoginButton, "Login button");
			if (userType.equals("NonSubscribe")) {
				verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, NonSubUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, NonSubPassword, "Password field");

				waitTime(5000);
				click(PWALoginPage.objWebLoginButton, "Login Button");
				waitTime(5000);
				if (verifyElementExist(PWASearchPage.objSubscribepopup, "Subscribepopup")) {
					click(PWASearchPage.objSubscribepopupCLoseButton, "close button");
				}
			} else if (userType.equals("Subscribe")) {
				verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
				type(PWALoginPage.objEmailField, SubUsername, "Email Field");
				waitTime(3000);
				verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
				type(PWALoginPage.objPasswordField, SubPassword, "Password field");

				waitTime(5000);
				click(PWALoginPage.objWebLoginButton, "Login Button");
				waitTime(5000);
			}
			waitTime(5000);
			String contentName2 = getElementPropertyToString("innerText", PWAPlayerPage.objContentName, "Title");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
			waitTime(3000);
			click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
			if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
				click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
			}
			waitTime(3000);
			click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
			String ContentNameAddedToWatchlist = null;

			List<WebElement> contentsInWatchlist = findElements(
					By.xpath("(//h3[contains(@class,'cardTitle overflowEllipsis')]//a)"));
			ArrayList<String> ContentNameInWatchlist = new ArrayList<String>();
			for (int i = 0; i < contentsInWatchlist.size(); i++) {
				ContentNameInWatchlist.add(contentsInWatchlist.get(i).getText());
			}
			for (int i = 0; i < ContentNameInWatchlist.size(); i++) {

				if (contentName2.equals(ContentNameInWatchlist.get(i))) {
					ContentNameAddedToWatchlist = ContentNameInWatchlist.get(i);
				}
			}
			if (contentName2.equals(ContentNameAddedToWatchlist)) {
				extent.extentLogger("Verify Watchlist", "Added content is displayed in Watchlist screen");
				logger.info("Added content is displayed in Watchlist screen");
			} else {
				extent.extentLoggerFail("Verify Watchlist", "Added content is not displayed in Watchlist screen");
				logger.info("Added content is not displayed in Watchlist screen");
			}
		}
		click(PWAHomePage.objZeelogo1, "zee logo");
		click(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox, searchText, "Search field");
		waitTime(5000);
		click(PWASearchPage.objAssetTitleSearchNavigationTab, "Movie");
		waitTime(5000);
		if (verifyElementExist(PWASearchPage.objSubscribepopup, "Subscribepopup")) {
			click(PWASearchPage.objSubscribepopupCLoseButton, "close button");
		}
		String contentName2 = getElementPropertyToString("innerText", PWAPlayerPage.objContentName, "Title");
		click(PWAPlayerPage.watchListBtn, "Watchlist");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconWEB, "Profile icon");
		click(PWAAddToWatchListPage.objMyWatchList, "My Watchlist");
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}
		click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
		if (!verifyElementExist(PWAAddToWatchListPage.objTooltip(contentName2), "Added movie")) {
			logger.info(
					"Content is removed from My Watchlist when user taps on highlighted watchlist button in consumption page");
			extent.extentLogger("Watchlist",
					"Content is removed from My Watchlist when user taps on highlighted watchlist button in consumption page");
		}
		click(PWAHomePage.objZeelogo1, "Zee logo");
		logout();
	}

	public void registerandCheckCW() throws Exception {
		extent.HeaderChildNode("Registering as new user and checking Continue Watching Tray");
		click(PWALoginPage.objSignUpBtnWEB, "Sign up button");
		waitTime(3000);
		waitForElementDisplayed(PWALoginPage.objSignUpHeaderInSignUpPageWeb, 10);
		verifyElementExist(PWALoginPage.objSignUpHeaderInSignUpPageWeb, "SignUp Page");

		verifyElementExist(PWALoginPage.objEmailField, "Email/PhoneNo Field");

		verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
		type(PWALoginPage.objEmailField, RandomStringGenerator(5) + "@gmail.com", "Email Field");

		waitTime(3000);
		verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
		type(PWALoginPage.objPasswordField, NonSubPassword, "Password field");
		calenderFunctionality();
		click(PWASignupPage.objGenderMaleBtn, "Gender Option");
		waitTime(5000);
		click(PWASignupPage.objSignUpButtonHighlightedWeb, "Sign up Button");
		waitTime(3000);
		if (verifyElementExist(PWAHomePage.objContinueWatchingTray, "Continue Watching tray") == false) {
			extent.extentLogger("Verify Continue Watching tray",
					"Continue watching tray is not displayed for guest user");
			logger.info("Continue watching tray is not displayed for guest user");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify Continue Watching tray",
					"Continue watching tray is displaying for guest user");
			logger.info("Continue watching tray is displaying for guest user");
		}
		logout();
	}

	public void audioTrackSelection() throws Exception {
		HeaderChildNode("Audio Track Functionality");
		click(PWAHomePage.objZeelogo1, "Zee logo");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWAHomePage.objSearchField, audioTrackContent + "\n", "Search");
		waitTime(5000);
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(audioTrackContent), "Search Result");
		waitForPlayerAdToComplete("Video Player");
		pausePlayer();
		click(PWAPlayerPage.settingsBtn, "Settings icon");
		click(PWAPlayerPage.objPlayerAudioTrackIcon, "Audio Track icon");
		waitTime(5000);
		int size = getWebDriver().findElements(PWAPlayerPage.objPlayerAudioTracksAvailable).size();
		if (size == 0) {
			logger.info("Audio Tracks unavailable");
			extent.extentLogger("quality", "Audio Tracks unavailable");
		} else {
			for (int i = 1; i <= size; i++) {
				logger.info("Audio Tracks available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
				extent.extentLogger("audio", "Audio Tracks available : "
						+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
			}
			// Select audio tracks
			List<WebElement> unselected = findElements(PWAPlayerPage.objPlayerUnSelectedAudioTrack);
			ArrayList<String> tracks = new ArrayList<String>();
			for (int i = 0; i < unselected.size(); i++) {
				tracks.add(unselected.get(i).getText());
			}
			for (int i = 0; i < tracks.size(); i++) {
				String selectedTrackBefore = getText(PWAPlayerPage.objPlayerSelectedAudioTrack);
				logger.info("Audio Track before selection: " + selectedTrackBefore);
				extent.extentLogger("quality", "Audio Track before selection: " + selectedTrackBefore);
				String track = tracks.get(i);
				Thread.sleep(5000);
				click(PWAPlayerPage.objPlayerUnSelectedAudioTrack(track), "Audio Track " + track);
				Thread.sleep(5000);
				click(PWAPlayerPage.settingsBtn, "Settings icon");
				click(PWAPlayerPage.objPlayerAudioTrackIcon, "Audio Track icon");
				waitTime(5000);
				String selectedTrackAfter = getElementPropertyToString("innerText",
						PWAPlayerPage.objPlayerSelectedAudioTrack, "Selected Audio Track");
				logger.info("Audio Track after selection : " + selectedTrackAfter);
				extent.extentLogger("quality", "Quality option after selection: " + selectedTrackAfter);
				if (selectedTrackBefore.equals(selectedTrackAfter)) {
					logger.error("Audio Track selection unsuccessful");
					extent.extentLoggerFail("quality", "Audio Track selection unsuccessful");
				} else {
					logger.info("Audio Track selection successful");
					extent.extentLogger("quality", "Audio Track selection successful");
				}
			}
		}
	}

	@SuppressWarnings("unused")
	public void trayTitleAndContentValidationWithApiDataNews(String tab, String api) throws Exception {

		extent.HeaderChildNode(tab + " page validation with Api response");
		navigateToAnyScreenOnWeb(tab);
		Actions actions = new Actions(getWebDriver());
		WebElement menuOption = getWebDriver().findElement(PWAHamburgerMenuPage.objZeeLogo1);
		actions.moveToElement(menuOption).build().perform();

		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);

		Response resp = ResponseInstance.getResponseForPages(api, languageSmallText);
		List<String> apiTitleList = new LinkedList<String>();
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		List<String> contentList = resp.jsonPath().getList("buckets[1].items");
		System.out.println(contentList.size());
		partialScrollDown();
		List<WebElement> card = getWebDriver().findElements(By.xpath("((//div[@class='slick-list'])[2]//img)"));
		System.out.println(card.size());
		for (int i = 0; i < card.size(); i++) {
			// API DATA
			String title = resp.jsonPath().getString("buckets[1].items[" + i + "].title");
			String businessType = resp.jsonPath().getString("buckets[1].items[" + i + "].business_type");

			apiTitleList.add(title);
			WebElement contentCard = getWebDriver()
					.findElement(By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));
			actions.moveToElement(contentCard).build().perform();

			// to get metadata from content
			String contentMetadata = getAttributValue("title",
					By.xpath("((//div[@class='slick-list'])[2]//img)[" + (i + 1) + "]"));

			// String trayTitle = apiTitleList.get(i);
			logger.info("UI data " + title);
			logger.info("API data " + apiTitleList.get(i));
			extent.extentLogger("API data ", "API data " + apiTitleList.get(i));
			waitTime(2000);
			extent.extentLogger("UI data ", "UI data " + contentMetadata);
			if (verifyElementExist(PWAPremiumPage.objContentCardPlayBtn, "Play Icon")) {
				logger.info("Play icon is displayed");
				extent.extentLogger("Play", "Play icon is displayed");
			} else {
				logger.info("Play icon is not displayed");
				extent.extentLogger("Play", "Play icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardShareBtn, "Share Icon")) {
				logger.info("Share icon is displayed");
				extent.extentLogger("Share", "Share icon is displayed");
			} else {
				logger.info("Share icon is not displayed");
				extent.extentLogger("Share", "Share icon is not displayed");
			}
			waitTime(1000);
			if (verifyElementExist(PWAPremiumPage.objContentCardWatchlistBtn, "Watchlist Icon")) {
				logger.info("Watchlist icon is displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is displayed");
			} else {
				logger.info("Watchlist icon is not displayed");
				extent.extentLogger("Watchlist", "Watchlist icon is not displayed");
			}
		}
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objZeeLogo1, "Zee Logo");
	}

}