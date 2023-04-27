package com.xadmin.swagger.vo;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private String compared_with;
	private String compared_site;
	
	private List<String> cbtf_fixture_ids;
	private List<String> starbet_fixture_ids;
	private List<String> cric10_fixture_ids;
	
	private Set<String> cbtf_missing_fixture_ids;
	private Set<String> starbet_missing_fixture_ids;
	private Set<String> cric10_missing_fixture_ids;
	
	private int starbet_fixture_count;
	private int cbtf_fixture_count;
	private int cric10_fixture_count;
	
	public String getCompared_with() {
		return compared_with;
	}
	public void setCompared_with(String compared_with) {
		this.compared_with = compared_with;
	}
	public String getCompared_site() {
		return compared_site;
	}
	public void setCompared_site(String compared_site) {
		this.compared_site = compared_site;
	}
	public List<String> getCbtf_fixture_ids() {
		return cbtf_fixture_ids;
	}
	public void setCbtf_fixture_ids(List<String> cbtf_fixture_ids) {
		this.cbtf_fixture_ids = cbtf_fixture_ids;
	}
	public List<String> getStarbet_fixture_ids() {
		return starbet_fixture_ids;
	}
	public void setStarbet_fixture_ids(List<String> starbet_fixture_ids) {
		this.starbet_fixture_ids = starbet_fixture_ids;
	}
	public List<String> getCric10_fixture_ids() {
		return cric10_fixture_ids;
	}
	public void setCric10_fixture_ids(List<String> cric10_fixture_ids) {
		this.cric10_fixture_ids = cric10_fixture_ids;
	}
	public Set<String> getCric10_missing_fixture_ids() {
		return cric10_missing_fixture_ids;
	}
	public void setCric10_missing_fixture_ids(Set<String> cric10_missing_fixture_ids) {
		this.cric10_missing_fixture_ids = cric10_missing_fixture_ids;
	}
	public int getCric10_fixture_count() {
		return cric10_fixture_count;
	}
	public void setCric10_fixture_count(int cric10_fixture_count) {
		this.cric10_fixture_count = cric10_fixture_count;
	}
	public Set<String> getCbtf_missing_fixture_ids() {
		return cbtf_missing_fixture_ids;
	}
	public void setCbtf_missing_fixture_ids(Set<String> cbtf_missing_fixture_ids) {
		this.cbtf_missing_fixture_ids = cbtf_missing_fixture_ids;
	}
	public Set<String> getStarbet_missing_fixture_ids() {
		return starbet_missing_fixture_ids;
	}
	public void setStarbet_missing_fixture_ids(Set<String> starbet_missing_fixture_ids) {
		this.starbet_missing_fixture_ids = starbet_missing_fixture_ids;
	}
	public int getStarbet_fixture_count() {
		return starbet_fixture_count;
	}
	public void setStarbet_fixture_count(int starbet_fixture_count) {
		this.starbet_fixture_count = starbet_fixture_count;
	}
	public int getCbtf_fixture_count() {
		return cbtf_fixture_count;
	}
	public void setCbtf_fixture_count(int cbtf_fixture_count) {
		this.cbtf_fixture_count = cbtf_fixture_count;
	}
	
	
}
