package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAVodafonePlayPage {

	
	//VodafonePlay Search Button
	public static By searchBtn = By.xpath("(//*[@alt='mobile_search'])[1]");
	
	//VodafonePlay Search TextField
	public static By searchTextField = By.xpath("(//*[@id='mainSearch'])");
	
	//VodafonePlay Searched Data
	public static By searchedData(String searchData)
	{
		return By.xpath("//span[contains(text(),'"+searchData+"')]");
	}
	
	//VodafonePlay OTP Popup Button
	public static By OTPPopupBtn = By.xpath("//button[contains(text(),'Get OTP')]");
	
	//VodafonePlay OTP Popup MobileNumber Field
	public static By OTPPopupMobileNumberField = By.xpath("//*[contains(@placeholder,'Enter Vodafone mobile number')]");
	
	
	//ZEE5PWA
	//ConsumptionPage Title
	public static By consumptionPageTitle = By.xpath("//div[contains(@class,'consumptionMetaDiv')]/h1");
	
	//Hamburger Btn
	public static By HamburgerBtn = By.xpath("//button[contains(text(),'Open Menu')]");
	
	//UserProfile Name
	public static By userProfileName = By.xpath("//*[contains(@class,'userEmail')]//preceding-sibling::div");
	
	//Popup Close button
	public static By popupCloseBtn = By.xpath("//*[contains(@class,'noSelect closePupup iconInitialLoad-ic_close')]");
	
	//User Phone Number Data
	public static By userPhoneNumber = By.xpath("//*[contains(@class,'userId')]");
	
	//HomePage Highlighted Tab
	public static By highlightedTab = By.xpath("(//*[contains(@class,'noSelect active')])[1]");
	
	//MySubscription
	public static By MySubscription = By.xpath("//div[.='My Subscription']");

	
}
