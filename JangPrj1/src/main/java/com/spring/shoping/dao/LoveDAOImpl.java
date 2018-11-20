package com.spring.shoping.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoveDAOImpl implements LoveDAO {
    
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int prLoveCount(int pseq) {
		// TODO Auto-generated method stub
	
		
		
		return sqlSession.selectOne("love.prLoveCount",pseq);
	}

	@Override
	public void prLovePlus(int pseq) {
		// TODO Auto-generated method stub
		sqlSession.update("love.prLovePlus",pseq);
	}

	@Override
	public void prLoveMemberI(int pseq, String pr_member) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pseq",pseq);
		map.put("pr_member",pr_member);
		
		sqlSession.insert("love.prLoveMemberI",map);
	}

	@Override
	public boolean prLoveMemberCh(int pseq, String pr_member) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pseq",pseq);
		map.put("pr_member",pr_member);
		int result=sqlSession.selectOne("love.prLoveMemberCh",map);
		
		return (result>=1)?false:true;
	}

	@Override
	public void prLoveM(int pseq) {
		// TODO Auto-generated method stub
		sqlSession.update("love.prLoveM",pseq);
	}

	@Override
	public void prLoveMemberD(int pseq, String pr_member) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pseq",pseq);
		map.put("pr_member",pr_member);
		sqlSession.delete("love.prLoveMemberD",map);

	}

	@Override
	public void prLoveInsert(int pseq) {
		// TODO Auto-generated method stub
		sqlSession.insert("love.prLoveInsert",pseq);
	}

	@Override
	public void prLoveD(int pseq) {
		// TODO Auto-generated method stub
		sqlSession.delete("love.prLoveD",pseq);
		
	}

}
