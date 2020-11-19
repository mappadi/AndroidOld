package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDPlayerScreen {

	public static By objPlayer = By.xpath("//*[@id='controller']");
	public static By objPlayIcon = By.xpath("//*[@id='icon_play']");
	public static By objPauseIcon = By.xpath("//*[@id='icon_pause']");
	public static By objNextIcon = By.xpath("//*[@id='icon_next']");
	public static By objProgressBar = By.xpath("//*[@id='progress']");
	public static By objBackButton = By.xpath("//*[@id='icon_down']");
	public static By objChromeCastIcon = By.xpath("//*[@id='icon_cast']");
	public static By objFullscreenIcon = By.xpath("//*[@id='icon_fullscreen']");
	public static By objTotalDuration = By.xpath("//*[@id='durationText1']");
	public static By objTimer = By.xpath("//*[@id='positionText1']");
	public static By objShareIcon = By.xpath("//*[@id='native_share_button']");
	public static By objWatchlistIcon = By.xpath("//*[@id='watch_list_image']");
	public static By objDownloadIcon = By.xpath("//*[@id='downlowd_image']");
	public static By objfirstContentcardOfParticularTray(String trayTitle) {
		return By.xpath("(//*[@text="+trayTitle+"]/parent::*/parent::*/following-sibling::*/child::*/child::*/child::*/child::*)[1]");
	}
	public static By objThreeDotsOnPlayer = By.xpath("//*[@id='icon_more']");
	public static By objTitleOnPlayer = By.xpath("//*[@id='title_main']");
	public static By objPlayerScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/player_root']");
	
	public static By objAd = By.xpath("(//*[contains(text(),'Ad')])[1]");
	public static By objQuality = By.xpath("//*[@id='icon_quality_text']");
	public static By objQualityOptions = By.xpath("//*[@id='textView2']");
	public static By objQualityOptions(int i) {
		return By.xpath("(//*[@id='textView2'])["+i+"]");
	}
	public static By objAddToWatchlist = By.xpath("//*[@id='icon_add_to_watch_list_text']");
	public static By objPlaybackRate = By.xpath("//*[@id='icon_playback_rate_text']");
	
	public static By objPremiumTextOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/static_premium_text']");
	public static By objSubscribeNowLinkOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribe_now_action']");
	public static By objLoginTextOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/static_login_text']");
	public static By objLoginLinkOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/login_button']");
	public static By objGetPremiumPopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title' and @text='Subscribe']");
	
	public static By objPlaybackRateSelected = By.xpath("(//*[@id='icon_selected']//parent::*//child::*)[2]");
	public static By objPlayerLoader = By.xpath("//*[@id='player_loading_progress']");
	public static By objSharePopUp = By.xpath("//*[@id='resolver_list']");
	public static By objTwitter = By.xpath("//*[@text='Tweet']");
	public static By objTweetButton = By.xpath("//*[@id='button_tweet']");
	public static By objFacebook = By.xpath("//*[@text='News Feed']");
	public static By objFacebookPost = By.xpath("//*[@text='POST']");
	public static By objCopyToClipboard = By.xpath("//*[@text='Copy to clipboard']");
	public static By objRetryBtn = By.xpath("//*[@id='zretry']");
}
