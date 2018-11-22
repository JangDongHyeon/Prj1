package com.spring.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.member.dvo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	  @Autowired
	  SqlSession sqlSession;
	@Override
	public boolean loginCheck(MemberVO vo)throws Exception {
		// TODO Auto-generated method stub
		String check=sqlSession.selectOne("member.loginCheck",vo);
	
		return (check==null)?false:true;
				
	}

	@Override
	public void join(MemberVO vo)throws Exception {
		// TODO Auto-generated method stub
	sqlSession.insert("member.join",vo);
	}

	@Override
	public MemberVO getMember(MemberVO vo) throws Exception{
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.getMember",vo);
	}

	@Override
	public boolean checkId(String id) throws Exception {
		// TODO Auto-generated method stub
		int userId=sqlSession.selectOne("member.checkId",id);
	
		return (userId==1)?false:true;
	}

	@Override
	public String findEmail(String email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.findEmail",email);
	}

}
