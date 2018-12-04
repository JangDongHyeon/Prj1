package com.spring.board.service;

import java.util.List;

import com.spring.admin.dvo.Critia;
import com.spring.board.dvo.BReply;

public interface BReplyService {
	List<BReply> bReplyList(int bno,Critia critia);
	boolean breplyCreate(BReply replyVO);
	boolean breplyUpdate(BReply replyVO);
	void breplyDelete(int r_no);
	void bReplyNewInsert(BReply replyVO);
	int breplyCount(int bno);
}
