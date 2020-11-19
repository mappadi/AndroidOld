package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWASearchPage {

// List of locators before performing Search functionality

	// Back button in Search Edit box
	public static By objBackButton = By.xpath("//div[contains(@class,'iconNavi-ic_back')]");

	// Voice Search button in Search Edit box
	public static By objVoiceSearchButton = By.xpath("//div[contains(@class,'searchMic')]");

	// Trending Searches tray
	public static By objTrendingSearchesTray = By.xpath("//div[@class='trayHeader']//h2[text()='Trending Searches']");

	// First asset of Trending Searches tray
	public static By objFirstAssetThumbnailTrendingSearch = By.xpath(
			"//div[@class='trayHeader']//h2[text()='Trending Searches']//parent::*//following-sibling::div[@class='latestEpisodeTrayWrapper']//div[@data-index='0']");

	// First asset name of Trending Searches tray
	public static By objFirstAssetTitleTrendingSearch = By.xpath(
			"//div[@class='trayHeader']//h2[text()='Trending Searches']//parent::*//following-sibling::div[@class='latestEpisodeTrayWrapper']//div[@data-index='0']//h3[@class='cardTitle']");

	// First asset episode number and Date of Trending Searches tray
	public static By objFirstAssetEpDateTrendingSearch = By.xpath(
			"//div[@class='trayHeader']//h2[text()='Trending Searches']//parent::*//following-sibling::div[@class='latestEpisodeTrayWrapper']//div[@data-index='0']//span[@class='cardEpisodeDate']");

	// Google Play button in Search
	public static By objGooglePlayButton = By.xpath("//img[@alt='Google Play']");

	// App Store button in Search
	public static By objAppStoreButton = By.xpath("//img[@alt='App Store']");

	// Facebook button in Search
	public static By objFacebookButton = By.xpath("//a[contains(@class,'facebookIcon')]");

	// Instagram button in Search
	public static By objInstagramButton = By.xpath("//a[contains(@class,'instagramIcon')]");

	// Twitter button in Search
	public static By objFacebookTwitterButton = By.xpath("//a[contains(@class,'twittercon')]");

	// Youtube button in Search
	public static By objYoutubeButton = By.xpath("//a[contains(@class,'youtubeIcon')]");

	// About us link in Search
	public static By objAboutUsLink = By.xpath("//div[@class='footerMenu']//div[text()='About Us']");

	// Help Center link in Search
	public static By objHelpCenterLink = By.xpath("//div[@class='footerMenu']//div[text()='Help Center']");

	// Privacy Policy link in Search
	public static By objPrivacyPolicyLink = By.xpath("//div[@class='footerMenu']//div[text()='Privacy Policy']");

	// Terms of Use link in Search
	public static By objTermsOfUseLink = By.xpath("//div[@class='footerMenu']//div[text()='Terms of Use']");

	// Collapsible Items in Search (Fetch as a list in script)
	public static By objCollapsibleItems = By.xpath("//div[@class='Collapsible']//h3");

	// List of items under the collapsible items
	public static By objListItemsOfCollapsibleItem(String objCollapsibleItems) {
		return By.xpath("//div[@class='menuGrid']//h3[text()=\"" + objCollapsibleItems
				+ "\"]//following-sibling::div//li//div");
	}

	public static By objSearchMoviesTab = By.xpath("//div[contains(@class,'tabMenuItem') and @id='movies']");

	// Close icon in get premium dialog
	public static By objSubscribeDialog = By.xpath("//*[contains(@class,'popupTitle') and .='Subscribe']");

	// Movies By Language
	public static By objMoviesByLang = By.xpath("//h3[text()='Movies By Language']");

	// Movies By Language dropdown open
	public static By objMoviesByLangDropDownOpen = By
			.xpath("//h3[text()='Movies By Language' and contains(@class,'is-closed')]");

	// Movies By Language dropdown closed
	public static By objMoviesByLangDropDownClose = By
			.xpath("//h3[text()='Movies By Language' and contains(@class,'is-open')]");

	// First asset under Movies By Language
	public static By objFirstAssetMoviesByLang = By
			.xpath("(//h3[text()='Movies By Language']//following-sibling::*//div[contains(@class,'menuItem')])[1]");

	// ZEE5 Channels
	public static By objZee5Channels = By.xpath("//h3[text()='ZEE5 Channels']");

	// ZEE5 Channels dropdown open
	public static By objZee5ChannelsDropDownOpen = By
			.xpath("//h3[text()='ZEE5 Channels' and contains(@class,'is-closed')]");

	// ZEE5 Channels dropdown closed
	public static By objZee5ChannelsDropDownClose = By
			.xpath("//h3[text()='ZEE5 Channels' and contains(@class,'is-open')]");

	// First asset under Movies By Language
	public static By objFirstAssetZee5Channels = By
			.xpath("(//h3[text()='ZEE5 Channels']//following-sibling::*//div[contains(@class,'menuItem')])[1]");

	// Stories
	public static By objStories = By.xpath("//h3[text()='Stories']");

	// ZEE5 Channels dropdown open
	public static By objStoriesDropDownOpen = By.xpath("//h3[text()='Stories' and contains(@class,'is-closed')]");

	// ZEE5 Channels dropdown closed
	public static By objStoriesDropDownClose = By.xpath("//h3[text()='Stories' and contains(@class,'is-open')]");

	// First asset under Movies By Language
	public static By objFirstAssetStories = By
			.xpath("(//h3[text()='Stories']//following-sibling::*//div[contains(@class,'menuItem')])[1]");

	// Copyright Text
	public static By objCopyRightText = By.xpath("//div[@class='copyRightTxt']");

// List of locators after performing Search functionality	

	// Close button in Search Edit box
	public static By objSearchCloseButton = By.xpath("//div[contains(@class,'closeIcon')]");

	// Search Navigation tabs
	public static By objSearchNavigationTabs = By
			.xpath("//div[@class='searchNav']//div[contains(@class,'tabMenuItem')]");

	// For Search Navigation tab click
	public static By objSearchNavigationTab(String tabName) {
		return By
				.xpath("//div[@class='searchNav']//div[contains(@class,'tabMenuItem') and text()=\"" + tabName + "\"]");
	}

	// For Search Navigation tab, first asset click
	public static By objFirstAssetImgSearchNavigationTab = By.xpath("(//div[@class='listingGrid']//img)[1]");

	// First Assets title under any Search Navigation tab
	public static By objFirstAssetTitleSearchNavigationTab = By.xpath(
			"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])[1]");

	// Close icon in register dialog
	public static By objCloseRegisterDialog = By.xpath("//div[@class='manCloseIcon' or contains(@class,'closePupup')]");

	// Close icon in get premium dialog
	public static By objClosePremiumDialog = By.xpath("//div[@class='manCloseIcon' or contains(@class,'closePupup')]");

	// SearchResultPremium
	public static By objSearchResultPremiumContent2 = By.xpath("//div[@class='cardPremiumContent']/parent::*");

	// List of locators before performing Search functionality

	// Recent Searches overlay
	public static By objRecentSearchesOverlay = By.xpath("//div[@class='recentSearchWrapper']");

	// List of locators after performing Search functionality

	// Asset title under ang Search navigation tab
	public static By objAssetTitleSearchNavigationTab = By.xpath(
			"(//div[@class='listingGrid']//div[@class='metaData']//h3[contains(@class,'cardTitle')]//span[@class='highLight'])[1]");

	// Search result based on Search key
	public static By objSearchedResult(String keyword) {
		return By.xpath("((//div[@class='listingGrid']//a[contains(text(),\"" + keyword
				+ "\")])[1] | (//div[@class='listingGrid']//span[contains(text(),\"" + keyword + "\")])[1])[1]");
	}

	public static By objFirstContentAfterSearch = By.xpath("//*[@text='Bhinna']");
	public static By watchTrailerBtn = By.xpath("//*[@text='Watch Trailer']");
	public static By Obj_Pwa_Get_Premium_btn = By.xpath("//*[@text='GET PREMIUM']");

	// First content card name
	public static By objFirstContentCardNameAfterSearch = By
			.xpath("//h3[contains(@class,'cardTitle')]//parent::div//preceding-sibling::figure");

	public static By objFirstContentCardNameAfterSearch(int index) {
		return By.xpath("(//h3[contains(@class,'cardTitle')])[" + index + "]");
	}

	public static By watchTrailer = By.xpath("//div[@class='bindDiv']");

	// First searched asset Title
	public static By objFirstSearchedAssetTitle = By.xpath(
			"//div[contains(@class,'searchCategoryLanding')]//h3[contains(@class,'cardTitle')]//span[contains(@class,'highLight')]");

	// Buy Subscription Option
	public static By objBuySubscriptionOption = By.xpath(
			"//div[contains(text(),'Buy Subscription') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'menuTitle noSelect menuForPlans ')]]");

	public static By objClearAllTextofRecentSearches = By.xpath("//div[text()='Clear All']");

	public static By objEpisodeTitleInconsumptionPage(String title) {
		return By.xpath("//h1[contains(text(),'" + title + "')] | //a[text()='" + title + "']");
	}

	public static By objWEBWatchTrailerBtn = By.xpath("//p[contains(text(),'Watch Trailer')]");

	public static By objFirstContentCardNameAfterSearch1(int index) {
		return By.xpath("(//div[@class='metaData'])[" + index + "]");
	}

	public static By objEmptyStateScreenErrormsg = By.xpath("//div[@class='errorPageContainer']");

//	====================================================================================================================
//	SANITY SUSHMA LIVETV MODULE
	public static By objShowTitleInconsumptionPage(String title) {

		return By.xpath("//h2[contains(text(),'" + title + "')]");
	}

	//	MandatoryRegistration
	public static By objRegisterDialogAfterchangedLanguage = By.xpath("//div[.='ಸೈನ್ ಅಪ್ ಮಾಡಿ']");

	public static By objUpgradePopup = By
			.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open popupModal']");

	public static By objUpgradePopupCloseButton = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

//	BASAVARAJ VIL MODULE
	public static By objSpecificSearch(String str) {
		return By.xpath("((//div[@class='searchCategoryLanding']//img)[@title='" + str + "'])[1]");
	}

	public static By objSpecificSearch1(String str) {
		return By.xpath("//h3[@class='cardTitle overflowEllipsis ']//span[contains(text(),'" + str + "')]");
	}

	public static By objspecificSearch = By.xpath("(//div[@class='metaData'])[1]");

	public static By objSearchTextBox = By.xpath("//input[@name='search']");

	public static By objfirstdata = By.xpath("(//div[@class='listingGrid']//h3)[1]");

	public static By objSearchNewsTab = By.xpath("(//div[@id='news'])");

	public static By objSearchBtn = By.xpath("//*[contains(@class,'noSelect searchBtn iconInitialLoad-ic_search')]");

	public static By objSearchEditBox = By.xpath("//*[@id='searchInput']");

	public static By objSearchResultPremiumContent = By.xpath("//*[@class='cardPremiumContent']/parent::*");

	public static By objShowTitle(String title) {
		return By.xpath("//h1[text()='" + title + "']");
	}

	// Show title in Consumption Page
	public static By objShowTitleInConsumptionPage = By.xpath("(//div[@class='metaInfo']/child::a)[1]");

	public static By objallow = By.xpath("//*[@text='Allow']");

	public static By objSearchBtnWEB = By.xpath("//a[@class='noSelect searchBtn iconInitialLoad-ic_search']");

	public static By objPremiumSearchResult = By.xpath("//*[@title='RX Soori']");

	public static By objPremiumSearchResult(String keyword) {
		return By.xpath("//*[@title=\"" + keyword + "\"]");
	}

	public static By objActivePrograme = By.xpath("(//*[@class='programInner active'])[1]//*[@class='title']");

	public static By objSubscribepopup = By.xpath("//div[@class='popupContent upgradePopupContent']");
	public static By objSubscribepopupCLoseButton = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objSearchResultScreen = By.xpath("//*[@class='searchCategoryLanding']");

	public static By objMandatoryPopup = By.xpath("//div[@class='poupWrapper']");

	public static By objMandatoryPopupCloseButton = By.xpath("//div[@class='manCloseIcon']");

	public static By recentSearchItem1 = By
			.xpath("(//div[@class='recentSearchWrapper']//div[contains(@class,'searchitem')])[1]");

	public static By recentSearchItem(int i) {
		return By.xpath("(//div[@class='recentSearchWrapper']//div[contains(@class,'searchitem')])[" + i + "]");
	}

	public static By recentSearchItem(String keyword) {
		return By.xpath(
				"//div[@class='recentSearchWrapper']//div[contains(@class,'searchitem') and .='" + keyword + "']");
	}

	public static By recentSearchsLabel = By.xpath("//div[@class='recentSearchLbl' and .='Recent Searches']");

	public static By recentSearchItems = By
			.xpath("//div[@class='recentSearchWrapper']//div[contains(@class,'searchitem')]");

	public static By objSearchShowsTab = By.xpath("//div[contains(@class,'tabMenuItem') and @id='tvshows']");

	public static By objSearchPlaceHolderText = By.xpath("//div[@class='searchInputBox']//input");

	public static By objSearchPageFooter = By.xpath("//div[@class='footerMenu']");

	public static By recentSearchItem(String keyword, int i) {
		return By.xpath("//div[@class='recentSearchWrapper']//li[" + i + "]//div[contains(@class,'searchitem') and .='"
				+ keyword + "']");
	}

	public static By emptyStateScreenText = By.xpath("//div[@class='textArea']");

	public static By objSearchEpisodesTab = By.xpath("//div[contains(@class,'tabMenuItem') and @id='episodes']");
	public static By objSearchVideosTab = By.xpath("//div[contains(@class,'tabMenuItem') and @id='videos']");

	public static By objallowCaps = By.xpath("//*[@text='ALLOW']");
	
	public static By objSearchedResultChangedLanguage(String keyword) {
		return By.xpath("((//div[@class='listingGrid']//a[contains(text(),'')])[1] | (//div[@class='listingGrid']//span[contains(text(),\"" + keyword + "\")])[1])[1]");
	}
	
	public static By objShowTitleInconsumptionPage=By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");
	
	public static By objSpecificSearch2(String str) {
		return By.xpath("((//div[@class='searchCategoryLanding']//img)[@title='" + str + "'])[2]"); //added
	}
}
