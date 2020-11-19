package com.zee5Pages;

import org.openqa.selenium.By;

public class SettingsPage {

	//Settings Page Title
	public static By objSettingsPageTitle = By.xpath("//*[@text='Settings']");
	
	//Video Streaming
	public static By objVideoStreamingOption = By.xpath("//*[@text='Video Streaming']");
	//Quality option
	public static By objVideoQualityOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/videoQuality']");
	//Selected quality option(ex:Auto,Best,High etc)
	public static By objSelectedVideoQualityOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/qualityPixels']");
	//video quality screen text
	public static By objSelectVideoQualityText = By.xpath("//*[@text='Select Download Video Quality']");
	//'Best' Video quality option
	public static By objBestVideoQualityOption = By.xpath("//*[contains(text(),'Best')]");
	//'High' Video quality option
	public static By objHighVideoQualityOption = By.xpath("//*[contains(text(),'High')]");
	//'Medium' Video quality option
	public static By objMediumVideoQualityOption = By.xpath("//*[contains(text(),'Medium')]");
	//'Low' Video quality option
	public static By objLowVideoQualityOption = By.xpath("//*[contains(text(),'Low')]");
	//'Auto' Video quality option
	public static By objAutoVideoQualityOption = By.xpath("//*[contains(text(),'Auto')]");
	//Cancel Button
	public static By objSelectVideoPopUpScreenCancelButton = By.xpath("//*[@text='D']");
	
	
	//'Stream over WiFi only' option
	public static By objStreamOverWiFiOnlyOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/videoOverWifi']");
	//'Stream over WiFi only' switch
	public static By objStreamOverWiFiOnlySwitch = By.xpath("//*[@resource-id='com.graymatrix.did:id/videoOverWifiSwitch']");
	
	//Autoplay option
	public static By objAutoPlayOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/autoPlayLabel']");
	//Autoplay Switch
	public static By objAutoPlaySwitch= By.xpath("//*[@resource-id='com.graymatrix.did:id/autoPlaySwitch']");
	
	//'Downloads' option
	public static By objDownloadsOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/downloadTitle']");
	//Download Quality option
	public static By objDownloadQualityOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/downloadQuality']");
	// Selected download quality option(ex:High, Low, Ask me later etc)
	public static By objSelectedDownloadQualityOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/downloadLabel']");
    //Download video quality screen title
	public static By objSelectDownloadVideoQualityText = By.xpath("//*[@text='Select Download Video Quality']");
	//'High' Download option
	public static By objHighDownloadQualityOption = By.xpath("//*[contains(text(),'High')]");
	//'Medium' Download option
	public static By objMediumDownloadQualityOption = By.xpath("//*[contains(text(),'Medium')]");
	//'Low' Download option
	public static By objLowDownloadQualityOption = By.xpath("//*[contains(text(),'Low')]");
	//'Ask each time' Download option
	public static By objAskEachTimeOption = By.xpath("//*[@text='Ask Each Time']");
	
	public static By objSelectDownloadVideoPopUpScreenCancelButton = By.xpath("//*[@text='D']");
	
	
	
	public static By objDownloadOverWiFiOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/downloadOverWifi']");
	public static By objDownloadOverWiFiSwitch= By.xpath("//*[@resource-id='com.graymatrix.did:id/downloadOverWifiSwitch']");
	
	
	public static By objLanguageOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/languageTitle']");
	public static By objDisplaylanguageOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/displayLanguage']");
	public static By objSelectedDisplaylanguage= By.xpath("//*[@resource-id='com.graymatrix.did:id/displayLanguageValue']");
	public static By objChooseDisplaylanguageText = By.xpath("//*[@resource-id='com.graymatrix.did:id/dl_screen_title']");
	public static By objLanguageContent = By.xpath("//*[@resource-id='com.graymatrix.did:id/display_language_content']");
	public static By objLanguageSelectionMark = By.xpath("//*[@resource-id='com.graymatrix.did:id/selectionImage']");
	public static By objContinueButton = By.xpath("//*[@text='Continue']");
	
	
	
	public static By objContentlanguageOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/contentLanguage']");
	public static By objSelectedContentlanguage = By.xpath("//*[@resource-id='com.graymatrix.did:id/contentLanguageValue']");
	
	
	public static By objParentalControlOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/parentalControl']");
	
	
	public static By objAuthenticateDeviceOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/authenticateDevice']");
	
	
	public static By objSearchHistroyOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/searchHistoryLabel']");
	public static By objClear = By.xpath("//*[@text='Clear']");
	
	public static By objResetSettingToDefaultOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/resetSettingLabel']");
	public static By objSelectedResetSettingOption = By.xpath("//*[@text='Default Setting']");
	
	
	
	
	
	
	
}
