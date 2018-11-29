package com.spring.qna.dvo;

import java.util.Date;

public class QnaVO {
	private int qseq;
	private String subject;
	private String content;
	private String reply;
	private String id;
	private String rep;
	private Date indate;
	private int bGroup;
	private int bStep;
	private int bIndent;
	
	public QnaVO() {
		// TODO Auto-generated constructor stub
		this.bStep=0;
		this.bIndent=0;
	}
	
	public int getQseq() {
		return qseq;
	}
	public void setQseq(int qseq) {
		this.qseq = qseq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRep() {
		return rep;
	}
	public void setRep(String rep) {
		this.rep = rep;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	@Override
	public String toString() {
		return "QnaVO [qseq=" + qseq + ", subject=" + subject + ", content=" + content + ", reply=" + reply + ", id="
				+ id + ", rep=" + rep + ", indate=" + indate + ", bGroup=" + bGroup + ", bStep=" + bStep + ", bIndent="
				+ bIndent + "]";
	}
	
	
}
