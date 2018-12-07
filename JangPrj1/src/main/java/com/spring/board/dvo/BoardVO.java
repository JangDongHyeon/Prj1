package com.spring.board.dvo;

import java.util.Date;
import java.util.List;

public class BoardVO {
	private int bno;
	private String bid;
	private String content;
	private String subject;
	private Date indate;
	private Date updatedate;
	private int cnt;
	private int love;
	private int replycnt;
	
	private List<BoardFileVO> boardFileList;
	
	
	
	public List<BoardFileVO> getBoardFileList() {
		return boardFileList;
	}
	public void setBoardFileList(List<BoardFileVO> boardFileList) {
		this.boardFileList = boardFileList;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	
	
}
