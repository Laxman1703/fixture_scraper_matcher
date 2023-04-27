package com.xadmin.swagger.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xadmin.swagger.vo.Response;

@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportServiceImpl10cric implements SportService10cric {
	List<String> cric10FixtureIds = new ArrayList<>();
	List<String> cbtfFixtureIds = new ArrayList<>();

	private Response getDataFromUrls(String url1, String url2) {
		cric10FixtureIds.clear();
		cbtfFixtureIds.clear();
		JSONArray fixturesArray = new JSONArray();

		int cric10FixtureCount = 0;
		int cbtfFixtureCount = 0;

		for (String url : new String[] { url1, url2 }) {
			try {
				int matchCount = 0;

				List<String> providerFixtureIds = new ArrayList<>();
				URL requestUrl = null;
				HttpURLConnection con = null;
				if (url.equals(url1)) {

					requestUrl = new URL(url1);
					con = (HttpURLConnection) requestUrl.openConnection();
					con.setRequestMethod("GET");
					con.setRequestProperty("x-api-key", "8LKvrh6C2fe8mKXZ");
					con.setRequestProperty("x-client-id", "BFED6DA4-D1B5-4437-665D-08D8EE99AC1C");
				} else {
					requestUrl = new URL(url2);
					con = (HttpURLConnection) requestUrl.openConnection();
					con.setRequestMethod("GET");
				}
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
					cric10FixtureIds.addAll(providerFixtureIds);
					cric10FixtureCount += providerFixtureIds.size();
				} else {
					cbtfFixtureIds.addAll(providerFixtureIds);
					cbtfFixtureCount += providerFixtureIds.size();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("compared_with", "Cric10");
		jsonResponse.put("compared_site", "CBTF");

		jsonResponse.put("cbtf_fixture_ids", new JSONArray(cbtfFixtureIds));
		jsonResponse.put("cbtf_fixture_count", cbtfFixtureCount);

		jsonResponse.put("cric10_fixture_ids", new JSONArray(cric10FixtureIds));
		jsonResponse.put("cric10_fixture_count", cric10FixtureCount);

		Set<String> cbtfSet = new HashSet<>(cbtfFixtureIds);
		Set<String> cric10Set = new HashSet<>(cric10FixtureIds);

		Set<String> missingInCbtf = new HashSet<>(cric10Set);
		missingInCbtf.removeAll(cbtfSet);
		Set<String> missingInCric10 = new HashSet<>(cbtfSet);
		missingInCric10.removeAll(cric10Set);

		jsonResponse.put("cbtf_missing_fixture_ids", new JSONArray(missingInCbtf));
		jsonResponse.put("cric10_missing_fixture_ids", new JSONArray(missingInCric10));

		Response response = new Response();
		response.setCompared_with("Cric10");
		response.setCompared_site("CBTF");

		response.setCbtf_fixture_ids(cbtfFixtureIds);
		response.setCbtf_fixture_count(cbtfFixtureCount);

		response.setCric10_fixture_ids(cric10FixtureIds);
		response.setCric10_fixture_count(cric10FixtureCount);

		response.setCbtf_missing_fixture_ids(missingInCbtf);
		response.setCric10_missing_fixture_ids(missingInCric10);

		return response;
	}

	@Override
	public Response getCricketTopLeagueInfo() {
		return getDataFromUrls(" https://prod-platformapi.song88.com/v1/sportsbook/sports/topleaguefixtures?SportId=21",
				"https://cbtfsports365.com/Sport/LoadTopLeagues?sportId=21&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

	@Override
	public Response getCricketsUpcomingInfo() {
		return getDataFromUrls(
				"https://prod-platformapi.song88.com/v1/sportsbook/sports/dailyfixtures?SportId=21&dateUTC=04-12-2023&minuteUTCOffset=330",
				"https://cbtfsports365.com/Sport/LoadMatchesDaily?sportId=21&market=undefined&btnId=undefined&isMarketddlChange=false&timeZoneOffset=330&isTabClick=true&isButtonClick=false");
	}

	@Override
	public Response getCricketAllLeagueInfo() {
		return getDataFromUrls("https://prod-platformapi.song88.com/v1/sportsbook/sports/allleagues?SportId=21",
				"https://cbtfsports365.com/Sport/LoadAllLeagues?sportId=21&btnText=showless&isTabClick=true");
	}

	@Override
	public Response getCricketLiveInfo() {
		return getDataFromUrls("https://prod-platformapi.song88.com/v1/sportsbook/sports/livefixtures?sportId=21",
				"https://cbtfsports365.com/Sport/LoadLiveSport?sportId=21&market=undefined&isMarketddlChange=false&isTabClick=true");
	}

}
