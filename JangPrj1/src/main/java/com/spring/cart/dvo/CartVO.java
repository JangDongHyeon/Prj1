package com.spring.cart.dvo;

import java.util.Date;

public class CartVO {
	private int cseq;
	private String id;
	private int pseq;
	private int quantity;
	private String result;
	private Date indate;
	private String image;
	private String pname;
	private int price;
	private int sumding;
	
	
	
	public int getSumding() {
		return sumding;
	}
	public void setSumding(int sumding) {
		this.sumding = sumding;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getCseq() {
		return cseq;
	}
	public void setCseq(int cseq) {
		this.cseq = cseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPseq() {
		return pseq;
	}
	public void setPseq(int pseq) {
		this.pseq = pseq;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	@Override
	public String toString() {
		return "CartVO [cseq=" + cseq + ", id=" + id + ", pseq=" + pseq + ", quantity=" + quantity + ", result="
				+ result + ", indate=" + indate + "]";
	}
	
	
}
