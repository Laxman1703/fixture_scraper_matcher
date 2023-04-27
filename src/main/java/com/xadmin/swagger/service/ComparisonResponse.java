package com.xadmin.swagger.service;

import org.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComparisonResponse {
	
	    private String comparedWith;
	    private String comparedSite;
	    private JSONArray cbtfFixtureIds;
	    private int cbtfFixtureCount;
	    private JSONArray starbetFixtureIds;
	    private int starbetFixtureCount;
	    private JSONArray cbtfMissingFixtureIds;
	    private JSONArray starbetMissingFixtureIds;

	    public ComparisonResponse(String comparedWith, String comparedSite, JSONArray cbtfFixtureIds, int cbtfFixtureCount,
	                              JSONArray starbetFixtureIds, int starbetFixtureCount, JSONArray cbtfMissingFixtureIds,
	                              JSONArray starbetMissingFixtureIds) {
	        this.comparedWith = comparedWith;
	        this.comparedSite = comparedSite;
	        this.cbtfFixtureIds = cbtfFixtureIds;
	        this.cbtfFixtureCount = cbtfFixtureCount;
	        this.starbetFixtureIds = starbetFixtureIds;
	        this.starbetFixtureCount = starbetFixtureCount;
	        this.cbtfMissingFixtureIds = cbtfMissingFixtureIds;
	        this.starbetMissingFixtureIds = starbetMissingFixtureIds;
	    }

		public String getComparedWith() {
			return comparedWith;
		}

		public void setComparedWith(String comparedWith) {
			this.comparedWith = comparedWith;
		}

		public String getComparedSite() {
			return comparedSite;
		}

		public void setComparedSite(String comparedSite) {
			this.comparedSite = comparedSite;
		}

		public JSONArray getCbtfFixtureIds() {
			return cbtfFixtureIds;
		}

		public void setCbtfFixtureIds(JSONArray cbtfFixtureIds) {
			this.cbtfFixtureIds = cbtfFixtureIds;
		}

		public int getCbtfFixtureCount() {
			return cbtfFixtureCount;
		}

		public void setCbtfFixtureCount(int cbtfFixtureCount) {
			this.cbtfFixtureCount = cbtfFixtureCount;
		}

		public JSONArray getStarbetFixtureIds() {
			return starbetFixtureIds;
		}

		public void setStarbetFixtureIds(JSONArray starbetFixtureIds) {
			this.starbetFixtureIds = starbetFixtureIds;
		}

		public int getStarbetFixtureCount() {
			return starbetFixtureCount;
		}

		public void setStarbetFixtureCount(int starbetFixtureCount) {
			this.starbetFixtureCount = starbetFixtureCount;
		}

		public JSONArray getCbtfMissingFixtureIds() {
			return cbtfMissingFixtureIds;
		}

		public void setCbtfMissingFixtureIds(JSONArray cbtfMissingFixtureIds) {
			this.cbtfMissingFixtureIds = cbtfMissingFixtureIds;
		}

		public JSONArray getStarbetMissingFixtureIds() {
			return starbetMissingFixtureIds;
		}

		public void setStarbetMissingFixtureIds(JSONArray starbetMissingFixtureIds) {
			this.starbetMissingFixtureIds = starbetMissingFixtureIds;
		}

	}


