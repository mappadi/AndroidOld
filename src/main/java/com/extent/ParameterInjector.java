package com.extent;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlSuite;

import com.jayway.restassured.response.Response;

import org.testng.IAlterSuiteListener;

public class ParameterInjector implements IAlterSuiteListener  {
	
	@Override
	public void alter(List<XmlSuite> suites) {
		XmlSuite suite = suites.get(0);
		Map<String, String> params = new HashMap<>();	
		
		//Pass environment data
		params.put("url", suite.getParameter("url"));
		params.put("browserType", suite.getParameter("browserType"));
		params.put("userType", suite.getParameter("userType"));
		params.put("NonsubscribedUserName", suite.getParameter("NonsubscribedUserName"));
		params.put("NonsubscribedPassword", suite.getParameter("NonsubscribedPassword"));
		params.put("SubscribedUserName", suite.getParameter("SubscribedUserName"));
		params.put("SubscribedPassword", suite.getParameter("SubscribedPassword"));
		params.put("devicePin", suite.getParameter("devicePin"));
		params.put("runModule", suite.getParameter("runModule"));
		params.put("runMode", suite.getParameter("runMode"));
		
		// Pass region specific data
		Response regionResponse=given().urlEncodingEnabled(false).when().get("https://xtra.zee5.com/country");
		String region=regionResponse.getBody().jsonPath().getString("state_code");
		if(region.equals("KA")) {
			params.put("searchModuleSearchKey", "Kamali");
			params.put("consumptionsEpisode", "Digvijay stunned on hearing Sambhashiva");
			params.put("consumptionsShow", "Paaru");
			params.put("consumptionsFreeContent", "Robin Hood");
			params.put("consumptionsPremiumContent", "Huliraya");
			params.put("consumptionsContentWithMetaData", "Jothe Jotheyali");
			params.put("musicToTriggerReco", "Nenne Monneya Ninna Parichaya - Giftbox | Ameeta Kulal | Ritvvikk Mathad | Bindu Malini");
			params.put("newsToTriggerReco", "Amid coronavirus, cong holds massive 'cycle rally' to protest against fuel price hike in Bengaluru");
			params.put("freeMovie","Gooli");
			params.put("freeMovie2","Bablu Dablu - Robo Rumble");
			params.put("freeMovie3","Pup Scouts");
			params.put("freeMovie4","Robin Hood");
			params.put("premiumMovie","The Road Within");
			params.put("premiumMovie2","Bhinna");
			params.put("premiumMovieNoTrailer","Mugulu Nage");
			params.put("premiumMovieNoTrailer2","Londonalli Lambodara");
			params.put("premiumMovieNoTrailer3","Raambo 2");
			params.put("premiumMovieWithTrailer","Premier Padmini");			
			params.put("freeEpisode1","Comedy Kiladigalu Championship - Episode 1 - July 7, 2018 - Full Episode");
			params.put("freeEpisode2","Everyone delighted about Adya's pregnancy - Gattimela");
			params.put("freeEpisode3","Jhende admits defeat - Jothe Jotheyali");
			params.put("freeEpisode4","Aditya shares his feelings - Paaru");
			params.put("episodeSpoiler","Paaru - April 06, 2020 - Episode Spoiler");
			params.put("livetv","TV9 Kannada");
			params.put("news","R News");
			params.put("tvshow","Nisha");
			params.put("trailerOfPremiumMovie","Bhinna - Trailer");
			params.put("music","Nenne Monneya Ninna Parichaya - Giftbox | Ameeta Kulal | Ritvvikk Mathad | Bindu Malini");
			params.put("timedAnchorEpisode", "Paarvathi kisses Aditya - Paaru");
			params.put("timedAnchorMusic", "Kanneeraa - Kavacha | Shivaraj Kumar");		
			params.put("timedAnchorMovie", "Bablu Dablu - Robo Rumble");
			params.put("episodeToTriggerReco", "Aditya-Paarvathi challenge themselves for fun");
			params.put("movieToTriggerReco", "Monsters And Pirates");
			params.put("episodeToTriggerReco", "Aditya-Paarvathi challenge themselves for fun");
			params.put("ExpiredUserName", "zee5latest@gmail.com");
			params.put("ExpiredUserPassword", "User@123");
			params.put("audioTrackContent", "Episode 13 - Agent Raghav");

		}
		if(region.equals("MH")) {
			params.put("searchModuleSearchKey", "Kundali Bhagya");
			params.put("consumptionsEpisode", "Guddan steps up to protect her family");
			params.put("consumptionsShow", "Pavitra Rishta");
			params.put("consumptionsFreeContent", "Robin Hood");
			params.put("consumptionsPremiumContent", "Badnaam Gali");
			params.put("consumptionsContentWithMetaData", "Jodha Akbar");
			params.put("musicToTriggerReco", "Dopamine - REJCTX");
			params.put("newsToTriggerReco", "Rapid fire with Leander Pace: 18-Time grand slam winner on the hot seat");
			params.put("freeMovie","Krishna Balram: The Warrier Princess");
			params.put("freeMovie2","Manthan");
			params.put("freeMovie3","Pup Scouts");
			params.put("freeMovie4","Robin Hood");
			params.put("premiumMovie","The Road Within");
			params.put("premiumMovie2","Hotel Mumbai");
			params.put("premiumMovieNoTrailer","Fitoor");
			params.put("premiumMovieNoTrailer2","The Real Tiger");
			params.put("premiumMovieNoTrailer3","Tamasha");
			params.put("premiumMovieWithTrailer","Chintu Ka Birthday");
			params.put("freeEpisode1","Ep 1 - Tamannaah Bhatia's Fitness Mantras | Dabur Honey Hello Fitness");
			params.put("freeEpisode2","Kumkum Bhagya - Episode 1166 - August 15, 2018 - Full Episode");
			params.put("freeEpisode3","Yeh Teri Galiyan - Episode 1 - July 25, 2018 - Full Episode");
			params.put("freeEpisode4","Episode 4 - Pavitra Rishta");
			params.put("episodeSpoiler","Happu Ki Ultan Paltan - Episode 22 - April 02, 2019 - Next Episode Spoiler");
			params.put("livetv","ZEE News");
			params.put("news","R News");
			params.put("tvshow","Kannamoochi");
			params.put("trailerOfPremiumMovie","Kedarnath - Trailer");
			params.put("music","Nachde Ne Saare - Baar Baar Dekho | Sidharth Malhotra | Katrina Kaif | Armaan");
			params.put("timedAnchorEpisode", "Paarvathi kisses Aditya - Paaru");
			params.put("timedAnchorMusic", "Appa Lyrical - Punith Shetty");		
			params.put("timedAnchorMovie", "Robin Hood");
			params.put("episodeToTriggerReco", "Aditya-Paarvathi challenge themselves for fun");
			params.put("movieToTriggerReco", "Monsters And Pirates");
			params.put("episodeToTriggerReco", "Aditya-Paarvathi challenge themselves for fun");
			params.put("ExpiredUserName", "zee5latest@gmail.com");
			params.put("ExpiredUserPassword", "User@123");
			params.put("audioTrackContent", "Episode 13 - Agent Raghav");
		}
        suite.setParameters(params);	
	}
}
