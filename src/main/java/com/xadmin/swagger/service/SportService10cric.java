
package com.xadmin.swagger.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xadmin.swagger.vo.Response;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface SportService10cric {

	Response getCricketTopLeagueInfo();

	Response getCricketsUpcomingInfo();

	Response getCricketAllLeagueInfo();

	Response getCricketLiveInfo();

}
