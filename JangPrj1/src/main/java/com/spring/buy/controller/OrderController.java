package com.spring.buy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.buy.dvo.OrderBuyVO;
import com.spring.buy.service.OrderService;
import com.spring.cart.dvo.CartVO;
import com.spring.shoping.dvo.SearchVO;

@Controller
@RequestMapping("/order/*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/buy",method=RequestMethod.POST)
	public String orderBuy(RedirectAttributes rttr,HttpSession session,OrderBuyVO vo,
		@RequestParam(value = "kind", defaultValue = "0") String kind,
		SearchVO critia) {
	
		String id=(String)session.getAttribute("userId");
		
		vo.setId(id);
	
				orderService.orderIn(vo);
			
		
		rttr.addFlashAttribute("msg","success");
		rttr.addFlashAttribute("searchIn",critia);
		
		return "redirect:/order/mypage";
	}
	@RequestMapping(value="/buyAll",method=RequestMethod.POST)
	@ResponseBody
	public String orderBuyAll(HttpSession session,
		@RequestParam(value = "kind", defaultValue = "0") String kind,
		SearchVO critia,@RequestParam(value="listPseq[]") List<Integer> listPseq,
		@RequestParam(value="listq[]")List<Integer>listq,
		@RequestParam(value="listc[]")List<Integer>listc) {
	
		String id=(String)session.getAttribute("userId");
		OrderBuyVO vo=new OrderBuyVO();
		int cseq;
		String msg="";
		vo.setId(id);
		for(int i=0;i<listPseq.size();i++) {
			vo.setPseq(listPseq.get(i));
			vo.setQuantity(listq.get(i));
			cseq=listc.get(i);
			orderService.orderIn(vo,cseq);
			msg="success";
		}
		
			
		
	
		
	
		
		return msg;
	}
	
	@RequestMapping("mypage")
	public String orderMypage(Model model,HttpSession session,OrderBuyVO vo, @RequestParam(value = "kind", defaultValue = "0") String kind,
			@ModelAttribute("searchIn") SearchVO critia) {
		String id=(String)session.getAttribute("userId"); 
		vo.setId(id);
		
		model.addAttribute("list",orderService.orderDetailSel(vo));
		
		return "order/mypage";
		
		
	}
	@RequestMapping("mypageJson")
	@ResponseBody
	public List<OrderBuyVO> orderMypageJson(Model model,HttpSession session,OrderBuyVO vo, @RequestParam(value = "kind", defaultValue = "0") String kind,
			@ModelAttribute("searchIn") SearchVO critia) {
		String id=(String)session.getAttribute("userId"); 
		vo.setId(id);
		
		
		return orderService.orderDetailSel(vo);
		
		
	}
	@RequestMapping("del/{oseq}")
	@ResponseBody
	public String orderDel(@PathVariable("oseq")int oseq) {
	
		String msg="";

		if(orderService.oderDel(oseq)) {
			msg="success";
		}
		
		return msg;
		
	}
	
}
