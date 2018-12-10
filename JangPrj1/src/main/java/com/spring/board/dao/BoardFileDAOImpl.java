package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dvo.BoardFileVO;
@Repository
public class BoardFileDAOImpl implements BoardFileDAO {

	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public void insert(BoardFileVO vo) {
		// TODO Auto-generated method stub
		sqlsession.insert("bFile.insert",vo);
	}

	@Override
	public void delete(String uuid) {
		// TODO Auto-generated method stub
		sqlsession.delete("bFile.delete",uuid);
	}

	@Override
	public List<BoardFileVO> fileByBno(int bno) {
		// TODO Auto-generated method stub
		return sqlsession.selectList("bFile.findByBno",bno);
	}

	@Override
	public void deleteAll(int bno) {
		// TODO Auto-generated method stub
		sqlsession.delete("bFile.deleteAll",bno);
	}

	@Override
	public List<BoardFileVO> getOldFiles() {
		// TODO Auto-generated method stub
		return sqlsession.selectList("bFile.getOldFiles");
	}

}
