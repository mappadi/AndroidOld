package com.zee5Pages;

import org.openqa.selenium.By;

public class WelcomePage {
	
	public static By objWelcomeToZeeText = By.xpath("//*[@resource-id='com.graymatrix.did:id/welcome']");

	public static By objWelcomeToZeeText1 = By.xpath("//*[@resource-id='com.graymatrix.did:id/welcome_txt1']");
	
	public static By objWelcomeToZeeText2 = By.xpath("//*[@resource-id='com.graymatrix.did:id/welcome_txt2']");
	
	public static By objSkip = By.xpath("//*[@resource-id='com.graymatrix.did:id/welcome_skip']");
	
	public static By objUpdateLanguageBtn = By.xpath("//*[@resource-id='com.graymatrix.did:id/update_lang']");
}
