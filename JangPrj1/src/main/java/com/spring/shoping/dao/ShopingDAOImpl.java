package com.spring.shoping.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shoping.dvo.Critia;
import com.spring.shoping.dvo.PageCatogory;
import com.spring.shoping.dvo.ProductVO;
@Repository
public class ShopingDAOImpl implements ShopingDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ProductVO> bestItem() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("shoping.bestItem");
	}

	@Override
	public List<ProductVO> newItem() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("shoping.newItem");
	}

	@Override
	public ProductVO readItem(int pseq) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("shoping.readItem",pseq);
		
	}

	@Override
	public List<ProductVO> catagory(String kind,Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("kind",kind);
		map.put("pageVO",pageVO);
		
		return sqlSession.selectList("shoping.catagory",map);
	}

	@Override
	public int pageCount(String kind,Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("kind",kind);
		map.put("pageVO",pageVO);
		return sqlSession.selectOne("shoping.pageCount",map);
	}

	@Override
	public String catagoeyTitle(String kind) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("shoping.catagoeyTitle",kind);
	}

	@Override
	public int prLoveCount(int pseq) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("shoping.prLoveCount",pseq);
	}

	@Override
	public void prLoveInsert(int pseq) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("shoping.prLoveInsert",pseq);
	}

	@Override
	public List<ProductVO> allList(Critia pageVO) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		
		map.put("pageVO",pageVO);
		return sqlSession.selectList("shoping.allList",map);
	}

	@Override
	public int pageCount(Critia pageVO) throws Exception {
		Map<String,Object> map=new HashMap<String, Object>();
		
		map.put("pageVO",pageVO);
		return sqlSession.selectOne("shoping.listAllCount",map);
	}

}
