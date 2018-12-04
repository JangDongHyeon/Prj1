package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.admin.dvo.Critia;
import com.spring.board.dao.BRplyDAO;
import com.spring.board.dao.BoardDAO;
import com.spring.board.dvo.BReply;

@Service
public class BReplyServiceImpl implements BReplyService {

	@Autowired
	private BRplyDAO bReplyDAO;
	@Autowired
	private BoardDAO boardDAO;
	@Override
	public List<BReply> bReplyList(int bno,Critia critia) {
		// TODO Auto-generated method stub
		return bReplyDAO.bReplyList(bno,critia);
	}

	@Override
	public boolean breplyCreate(BReply replyVO) {
		// TODO Auto-generated method stub
		bReplyDAO.bReplyUpdateSt(replyVO);
		replyVO.setbStep(replyVO.getbStep()+1);
		replyVO.setbIndent(replyVO.getbIndent()+1);
		return bReplyDAO.breplyCreate(replyVO);
	}

	@Override
	public boolean breplyUpdate(BReply replyVO) {
		// TODO Auto-generated method stub
		
		return bReplyDAO.breplyUpdate(replyVO);
	}
	@Transactional
	@Override
	public void breplyDelete(int r_no) {
		// TODO Auto-generated method stub
		int bno=bReplyDAO.getBoardNo(r_no);
		
		bReplyDAO.breplyDelete(r_no);
		boardDAO.updateReplyCnt(bno, -1);
	}
	@Transactional
	@Override
	public void bReplyNewInsert(BReply replyVO) {
		// TODO Auto-generated method stub
		
		bReplyDAO.bReplyNewInsert(replyVO);
		boardDAO.updateReplyCnt(bReplyDAO.getBoardNo(replyVO.getR_no()),1);
	}

	@Override
	public int breplyCount(int bno) {
		// TODO Auto-generated method stub
		return bReplyDAO.breplyCount(bno);
	}

	
	
	
}
