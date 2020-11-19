package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PwaNews {
	
	public static By obj_Pwa_LiveNews = By.xpath("//*[@text='Live News']");
	public static By obj_Pwa_Suvarna_Live_News = By.xpath("//*[@title='Suvarna News']");
	public static By obj_Pwa_Live = By.xpath("//*[@text='LIVE']");
	public static By obj_Pwa_Player_Volume_icon = By.xpath("//*[@css='I.playkit-icon.playkit-icon-volume-waves']");
	public static By obj_Pwa_Player_Ads = By.xpath("//*[contains(text(),'Ad :')]");
	public static By obj_Pwa_News_Vod_Rail_Title = By.xpath("//*[@text='India Goes Under Total Lockdown' and @nodeName='DIV']");
	public static By obj_Pwa_News_Vod_Title = By.xpath("//*[@title='COVID-19 lockdown: Moradabad social worker wears coronavirus-themed helmet to create awareness']");

	public static By objViewAll = By.xpath("(//div[@class='arrow iconInitialLoad-ic_viewall noSelect'])[1]");
	public static By objViewAllWrap = By.xpath("//div[@class='viewAllWrap']");
}
