package com.zee5Pages;

import org.openqa.selenium.By;

public class DownloadPage {

//	Download Icon
	public static By objDownload = By.xpath("//*[@id='download_progress']");
	
//	Download Option Pop Up
	public static By objDownloadOptions = By.xpath("//*[@id='player_dialog_head_text']");
	
//	Download Quality Options
	public static By objDownloadQualityList(int OptionsIndex) {
		return By.xpath("(//*[@class='android.widget.RadioButton'])["+OptionsIndex+"]");
	}
	
//	Download Quality Text
	public static By objDownloadText(int IndexofQualityText) {
		return By.xpath("//*[contains(@id,'_text_size')]["+IndexofQualityText+"]");
	}
	
//  Download Make this my default Setting Text
	public static By objDefaultTxt = By.xpath("//*[@id='download_dialog_check_box']");
	
//	Download Cancel Button
	public static By objCancel = By.xpath("//*[@id='player_dialog_cancel_text']");
	
//	Download OK Button
	public static By objOkBtn = By.xpath("//*[@id='player_dialog_ok_text']");
	
//	Save File To
	public static By objSaveFileToTxt = By.xpath("//*[@id='player_dialog_head_text']");
	
//	Internal Memory 
	public static By objInternalMemoryCheckBox = By.xpath("//*[@id='dialog_radio_button']");
	
	public static By objDownloadList = By.xpath("//*[@id='download_list_shows_layout']");
	public static By objDownloaExpandIcon= By.xpath("//*[@id='download_shows_expand_collapse_icon']");

	public static By objDownloadedVideoCard= By.xpath("	//*[@id='download_card_layout']");
		
	public static By objDownloadedVideoTitle = By.xpath("//*[@id='download_movies_title']");
	
	public static By objSelectDownloadedVideo = By.xpath("//*[@id='download_movies_selector']");

	public static By objSelectDownloadDeleteBtn = By.xpath("//*[@id='download_delete_text']");
	
	public static By objDownloadIcon = By.xpath("//*[@id='download_progress']");
	
	
	public static By objDownloadPopUp = By.xpath("//*[@id='dialog_layout']");
	
	public static By objDownoadQualitytxt1 = By.xpath("(//*[@class='android.widget.RadioButton'])[1]");
	public static By objDownoadQualitytxt2 = By.xpath("(//*[@class='android.widget.RadioButton'])[2]");

	
	public static By objDownoadQualitytxt3 = By.xpath("(//*[@class='android.widget.RadioButton'])[3]");

	
	public static By objDownoadQualitytxt4 = By.xpath("(//*[@class='android.widget.RadioButton'])[4]");

	
	
	

	
	
	
}
