package com.spring.shoping.dao;

public interface LoveDAO {

	public int prLoveCount(int pseq);
	
	public void prLovePlus(int pseq);
	
	public void prLoveMemberI(int pseq,String pr_member);
	
	public boolean prLoveMemberCh(int pseq,String pr_member);
	
	public void prLoveM(int pseq);
	
	public void prLoveMemberD(int pseq,String pr_member);

	public void prLoveInsert(int pseq);
	
	public void prLoveD(int pseq);
	
	
}
