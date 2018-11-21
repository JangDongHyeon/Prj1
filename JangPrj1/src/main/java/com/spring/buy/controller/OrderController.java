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
			
		
		
		rttr.addFlashAttribute("searchIn",critia);
		
		return "redirect:/order/mypage";
	}
	@RequestMapping(value="/buyAll",method=RequestMethod.POST)
	@ResponseBody
	public String orderBuyAll(HttpSession session,
		@RequestParam(value = "kind", defaultValue = "0") String kind,
		SearchVO critia,@RequestBody List<OrderBuyVO> list) {
		System.out.println("1111");
		String id=(String)session.getAttribute("userId");
		
		String msg="";

		
			for(OrderBuyVO i:list) {
				i.setId(id);
				orderService.orderIn(i);
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
	@RequestMapping("del/{oseq}")
	@ResponseBody
	public String orderDel(@PathVariable("oseq")int oseq) {
		System.out.println("aaaaaaa");
		String msg="";

		if(orderService.oderDel(oseq)) {
			msg="success";
		}
		
		return msg;
		
	}
	
}
