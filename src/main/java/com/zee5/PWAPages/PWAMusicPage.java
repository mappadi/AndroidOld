package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAMusicPage {

	// Select tab by text
	public static By objTabName(String tabName) {
		return By.xpath("//div[contains(@class,'noSelect') and contains(text(),'" + tabName + "')]");
	}

//		Check for the premium tag
	public static By objPremiumTag = By.xpath("//*[@class='cardPremiumContent']");

//		Music Highlited
	public static By objMusicHighlited = By.xpath("//div[@class='noSelect active' and //DIV[text()='Music']]");

//	ViewAll Icon for first tray
	public static By objViewAllIcon = By.xpath("(//*[@class='arrow iconInitialLoad-ic_viewall noSelect'])[1]");

//	First tray Title
	public static By objFirstTrayTitle = By.xpath("(.//div[@class='titleLink'])[1]");

//	Carousel title 
	public static By objCarouselTitle = By
			.xpath("//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle')]");

//	Consumption Page Title
	public static By objConsumptionPageTitle = By.xpath(".//div[@class='consumptionMetaDiv']/h1");

//	Close Button getPremium
	public static By objGetPremiumCloseBtn = By.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

//	Selected Language in content language pop-Up
	public static By objContentLanguage = By.xpath(
			"//div[@class='checkboxWrap checkedHighlight']//*[@class='innnerContentWrap']//*[@class='commonName']");

//	Recommended try header
	public static By objRecommendedTrayHeader = By.xpath("//div[@class='recommendRow']//h2[.='Recommended Videos']");

//	Minute content
	public static By objMinuteContent = By.xpath("(//video[@class='minute_apv'])[1]");

//	ViewAll button
	public static By objViewAllBtn(String trayTitle) {
		return By.xpath("((//div[.='" + trayTitle + "']//parent::*//parent::*)//div[.='View All'])[1]");
	}

//	ViewAll page
	public static By objViewAllPage = By.xpath("//div[contains(@class,'viewAllLanding middleContainer')]");

//	Premium tumbnail
	public static By objVideoIsPremiumTumbnail(String str, int i) {
		return By.xpath("((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "]//div[@class='cardPremiumContent']");
	}

//	Video tumbnail
	public static By objVideoTumbnailTitle(String str, int i) {
		return By.xpath("(((//div[@class='trayHeader']//h2[.='" + str
				+ "'])//parent::*//parent::*//div[@class='noSelect clickWrapper' or @class='clickWrapper'])[" + i
				+ "])//figure//img");
	}

//	Close Button of register popUp
	public static By objWEBCloseBtnLoginPopup = By.xpath("//div[@class='manCloseIcon']");

//	Why register popUp
	public static By objWhyRegisterPopUp = By.xpath("//div[.='Why Register?']");

	public static By TextToXpath(String text) {
		return By.xpath("//div[contains(@class,'trayContentWrap')]//*[contains(text(),'" + text + "')]");
	}

//	Maximize window button
	public static By maximizeBtn = By.xpath("//*[contains(@class,'playkit-icon-maximize')]");

//	Minimize window button
	public static By minimizeBtn = By.xpath("//*[contains(@class,'playkit-icon-minimize')]");

	public static By objArrowToNavigateTop = By.xpath(".//*[@class='iconNavi-ic_arrow_back']");

	public static By objPremiumTagContentCard(String str) {
		return By.xpath("((//div[@class='clickWrapper'])//a//img[@title='" + str
				+ "']//parent::*)//parent::*//div[@class='cardPremiumContent']");
	}

	public static By objPremiumTagContentTumbnail(String str) {
		return By.xpath("//div[@class='cardPremiumContent']//parent::*//img[@title='" + str + "']");
	}

	public static By objRecommendedVideos = By.xpath("//div[@class='recommendCol']");
	public static By objMusicTabInSearch = By.xpath("(//*[@id='music'])");

	// Music card from music tray
	public static By objMusicCardInMusicTab = By.xpath("((//div[@class='latestEpisodeTrayWrapper'])[1]//img)[1]");

	// Music card name from music tray
	public static By objMusicCardTitleInMusicTab = By.xpath(
			"(//div[@class='latestEpisodeTrayWrapper'])[1]//div[@data-index='0']//div[@class='episodeMetaWrap']//h3");

	public static By objTrayTitle(String trayTitle) {
		return By.xpath("//a[contains(text(),'" + trayTitle + "')]");
	}

	public static By objFreeMusicContentCard = By.xpath("(//div[@class='bannerPlusTray topTenSongsTray']//img)[1]");
	
	public static By musicTrayContentCard(String trayTitle, int i) {
		return By.xpath("(((((//div[@class='trayHeader']//h2[.='"+trayTitle+"'])//parent::*//parent::*//div[@class='cardCarousel Wrapper'])//div//div[@class='slick-list'])//div)//div//figure)["+i+"]");
	}

	public static By musicTrayContentCards(String trayTitle) {
		return By.xpath("(((((//div[@class='trayHeader']//h2[.='"+trayTitle+"'])//parent::*//parent::*//div[@class='cardCarousel Wrapper'])//div//div[@class='slick-list'])//div)//div//figure)");
	}

	public static By musicTrayContentCardTitle(String trayTitle, int i) {
		return By.xpath("((((((//div[@class='trayHeader']//h2[.='"+trayTitle+"'])//parent::*//parent::*//div[@class='cardCarousel Wrapper'])//div//div[@class='slick-list'])//div)//div//figure)//a//img[@title])["+i+"]");
	}
	
	public static By musicTrayContentCardWatchListBtn(String trayTitle, int i) {
		return By.xpath("(((((((//div[@class='trayHeader']//h2[.='"+trayTitle+"'])//parent::*//parent::*//div[@class='cardCarousel Wrapper'])//div//div[@class='slick-list'])//div)//div//figure)["+i+"])//parent::*//parent::*)//div[@class='cardPopupWrap']//div[@class='popupButton']//span[@title='Watchlist']");
	}
}
