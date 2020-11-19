package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class LiveTVPage {
	
	String xPathStr1 = "//div[contains(@class,'noSelect active')]";
	
	public static By objLiveTVMenu = By.xpath("//div[contains(@class,'navMenuWrapper')]//div[@class='noSelect active'][contains(text(),'Live TV')]");
	
	public static By objLiveTVToggleActive = By.xpath("//div[@class='toggleGroup']//*[contains(@class,'noSelect active')][contains(text(),'Live TV')]");
	public static By objLiveTVToggleInactive = By.xpath("//div[contains(@class,'toggleGroup')]//div[contains(@class,'noSelect')][contains(text(),'Live TV')]");
	
	public static By objChannelGuideToggle = By.xpath("//div[contains(text(),'Channel Guide')]");
	
	public static By objLiveTVTitle = By.xpath("//h1[contains(text(),'Live TV')]");
	
	//Filter Options for LiveTV - All,FREE Channels,English Entertainment,Movie,English News,Music,Lifestyle,Devotional
	public static By objFilterOption(String label) {
		return By.xpath("//span[contains(text(),'"+label+"')]");
	}
	
	// Filter Options for Entertainment 
	public static By objEntertainmentFiltrOptn = By.xpath("//div[contains(@class,'chipTray staticChipTray')]//div[4]//span[1]");
	
	// Filter Options for News 
	public static By objNewsFiltrOptn = By.xpath("//div[contains(@class,'chipTray staticChipTray')]//div[6]//span[1]");
	
	// Filter Options for Kannada
	public static By objKannadaFiltrOptn = By.xpath("//div[contains(@class,'noSelect filterOptions')]//span[contains(text(),'Kannada')]");
	
	// Language filter button
	public static By objLanguageFiltrBtn = By.xpath("//button[contains(@class,'noSelect filterCompButton noSelect activeFilter iconInitialLoad-ic_filter')]");
	
	// Close button on Language selection PopUp
	public static By objCloseLanguagePopuUpBtn = By.xpath("//div[contains(@class,'noSelect closePupup iconInitialLoad-ic_close')]");

	// Object to select Language option from PopUp
	public static By objSelectLanguageOption(String lang) {
		return By.xpath("//div[@class='language noSelect'][contains(text(),'"+lang+"')]");
		
	}
	
	// Object to Check whether Language is already selected in the PopUp
	public static By objCheckSelectedLanguage(String lang) {
		return By.xpath("//div[@class='selectedLanguage language noSelect'][contains(text(),'"+lang+"')]");
	}
	
	// No of Language selected description
	public static By objNoOfLangSelectedText = By.xpath("//div[@class='selectedLanguageText']");
	
	// Reset button on Language PopUp screen
	public static By objResetBtn = By.xpath("//div[@class='popupBtn accentBtn noSelect']");
	// Apply button on Language PopUp screen
	public static By objApplyBtn = By.xpath("//div[@class='popupBtn noSelect']");
	
	// Tray Title 
	public static By objTrayTitleLabel(String title) {
		return By.xpath("//h2[text()='"+title+"']");
	}
	
	// Channel Names above the card
	public static By objCardAboveChannelName(String channelName) {
		return By.xpath("//div[@class='cardAboveMeta' and text()='"+channelName+"' and @xpath=1]");
	}
	
	public static By objCardTitleName(String title) {
		return By.xpath("//*[@class='cardTitle']//following::span[text()='"+title+"'][1]");
	}
	
	// Elapsed Meta Time data for the title
	public static By objMetaTime(String title) {
		return By.xpath("//*[contains(text(),'"+title+"')]//following::*[starts-with(text(),'Elapsed')][1]");
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
	
}
