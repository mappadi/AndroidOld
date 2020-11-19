package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAContinueWatchingTrayPage {

	//Progress bar
	public static By objProgressBar = By.xpath("(//div[@class='progress-bar'])[1]");
	public static By objCardTitle(int index) {
		return By.xpath("(//h3[@class='cardTitle'])["+index+"]");
	}
	public static By objTotalTimeLeft(int index) {
		return By.xpath("(//div[@class='continueWatchCardMetaData'])["+index+"]");
	}
	public static By objProgressBarProgress(int index) {
		return By.xpath("(//div[@class='filler'])["+index+"]");
	}
	public static By objCloseContent = By.xpath("(//div[@class='removeBtn iconInitialLoad-ic_close'])[1]");
}
