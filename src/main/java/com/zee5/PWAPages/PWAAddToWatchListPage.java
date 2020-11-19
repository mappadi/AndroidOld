package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAAddToWatchListPage {

	// My Account tab

	public static By objMyAccount = By.xpath("//h5[contains(text(),'My Account')]");
	public static By objtoastMessage = By.xpath("//div[@text='Added to Watchlist']");
	public static By objMyWatchList = By.xpath("//a[contains(text(),'My Watchlist')]");
	public static By objMoviesTab = By.xpath("//div[@id='movies']");
	public static By objContentsInWatchList = By.xpath("//div[@class='clickWrapper']");
	public static By objRemoveContentsInWatchList = By.xpath("//span[contains(text(),'Remove All')]");

	public static By objWatchListed(String contentTitle) {
		return By.xpath("//a[text()=\"" + contentTitle + "\"]");
	}

	public static By objTooltip(String tooltipText) {
		return By.xpath("//img[@alt='" + tooltipText + "']");
	}

//		VINAY 

	// My Account tab
	public static By objEpisodeTab = By.xpath("//div[@id='episodes']");
	public static By objVideoTab = By.xpath("//div[@id='videos']");
	public static By objMyWatchlistHeader = By.xpath("//h1[contains(text(),'My Watchlist')]");

	public static By objCancelBtn = By.xpath("//span[@class='noSelect iconInitialLoad-ic_close']");

	public static By objCancelBtn(int index) {
		return By.xpath("(//span[@class='noSelect iconInitialLoad-ic_close'])[" + index + "]");
	}

	public static By objFirstContentInWatchlist = By.xpath("(//h3[contains(@class,'cardTitle overflowEllipsis')]//a)[1]");
	public static By objContentName = By.xpath("//h1[contains(text(),\"\")]");
	public static By objCompleteProfilePopUp = By.xpath("//div[contains(@class,'formHeader')]");
	public static By objClosePopup = By.xpath("//div[contains(@class,'manCloseIcon')]");
	public static By objPopUpForVideoClip = By.xpath("//div[@class='popupDesc']");
	public static By objCloseBtnForVideoClipPopup = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");
	public static By objTotalContents = By.xpath("(//h3[contains(@class,'cardTitle overflowEllipsis')])");

	public static By objWatchlistedItem(String title) {
		return By.xpath("//*[contains(@class,'cardTitle')]//*[text()=\"" + title + "\"]");
	}

	public static By objWatchlistedItemCancel(String title) {
		return By.xpath("//*[contains(@class,'cardTitle')]//*[text()=\"" + title
				+ "\"]//parent::*//parent::div[@class='metaData']//following-sibling::span[contains(@class,'close')]");
	}

	public static By objEmptyWatchlistPage = By.xpath("//div[contains(text(),'Nothing to watch')]");

	public static By objWatchlistedItems = By.xpath("//*[contains(@class,'cardTitle')]//*");
}
