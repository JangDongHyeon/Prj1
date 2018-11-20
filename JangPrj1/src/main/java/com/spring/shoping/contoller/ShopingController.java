package com.spring.shoping.contoller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shoping.dvo.Critia;
import com.spring.shoping.dvo.PageCatogory;
import com.spring.shoping.dvo.ProReplyVO;
import com.spring.shoping.dvo.ProductVO;
import com.spring.shoping.dvo.SearchVO;
import com.spring.shoping.service.LoveService;
import com.spring.shoping.service.ProReplyService;
import com.spring.shoping.service.ShopingService;

@Controller
@RequestMapping("/shoping/*")
public class ShopingController {
	@Autowired
	private ShopingService shopginService;
	@Autowired
	private LoveService loveService;

	private static final Logger logger = LoggerFactory.getLogger(ShopingController.class);

	@RequestMapping("/main")
	public String mainPage(Model model) throws Exception {
		logger.info("shoping/main....called");
		model.addAttribute("bestItem", shopginService.bestItem());
		model.addAttribute("newItem", shopginService.newItem());

		return "shoping/main";
	}

	@RequestMapping("/allList")
	public String AllList(Model model, SearchVO critia, @RequestParam(value = "kind", defaultValue = "0") String kind)
			throws Exception {
		logger.info("shoping/catagory....called");
		Map<String, Object> map = new HashMap<String, Object>();

		int count = shopginService.pageCount(critia);
		PageCatogory curryPage = new PageCatogory(critia, count);

		map.put("title", shopginService.catagoeyTitle(kind));
		map.put("pageMaker", curryPage);
		map.put("kindVO", shopginService.allList(critia));

		model.addAttribute("map", map);

		return "shoping/catagory";

	}

	@RequestMapping("/readItem")
	public String readItem(Model model, @RequestParam("pseq") int pseq,@ModelAttribute("searchIn")SearchVO searchVO) throws Exception {
		logger.info("shoping/readItem....called");
		model.addAttribute("readVO", shopginService.readItem(pseq));

		return "shoping/read";

	}

	@RequestMapping("/catagory")
	public String catagoeyGET(Model model, @RequestParam(value = "kind", defaultValue = "0") String kind,
			@ModelAttribute("searchIn") SearchVO critia) throws Exception {
		logger.info("shoping/catagory....called");
		Map<String, Object> map = new HashMap<String, Object>();
		kind = check(critia, kind);
	
		int count;
		if (kind.equals("0"))
			count = shopginService.pageCount(critia);
		else
			count = shopginService.pageCount(kind,critia);

		PageCatogory curryPage = new PageCatogory(critia, count);

		map.put("title", shopginService.catagoeyTitle(kind));
		map.put("pageMaker", curryPage);
		if (kind.equals("0"))
			map.put("kindVO", shopginService.allList(critia));
		else
			map.put("kindVO", shopginService.catagory(kind, critia));

		model.addAttribute("map", map);

		return "shoping/catagory";

	}

	@RequestMapping("/prLoveCount/{pseq}")
	@ResponseBody
	public int prLoveCount(@PathVariable("pseq") int pseq) throws Exception {

		return loveService.prLoveCount(pseq);

	}

	@RequestMapping("/prLoveInsert/{pseq}")
	@ResponseBody
	public String prLoveInsert(@PathVariable("pseq") int pseq, HttpSession session) throws Exception {

		String pr_member = (String) session.getAttribute("userId");
		String str = "";
		int count = loveService.prLoveCount(pseq);
		if (count <= 0)
			loveService.prLoveInsert(pseq, pr_member);

		else {
			if (loveService.prLoveMemberCh(pseq, pr_member)) {
				loveService.prLoveMemberI(pseq, pr_member);

			} else {
				loveService.prLoveMemberD(pseq, pr_member, count);
				str = "Mai";

			}
		}

		return str;

	}

	public String check(SearchVO vo, String kind) {
		if (vo.getSearch().equals("he"))
			kind = "1";
		else if (vo.getSearch().equals("bt"))
			kind = "2";
		else if (vo.getSearch().equals("sd"))
			kind = "3";
		else if (vo.getSearch().equals("sk"))
			kind = "4";
		else if (vo.getSearch().equals("sl"))
			kind = "5";
		else
			kind = "0";

		return kind;
	}

}
