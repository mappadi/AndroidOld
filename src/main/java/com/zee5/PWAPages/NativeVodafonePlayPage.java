package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class NativeVodafonePlayPage {

	public static By HamburgerBtn = By.xpath("//*[@content-desc='Vodafone Play']");
	
	public static By SearchBtn = By.xpath("//*[@id='search_button']");
	
	public static By ChannelSearchIcon = By.xpath("(//*[@id='action_search'])[1]");
	
	public static By SearchTextBox = By.xpath("//*[@id='search_src_text']");
	
	//public static By searchedData = By.xpath("//*[@class='android.widget.TextView' and @text='Commando 3']");
	
	public static By searchedData(String searchData)
	{
		return By.xpath("//*[@class='android.widget.TextView' and @text='"+searchData+"']");
	}
	
//	SANITY BASAVARAJ VIL MODULE
	public static By EnterYourNumber = By.xpath("//*[@text='Enter Your Vodafone Mobile Number']");
	public static By NumberTextBox = By.xpath("//*[@class='android.widget.EditText']");
	public static By continueBtn = By.xpath("//*[@text='Continue']");
	public static By EnterOTPText = By.xpath("//*[@text='Enter OTP!']");
	public static By OTPTextField = By.xpath("//*[@text='Enter 6-digit OTP']");
	public static By OTPgoBtn = By.xpath("//*[@class='android.widget.Button' and @text='Go']");
	public static By Zee5IconinList = By.xpath("(//*[@class='android.widget.TextView' and @text='ZEE5'])[1]");
	public static By VILPlayIcon = By.xpath("//*[@id='cardmediasubitemvideo_play_icon']");
	public static By Zee5Icon = By.xpath("//*[@id='cp_logo'][1]");
	
}
