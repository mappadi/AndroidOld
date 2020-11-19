package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Sushma

public class AMDDownloadPage {
	
	public static By objBrowseToDownloadBtn = By.xpath("//*[@id='btn_browse_to_download']");
	
	public static By objCardContentImage = By.xpath("//*[@id='cardContentImage']");
	
	public static By objShowDetails = By.xpath("//*[@id='rl_show_details']");
	
	public static By objShowTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_title']");
	
	public static By objEpisodeMetadata = By.xpath("//*[@id='ll_episode_size']");
	
	public static By objEpisodeNumber = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_episode']");
	
	public static By objEpisodeSize = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_size']");
	
	public static By objContentCardRightArrowMark = By.xpath("//*[@id='img_right_arrow']");
	
	//Select the Show card
	public static By objSelectShowName(String showName) {
		return By.xpath("//*[@id='img_show']//following::*[@text='"+showName+"']//following::*[@id='img_right_arrow'][1]");
	}
	
	public static By objEpisodeDuration = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_time']");
	
	public static By objEpisodeState = By.xpath("//*[@resource-id='com.graymatrix.did:id/img_state']");
	
	public static By objDownloadMoreEpisodesButton = By.xpath("//*[@id='btn_download_more_episode']");

//Downloads text at the top center 
	public static By objDwnloadsHeader= By.xpath("//*[@id='title']");
	
	//Shows tab
	public static By objshowstab = By.xpath("//*[@text='Shows']");
	
	//Movies tab
	public static By objmoviestab=By.xpath("//*[@text='Movies']");
	//videos tab
	public static By objvideostab= By.xpath("//*[@text='Videos']");
	
	/**
	 * Bhavana
	 */
	public static By objGreyedThumbnail = By.xpath("//*[@id='downloadView' and @class='android.view.View']");
	public static By objdownloadpopup = By
			.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Pause Download']]");
	public static By objPauseDownloadoption = By.xpath("//*[@resource-id='com.graymatrix.did:id/tvPauseAll']");
	public static By objCancelDownloadOption = By.xpath("//*[@id='tvCancelDownload']");
	
	public static By objDownloadbtn = By.xpath("//*[@id='downlowd_image']");
	public static By objDownloadVideoQualityPopup = By.xpath("//*[@id='popup_title']");
	public static By objStartDownloadCTA = By.xpath("//*[@id='bottomSheetDialogStartDownloadBtn']");
	
	public static By objSearchIcononDownloadsScreen = By.xpath("//*[@class='android.widget.ImageView' and ./parent::*[@class='android.support.v7.widget.LinearLayoutCompat']]");
	public static By objSearchedResultFirstcontent = By.xpath("//*[@class='android.view.ViewGroup' and ./*[@id='itemImageParent']]");
	public static By objGoToDownloadsOption = By.xpath("//*[@id='tvNavigateDownload']");
	public static By objRightArrow = By.xpath("//*[@id='img_right_arrow']");
	public static By objDownloadingText = By.xpath("//*[@id='tv_downloading_out_of']");
	public static By objDownloadingCircularBar = By.xpath("//*[@class='android.widget.ImageView' and ./parent::*[@id='rl_circular']]");
	public static By objPausedText = By.xpath("//*[@text='Paused']");
	public static By objPausedBar = By.xpath("//*[@id='img_state']");
	public static By objContinueOption = By.xpath("//*[@id='tvRetryDownload']");
	public static By objDownloadFailedText = By.xpath("//*[@text='Download Failed']");
	public static By objDownloadErrorText = By.xpath("	//*[@id='tv_download_again']");
	public static By objRetryCTA = By.xpath("//*[@id='tvRetryDownload']");
	public static By objDownloadIcon = By.xpath("//*[@id='downlowd_image']");
	
	public static By objSpecificSearch(String str){
			return By.xpath("//*[@class='android.view.ViewGroup' and ./*[@id='itemImageParent'] and ./*[@text='" + str + "']]");
		}
	public static By objCalloutPopup = By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Pause All']]");
	public static By objPauseAllOption = By.xpath("//*[@id='tvPauseAll']");
	public static By objDownloadingConents(String str){
			return By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[./*[@class='android.view.ViewGroup']] and ./*[./*[@text='" + str + "']]]");
		}
	
	public static By objPackExpiredText = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_days']");
	public static By objRemaindMeLater = By.xpath("//*[@id='btn_remind_me_later']");
	public static By objDownloadedTickMark = By.xpath("(//*[@resource-id='com.graymatrix.did:id/img_state'])[1]");
	public static By objDeleteDownloadOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/tvDeleteDownload']");
	public static By objDownloadTextIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/iconTextView_download']");
	public static By objSearchedContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[2]");
	public static By objQueuedbar(String str){
		return By.xpath("//*[@id='circularProgressbar' and ./parent::*[./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='" + str + "']]]]]]");
		}

	public static By objPausedIcon(String str){
			return By.xpath("//*[@id='img_state' and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='" + str + "']]]]]");
		}
	
	public static By objNoOfEpisodeList = By.xpath("//*[@id='cardContentImage']");
	
	public static By objShowsDownloadPage = By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@id='cardContentImage']]");
	
	public static By objEpisodesList = By.xpath("//*[@id='rcl_show_downloaded']");
	
	public static By objDownloadedVideoContent = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_title']");
	
	
	/**
	 * Manasa
	 */
	public static By objDownloadedContent = By.xpath("(//*[@id='tv_title'])[1]");
	public static By objPlayDownloadedContent = By.xpath("//*[@id='tvPlay']");
	public static By objDeleteDownloadedContent = By.xpath("//*[@id='tvDeleteDownload']");
	
	public static By objCalloutPopupwhileDownloading = By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Pause']]");
	public static By objTickIcon= By.xpath("//*[@id='img_state']");
	public static By objCalloutAfterDownload = By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Play']]");
	public static By objPlayCTA = By.xpath("//*[@id='tvPlay']");
	public static By objDeleteDownloadCTA = By.xpath("//*[@id='tvDeleteDownload']");
	public static By objPauseIconOnPlayer = By.xpath("//*[@id='icon_pause']");
	public static By objPlayIconOnPlayer = By.xpath("//*[@id='icon_play']");
	public static By objBackOnPlayer = By.xpath("//*[@id='icon_down']");
	
	public static By objTitleoftheShow = By.xpath("//*[@id='tv_title']");
	public static By objThumbnailOfShows = By.xpath("//*[@id='img_show']");
	public static By objNoOfEpisodes = By.xpath("//*[@id='tv_episode']");
	public static By objSizeOfEpiodes = By.xpath("//*[@id='tv_size']");
	public static By objRightArrowinDownloads = By.xpath("//*[@id='img_right_arrow']");
	public static By objNewEpisodeTag = By.xpath("//*[@id='tv_new_epiosode']");
	public static By objDownloadNowbbtn = By.xpath("//*[@id='tv_download_Now']");
	public static By objNewEpisodeContent = By.xpath("//*[@id='rl_new_episode']");	
	public static By objDownloadMoreCTA = By.xpath("//*[@id='btn_download_more_episode']");
	public static By objClosebtn = By.xpath("//*[@id='img_close']");
	public static By objThumbnailOfLatestEpisode = By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@id='img_show']]");
	
	public static By objEnterPinCTA = By.xpath("//*[@id='btnAction']");
	public static By objRemindMeLaterCTA = By.xpath("//*[@id='btn_remind_me_later']");
	public static By objSubscriptionExpiry = By.xpath("//*[@id='tv_days']");
	public static By objSubscriptionExpiryMessage = By.xpath("//*[@text='Please remember to renew your subscription to access your downloads.']");
	
	public static By objtitleofNewEpisode = By.xpath("//*[@id='rl_show_details']//*[@id='tv_title']");

	public static By objIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/iconTextView_download']");		
	public static By objCallOutwithPauseAll = By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Pause All']]");
	public static By objSearchIcon = By.xpath("//*[@id='title' and @text='Downloads']//following::*[1]");
	
	/**
	 * Kushal
	 */
	
	public static By objDownloadQualityOptions(String Quality) {
		return By.xpath("//*[@id='radioButton_downlaodQuality' and @text='"+Quality+"']");
	}
	public static By objAlwaysAskCheckBox = By.xpath("//*[@id='checkBox_rememberThis']");
	
	public static By objDownloadProgressIcon = By.xpath("//*[@id='circularProgressbar']");
}
