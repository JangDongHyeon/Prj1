package com.spring.board.dao;

import java.util.List;

import com.spring.admin.dvo.Critia;
import com.spring.board.dvo.BReply;

public interface BRplyDAO {
	List<BReply> bReplyList(int bno,Critia critia);
	boolean breplyCreate(BReply replyVO);
	boolean breplyUpdate(BReply replyVO);
	boolean breplyDelete(int r_no);
	void bReplyNewInsert(BReply replyVO);
	void bReplyUpdateSt(BReply replyVO);
	int breplyCount();
}
