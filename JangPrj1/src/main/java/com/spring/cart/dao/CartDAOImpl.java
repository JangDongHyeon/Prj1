package com.spring.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.cart.dvo.CartVO;
@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CartVO> cartList(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cart.cartList",userId);
	}

	@Override
	public int cartDelete(int cseq) {
		// TODO Auto-generated method stub
		return sqlSession.delete("cart.cartDelete",cseq);
	}

	@Override
	public int cartInsert(CartVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("cart.cartInsert",vo);
	}

	@Override
	public List<CartVO> cartSelPseq(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cart.cartSelPseq",userId);
	}

}
