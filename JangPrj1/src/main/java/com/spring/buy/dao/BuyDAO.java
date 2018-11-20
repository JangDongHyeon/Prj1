package com.spring.buy.dao;

import java.util.List;

import com.spring.buy.dvo.OrderBuyVO;

public interface BuyDAO {
 public boolean orderIn(OrderBuyVO p);
 public boolean orderDetailIn(OrderBuyVO p);
 public List<OrderBuyVO> orderDetailSel(OrderBuyVO p);
 public int oseqMax();
 public boolean oderDel(int oseq);
 public void orderDetailDel(int oseq);
}
