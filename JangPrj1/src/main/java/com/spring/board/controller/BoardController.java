package com.spring.board.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.board.dao.BoardDAO;
import com.spring.board.dvo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping("boardSelect")
	public String boardSelect(Model model,SearchVO vo) {
		
		int count=boardDAO.boardPageCount(vo);
		vo.setNumPage(10);
		PageMaker maker=new PageMaker(vo, count);
		vo.setStartPage(maker.getStartPageto());
		vo.setEndPage(vo.getEndPageto());
		Map<String, Object> map=new HashMap<>();
		map.put("boardList",boardDAO.boardSelect(vo));
		map.put("pageMaker",maker);
		model.addAttribute("map",map);
		return "board/boardList";
	}
	@RequestMapping(value="boardInsert",method=RequestMethod.GET)
	public String boardInsertGET() {
		return "board/boardInsert";
	}
	
	@RequestMapping(value="boardInsert",method=RequestMethod.POST)
	public String boardInsertPOST(BoardVO vo,RedirectAttributes rttr,HttpSession session) {
		String id=(String)session.getAttribute("userId");
		vo.setBid(id);
		if(boardDAO.boardInsert(vo))
		rttr.addFlashAttribute("msg","등록되었습니다");
		
		
		return "redirect:/board/boardInsert";
	}
	@RequestMapping(value="boardUpdate",method=RequestMethod.GET)
	public String boardUpdateGET() {
		return "board/boardUpdate";
	}
	
	
	
	
}
