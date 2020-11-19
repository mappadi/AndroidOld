package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAZee5OriginalPage {

	// Zee5OriginalTab
	public static By objZee5OriginalTab = By.xpath("//div[contains(text(),'ZEE5 Originals')]");

	// GetPremiumBadge
	public static By objGetPremiumBadge = By.xpath("//div[@class='noSelect premiumBtn ' and text()='Get premium']");

	// MastheadTitle
	public static By objMastheadTitle(String str) {
		return By.xpath("//h2[text()='" + str + "']");
	}

	// MastheadPlayIcon
	public static By objMastheadPlayIcon(String MastheadTitle) {
		return By.xpath("//h2[text()='" + MastheadTitle + "']/parent::*/following-sibling::*/child::*/DIV");
	}

	// TrayTitle
	public static By objTrayTitle(String TrayTitle) {
		return By.xpath("//div[@class='titleLink' and text()='" + TrayTitle + "']");
	}

	// ViewAllBtn
	public static By objViewAllBtn(String TrayTitle) {
		return By.xpath("//div[contains(text(),'" + TrayTitle + "')]/parent::*//following-sibling::*");
	}

	// TrayAsset
	public static By objTrayAsset(String Assetitle) {
		return By.xpath("//img[@title='" + Assetitle + "']/ancestor::div[@class='noSelect clickWrapper']");
	}

//	======================================================================================================
//	BINDU
	// Highlighted Tab

	public static By objHighlightedTab(String tabname) {
		return By.xpath(
				"//div[@class='navMenuWrapper ']/child::ul/child::li/child::div[@class='noSelect active' and text()='"
						+ tabname + "']");
	}

	// Scroll to Top of screen
	public static By objNavigateToTop = By.xpath("//div[@class='iconNavi-ic_arrow_back']");

	// Premium card
	public static By objPremiumCard = By.xpath("(//div[@class='cardPremiumContent']/preceding-sibling::*/child::*/child::img)[1]");

	// Premium Tag
	public static By objPremiumTag = By.xpath("(//div[@class='cardPremiunIconContainer '])[1]");

	// First tray
	public static By objTrayTitle1 = By.xpath("(//div[@class='titleLink'])[1]");

	// Object of carousel content
	public static By objCarouselContent = By.xpath("//*[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle ')]");

	
	public static By objWEBMastheadCarousel = By.xpath("//div[@class='slick-slide slick-active slick-center slick-current']");
	
	public static By objWhatWonderingPopUp = By.xpath("//div[(contains(@style,'display: block'))]//child::div[contains(@id,'adoric_smartbox_')]");

	public static By objWhatWonderingPopUpCloseIcon = By.xpath("//div[(contains(@style,'display: block'))]//child::div[@aria-label='close']");
	
	public static By objCarousel = By.xpath("(//div[@class='slick-list'])[1]");

	public static By objPremiumContentCard =By.xpath("(//div[@class='cardPremiumContent']/preceding-sibling::*/child::*/child::img)[1]");

}
