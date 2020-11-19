package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWALiveTVPage {

	String xPathStr1 = "//div[contains(@class,'noSelect active')]";
	public static By objLiveTVToggleActive = By
			.xpath("//div[@class='toggleGroup']//*[contains(@class,'noSelect active')][contains(text(),'Live TV')]");

	public static By objLiveTVTitle = By.xpath("//h1[contains(text(),'Live TV')]");

	public static By objLivelogo = By.xpath("(//div[@class='liveAndProgressbar'])[1]");

	// Filter Options for LiveTV - All,FREE Channels,English
	// Entertainment,Movie,English News,Music,Lifestyle,Devotional
	public static By objFilterOption(String label) {
		return By.xpath("//span[contains(text(),'" + label + "')]");
	}

	// Filter Options for Entertainment
	public static By objEntertainmentFiltrOptn = By
			.xpath("//div[contains(@class,'chipTray staticChipTray')]//div[4]//span[1]");

	// Filter Options for News
	public static By objNewsFiltrOptn = By.xpath("//div[contains(@class,'chipTray staticChipTray')]//div[6]//span[1]");

	// Filter Options for Kannada
	public static By objKannadaFiltrOptn = By
			.xpath("//div[contains(@class,'noSelect filterOptions')]//span[contains(text(),'Kannada')]");

	// Language filter button
	public static By objLanguageFiltrBtn = By.xpath(
			"//button[contains(@class,'noSelect filterCompButton noSelect activeFilter iconInitialLoad-ic_filter')]");

	// Close button on Language selection PopUp
	public static By objCloseLanguagePopuUpBtn = By
			.xpath("//div[contains(@class,'noSelect closePupup iconInitialLoad-ic_close')]");

	// Object to select Language option from PopUp
	public static By objSelectLanguageOption(String lang) {
		return By.xpath("//div[@class='language noSelect'][contains(text(),'" + lang + "')]");

	}

	// Object to Check whether Language is already selected in the PopUp
	public static By objCheckSelectedLanguage(String lang) {
		return By.xpath("//div[@class='selectedLanguage language noSelect'][contains(text(),'" + lang + "')]");
	}

	// No of Language selected description
	public static By objNoOfLangSelectedText = By.xpath("//div[@class='selectedLanguageText']");

	// Reset button on Language PopUp screen
	public static By objResetBtn = By.xpath("//div[@class='popupBtn accentBtn noSelect']");
	// Apply button on Language PopUp screen
	public static By objApplyBtn = By.xpath("//div[@class='popupBtn noSelect']");

	// Tray Title
	public static By objTrayTitleLabel(String title) {
		return By.xpath("//h2[text()='" + title + "']");
	}

	// Channel Names above the card
	public static By objCardAboveChannelName(String channelName) {
		return By.xpath("//div[@class='cardAboveMeta' and text()='" + channelName + "' and @xpath=1]");
	}

	public static By objCardTitleName(String title) {
		return By.xpath("//*[@class='cardTitle']//following::span[text()='" + title + "'][1]");
	}

	// Elapsed Meta Time data for the title
	public static By objMetaTime(String title) {
		return By.xpath("//*[contains(text(),'" + title + "')]//following::*[starts-with(text(),'Elapsed')][1]");
	}

	// Player Play button
	public static By objPlayBtn = By.xpath("//i[contains(@class,'playkit-icon playkit-icon-play')]");

	// Player Pause button
	public static By objPauseBtn = By.xpath("//i[contains(@class,'playkit-icon playkit-icon-pause')]");

	public static By objPlayPauseBtn = By.xpath("//div[contains(@class,'PlayPause-control')]");

	// Access Settings on the Consumption Screen
	public static By objPlayerSettingIcon = By.xpath("//i[contains(@class,'playkit-icon playkit-icon-settings')]");

	// Access Maximize icon from the Consumption Screen
	public static By objMaximizeBtn = By.xpath("//i[contains(@class,'playkit-icon playkit-icon-maximize')]");

	// Access Minimize icon from the Consumption Screen
	public static By objMinmizeBtn = By.xpath("//i[contains(@class,'playkit-icon playkit-icon-minimize')]");

	// Access Share button under the Consumption Screen
	public static By objShareBtn = By.xpath("//p[contains(@class,'shareLabel')]");

	// Description under the Consumption Screen
	public static By objDescText = By.xpath("//*[@id='description']");

	public static By objHindiFiltrOptn = By.xpath("(//div[contains(@class,'language noSelect')])[1]");

	public static By objChannelDayStrip = By.xpath("(//div[@class=\"channelDay\"])");

	public static By objChannelGuideFilterOption = By
			.xpath("(//div[@class=\"filterComponent  filterComponentFixedBottom\"])");

	public static By objLiveTvFilterOption = By
			.xpath("//*[contains(@class,'filterComponent  filterComponentFixedBottom')]");

	public static By objChannelGuideSortOption = By
			.xpath("(//button[@class='sortComponent iconInitialLoad-ic_sort '])");

	public static By objLiveChannelCardTitle = By.xpath("(//h3[@class=\"cardTitle\"])[1]");

	public static By objUpcomingLiveProgramDate = By.xpath("(//div[@class='date'])[10]");

	public static By objUpcomingLiveProgram = By.xpath("(//div[@class=\"programMeta\"])[1]");

	public static By objUpcomingLiveProgramClose = By.xpath("//div[contains(@class,'closePupup')]");

	public static By objUpcomingLiveProgramShareBtn = By.xpath("(//div[@class=\"shareContainer noSelect\"])");

	public static By objSortByPopularity = By.xpath("(//div[@class='sortleft'])[1]");

	public static By objFacebookShare = By.xpath("//*[@text='Post' or @text='Facebook' or @text='News Feed']");

	public static By objSortByAZ = By.xpath("(//div[@class='sortleft'])[2]");

	public static By objPostToFB = By.xpath("//*[@class='android.widget.Button' and @text='POST']");

	public static By objUpcomingLiveProgramCloseBtn = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objHomeBarText(String HomeBartitle) {
		return By.xpath("//div[contains(@class,'noSelect')][contains(text(),'" + HomeBartitle + "')]");
	}

	public static By objCardTitle = By.xpath("(//h3[@class='cardTitle']/a)[1]");

	public static By objLiveChannelCardProgressBar = By.xpath("(//div[@class='progressBar'])[1]");
	public static By objGoHomeLink = By.xpath("//p[.='GO HOME']");
	public static By objLiveChannelCardTitle1 = By.xpath("(//h3[@class=\"cardTitle\"])[2]");
	public static By objLiveChannelCard1 = By.xpath("(//div[@class='noSelect content'])[2]");
	public static By objFacebookShareBtn = By.xpath("//span[@id='facebookShare']");
	public static By objPostToFacebookBtn = By.xpath("//span[contains(text(),'Post to Facebook')]");
	public static By objFacebookEmailField = By.xpath("//input[@id='email']");
	public static By objFacebookPasswordField = By.xpath("//input[@id='pass']");
	public static By objFacebookLoginBtn = By.xpath("//input[@name='login']");

//	=============================================================================================

//		SUSHMA LIVETV MODULE
	public static By objHighlightedTab(String tabName) {
		return By.xpath(
				"//div[@class='navMenuWrapper ']/child::ul/child::li/child::div[@class='noSelect active' and text()='"
						+ tabName + "']");
	}

	public static By objFirstfreeContentCard = By.xpath("(//a[@class='noSelect content'])[1]");

	public static By objBackToTopArrow = By.xpath("//div[@class='iconNavi-ic_arrow_back']");

	public static By objHighlightedChannelGuideToggle = By
			.xpath("//a[@class='noSelect active' and text()='Channel Guide']");

	public static By objOngoingLiveTvShowTitle = By
			.xpath("(//div[@class='programInner active'])[1]/child::*/child::div[@class='title']");

	public static By objFirstUpcomingShowcard = By
			.xpath("((//div[@class='programInner active'])[1]/parent::*/following-sibling::*)[1]");

	public static By objUpcomingShowContentDialoguebox = By.xpath("//div[@role='dialog']");

	public static By objFirstPremiumCardinTray = By.xpath("//div[@class='cardPremiumContent']//parent::*//img");

	public static By objPremiumPopUp = By.xpath("//h2[contains(@class,'popupTitle') and text()='Get premium']");

	public static By objClosePremiumPopup = By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objPlayerInlineSubscriptionLink = By.xpath("(//div[@class='main-container-1'])[1]");
//		SUSHMA
	public static By objLiveChannelCard = By.xpath("(//*[@class=\"noSelect content\"])[1]");

	public static By objLiveChannelnameaboveCard = By.xpath("(//div[@class='cardAboveMeta'])[1]");

	public static By objTodayDate = By.xpath("//div[text()='Today']");
	public static By objTomorrowDate = By.xpath("(//div[@class='active']//parent::*//following-sibling::*)[1]");
	public static By objShowName = By.xpath(
			"(//*[@title='Suvarna News' or @title='India Today' or @title='Republic TV' or @title='WION' or @title='DD India']//parent::*//parent::*//following-sibling::*//div)[2]");
	public static By objRemainderButton = By.xpath("//div[contains(@class,'reminder')]");
	public static By objPopupCloseButton = By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objPremiumCardtext(String title) {
		return By.xpath("//div[@class='cardPremiumContent']/preceding-sibling::figure/child::*/child::img[@title='"
				+ title + "']");
	}

	public static By objRegisterPopUp = By
			.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open mandatoryRegisterPopup ']");

	public static By objRegisterPopUpCloseIcon = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objVideoIsPremiumTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "]//div[@class='cardPremiumContent']");
	}

	public static By objVideoTumbnailTitle(String str, int i) {
		return By.xpath("(((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "])//figure//img");
	}

//		SUSHMA
	public static By objFilterLanguageChannelGuide = By.xpath(
			"//button[contains(@class,'noSelect filterCompButton noSelect activeFilter iconInitialLoad-ic_filter')]");

	public static By objSelectedlang = By.xpath("//div[contains(@class,'selectedLanguage language noSelect')]");

	public static By objKannadaLang = By
			.xpath("//div[@class='languageContainer']/child::*/child::div[text()='Kannada']");

	public static By objShareOption = By.xpath("//div[@class='shareCompIcon iconInitialLoad-ic_share']");

//	  	==================VINAY=================

	// Upcoming Live TV show
	public static By objFutureChannelInfo = By
			.xpath("(((//*[contains(@class,'programInner active')])[2]//parent::*)[1]//following-sibling::*)[2]");
	// Close button Channel info page
	public static By objCloseBtn = By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	public static By objShowTitle = By.xpath(
			"(((//*[contains(@class,'programInner active')])[1]//parent::*)[1]//following-sibling::*)[2]//div[@class='title']");
	public static By objDescription = By.xpath(
			"(((//*[contains(@class,\"programInner active\")])[1]//parent::*)[1]//following-sibling::*)[2]//div[@class=\"timeInterval\"]");

	public static By objHindiLang = By.xpath("//div[@class='language noSelect'][contains(text(),'Hindi')]");
	public static By objMalayaliLang = By.xpath("//div[@class='language noSelect'][contains(text(),'Malayalam')]");
	public static By objPunjabiLang = By.xpath("//div[@class='language noSelect'][contains(text(),'Punjabi')]");
	public static By objPunjabiShow = By.xpath(
			"(//*[@title=\"Zee Punjabi\"]//parent::*//parent::*//following-sibling::*//following-sibling::*)[1]");

	// public static By objApplyBtn = By.xpath("//div[contains(@class,'popupBtn
	// noSelect')]");
	public static By objGenreBtn = By.xpath("//div[contains(@class,'noSelect genreHeader')]");

	public static By objShowName2 = By.xpath(
			"(//*[@title=\"Zee Keralam\"]//parent::*//parent::*//following-sibling::*//following-sibling::*)[1]");
	public static By objShowDesc = By.xpath("//div[@class='programDescription']");

	// Movie name
	public static By objMovieShow = By.xpath(
			"(//*[@title=\"Zee Cinemalu\" or @title=\"Zee Picchar\" or @title=\"Zee Cinema\" or @title=\"Zee Talkies\"]//parent::*//parent::*//following-sibling::*//div)[2]");

	public static By objLiveTVMenu = By.xpath("//a[contains(text(),'Live TV')]");

	public static By objNothighlightedChannelGuideToggle = By
			.xpath("//*[@class='noSelect ' and text()='Channel Guide']");

	public static By objChannelGuideToggle = By.xpath("//a[contains(text(),'Channel Guide')]");

	public static By objLiveTVToggleInactive = By
			.xpath("//*[contains(@class,'toggleGroup')]//*[contains(@class,'noSelect')][contains(text(),'Live TV')]");

	public static By objFirstOngoingLiveTvShowCard = By.xpath("(//*[@class='programInner active'])[1]");

	public static By objparticularTime = By.xpath("//div[@class='timeSlot']/child::div[text()='8:00 PM']");
	public static By objShowNameweb = By.xpath(
			"(//*[@title='Zee Kannada']//parent::*//parent::*//following-sibling::*//div[@class='noSelect programOuter']/child::*/child::*/child::div[contains(text(),'08:00 PM')])[2]");

	public static By objTwitterEmailField = By.xpath("//input[@name='session[username_or_email]']");
	public static By objTwitterPasswordField = By.xpath("//input[@name='session[password]']");
	public static By objTweetButton = By.xpath("//span[text()='Tweet']");

	public static By objPunjabiShow1 = By
			.xpath("(//*[@title='Zee Punjabi']//parent::*//parent::*//following-sibling::*//div)[2]");
	// Malayalam show
	public static By objMalayalamShow = By
			.xpath("(//*[@title='Zee Keralam']//parent::*//parent::*//following-sibling::*//div)[2]");

	public static By objSelectLang(String text) {
		return By.xpath("//div[@class='languageContainer']/child::*/child::div[text()='" + text + "']");
	}

	public static By objTwitterPasswordField1 = By.xpath("//input[@name='session[password]']");
	public static By objFacebookEmailField1 = By.xpath("//input[@id='email']");
	public static By objChromeOpenWith = By.xpath("//*[@text='Chrome' or contains(@text,'JUST ONCE')]");

	// My remainder option
	public static By objMyRemainder = By.xpath("//*[@class='noSelect menuItem   ' and text()='My Reminders']");

	public static By objBengaliShow1 = By
			.xpath("(//*[@title='Zee Bangla']//parent::*//parent::*//following-sibling::*//div)[2]");

	public static By objLiveLabel(String liveshow) {
		return By.xpath("//img[@title='" + liveshow + "']//parent::a//div//div[@class='liveLogo ' and .='LIVE']");
	}

	public static By objLiveProgressBar(String liveshow) {
		return By.xpath(
				"//img[@title='" + liveshow + "']//parent::a//div//div[@class='progressBar']//div[@class='filler']");
	}

	public static By objBanglaShow1 = By
			.xpath("(//*[@title='Zee Bangla']//parent::*//parent::*//following-sibling::*//div)[2]");

	public static By objLiveChannelConsumptionPageTitle = By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");

	public static By objTwitterOpenWith = By.xpath("//*[@text='Twitter']");

	public static By objTwitterLoginButton = By.xpath("//div[@role=\"button\"]//span[text()='Log in']");

	public static By objOngoingLiveTvShowTitles = By
			.xpath("//div[@class='programInner active']/child::*/child::div[@class='title']");

	public static By objOngoingLiveTvShowTitles(int i) {
		return By.xpath("(//div[@class='programInner active'])[" + i + "]/child::*/child::div[@class='title']");
	}

	public static By objChannelForReminder = By.xpath("//img[@title='Zee TV HD']");
	public static By objShowZEETVHDShow1ForReminder = By.xpath(
			"(//img[@title='Zee TV HD']//ancestor::*[@class='channelPngDiv']//following-sibling::*[@class='epgOuter']//div[@title])[1]");
	public static By objShowZEETVHDShow2ForReminder = By.xpath(
			"(//img[@title='Zee TV HD']//ancestor::*[@class='channelPngDiv']//following-sibling::*[@class='epgOuter']//div[@title])[3]");
	public static By objeMovieForReminder = By.xpath(
			"//img[@title='Zee Cinema']//ancestor::div[@class='channelPngDiv']//following-sibling::div[@class='epgOuter']//div[contains(@class,'programOuter')]");

	public static By objOngoingShow(String activeTime) {
		return By.xpath("//div[@class='timeInterval' and contains(text(),'" + activeTime
				+ " -')]//ancestor::*[contains(@class,'programInner active')]//div[@class='title']");
	}

	public static By objOngoingShowTiming(String activeTime) {
		return By.xpath("//div[@class='timeInterval' and contains(text(),'" + activeTime
				+ " -')]//ancestor::*[contains(@class,'programInner active')]//div[@class='timeInterval']");
	}

	public static By objUpcomingShow(String futureTime) {
		return By.xpath("(//div[@class='timeInterval' and contains(text(),'" + futureTime
				+ " -')]//ancestor::*[contains(@class,'programOuter')]//div[@class='title'])[2]");
	}

	public static By objUpcomingShowTiming(String futureTime) {
		return By.xpath("(//div[@class='timeInterval' and contains(text(),'" + futureTime
				+ " -')]//ancestor::*[contains(@class,'programOuter')]//div[@class='timeInterval'])[2]");
	}

	public static By objActiveTimeSlot = By.xpath("//div[@class='timeSlot']//div[@class='active']");
	public static By objUpcominfTimeSlot = By.xpath(
			"(//div[@class='timeSlot']//div[@class='active']//parent::*//following-sibling::div[@class='timeSlot']//div)[1]");
	public static By objEPG = By.xpath("//div[@class='epgOuter']");
	
	public static By objrelatedChannel(String Channel) {
		return By.xpath("//div[@class='metaData']//span[contains(text(), \"" + Channel + "\")]");
	}

	public static By objrelatedChannelLiveLogo(String Channel) {
		return By.xpath("//span[.=\"" + Channel+ "\"]//ancestor::div[@class='metaData']//preceding-sibling::figure//div[contains(@class,'liveLogo')]");
	}
	
	public static By objChannelWrapper = By.xpath("//div[@class='channelWrapper']");
}
