package com.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.member.dvo.MemberVO;
import com.spring.member.service.MemberService;
import com.spring.shoping.contoller.ShopingController;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(ShopingController.class);
	@Autowired
	private MemberService membetService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String loginGet() {
		logger.info("loginGet.....called");
		return "member/login";
	}
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginPost(MemberVO vo,HttpSession session,Model model) throws Exception {
		logger.info("loginPost.....called");
		if(membetService.loginCheck(vo, session)) {
			return "redirect:/shoping/main";
			
		}
		model.addAttribute("msg","fail");
		return "member/login";
	}
	@RequestMapping(value="join",method=RequestMethod.GET)
	public String joinGet()throws Exception {
		logger.info("joinGet.....called");
		return "member/join";
	}
	@RequestMapping(value="join",method=RequestMethod.POST)
	public String joinPost(MemberVO vo,@RequestParam("id")String id,RedirectAttributes rttr) throws Exception{
		logger.info("joinPost.....called");
		membetService.join(vo);
		rttr.addFlashAttribute("id",id);
		
		
		return "redirect:/member/login";
	}
	@RequestMapping(value="/checkId/{id}")
	@ResponseBody
	public Map<String, Object> checkId(@PathVariable("id")String id) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		
		if(membetService.checkId(id)) {
			
			map.put("msg","success");
			map.put("id",id);
		}
		
		return map;
		
		
	}
	@RequestMapping(value="/memberEmFind/{email}")
	@ResponseBody
	public String memberEmFind(@PathVariable("email")String email) {
		System.out.println(email);
		System.out.println(membetService.findEmail(email));
		return membetService.findEmail(email);
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session,Model model)throws Exception {
		logger.info("logout.....called");
		membetService.logout(session);
		model.addAttribute("msg","logout");
		return "member/login";
	}
	
}
