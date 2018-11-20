package com.spring.shoping.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shoping.dvo.ProReplyVO;
@Repository
public class ProReplyDAOImpl implements ProReplyDAO {
    @Autowired
    private SqlSession sqlSession;
	
	@Override
	public List<ProReplyVO> list(int pseq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("pro_reply.list",pseq);
	}

	@Override
	public void insert(ProReplyVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert("pro_reply.insert",vo);
	}

	@Override
	public void update(ProReplyVO vo) {
		// TODO Auto-generated method stub
		
		sqlSession.update("pro_reply.update",vo);
	}

	@Override
	public void delete(int rid) {
		// TODO Auto-generated method stub
		sqlSession.delete("pro_reply.delete",rid);
	}

	@Override
	public ProReplyVO getReply(int rid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("pro_reply.getReply",rid);
	}

}
