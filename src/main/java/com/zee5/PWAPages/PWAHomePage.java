package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAHomePage {

	// Zee logo
	public static By objZeeLogo = By.xpath("//*[contains(@class,'zeeLogo')]//img[@title='ZEE5 Logo']");

	// Subscribe Btn
	public static By objSubscribeBtn = By.xpath("//*[@class='subscribeBtn noSelect']");

	// Search Btn
	public static By objSearchBtn = By.xpath("//*[contains(@class,'searchBtn')]");

	// Hamburger menu
	public static By objHamburgerMenu = By.xpath("//*[text()='Open Menu']");

	// Select tab by text
	public static By objTabName(String tabName) {
		return By.xpath("//*[@class='navMenuWrapper ']//*[text()='" + tabName + "']");
	}

	public static By objLiveTVtab = By.xpath("//a[contains(text(),'Live TV')]");

	// h3[@class="cardTitle cardTitleMultiline"]

	// Select tray by text
	public static By objLanguageTextEnglishGetvalue(String trayName) {
		return By.xpath("//*[contains(text(),'" + trayName + "')]");
	}

	public static By objOverlayTrayActive(String text) {
		return By.xpath(" //span[@class='noSelect active' and contains(text(),'" + text + "')]");
	}

	public static By objTabContBar = By.xpath("//div[@class='navMenuWrapper ']");

	public static By objDownloadIcon = By
			.xpath("//div[contains(@class,'noSelect addHomeScreen iconInitialLoad-ic_add_home')]");

	// Device Pin
	public static By objDevicePin1 = By.xpath("//input[@id='parentLockId1']");
	public static By objDevicePin2 = By.xpath("//input[@id='parentLockId2']");
	public static By objDevicePin3 = By.xpath("//input[@id='parentLockId3']");
	public static By objDevicePin4 = By.xpath("//input[@id='parentLockId4']");

//	Manas Nath

	// Subscription page
	public static By objSubscriptionPage = By.xpath("//div[@class =\"subscriptionAndPaymentWrapper\"]");

	public static By objContTitleTextCarousel(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle') and contains(text(),\""
						+ text + "\")]");
	}

	public static By objContTitleWithPlayIconCarousel(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle') and contains(text(),\""
						+ text + "\")]//parent::*//following-sibling::div//div[@class='playIcon']");
	}

	// Play Btn On carousel
	public static By objPlayBtn = By
			.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'playIcon')]");

	// Carousel Banner
	public static By objCarouselBanner = By.xpath("//div[@class=\"carouselMain\"]");

	// Profile Menu
	public static By objProfileMenu = By.xpath("//div[contains(@class,'bm-icon profileMenuBtn')]");

	public static By objSearchField = By.xpath("//input[@type='text']");

	// Subscribe Btn in Top Header
	public static By objSubscribeBtnTopHeader = By
			.xpath("//*[contains(@class,'subscribeBtn noSelect')]//span[contains(text(), 'Subscribe')]");

	// Continue in Display/Content language popup
	public static By objContinueDisplayContentLangPopup = By
			.xpath("//div[contains(@class,'popupBtn noSelect') and text()='Continue']");

	public static By objLanguageBtn = By.xpath("//div[@id='languageBtn']");

	public static By objGetPremiumWeb = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'noSelect premiumBtn')][contains(text(),'Get premium')]");

	// Masthead carousel current content
	public static By objMastheadCarouselCurrentContent = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'content')]//img");
	// Display Language popup Title
	public static By objDisplayLanguagePopupTitle = By.xpath(
			"//div[contains(@class, 'popupContent langFilterPopupWrapper')]//div[contains(text(), 'Display Language')]");

	// Display Language Popup English Option
	public static By objDisplayLanguagePopupOption(String languageOption) {
		return By.xpath("//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(text(), '"
				+ languageOption + "')][2]");
	}

	// Display Language Popup Continue Button
	public static By objDisplayLanguageContinueButton = By
			.xpath("//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(text(), 'Continue')]");
	public static By objWEBWatchTrailerBtn = By.xpath("//p[contains(text(),'Watch Trailer')]");
	public static By objWEBCarouselTitle = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'legendTitle ')]");
	public static By objWEBPlayBtn = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'playIcon')]");

	// Display Language Popup English Option
	public static By objContentLanguagePopupSelectedOption(String languageOption) {
		return By.xpath(
				"//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(@class, 'checkboxWrap checkedHighlight')]//*[contains(text(), '"
						+ languageOption + "') and @class='commonName']");
	}

	// Display Language Popup English Option
	public static By objContentLanguagePopupUnSelectedOption(String languageOption) {
		return By.xpath("//div[contains(@class, 'popupContent langFilterPopupWrapper')]//*[contains(text(), '"
				+ languageOption + "') and @class='commonName']");
	}

	public static By objContTitleTextCarouselWeb(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'legendTitle') and contains(text(),'"
						+ text + "')]");
	}

	public static By objHomeBarText(String HomeBartitle) {
		return By.xpath("//a[contains(@class,'noSelect')][contains(text(),'" + HomeBartitle + "')]");
	}

	public static By objMoreMenuTabs(String tabName) {
		return By.xpath(
				"//*[@class='moreMenuBtn iconInitialLoad-ic_Bento']/following-sibling::*/child::*/child::*[text()='"
						+ tabName + "']");
	}

	public static By objMoreMenuIcon = By.xpath("//*[@class='moreMenuBtn iconInitialLoad-ic_Bento']");

	public static By objFirstAssetTrendingOnZEE5 = By.xpath(
			"//h2[text()='Trending on ZEE5']//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']");

//		====================================== SANITY ===================================

//		MandatoryRegistration
	public static By objSearchResult = By.xpath("//*[@title='Ramayana']");
	public static By objPopUpMobileField = By.xpath("//input[@id='textField']");
	public static By objPopUpPasswordField = By.xpath("//input[@name='inputPassword']");
	public static By objPopUpProceedButton = By.xpath("//button[@class='noSelect buttonGradient ']");

//	public static By objPopUpProceedButton = By.xpath("//div[@class='registerLoginContainer']//button[@class='noSelect buttonGradient null']");
	public static By objOtpPopUp = By.xpath("//div[@class='poupWrapper']");

//		BHAVANA SHOWS MODULE

	public static By objAllowNotification = By
			.xpath("//*[@text=concat('Click on the lock in the URL bar, go to') and @nodeName='DIV']");

	public static By objcloseonAllowNotification = By
			.xpath("//*[@nodeName='BUTTON' and [@text=concat('Click on the lock in the URL bar, go to ']]");
	public static By objGooglePlay = By.xpath("//a[@class='gb_ue gb_vc gb_se']");
	public static By objiOS = By.xpath("//a[@id='ac-gn-firstfocus-small']");

	// --------------------------------------------------
	public static By objTumbnailTitle(String str, int i) {
		return By.xpath("((((//div[.='" + str
				+ "']//parent::*//parent::*//parent::*)//child::*[@class='movieTrayWrapper'])//child::*[1][@class='noSelect clickWrapper'])["
				+ i + "])//child::*[@class='content']//child::*");
	}

	public static By objIsPremiumTumbnail(String str, int i) {
		return By.xpath("((((//div[.='" + str
				+ "']//parent::*//parent::*//parent::*)//child::*[@class='movieTrayWrapper'])//child::*[1][@class='noSelect clickWrapper'])["
				+ i + "])//child::*[@class='cardPremiumContent']");
	}

	public static By objPlaybackMovieTitle(String str) {
		return By.xpath("//div[@class='consumptionMetaDiv']//h1[.='" + str + "']");
	}

	// --------------------------------------------------
	public static By objLIVETvTumbnailTitle(String str, int i) {
		return By.xpath("((((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
				+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper'])["
				+ i + "])//figure//a[@class='noSelect content']//child::*)[1]");
//		return By.xpath("((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='"+str+"']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//h3[@class='cardTitle']//span[@class='noSelect '])["+i+"]");
	}

	public static By objLIVETVIsPremiumTumbnail(String str, int i) {
		return By.xpath("((((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='" + str
				+ "']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper'])//figure//a[@class='noSelect content'])//div[@class='cardPremiumContent'])["
				+ i + "]");
	}

	public static By objPlaybackLIVETVTitle(String str) {
		return By.xpath("//div[@class='channelConsumptionMetaDiv']//h2[.='" + str + "']");
	}

	public static By objPlaybackLIVETVTitle1 = By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");

	// -------------------------------------------------
	public static By objShowTumbnailTitle(String str, int i) {
		return By.xpath("(((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='latestEpisodeTrayWrapper']//child::*//div[@class='noSelect clickWrapper'])["
				+ i + "]//figure//div)//img");
	}

	public static By objShowIsPremiumTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='latestEpisodeTrayWrapper']//child::*//div[@class='noSelect clickWrapper'])["
				+ i + "]//div[@class='cardPremiumContent']");
	}

	public static By objPlaybackShowTitle(String str) {
		return By.xpath("//div[@class='consumptionMetaDiv']//h1[.='" + str + "']");
	}

	// -------------------------------------------------
	public static By objVideoTumbnailTitle(String str, int i) {
		return By.xpath("(((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//*[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "])//figure//img");
	}

	public static By objVideoIsPremiumTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//*[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "]//div[@class='cardPremiumContent']");
	}

	public static By toTray(String str) {
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + str + "')]");
	}

	// ------------------------------------------------------
	public static By objspecificTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "]");
	}

	public static By objspecificTumbnail1(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='continueWatchCard card positionRelative marginRight zoomCardHover minutelyUrl']//a)["
				+ i + "]");
	}

	public static By objspecificTumbnail2(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='"+str+"'])//parent::*//parent::*//div[@class='slick-slider continueWatchTray slick-initialized']//div[@class='slick-track']//div//div//a)["+i+"]");
	}
	
	public static By objtumnails(String str) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])//figure");
	}

	// ------------------------------------------------------
	public static By objKalGetPremium = By.xpath("//a[@class='premiumBtn']");
	public static By objKalGetTitle = By.xpath("//h1[@class='title']");
	public static By objKalGetPremiumPlayicon = By
			.xpath("//div[@class='iconsWrap getPremiumBtn']//child::*[@class='playIcon']");
	public static By objKalKalturaPlayer = By.xpath("//div[@class='kaltura-player-container']");
	public static By objKalconsumptionMetaDiv = By.xpath("//div[@class='consumptionMetaDiv']//h1");
	public static By objKalconsumptionMetainfo = By.xpath("(//div[@class='metaInfo']//p)[1]");
	public static By objKalGetFirstEpisode = By
			.xpath("//div[@class='showDetailIcon']//p[contains(text(),'Watch First Episode')]");
	public static By objKalGetFirstEpisodePlayicon = By
			.xpath("//div[@class='showDetailIcon']//child::*[@class='playIcon']");
	public static By objKalLivetvPlaying = By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");
	public static By objKalLivetvChannel = By.xpath("//div[@class='channelConsumptionMetaDiv']//h1");

//		CONTENT DETAILS MODULE
	public static By objSeletedTab = By.xpath("//div[@class='navMenuWrapper ']//a[@class='noSelect active']");

	public static By objZeelogo1 = By.xpath("//a[@class='zeeLogo noSelect']//child::*");

//		TANISHA
	// Minutely
	public static By objMinutelyContentNonPremium = By.xpath(
			"//div[contains(@class,'latestEpisodeTray')]//*[contains(@class,'clickWrapper') and not(.//div[@class='cardPremiumContent'])]");

	public static By objContentNonPremium = By
			.xpath("//div[contains(@class,'clickWrapper') and not(.//div[@class='cardPremiumContent'])]");

	public static By objAllTabs = By
			.xpath("//div[contains(@class,'navMenuWrapper')]//div[contains(@class,'noSelect')]");

	public static By objPWANews = By.xpath("//div[contains(text(),'News')]");

//		SUSHMA SANITY LIVETV MODULE
	public static By objBackToTopArrow = By.xpath("//div[@class='iconNavi-ic_arrow_back']");

	// Footer section of home page

	// Download Apps text UI
	public static By objDownloadApps = By.xpath("//h3[contains(text(),'Download Apps')]");

	// public static By objTwitterIcon =
	// By.xpath("//span[contains(text(),'twitter')]");

	// Up Arrow
	public static By objUpArrow = By.xpath("//div[@class='iconNavi-ic_arrow_back']");

	// Copy right text
	public static By objCopyRightText = By.xpath("//div[@class='copyRightTxt']");

	// Facebook page
	public static By objFacebookPage = By.xpath("(//*[@text='Log In'])[2]");

	// Instagram page
	public static By objInstagramPage = By.xpath("//button[contains(text(),'Follow')]");

	// Twitter page

	public static By objTwitterPage = By.xpath("//*[@resource-id='com.twitter.android:id/button_bar_follow']");

	// Help Center in Footer section
	public static By objHelp = By.xpath("//a[@class='noSelect'][contains(text(),'Help Center')]");
	// Help Center screen
	public static By objHelpScreen = By.xpath("//h2[contains(text(),'Help Center')]");

	// write to us
	public static By objwritetous = By.xpath("//img[contains(@class,'img1')]");

	// contact us page
	public static By objcontactus = By.xpath("//*[@text='Contact Us']");

	// About Us in Footer Section
	public static By objAboutUsInFooterSection = By.xpath("//a[@class='noSelect'][contains(text(),'About Us')]");
	// AboutUsScreen
	public static By objAboutUs = By.xpath("//h1[contains(text(),'About Us')]");

	// Privacy Policy in Footer Section
	public static By objPrivacyPolicyInFooterSection = By
			.xpath("//a[@class='noSelect'][contains(text(),'Privacy Policy')]");
	// PrivacyPolicyScreen
	public static By objPrivacyPolicy = By.xpath("//h1[contains(text(),'Privacy Policy')]");
	// TermsOfUse in Footer Section
	public static By objTermsOfUseInfooterSection = By.xpath("//a[@class='noSelect'][contains(text(),'Terms of Use')]");
	// TermsOfUSeScreen
	public static By objTerms = By.xpath("//h1[contains(text(),'Terms of Use')]");

	// Home page continue watching tray
	public static By objContinueWatchingTray = By.xpath("//h2[contains(text(),'Continue Watching')]");

	public static By objActiveTab = By.xpath("//a[contains(@class,'noSelect active')]");

	public static By objAndroidPlayStoreIcon = By.xpath("//*[@class='playstoreIcon']//a[@class='noSelect content']");

	public static By objIoSAppStoreIcon = By.xpath("//*[@class='appStoreIcon']//a[@class='noSelect content']");

	public static By objFacebookIcon = By.xpath("//*[@class='noSelect facebookIcon iconSocial-ic_social_facebook']");

	public static By objInstagramIcon = By.xpath("//*[@class='noSelect instagramIcon iconSocial-ic_social_instagram']");

	public static By objTwitterIcon = By.xpath("//*[@class='noSelect twittercon iconSocial-ic_social_twitter']");

	public static By objHighlightedTab(String tabname) {
		return By.xpath("//*[@class=\"noSelect active\"]");
	}

	public static By objSearchResultWeb = By.xpath("//div[@class='searchListingNewsWrap']//img[@title='Ramayana']");

	// Subscribe PopUp Home Page
	public static By objSubscripePopupHomePage = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");
	// Subscribe PopUp close button Home Page
	public static By objSubscripePopupCloseButtonHomePage = By.xpath(
			"//div[(contains(@style,'display: block'))]//div[@class='adoric_element element-text editing closeLightboxButton']//child::*[@class='inner-element']");

	public static By objWhatWonderingPopUp = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");

	public static By objWhatWonderingPopUpCloseIcon = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[@aria-label='close']");

	public static By objPlayiconAfterMouseHover = By
			.xpath(".//*[@class='noSelect btnIcon playBtnIcon']//child::*[@class='btnText']");

	public static By objShareiconAfterMouseHover = By
			.xpath(".//*[@class='shareCompIcon iconInitialLoad-ic_share']//child::*[@class='shareLabel']");

	public static By objWEBSubscribeBtn = By.xpath("//a[@class='subscribeBtn noSelect']");

	public static By objMoreMenuBtn = By.xpath("//div[@class='moreMenu']");

	public static By objWhatToWatchPopUp = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");

	public static By objWhatToWatchCloseButton = By
			.xpath("//div[(contains(@style,'display: block'))]//child::div[@aria-label='close']");

	public static By objLanguage = By.xpath("//div[@id='languageBtn']");
	public static By objKannadaWEB = By.xpath("//span[contains(@class,'commonName')][contains(text(),'Kannada')]");
	public static By objEnglishWEB = By.xpath("//span[contains(text(),'English')]");

	public static By objGooglePlayLogo = By.xpath("//a[@title='Google Play Logo']");

	public static By objCarousel = By.xpath("//*[contains(@class,'heroBannerCarousel minutelyUrl')]");

	public static By objPageHighlighted(String PageName) {
		return By.xpath("//*[@class='noSelect active' and text()='" + PageName + "']");
	}

	public static By objLanguageChangeContentPopup() {
		return By.xpath("//div[@class='popupContent languageChangeContentPopup']");
	}

	public static By objLanguageChangeContentPopupCloseicon() {
		return By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	}

	public static By objLanguagePop = By.xpath("//div[@class='popupContent langFilterPopupWrapper']");

	// Subscribe Now
	public static By objSubscribeNow = By.xpath(
			"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'noSelect premiumBtn')][contains(text(),'Subscribe Now')]");

	public static By objTwitterFollowWeb = By.xpath("//*[text()='Follow']");

	public static By objAddToWatchlistButtonOnTrayContentCard(String trayName) {
		return By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'" + trayName
				+ "')]//parent::*//following-sibling::*//*[contains(@class, 'noSelect btnIcon iconInitialLoad-ic_add_Watchlist')])[1]");
	}

	public static By objLoginRequiredPopUpHeader = By.xpath("//*[contains(text(), 'Login Required')]");

	public static By objPopupCloseicon() {
		return By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	}

	public static By objFirstContentCardOfTray(String trayName) {
		return By.xpath("(//*[contains(@class,'trayHeader')]//*[contains(text(),'" + trayName
				+ "')]//parent::*//parent::*//following-sibling::*//*[contains(@class, 'content')])[1]");
	}

	public static By objsearchcontent = By.xpath("//*[@title='Krishna Balram: The Warrier Princess']");

	public static By objPlayCarousel = By.xpath(
			"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'noSelect premiumBtn')][contains(text(),'Play')]");

	public static By objHoverMenu(String text) {
		return By.xpath("//a[contains(@class,'noSelect')][contains(text(),'" + text
				+ "')]//following-sibling::*[@class='megaMenu megaMenuCards cardTypetvshows']");
	}

	public static By objOverlayTray = By.xpath("//span[contains(text(),'Top Zee')]");

	public static By objOverlayTray2 = By.xpath("(//span[@class='noSelect ' and contains(text(),'Latest')])[1]");

	public static By objHomeInHambugerMenu = By
			.xpath("//*[contains(@class,'menuGroup')]//a[contains(@class,'active')]//div[contains(text(),'Home')]");

	// Content Title on carousel
	public static By objContTitleOnCarousel = By
			.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle')]");

	// Get premium btn
	public static By objGetPremium = By
			.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'noSelect premiumBtn')]");
	public static By objWEBGetPremium = By
			.xpath("//div[contains(@class,'slick-active')]//a[contains(@class,'premiumBtn')]");

	public static By objWEBShowsPagePlayCarousel = By.xpath(
			"//div[@class='slick-slide slick-active slick-center slick-current']//*[contains(@class,'carouselMain')]");

	// Android google play store
	public static By objGooglePlayStore = By.xpath("//*[@text='Latest Movies, TV Shows, Originals, News, HiPi']");

	public static By objGetPremiumGetClubButton = By
			.xpath("//div[contains(@class,'slick-active')]//a[contains(@class,'premiumBtn')]");

	public static By obj3xfasterPopUpNoThanks = By.xpath("//*[@text='NO THANKS\\n']");
}
