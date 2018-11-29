package com.spring.qna.service;

import java.util.List;

import com.spring.qna.dvo.QnaVO;



public interface QnaService {
	public List<QnaVO> qnaList(String userId);              
	public void qnaInsert(QnaVO vo);           
	public QnaVO qnaDetail(int qseq);             
	public void qnaUpdateContent(QnaVO vo);    
	public void qnaDel(int qseq);            
	public void qnaNewInsert(QnaVO vo);   
	public void qnaDelAll(int p);
}
