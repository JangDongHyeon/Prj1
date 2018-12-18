package com.spring.cart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.cart.dvo.CartVO;
import com.spring.cart.service.CartService;
import com.spring.shoping.contoller.ShopingController;
import com.spring.shoping.dvo.SearchVO;


@RequestMapping("/cart/*")
@Controller
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cartList")
	public String cartList(HttpSession session,Model model){
		logger.info("cartList...called");
		String userId=(String)session.getAttribute("userId");
	
		model.addAttribute("list",cartService.cartList(userId));
		
		return "cart/cartList";
	}
	@RequestMapping("/cartDel")
	@ResponseBody
	public String cartDelete(@RequestParam(value="charray[]")List<Integer> cseq) {
		logger.info("cartDelJson...called");
		String msg="";
		for(Integer i:cseq) {
		if(cartService.cartDelete(i)) 
			msg="success";
		
		}
		
		
		return msg;
	}
	
	@RequestMapping("/cartDelete")
	public String cartDelete(RedirectAttributes rttr,@RequestParam("checkVal")int[] checkVal) {
		logger.info("cartDelete...called");
		String msg="";
		for(int i :checkVal) {
		if(cartService.cartDelete(i)) {
			msg="success";
			
		}
		}
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/cart/cartList";
	}
	@RequestMapping(value="/cartInsert",method=RequestMethod.POST)
	public String cartInsert(CartVO vo,RedirectAttributes rttr,HttpSession session,SearchVO seVO) {
		logger.info("cartInsertPost...called");
		String userId=(String)session.getAttribute("userId");
		vo.setId(userId);
		
		String msg="";
		if(cartService.cartInsert(vo)) {
			msg="success";
			rttr.addFlashAttribute("msg",msg);
		}
		return "redirect:/cart/cartList";
	}

}
