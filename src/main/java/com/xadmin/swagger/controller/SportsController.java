package com.xadmin.swagger.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xadmin.swagger.service.ComparisonResponse;
import com.xadmin.swagger.service.SportService;
import com.xadmin.swagger.utils.SportsConstant;
import com.xadmin.swagger.vo.CbtfNStrarbetResponse;

@RestController
@RequestMapping("/api")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportsController {

	@Autowired
	SportService sportsService;

	/*
	 * public static class WelcomeMessage { public String sportName; public String
	 * message; }
	 * 
	 * public static class categoryResponse { public String sportName; public String
	 * category; public String response;
	 * 
	 * }
	 */

	@GetMapping("/ping")
	public static String ping() {
		return "pong";
	}

	@GetMapping("/{sportName}")
	public ResponseEntity<CbtfNStrarbetResponse> getCategory(@PathVariable String sportName) {
		CbtfNStrarbetResponse response = new CbtfNStrarbetResponse();

		HttpStatus status;
		if (SportsConstant.SPORT_TENNIS.equalsIgnoreCase(sportName)
				|| SportsConstant.SPORT_BASKETBALL.equalsIgnoreCase(sportName)
				|| SportsConstant.SPORT_SOCCER.equalsIgnoreCase(sportName)) {
			response.setSportName(sportName);
			response.setCatagory(sportName.toUpperCase() + " Contains Four Categories ::" + " 1)Top Leagues"
					+ " 2)Upcoming" + " )All League" + " 4)Live");

			status = HttpStatus.OK;
		} else {
			response.setSportName(null);
			response.setResponseMessage("Invalid sport name");
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, status);
	}

	@GetMapping("/{sportName}/{category}")
	@ResponseBody
	public ResponseEntity<CbtfNStrarbetResponse> getDetails(@PathVariable String sportName,
			@PathVariable String category) {
		CbtfNStrarbetResponse response = new CbtfNStrarbetResponse();
		HttpStatus status = null;

		if (sportName.equalsIgnoreCase(SportsConstant.SPORT_TENNIS)) {
			if (category.equalsIgnoreCase("TopLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getTennisTopLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Upcoming")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getTennisUpcomingInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("AllLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getTennisAllLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Live")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getTennisLiveInfo());
				status = HttpStatus.OK;
			} else {
				response.setSportName(null);
				response.setCatagory(null);
				response.setResponseMessage("Invalid sub-endpoint");
				status = HttpStatus.BAD_REQUEST;
			}
		} else if (sportName.equalsIgnoreCase("Basketball")) {
			if (category.equalsIgnoreCase("TopLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getBasketballTopLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Upcoming")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getBasketballUpcomingInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("AllLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getBasketballAllLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Live")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getBasketballLiveInfo());
				status = HttpStatus.OK;
			} else {
				response.setSportName(null);
				response.setCatagory(null);
				response.setResponseMessage("Invalid sub-endpoint");
				status = HttpStatus.BAD_REQUEST;
			}
		} else if (sportName.equalsIgnoreCase("Soccer")) {
			if (category.equalsIgnoreCase("TopLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getSoccerTopLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Upcoming")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getSoccerUpcomingInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("AllLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getSoccerAllLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Live")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService.getSoccerLiveInfo());
				status = HttpStatus.OK;
			} else {
				response.setSportName(null);
				response.setCatagory(null);
				response.setResponseMessage("Invalid sub-endpoint");
				status = HttpStatus.BAD_REQUEST;
			}
		} else {
			response.setSportName(null);
			response.setCatagory(null);
			response.setResponseMessage("Invalid sport name");
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<>(response, status);

	}
}