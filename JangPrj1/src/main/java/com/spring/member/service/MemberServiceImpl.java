package com.spring.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.member.dao.MemberDAO;
import com.spring.member.dvo.MemberVO;
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public boolean loginCheck(MemberVO vo, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		boolean  result=memberDAO.loginCheck(vo);
		if(result) {
			MemberVO mem=memberDAO.getMember(vo);
			session.setAttribute("userId",mem.getId());
			session.setAttribute("userName",mem.getName());
		}
		return result;
	}

	@Override
	public void join(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		memberDAO.join(vo);
	}

	@Override
	public MemberVO getMember(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.getMember(vo);
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		if(session.getAttribute("userId")!=null) {
		session.invalidate();
		}
	}

	@Override
	public boolean checkId(String id) throws Exception {
		// TODO Auto-generated method stub
		
		
		return memberDAO.checkId(id);
	}

	@Override
	public String findEmail(String email) {
		// TODO Auto-generated method stub
		return memberDAO.findEmail(email);
	}

	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		 memberDAO.memberUpdate(vo);
	}

}
