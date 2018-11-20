package com.spring.shoping.dvo;

import java.util.Date;

public class ProReplyVO {
	private int rid;
	private int pseq;
	private String content;
	private Date indate;
	private Date updatedate;
	private String reply;
	
	public ProReplyVO() {
		// TODO Auto-generated constructor stub
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getPseq() {
		return pseq;
	}

	public void setPseq(int pseq) {
		this.pseq = pseq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	@Override
	public String toString() {
		return "ProReplyVO [rid=" + rid + ", pseq=" + pseq + ", content=" + content + ", indate=" + indate
				+ ", updatedate=" + updatedate + ", reply=" + reply + "]";
	}
	
}

