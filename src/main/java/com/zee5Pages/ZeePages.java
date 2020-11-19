package com.zee5Pages;

import org.openqa.selenium.By;

public class ZeePages {
	
	public static By objSkip = By.xpath("//*[@id='skip']");
	public static By objHome = By.xpath("//*[@text='HOME']");
	public static By objEpisodeTitle = By.xpath("(//*[@id='episode_title'])[1]");
	public static By objShare = By.xpath("//*[@id='player_share_button']");
	public static By objFbShare = By.xpath("//*[@text='Facebook']");
	public static By objFbPost = By.xpath("//*[@text='POST']");
	public static By objSubscribeNowBtn = By.xpath("//*[@id='subscribe_now']");
	public static By objWatchFreeEpisodesBtn = By.xpath("//*[@id='freeepisode_title']");
	public static By objShows = By.xpath("//*[@text='SHOWS']");
	public static By objPlayerFrame1 = By.xpath("//*[@id='player_playback_controls_layout']");
	public static By objPlayerTimer = By.xpath("//*[@id='player_current_time']");
	public static By objClickHereToLogin = By.xpath("//*[@id='click_here_login_pop']");
	public static By objSkipSubscribe = By.xpath("//*[@id='skip_now']");
	public static By objUpgradeToPremium = By.xpath("//*[@id='upgrade_premium']");
	public static By objLoginViaEmail = By.xpath("//*[@id='login_email_button_text']");
	public static By objEmailId = By.xpath("//*[@id='input_email_text']");
	public static By objPassword = By.xpath("//*[@id='input_password_text']");
	public static By objLoginBtn = By.xpath("//*[@text='LOGIN']");
	public static By objHamburgerMenu = By.xpath("//*[@id='action_menu']");
	public static By objLogoutBtn = By.xpath("//*[@text='Logout']");
	
	public static By objProgressBar = By.xpath("//*[contains(@id,'progressBar')]");
	public static By objTwitter = By.xpath("//*[@text='Tweet']");
	public static By objTwitterShareButton = By.xpath("//*[@id='button_tweet']");
	public static By objAd = By.xpath("//*[contains(text(),'Ad :')] | //*[contains(@contentDescription,'Ad :')]");	
}
