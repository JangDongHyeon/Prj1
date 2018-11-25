package com.spring.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.Critia;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.shoping.dvo.ProductVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
 
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String adminCheck(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.adminCheck",id);
	}

	@Override
	public AdminVO getAdmin(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.getAdmin",id);
	}

	@Override
	public int AdpageCount(Critia vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("admin.AdpageCount",vo);
	}

	@Override
	public List<ProductVO> AdproductList(Critia vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("admin.AdproductList",vo);
	}

	@Override
	public boolean AdproductInsert(ProductVO vo) {
		// TODO Auto-generated method stub
		int result=sqlSession.insert("admin.AdproductInsert",vo);
		return result==1?true:false;
	}

	@Override
	public void AdProDelete(ProductVO vo) {
		// TODO Auto-generated method stub
		sqlSession.delete("admin.AdProDelete",vo);
		
		
	}

	@Override
	public void AdproductUpdate(ProductVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update("admin.AdproductUpdate",vo);
	}

}
