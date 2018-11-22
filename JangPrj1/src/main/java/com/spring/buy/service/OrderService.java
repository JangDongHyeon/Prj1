package com.spring.buy.service;

import java.util.List;

import com.spring.buy.dvo.OrderBuyVO;

public interface OrderService {
	 public void orderIn(OrderBuyVO vo);
	 public boolean orderDetailIn(OrderBuyVO vo);
	 public List<OrderBuyVO> orderDetailSel(OrderBuyVO vo);
	 public boolean oderDel(int oseq);
	 public void orderIn(OrderBuyVO vo,int count);
}
