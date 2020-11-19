package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDWatchlistPage {

	public static By objWatchlistTitle = By.xpath("//*[@id='screen_title' and //*[@text='Watchlist']]");
	public static By objBackBtn = By.xpath("//*[@id='icon_back']");
	public static By objNoReminderIcon = By.xpath("//*[@id='icon_ic_no_reminder']");
	public static By objNoReminderTxt = By.xpath("//*[@id='txt_no_reminder']");
	public static By objEditBtn = By.xpath("//*[@id='skip_link' and //*[@text='Edit']]");
	public static By objTitle = By.xpath("//*[@id='txt_reminder_item_title']");
	public static By objForwardIcon = By.xpath("//*[@id='icon_ic_forward_button']");
	public static By objSelectAllIcon = By.xpath("//*[@id='txt_select_all']");
	public static By objDeleteAllBtn = By.xpath("//*[@id='txt_delete']");
	public static By objSelectItemsToDeleteTxt = By.xpath("//*[@text='Select Items to Delete']");
	public static By objCloseIcon = By.xpath("//*[@id='icon_close']");
	public static By objTitleOfContent =By.xpath("//*[@id='txt_reminder_item_title']");
	
	public static By objIterateEpisode(int i) {
		return By.xpath("(//*[@id='txt_episode_duration'])["+i+"]");
	}
	public static By objIterateTitle(int i) {
		return By.xpath("(//*[@id='txt_reminder_item_title'])["+i+"]");
	}
	public static By objEpisodeCount = By.xpath("//*[@id='txt_episode_duration']");
	public static By objSelectCheckBox = By.xpath("//*[@id='check_box_item_selector']");
	
	public static By objSelectContentByIndex(int i) {
		return By.xpath("(//*[@id='check_box_item_selector'])["+i+"]");
	}
	public static By objContentThumbnail = By.xpath("//*[@id='img_reminder_item']");
	
	public static By objTabs(int i) {
		return By.xpath("(//*[@class='android.support.v7.app.ActionBar$b']//*[@class='android.widget.TextView'])["+i+"]");
	}
	
	public static By objNumberOfTabs = By.xpath("//*[@class='android.support.v7.app.ActionBar$b']");
	
	public static By objDurationtxt = By.xpath("(//*[@id='episodsList']//*[@id='txt_episode_duration'])[1]");
	
	public static By objTitleTxt = By.xpath("(//*[@id='episodsList']//*[@id='txt_reminder_item_title'])[1]");
	
	public static By objSelectedTab = By.xpath("(//*[@class='android.support.v7.app.ActionBar$b']//*[@class='android.widget.TextView' and @selected='true'])");

	public static By objPlayerScreen = By.xpath("//*[@id='player_root']");
	
	public static By objWatchlistIcon = By.xpath("//*[@id='watchListView']");
}
