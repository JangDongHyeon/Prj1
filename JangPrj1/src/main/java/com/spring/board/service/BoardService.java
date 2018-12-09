package com.spring.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.admin.dvo.SearchVO;
import com.spring.board.dvo.BoardFileVO;
import com.spring.board.dvo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardSelect(SearchVO vo);
	public int boardPageCount(SearchVO vo);
	public void boardInsert(BoardVO vo);
	public boolean boardUpdate(BoardVO vo);
	public boolean boardDelete(int bno);
	public BoardVO boardDetail(int bno,HttpSession session);
	public List<BoardFileVO> getBoardFileList(int bno);
	
	
}
