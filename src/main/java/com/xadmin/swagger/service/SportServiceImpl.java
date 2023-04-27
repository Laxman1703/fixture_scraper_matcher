package com.xadmin.swagger.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xadmin.swagger.vo.Response;

@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportServiceImpl implements SportService {
	List<String> starbetFixtureIds = new ArrayList<>();
	List<String> cbtfFixtureIds = new ArrayList<>();

	public Response getDataFromUrl(String url1, String url2) {
		starbetFixtureIds.clear();
		cbtfFixtureIds.clear();
		JSONArray fixturesArray = new JSONArray();

		int starbetFixtureCount = 0;
		int cbtfFixtureCount = 0;

		for (String url : new String[] { url1, url2 }) {
			try {
				int matchCount = 0;
				List<String> providerFixtureIds = new ArrayList<>();
				URL requestUrl = new URL(url);
				HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				String input = response.toString();
				String regex = "id='highlightEvent_(\\d+)'[^>]+data-league='\\{\\\\\"leagueId\\\\\":(\\d+),\\\\\"leagueName\\\\\":\\\\\"([^\\\\\"]+)\\\\\",\\\\\"sportId\\\\\":(\\d+),\\\\\"sportName\\\\\":\\\\\"([^\\\\\"]+)\\\\\"\\}'";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(input);

				while (matcher.find()) {
					matchCount++;
					String sportId = matcher.group(4);
					String sportName = matcher.group(5);
					String leagueName = matcher.group(3);
					String leagueId = matcher.group(2);
					String providerFixtureId = matcher.group(1);
					providerFixtureIds.add(providerFixtureId);

					JSONObject fixtureJsonObject = new JSONObject();
					fixtureJsonObject.put("sport_name", sportName);
					fixtureJsonObject.put("sport_id", sportId);
					fixtureJsonObject.put("league_name", leagueName);
					fixtureJsonObject.put("league_id", leagueId);
					fixtureJsonObject.put("provider_fixture_id", providerFixtureId);
					fixturesArray.put(fixtureJsonObject);
				}

				if (url.equals(url1)) {
					starbetFixtureIds.addAll(providerFixtureIds);
					starbetFixtureCount += providerFixtureIds.size();
				} else {
					cbtfFixtureIds.addAll(providerFixtureIds);
					cbtfFixtureCount += providerFixtureIds.size();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("compared_with", "Starbet");
		jsonResponse.put("compared_site", "CBTF");

		jsonResponse.put("cbtf_fixture_ids", new JSONArray(cbtfFixtureIds));
		jsonResponse.put("cbtf_fixture_count", cbtfFixtureCount);

		jsonResponse.put("starbet_fixture_ids", new JSONArray(starbetFixtureIds));
		jsonResponse.put("starbet_fixture_count", starbetFixtureCount);

		Set<String> cbtfSet = new HashSet<>(cbtfFixtureIds);
		Set<String> starbetSet = new HashSet<>(starbetFixtureIds);

		// Finding missing fixture ids
		Set<String> missingInCbtf = new HashSet<>(starbetSet);
		missingInCbtf.removeAll(cbtfSet);
		Set<String> missingInStarbet = new HashSet<>(cbtfSet);
		missingInStarbet.removeAll(starbetSet);

		jsonResponse.put("cbtf_missing_fixture_ids", new JSONArray(missingInCbtf));
		jsonResponse.put("starbet_missing_fixture_ids", new JSONArray(missingInStarbet));

		//System.out.println(starbetFixtureIds);
		Response response = new Response();
		response.setCompared_with("Starbet");
		response.setCompared_site("CBTF");
		response.setCbtf_fixture_ids(cbtfFixtureIds);
		response.setCbtf_fixture_count(cbtfFixtureCount);
		response.setStarbet_fixture_ids(starbetFixtureIds);
		response.setStarbet_fixture_count(starbetFixtureCount);
		response.setCbtf_missing_fixture_ids(missingInCbtf);
		response.setStarbet_missing_fixture_ids(missingInStarbet);

		return response;

	}

//******************************************************************************************************************************//
//		The JSON response object includes the following keys and values:														//
//																																//
//			"compared_with": A string indicating the source being compared with, in this case "Starbet".						//
//			"cbtf_fixture_ids": An array of soccer fixture ids in CBTF.															//
//			"cbtf_fixture_count": An integer indicating the total number of fixtures in CBTF.									//
//			"compared_site": A string indicating the site being compared, in this case "CBTF".									//
//			"starbet_fixture_ids": An array of soccer fixture ids in Starbet.													//
//			"starbet_fixture_count": An integer indicating the total number of fixtures in Starbet.								//
//			"cbtf_missing_fixture_ids": An array of soccer fixture ids that are missing in CBTF but present in Starbet.			//
//			"starbet_missing_fixture_ids": An array of soccer fixture ids that are missing in Starbet but present in CBTF.		//
//																																//
//******************************************************************************************************************************//

	@Override
	public Response getTennisTopLeagueInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadTopLeagues?sportId=5&market=undefined&isMarketddlChange=false&isTabClick=true&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadTopLeagues?sportId=5&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

	@Override
	public Response getTennisUpcomingInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadMatchesDaily?sportId=5&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadMatchesDaily?sportId=5&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false");
	}

	@Override
	public Response getTennisAllLeagueInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadAllLeagues?sportId=5&btnText=showless&isTabClick=true&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadAllLeagues?sportId=5&btnText=showless&isTabClick=true");
	}

	@Override
	public Response getTennisLiveInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadLiveSport?sportId=5&market=undefined&isMarketddlChange=false&isTabClick=true",
				"https://cbtfsports365.com/Sport/LoadLiveSport?sportId=5&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

	@Override
	public Response getBasketballTopLeagueInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadTopLeagues?sportId=2&market=undefined&isMarketddlChange=false&isTabClick=true&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadTopLeagues?sportId=2&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

	@Override
	public Response getBasketballUpcomingInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadMatchesDaily?sportId=2&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadMatchesDaily?sportId=2&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false");
	}

	@Override
	public Response getBasketballAllLeagueInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadAllLeagues?sportId=2&btnText=showless&isTabClick=true&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadAllLeagues?sportId=2&btnText=showless&isTabClick=true");
	}

	@Override
	public Response getBasketballLiveInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadLiveSport?sportId=2&market=undefined&isMarketddlChange=false&isTabClick=true",
				"https://cbtfsports365.com/Sport/LoadLiveSport?sportId=2&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

	@Override
	public Response getSoccerTopLeagueInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadTopLeagues?sportId=1&market=undefined&isMarketddlChange=false&isTabClick=true&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadTopLeagues?sportId=1&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

	@Override
	public Response getSoccerUpcomingInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadMatchesDaily?sportId=1&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadMatchesDaily?sportId=1&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false");
	}

	@Override
	public Response getSoccerAllLeagueInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadAllLeagues?sportId=1&btnText=showless&isTabClick=true&daysFilter=4&dayFilterClicked=false",
				"https://cbtfsports365.com/Sport/LoadAllLeagues?sportId=1&btnText=showless&isTabClick=true");
	}

	@Override
	public Response getSoccerLiveInfo() {
		return getDataFromUrl(
				"https://starbet.ng/Sport/LoadLiveSport?sportId=1&market=undefined&isMarketddlChange=false&isTabClick=true",
				"https://cbtfsports365.com/Sport/LoadLiveSport?sportId=1&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

}
