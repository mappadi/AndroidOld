package com.business.zee;

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
import com.propertyfilereader.PropertyFileReader;
import com.utility.Utilities;
import com.zee5.PWAPages.*;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Zee5PWASmokeWEBBusinessLogic extends Utilities {

	public Zee5PWASmokeWEBBusinessLogic(String Application) throws InterruptedException {
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

	Response resp;

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

	public void scroll1() {
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		WebElement Element = getWebDriver().findElement(By.xpath("//h2[.='Trending on ZEE5']"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info("Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}

	public void ZeeWEBPWALogin(String LoginMethod) throws Exception {
		extent.HeaderChildNode("Login");

		String UserType = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userType");
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
			waitTime(3000);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Login as NonSubscribed User");

			String Username = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("NonsubscribedUserName");
			String Password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("NonsubscribedPassword");

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

		case "Twitter":
			extent.HeaderChildNode("Login through Twitter");
			verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
			waitTime(7000);
			switchToWindow(2);
			waitTime(5000);
			if (verifyElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			} else if (verifyElementExist(PWALoginPage.objTwitterAuthorizeButton, "Authorize app")) {
				click(PWALoginPage.objTwitterAuthorizeButton, "Authorize app");
				regestrationfromSocialMedia();
			} else if (verifyElementExist(PWALoginPage.objTwitterEmaildField, "Twitter Email field")) {
				type(PWALoginPage.objTwitterEmaildField, "Zee5latest@gmail.com", "Email Field");
				hideKeyboard();
				verifyElementPresentAndClick(PWALoginPage.objTwitterPasswordField, "Twitter Password field");
				type(PWALoginPage.objTwitterPasswordField, "User@123", "Password field");
				click(PWALoginPage.objTwitterSignInButton, "Sign in button");
				waitTime(5000);
				verifyElementExist(PWAHomePage.objZeeLogo, "Home page");
				waitTime(5000);
				verifyElementPresentAndClick(PWALoginPage.objLoginBtnWEB, "Login");
				verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
				waitTime(10000);
				verifyElementPresent(PWAHamburgerMenuPage.objProfileIconWEB, "Profile");
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}
			break;
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

	public void WEBPWAValidatingSubscriptionAndTransaction(String userType) throws Exception {
		extent.HeaderChildNode("Validation of Subscription and Transaction");
		waitTime(5000);
		waitTime(5000);


		extent.HeaderChildNode("Validation of Get Premium CTA on Carousel");
		List<WebElement> ele = getWebDriver().findElements(By.xpath(
				"(//div[@class='slick-slide slick-active slick-center slick-current']//div[.='Get premium'][1])[2]"));
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

		

			extent.HeaderChildNode("Validating Subscribe Button");
			verifyElementExist(PWAHomePage.objSubscribeBtn, "Subscribe Button");			


		extent.HeaderChildNode("Validating BuySubscription and HaveAPrepaidCode under MyPlans");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
		Thread.sleep(3000);
		if (verifyElementExist(PWAHamburgerMenuPage.objPlans, "My Plans")) {
			verifyElementExist(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription");
			verifyElementExist(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code");
		}
		verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
		Thread.sleep(3000);
		if (verifyElementExist(PWALandingPages.objWebProfileIcon, "Profile Icon")) {
			//verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");
			click(PWALandingPages.objWebProfileIcon, "Profile Icon");
		}

		extent.HeaderChildNode("Validating MyAccount");
		boolean myAccountPresent = verifyElementExist(PWAHamburgerMenuPage.objWEBMyAccount, "My Account");
		if (myAccountPresent == true) {

			extent.HeaderChildNode("Validating MySubscription Button Under MyAccount Menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMySubscription, "MySubscription");
			Thread.sleep(3000);

			extent.HeaderChildNode("Validating MySubscription Screen");
			verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPage, "MySubscription Page");
			Thread.sleep(5000);

			extent.HeaderChildNode("Validating the Active Subscription Plans in MySubscription Screen");
			boolean NoSubscriptionActivePresent = verifyElementExist(PWAHamburgerMenuPage.objNoActiveSubscription,
					"No Active Subscription");
			if (NoSubscriptionActivePresent) {
				verifyElementExist(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item");
				verifyElementExist(PWAHamburgerMenuPage.objMySubscriptionPackName, "MySubscription Name");
				verifyElementExist(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus, "My Subscription Status");
			} else {
				if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item") == true) {
					if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPackName,
							"MySubscription Name")) {
						System.out.println(getText(PWAHamburgerMenuPage.objMySubscriptionPackName));
						logger.info(
								"Subscription PackName : " + getText(PWAHamburgerMenuPage.objMySubscriptionPackName));
						extent.extentLogger(
								"<b>" + "Subscription PackName : "
										+ getText(PWAHamburgerMenuPage.objMySubscriptionPackName),
								"Subscription pack name");
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus,
							"My Subscription Status")) {
						System.out.println(getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus));
						logger.info(
								"Subscription Status : " + getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus));
						extent.extentLogger(
								"<b>" + "Subscription Status : "
										+ getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus),
								"Subscription ActiveStatus");
					}
				}
			}

			getWebDriver().navigate().back();

			verifyElementPresentAndClick(PWALandingPages.objWebProfileIcon, "Profile Icon");

			Thread.sleep(5000);
			extent.HeaderChildNode("Validating MyAccount");
			verifyElementExist(PWAHamburgerMenuPage.objWEBMyAccount, "My Account");

			extent.HeaderChildNode("Validating MyTransactions Button Under MyAccount Menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyTransactions, "MyTransaction");
			Thread.sleep(3000);

			extent.HeaderChildNode("Validating MyTransaction Screen");
			verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPage, "MyTransaction Page");
			Thread.sleep(5000);

			extent.HeaderChildNode("Validating the Active and Expired Plans in MyTransaction Screen");
			boolean NoTransactionPresent = verifyElementExist(PWAHamburgerMenuPage.objNoTransaction, "No Transactions");
			if (NoTransactionPresent == true) {
				verifyElementExist(PWAHamburgerMenuPage.objMyTransactionDate, "MyTransaction Date");
				verifyElementExist(PWAHamburgerMenuPage.objMyTransactionPackName, "MyTransaction Name");
				verifyElementExist(PWAHamburgerMenuPage.objMyTransactionPackStatus, "MyTransaction Status");
			} else {
				if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionDate, "MyTransaction Date") == true) {
					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackName,
							"MyTransaction Name") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackName));
						logger.info(
								"MyTransactionPackName : " + getText(PWAHamburgerMenuPage.objMyTransactionPackName));
						extent.extentLogger(
								"<b>" + "MyTransactionPackName : "
										+ getText(PWAHamburgerMenuPage.objMyTransactionPackName),
								"objMyTransactionPackName");
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackStatus,
							"MyTransaction Status") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackStatus));
						logger.info("MyTransactionPackStatus : "
								+ getText(PWAHamburgerMenuPage.objMyTransactionPackStatus));
						extent.extentLogger(
								"<b>" + "MyTransactionPackStatus : "
										+ getText(PWAHamburgerMenuPage.objMyTransactionPackStatus),
								"objMyTransactionPackStatus");
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus,
							"MyTransaction AutoRenewal Status") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus));
						logger.info("MyTransactionAutoRenewalStatus : "
								+ getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus));
						extent.extentLogger(
								"<b>" + "MyTransactionAutoRenewalStatus : "
										+ getText(PWAHamburgerMenuPage.objMyTransactionAutoRenewalStatus),
								"objMyTransactionAutoRenewalStatus");
					}
				}
			}
		}
		validateDisplayLanguagePopup();
		getWebDriver().navigate().refresh();
//		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
		waitTime(5000);
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

	public void WEBPWAValidatingSubscribeLinks() throws Exception {
		extent.HeaderChildNode(" Validating Subscription Link");
		Thread.sleep(10000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, "Bhinna", "Search Field");
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		waitTime(10000);
		if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Close in Register Pop Up")) {
			click(PWASearchPage.objCloseRegisterDialog, "Close in Register Pop Up");
			logger.info("clicked on popup close button");
			extent.extentLogger("clicked on popup close button", "clicked on popup close button");
		} else {
			logger.info("Register Popup not displayed");
			extent.extentLogger("Register Popup not displayed", "Register Popup not displayed");
		}
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
		}
		Thread.sleep(5000);
		try {
			waitForElementDisplayed(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, 30);

			// Validating GET PREMIUM CTA BUTTON below Player
			extent.HeaderChildNode("Validating Get Premium CTA below the player");
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer,
					"GET PREMIUM CTA BELOW PLAYER ") == true) {
				click(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, "GET PREMIUM CTA BELOW PLAYER");

				Thread.sleep(3000);
				extent.HeaderChildNode("Validating Get Premium Popup");
				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
				}

			} else {
				extent.HeaderChildNode("Validating Get Premium Popup");
				if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Subscription Link");
				}

			}
		} catch (Exception e) {
			System.out.println("GetPremiumCTAbelowPlayer is not displayed");
		}
		waitTime(2000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		click(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
		waitTime(5000);
		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
		}

		extent.HeaderChildNode("Validating Player In-line Subscribe link");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, "Londonalli Lambodara", "Search Field");
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		waitTime(10000);

		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}

		if(verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Player In-line Subscribe link"))
		{
			System.out.println("Player In-Line Subscribe link is displayed");
			extent.extentLogger("Player In-Line Subscribe link is displayed", "Player In-Line Subscribe link is displayed");
		}
		else
		{
			System.out.println("Player In-Line Subscribe link is not displayed");
			extent.extentLogger("Player In-Line Subscribe link is not displayed", "Player In-Line Subscribe link is not displayed");
		}


		waitTime(2000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		click(PWALandingPages.obj_Pwa_Zee5Logo, "ZeeLogo");
		waitTime(5000);

		if (verifyElementExist(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup") == true) {
			click(PWAPlayerPage.objWouldYouLikeClosePopup, "WouldYouLikeClosePopup");
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
		verifyElementExist(PWAHamburgerMenuPage.objLoginBtn, "Login button");
		extent.HeaderChildNode("Validating Sign Up for free button on Homepage");
		verifyElementExist(PWAHamburgerMenuPage.objSignUpForFree, "Sign Up for free");
		extent.HeaderChildNode("Validating Download icon on Homepage");
		verifyElementExist(PWAHomePage.objDownloadIcon, "Download icon");
	}

	public void verifyLiveTvAndChannelGuideScreen() throws Exception {
		extent.HeaderChildNode("Validation of UI of Live Tv and Channel Guide");
		waitTime(15000);
		System.out.println(getText(PWAHomePage.objActiveTab));
		validateDisplayLanguagePopup();
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
		verifyElementPresentAndClick(PWALiveTVPage.objChannelDayStrip, "Channel/Day Strip");
		click(PWALiveTVPage.objUpcomingLiveProgramDate, "Upcoming Live Program Date");
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
		verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgram, "Upcoming Live Program");
		waitTime(10000);
		verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgramShareBtn, "Share button");
		waitTime(3000);
		verifyElementPresentAndClick(PWALiveTVPage.objFacebookShareBtn, "Share to Facebook");
		waitTime(3000);
		verifyAlert();
		switchToWindow(2);
		if(!verifyElementExist(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook")) 
		{
		verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField, "Facebook Email field");
		getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField).sendKeys("igszeetest@gmail.com");
		verifyElementPresentAndClick(PWALiveTVPage.objFacebookPasswordField, "Facebook Password field");
		getWebDriver().findElement(PWALiveTVPage.objFacebookPasswordField).sendKeys("igs@12345");
		verifyElementPresentAndClick(PWALiveTVPage.objFacebookLoginBtn, "Facebook Login button");
		waitTime(4000);
		}
		verifyElementPresentAndClick(PWALiveTVPage.objPostToFacebookBtn, "Post to Facebook");
		verifyAlert();
		waitTime(10000);
		switchToWindow(1);
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

	public void navigationToConsumptionScreenThroughTrendingSearches() throws Exception {

		extent.HeaderChildNode("Navigation to Consumption Screen through Trending Searches");

		waitTime(3000);

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
		}
		else
		{
		String showtitle=getText(PWASearchPage.objShowTitle(searchScreenTitle));
		waitTime(3000);

		if (searchScreenTitle.contains(showtitle)) {
		logger.info("user is navigated to respective consumption screen");
		extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
		} else {
		logger.info("user is not navigated to respective consumption screen");
		extent.extentLogger("Consumption Screen", "user is navigated to respective consumption screen");
		}
		}
		}
		}

	public String fetchLiveContent() throws Exception {
		extent.HeaderChildNode("Fetching the Live Content name from Live Tab");
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
			extent.HeaderChildNode("Guest user scenario");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			navigationToMyPlanFromHome();
			navigationToCTAInPlayerFromSearch();
			if (verifyElementExist(PWALoginPage.objLoginBtnWEB, "Login")) {
				extent.extentLogger("Not Logged in", "User is not logged in");
				logger.info("User is not logged in");
				noLogoutOption();
				forgotPassword();
				waitTime(5000);
				getWebDriver().get("https://newpwa.zee5.com/");
				waitTime(8000);
			}
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Non-Subscribed user scenario");
			waitTime(3000);
			myaccountOptionsVerification();
			NavigateToMyProfilePage();
			verificationsInMyProfilePage();
			editProfileFuncionality();
			subscribeCTAFuncionality();
			changePasswordFuncionality();
			break;

		case "SubscribedUser":

			extent.HeaderChildNode("Subscribed user scenario");
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
		extent.HeaderChildNode("My Watchlist");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Watchlist"), "My watchlist");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Watchlist"), "My Watchlist page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyReminders .
	 */
	public void NavigationsToMyReminders() throws Exception {
		extent.HeaderChildNode("My Reminders");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Reminders"), "My Reminders");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Reminders"), "My Reminders page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MySubscription.
	 */
	public void NavigationsToMySubsccription() throws Exception {
		extent.HeaderChildNode("My subscriptions");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Subscription"), "My Subscription");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Subscription"), "My Subscription page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyTransaction.
	 */
	public void NavigationsToMyTransactions() throws Exception {
		extent.HeaderChildNode("My Transactions");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyProfileOptionsWEB("My Transactions"), "My Transactions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Transactions"), "My Transactions page");
		click(PWAHamburgerMenuPage.objProfileIconWEB, "profile icon");
	}

	/**
	 * Function for Navigation to MyProfilePage.
	 */
	public void NavigateToMyProfilePage() throws Exception {
		extent.HeaderChildNode("NavigateToMyProfilePage");
		System.out.println("NavigateToMyProfilePage");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIconInProfilePage, "profile icon");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		click(PWAHamburgerMenuPage.objProfileTextWEB, "profile");
	}

	/**
	 * Function To Verifing the options present in MyProfilePage.
	 */
	public void verificationsInMyProfilePage() throws Exception {
		extent.HeaderChildNode("verificationsInMyProfilePage");
		System.out.println("verificationsInMyProfilePage");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageNameTxtWEB, "User name");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageUserIdTxt, "User id");
		verifyElementPresent(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
	}

	/**
	 * Function To check the Funcionality of EditProfile option .
	 */

	public void editProfileFuncionality() throws Exception {
		extent.HeaderChildNode("EditProfileFuncionality");
		System.out.println("editProfileFuncionality");
		waitTime(6000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objEditProfileTextWEB, "edit profile page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
		clearField(PWAHamburgerMenuPage.objEditProfileFirstName, "email field");
		type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Editprofile first name");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "save changes");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "go back button");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
	}

	/**
	 * Function To check the Funcionality of SubscribeCTA option.
	 */
	public void subscribeCTAFuncionality() throws Exception {
		extent.HeaderChildNode("subscribeCTAFuncionality");
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
	 * Function To check the Funcionality of ChangePassword option.
	 */
	public void changePasswordFuncionality() throws Exception {
		extent.HeaderChildNode("changePasswordFuncionality");
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

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToMyPlanFromHome() throws Exception {
		extent.HeaderChildNode("Validating user navigated to signin screen from my plans screen");
		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscription button");
		waitTime(3000);
		if (verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "subscription page")) {
			logger.info("User is navigated to Subscription page");
			extent.extentLogger("Subscription page", "User is navigated to Subscription page");
			navigationToSignInFromMyplans();
		}

		else {
			logger.info("User is not navigated to Subscription page");
			extent.extentLogger("Subscription page", "User is not navigated to Subscription page");

		}
	}

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToSignInFromMyplans() throws Exception {
		if (verifyElementExist(PWASubscriptionPages.objadhocPopupArea, "Adoric popup")) {
			click(PWASubscriptionPages.objadhocPopupSignUpBtn, "Adoric popup SignUP Button");
			waitTime(3000);
			verifyElementPresent(PWASubscriptionPages.objadhocPopupRegestrationScreen, "Sign up page");
			BackButton(1);
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
			}
		} else {
			waitTime(1000);
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(2000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
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
	public void navigationToCTAInPlayerFromSearch() throws Exception {
		extent.HeaderChildNode("Validating user navigated to signin from CTA in player");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, "Bhinna", "Search Field");
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		waitTime(2000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		waitTime(3000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumCTAInPlater, "CTA in player");
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
		extent.HeaderChildNode("Validating Subscribe popup post tapping Cta in player");
		verifyElementPresent(PWASubscriptionPages.objSubscribepopup, "Subscribe popup");
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupPlan, "Subscribe popup plan");
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopipProceed,
				"Proceed button in Subscribe popup");
		click(PWASubscriptionPages.objGetPremiumPopipProceed,"Proceed button in Subscribe popup");
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
		//click(PWALoginPage.objForgotPasswordTxt, "forgot password Text");
		click(PWALoginPage.objForgotPasswordLinkButtonWEB, "Reset password button");
		waitTime(60000);
		String url = GmailInbox.readEmail("ZEE5 account password reset request");
		if (!url.isEmpty()) {
			getWebDriver().get(url);
			waitTime(5000);
			verifyElementPresent(PWALoginPage.objForgotNextPageTextWEB, "Reset password page");
			type(PWALoginPage.objForgotNextPagePwsswordFielfd, "User@123", "Password");
			waitTime(5000);
			type(PWALoginPage.objForgotNextPageConfirmPasswordField, "User@123", "Confirm password");
			click(PWALoginPage.objForgotNextPageResetPaswwordButtonWEB, "Reset password");
			waitTime(10000);
			if(verifyElementExist(PWALoginPage.objLoginPageLoginBtn, "LoginButton")) {
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
				extentLoggerFail("Logged in", "User is not logged in");
			}
			}else {
				logger.info("Reset password link expired");
				extent.extentLoggerFail("Reset link", "Reset password link expired");
			}
		} else {
			logger.info("User is not received the mail or the mail content is read");
			extent.extentLoggerFail("Logged in", "User is not received the mail or the mail content is read");
			logger.info("User is not logged in");
			extent.extentLoggerFail("Logged in", "User is not logged in");
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

//		zeePWASubscriptionFlowFromHomePageGetPremiumCTAOnCarousel(userType, platform);
//		zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent(userType, platform);
//		zeePWASubscriptionFlowFromPlayerInlineSubscribelink(userType, platform);
//		zeePWASubscriptionFlowFromSubscriptionGetPremiumCTABelowPlayer(userType, platform);
//		zeePWASubscriptionFlowFromBuySubscriptionOptionUnderMyPlansInHamburgerMenu(userType, platform);
//		zeePWASubscriptionFlowFromHaveAPrepaidCodeOptionUnderMyPlansInHamburgerMenu(userType, platform);

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
			HeaderChildNode("Navigate to Subscription Flow From 'Buy Subscription' option under My plans in hamburger menu");
			waitTime(10000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
			waitTime(2000);
			validateDisplayLanguagePopup();
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objWebBuySubscriptionOption,"Buy Subscribe Option in Hamburger Menu");
			zeeSubscriptionPageValidationAndNavigateToHomePage();

			// Scenario no. 97
			HeaderChildNode("Navigate to Subscription Flow From 'Have a Prepaid code' option under My plans in hamburger menu");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Button");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have A Prepaid Code? Option in Hamburger Menu");
			waitTime(2000);
			zeeSubscriptionPageValidationAndNavigateToHomePage();
		}

		// Scenario no. 91,92,94
		HeaderChildNode("Navigate to Subscription Flow From Adoric Popup/Get Premium popup On Playing Premium Content");
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
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
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
		waitTime(2000);
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
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
//		verifyElementPresentAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, "Get Premium Link below the Player");
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
//		HeaderChildNode("Search For Content "+contentName+" And Click On First Result");
		
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
//		click(PWASearchPage.objSearchEditBox,"Search bar");
		type(PWASearchPage.objSearchEditBox, contentName, "Search bar");
		waitTime(3000);
		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
		waitTime(5000);
//		type(PWASearchPage.objSearchEditBox, " ", "Search bar");
//		driver.findElement(PWASearchPage.objSearchEditBox).sendKeys(contentName+"\n");
//		hideKeyboard();
		waitForElementDisplayed(PWASearchPage.objFirstSearchedAssetTitle, 20);
		waitTime(5000);
		String FirstSearchedAssetTitle = findElement(PWASearchPage.objFirstSearchedAssetTitle).getText();
		System.out.println("First Asset Title of the Search Result is: " + FirstSearchedAssetTitle);
		click(PWASearchPage.objFirstSearchedAssetTitle, "First Searched Asset Title");
	}

	/**
	 * PWA Subscription Page Validation
	 */
	public void zeeSubscriptionPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Subscription Page Validation and Navigate to Home Page");

		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * PWA Account Info Page Validation
	 */
	public void zeeAccountInfoPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Account Info Page Validation and Navigate to Home Page");

		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * PWA Payment Page Validation
	 */
	public void zeePaymentPageValidationAndNavigateToHomePage() throws Exception {
		HeaderChildNode("PWA Payment Page Validation and Navigate to Home Page");

		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * Verify Get Premium Popup
	 */
	public void zeeVerifyGetPremiumPopup() throws Exception {
		HeaderChildNode("Verify Get Premium Popup");

		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopup99Plan, "99 Plan in Popup");
		waitTime(5000);
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
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
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
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
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
		zeeSearchForContentAndClickOnFirstResult("Premier Padmini");
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
			type(PWASubscriptionPages.objHaveACode, "ZEE5PTM20" + "\n", "'Have A Code?' field");
		} else if (platform.equalsIgnoreCase("Web")) {
			type(PWASubscriptionPages.objHaveACode, "ZEE5PTM20", "'Have A Code?' field");
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
	public void zeePWAPaymentPageValidation() throws Exception {
		HeaderChildNode("Validate that user is navigated to Payment options screen post successful sign in/sign up");

		// Scenario no. 103
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(3000);
		zeePWASelectedPackDisplayValidation();
		verifyElementPresent(PWASubscriptionPages.objAccountInfoText, "Account Info Text in Payments Section");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoDetails, "Account Info Details in Payments Section");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objCreditCardRadioBtn, "Credit Card Radio Button");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objDebitCardRadioBtn, "Debit Card Radio Button");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objPayTMRadioBtn, "PayTm Radio Button");
		waitTime(3000);
		ScrollToElement(PWASubscriptionPages.objContinueBtnDisabled, "Continue Button Disabled");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objRecurrenceMessage, "Recurrence Message");
		waitTime(3000);
		click(PWASubscriptionPages.objPayTMRadioBtn, "PayTm Radio Button");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtnEnabled, "Continue Button Enabled");
//		verifyElementPresent(PWASubscriptionPages.objContinueBtnEnabled, "Continue Button Enabled");
		waitTime(5000);
		// PayTM Page
		verifyElementPresent(PWASubscriptionPages.objPaytmWalletOption, "PayTM Wallet option");

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

		if (verifyElementExist(PWAHomePage.objDisplayLanguagePopupTitle, "Display Language Popup")) {

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
		//WatchCredit(userType);
		upnext(userType);
	}

	public void AddToWatchListLoggedInUser() throws Exception {
		extent.HeaderChildNode("Add to Watch List");
		// Click on Watchlist
		click(PWAPlayerPage.watchListBtn, "WatchList icon");
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
		click(PWAHomePage.objTabName("Movies"), "Movies tab");
		// Click on Free content
		click(PWAMoviesPage.objContentCard(2), "Content card");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		type(PWASearchPage.objSearchEditBox,"Doddmane hudga","Search edit");
		click(PWASearchPage.objspecificSearch,"Searched content");
		Thread.sleep(6000);
		// Verify the Pop up behavior
		//verifyElementNotPresent(PWAPlayerPage.objSpinner, 60);
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

		waitTime(3000);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {
			verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupCloseButton, "POP-UP CLOSE BUTTON");
		}
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
		//click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.pauseBtn, "Pause button");
		verifyElementPresent(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.playBtn, "Play icon");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconds icon");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.progressBar, "Progress bar");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.audioBtn, "Audio icon");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total duration time");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.settingsBtn, "Settings icon");
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
		verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize window icon");
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total time");
		Thread.sleep(10000);
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
		// click(PWAPlayerPage.pauseBtn,"Pause icon");
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
		//click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
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
		click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
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
		click(PWAPlayerPage.objPlaybackVideoOverlay, "player");
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
		click(PWAPlayerPage.objPlaybackVideoOverlay, "Player");
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
		if(verifyElementExist(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
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
		type(PWASearchPage.objSearchEditBox, "Premier Padmini" + "\n", "Search Edit box");
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
		type(PWASearchPage.objSearchEditBox,"Jhende admits defeat - Jothe Jotheyali" ,"Search edit");
		click(PWASearchPage.objspecificSearch,"Searched content");
		if(verifyElementExist(PWAPlayerPage.objWhyRegisterPopUp, "Register pop up")== true) {
			click(PWAPlayerPage.oblClosePopup, "Close button");
		}
		if(userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			waitForPlayerAdToComplete("Video");
		}
		Thread.sleep(5000);
		click(PWAPlayerPage.objPlaybackVideoOverlay,"Player");
		Thread.sleep(5000);
		WebElement scrubber = getWebDriver().findElement(By.xpath("//a[@class='playkit-scrubber']"));
//		Actions move = new Actions(getWebDriver());
//		Action action = (Action) move.dragAndDropBy(scrubber, 620, 0).build();
//		action.perform();
		Actions action = new Actions(getWebDriver());
		action.clickAndHold(scrubber);
        action.moveByOffset(40, 0).build().perform();

		//click(PWAPlayerPage.objPlaybackVideoOverlay,"Player");
		verifyElementPresent(PWAPlayerPage.objUpnextCard, "Up Next Rail on player");
		action.clickAndHold(scrubber).release();
//		action.moveToElement(getWebDriver().findElement(PWAHomePage.objMoreMenuIcon)).release();
		
		//Verify the Upnext content is auto playing 
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
			System.out.println("title1 >>> " + firstCarouselTitle);
			waitTime(7000);
			secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
			System.out.println("title2 >>> " + secondCarouselTitle);
			waitTime(7000);
			thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
			System.out.println("title3 >>> " + thirdCarouselTitle);

		} catch (Exception e) {
			e.getMessage();
		}

//			              w.until(ExpectedConditions.invisibilityOfElementLocated(PWAHomePage.objWEBContTitleTextCarousel(secondCarouselTitle)));
//			              //verifyElementNotPresent(PWAHomePage.objWEBContTitleTextCarousel(secondCarouselTitle), 60);
//			              thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//			              System.out.println("title3 >>> " +thirdCarouselTitle);
////			                break;

		// }
		if (firstCarouselTitle.equals(secondCarouselTitle) == false
				&& firstCarouselTitle.equals(thirdCarouselTitle) == false) {
			softAssert.assertFalse(firstCarouselTitle.equals(secondCarouselTitle));
			logger.info("Content is auto rotated ");
			extent.extentLogger("Autorotating", "First content title :" + firstCarouselTitle + " second content title :"
					+ secondCarouselTitle + " and trird content title :" + thirdCarouselTitle);
			extent.extentLogger("Autorotating", "Content is auto rotated");
		} else {
			softAssert.assertFalse(firstCarouselTitle.equals(secondCarouselTitle));
			logger.info("Content is not auto rotated");
			extent.extentLogger("Autorotating", "Content is not auto rotated");
			softAssert.assertAll();
		}
	}

	/**
	 * Function to select any tab
	 * 
	 */
	public void navigateToAnyScreenOnWeb(String screen) throws Exception {
		try {
			if (verifyElementExist(PWAHomePage.objHomeBarText(screen), screen+" Tab")) {
				click(PWAHomePage.objHomeBarText(screen), screen+" Tab");
			} else {
				JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
				getWebDriver().findElement(PWAHomePage.objMoreMenuIcon);
				waitTime(2000);				
				try {
					WebElement tab = getWebDriver().findElement(PWAHomePage.objMoreMenuTabs(screen));
					executor.executeScript("arguments[0].click();", tab);
				} catch (Exception e) {
				}
			} 
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}

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

		boolean isNextPageDisplayed = false;

		if (screen.equalsIgnoreCase("Premium")) {
			try {
				getWebDriver().findElement(PWAPremiumPage.objClosePremiumPopup).click();
			} catch (Exception e) {
				e.getMessage();
			}
		}
//        navigateToAnyScreenOnWeb(screen);
		JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
		try {
			WebElement premiumText = getWebDriver().findElement(PWAHomePage.objWEBPlayBtn);
			executor.executeScript("arguments[0].click();", premiumText);
		} catch (Exception e) {
			Thread.sleep(2000);
			getWebDriver().findElement(PWAHomePage.objWEBPlayBtn).click();
		}

		waitForElementDisplayed(PWAPlayerPage.objPlayerControlScreen, 10);

		if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
			isNextPageDisplayed = true;
		} else {
			isNextPageDisplayed = false;
		}
		if (isNextPageDisplayed) {
			softAssert.assertTrue(isNextPageDisplayed, "Next page is displayed");
			logger.info("Next page is displayed on banner for " + screen);
			extent.extentLogger("Play btn validation", "Next page is displayed for " + screen);
		} else {
			softAssert.assertTrue(isNextPageDisplayed, "Next page is not displayed");
			logger.info("Next page is not displayed for " + screen);
			extent.extentLogger("Play btn validation", "Next page is not displayed for " + screen);
			softAssert.assertAll();
		}

		Thread.sleep(5000);
		waitForElementDisplayed(PWAHamburgerMenuPage.objGetPremiumPopup, 30);
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}

		validateDisplayLanguagePopup();
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP") == true) {

			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

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
		extent.HeaderChildNode("Verifying premium icon functionality On : " + screen + " for " + userType);
		boolean isNextPageDisplayed = false;
		navigateToAnyScreenOnWeb(screen);

		if (userType.equalsIgnoreCase("SubscribedUser")) {
			waitTime(4000);
			List<WebElement> getPremiumTextList = getWebDriver().findElements(PWAHomePage.objWEBGetPremium);
			System.out.println("Size : " + getPremiumTextList.size());
			if (getPremiumTextList.size() == 0) {
				softAssert.assertTrue(true, "Next page is not displayed");
				logger.info("Get premium text is not displayed for subscribed users");
				extent.extentLogger("Premium text for subscribed user",
						"Get premium text is not displayed for subscribed users" + screen);
			}
		} else {
			JavascriptExecutor executor = (JavascriptExecutor) getWebDriver();
			try {
				WebElement premiumText = getWebDriver().findElement(PWAHomePage.objWEBGetPremium);
				executor.executeScript("arguments[0].click();", premiumText);
			} catch (Exception e) {
				Thread.sleep(2000);
				getWebDriver().findElement(PWAHomePage.objWEBGetPremium).click();
			}
		}
		if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
			if (verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page")) {
				isNextPageDisplayed = true;
				getWebDriver().navigate().back();
			} else {
				isNextPageDisplayed = false;
			}
		}

		if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
			if (isNextPageDisplayed) {
				softAssert.assertTrue(isNextPageDisplayed, "Next page is displayed");
				logger.info("Next page is displayed on banner for " + screen);
				extent.extentLogger("Premium btn validation", "Next page is displayed for " + screen);
			} else {
				softAssert.assertTrue(isNextPageDisplayed, "Next page is not displayed");
				logger.info("Next page is not displayed for " + screen);
				extent.extentLogger("Premium btn validation", "Next page is not displayed for " + screen);
				softAssert.assertAll();
			}
		}
	}

//    /**
//     * Function to verify Play icon functionality for users
//     * 
//     * @throws Exception
//     */
//    public void verifyPremiumIconFunctionality(String screen, String userType) throws Exception {
//        extent.HeaderChildNode("Verifying premium icon functionality On : " + screen + " for " + userType);
//        boolean isNextPageDisplayed = false;
//        navigateToAnyScreenOnWeb(screen);
//        if (userType.equalsIgnoreCase("subscribed")) {
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
//        
//        if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
//            if (verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page")) {
//                isNextPageDisplayed = true;
//            } else {
//                isNextPageDisplayed = false;
//            }
//        }
//        getWebDriver().navigate().back();
//        if (userType.equalsIgnoreCase("Nonsubscribed") || userType.equalsIgnoreCase("Guest")) {
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
//            
//        }
//    }

//    //manas scrpit modified by sushma
//    public void verifyLeftRightFunctionality(String screen) throws Exception {
//        extent.HeaderChildNode("Verifying left and right functionality");
//        
//        String firstCarouselTitle = "", secondCarouselTitle="";
//        WebDriverWait w = new WebDriverWait(getWebDriver(), 40);
//        navigateToAnyScreenOnWeb(screen);
//        
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        firstCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title1 >>> " + firstCarouselTitle);
//        waitTime(3000);
//        
//        click(PWANewsPage.objRight, "rightButton");
//    
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title2 >>> " +secondCarouselTitle);
//        waitTime(3000);
//        
//        click(PWANewsPage.objLeft, "leftButton");
//    
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        String firstCarouselTitle2 = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title3 >>> " + firstCarouselTitle2);
//        waitTime(4000);
//        
//        click(PWANewsPage.objRight, "rightButton");
//        
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        String secondCarouselTitle2 = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title4 >>> " + secondCarouselTitle2);
//        waitTime(4000);
//        
//        if (firstCarouselTitle.equals(firstCarouselTitle2) == true) {
//            logger.info("Content can be swiped left ");
//            extent.extentLogger("Swipe left and right", "Content can be swiped left ");
//        } else {
//            logger.info("Content can not be swiped left ");
//            extent.extentLogger("Swipe left and right", "Content can not be swiped left  ");
//        }
//        
//        if (secondCarouselTitle.equals(secondCarouselTitle2) == true) {
//            logger.info("Content can be swiped right");
//            extent.extentLogger("Swipe left and right", "Content can be swiped right");
//        } else {
//            logger.info("Content can not be swiped right");
//            extent.extentLogger("Swipe left and right", "Content can not be swiped right");
//        }
//    }

	public void verifyLeftRightFunctionality(String screen) throws Exception {
		extent.HeaderChildNode("Verifying left and right functionality");

		String firstCarouselTitle = "", secondCarouselTitle = "";
		WebDriverWait w = new WebDriverWait(getWebDriver(), 40);
		navigateToAnyScreenOnWeb(screen);

		w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
		firstCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		System.out.println("title1 >>> " + firstCarouselTitle);
		waitTime(3000);

		click(PWANewsPage.objRight, "rightButton");
		// w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
		secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		System.out.println("title2 >>> " + secondCarouselTitle);

		waitTime(9000);
		w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
		String thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		thirdCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		System.out.println("title3 >>> " + thirdCarouselTitle);
		waitTime(4000);

		click(PWANewsPage.objLeft, "leftButton");
		w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
		String fourthCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
		System.out.println("title4 >>> " + fourthCarouselTitle);

		if (firstCarouselTitle.equals(secondCarouselTitle) == false) {
			logger.info("Content can be swiped right ");
			extent.extentLogger("Swipe left and right", "Content can be swiped right ");
		} else {
			logger.info("Content can not be swiped right ");
			extent.extentLogger("Swipe left and right", "Content can not be swiped right  ");
			softAssert.assertAll();
		}

		if (thirdCarouselTitle.equals(fourthCarouselTitle) == false) {
			logger.info("Content can be swiped left");
			extent.extentLogger("Swipe left and right", "Content can be swiped left");
		} else {
			logger.info("Content can not be swiped left");
			extent.extentLogger("Swipe left and right", "Content can not be swiped left");
			softAssert.assertAll();
		}
	}

	// manas
	// /**
//     * Function to left and right(<>) functionality on carousel
//     * 
//     * @throws Exception
//     */
//    public void verifyLeftRightFunctionality(String screen) throws Exception {
//        extent.HeaderChildNode("Verifying left and right functionality");
//        
//        String firstCarouselTitle = "", secondCarouselTitle="";
//        WebDriverWait w = new WebDriverWait(getWebDriver(), 40);
//        navigateToAnyScreenOnWeb(screen);
//        
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        firstCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title1 >>> " + firstCarouselTitle);
//        waitTime(3000);
//        
//        click(PWANewsPage.objRight, "rightButton");
//    
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        secondCarouselTitle = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title2 >>> " +secondCarouselTitle);
//        waitTime(3000);
//        
//        click(PWANewsPage.objLeft, "leftButton");
//    
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        String firstCarouselTitle2 = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title3 >>> " + firstCarouselTitle2);
//        waitTime(4000);
//        
//        click(PWANewsPage.objRight, "rightButton");
//        
//        w.until(ExpectedConditions.visibilityOfElementLocated(PWAHomePage.objWEBCarouselTitle));
//        String secondCarouselTitle2 = getWebDriver().findElement(PWAHomePage.objWEBCarouselTitle).getText();
//        System.out.println("title4 >>> " + secondCarouselTitle2);
//        waitTime(4000);
//        
//        if (firstCarouselTitle.equals(firstCarouselTitle2) == true) {
//            logger.info("Content can be swiped left ");
//            extent.extentLogger("Swipe left and right", "Content can be swiped left ");
//        } else {
//            logger.info("Content can not be swiped left ");
//            extent.extentLogger("Swipe left and right", "Content can not be swiped left  ");
//        }
//        
//        if (secondCarouselTitle.equals(secondCarouselTitle2) == true) {
//            logger.info("Content can be swiped right");
//            extent.extentLogger("Swipe left and right", "Content can be swiped right");
//        } else {
//            logger.info("Content can not be swiped right");
//            extent.extentLogger("Swipe left and right", "Content can not be swiped right");
//        }
//    }

	// ----------------------------------------------------------------------------------------------------

	// TEJAS

	// ---------------------------------------------------------------------------------------------------

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
		
		if(verifyElementPresent(PWALandingPages.obj_WEBPwa_HamburgerMenu1, "Hamburger Menu")) {
			logger.info("HamburgerMenu icon is displayed");
			extent.extentLogger("HamburgerMenu", "HamburgerMenu icon is displayed");
		}else {
			logger.info("HamburgerMenu icon is not displayed");
			extent.extentLogger("HamburgerMenu", "HamburgerMenu icon is not displayed");
		}
		//Zee5Logo
		if(verifyElementPresent(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo")) {
			logger.info("Zee5 Logo is displayed");
			extent.extentLogger("Zee5 Logo", "Zee5 Logo is displayed");
		}else {
			logger.info("Zee5 Logo is not displayed");
			extent.extentLogger("Zee5 Logo", "Zee5 Logo is not displayed");
		}
		
		//Search button
		if(verifyElementPresent(PWALandingPages.obj_Pwa_SearchBtn, "Search")) {
			logger.info("Search button is displayed");
			extent.extentLogger("Search", "Search button is displayed");
		}else {
			logger.info("Search button is not displayed");
			extent.extentLogger("Search", "Search button is not displayed");
		}
		//Subscription_button
		waitTime(2000);
		if(verifyElementPresent(PWALandingPages.obj_Pwa_Subcription_teaser_btn, "Subcription button")) {
			logger.info("Subscription button is displayed");
			extent.extentLogger("Subscription", "Subscription button is displayed");
		}else {
			logger.info("Subscription button is not displayed");
			extent.extentLogger("Subscription", "Subscription button is not displayed");
		}
		
		partialScroll();
		
		if(verifyElementExist(PWAPremiumPage.objViewAllBtn, "View All Button"))
		{
			click(PWAPremiumPage.objViewAllBtn, "View All Button");
			
			if(verifyElementExist(PWAPremiumPage.objViewAllPage, "View All Page")) {
				logger.info("Navigated to View All Page");
				extent.extentLogger("View All", "Navigated to View All Page");
			}else {
				logger.info("Not navigated to View All Page");
				extent.extentLogger("View All", "Not navigated to View All Page");
			}
		}
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

	public void WebHomepageTrayTitleAndContentValidationWithApiData(Response ApiData) throws Exception {

		extent.HeaderChildNode("Home page validation with Api response");
		Response resp = ApiData;
		String Tray_Title = resp.jsonPath().getString("buckets[1].title");
		System.out.println("The Title of the Tray is " + Tray_Title + "");
		webscrollToXpath(WebText_To_Xpath(Tray_Title));
		waitTime(3000);
		if (verifyElementExist(WebText_To_Xpath(Tray_Title), Tray_Title)) {
			System.out.println("Tray title Found");
//			Verify_SeeAll_Functionality(Tray_Title);
//			Navigate_to_HomeScreen_using_Zee5Logo();
		} else {
			System.out.println("Tray title Not found");
		}

		String Content_Title = resp.jsonPath().getString("buckets[1].items[0].title");
		System.out.println("Content Title is " + Content_Title + "");
		scrollDownWEB();
		webscrollToXpath(TitleTextToXpath(Content_Title));
		waitTime(3000);
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

	public void Loggedin_User(String Rail_Name, String Content_Name) throws Exception {
		landingpagePropertiesValidation();
		Homepage_Title_with_Api(Rail_Name);
		Homepage_Content_selection_playback_with_Api(Rail_Name, Content_Name);
		Verify_Get_Premium_Trailer();
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

	public By WebText_To_Xpath(String text) throws Exception {

		return By.xpath("//div[.='" + text + "'] | //*[contains(@text,'" + text + "')]");

	}

	public void Verify_Get_Premium_Trailer() throws Exception {

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
	
	public void verifyConsumptionsScreenTappingOnCard(String userType, String contentType, String contentTitle,String devicePin) throws Exception {
		extent.HeaderChildNode("Verify Consumption Page for Content type: " + contentType);
		System.out.println("Verify Consumption Page for Content type: " + contentType);
		String consumptionPageTitle = "";
		if (contentType.equals("Live TV")) 
		{
			navigateToAnyScreenOnWeb("Live TV");
			waitForElementAndClickIfPresent(PWAShowsPage.objFirstAssetTitleLiveTvCard, 10, "Live TV Card");
			waitForElement(PWAPlayerPage.objContentTitleLiveTV, 10, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitleLiveTV,"Content Title").toString();
		} 
		else {
			waitTime(6000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchedResult(contentTitle), 10, "Search Result");
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
		}
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation","Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			logger.info("Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			if (contentType.equals("Live TV")) {
				pausePlayerForLiveTV();
			} else {
				pausePlayerAndGetLastPlayedTime();
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}
	
	/**
	 * Method to pause the player and get the duration lapsed
	 * 
	 * @throws Exception
	 */
	public void pausePlayerAndGetLastPlayedTime() throws Exception {
		if(!waitForElementToLeaveScreen(PWAPlayerPage.objPlayLoader,10,"Player Loader")) {
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
		String duration = getElementPropertyToString("innerText", PWAPlayerPage.objPlayerCurrentDuration,"Player Current Duration").toString();
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
				click(PWAPlayerPage.objPlayerPause, "Player Pause");
				try {
					getWebDriver().findElement(PWAPlayerPage.playBtn);
					extent.extentLogger("playerPaused", "Paused the Player");
					logger.info("Paused the Player");
					playerPaused = true;
					break;
				} catch (Exception e) {}
			} 
			catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Player");
					logger.error("Error when handling Player");
				}
			}
		}
		return playerPaused;
	}
	
	public boolean pauseLivePlayer() throws InterruptedException {
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			try {
				click(PWAPlayerPage.objPlayer, "Player");
				//Thread.sleep(1000);
				//(PWAPlayerPage.objPlayerPause, "Player Pause");
				try {
					getWebDriver().findElement(PWAPlayerPage.playBtn);
					extent.extentLogger("playerPaused", "Paused the Player");
					logger.info("Paused the Player");
					playerPaused = true;
					break;
				} catch (Exception e) {}
			} 
			catch (Exception e) {
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
		verifyElementNotPresent(PWAPlayerPage.objPlayerLoader, 60);
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
			extent.extentLoggerFail("failedAd", "Ad play failure");
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
		if(!waitForElementToLeaveScreen(PWAPlayerPage.objPlayLoader,10,"Player Loader")) {
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
				if(time==seconds) {
					logger.info(message+" is displayed");
					extent.extentLogger("element is displayed", message+" is displayed");
					return true;
				}
			} catch (Exception e) {
				logger.info(message+" is not displayed");
				extent.extentLogger("element is displayed", message+" is not displayed");
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
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup,90,"Continue on Display Language Pop Up");
		Thread.sleep(5000);
		waitForElementAndClickIfPresent(PWAHomePage.objContinueDisplayContentLangPopup,10,"Continue on Content Language Pop Up");
	}

	public void verifyWatchLatestEpisodeCTA(String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Watch Latest Episode CTA");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Details page: "+contentTitle);
			logger.info("Successfully navigated to the correct Details page: "+contentTitle);
			verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest CTA button");
			String watchLatestCTAText = getElementPropertyToString("innerText", PWAShowsPage.objWatchLatestCTA,"Watch Latest CTA button").toString();
			if (watchLatestCTAText.equals("Watch Latest Episode")) {
				extent.extentLogger("correctButtonText", "Correct button text displayed: " + watchLatestCTAText);
				logger.info("Correct button text displayed: " + watchLatestCTAText);
			} else {
				extent.extentLoggerFail("incorrectButtonText","Incorrect button text displayed: " + watchLatestCTAText);
				logger.error("Incorrect button text displayed: " + watchLatestCTAText);
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
	}

	public void verifyShareAndMetaDataInDetailsAndConsumption(String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Share functionality and metadata comparison between Show Details and Consumption page");
		boolean sharePassed = false;
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 60, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct details page: "+contentTitle);
			logger.info("Successfully navigated to the correct Details page: "+contentTitle);
			// Share functionality
			waitForElementAndClickIfPresent(PWAShowsPage.objShareIcon, 5, "Share Icon");
			waitForElementAndClickIfPresent(PWALiveTVPage.objFacebookShareBtn, 5, "Share to Facebook");
			switchToWindow(2);
			if(verifyElementExist(PWALiveTVPage.objFacebookEmailField, "Facebook Email field"))
			{
			//waitForElementAndClickIfPresent(PWALiveTVPage.objFacebookEmailField, 5, "Facebook Email field");
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
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
		if (sharePassed == true) {
			ScrollToTheElementWEB(PWAShowsPage.objFirstAssetTitleFirstRail);
			String detailsTitle = getElementPropertyToString("innerText", PWAShowsPage.objFirstAssetTitleFirstRail,"Content Title in Details Page").toString();
			String detailsEpisode = getElementPropertyToString("innerText", PWAShowsPage.objFirstAssetEpisodeFirstRail,"Content Episode number in Details Page").toString();
			String detailsDuration = getElementPropertyToString("innerText",PWAShowsPage.objFirstAssetDurationFirstRail, "Content total Duration in Details Page").toString();
			System.out.println("Data fetched from Show details: Title: " + detailsTitle + ", Episode: " + detailsEpisode+ ", Duration: " + detailsDuration);
			extent.extentLogger("dataFetched", "Data fetched from Show details: Title: " + detailsTitle + ", Episode: "+ detailsEpisode + ", Duration: " + detailsDuration);
			logger.info("Data fetched from Show details: Title: " + detailsTitle + ", Episode: " + detailsEpisode+ ", Duration: " + detailsDuration);
			detailsEpisode = detailsEpisode.split("E")[1];
			verifyElementPresentAndClick(PWAShowsPage.objFirstAssetImageFirstRail, "First asset image from first rail");
			waitForElementDisplayed(PWAPlayerPage.objContentTitle,10);
			String consumptionTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title in Consumption Page").toString();
			String consumptionEpisode = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaEpisode,"Content Episode Number in Consumption Page").toString();
			String consumptionDuration = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaDuration,"Content Total Duration in Consumption Page").toString();
			System.out.println("Data fetched from Consumptions page: Title: " + consumptionTitle + ", Episode: "+ consumptionEpisode + ", Duration: " + consumptionDuration);
			extent.extentLogger("dataFetched", "Data fetched from Consumptions page: Title: " + consumptionTitle+ ", Episode: " + consumptionEpisode + ", Duration: " + consumptionDuration);
			logger.info("Data fetched from Consumptions page: Title: " + consumptionTitle + ", Episode: "+ consumptionEpisode + ", Duration: " + consumptionDuration);
			consumptionEpisode = consumptionEpisode.split("Episode ")[1];
			if (detailsTitle.equals(consumptionTitle)) {
				extent.extentLogger("titleMatch","Details page and Consumption page content Title matched: " + consumptionTitle);
				logger.info("Details page and Consumption page content Title matched: " + consumptionTitle);
			} else {
				extent.extentLoggerFail("titleMismatch","Details page and Consumption page content mismatched: " + consumptionTitle);
				logger.error("Details page and Consumption page content mismatched: " + consumptionTitle);
			}
			if (detailsEpisode.equals(consumptionEpisode)) {
				extent.extentLogger("episodeMatch","Details page and Consumption page content episode number matched: " + consumptionEpisode);
				logger.info("Details page and Consumption page content episode number matched: " + consumptionEpisode);
			} else {
				extent.extentLoggerFail("episodeMismatch","Details page and Consumption page content episode number mismatched: " + consumptionEpisode);
				logger.error("Details page and Consumption page content episode number mismatched: " + consumptionEpisode);
			}
			if (detailsDuration.equals(consumptionDuration)) {
				extent.extentLogger("durationMatch","Details page and Consumption page content total duration matched: " + consumptionDuration);
				logger.info("Details page and Consumption page content total duration matched: " + consumptionDuration);
			} else {
				extent.extentLoggerFail("durationMismatch","Details page and Consumption page content total duration mismatched: " + consumptionDuration);
				logger.error("Details page and Consumption page content total duration mismatched: " + consumptionDuration);
			}
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Details page: " + consumptionPageTitle);
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
			extent.extentLogger("Alert PopUp","Dismissed the alert Pop Up");
		}catch(Exception e) {
			
		}
	}


	public void verifySubscriptionPopupForPremiumContent(String userType, String contentType, String contentTitle, String devicePin) throws Exception {
		extent.HeaderChildNode("Verify Subscription Popup For Premium Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 60, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		if(waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog,1,"Close in Premium Pop Up")) {
			logger.info("Verify Subscription Popup For Premium Content is passed");
			extent.extentLogger("LocatedElementPopup", "Verify Subscription Popup For Premium Content is passed");
		}
		else {
			if(userType.equals("Guest"))
				waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog,5,"Close in Register Pop Up");
			waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
			String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
			if (consumptionPageTitle.contains(contentTitle)) {
				extent.extentLogger("correctNavigation", "Successfully navigated to the correct Consumption page: "+contentTitle);
				logger.info("Successfully navigated to the correct Consumption page: "+contentTitle);
				if(userType.equals("Guest") || userType.equals("NonSubscribedUser")) 
				{
					waitForElement(PWAPremiumPage.objPremiumPopUp, 15, "Premium Pop Up");
					waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog,1,"Close in Premium Pop Up");
				}
				else {
					waitForElementAbsence(PWAPremiumPage.objPremiumPopUp, 15, "Premium Pop Up for Subscribed User");
				}
			} else {
				extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page: " + consumptionPageTitle);
				logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
			}
		}	
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

	public void verifyNoSubscriptionPopupForFreeContent(String userType, String contentType, String contentTitle, String devicePin) throws Exception {
		extent.HeaderChildNode("Verify No Subscription Popup For Free Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 60, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		if(userType.equals("Guest"))
		waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog,5,"Close in Register Pop Up");
		waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Consumption page: "+contentTitle);
			logger.info("Successfully navigated to the correct Consumption page: "+contentTitle);
			waitForElementAbsence(PWAPremiumPage.objPremiumPopUp, 45, "Premium Pop Up");
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}	
	}
	
	public boolean waitForElementAndClickIfPresent(By locator, int seconds, String message) throws InterruptedException  {
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
			logger.info(message+" is displayed");
			extent.extentLogger("element is displayed", message+" is displayed");
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
        js.executeScript("window.scrollBy(0,"+y+")", "");
	}
	
	 /**
     * fetch selected languages
     * @throws Exception
     */
    public String allSelectedLanguagesWEB() throws Exception {
    	waitTime(3000);
    	(new WebDriverWait(getWebDriver(), 60)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PWAHamburgerMenuPage.objLanguageBtnWEB));
    	Actions act = new Actions(getWebDriver());
    	act.moveToElement(getWebDriver().findElement(PWAHamburgerMenuPage.objLanguageBtnWEB));
    		click(PWAHamburgerMenuPage.objLanguageBtnWEB, "language btn");
//    		waitTime(2000);
    		waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn,2, "content languages");
    		(new WebDriverWait(getWebDriver(), 60))
   		 .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PWAHamburgerMenuPage.objContentLanguageWrapper));
    		List<WebElement> allSelectedLanguages = getWebDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);

    		String langtext = "";
    		for(int i=0;i<allSelectedLanguages.size();i++) {
    			
    		    langtext = allSelectedLanguages.get(i).getAttribute("for").replace("content_", "") +"," + langtext;
    		}
    		String finalLangString = langtext.replaceAll(",$","");
    		waitForElementAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB,2, "language btn");
    	return finalLangString;
    }  
    
    
	public void verifyMetadataOnCarousel(String screen, String pageName, String languageSmallText) throws Exception {
		extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);
		navigateToAnyScreenOnWeb(screen);
		waitTime(5000);
		boolean isTitlePresent = false;
		
		System.out.println("Selected languages : "+languageSmallText);
		List<String> allMetaTitleOnCarouselAPI = ResponseInstance.traysTitleCarousel(pageName, languageSmallText);
	    System.out.println("API Data : " + allMetaTitleOnCarouselAPI);
	    
		for(int i=0; i<allMetaTitleOnCarouselAPI.size();i++) {
	    	for(int j=0; j<30;j++) {
	    		 WebElement mastHeadEle = (new WebDriverWait(getWebDriver(), 60)).until(ExpectedConditions.presenceOfElementLocated(PWAHomePage.objContTitleTextCarouselWeb(allMetaTitleOnCarouselAPI.get(i))));
	    		 isTitlePresent = verifyElementExistUsingWebEl(mastHeadEle, "Carousel Title");
	    		 if(isTitlePresent == true) {
	    			 break;
	    		 }
	    		 else {
	    			 click(PWANewsPage.objRight, "Right button of Carousel");
	    		 }
	    	}
	    	if(isTitlePresent==true) {
		    	logger.info("API title "+allMetaTitleOnCarouselAPI.get(i)+" is present on UI");
		    	extent.extentLogger("Metadata validation","API title "+allMetaTitleOnCarouselAPI.get(i)+" is present on UI");
	    	}
	    	else {
	    		logger.error("API title did not matched with UI title");
	    		extent.extentLoggerFail("Metadata validation","API title did not matched with UI title");
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
			extent.extentLogger(""+str + " is not displayed", ""+str + " is not displayed");
			return false;
		}
		return false;
	}
	
	
	
	
	 /**
	  * ==============================SMOKE P2 Scenarios==================================
	  * 
	  */
	   
	  
	   /**
		  * ==========================YASHASWINI LANGUAGE MODULE===========================
		  * 
		  */ 
	   
	
	public void LanguageModule(String userType) throws Exception {
		extent.HeaderChildNode("Language setting Module");
		// Validate language selection option is displayed
		// click on language button
		Thread.sleep(5000);
		partialScroll();partialScroll();
			boolean staleElement2 = true;
			while (staleElement2) {
				try {
					Thread.sleep(5000);
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language button");
					staleElement2 = false;
				} catch (StaleElementReferenceException e) {
					staleElement2 = true;
				}
			}
		
		//	click(PWAHomePage.objLanguageBtn, "Language button");
		
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
		partialScroll();partialScroll();
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
		for(int i=0; i<= sele; i++) {
			Thread.sleep(2000);
			click(PWALanguageSettingsPage.objSelectedLang, "Selected language");
		}

		// Verify user can select multiple Content languages
		
		for (int i = 1; i <= 3; i++) {
			String language = getElementPropertyToString("innerText", PWALanguageSettingsPage.objAllLangByindex(i),"Language");
			Thread.sleep(1000);
			click(PWALanguageSettingsPage.objAllLangByindex(i), language + " Language");
		}
		Thread.sleep(5000);
		int size = getWebDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
		if (size > 1) {
			softAssert.assertEquals(size > 1, true, "User can select multiple languages");
			extent.extentLogger("Selected content languages : ","Selected content languages : " +size);
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
		
		//Verify user can close the pop up by clicking anywhere in the application
		//click on home button
		if(userType.equals("NonSubscribedUser")) {
			getWebDriver().findElement(By.xpath("//body")).click();
		}else {
			getWebDriver().findElement(By.xpath("//html")).click();
		}
		
		//Verify the Language pop up
		if(verifyElementExist(PWAHamburgerMenuPage.objDisplayLang, "Language Pop up") == false) {
			logger.info("Langugae Pop up is closed after clicking anywhere on the application");
			extent.extentLogger("Verify Pop up", "Langugae Pop up is closed after clicking anywhere on the application");
		}else {
			logger.info("Langugae Pop up did not closed after clicking anywhere on the application");
			extent.extentLoggerFail("Verify Pop up", "Langugae Pop up did not closed after clicking anywhere on the application");
			JSClick(PWAHomePage.objLanguageBtn,"Language button");
		}
	
	}

	   
	   
	 
	 /**
	  * ==========================YASHASWINI MenuORSettings===========================
	  * 
	  */
	
	
	public void MenuOrSettingScenarios(String UserType) throws Exception {
		
		switch (UserType) {
		case "Guest" :
			extent.HeaderChildNode("Guest user scenario");
			extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
			logger.info("Accessing as Guest User");
			verificationsOfExploreOptions();
			verificationsOfMenuOptions();
			verificationsOfInfoOptions();
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
			verificationsOfInfoOptions();
			resetToDefault();
			parentControlFunctionality("Subscribed");
			authenticationFunctionality();
		}
	}
	
	
public void verificationsOfInfoOptions() throws Exception {
	extent.HeaderChildNode("Verifications of Info dropdown options");
//	waitTime(15000);
	HeaderChildNode("About us screen");		
	verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	partialScroll();
	verifyElementPresent(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
	click(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
	if(verifyElementExist(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page")){
		logger.info("User is navigated to About Us screen");
		extent.extentLogger("About Us", "User is navigated to About Us screen");
	}
	String aboutUsURL = getWebDriver().getCurrentUrl();
	if (aboutUsURL.contains("aboutus")) {
		logger.info("About Us screen is opened in webview");
		extent.extentLogger("About Us", "About Us screen is opened in webview");
		
	}
	else{
		logger.info("About Us screen is not opened in webview");
	}
	verifyElementPresent(PWAHamburgerMenuPage.objAboutUsInfo, "Brief information of the application");
	Back(1);
	
	HeaderChildNode("Help Center Screen");
	verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	partialScroll();
	waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
		switchToWindow(2);
		if(verifyElementExist(PWAHamburgerMenuPage.objHelpUsHeader,"Help Center screen")){
			logger.info("User is navigated to Help Center screen");
			extent.extentLogger("Help Center", "User is navigated to Help Center screen"); 
		}
		waitTime(2000);
		getWebDriver().close();
		switchToParentWindow();
	//Terms of Use
		HeaderChildNode("Terms of Use screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
		if(verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen")){
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
	//Privacy Policy
		HeaderChildNode("Privacy Policy screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
		if(verifyElementExist(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen")){
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
		
		
		HeaderChildNode("Build version");		
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");		
		partialScroll();
		verifyElementExist(PWAHamburgerMenuPage.objBuildVersion, "Build Version");
		String version = getText(PWAHamburgerMenuPage.objBuildVersion);
		logger.info("Build version is : "+version);
		extent.extentLogger("version", "Build version is : "+version);
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	
}

	public void verificationsOfMenuOptions() throws Exception {
	extent.HeaderChildNode("Verifications of Menu dropdown options");
//	waitTime(15000);
//	Swipe("UP", 1);
	if (verifyElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu")) {
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if(verifyElementPresent(PWAHamburgerMenuPage.objExploreBtn,"Explore button")) {
			logger.info("Explore button displayed under Menu");
			extent.extentLogger("Explore button", "Explore button displayed under Menu");
		}else {
			logger.info("Explore button not displayed under Menu");
			extent.extentLogger("Explore button", "Explore button not displayed under Menu");
		}
		
		if(verifyElementPresent(PWAHamburgerMenuPage.objPlans,"Plans button")) {
			logger.info("Plans button displayed under Menu");
			extent.extentLogger("Plans button", "Plans button displayed under Menu");
		}else {
			logger.info("Plans button not displayed under Menu");
			extent.extentLogger("Plans button", "Plans button not displayed under Menu");
		}
		
		if(verifyElementPresent(PWAHamburgerMenuPage.objSettings,"Settings button")) {
			logger.info("Settings button displayed under Menu");
			extent.extentLogger("Settings button", "Settings button displayed under Menu");
		}else {
			logger.info("Settings button not displayed under Menu");
			extent.extentLogger("Settings button", "Settings button not displayed under Menu");
		}
		
		if(verifyElementPresent(PWAHamburgerMenuPage.objInfo,"Info button")) {
			logger.info("Info button displayed under Menu");
			extent.extentLogger("Info button", "Info button displayed under Menu");
		}else {
			logger.info("Info button not displayed under Menu");
			extent.extentLogger("Info button", "Info button not displayed under Menu");
		}
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	}
	languageOptionFunctionality();
}

	public void languageOptionFunctionality() throws Exception {
		extent.HeaderChildNode("Verifying Language option availablity");
		//	waitTime(15000);
			if (verifyElementPresentAndClick(PWAHomePage.objLanguageBtn, "Language Button")) {
				logger.info("Language button on Home screen");
				verifyElementExist(PWAHomePage.objLanguagePop, "Languagepopup");
				System.out.println("Language wrap displayed");
				click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Display Language screen");
				click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Content Language screen");
			}
		}
	

	public void verificationsOfExploreOptions() throws Exception {
		extent.HeaderChildNode("Verifications of Explore dropdown options");
		waitTime(5000);
		scrollDownWEB();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if(verifyElementExist(PWAHamburgerMenuPage.objExploreBtn, "Explore option")) {
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
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		}
	}
		
	public void navigationsFromPlanSectionWeb() throws Exception {
		extent.HeaderChildNode("Functionality of MyPlan options");
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
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInHamburger, "More settings in settings section");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, "Language button");
			waitTime(2000);
			click(PWAHamburgerMenuPage.objSelectLanguage, "Language icon");
			if(verifyElementExist(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Language")) {
				logger.info("clicked on hindi language in Display language popup");
				extent.extentLogger("Content language",
	                    "clicked on hindi language in Display language popup");
			}
			click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
			click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtnWEB, "Language button");
			if(verifyElementExist(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Hindi Selected Language")==false) {
				logger.info("Reset to default is success");
				extent.extentLogger("Content language",
	                    "Reset to default is success");
				click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
				click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");
				verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
			}
			else {
				logger.info("Reset to defualt was unsuccessfull");
				extent.extentLogger("Reset to defualt",
	                    "Reset to defualt was unsuccessfull");
			}
		}
		public void parentControlFunctionality(String UserType) throws Exception {
			extent.HeaderChildNode("Parent Control Functionality");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objParentalControl, "ParentalControl");
			verifyElementExist(PWALoginPage.objPasswordField, "password field");
			String password="";
			if(UserType.equals("Non-Subscribed")) {
				password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("NonsubscribedPassword");
			}
			else if(UserType.equals("Subscribed")) {
				 password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
						.getParameter("SubscribedPassword");
			}
			type(PWALoginPage.objPasswordField, password, "Password field");
			click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
			waitTime(2000);
			verifyElementExist(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
			verifyElementExist(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restricted option");
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
	 		type(PWAHomePage.objSearchField, "Gooli", "Search");
	 		waitTime(10000);
	 		verifyElementPresentAndClick(PWAHomePage.objSearchResult, "Search result");
	 		waitTime(5000);
			verifyElementExist(PWAHamburgerMenuPage.objParentalLockPopUpInPlayer, "Parent control Popup");
			 type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
			    type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
			    type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
			    type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
			    waitTime(5000);
			    waitForPlayerAdToComplete("Video Player");
			    click(PWAPlayerPage.objPlaybackVideoOverlay, "Playback Overlay");
			    if(verifyElementExist(PWAPlayerPage.playBtn, "Pause icon")) {
			    	logger.info("Playback is played after entering parental lock");
			    	extent.extentLogger("Playback", "Playback is played after entering parental lock");
			    }
			    else {
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
			if(getWebDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled()==false) {
				logger.info("Authenticate button is not highlighted by default");
				extent.extentLogger("Authenticate", "Authenticate button is not highlighted by default");
			}
			type(PWAHamburgerMenuPage.objAuthenticationField, "abcdef", "AuthenticationField");
			String AuthenticationField = getText(PWAHamburgerMenuPage.objAuthenticationField);
			if (AuthenticationField!=null) {
				logger.info("User is able to enter the value in AuthenticationField");
				extent.extentLogger("AuthenticationField", "User is able to enter the value in AuthenticationField");
			}
			
			if(getWebDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled()) {
				logger.info("Authenticate button is highlighted after entering the input in AuthenticationField");
				extent.extentLogger("Authenticate", "Authenticate button is highlighted after entering the input in AuthenticationField");
			}
			
			click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
			try {
		 Boolean ExpiredToastMessage = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
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
			Boolean NotfounfToastMessage = getWebDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
			if (NotfounfToastMessage == true) {
				extent.extentLogger("Toast", "Not found Toast message displayed");
				logger.info("Not found Toast message displayed");
			} else {
				extent.extentLogger("Toast", "Not found Toast message not displayed");
				logger.info("Not found Toast message not displayed");
			}
			}catch(Exception e) {
				System.out.println("Toast message is not displayed");
			}
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
		}
	
	
	
		
		/**
		 * ==========================BHAVANA ShareFunctionality===========================
		 * 
		 */ 
		
		
		public void ShareModule(String userType) throws Exception{
			extent.HeaderChildNode(userType + " scenarios");
			extent.extentLogger("Accessing as " + userType , "Accessing as " + userType);
			logger.info("Accessing as " + userType);
			ZeeWEBPWALogin(userType);
			WebFacebookShareFunctionality();
			WebTwitterShareFunctionality();
			shareModuleValidationforWEB();
		}	
		
		
		public void shareModuleValidationforWEB() throws Exception{
			extent.HeaderChildNode("Share functionality validation");
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
			type(PWASearchPage.objSearchEditBox, "kannadada Kanmani", "Search Field");
			waitTime(5000);
			hideKeyboard();
			click(PWASearchPage.objSpecificSearch("Kannadada Kanmani"), "Searched Show");
			verifyElementExist(PWAShowsPage.objWatchLatestCTA, "Watch latest episode CTA");
			waitTime(3000);
			click(PWAShowsPage.objWatchLatestCTAPlayicon, "Watch latest episode CTA");
			waitTime(4000);
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			waitTime(2000);
			Why_Register_POPUP();
			//waitTime(20000);
			click(PWAShowsPage.objShareIcon, "Share icon");
			WebShareFunctionalityContent();
			
		}
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
			if(verifyElementExist(PWALiveTVPage.objFacebookEmailField, "Facebook Email field")) {
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

		/*
		 * Function to validate the Web Share functionality
		 */
		public void WebFacebookShareFunctionality() throws Exception {
			// click on share Option
			extent.HeaderChildNode("Share through Facebook functionality Validation");
			
			partialScroll();
			Actions actions = new Actions(getWebDriver());
		    WebElement menuOption = getWebDriver().findElement(By.xpath("((//div[@class='slick-list'])[2]//img)"));
		    actions.moveToElement(menuOption).build().perform();		
			// Verify Facebook share option
		    verifyElementPresentAndClick(PWAPremiumPage.objContentCardShareBtn, "Share Icon");
			Thread.sleep(2000);
			verifyElementPresent(PWAPlayerPage.facebookShareBtn, "Facebook share option");
			Thread.sleep(2000);		
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
			if(verifyElementExist(PWALiveTVPage.objFacebookEmailField1, "Facebook Email field")) {
				verifyElementPresentAndClick(PWALiveTVPage.objFacebookEmailField1, "Facebook Email field");
//			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
//				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
//			}
			getWebDriver().findElement(PWALiveTVPage.objFacebookEmailField1).sendKeys("igszeetest@gmail.com");
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
		}


		public void WebTwitterShareFunctionality() throws Exception {
			partialScroll();
			Actions actions = new Actions(getWebDriver());
		    WebElement menuOption = getWebDriver().findElement(By.xpath("((//div[@class='slick-list'])[2]//img)"));
		    actions.moveToElement(menuOption).build().perform();		
			// Verify Facebook share option
		    verifyElementPresentAndClick(PWAPremiumPage.objContentCardShareBtn, "Share Icon");
			Thread.sleep(2000);
			// Verify user is navigated to Twitter page
			verifyElementPresent(PWAPlayerPage.twitterShareBtn, "Twitter share option");
			Thread.sleep(2000);		
			click(PWAPlayerPage.twitterShareBtn, "Twitter share option");
			Thread.sleep(2000);
			if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
			}
			// Switch to window
			verifyAlert();
			switchToWindow(2);
			Thread.sleep(2000);
			switchToWindow(2);
			Thread.sleep(2000);
			verifyAlert();
			waitTime(3000);
			verifyElementExist(PWALiveTVPage.objTwitterPasswordField1, "Twitter Email field");		
		    waitTime(2000);		
		    click(PWALiveTVPage.objTwitterEmailField, "Twitter Email field");
			getWebDriver().findElement(PWALiveTVPage.objTwitterPasswordField1).sendKeys("zee5latest@gmail.com");
			waitTime(2000);		
			verifyElementPresentAndClick(PWALiveTVPage.objTwitterPasswordField1, "Twitter Password field");
			getWebDriver().findElement(PWALiveTVPage.objTwitterPasswordField1).sendKeys("User@123");
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
		 * ==========================BHAVANA StaticPages===========================
		 * 
		 */ 
	
	
		public void StaticPages(String userType) throws Exception{
			extent.HeaderChildNode(userType + " scenarios");
			extent.extentLogger("Accessing as " + userType , "Accessing as " + userType);
			logger.info("Accessing as " + userType);
					ZeeWEBPWALogin(userType);
					staticPagesValidationWEB();
	}	

	public void staticPagesValidationWEB() throws Exception {
		extent.HeaderChildNode("Validation of Static Pages in Hamburger menu");
		//About Us
		HeaderChildNode("About us screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
		verifyElementPresent(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page");
		logger.info("Current URL is " + getWebDriver().getCurrentUrl());
		String AboutUsurl = getWebDriver().getCurrentUrl();
		if (AboutUsurl.contains("aboutus")) {
			logger.info("User is navigated to About Us screen");
		}
		Back(1);
		//Help Center
		HeaderChildNode("Help Center Screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
		switchToWindow(2);
		if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
			logger.info("User is navigated to Help Center Screen");
		}
		getWebDriver().close();
		switchToParentWindow();
		//Terms of Use
		HeaderChildNode("Terms of Use screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
		verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen");
		logger.info("Current URL is " + getWebDriver().getCurrentUrl());
		String TermsofuseURL = getWebDriver().getCurrentUrl();
		if (TermsofuseURL.contains("termsofuse")) {
			logger.info("User is navigated to Terms of Use screen");
		}
		Back(1);
		//Privacy Policy
		HeaderChildNode("Privacy Policy screen");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		partialScroll();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
		verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen");
		logger.info("Current URL is " + getWebDriver().getCurrentUrl());
		String PrivacyPolicyURL = getWebDriver().getCurrentUrl();
		if (PrivacyPolicyURL.contains("privacypolicy")) {
			logger.info("User is navigated to Privacy Policy Screen");
		}
		Back(1);
		
		//Static Pages in Footer Section
		
		extent.HeaderChildNode("Static Pages in Footer Section Validation");
		scrollDownWEB();
		verifyElementPresentAndClick(PWAHomePage.objAboutUsInFooterSection, "About Us in footer section");
		if (verifyElementExist(PWAHomePage.objAboutUs, "About Us screen")) {
			logger.info("User is navigated to About Us Screen");
		}
		Back(1);
		verifyElementPresentAndClick(PWAHomePage.objHelp, "Help Center in footer section");
		switchToWindow(2);
		if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
			logger.info("User is navigated to Help Center Screen");
		}
		getWebDriver().close();
		switchToParentWindow();
		scrollDownWEB();
		verifyElementPresentAndClick(PWAHomePage.objPrivacyPolicyInFooterSection, "Privacy Policy in footer section");
		if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
			logger.info("User is navigated to Privacy Policy Screen");
		}
		Back(1);
		scrollDownWEB();
		verifyElementPresentAndClick(PWAHomePage.objTermsOfUseInfooterSection, "Terms of Use in footer section");
		if (verifyElementPresent(PWAHomePage.objTerms, "Terms of Use screen")) {
			logger.info("User is navigated to Terms of Use Screen");
		}
		Back(1);
		
		//Static pages in Sign Up screen
		
		extent.HeaderChildNode("Static Pages in Sign up screen Validation");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objSignUpForFree,"Sign Up for FREE");
		waitTime(2000);
		//Terms of Use
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfServicesInSignupScreen, "Terms of services in Sign up screen");
		waitTime(2000);
		switchToWindow(2);
		if (verifyElementPresent(PWAHomePage.objTerms, "Terms of Use screen")) {
			logger.info("User is navigated to Terms of Use Screen");
		}
	    getWebDriver().close();
		switchToParentWindow();
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicyInSignupScreen,"Privacy Policy in Sign up screen");
		waitTime(2000);
		switchToWindow(2);
		if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
			logger.info("User is navigated to Privacy Policy Screen");
		}
		getWebDriver().close();
		switchToParentWindow();
		Back(1);
		}	
		
		
	/*
	 * ==========================Guest_User_User-Actions_Module====================
	 */

	public void UserActionGuestUser() throws Exception {
		extent.HeaderChildNode("User Action module- Guest user Validataions");
		// Validate Continue watching tray is not displayed for Guest user
		
		if(getPlatform().equalsIgnoreCase("Web")){
			ScrollToTheElementWEB(PWAHomePage.objFirstContentCardOfTray("Recommended Movies"));
			if(verifyElementExist(PWAHomePage.objFirstContentCardOfTray("Recommended Movies"),"First Content Card Of Recommended Movies Tray")){
				Actions action = new Actions(getWebDriver());
				action.moveToElement(findElement(PWAHomePage.objFirstContentCardOfTray("Recommended Movies")));
				action.perform();
				if(verifyElementExist(PWAHomePage.objAddToWatchlistButtonOnTrayContentCard("Recommended Movies"),"Add To Watchlist icon on tray 1st content card")){
					extent.extentLogger("Verify Add To Watchlist icon on tray content card", "Add To Watchlist icon on tray content card is displayed for guest user");
					logger.info("Add To Watchlist icon on tray content card is displayed for guest user");
				} else {
					extent.extentLoggerFail("Verify Add To Watchlist icon on tray content card", "Add To Watchlist icon on tray content card is not displaying for guest user");
					logger.info("Add To Watchlist icon on tray content card is not displaying for guest user");
				}
				
				click(PWAHomePage.objAddToWatchlistButtonOnTrayContentCard("Recommended Movies"),"Add To Watchlist icon on tray 1st content card");
				if(verifyElementExist(PWAHomePage.objLoginRequiredPopUpHeader, "Login Required PopUp Header")){
					extent.extentLogger("Login popup is displayed when clicked on 'Add to Watchlist' icon on tray content card", "Login popup is displayed when clicked on 'Add to Watchlist' icon on tray content card for guest user");
					logger.info("Login popup is displayed when clicked on 'Add to Watchlist' icon on tray content card for guest user");
					click(PWAHomePage.objPopupCloseicon(), "Popup Close icon");
				} else {
					extent.extentLoggerFail("Login popup is not displayed when clicked on 'Add to Watchlist' icon on tray content card", "Login popup is not displayed when clicked on 'Add to Watchlist' icon on tray content card");
					logger.info("Login popup is not displayed when clicked on 'Add to Watchlist' icon on tray content card");
				}
				
				
			}
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee5 Logo");
		}
		
		waitTime(3000);
		if (verifyElementExist(PWAHomePage.objContinueWatchingTray, "Continue Watching tray") == false) {
			extent.extentLogger("Verify Continue Watching tray",
					"Continue watching tray is not displayed for guest user");
			logger.info("Continue watching tray is not displayed for guest user");
		} else {
			softAssert.assertAll();
			extent.extentLoggerFail("Verify Continue Watching tray", "Continue watching tray is displaying for guest user");
			logger.info("Continue watching tray is displaying for guest user");
		}
		validateDisplayLanguagePopup();
		// Verify Add to Reminder is not displayed for guest user
		// Click on live tv tab
		if(getPlatform().equalsIgnoreCase("Android")){
			navigateToAnyScreen("Live TV");
		}else if(getPlatform().equalsIgnoreCase("Web")){
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
		
	public void FilterLanguage() throws Exception {
		click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
		int size = findElements(PWALiveTVPage.objSelectedlang).size();
		for (int i = 1; i <= size; i++) {
			click(PWALiveTVPage.objSelectedlang, "Selected language");
		}
		click(PWALiveTVPage.objKannadaLang, "Kannada language");
		click(PWALiveTVPage.objApplyBtn, "Apply button");
//		click(PWALiveTVPage.objApplyBtn,"Apply button");
	}
	
	public void navigateToAnyScreen(String screen) throws Exception {
		for (int i = 0; i < 3; i++) {
			try {
				verifyElementPresentAndClick(PWAHomePage.objTabName(screen), "Tab : " + screen);
				waitTime(5000);
				break;
			} catch (Exception e) {
				try {
					swipeOnTab("Left");
					verifyElementPresentAndClick(PWAHomePage.objTabName(screen), "Tab : " + screen);
					waitTime(5000);
					break;
				} catch (Exception exc) {
					swipeOnTab("Right");
				}
			}
		}
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

}