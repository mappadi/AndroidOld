package com.zee5Pages;

import org.openqa.selenium.By;

public class WatchListPage {

	
//	Screen Title
	public static By objScreenTitleTxt = By.xpath("//*[@id='screen_title']");
	
//	Icon Back Arrow
	public static By objBackArraowIcon = By.xpath("//*[@id='icon_back']");
	
//	Tab Navigation
	public static By objTabNavigation(String TabName) {
		return By.xpath("//*[@text='"+TabName+"']");
	}
	
//	Icon No Reminder
	public static By objNoReminderIcon = By.xpath("//*[@id='icon_ic_no_reminder']");
	
//	Text No Reminder
	public static By objNoReminderTxt = By.xpath("//*[@id='txt_no_reminder']");
	
	
}
