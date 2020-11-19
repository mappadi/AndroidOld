package com.zee5Pages;

import org.openqa.selenium.By;

public class BaseLoginPage {

//		Login / Register ::
//	LOGO 
	public static By objLOGOIcon = By.xpath("//*[@id='z5LoginLogoImageView']");

//	text 
	public static By objContenttext = By.xpath("//*[@id='ConnectwithSocial']");

//	FACEBOOK ICON 
	public static By objFacebookIcon = By.xpath("//*[@id='facebook']");

//	TWITTER ICON 
	public static By objTwitterIcon = By.xpath("//*[@id='twitter']");

//	GOOGLE ICON 
	public static By objGoogleIcon = By.xpath("//*[@id='google_sign_in']");

//	MOBILE NUMBER 
	public static By objMobileNumber = By.xpath("//*[@id='login_mobile_button_text']");

//	EMAIL ID 
	public static By objEmailID = By.xpath("//*[@id='login_email_button_text']");

//	Dont have account 
	public static By objDonthaveaccountText = By.xpath("//*[@id='already_have_an_account']");

//	REGISTER
	public static By objRegisterBtn = By.xpath("//*[@id='login_text']");

//	SKIP
	public static By objSkipBtn = By.xpath("//*[@id='skip']|//*[@id='SKIP']");

//	TERMS TEXT
	public static By objTermsTxt = By.xpath("//*[@id='terms_text']");
	
//	GUIDETOUR TEXT
	public static By objGuideTourTxt = By.xpath("//*[@id='dialog_spot_tilte']");
	
//	NOTHANKS LINK
	public static By objGuideTourNoThanksLink = By.xpath("//*[@id='no_thanks']");
	
//	START BUTTON
	public static By objGuideTourStartBtn = By.xpath("//*[@id='start_button']");
	
//	AdBanner
	public static By objAbBanner = By.xpath("//*[@text='Click here to Subscribe']");
	
}
