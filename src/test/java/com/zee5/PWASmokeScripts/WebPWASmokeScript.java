package com.zee5.PWASmokeScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASmokeWEBBusinessLogic;

public class WebPWASmokeScript {

	private Zee5PWASmokeWEBBusinessLogic zee5WebBusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		zee5WebBusinessLogic = new Zee5PWASmokeWEBBusinessLogic("Chrome");
	}

	@Test(priority = 0)
	@Parameters({ "userType" })
	public void IntegratedScript(String userType) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			zee5WebBusinessLogic.ZeeWEBPWALogin("SubscribedUser");
			zee5WebBusinessLogic.verificationOfLoggedIn();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zee5WebBusinessLogic.ZeeWEBPWALogin("NonSubscribedUser");
			zee5WebBusinessLogic.verificationOfLoggedIn();
		} else {
			zee5WebBusinessLogic.ZeeWEBPWALogin(userType);
			zee5WebBusinessLogic.dismissDisplayContentLanguagePopUp();
		}
	}

	
	@Test(priority = 1)
	@Parameters({ "userType" })
	public void WEBPWAOnboarding(String userType) throws Exception {
		zee5WebBusinessLogic.OnboardingScenario(userType);
	}
	 
	@Test(priority = 2)
	@Parameters({ "browserType", "url", "userType", "devicePin", "consumptionsEpisode", "consumptionsShow","consumptionsFreeContent", "consumptionsPremiumContent", "consumptionsContentWithMetaData","consumptionsContentWithShare" })
	public void WEBPWAConsumptionScreen(String browser, String url, String userType, String devicePin,String consumptionsEpisode, String consumptionsShow, String consumptionsFreeContent,String consumptionsPremiumContent, String consumptionsContentWithMetaData,String consumptionsContentWithShare) throws Exception {
		//zee5WebBusinessLogic.navigateHome();
		zee5WebBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Episode", consumptionsEpisode, devicePin); // Episode
		zee5WebBusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Live TV", "", devicePin); // Live TV Card
		//zee5WebBusinessLogic.verifyWatchLatestEpisodeCTA(consumptionsShow);
		zee5WebBusinessLogic.verifyNoSubscriptionPopupForFreeContent(userType, "Movies", consumptionsFreeContent,devicePin);
		zee5WebBusinessLogic.verifySubscriptionPopupForPremiumContent(userType, "Movies", consumptionsPremiumContent,devicePin);
		zee5WebBusinessLogic.verifyShareAndMetaDataInDetailsAndConsumption(consumptionsContentWithMetaData);
	}

	@Test(priority = 3)
	@Parameters({ "userType" })
	public void WEBPWALandingPages(String userType) throws Exception {
		zee5WebBusinessLogic.navigateHome();
		zee5WebBusinessLogic.WebValidatingLandingPages(userType);
	}

	@Test(priority = 4)
	@Parameters({ "userType" })
	public void WEBPWACarousel(String userType) throws Exception {
		zee5WebBusinessLogic.navigateHome();
		// auto rotating
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Home");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Movies");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Free Movies");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Shows");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Premium");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Play");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Kids");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("Stories");
		zee5WebBusinessLogic.verifyAutoroatingOnCarousel("ZEE5 Originals");	
		// play icon functionality
		zee5WebBusinessLogic.verifyPlayIconFunctionality("ZEE5 Originals");
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Kids");
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Premium");
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Home");
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Shows");
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Free Movies");;
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Movies");
		zee5WebBusinessLogic.verifyPlayIconFunctionality("Home");
		// premium icon functionality
		zee5WebBusinessLogic.verifyPremiumIconFunctionality("Home", userType);
		zee5WebBusinessLogic.verifyPremiumIconFunctionality("Premium", userType);
		zee5WebBusinessLogic.verifyPremiumIconFunctionality("Movies", userType);
		zee5WebBusinessLogic.verifyPremiumIconFunctionality("ZEE5 Originals", userType);
		// left right functionality
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Home");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Movies");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Free Movies");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Shows");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Premium");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Play");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Kids");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("Stories");
		zee5WebBusinessLogic.verifyLeftRightFunctionality("ZEE5 Originals");
		//zee5WebBusinessLogic.verifyLeftRightFunctionality("News");
		// metadata
		String languageSmallText = zee5WebBusinessLogic.allSelectedLanguagesWEB();
		zee5WebBusinessLogic.verifyMetadataOnCarousel("ZEE5 Originals","zeeoriginals", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Stories", "stories", "");
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Kids", "kids", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Play","play", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Premium","premiumcontents", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Shows", "tvshows", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Free Movies", "freemovies", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Movies", "movies", languageSmallText);
		zee5WebBusinessLogic.verifyMetadataOnCarousel("Home", "home", languageSmallText);	
	    //zee5WebBusinessLogic.verifyMetadataOnNews("News", "news", languageSmallText);
	}

	@Test(priority = 5)
	@Parameters({ "userType" })
	public void WEBPWAPlayerValidation(String userType) throws Exception {
		zee5WebBusinessLogic.navigateHome();
		zee5WebBusinessLogic.ValidatingPlayer(userType);
	}

	@Test(priority = 6)
	public void WEBPWAUICheck() throws Exception {
		zee5WebBusinessLogic.navigateHome();
		System.out.println("verifyUIofHomePage");
		zee5WebBusinessLogic.verifyUIofHomePage();
		System.out.println("verifyLiveTvAndChannelGuideScreen");
		zee5WebBusinessLogic.verifyLiveTvAndChannelGuideScreen();
	}

	@Test(priority = 7)
	public void WEBPWASearch() throws Exception {
		zee5WebBusinessLogic.navigateHome();
		String liveContentName = zee5WebBusinessLogic.fetchLiveContent();
		zee5WebBusinessLogic.landingOnSearchScreen();
		zee5WebBusinessLogic.searchResultScreen("Paaru");
		zee5WebBusinessLogic.liveTv(liveContentName);
		zee5WebBusinessLogic.navigationToConsumptionScreenThroughTrendingSearches();
	}

	@Test(priority = 8)
	@Parameters({ "userType" })
	public void WEBPWASubscriptionTransaction(String userType) throws Exception {
		zee5WebBusinessLogic.navigateHome();
		zee5WebBusinessLogic.WEBPWAValidatingSubscriptionAndTransaction(userType);
		zee5WebBusinessLogic.WEBPWAValidatingSubscribeLinks();
	}

	@Test(priority = 9)
	@Parameters({ "userType" })
	public void WEBPWASubscription(String userType) throws Exception {
		zee5WebBusinessLogic.navigateHome();
		zee5WebBusinessLogic.zeePWASubscriptionSuite(userType);
	}

	
	//-------------------------SMOKE p2-------------------------------------------
	
	
	//--------------------------YASHASWINI LanguageModule--------------------------
	@Test(priority = 10)
	@Parameters({"userType"})
	public void PWAlanguageSettingsValidation(String userType) throws Exception {
		zee5WebBusinessLogic.LanguageModule(userType);
	}
	
	
	//--------------------------YASHASWINI MenuOrSetting--------------------------
	@Test(priority = 11)
	@Parameters({ "userType" })
	public void PWAMenuOrSetting(String userType) throws Exception {
		zee5WebBusinessLogic.MenuOrSettingScenarios(userType);	
	}
	
	
	//--------------------------BHAVANA ShareFunctionality--------------------------
	@Test(priority = 10)
	@Parameters({"userType","url"})
	public void ShareFunctionalityValidation(String userType,String url) throws Exception {
		zee5WebBusinessLogic.dismissDisplayContentLanguagePopUp();
		zee5WebBusinessLogic.ShareModule(userType);
				
	}
	
	//--------------------------BHAVANA StaticPages--------------------------
	@Test(priority = 11)
	@Parameters({"userType","url"})
	public void StaticPagesValidation(String userType,String url) throws Exception {
		zee5WebBusinessLogic.dismissDisplayContentLanguagePopUp();
		zee5WebBusinessLogic.StaticPages(userType);
		
	}
	
	//--------------------------SATISH UserAction--------------------------
		@Test(priority = 11)
		@Parameters({"userType","url"})
		public void UserAction(String userType,String url) throws Exception {
			zee5WebBusinessLogic.dismissDisplayContentLanguagePopUp();
			zee5WebBusinessLogic.UserActionGuestUser();
			
		}
	
	
	@AfterClass
	public void tearDown() {
		zee5WebBusinessLogic.tearDown();
	}

}
