package com.spring.shoping.dvo;

public class Critia {

	private int page;
	private int numPage;
	private int startPage;
	private int endPage;
	
	public Critia() {
		// TODO Auto-generated constructor stub
		this.page=1;
		this.numPage=9;
		setStartPage();
		setEndPage();
		
	}
	
	
	
	public int getStartPage() {
		return startPage;
	}



	public void setStartPage() {
		this.startPage = (this.page-1)*this.numPage+1;
	}



	public int getEndPage() {
		return endPage;
	}



	public void setEndPage() {
		this.endPage = this.startPage+this.numPage-1;
	}



	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if(page<=0) {
			this.page=1;
			return;
		}
		this.page = page;
	}
	public int getNumPage() {
		return numPage;
	}
	public void setNumPage(int numPage) {
		if(numPage<=5||numPage>=100) {
			this.numPage=9;
			return;
		}
		this.numPage = numPage;
	}



	
}
