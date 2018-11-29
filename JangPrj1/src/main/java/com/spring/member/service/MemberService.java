package com.spring.member.service;

import javax.servlet.http.HttpSession;

import com.spring.member.dvo.MemberVO;

public interface MemberService {
	public boolean loginCheck(MemberVO vo,HttpSession session)throws Exception;
	public void join(MemberVO vo)throws Exception;
	public MemberVO getMember(MemberVO vo)throws Exception;
	public void logout(HttpSession session)throws Exception;
	public boolean checkId(String id)throws Exception;
	public String findEmail(String email);
	public void memberUpdate(MemberVO vo)throws Exception;
}
