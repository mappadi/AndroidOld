package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDSettingsScreen {
	
	public static By objSelectVideoQualityLabel = By.xpath("//*[@id='selector_screen_title']");
	public static By objVideoQualityBest = By.xpath("//*[@text='Best']");
	public static By objVideoQualityBetter = By.xpath("//*[@text='Better']");
	public static By objVideoQualityGood = By.xpath("//*[@text='Good']");
	public static By objVideoQualityDatasaver = By.xpath("//*[@text='Datasaver']");
	public static By objVideoQualityAskEachTime = By.xpath("//*[@text='Ask each time']");
	public static By objXButton = By.xpath("//*[@text='D']");
	public static By objTickMark = By.xpath("//*[@id='selectionImageSelector']");
	public static By objDownloadOverWifiToggle = By.xpath("//*[@id='downloadOverWifiSwitch']");
	
	public static By objSettingsScreenTitle = By.xpath("//*[@id='screen_title' and //*[@text='Settings']]");
	
	public static By objClearSearchHistory = By.xpath("//*[@id='searchHistoryAction']");
	public static By objSearchHistory = By.xpath("//*[@id='searchHistoryLabel']");
	public static By objResetSettings = By.xpath("//*[@id='resetSettingLabel']");
	public static By objDefaultSetting = By.xpath("//*[@id='resetSettingAction']");
	public static By objResetSettingPopUp = By.xpath("//*[@text='Are you sure you want to reset your settings?']");
	public static By objNoCTA = By.xpath("//*[@id='btn_exit_no']");
	public static By objYesCTA = By.xpath("//*[@id='btn_exit_yes']");
	public static By objAuthenticateDevice = By.xpath("//*[@id='authenticateDevice']");
	public static By objAuthenticateScreen = By.xpath("//*[@text='Authenticate Device']");
	public static By objAuthenticateCloseBtn= By.xpath("//*[@id='icon_exit']");
	public static By objLoadingAnimator= By.xpath("//*[@class='android.widget.ProgressBar']");
	public static By objUpdateSettingsMessage= By.xpath("//*[@id='txt_progress']");
}
