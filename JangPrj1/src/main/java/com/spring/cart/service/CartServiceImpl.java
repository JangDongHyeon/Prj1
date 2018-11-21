package com.spring.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cart.dao.CartDAO;
import com.spring.cart.dvo.CartVO;
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public List<CartVO> cartList(String userId) {
		// TODO Auto-generated method stub
		return cartDAO.cartList(userId);
	}

	@Override
	public boolean cartDelete(int cseq) {
		// TODO Auto-generated method stub
		int result=cartDAO.cartDelete(cseq);
		return result==1?true:false;
	}

	@Override
	public boolean cartInsert(CartVO vo) {
		// TODO Auto-generated method stub
	
		int result=cartDAO.cartInsert(vo);
		return result==1?true:false;
	}

	@Override
	public List<CartVO> cartSelPseq(String userId) {
		// TODO Auto-generated method stub
		return cartDAO.cartSelPseq(userId);
	}

}
