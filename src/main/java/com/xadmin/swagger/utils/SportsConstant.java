package com.xadmin.swagger.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SportsConstant {

	public static final String SPORT_TENNIS = "TENNIS";
	public static final String SPORT_SOCCER = "SOCCER";
	public static final String SPORT_BASKETBALL = "BASKETBALL";
	public static final String SPORT_CRICKET = "CRICKET";
}
