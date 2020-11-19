package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAPlayerPage {

	// Verify ad Present
	public static By objAd = By.xpath("//div[contains(@class,'playkit-ad-break')]");

	// Player loader
	public static By objPlayLoader = By
			.xpath("//div[contains(@class,'playkit-state-idle') or contains(@class,'playkit-state-paused')]");

	// Play

//	 Playback Video Overlay
	public static By objPlaybackVideoOverlay = By.xpath("//*[@class='kaltura-player-container']");

	public static By objPlayerQualityButton = By.xpath("//span[contains(text(),'Quality')]");
//	 Play button
	public static By playBtn = By.xpath("//*[@class='playkit-icon playkit-icon-play']");
//	 Pause button
	public static By pauseBtn = By.xpath("//*[@class='playkit-icon playkit-icon-pause']");
//	 SKIP INTRO button
	public static By skipIntroBtn = By.xpath("//*[contains(@class,'skip_intro')]");
//	 Progress bar or Seek bar
	public static By progressBar = By.xpath("//*[contains(@class,'playkit-progress-bar')]");
//	 Rewind 10 Seconds button
	public static By rewind10SecBtn = By.xpath("//*[@class='playkit-icon playkit-icon-rewind-10']");
//	 Forward 10 Seconds button
	public static By forward10SecBtn = By.xpath("//*[@class='playkit-icon playkit-icon-forward-10']");
//	 Audio button
	public static By audioBtn = By.xpath("//*[contains(@class,'playkit-volume-control')]//button");
//	 Settings button
	public static By settingsBtn = By.xpath("//*[contains(@aria-label,'Settings')]");
//	 Quality button on Settings menu
	public static By qualityBtn = By.xpath("//span[.='Quality']");
//	 Subtitles button on Settings menu
	public static By subtitlesBtn = By.xpath("(//*[contains(@class,'mainMenu')]//span)[3]");
//	 Maximize window button
	public static By maximizeBtn = By.xpath("//*[contains(@class,'playkit-icon-maximize')]");
//	 Minimize window button
	public static By minimizeBtn = By.xpath("//*[contains(@class,'playkit-icon-minimize')]");
//	 Share button
	public static By shareBtn = By.xpath("//*[contains(@title,'Share')]");
//	 Facebook Share button
	public static By facebookShareBtn = By.xpath("//*[contains(@aria-label,'facebook')]");
//	 Twitter Share button
	public static By twitterShareBtn = By.xpath("//*[contains(@aria-label,'twitter')]");
//	 Email Share button
	public static By emailShareBtn = By.xpath("//*[contains(@aria-label,'email')]");
//	 Whats-app share button
	public static By whatsAppShareBtn = By.xpath("//*[contains(@aria-label,'whatsapp')]");
//	 Watchlist button
	public static By watchListBtn = By.xpath("//p[contains(text(),'Watchlist')]");
//	 Watch Trailer button
	public static By watchTrailerBtn = By.xpath("//p[contains(text(),'Watch Trailer')]");
//	 Pop up
	public static By objWhyRegister = By.xpath("//div[@class='heading']");
//	 Close button
	public static By objCloseBtn = By.xpath("//div[@class='manCloseIcon']");

	// Mute button
	public static By objMuteButton = By.xpath("//button[@class='playkit-control-button' and @aria-label='Mute']");

	// Unmute button
	public static By objUnmuteButton = By.xpath("//button[@class='playkit-control-button' and @aria-label='Unmute']");

//	 Content cards on Up next rail
	public static By objContentCardsOnPlayback(int index) {
		return By.xpath("(//div[@class='carousel-slider']//div)[" + index + "]");
	}

	public static By objBetterQualityOption = By.xpath("//div[contains(text(),'Better')]");

	public static By objBestQualityOption = By.xpath("//div[text()='Best']");
//	 Ad Present
	public static By objPlayerAdPresent = By
			.xpath("//div[contains(@id,'ads-container') and contains(@style,'visible')]");
//	 Ad absent
	public static By objPlayerAdAbsent = By.xpath("//div[contains(@id,'ads-container') and contains(@style,'hidden')]");

	// Consumption page content title for Live News Card
	public static By objContentTitleLiveTV = By.xpath("//div[@class='channelConsumptionMetaDiv']//h2");

	// Consumption page content meta data episode number
	public static By objContentMetaEpisode = By
			.xpath("//div[@class='consumptionMetaDiv']//div[@class='metaInfo']//p[1]");

	// Consumption page content meta data episode number
	public static By objContentMetaDate = By.xpath("//div[@class='consumptionMetaDiv']//div[@class='metaInfo']//p[2]");

	// Consumption page content meta data duration
	public static By objContentMetaDuration = By
			.xpath("//div[@class='consumptionMetaDiv']//div[@class='metaInfo']//p[3]");

	// Consumption page content meta data duration
	public static By objContentMetaGenre = By.xpath("//div[@class='consumptionMetaDiv']//div[@class='metaInfo']//p[4]");

	// Consumption page content meta data duration
	public static By objContentMetaAgeRating = By
			.xpath("//div[@class='consumptionMetaDiv']//div[@class='metaInfo']//p[5]");

	// Player loader
	public static By objPlayerLoader = By.xpath("//div[@class='playkit-spinner']");

	// Player
	// public static By objPlayer =
	// By.xpath("//div[@class='playkit-overlay-action']");
	// public static By objPlayer =
	// By.xpath("//*[@class='kaltura-player-container']");
	public static By objPlayer = By.xpath("//div[@class='playkit-overlay-action']");

	// Player pause
	public static By objPlayerPause = By.xpath("//i[contains(@class,'playkit-icon-pause')]");

	// Player play
	public static By objPlayerPlay = By.xpath("//i[contains(@class,'playkit-icon-play')]");

	// Player seek bar
	public static By objPlayerSeekBar = By.xpath("//div[contains(@class,'playkit-progress-bar')]");

	// Player current duration
	public static By objPlayerCurrentDuration = By.xpath("//div[@class='currentDuration']//span");

	// Player total duration
	public static By objPlayerTotalDuration = By.xpath("//div[@class='totalDuration']//span");

	// Live player volume button
	public static By objLivePlayerVolume = By.xpath("//i[contains(@class,'playkit-icon-volume-waves')]");

	// Live player Live tag
	public static By objLivePlayerLiveTag = By.xpath("//div[@class='playkit-live-tag']");

	// Player screen
	public static By objPlayerControlScreen = By.xpath("//div[@class=\"playkit-video-player\"]");

	// Player play and pause button
	public static By obyPlayAndPauseBtn = By.xpath("//button[@class='playkit-control-button']//div");

	// Quality Auto
	public static By objAutoQuality = By
			.xpath("//*[contains(@class,\"subMenuWrapper\")]//div[contains(text(),'Auto')]");
	// Quality Good
	public static By objGoodQuality = By
			.xpath("//*[contains(@class,\"subMenuWrapper\")]//div[contains(text(),'Good')]");
	// Quality Best
	public static By objBestQuality = By
			.xpath("//*[contains(@class,\"subMenuWrapper\")]//div[contains(text(),'Best')]");
	// Quality Better
	public static By objBetterQuality = By
			.xpath("//*[contains(@class,\"subMenuWrapper\")]//div[contains(text(),'Better')]");
	// Unique xpath for all the Qualities
	public static By objAllQaulities = By.xpath("//*[contains(@class,\"subMenuWrapper\")]");

	public static By objAllQualityOptions(int index) {
		return By.xpath("(//*[contains(@class,\"subMenuWrapper\")])[" + index + "]");
	}

	// Qaulity title in settings menu
	public static By objPlayerQualitySelected = By.xpath("//span[contains(text(),'Quality')]//following-sibling::*");
	// Add to Watchlist Login Popup for guest user
	public static By objLoginRequiredTxt = By.xpath("//h2[contains(@class,'popupTitle bigTitle')]");
	// Close button for Login Pop up
	public static By objCloseBtnLoginPopup = By
			.xpath("//div[contains(@class,'noSelect closePupup iconInitialLoad-ic_close')]");

	public static By objCloseBtnLoginPopupWeb = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	// Ad
	public static By objAdPresent = By.xpath("//div[@class='totalDuration adBreak']");
	// Watch Credits icon
	public static By objWatchCredit = By.xpath("//div[@class='watchCreditText']");
	// Content cards on player
	public static By objContentCardsOnPlayer = By.xpath("//*[@class='carousel-slider']");
	// First content on player
	public static By objfirstContent = By
			.xpath("(//*[@class='mainCarouselDiv']//parent::*[@class='mainCarouselDiv'])[1]");
	// Content Name below the Playback
	public static By objContentName = By.xpath("//div[@class='consumptionMetaDiv']//h1");

	public static By objPlayerScrubber = By.xpath("//a[@class='playkit-scrubber']");
	public static By objPlayerQualities = By.xpath("//*[contains(@class,'subMenuWrapper')]//div");

//	=========================================================================================

//	NETWORK
	public static By objcurrenttime = By.xpath("//div[@class='currentDuration']//span");

//	public static By objcurrenttime1 = By.xpath("//div[@class='playkit-time-display']//span");

//	BINDU
	// Subscription popup
	public static By objSubscriptionpopup = By
			.xpath("//h2[contains(@class,'popupTitle bigTitle')]//p[text()='Subscribe']");

	// Subscription popup close icon
	public static By ObjSubscriptionpopupCloseIcon = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	// Watch promo
	public static By objPromo = By.xpath("(//div[@class='playBtn'])[1]");

	// Meta data of promo
	public static By objPlayerPromoMetadata = By.xpath("//div[@class='consumptionMetaDiv']/child::h1");

	// Meta data of CarouselConsumption screen/
	public static By objConsumptionCarouselContent = By.xpath("//div[@class='metaInfo']//div");

	// Meta data of showTitle in consumption screen
	public static By objShowTitle(String title) {

		return By.xpath("//h1[text()='" + title + "']");
	}

	// Premium Episode
	public static By objPremiumEpisode = By.xpath("(//div[@class='cardPremiunIconContainer ']//parent::*)[1]");

	// Close icon in register dialog
	public static By objCloseRegisterDialog = By.xpath("//div[@class='manCloseIcon']");

//	SUSHMA MOVIE MODULE
	public static By objRegisterPopUp = By
			.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open mandatoryRegisterPopup ']");

	public static By objRegisterPopUpCloseIcon = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

//	BHAVANA SHOWS MODULE
	// Watch latest Episode CTA
	public static By objWatchLatestEpisode = By.xpath("//p[contains(text(),'Watch Latest Episode')]");

	public static By objlatestepisode = By.xpath("//p[contains(text(),'Watch Latest Episode')]");

	public static By objWhyRegisterPopUp = By.xpath("//div[.='Sign Up']");

	public static By objPopUpafterPlayer = By
			.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open popupModal']");

	public static By oblClosePopup = By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objWEBCloseBtnLoginPopup = By.xpath("//div[@class='manCloseIcon']");

	public static By objtotaltime = By.xpath("//div[@class='totalDuration']//span");
	public static By objprogressBar = By.xpath("//div[@class='playkit-progress-bar']");
	public static By objprogressProgress = By.xpath("//div[@class='playkit-progress']");
	public static By objtimedanchors = By.xpath("(//div[@playermarkertag='timer']//div)");

	public static By objtimedAnchor(int i) {
		return By.xpath("(//div[@playermarkertag='timer']//div)[" + i + "]");
	}

	public static By vootCarouselContent = By
			.xpath("(//div[contains(@class,'slick-center')]//img[contains(@class,'image-transition')])[2]");
	public static By startPlayer = By.xpath(
			"//div[@class='playkit-pre-playback-play-overlay']//i[contains(@class,'playkit-icon playkit-icon-play')]");
	public static By vootPlayer = By.xpath("//div[contains(@class,'playkit-overlay-action')]");
	public static By vootPlayerPause = By
			.xpath("//div[@class='playkit-playback-controls']//i[contains(@class,'playkit-icon-pause')]");
	public static By objWouldYouLikeClosePopup = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	public static By objWebZeeLogo = By.xpath("//div[@class='zeeLogo noSelect']//child::*");
	public static By objZeeLogo = By.xpath("//div[@class='zeeLogo noSelect']");
	public static By objSpinner = By.xpath(".//*[@class='playkit-spinner']");
	public static By objContentTitleInConsumptionPage = By.xpath("//div[@class='consumptionMetaDiv']//h1");
	public static By objLiveTag = By.xpath("//*[@class='playkit-live-tag']");

	public static By objCompleteProfile = By.xpath("//div[contains(text(),'Complete profile')]");
	public static By objCompleteProfileCloseIcon = By.xpath("//div[@class='manCloseIcon']");

	public static By objGetPremium = By.xpath("//div//button[@class='subscribe-teaser-button']");

	public static By objSkipAd = By.xpath("//button[@aria-label='Skip Ad']");

	public static By objAdultView = By
			.xpath("//span[contains(text(),'This content is for Adult viewing only. Please login to continue. ')]");

	public static By objPlayerscreen = By.xpath("//div[@class='playkit-overlay-action']");

	public static By objUpnextCard = By.xpath("(//div[@class='mainCarouselDiv'])[1]");

	public static By objLoginText = By.xpath("//span[@class='login-link']");

	public static By objSubscribeLink = By.xpath("//*[@class='popupContent upgradePopupContent']");

	public static By objWatchPromo = By.xpath("//a[@class='showDetailIcon play']/child::div[@class='playIcon']");

	public static By subscribePopUp = By.xpath("//*[contains(@class,'popupTitle') and .='Subscribe']");

	public static By objfasterclosePopUp = By
			.xpath("//div[@class='adoric_element element-shape closeLightboxButton editing']");

	public static By objfasterPopUp = By.xpath("//div[@class='adoric_element element-text selected editing']");

	// Consumptions page show title
	public static By objConsumptionsShowTitle = By.xpath("//div[@class='metaInfo']//a");

	// Show details page show title
	public static By objContentShowTitle = By
			.xpath("//div[@class='showDetails']//div[contains(@class,'bannerTitle')]//h1");

	public static By objPlayerSelectedQuality = By
			.xpath("//*[contains(@class,'subMenuWrapper') and contains(@class,'tickMark')]//div");

	public static By objPlayerUnSelectedQuality = By
			.xpath("//*[contains(@class,'subMenuWrapper') and not(contains(@class,'tickMark'))]//div");

	public static By objPlayerUnSelectedQuality(String quality) {
		return By.xpath("//*[contains(@class,'subMenuWrapper') and not(contains(@class,'tickMark'))]//div[.='" + quality
				+ "']");
	}

	public static By objPlayerAudioTrack = By.xpath("//span[@class='menuTitle' and .='Audio Track']");

	public static By objPlayerAudioTrackIcon = By.xpath("//span[@class='menuTitle' and .='Audio Track']");
	public static By objPlayerAudioTracksAvailable = By.xpath("//*[contains(@class,'subMenuWrapper')]//div");
	public static By objPlayerSelectedAudioTrack = By
			.xpath("//*[contains(@class,'subMenuWrapper') and contains(@class,'tickMark')]//div");
	public static By objPlayerUnSelectedAudioTrack = By
			.xpath("//*[contains(@class,'subMenuWrapper') and not(contains(@class,'tickMark'))]//div");

	public static By objPlayerUnSelectedAudioTrack(String track) {
		return By.xpath(
				"//*[contains(@class,'subMenuWrapper') and not(contains(@class,'tickMark'))]//div[.='" + track + "']");
	}

	public static By objFirstCardFreeChnnels = By.xpath(
			"(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='FREE Channels']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//a[@class='noSelect content'])[1])");

	public static By watchListLoginPopup = By.xpath("//div[@class='popupContent watchListLoginCheckContent']");

	public static By watchListLoginButton = By.xpath("//div[contains(text(),'Login')]");

	public static By watchListLoginPopupCloseButton = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objFirstCardFreeChnnelName = By.xpath(
			"(((((((//div[@class='trayContentWrap']//div[@class='trayHeader']//h2[.='FREE Channels']))//parent::*//parent::*)//child::*[2])//child::*//div[@class='slick-track'])//child::*//div[@class='clickWrapper']//figure//a[@class='noSelect content']//img)[1])");

	// Consumption page content title
	public static By objContentTitle = By
			.xpath("//div[@class='consumptionMetaDiv' or contains(@class,'bannerTitle')]//h1");

//	 Total Duration time
	public static By totalDurationTime = By.xpath("//*[@class='totalDuration']//*[@class='playkit-time-display']//*");
//	 Current duration time
	public static By currentDurationTime = By
			.xpath("//*[@class='currentDuration']//*[@class='playkit-time-display']//*");
//Play or Pause
	public static By objPlayOrPause = By
			.xpath("//*[@class='playkit-icon playkit-icon-pause' or @class='playkit-icon playkit-icon-play']");

	// Watchlist button already added
	public static By watchListBtnAlreadyAdded = By
			.xpath("//div[contains(@class,'ic_watchlist')]//p[contains(text(),'Watchlist')]");

	// Watchlist button not added
	public static By watchListBtnNotAdded = By
			.xpath("//div[contains(@class,'add_Watchlist')]//p[contains(text(),'Watchlist')]");

	// Subscribe Now Link on player
	public static By objSubscribeNowLink = By
			.xpath("//span[contains(@class,'subscribe-link') and contains(text(),'Subscribe')]");

	// Get Premium CTA Below the Player Screen
	public static By objGetPremiumCTABelowPlayerScreen = By
			.xpath("//button[contains(@class,'subscribe-teaser-button') and contains(text(),'Subscribe')]");

	public static By objWatchingATrailerMessage = By
			.xpath("//div[@class='trailerInfoContainer' and contains(text(),'watching a trailer')]");
	
	public static By objSkipAdButton = By.xpath("//*[@text='Skip Ad']");

}
