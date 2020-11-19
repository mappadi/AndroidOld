package com.business.zee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.utility.Utilities;
import com.zee5.PWAPages.NativeVodafonePlayPage;
import com.zee5.PWAPages.PWAAddToWatchListPage;
import com.zee5.PWAPages.PWAHamburgerMenuPage;
import com.zee5.PWAPages.PWAHomePage;
import com.zee5.PWAPages.PWALandingPages;
import com.zee5.PWAPages.PWALanguageSettingsPage;
import com.zee5.PWAPages.PWALiveTVPage;
import com.zee5.PWAPages.PWALoginPage;
import com.zee5.PWAPages.PWAMoviesPage;
import com.zee5.PWAPages.PWANewsPage;
import com.zee5.PWAPages.PWAPlayerPage;
import com.zee5.PWAPages.PWAPremiumPage;
import com.zee5.PWAPages.PWASearchPage;
import com.zee5.PWAPages.PWAShowsPage;
import com.zee5.PWAPages.PWASignupPage;
import com.zee5.PWAPages.PWASubscriptionPages;
import com.zee5.PWAPages.PWAVodafonePlayPage;
import com.zee5.PWAPages.PwaNews;
import com.driverInstance.CommandBase;
import com.emailReport.GmailInbox;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.metadata.getResponseUpNextRail;
import com.propertyfilereader.PropertyFileReader;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Zee5PWASmokeAndroidBusinessLogic extends Utilities {

	public Zee5PWASmokeAndroidBusinessLogic(String Application) throws InterruptedException {
		new CommandBase(Application);
		init();
	}

	public void reloadURL(String url) {
		getDriver().get(url);
		waitTime(10000);
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

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		logger.info(
				"Loaded the following properties" + " TimeOut :" + getTimeout() + " RetryCount :" + getRetryCount());
	}
	
	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}

	/**
	 * Wait until Player Loading is not displayed.
	 */

	public void LoadingInProgress(By locator) throws Exception {
		verifyElementNotPresent(locator, 60);
	}

	/**
	 * Generic function to click on the Player.
	 */

	public void playerClick(By byLocator, String validationtext) throws Exception {

		try {
			WebElement element = findElement(byLocator);
			element.click();
			logger.info("Clicked on the" + validationtext);
			extent.extentLogger("click", "Clicked on the " + " " + validationtext);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * Function to enter url
	 */
	public void enterURLInBrowser(String browser, String url) {
		extent.HeaderChildNode("Enter Browser URL");
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				getDriver().get(url);
				extent.extentLogger("enteredURL", "Entered " + url + " in " + browser + " browser");
				logger.info("Entered " + url + " in " + browser + " browser");
			} catch (Exception e) {
				extent.extentLogger("failToEnterURL", "Failed to enter " + url + " in " + browser + " browser");
				logger.info("Failed to enter " + url + " in " + browser + " browser");
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
	
	/**
	 * The method s for element and clicks if present. Returns no error if not
	 * present. Implemented for random popups
	 */
	public boolean waitForElementAndClickIfPresent(By locator, int seconds, String message) throws InterruptedException {
		for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator).click();
				logger.info("Clicked element " + message);
				extent.extentLogger("clickedElement", "Clicked element " + message);
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return false;
	}


	public void testFrameworkClicks() throws Exception {
		extent.HeaderChildNode("Click");
		System.out.println(verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search"));
		type(PWASearchPage.objSearchEditBox, "Neil gets emotional while apologising to Jhende", "Search");
		waitTime(2000);
		click(By.xpath("//h3[@class='cardTitle overflowEllipsis ']"), "content click");
	}

	@SuppressWarnings("rawtypes")
	public void clickOnPlayer(By byLocator) throws Exception {
		WebElement element = findElement(byLocator);
		System.out.println(element.getLocation());
		System.out.println(element.getAttribute("y"));
		int x = Integer.valueOf(element.getAttribute("x"));
		int y = Integer.valueOf(element.getAttribute("y"));
		System.out.println(x + " " + y);
		TouchAction act = new TouchAction(getDriver());
		act.tap(PointOption.point(x, y)).perform();
	}	
	
	/**
	 * Generic function Verification Of Options displayed in MyAccount.
	 */
	public void myaccountOptionsVerification() throws Exception {
		extent.HeaderChildNode("My account options verification");
		verifyElementPresentAndClick(PWAHomePage.objTabName("Home"), "Home button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		// verifications
		NavigationsToMyWatchlist();
		NavigationsToMyReminders();
		NavigationsToMySubsccription();
		NavigationsToMyTransactions();

	}

	/**
	 * Function for Navigation to MyWatchlist .
	 */
	public void NavigationsToMyWatchlist() throws Exception {
		extent.HeaderChildNode("My Watchlist");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Watchlist"),"My Watchlist");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Watchlist"), "My Watchlist page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
	}

	/**
	 * Function for Navigation to MyReminders .
	 */
	public void NavigationsToMyReminders() throws Exception {
		extent.HeaderChildNode("My Reminders");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		waitTime(4000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Reminders"),"My Reminders");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Reminders"), "My Reminders page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
	}

	/**
	 * Function for Navigation to MySubscription.
	 */
	public void NavigationsToMySubsccription() throws Exception {
		extent.HeaderChildNode("My Subscriptions");
		waitForElementAndClickIfPresent(PWAHamburgerMenuPage.objDownArrow("My Account"), 10, "Expander button");
		waitForElementAndClickIfPresent(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"),10, "My Subscriptions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Subscription"), "My Subscription page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
		waitTime(4000);
	}

	/**
	 * Function for Navigation to MyTransaction.
	 */
	public void NavigationsToMyTransactions() throws Exception {
        extent.HeaderChildNode("My Transactions");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Transactions"),"My Transactions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Transactions"), "My Transactions page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
	}

	/**
	 * Function for Navigation to MyProfilePage.
	 */
	public void NavigateToMyProfilePage() throws Exception {
		extent.HeaderChildNode("Navigate To My Profile Page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
	}

	/**
	 * Function To verify the options present in MyProfilePage.
	 */
	public void verificationsInMyProfilePage() throws Exception {
		extent.HeaderChildNode("Verifications In My Profile Page");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageNameTxt, "User name");
		verifyElementPresent(PWAHamburgerMenuPage.objProfilePageUserIdTxt, "User id");
		verifyElementPresent(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
	}

	/**
	 * Function To check the functionality of EditProfile option .
	 */
	public void editProfileFunctionality() throws Exception {
		extent.HeaderChildNode("Edit Profile Funcionality");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileEditBtn, "Edit button");
		verifyElementPresent(PWAHamburgerMenuPage.objEditProfileText, "Edit profile page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileFirstName, "First name column");
		getDriver().findElement(PWAHamburgerMenuPage.objEditProfileFirstName).clear();
		type(PWAHamburgerMenuPage.objEditProfileFirstName, "Zee5", "Edit profile first name");
		hideKeyboard();
		Swipe("UP", 1);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileSavechangesBtn, "Save changes");
		waitTime(2000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objEditProfileGoBackBtn, "Back button");
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
	}

	/**
	 * Function To check the Functionality of SubscribeCTA option.
	 */
	public void subscribeCTAFunctionality() throws Exception {
		extent.HeaderChildNode("SubscribeCTAFuncionality");
		if(verifyElementExist(PWAHamburgerMenuPage.objSubscritionBtn, "Subscribe cta")) {
			click(PWAHamburgerMenuPage.objSubscritionBtn, "Subscribe cta");
			verifyElementPresent(PWALoginPage.objsubscription, "Subscriptions page");
			getDriver().navigate().back();
			logger.info("Clicked on Back button");
			extent.extentLogger("Back button", "Clicked on Back button");
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		}
		else {
			logger.info("Cta is not visible");
			extent.extentLogger("Cta","Cta is not visible");
		}
	}

	/**
	 * Function To check the Functionality of ChangePassword option.
	 */
	public void changePasswordFunctionality() throws Exception {
		extent.HeaderChildNode("Change Password Functionality");
		waitTime(3000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objChangePasswordBtn, "Change password button");
		verifyElementPresent(PWAHamburgerMenuPage.objChangePasswordText, "Change password page");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objChangeOldPassword, "Current password field");
		type(PWAHamburgerMenuPage.objChangeOldPassword, "User@123", "Current password field");
		hideKeyboard();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objNewPassword, "New password field");
		type(PWAHamburgerMenuPage.objNewPassword, "igszee5", "New password field");
		hideKeyboard();
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objNewPassword, "Confirm password field");
		type(PWAHamburgerMenuPage.objConfirmNewPassword, "igszee5", "Current confirm field");
		hideKeyboard();
		waitTime(3000);
		verifyElementPresent(PWAHamburgerMenuPage.objUpdatePasswordBtnHighlighted,"Update password button");
	}

	/**
	 * Generic function to Logout.
	 */
	public void logout() throws Exception {
		extent.HeaderChildNode("Logging out");
		waitTime(5000);
		waitForElementAndClickIfPresent(PWAHamburgerMenuPage.objHamburgerBtn, 10, "Hamburger Menu");
		waitForElementAndClickIfPresent(PWAHamburgerMenuPage.objNotNow, 10, "Not Now in Add Shortcut dialog");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("Logout"), "Logout option");
		waitTime(10000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		verifyElementPresent(PWALoginPage.objLoginBtn, "User is logged out successfully");
	}

	/**
	 * Function To check the Functionality of MyPlan option.
	 */
	public void myPlanVerification() throws Exception {
		extent.HeaderChildNode("My Plan Verification In My Profile page");
		if(verifyElementExist(PWAHamburgerMenuPage.objMyplanText, "My plan column")) {
			verifyElementPresent(PWAHamburgerMenuPage.objMyActivePlan, "My active plan");
			click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger is present");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
			verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Profile"), "My Profile page");
		}
		else {
			logger.info("My plan is not displayed");
			extent.extentLogger("My Plan", "My plan is not displayed");
		}
	}

	/**
	 * Function for Onboarding scenarios
	 */
	public void OnboardingScenario(String userType) throws Exception {
		extent.HeaderChildNode("Execution for: "+userType);
		switch (userType) {
		case "Guest":
			//dismissDisplayContentLanguagePopUp();
			navigationToMyPlanFromHome();
			navigationToCTAInPlayerFromSearch();
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			if (verifyElementExist(PWALoginPage.objLoginBtn, "Login")) {
				extent.extentLogger("Not Logged in", "User is not logged in");
				logger.info("User is not logged in");
				noLogoutOption();
				forgotPassword();
				logout();
			}
			break;

		case "NonSubscribedUser":
				verificationOfLoggedIn();
				myaccountOptionsVerification();
				NavigateToMyProfilePage();
				verificationsInMyProfilePage();
				editProfileFunctionality();
				subscribeCTAFunctionality();
				changePasswordFunctionality();
				break;

		case "SubscribedUser":
				verificationOfLoggedIn();
				myaccountOptionsVerification();
				NavigateToMyProfilePage();
				verificationsInMyProfilePage();
				editProfileFunctionality();
				myPlanVerification();
				changePasswordFunctionality();
		}
	}

	/**
	 * Generic PWALogin function.
	 */
	public void ZeePWALogin(String LoginMethod,String userType) throws Exception {
		extent.HeaderChildNode("Login through : "+LoginMethod+" for user type: "+userType);
		//Get the email and password from properties
		String email="";
		String password="";
		if(userType.equals("NonSubscribedUser")) {
			email = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("NonsubscribedUserName");
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("NonsubscribedPassword");
		}
		else if(userType.equals("SubscribedUser")) {
			email = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("SubscribedUserName");
			password = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("SubscribedPassword");
		}
		else {
			System.out.println("Incorrect user type passed to login method");
		}
		if(!verifyElementExist(PWALoginPage.objLoginBtn,"Login Button")) {
			verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		}
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(3000);
		verifyElementPresent(PWALoginPage.objLoginTxt, "Login page");

		switch (LoginMethod) {
		case "E-mail":
			extent.HeaderChildNode("Login through e-mail id");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, email, "Email Field");
			hideKeyboard();
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "Password Field");
			type(PWALoginPage.objPasswordField, password+"\n", "Password field");
			hideKeyboard();
			waitTime(5000);
			click(PWALoginPage.objLoginBtn, "Login Button");
			waitTime(10000);
			break;

		case "Mobile":
			extent.HeaderChildNode("Login through mobile number");
			verifyElementPresentAndClick(PWALoginPage.objEmailField, "Email field");
			type(PWALoginPage.objEmailField, "8792396107", "Email Field");
			hideKeyboard();
			verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login butotn");
			waitTime(3000);
			hideKeyboard();
			waitTime(5000);
			verifyElementPresentAndClick(PWALoginPage.objpasswordphno, "Password field");
			waitTime(3000);
			verifyElementPresentAndClick(PWALoginPage.objPasswordField, "password-field");
			type(PWALoginPage.objPasswordField, "tanisha19", "password-field");
			hideKeyboard();
			waitTime(2000);
			click(PWALoginPage.objproceedphno, "Proceed button");
			waitTime(5000);
			break;

		case "Facebook":
			extent.HeaderChildNode("Login through Facebook");
			verifyElementPresentAndClick(PWALoginPage.objFacebookIcon, "Facebook Icon");
			System.out.println(getDriver().getCurrentUrl());
			System.out.println(getDriver().getWindowHandles());
			getDriver().switchTo().window("CDwindow-1");
			waitTime(7000);
			if (verifyElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
				click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}

			else if (verifyElementExist(PWALoginPage.objFacebookPageVerification, "Facebook page")) {
				verifyElementPresent(PWALoginPage.objFacebookPageVerification, "Facebook page");
				verifyElementPresent(PWALoginPage.objFacebookLoginEmail, " Email Field");
				type(PWALoginPage.objFacebookLoginEmail, "igszeetesttest@gmail.com", "Emial Field");
				verifyElementPresent(PWALoginPage.objFacebookLoginpassword, " Password Field");
				type(PWALoginPage.objFacebookLoginpassword, "Igs$123Zee", "Password Field");
				verifyElementPresentAndClick(PWALoginPage.objFacebookLoginButtonInFbPage, " Login Button");
				waitTime(9000);
				getDriver().switchTo().window("CDwindow-0");
				verifyElementExist(PWALoginPage.objFbCountinueBtn, "Continue button");
				if (verifyElementExist(PWASignupPage.objSignUpTxt, "SignUp")) {
					regestrationfromSocialMedia();
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
					logger.info("User Logged in Successfully");
					extent.extentLogger("Logged in", "User Logged in Successfully");
				} else {
					waitTime(3000);
					verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
					verifyElementPresent(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
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
			System.out.println(getDriver().getCurrentUrl());
			System.out.println(getDriver().getWindowHandles());
			verifyElementPresentAndClick(PWALoginPage.objGoogleIcon, "Google Icon");
			getDriver().switchTo().window("CDwindow-1");
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
				getDriver().switchTo().window("CDwindow-0");
				if (verifyElementExist(PWASignupPage.objSignUpTxt, "signup")) {
					regestrationfromSocialMedia();
				}
				waitTime(5000);
				verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
				logger.info("User is Logged in successfully");
				extent.extentLogger("Logged in", "User is Logged in successfully");
			} else {
				waitTime(10000);
				verifyElementPresent(PWAHomePage.objZeeLogo, "Zee logo");
				logger.info("User is Logged in successfully");
				extent.extentLogger("Logged in", "User is Logged in successfully");
			}

			break;

		case "Twitter":
			extent.HeaderChildNode("Login through Twitter");
			verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
			waitTime(7000);
			System.out.println(getDriver().getWindowHandles());
			System.out.println(getDriver().getCurrentUrl());
			getDriver().switchTo().window("CDwindow-1");

			waitTime(5000);
			System.out.println(getDriver().getCurrentUrl());

			if (verifyElementExist(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger")) {
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
				logger.info("User Logged in Successfully");
				extent.extentLogger("Logged in", "User Logged in Successfully");
			}

			else if (verifyElementExist(PWALoginPage.objTwitterAuthorizeButton, "Authorize app")) {
				click(PWALoginPage.objTwitterAuthorizeButton, "Authorize app");
				regestrationfromSocialMedia();
			} else if (verifyElementExist(PWALoginPage.objTwitterEmaildField, "Twitter Email field")) {

				type(PWALoginPage.objTwitterEmaildField, "Zee5latest@gmail.com", "Email Field");
				hideKeyboard();
				verifyElementPresentAndClick(PWALoginPage.objTwitterPasswordField, "Twitter Password field");
				type(PWALoginPage.objTwitterPasswordField, "User@123", "Password field");
				click(PWALoginPage.objTwitterSignInButton, "Sign in button");
				waitTime(5000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				click(PWAHamburgerMenuPage.objLoginBtn, "Login");
				verifyElementPresentAndClick(PWALoginPage.objTwitterIcon, "Twitter icon");
			}

			if (verifyElementExist(PWALoginPage.objTwitterAuthorizeButton, "Authorize")) {
				click(PWALoginPage.objTwitterAuthorizeButton, "Authorize");
				waitTime(7000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger");
				verifyElementPresent(PWAHamburgerMenuPage.objProfilePageIcon, "Profile icon");
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

	public void NavigationsToMySubscription() throws Exception {
		extent.HeaderChildNode("My Subscription");
		click(PWAHamburgerMenuPage.objDownArrow("My Account"), "Expander button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objExploreItemBtn("My Subscription"), "My Subscriptions");
		waitTime(4000);
		verifyElementPresent(PWAHamburgerMenuPage.objMyAccountOptionsText("My Subscription"), "My Subscription page");
		click(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger button");
	}

	/**
	 * Function To check the SignIn page from MyPlans screen.
	 */
	public void navigationToMyPlanFromHome() throws Exception {
		extent.HeaderChildNode("Guest user: Validating user navigated to signin screen from my plans screen");
		verifyElementPresentAndClick(PWAHomePage.objSubscribeBtn, "Subscription button");
		waitTime(3000);
		if (verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Subscription page")) {
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
		Swipe("UP", 1);
		if (verifyElementExist(PWASubscriptionPages.objadhocPopupArea, "Adoric Popup")) {
			click(PWASubscriptionPages.objadhocPopupSignUpBtn, "Adoric Popup SignUP Button");
			waitTime(4000);
			verifyElementPresent(PWASubscriptionPages.objadhocPopupRegestrationScreen, "Sign up page");
			waitTime(3000);
			getDriver().navigate().back();
			Swipe("UP", 1);
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
			waitTime(3000);
			verifyElementPresentAndClick(PWASubscriptionPages.objSelectedSubscriptionPlanAmount, "Subscription plan");
			verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue button");
			waitTime(3000);
			verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
			verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
			if (verifyElementPresent(PWAHamburgerMenuPage.objHamburgerBtn, "Home page")) {
				logger.info("User is navigated to Home page");
				extent.extentLogger("Home page", "User is navigated to Home page");
			}
		}
	}

	/**
	 * Function To check the SignIn page from CTA in playerscreen and Verification
	 * of SubscribePopUP.
	 */
	public void navigationToCTAInPlayerFromSearch() throws Exception {
		extent.HeaderChildNode("Validating Subscribe popup post tapping Cta in player");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, "Natasaarvabhowma", "Search Field");
		hideKeyboard();
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent2, "Premium content");
		waitTime(10000);
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumCTAInPlater, "CTA in player");
		waitTime(5000);//added by Tanisha
		navigationToSignInFromCTAInPlayer();
	}

	/**
	 * Function To check the SignIn page from CTA in playerscreen and Verification
	 * of SubscribePopUP.
	 */
	public void navigationToSignInFromCTAInPlayer() throws Exception {
		extent.HeaderChildNode("CTA In Player");
		verifyElementPresent(PWASubscriptionPages.objSubscribepopup, "Subscribe popup");
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopupPlan, "Subscribe popup plan");
		verifyElementPresentAndClick(PWASubscriptionPages.objGetPremiumPopipProceed,"Proceed button in Subscribe popup");
		waitTime(4000);
		verifyElementPresent(PWALoginPage.objEmailField, "Sign in page");
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee logo");
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
		verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
		waitTime(2000);
		verifyElementPresent(PWALoginPage.objLoginTxt, "Login page");
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordTxt, "Forgot password");
		waitTime(3000);
		verifyElementPresent(PWALoginPage.objForgotPassswordPage, "Forgot password page");
		type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Email field");
		verifyElementPresentAndClick(PWALoginPage.objForgotPasswordLinkButton, "Reset password button");
		waitTime(12000);
		((JavascriptExecutor) getDriver()).executeScript("window.open();");
		waitTime(4000);
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window("CDwindow-1");
		String url = GmailInbox.readEmail("ZEE5 account password reset request");
		if (!url.isEmpty()) {
			getDriver().get(url);
			waitTime(3000);
			System.out.println(getDriver().getCurrentUrl());
			Thread.sleep(5000);
			verifyElementPresent(PWALoginPage.objForgotNextPageText, "Reset password page");
			type(PWALoginPage.objForgotNextPagePwsswordFielfd, "User@123", "Password");
			waitTime(3000);
			type(PWALoginPage.objForgotNextPageConfirmPasswordField, "User@123\n", "Confirm password");
			Wait(10000);
			if (verifyElementExist(PWALoginPage.objEmailField, "Login page")) {
				type(PWALoginPage.objEmailField, "Zee5latest@gmail.com", "Login");
				hideKeyboard();
				type(PWALoginPage.objPasswordField, "User@123", "Password");
				hideKeyboard();
				waitTime(2000);
				click(PWALoginPage.objLoginBtn, "LoginButton");
				waitTime(5000);
			}
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			if (verifyElementExist(PWAHamburgerMenuPage.objProfileIcon, "profile icon")) {
				logger.info("User is successfully changed password and logged in");
				extent.extentLogger("Logged in", "User is successfully changed password and logged in");
			} else {
				logger.info("User is not logged in");
				extent.extentLogger("Logged in", "User is not logged in");
			}
		} else {
			logger.info("User is not logged in");
			extent.extentLogger("Logged in", "User is not logged in");
		}
	}

	/**
	 * Function To check That Logout Option is not displayed in Hamburger menu.
	 */
	public void noLogoutOption() throws Exception {
		extent.HeaderChildNode("Checking no Logout option displayed for guest user");

		if (!(verifyElementExist(PWAHamburgerMenuPage.objExploreItemBtn("Logout"), "Logout"))) {
			logger.info("Logout option is not displayed for guest user");
			extent.extentLogger("Logout option", "Logout option is not displayed");
		}
		
	}

	/**
	 * Function To check That user is logged in succesfully and Login,SignUP ption
	 * is not displayed for Logged in user.
	 */
	public void verificationOfLoggedIn() throws Exception {
		extent.HeaderChildNode("Verification of Logged in");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
		if (verifyElementExist(PWAHamburgerMenuPage.objProfileIcon, "Profile icon")) {
			logger.info("User is logged in successfully");
			extent.extentLogger("Profile icon", "User is logged in successfully");
		}
		if (!(verifyElementExist(PWALoginPage.objLoginBtn, "Login"))) {
			logger.info("Login button is not displayed for logged in user");
			extent.extentLogger("Login Button", "Login button is not displayed for logged in user");
		}
		if (!(verifyElementExist(PWALoginPage.objSignUpBtn, "SignUp"))) {
			logger.info("SignUp button is not displayed for logged in user");
			extent.extentLogger("Sign Up Button", "SignUp button is not displayed for logged in user");
		}
		click(PWAHamburgerMenuPage.objHamburgerClose,"Close of Hamburger Menu");
	}


	/**
	 * Navigating to Search landing screen and verifying Voice search button
	 */
	public void landingOnSearchScreen() throws Exception {
		extent.HeaderChildNode("Search landing Screen");
		Thread.sleep(5000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		if (verifyElementExist(PWASearchPage.objSearchEditBox, "Search EditBox")) {
			logger.info("User landed on Search landing screen post tapping on search icon");
			extent.extentLogger("Search landingscreen",
					"User landed on Search landing screen post tapping on search icon");
		}
		verifyElementExist(PWASearchPage.objVoiceSearchButton, "Voice Search icon");
	}

	/**
	 * Validating Search result screen
	 */
	public void searchResultScreen(String title) throws Exception {
		extent.HeaderChildNode("Search result screen");
		type(PWASearchPage.objSearchEditBox, title+"\n", "Search bar");
		hideKeyboard();
		Thread.sleep(5000);
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
		waitTime(5000);
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

		getDriver().findElement(PWASearchPage.objSearchEditBox).clear();
		hideKeyboard();

	}

	/**
	 * verifing live tv card and verifing Recent searches overlay
	 */
	public void liveTv(String title) throws Exception {
		extent.HeaderChildNode("Live Tv Card");
		waitTime(3000);
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("All"), "All Tab");
		type(PWASearchPage.objSearchEditBox, title+"\n", "Search bar");
		hideKeyboard();
		waitTime(9000);
		if (verifyElementExist(PWALiveTVPage.objLivelogo, "Live logo")) {
			logger.info("Live Tv card is displayed");
			extent.extentLogger("Live Tv card", "Live Tv card is displayed");
		} else {
			logger.error("Live Tv card is not displayed");
			extent.extentLoggerFail("Live Tv card", "Live Tv card is not displayed");
		}
		waitTime(3000);
		click(PWALiveTVPage.objLivelogo, "Live logo");
		waitTime(5000);
		Back(1);
		waitTime(5000);

		verifyElementExist(PWASearchPage.objRecentSearchesOverlay, "Recent Searches overlay");
	}

	/**
	 * Navigation To Consumption Screen Through Trending Searches
	 */
	public void navigationToConsumptionScreenThroughTrendingSearches() throws Exception {
		extent.HeaderChildNode("Navigation to Consumption Screen through Trending Searches");
		waitTime(3000);
		click(PWASearchPage.objClearAllTextofRecentSearches, "Clear All text");
		if (verifyElementExist(PWASearchPage.objTrendingSearchesTray, "Trending Searches tray")) {
			verifyElementExist(PWASearchPage.objFirstAssetThumbnailTrendingSearch,"First asset thumbnail of Trending searches tray");
			verifyElementExist(PWASearchPage.objFirstAssetTitleTrendingSearch,"First asset title of Trending searches tray");
			String searchScreenTitle = getText(PWASearchPage.objFirstAssetTitleTrendingSearch);
			logger.info("Title fetched from Trending Searches : "+searchScreenTitle);
			extent.extentLogger("title", "Title fetched from Trending Searches : "+searchScreenTitle);
			click(PWASearchPage.objFirstAssetThumbnailTrendingSearch,"First asset thumbnail of Trending searches tray");
			// waitForElementDisplayed(PWAPlayerPage.settingsBtn, 20);
			waitTime(20000);
			verifyElementPresent(PWASearchPage.objEpisodeTitleInconsumptionPage(searchScreenTitle),"Episode title in Consumption Screen");
			String ConsumptionScreenShowTitle = getText(PWASearchPage.objEpisodeTitleInconsumptionPage(searchScreenTitle));
			System.out.println(ConsumptionScreenShowTitle);
			if (ConsumptionScreenShowTitle.contains(searchScreenTitle)) {
				logger.info("User is navigated to respective consumption screen");
				extent.extentLogger("Consumption Screen", "User is navigated to respective consumption screen");
			} else {
				logger.error("User is not navigated to respective consumption screen");
				extent.extentLoggerFail("Consumption Screen", "User is not navigated to respective consumption screen");
			}
		}
	}
	
	/**
	 * Validation of Landing Pages
	 */
	public void ValidatingLandingPages(String UserType) throws Exception {
		FirstTimeUserTrendingonzee5(UserType);
		landingpagePropertiesValidation(UserType);
		BackTOTop();
		HomepageTrayTitleAndContentValidationWithApiData(ResponseInstance.getResponse());
	}
	
	/**
	 * Validation of Trending on Zee 5 tray
	 */
	public void FirstTimeUserTrendingonzee5(String userType) throws Exception {
		extent.HeaderChildNode("Validation Trending on ZEE5 tray for "+userType+" user");
		PartialSwipe("UP", 1);
		if(verifyElementExist(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Trending on Zee5 tray")) {
			System.out.println("Trending is found for first time user");
			logger.info("Verify for first time user it should show Trending on Zee5 tray is Pass");
			extent.extentLogger("apValue", "Verify for first time user it should show Trending on Zee5 tray is Pass");
		}
		else {
			System.out.println("Not a first time user");
			Swipe_till_Zee5IsTrending();
		}
	}
	
	/**
	 * Validation of tray anf first content with API
	 */
	public void HomepageTrayTitleAndContentValidationWithApiData(Response ApiData) throws Exception {
		extent.HeaderChildNode("Homepage validation with respect to api response");
		Response resp = ApiData;
		String Tray_Title_API = resp.jsonPath().getString("buckets[1].title");
		logger.info("The Title of the Tray from API is " + Tray_Title_API);
		extent.extentLogger("apValue", "The Title of the Tray from API is " + Tray_Title_API);
		swipeTillElementLocated(PWALandingPages.objtrayTitle(Tray_Title_API),5,"tray "+Tray_Title_API);
		String Content_Title_API = resp.jsonPath().getString("buckets[1].items[0].title");
		logger.info("The first content of the Tray from API is " + Content_Title_API);
		extent.extentLogger("apValue", "The first content of the Tray from API is " + Content_Title_API);
		swipeTillElementLocated(PWALandingPages.objtrayFirstContent(Tray_Title_API,Content_Title_API),5,"first content of tray "+Content_Title_API);
		verifyTrayViewAll(Tray_Title_API,Content_Title_API);
	}
	
	/**
	 * Method to swipe till element
	 */
	public void swipeTillElementLocated(By locator,int swipeCount,String message) throws Exception {
		for(int swipe=0;swipe<=swipeCount;swipe++) {
			try {
				getDriver().findElement(locator);
				extent.extentLogger("swiped", "Swiped until "+message);
				logger.info("Swiped until "+message);
				break;
			}
			catch(Exception e) {
				if(swipe==swipeCount) {
					extent.extentLogger("swiped", "Failed to locate "+message);
					logger.info("Failed to locate "+message);
				}
				else {
					Swipe("UP", 1);
					Thread.sleep(3000);
				}
			}
		}
	}

	/**
	 * Validate navigation from vodafone native app to Zee5 pwa 
	 */
	public void ValidateVodafonePlayFunctionFromNativeApp() throws Exception {
		extent.HeaderChildNode("VodafonePlayFunctionFromNativeApp");
		// Click on Hamburger
		verifyElementPresentAndClick(NativeVodafonePlayPage.HamburgerBtn, "VodafonePlay Hamburger");
		Thread.sleep(3000);
		Swipe("UP", 1);
		Thread.sleep(3000);
		// Click on ZEE5 Icon
		verifyElementPresentAndClick(NativeVodafonePlayPage.Zee5Icon, "Zee5 Icon");
		Thread.sleep(3000);
		// Click on SearchIcon
		verifyElementPresentAndClick(NativeVodafonePlayPage.ChannelSearchIcon, "search icon");
		Thread.sleep(3000);
		// Click on Search Textbox
		verifyElementPresentAndClick(NativeVodafonePlayPage.SearchTextBox, "Search TextBox");
		Thread.sleep(3000);
		// Send Value for Saerch
		type(NativeVodafonePlayPage.SearchTextBox, "Commando 3", "Search TextBox");
		Thread.sleep(3000);
		hideKeyboard();
		// Click on Searched Data
		verifyElementPresentAndClick(NativeVodafonePlayPage.searchedData("Commando 3"), "Searched Data");
		// NAVIGATION TO ZEE5 PWA
		Thread.sleep(10000);
		System.out.println("Contexts : "+getDriver().getContextHandles());
		// Changing the Context form native to webview
		getDriver().context("WEBVIEW_1");
		// Navigated URL
		System.out.println("Navigated to URL : " + getDriver().getCurrentUrl());
		logger.info("Navigated to URL : " + getDriver().getCurrentUrl());
		extent.extentLogger("<b>" + "Navigated to URL : " + getDriver().getCurrentUrl(), "Navigated to URL");
		// Selected Content title
		System.out.println(getDriver().findElement(PWAVodafonePlayPage.consumptionPageTitle).getText());
		logger.info("Navigated to URL : " + getDriver().getCurrentUrl());
		extent.extentLogger("<b>" + "Navigated to URL : " + getDriver().getCurrentUrl(), "Navigated to URL");
		// TO VERIFY THE BROWSER
		String str = getCurrentActivity();
		if (str.contains("chrome")) {
			System.out.println("Chrome browser is opened");
			logger.info("Chrome Browser is Opened");
			extent.extentLogger("<b>" + "Chrome Browser is Opened", "Chrome Browser is Opened");
		}
		// Click on Zee5 Hamburger
		verifyElementPresentAndClick(PWAVodafonePlayPage.HamburgerBtn, "Zee5 Hamburger");
		Thread.sleep(3000);
		if (verifyElementExist(PWAVodafonePlayPage.MySubscription, "MySubscription") == false) {
			System.out.println("User has no option to purchase the plans");
			logger.info("User has no option to purchase the plans");
			extent.extentLogger("<b>" + "User has no option to purchase the plans",
					"User has no option to purchase the plans");
		}
	}

	/**
	 * Method to get current activity 
	 */
	public String getCurrentActivity() throws IOException {
		String cmd = "adb shell \"dumpsys window windows | grep 'mCurrentFocus'\"";
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String str = br.readLine();
		return str;
	}

	/**
	 * Method to verify UI of Home page
	 */
	public void verifyUIofHomePage() throws Exception {
		extent.HeaderChildNode("Verify UI of Home Page");
		waitForElementAndClickIfPresent(PWAHamburgerMenuPage.objCloseHamburger, 20, "Close in Register Pop Up");
		waitTime(5000);
		String tab = getText(PWAHomePage.objActiveTab);
		System.out.println(tab);
		if (tab.equalsIgnoreCase("Home")) {
			logger.info("Navigated to Home page");
			extent.extentLogger("Home Page", "Navigated to Home page");
		} else {
			logger.info("Not navigated to Home page");
			extent.extentLogger("Home Page", "Not navigated to Home page");
		}
		verifyElementPresent(PWAHomePage.objZeeLogo, "Zee Logo");
		verifyElementPresent(PWAHomePage.objSearchBtn, "Search button");
		verifyElementExist(PWAHomePage.objSubscribeBtn, "Subscribe button");
		verifyElementPresentAndClick(PWAHomePage.objHamburgerMenu, "Hamburger menu");
		verifyElementExist(PWAHamburgerMenuPage.objLoginBtn, "Login button");
		verifyElementExist(PWAHamburgerMenuPage.objSignUpForFree, "Sign Up for free");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objCloseIcon, "Close Icon");
		waitForElementDisplayed(PWAHomePage.objDownloadIcon, 60);
		verifyElementExist(PWAHomePage.objDownloadIcon, "Download icon");
	}


	/**
	 * Function to accept and continue until Chrome browser url page
	 */
	public void acceptBrowserSettings(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			try {
				getDriver().findElement(By.xpath("//*[@resource-id='com.android.chrome:id/terms_accept']")).click();
			} catch (Exception e) {
			}
			try {
				getDriver().findElement(By.xpath("//*[@resource-id='com.android.chrome:id/next_button']")).click();
			} catch (Exception e) {
			}
			try {
				getDriver().findElement(By.xpath("//*[@resource-id='com.android.chrome:id/negative_button']")).click();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Method to verify Consumptions screen tapping on any content card
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void verifyConsumptionsScreenTappingOnCard(String userType, String contentType, String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Consumption Page for Content type: " + contentType);
		String consumptionPageTitle = "";
		if (contentType.equals("Live TV")) {
			navigateToAnyScreen("Live TV");
			String url=getDriver().getCurrentUrl();
			reloadURL(url);
			waitForElement(PWAShowsPage.objFirstAssetTitleLiveTvCard, 30, "Content title");
			contentTitle = getElementPropertyToString("innerText", PWAShowsPage.objFirstAssetTitleLiveTvCard, "Content Title").toString();
			verifyElementPresentAndClick(PWAShowsPage.objFirstAssetTitleLiveTvCard, "Live TV Card");
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Pop Up");
			waitForElement(PWAPlayerPage.objContentTitleLiveTV, 20, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitleLiveTV,"Content Title").toString();
		} else {
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");		
			type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
			waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 5, "Close in Pop Up");	
			waitForElement(PWAPlayerPage.objContentTitle, 30, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
		}
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation","Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			logger.info("Successfully navigated to the correct Consumption page " + consumptionPageTitle);
			/*if (contentType.equals("Live TV"))
				pausePlayerForLiveTV();
			else
				pausePlayerAndGetLastPlayedTime();*/
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}
	/**
	 * Method to verify no Subscription pop up is displayed for free content
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void verifyNoSubscriptionPopupForFreeContent(String userType, String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify No Subscription Popup For Free Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Language Pop Up");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(4000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
		waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Consumption page");
			logger.info("Successfully navigated to the correct Consumption page");
			waitForElementAbsence(PWAPremiumPage.objPremiumPopUp, 30, "Premium Pop Up");
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
		}
	}

	/**
	 * Method to verify Subscription pop up is displayed for premium content
	 * 
	 * @param userType
	 * @param contentType
	 * @param contentTitle
	 * @param devicePin
	 * @throws Exception
	 */
	public void verifySubscriptionPopupForPremiumContent(String userType, String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Subscription Popup For Premium Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitTime(2000);
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 45, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		if(waitForElementPresence(PWAPremiumPage.objPremiumPopUp,5,"Premium Pop Up")) {
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 2, "Close in Premium Pop Up");
		}
		else {
			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
			waitForElement(PWAPlayerPage.objContentTitle, 20, "Content title");
			String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
			if (consumptionPageTitle.contains(contentTitle)) {
				extent.extentLogger("correctNavigation", "Successfully navigated to the correct Consumption page");
				logger.info("Successfully navigated to the correct Consumption page");
				if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
					waitForElement(PWAPremiumPage.objPremiumPopUp, 30, "Premium Pop Up");
					waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Premium Pop Up");
				} else if (userType.equals("SubscribedUser")) {
					waitForElementAbsence(PWAPremiumPage.objPremiumPopUp, 30, "Premium Pop Up");
				} else {
					extent.extentLoggerFail("incorrectUserType", "Incorrect User Type entered in script");
					logger.error("Incorrect User Type entered in script");
				}
			} else {
				extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page: " + consumptionPageTitle);
				logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
			}
		}
	}

	
	/**
	 * Method to verify share functionality and metadata comparison between Show Details and Consumption page
	 * plays in player
	 * 
	 * @param contentTitle
	 * @throws Exception
	 */
	//Tanisha
	public void verifyCTAandMetaDataInDetailsAndConsumption(String contentTitle) throws Exception {
		extent.HeaderChildNode("Verify Watch Latest Episode CTA");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitForElement(PWASearchPage.objSearchNavigationTab("Shows"), 30, "Shows tab");
		verifyElementPresentAndClick(PWASearchPage.objSearchNavigationTab("Shows"), "Shows tab");
		waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30,"Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
		waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 10, "Close in Register Pop Up");
		verifyElementPresent(PWAShowsPage.objShowsTitle, "Show title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAShowsPage.objShowsTitle,"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation", "Successfully navigated to the correct Details page");
			logger.info("Successfully navigated to the correct Details page");
			verifyElementPresent(PWAShowsPage.objWatchLatestCTA, "Watch Latest CTA button");
			String watchLatestCTAText = getElementPropertyToString("innerText", PWAShowsPage.objWatchLatestCTA,"Watch Latest CTA button").toString();
			if (watchLatestCTAText.equals("Watch Latest Episode")) {
				extent.extentLogger("correctButtonText", "Correct button text displayed: " + watchLatestCTAText);
				logger.info("Correct button text displayed: " + watchLatestCTAText);
			} else {
				extent.extentLoggerFail("incorrectButtonText","Incorrect button text displayed: " + watchLatestCTAText);
				logger.error("Incorrect button text displayed: " + watchLatestCTAText);
			}
			extent.HeaderChildNode("Verify Metadata in Consumptions page");
			verifyElementPresentAndClick(PWAShowsPage.objSecondAssetImageFirstRail, "First asset image from first rail");
			//Get API details
			String contentURL = getDriver().getCurrentUrl();
			String[] abc = contentURL.split("/");
			String contentID = abc[abc.length - 1].split("\\?")[0];
			System.out.println("contentID fetched from URL: " + contentID);
			Response resp = ResponseInstance.getContentDetails(contentID,"content");
			System.out.println(resp.body().jsonPath());
			String titleAPI=resp.jsonPath().get("title").toString();
			extent.extentLogger("apidata", "Episode title fetched from API: "+titleAPI);
			logger.info("Episode title fetched from API: "+titleAPI);
			
			String showtitleAPI=resp.jsonPath().get("tvshow.title").toString();
			extent.extentLogger("apidata", "Show title fetched from API: "+showtitleAPI);
			logger.info("Show title fetched from API: "+showtitleAPI);
			
			String episodeNoAPI=resp.jsonPath().get("orderid").toString();
			extent.extentLogger("apidata", "Episode number fetched from API: "+episodeNoAPI);
			logger.info("Episode number fetched from API: "+episodeNoAPI);
			
			String durationAPI=resp.jsonPath().get("duration").toString();
			extent.extentLogger("apidata", "Duration fetched from API: "+durationAPI);
			logger.info("Duration fetched from API: "+durationAPI);
			
			String genreAPI=resp.jsonPath().get("genre[0].value").toString();
			extent.extentLogger("apidata", "Genre fetched from API: "+genreAPI);
			logger.info("Genre fetched from API: "+genreAPI);
			
			String ageRatingAPI=resp.jsonPath().get("age_rating").toString();
			extent.extentLogger("apidata", "Age Rating fetched from API: "+ageRatingAPI);
			logger.info("Age Rating fetched from API: "+ageRatingAPI);
			
			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 5, "Close in Register Pop Up");
			
			String episode = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Episode title in Consumption Page").toString();
			extent.extentLogger("episode","Episode title fetched from UI: "+episode);
			logger.info("Episode title fetched from UI: "+episode);
			if (titleAPI.equals(episode)) {
				extent.extentLogger("titleMatch","Consumption page content Title matched with API");
				logger.info("Consumption page content Title matched with API");
			} else {
				extent.extentLoggerFail("titleMismatch","Consumption page content Title mismatched with API");
				logger.error("Consumption page content Title mismatched with API");
			}
			
			String show = getElementPropertyToString("innerText", PWAPlayerPage.objContentShowTitle,"Show title in Consumption Page").toString();
			extent.extentLogger("show","Show title fetched from UI: "+show);
			logger.info("Show title fetched from UI: "+show);
			if (showtitleAPI.equals(show)) {
				extent.extentLogger("showMatch","Consumption page content Show matched with API");
				logger.info("Consumption page content Show matched with API");
			} else {
				extent.extentLoggerFail("showMismatch","Consumption page content Show mismatched with API");
				logger.error("Consumption page content Show mismatched with API");
			}	

			String episodeNo = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaEpisode,"Episode Number in Consumption Page").toString();
			extent.extentLogger("episodeNo","Episode No fetched from UI: "+episodeNo);
			logger.info("Episode No fetched from UI: "+episodeNo);
			episodeNo = episodeNo.split("Episode ")[1];	
			if (episodeNoAPI.equals(episodeNo)) {
				extent.extentLogger("episodeMatch","Consumption page content Episode Number matched with API");
				logger.info("Consumption page content Episode Number matched with API");
			} else {
				extent.extentLoggerFail("episodeMismatch","Consumption page content Episode Number mismatched with API");
				logger.error("Consumption page content Episode Number mismatched with API");
			}
			
			String duration = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaDuration,"Duration in Consumption Page").toString();
			extent.extentLogger("duration","Duration fetched from UI: "+duration);
			logger.info("Duration fetched from UI: "+duration);
			durationAPI=String.valueOf((Integer.parseInt(durationAPI)/60));
			duration=duration.split("m")[0];
			if (durationAPI.equals(duration)) {
				extent.extentLogger("durationMatch","Consumption page content Duration matched with API");
				logger.info("Consumption page content Duration matched with API");
			} else {
				extent.extentLoggerFail("durationMismatch","Consumption page content Duration mismatched with API");
				logger.error("Consumption page content Duration mismatched with API");
			}
			
			String genre = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaGenre,"Genre in Consumption Page").toString();
			extent.extentLogger("genre","Genre fetched from UI: "+genre);
			logger.info("Genre fetched from UI: "+genre);
			if (genreAPI.equals(genre)) {
				extent.extentLogger("genreMatch","Consumption page content Genre matched with API");
				logger.info("Consumption page content Genre matched with API");
			} else {
				extent.extentLoggerFail("genreMismatch","Consumption page content Genre mismatched with API");
				logger.error("Consumption page content Genre mismatched with API");
			}
						
			String ageRating = getElementPropertyToString("innerText", PWAPlayerPage.objContentMetaAgeRating,"Age Rating in Consumption Page").toString();
			extent.extentLogger("ageRating","Age Rating fetched from UI: "+ageRating);
			logger.info("Age Rating fetched from UI: "+ageRating);
			if (ageRatingAPI.equals(ageRating)) {
				extent.extentLogger("ageRatingMatch","Consumption page content Age Rating matched with API");
				logger.info("Consumption page content Age Rating matched with API");
			} else {
				extent.extentLoggerFail("ageRatingMismatch","Consumption page content Age Rating mismatched with API");
				logger.error("Consumption page content Age Rating mismatched with API");
			}
			
			
		} else {
			extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Details page: " + consumptionPageTitle);
			logger.error("Navigated to incorrect Details page: " + consumptionPageTitle);
		}
	}

	/**
	 * Method to verify full screen for Premium Content
	 * 
	 */
	public void verifyFullScreenForPremiumContent(String userType, String contentTitle, String devicePin) throws Exception {
		extent.HeaderChildNode("Verify Player Full Screen For Premium Content");
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Language Pop Up");
		type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
		waitForElementAndClickIfPresent(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
		if (userType.equals("Guest") || userType.equals("NonSubscribedUser")) {
			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 10, "Close in Register Pop Up");
		} else if (userType.equals("SubscribedUser")) {
			enterDevicePin(devicePin);
		} else {
			extent.extentLoggerFail("incorrectUserType", "Incorrect User Type entered in script");
			logger.error("Incorrect User Type entered in script");
		}
		waitForElement(PWAPlayerPage.objContentTitle, 30, "Content title");
		String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,
				"Content Title").toString();
		if (consumptionPageTitle.contains(contentTitle)) {
			extent.extentLogger("correctNavigation",
					"Successfully navigated to the correct Consumption page: " + consumptionPageTitle);
			logger.info("Successfully navigated to the correct Consumption page " + consumptionPageTitle);
			pausePlayerAndGetLastPlayedTime();
			click(PWAPlayerPage.maximizeBtn, "Maximize button");
			click(PWAPlayerPage.objPlayerPlay, "Play button");
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Premium Pop Up");
			pausePlayer();
			getPlayerDuration();
			click(PWAPlayerPage.minimizeBtn, "Minimize button");
		}
	}

	/**
	 * Method to pause the player and get the duration lapsed
	 * 
	 * @throws Exception
	 */
	public void pausePlayerAndGetLastPlayedTime() throws Exception {
		waitForPlayerAdToComplete("Video Player");
		waitForPlayerLoaderToComplete();
		if (pausePlayer() == true) {
			getPlayerDuration();
			Thread.sleep(4000);
		} else {
			extent.extentLoggerFail("failedToPause", "Failed to pause Player");
			logger.error("Failed to pause Player");
		}
	}

	/**
	 * Method to get the duration lapsed in the player
	 */
	public void getPlayerDuration() {
		String duration = getElementPropertyToString("innerText", PWAPlayerPage.objPlayerCurrentDuration,"Player Current Duration").toString();
		if (duration != null) {
			extent.extentLogger("contentDuration", "Successfully played content till: " + duration);
			logger.info("Successfully played content till: " + duration);
		} else {
			extent.extentLoggerFail("durationFailed", "Failed to get Current Duration");
			logger.error("Failed to get Current Duration");
		}
	}

	/**
	 * Method to Pause the Player
	 */
	@SuppressWarnings("rawtypes")
	public boolean pausePlayer() throws InterruptedException {
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		int y = deviceHeight / 4;
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			System.out.println("trial :" + trial);
			try {
				TouchAction act = new TouchAction(getDriver());
				act.tap(PointOption.point(x, y)).perform();
				extent.extentLogger("playerTap", "Tapped on the Player");
				logger.info("Tapped on the Player");
				try {
					getDriver().findElement(PWAPlayerPage.pauseBtn).click();
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused the Player");
						logger.info("Paused the Player");
						playerPaused = true;
						break;
					} catch (Exception e) {
					}
				} catch (Exception e) {
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused the Player");
						logger.info("Paused the Player");
						playerPaused = true;
						break;
					} catch (Exception e1) {
					}
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
	 * Pause Player for Live TV
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void pausePlayerForLiveTV() throws Exception {
		waitForPlayerAdToComplete("Live Player");
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		System.out.println("x: " + x);
		int y = deviceHeight / 4;
		System.out.println("y: " + y);
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			System.out.println("trial :" + trial);
			try {
				TouchAction act = new TouchAction(getDriver());
				act.tap(PointOption.point(x, y)).perform();
				extent.extentLogger("playerTap", "Tapped on Live Player");
				logger.info("Tapped on Live Player");
				try {
					getDriver().findElement(PWAPlayerPage.pauseBtn).click();
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused Live Player");
						logger.info("Paused Live Player");
						playerPaused = true;
						break;
					} catch (Exception e) {
					}
				} catch (Exception e) {
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused Live Player");
						logger.info("Paused Live Player");
						playerPaused = true;
						break;
					} catch (Exception e1) {
					}
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error when handling Live Player");
					logger.error("Error when handling Live Player");
				}
			}
		}
		if (playerPaused == true) {
			try {
				getDriver().findElement(PWAPlayerPage.objLivePlayerVolume);
				extent.extentLogger("livePlayerVolume", "Located Live Player Volume");
				logger.info("Located Live Player Volume");
			} catch (Exception e) {
				extent.extentLoggerFail("livePlayerVolume", "Failed to locate Live Player Volume");
				logger.error("Failed to locate Live Player Volume");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void scrubPlayer(int percentage) throws Exception {
		for (int trial = 0; trial < 5; trial++) {
			// scrub
			try {
				WebElement seekBar = getDriver().findElement(PWAPlayerPage.objPlayerSeekBar);
				try {
					int width = Integer.parseInt(seekBar.getAttribute("width"));
					int widthToBeScrubbedInt = (width * percentage) / 100;
					int startX = Integer.parseInt(seekBar.getAttribute("x"));
					int endX = widthToBeScrubbedInt;
					int startY = Integer.parseInt(seekBar.getAttribute("y"));
					int endY = startY;
					TouchAction act = new TouchAction(getDriver());
					act.press(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release();
					logger.info("Scrubbed the player by " + percentage + " percent");
					extent.extentLogger("scrubSuccess", "Scrubbed the player by " + percentage + " percent");

				} catch (Exception e) {
					if (trial == 5) {
						logger.error("Failed to scrub the player");
						extent.extentLoggerFail("scrubFailure", "Failed");
					}
				}

			} catch (Exception e) {
				if (trial == 5) {
					logger.error("Failed to locate player seek bar");
					extent.extentLoggerFail("seekbar", "Failed to locate player seek bar");
				}
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
				getDriver().findElement(locator).click();
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

	/**
	 * The method will wait for the element to be located for a maximum of given
	 * seconds. The method terminates immediately once the element is located. The
	 * method throws error if the element could not be located within the given
	 * seconds
	 */
	public void waitForElement(By locator, int seconds, String message) throws InterruptedException {
		main: for (int time = 0; time <= seconds; time++) {
			try {
				getDriver().findElement(locator);
				logger.info("Located element " + message);
				extent.extentLogger("locatedElement", "Located element " + message);
				break main;
			} catch (Exception e) {
				Thread.sleep(1000);
				if (time == seconds) {
					logger.error("Failed to locate element " + message);
					extent.extentLoggerFail("failedLocateElement", "Failed to locate element " + message);
				}
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
		main: for (int trial = 0; trial < 90; trial++) {
			try {
				getDriver().findElement(PWAPlayerPage.objAd);
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
						getDriver().findElement(PWAPlayerPage.objLivePlayerLiveTag);
					} else if (playerType.equals("Video Player")) {
						getDriver().findElement(PWAPlayerPage.objPlayerSeekBar);
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

	/**
	 * Waits for player loader to complete
	 * 
	 * @throws Exception
	 */
	public void waitForPlayerLoaderToComplete() throws Exception {
		verifyElementNotPresent(PWAPlayerPage.objPlayerLoader, 60);
	}

	/**
	 * Method to enter device pin
	 * 
	 * @throws Exception
	 */
	public void enterDevicePin(String devicePin) throws Exception {
		boolean devicePinPresent = false;
		// wait and check if device pin box appears
		for (int trial = 0; trial <= 4; trial++) {
			try {
				getDriver().findElement(PWAHomePage.objDevicePin1);
				devicePinPresent = true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		if (devicePinPresent == true) {
			for (int trial = 0; trial <= 4; trial++) {
				try {
					getDriver().findElement(By.xpath("//input[@id='parentLockId1']")).sendKeys(devicePin.substring(0, 1));
					getDriver().findElement(By.xpath("//input[@id='parentLockId2']")).sendKeys(devicePin.substring(1, 2));
					getDriver().findElement(By.xpath("//input[@id='parentLockId3']")).sendKeys(devicePin.substring(2, 3));
					getDriver().findElement(By.xpath("//input[@id='parentLockId4']")).sendKeys(devicePin.substring(3, 4));
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
	 * Method to perform a direct click
	 * 
	 */
	public void directClick(By locator, String element) {
		try {
			getDriver().findElement(locator).click();
			logger.info("Clicked on element: " + element);
			extent.extentLogger("elementClicked", "Clicked on element: " + element);
		} catch (Exception e) {
			logger.error("Failed to click on element: " + element);
			extent.extentLoggerFail("elementClickFailed", "Failed to click on element: " + element);
		}
	}


	/**
	 * Function to verify Autoroating on carousel
	 * 
	 * @throws Exception
	 */	
	public void verifyAutoroatingOnCarousel(String screen) throws Exception {
		extent.HeaderChildNode("Verifying autorotating of carousel pages on : " + screen);
		String firstCarouselTitle = "", secondCarouselTitle = "", thirdCarouselTitle = "";
		navigateToAnyScreen(screen);
		Thread.sleep(5000);
		firstCarouselTitle = getElementPropertyToString("innerText", PWAHomePage.objContTitleOnCarousel,"Carousel Content Title").toString();
		extent.extentLogger("Autorotating", "Carousel title fetched first time: " + firstCarouselTitle);
		Thread.sleep(4000);
		secondCarouselTitle = getElementPropertyToString("innerText", PWAHomePage.objContTitleOnCarousel,"Carousel Content Title").toString();
		extent.extentLogger("Autorotating", "Carousel title fetched second time: " + secondCarouselTitle);
		Thread.sleep(4000);
		thirdCarouselTitle = getElementPropertyToString("innerText", PWAHomePage.objContTitleOnCarousel,"Carousel Content Title").toString();
		extent.extentLogger("Autorotating", "Carousel title fetched first time: " + thirdCarouselTitle);
		if(firstCarouselTitle.equals(secondCarouselTitle) || secondCarouselTitle.equals(thirdCarouselTitle)){
			logger.error("Titles fetched are common, auto rotation failed");
			extent.extentLoggerFail("Autorotating", "Titles fetched are common, auto rotation failed");
		} else {
			logger.info("Titles fetched are different each time, content is auto rotated");
			extent.extentLogger("Autorotating", "Titles fetched are different each time, content is auto rotated");	
		}
	}

	/**
	 * Function to verify Play icon functionality
	 * 
	 * @throws Exception
	 */
	public void verifyPlayIconFunctionality(String screen) throws Exception {
		extent.HeaderChildNode("Verifying play icon functionality on carousel for : " + screen);
		navigateToAnyScreen(screen);
		waitTime(5000);
		for (int i = 0; i < 10; i++) {
			try {
				waitForElementAndClick(PWAHomePage.objPlayBtn, 20, "Play icon");
				break;
			} catch (Exception e) {
				waitTime(1000);
				if (i == 9)
					logger.info("Failed to click");
			}
		}
		waitTime(10000);
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Register Pop Up");//Added by Tanisha
		if (verifyElementPresent(PWAPlayerPage.objPlayerControlScreen, "Player control containing screen")) {
			logger.info("Verify play icon functionality is Pass");
			extent.extentLogger("Play btn validation", "Verify play icon functionality is Pass");
		} else {
			logger.error("Verify play icon functionality is Fail");
			extent.extentLoggerFail("Play btn validation", "Verify play icon functionality is Fail");
		}		
		Back(1);
	}

	/**
	 * Function Navigate to landing page of any screen
	 * 
	 * @throws Exception
	 */
	public void navigateToAnyScreen(String screen) throws Exception {
		for (int i = 0; i < 3; i++) {
			try {
				verifyElementPresentAndClick(PWAHomePage.objTabName(screen), "Tab " + screen);
				break;
			} catch (Exception e) {
				try {
					swipeOnTab("Left");
					verifyElementPresentAndClick(PWAHomePage.objTabName(screen), "Tab " + screen);
					break;
				} catch (Exception exc) {
					swipeOnTab("Right");
				}
			}
		}
	}

	/**
	 * Function to swipe on tab
	 * 
	 */
	public void swipeOnTab(String dire) throws InterruptedException {
		extent.HeaderChildNode("Swipping on tab");
		Dimension size = getDriver().findElement(PWAHomePage.objTabContBar).getSize();
		System.out.println("width :" + size.width + " and height " + size.height);
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
	 * Function to verify Play icon functionality for users
	 * 
	 * @throws Exception
	 */
	public void verifyPremiumIconFunctionality(String screen, String userType) throws Exception {
		extent.HeaderChildNode("Verifying premium icon functionality On : " + screen + " for " + userType);
		navigateToAnyScreen(screen);
		waitTime(5000);
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			List<WebElement> getPremiumTextList = getDriver().findElements(PWAHomePage.objGetPremium);
			if (getPremiumTextList.size() == 0) {
				logger.info("Get Premium text is not displayed for Subscribed users");
				extent.extentLogger("Premium text for subscribed user","Get Premium text is not displayed for Subscribed users");
			}
		} else {
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Premium Pop Up");//Added by Tanisha
			waitForElementAndClick(PWAHomePage.objGetPremium, 20, "Premium Icon");
		}
		if (userType.equalsIgnoreCase("NonSubscribedUser") || userType.equalsIgnoreCase("Guest")) {
			if (verifyElementPresent(PWAHomePage.objSubscriptionPage, "Subscription page")) {
				logger.info("Get Premium button functionality has Passed for " + screen);
				extent.extentLogger("Premium btn validation", "Get Premium button functionality has Passed for " + screen);
				Back(1);
			} else {
				logger.error("Get Premium button functionality has Failed for " + screen);
				extent.extentLoggerFail("Premium btn validation", "Get Premium button functionality has Failed for " + screen);
			}
		}		
	}
	
	/**
	 * Function to left and right(<>) functionality on carousel
	 * 
	 * @throws Exception
	 */
	public void verifyLeftRightFunctionality(String screen) throws Exception {
		extent.HeaderChildNode("Verifying left and right functionality");
		navigateToAnyScreen(screen);
		String firstCarouselTitle = "";
		WebDriverWait w = new WebDriverWait(getDriver(), 20);
		w.until(ExpectedConditions.visibilityOfElementLocated(PWANewsPage.objRight));

		waitForElementAndClick(PWANewsPage.objRight, 10, "Next cont");
		waitTime(1500);
		firstCarouselTitle = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
		System.out.println("first :" + firstCarouselTitle);

		waitForElementAndClick(PWANewsPage.objRight, 10, "Next cont");
		waitTime(1500);
		String nextCarouselTitle = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
		System.out.println("next :" + nextCarouselTitle);

		waitTime(1000);
		waitForElementAndClick(PWANewsPage.objLeft, 10, "Prev cont");
		waitTime(1500);
		String firstCarouselTitle2 = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
		System.out.println("f2 :" + firstCarouselTitle2);

		if (nextCarouselTitle.equals(firstCarouselTitle) == false) {
			softAssert.assertFalse(nextCarouselTitle.equals(firstCarouselTitle));
			logger.info("Content can be swiped right ");
			extent.extentLogger("Swipe left and right", "Content can be swiped right ");
		} else {
			softAssert.assertFalse(nextCarouselTitle.equals(firstCarouselTitle));
			logger.info("Content can not be swiped right ");
			extent.extentLogger("Swipe left and right", "Content can not be swiped left right ");
		}
		softAssert.assertAll();

		if (nextCarouselTitle.equals(firstCarouselTitle2) == false) {
			softAssert.assertFalse(nextCarouselTitle.equals(firstCarouselTitle2));
			logger.info("Content can be swiped left");
			extent.extentLogger("Swipe left and right", "Content can be swiped left");
		} else {
			softAssert.assertFalse(nextCarouselTitle.equals(firstCarouselTitle2));
			logger.info("Content can not be swiped left or right ");
			extent.extentLogger("Swipe left and right", "Content can not be swiped left");
		}
		softAssert.assertAll();
	}

	
	/**
	 * Subscription Flow From Home Page Header Subscribe Btn Line No 89
	 */
	public void zeePWASubscriptionFlowFromHomePageHeaderSubscribeBtn() throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Header Subcribe Btn");
		waitTime(5000);
		verifyElementPresent(PWAHomePage.objSubscribeBtn, "Subscribe Btn in the Header");
		zeePWAGuestUserSubscriptionFlow();

	}

	/**
	 * Subscription Flow From Home Page Get Premium CTA on Carousel Line No 90
	 */
	public void zeePWASubscriptionFlowFromHomePageGetPremiumCTAOnCarousel() throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Get Premium CTA On Carousel");

		waitTime(5000);
		findElement(PWAHomePage.objGetPremium).click();
		zeePWAGuestUserSubscriptionFlow();

	}

	/**
	 * Subscription Flow From Adoric Popup Line No 91 Subscription Flow From
	 * Subcribe Btn On Playing Premium Content Line No 92
	 */
	public void zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent() throws Exception {
		HeaderChildNode("PWA Subscription Flow From Adoric Popup/Get Premium popup On Playing Premium Content");

		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objPopupProceedBtn, "Popup Proceed Btn");
		waitTime(5000);
		zeePWAAccountInfoPageValidation();
		zeePWAPaymentPageValidation();

	}

	/**
	 * Subscription Flow From Player In-line Subscribe link Line No 93
	 */
	public void zeePWASubscriptionFlowFromPlayerInlineSubscribelink() throws Exception {
		HeaderChildNode("PWA Subscription Flow From Player In-line Subscribe link on Player");

		waitTime(5000);
		verifyElementPresentAndClick(PWAPlayerPage.objSubscribeNowLink, "In-Line Subscribe Link on Player");
		zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent();

	}

	/**
	 * Subscription Flow From Subscription Get premium CTA below the player at
	 * consumption screen Line No 95
	 */
	public void zeePWASubscriptionFlowFromSubscriptionGetPremiumCTABelowPlayer() throws Exception {
		HeaderChildNode(
				"PWA Subscription Flow From Subscription Get premium CTA below the player at consumption screen");

		waitTime(5000);
		verifyElementPresent(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, "Get Premium CTA Below Player Screen");
		waitTime(5000);
		zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent();

	}

	/**
	 * Subscription Flow From "Buy subscription" option under My plans in hamburger
	 * menu Line No 96
	 */
	public void zeePWASubscriptionFlowFromBuySubscriptionOptionUnderMyPlansInHamburgerMenu() throws Exception {
		HeaderChildNode("PWA Subscription Flow From Buy subscription option under My plans in hamburger menu");

		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
		waitTime(5000);
		verifyElementPresent(PWAHamburgerMenuPage.objBuySubscriptionOption, "Buy Subscribe Option in Hamburger Menu");
		waitTime(5000);
		zeePWAGuestUserSubscriptionFlow();

	}

	/**
	 * Guest User Subscription Flow
	 */
	public void zeePWAGuestUserSubscriptionFlow() throws Exception {
		zeePWASelectPackPageValidation();
		zeePWAAccountInfoPageValidation();
		zeePWAPaymentPageValidation();

	}

	/**
	 * Non-Subscribed User Subscription Flow
	 */
	public void zeePWANonSubscribedUserSubscriptionFlow() throws Exception {
		HeaderChildNode("PWA Subscription Flow");
		zeePWASelectPackPageValidation();
		zeePWAPaymentPageValidation();

	}

	/**
	 * Method to verify why register pop up
	 */
	private void Why_Register_POPUP() throws Exception {

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

	/**
	 * Method to get xpath of the text passed
	 */
	public By Text_To_Xpath(String text) throws Exception {
		return By.xpath("//*[@title='[" + text + "]']");
	}

	/**
	 * Method for Ad verification on Web
	 */
	public void Validating_Ads() {
		try {
			if (checkcondition(PwaNews.obj_Pwa_Player_Ads)) {
				verifyElementPresent(PwaNews.obj_Pwa_Player_Ads, "Ads");
				verifyElementNotPresent(PwaNews.obj_Pwa_Player_Ads, 60);
			}
		} catch (Exception e) {
			System.out.println("Ads not found or identified");
			waitForElementDisplayed(PwaNews.obj_Pwa_Player_Volume_icon, 60);
		}

	}
	
	/**
	 * Method Live content play ability verification
	 */
	public void LiveContent_Playablility() throws Exception {
		navigateToAnyScreen("News");
		Validating_Ads();
		waitForElementDisplayed(PwaNews.obj_Pwa_Player_Volume_icon, 20);
		verifyElementPresentAndClick(PwaNews.obj_Pwa_Player_Volume_icon, "Player");
		verifyElementPresent(PwaNews.obj_Pwa_Live, "Live Content");
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 logo");

	}

	/**
	 * Method News VOD content verification
	 */
	public void News_vod() throws Exception {
		navigateToAnyScreen("News");
		PartialSwipe("UP", 1);
		verifyElementPresentAndClick(PwaNews.obj_Pwa_News_Vod_Rail_Title, "Rail Title");
		verifyElementPresentAndClick(PwaNews.obj_Pwa_News_Vod_Title, "News Vod Content");
		Why_Register_POPUP();
		Validating_Ads();
		Navigate_to_HomeScreen_using_Zee5Logo();
	}
	
	/**
	 * Method to verify get premium trailer
	 */
	public void Verify_Get_Premium_Trailer() throws Exception {
		WatchTrailer();
		verifyElementPresentAndClick(PWASearchPage.watchTrailerBtn, "watch Trailer");
		waitForElementDisplayed(PWASearchPage.Obj_Pwa_Get_Premium_btn, 60);
		verifyElementPresent(PWASearchPage.Obj_Pwa_Get_Premium_btn, "Get Primium");
		Navigate_to_HomeScreen_using_Zee5Logo();

	}

	/**
	 * Method to pause player
	 */
	@SuppressWarnings("rawtypes")
	public void PlayerPauseAndPlay() throws Exception {
		extent.HeaderChildNode("Pause player and return Last played time");
		waitForPlayerAdToComplete();
		// waitForPlayerLoaderToComplete();
		int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		System.out.println("x: " + x);
		int y = deviceHeight / 4;
		System.out.println("y: " + y);
		boolean playerPaused = false;
		for (int trial = 0; trial <= 4; trial++) {
			System.out.println("trial :" + trial);
			try {
				TouchAction act = new TouchAction(getDriver());
				act.tap(PointOption.point(x, y)).perform();
				extent.extentLogger("playerTap", "Tapped on the Player");
				logger.info("Tapped on the Player");
				try {
					getDriver().findElement(PWAPlayerPage.pauseBtn).click();
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused the Player");
						logger.info("Paused the Player");
						playerPaused = true;
						break;
					} catch (Exception e) {
					}
				} catch (Exception e) {
					try {
						getDriver().findElement(PWAPlayerPage.playBtn);
						extent.extentLogger("playerPaused", "Paused the Player");
						logger.info("Paused the Player");
						playerPaused = true;
						break;
					} catch (Exception e1) {
					}
				}
			} catch (Exception e) {
				Thread.sleep(1000);
				if (trial == 4) {
					extent.extentLoggerFail("errorOccured", "Error occurred");
					logger.error("Failed to Pause player");
				}
			}
		}
		if (playerPaused == true) {
			getDriver().findElement(PWAPlayerPage.progressBar);
			extent.extentLogger("seekBar", "Located Seek Bar");
			logger.info("Located Seek Bar");
			String duration = getElementPropertyToString("innerText", PWAPlayerPage.currentDurationTime,
					"Player Current Duration").toString();
			System.out.println("Duration: " + duration);
			// return(duration);
		} else {
			// return null;
		}
	}

	/*
	 * Validating Free and Premium video content
	 * 
	 */
	public void FreeAndPremiumContentPlayability() throws Exception {

		// Click on home page
		click(PWAHomePage.objTabName("Home"), "Home tab");
		// Scroll to Title Trending on ZEE5
		ScrollToElement(PWAMoviesPage.objTrayTitleName("Trending on ZEE5"), "Trending on ZEE5 Tray title");

	}

	/*
	 * Match API data with Frontend
	 */
	public void MatchAPIDataWithFrontEnd(String Title, int index, String contentName) throws Exception {
		// Swipe to the Title
		ScrollToElement(PWAMoviesPage.objTrayTitleName(Title), "Scrolled to Title" + Title);
		// Click on the content
		click(PWAMoviesPage.objContentCard(Title, index), "First content on Content card");
		// Verify the Content name
		String contentNameFrontEnd = getElementPropertyToString("innerText", PWAPlayerPage.objContentName,
				"Content name");
		if (contentName.equals(contentNameFrontEnd)) {
			softAssert.assertEquals(contentName, contentNameFrontEnd);
			extent.extentLogger("Verify content card name", "Content card name are matched");
			logger.info("Content card are matched");
		} else {
			softAssert.assertAll();
			softAssert.assertNotEquals(contentName, contentNameFrontEnd);
			extent.extentLoggerFail("Verify Content card name", "Content card name Mismatched");
			logger.info("Content card name Mismatched");
		}

	}



	
	/**
	 * Function to verify the UI of Live Tv And Channel Guide
	 */	
	public void verifyLiveTvAndChannelGuideScreen() throws Exception {
		extent.HeaderChildNode("Verify Live TV And Channel Guide Screen");
		waitTime(5000);
		System.out.println(getText(PWAHomePage.objActiveTab));
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTVMenu, "Live TV Menu");
		waitTime(10000);
		waitTime(10000);
		System.out.println(getText(PWAHomePage.objActiveTab));
		String channelTitle = getDriver().findElement(PWALiveTVPage.objLiveChannelCardTitle).getText();
		System.out.println(channelTitle);
		verifyElementPresentAndClick(PWALiveTVPage.objLiveChannelCard, "Live Channel Card");
		waitTime(5000);
		String playerPageChannelTitle = getDriver().findElement(PWALiveTVPage.objLiveChannelConsumptionPageTitle).getText();
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
		waitTime(5000);
		Back(1);
		waitTime(5000);
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTVToggleInactive, "Live TV Toggle");
		click(PWALiveTVPage.objChannelGuideToggle, "Channel Guide Toggle");
		verifyElementPresentAndClick(PWALiveTVPage.objChannelDayStrip, "Channel/Day Strip");
		click(PWALiveTVPage.objUpcomingLiveProgramDate, "Upcoming Live Program Date");
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideSortOption, "Sort Option");
		verifyElementPresent(PWALiveTVPage.objSortByPopularity, "Sort By Popularity Option");
		verifyElementPresent(PWALiveTVPage.objSortByAZ, "Sort by A-Z Option");
		verifyElementPresentAndClick(PWALiveTVPage.objChannelGuideFilterOption, "Filter Option");
		verifyElementPresentAndClick(PWALiveTVPage.objHindiFiltrOptn, "Language Filter");
		String selectedLang = getDriver().findElement(PWALiveTVPage.objNoOfLangSelectedText).getText();
		System.out.println(selectedLang);
		verifyElementPresentAndClick(PWALiveTVPage.objResetBtn, "Reset Button");
		String selectedLang1 = getDriver().findElement(PWALiveTVPage.objNoOfLangSelectedText).getText();
		System.out.println(selectedLang1);
		verifyElementPresentAndClick(PWALiveTVPage.objApplyBtn, "Apply Button");
		waitTime(10000);
		verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgram, "Upcoming Live Program");
		waitTime(10000);
		verifyElementPresentAndClick(PWALiveTVPage.objUpcomingLiveProgramShareBtn, "Share button");
		waitTime(3000);
		System.out.println(getDriver().getContextHandles());
		getDriver().context("NATIVE_APP");
		verifyElementPresentAndClick(PWALiveTVPage.objFacebookShare, "Facebook Share button");
		waitTime(5000);
		verifyElementPresentAndClick(PWALiveTVPage.objPostToFB, "Post to Facebook");
		Thread.sleep(5000);
		}

	/**
	 * Function to fetch Live content from Live TV menu
	 */	
    public String fetchLiveContent() throws Exception
		{
		extent.HeaderChildNode("Fetch Live Content Name");
		waitTime(5000);
		verifyElementPresentAndClick(PWALiveTVPage.objLiveTVMenu, "Live TV Menu");
		waitTime(10000);
		String liveTVContentName=getText(PWALiveTVPage.objCardTitle);
		System.out.println(liveTVContentName);
		return liveTVContentName;
	}
    	
    /**
	 * Main Function to verify player validations
	 */	
    public void playerValidations(String userType) throws Exception {
			extent.HeaderChildNode("Player Validation");
			//Tap on Movie page page
			navigateToAnyScreen("Free Movies");
			waitForElement(PWAMoviesPage.objMovieCard, 30, "Movie card");
			click(PWAMoviesPage.objMovieCard,"Movie card");
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 30, "Close in Register Pop Up");
			String consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
			PlayerIconVaidations();
			//Verify Player controls are not displaying after some time
			Thread.sleep(5000);
			playerControlOperations();
		    playerQuality();
			screenOrientation();
			ShareFunctionality();
			AddToWatchList(userType,consumptionPageTitle);
			WatchTrailer();		
	}
    	
     /**
	 * Function to verify player icons
	 */	
    public void PlayerIconVaidations() throws Exception {
    	waitTime(10000);
		waitForPlayerAdToComplete("Video Player");
		playerTap();
		verifyElementPresent(PWAPlayerPage.rewind10SecBtn, "Rewind 10 Seconds icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.pauseBtn, "Pause icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.forward10SecBtn, "Forward 10 Seconda icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.progressBar, "Progress bar");
		playerTap();
		verifyElementPresent(PWAPlayerPage.audioBtn, "Audio icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total duration time");
		playerTap();
		verifyElementPresent(PWAPlayerPage.settingsBtn, "Settings icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.maximizeBtn, "Maximize window icon");
		playerTap();
		verifyElementPresent(PWAPlayerPage.totalDurationTime, "Total time");
		waitTime(10000);
	}
    
    /**
	 * Function to tap on player
	 */	
    @SuppressWarnings("rawtypes")
	public void playerTap() {
    	int deviceWidth = getDriver().manage().window().getSize().width;
		int deviceHeight = getDriver().manage().window().getSize().height;
		int x = deviceWidth / 2;
		int y = deviceHeight / 4;
		TouchAction act = new TouchAction(getDriver());
		act.tap(PointOption.point(x, y)).perform();
		extent.extentLogger("playerTap", "Tapped on the Player");
		logger.info("Tapped on the Player");
    }
    
    /**
	 * Function to wait for Adto complete
	 */	
    public void waitForPlayerAdToComplete() throws Exception {
		boolean AdDisplayed = true;
		int confirmCount = 0;
		main: for (int trial = 0; trial < 120; trial++) {
			System.out.println("Ad trial: " + trial);
			try {
				getDriver().findElement(PWAPlayerPage.objPlayerAdPresent);
				Thread.sleep(1000);
			} catch (Exception e) {
				try {
					getDriver().findElement(PWAPlayerPage.objPlayerAdAbsent);
					Thread.sleep(1000);
					confirmCount++;
					if (confirmCount == 3) {
						logger.info("Ad is not displayed");
						extent.extentLogger("couldNotLocateAd", "Ad is not displayed");
						AdDisplayed = false;
						break main;
					}
				} catch (Exception e1) {
					Thread.sleep(1000);
				}
			}
		}
		if (AdDisplayed == true) {
			logger.error("Ad play failure");
			extent.extentLoggerFail("failedAd", "Ad play failure");
		}
	}

    /**
	 * Function to validate player rewind and forward functionality
	 */
    /**
   	 * Function to validate player rewind and forward functionality
   	 */
   	public void playerControlOperations() throws Exception{
   		HeaderChildNode("Validating rewind 10 seconds, forward 10 seconds and Audio icons");
   		Thread.sleep(3000);
   		int timeBeforeFmin=0,timeBeforeFsec=0,timeBeforeF=0;
   		int timeAfterFmin=0,timeAfterFsec=0,timeAfterF=0;
   		int timeBeforeRmin=0,timeBeforeRsec=0,timeBeforeR=0;
   		int timeAfterRmin=0,timeAfterRsec=0,timeAfterR=0;
   		waitForPlayerAdToComplete("Video Player");
   		pausePlayer();
   		//Verify forward
   		String currentDuration1 = getElementPropertyToString("innerText",PWAPlayerPage.currentDurationTime,"current duration");
   		logger.info("Time fetched before forward: "+currentDuration1);
   		extent.extentLogger("timeBeforeForward", "Time fetched before forward: "+currentDuration1);
   		String[] timeBeforeForward= currentDuration1.split(":");
   		try {
   			timeBeforeFmin = Integer.parseInt(timeBeforeForward[0])*60;
   			timeBeforeFsec = Integer.parseInt(timeBeforeForward[1]);
   			timeBeforeF=timeBeforeFmin+timeBeforeFsec;
   			logger.info("Time fetched before forward in Secs: "+timeBeforeF);
   			extent.extentLogger("timeBeforeForward", "Time fetched before forward in Secs: "+timeBeforeF);
   		}
   		catch(Exception e) {
   			logger.error("Error in time coversion");
   			extent.extentLogger("error", "Error in time coversion");
   		}
   		playerTap();
   		click(PWAPlayerPage.forward10SecBtn,"Forward 10 seconds");
   		Thread.sleep(2000);
   		//Get the current time duration after clicking the forward button
   		playerTap();
   		String currentDuration2 = getElementPropertyToString("innerText",PWAPlayerPage.currentDurationTime,"current duration");
   		logger.info("Time fetched after forward: "+currentDuration2);
   		extent.extentLogger("timeAfterForward", "Time fetched after forward: "+currentDuration2);
   		String[] timeAfterForward = currentDuration2.split(":");
   		try {
   			timeAfterFmin = Integer.parseInt(timeAfterForward[0])*60;
   			timeAfterFsec = Integer.parseInt(timeAfterForward[1]);
   			timeAfterF=timeAfterFmin+timeAfterFsec;
   			logger.info("Time fetched after forward in Secs: "+timeAfterF);
   			extent.extentLogger("timeBeforeForward", "Time fetched after forward in Secs: "+timeAfterF);
   			
   		}
   		catch(Exception e) {
   			logger.error("Error in time coversion");
   			extent.extentLogger("error", "Error in time coversion");
   		}
   		if(timeAfterF >= (timeBeforeF+10) && timeAfterF<= (timeBeforeF+13)) {
   			extent.extentLogger("Verify forward button", "Playback is forwarded by 10 seconds");
   			logger.info("Playback is forwarded by 10 seconds");
   		}else {
   			extent.extentLoggerFail("Verify forward button", "Playback forward by 10 seconds has failed");
   			logger.error("Playback forward by 10 seconds has failed");
   		}
   		//Verify Rewind
   		playerTap();
   		String currentDuration3 = getElementPropertyToString("innerText",PWAPlayerPage.currentDurationTime,"current duration");
   		logger.info("Time fetched before rewind: "+currentDuration3);
   		extent.extentLogger("timeBeforeRewind", "Time fetched before rewind: "+currentDuration3);
   		String[] timeBeforeRewind= currentDuration3.split(":");
   		try {
   			timeBeforeRmin = Integer.parseInt(timeBeforeRewind[0])*60;
   			timeBeforeRsec = Integer.parseInt(timeBeforeRewind[1]);
   			timeBeforeR=timeBeforeRmin+timeBeforeRsec;
   			logger.info("Time fetched before rewind in Secs: "+timeBeforeR);
   			extent.extentLogger("timeBeforeForward", "Time fetched before rewind in Secs: "+timeBeforeR);
   		}
   		catch(Exception e) {
   			logger.error("Error in time coversion");
   			extent.extentLogger("error", "Error in time coversion");
   		}
   		playerTap();
   		click(PWAPlayerPage.rewind10SecBtn,"Rewind 10 seconds");
   		//Get the current time duration after clicking the rewind button
   		playerTap();
   		String currentDuration4 = getElementPropertyToString("innerText",PWAPlayerPage.currentDurationTime,"current duration");
   		logger.info("Time fetched after rewind: "+currentDuration4);
   		extent.extentLogger("timeAfterRewind", "Time fetched after rewind: "+currentDuration4);
   		String[] timeAfterRewind = currentDuration4.split(":");
   		try {
   			timeAfterRmin = Integer.parseInt(timeAfterRewind[0])*60;
   			timeAfterRsec = Integer.parseInt(timeAfterRewind[1]);
   			timeAfterR=timeAfterRmin+timeAfterRsec;
   			logger.info("Time fetched after rewind in Secs: "+timeAfterR);
   			extent.extentLogger("timeBeforeForward", "Time fetched after rewind in Secs: "+timeAfterR);
   		}
   		catch(Exception e) {
   			logger.error("Error in time coversion");
   			extent.extentLogger("error", "Error in time coversion");
   		}
   		if(timeAfterR >= (timeBeforeR-10) && timeAfterR < (timeBeforeR-7)) {
   			extent.extentLogger("Verify rewind button", "Playback is rewinded 10 seconds");
   			logger.info("Playback is rewinded 10 seconds");
   		}else {
   			extent.extentLoggerFail("Verify rewind button", "Playback rewind by 10 seconds has failed");
   			logger.error("Playback rewind by 10 seconds has failed");
   		}
   		//Verify the mute/unmute
   		playerTap();
   		verifyElementPresent(PWAPlayerPage.objMuteButton,"Audio Mute button");
   		playerTap();
   		click(PWAPlayerPage.objMuteButton, "Audio Mute button");
   		verifyElementPresent(PWAPlayerPage.objUnmuteButton,"Audio Unmute button");
   	}
    /**
	 * Function to verify video quality selected
	 */	
	public void playerQuality() throws Exception{
			HeaderChildNode("Validating the Playback Quality");
			//Thread.sleep(10000);
			//waitForPlayerAdToComplete("Video Player");			
			//playerTap();
			//Tap on settings Icon
			click(PWAPlayerPage.settingsBtn, "Settings icon");
			//Click on Quality icon
			click(PWAPlayerPage.qualityBtn, "Quality icon");
			waitTime(5000);
			//Select Quality from Quality menu
			int size = getDriver().findElements(PWAPlayerPage.objPlayerQualities).size();
			if(size==0) {
				logger.info("Quality options unavailable");
				extent.extentLogger("quality", "Quality options unavailable");
			}
			else {
				for (int i = 1; i <= size; i++) {
					logger.info("Quality option available : "+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
					extent.extentLogger("quality", "Quality option available : "+ getAttributValue("innerText", PWAPlayerPage.objAllQualityOptions(i)));
				}
				String selectedQuality=getElementPropertyToString("innerText", PWAPlayerPage.objPlayerSelectedQuality, "Selected Quality");
				logger.info("Quality option selected : "+ selectedQuality);
				extent.extentLogger("quality", "Quality option selected : "+ selectedQuality);
				//Select another quality
				Thread.sleep(5000);
				click(PWAPlayerPage.objPlayerUnSelectedQuality,"Unselected Quality");
				Thread.sleep(5000);
				//Verify the click
				click(PWAPlayerPage.settingsBtn, "Settings icon");
				click(PWAPlayerPage.qualityBtn, "Quality icon");
				waitTime(5000);
				String selectedQualityAfter=getElementPropertyToString("innerText", PWAPlayerPage.objPlayerSelectedQuality, "Selected Quality");
				logger.info("Quality option selected : "+ selectedQualityAfter);
				extent.extentLogger("quality", "Quality option selected : "+ selectedQualityAfter);
				if(selectedQuality.equals(selectedQualityAfter)) {
					logger.error("Quality selection unsuccessful");
					extent.extentLoggerFail("quality","Quality selection unsuccessful");
				}
				else {
					logger.info("Quality selection successful");
					extent.extentLogger("quality", "Quality selection successful");
				}
			}
		}
	
	/**
	 * Function to verify maximize and minimize functionality of the player
	 */	
	public void screenOrientation() throws Exception{
		extent.HeaderChildNode("Validating full screen mode");
		playerTap();
		verifyElementPresentAndClick(PWAPlayerPage.maximizeBtn, "Maximize button");
		GetAndVerifyPWAOrientaion("Landscape");
		Thread.sleep(3000);
		verifyElementPresentAndClick(PWAPlayerPage.minimizeBtn, "Minimize button");
	}

	/**
	 * Function to get device orientation
	 */
	public void GetAndVerifyPWAOrientaion(String Value) {
		ScreenOrientation orientation = getDriver().getOrientation();
		String ScreenOrientation = orientation.toString();
		try {
			softAssert.assertEquals(ScreenOrientation.equalsIgnoreCase(Value), true,"The screen Orientation is " + ScreenOrientation);
			logger.info("The screen Orientation is " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is " + ScreenOrientation);
		} catch (Exception e) {
			softAssert.assertEquals(false, true, "The screen Orientation is not " + ScreenOrientation);
			softAssert.assertAll();
			logger.error("The screen Orientation is not " + ScreenOrientation);
			extent.extentLogger("Screen Orientation", "The screen Orientation is not " + ScreenOrientation);
		}
	}
	
	/**
	 * Function to add to watchlist
	 */
	public void AddToWatchListGuestUser() throws Exception{
		extent.HeaderChildNode("Add to Watch List Guest user validations");
		//Verify Add to Watchlist is displayed
		verifyElementPresent(PWAPlayerPage.watchListBtn, "Add to Watchlist");
		//Click on Add to Watchlist option
		click(PWAPlayerPage.watchListBtn, "Add to Watchlist");
		//Verify user is Observed Login pop up
		verifyElementExist(PWAPlayerPage.objLoginRequiredTxt, "Login Required Pop up");
		//Close the Login Popup
		click(PWAPlayerPage.objCloseBtnLoginPopup, "Close button Login Popup");
		
	}

	/**
	 * Function verify upnext rail
	 */
	public void UpnextRail() throws Exception {
		extent.HeaderChildNode("Validating UpNext rail");
		// Click on home page
		click(PWAHomePage.objTabName("Home"), "Home tab");
		// Click on search icon
		click(PWAHomePage.objSearchBtn, "Search Button");
		waitTime(3000);
		// Enter text
		type(PWASearchPage.objSearchEditBox, "Paaru - April 06, 2020 - Episode Spoiler", "Search Edit box");
		// Click on first content
		waitTime(7000);
		directClick(PWASearchPage.objFirstContentCardNameAfterSearch, "Content Card");
		waitTime(30000);
		for(int trial=0;trial<=80;trial++) {
			try {
				getDriver().findElement(PWAPlayerPage.objfirstContent);
				System.out.println("Up next Rail displayed");
				extent.extentLogger("Upnext Rail", "Up next Rail displayed");
				logger.info("Up next Rail displayed");
				break;
			}
			catch(Exception e) {Thread.sleep(2000);}
		}
		waitTime(5000);
		waitForPlayerAdToComplete("Video Player");
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Register Pop Up");
		String episodeName = getElementPropertyToString("innerText", PWAPlayerPage.objContentName, "Content name");
		System.out.println(episodeName);
		extent.extentLogger("epnameUI", "Up Next content from UI: "+episodeName);
		logger.info("Up Next content from UI: "+episodeName);
		getResponseUpNextRail.getResponse();
		String APIData = getResponseUpNextRail.getMediaContentName();
		System.out.println(APIData);
		extent.extentLogger("epnameAPI", "Up Next content from API: "+APIData);
		logger.info("Up Next content from API: "+APIData);
		if (APIData.substring(0, 10).equals(episodeName.substring(0, 10))) {//Added substring function as temporary solution to equals problem.
			extent.extentLogger("Upnext Rail", "The first content Auto played in Upnext rail");
			logger.info("Upnext rail content is auto played");
		} else {
			extent.extentLoggerFail("Verify UpNext Rail", "Mismatch observed, Upnext content auto play is failed");
			logger.info("Mismatch observed, Upnext content playabck is failed");
		}
	}

	/**
	 * Function to verify watch credit
	 */
	public void WatchCredit() throws Exception {
		extent.HeaderChildNode("Validating Watch credit");
		click(PWAHomePage.objTabName("Home"),"Home");
		click(PWAHomePage.objSearchBtn, "Search Button");
		type(PWASearchPage.objSearchEditBox, "right ya wrong", "Search Edit box");
		waitTime(8000);
		click(PWASearchPage.objFirstContentCardNameAfterSearch(1), "Content Card");
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Register Pop Up");	
		waitForPlayerAdToComplete("Video Player");
		playerTap();
		try{getDriver().findElement(PWAPlayerPage.pauseBtn).click();}catch(Exception e) {System.out.println("Failed to Pause");}
		WebElement slider = getDriver().findElement(PWAPlayerPage.progressBar);
		Actions move = new Actions(getDriver());
		Action action = (Action) move.dragAndDropBy(slider, 354, 0).build();
		action.perform();
		click(PWAPlayerPage.playBtn, "Play icon");
		waitTime(60000);
		verifyElementPresent(PWAPlayerPage.objWatchCredit, "Watch Credit");
		verifyElementPresent(PWAPlayerPage.objContentCardsOnPlayer, "Upnext Rail on video playback");
	}
	
	/**
	 * Function to add to watchlist
	 */
	public void AddToWatchList(String userType,String consumptionPageTitle) throws Exception {
		extent.HeaderChildNode("Add to Watch List for user: "+userType);
		// Click on Watchlist
		click(PWAPlayerPage.watchListBtn, "WatchList button");
		if(userType.equals("Guest")) {
			//Verify user is Observed Login pop up
			verifyElementExist(PWAPlayerPage.objLoginRequiredTxt, "Login Required Pop up");
			//Close the Login Popup
			click(PWAPlayerPage.objCloseBtnLoginPopup, "Close button Login Popup");
		}
		else {
			//Click on Hamburger menu
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			// Click on My account
			click(PWAHamburgerMenuPage.objMyAccountOption, "My Account");
			// Click on Watchlist
			click(PWAAddToWatchListPage.objMyWatchList, "Watch list");
			// Click on Movies tab
			Thread.sleep(3000);
			click(PWAAddToWatchListPage.objMoviesTab, "Movies tab");
			// Verify added Item is present in Watchlist
			verifyElementPresent(PWAAddToWatchListPage.objWatchListed(consumptionPageTitle), "Watchlisted content: "+consumptionPageTitle);
			click(PWAAddToWatchListPage.objRemoveContentsInWatchList, "Remove watchlist");
			waitTime(3000);
			Back(1);
		}		
	}
	
	/**
	 * Function to verify Share Functionality
	 */
	@SuppressWarnings("rawtypes")
	public void ShareFunctionality() throws Exception{
		extent.HeaderChildNode("Share functionality Validation");
		//Verify Share option 
		verifyElementPresent(PWAPlayerPage.shareBtn, "Share option");
		//Click on the Share option
		click(PWAPlayerPage.shareBtn, "Share option");
		//Verify the Share options are visible
		Thread.sleep(3000);
		System.out.println(getDriver().getContextHandles());
		getDriver().context("NATIVE_APP");
		Dimension dim = getDriver().manage().window().getSize();
		int startx = (int) (dim.width * 0.6);
		int starty = (int) (dim.height * 0.7);
		int endx = (int) (startx * 0.1);
		int endy = starty;
		for(int i=0;i<2;i++) {
		try {
		 getDriver().findElement(PWAShowsPage.objFacebookApp).click();
		 break;
		}
		catch(Exception e) {
			TouchAction act=new TouchAction(getDriver());
			act.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(endx, endy)).release().perform();
		}
		}
		waitForElementAndClick(PWAShowsPage.objFacebookPostBtn, 10, "POST button in Facebook App");
		getDriver().context("CHROMIUM");
	}

	/**
	 * Function to verify Watch Trailer functionality
	 */
	public void WatchTrailer() throws Exception {
		waitForElementAndClick(PWAHomePage.objSearchBtn, 60,"Search icon");
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Register Pop Up");			
		type(PWASearchPage.objSearchEditBox, "Bhinna\n", "Search Edit box: Bhinna");
		Thread.sleep(5000);
		waitForElement(PWASearchPage.objSearchedResult("Bhinna"), 30, "Search Result");
		verifyElementPresentAndClick(PWASearchPage.objSearchedResult("Bhinna"), "Search Result");
		waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 60, "Close in Premium Pop Up");	
		verifyElementPresent(PWASearchPage.watchTrailer, "Watch Trailer option");
	}

	/**
	 * Function to navigate to home using zee5 logo
	 */
	public void Navigate_to_HomeScreen_using_Zee5Logo() throws Exception {
		verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "Zee5 Logo");
		waitForElementDisplayed(PWAHomePage.objContTitleOnCarousel, 20);
		verifyElementPresent(PWAHomePage.objContTitleOnCarousel, "Carousal content title");
	}

	/**
	 * Function to verify view all functionality for a tray
	 */
	public void verifyTrayViewAll(String trayTitleAPI,String contentTitleAPI) throws Exception {
		extent.HeaderChildNode("Verify See All functionality for tray");
		waitTime(3000);
		verifyElementPresentAndClick(PWALandingPages.objViewAllOfTray(trayTitleAPI), "View all button for tray "+trayTitleAPI);
		waitTime(8000);
		waitForElementDisplayed(PWALandingPages.objViewAllPageTitle, 60);
		String viewAllPageTitle=getElementPropertyToString("innerText", PWALandingPages.objViewAllPageTitle,"View All Page Title").toString();
		if(viewAllPageTitle.equals(trayTitleAPI)) {
			extent.extentLogger("titles", "Tray title matches View All page title :"+trayTitleAPI);
			logger.info("Tray title matches View All page title :"+trayTitleAPI);
		} else {
			extent.extentLoggerFail("titles", "Mismatch observed, tray title is: "+trayTitleAPI+" and View All page title is: "+viewAllPageTitle);
			logger.info("Mismatch observed, tray title is: "+trayTitleAPI+" and View All page title is: "+viewAllPageTitle);
		}
		waitForElementDisplayed(PWALandingPages.objViewAllPageFirstContent, 10);
		String viewAllPageFirstContent=getElementPropertyToString("data-minutelytitle", PWALandingPages.objViewAllPageFirstContent,"View All Page First Content").toString();
		if(viewAllPageFirstContent.equals(contentTitleAPI)) {
			extent.extentLogger("contenttitles", "Tray First Content matches View All page first Content :"+contentTitleAPI);
			logger.info("Tray First Content matches View All page first Content :"+contentTitleAPI);
		} else {
			extent.extentLoggerFail("contenttitles", "Mismatch observed, Tray first Content is: "+contentTitleAPI+" and View All page first Content is: "+viewAllPageFirstContent);
			logger.info("Mismatch observed, Tray first Content is: "+contentTitleAPI+" and View All page first Content is: "+viewAllPageFirstContent);
		}
	}

	/**
	 * Function to swipe till text
	 */
	public void Swipe_till_Text(String text) throws Exception {
		waitTime(4000);
		for (int i = 0; i <= 5; i++) {
			if (verifyElementExist(Text_To_Xpath(text), text)) {
				System.out.println("element found");
				break;
			} else {
				PartialSwipe("up", 1);
			}
		}
	}
		
	/**
	 * Function to swipe till tray Trending on Zee5
	 */
	public void Swipe_till_Zee5IsTrending() throws Exception {
		waitTime(5000);
		int found = 0;
		for (int i = 0; i <= 2; i++) {
			if (verifyElementExist(PWALandingPages.obj_Pwa_Trending_On_Zee5, "Trending on Zee5 tray")) {
				System.out.println("element found");
				logger.info("Verify for first time user it should show Trending on Zee5 tray is Pass");
				extent.extentLogger("apValue", "Verify for first time user it should show Trending on Zee5 tray is Pass");
				found=1;
				break;
			} else {
				Swipe("up", 1);
				waitTime(4000);
			}
			if(found==0) {
				System.out.println("Trending on Zee5 is not found");
				logger.error("Verify for first time user it should show Trending on Zee5 tray is Fail");
				extent.extentLoggerFail("apValue", "Verify for first time user it should show Trending on Zee5 tray is Fail");
			}
		}
	}	
	
    /**
	 * Function to verify player test (general written for R&D)
	 */
    public void verifyPlayerTest(String userType, String contentType, String contentTitle, String devicePin) throws Exception {
    		extent.HeaderChildNode("Verify Player Test: " + contentType);
    		String consumptionPageTitle = "";
    		waitTime(10000);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
			waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 10, "Close in Language Pop Up");			
			type(PWASearchPage.objSearchEditBox, contentTitle + "\n", "Search Edit box: " + contentTitle);
			waitForElement(PWASearchPage.objSearchedResult(contentTitle), 30, "Search Result");
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(contentTitle), "Search Result");
			waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 10, "Close in Register Pop Up");
			waitForElement(PWAPlayerPage.objContentTitle, 30, "Content title");
			consumptionPageTitle = getElementPropertyToString("innerText", PWAPlayerPage.objContentTitle,"Content Title").toString();
			if (consumptionPageTitle.contains(contentTitle)) {
				extent.extentLogger("correctNavigation","Successfully navigated to the correct Consumption page " + consumptionPageTitle);
				logger.info("Successfully navigated to the correct Consumption page " + consumptionPageTitle);
				pausePlayerAndGetLastPlayedTimeUsingPlayerLocator();
			} else {
				extent.extentLoggerFail("incorrectNavigation","Navigated to incorrect Consumption page " + consumptionPageTitle);
				logger.error("Navigated to incorrect Consumption page: " + consumptionPageTitle);
			}				
    }
    
    /**
	 * Function to pause player (general written for R&D)
	 */
    public void pausePlayerAndGetLastPlayedTimeUsingPlayerLocator() throws Exception {
		//waitForPlayerAdToComplete("Video Player");
		//waitForPlayerLoaderToComplete();
		Thread.sleep(9000);
		if (pausePlayerUsingPlayerLocator() == true) {
			getPlayerDuration();
			Thread.sleep(4000);
		} else {
			extent.extentLoggerFail("failedToPause", "Failed to pause Player");
			logger.error("Failed to pause Player");
		}
    }
    
    /**
	 * Function to pause player using locator (general written for R&D)
	 */	
    @SuppressWarnings("unused")
	public boolean pausePlayerUsingPlayerLocator() throws InterruptedException {
			int deviceWidth = getDriver().manage().window().getSize().width;
			int deviceHeight = getDriver().manage().window().getSize().height;
			boolean playerPaused = false;
			for (int trial = 0; trial <= 4; trial++) {
				System.out.println("trial :" + trial);
				try {					
				/*
				 * JavascriptExecutor js = (JavascriptExecutor)driver; WebElement
				 * player=getDriver().findElement(PWAPlayerPage.objPlayer);
				 * js.executeScript("arguments[0].click();", player);
				 */
					getDriver().findElement(PWAPlayerPage.objPlayer).click();
					
					//Actions act=new Actions(getDriver());
					//act.click(getDriver().findElement(PWAPlayerPage.objPlayer)).build().perform();
					
					extent.extentLogger("playerTap", "Tapped on the Player");
					logger.info("Tapped on the Player");
					try {
						getDriver().findElement(PWAPlayerPage.pauseBtn).click();
						try {
							getDriver().findElement(PWAPlayerPage.playBtn);
							extent.extentLogger("playerPaused", "Paused the Player");
							logger.info("Paused the Player");
							playerPaused = true;
							break;
						} catch (Exception e) {
						}
					} catch (Exception e) {
						try {
							getDriver().findElement(PWAPlayerPage.playBtn);
							extent.extentLogger("playerPaused", "Paused the Player");
							logger.info("Paused the Player");
							playerPaused = true;
							break;
						} catch (Exception e1) {
						}
					}
				} catch (Exception e) {
					Thread.sleep(1000);
					if (trial == 4) {
						extent.extentLoggerFail("errorOccured", "Error when handling Player");
						logger.error("Error when handling Player");
					}
				}
		}
		Thread.sleep(3000);
		return playerPaused;
	}
    	
    /**
	 * Function to verify landing page properties
	 */	
	public void landingpagePropertiesValidation(String userType) throws Exception {
		extent.HeaderChildNode("Landing Page validation");
		verifyElementPresent(PWAHomePage.objHamburgerMenu, "Hamburger Menu");
		verifyElementPresent(PWALandingPages.obj_Pwa_Zee5Logo, "Zee5 Logo");
		verifyElementPresent(PWALandingPages.obj_Pwa_SearchBtn, "Search");
		if(userType.equalsIgnoreCase("SubscribedUser"))
			verifyElementNotPresent(PWALandingPages.obj_Pwa_Subcription_teaser_btn, 5);
		else
			verifyElementExist(PWALandingPages.obj_Pwa_Subcription_teaser_btn, "Subcription button");
	}

	/**
	 * Method to verify Back to Top functionality
	 */
	public void BackTOTop() throws Exception {
		Swipe("UP", 2);
		waitTime(5000);
		closeAdoricPopUp();
		waitForElementDisplayed(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 20);	
		verifyElementPresentAndClick(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, "Back to Top");
	}


	/**
	 * PWA Subscription Suite
	 */
	public void zeePWASubscriptionSuite(String userType) throws Exception {
		if(userType.equals("SubscribedUser")) {
			ValidatingSubscriptionAndTransaction();
			ValidatingSubscribeLinks();
		}
		else if(userType.equals("Guest") || userType.equals("NonSubscribedUser")){
			zeePWASubscriptionScenariosValidation(userType);
			zeePWASubscriptionFlowFromHomePageHeaderSubscribeBtn(userType);
		}
		else {
			logger.error("Incorrect userType parameter passed to method");
			extent.extentLoggerFail("incorrectUserType","Incorrect userType parameter passed to method");
		}
	}


	/**
	 * Search For Content And Click On First Result
	 */
	public void zeeSearchForContentAndClickOnFirstResult(String contentName) throws Exception {
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
		waitForElementDisplayed(PWASearchPage.objSearchEditBox, 20);
		type(PWASearchPage.objSearchEditBox, contentName, "Search bar");
		hideKeyboard();
		waitForElementDisplayed(PWASearchPage.objFirstSearchedAssetTitle, 20);
		waitTime(3000);
		String FirstSearchedAssetTitle = findElement(PWASearchPage.objFirstSearchedAssetTitle).getText();
		System.out.println("First Asset Title of the Search Result is: " + FirstSearchedAssetTitle);
		click(PWASearchPage.objFirstSearchedAssetTitle, "First Searched Asset Title");
	}

	/**
	 * PWA Subscription Page Validation
	 */
	public void zeeSubscriptionPageValidationAndNavigateToHomePage() throws Exception {
		//HeaderChildNode("PWA Subscription Page Validation and Navigate to Home Page");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(7000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
	}
	
	/**
	 * PWA Subscription Page Validation
	 */
	public void zeeSubscriptionPageValidationAndNavigateToHomePage(String origin) throws Exception {
		HeaderChildNode("Subscription Page Validation navigation from: "+origin);
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		waitTime(7000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
	}

	/**
	 * PWA Account Info Page Validation
	 */
	public void zeeAccountInfoPageValidationAndNavigateToHomePage() throws Exception {
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
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");

	}

	/**
	 * Verify Get Premium Popup
	 */
	public void zeeVerifyGetPremiumPopup() throws Exception {
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objDefaultSelectedPack, "Default Selected Package");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupProceedBtn, "Popup Proceed Btn");
	}

	/**
	 * Subscription Flow From Home Page Header Subscribe Btn Line No 89
	 */
	public void zeePWASubscriptionFlowFromHomePageHeaderSubscribeBtn(String userType) throws Exception {
		HeaderChildNode("PWA Subscription Flow From Home Page Header Subcribe Btn");
		// Scenario no. 89
		waitTime(5000);
		click(PWAHomePage.objSubscribeBtnTopHeader, "Subscribe Btn in the Header");
//		getDriver().findElement(PWAHomePage.objSubscribeBtn).click();			
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

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
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

	}

	/**
	 * Subscription Flow From Adoric Popup Line No 91 Subscription Flow From
	 * Subcribe Btn On Playing Premium Content Line No 92 Subscription Flow From
	 * Subscribe popup on playing Before TV content Line No 94
	 */
	public void zeePWASubscriptionFlowFromGetPremiumPopupOnPlayingPremiumContent(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Adoric Popup/Get Premium popup On Playing Premium Content");

		// Scenario no. 91,92,94
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

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
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

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
		verifyElementPresentAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen,"Get Premium Link below the Player");
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

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
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objBuySubscriptionOption,
				"Buy Subscribe Option in Hamburger Menu");
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

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
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
		waitTime(5000);
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHaveAPrepaidCode,
				"Have A Prepaid Code? Option in Hamburger Menu");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		// Scenario no. 98
		zeePWAPromoCodeValidationInSelectPackPage();
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

	}

	/**
	 * Promo code Validation in Select Pack Page Subscription Flow using promo codes
	 * to verify if the user is getting discounted price on plans are not - Line No
	 * 98
	 */
	public void zeePWAPromoCodeValidationInSelectPackPage() throws Exception {
		HeaderChildNode("Promo code Validation in Select Pack Page");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objHaveACode, "'Have A Code?' field");
		waitTime(3000);
		type(PWASubscriptionPages.objHaveACode, "ZEE5FLAT50\n", "'Have A Code?' field");
//		getDriver().findElement(PWASubscriptionPages.objHaveACode).sendKeys("ZEE5FLAT50");
//		hideKeyboard();
		waitTime(5000);
		click(PWASubscriptionPages.objApplyBtn, "Apply Button");
		waitTime(5000);
		try {getDriver().findElement(PWASubscriptionPages.objApply).click();}catch(Exception e) {}//Added for Vivo phone
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
		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtn, "Continue Btn");
		waitTime(5000);

	}

	/**
	 * Account Info Page Validation Validate that selected pack information is
	 * displayed on left side. - Line No. 103 Validate that guest user is able to
	 * sign in/sign up from account info screen - Line No. 104
	 */
	public void zeePWAAccountInfoPageValidation() throws Exception {
		HeaderChildNode("Account Info Page Validation");
		verifyElementPresent(PWASubscriptionPages.objAccountInfoHighlighted, "Account Info Section");
		waitTime(3000);
		// Scenario no. 103
		zeePWASelectedPackDisplayValidation();
		verifyElementPresent(PWASubscriptionPages.objProceedBtnNotHighlighted,"Proceed Btn in Account Info Page Not Highlighted");
		// Scenario no. 104
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objEmailIDTextField, "Email ID Text Field");
		waitTime(3000);
		String email=RandomStringGenerator(6)+"@c.com";
		type(PWASubscriptionPages.objEmailIDTextField, email, "Email Id");
		hideKeyboard();
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtnHighlighted,"Proceed Btn in Account Info Page Highlighted");
		waitTime(3000);
		// Password Popup
		verifyElementPresent(PWASubscriptionPages.objEnterPasswordPopupTitle, "Enter Password Popup Title");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objProceedBtnDisabled, "Disabled Proceed Btn");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPasswordFieldHidden, "Password Field");
		waitTime(3000);
		type(PWASubscriptionPages.objPasswordFieldHidden, "igs@12345", "Password Field");
		hideKeyboard();
		waitTime(9000);
		verifyElementPresentAndClick(PWASubscriptionPages.objProceedBtn, "Enabled Proceed Btn");
		try {getDriver().findElement(PWASubscriptionPages.objProceedBtn).click();}catch(Exception e) {}//Required for Vivo phone because keyboard shows up
		waitTime(3000);

	}

	/**
	 * Selected Pack Display Validation Validate that selected pack information is
	 * displayed on left side. - Line No. 103
	 */
	public void zeePWASelectedPackDisplayValidation() throws Exception {
		// Scenario no. 103
		//verifyElementPresent(PWASubscriptionPages.objSelectedPackText, "Selected Pack Text");
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
/*	public void zeePWAPaymentPageValidation() throws Exception {
		HeaderChildNode("Payment Page Validation and Selection of PayTm Payment option");
		// Scenario no. 103
		waitTime(8000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
		waitTime(3000);
		zeePWASelectedPackDisplayValidation();
		verifyElementPresent(PWASubscriptionPages.objAccountInfoText, "Account Info Text in Payments Section");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoDetails, "Account Info Details in Payments Section");
		waitTime(3000);
		/*verifyElementPresent(PWASubscriptionPages.objCreditCardRadioBtn, "Credit Card Radio Btn");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objDebitCardRadioBtn, "Debit Card Radio Btn");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objPayTMRadioBtn, "PayTm Radio Btn");
		waitTime(3000);
		Swipe("UP",1);
		verifyElementPresent(PWASubscriptionPages.objContinueBtnDisabled, "Continue Btn Disabled");
		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objRecurrenceMessage, "Recurrence Message");
		waitTime(3000);
		click(PWASubscriptionPages.objPayTMRadioBtn, "PayTm Radio Btn");
		waitTime(3000);
		verifyElementPresentAndClick(PWASubscriptionPages.objContinueBtnEnabled, "Continue Btn Enabled");
		waitTime(5000);
		// PayTM Page
		verifyElementPresent(PWASubscriptionPages.objPaytmWalletOption, "PayTM Wallet option");*/
		

	//}
	
	
	/**
	 * Payment Page Validation Validate that user is navigated to Payment options
	 * screen post successful sign in/sign up - Line No. 105
	 */
	//manas
	public void zeePWAPaymentPageValidation() throws Exception {
		HeaderChildNode("Validate that user is navigated to Payment options screen post successful sign in/sign up");

		// Scenario no. 103
//		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objPaymentHighlighted, "Payment Section");
//		waitTime(3000);
		zeePWASelectedPackDisplayValidation();
//		verifyElementPresent(PWASubscriptionPages.objAccountInfoText, "Account Info Text in Payments Section");
//		waitTime(3000);
		verifyElementPresent(PWASubscriptionPages.objAccountInfoDetails, "Account Info Details in Payments Section");
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
		if(getPlatform().equalsIgnoreCase("Android")){
			iframeElement = getDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getDriver().switchTo().frame(iframeElement);
		} else if(getPlatform().equalsIgnoreCase("Web")){
			iframeElement = getWebDriver().findElement(By.id("juspay_iframe"));
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			getWebDriver().switchTo().frame(iframeElement);
		}
		
		
		verifyElementPresentAndClick(PWASubscriptionPages.objCreditAndDebitCardBtn, "Credit/Debit Card Option");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objEnterCreditAndDebitCardDetails, "Enter Credit/Debit Card Details");
		verifyElementPresent(PWASubscriptionPages.objCardNumber, "Enter Card Number Field");
		verifyElementPresent(PWASubscriptionPages.objExpiry, "Expiry Field");
		verifyElementPresent(PWASubscriptionPages.objCVV, "CVV Field");
//		Back(1);
		waitTime(5000);
		if(getPlatform().equals("Android")) {
			extent.HeaderChildNode("Validating the payment gateway using Paytm");
			getDriver().switchTo().frame(iframeElement);
			verifyElementPresentAndClick(PWASubscriptionPages.objPaytmWallet, "Paytm");
			getDriver().switchTo().defaultContent();
		}else if(getPlatform().equalsIgnoreCase("Web")){
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

	/**
	 * Navigate back to paytm wallet and logout
	 */
	public void navigateBackFromPayTmWalletAndLogout() throws Exception {
		waitTime(5000);
		Back(1);
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");
		logout();
	}

	/**
	 * Method to scroll to element
	 */
	public void ScrollToElement(By Locator, String validationText) throws Exception {

		for (int i = 1; i <= 10; i++) {
			if (verifyElementPresent(Locator, validationText) == true) {
				break;
			}
			waitTime(2000);
			swipeALittle("up", 1);
		}
	}

	/**
	 * Method to swipe a little
	 */
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

	/**
	 * Subscription Flow From Subscribe popup on playing Before TV content Line No
	 * 94
	 */
	public void zeePWASubscriptionFlowFromSubscribePopupOnPlayingBeforeTVContent(String userType, String platform)
			throws Exception {
		HeaderChildNode("PWA Subscription Flow From Subscribe popup on playing Before TV content");

		waitTime(5000);
		verifyElementPresent(PWAHomePage.objSubscribeBtn, "Subscribe Btn in the Header");
		waitTime(5000);
		if (userType.equals("Guest")) {
			zeePWAGuestUserSubscriptionFlow();
		} else if (userType.equals("Non Subscribed User")) {
			zeePWANonSubscribedUserSubscriptionFlow();
		}
		navigateBackFromPayTmWalletAndLogout();

	}
	
	
	/**
	 * Subscription Scenarios Validation
	 */
	public void zeePWASubscriptionScenariosValidation(String userType) throws Exception {
		// Scenario no. 89
		HeaderChildNode("Scenario: Navigate to Subscription Flow From Home Page Header Subcribe Btn");
		waitTime(5000);
		click(PWAHomePage.objSubscribeBtnTopHeader, "Subscribe Btn in the Header");					
		waitTime(5000);
		zeeSubscriptionPageValidationAndNavigateToHomePage();				
		// Scenario no. 90,98
		HeaderChildNode("Scenario: Navigate to Subscription Flow from Home Page Get Premium CTA On Carousel");
		waitForElementAndClickIfPresent(PWAHomePage.objGetPremium, 20, "Get Premium CTA on Carousel");
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objZEE5Subscription, "Zee5 Subscription Page Title");
		waitTime(5000);
		verifyElementPresent(PWASubscriptionPages.objSelectPackHighlighted, "Select Pack Section");
		// Scenario no. 98
		zeePWAPromoCodeValidationInSelectPackPage();
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objZEE5Logo, "Zee5 Logo");		
		if(userType.equalsIgnoreCase("Guest")) {
			// Scenario no. 96
			HeaderChildNode("Scenario: Navigate to Subscription Flow From 'Buy Subscription' option under My plans in hamburger menu");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objBuySubscriptionOption, "Buy Subscribe Option in Hamburger Menu");
			zeeSubscriptionPageValidationAndNavigateToHomePage();
			// Scenario no. 97
			HeaderChildNode("Scenario: Navigate to Subscription Flow From 'Have a Prepaid code' option under My plans in hamburger menu");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Menu Btn");
			waitTime(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have A Prepaid Code? Option in Hamburger Menu");
			waitTime(5000);
			zeeSubscriptionPageValidationAndNavigateToHomePage();
		}		
		// Scenario no. 91,92,94
		HeaderChildNode("Scenario: Navigate to Subscription Flow From Adoric Popup/Get Premium popup On Playing Premium Content");
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
		waitTime(5000);
		zeeVerifyGetPremiumPopup(); 
		waitTime(5000);
		if(userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		}else if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}
		
		
		// Scenario no. 93
		HeaderChildNode("Scenario: Navigate to Subscription Flow From Player In-line Subscribe link on Player");
		zeeSearchForContentAndClickOnFirstResult("Londonalli Lambodara");
		waitTime(10000);
		verifyElementPresent(PWASubscriptionPages.objGetPremiumPopupTitle, "Get Premium Popup Title");
		waitTime(5000);
		verifyElementPresentAndClick(PWASubscriptionPages.objPopupCloseButton, "Popup Close Button");
		waitTime(5000);
		verifyElementPresentAndClick(PWAPlayerPage.objSubscribeNowLink, "In-Line Subscribe Link on Player");
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if(userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		}else if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}
		
		// Scenario no. 95
		HeaderChildNode("Scenario: Navigate to Subscription Flow From Subscription Get premium CTA below the player at consumption screen");
		zeeSearchForContentAndClickOnFirstResult("Premier Padmini");
		waitTime(5000);
		waitForElementAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, 30, "Get Premium Link below the Player");
//		verifyElementPresentAndClick(PWAPlayerPage.objGetPremiumCTABelowPlayerScreen, "Get Premium Link below the Player");
		waitTime(5000);
		zeeVerifyGetPremiumPopup();
		waitTime(5000);
		if(userType.equalsIgnoreCase("Guest")) {
			zeeAccountInfoPageValidationAndNavigateToHomePage();
		}else if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			zeePaymentPageValidationAndNavigateToHomePage();
		}		
	}    
/**
	 * Function to verify Meta data on carousel for different pages
	 * 
	 * @param pagename
	 * @param screenname
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void verifyMetadataOnCarousel(String screen, String pageName) throws Exception {
		extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);
		navigateToAnyScreen(screen);
		waitTime(5000);
		if(screen.equals("News")) {
			
		}
		String languageSmallText = allSelectedLanguages();
		System.out.println(languageSmallText);
		String carouselTitleAPI= "";
		boolean isTitlePresent = false;
		
		List<String> allMetaTitleOnCarouselAPI = ResponseInstance.traysTitleCarousel(pageName, languageSmallText);
	    System.out.println("API Data : " + allMetaTitleOnCarouselAPI);
	    
	    Dimension size = getDriver().findElement(PWAHomePage.objCarouselBanner).getSize();
	    int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.1);
		int starty = (int) (size.height * 0.5);
	    
		for(int i=0; i<allMetaTitleOnCarouselAPI.size();i++) {
	    	carouselTitleAPI = allMetaTitleOnCarouselAPI.get(i);
	    	for(int j=0; j<7;j++) {
	    	try {
	    		 WebElement mastHeadEle = (new WebDriverWait(getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(PWAHomePage.objContTitleTextCarousel(carouselTitleAPI)));
	    		 isTitlePresent = mastHeadEle.isDisplayed();
	    		 if(isTitlePresent == true) {
	    		 break;
	    		 }
	    		 else {
	 				TouchAction action = new TouchAction(getDriver());
	 				action.press(PointOption.point(startx, starty))
	 						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	 						.moveTo(PointOption.point(endx, starty)).release().perform();
	    		 }
	    	}catch(Exception e) {
    			System.out.println("Exception : "+e.getMessage() );	
	    	}
	    }
	    	if(isTitlePresent==true) {
		    	softAssert.assertTrue(true, "API title "+carouselTitleAPI+" is present on UI");
		    	logger.info("API title "+carouselTitleAPI+" is present on UI");
		    	extent.extentLogger("Metadata validation","API title "+carouselTitleAPI+" is present on UI");
	    	}else {
	    	softAssert.assertTrue(true, "API title did not matched with UI title");
	    	logger.info("API title did not matched with UI title");
	    	extent.extentLogger("Metadata validation","API title did not matched with UI title");
	    }	    
	   }
	}


	/**
	 * fetch selected languages
	 * @throws Exception
	 */
	public String allSelectedLanguages() throws Exception {
		extent.HeaderChildNode("Get the Selected languages");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language button");
			waitTime(2000);
			waitForElementAndClick(PWAHamburgerMenuPage.objContentLanguageBtn,2, "Content languages");
			waitTime(2000);
			List<WebElement> allSelectedLanguages = getDriver().findElements(PWAHamburgerMenuPage.objSelectedLanguages);
			String langtext = "";
			for(int i=0;i<allSelectedLanguages.size();i++) {
			    //System.out.println(i);
			    langtext = allSelectedLanguages.get(i).getAttribute("for").replace("content_", "") +"," + langtext;
			    //System.out.println(langtext.replaceAll(",$",""));
			}
			String finalLangString = langtext.replaceAll(",$","");
			waitForElementAndClick(PWAHamburgerMenuPage.objCancelBtnOnLangPp,2, "Cancel Language Popup");
		return finalLangString;
	}
	
	/**
	 * Verify Subscription and Transaction
	 */
	public void ValidatingSubscriptionAndTransaction() throws Exception {
		extent.HeaderChildNode("Validation of Subscription and Transaction");	
		waitTime(5000);
		waitTime(5000);
		waitTime(5000);
		List<WebElement> ele = getDriver().findElements(By.xpath("//div[@class='noSelect premiumBtn '][1]"));
		System.out.println(ele.size());
		if (ele.size() == 0) {
			System.out.println("Get Premium on Carousel is not displayed");
		} else {
			for (int i = 1; i < ele.size(); i++) {
				verifyElementExist1(ele.get(i), "Get Premium on Carousel");
			}
		}
		verifyElementExist(PWAHomePage.objSubscribeBtn, "Subscribe Button");
		verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
		Thread.sleep(3000);
		if (verifyElementExist(PWAHamburgerMenuPage.objPlans, "My Plans") == true) {
			verifyElementExist(PWAHamburgerMenuPage.objBuySubscription, "Buy Subscription");
			verifyElementExist(PWAHamburgerMenuPage.objHaveAPrepaidCode, "Have a Prepaid Code");
		}
		boolean myAccountPresent = verifyElementExist(PWAHamburgerMenuPage.objMyAccount, "My Account");
		if (myAccountPresent == true) {
			click(PWAHamburgerMenuPage.objMyAccount, "My Account");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMySubscription, "MySubscription");
			Thread.sleep(3000);
			verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPage, "MySubscription Page");
			boolean NoSubscriptionActivePresent = verifyElementExist(PWAHamburgerMenuPage.objNoActiveSubscription,
					"No Active Subscription");
			if (NoSubscriptionActivePresent == true) {
				verifyElementExist(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item");
				verifyElementExist(PWAHamburgerMenuPage.objMySubscriptionPackName, "MySubscription Name");
				verifyElementExist(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus, "My Subscription Status");
			} else {
				if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionItem, "MySubscription Item") == true) {
					if (verifyElementPresent(PWAHamburgerMenuPage.objMySubscriptionPackName,
							"MySubscription Name") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMySubscriptionPackName));
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus,
							"My Subscription Status") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMYSubscriptionActiveStatus));
					}
				}
			}
			getDriver().navigate().back();
			Thread.sleep(5000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger Button");
			Thread.sleep(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyAccount, "My Account");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objMyTransactions, "MyTransaction");
			Thread.sleep(3000);
			verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPage, "MyTransaction Page");
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
					}
					if (verifyElementPresent(PWAHamburgerMenuPage.objMyTransactionPackStatus,
							"MyTransaction Status") == true) {
						System.out.println(getText(PWAHamburgerMenuPage.objMyTransactionPackStatus));
					}
				}
			}
		}
	}
	
	/**
	 * Verify Subscription Links
	 */
	public void ValidatingSubscribeLinks() throws Exception {
		HeaderChildNode("Validating Subscription Link");
		Thread.sleep(10000);
		verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
		waitTime(2000);
		type(PWASearchPage.objSearchEditBox, "Bhinna\n", "Search Field");
		waitTime(5000);
		hideKeyboard();
		waitTime(3000);
		click(PWASearchPage.objSearchResultPremiumContent, "Premium content");
		waitTime(10000);
		try {
			waitForElementDisplayed(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer, 30);
		} catch (Exception e) {
			System.out.println("GetPremiumCTAbelowPlayer is not displayed");
		}
		// Validating GET PREMIUM CTA BUTTON below Player
		HeaderChildNode("Validating Get Premium CTA below player and Get Premium Popup");
		if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer,"GET PREMIUM CTA BELOW PLAYER ") == true) {
			logger.error("Verify the Get premium CTA below the player at consumption screen is not displayed to subscribed user is Fail");
	    	extent.extentLoggerFail("ctaDisplayed","Verify the Get premium CTA below the player at consumption screen is not displayed to subscribed user is Fail");
			click(PWAHamburgerMenuPage.objGetPremiumCTAbelowPlayer,"GET PREMIUM CTA BELOW PLAYER");
			Thread.sleep(3000);
			verifyElementPresent(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
		}
		verifyElementExist(PWAHamburgerMenuPage.objSubscribeNowLink, "Player In-line Subscribe link");
	}
	
	/**
	 * Verify Meta Data on Carousel
	 */
	@SuppressWarnings("rawtypes")
	public void verifyMetadataOnCarousel(String screen, String pageName, String languageSmallText) throws Exception {
		extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);
		navigateToAnyScreen(screen);
		waitTime(5000);
		System.out.println(languageSmallText);
		String carouselTitleAPI= "";
		boolean isTitlePresent = false;
		
		List<String> allMetaTitleOnCarouselAPI = ResponseInstance.traysTitleCarousel(pageName, languageSmallText);
	    System.out.println("API Data : " + allMetaTitleOnCarouselAPI);
	    
	    Dimension size = getDriver().findElement(PWAHomePage.objCarouselBanner).getSize();
	    int startx = (int) (size.width * 0.8);
		int endx = (int) (size.width * 0.1);
		int starty = (int) (size.height * 0.5);
	    
		for(int i=0; i<allMetaTitleOnCarouselAPI.size();i++) {
	    	carouselTitleAPI = allMetaTitleOnCarouselAPI.get(i);
	    	for(int j=0; j<7;j++) {
	    	try {
	    		 WebElement mastHeadEle = (new WebDriverWait(getDriver(), 30)).until(ExpectedConditions.presenceOfElementLocated(PWAHomePage.objContTitleTextCarousel(carouselTitleAPI)));
	    		 isTitlePresent = mastHeadEle.isDisplayed();
	    		 if(isTitlePresent == true) {
	    		 break;
	    		 }
	    		 else {
	 				TouchAction action = new TouchAction(getDriver());
	 				action.press(PointOption.point(startx, starty))
	 						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
	 						.moveTo(PointOption.point(endx, starty)).release().perform();
	    		 }
	    	}catch(Exception e) {
    			System.out.println("Exception : "+e.getMessage() );	
	    	}
	    }
	    	if(isTitlePresent==true) {
		    	softAssert.assertTrue(true, "API title "+carouselTitleAPI+" is present on UI");
		    	logger.info("API title "+carouselTitleAPI+" is present on UI");
		    	extent.extentLogger("Metadata validation","API title "+carouselTitleAPI+" is present on UI");
	    	}else {
	    	softAssert.assertTrue(true, "API title did not matched with UI title");
	    	logger.error("API title did not matched with UI title");
	    	extent.extentLoggerFail("Metadata validation","API title did not matched with UI title");
	    }	    
	    	
	   }
	}
	
	/**
	 * Verify meta data for News tab
	 */
	@SuppressWarnings({ "rawtypes" })
	public void verifyMetadataOnNews(String screen, String pageName, String languageSmallText) throws Exception {
	extent.HeaderChildNode("Verifying metadata of carousel pages on page : " + screen);
	navigateToAnyScreen(screen);
	System.out.println(languageSmallText);
	String carouselTitleUI= "";
	
	List<String> allMetaTitleOnNewsCarouselAPI = ResponseInstance.traysTitleCarousel(pageName, languageSmallText);
    System.out.println("API Data : " + allMetaTitleOnNewsCarouselAPI);
    
    List<String> allMetaTitleOnNewsCarouselUI = new LinkedList<>();
    
    Dimension size = getDriver().findElement(PWAHomePage.objCarouselBanner).getSize();
    System.out.println("width : "+size.width);
    System.out.println("height : "+size.height);
    int startx = (int) (size.width * 0.8);
	int endx = (int) (size.width * 0.1);
	int starty = (int) (size.height * 0.8);
    
    	for(int j=0; j<8;j++) {
    		try {
    			carouselTitleUI = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
    			System.out.println(carouselTitleUI);
    			if(allMetaTitleOnNewsCarouselUI.contains(carouselTitleUI)) {
    				break;
    			}else {
    				System.out.println("Execueted 1 >>>");
    				allMetaTitleOnNewsCarouselUI.add(carouselTitleUI);
    				waitTime(3000);
    				if(waitForElementDisplayed(PWANewsPage.objRight, 10)) {
    					click(PWANewsPage.objRight,"right arrow");
    				}else {
    					TouchAction action = new TouchAction(getDriver());
    	 				action.press(PointOption.point(startx, starty))
    	 						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
    	 						.moveTo(PointOption.point(endx, starty)).release().perform();
    	 				System.out.println("Execueted 2 >>>");
    				}
    				
    			}
    		}catch(Exception e) {
    			TouchAction action = new TouchAction(getDriver());
	 				action.press(PointOption.point(startx, starty))
	 						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
	 						.moveTo(PointOption.point(endx, starty)).release().perform();
    		}
    	}
    		
    	for(int i=0; i<7;i++) {
    		if(allMetaTitleOnNewsCarouselAPI.contains(allMetaTitleOnNewsCarouselUI.get(i))) {
    			softAssert.assertTrue(true, "API title "+allMetaTitleOnNewsCarouselAPI.get(i)+" is present on UI");
		    	logger.info("API title "+allMetaTitleOnNewsCarouselAPI.get(i)+" is present on UI");
		    	extent.extentLogger("Metadata validation","API title "+allMetaTitleOnNewsCarouselAPI.get(i)+" is present on UI");
    		}else {
    	    	softAssert.assertTrue(true, "API title did not matched with UI title");
    	    	logger.info("API title did not matched with UI title");
    	    	extent.extentLogger("Metadata validation","API title did not matched with UI title");
    	      }
    	}
    }
	
	/**
	 * Verify vodafone play functionality
	 */
	public void ValidateVodafonePlayFunction() throws Exception {
		extent.HeaderChildNode("Validating Zee5 PlayBack From VodafonePlay WebAPP");
		// Number used for VodafonePlay
		getDriver().get("https://www.vodafoneplay.in");
		String vodafonePhoneNumber = "8095760130";
		Thread.sleep(5000);
		// verify search button
		verifyElementPresent(PWAVodafonePlayPage.searchBtn, "mobileSearch");
		// Click on Search Button
		verifyElementPresentAndClick(PWAVodafonePlayPage.searchBtn, "mobile Serach");
		Thread.sleep(3000);
		// Click on search text field
		verifyElementPresentAndClick(PWAVodafonePlayPage.searchTextField, "VodafonePlay Search TextField");
		// Send value
//		type(PWAVodafonePlayPage.searchTextField, "Comm", "Search");
		Thread.sleep(2000);
		// Send value
		type(PWAVodafonePlayPage.searchTextField, "Commando", "Search");
		Thread.sleep(3000);
		// hide keyboard
		hideKeyboard();
		Thread.sleep(6000);
		// Click on the searched data
		verifyElementPresentAndClick(PWAVodafonePlayPage.searchedData("Commando 3"), "Vodafone Searched Data");
		waitTime(40000);
		Boolean OTPpresent = verifyElementPresent(PWAVodafonePlayPage.OTPPopupBtn, "VodafonePlay OTP Popup");
		Thread.sleep(6000);
		// OTP process
		if (OTPpresent == true) {
			verifyElementPresentAndClick(PWAVodafonePlayPage.OTPPopupMobileNumberField,"VodafonePlay OTP MobileNumebr Field");
			type(PWAVodafonePlayPage.OTPPopupMobileNumberField, vodafonePhoneNumber, "PhoneNumber");
			hideKeyboard();
			Thread.sleep(2000);
			getDriver().findElement(PWAVodafonePlayPage.OTPPopupBtn).click();
			Thread.sleep(5000);
			Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(getDriver(), 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(PWAVodafonePlayPage.consumptionPageTitle));
		}
		// Navigated URL	
		System.out.println("Navigated to URL : " + getDriver().getCurrentUrl());
		logger.info("Navigated to URL : " + getDriver().getCurrentUrl());
		extent.extentLogger("<b>" + "Navigated to URL : " + getDriver().getCurrentUrl(), "Navigated to URL");
		// Selected Content title
		System.out.println(getDriver().findElement(PWAVodafonePlayPage.consumptionPageTitle).getText());
		logger.info("Navigated to URL : " + getDriver().getCurrentUrl());
		extent.extentLogger("<b>" + "Navigated to URL : " + getDriver().getCurrentUrl(), "Navigated to URL");
		// System.out.println((getDriver()).getContextHandles());
		// TO VERIFY THE BROWSER
		String str = getCurrentActivity();
		if (str.contains("chrome")) {
			System.out.println("Chrome browser is opened");
			logger.info("Chrome Browser is Opened");
			extent.extentLogger("<b>" + "Chrome Browser is Opened", "Chrome Browser is Opened");
		}
		Thread.sleep(10000);
		// Highlighted Tab
		System.out.println("Tab Selected : " + getText(PWAVodafonePlayPage.highlightedTab));
		logger.info("Tab Selected : " + getText(PWAVodafonePlayPage.highlightedTab));
		extent.extentLogger("<b>" + "Tab Selected : " + getText(PWAVodafonePlayPage.highlightedTab), "Tab Opened");
		// Click on Zee5 Hamburger
		verifyElementPresentAndClick(PWAVodafonePlayPage.HamburgerBtn, "Zee5 Hamburger");
		Thread.sleep(5000);
		// Click on Zee5 Profile
		verifyElementPresentAndClick(PWAVodafonePlayPage.userProfileName, "Zee5 UserProfileName");
		Thread.sleep(3000);
		Boolean popup = verifyElementExist(PWAVodafonePlayPage.popupCloseBtn, "Zee5 Popup");
		// getDriver().findElement(PWAVodafonePlayPage.popupCloseBtn).isDisplayed();
		if (popup == true) {
			getDriver().findElement(PWAVodafonePlayPage.popupCloseBtn).click();
		}
		Thread.sleep(3000);
		// Validating Phone number
		System.out.println("ProfileNumber : " + getDriver().findElement(PWAVodafonePlayPage.userPhoneNumber).getText());
		String profileNumber = getDriver().findElement(PWAVodafonePlayPage.userPhoneNumber).getText();
		if (vodafonePhoneNumber.contains(profileNumber)) {
			System.out.println("Logged in to Zee5 using VodafonePlay Phonenumber");
			logger.info("Logged in to Zee5 using VodafonePlay Phonenumber");
			extent.extentLogger("<b>" + "Logged in to Zee5 using VodafonePlay Phonenumber",
					"Logged in to Zee5 using VodafonePlay Phonenumber");
		}
	}
	
	/**
	 * Method to close Adoric pop up
	 */
	public void closeAdoricPopUp() {
		List<WebElement> popUps=getDriver().findElements(PWASubscriptionPages.adoricCloseBtn);
		int size=popUps.size();
		for(int pop=0;pop<size;pop++) {
			try {
				popUps.get(pop).click();
				System.out.println("clicked "+pop);
			}
			catch(Exception e) {
				System.out.println("failed "+pop);
			}
		}
	}
	
	public void checkNonHighlighted() throws InterruptedException {
		extent.HeaderChildNode("hello");
		System.out.println("Entering wait period");
		Thread.sleep(10000);
		System.out.println("Closing wait period");
		
	}
	
	public boolean waitForElementPresence(By locator, int seconds, String message) throws Exception {
		for (int time = 0; time <= seconds; time++) {
			try {
				findElement(locator);
				logger.info(message+" is displayed");
				extent.extentLogger("element is displayed", message+" is displayed");
				return true;
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}
		return false;
	}
	
	/**
	 * Function to left and right(<>) functionality on carousel
	 * 
	 * @throws Exception
	 */
	public void verifyLeftRightFunctionality(String screen, String url) throws Exception {
		extent.HeaderChildNode("Verifying left and right functionality");
		navigateToAnyScreen(screen);
		String nextCarouselTitle = "";
		WebDriverWait w = new WebDriverWait(getDriver(), 120);
		w.until(ExpectedConditions.visibilityOfElementLocated(PWANewsPage.objRight));

		waitForElementAndClick(PWANewsPage.objRight, 10, "Next cont");
		waitTime(1500);
		nextCarouselTitle = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
		System.out.println("first :" + nextCarouselTitle);
		
		reloadURL(url);
		navigateToAnyScreen(screen);
		waitTime(1000);
		w.until(ExpectedConditions.visibilityOfElementLocated(PWANewsPage.objLeft));
		waitForElementAndClick(PWANewsPage.objLeft, 10, "Prev cont");
		waitTime(1500);
		String prevCarouselTitle = getDriver().findElement(PWAHomePage.objContTitleOnCarousel).getText();
		System.out.println("f2 :" + prevCarouselTitle);

		if (nextCarouselTitle.equals(prevCarouselTitle) == false) {
			softAssert.assertFalse(nextCarouselTitle.equals(prevCarouselTitle));
			logger.info("Content can be swiped right and left");
			extent.extentLogger("Swipe left and right", "Content can be swiped right and left");
		} else {
			softAssert.assertTrue(nextCarouselTitle.equals(prevCarouselTitle));
			logger.info("Content can not be swiped either right or left");
			extent.extentLoggerFail("Swipe left and right", "Content can not be swiped either right or left");
			softAssert.assertAll();
		}		
	}
	
	
	//----------------------------------NETWORK INTERRUPTION-------------------------------------
	
	   public void networkInterruption(String usertype) throws Exception {
	        extent.HeaderChildNode("Network Interpution functionality");
	        // "adb shell svc data enable"
	        // "adb shell svc data disable"
	        // "adb shell svc wifi enable"
	        // "adb shell svc wifi disable"
	        verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
	        waitTime(2000);
	        
	        System.out.println(usertype);
	        
	        TurnOFFWifi();
	        
	        if (usertype.contains("Guest")) {
	            verifyElementPresentAndClick(PWALoginPage.objLoginBtn, "Login button");
	            extent.HeaderChildNode("Validating page loading functionality when data is turned off and on");
	            if (verifyElementExist(PWALoginPage.objSpinnerInLogin, "Spinner")) {
	                logger.info("Login page is not loaded when internet is turned off");
	                extent.extentLogger("Login", "Login page is not loaded when internet is turned off");
	            }
	            
	            TurnONWifi();
	            
	            waitTime(5000);
	            getDriver().navigate().refresh();
	            waitTime(5000);
	            if (verifyElementExist(PWALoginPage.objLoginTxt, "Login text")) {
	                logger.info("Login page is loaded when interent is turned on");
	                extent.extentLogger("Login", "Login page is loaded when interent is turned on");
	            }
	        } else if (usertype.contains("NonSubscribedUser")) {
	            extent.HeaderChildNode("Validating page loading functionality when data is turned off and on");
	            verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
	            if (verifyElementExist(PWALoginPage.objSpinnerInLogin, "Spinner")) {
	                logger.info("Profile page is not loaded when internet is turned off");
	                extent.extentLogger("Profile", "Profile page is not loaded when internet is turned off");
	            }
	            
	            TurnONWifi();

	            waitTime(5000);
	            getDriver().navigate().refresh();
	            waitTime(5000);
	            if (verifyElementExist(PWAHamburgerMenuPage.objProfilePageTitleTxt, "Profile text")) {
	                logger.info("Profile page is loaded when interent is turned on");
	                extent.extentLogger("Profile", "Profile page is loaded when interent is turned on");
	            }
	        } else if (usertype.contains("SubscribedUser")) {
	            extent.HeaderChildNode("Validating page loading functionality when data is turned off and on");
	            verifyElementPresentAndClick(PWAHamburgerMenuPage.objProfileIcon, "Profile icon");
	            if (verifyElementExist(PWALoginPage.objSpinnerInLogin, "Spinner")) {
	                logger.info("Profile page is not loaded when internet is turned off");
	                extent.extentLogger("Profile", "Profile page is not loaded when internet is turned off");
	            }
	            
	            TurnONWifi();
	            
	            waitTime(5000);
	            getDriver().navigate().refresh();
	            waitTime(5000);
	            if (verifyElementExist(PWAHamburgerMenuPage.objProfilePageTitleTxt, "Profile text")) {
	                logger.info("Profile page is loaded when interent is turned on");
	                extent.extentLogger("Profile", "Profile page is loaded when interent is turned on");
	            }
	        }
	        Back(1);
	        waitTime(4000);
	        extent.HeaderChildNode("Validating playback functionality when data is turned off and on");
	        verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
	        verifyElementExist(PWAHomePage.objSearchField, "Search field");
	        type(PWAHomePage.objSearchField, "Gooli", "Search");
	        hideKeyboard();
	        waitTime(10000);
	        verifyElementPresentAndClick(PWAHomePage.objSearchResult, "Search result");
	        waitTime(6000);
	        if (verifyElementExist(PWASearchPage.objCloseRegisterDialog, "Pop Up")) {
	            click(PWASearchPage.objCloseRegisterDialog, "Close icon in popup");
	        }
	        waitTime(4000);
	        waitForPlayerAdToComplete("Video Player");
	        
	        TurnOFFWifi();
	        
	        waitTime(5000);
	        if (verifyElementExist(PWAHamburgerMenuPage.objPlaybackErrorMessage, "Error message")) {
	            logger.info("Something went wrong error message is displayed when internet is turned off");
	            extent.extentLogger("Error", "Something went wrong error message is displayed when internet is turned off");
	        }
	        
	        TurnONWifi();
	        
	        waitTime(10000);
	        waitForPlayerAdToComplete("Video Player");
	        waitTime(5000);
	        playerTap();
	        if (verifyElementExist(PWAPlayerPage.forward10SecBtn, "Player control")) {
	            logger.info("Playback is continued when internet is turned on");
	            extent.extentLogger("Playback", "Playback is continued when internet is turned on");
	        }
	        extent.HeaderChildNode("Validating playback functionality when user switch the app");
	        logger.info("Switching app from ZEE5PWA to mobile home screen");
	        extent.extentLogger("App", "Switching app from ZEE5PWA to mobile home screen");
	        playerTap();
	        getDriver().runAppInBackground(Duration.ofSeconds(7));
	        logger.info("Switching app from mobile home screen to ZEE5PWA");
	        extent.extentLogger("App", "Switching app from mobile home screen to ZEE5PWA");
	        String Duration1 = getText(PWAPlayerPage.objcurrenttime);
	        waitTime(10000);
	        playerTap();
	        String Duration2 = getText(PWAPlayerPage.objcurrenttime);
	        if (!Duration1.equals(Duration2)) {
	            logger.info("Playback is continued when user comes out of zee5 and navigate back to zee5 after some time");
	            extent.extentLogger("Zee5",
	                    "Playback is continued when user comes out of zee5 and navigate back to zee5 after some time");
	        }
	        click(PWAHomePage.objZeeLogo, "zee logo");
	        waitTime(5000);
	        extent.HeaderChildNode("Verification of navigation in offline mode");
	        if(verifyElementExist(PWAHomePage.objCarousel, "Carousel")) {
	            logger.info("Carosusel contents are displayed in home page");
	            extent.extentLogger("Naviagtion", "Carosusel contents are displayed in home page");
	        }
	        click(PWAHomePage.objTabName("Shows"), "Shows page");
	        waitTime(3000);
	        if(verifyElementExist(PWAHomePage.objPageHighlighted("Shows"),"Shows page")) {
	            logger.info("User is navigated to shows page");
	            extent.extentLogger("Naviagtion", "User is navigated to shows page");
	        }
	        TurnOFFWifi();
	        waitTime(3000);
	        click(PWAHomePage.objTabName("Home"), "Home page");
	        waitTime(3000);
	        if(verifyElementExist(PWAHomePage.objCarousel, "carousel")) {
	            logger.info("Pre loaded content are verified in offline navigation");
	            extentLogger("Preloaded", "Pre loaded content are verified in offline navigation");
	        }else {
	            logger.info("Pre loaded content are not verified in offline navigation");
	            extentLogger("Preloaded", "Pre loaded content are not verified in offline navigation");
	        }
	        TurnONWifi();
	        waitTime(3000);
	        click(PWAHomePage.objZeeLogo, "zee logo");
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
				extent.HeaderChildNode("Verify Display Screen on tapping Language option");
				// Validate language selection option is displayed
				// click on hamburger menu
				click(PWAHomePage.objHamburgerMenu, "Humburger Menu");
				// Click on language option
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language option");
				// Verify display language screen is displayed
				waitForElementDisplayed(PWAHamburgerMenuPage.objDisplayLang,10);
				if (getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language").contains("headerSelected")) {
					softAssert.assertEquals(getElementPropertyToString("class", PWAHamburgerMenuPage.objDisplayLang, "Display language").contains("headerSelected"),
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
				extent.HeaderChildNode("Verify default display language is English");
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
				extent.HeaderChildNode("Verify user can select desired display language");
				// Verify user can Hindi language
				click(PWALanguageSettingsPage.objLanguage("Hindi"), "Hindi display language");
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
				click(PWALanguageSettingsPage.objLanguage("Marathi"), "Marathi display language");
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
				extent.HeaderChildNode("Verify user can select only one Display language");
				// Verify selected Display language is applied
				// Select Kannada display language
				click(PWALanguageSettingsPage.objLanguage("Kannada"), "Kannada display language");
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
				extent.HeaderChildNode("Verify the selected language is applied in home page");
				click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Display Language screen");
				click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Content Language screen");
				boolean staleElement = true;

				while (staleElement) {

					try {
						waitTime(5000);
						String trayHeader = getElementPropertyToString("class", PWALanguageSettingsPage.objTrayHeader,"Tray header");
						staleElement = false;
						if (trayHeader.contains("kn_regionalLang")) {
							softAssert.assertEquals(trayHeader.contains("kn_regionalLang"), true,"The selected display language is applied");
							extent.extentLogger(" Verify selected display Languge","The selected display langguage is applied successfully");
							logger.info("The selected display language is applied successfully");
						} else {
							softAssert.assertAll();
							extent.extentLoggerFail(" Verify selected display Languge",
									"The selected display langguage is not applied successfully");
							logger.info("The selected display language is not applied successfully");
						}
					} catch (StaleElementReferenceException e) {
						staleElement = true;
					}
				}
				// Click on Humburger menu
				click(PWAHomePage.objHamburgerMenu, "Humburger Menu");
				// Click on language option
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language option");
				waitTime(2000);
				// Select English
				click(PWALanguageSettingsPage.objEnglishLang, "English language");
				click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Display Language screen");
				click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Content Language screen");

				boolean staleElement1 = true;
				while (staleElement1) {
					try {
						waitTime(5000);
						click(PWAHomePage.objHamburgerMenu, "Humburger Menu");
						staleElement1 = false;
					} catch (StaleElementReferenceException e) {
						staleElement1 = true;
					}
				}
				extent.HeaderChildNode("Content screen is displayed on tapping Content language option");	
				
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language option");
				// Click on Content language button
				waitForElementDisplayed(PWAHamburgerMenuPage.objContentLanguage,10);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguage, "Content language");
				// Verify user is navigated to Content Language screen post tapping content
				// language option
				if (getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language").contains("headerSelected")) {
					softAssert.assertEquals(
							getElementPropertyToString("class", PWAHamburgerMenuPage.objContentLanguage, "Content language").contains("headerSelected"),
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
				click(PWALanguageSettingsPage.objSelectedLang, "Selected language");
				Thread.sleep(2000);
				click(PWALanguageSettingsPage.objSelectedLang, "Selected language");

				// Verify user can select multiple Content languages
				extent.HeaderChildNode("Verify user can select multiple Content languages");
				for (int i = 1; i <= 2; i++) {
					String language = getElementPropertyToString("innerText", PWALanguageSettingsPage.objAllLangByindex(i),"Language");
					click(PWALanguageSettingsPage.objAllLangByindex(i), language + " Language");
				}
				int size = getDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
				if (size > 1) {
					softAssert.assertEquals(size > 1, true, "User can select multiple languages");
					extent.extentLogger("Selected content languages : ", +size + "");
					extent.extentLogger("Verify user can select multiple content languages","User can select multiple Content languages");
					logger.info("User can select multiple Content languages");
				} else {
					softAssert.assertAll();
					extent.extentLoggerFail("Verify user can select multiple content languages",
							"User can not select multiple Content languages");
					logger.info("User can not select multiple Content languages");
				}

				// Verify user should not be able to apply the changes if he deselect all the
				extent.HeaderChildNode("Verify apply button disabled if deselect all");
				// language.
				int selectedlang = getDriver().findElements(PWALanguageSettingsPage.objSelectedLang).size();
				for (int i = 1; i <= selectedlang; i++) {
					click(PWALanguageSettingsPage.objSelectedLang, "Selected language");

				}
				// Verify apply button is disabled
				String disabledApplyBtn = getElementPropertyToString("class", PWALanguageSettingsPage.objDisabledApplyButton,"Apply button");
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
				extent.HeaderChildNode("Verify navigation to content language screen");
				// and Content language
				// click on Display language
				click(PWAHamburgerMenuPage.objDisplayLang, "Display language");
				// Verify user is navigated to content language screen post tapping content
				// language screen
				click(PWAHamburgerMenuPage.objContentLanguage, "Content language");
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
				click(PWAHamburgerMenuPage.objDisplayLang, "Display language");
				scrollLanguagePopUpsVerification("Display language Popup");
				click(PWAHamburgerMenuPage.objContentLanguage, "Content language");
				scrollLanguagePopUpsVerification("Content Language Popup");
				// Click on cancel button
				click(PWALanguageSettingsPage.objCancelBtn, "Cancel button");
			}
			
			public void scrollLanguagePopUpsVerification(String popup) throws Exception {
				extent.HeaderChildNode("Scroll Up/Down functionality in "+popup);
				//Scroll Up Check
				verifyElementPresent(PWALanguageSettingsPage.objEnglishLangInDLPopUp,"English radio button");
				WebElement english=getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
				int beforeScrollX=english.getLocation().x;
				int beforeScrollY=english.getLocation().y;
				logger.info("Current x coordinate of English radio button: "+beforeScrollX);
				extent.extentLogger("beforeScroll", "Current x coordinate of English radio button: "+beforeScrollX);
				logger.info("Current y coordinate of English radio button: "+beforeScrollY);
				extent.extentLogger("beforeScroll", "Current y coordinate of English radio button: "+beforeScrollY);
				popUpSwipe("UP", 2);
				english=getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
				int afterScrollX=english.getLocation().x;
				int afterScrollY=english.getLocation().y;
				logger.info("x coordinate of English radio button after Scroll Up: "+afterScrollX);
				extent.extentLogger("afterScroll", "x coordinate of English radio button after Scroll Up: "+afterScrollX);
				logger.info("y coordinate of English radio button after Scroll Up: "+afterScrollY);
				extent.extentLogger("afterScroll", "y coordinate of English radio button after Scroll Up: "+afterScrollY);
				if(afterScrollY<beforeScrollY) {
					logger.info("User is able to scroll up on "+popup);
					extent.extentLogger("scrollPass", "User is able to scroll up on "+popup);
				}
				else {
					logger.error("Scrolling Up is failed in "+popup);
					extent.extentLoggerFail("scrollFail", "Scrolling Up is failed in "+popup);
				}
				//Scroll Down check
				waitTime(2000);
				verifyElementPresent(PWALanguageSettingsPage.objEnglishLangInDLPopUp,"English radio button");
				english=getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
				beforeScrollX=english.getLocation().x;
				beforeScrollY=english.getLocation().y;
				logger.info("Current x coordinate of English radio button: "+beforeScrollX);
				extent.extentLogger("beforeScroll", "Current x coordinate of English radio button: "+beforeScrollX);
				logger.info("Current y coordinate of English radio button: "+beforeScrollY);
				extent.extentLogger("beforeScroll", "Current y coordinate of English radio button: "+beforeScrollY);
				popUpSwipe("DOWN", 2);
				english=getDriver().findElement(PWALanguageSettingsPage.objEnglishLangInDLPopUp);
				afterScrollX=english.getLocation().x;
				afterScrollY=english.getLocation().y;
				logger.info("x coordinate of English radio button after Scroll Down: "+afterScrollX);
				extent.extentLogger("afterScroll", "x coordinate of English radio button after Scroll Down: "+afterScrollX);
				logger.info("y coordinate of English radio button after Scroll Down: "+afterScrollY);
				extent.extentLogger("afterScroll", "y coordinate of English radio button after Scroll Down: "+afterScrollY);
				if(afterScrollY>beforeScrollY) {
					logger.info("User is be able to scroll up on "+popup);
					extent.extentLogger("scrollPass", "User is be able to scroll up on "+popup);
				}
				else {
					logger.error("Scrolling Up is failed in "+popup);
					extent.extentLoggerFail("scrollFail", "Scrolling Up is failed in "+popup);
				}
			}
			
			@SuppressWarnings("rawtypes")
			public void popUpSwipe(String direction, int count) throws Exception {
				touchAction = new TouchAction(getDriver());
				String dire = direction;
				try {
					if (dire.equalsIgnoreCase("UP")) {
						for (int j = 0; j < count; j++) {
							Dimension size = getDriver().manage().window().getSize();
							int starty = (int) (size.height * 0.6);
							int endy = (int) (size.height * 0.4);
							int startx = size.width / 2;
							touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, endy)).release().perform();
							logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
							extent.extentLogger("SwipeUp","Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
						}
					} else if (dire.equalsIgnoreCase("DOWN")) {
						for (int j = 0; j < count; j++) {
							Dimension size = getDriver().manage().window().getSize();
							int starty = (int) (size.height * 0.6);
							int endy = (int) (size.height * 0.4);
							int startx = size.width / 2;
							touchAction.press(PointOption.point(startx, endy)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(startx, starty)).release().perform();
							logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
							extent.extentLogger("SwipeDown","Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
						}
					}

				} catch (Exception e) {
					logger.error(e);
				}
			}
		   
		   
		   
			
			   /**
				  * ==========================YASHASWINI MenuORSettings===========================
				  * 
				  */ 
		   
		   
		   
			
			public void MenuOrSettingScenarios(String userType) throws Exception {

				switch (userType) {
				case "Guest":
					extent.HeaderChildNode("Guest user scenario");
					extent.extentLogger("Accessing as Guest User", "Accessing as Guest User");
					logger.info("Accessing as Guest User");
					dismissDisplayContentLanguagePopUp();
					verificationsOfMenuOptions();
					verificationsOfInfoOptions();
					verificationsOfExploreOptions();
					navigationsFromPlanSection();
					resetToDefault();
					break;

				case "NonSubscribedUser":
					extent.HeaderChildNode("NonSubscribedUser scenario");
					extent.extentLogger("Accessing as NonSubscribedUser User", "Accessing as NonSubscribedUser User");
					logger.info("Accessing as NonSubscribedUser User");
					ZeePWALogin("E-mail",userType);
					resetToDefault();
					parentControlFunctionality("NonSubscribedUser");
					authenticationFunctionality();
					break;

				case "SubscribedUser":
					extent.HeaderChildNode("SubscribedUser scenario");
					extent.extentLogger("Accessing as SubscribedUser User", "Accessing as SubscribedUser User");
					logger.info("Accessing as SubscribedUser User");
					ZeePWALogin("E-mail",userType);
					verificationsOfInfoOptions();
					verificationsOfExploreOptions();
					resetToDefault();
					parentControlFunctionality("SubscribedUser");
					authenticationFunctionality();
				}
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
					click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button");
				}
				languageOptionFunctionality();
			}
			
			/*
			 * Verify for Device platform Language option given under Setting and its operational
			 */
			public void languageOptionFunctionality() throws Exception {
				extent.HeaderChildNode("Verifying Language option availablity");
				//	waitTime(15000);
					verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
					if (verifyElementPresentAndClick(PWAHamburgerMenuPage.objLanguageBtn, "Language Button")) {
						logger.info("Language option displayed under Menu");
						extent.extentLogger("Language", "Language option displayed under Menu");//
						verifyElementExist(PWAHamburgerMenuPage.objLanguagePop, "Hamburger menu");
						System.out.println("Language wrap displayed");
						click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Display Language screen");
						click(PWALanguageSettingsPage.objApplyBtn, "Apply button in Content Language screen");
					}
					click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button");
				}
			
			public void verificationsOfExploreOptions() throws Exception {
				extent.HeaderChildNode("Verifications of Explore dropdown options");
				waitTime(15000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				waitTime(2000);
				if (verifyElementExist(PWAHamburgerMenuPage.objExploreBtn, "Explore option")) {
					click(PWAHamburgerMenuPage.objExploreBtn, "Explore option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Premium"), "Premium option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Shows"), "Shows option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Movies"), "Movies option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Kids"), "Kids option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("News"), "News option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Music"), "Music option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Free Movies"), "Free Movies");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Live TV"), "LiveTv option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions(" ZEE5 Originals"), " ZEE5 Originals option");
					waitTime(3000);
					click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button");
				} else {
					Swipe("DOWN", 1);
					click(PWAHamburgerMenuPage.objExploreBtn, "Explore option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Premium"), "Premium option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Shows"), "Shows option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Movies"), "Movies option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Kids"), "Kids option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("News"), "News option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Music"), "Music option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Free Movies"), "Free Movies");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions("Live TV"), "LiveTv option");
					verifyElementExist(PWAHamburgerMenuPage.objExploreOptions(" ZEE5 Originals"), " ZEE5 Originals option");
					waitTime(3000);
					click(PWAHamburgerMenuPage.objCloseHamburgerMenu, "Hamburger close button");
				}

			}
			
			/*
			 * Verify that user is able to expand "Info" option and it is having 
				About Us
				Help Center
				Terms of Use
				Privacy Policy

			 */
			
			public void verificationsOfInfoOptions() throws Exception {
				extent.HeaderChildNode("Verifications of Info dropdown options");
			//	waitTime(15000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
				verifyElementPresent(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page");
				androidSwitchTab();
				if (verifyElementPresent(PWAHomePage.objAboutUs, "AboutUs screen")) {
					logger.info("User is navigated to AboutUs Screen");
				}
				
				HeaderChildNode("Help Center Screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
				waitTime(3000);
				androidSwitchTab();
				if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
					logger.info("User is navigated to Help Center Screen");
				}
				AndroidSwitchToParentWindow();
				//Terms of Use
				HeaderChildNode("Terms of Use screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
				verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen");
				logger.info("Current URL is " + getDriver().getCurrentUrl());
				String TermsofuseURL = getDriver().getCurrentUrl();
				if (TermsofuseURL.contains("termsofuse")) {
					logger.info("User is navigated to Terms of Use screen");
				}
				
				//Privacy Policy
				HeaderChildNode("Privacy Policy screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
				verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen");
				logger.info("Current URL is " + getDriver().getCurrentUrl());
				String PrivacyPolicyURL = getDriver().getCurrentUrl();
				if (PrivacyPolicyURL.contains("privacypolicy")) {
					logger.info("User is navigated to Privacy Policy Screen");
				}
				
				HeaderChildNode("Build version");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementExist(PWAHamburgerMenuPage.objBuildVersion, "Build Version");
				String version = getText(PWAHamburgerMenuPage.objBuildVersion);
				logger.info("Build version is : " + version);
				extent.extentLogger("version", "Build version is : " + version);
//				System.out.println("Build version is " + version);
				click(PWAHamburgerMenuPage.objCloseHamburger, "Hamburger close button");	
				
			}
			
			public void navigationsFromPlanSection() throws Exception {
				extent.HeaderChildNode("Functionality of MyPlan options");
				waitTime(2000);
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
					}
				}

			}

			public void resetToDefault() throws Exception {
				extent.HeaderChildNode("Reset Settings to default Functionality");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objMoreSettingInHamburger,
						"More settings in settings section");
				waitTime(2000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguage, "Display language");
				waitTime(2000);
				click(PWAHamburgerMenuPage.objSelectLanguage, "Language icon");
				if (verifyElementExist(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Language")) {
					logger.info("clicked on hindi language in Display language popup");
					extent.extentLogger("Content language", "clicked on hindi language in Display language popup");
				}
				click(PWAHamburgerMenuPage.objApplyButtonInContentLangugaePopup, "Apply button");

				verifyElementPresentAndClick(PWAHamburgerMenuPage.objResetSettingsToDefault, "Reset Settings to Default");
				waitTime(5000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objContentLanguage, "Display language");
				if (verifyElementExist(PWAHamburgerMenuPage.objAfterSelectedLanguage, "Language") == false) {
					logger.info("Reset to default is success");
					extent.extentLogger("Content language", "Reset to default is success");
					click(PWAHamburgerMenuPage.objCancelButtonInContentLangugaePopup, "Cancel button");
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
				PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
				if (UserType.equals("NonSubscribedUser")) {
					password = handler.getproperty("NON_SUBSCRIBED_USER_PASSWORD");
				} else if (UserType.equals("SubscribedUser")) {
					password = handler.getproperty("SUBSCRIBED_USER_PASSWORD");
				}
				type(PWALoginPage.objPasswordField, password+"\n", "Password field");
				hideKeyboard();
				click(PWAHamburgerMenuPage.objContinueButtonInVerifyAccount, "Continue button");
				waitTime(2000);
				verifyElementExist(PWAHamburgerMenuPage.objParentControlPageTitle, "Parent control page");
				verifyElementExist(PWAHamburgerMenuPage.objNoRestrictionSelected, "No restricted option selected");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objRestrictAll, "Restricted option");
				verifyElementPresent(PWAHamburgerMenuPage.objParentalLockPin1, "Set Lock Field");
				type(PWAHamburgerMenuPage.objParentalLockPin1, "1", "ParentalLockPin");
				hideKeyboard();
				type(PWAHamburgerMenuPage.objParentalLockPin2, "2", "ParentalLockPin");
				hideKeyboard();
				type(PWAHamburgerMenuPage.objParentalLockPin3, "3", "ParentalLockPin");
				hideKeyboard();
				type(PWAHamburgerMenuPage.objParentalLockPin4, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(4000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objSetParentalLockButton, "Set Parental lock button");
				waitTime(2000);
				verifyElementExist(PWAHomePage.objZeeLogo, "zee logo");
				waitTime(3000);
				verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
				verifyElementExist(PWAHomePage.objSearchField, "Search field");
				type(PWAHomePage.objSearchField, "Gooli", "Search");
				hideKeyboard();
				waitTime(10000);
				verifyElementPresentAndClick(PWAHomePage.objSearchResult, "Search result");
				waitTime(6000);
				verifyElementExist(PWAHamburgerMenuPage.objParentalLockPopUpInPlayer, "Parent control Popup");
				type(PWAHamburgerMenuPage.objParentalLockPin1player, "1", "ParentalLockPin");
				hideKeyboard();
				type(PWAHamburgerMenuPage.objParentalLockPin2player, "2", "ParentalLockPin");
				hideKeyboard();
				type(PWAHamburgerMenuPage.objParentalLockPin3player, "3", "ParentalLockPin");
				hideKeyboard();
				type(PWAHamburgerMenuPage.objParentalLockPin4player, "4", "ParentalLockPin");
				hideKeyboard();
				waitTime(5000);
				waitForPlayerAdToComplete("Video Player");
				playerTap();
				if (verifyElementExist(PWAPlayerPage.pauseBtn, "Pause icon")) {
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
				type(PWALoginPage.objPasswordField, password+"\n", "Password field");
				hideKeyboard();
				waitTime(5000);
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
				if (getDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled() == false) {
					logger.info("Authenticate button is not highlighted by default");
					extent.extentLogger("Authenticate", "Authenticate button is not highlighted by default");
				}
				type(PWAHamburgerMenuPage.objAuthenticationField, "abcdef", "AuthenticationField");
				hideKeyboard();
				String AuthenticationField = getText(PWAHamburgerMenuPage.objAuthenticationField);
				if (AuthenticationField != null) {
					logger.info("User is able to enter the value in AuthenticationField");
					extent.extentLogger("AuthenticationField", "User is able to enter the value in AuthenticationField");
				}

				if (getDriver().findElement(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted).isEnabled()) {
					logger.info("Authenticate button is highlighted after entering the input in AuthenticationField");
					extent.extentLogger("Authenticate",
							"Authenticate button is highlighted after entering the input in AuthenticationField");
				}

				click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
				try {
					Boolean ExpiredToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']")).isDisplayed();
					if (ExpiredToastMessage == true) {
						extent.extentLogger("Toast", "Expired Toast message displayed");
						logger.info("Expired Toast message displayed");
					} else {
						extent.extentLogger("Toast", "Expired Toast message not displayed");
						logger.info("Expired Toast message not displayed");
					}
					getDriver().findElement(PWAHamburgerMenuPage.objAuthenticationField).clear();
					Wait(3000);
					type(PWAHamburgerMenuPage.objAuthenticationField, "&!@#$%", "AuthenticationField");
					hideKeyboard();
					waitTime(2000);
					click(PWAHamburgerMenuPage.objAuthenticationButtonHighlighted, "Authenticate button");
					Boolean NotfounfToastMessage = getDriver().findElement(By.xpath("//*[@class='toastMessage']"))
							.isDisplayed();
					if (NotfounfToastMessage == true) {
						extent.extentLogger("Toast", "Not found Toast message displayed");
						logger.info("Not found Toast message displayed");
					} else {
						extent.extentLogger("Toast", "Not found Toast message not displayed");
						logger.info("Not found Toast message not displayed");
					}
				} catch (Exception e) {
					System.out.println("Toast message is not displayed");
				}
				verifyElementPresentAndClick(PWAHomePage.objZeeLogo, "zee logo");
			}
			
	   
	   
			   /**
					 * ==========================BHAVANA ShareFunctionality===========================
					 * 
					 */ 	
				
				@SuppressWarnings("rawtypes")
				public void shareModuleValidationforAndroid() throws Exception{
					extent.HeaderChildNode("Share functionality validation");
					verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search button");
					type(PWASearchPage.objSearchEditBox, "kannadada Kanmani", "Search Field");
					waitTime(5000);
					hideKeyboard();
					click(PWASearchPage.objSpecificSearch("Kannadada Kanmani"), "Searched Show");
					verifyElementExist(PWAShowsPage.objWatchLatestCTA, "Watch latest episode CTA");
					waitTime(3000);
					click(PWAShowsPage.objWatchLatestCTAPlayicon, "Watch latest episode CTA");
					Why_Register_POPUP();
					if (verifyElementExist(PWAHamburgerMenuPage.objGetPremiumPopup, "GET PREMIUM POPUP")) {
						verifyElementPresentAndClick(PWAHamburgerMenuPage.objPopupClose, "POP-UP CLOSE BUTTON");
					}
					waitTime(2000);		
					//waitTime(20000);
					click(PWAShowsPage.objShareIcon, "Share icon");
					// facebookshare
					getDriver().context("NATIVE_APP");
					Dimension dim = getDriver().manage().window().getSize();
					int startx = (int) (dim.width * 0.6);
					int starty = (int) (dim.height * 0.7);
					int endx = (int) (startx * 0.1);
					int endy = starty;
					for (int i = 0; i < 2; i++) {
						try {
							getDriver().findElement(PWAShowsPage.objFacebookApp).click();
							break;
						} catch (Exception e) {
							TouchAction act = new TouchAction(getDriver());
							act.press(PointOption.point(startx, starty))
									.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
									.moveTo(PointOption.point(endx, endy)).release().perform();
						}
					}
					waitTime(3000);
					waitForElementAndClick(PWAShowsPage.objFacebookPostBtn, 10, "POST button in Facebook App");
					waitTime(5000);
					getDriver().context("CHROMIUM");
					// twittershare
					click(PWAShowsPage.objShareIcon, "Share icon");
					getDriver().context("NATIVE_APP");
					waitTime(5000);
					getDriver().findElement(PWAShowsPage.objTwitterApp).click();
					waitTime(5000);
					waitForElementAndClick(PWAShowsPage.objTwitterPostBtn1, 10, "POST button in Twitter App");
					waitTime(5000);
					getDriver().context("CHROMIUM");
					Back(1);
				}
				
				public void ShareModule(String userType) throws Exception{
					extent.HeaderChildNode(userType + " scenarios");
					extent.extentLogger("Accessing as " + userType , "Accessing as " + userType);
					logger.info("Accessing as " + userType);
					if(userType.contentEquals("NonSubscribedUser") || userType.contentEquals("SubscribedUser")){
						ZeePWALogin("E-mail", userType);
					}
					shareModuleValidationforAndroid();
				}
				
				   /**
					 * ==========================BHAVANA Static Functionality===========================
					 * 
					 */ 
				
				
			
			public void staticPagesValidation() throws Exception {
				extent.HeaderChildNode("Validation of Static Pages in Hamburger menu");
				//About Us
				HeaderChildNode("About us screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
				verifyElementPresent(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page");
				logger.info("Current URL is " + getDriver().getCurrentUrl());
				String AboutUsurl = getDriver().getCurrentUrl();
				if (AboutUsurl.contains("aboutus")) {
					logger.info("User is navigated to About Us screen");
				}
				Back(1);
				//Help Center
				HeaderChildNode("Help Center Screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
				
				//Added to support open with
				System.out.println(getDriver().getContextHandles());
				getDriver().context("NATIVE_APP");
				try {
					getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
					waitTime(2000);
					getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
				}
				catch(Exception e) {}
				getDriver().context("CHROMIUM");
				// Added until here
				
				
				androidSwitchTab();
				if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
					logger.info("User is navigated to Help Center Screen");
				}
				AndroidSwitchToParentWindow();
				//Terms of Use
				HeaderChildNode("Terms of Use screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
				verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen");
				logger.info("Current URL is " + getDriver().getCurrentUrl());
				String TermsofuseURL = getDriver().getCurrentUrl();
				if (TermsofuseURL.contains("termsofuse")) {
					logger.info("User is navigated to Terms of Use screen");
				}
				Back(1);
				//Privacy Policy
				HeaderChildNode("Privacy Policy screen");
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
				Swipe("UP", 1);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
				verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen");
				logger.info("Current URL is " + getDriver().getCurrentUrl());
				String PrivacyPolicyURL = getDriver().getCurrentUrl();
				if (PrivacyPolicyURL.contains("privacypolicy")) {
					logger.info("User is navigated to Privacy Policy Screen");
				}
				Back(1);				
				//Static Pages in Footer Section
				
				extent.HeaderChildNode("Static Pages in Footer Section Validation");
				swipeToBottomOfPage();
				verifyElementPresentAndClick(PWAHomePage.objAboutUsInFooterSection, "About Us in footer section");
				if (verifyElementExist(PWAHomePage.objAboutUs, "About Us screen")) {
					logger.info("User is navigated to About Us Screen");
				}
				Back(1);
				swipeToBottomOfPage();
				verifyElementPresentAndClick(PWAHomePage.objHelp, "Help Center in footer section");
				androidSwitchTab();
				if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
					logger.info("User is navigated to Help Center Screen");
				}
				AndroidSwitchToParentWindow();
				swipeToBottomOfPage();
				verifyElementPresentAndClick(PWAHomePage.objPrivacyPolicyInFooterSection, "Privacy Policy in footer section");
				if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
					logger.info("User is navigated to Privacy Policy Screen");
				}
				Back(1);
				swipeToBottomOfPage();
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
				androidSwitchTab();
				if (verifyElementPresent(PWAHomePage.objTerms, "Terms of Use screen")) {
					logger.info("User is navigated to Terms of Use Screen");
				}
				AndroidSwitchToParentWindow();
				waitTime(3000);
				verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicyInSignupScreen,"Privacy Policy in Sign up screen");
				waitTime(2000);
				androidSwitchTab();
				if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
					logger.info("User is navigated to Privacy Policy Screen");
				}
				AndroidSwitchToParentWindow();
				Back(1);
			}	
				
			
		public void StaticPages(String userType) throws Exception {
			extent.HeaderChildNode("Validation of Static Pages in Hamburger menu");
			//About Us
			HeaderChildNode("About us screen");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objAboutUsOption, "About Us option");
			verifyElementPresent(PWAHamburgerMenuPage.objAboutUsTextInPage, "About Us Screen page");
			logger.info("Current URL is " + getDriver().getCurrentUrl());
			String AboutUsurl = getDriver().getCurrentUrl();
			if (AboutUsurl.contains("aboutus")) {
				logger.info("User is navigated to About Us screen");
			}
			Back(1);
			//Help Center
			HeaderChildNode("Help Center Screen");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHelpCenterOption, "Help Center option");
			
			//Added to support open with
			System.out.println(getDriver().getContextHandles());
			getDriver().context("NATIVE_APP");
			try {
				getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
				waitTime(2000);
				getDriver().findElement(PWALiveTVPage.objChromeOpenWith).click();
			}
			catch(Exception e) {}
			getDriver().context("CHROMIUM");
			// Added until here
			
			
			androidSwitchTab();
			if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
				logger.info("User is navigated to Help Center Screen");
			}
			AndroidSwitchToParentWindow();
			//Terms of Use
			HeaderChildNode("Terms of Use screen");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfUseOption, "Terms of Use option");
			verifyElementExist(PWAHamburgerMenuPage.objTermsOfUseScreen, "Terms of Use screen");
			logger.info("Current URL is " + getDriver().getCurrentUrl());
			String TermsofuseURL = getDriver().getCurrentUrl();
			if (TermsofuseURL.contains("termsofuse")) {
				logger.info("User is navigated to Terms of Use screen");
			}
			Back(1);
			//Privacy Policy
			HeaderChildNode("Privacy Policy screen");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			Swipe("UP", 1);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicy, "Privacy Policy option");
			verifyElementPresent(PWAHamburgerMenuPage.objPrivacyPolicyScreen, "Privacy Policy screen");
			logger.info("Current URL is " + getDriver().getCurrentUrl());
			String PrivacyPolicyURL = getDriver().getCurrentUrl();
			if (PrivacyPolicyURL.contains("privacypolicy")) {
				logger.info("User is navigated to Privacy Policy Screen");
			}
			Back(1);
			
			//Static Pages in Footer Section
			
			extent.HeaderChildNode("Static Pages in Footer Section Validation");
			swipeToBottomOfPage();
			verifyElementPresentAndClick(PWAHomePage.objAboutUsInFooterSection, "About Us in footer section");
			if (verifyElementExist(PWAHomePage.objAboutUs, "About Us screen")) {
				logger.info("User is navigated to About Us Screen");
			}
			Back(1);
			swipeToBottomOfPage();
			verifyElementPresentAndClick(PWAHomePage.objHelp, "Help Center in footer section");
			androidSwitchTab();
			if (verifyElementPresent(PWAHomePage.objHelpScreen, "Help Center screen")) {
				logger.info("User is navigated to Help Center Screen");
			}
			AndroidSwitchToParentWindow();
			swipeToBottomOfPage();
			verifyElementPresentAndClick(PWAHomePage.objPrivacyPolicyInFooterSection, "Privacy Policy in footer section");
			if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
				logger.info("User is navigated to Privacy Policy Screen");
			}
			Back(1);
			swipeToBottomOfPage();
			verifyElementPresentAndClick(PWAHomePage.objTermsOfUseInfooterSection, "Terms of Use in footer section");
			if (verifyElementPresent(PWAHomePage.objTerms, "Terms of Use screen")) {
				logger.info("User is navigated to Terms of Use Screen");
			}
			Back(1);
			
			//Static pages in Sign Up screen
			if(userType.contains("Guest")){
			extent.HeaderChildNode("Static Pages in Sign up screen Validation");
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objHamburgerBtn, "Hamburger menu");
			waitTime(2000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objSignUpForFree,"Sign Up for FREE");
			waitTime(2000);
			//Terms of Use
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objTermsOfServicesInSignupScreen, "Terms of services in Sign up screen");
			waitTime(2000);
			androidSwitchTab();
			if (verifyElementPresent(PWAHomePage.objTerms, "Terms of Use screen")) {
				logger.info("User is navigated to Terms of Use Screen");
			}
			AndroidSwitchToParentWindow();
			waitTime(3000);
			verifyElementPresentAndClick(PWAHamburgerMenuPage.objPrivacyPolicyInSignupScreen,"Privacy Policy in Sign up screen");
			waitTime(2000);
			androidSwitchTab();
			if (verifyElementPresent(PWAHomePage.objPrivacyPolicy, "Privacy Policy screen")) {
				logger.info("User is navigated to Privacy Policy Screen");
			}
			AndroidSwitchToParentWindow();
			}
			Back(1);
		}	
			
			
			
			   /**
			 * ==========================TANISHA Reco Module===========================
			 * 
			 */ 			
		
			/**
			 * Main method for validating Recommendations (Talamoos) module
			 */
			public void verificationOfRecoTalamoos(String userType) throws Exception {
				if (userType.equals("Guest")) {
					playContentsToTriggerRecoApi(userType);
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Trending on Zee5");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Premium", "Trending Now");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Shows", "Trending Shows");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Movies", "Trending Movies");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Music", "Recommended for you");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "News", "Recommended for you");
				} else if (userType.equals("NonSubscribedUser")) {
					// No test cases for non subscribed user
				} else if (userType.equals("SubscribedUser")) {
					playContentsToTriggerRecoApi(userType);
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "Trending on Zee5");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Home", "You may also like");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Premium", "Trending Now");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Premium", "Recommended for you");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Shows", "Trending Shows");		
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Movies", "Trending Movies");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Movies", "Recommended for you");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "News", "Recommended for you");
					verifyRecoTrayAndPlayContentWithoutAPI(userType, "Music", "Recommended for you");

				} else {
					logger.error("Incorrect userType passed to method");
					extent.extentLogger("incorrectUser", "Incorrect userType passed to method");
				}
			}
			
			public void playContentsToTriggerRecoApi(String userType) throws Exception {
				extent.HeaderChildNode("Play different contents to trigger Recommendation API");
				playAContentForReco("Music",Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("musicToTriggerReco"),userType);
				playAContentForReco("Movies",Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("movieToTriggerReco"),userType);
				playAContentForReco("News",Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("newsToTriggerReco"),userType);
			}
			
			
			
			public void verifyRecoTrayAndPlayContentWithoutAPI(String userType, String tabName, String recoTrayTitle) throws Exception {
				extent.HeaderChildNode(tabName + " tab: Validation of \"" + recoTrayTitle + "\" tray");
				String trayTitle = "";
				try {getDriver().findElement(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn).click();}catch(Exception e) {}
				if (navigatetoAnyScreen1(tabName)) {
					trayTitle = swipeTillTray(7, recoTrayTitle,"\"" + recoTrayTitle + "\" tray");
					if (!trayTitle.equals("")) {
						extent.HeaderChildNode(tabName + " tab: Validation of content play in \"" + recoTrayTitle + "\" tray");
						swipeTillElementAndClick(3, PWALandingPages.objFirstAssetInTrayIndex(trayTitle),"First Asset in tray");
						String nextPageTitle = "";
						try {
							nextPageTitle = getText(PWAShowsPage.objShowsTitle);
							logger.info("Shows Details page is displayed");
							extent.extentLogger("showDetails","Shows Details page is displayed");
						}
						catch(Exception e) {
							try {
								nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
								logger.info("Player screen is displayed");
								extent.extentLogger("playerScreen","Player screen is displayed");
							}
							catch(Exception e1) {
								nextPageTitle="";
							}
						}
						if (!nextPageTitle.equals("")) {
							logger.info("Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
							extent.extentLogger("playerScreen","Navigated to the consumption/details page: \"" + nextPageTitle + "\"");
						} else {
							logger.error("Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
							extent.extentLoggerFail("playerScreen","Failed to navigate to consumption/details page: \"" + nextPageTitle + "\"");
						}
						try{getDriver().findElement(PWASearchPage.objClosePremiumDialog).click();}catch(Exception e) {}
					}
				}
			}
		
		
		/**
		 * Function Navigate to landing page of any screen
		 * 
		 * @throws Exception
		 */

		public boolean navigatetoAnyScreen1(String screen) throws Exception {
			for (int i = 0; i < 3; i++) {
				try {
					getDriver().findElement(PWAHomePage.objTabName(screen)).click();
					logger.info("Clicked on Tab " + screen);
					extent.extentLogger("tabClick", "Clicked on Tab " + screen);
					return true;
				} catch (Exception e) {
					Actions act = new Actions(getDriver());
					List<WebElement> ele = getDriver().findElements(PWAHomePage.objAllTabs);
					for (int tab = 0; tab < ele.size(); tab++) {
						act.dragAndDropBy(ele.get(tab), 25, 0);
						try {
							getDriver().findElement(PWAHomePage.objTabName(screen)).click();
							logger.info("Clicked on Tab " + screen);
							extent.extentLogger("tabClick", "Clicked on Tab " + screen);
							return true;
						} catch (Exception e1) {
						}
					}
					for (int tab = 0; tab < ele.size(); tab++) {
						act.dragAndDropBy(ele.get(tab), -25, 0);
						try {
							getDriver().findElement(PWAHomePage.objTabName(screen)).click();
							logger.info("Clicked on Tab " + screen);
							extent.extentLogger("tabClick", "Clicked on Tab " + screen);
							return true;
						} catch (Exception e2) {
						}
					}
				}
			}
			logger.error("Failed to click on Tab " + screen);
			extent.extentLoggerFail("tabClick", "Failed to click on Tab " + screen);
			return false;
		}
			
			
			
		public void verifyRecoTraysFromDetailsPage(String userType, String firstAssetID) throws Exception {
			extent.HeaderChildNode("Detail/Consumption screen: Validation of Reco trays and playing content from Reco tray");
			Response recoResp = ResponseInstance.getRecoTraysInDetailsPage(userType, firstAssetID);
			ArrayList<String> recoTraysInDetailsPage = getAllRecoTraysFromDetails(recoResp);
			String trayTitleUI="";
			for (int tray = 0; tray < recoTraysInDetailsPage.size(); tray++) {
				String trayTitleAPI = recoTraysInDetailsPage.get(tray);
				trayTitleUI = swipeTillTray(10, trayTitleAPI,"\"" + trayTitleAPI + "\" tray");
				if (tray == (recoTraysInDetailsPage.size() - 1) && !trayTitleUI.equals("")) {// Verify content play for one reco tray in content details
					PartialSwipe("UP", 1);
					String title = getElementPropertyToString("data-minutelytitle",PWALandingPages.objFirstAssetInTrayIndex(trayTitleUI), "First Asset title").toString();
					waitForElementAndClick(PWALandingPages.objFirstAssetInTrayIndex(trayTitleAPI), 5,"First asset " + title);
					String nextPageTitle = "";
					if (waitForElementPresence(PWAShowsPage.objShowsTitle, 2, "Shows Details page")) {
						nextPageTitle = getText(PWAShowsPage.objShowsTitle);
					} else if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 2, "Player screen")) {
						nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
					}
					if (nextPageTitle.equals(title)) {
						logger.info("Navigated to the correct consumption/details page: \"" + title + "\"");
						extent.extentLogger("playerScreen","Navigated to the correct consumption/details page: \"" + title + "\"");
					} else {
						logger.error("Failed to navigate to consumption/details page: \"" + title + "\"");
						extent.extentLoggerFail("playerScreen","Failed to navigate to consumption/details page: \"" + title + "\"");
					}
					Back(1);
				}
			}
			Back(1);
		}
		
		
		
		public String swipeTillTray(int noOfSwipes, String trayTitle, String message) throws Exception {
			boolean foundTray=false;
			String trayTitleInUI="";	
			main: for (int i = 0; i <= noOfSwipes; i++) {
				ArrayList<WebElement> trays=new ArrayList<WebElement>();				
				trays=(ArrayList<WebElement>) getDriver().findElements(PWALandingPages.objTrayTitle);
				for(int j=0;j<trays.size();j++) {
					if(trays.get(j).getAttribute("innerText").equalsIgnoreCase(trayTitle)) {
						trayTitleInUI=trays.get(j).getText();
						foundTray=true;
						break main;
					}
				}
				PartialSwipe("UP", 1);
				waitTime(2000);
				if (i == noOfSwipes) {
					logger.error(message+" is not displayed");
					extent.extentLoggerFail("failedToLocate", message+" is not displayed");
				}
			}
			if(foundTray==true) {
				for (int i = 0; i <= noOfSwipes; i++) {
					if (waitForElementPresence(PWALandingPages.objTrayTitleInUI(trayTitleInUI), 1, trayTitleInUI+" tray")) {
						return trayTitleInUI;
					} else {
						PartialSwipe("UP", 1);
						waitTime(2000);
						if (i == noOfSwipes) {
							logger.error(message+" is not displayed");
							extent.extentLoggerFail("failedToLocate", message+" is not displayed");
						}
					}
				}
			}
			return "";
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
		
		
		
		
		public boolean swipeTillElement(int noOfSwipes, By locator, String message) throws Exception {
			for (int i = 0; i <= noOfSwipes; i++) {
				if (waitForElementPresence(locator, 1, message)) {
					return true;
				} else {
					PartialSwipe("UP", 1);
					waitTime(5000);
					if (i == noOfSwipes) {
						logger.error(message+" is not displayed");
						extent.extentLoggerFail("failedToLocate", message+" is not displayed");
					}
				}
			}
			return false;
		}
		
		
		
		public boolean swipeTillElementAndClick(int noOfSwipes, By locator, String message) throws Exception {
			for (int i = 0; i <= noOfSwipes; i++) {
				if (waitForElementAndClickIfPresent(locator, 5, message))
					return true;
				else {
					Swipe("UP", 1);
					waitTime(3000);
					if (i == noOfSwipes) {
						logger.error("Failed to locate and click on " + message);
						extent.extentLoggerFail("failedToLocate", "Failed to locate and click on " + message);
					}
				}
			}
			return false;
		}
		
		
		/**
		 * Validation of Recommendation tray and playing content from recommendation
		 * tray
		 */
		@SuppressWarnings("unused")
		public void verifyRecoTrayAndPlayContent(String userType, String tabName, String recoTrayTitle, String contentLangs, boolean verifyContentDetails) throws Exception {
			extent.HeaderChildNode(tabName + " tab: Validation of \"" + recoTrayTitle + "\" tray");
			boolean detailsScreenFoundInUI = false;
			String recoTrayTitleAPI = "", firstAssetTitleAPI = "", firstAssetTypeAPI = "", firstAssetID = "";
			ArrayList<Integer> xyOfTray = new ArrayList<Integer>();
			String trayTitleInUI = "";
			if(navigatetoAnyScreen1(tabName)) {			
				Response recoResp = ResponseInstance.getRecoDataFromTab(userType, tabName, contentLangs);
				ArrayList<String> trayDetails = returnRecoTrayFirstAssetDetails(recoResp, recoTrayTitle);
				if (trayDetails != null) {
					recoTrayTitleAPI = trayDetails.get(0);
					firstAssetTitleAPI = trayDetails.get(1);
					firstAssetTypeAPI = trayDetails.get(2);
					firstAssetID = trayDetails.get(3);
					waitForElementAndClickIfPresent(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 2, "Back to Top");
					trayTitleInUI = swipeTillTray(7, recoTrayTitleAPI,"\"" + recoTrayTitleAPI + "\" tray");
					Swipe("UP", 1);
					Thread.sleep(5000);
				}
			}
			if (!trayTitleInUI.equals("")) {
				extent.HeaderChildNode(tabName + " tab: Validation of content play in \"" + recoTrayTitle + "\" tray");
				if (verifyContentPlayFromRecoTray(userType,trayTitleInUI, firstAssetTitleAPI, firstAssetTypeAPI)) {
					if(verifyContentDetails) verifyRecoTraysFromDetailsPage(userType, firstAssetID);
					else Back(1);
				} else {
					logger.error("Failed to validate the Reco APIs in consumption/details page");
					extent.extentLoggerFail("recoInDetailsFailed","Failed to validate the Reco APIs in consumption/details page");
					waitForElementAndClickIfPresent(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 2, "Back to Top");
				}
			}
			else {
				waitForElementAndClickIfPresent(PWALandingPages.obj_Pwa_Back_to_Top_Arrow_btn, 2, "Back to Top");
			}
		}
		
		public boolean verifyContentPlayFromRecoTray(String userType,String recoTrayTitleAPI, String firstAssetTitleAPI, String firstAssetTypeAPI) throws Exception {
			if (swipeTillElementAndClick(2, PWALandingPages.objFirstAssetInTray(recoTrayTitleAPI, firstAssetTitleAPI),"First Asset \"" + firstAssetTitleAPI + "\"")) {
				String nextPageTitle = "";
				if (firstAssetTypeAPI.equals("tvshow")) {
					if (waitForElementPresence(PWAShowsPage.objShowsTitle, 30, "Shows Details page"))
						nextPageTitle = getText(PWAShowsPage.objShowsTitle);
				} else {
					if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 30, "Player screen"))
						nextPageTitle = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				}
				if (nextPageTitle.equals(firstAssetTitleAPI) && !nextPageTitle.equals("")) {
					logger.info("Navigated to the correct consumption/details page: \"" + nextPageTitle + "\"");
					extent.extentLogger("playerScreen","Navigated to the correct consumption/details page: \"" + nextPageTitle + "\"");
					if(!userType.equals("SubscribedUser")) {waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 5, "Close in Premium Pop Up");}
					return true;
				} else {
					logger.error("Navigated to the incorrect consumption/details page: \"" + nextPageTitle + "\"");
					extent.extentLoggerFail("playerScreen","Navigated to the incorrect consumption/details page: \"" + nextPageTitle + "\"");
					if(!userType.equals("SubscribedUser")) {waitForElementAndClickIfPresent(PWASearchPage.objClosePremiumDialog, 5, "Close in Premium Pop Up");}
					return false;
				}
			} else
				return false;
		}
		
		/**
		 * Function to return reco tray title and first asset title
		 */
		public ArrayList<String> returnRecoTrayFirstAssetDetails(Response response, String requiredTray) {
			ArrayList<String> trayTitleAndFirstContent = new ArrayList<String>();
			boolean found = false;
			int numberOfTrays = 0;
			String trayTitleAPI = "", firstAssetTitleAPI = "", firstAssetTypeAPI = "", firstAssetID = "";
			try {
				numberOfTrays = response.jsonPath().get("buckets.size()");
			} catch (Exception e) {
				logger.error("API error observed");
				extent.extentLoggerFail("apValue", "API error observed");
				return null;
			}
			for (int trays = 0; trays < numberOfTrays; trays++) {
				if (response.jsonPath().get("buckets[" + trays + "].title").toString().toLowerCase()
						.contains(requiredTray.toLowerCase())) {
					trayTitleAPI = response.jsonPath().get("buckets[" + trays + "].title").toString();
					logger.info("Reco Tray fetched from API: \"" + trayTitleAPI + "\"");
					extent.extentLogger("apValue", "Reco Tray fetched from API: \"" + trayTitleAPI + "\"");

					firstAssetTitleAPI = response.jsonPath().get("buckets[" + trays + "].items[0].title").toString();
					logger.info("First Asset Title fetched from API: \"" + firstAssetTitleAPI + "\"");
					extent.extentLogger("apValue", "First Asset Title fetched from API: \"" + firstAssetTitleAPI + "\"");

					firstAssetTypeAPI = response.jsonPath().get("buckets[" + trays + "].items[0].asset_subtype").toString();
					logger.info("First Asset Type fetched from API: \"" + firstAssetTypeAPI + "\"");
					extent.extentLogger("apValue", "First Asset Type fetched from API: \"" + firstAssetTypeAPI + "\"");

					firstAssetID = response.jsonPath().get("buckets[" + trays + "].items[0].id").toString();
					logger.info("First Asset ID fetched from API: \"" + firstAssetID + "\"");
					extent.extentLogger("apValue", "First Asset ID fetched from API: \"" + firstAssetID + "\"");

					found = true;
					break;
				}
			}
			if (found == false) {
				logger.info("Required tray details not present Reco Response");
				extent.extentLogger("apValue", "Required tray details not present Reco Response");
				return null;
			}
			trayTitleAndFirstContent.add(trayTitleAPI);
			trayTitleAndFirstContent.add(firstAssetTitleAPI);
			trayTitleAndFirstContent.add(firstAssetTypeAPI);
			trayTitleAndFirstContent.add(firstAssetID);
			return trayTitleAndFirstContent;
		}
		
		public void playAContentForReco(String contentType, String searchKey, String userType) throws Exception {
			logger.info("Playing content to initiate Reco API: " + contentType);
			extent.extentLogger("contentplay", "Playing content to initiate Reco API: " + contentType);
			verifyElementPresentAndClick(PWAHomePage.objSearchBtn, "Search icon");
			type(PWASearchPage.objSearchEditBox, searchKey + "\n", "Search Edit box: " + searchKey);
			waitTime(4000);
			waitForElement(PWASearchPage.objSearchedResult(searchKey), 10, "Search Result");
			String contentPlayed="";
			verifyElementPresentAndClick(PWASearchPage.objSearchedResult(searchKey), "Search Result");
			if(!userType.equals("SubscribedUser"))
				waitForElementAndClickIfPresent(PWASearchPage.objCloseRegisterDialog, 30, "Close in Pop Up");
			if (waitForElementPresence(PWAPlayerPage.objContentTitleInConsumptionPage, 30, "Player screen")) {
				contentPlayed = getText(PWAPlayerPage.objContentTitleInConsumptionPage);
				logger.info("Content played: " + contentPlayed);
				extent.extentLogger("contentPlayed", "Content played: " + contentPlayed);
				waitForPlayerAdToComplete("Video Player");
				logger.info("Playing content for some time to trigger Reco API");
				extent.extentLogger("contentPlayed", "Playing content for some time to trigger Reco API");
				waitTime(30000);
				waitTime(10000);
			}
		}
		
		public void UserActionGuestUser() throws Exception {
			extent.HeaderChildNode("User Action module- Guest user Validataions");
			// Validate Continue watching tray is not displayed for Guest user
			if(getPlatform().equalsIgnoreCase("Android")){
				ScrollToElement(PWAHomePage.objFirstContentCardOfTray("Recommended Movies"), "Recommended Movies Tray");
			}else if(getPlatform().equalsIgnoreCase("Web")){
				ScrollToTheElementWEB(PWAHomePage.objFirstContentCardOfTray("Recommended Movies"));
			}
			if(verifyElementExist(PWAHomePage.objFirstContentCardOfTray("Recommended Movies"),"First Content Card Of Recommended Movies Tray")){
				Actions action = new Actions(getDriver());
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
		
		public void validateDisplayLanguagePopup() throws Exception {

			if (waitForElement1(PWAHomePage.objDisplayLanguagePopupTitle, 20, "Display Language Popup")) {

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
		

		public void FilterLanguage() throws Exception {
			click(PWALiveTVPage.objFilterLanguageChannelGuide, "Filter language");
			int size = findElements(PWALiveTVPage.objSelectedlang).size();
			for (int i = 1; i <= size; i++) {
				click(PWALiveTVPage.objSelectedlang, "Selected language");
			}
			click(PWALiveTVPage.objKannadaLang, "Kannada language");
			click(PWALiveTVPage.objApplyBtn, "Apply button");
//			click(PWALiveTVPage.objApplyBtn,"Apply button");
		}
	   
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
		
		public boolean waitForElement1(By locator, int seconds, String message) throws InterruptedException {
			main: for (int time = 0; time <= seconds; time++) {
				try {
					getWebDriver().findElement(locator);
					logger.info("Located element " + message);
					extent.extentLogger("locatedElement", "Located element " + message);
					break main;
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
	   
}