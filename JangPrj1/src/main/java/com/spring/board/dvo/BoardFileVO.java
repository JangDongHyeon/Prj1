package com.spring.board.dvo;

import java.util.Date;

public class BoardFileVO {
	private String uuid;
	private String uploadPath;
	private String filename;
	private int bno;
	private String filetype;
	private Date reg_data;
	private boolean image;
	
	
	
	public boolean isImage() {
		return image;
	}
	public void setImage(boolean image) {
		this.image = image;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public Date getReg_data() {
		return reg_data;
	}
	public void setReg_data(Date reg_data) {
		this.reg_data = reg_data;
	}
	
	
}