package com.spring.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.admin.controller.AdminController;

@Controller
@RequestMapping("/BFile/*")
public class BoardFileController {
	private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
	
	@RequestMapping(value="uploadAjaxFile",method=RequestMethod.POST)
	public void uploadAjaxFilePost() {
		
	}
	
	
	
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}
}
