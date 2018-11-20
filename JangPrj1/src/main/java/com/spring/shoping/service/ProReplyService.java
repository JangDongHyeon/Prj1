package com.spring.shoping.service;

import java.util.List;

import com.spring.shoping.dvo.ProReplyVO;

public interface ProReplyService {
	public List<ProReplyVO> list(int pseq);
	public void insert(ProReplyVO vo);
	public void update(ProReplyVO vo);
	public void delete(int rid);
	public ProReplyVO getReply(int rid);
}
