package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAPremiumPage {

	public static By objPremiumTabHighlighted = By.xpath("//div[text()='Premium' and @class='noSelect active']");
	public static By objPremiumTabNotHighlighted = By.xpath("//div[text()='Premium' and @class='noSelect ']");

	public static By objHeoCarouselCard(String titleName) {
		return By.xpath("//h2[text()='" + titleName + "']/ancestor::div[@aria-hidden='false']");
	}

	public static By objHeroCarouselTitle(String titleName) {

		return By.xpath(
				"//div[@aria-hidden='false']/following::h2[@class='legendTitle ' and text()='" + titleName + "']");

	}

	public static By objGetPremium = By.xpath("//button[text()='Subscribe']");

	public static By objHerocarouselPlayBtn(String titleName) {
		return By.xpath("//div[@aria-hidden='false']/following::h2[text()='" + titleName
				+ "']/parent::div/following-sibling::div/child::div[@class='playIcon']");
	}

	public static By objTrayTitle(String trayTitle) {

		return By.xpath("//div[text()='" + trayTitle + "']");

	}

	public static By objTrayAssetCard(String assetTitle) {

		return By.xpath("//img[@title='" + assetTitle
				+ "']/ancestor::div[@class='noSelect clickWrapper' or @class='clickWrapper']");

	}

	public static By objPremiumSymbol(String assetTitle) {
		return By.xpath("//img[@title='" + assetTitle
				+ "']/ancestor::div[@class='noSelect clickWrapper']/child::figure/following-sibling::div");
	}

	// Premium popup
	public static By objPremiumPopUp = By.xpath("//h2[contains(@class,'popupTitle bigTitle')]//p[text()='Subscribe']");

//	SANITY SUSHMA LIVETV MODULE
	public static By objClosePremiumPopup = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close' or @class='manCloseIcon']");

//	MANASA
	// Premium popup

	public static By objPremiumTab = By.xpath("//div[.='Premium']");

	public static By objMinuteContent = By.xpath("(//video[@class='minute_apv'])[1]");

	public static By objViewAllBtn(String trayTitle) {
		return By.xpath("((//div[.='" + trayTitle + "']//parent::*//parent::*)//div[.='View All'])[1]");
	}

	public static By objPremiumTag = By.xpath("(//div[@class='cardPremiumContent'])[1]");

	public static By objViewAllPageTitle = By.xpath("//h1");

	public static By objSubscribeNowAndGoAdFree = By.xpath("//span[contains(text(),'Subscribe now and Go Ad Free')]");

	public static By objCarouselTitle = By
			.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle')]");

	public static By objShowTitle(String title) {
		return By.xpath("(//*[@text[contains(.,'" + title + "')])[1]");
	}

	public static By objWatchTrailerBtn = By.xpath("//div[@class='noSelect playBtn']");

	public static By objViewAllPage = By.xpath("//div[contains(@class,'viewAllWrap')]");

	public static By objPlayer = By.xpath("//div[@class='playkit-overlay-action']");

	public static By TextToXpath(String text) {
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + text + "')]");
	}

	public static By specificContentisMinuteimage(String str, int i) {
		return By.xpath("(((//a[@class='titleLink'][contains(text(),'" + str
				+ "')]//parent::*//parent::*//parent::*)//div[2]//div[@class='slick-track']//figure//div)[" + i
				+ "])//div[@class='minute-container']");
	}

	public static By specificContentPremiumIcon(String str, int i) {
		return By.xpath("((//a[@class='titleLink'][contains(text(),'" + str
				+ "')]//parent::*//parent::*//parent::*)//div[2]//div[@class='slick-track']//div//div[@class='cardPremiumContent'])["
				+ i + "]");
	}

	public static By objTrayTitle(int i) {
		return By.xpath("(//div[contains(@class,'trayContentWrap')]//div[@class='trayHeader'])[" + i + "]");
	}

	public static By objMastheadCarousel = By
			.xpath("//div[contains(@class,'slick-current')]//div[@class='legend legendWrapper']");

	public static By objNewsMastheadCarousel = By
			.xpath("//div[contains(@class,'slick-active')]//div[@class='carouselMain']");

	public static By objViewAllBtn = By.xpath("(//*[@class='arrow iconInitialLoad-ic_viewall noSelect'])[1]");
	
	public static By objNextArrowBtn = By.xpath("(//button[@class='slick-arrow slick-next'])[2]");
	public static By objPreviousArrowBtn = By.xpath("(//button[@class='slick-arrow slick-prev'])[2]");
	public static By objContentCard = By.xpath("//div[@class='slick-slide slick-active slick-current']");
	public static By objContentCardPlayBtn = By.xpath("//div[@class='noSelect btnIcon playBtnIcon']");
	public static By objContentCardShareBtn = By.xpath("//div[@class='shareCompIcon iconInitialLoad-ic_share']");
	public static By objContentCardWatchlistBtn = By
			.xpath("(//span[@class='noSelect btnIcon iconInitialLoad-ic_add_Watchlist'])[1]");
	public static By objWEBMastheadCarousel = By
			.xpath("//div[@class='slick-slide slick-active slick-center slick-current']");

	public static By obj1stContentInViewAllPage = By
			.xpath("(//div[@class='movieCard card marginRight minutelyUrl zoomCardHover'])[1]");
	
	
	public static By objUpgradeToPremiumCloseBtn = By.xpath("//div[@class='noSelect playBtn']");
}
