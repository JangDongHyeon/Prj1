package com.spring.shoping.dao;

import java.util.List;

import com.spring.shoping.dvo.Critia;
import com.spring.shoping.dvo.PageCatogory;
import com.spring.shoping.dvo.ProductVO;

public interface ShopingDAO {
	public List<ProductVO> bestItem() throws Exception;
	public List<ProductVO> newItem() throws Exception;
	public ProductVO readItem(int pseq)throws Exception;
	public List<ProductVO> catagory(String kind,Critia pageVO)throws Exception;
	public int pageCount(String kind,Critia pageVO)throws Exception;
	public int pageCount(Critia pageVO)throws Exception;
	public String catagoeyTitle(String kind)throws Exception;
	public int prLoveCount(int pseq)throws Exception;
	public void prLoveInsert(int pseq)throws Exception;
	public List<ProductVO> allList(Critia pageVO)throws Exception;
	
}
