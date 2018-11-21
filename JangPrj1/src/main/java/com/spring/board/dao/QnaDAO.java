package com.spring.board.dao;

import java.util.List;

import com.spring.board.dvo.QnaVO;

public interface QnaDAO {
	public List<QnaVO> qnaList(String userId);
	public void qnaInsert(QnaVO vo);
	public void qnaUpdate(QnaVO vo);
	public void qnaUpdateContent(QnaVO vo);
	public void qnaDel(int qseq);
	public QnaVO qnaDetail(int qseq);
	public void qnaNewInsert(QnaVO vo);
	public void qnaDelAll(int p);
}
