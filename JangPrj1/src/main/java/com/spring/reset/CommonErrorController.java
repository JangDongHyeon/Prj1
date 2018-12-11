package com.spring.reset;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.admin.controller.AdminController;

@Controller
@RequestMapping("/common/")
public class CommonErrorController {	
	private static final Logger logger = LoggerFactory.getLogger(CommonErrorController.class);
	
	@RequestMapping(value="/throwable")
	public String throwable(HttpServletRequest request,Model model) {
		logger.info("throwable");
		pageErrorLog(request);
		model.addAttribute("msg","throwable예외가 발생했습니다");
		return "common/error";
	}
	
	@RequestMapping(value="/exception")
	public String exception(HttpServletRequest request,Model model) {
		logger.info("exception");
		pageErrorLog(request);
		model.addAttribute("msg","exception예외가 발생했습니다");
		return "common/error";
	}
	
	@RequestMapping(value="/400")
	public String page400(HttpServletRequest request,Model model) {
		logger.info("page error400");
		pageErrorLog(request);
		model.addAttribute("msg","400예외가 발생했습니다 잘못된 요청입니다");
		return "common/error";
	}
	
	@RequestMapping(value="/403")
	public String page403(HttpServletRequest request,Model model) {
		logger.info("page error 403");
		pageErrorLog(request);
		model.addAttribute("msg","403예외가 발생했습니다 접근이 금지되었습니다");
		return "common/error";
	}
	
	@RequestMapping(value="/404")
	public String page404(HttpServletRequest request,Model model) {
		logger.info("page error 404");
		pageErrorLog(request);
		model.addAttribute("msg","404예외가 발생했습니다 요청하신 페이지는 존재하지 않습니다.");
		return "common/error";
	}
	
	@RequestMapping(value="/405")
	public String page405(HttpServletRequest request,Model model) {
		logger.info("page error 405");
		pageErrorLog(request);
		model.addAttribute("msg","405예외가 발생했습니다 요청된 메소드가 혀용되지 않습니다");
		return "common/error";
	}

	@RequestMapping(value="/500")
	public String page500(HttpServletRequest request,Model model) {
		logger.info("page error 500");
		pageErrorLog(request);
		model.addAttribute("msg","500예외가 발생했습니다  서버에 오류가 발생하였습니다");
		return "common/error";
	}
	
	@RequestMapping(value="/503")
	public String page503(HttpServletRequest request,Model model) {
		logger.info("page error 503");
		pageErrorLog(request);
		model.addAttribute("msg","503예외가 발생했습니다 서비스를 사용할 수 없습니다");
		return "common/error";
	}
private void pageErrorLog(HttpServletRequest request) {
	logger.info("status_code: "+request.getAttribute("javax.servlet.error.status_code"));
	logger.info("exception_type: "+request.getAttribute("javax.servlet.error.exception_type"));
	logger.info("message: "+request.getAttribute("javax.servlet.error.message"));
	logger.info("request_uri: "+request.getAttribute("javax.servlet.error.request_uri"));
	logger.info("exception: "+request.getAttribute("javax.servlet.error.exception"));
	logger.info("servlet_name: "+request.getAttribute("javax.servlet.error.servlet_name"));
	
}
	
	
}
