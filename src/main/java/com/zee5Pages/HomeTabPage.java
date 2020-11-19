package com.zee5Pages;

import org.openqa.selenium.By;

public class HomeTabPage {

//	Home Tab Icon
	public static By objHomeTabIcon = By.xpath(" //*[@text='HOME' and //*[@id='home_tab_item_image']]");
	
	public static By objSelectThumbnail(int value)
	{
		return By.xpath("(//*[@id='episode_thumbnail'])["+value+"]");	
	}
	
//	MastHead Carousel
	public static By objMastHeadTitle = By.xpath("(//*[@id='image_slider_text'])[1]");
	
//	Tray Name
	public static By objHomeTabTrayTitle() {
		return By.xpath("//*[@text='Top ZEE5 Movies in Kannada']");
	}
	
}
