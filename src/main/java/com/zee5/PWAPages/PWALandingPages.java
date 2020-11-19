package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWALandingPages {

	public static By obj_Pwa_HamburgerMenu = By.xpath("//*[@text='Open Menu']");
	public static By obj_Pwa_Zee5Logo = By.xpath("//*[@title='ZEE5 Logo']");
	public static By obj_Pwa_SearchBtn = By.xpath("//*[@class='noSelect searchBtn iconInitialLoad-ic_search']");

	public static By obj_Pwa_PlayIcon_Carousal = By.xpath(
			"(//*[@nodeName='DIV' and ./parent::*[@nodeName='DIV' and ./parent::*[@nodeName='DIV'] and (./preceding-sibling::* | ./following-sibling::*)[@text=' Previous']]]/*/*/*/*/*/*/*[@nodeName='DIV' and @width>0 and ./parent::*[@nodeName='DIV' and @width>0 and (./preceding-sibling::* | ./following-sibling::*)[@text='Get premium'] and ./parent::*[@nodeName='DIV' and @width>0 and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='Seetharama Kalyana']]]]])[1]");

	public static By obj_Pwa_Content_Rail_View_all = By.xpath(
			"//*[@nodeName='DIV' and @width>0 and ./parent::*[@nodeName='DIV' and @width>0] and (./preceding-sibling::* | ./following-sibling::*)[@nodeName='H2' and ./*[@text='Top ZEE5 Movies in Kannada']]]");
	public static By obj_Pwa_WhyRegister_Popup = By.xpath("//*[@text='Why Register?']");
	public static By obj_Pwa_Popup_Close = By.xpath("//*[@class='manCloseIcon']");
	public static By obj_Pwa_Subcription_teaser_btn = By.xpath("//*[@class='subscribeBtn noSelect']");

	public static By obj_Pwa_Back_to_Top_Arrow_btn = By.xpath("//*[@class='iconNavi-ic_arrow_back']");
	public static By objFirstTray = By.xpath("(//div[contains(@class,'trayHeader')])[1]");

	public static By objtrayTitle(String trayTitleFromAPI) {
		return By.xpath(
				"//div[@class='trayHeader']/h2/a[@class='titleLink' and contains(text(),'" + trayTitleFromAPI + "')]");
	}

	public static By objtrayFirstContent(String trayTitleFromAPI, String trayFirstContentFromAPI) {
		return By.xpath("//*[@class='titleLink' and contains(text(),'" + trayTitleFromAPI
				+ "')]/following::div[contains(@data-minutelytitle,'" + trayFirstContentFromAPI
				+ "') and contains(@class,'content')]");
	}

	public static By objViewAllOfTray(String trayTitle) {
		return By.xpath("//*[@class='titleLink' and contains(text(),'" + trayTitle
				+ "')]//parent::*//following-sibling::*[@class='arrow iconInitialLoad-ic_viewall noSelect']");
	}

	public static By objViewAllPageTitle = By.xpath("//h1");

	public static By objWebProfileIcon = By.xpath("(//button[.='Open Menu'])[2]");
	public static By obj_WEBPwa_HamburgerMenu1 = By.xpath("//button[.='Open Menu']");

	public static By objTrayTitleArrowBtn(String str) {
		return By.xpath("//div[contains(text(),'" + str + "')]/parent::*//following-sibling::div");
	}

//	===============================================================================================================
//	BHAVANA SHOWS MODULE
	public static By objHindiInContentLanguageNotSelected = By.xpath("//label[@for='content_hi']");

//	TANISHA RECO MODULE
	public static By objTrayTitleInUI(String apiTitle) {
		return By.xpath(
				"(//a[@class='titleLink' and text()=\"" + apiTitle + "\"]) | (//h2[text()=\"" + apiTitle + "\"])");
	}

	public static By objFirstAssetInTray(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//h2[text()=\"" + apiTrayTitle
				+ "\"]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//*[@data-minutelytitle=\""
				+ apiContentTitle + "\"]");
	}

	public static By objFirstAssetInTrayIndex(String trayTitleUI) {
		return By.xpath("//h2[contains(text(),\"" + trayTitleUI
				+ "\")]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//a");
	}

	public static By objAssetInTray(String apiTrayTitle, String apiContentTitle, String index) {
		return By.xpath("//h2[text()=\"" + apiTrayTitle
				+ "\"]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index=\""
				+ index + "\"]//div[@data-minutelytitle=\"" + apiContentTitle + "\"]");
	}

	public static By objTray(String apiTrayTitle) {
		return By.xpath("//h2[text()=\"" + apiTrayTitle
				+ "\"]//parent::div[@class='trayHeader']//following-sibling::div//div[@class='slick-track']");
	}

	public static By objHindiInContentLanguageSelected = By
			.xpath("//*[@class='checkboxWrap checkedHighlight']//*[@for='content_hi']");

	public static By objTrayTitle = By.xpath("(//a[@class='titleLink']) | (//div[@class='trayHeader']//h2)");

	public static By objFirstAssetInTrayTitle(String trayTitleUI) {
		return By.xpath("//h2[contains(text(),\"" + trayTitleUI
				+ "\")]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//div[@data-minutelytitle]");
	}

	public static By objTrayTitleInUIContains(String apiTitle) {
		return By.xpath("(//div[@class='trayHeader']//h2[contains(text(),\"" + apiTitle
				+ "\")]) | (//h2//a[contains(text(),\"" + apiTitle + "\")])");
	}

	public static By objViewAllPageFirstContent = By.xpath(
			"((//div[@class='viewAllGrid']//div[@data-minutelytitle]) | (//div[@class='viewAllGrid']//a[@data-minutelytitle]))[1]");
	public static By objViewAllPageSecondContent = By.xpath(
			"((//div[@class='viewAllGrid']//div[@data-minutelytitle]) | (//div[@class='viewAllGrid']//a[@data-minutelytitle]))[2]");

	public static By objTrayTitleInUIContainsViewAll(String apiTitle) {
		return By.xpath("//h2//a[contains(text(),\"" + apiTitle + "\")]//parent::*//following-sibling::*");
	}

	public static By firstAssetNonRecoTray(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//a[contains(text(),\"" + apiTrayTitle
				+ "\")]//parent::*//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//*[contains(@data-minutelytitle,\""
				+ apiContentTitle + "\")]");
	}

	public static By secondAssetNonRecoTray(String apiTrayTitle, String apiContentTitle) {
		return By.xpath("//a[contains(text(),\"" + apiTrayTitle
				+ "\")]//parent::*//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='1']//*[contains(@data-minutelytitle,\""
				+ apiContentTitle + "\")]");
	}

	public static By objFirstAssetInTrayPlayIcon(String trayTitleUI) {
		return By.xpath("//h2[contains(text(),\"" + trayTitleUI
				+ "\")]//parent::div//following-sibling::div//div[contains(@class,'slick-track')]//div[@data-index='0']//div[@title='Play']");
	}

	public static By obj_Pwa_Trending_On_Zee5 = By.xpath("//*[text()='Trending on ZEE5' or text()='Trending on Zee5']");

}
