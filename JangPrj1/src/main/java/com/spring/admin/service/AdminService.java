package com.spring.admin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.Critia;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.board.dvo.QnaVO;
import com.spring.buy.dvo.OrderBuyVO;
import com.spring.member.dvo.MemberVO;
import com.spring.shoping.dvo.ProductVO;

public interface AdminService {
	//member 관련
	 public boolean adminCheck(AdminVO vo,HttpSession session);
	 public void logout(HttpSession session);
	//상품 관련
	 public int AdpageCount(Critia vo);
	 public List<ProductVO> AdproductList(Critia vo);
	 public boolean AdproductInsert(ProductVO vo);
	 public void AdProDelete(ProductVO vo);
	 public void AdproductUpdate(ProductVO vo)throws Exception;
	 //구입
	 public List<OrderBuyVO> AdOrderList(String id);
	 public void AdOrderUpdate(int obseq);
	 //회원
	 public List<MemberVO> AdMemberList(String id);
	 //Qna
	 public List<QnaVO> AdQnaList();
	 public void adQnaUpdate(QnaVO vo);
}
