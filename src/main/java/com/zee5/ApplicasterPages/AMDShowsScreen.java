package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDShowsScreen {
//	 public static By objTrays = By.xpath("(//*[@id='recyclerView']//*[@id='header_primary_text'])");
	
	public static By objSubscribeIcon = By.xpath("//*[@id='toolbar_subscribe_text']");
	
	 public static By objTrays(int i) {
			return By.xpath("(//*[@id='recyclerView']//*[@id='header_primary_text'])["+i+"]");
		}

	 public static By objContentImages(int i) {
			return By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])["+i+"]");
		}
	 
	 public static By objPaginationDots = By.xpath("(//*[@id='indicator']/*[@class='android.widget.ImageView'])[1]");
	 
	 public static By objcontentCard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[4]");
	 
	 public static By objPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/controller']");
	 
	 public static By objCarouselContentCard = By.xpath("//*[@resource-id='com.graymatrix.did:id/item_image']");
	 
	 public static By objPremiumContentCard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/special_image_1'])[1]");
	 
	 public static By objWatchTrailer = By.xpath("//*[@id='watchtrailer_image']");
	 
	 public static By objPlayIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_play']");
	 
	 public static By objPauseIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_pause']");
	 
	 public static By objBeforeTVContent = By.xpath("(//*[@id='recyclerView' and ./parent::*[./parent::*[@id='recyclerView']]]/*/*/*[@class='android.view.View' and ./parent::*[@id='horizontal_list_7_parent']])[1]");
	 
	 public static By objPlayerPageTitle = By.xpath("//*[@id='item_primary_text']");
	 
	 public static By objWatchNextTray = By.xpath("//*[@resource-id='com.graymatrix.did:id/header_primary_text' and contains(text(),'Watch Next')]");
	 
	 public static By objLoginLink = By.xpath("//*[@id='login_button']");
	 
	 public static By objProgressBar = By.xpath("//*[@resource-id='com.graymatrix.did:id/progress']");
	 
	 public static By objWatchLeftTime = By.xpath("//*[@resource-id='com.graymatrix.did:id/durationText1']");
	 
	 public static By objContinueWatching = By.xpath("//*[@resource-id='com.graymatrix.did:id/header_primary_text' and contains(text(),'Continue Watching')]");
	 
	 public static By objContinueWatchingContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_image'])[2]");
	 
	 public static By objContinueWatingCloseIcon = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_remove'])[1]");
	 
	 public static By objBeforeTVContent1 = By.xpath("(//*[@id='item_image' and @resource-id='com.graymatrix.did:id/item_image' and @index=1])[2]");
	 
	 public static By objSubscribeNowlink = By.xpath("//*[@text='Subscribe Now']");

	 public static By objContinueWatchingTray = By.xpath("//*[@text='Continue Watching']");

	 public static By objTrendingShowsTray = By.xpath("//*[@text='Trending Shows']");
	 	 
	 public static By objMastheadAD = By.xpath("//*[@resource-id='com.graymatrix.did:id/adTag' and @text='AD']");

	 public static By objLeftWatchTime = By.xpath("(//*[@resource-id='com.graymatrix.did:id/time_progress'])[1]");
}
