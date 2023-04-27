package com.xadmin.swagger.service;

import com.xadmin.swagger.vo.Response;

public interface SportService {

	Response getTennisTopLeagueInfo();

	Response getTennisUpcomingInfo();

	Response getTennisAllLeagueInfo();

	Response getTennisLiveInfo();

	Response getBasketballTopLeagueInfo();

	Response getBasketballUpcomingInfo();

	Response getBasketballAllLeagueInfo();

	Response getBasketballLiveInfo();

	Response getSoccerTopLeagueInfo();

	Response getSoccerUpcomingInfo();

	Response getSoccerAllLeagueInfo();

	Response getSoccerLiveInfo();

}
