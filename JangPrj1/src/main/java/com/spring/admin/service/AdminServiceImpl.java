package com.spring.admin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.admin.dao.AdminDAO;
import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.Critia;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.shoping.dvo.ProductVO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDAO admindao;
	
	@Override
	public boolean adminCheck(AdminVO vo,HttpSession session) {
		// TODO Auto-generated method stub
		 String pwd=admindao.adminCheck(vo.getId());
		 if(pwd==null) return false;
		 else if(pwd.equals(vo.getPwd())) {
			 AdminVO vo1=admindao.getAdmin(vo.getId());
				session.setAttribute("adminId",vo.getName());
				session.setAttribute("adminName", vo1.getName());
				return true;
		 }else return false;
		
	}

	@Override
	public void logout(HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();
	}

	@Override
	public int AdpageCount(Critia vo) {
		// TODO Auto-generated method stub
		return admindao.AdpageCount(vo);
	}

	@Override
	public List<ProductVO> AdproductList(Critia vo) {
		// TODO Auto-generated method stub
		return admindao.AdproductList(vo);
	}

	@Override
	public boolean AdproductInsert(ProductVO vo) {
		// TODO Auto-generated method stub
		return admindao.AdproductInsert(vo);
	}

	@Override
	public void AdProDelete(ProductVO vo) {
		// TODO Auto-generated method stub
		admindao.AdProDelete(vo);
	}

	@Override
	public void AdproductUpdate(ProductVO vo) {
		// TODO Auto-generated method stub
		admindao.AdproductUpdate(vo);
	}

}
