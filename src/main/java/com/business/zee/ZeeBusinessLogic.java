package com.business.zee;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.utility.Utilities;
import com.driverInstance.CommandBase;
import com.extent.ExtentReporter;
import com.jayway.restassured.response.Response;
import com.metadata.ResponseInstance;
import com.propertyfilereader.PropertyFileReader;
import com.zee5Pages.BaseLoginPage;
import com.zee5Pages.DownloadPage;
import com.zee5Pages.HamburgerMenuPage;
import com.zee5Pages.HomePage;
import com.zee5Pages.IntermediatePage;
import com.zee5Pages.LanguageUpdatePage;
import com.zee5Pages.PermissionPage;
import com.zee5Pages.PlayerPage;
import com.zee5Pages.RegistrationPage;
import com.zee5Pages.SocialMediaLoginPage;
import com.zee5Pages.TraditionalLoginPage;
import com.zee5Pages.VideoPlayer;
import com.zee5Pages.WelcomePage;
import com.zee5Pages.ZeePages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ZeeBusinessLogic extends Utilities {

	public ZeeBusinessLogic(String Application) throws InterruptedException {
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
	 * Wait until Player Loading is not displayed.
	 */

	public void LoadingInProgress() throws Exception {
		verifyElementNotPresent(ZeePages.objProgressBar, 60);
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
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();
	}

	/**
	 * Function to verify the subscribe Pop up
	 */
	public void VerifySubscribePopUp() throws Exception {

		if (verifyElementExist(ZeePages.objWatchFreeEpisodesBtn, "Watch Free Episodes") == true) {
			click(ZeePages.objWatchFreeEpisodesBtn, "Watch Free Episodes");
		} else if (verifyElementExist(ZeePages.objClickHereToLogin, "Login") == true) {
			click(ZeePages.objClickHereToLogin, "Login");
			ZeeLogin("email");
			waitTime(5000);
			verifyElementPresentAndClick(ZeePages.objEpisodeTitle, "Episode");
			waitTime(3000);
			click(ZeePages.objWatchFreeEpisodesBtn, "Watch Free Episodes");
		}
	}

	/**
	 * Function to login
	 */
	public void ZeeLogin(String loginType) throws Exception {
		HeaderChildNode("Login -> " + loginType);
		if (verifyElementExist(BaseLoginPage.objMobileNumber, "Login Screen")) {
			if (verifyElementExist(BaseLoginPage.objAbBanner, "Ad")) {
				Swipe("LEFT", 1);
			}
			VerifyElements(); // Verify Elements of an Base Login Screen
			switch (loginType.toUpperCase()) {
			case "MOBILE":
				verifyElementPresentAndClick(BaseLoginPage.objMobileNumber, "Mobile Icon");
				verifyElementPresent(TraditionalLoginPage.objMobileNumbertxtField, "Mobile number Field");
				type(TraditionalLoginPage.objMobileNumbertxtField, "1234567980", "MobileNumber text Field");
				hideKeyboard();
				verifyElementPresent(TraditionalLoginPage.objPasswordTxtField, "Password Text Field");
				type(TraditionalLoginPage.objPasswordTxtField, "1234567980", "Password text field");
				hideKeyboard();
				verifyElementPresentAndClick(TraditionalLoginPage.objLogintn, "Login Button");
				break;

			case "EMAIL":

				verifyElementPresentAndClick(BaseLoginPage.objEmailID, "Email Icon ");
				verifyElementPresent(TraditionalLoginPage.objEmailId, "Email ID Field");
				type(TraditionalLoginPage.objEmailId, "basavaraj.pn5@gmail.com", "Email Field"); // abhilash.s@igs-india.net
				hideKeyboard();
				verifyElementPresent(TraditionalLoginPage.objPassword, "Password Field");
				type(TraditionalLoginPage.objPassword, "zee5@123", "Password Field"); // IGS!2345
				hideKeyboard();
				click(TraditionalLoginPage.objLoginBtn, "Login Button");
				waitTime(5000);
				break;

			case "TWITTER":
				verifyElementPresentAndClick(BaseLoginPage.objTwitterIcon, "Twitter Icon");
				break;

			case "FB":
				verifyElementPresentAndClick(BaseLoginPage.objFacebookIcon, "Facebook Icon");
				if (findElements(SocialMediaLoginPage.objCountinueBtn).size() != 0) {
					verifyElementPresentAndClick(SocialMediaLoginPage.objCountinueBtn, " Clicked On Countinue Button");
				} else {
					verifyElementPresent(SocialMediaLoginPage.objEmailIdtxtField, " Email Field");
					type(SocialMediaLoginPage.objEmailIdtxtField, "ABC@Gmail.com", "Emial Field");
					hideKeyboard();
					verifyElementPresent(SocialMediaLoginPage.objPasswordTxtField, " Password Field");
					type(SocialMediaLoginPage.objPasswordTxtField, "XYZ", "Password Field");
					hideKeyboard();
					verifyElementPresentAndClick(SocialMediaLoginPage.objLoginBtn, " Login Button ");
				}
				break;

			case "GOOGLE":
				verifyElementPresentAndClick(BaseLoginPage.objGoogleIcon, " Google Icon");
				break;

			case "SKIP":
				verifyElementPresentAndClick(BaseLoginPage.objSkipBtn, " Skip Icon");
				break;
			}
		} else {

			/*
			 * extent.extentLogger("", "<style=\"width: 50px\">\n" + "table, th, td {\n" +
			 * "  border: 2px solid black;\n" + "  padding: 5px;\n" + "}\n"+ "table {\n" +
			 * "  border-spacing: 15px;\n" + "}\n" + "</style>" + "<table>\n" + "  <tr>" +
			 * "    <th>Firstname</th>\n" + "    <th>Lastname</th>\n" + "    <th>Age</th>\n"
			 * + "  </tr>\n" + "  <tr>" + "    <td>Jill</td>\n" + "    <td>Smith</td>\n" +
			 * "    <td>50</td>\n" + "  </tr>\n" + "  <tr>" + "    <td>Eve</td>\n" +
			 * "    <td>Jackson</td>\n" + "    <td>94</td>\n" + "  </tr>\n" + "</table>");
			 */

			extent.extentLogger("User Name Field Display", " <b>User already logged in <b>");
			logger.info("User already logged in");
		}

	}

	/**
	 * Function to logout the Screen
	 */
	public void ZeeLogout() throws Exception {
		HeaderChildNode("<b>GTM </b> :: Logout");
		DragMiniPlayer(PlayerPage.objminiPlayer);
		verifyElementPresentAndClick(ZeePages.objHamburgerMenu, "Menu");
		waitTime(2000);
		Swipe("Up", 1);
		verifyElementPresentAndClick(ZeePages.objLogoutBtn, "Logout");
	}

	/**
	 * Function to verify the Object of an Base Login screen
	 */
	public void VerifyElements() throws Exception {
		extent.HeaderChildNode("Login Verification");
		verifyElementPresent(BaseLoginPage.objLOGOIcon, " 'Zee5 Logo' ");
		verifyElementPresent(BaseLoginPage.objContenttext, " 'Content Via Social Account' Text ");
		verifyElementPresent(BaseLoginPage.objFacebookIcon, " 'Facebook' Icon ");
		verifyElementPresent(BaseLoginPage.objTwitterIcon, " 'Twitter' Icon ");
		verifyElementPresent(BaseLoginPage.objGoogleIcon, " 'Google' Icon ");
		verifyElementPresent(BaseLoginPage.objMobileNumber, " 'Login via Mobile Number' ");
		verifyElementPresent(BaseLoginPage.objEmailID, " 'Login via EMail ID' ");
		verifyElementPresent(BaseLoginPage.objDonthaveaccountText, " 'Don't have an account' text ");
		verifyElementPresent(BaseLoginPage.objRegisterBtn, " 'Register' button ");
		verifyElementPresent(BaseLoginPage.objSkipBtn, " 'Skip' Button ");
		verifyElementPresent(BaseLoginPage.objTermsTxt,
				" 'By Continuing, you agree to our Terms & Privacy Policy' text ");

	}

	/**
	 * Function to verify the functionality of an intermediate screen
	 */
	public void verifyIntermediateScreen() throws Exception {
		HeaderChildNode("Validate Intermediate screen");
		waitTime(2000);
		verifyElementPresent(IntermediatePage.objIntermediateUserName, "User Name Text field");
//		ExtentReporter.screencapture("");
		extent.extentLogger("User Name Field Display",
				"User Name field is displayed : " + findElement(IntermediatePage.objIntermediateUserName).getText());
		logger.info(
				"User Name field is displayed : " + findElement(IntermediatePage.objIntermediateUserName).getText());

		verifyElementPresent(IntermediatePage.objIntermediateUserLike, "User Like Text field");
		extent.extentLogger("User text Field Display",
				"User Like field is displayed : " + findElement(IntermediatePage.objIntermediateUserLike).getText());
		logger.info(
				"User Like field is displayed : " + findElement(IntermediatePage.objIntermediateUserLike).getText());

		for (int i = 1; i <= 2; i++) {
			int sizeOfButtons = findElements(IntermediatePage.objSizeOfButtonsDisplayedOnScreen).size();
			for (int j = 1; j <= sizeOfButtons - 1; j++) {
				WebElement element = findElement(IntermediatePage.objIntermediateButtons(j));
				if (i == 1 || (i == 2 && !hash_Set.contains(element.getText()))) {
					if (element.isDisplayed() && element.isEnabled()) {
						hash_Set.add(findElement(IntermediatePage.objIntermediateButtons(j)).getText());
						extent.extentLogger("Buttons Displayed",
								"Button " + findElement(IntermediatePage.objIntermediateButtons(j)).getText()
										+ " is Displayed and Enabled");
						logger.info("Button " + findElement(IntermediatePage.objIntermediateButtons(j)).getText()
								+ " is Displayed and Enabled");
					} else {
						extent.extentLogger("Buttons not Displayed",
								"Button " + findElement(IntermediatePage.objIntermediateButtons(j)).getText()
										+ " is not Displayed and Disabled");
						logger.info("Button " + findElement(IntermediatePage.objIntermediateButtons(j)).getText()
								+ " is not Displayed and Disabled");
					}
					if (j == sizeOfButtons) {
						String Firstbounds = findElement(IntermediatePage.objIntermediateButtons(sizeOfButtons))
								.getAttribute("bounds");
						Swipe("UP", 2);
						String Secondbounds = findElement(IntermediatePage.objIntermediateButtons(sizeOfButtons))
								.getAttribute("bounds");
						if (!Firstbounds.equals(Secondbounds)) {
							extent.extentLogger("Intermediate Screen", "Intermediate List Is Scrollable");
							logger.info("Intermediate List Is Scrollable");
						} else {
							extent.extentLogger("Intermediate Screen", "Intermediate List Is Not Scrollable");
							logger.info("Intermediate List Is Not Scrollable");
						}
					}
				}
			}
		}
		logger.info("List of Buttons : " + hash_Set);

	}

	/**
	 * Navigate to HomePage MastheadCarousel and validate the Carousel with API
	 * Response
	 * 
	 */
	@SuppressWarnings("unused")
	public void NavigatFromIntermediateScreen(String NavigateIcon) throws Exception {
		HeaderChildNode("Navigate To Home Page From Intermediate Screen");
		waitTime(5000);
//		ScrollTillElementVisible(NavigateIcon);
		int sizeOfButtons = findElements(IntermediatePage.objSizeOfButtonsDisplayedOnScreen).size();
		String[] bounds = findElement(IntermediatePage.objIntermediateButtons(sizeOfButtons)).getAttribute("bounds")
				.replace("[", "").replace("]", ",").split(",");

		int starty = Integer.valueOf(bounds[3]);
		int endy = Integer.valueOf(bounds[2]) - 100;
		int startx = Integer.valueOf(bounds[0]);
//		getDriver().swipe(startx, endy, startx, starty, 3000);
//		getDriver().swipe(startx, endy, startx, starty, 3000);
		logger.info("Swiping the screen in " + " " + "Down" + " direction" + " " + "1" + " times");
		extent.extentLogger("SwipeDown", "Swiping the screen in " + " " + "Down" + " direction" + " " + "1" + " times");

		boolean found = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= sizeOfButtons; j++) {
				if (findElement(IntermediatePage.objIntermediateButtons(j)).getText().toUpperCase()
						.contains(NavigateIcon.toUpperCase())) {
					click(IntermediatePage.objIntermediateButtons(j), NavigateIcon);
					LoadingInProgress();
					GuideTourValidate();
					found = true;
					Thread.sleep(3000);
					// ValidateMastheadCarousel();
					break;
				}
			}
			if (found) {
				break;
			}
		}

	}

	/**
	 * Verifying the Grant Permission button is not visible for the second time
	 */
	public void RelaunchApp() throws Exception {
		HeaderChildNode("Welcome screen validation for subsequent login");
		logger.info("Relaunching the App");
		getDriver().quit();
		relaunchFlag = true;
		new ZeeBusinessLogic("zee");
		if (verifyElementExist(PermissionPage.objExplanationText,
				"For subsequent launch 'Permission Exlanation' Text") == true) {
			extent.extentLogger("check Give Permission button",
					"check 'Give Permission' button is visible for subsequent launch");
			logger.info("check 'Give Permission' button is visible for subsequent launch");
		} else {
			extent.extentLogger("check Give Permission button",
					"check 'Give Permission' button is not visible for subsequent launch");
			logger.info("check 'Give Permission' button is not visible for subsequent launch");
		}
		if (verifyElementExist(WelcomePage.objWelcomeToZeeText, "For subsequent launch 'Welcome' Text") == true) {
			extent.extentLogger("Skip button ", "'Skip' button is visible for subsequent launch");
			logger.info("'Skip' button is visible for subsequent launch");
		} else {
			extent.extentLogger("Skip button ", "'Skip' button is not visible for subsequent launch");
			logger.info("'Skip' button is not visible for subsequent launch");
		}

	}

	/**
	 * Permission Grant Functionality
	 */
	public void GivePermission() throws Exception {
		HeaderChildNode("Give Permission Screen Validation");
		verifyElementPresent(PermissionPage.objExplanationText, "'Permission Explanation' Text");
		getElementPropertyToString("text", PermissionPage.objExplanationText, "Explanation Message");
		verifyElementPresentAndClick(PermissionPage.objGivePermissionBtn, "'Give Permission' Button");
		verifyElementPresent(PermissionPage.objPopupMsg, "Popup Message");
		getElementPropertyToString("text", PermissionPage.objPopupMsg, "'Give permission' Pop-Up");
		verifyElementPresentAndClick(PermissionPage.objAllowBtn, "'Allow' Button");

	}

	/**
	 * Scroll to the tray and click on view all button
	 */
	public void scroll(String text) throws Exception {
		HeaderChildNode("Scroll to the tray/Video");
		waitTime(5000);
		for (int i = 0; i <= 25; i++) {
			List<WebElement> elemore = getDriver().findElements(VideoPlayer.objScrollTotext(text));
			if (elemore.size() == 1) {
				extent.extentLogger("Scroll ", "scroll till " + text + " tray and click");
				logger.info("scroll till " + text + " tray and click");
				break;
			}
			Swipe("UP", 1);
		}
		Point Location = getDriver().findElement(VideoPlayer.objViewAllBtn(text)).getLocation();
		Dimension Dimension = getDriver().manage().window().getSize();
		partialscrollTray(Location, Dimension);
		// SwipeTOMidOfScreen(text);
		click(VideoPlayer.objViewAllBtn(text), "View All button");
		LoadingInProgress();
		waitTime(2000);
		// ViewAllContentAPI();

	}

	/**
	 * Scroll to the mid of an screen
	 */
	public void partialscrollTray(org.openqa.selenium.Point location, Dimension D) {
		String traypoint = location.toString();
		String trayDimension = D.toString();
		String replaceP = traypoint.replace("(", "").replace(")", "");
		String replaceD = trayDimension.replace("(", "").replace(")", "");
		String replacep1[] = replaceP.split(",");
		String replaceD1[] = replaceD.split(",");
		if (Integer.parseInt(replacep1[1].trim()) >= Integer.parseInt(replaceD1[1].trim()) - 550) {
			PartialSwipe("UP", 1);
		}
	}

	/**
	 * Verifying the watch list
	 */
	public void VerifyAddToWatchList() throws Exception {
		verifyElementPresentAndClick(VideoPlayer.objWatchList, "Watch list ");
		Boolean ToastMessage = getDriver().findElement(By.xpath("//*[@id='message']")).isDisplayed();
		if (ToastMessage != true) {
			extent.extentLogger("Toast", "Toast message not displayed");
			logger.info("Toast message not displayed");
			click(VideoPlayer.objWatchList, "Watch list ");
		}
		if (ToastMessage == true) {
			extent.extentLogger("Toast", "Toast message displayed");
			logger.info("Toast message displayed");
		} else {
			extent.extentLogger("Toast", "Toast message not displayed");
			logger.info("Toast message not displayed");
		}
	}

	/**
	 * Function to make Portrait to MiniPlayer
	 */
	public void potraitToMiniplayer() throws Exception {
		click(PlayerPage.objPlayerView, "Player Screen");
		click(PlayerPage.objPlayerMinimizeIcon, "Player Down arrow Icon");
//		verifyElementPresentAndClick(PlayerPage.objPlayerMinimizeIcon, "Player Down arrow Icon");
	}

	/**
	 * Verifying the Guide Tour Pop is Displayed
	 */
	public void GuideTourValidate() throws Exception {
		Boolean value = verifyElementExist(BaseLoginPage.objGuideTourTxt, "Guide Tour");
		if (value.equals(true)) {
			verifyElementPresentAndClick(BaseLoginPage.objGuideTourNoThanksLink, "NoThanks Link");
		}
	}

	/**
	 * Validating the welcome screen Objects
	 */
	public void WelcomeScreenValidation() throws Exception {
		HeaderChildNode("Welcome Screen Validation");
		verifyElementPresent(WelcomePage.objWelcomeToZeeText, "Welcome Text");
		String welecometext = getElementPropertyToString("text", WelcomePage.objWelcomeToZeeText, "WelCome page");
		verifyElementExist(WelcomePage.objWelcomeToZeeText, welecometext);
		verifyElementPresent(WelcomePage.objWelcomeToZeeText1, "Welcome Text");
		String welecometext1 = getElementPropertyToString("text", WelcomePage.objWelcomeToZeeText1, "WelCome page");
		verifyElementExist(WelcomePage.objWelcomeToZeeText1, welecometext1);
		verifyElementPresent(WelcomePage.objWelcomeToZeeText2, "Welcome Text");
		String welecometext2 = getElementPropertyToString("text", WelcomePage.objWelcomeToZeeText2, "WelCome page");
		verifyElementExist(WelcomePage.objWelcomeToZeeText2, welecometext2);
		LacationBasedLanguge = fetchLocationBasedLanguage(welecometext2);
		TrimString(LacationBasedLanguge);
		verifyElementExist(WelcomePage.objSkip, "SKIP");
		verifyElementExist(WelcomePage.objUpdateLanguageBtn, "Update Language");

	}

	/**
	 * Function to Fetch Location based Languages
	 */
	public String fetchLocationBasedLanguage(String Message) {
		Pattern p = Pattern.compile("Based on your current location, your content language is (\\w+)  and  (\\w+)");
		Matcher m = p.matcher(Message);
		m.find();
		return m.group(1) + " " + m.group(2).replace(".", "");
	}

	/**
	 * Function to trim an String
	 */
	public void TrimString(String str) {
		String Language = str;
		String[] arr = Language.split(" ");
		LocationLanguage.add(arr[0]);
		LocationLanguage.add(arr[1]);
	}

	/**
	 * Function to validate the selected Languages
	 */
	public void LanguageValidation() throws Exception {
		HeaderChildNode("Language in Welcome Screen Validation");
		verifyElementPresentAndClick(WelcomePage.objUpdateLanguageBtn, "Update Language Button");
		verifyElementPresent(LanguageUpdatePage.objLanguageTitle, "Language Tilte");
		verifyElementPresent(LanguageUpdatePage.objDisplayTab, "DISPLAY tab");
		verifyElementPresent(LanguageUpdatePage.objContentTab, "CONTENT tab");
		verifyElementPresent(LanguageUpdatePage.objLanguageDoneBtn, "Done Button");
//		ExtentReporter.screencapture("");
		// default landing of indicator
		String indicator = getElementPropertyToString("text", LanguageUpdatePage.objIndicator, " Language Indicator");
		extent.extentLogger("indicator", "Default Landing in Tab : " + indicator);
		logger.info("Default Landing in Tab : " + indicator);
		verifyElementExist(WelcomePage.objWelcomeToZeeText2, indicator);
		// default Language selected in CONTENT tab as per Welcome screen
		for (int i = 1; i <= findElements(LanguageUpdatePage.objLanguageTextEnglish).size(); i++) {
			String checkedState = getAttributValue("checked", LanguageUpdatePage.objLanguageCheckbox(i));
			if (checkedState.contains("true")) {
				String defaultLanguageAsSelected = getAttributValue("text",
						LanguageUpdatePage.objLanguageTextEnglishGetvalue(i));
				DefaultLanguage.add(defaultLanguageAsSelected);
				extent.extentLogger("DefaultSelectedLanguage", "Defalut selected Language in CONTENT Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
				logger.info("Defalut selected Language in CONTENT Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));

			}
		}
		// Language comparion btw Lacation based language and Default selected language
		if (LocationLanguage.equals(DefaultLanguage)) {
			extent.extentLogger("",
					"The list of language shown on welcome screen is selected by default on CONTENT tab");
			logger.info("The list of language shown on welcome screen is selected by default on CONTENT tab");
		} else {
			extent.extentLogger("", "LANGUAGE DIDN'T MATCH");
			logger.info("LANGUAGE DIDN'T MATCH");
		}
		// seleting the languages in CONTENT tab
		for (int i = 1; i <= findElements(LanguageUpdatePage.objLanguageTextEnglish).size(); i++) {
			String language = getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i));
			if (language.contains("Hindi")) {
				// click(LanguageUpdatePage.objLanguageCheckbox(i), "Hindi");
				verifyElementPresentAndClick(LanguageUpdatePage.objLanguageCheckbox(i), "Hindi Icon");
			}
			String checkedState = getAttributValue("checked", LanguageUpdatePage.objLanguageCheckbox(i));
			if (checkedState.contains("true")) {
				SelectedCONTENTLanguageInWelcomscreen
						.add(findElement(LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)).getText());
				extent.extentLogger("SelectedLanguage", "Selected Language in CONTENT Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
				logger.info("Selected Language in CONTENT Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
			}
		}
		// default Language selected in DISPLAY tab
		verifyElementPresentAndClick(LanguageUpdatePage.objDisplayTab, "Display Tab");
		for (int i = 1; i <= findElements(LanguageUpdatePage.objLanguageTextEnglish).size(); i++) {
			String checkedState = getAttributValue("checked", LanguageUpdatePage.objLanguageRadioBtn(i));
			if (checkedState.contains("true")) {
				extent.extentLogger("DefaultSelectedLanguage", "Defalut selected Language in DISPLAY Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
				logger.info("Defalut selected Language in DISPLAY Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
			}
		}
		// Selecting language in DISPLAY tab
		for (int i = 1; i <= findElements(LanguageUpdatePage.objLanguageTextEnglish).size(); i++) {
			String language = getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i));
			if (language.contains("Hindi")) {
				// click(LanguageUpdatePage.objLanguageCheckbox(i), "Hindi");
				verifyElementPresentAndClick(LanguageUpdatePage.objLanguageRadioBtn(i), "Hindi");
			}
			String checkedState = getAttributValue("checked", LanguageUpdatePage.objLanguageRadioBtn(i));
			if (checkedState.contains("true")) {
				extent.extentLogger("SelectedLanguage", "Selected Language in DISPLAY Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
				logger.info("Selected Language in DISPLAY Tab : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
			}
			if (language.contains("English")) {
				// click(LanguageUpdatePage.objLanguageCheckbox(i), "Hindi");
				verifyElementPresentAndClick(LanguageUpdatePage.objLanguageRadioBtn(i), "English");
				break;
			}
		}
		// Selecting DONE button
		verifyElementPresentAndClick(LanguageUpdatePage.objLanguageDoneBtn, "Language Update DONE Button");
		verifyElementPresentAndClick(LanguageUpdatePage.objLanguageDoneBtn, "Language Update DONE Button");
		verifyElementExist(BaseLoginPage.objEmailID, "Email Button");

	}

	/**
	 * Function to verify the user selected Language in HamburgerMenu Language
	 * Option
	 * 
	 */
	public void SelectedContentLanguageValidation() throws Exception {
		HeaderChildNode("Validate Content of Language in Hamburger Menu");
		verifyElementPresentAndClick(HamburgerMenuPage.objHamburgerMenu, "Hamburger Menu");
		waitTime(2000);
//		Swipe("UP", 1);
//		ScrollTillElementVisible("Language");
		verifyElementPresentAndClick(HamburgerMenuPage.objLanguageHamburgerMenu, " Language Icon");
		verifyElementPresentAndClick(LanguageUpdatePage.objContentTab, " Content Icon");

		for (int i = 1; i <= findElements(LanguageUpdatePage.objLanguageTextEnglish).size(); i++) {
			String checkedState = getAttributValue("checked", LanguageUpdatePage.objLanguageCheckbox(i));
			if (checkedState.contains("true")) {
				SelectedCONTENTLanguageInHamburgerMenu
						.add(findElement(LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)).getText());
				extent.extentLogger("SelectedLanguage", "Selected CONTENT Language Validation : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
				logger.info("Selected CONTENT Language Validation : "
						+ getAttributValue("text", LanguageUpdatePage.objLanguageTextEnglishGetvalue(i)));
			}
		}
		// Language comparion btw Lacation based language and Default selected language
		if (SelectedCONTENTLanguageInWelcomscreen.equals(SelectedCONTENTLanguageInHamburgerMenu)) {
			extent.extentLogger("",
					"The list of languages selected in CONTENT tab during login is same in Hamburger Language CONTENT tab");
			logger.info(
					"The list of languages selected in CONTENT tab during login is same in Hamburger Language CONTENT tab");

		} else {
			extent.extentLogger("", "LANGUAGE DIDN'T MATCH");
			logger.info("LANGUAGE DIDN'T MATCH");
		}
		verifyElementPresentAndClick(HamburgerMenuPage.objLanguageDoneBtn, "Language Update DONE Button");
		LoadingInProgress();

	}

	/**
	 * Function to verify the Player Icons are Displayed
	 */
	public void PlayerIconsCheck() throws Exception {
		HeaderChildNode("Player Icons Validation");
		waitTime(4000);
		GetAndVerifyOrientation("portrait");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerCurrentTime, "Player Current Time");
		findElement(PlayerPage.objPlayerCurrentTime).getText();
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerCurrentTime, "Player Current Time");
		String AfterPause = findElement(PlayerPage.objPlayerCurrentTime).getText();
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerDownloadBtn, "Down Arrow Icon");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayPauseIcon, "Play Pause Icon");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerNextIcon, "Next Button");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerShareIcon, "Share Icon");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerThreeDotsIcon, "3 dot Icon");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerCurrentTime, "Current Time");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerEndTime, "End Time");
		click(PlayerPage.objPlayerView, "Player Screen");
		verifyElementExist(PlayerPage.objPlayerExpanderIcon, "Player Expand Icon");
		click(PlayerPage.objPlayerView, "Play Pause Icon");
		String AfterPlay = findElement(PlayerPage.objPlayerCurrentTime).getText();

		if (!AfterPause.equals(AfterPlay)) {
			extent.extentLogger("", "Player" + "<b>Play/Pause</b>" + " Button Functionality working");
			logger.info("Player Play/Pause Button Functionality working");
		} else {
			extent.extentLogger("", "Player" + "<b>Play/Pause</b>" + " Button Functionality not working");
			logger.info("Player Play/Pause Button Functionality not working");
		}
	}

	/**
	 * Function to verify the 3 Dots on the player
	 */
	public void ThreeDotOptionValidation() throws Exception {
		HeaderChildNode("Player 3Dot Option Validation");
		waitForElementDisplayed(PlayerPage.objWaitForElementDisplay, 60);
		LoadingInProgress();
		waitForElementDisplayed(PlayerPage.objWaitForElementDisplay, 60);
		AdVerify();
		LoadingInProgress();
		PlayerIconsCheck();
		verifyElementPresentAndClick(PlayerPage.objPlayerThreeDotsIcon, "3 dot Icon");
		verifyElementPresent(PlayerPage.obj3dotPopup, "3Dot Popup");
		for (int i = 1; i <= findElements(PlayerPage.obj3dotPopupElements).size(); i++) {
			extent.extentLogger("3dots",
					"3 Dot Popup Options : " + getAttributValue("text", PlayerPage.obj3dotPopupSelectElements(i)));
			logger.info("3 Dot Popup Options : " + getAttributValue("text", PlayerPage.obj3dotPopupSelectElements(i)));
		}

	}

	/**
	 * Function to select the option from the Action icon on the player
	 */
	public void ThreeDotPopupOptionSelection(String str) throws Exception {
		HeaderChildNode("Selecting the option from 3Dot popup Options");
		PopUp = false;
		boolean popupPresent = verifyElementExist(PlayerPage.obj3dotPopup, "3Dot Popup");
		if (popupPresent == true) {
			for (int i = 1; i <= findElements(PlayerPage.obj3dotPopupElements).size(); i++) {
				String options = getAttributValue("text", PlayerPage.obj3dotPopupSelectElements(i));
				if (options.contains(str)) {
					click(PlayerPage.obj3dotPopupSelectElements(i), str);
					PopUp = true;
				} else {
					logger.info("Option " + str + " is not present.");
					extent.extentLogger("", "Option " + str + " is not present.");
				}
			}
		} else {
			logger.info("3Dot Popup not present");
			extent.extentLogger("", "3Dot Popup not present");
		}

	}

	public void Click3DotOption() throws Exception {
		for (int i = 0; i < 4; i++) {
			if (!verifyElementExist(PlayerPage.obj3dotPopup, "3Dot Popup")) {
				click(PlayerPage.objPlayerView, "");
				click(PlayerPage.objPlayPauseIcon, " Player Screen ");
				click(PlayerPage.obj3dotPopup, "3 Dot pop up");
			} else {
				break;
			}
		}
	}

	/**
	 * Function to verify the Quality of an Content Played
	 */
	public void QualityFunctionality() throws Exception {
		HeaderChildNode("verifying the Quality Functionality");
		boolean present = verifyElementExist(PlayerPage.objQulityPopupText, "Quality Text");
		if (present == true) {
			for (int i = 1; i <= findElements(PlayerPage.objQulityPopuoOption).size(); i++) {
				String checkedOption = getAttributValue("checked", PlayerPage.objQulityPopuoOptions(i));
				if (checkedOption.contains("true")) {
					logger.info("Option previously selected : "
							+ getAttributValue("text", PlayerPage.objQulityPopuoOptions(i)));
					extent.extentLogger("", "Option previously selected : "
							+ getAttributValue("text", PlayerPage.objQulityPopuoOptions(i)));
					break;
				}
			}
			waitTime(2000);
			for (int i = 1; i <= findElements(PlayerPage.objQulityPopuoOption).size(); i++) {
				String checkedOption = getAttributValue("checked", PlayerPage.objQulityPopuoOptions(i));
				if (checkedOption.contains("false")) {
					logger.info("Selecting the the option which is not selected : "
							+ getAttributValue("text", PlayerPage.objQulityPopuoOptions(i)));
					extent.extentLogger("", "Selecting the the option which is not selected : "
							+ getAttributValue("text", PlayerPage.objQulityPopuoOptions(i)));
					click(PlayerPage.objQulityPopuoOptions(i), "Option selected");
					break;
				}
			}
			waitTime(6000);
			click(PlayerPage.objPlayerView, "Player Screen");
			verifyElementPresentAndClick(PlayerPage.objPlayerThreeDotsIcon, "3Dot Icon");
//			JavascriptExecutor js = (JavascriptExecutor) getDriver();
//            WebElement element = getDriver().findElement(PlayerPage.objPlayerThreeDotsIcon);
//           js.executeScript("return arguments[0].text", element);
		} else {
		}

	}

	/**
	 * Function to verify the Functionality of an Audio Language selected
	 */
	public void AudioLanguageFunctionality() throws Exception {
		HeaderChildNode("Validating Audio Language Functionality");
		boolean present = verifyElementExist(PlayerPage.objAudioLanguagePopupText, "AudioLanguage");
		if (present == true) {
			for (int i = 1; i <= findElements(PlayerPage.objAudioLanguagePopuoOption).size(); i++) {
				String checkedOption = getAttributValue("checked", PlayerPage.objAudioLanguagePopuoOptions(i));
				if (checkedOption.contains("true")) {
					logger.info("Option previously selected : "
							+ getAttributValue("text", PlayerPage.objAudioLanguagePopuoOptions(i)));
					extent.extentLogger("", "Option previously selected : "
							+ getAttributValue("text", PlayerPage.objAudioLanguagePopuoOptions(i)));
					break;
				}
			}
			waitTime(2000);
			for (int i = 1; i <= findElements(PlayerPage.objAudioLanguagePopuoOption).size(); i++) {
				String checkedOption = getAttributValue("checked", PlayerPage.objAudioLanguagePopuoOptions(i));
				if (checkedOption.contains("false")) {
					logger.info("Selecting the the option which is not selected : "
							+ getAttributValue("text", PlayerPage.objAudioLanguagePopuoOptions(i)));
					extent.extentLogger("", "Selecting the the option which is not selected : "
							+ getAttributValue("text", PlayerPage.objAudioLanguagePopuoOptions(i)));
					click(PlayerPage.objAudioLanguagePopuoOptions(i), "Option selected");
					break;
				}
			}
			verifyElementPresentAndClick(PlayerPage.objAudioLanguageOKbtn, "Ok btn");
			waitTime(6000);
			click(PlayerPage.objPlayerView, "Player Screen");
			verifyElementPresentAndClick(PlayerPage.objPlayerThreeDotsIcon, "3Dot Icon");
//			touch.tap(x, y).perform();
		} else {
		}

	}

	/**
	 * Function to verify the subtitle icon and pop up
	 */
	public void SubtitleFunctionality() throws Exception {
		HeaderChildNode("Validating Subtitle Functionality");
		boolean present = verifyElementExist(PlayerPage.objSubtitlePopupText, "Subtitle/CC Text");
		if (present == true) {
			for (int i = 1; i <= findElements(PlayerPage.objSubtitlePopuoOption).size(); i++) {
				String checkedOption = getAttributValue("checked", PlayerPage.objSubtitlePopuoOptions(i));
				if (checkedOption.contains("true")) {
					logger.info("Option previously selected : "
							+ getAttributValue("text", PlayerPage.objSubtitlePopuoOptions(i)));
					extent.extentLogger("", "Option previously selected : "
							+ getAttributValue("text", PlayerPage.objSubtitlePopuoOptions(i)));
					break;
				}
			}
			waitTime(2000);
			for (int i = 1; i <= findElements(PlayerPage.objSubtitlePopuoOption).size(); i++) {
				String checkedOption = getAttributValue("checked", PlayerPage.objSubtitlePopuoOptions(i));
				if (checkedOption.contains("false")) {
					logger.info("Selecting the the option which is not selected : "
							+ getAttributValue("text", PlayerPage.objSubtitlePopuoOptions(i)));
					extent.extentLogger("", "Selecting the the option which is not selected : "
							+ getAttributValue("text", PlayerPage.objSubtitlePopuoOptions(i)));
					click(PlayerPage.objSubtitlePopuoOptions(i), "Option selected");
					break;
				}
			}
			verifyElementPresentAndClick(PlayerPage.objSubtitleOKbtn, "Ok button");
			waitTime(6000);
			click(PlayerPage.objPlayerView, "Player Screen");
			verifyElementPresentAndClick(PlayerPage.objPlayerThreeDotsIcon, "3Dot Icon");
		} else {
		}

	}

	/**
	 * Function to add the content to watch list
	 */
	public void AddToWatchListFunctionality() throws Exception {
		HeaderChildNode("Validating Add TO Watch List Functionality");
		if (PopUp) {
			String title = getAttributValue("text", PlayerPage.objtvShowTitle);
			waitTime(5000);
			click(PlayerPage.objPlayerView, "Player Screen");
			click(PlayerPage.objPlayerMinimizeIcon, "Player Down arrow Icon");
			click(PlayerPage.objBackBtn, "Back Button");
			CheckHamburgerWatchList(title);
		} else {
			logger.info("Add To Watch List Option not present in 3Dot Options");
			extent.extentLogger("", "Add To Watch List Option not present in 3Dot Options");
		}

	}

	/**
	 * Function to verify the added content in watchList in hamburger Icon
	 */
	public void CheckHamburgerWatchList(String showtitle) throws Exception {
		boolean present = verifyElementExist(PlayerPage.objHamburgerIcon, "Hamburger Icon");
		if (present == true) {
			click(PlayerPage.objHamburgerIcon, "Hamburger Icon");
//			ScrollTillElementVisible("Watchlist");
			click(PlayerPage.objWatchList, "Watchlist");
			String str = getAttributValue("text", PlayerPage.objWatchlistcount);
			String value;
			Pattern p = Pattern.compile("(\\d)");
			Matcher m = p.matcher(str);
			m.find();
			value = m.group(1);
			if (!value.equals("0")) {
				for (int i = 1; i <= findElements(PlayerPage.objWatchlistsItems).size(); i++) {
					if (showtitle.contains(getAttributValue("text", PlayerPage.objWatchlistsItem(i)))) {
						extent.extentLogger("", showtitle + " is present in Watchlist");
						logger.info(showtitle + " is present in Watchlist");

						break;
					} else {
						System.out.println();
					}
				}
			}
		} else {
			extent.extentLogger("", "Hamburger not present");
			logger.info("Hamburger not present");
		}
	}

	/*
	 * Remove from watchlist
	 */
	public void RemoveFromWatchList() throws Exception {
		waitTime(2000);
		click(PlayerPage.objRemoveAllBtn, "Remove ALl");
		waitTime(1000);
		click(PlayerPage.objDeleteBtn, "Delete buitton");
		waitTime(5000);
	}

	/**
	 * Function to fetch the MastheadCarousel Titles
	 * 
	 * @return
	 */
	public List<String> MastheadAPI() {
		resp = ResponseInstance.getResponse();
		for (int i = 0; i <= 6; i++) {
			MastheadTitleApi.add(resp.jsonPath().getString("buckets[0].items[" + i + "].title"));
		}
		return MastheadTitleApi;
	}

	/**
	 * Function to verify the UI Title of an mastheadCarousel title with response
	 * from API
	 * 
	 * @throws Exception
	 */
	public void ValidateMastheadCarousel() throws Exception {
		List<String> mastHeadTitleList = MastheadAPI();
		for (int i = 0; i < mastHeadTitleList.size(); i++) {
			System.out.println("List api : " + mastHeadTitleList.get(i));
			String mastHeadXpath = "//*[@resource-id='com.graymatrix.did:id/image_slider_text' and @text='"
					+ mastHeadTitleList.get(i) + "']";
			List<WebElement> mastHeadEle1 = getDriver().findElements(By.xpath(mastHeadXpath));
			System.out.println(mastHeadEle1.size());
			WebElement mastHeadEle = (new WebDriverWait(getDriver(), 30))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mastHeadXpath)));
			if (mastHeadEle.isDisplayed()) {
				logger.info("MastheadCarousel Title " + mastHeadEle.getText() + " is validated with API Response.");
				extent.extentLogger("",
						"MastheadCarousel Title " + mastHeadEle.getText() + " is validated with API Response.");
			} else {
				extent.extentLogger("",
						"MastheadCarousel Title " + mastHeadEle.getText() + " is not matching with API Response.");
				logger.info("MastheadCarousel Title " + mastHeadEle.getText() + " is not matching with API Response.");
			}
		}
	}

	/**
	 * Function to verify the Rail Content Title in Trays
	 */
	public void SwipeTOMidOfScreen(String trayName) throws Exception {
		HeaderChildNode("Verify the title of an Content in Rail with API");
		ArrayList<String> arrayTrays = ResponseInstance.traysIndiviualTags(trayName);
		for (int i = 0; i < arrayTrays.size(); i++) {
			if (verifyElementPresent(By.xpath("//*[@text='" + arrayTrays.get(i) + "']"), arrayTrays.get(i))) {
				extent.extentLogger("", "Title '" + arrayTrays.get(i) + "' is matched with API Response");
				logger.info("Title '" + arrayTrays.get(i) + "' is matched with API Response");
			} else {
				extent.extentLogger("", "Title '" + arrayTrays.get(i) + "' is not matched with API Response");
				logger.info("Title '" + arrayTrays.get(i) + "' is not matched with API Response");
			}
			SwipeRail(arrayTrays.get((i)));
			waitTime(2000);
		}
	}

	/**
	 * Function to swipe the content of an tray rail
	 */
	@SuppressWarnings("unused")
	public void SwipeRail(String TrayName) throws Exception {
		WebElement element = findElement(By.xpath("//*[@text=\"" + TrayName + "\"]"));
		String eleX = element.getAttribute("x");
		String eleY = element.getAttribute("y");
		int currentPosX = Integer.parseInt(eleX);
		int currentPosY = Integer.parseInt(eleY);
//		getDriver().swipe(currentPosX, currentPosY, 50, currentPosY, 2000);
	}

	/**
	 * Function to click on Non Premium Video
	 */
	public void NonPremiumVideo() throws Exception {
		HeaderChildNode("Play Non Premium Video");
//		ScrollTillElementVisible("Happy New Year");
		click(PlayerPage.objPlayBtn("Happy New Year"), "Play button");
		PinPoup();
	}

	/**
	 * Function to scroll for the visibility of an Tray
	 */
//	@SuppressWarnings("unchecked")
//	public void ScrollTillElementVisible(String text) {
//		for (int i = 0; i <= 5; i++)
//			try {
//				((FindsByAndroidUIAutomator<AndroidElement>) getDriver()).findElementByAndroidUIAutomator(
//						"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\").instance(0)));");
//			} catch (Exception e) {
//			}
//	}

	/**
	 * Function to verify Trailer Button
	 */
	public void VerifyTrailerBtn() throws Exception {
		HeaderChildNode("Verifying Trailer Button");
		waitTime(5000);
		LoadingInProgress();
		AdVerify();
		PinCancelPopUp();
		click(PlayerPage.objPlayerView, " Player screen");
		verifyElementPresentAndClick(PlayerPage.objNextBtn, " Next button");
		click(PlayerPage.objSubCancleBtn, "SubPopup");
		if (verifyElementExist(PlayerPage.objTrailerBtn, "Trailer button")) {
			extent.extentLogger("Trailer button", "is displayed");
			logger.info("Trailer button is displayed");
		} else {
			extent.extentLogger("Trailer button", "is not displayed");
			logger.info("Trailer button is not displayed");
		}

	}

	/**
	 * 
	 */
	public void PinPoup() throws Exception {
		if (verifyElementExist(PlayerPage.objPinPopup, "Pin popup")) {
			click(PlayerPage.objPinPopupTxt, "Pin popup");
			getDriver().findElement(PlayerPage.objPinPopupTxt).sendKeys("1234");
			extent.extentLogger("PinPopup", "PinPopup is present");
		} else {
			extent.extentLogger("PinPopup", "PinPopup is not present");
		}
	}

	/**
	 * Function to verify the functionality
	 */
	public void VerifyDowloadFunctonality() throws Exception {
		HeaderChildNode("Verifying Download Functionality");
		waitTime(4000);
		potraitToMiniplayer();
		click(PlayerPage.objminiPlayer, "Mini player");
		String v1 = getElementPropertyToString("text", VideoPlayer.objShowTitle, "Player Header");
		VerifyAddToWatchList();
		verifyElementPresent(DownloadPage.objDownloadIcon, "Download icon");
		verifyElementPresentAndClick(DownloadPage.objDownloadIcon, "Download icon");
		verifyElementExist(DownloadPage.objDownloadOptions, "Download popup");
		verifyElementPresent(DownloadPage.objDownoadQualitytxt1, "Best");
		String welecometext1 = getElementPropertyToString("text", DownloadPage.objDownoadQualitytxt1,
				"Download Quality Pop up");
		verifyElementExist(DownloadPage.objDownoadQualitytxt1, welecometext1);
		verifyElementPresent(DownloadPage.objDownoadQualitytxt2, "Better");
		String welecometext2 = getElementPropertyToString("text", DownloadPage.objDownoadQualitytxt2,
				"Download Quality Pop up");
		verifyElementExist(DownloadPage.objDownoadQualitytxt2, welecometext2);
		verifyElementPresent(DownloadPage.objDownoadQualitytxt3, "Better");
		String welecometext3 = getElementPropertyToString("text", DownloadPage.objDownoadQualitytxt3,
				"Download Quality Pop up");
		verifyElementExist(DownloadPage.objDownoadQualitytxt3, welecometext3);
		verifyElementPresent(DownloadPage.objDownoadQualitytxt4, "Better");
		String welecometext4 = getElementPropertyToString("text", DownloadPage.objDownoadQualitytxt4,
				"Download Quality Pop up");
		verifyElementExist(DownloadPage.objDownoadQualitytxt4, welecometext4);
		verifyElementPresentAndClick(DownloadPage.objDownoadQualitytxt3, "Download quality button");
		click(VideoPlayer.objDownloadOptionOKBtn, "OK button on download popup");
		verifyElementExist(VideoPlayer.objDeviceStoragePopup, "Internal Storage popup");
		click(VideoPlayer.objDeviceStoragePopupOkBtn, "OK button on Internal storage Popup");
		// Potrait to mini player
		potraitToMiniplayer();
		Back(3);
		waitTime(3000);
		DragMiniPlayer(PlayerPage.objminiPlayer);
		verifyElementPresentAndClick(HamburgerMenuPage.objHamburgerMenu, "Hamburgermenu Btn");
		//
		verifyElementPresentAndClick(PlayerPage.objWatchLaterBtn, "Watchlist");
		String v3 = getElementPropertyToString("text", PlayerPage.objwatchlistShowTitle, "Show Title");
		if ((v1.equalsIgnoreCase(v3))) {
			softAssert.assertEquals(v1.equalsIgnoreCase(v3), false, "Video is added to watchlist");
			logger.info("The selected Video Downloaded");
			extent.extentLogger("Download video", "Video is added to watchlist");
		} else {
			softAssert.assertEquals(true, true, "Video is not added to watchlist");
			softAssert.assertAll();
			logger.info("Video is not added to watchlist");
			extent.extentLogger("Download video", "Video is not added to watchlist");
		}
		Back(1);
		verifyElementPresentAndClick(HamburgerMenuPage.objHamburgerMenu, "Hamburgermenu Btn");
		PartialSwipe("UP", 1);
		verifyElementPresentAndClick(HamburgerMenuPage.objMyDownloadBtn, "My downloads page");
		String v2 = getElementPropertyToString("text", VideoPlayer.objDownloadedShowTitle, "Show title");
		if ((v1.equalsIgnoreCase(v2))) {
			softAssert.assertEquals(v1.equalsIgnoreCase(v2), false, "The selected Video is Downloaded");
			logger.info("The selected Video Downloaded");
			extent.extentLogger("Download video", "The selected Video Downloaded");
		} else {
			softAssert.assertEquals(true, true, "The selected Video is not Downloaded");
			softAssert.assertAll();
			logger.info("The selected Video is not Downloaded");
			extent.extentLogger("Download video", "The selected Video is not Downloaded");
		}

	}

	/**
	 * Drag Mini player
	 *
	 * @param byLocator, byTimer
	 */
	public void DragMiniPlayer(By byLocator) throws Exception {
		WebElement element = getDriver().findElement(byLocator);
		String ele = element.getAttribute("bounds");
		String y = ele.replace("[", "").replace("]", ",");
		System.out.println("y : " + y);
		String[] coordinates = y.split(",");
		int startx = Integer.valueOf(coordinates[0]);
		int starty = Integer.valueOf(coordinates[1]);
//		getDriver().swipe(startx, starty, 53, starty, 2000);
		touchAction.longPress(PointOption.point(startx, starty))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point((int) 53, (int) starty)).release().perform();
	}

	/**
	 * Function to verify Next Button in the Player
	 */
	public void VerifyNextBtn() throws Exception {
		HeaderChildNode("Validaing Next Button functionality");
		waitTime(8000);
		verifyElementPresentAndClick(PlayerPage.objSearchActionBar, "Search icon");
		getDriver().findElement(PlayerPage.objSearchBar).sendKeys("Jothe Jotheyali Show");
		click(PlayerPage.objSearchedVideo, "video");
		waitTime(5000);
		PinCancelPopUp();
		LoadingInProgress();
		AdVerify();
		PartialSwipe("UP", 1);
		click(PlayerPage.objViewAllBtn, "View All Button");
		waitTime(3000);
		String BeforeTapOnNextBtn = getDriver().findElement(PlayerPage.objShowName(3)).getText();
		verifyElementPresentAndClick(PlayerPage.objShowName(4), "Show");
		waitTime(6000);
		LoadingInProgress();
		AdVerify();
		waitTime(3000);
		click(PlayerPage.objPlayerView, "player");
		LoadingInProgress();
		AdVerify();
		click(PlayerPage.objPlayerView, "player");
		click(PlayerPage.objPlayerView, "player");
		Boolean NextBtn = verifyElementExist(PlayerPage.objNextBtn, "Next button");
		if (NextBtn == true) {
			FullScreen();
			waitTime(5000);
			LoadingInProgress();
			click(PlayerPage.objPlayerView, "player");
			click(PlayerPage.objPlayerView, "player");
			verifyElementPresentAndClick(PlayerPage.objNextBtn, "Next button");
			PinCancelPopUp();
			waitTime(6000);
			LoadingInProgress();
			AdVerify();
			click(PlayerPage.objPlayerView, "player");

			click(PlayerPage.objPlayPauseIcon, "Pause");
			String AfterTapingOnNextBtn = getDriver().findElement(PlayerPage.objPlayerShowtitle).getText();
			if (BeforeTapOnNextBtn.equalsIgnoreCase(AfterTapingOnNextBtn)) {
				extent.extentLogger("", "Next video is initied");
				potraitToMiniplayer();
				DragMiniPlayer(PlayerPage.objminiPlayer);

				waitTime(2000);
			} else {
				extent.extentLogger("", "Next video is not initied");
				potraitToMiniplayer();

				DragMiniPlayer(PlayerPage.objminiPlayer);

			}
		} else {
			extent.extentLogger("", "Next button is not present");
		}

	}

	/**
	 * Pin cancel popup
	 */
	public void PinCancelPopUp() throws Exception {
		if (verifyElementExist(PlayerPage.objSubCancleBtn, "Pin cancel popup")) {
			click(PlayerPage.objSubCancleBtn, "Pin cancel popup");
			extent.extentLogger("", "Pin cancel popup is present");
		} else {
			extent.extentLogger("", "Pin cancel popup is not present");
		}
	}

	/**
	 * Function to scrub the video
	 */
	public void scrubVideo(int n) throws Exception {
		waitTime(4000);
		waitTime(4000);
		LoadingInProgress();
		if (verifyElementExist(PlayerPage.objsubPopupCanclBtn, "Subsribe pop-up close button")) {
			click(PlayerPage.objsubPopupCanclBtn, "Pop-up Cancel Button");
		}
		click(PlayerPage.objPlayerView, "player");
		waitForElementDisplayed(PlayerPage.objWaitForElementDisplay, 30);
		AdVerify();
		waitTime(4000);
		AdVerify();
		waitTime(4000);
		click(PlayerPage.objPlayerView, "player");
		String beforeSeek = findElement(PlayerPage.objPlayerCurrentTime).getText();
		extent.extentLogger(beforeSeek, "start time  before seek " + beforeSeek);
		click(PlayerPage.objPlayerView, "player");
		WebElement element = getDriver().findElement(PlayerPage.objPlayerSeekBar);
		String ele = element.getAttribute("bounds");
		String y = ele.replace("[", "").replace("]", ",");
		String[] coordinates = y.split(",");
		int startX = Integer.valueOf(coordinates[0]);
		int startY = Integer.valueOf(coordinates[1]);
		int endX = Integer.valueOf(coordinates[2]);
		int endY = Integer.valueOf(coordinates[3]);
		click(PlayerPage.objPlayerView, "player");
//		System.out.println("startX : "+startX+"\n startY : "+startY+"\n endX : "+endX+"\n endY : "+endY+"\n\n");
//		System.out.println("startX : "+startX+"\n startY : "+startY+"\n endX : "+(int) endX / n+"\n endY : "+(int) endY / n);
//		getDriver().swipe(startX, startY, ((int) endX / n)+50, ((int) endY / n)+50, 1000);
		touchAction.longPress(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(((int) endX / n) + 50, ((int) endY / n) + 50)).release().perform();

		waitTime(5000);
		LoadingInProgress();
		AdVerify();
		waitTime(2000);
		AdVerify();
		click(PlayerPage.objPlayerView, "player");
		AdVerify();
		String AfterSeek = findElement(PlayerPage.objPlayerCurrentTime).getText();
		extent.extentLogger(AfterSeek, "start time after seek " + AfterSeek);
		Assert.assertNotEquals(beforeSeek, AfterSeek);
		logger.info("Validated Seek Functionality");
		extent.extentLogger("seek", "Validated Seek Functionality");
	}

	/**
	 * Function to change Orientation from portrait to full screen
	 */
	public void FullScreen() throws Exception {
		playerClick(PlayerPage.objPlayerView, "player");
		verifyElementPresentAndClick(VideoPlayer.objFullScrnBtn, "Full Screen option");
		GetAndVerifyOrientation("Landscape");
	}

	/**
	 * Login to Zee5 using invalid credentials
	 */

	public void ZeeInvalidLogin(String loginType) throws Exception {
		HeaderChildNode("<b>GTM </b>:: " + loginType);
		switch (loginType) {
		case "Mobile":
			verifyElementPresentAndClick(BaseLoginPage.objMobileNumber, "Mobile Icon");
			verifyElementPresent(TraditionalLoginPage.objMobileNumbertxtField, "Mobile number Field");
			type(TraditionalLoginPage.objMobileNumbertxtField, "1234567980", "Mobile number text Field");
			hideKeyboard();
			verifyElementPresent(TraditionalLoginPage.objPasswordTxtField, "Password Text Field");
			type(TraditionalLoginPage.objPasswordTxtField, "1234567980", "Password Field");
			hideKeyboard();
			verifyElementPresentAndClick(TraditionalLoginPage.objLogintn, "Login Button");
			hideKeyboard();
			Back(1);
			break;

		case "Email":
			verifyElementPresentAndClick(BaseLoginPage.objEmailID, "Email Icon ");
			verifyElementPresent(TraditionalLoginPage.objEmailId, "Email ID Field");
			type(TraditionalLoginPage.objEmailId, "basavaraj.pn5@gmail.com", "Email Field");
			hideKeyboard();
			verifyElementPresent(TraditionalLoginPage.objPassword, "Password Field");
			type(TraditionalLoginPage.objPassword, "abcd1677", "Password Field");
			hideKeyboard();
			click(TraditionalLoginPage.objLoginBtn, "Login Button");
			waitTime(5000);
			Back(1);
			break;

		case "Twitter":
			verifyElementPresentAndClick(BaseLoginPage.objTwitterIcon, "Twitter Icon");
			break;

		case "FB":
			verifyElementPresentAndClick(BaseLoginPage.objFacebookIcon, "Facebook Icon");
			if (findElements(SocialMediaLoginPage.objCountinueBtn).size() != 0) {
				verifyElementPresentAndClick(SocialMediaLoginPage.objCountinueBtn, " Clicked On Countinue Button");
			} else {
				verifyElementPresent(SocialMediaLoginPage.objEmailIdtxtField, " Email Field");
				type(SocialMediaLoginPage.objEmailIdtxtField, "ABC@Gmail.com", "Email Field");
				verifyElementPresent(SocialMediaLoginPage.objPasswordTxtField, " Password Field");
				type(SocialMediaLoginPage.objPasswordTxtField, "XYZ", "Password Field");
				verifyElementPresentAndClick(SocialMediaLoginPage.objLoginBtn, " Login Button ");
			}
			break;

		case "Google":
			verifyElementPresentAndClick(BaseLoginPage.objGoogleIcon, " Google Icon");
			break;

		case "Skip":
			verifyElementPresentAndClick(BaseLoginPage.objSkipBtn, " Skip Icon");
			waitTime(5000);
			Back(1);
			break;

		}
	}

	/**
	 * Unsuccessful Registration to Zee5
	 */

	public void ZeeUnsuccessfullRegistration(String loginType) throws Exception {
		HeaderChildNode("<b>GTM </b>:: Unsuccessfull Registration - " + loginType);
		verifyElementPresentAndClick(BaseLoginPage.objRegisterBtn, "Register Button");
		waitTime(5000);
		switch (loginType) {
		case "Mobile":
			verifyElementPresentAndClick(BaseLoginPage.objMobileNumber, "Mobile Icon");
			verifyElementPresent(TraditionalLoginPage.objMobileNumbertxtField, "Mobile number Field");
			type(TraditionalLoginPage.objMobileNumbertxtField, "1234567980", "Mobile number text Field");
			verifyElementPresent(TraditionalLoginPage.objPasswordTxtField, "Password Text Field");
			type(TraditionalLoginPage.objPasswordTxtField, "1234567980", "Password Field");
			verifyElementPresentAndClick(TraditionalLoginPage.objLogintn, "Login Button");
			hideKeyboard();
			Back(1);
			break;

		case "Email":
			verifyElementPresentAndClick(BaseLoginPage.objEmailID, "Email Icon ");
			verifyElementPresent(TraditionalLoginPage.objEmailId, "Email ID Field");
			type(TraditionalLoginPage.objEmailId, "basavaraj.pn5@gmail.com", "Emial Field");
			hideKeyboard();
			verifyElementPresent(TraditionalLoginPage.objPassword, "Password Field");
			type(TraditionalLoginPage.objPassword, "abcd1677", "Password Field");
			hideKeyboard();
			verifyElementPresentAndClick(RegistrationPage.objTermsCheckbox, "Terms and conditions Checkbox");
			verifyElementPresentAndClick(RegistrationPage.objAgeCheckbox, "Age Checkbox");
			click(RegistrationPage.objRegisterbtn, "Register button");
			waitTime(5000);
			Back(1);
			break;

		case "Twitter":
			verifyElementPresentAndClick(BaseLoginPage.objTwitterIcon, "Twitter Icon");
			break;

		case "FB":
			verifyElementPresentAndClick(BaseLoginPage.objFacebookIcon, "Facebook Icon");
			if (findElements(SocialMediaLoginPage.objCountinueBtn).size() != 0) {
				verifyElementPresentAndClick(SocialMediaLoginPage.objCountinueBtn, " Clicked On Countinue Button");
			} else {
				verifyElementPresent(SocialMediaLoginPage.objEmailIdtxtField, " Email Field");
				type(SocialMediaLoginPage.objEmailIdtxtField, "ABC@Gmail.com", "Emial Field");
				verifyElementPresent(SocialMediaLoginPage.objPasswordTxtField, " Password Field");
				type(SocialMediaLoginPage.objPasswordTxtField, "XYZ", "Password Field");
				verifyElementPresentAndClick(SocialMediaLoginPage.objLoginBtn, " Login Button ");
			}
			break;

		case "Google":
			verifyElementPresentAndClick(BaseLoginPage.objGoogleIcon, " Google Icon");
			break;

		case "Skip":
			verifyElementPresentAndClick(BaseLoginPage.objSkipBtn, " Skip Icon");
			waitTime(5000);
			Back(1);
			break;

		}
	}

	/**
	 * Zee5 Successful Registration
	 */

	public void ZeeRegistration(String loginType) throws Exception {
		HeaderChildNode("<b>GTM </b>:: Registration - " + loginType);
		verifyElementPresentAndClick(BaseLoginPage.objRegisterBtn, "Register Button");
		waitTime(5000);
		switch (loginType) {
		case "Mobile":
			verifyElementPresentAndClick(BaseLoginPage.objMobileNumber, "Mobile Icon");
			verifyElementPresent(TraditionalLoginPage.objMobileNumbertxtField, "Mobile number Field");
			type(TraditionalLoginPage.objMobileNumbertxtField, "1234567980", "Mobile Number Field");
			verifyElementPresent(TraditionalLoginPage.objPasswordTxtField, "Password Text Field");
			type(TraditionalLoginPage.objPasswordTxtField, "1234567980", "Password Field");
			verifyElementPresentAndClick(TraditionalLoginPage.objLogintn, "Login Button");
			hideKeyboard();
			Back(1);
			break;

		case "Email":
			verifyElementPresentAndClick(BaseLoginPage.objEmailID, "Email Icon ");
			verifyElementPresent(TraditionalLoginPage.objEmailId, "Email ID Field");
			int random = (int) (Math.random() * 10000 + 1);
			type(TraditionalLoginPage.objEmailId, "testing" + random + "@gmail.com", "Email Field");
			hideKeyboard();
			verifyElementPresent(TraditionalLoginPage.objPassword, "Password Field");
			type(TraditionalLoginPage.objPassword, "abcd1677", "Password Filed");
			hideKeyboard();
			verifyElementPresentAndClick(RegistrationPage.objTermsCheckbox, "Terms and conditions Checkbox");
			verifyElementPresentAndClick(RegistrationPage.objAgeCheckbox, "Age Checkbox");
			click(RegistrationPage.objRegisterbtn, "Register button");
			LoadingInProgress();
			waitTime(5000);
			verifyElementPresentAndClick(RegistrationPage.objRegisterContinuebtn, "Continue button");
			LoadingInProgress();
			waitTime(5000);
			verifyElementPresentAndClick(RegistrationPage.objRegisterBrowsebtn, "Browse button");
			waitTime(5000);
			break;

		case "Twitter":
			verifyElementPresentAndClick(BaseLoginPage.objTwitterIcon, "Twitter Icon");
			break;

		case "FB":
			verifyElementPresentAndClick(BaseLoginPage.objFacebookIcon, "Facebook Icon");
			if (findElements(SocialMediaLoginPage.objCountinueBtn).size() != 0) {
				verifyElementPresentAndClick(SocialMediaLoginPage.objCountinueBtn, " Clicked On Countinue Button");
			} else {
				verifyElementPresent(SocialMediaLoginPage.objEmailIdtxtField, " Email Field");
				type(SocialMediaLoginPage.objEmailIdtxtField, "ABC@Gmail.com", "Email Field");
				verifyElementPresent(SocialMediaLoginPage.objPasswordTxtField, " Password Field");
				type(SocialMediaLoginPage.objPasswordTxtField, "XYZ", "Password Field");
				verifyElementPresentAndClick(SocialMediaLoginPage.objLoginBtn, " Login Button ");
			}
			break;

		case "Google":
			verifyElementPresentAndClick(BaseLoginPage.objGoogleIcon, " Google Icon");
			break;

		case "Skip":
			verifyElementPresentAndClick(BaseLoginPage.objSkipBtn, " Skip Icon");
			waitTime(5000);
			Back(1);
			break;

		}
	}

	/**
	 * Verify GTM Calls
	 */

	@SuppressWarnings({ "resource", "unused" })
	public void verifyGTMCall(String GTMFileName, String GTMDelimiter) throws Exception {
		HeaderChildNode("Verify GTM Calls - " + GTMDelimiter);
		String dir = System.getProperty("user.dir");
		String GTMfile = dir + "//GTM_Logs//" + GTMFileName + ".txt";
		File file = new File(GTMfile);
		String Delimiter = GTMDelimiter + ": Bundle";
		try {
			Scanner scanner = new Scanner(file);

			// now read the file line by line...
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNum++;
				// extent.extentLogger(Delimiter +" call verify", "Verifying " + Delimiter + "
				// event call");
				if (line.contains(Delimiter)) {
					String[] EventName = Delimiter.split(":");
					logger.info(EventName[0] + " event is displayed");
					extent.extentLogger(EventName[0] + " event", EventName[0] + " event is displayed");
					String[] first = line.split(Delimiter + "\\[");
					String[] total = first[1].split("]");

					String FirstReplace = total[0].replace("{", "");
					String SecondReplace = FirstReplace.replace("}", "");
					String keyValArray[] = SecondReplace.split(", ");

					for (int a = 0; a < keyValArray.length; a++) {
						String[] split1 = keyValArray[a].split("=");
						if (split1.length > 1) {
							if (split1[0].equalsIgnoreCase("Content_Date")) {
								String[] split2 = keyValArray[a + 1].split("=");
								logger.info("The value of " + split1[0] + " " + split1[1] + ", " + split2[0]);
								extent.extentLogger(EventName[0] + " details",
										"The value of <b>" + split1[0] + " " + split1[1] + ", " + split2[0]);
								a = a + 1;
							} else {
								logger.info("The value of " + split1[0] + " is " + split1[1]);
								extent.extentLogger(EventName[0] + " details",
										"The value of <b>" + split1[0] + "</b> is <b>" + split1[1]);
							}
						} else if (split1.length == 1) {
							logger.info("The value of " + split1[0] + " is null");
							extent.extentLoggerFail(EventName[0] + " details",
									"The value of <b>" + split1[0] + "</b> is <b>null");
						}
					}
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Capture GTM Calls
	 */

	public void captureGTMCall(String GTMFileName) throws Exception {
		String dir = System.getProperty("user.dir");
		String GTMfile = dir + "//GTM_Logs//" + GTMFileName + ".txt";
		File file = new File(GTMfile);

		if (file.exists()) {
			file.delete();
			waitTime(5000);
		}
		try {
			Runtime rt = Runtime.getRuntime();
			if (GTMFileName.equals("videoQuality")) {
				rt.exec("cmd /c adb logcat -s \"D PlaybackManager\" >" + GTMfile);
			} else {
				rt.exec("cmd /c adb logcat -s \"D Firebaseanalytics\" >" + GTMfile);
				System.out.println("Firebaseanalytics");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Function to play a non premium video and wait for playback
	 */

	public void PlayNonPremiumVideo() throws Exception {
//		ScrollTillElementVisible("Happy New Year");
		click(PlayerPage.objPlayBtn("Happy New Year"), "Play button");
		waitForElementDisplayed(PlayerPage.objWaitForElementDisplay, 30);
		AdVerify();
		click(PlayerPage.objPlayerView, "PlayerScreen");
		waitTime(4000);
	}

	/**
	 * Function to get Content from ViewAll button
	 * 
	 * @throws Exception
	 */
	public void ViewAllContentAPI() throws Exception {
		HeaderChildNode("Validating the contents of ViewAll Screen with API");
		ArrayList<String> contentFromAPI = ResponseInstance.ContentOfViewAll();
		if (contentFromAPI != null) {
			for (int i = 0; i < contentFromAPI.size(); i++) {

				if (verifyElementPresent(VideoPlayer.objtitleOfAnContentInViewAll(contentFromAPI.get(i)), "Title")) {
					logger.info("Title of an conent" + contentFromAPI.get(i) + " is matched with API Response");
					extent.extentLogger("",
							"Title of an conent" + contentFromAPI.get(i) + " is matched with API Response");
				} else {
					logger.info("Title of an conent" + contentFromAPI.get(i) + " is not matched with API Response");
					extent.extentLogger("",
							"Title of an conent" + contentFromAPI.get(i) + " is not matched with API Response");
				}
				if (i == 9 || i == 13) {
					Swipe("UP", 1);
				}
			}
		}
	}

	public void PlayPremiumVideo() throws Exception {
		for (int i = 0; i <= 4;) {
			if (verifyElementExist(VideoPlayer.objPremiumTag, "Premium video") == true) {
				click(VideoPlayer.objPremiumTag, "Premium Video");
				extent.extentLogger("Premium", "Premium video is playing");
			} else {
				Swipe("up", 1);
			}
			break;
		}
	}

	@SuppressWarnings({ "resource", "unused" })
	public void GTMCallForVideoQuality(String GTMFileName) throws FileNotFoundException {
		int count = 1;
		String dir = System.getProperty("user.dir");
		String GTMfile = dir + "//GTM_Logs//" + GTMFileName + ".txt";
		File file = new File(GTMfile);
		if (file.exists()) {
			Scanner scanner = new Scanner(file);

			// now read the file line by line...
			int lineNum = 0;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNum++;
				if (line.contains("videoQuality")) {
					String[] splitVideoQuality = line.split(": ");
					Pattern p = Pattern.compile("videoQuality(.*)");
					Matcher m = p.matcher(splitVideoQuality[1]);
					m.find();
					Pattern p1 = Pattern.compile("(\\d+)");
					Matcher m1 = p1.matcher(m.group(1));
					m1.find();
					if (!m.group(1).contains("Auto")) {
						String BitRatevalue = (m.group(1).split((" ")))[1].replace("(", "");
						int BitValue = Integer.valueOf(BitRatevalue);
						if (m.group(1).contains("144p") && (BitValue > 0 || BitValue < 300)) {
							logger.info("Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
							extent.extentLogger("",
									"Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
						} else if (m.group(1).contains("240p") && (BitValue > 200 || BitValue < 420)) {
							logger.info("Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
							extent.extentLogger("",
									"Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
						} else if (m.group(1).contains("720p") && (BitValue > 720 || BitValue < 1080)) {
							logger.info("Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
							extent.extentLogger("",
									"Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
						} else if (m.group(1).contains("1080p") && (BitValue > 1080 || BitValue < 3000)) {
							logger.info("Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
							extent.extentLogger("",
									"Bit Rate " + BitRatevalue + "kbps for the selected Quality : " + m1.group(0));
						}
					} else {

						if (count == 1) {
							logger.info("Quality is set to Auto");
							extent.extentLogger("", "Quality is set to Auto");
						}
						count++;

					}
				}
			}
		}
	}

	public void PlayerControlView() throws Exception {
		waitTime(12000);
		AdVerify();
//		if(findElements(PlayerPage.objPlayPauseIcon).size() != 1 ) 
//		{
//			click(PlayerPage.objPlayerView, "Payer Screen");
//			for (int i = 0; i < 5; i++) {
//				waitTime(3000);
//				if(findElements(PlayerPage.objPlayPauseIcon).size() != 1) {
//					click(PlayerPage.objPlayerView, "Payer Screen");
//				}else {
//					break;
//				}
//			}
//		}

		for (int i = 0; i < 5; i++) {
			click(PlayerPage.objPlayerView, "Payer Screen");
			waitTime(4);
			if (findElements(PlayerPage.objPlayPauseIcon).size() != 1) {
				break;
			} else {
				click(PlayerPage.objPlayerView, "Payer Screen");
			}
		}
	}

	public void MultiLanguage() throws Exception {
		HeaderChildNode(" Playing Video ");
		verifyElementPresentAndClick(PlayerPage.objSearchActionBar, "Search icon");
		getDriver().findElement(PlayerPage.objSearchBar).sendKeys("36 China Town Movie");
		getDriver().findElement(PlayerPage.objSearchBar).sendKeys(Keys.INSERT);
//		ScrollTillElementVisible("36 China Town");
//		click(PlayerPage.objSearchTabs("MOVIES"), "Movies Tab");
		click(PlayerPage.objSearchMovieFirstContent, " Video ");
	}

	/**
	 * Function to verify the Rail Content Title in Trays
	 */
	public void StartVideoInRail(String contentName) throws Exception {
		HeaderChildNode("Verify the title of an Content in Rail with API");

		for (int i = 0; i < 20; i++) {
			if (verifyElementPresent(By.xpath("//*[@text='" + contentName + "']"), contentName)) {
				click(By.xpath("//*[@text='" + contentName + "']"), contentName);
			}
			SwipeRail();
			waitTime(2000);
		}
	}

	/**
	 * Function to swipe the content of an tray rail
	 */
	public void SwipeRail() throws Exception {
		WebElement element = findElement(By.xpath(
				"(//*[@text='Trending on ZEE5']/parent::*/parent::*/child::android.support.v7.widget.RecyclerView/child::*)[2]"));
		String eleX = element.getAttribute("x");
		String eleY = element.getAttribute("y");
		int currentPosX = Integer.parseInt(eleX);
		int currentPosY = Integer.parseInt(eleY);
//		getDriver().swipe(currentPosX, currentPosY, 50, currentPosY, 2000);
		touchAction.longPress(PointOption.point(currentPosX, currentPosY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
				.moveTo(PointOption.point((int) 50, (int) currentPosY)).release().perform();
	}

	/**
	 * Scroll to the tray and click on view all button
	 */
	public void scrollToTray(String text) throws Exception {
		HeaderChildNode("Scroll to the tray/Video");
		for (int i = 0; i <= 25; i++) {
			List<WebElement> elemore = getDriver().findElements(VideoPlayer.objScrollTotext(text));
			if (elemore.size() == 1) {
				extent.extentLogger("Scroll ", "scroll till " + text + " tray and click");
				logger.info("scroll till " + text + " tray and click");
				break;
			}
			Swipe("UP", 1);
		}
		PartialSwipe("UP", 1);
	}

	public void searchVideo() throws Exception {
		HeaderChildNode("Search Video");
		verifyElementPresentAndClick(PlayerPage.objSearchActionBar, "Search icon");
		type(PlayerPage.objSearchBar, "Kumkuma bhagya", "Search Action Field");
		getDriver().hideKeyboard();
		click(PlayerPage.objTVShowsIcon, "Tv Shows Icon");
		verifyElementPresentAndClick(PlayerPage.objKumkumaBhagya, "Kumkuma Bhagya");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void Notification() {
		AndroidDriver driver = (AndroidDriver) getDriver();
		driver.openNotifications();
		List<WebElement> allnotifications = driver.findElements(By.id("android:id/title"));
		for (WebElement webElement : allnotifications) {
			System.out.println(webElement.getText());
			if (webElement.getText().contains("something")) {
				System.out.println("Notification found");
				break;
			}
		}
	}

	public void UIValidation(String Location) throws InterruptedException {
		HeaderChildNode("UI Validation");
//		captureScreenshotAndCompare(Location);
	}

	public void PWA() throws Exception {
		extent.HeaderChildNode("PWA");
		getDriver().get("http://newpwa.zee5.com/");
		waitTime(12000);
		verifyElementPresentAndClick(By.xpath(".//*[@title='Doddmane Hudga']"), "Video");
		if (verifyElementExist(By.xpath(".//*[@class='manCloseIcon']"), "Close")) {
			verifyElementPresentAndClick(By.xpath(".//*[@class='manCloseIcon']"), "Close");
		}
		waitTime(5000);
		verifyElementPresentAndClick(By.xpath(".//*[@class=\"PlayPause-control\"]"), "Play Icon");
		System.out.println(findElement(By.xpath(".//*[@class=\"playkit-time-display\"]")).getText());
	}

	/**
	 * Scroll to the collection
	 */
	public void scrollToCollection(String CollectionName) throws Exception {
		HeaderChildNode("Scroll to the " + CollectionName);
		waitTime(5000);
		for (int i = 0; i <= 25; i++) {
			List<WebElement> elemore = getDriver().findElements(By.xpath(".//*[@text='" + CollectionName + "']"));
			if (elemore.size() == 1) {
				extent.extentLogger("Scroll ", "scroll to the " + CollectionName);
				logger.info("scroll to the  " + CollectionName);
				break;
			}
			Swipe("UP", 1);
		}
		Point Location = getDriver().findElement(By.xpath(".//*[@text='" + CollectionName + "']")).getLocation();
		Dimension Dimension = getDriver().manage().window().getSize();
		partialscrollTray(Location, Dimension);
		LoadingInProgress();
	}

	/**
	 * click on the arrow icon
	 * 
	 * @throws Exception
	 */
	public void ViewAllContent(String CollectionName) throws Exception {
		HeaderChildNode("View All content from collection " + CollectionName);
		scrollToCollection(CollectionName);
		click(HomePage.objContainerArrowButton(CollectionName), " Arrow Icon ");
	}

	/**
	 * Click on the Content/video
	 * 
	 * @throws Exception
	 */
	public void PlayContentOrVideoInViewAll(By Locator, String CollectionName) throws Exception {
		ArrayList<String> collectionItems = ResponseInstance.getCollectionContent(CollectionName);

		for (int i = 0; i < collectionItems.size(); i++) {
			click(Locator, "");
		}
	}

	/**
	 * Click on the Content/Video
	 */
	public void ClickContentOnCollection() {

	}

	/**
	 * Verify the player controls
	 */
	public void VerifyPlayerControls() {

	}

	public void testVadofone() throws InterruptedException {
		System.out.println("TEST");
		Thread.sleep(12000);
		getDriver().context("WEBVIEW_1");

//		driver.findElement(By.xpath("//*[@content-desc='Vodafone Play']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//*[@text='ZEE5']")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("((((((//*[@text='Top Movies'])//parent::*//parent::*//parent::*//parent::*)//child::*[4])//child::*//child::*)//child::*)//child::*[1])[1]")).click();
//		Thread.sleep(10000);
//		Thread.sleep(10000);
//		Thread.sleep(10000);
		System.out.println(getDriver().getCurrentUrl());
	}

	public void AdVerify() throws Exception {
//		verifyElementNotPresent(PlayerPage.objAd, 60);
	}

}
