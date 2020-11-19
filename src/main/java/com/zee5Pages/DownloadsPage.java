package com.zee5Pages;

import org.openqa.selenium.By;

public class DownloadsPage {

//	Download Text
	public static By objDownloadsTxt = By.xpath("//*[@id='title']");
	
//	Downloads Tab Names
	public static By objTabNames(String TabName) {
		return By.xpath("//*[@text='"+TabName+"']");
	}
	
//	Download Image
	public static By objDownloadImage = By.xpath("//*[@id='iconTextView_download']");
	
//	Look for this icon text
	public static By objLookForThisIconTxt = By.xpath("//*[@id='iconTextView_download']//following-sibling::*[@class='android.widget.TextView']");
	
//	Browse to download button
	public static By objBrowseToDownloadBtn = By.xpath("//*[@id='btn_browse_to_download']");
	
//	Hide button
	public static By objHideBtn = By.xpath("//*[@id='hide_button']");
	
//	Download video quality
	public static By objDownloadVideoQualityTxt = By.xpath("//*[@id='popup_title']");
	
//	Download video quality radio button
	public static By objDownloadVideoQualityRadioBtn = By.xpath("//*[@id='radioButton_downlaodQuality']");
	
//	Data Usage Text
	public static By objDataUsageTxt = By.xpath("//*[@id='tv_message']");
	
//	Label and CheckBox Remember This
	public static By objAlwaysAskQuaityCheckAndTxt = By.xpath("//*[@id='label_rememberThis']");
	
//	Start Download Button
	public static By objStartDownloadBtn = By.xpath("//*[@id='bottomSheetDialogStartDownloadBtn']");
	
//	Download Video quality PopUplistView
	public static By objPopUpListView = By.xpath("//*[@id='popuplistView']");
	
//	Number of videos in downloads
	public static By objVideos = By.xpath("//*[@id='img_show']");
	
//	Title of an Content
	public static By objTitleOfAnContent = By.xpath("//*[@id='tv_title']");
	
//	Episode title
	public static By objEpsodeTitle = By.xpath("//*[@id='tv_episode']");
	
//	File size
	public static By objFileSize = By.xpath("//*[@id='tv_size']");
	
//	Episode by using title of content
	public static By objEpisodeByTitle(String ContentTitle) {
		return By.xpath("//*[@text='"+ContentTitle+"' ]//ancestor::*[@id='rl_show_details']//following::*[@id='tv_episode']");
	}
	
//  Right Arrow Button
	public static By objRightArrowBtn = By.xpath("//*[@id='img_right_arrow']");
	
//	Image status CheckBox
	public static By objStatusCheckBox = By.xpath("//*[@id='img_state']");
	
//	Download More Episode Button
	public static By objDownloadMoreEpisodeBtn = By.xpath("//*[@id='btn_download_more_episode']");
	
//	Image close Button
	public static By objCloseButton =  By.xpath("//*[@id='img_close']");
	
//	Play icon
	public static By objPlayIcon = By.xpath("//*[@id='tvPlay']");
	
//	Delete Icon
	public static By objDeleteIcon = By.xpath("//*[@id='tvDeleteDownload']");
	
//	Play/Delete PopUp
	public static By objPlayOrDeletePopUp = By.xpath("//*[@id='tvPlay']//ancestor::*[@class='android.widget.LinearLayout']");
	
//	New Episode Tag
	public static By objNewEpisodeTag = By.xpath("//*[@id='tv_new_epiosode']");
	
//	New Episode Download Now Button
	public static By objNewEpisodeDownloadNowBtn = By.xpath("//*[@id='tv_download_Now']");
}
