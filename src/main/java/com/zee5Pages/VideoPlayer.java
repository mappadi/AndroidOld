package com.zee5Pages;

import org.openqa.selenium.By;

public class VideoPlayer {
	
	public static By objPremiumTag = By.xpath("//*[@id='premium_tag']");
	
	
	public static By objPremiumTagwithShowName(String ShowName) {
		return By.xpath("//*[@text='Premium' and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='"+ShowName+"']]]");
	}
	
	public static By objScrollTotext(String trayName) {

		return By.xpath("//*[@text='" + trayName + "']");
	}
	
	public static By objViewAllBtn(String ShowName) {
		return By.xpath("	//*[@text='View All' and (./preceding-sibling::* | ./following-sibling::*)[@text='"+ShowName+"']]\r\n" + 
				"");
	}
	public static By objMore(String TrayTitle) {
		return By.xpath("//*[@text='More' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='" + TrayTitle +"']]]]");
	}
	
	public static By objPremiumTag(int value)
	{
		return By.xpath("(//*[@resource-id='com.graymatrix.did:id/premium_tag'])["+value+"]");	
	}
	
	public static By objEpisodeThumbnail = By.xpath("//*[@id='episode_thumbnail']");
	
	public static By objSelectThumbnail(int value)
	{
		return By.xpath("(//*[@id='episode_thumbnail'])["+value+"]");	
	}
	
	public static By objSunscriptionPopUp = By.xpath("//*[@id='subscription_banner_layout']");
	public static By objDownArrowBtn = By.xpath("//*[@id='player_minimize_button']");
	public static By objPlayPauseBtn = By.xpath("//*[@id='player_play_pause']");
	public static By objNextBtn = By.xpath("//*[@id='player_play_next']");
	public static By objPreviousBtn = By.xpath("//*[@id='player_play_previous']");
	
	public static By objShareIcon = By.xpath("//*[@id='player_share_button']");
	public static By objPlayerOptions = By.xpath("//*[@id='player_options_button']");
	public static By objPlayerStartTime = By.xpath("//*[@id='player_current_time']");
	public static By objPlayerEndTime = By.xpath("//*[@id='player_end_time']");
	public static By objSeekBar = By.xpath("//*[@resource-id='com.graymatrix.did:id/player_seek_bar']");
	
	public static By objQualityBtn = By.xpath("//*[@text='Quality']");
	public static By objAutoPlayBtn = By.xpath("//*[@text='Autoplay']");
	public static By objQualityAuto = By.xpath("//*[@text='Auto']");
	public static By objQuality1080 = By.xpath("//*[@text='1080p']");
	public static By objQuality720p = By.xpath("//*[@text='720p']");


	public static By ObjAutoPlayToggle = By.xpath("//*[@id='player_settings_switch']");
	public static By objAddtoWatchListBtn = By.xpath("//*[@text='Add to Watchlist']");
	public static By objBy = By.xpath("//*[@id='sub_menu_text_view']");
	public static By objDownloadIcon = By.xpath("//*[@id='download_progress']");
	
	public static By ObjToastTxt = By.xpath("//*[@id='message']");
	
	public static By objDownloadOptionPopUp = By.xpath("//*[@id='dialog_layout']");
	public static By objDownloadOptionOKBtn = By.xpath("//*[@id='player_dialog_ok_text']");
	public static By objDownloadOptionCancelBtn = By.xpath("//*[@id='player_dialog_cancel_text']");
	public static By objDownloadOptionBest = By.xpath("//*[@resource-id='com.graymatrix.did:id/high_text_size']");
	public static By objShowTitle = By.xpath("//*[@id='tvshow_name']");
	public static By objShowDetails = By.xpath("//*[@id='tvshow_season_episodes_text']");
	
	public static By objDeviceStoragePopup = By.xpath("//*[@id='dialog_layout']");
	
	
	public static By objDeviceStoragePopupOkBtn = By.xpath("//*[@id='player_dialog_ok_text']");
	
	
	public static By objDownloadedShowTitle = By.xpath("//*[@id='download_movies_title']");
	
	public static By objMyDownloadsPage = By.xpath("//*[@id='download_offline_title']");
	
	
	public static By objWatchList = By.xpath("//*[@id='player_options_watch_later']");

	public static By objFullScrnBtn = By.xpath("//*[@id='expand_icon_player']");
	

	public static By objtitleOfAnContentInViewAll(String TitleName) {
		return By.xpath("//*[@text=\""+TitleName+"\"]");
	}
	

}
