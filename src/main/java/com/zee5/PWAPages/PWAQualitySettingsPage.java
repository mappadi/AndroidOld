package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAQualitySettingsPage {
	
	//All qualities present in menu
	public static By objAllQualities  = By.xpath("//div[@class='subMenu']");
	//Selected Quality 
	public static By objSelectedQuality(int index) {
		return By.xpath("(//div[@class='subMenu']//parent::*)["+index+"]");
	}
	//Individual Qualities	
	public static By objIndividualQuality(int index) {
		return By.xpath("(//div[@class='subMenu'])["+index+"]");
	}
	//Quality text in settings menu
	public static By objQualityText = By.xpath("//*[contains(@class,'mainMenu')]//preceding-sibling::span[text()=\"Quality\"]//following-sibling::*");
}
