package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.admin.dvo.SearchVO;
import com.spring.board.dvo.BoardVO;
@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardVO> boardSelect(SearchVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardSelect",vo);
	}

	@Override
	public int boardPageCount(SearchVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardPageCount",vo);
	}

	@Override
	public boolean boardInsert(BoardVO vo) {
		// TODO Auto-generated method stub
		int result=sqlSession.insert("board.boardInsert",vo);
		return result>=1?true:false;
	}

	@Override
	public boolean boardUpdate(BoardVO vo) {
		// TODO Auto-generated method stub
		int result=sqlSession.update("board.boardUpdate",vo);
		return result>=1?true:false;
	}

	@Override
	public void boardCntUp(int bno) {
		// TODO Auto-generated method stub
		sqlSession.update("board.boardCntUp",bno);
	}

	@Override
	public boolean boardDelete(int bno) {
		// TODO Auto-generated method stub
		int result=sqlSession.update("board.boardDelete",bno);
		return result>=1?true:false;
	}

	@Override
	public BoardVO boardDetail(int bno) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardDetail",bno);
	}

}
