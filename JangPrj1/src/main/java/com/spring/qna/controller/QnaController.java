package com.spring.qna.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.qna.dvo.QnaVO;
import com.spring.qna.service.QnaService;



@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@RequestMapping("/qnaList")
	public String qnaList(Model model,HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		model.addAttribute("list",qnaService.qnaList(userId));
		return "qna/qnaList";
	}
	@RequestMapping(value="qnaInsert",method=RequestMethod.GET)
	public String qnaInsertGet(@ModelAttribute("vo")QnaVO vo) {
		
		return "/qna/qnaWrite";
	}
	@RequestMapping(value="qnaInsert",method=RequestMethod.POST)
	public String qnaInsertPost(RedirectAttributes rttr,QnaVO vo,HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		vo.setId(userId);
		
		qnaService.qnaInsert(vo);
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/qna/qnaList";
	}
	@RequestMapping("qnaDel")
	public String qnaDel(RedirectAttributes rttr,int qseq) {
		qnaService.qnaDel(qseq);
		
		rttr.addFlashAttribute("msg","success");
		
		return "redirect:/qna/qnaList";
	}
	@RequestMapping("qnaDetail")
	public String qnaDetail(int qseq,Model model) {
		model.addAttribute("vo",qnaService.qnaDetail(qseq));
		
		return "qna/qnaDetail";
	}
	@RequestMapping("qnaDetailJson")
	@ResponseBody
	public QnaVO qnaDetailJSON(@RequestParam("qseq")int qseq) {
		
		
		return qnaService.qnaDetail(qseq);
	}
	
	
	@RequestMapping(value="qnaModify",method=RequestMethod.POST)
	public String qnaModify(RedirectAttributes rttr,QnaVO vo) {
		qnaService.qnaUpdateContent(vo);
		
		rttr.addFlashAttribute("msg","success");
	
		return "redirect:/qna/qnaList";
	}
	@RequestMapping(value="qnaNewInsert",method=RequestMethod.POST)
	public String qnaNewInsert(RedirectAttributes rttr,QnaVO vo,HttpSession session) {
	String userId=(String)session.getAttribute("userId");
	vo.setId(userId);
	qnaService.qnaNewInsert(vo);
	
	return "redirect:/qna/qnaList";
}
	@RequestMapping("qnaDelAll")
	public String qnaDelAll(@RequestParam("bGroup")int bGroup) {
	
	qnaService.qnaDelAll(bGroup);
	return "redirect:/qna/qnaList";
	
		
	}
	
}
