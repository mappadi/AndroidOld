package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDNewsPage {
	
	public static By objListingScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/title']");
	
	public static By objThumbNailImg = By.xpath("(//*[@id='item_image' and @resource-id='com.graymatrix.did:id/item_image' and @index=1])[1]");
	
	public static By objConsumptionScreenTitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_primary_text']");
	
	public static By objStartTime = By.xpath("//*[@resource-id='com.graymatrix.did:id/positionText1']");
	
	public static By objEndTime = By.xpath("//*[@resource-id='com.graymatrix.did:id/durationText1']");
	
	public static By objMetaData = By.xpath("(//*[@id='movieInfoLl']/*[@text])");
	
	public static By objNewsContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[2]");
	
	public static By objNewsContentCardTitle = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[2]");
	
	public static By objLiveNewsTray = By.xpath("//*[@text='Live News Channels']");
	
	public static By objTopStoriesTray = By.xpath("//*[@text='Top Stories']");
	
	public static By objTodaysHeadlinesTray =By.xpath("//*[contains(@text,\"Today's Headlines\")]");
	
	public static By objCarouselCollectionContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/indicator']/child::*)[1]");
	
	public static By objEntertainmentNewsTray = By.xpath("//*[@text=' Entertainment News']");
	
	public static By objRightArrowBtn = By.xpath("(//*[@resource-id='com.graymatrix.did:id/header_arrow'])[2]");
	
	public static By objTrayHeader = By.xpath("//*[@id='item_primary_text']");
	
	public static By objThumbnailImg1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[10]");
	
	public static By objNextContentImg = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_image']");

}
