package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.QnaDAO;
import com.spring.board.dvo.QnaVO;
@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO qnaDAO;
	@Override
	public List<QnaVO> qnaList(String userId) {
		// TODO Auto-generated method stub
		return qnaDAO.qnaList(userId);
	}

	@Override
	public void qnaInsert(QnaVO vo) {
		// TODO Auto-generated method stub
		qnaDAO.qnaUpdate(vo);
		vo.setbStep(vo.getbStep()+1);
		vo.setbGroup(vo.getbIndent()+1);
		qnaDAO.qnaInsert(vo);
	}

	@Override
	public void qnaUpdateContent(QnaVO vo) {
		// TODO Auto-generated method stub
		qnaDAO.qnaUpdateContent(vo);
	}

	@Override
	public void qnaDel(int qseq) {
		// TODO Auto-generated method stub
		qnaDAO.qnaDel(qseq);
	}

	@Override
	public QnaVO qnaDetail(int qseq) {
		// TODO Auto-generated method stub
		return qnaDAO.qnaDetail(qseq);
		
	}

	@Override
	public void qnaNewInsert(QnaVO vo) {
		// TODO Auto-generated method stub
		qnaDAO.qnaNewInsert(vo);
	}

}
