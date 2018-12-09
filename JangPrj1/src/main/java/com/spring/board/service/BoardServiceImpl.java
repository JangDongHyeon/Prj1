package com.spring.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.admin.dvo.SearchVO;
import com.spring.board.dao.BoardDAO;
import com.spring.board.dao.BoardFileDAO;
import com.spring.board.dvo.BoardFileVO;
import com.spring.board.dvo.BoardVO;
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private BoardFileDAO boardFileDAO;
	
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
	@Transactional
	@Override
	public void boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		boardDAO.boardInsert(vo);
		
		if(vo.getBoardFileList()==null||vo.getBoardFileList().size()<=0) {
			return;
		}
		for(BoardFileVO v:vo.getBoardFileList()) {
			v.setBno(vo.getBno());
			boardFileDAO.insert(v);
		}
		
	
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
	@Transactional
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

	@Override
	public List<BoardFileVO> getBoardFileList(int bno) {
		// TODO Auto-generated method stub
		
		return boardFileDAO.fileByBno(bno);
	}

}
