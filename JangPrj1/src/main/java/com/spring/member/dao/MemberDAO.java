package com.spring.member.dao;

import com.spring.member.dvo.MemberVO;

public interface MemberDAO {
	public boolean loginCheck(MemberVO vo)throws Exception;
	public void join(MemberVO vo)throws Exception;
	public MemberVO getMember(MemberVO vo)throws Exception;
	public boolean checkId(String id)throws Exception;
	public String findEmail(String email);
	
	
}
