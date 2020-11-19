package com.zee5.PWASmokeScripts;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.business.zee.Zee5PWASmokeAndroidBusinessLogic;



public class AndroidPWASmokeScript {
	private Zee5PWASmokeAndroidBusinessLogic zee5PWABusinessLogic;

	@BeforeTest
	public void init() throws Exception {
		zee5PWABusinessLogic = new Zee5PWASmokeAndroidBusinessLogic("Chrome");
	}

	
	@Test(priority = 0)
	@Parameters({ "userType" })
	public void PWASmokeAndroid(String userType) throws Exception {
		if (userType.equalsIgnoreCase("SubscribedUser")) {
			zee5PWABusinessLogic.ZeePWALogin("E-mail",userType);
			zee5PWABusinessLogic.verificationOfLoggedIn();
		} else if (userType.equalsIgnoreCase("NonSubscribedUser")) {
			zee5PWABusinessLogic.ZeePWALogin("E-mail",userType);
			zee5PWABusinessLogic.verificationOfLoggedIn();
		} else {
			zee5PWABusinessLogic.dismissDisplayContentLanguagePopUp();
		}
	}
	
	
	
	@Test(priority = 1)
	@Parameters({ "userType" })
	public void PWAOnboarding(String userType) throws Exception {
		System.out.println("1st method started >>>>");
		zee5PWABusinessLogic.OnboardingScenario(userType); 		
		System.out.println(">>>>> 1st method completed");
	}
	
//	@Test(priority = 2)
//	@Parameters({ "browser", "url", "userType", "consumptionsEpisode","consumptionsShow","consumptionsFreeContent","consumptionsPremiumContent"}) 
//	public void PWAContentDetails(String browser, String url, String userType, String consumptionsEpisode,String consumptionsShow,String consumptionsFreeContent,String consumptionsPremiumContent)throws Exception {
//		System.out.println("2nd method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);
//		zee5PWABusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Episode", consumptionsEpisode); // Episode //Mahira accuses Preeta of// trying to kill Mahesh //Neil gets// emotional while apologising to Jhende
//		zee5PWABusinessLogic.verifyConsumptionsScreenTappingOnCard(userType, "Live TV", ""); // Live TV Card
//		zee5PWABusinessLogic.verifyNoSubscriptionPopupForFreeContent(userType, consumptionsFreeContent);
//		zee5PWABusinessLogic.verifySubscriptionPopupForPremiumContent(userType, consumptionsPremiumContent);
//		zee5PWABusinessLogic.verifyCTAandMetaDataInDetailsAndConsumption(consumptionsShow);
//		System.out.println(">>>> 2nd method completed");
//
//	}
//
//	@Test(priority = 3)
//	@Parameters({"userType","url"}) // vinay player
//	public void PWAPlayer(String userType,String url)throws Exception {
//		System.out.println("3rd method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);		
//		zee5PWABusinessLogic.playerValidations(userType);
//		zee5PWABusinessLogic.UpnextRail();
//		System.out.println(">>>> 3rd method completed");
//	}
//
//	@Test(priority = 4)
//	@Parameters({ "userType", "url"})
//	public void PWACarousel(String userType, String url) throws Exception { 
//		System.out.println("4th method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);
//		// autorotating
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Home");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Movies");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Free Movies");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Shows");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Premium");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Play");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Kids");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("Stories");
//		zee5PWABusinessLogic.verifyAutoroatingOnCarousel("ZEE5 Originals");	
//		// play icon functionality
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("ZEE5 Originals");
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("Kids");
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("Premium");
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("Shows");
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("Free Movies");;
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("Movies");
//		zee5PWABusinessLogic.verifyPlayIconFunctionality("Home");
//		// premium icon functionality
//		zee5PWABusinessLogic.verifyPremiumIconFunctionality("Home", userType);
//		zee5PWABusinessLogic.verifyPremiumIconFunctionality("Premium", userType);
//		zee5PWABusinessLogic.verifyPremiumIconFunctionality("Movies", userType);
//		zee5PWABusinessLogic.verifyPremiumIconFunctionality("ZEE5 Originals", userType);
//		// metadata
//		String languageSmallText = zee5PWABusinessLogic.allSelectedLanguages();
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("ZEE5 Originals","zeeoriginals", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Stories", "stories", "");
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Kids", "kids", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Play","play", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Premium","premiumcontents", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Shows", "tvshows", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Free Movies", "freemovies", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Movies", "movies", languageSmallText);
//		zee5PWABusinessLogic.verifyMetadataOnCarousel("Home", "home", languageSmallText);	
//	    //zee5PWABusinessLogic.verifyMetadataOnNews("News", "news", languageSmallText);
//		// <> Functionality
//		zee5PWABusinessLogic.verifyLeftRightFunctionality("News", url);
//		System.out.println(">>>> 4th method completed");
//	}
//	
//	@Test(priority = 5)
//	@Parameters({"url"})
//	public void PWAUICheck(String url) throws Exception {
//		System.out.println("5th method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);
//		zee5PWABusinessLogic.waitTime(2000);
//		zee5PWABusinessLogic.verifyUIofHomePage(); // manasa default home
//		zee5PWABusinessLogic.verifyLiveTvAndChannelGuideScreen(); // manasa live tv
//		System.out.println(">>>> 5th method completed");
//
//	}
//
//	@Test(priority = 6)
//	@Parameters({"searchModuleSearchKey","url"})
//	public void PWASearch(String searchKey,String url) throws Exception { 
//		System.out.println("6th method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);
//		String liveContentName = zee5PWABusinessLogic.fetchLiveContent();
//		System.out.println("Live content : "+liveContentName);
//		zee5PWABusinessLogic.landingOnSearchScreen();
//		zee5PWABusinessLogic.searchResultScreen(searchKey);
//		zee5PWABusinessLogic.liveTv(liveContentName);
//		zee5PWABusinessLogic.navigationToConsumptionScreenThroughTrendingSearches();
//		System.out.println(">>>> 6th method completed");
//
//	}
//
//	@Test(priority = 7)
//	@Parameters({"userType","url"})
//	public void PWALandingScreen(String userType,String url) throws Exception {
//		System.out.println("7th method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);
//		zee5PWABusinessLogic.ValidatingLandingPages(userType);
//		System.out.println(">>>> 7th method completed");
//	}
//
//	@Test(priority = 8)
//	@Parameters({"userType","url"})
//    public void PWASubscription(String userType,String url) throws Exception {	
//		System.out.println("8th method started >>>>");
//		zee5PWABusinessLogic.reloadURL(url);
//        zee5PWABusinessLogic.zeePWASubscriptionSuite(userType); 
//        System.out.println(">>>> 8th method completed");
//    }
	
	
	//--------------------------BASAVARAJ NetWorkinterruption--------------------------
	
		@Test(priority = 9)
		@Parameters({"userType","url"})
	    public void PWANetworkInterruption(String userType,String url) throws Exception {	
			System.out.println("9th method started >>>>");
			zee5PWABusinessLogic.reloadURL(url);
	        zee5PWABusinessLogic.networkInterruption(userType); 
	        System.out.println(">>>> 9th method completed");
	    }
		
		
		//--------------------------YASHASWINI LanguageModule--------------------------
		
			@Test(priority = 10)
			@Parameters({"userType","url"})
		    public void PWALanguageModule(String userType,String url) throws Exception {	
				System.out.println("10th method started >>>>");
				zee5PWABusinessLogic.reloadURL(url);
				zee5PWABusinessLogic.LanguageModule(userType);
		        System.out.println(">>>> 10th method completed");
		    }
			
			
		//--------------------------YASHASWINI MenuOrSetting--------------------------
			
			@Test(priority = 11)
			@Parameters({"userType","url"})
		    public void PWAMenuOrSettingScenarios(String userType,String url) throws Exception {	
				System.out.println("11th method started >>>>");
				zee5PWABusinessLogic.reloadURL(url);
				zee5PWABusinessLogic.MenuOrSettingScenarios(userType);
		        System.out.println(">>>> 11th method completed");
		    }
		
	
			
			
			//--------------------------BHAVANA Sharefuntionality--------------------------
			
			@Test(priority = 12)
			@Parameters({"userType","url"})
		    public void PWAShareFunctionalityValidation(String userType,String url) throws Exception {	
				System.out.println("12th method started >>>>");
				zee5PWABusinessLogic.reloadURL(url);
				zee5PWABusinessLogic.ShareModule(userType);
		        System.out.println(">>>> 12th method completed");
		    }
			
			
			
		//--------------------------BHAVANA StaticPageValidation--------------------------
			
			@Test(priority = 13)
			@Parameters({"userType","url"})
			public void PWAStaticPagesValidation(String userType,String url) throws Exception {	
				System.out.println("13th method started >>>>");
				zee5PWABusinessLogic.reloadURL(url);
				zee5PWABusinessLogic.StaticPages(userType);
			    System.out.println(">>>> 13th method completed");
			}	
			
			
			
		//--------------------------TANISHA Reco Module--------------------------
			
			@Test(priority = 14)
			@Parameters({"userType","url"})
			public void PWARecoTalamoos(String userType,String url) throws Exception {	
				System.out.println("14th method started >>>>");
				zee5PWABusinessLogic.reloadURL(url);
				zee5PWABusinessLogic.verificationOfRecoTalamoos(userType);
			    System.out.println(">>>> 14th method completed");
			}	
			
			
			//--------------------------SATISH UserActions--------------------------
			
			
			@Test(priority = 15)
			@Parameters({"userType","url"})
			public void PWAUserActions(String userType,String url) throws Exception {	
				System.out.println("15th method started >>>>");
				zee5PWABusinessLogic.reloadURL(url);
				zee5PWABusinessLogic.UserActionGuestUser();
			    System.out.println(">>>> 15th method completed");
			}
			
	
	

	@AfterClass
	public void tearDown() {
		zee5PWABusinessLogic.tearDown();
	}
}
