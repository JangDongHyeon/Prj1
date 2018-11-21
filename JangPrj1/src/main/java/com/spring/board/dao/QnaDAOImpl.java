package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dvo.QnaVO;
@Repository
public class QnaDAOImpl implements QnaDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<QnaVO> qnaList(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("qna.qnaList",userId);
	}

	@Override
	public void qnaInsert(QnaVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert("qna.qnaInsert",vo);
	}

	@Override
	public void qnaUpdate(QnaVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update("qna.qnaUpdate",vo);
	}

	@Override
	public void qnaUpdateContent(QnaVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update("qna.qnaUpdateContent",vo);
	}

	@Override
	public void qnaDel(int qseq) {
		// TODO Auto-generated method stub
		sqlSession.delete("qna.qnaDel",qseq);
	}

	@Override
	public QnaVO qnaDetail(int qseq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("qna.qnaDetail",qseq);
	}

	@Override
	public void qnaNewInsert(QnaVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert("qna.qnaNewInsert",vo);
	}

}
