package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAMyRemindersPage {
	
	public static By objMyReminderHeader = By.xpath(".//h1[@class='pageTitle']");
	public static By objDateTime = By.xpath("//div[@class='dateTime']");
	public static By objNoReminderMessage = By.xpath("//div[@class='textArea']");
	public static By objTotalContentsInReminder = By.xpath("//h3[contains(@class,'cardTitle')]//a");
	public static By objReminderTitle (String title){
		return By.xpath("//h3[contains(@class,'cardTitle')]//a[text()=\""+title+"\"]");
	}
	

}
