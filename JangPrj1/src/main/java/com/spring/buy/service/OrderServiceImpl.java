package com.spring.buy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.buy.dao.BuyDAO;
import com.spring.buy.dvo.OrderBuyVO;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private BuyDAO buyDAO;
	
	@Override
	public void orderIn(OrderBuyVO vo) {
		// TODO Auto-generated method stub
		
		if(buyDAO.orderIn(vo)){
	
		buyDAO.orderDetailIn(vo);
		
		}
		
	
	}

	@Override
	public boolean orderDetailIn(OrderBuyVO vo) {
		// TODO Auto-generated method stub
		return buyDAO.orderDetailIn(vo);
	}

	@Override
	public List<OrderBuyVO> orderDetailSel(OrderBuyVO vo) {
		// TODO Auto-generated method stub
		return buyDAO.orderDetailSel(vo);
	}

	@Override
	public boolean oderDel(int oseq) {
		// TODO Auto-generated method stub
		buyDAO.orderDetailDel(oseq);
		return buyDAO.oderDel(oseq);
	}

}
