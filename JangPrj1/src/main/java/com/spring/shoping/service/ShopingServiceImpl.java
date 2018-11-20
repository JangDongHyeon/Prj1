package com.spring.shoping.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shoping.dao.ShopingDAO;
import com.spring.shoping.dvo.Critia;
import com.spring.shoping.dvo.PageCatogory;
import com.spring.shoping.dvo.ProductVO;
@Service
public class ShopingServiceImpl implements ShopingService {
	
	@Autowired
	ShopingDAO shopingDAO;
	
	@Override
	public List<ProductVO> bestItem() throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.bestItem();
	}

	@Override
	public List<ProductVO> newItem() throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.newItem();
	}

	@Override
	public ProductVO readItem(int pseq) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.readItem(pseq);
	}

	@Override
	public List<ProductVO> catagory(String kind,Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.catagory(kind,pageVO);
	}

	@Override
	public int pageCount(String kind,Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.pageCount(kind,pageVO);
	}

	@Override
	public String catagoeyTitle(String kind) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.catagoeyTitle(kind);
	}

	@Override
	public int prLoveCount(int pseq) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.prLoveCount(pseq);
	}

	@Override
	public void prLoveInsert(int pseq) throws Exception {
		// TODO Auto-generated method stub
		shopingDAO.prLoveInsert(pseq);
	}

	@Override
	public List<ProductVO> allList(Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.allList(pageVO);
	}

	@Override
	public int pageCount(Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		return shopingDAO.pageCount(pageVO);
	}

}
