package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWALanguageSettingsPage {
	
	//Selected language
	public static By objSelectedLang = By.xpath("//div[@class='checkboxWrap checkedHighlight']");
	public static By objSelectedLang(int index) {
		return By.xpath("(//div[@class='checkboxWrap checkedHighlight'])["+index+"]");
	}
	//English language 
	public static By objEnglishLang = By.xpath("//label[@for=\"select_en\"]");
	
	//Language by name
	public static By objLanguage(String text) {
		return By.xpath("//span[@class='commonName'][contains(text(),'"+text+"')]");
	}
	//tray header for selected display language
	public static By objTrayHeader  = By.xpath("(//*[contains(@class,\"trayHeader\")]//h2)[1]");
	
	//Cancel button
	public static By objCancelBtn = By.xpath("//div[@class='popupBtn accentBtn noSelect']");
	
	public static By objEnglishLangInDLPopUp=By.xpath("//span[@class='commonName' and text()='English']");
	
	//Apply button
	public static By objApplyBtn = By.xpath("//div[@class='popupBtn noSelect']");
	
	//Apply button on Content display page if no content language is selected
	public static By objDisabledApplyButton = By.xpath("//div[contains(@class,'popupBtn disable noSelect')]");
	
	//All languages 
	public static By objAllLanguage = By.xpath("//span[@class='commonName']");
	public static By objAllLangByindex(int index) {
		return By.xpath("(//span[@class='commonName'])["+index+"]");
	}
			
	//Language pop up overlay
	public static By objLanguagePopupOverlay = By.xpath("//div[contains(@class,'contentWrapouter')]");
	public static By objLanguagePopupOverlay1 = By.xpath("//div[contains(@class,'contentWrapouter')]");

	public static By objNonSelectedEng = By.xpath("//div[@class='checkboxText']//span[@class='commonName' and .='English']");
	public static By objNonSelectedHin = By.xpath("//div[@class='checkboxText']//span[@class='commonName' and .='Hindi']");
	public static By objNonSelectedKan = By.xpath("//div[@class='checkboxText']//span[@class='commonName' and .='Kannada']");
}
