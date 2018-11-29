package com.spring.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.admin.service.AdminService;
import com.spring.qna.dvo.QnaVO;
import com.spring.qna.service.QnaService;
import com.spring.shoping.contoller.ShopingController;
import com.spring.shoping.dao.ShopingDAO;
import com.spring.shoping.dvo.ProductVO;
import com.spring.shoping.service.ShopingService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;
	@Autowired
	private ShopingService shopingService;
	@Autowired
	private QnaService qnaService;
	
	
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
	public String adminProList(@ModelAttribute("searchIn")SearchVO vo,Model model) {
		logger.info("productList called");
		
		
		int count=adminService.AdpageCount(vo);
		PageMaker maker=new PageMaker(vo, count);

		model.addAttribute("pageMaker",maker);
		
		int startPage=maker.getStartPageto();
		int endPage=maker.getEndPageto();
	
		vo.setEndPageto(endPage);vo.setStartPageto(startPage);
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
	public String amdinInsertPOST(MultipartFile uploadFile ,ProductVO vo,RedirectAttributes rttr) {
		
		String uploadFolder="C:\\Users\\장동현\\eclipse-workspace7\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JangPrj1\\resources\\product_images";
	
		
		logger.info(uploadFolder);
		logger.info("===================");
		logger.info("Upload file name:"+uploadFile.getOriginalFilename());
		logger.info("upload file size:"+uploadFile.getSize());
		String uploadFileName=uploadFile.getOriginalFilename();
		uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		logger.info("only file name: "+uploadFileName);
		UUID uuid=UUID.randomUUID();
		uploadFileName=uuid.toString()+"_"+uploadFileName;
		
		
		File saveFile=new File(uploadFolder,uploadFileName);
		
		try {
			uploadFile.transferTo(saveFile);
			vo.setImage(uploadFileName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// TODO: handle exception
		}
		
		if(adminService.AdproductInsert(vo)) rttr.addFlashAttribute("msg","추가완료");
		return "redirect:/admin/productList";
	}
	@RequestMapping("/adDetail")
	public String adminDetail(@ModelAttribute("searchIn")SearchVO searchVO,@RequestParam("pseq")int pseq,Model model) throws Exception {
		logger.info("adDetail");
		
		
		model.addAttribute("vo",shopingService.readItem(pseq));
		
		return "admin/adDetail";
		
		
	}
	@RequestMapping("/delete")
	public String adDelete(@ModelAttribute("searchIn")SearchVO searchVO,ProductVO vo) {
		logger.info("adDelete");
		String uploadFolder="C:\\Users\\장동현\\eclipse-workspace7\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JangPrj1\\resources\\product_images";
		File file=new File(uploadFolder,vo.getImage());
		file.delete();
		adminService.AdProDelete(vo);
		
		return "redirect:/admin/productList";
		
	}
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String adModify(@ModelAttribute("searchIn")SearchVO searchVO,Model model,@RequestParam("pseq")int pseq) throws Exception {
		String kind[]= {"Heels","Boots","Sandals","Sneakers","On Sale"};
		model.addAttribute("kindList",kind);
		model.addAttribute("productVO",shopingService.readItem(pseq));
		
		return "admin/ProModify"; 
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String adModifyPost(@ModelAttribute("searchIn")SearchVO searchVO,Model model,ProductVO vo,MultipartFile uploadFile,
			HttpServletRequest req) throws Exception {
		
		if(!uploadFile.isEmpty()) {
			String uploadFolder="C:\\Users\\장동현\\eclipse-workspace7\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JangPrj1\\resources\\product_images";
			System.out.println("이미지 이름:"+uploadFolder+"\\"+vo.getImage());
			
			File deleteFile=new File(uploadFolder+"\\",URLDecoder.decode(vo.getImage(),"UTF-8"));
			if(deleteFile.exists()) {
			deleteFile.delete();
			}else {
				logger.info("=============================파일이없스비다");
			}
			logger.info(uploadFolder);
			logger.info("===================");
			logger.info("Upload file name:"+uploadFile.getOriginalFilename());
			logger.info("upload file size:"+uploadFile.getSize());
			String uploadFileName=uploadFile.getOriginalFilename();
			uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			logger.info("only file name: "+uploadFileName);
			UUID uuid=UUID.randomUUID();
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			
			
			File saveFile=new File(uploadFolder,uploadFileName);
			try {
				uploadFile.transferTo(saveFile);
				vo.setImage(uploadFileName);
			} catch (Exception e) {
				e.getMessage();
				// TODO: handle exception
			}
		}
		adminService.AdproductUpdate(vo);
		
		
		return "redirect:/admin/ProModify"; 
	}
	//구입
	@RequestMapping(value="orderList",method=RequestMethod.GET)
	public String orderListGet(Model model,@RequestParam(value="keyword",defaultValue="")String id) {
		
		model.addAttribute("orderList",adminService.AdOrderList(id));
		return "admin/orderList";
	}
	@RequestMapping(value="orderList",method=RequestMethod.POST)
	@ResponseBody
	public String orderListPost(@RequestParam("arrayList[]")int[] obseq) {
	
		String msg="";
		for(int p:obseq) {
		adminService.AdOrderUpdate(p);
		msg="success";
		}
		return msg;	
	}
	
	@RequestMapping("/memberList")
	public String memberList(@RequestParam(value="keyword",defaultValue="")String id,Model model) {
		
		model.addAttribute("memberVO",adminService.AdMemberList(id));
		
		return "admin/memberList";
	}
	
	@RequestMapping("/adQnaList")
	public String adQnaList(Model model) {
		model.addAttribute("qnaList",adminService.AdQnaList());
		return "admin/adQnaList";
	}
	@RequestMapping("/qnaDetail")
	public String qnaDetail(Model model,@RequestParam("qseq")int qseq) {
		model.addAttribute("qnaVO",qnaService.qnaDetail(qseq));
		
		return "admin/qnaDetail";
	}
	@RequestMapping("/adModifyQna")
	public String adModifyQna(QnaVO vo) {
		
		adminService.adQnaUpdate(vo);
		
		return "redirect:/admin/adQnaList";
		
	}
	
}
