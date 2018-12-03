package com.spring.board.dvo;

import java.util.Date;

public class BReply {
 private int r_no;
 private int bno;
 private String r_content;
 private String r_id;
 private Date indate;
 private Date updatedate;
 private int bGroup;
	private int bStep;
	private int bIndent;
public int getR_no() {
	return r_no;
}
public void setR_no(int r_no) {
	this.r_no = r_no;
}
public int getBno() {
	return bno;
}
public void setBno(int bno) {
	this.bno = bno;
}
public String getR_content() {
	return r_content;
}
public void setR_content(String r_content) {
	this.r_content = r_content;
}
public String getR_id() {
	return r_id;
}
public void setR_id(String r_id) {
	this.r_id = r_id;
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
 
 
}
