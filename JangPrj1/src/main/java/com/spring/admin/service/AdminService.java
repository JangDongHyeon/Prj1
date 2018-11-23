package com.spring.admin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.SearchVO;
import com.spring.shoping.dvo.ProductVO;

public interface AdminService {
	//member 관련
	 public boolean adminCheck(AdminVO vo,HttpSession session);
	 public void logout(HttpSession session);
	//상품 관련
	 public int AdpageCount(SearchVO vo);
	 public List<ProductVO> AdproductList(SearchVO vo);
	 public boolean AdproductInsert(ProductVO vo);
}
