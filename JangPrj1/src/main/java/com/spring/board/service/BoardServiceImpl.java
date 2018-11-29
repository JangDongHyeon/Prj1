package com.spring.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.dvo.SearchVO;
import com.spring.board.dao.BoardDAO;
import com.spring.board.dvo.BoardVO;
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> boardSelect(SearchVO vo) {
		// TODO Auto-generated method stub
		
		
		return boardDAO.boardSelect(vo);
	}

	@Override
	public int boardPageCount(SearchVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.boardPageCount(vo);
	}

	@Override
	public boolean boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		
		return boardDAO.boardInsert(vo);
	}

	@Override
	public boolean boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDAO.boardUpdate(vo);
	}

	@Override
	public boolean boardDelete(int bno) {
		// TODO Auto-generated method stub
		return boardDAO.boardDelete(bno);
	}

	@Override
	public BoardVO boardDetail(int bno,HttpSession session) {
		// TODO Auto-generated method stub
		long update_time=0;
		if(session.getAttribute("update_time_"+bno)!=null) {
			update_time=(long)session.getAttribute("update_time_"+bno);
		}
		long currey_time=System.currentTimeMillis();
		if(currey_time-update_time>5*1000) {
			boardDAO.boardCntUp(bno);
			session.setAttribute("update_time_"+bno,currey_time);
		}
		return boardDAO.boardDetail(bno);
	}

}
