package com.zee5Pages;

import org.openqa.selenium.By;

public class PlayerPage {

	public static By objAd = By.xpath("//*[contains(text(),'Ad :')]");

	public static By objPlayerView = By.xpath("//*[@id='playerView']");

	public static By objPlayerMinimizeIcon = By.xpath("//*[@id='player_minimize_button']");

	public static By objPlayerTitle = By.xpath("//*[@id='player_title_video']");

	public static By objPlayPauseIcon = By.xpath("//*[@id='player_play_pause']");

	public static By objPlayerNextIcon = By.xpath("//*[@id='player_play_next']");

	public static By objPlayerCurrentTime = By.xpath("//*[@id='player_current_time'] | //*[@id='currentTimeTxt']");

	public static By objPlayerEndTime = By.xpath("//*[@id='player_end_time']");

	public static By objPlayerExpanderIcon = By.xpath("//*[@id='expand_icon_player']");

	public static By objPlayerShareIcon = By.xpath("//*[@id='player_share_button']");

	public static By objPlayerThreeDotsIcon = By.xpath("//*[@id='player_options_button']");

	public static By objPlayerSeekBar = By.xpath("//*[@id='player_seek_bar'] | //*[@id='customSeekBar']");

	public static By objtvShowTitle = By.xpath("//*[@id='tvshow_name']");

	public static By objtvShowMetadata = By.xpath("//*[@id='tvshow_season_episodes_text']");

	public static By objPlayerWatchLaterBtn = By.xpath("//*[@id='player_options_watch_later']");

	public static By objPlayerShareBtn = By
			.xpath("//*[@id='player_share_button' and ./parent::*[@id='buttons_screen']]");

	public static By objPlayerDownloadBtn = By.xpath("//*[@id='download_progress']");

	public static By objPlayerAudioLanguage = By.xpath("//*[@id='tvshows_audio_lang_text']");

	public static By objPlayerAudioLanguageText = By.xpath("//*[@id='tvshows_audio_lang_input']");

	public static By objPlayerCollapseIcon = By.xpath("//*[@id='expand_collapse_icon']");

	public static By objPlayerDescription = By.xpath("//*[@id='tvshow_overview_detail_text']");

	// 3Dot option's Pop up
	public static By obj3dotPopup = By.xpath("//*[@id='popup_layout']");

	public static By obj3dotPopupElements = By.xpath("//*[@id='sub_menu_text_view']");

	public static By obj3dotPopupSelectElements(int value) {
		return By.xpath("(//*[@id='sub_menu_text_view'])[" + value + "]");
	}

	// Quality
	public static By objQulityPopupText = By.xpath("//*[@id='player_dialog_head_text']");

	public static By objQulityPopuoOption = By.xpath("//*[@id='dialog_radio_button']");

	public static By objQulityPopuoOptions(int value) {
		return By.xpath("(//*[@id='dialog_radio_button'])[" + value + "]");
	}

	// Subtitle/CC
	public static By objSubtitlePopupText = By.xpath("//*[@id='player_dialog_head_text']");

	public static By objSubtitlePopuoOption = By.xpath("//*[@id='dialog_radio_button']");

	public static By objSubtitlePopuoOptions(int value) {
		return By.xpath("(//*[@id='dialog_radio_button'])[" + value + "]");
	}

	public static By objSubtitleOKbtn = By.xpath("//*[@id='player_dialog_ok_text']");

	// Audio Language
	public static By objAudioLanguagePopupText = By.xpath("//*[@id='player_dialog_head_text']");

	public static By objAudioLanguagePopuoOption = By.xpath("//*[@id='dialog_radio_button']");

	public static By objAudioLanguagePopuoOptions(int value) {
		return By.xpath("(//*[@id='dialog_radio_button'])[" + value + "]");
	}

	public static By objAudioLanguageOKbtn = By.xpath("//*[@id='player_dialog_ok_text']");

	// AddToWatchList Toast Msg
	public static By objAddToWatchListToastMsg = By.xpath("//*[@text='Added to Watchlist']");

	public static By objRemovedFromWatchListToastMsg = By.xpath("//*[@text='Removed from Watchlist']");

	// Hamburger
	public static By objHamburgerIcon = By.xpath("//*[@id='action_menu']");

	public static By objWatchList = By.xpath("//*[@id='slide_menu_watchlist_text']");

	public static By objWatchlistcount = By.xpath("//*[@id='profile_fragment_page_title']");

	public static By objWatchlistsItems = By.xpath("//*[@id='textview_fragment']");

	public static By objWatchlistsItem(int value) {
		return By.xpath("(//*[@id='textview_fragment'])[" + value + "]");
	}

	public static By objProfileScreenBackbutton = By.xpath("//*[@id='profile_screen_back_button']");

	public static By objPlayerLoader = By.xpath("//*[@id='player_loading_progress']");

	public static By objWaitForElementDisplay = By.xpath("//*[@id='player_play_pause'] | //*[contains(text(),'Ad :')]");

	public static By objPlayBtn(String PlayBtn) {
		return By.xpath("//*[@id='episode_thumbnail' and (./preceding-sibling::* | ./following-sibling::*)[./*[@text='"
				+ PlayBtn + "']]]");
	}

	public static By objBackBtn = By.xpath("//*[@id='view_all_back_button']");

	public static By objNextBtn = By.xpath("//*[@id='player_play_next']");

	public static By objsubPopup = By.xpath("//*[@id='subscribe_now']");

	public static By objsubPopupCanclBtn = By.xpath("//*[@id='PIN_PopUpCancel']");

	public static By objPlayerShowtitle = By.xpath("//*[@id='player_title_video']");

	public static By objShowName(int value) {
		return By.xpath("(//*[@id='textview_fragment'])[" + value + "]");
	}

	public static By objSearchActionBar = By.xpath("//*[@id='action_bar_search']");

	public static By objSearchBar = By.xpath("//*[@id='text1']");

	public static By objsearchIcon = By.xpath("//*[@id='search_back']");

	public static By objTVShowsIcon = By.xpath("//*[@id='tvshows_text']");

	public static By objSearchedVideo = By.xpath("//*[@text='Jothe Jotheyali']");

	public static By objSubCancleBtn = By.xpath("//*[@id='PIN_PopUpCancel']");

	public static By objViewAllBtn = By.xpath("//*[@id='view_all_home_text']");

	public static By objminiPlayer = By.xpath(
			"//*[@class='android.view.View' and ./parent::*[@class='android.widget.FrameLayout' and ./parent::*[@id='playerView']]]");

	public static By objDownArrowBtn = By.xpath("//*[@id='season_screen_back_button']");

	public static By objWatchLaterBtn = By.xpath("//*[@id='slide_menu_watchlist_text']");

	public static By objwatchlistShowTitle = By.xpath("//*[@id='textview_fragment']");

	public static By objTrailerBtn = By.xpath("//*[@id='trailer_text']");

	public static By objPinPopup = By.xpath("//*[@id='PIN_title']");

	public static By objPinPopupTxt = By.xpath("//*[@id='parental_pin_password']");

	public static By objSearchTabs(String tabName) {
		return By.xpath("//*[@text='" + tabName + "' and //*[@id='search_tab_item_text']]");
	}

	public static By objSearchMovieFirstContent = By.xpath("//*[@text='36 China Town']");

	public static By objKumkumaBhagya = By.xpath("//*[@text='Kumkum Bhagya']");

	public static By objRemoveAllBtn = By.xpath("//*[@text='Remove All']");

	public static By objDeleteBtn = By.xpath("//*[@text='DELETE']");

//	 Total Duration time
	public static By totalDurationTime = By.xpath("//*[@class='totalDuration']//*[@class='playkit-time-display']//*");

//	 Current duration time
	public static By currentDurationTime = By
			.xpath("//*[@class='currentDuration']//*[@class='playkit-time-display']//*");
//Play or Pause
	public static By objPlayOrPause = By
			.xpath("//*[@class='playkit-icon playkit-icon-pause' or @class='playkit-icon playkit-icon-play']");

}
