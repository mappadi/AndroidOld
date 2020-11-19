package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAMoviesPage {

	// Main Carousel items
	public static By objHeroCarouselItems(int index) {
		return By.xpath("(//*[contains(@class,'carousel')])[" + index + "]");
	}

	// Title name in Hero Carousel (For all the titles)
	public static By objTitleNameHeroCarosel(int index) {
		return By.xpath("(//*[contains(@class,'legendTitle')])[" + index + "]");
	}

	// Play button in Hero Carousel
	public static By objPlayBtnHeroCarousel(int index) {
		return By.xpath("(//*[contains(@class,'playBtn')])[" + index + "]");
	}

	// Get Premium text in Hero Carousel
	public static By objGetPremiumTxt = By.xpath("//*[contains(text(),'Get premium')]");

	// Tray Title name
	public static By objTrayTitleName(String str) {
		return By.xpath("//*[contains(text(),'" + str + "')]");
	}

	// Arrow button
	public static By objTrayTitleArrowBtn(String str) {
		return By.xpath("//div[contains(text(),'" + str + "')]/parent::*//following-sibling::*");
	}

	// Tray title Content cards
	public static By objContentCard(String str) {
		return By.xpath("//div[contains(text(),'" + str
				+ "')]/parent::*/parent::*/parent::*//parent::*[contains(@class,'content')]");
	}

	// Tray title Content cards
	public static By objContentCard(String str, int index) {
		return By.xpath("(//div[contains(text(),'" + str
				+ "')]/parent::*/parent::*/parent::*//parent::*[contains(@class,'content')])[" + index + "]");
	}
	
	public static By objMoveTab = By.xpath("//div[.='Movies']");
	public static By objMovieCard = By.xpath("//div[contains(@class,'tray-container') and not(.//div[@class='cardPremiumContent'])]//figure//img");

//	=====================================================================================================
	
	public static By objNotNow = By.xpath(".//*[contains(@text,'Not Now')]");
	
//	SUSHMA
	public static By objPremiumContentCard =By.xpath("(//div[@class='content']//parent::figure[@class='placeHolderRatio2X3'])[1]");
	
	@SuppressWarnings("unused")
	public static By TextToXpath(String text) throws Exception {
		String xpath = "";
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + text + "')]");
	}
	
	public static By objContentCard(int index) {
		return By.xpath("(//div[contains(@class,'trayHeader')]/following-sibling::*//div[contains(@class,'content')])[" + index + "]");
	}

}
