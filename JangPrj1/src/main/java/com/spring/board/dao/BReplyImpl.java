package com.spring.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.admin.dvo.Critia;
import com.spring.board.dvo.BReply;
@Repository
public class BReplyImpl implements BRplyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BReply> bReplyList(int bno,Critia critia) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<>();
		map.put("bno",bno);
		map.put("critia",critia);
		return sqlSession.selectList("breply.bReplyList",map);
	}

	@Override
	public boolean breplyCreate(BReply replyVO) {
		// TODO Auto-generated method stub
		int result=sqlSession.insert("breply.breplyCreate",replyVO);
	
		return result>=1?true:false;
	}

	@Override
	public boolean breplyUpdate(BReply replyVO) {
		// TODO Auto-generated method stub
		int result=sqlSession.update("breply.breplyUpdate",replyVO);
		
		return result>=1?true:false;
	}

	@Override
	public boolean breplyDelete(int r_no) {
		// TODO Auto-generated method stub
		int result=sqlSession.delete("breply.breplyDelete",r_no);
		
		return result>=1?true:false;
	}

	@Override
	public void bReplyNewInsert(BReply replyVO) {
		// TODO Auto-generated method stub
		sqlSession.insert("breply.bReplyNewInsert",replyVO);
	}

	@Override
	public void bReplyUpdateSt(BReply replyVO) {
		// TODO Auto-generated method stub
		sqlSession.update("breply.bReplyUpdateSt",replyVO);
	}

	@Override
	public int breplyCount() {
	
		return sqlSession.selectOne("breply.breplyCount");
	}

}
