package com.spring.admin.dvo;

public class SearchVO extends Critia {
	private String  search;
	private String keyword;
	
	public SearchVO() {
		// TODO Auto-generated constructor stub
		this.search="al";
		this.keyword="";
	}
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
