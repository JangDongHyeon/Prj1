package com.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.admin.dvo.Critia;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.board.dvo.BReply;

import com.spring.board.service.BReplyService;

@RestController
@RequestMapping("/bReply/*")
public class BReplyController {

	@Autowired
	private BReplyService replyService;
	
	
	@RequestMapping(value="replyList/{bno}/{page}",method=RequestMethod.GET)
	public Map<String, Object> replyList(@PathVariable("bno")int bno,@PathVariable("page")int page){
		
		int count=replyService.breplyCount(bno);
		Critia critia=new Critia();
		critia.setPage(page);
		critia.setNumPage(10);
		PageMaker maker=new PageMaker(critia, count);
		
		critia.setStartPage(maker.getStartPageto());
		critia.setEndPage(maker.getEndPageto());
		Map<String, Object> map=new HashMap<>();
		map.put("boardList",replyService.bReplyList(bno, critia));
		map.put("pageMaker",maker);
		
		
		return map;
	}
	
	@RequestMapping(value="replyInsert/{bno}",method=RequestMethod.POST)
	public String replyInsert(@PathVariable("bno")int bno,@RequestBody BReply bReply,HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		bReply.setR_id(userId);
		bReply.setBno(bno);
		if(replyService.breplyCreate(bReply))
			return "success";
		else
		    return "fail";
	}
	@RequestMapping(value="replyNewInsert",method=RequestMethod.POST)
	public String replyNewInsert(@RequestBody BReply bReply,HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		bReply.setR_id(userId);

		replyService.bReplyNewInsert(bReply);
			return "success";
	}
	@RequestMapping(value="replyUpdate",method=RequestMethod.POST)
	public String replyUpdate(@RequestBody BReply bReply) {
		if(replyService.breplyUpdate(bReply))
			return "success";
		else
			return "fail";
	}
	@RequestMapping("replyDelete/{rno}")
	public String replyDelete(@PathVariable("rno")int rno) {
		replyService.breplyDelete(rno);
			return "success";
		
	}
	
	
	
	
	
	
}
