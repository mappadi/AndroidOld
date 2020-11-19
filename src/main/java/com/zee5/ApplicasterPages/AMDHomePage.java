package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Kushal

public class AMDHomePage {

	// ZEE5 Logo
	public static By objZee5Logo = By.xpath("//*[@contentDescription='LOGO']");

	// Subscribe Teaser
	public static By objSubscribeTeaser = By.xpath("//*[@text='SUBSCRIBE']");

	// Search Button
	public static By objSearchBtn = By
			.xpath("//*[@class='android.support.v7.widget.LinearLayoutCompat']//*[@class='android.widget.ImageView']");
	public static By objTitle = By.xpath("//*[@id='title']");

	// Bottom Navigation TEXT Buttons
	public static By objHomeBtn = By.xpath("(//*[@id='bb_bottom_bar_title'])[1]");
	public static By objUpcomingBtn = By.xpath("(//*[@id='bb_bottom_bar_title'])[2]");
	public static By objDownloadBtn = By.xpath("(//*[@id='bb_bottom_bar_title'])[3]");
	public static By objMoreMenuBtn = By.xpath("(//*[@id='bb_bottom_bar_title'])[5]");

	// Bottom Navigation ICONS
	public static By HomeIcon = By.xpath("(//*[@id='bb_bottom_bar_icon'])[1]");
	public static By UpcomingIcon = By.xpath("(//*[@id='bb_bottom_bar_icon'])[2]");
	public static By DownloadIcon = By.xpath("(//*[@id='bb_bottom_bar_icon'])[3]");
	public static By MoreMenuIcon = By.xpath("(//*[@id='bb_bottom_bar_icon'])[5]");

	public static By objHome = By.xpath("(//*[@text='Home']//parent::*)[1]");
	public static By objUpcoming = By.xpath("(//*[@text='Upcoming']//parent::*)[1]");
	public static By objDownload = By.xpath("(//*[@text='Downloads']//parent::*)[1]");
	public static By objMoreMenu = By.xpath("(//*[@text='More']//parent::*)[1]");

	public static By objLogout = By.xpath("//*[@resource-id='com.graymatrix.did:id/list_item' and @text='Logout']");

	public static By objLogoutPopUpLogoutButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/logoutButton']");
	// Top Menu Navigation tabs
	public static By objHomeTab = By.xpath("//*[@id='title' and @text='Home']");
	public static By objMoviesTab = By.xpath("//*[@id='title' and @text='Movies']");
	public static By objShowsTab = By.xpath("//*[@id='title' and @text='Shows']");
	public static By objNewsTab = By.xpath("//*[@id='title' and @text='News']");
	public static By objFreeMoviesTab = By.xpath("//*[@id='title' and @text='Free Movies']");
	public static By objPremiumTab = By.xpath("//*[@id='title' and @text='Premium']");
	public static By objKidsTab = By.xpath("//*[@id='title' and @text='Kids']");
	public static By objMusicTab = By.xpath("//*[@id='title' and @text='Music']");
	public static By objLiveTvTab = By.xpath("//*[@id='title' and @text='Live TV']");
	public static By objZee5OriginalsTab = By.xpath("//*[@id='title' and @text='ZEE5 Originals']");

	// Banner ad
	public static By objBannerAd = By.xpath("//*[@id='adTag' and @text='AD']");

	public static By objEditProfile = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_edit_profile']");

	// Premium tag on the content cards
	public static By objPremiumTag = By.xpath("(//*[@id='special_image_1'])[1]");

	public static By objContentCard = By.xpath("//*[@id='item_image']");
	public static By objBackIcon = By.xpath("//*[@id='action_icon']");

	// Play Button On carousel
	public static By objPlayBtn = By.xpath("//*[@id='playImage']");

	// Premium button
	public static By objGetPremium = By.xpath("//*[@id='getPremiumButton']");

	public static By objContentTitle(String title) {
		return By.xpath("//*[@id='item_primary_text'and @text='" + title + "']");
	}

	// Tray Tile
	public static By objTrayTitle(String text) {
		return By.xpath("//*[@id='header_primary_text' and contains(text(),\"" + text + "\")]");
	}

	// Select view all button from trayTile
	public static By objViewAllBtn(String trayName) {
		return By.xpath("//*[contains(text(),'" + trayName + "')]//following::*[@id='header_arrow'][1]");
	}

	public static By objPageTitle(String title) {
		return By.xpath("//*[@id='title' and contains(text(),'" + title + "')]");
	}

	public static By objadTag = By.xpath("//*[@id='adTag']");

	// Added objects by Shree Nidhi
	public static By objMoreMenuOptions(String OptionName) {
		return By.xpath("//*[@resource-id='com.graymatrix.did:id/list_item' and @text='" + OptionName + "']");
	}

	public static By objSubscribeNowInMySubscription = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/btn_subscribe_now']");

	public static By objSubscribeNowInMyTransaction = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_sub_now']");
	public static By objPackAmount = By.xpath("//*[@resource-id='com.graymatrix.did:id/pack_amount']");
	public static By objCancelRenewal = By.xpath("//*[@resource-id='com.graymatrix.did:id/cancel_renewal']");
	public static By objBrowseAllPack = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_browse_packs']");
	public static By objMyProfileIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/iconSmile']");
	public static By objGetPremiumCTAOnCarosel = By.xpath("//*[@resource-id='com.graymatrix.did:id/getPremiumButton']");
	public static By objGetPremiumPopUP = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title']");
	public static By objGetPremiumPopUPProceedButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/proceed']");
	public static By objBeforeTVContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[2]");

	/**
	 * Sushma
	 */
	// Selected tab
	public static By objSelectedTab = By.xpath("//*[@resource-id='com.graymatrix.did:id/title' and @selected='true']");
	// Carousel unit as a 1st unit in the page
	public static By objCarouselUnit = By.xpath(
			"(//*[@resource-id='com.graymatrix.did:id/tabLayout']/following-sibling::*/child::*/child::*/child::*/child::*)[1]");
	// Carousel title
	public static By objCarouselTitle1 = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text' and @onScreen='true']");
	public static By objCarouselTitle2 = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/item_image']//following-sibling::*[@id='item_primary_text']");
	public static By objCarouselTitle3 = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/pager']//*[@id='item_primary_text']");
	// Carousel dots
	public static By objCarouselDots = By.xpath("//*[@resource-id='com.graymatrix.did:id/indicator']/child::*");

	public static By objCarouselConetentCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/hero_1_cell_parent']");
	public static By objCarouselPlayIconContentCard = By.xpath(
			"//*[@resource-id='com.graymatrix.did:id/playImage']/preceding-sibling::*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	// Consumption screen title
	public static By objConsumptionScreenTitle = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");

	// Continue watching tray
	public static By objContinueWatchingTray = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/header_primary_text' and @text='Continue Watching']");
	public static By objCloseIconOfFirstContentOfCWTray = By
			.xpath("(//*[@resource-id='com.graymatrix.did:id/item_remove'])[1]");
	public static By objLeftTimeOfFirstContentOfCWTray = By
			.xpath("(//*[@resource-id='com.graymatrix.did:id/time_progress'])[1]");
	public static By objProgressBarOfFirstContentOfCWTray = By
			.xpath("(//*[@resource-id='com.graymatrix.did:id/progress_bar'])[1]");

	public static By objSubscribeIcon = By.xpath("//*[@id='toolbar_subscribe_text']");

	public static By objTrendingNowTray = By.xpath("//*[@text='Trending Now']");
	public static By objTrendingOnZee5Tray = By.xpath("//*[@text='Trending on Zee5']");

	public static By objCarouselTitle = By.xpath("//*[@id='item_primary_text']");

	public static By objCarouselUnitwithmastHeadAdbanner = By.xpath(
			"(//*[@resource-id='com.graymatrix.did:id/tabLayout']/following-sibling::*/child::*/child::*/child::*/child::*)[2]");

	public static By objCarouselUnitwhenNomastHeadAdbanner = By.xpath(
			"(//*[@resource-id='com.graymatrix.did:id/tabLayout']/following-sibling::*/child::*/child::*/child::*/child::*)[1]");

	public static By objLoginButtonOnPlayerscreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/login_button']");

	public static By objWatchTrailerIconOnPlayerscreen = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/watchTrailerTv']");

	public static By objContentTitleOfCWTray(String title) {
		return By.xpath(
				"//*[@text='Continue Watching']/parent::*/parent::*/following-sibling::*/child::*/child::*/child::*/following-sibling::*[@text=\""
						+ title + "\"]");
	}

	public static By objBeforeTVTray = By
			.xpath("//*[@resource-id='com.graymatrix.did:id/header_primary_text' and contains(text(),'Before')]");

	public static By objTab1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[1]");

	public static By objTab5 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/title'])[5]");
	
	public static By objSubscribePopUpInConsumptionPage = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title' and @text='Subscribe']");

	public static By objSubscribePopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title']");
	public static By objPlayerScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/player_root']");
	public static By objPauseIconOnPlayerScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_pause']");
	public static By objOpenWithDevicePopUp = By.xpath("//*[@text='Open with']");
	public static By objAdBannerAboveCarousel =By.xpath("//*[@resource-id='com.graymatrix.did:id/ad_image']//following::*[@id='indicator']");
	public static By objMaximizeIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_fullscreen']");
	
	public static By objFirstChannelCard  = By.xpath("(//*[@id='item_primary_text'])[1]");
	public static By objKidsContentCard = By.xpath("//*[@id='header_primary_text']//following::*[6]");
}
