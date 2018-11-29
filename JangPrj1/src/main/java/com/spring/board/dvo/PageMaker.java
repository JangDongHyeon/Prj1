package com.spring.board.dvo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.shoping.dvo.Critia;
import com.spring.shoping.dvo.SearchVO;

public class PageMaker {

	private int board_num_page;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int startBlock;
	private int endBlock;
	private boolean nextPage;
	private boolean perPage;
	private Critia critia;
	private int startPageto;
	private int endPageto;

	
	
	public int getBoard_num_page() {
		return board_num_page;
	}

	public void setBoard_num_page(int board_num_page) {
		this.board_num_page = board_num_page;
	}

	public Critia getCritia() {
		return critia;
	}

	public void setCritia(Critia critia) {
		this.critia = critia;
	}

	public PageMaker(Critia critia, int count) {
		this.board_num_page=10;
		setCritia(critia);
		setTotalPage(count);
		setStartPageto();
		setEndPageto();
		setBlock();

	}

	public void setBlock() {
		startBlock = ((critia.getPage() - 1) / board_num_page) * board_num_page + 1;
		endBlock = startBlock + board_num_page - 1;
		if (endBlock > totalPage) {
			endBlock = totalPage;
		}
		perPage = (critia.getPage() == 1) ? false : true;
		nextPage = endBlock >= totalPage ? false : true;

	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int count) {

		int total = count / critia.getNumPage();
		if (totalPage % critia.getNumPage() > 0) {
			total++;
		}
		this.totalPage = total;

	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isPerPage() {
		return perPage;
	}

	public void setPerPage(boolean perPage) {
		this.perPage = perPage;
	}

	public String makeSearch(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
				.queryParam("numPage", critia.getNumPage()).queryParam("search", ((SearchVO) critia).getSearch())
				.queryParam("keyword", encoding(((SearchVO) critia).getKeyword())).build();
		return uriComponents.toUriString();

	}

	public String encoding(String keyword) {
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
			// TODO: handle exception
		}
	}

	public int getStartPageto() {
		return startPageto;
	}

	public void setStartPageto() {
		this.startPageto = (critia.getPage() - 1) * critia.getNumPage() + 1;
	}

	public int getEndPageto() {
		return endPageto;
	}

	public void setEndPageto() {
		this.endPageto = getStartPageto() + critia.getNumPage() - 1;
	}

}
