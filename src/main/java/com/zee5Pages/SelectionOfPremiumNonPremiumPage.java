package com.zee5Pages;

import org.openqa.selenium.By;

public class SelectionOfPremiumNonPremiumPage {

	
	public static By objNonPremium(int index) {
		return By.xpath("(//*[@id='premium_tag' and //*[@id='episode_thumbnail']])["+index+"]");
	}
	
	public static By objMetadata = By.xpath("//*[@id='tvshow_season_episodes_text']");
	
}
