package com.spring.shoping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shoping.dao.ProReplyDAO;
import com.spring.shoping.dvo.ProReplyVO;
@Service
public class ProReplyServiceImpl implements ProReplyService {

	@Autowired
	private ProReplyDAO rdao;
	@Override
	public List<ProReplyVO> list(int pseq) {
		// TODO Auto-generated method stub
		return rdao.list(pseq);
	}

	@Override
	public void insert(ProReplyVO vo) {
		// TODO Auto-generated method stub
		rdao.insert(vo);
	}

	@Override
	public void update(ProReplyVO vo) {
		// TODO Auto-generated method stub
		rdao.update(vo);
	}

	@Override
	public void delete(int rid) {
		// TODO Auto-generated method stub
		rdao.delete(rid);
	}

	@Override
	public ProReplyVO getReply(int rid) {
		// TODO Auto-generated method stub
		return rdao.getReply(rid);
	}

}
