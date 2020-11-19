package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Kushal

public class AMDOnboardingScreen {
	public static By objTellUsMore = By.xpath("//*[@text='Tell us more']");
	public static By objPermissionMsg = By.xpath("//*[@id='permission_message']");
	public static By objAllowBtn = By.xpath("//*[@id='permission_allow_button']");
	public static By objDenyBtn = By.xpath("//*[@id='permission_deny_button']");
	public static By objFeatureCarousel = By.xpath("//*[@id='zee5RailsTypeCarouselFragmentId']");
	public static By objBenefitsTextSection = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/benefitsTextRecyclerView']");
	public static By objDotsIndicator = By.xpath("(//*[@id='dotsIndicator']/*[@class='android.widget.ImageView'])");
	// Selected Display Language
	public static By objSelectedDisplayLang = By
			.xpath("//*[@id='selectionImage']//following::*[@id='display_language_content'][1]");

	// Get Screen Title
	public static By objScreenTitle = By.xpath("//*[@id='screen_title']");

	// Back Icon
	public static By objBackBtn = By.xpath("//*[@id='icon_back']");

	// To Select the Display language
	public static By objSelectDisplayLang(String Language) {
		int index = 0;
		if (Language.equalsIgnoreCase("Hindi")) {
			index = 1;
		} else if (Language.equalsIgnoreCase("English")) {
			index = 2;
		} else if (Language.equalsIgnoreCase("Marathi")) {
			index = 3;
		} else if (Language.equalsIgnoreCase("Telugu")) {
			index = 4;
		} else if (Language.equalsIgnoreCase("Kannada")) {
			index = 5;
		} else if (Language.equalsIgnoreCase("Tamil")) {
			index = 6;
		} else if (Language.equalsIgnoreCase("Malayalam")) {
			index = 3;
		} else if (Language.equalsIgnoreCase("Bengali")) {
			index = 4;
		} else if (Language.equalsIgnoreCase("Gujarati")) {
			index = 5;
		} else if (Language.equalsIgnoreCase("Punjabi")) {
			index = 6;
		} else if (Language.equalsIgnoreCase("Bhojpuri")) {
			index = 7;
		}
		return By.xpath("(//*[@id='display_language_content'])[" + index + "]");
	}

	public static By objContentLanguageContainer = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/content_lang_container']");
	public static By objContentLanguagePageTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/content_lang_screen_title']");
	// Continue button in the Display language screen
	public static By objDiplay_ContinueBtn = By.xpath("//*[@id='dl_language_selection']");

	// Select content language
	public static By objSelectContentLang(String language) {
		return By.xpath("//*[@id='btn_content_lang' and @text='" + language + "']");
	}

	// Continue button in the Content language screen
	public static By objContent_ContinueBtn = By.xpath("//*[@id='btn_content_language_selection']");

	// Login OR Skip Button in Intro Screen
	public static By objLoginLnk = By.xpath("//*[@id='skip_link']");

	public static By objLabels(String text) {
		return By.xpath("//*[@id='becomeATextView' and contains(text(),'" + text + "')]");
	}

	public static By objDotsIndicator(int index) {
		return By.xpath(
				"//*[@id='dotsIndicator']//following-sibling::*[@class='android.widget.ImageView'][" + index + "/]");
	}

	public static By objBrowseForFreeBtn = By.xpath("//*[@id='browseForFreeButton']");

	public static By objSubscribeNowBtn = By.xpath("//*[@id='joinNowButton']");

	public static By objHavePrepaidBtn = By.xpath("//*[@id='haveACouponCodeButton']");

	public static By objPrepaidPopupLabel = By.xpath("//*[@id='txt_prepaid_code']");

	public static By objPrepaidCodeField = By.xpath("//*[@id='edit_prepaid_code']");

	public static By objPopUpDivider = By.xpath("//*[@id='dialog_divider']");

	// What is Prepaid code? button in the Prepaid code Pop-up
	public static By objWhatIsPrepaidCodeBtn = By.xpath("//*[@id='txt_what_is_promo_code']");

	// Get description from About Prepaid Code
	public static By objDescriptionTxt = By.xpath("//*[@id='description']");

	// Apply Button
	public static By objApplyBtn = By.xpath("//*[@id='btn_promo_code_next']");

	// Pop-up screen elements
	public static By objFaceIcon = By.xpath("//*[@id='iconSmile']");
	public static By objSuccessTitle = By.xpath("//*[@id='txt_success_title']");
	public static By objSuccessDesc = By.xpath("//*[@id='txt_success_desc']");
	public static By objLoginBtn = By.xpath("//*[@id='btn_dialog_login']");
	public static By objRegisterBtn = By.xpath("//*[@id='btn_dialog_register']");
	public static By objDoneBtn = By.xpath("//*[@id='btn_dialog_done']");
	public static By objWatchNowBtn = By.xpath("//*[@id='btn_dialog_watch_now']");

	// Pop-up screen title
	public static By objSuccessTitle(String popupTitle) {
		return By.xpath("//*[@id='txt_success_title' and @text='" + popupTitle + "']");
	}

	public static By objNextBtnPopUpInCalender = By.xpath("//*[@id='next']");

	public static By objNextDate(String date) {
		return By.xpath("//*[@text='" + date + "']");
	}

	public static By objWaitForSplashScreenDisapear = By.xpath(
			"//*[@id='permission_allow_button'] | //*[@id='browseForFreeButton'] | //*[@id='title' and @text='Home']");
	/**
	 * Kushal
	 */

	public static By objContentLangBtns = By.xpath("//*[@id='btn_content_lang']");

	public static By objgetContentLangName(int index) {
		return By.xpath("(//*[@id='btn_content_lang'])[" + index + "]");
	}

	public static By objExitPopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/verify_account_desc']");

	public static By objExitYes = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_exit_yes']");

	public static By objExitNo = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_exit_no']");
	
	public static By objExitPopupDivider = By.xpath("//*[@resource-id='com.graymatrix.did:id/dialog_divider']");

	public static By objExitPopupHorizontalLinebar = By.xpath("(//*[@class='android.view.View'])[2]");



}
