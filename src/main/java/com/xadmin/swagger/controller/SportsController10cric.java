package com.xadmin.swagger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xadmin.swagger.service.SportService10cric;
import com.xadmin.swagger.utils.SportsConstant;
import com.xadmin.swagger.vo.CbtfNStrarbetResponse;

@Controller
@RequestMapping("/api2")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportsController10cric {

	@Autowired
	SportService10cric sportsService10cric;

//	@GetMapping("/Ding")
//	public static String ding() {
//		return "Dong";
//	}

	@GetMapping("/{sportName}")
	public ResponseEntity<CbtfNStrarbetResponse> getCategory(@PathVariable String sportName) {
		CbtfNStrarbetResponse response = new CbtfNStrarbetResponse();

		HttpStatus status;
		if (SportsConstant.SPORT_CRICKET.equalsIgnoreCase(sportName)){
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

		if (sportName.equalsIgnoreCase(SportsConstant.SPORT_CRICKET)) {
			if (category.equalsIgnoreCase("TopLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService10cric.getCricketTopLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Upcoming")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService10cric.getCricketsUpcomingInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("AllLeague")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService10cric.getCricketAllLeagueInfo());
				status = HttpStatus.OK;
			} else if (category.equalsIgnoreCase("Live")) {
				response.setSportName(sportName);
				response.setCatagory(category);
				response.setResponse(sportsService10cric.getCricketLiveInfo());
				status = HttpStatus.OK;
			} else {
				response.setSportName(null);
				response.setCatagory(null);
				response.setResponseMessage("Invalid sub-endpoint");
				status = HttpStatus.BAD_REQUEST;
			}
		}
		return new ResponseEntity<>(response, status);
	}

}