package com.zee5Pages;

import org.openqa.selenium.By;

public class HomePage {

//	Home Screen
	
//	Zee5 Logo
	public static By objZee5Logo = By.xpath("//*[@id='logo']");
	
//	Cast icon 
	public static By objCastIcon = By.xpath(""); //Need unique identifier
	
//	Search Icon
	public static By objSearchIcon = By.xpath(""); //Need unique identifier
	
//	Navigation using tabs
	public static By tabNavigation(String tabName) {
		return By.xpath("//*[@class='android.widget.RelativeLayout']//*[@text='"+tabName+"']");
	}
	
//  Masthead Ad
	public static By objMastheadAd = By.xpath("//*[@id='adTag']");
	
//	Masthead carousel 
	public static By objMastheadCarousel = By.xpath("(//*[@id='item_image'])[1]");
	
//	Masthead title
	public static By objMastheadTitle = By.xpath("(//*[@id='item_primary_text'])[1]");
	
//	Masthead Play button
	public static By objMastheadPlayButton = By.xpath("//*[@id='playImage']");
	
//	Masthead Premium tag
	public static By objMastheadPremiumTag = By.xpath("//*[@id='getPremiumButton']");
	
	
//	Masthead Indicator icon
	public static By objMastheadIndicator = By.xpath("//*[@id='indicator']");
	
//	Bottom tab bar
	public static By objBottomBarTitle = By.xpath("//*[@id='bb_bottom_bar_title']");
	
//	Bottom tab bar
	public static By objBottomBarTitleNav(String tabName) {
		return By.xpath("//*[@id='bb_bottom_bar_title' and @text='"+tabName+"']");
	}
	
//	ContainerArrowButton
	public static By objContainerArrowButton(String ContainerName) {
		return By.xpath("//*[@id='header_arrow' and (./preceding-sibling::* | ./following-sibling::*)[@text='"+ContainerName+"']]");
	}
	
	// Content Title on carousel
	public static By objContTitleOnCarousel = By.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle')]");
	
}
