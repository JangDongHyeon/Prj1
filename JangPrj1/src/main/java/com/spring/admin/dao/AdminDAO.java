package com.spring.admin.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.SearchVO;
import com.spring.shoping.dvo.ProductVO;

public interface AdminDAO {
//member 관련
 public String adminCheck(String id);
 public AdminVO getAdmin(String id);
//상품 관련
 public int AdpageCount(SearchVO vo);
 public List<ProductVO> AdproductList(SearchVO vo);
 public boolean AdproductInsert(ProductVO vo);
 
 
 
}