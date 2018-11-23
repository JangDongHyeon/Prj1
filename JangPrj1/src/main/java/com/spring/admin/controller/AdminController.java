package com.spring.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.admin.service.AdminService;
import com.spring.shoping.dvo.ProductVO;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String adminLoginGet() {
		
		return "admin/loginForm";
		
		
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String adminLoginPost(AdminVO vo,Model model,HttpSession session) {
		String msg="";
	
		if(!adminService.adminCheck(vo, session)){
			msg="아이디 및 비밀번호를 확인해주세요";
			model.addAttribute("msg",msg);
			return "/admin/loginForm";
		}
		return "redirect:/admin/productList";
		
	}
	@RequestMapping("/logout")
	public String adminLogout(HttpSession session,RedirectAttributes rttr) {
		adminService.logout(session);
		rttr.addFlashAttribute("msg","로그아웃");
		return "redirect:/admin/login";
	}
	@RequestMapping("/productList")
	public String adminProList(SearchVO vo,Model model) {
		int count=adminService.AdpageCount(vo);
		PageMaker maker=new PageMaker(vo, count);
		model.addAttribute("pageMaker",maker);
		model.addAttribute("list",adminService.AdproductList(vo));
	
		return "admin/productList";
	
	}
	@RequestMapping(value="/adminInsert",method=RequestMethod.GET)
	public String amdinInsertGet(Model model) {
		String kind[]= {"Heels","Boots","Sandals","Sneakers","On Sale"};
		model.addAttribute("kindList",kind);
		return "admin/write";
	}
	
	@RequestMapping(value="/adminInsert",method=RequestMethod.POST)
	public String amdinInsertPOST(ProductVO vo,RedirectAttributes rttr) {
		if(adminService.AdproductInsert(vo)) rttr.addFlashAttribute("msg","추가완료");
		return "redirect:/admin/adminProList";
	}
	
}
