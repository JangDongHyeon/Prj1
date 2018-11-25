package com.spring.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.admin.dvo.AdminVO;
import com.spring.admin.dvo.PageMaker;
import com.spring.admin.dvo.SearchVO;
import com.spring.admin.service.AdminService;
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
	public String adModifyPost(@ModelAttribute("searchIn")SearchVO searchVO,Model model,ProductVO vo,MultipartFile uploadFile) throws Exception {
		if(uploadFile!=null) {
			String uploadFolder="C:\\Users\\장동현\\eclipse-workspace7\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JangPrj1\\resources\\product_images";
			File deleteFile=new File(uploadFolder,vo.getImage());
			deleteFile.delete();
			
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
	
	
}
