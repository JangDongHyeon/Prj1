package com.spring.board.dao;

import java.util.List;

import com.spring.board.dvo.BoardFileVO;



public interface BoardFileDAO {
	void insert(BoardFileVO vo);
	void delete(String uuid);
	List<BoardFileVO> fileByBno(int bno);
	
}
