package com.xadmin.swagger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) throws IOException {
		
		  String url =
		  "https://prod-platformapi.song88.com/v1/sportsbook/sports/livefixtures?sportId=21";
		  URL requestUrl = new URL(url); HttpURLConnection con = (HttpURLConnection)
		  requestUrl.openConnection(); con.setRequestMethod("GET");
		  con.setRequestProperty("Accept", "application/json");
		  con.setRequestProperty("x-api-key", "8LKvrh6C2fe8mKXZ");
		  con.setRequestProperty("x-client-id",
		  "BFED6DA4-D1B5-4437-665D-08D8EE99AC1C");
		  con.setRequestProperty("x-country-code", "IN");
		  con.setRequestProperty("x-currency-code", "INR");
		  
		  BufferedReader in = new BufferedReader(new
		  InputStreamReader(con.getInputStream())); String inputLine; StringBuffer
		  response = new StringBuffer();
		  
		  while ((inputLine = in.readLine()) != null) { response.append(inputLine); }
		  in.close();
		  
		  System.out.println("response: "+response.toString());
		 
		
		
		/*try {
            URL url = new URL(
                    TOP League      "https://prod-platformapi.song88.com/v1/sportsbook/sports/topleaguefixtures?SportId=21"
                    ALL League      //"https://prod-platformapi.song88.com/v1/sportsbook/sports/allleagues?SportId=21"
                    LIVE Now        //"https://prod-platformapi.song88.com/v1/sportsbook/sports/livefixtures?sportId=21"
                    UPCOMING        //"https://prod-platformapi.song88.com/v1/sportsbook/sports/dailyfixtures?SportId=21&dateUTC=04-12-2023&minuteUTCOffset=330"
            );
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-api-key", "8LKvrh6C2fe8mKXZ");
            con.setRequestProperty("x-client-id", "BFED6DA4-D1B5-4437-665D-08D8EE99AC1C");
            con.setRequestProperty("x-country-code", "IN");
            con.setRequestProperty("x-currency-code", "INR");


            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder responseContent = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseContent.append(inputLine);
                }
//              System.out.println(responseContent);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseContent.toString());
                JsonNode fixtures = rootNode.get("result").get("fixtures");

                for (JsonNode fixture : fixtures) {
                    String sportId = fixture.get("sportId").asText();
                    String sportName = fixture.get("sportName").asText();
                    String leagueName = fixture.get("leagueName").asText();
                    String fixtureId = fixture.get("fixtureId").asText();
                    String providerFixtureId = fixture.get("providerFixtureId").asText();
                    String fixtureName = fixture.get("fixtureName").asText();
                    System.out.println();
                    System.out.println("Sport ID: " + sportId);
                    System.out.println("Sport Name: " + sportName);
                    System.out.println("League Name: " + leagueName);
                    System.out.println("Fixture ID: " + fixtureId);
                    System.out.println("Provider Fixture ID: " + providerFixtureId);
                    System.out.println("Fixture Name : " + fixtureName);
                }
            } else {
                System.out.println("Error parsing JSON response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}*/
	}
}
