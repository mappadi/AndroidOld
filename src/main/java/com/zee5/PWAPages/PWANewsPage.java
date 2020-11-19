package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWANewsPage {

	// Right on News
	public static By objRight = By
			.xpath("//div[@class='slick-slider slick-initialized']//*[contains(@class,'slick-arrow slick-next')]");

	// Left on News
	public static By objLeft = By
			.xpath("//div[@class='slick-slider slick-initialized']//*[contains(@class,'slick-arrow slick-prev')]");

	// Content Title on carousel
	public static By objContTitleOnCarousel = By.xpath("//div[@class=\"descWrap\"]/child::h2[@class=\"legendTitle \"]");

//		SANITY YASHASHWINI NEWS MODULE
	public static By objContent = By.xpath("(//div[@class='content'])[2]");

	public static By objLive = By.xpath("//div[@class='playkit-live-tag']");

	public static By objLiveNews = By.xpath("(//div[@class='titleLink'])[1]");
	public static By objVideoOnDemand = By.xpath("(//div[@class='titleLink'])[2]");

	public static By objViewAll = By.xpath("(//div[@class='arrow iconInitialLoad-ic_viewall noSelect'])[1]");
	public static By objViewAllWrap = By.xpath("//div[@class='viewAllWrap']");

	// Verifying different Feature on player screen

	public static By objFullScreen = By.xpath("//i[@class='playkit-icon playkit-icon-maximize']");
	public static By objNonLiveNews = By.xpath("(//div[@class='content'])[6]");
	public static By objSeekbar = By.xpath("//div[@class='playkit-progress-bar']");
	public static By ContentPlayer = By.xpath("//div[@id='player-placeholder']");

	public static By objLiveNewsCard = By.xpath("//div[@data-minutelytitle]");
	public static By objLiveNewsConsumptionsTitle = By.xpath("//div[@class='channelConsumptionMetaDiv']//h1");
}
