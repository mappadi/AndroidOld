package com.zee5Pages;

import org.openqa.selenium.By;

public class IntermediatePage {

//	User Name Text in intermediate screen
	public static By objIntermediateUserName = By.xpath("//*[@id='username']");
	
//	User like Text in intermediate screen
	public static By objIntermediateUserLike = By.xpath("//*[@id='user_like']");
	
//	Navigation buttons in intermediate screen
	public static By objIntermediateButtons(int value) {
		return  By.xpath("(//*[@id='button_text'])["+value+"]");
	}
	
	public static By objIntermediateIconClick(String IconName) {
		return By.xpath("//*[@text='"+IconName+"']");
	}
	
	public static By objSizeOfButtonsDisplayedOnScreen = By.xpath("(//*[@id='button_text'])");
	
}
