package com.spring.shoping.service;

public interface LoveService {

public int prLoveCount(int pseq);
	
	public void prLoveInsert(int pseq,String pr_member);
	
	public void prLoveMemberI(int pseq,String pr_member);
	
	public boolean prLoveMemberCh(int pseq,String pr_member);		
	
	public void prLoveMemberD(int pseq,String pr_member,int count);
	
}
