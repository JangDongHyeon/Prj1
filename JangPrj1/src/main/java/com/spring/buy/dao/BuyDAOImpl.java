package com.spring.buy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.buy.dvo.OrderBuyVO;
@Repository
public class BuyDAOImpl implements BuyDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean orderIn(OrderBuyVO p) {
		// TODO Auto-generated method stub
		int result=sqlSession.insert("buy.orderIn",p);
		return result==1?true:false;
	}

	@Override
	public boolean orderDetailIn(OrderBuyVO vo) {
		// TODO Auto-generated method stub
		int result=sqlSession.insert("buy.orderDetailIn",vo);
		return result==1?true:false;
	}

	@Override
	public List<OrderBuyVO> orderDetailSel(OrderBuyVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("buy.orderDetailSel",vo);
	}

	@Override
	public int oseqMax() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("buy.oseqMax");
	}

	@Override
	public boolean oderDel(int oseq) {
		// TODO Auto-generated method stub
		int result=sqlSession.insert("buy.orderDel",oseq);
		return result==1?true:false;
	}

	@Override
	public void orderDetailDel(int oseq) {
		// TODO Auto-generated method stub
		sqlSession.insert("buy.orderDetailDel",oseq);
	}

}
