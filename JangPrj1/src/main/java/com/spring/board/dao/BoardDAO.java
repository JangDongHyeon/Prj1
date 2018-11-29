package com.spring.board.dao;

import java.util.List;

import com.spring.admin.dvo.SearchVO;
import com.spring.board.dvo.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> boardSelect(SearchVO vo);
	public int boardPageCount(SearchVO vo);
	public boolean boardInsert(BoardVO vo);
	public boolean boardUpdate(BoardVO vo);
	public void boardCntUp(int bno);
	public boolean boardDelete(int bno);
	public BoardVO boardDetail(int bno);
	
	
	
	
}
