package com.spring.cart.service;

import java.util.List;

import com.spring.cart.dvo.CartVO;

public interface CartService {
	public List<CartVO> cartList(String userId);
	public boolean cartDelete(int cseq);
	public boolean cartInsert(CartVO vo);
}
