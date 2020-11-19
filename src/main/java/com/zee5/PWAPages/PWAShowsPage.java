package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAShowsPage {

	// Search Btn
	public static By objSearchBtn = By.xpath("//div[contains(@class,'searchBtn')]");

	// Play Btn On carousel
	public static By objPlayBtn = By.xpath("//div[@class=\"playBtn\"]");

	// Play Btn Txt On carousel
	public static By objPlayTxt = By.xpath("//div[@class=\"noSelect premiumBtn\"]");

	// First asset image from first content rail
	public static By objFirstAssetImageFirstRail = By
			.xpath("(//div[@class='slick-list'])[1]//div[@data-index='0']//img");

	// First asset title from first content rail
	public static By objFirstAssetTitleFirstRail = By.xpath("(//div[@class='slick-list'])[1]//div[@data-index='0']//h3[@class='showCardTitle']//span");

	// First asset episode number from first content rail
	public static By objFirstAssetEpisodeFirstRail = By.xpath("((//div[@class='slick-list'])[1]//div[@data-index='0']//div[@class='showDuration']//span)[1]");

	// First asset duration from first content rail
	public static By objFirstAssetDurationFirstRail = By.xpath("((//div[@class='slick-list'])[1]//div[@data-index='0']//div[@class='showDuration']//span)[3]");

	// First asset image from Live TV card rail
	public static By objFirstAssetImageLiveTvCard = By.xpath("//h2[text()='FREE Channels']//parent::*//following-sibling::*//div[@data-index='0']//img");
	// First asset title from Live TV card rail
	public static By objFirstAssetTitleLiveTvCard = By.xpath("//h2[text()='FREE Channels']//parent::*//following-sibling::*//div[@data-index='0']//h3[@class='cardTitle']//a");

	// Share button in Show Details page
	public static By objShareIcon = By.xpath("//div[contains(@class,'shareCompIcon')]");

	
	  // Facebook app for Share in Show Details Page public static By
	  public static By objFacebookApp = By.xpath("//*[@text='Facebook' or @text='News Feed' or @text='Post']");
	 
	// Native Share window
	public static By nativeShareWindow = By.xpath("//*[@class='android.widget.LinearLayout' and @index='3']//*[@class='android.widget.ImageView']");

//	===========================================================================================
//	SANITY 

//	BHAVANA SHOWS MODULE
	// (>) at end of the tray
	public static By objEndOfTray = By.xpath(
			"//body/div[@id='root']/div[contains(@class,'appContainer')]/div[contains(@class,'routerContainer')]/div[contains(@class,'pageContentWrapper')]/div[contains(@class,'pageLanding')]/div[contains(@class,'page-container')]/div[10]/div[1]/div[1]/div[1]");

	// Content card in Play and Win
	public static By objContentCardInPlayandWin = By
			.xpath("//div[@data-minutelytitle='ZEE5 Super Family League - Play & Win']");

	public static By objViewAll = By.xpath(
			"//body/div[@id='root']/div[contains(@class,'appContainer')]/div[contains(@class,'routerContainer')]/div[contains(@class,'pageContentWrapper')]/div[contains(@class,'pageLanding')]/div[contains(@class,'page-container')]/div[1]/div[1]/div[1]/div[1]");

	public static By objViewAllWrap = By.xpath("//div[@class='viewAllWrap']");

	public static By objPlayWin = By.xpath("//div[contains(text(),'Play & Win')]");

//		CONTENT MODULE
	public static By objTwitterPostBtn1 = By.xpath("//*[@text='Tweet' or @text='TWEET']");
	
	public static By objEpisodeTrayinShowdetailPage = By.xpath("//div[@class='AllEpisodesListDiv']");
	public static By objShowdeatilPlayIcon = By.xpath("(//div[@class='showDetailIcon'])[2]");
	public static By objShowDetailEpisodeDropdown = By
			.xpath("(//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[4]");

	public static By objShowDetailEpisodeDropdownValues(int i) {
		return By.xpath(
				"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span["
						+ i + "]");
	}

	public static By objSelectedEpisodeinDropdown = By.xpath(
			"(((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span[@aria-selected='true']");

	public static By objShowDetailNonSelectedEpisodeDropdownValues(int i) {
		return By.xpath(
				"((((//div[@class='AllEpisodesListDiv']//div[@class='allEpisodeSelect']//div)[6])[@aria-expanded='true']//div)//span[@aria-selected='false'])["
						+ i + "]");
	}
	
	
	// First asset image from second content rail
	public static By objSecondAssetImageFirstRail = By.xpath("(//div[@class='slick-list'])[1]//div[@data-index='1']");

	public static By objBackToTopArrow = By.xpath("//div[@class='iconNavi-ic_arrow_back']");
	public static By objTwitterApp = By.xpath("//*[@text='Tweet']");

	public static By objShowsTitle = By.xpath("//*[contains(@class,'bannerTitle')]//h1");

	public static By objWatchLatestCTA = By.xpath("//*[@class='playWrap']//following-sibling::p");

	public static By objLatestepisode = By.xpath("//*[@class='showWrap']//*[@class='iconsWrap latest'] ");

	public static By objPlayforPremium = By.xpath("//*[@class='showDetailIcon']//*[@class='playIcon']");

	public static By objShows = By.xpath("//a[contains(text(),'Shows')]");

	public static By objPlayAndWin = By.xpath("//a[contains(text(),'Play & Win')]");

	public static By objGuessScore = By.xpath("//*[@data-minutelytitle='Guess the score & win']");

	public static By objWatchLatestCTAPlayicon= By.xpath("//div[@class='playWrap']//div[@class='playBtn']");
	
	public static By objTwitterCloseBtn = By.xpath("//*[@class='android.widget.ImageButton' and @content-desc='Navigate up']");
	
	public static By objTwitterDeletePost = By.xpath("//*[@text='DELETE']");
	// POST button in Facebook app
	public static By objFacebookPostBtn = By.xpath("//*[@text='POST' or @text='Post' or @text='SHARE']");

	public static By metainfolist=By.xpath("//div[contains(@class,'metaInfo')]//span");
	
	public static By objThirdContentInTray=By.xpath("//div[@data-index='2']//img");
	
	public static By objFirstContentInTray=By.xpath("//div[@data-index='0']//img");
	public static By objSecondSetEpisodeTray = By.xpath("//div[@class='dropDownWrapper']//span[2]");
	
	public static By objallowCaps = By.xpath("//*[@text='ALLOW']");
	
	public static By objEpisodesSetTray = By.xpath("//div[@class='allEpisodeSelect']");
	
	public static By objEpisodeCard = By.xpath("//div[@data-index='0']//img");
	
	public static By objEpisodeCardTwo = By.xpath("//div[@data-index='1']//img");
}
