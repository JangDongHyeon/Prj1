package com.spring.shoping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shoping.dao.LoveDAO;

@Service
public class LoveServiceImpl implements LoveService {

	@Autowired
	LoveDAO loveDAO;
	
	
	@Override
	public int prLoveCount(int pseq) {
		// TODO Auto-generated method stub
		return loveDAO.prLoveCount(pseq);
	}

	

	@Override
	public void prLoveMemberI(int pseq, String pr_member) {
		// TODO Auto-generated method stub
		
		loveDAO.prLoveMemberI(pseq,pr_member);
		loveDAO.prLovePlus(pseq);
	}

	@Override
	public boolean prLoveMemberCh(int pseq, String pr_member) {
		// TODO Auto-generated method stub
		return loveDAO.prLoveMemberCh(pseq, pr_member);
	}



	@Override
	public void prLoveMemberD(int pseq, String pr_member,int count) {
		// TODO Auto-generated method stub
		if(count==1) {
			loveDAO.prLoveD(pseq);
		}
		loveDAO.prLoveM(pseq);
		loveDAO.prLoveMemberD(pseq, pr_member);
	}



	@Override
	public void prLoveInsert(int pseq,String pr_member) {
		// TODO Auto-generated method stub
		loveDAO.prLoveInsert(pseq);
		loveDAO.prLoveMemberI(pseq,pr_member);
	}

}
