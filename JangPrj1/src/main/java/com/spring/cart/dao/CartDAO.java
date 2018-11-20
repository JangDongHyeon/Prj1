package com.spring.cart.dao;

import java.util.List;

import com.spring.cart.dvo.CartVO;

public interface CartDAO {
	public List<CartVO> cartList(String userId);
	public int cartDelete(int cseq);
	public int cartInsert(CartVO vo);
	
}
