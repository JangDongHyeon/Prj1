package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.dvo.Critia;
import com.spring.board.dao.BRplyDAO;
import com.spring.board.dvo.BReply;

@Service
public class BReplyServiceImpl implements BReplyService {

	@Autowired
	private BRplyDAO bReplyDAO;

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

	@Override
	public boolean breplyDelete(int r_no) {
		// TODO Auto-generated method stub
		return bReplyDAO.breplyDelete(r_no);
	}

	@Override
	public void bReplyNewInsert(BReply replyVO) {
		// TODO Auto-generated method stub
		bReplyDAO.bReplyNewInsert(replyVO);
	}

	@Override
	public int breplyCount() {
		// TODO Auto-generated method stub
		return bReplyDAO.breplyCount();
	}

	
	
	
}
