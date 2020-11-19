package com.zee5Pages;

import org.openqa.selenium.By;

public class LanguageUpdatePage {

	public static By objLanguageTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/language_screen_title']");
	
	public static By objDisplayTab = By.xpath("//*[@text='DISPLAY']");

	public static By objContentTab = By.xpath("//*[@text='CONTENT']");
	
	public static By objIndicator = By.xpath("(//*[@resource-id='com.graymatrix.did:id/language_tab_indicator']//parent::*//child::*)[1]");
	
	public static By objLanguageTextEnglish = By.xpath("//*[@resource-id='com.graymatrix.did:id/english_language_text']");
	
	public static By objLanguageTextEnglishGetvalue(int value)
	{
		return By.xpath("(//*[@resource-id='com.graymatrix.did:id/english_language_text'])["+value+"]");
	}
		
	public static By objLanguageCheckbox(int value)
	{
		return By.xpath("(//*[@resource-id='com.graymatrix.did:id/content_language_check_box'])["+value+"]");	
	}
		
	public static By objLanguageRadioBtn(int value)
	{
		return By.xpath("(//*[@resource-id='com.graymatrix.did:id/display_radio_button'])["+value+"]");
	}
	
	public static By objLanguageDoneBtn = By.xpath("//*[@resource-id='com.graymatrix.did:id/update_lang']");
	
}
