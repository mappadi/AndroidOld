package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDSearchScreen {

	public static By objSearchIcon = By.xpath(
			"//*[@class='android.widget.ImageView' and ./parent::*[@class='android.support.v7.widget.LinearLayoutCompat']]");

	public static By objHomeOption = By
			.xpath("//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Home']]");

	public static By objUpcomingOption = By.xpath(
			"//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Upcoming']]");

	public static By objDownloadsOption = By.xpath(
			"//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='Downloads']]");

	public static By objMoreOption = By
			.xpath("//*[@id='bb_bottom_bar_icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='More']]");

	public static By objVoiceicon = By.xpath("//*[@id='voice_icon']");

	public static By objsearchBox = By.xpath("//*[@id='searchToolbarTitle']");

	public static By objMicroPhone = By.xpath("//*[@id='mainLabelTv']");

	public static By objSearchBoxEdit = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchToolbarTitle']");

	public static By objVoiceSearchBackButton = By.xpath("//*[@id='backIv']");

	public static By objRecentsearchOverlay = By
			.xpath("//*[@id='header_primary_text' and contains(text(),'Recent Searches')]");

	public static By objContentRecentSearch = By
			.xpath("//*[@id='item_primary_text' and contains(text(), ' \"+title+\" ')]");

	public static By objRecentSearchConent(String title) {
		return By.xpath("//*[@id='item_primary_text' and contains(text(), ' \"+title+\" ')]");
	}

	public static By objContentNameInPlayer(String ResultName) {
		return By.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text' and @text='" + ResultName + "']");
	}

	public static By objTrendingSearchOverlay = By
			.xpath("//*[@id='header_primary_text' and contains(text(),'Trending Searches')]");

	public static By objTopSearchOverlay = By
			.xpath("//*[@id='header_primary_text' and contains(text(),'Top Searches')]");

	public static By objMoviesTab = By.xpath("//*[@text='Movies' and @id='title']");

	public static By objShowsTab = By.xpath("//*[@text='Shows' and @id='title']");

	public static By objNewsTab = By.xpath("//*[@text='News' and @id='title']");

	public static By objFreeMoviesTab = By.xpath("//*[@text='Free Movies' and @id='title']");

	public static By objPremiumTab = By.xpath("//*[@text='Premium' and @id='title']");

	public static By objKidsTab = By.xpath("//*[@text='Kids' and @id='title']");

	public static By objMusicTab = By.xpath("//*[@text='Music' and @id='title']");

	public static By objLiveTVTab = By.xpath("//*[@text='Live TV' and @id='title']");

	public static By objZeeoriginalsTab = By.xpath("//*[@text='ZEE5 Originals' and @id='title']");

	public static By objRecentSearchContent = By.xpath("//*[@id='item_primary_text']");

	public static By objVirtualKeyboard = By.xpath("//*[@id='keyboard_area']");

	public static By objFreeMovies = By
			.xpath("//*[@id='title']//ancestor::*[@class='android.support.v7.app.ActionBar$b']");

	public static By objSearchEditBox = By.xpath("//*[@id='searchToolbarTitle']");

	public static By objSearchBoxBar = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchBar']");

	public static By objEnterKey = By.xpath("(//*[@id='icon'])[11]");

	public static By objNoSearchResults = By.xpath("//*[@id='tvNoResults']");

	public static By objRelatedSearchResult = By.xpath("//*[@id='item_primary_text']");

	// ***** Objects SearchPage by Manasa
	public static By objSearchInKeyboard = By.xpath("//*[@contentDescription='Search']");

	public static By objSearchResultPremiunm(String ResultName) {
		return By.xpath("//*[@resource-id='com.graymatrix.did:id/special_image_1']//following-sibling::*[@text='"
				+ ResultName + "']");
	}

	public static By objUpgradePopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/dialog_layout']");

	public static By objUpgradePopupTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title']");

	public static By objUpgradePopupDescription = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/upgrade_subtitle']");
	public static By objUpgradePopupProceedButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/proceed']");

	public static By objUpgradePopUpPacks(String ResultName) {
		return By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_packDescription' and @text='" + ResultName + "']");
	}

	public static By objTermsOfUse = By.xpath("//*[@resource-id='com.graymatrix.did:id/terms_of_use']");
	public static By objPrivacyPolicy = By.xpath("//*[@resource-id='com.graymatrix.did:id/privacy_policy']");

	public static By objPlanPrice = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_plan_price']");
	public static By objDiscount = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_discount']");
	public static By objRoundoff = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_round_off']");
	public static By objTotalPayableAmount = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_total_amount']");
	public static By objRevisedBillingInfo = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/revised_billing_cycle_decription']");
	public static By objAccountInfo = By.xpath("//*[@resource-id='com.graymatrix.did:id/account_info_title']");
	public static By objYouWillBeChargedInfo = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/payment_recurring_msg']");

	public static By objClearSearch = By.xpath("//*[@id='clearSearch']");

	public static By objAllTab = By.xpath("(//*[@id='title'and @text='All'])");

	public static By objEpsiodesTab = By.xpath("(//*[@id='title'and @text='Episodes'])");

	public static By objShowsTabIndx = By.xpath("(//*[@id='title'and @text='Shows'])[2]");
	public static By objMoviesTabIndx = By.xpath("(//*[@id='title'and @text='Movies'])[2]");
	public static By objMusicTabIndx = By.xpath("(//*[@id='title'and @text='Music'])");
	public static By objNewsTabIndx = By.xpath("(//*[@id='title'and @text='News'])[2]");

	public static By objVideosTab = By.xpath("(//*[@id='title'and @text='Videos'])");

	public static By objSearchResultPage = By.xpath("//*[@id='searchResultsContent']");

	public static By objMicrophoneIcon = By.xpath("//*[@id='voice_icon']");

	public static By objBackBtn = By.xpath("//*[@id='backIv']");

	public static By objVoiceSearchPermission = By.xpath("//*[@id='mainLabelTv']");

	public static By objMicrophoneIconLogo = By.xpath("//*[@id='micLogoIv']");

	public static By objTermsAndConditions = By.xpath("//*[@id='termsAndConditionsTv']");

	public static By objProceedBtn = By.xpath("//*[@id='proceedBtn']");

	public static By objAllow = By.xpath("//*[@id='permission_allow_button']");

	public static By objDeny = By.xpath("//*[@id='permission_deny_button']");

	public static By objAudioPermissionPopUp = By.xpath("//*[@id='permission_message']");

	public static By objCloseBtn = By.xpath("//*[@id='closeButton']");

	public static By objSeeUrTextMsg = By.xpath("//*[@id='resultsOfRec']");

	public static By objMicrophoneLogoInVoiceSearch = By.xpath("//*[@id='resultsOfRec']");

	public static By objRecentSearch = By.xpath("//*[@text='Recent Searches']");

	public static By objClearAll = By.xpath("//*[@id='search_cancel_text']");

	public static By objVoiceSearchScreen = By.xpath("//*[@id='speechListeningScreen']");

	public static By objVirtualKeypadLetter = By.xpath("//*[@id='key_pos_1_4']");

	public static By objTabs = By
			.xpath("//*[@id='searchResultsContent']//following-sibling::*[@id='tabLayout']//following-sibling::*");

	public static By objGetPremiumBelowThePlayer = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/get_premium_button']");

	// Search icon
	public static By objSearchIcon2 = By
			.xpath("//*[@class='android.support.v7.widget.LinearLayoutCompat']//*[@class='android.widget.ImageView']");

	// Trending searches tray
	public static By objTrendingSearches = By.xpath(
			"//*[@text='Recent Searches']/parent::*/parent::*/following-sibling::*[@class='android.widget.RelativeLayout']/child::*/child::*[@text='Trending Searches']");

	// title on Content card of Trending searches tray
	public static By objContentCardTitleOfTrendingSearchesTray = By.xpath(
			"//*[@text='Trending Searches']/parent::*/parent::*/following-sibling::*/child::*/child::*/child::*/child::*/following-sibling::*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	public static By objConsumptionScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	public static By objTopSearches = By.xpath(
			"//*[@text='Trending Searches']/parent::*/parent::*/following-sibling::*/child::*/child::*[@text='Top Searches']");

	public static By objContentCardTitleOfTopSearchesTray = By.xpath(
			"//*[@text='Top Searches']/parent::*/parent::*/following-sibling::*/child::*/child::*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	// public static By objSearchBox =
	// By.xpath("//*[@resource-id='com.graymatrix.did:id/searchBar']");
	public static By objSearchBar = By.xpath("//*[@id='searchBar' and //*[@class='android.widget.EditText']]");

	// public static By objSearchResultPage =
	// By.xpath("//*[@id='searchResultsContent']");

	public static By objSearchResultFirstContent = By
			.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[4]");

	public static By objNoOftraysInSearchpage = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/header_primary_text']");

	public static By objSelectFirstEpisodeResult = By.xpath("//*[@id='item_secondary_text'][1]");

	public static By objSearchResult(String title) {
		return By.xpath("//*[@id='item_primary_text' and contains(text(), \"" + title + "\")]");
	}

	public static void main(String[] args) {
		String title = "f";
		System.out.println("//*[@id='item_primary_text' and contains(text(), " + title + ")]");
	}

	public static By objFirstContentInSearchResult = By.xpath(
			"(//*[@resource-id='com.graymatrix.did:id/tabLayout_container']/following-sibling::*/child::*/child::*/child::*/child::*)[1]/child::*[@resource-id='com.graymatrix.did:id/item_primary_text']");
}
